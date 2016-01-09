
package joshua.nrod.stomp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.security.auth.login.LoginException;
import joshua.nrod.stomp.AllowAllAuthenticator;
import joshua.nrod.stomp.Authenticatable;
import joshua.nrod.stomp.Authenticator;
import joshua.nrod.stomp.Command;
import joshua.nrod.stomp.FileQueue;
import joshua.nrod.stomp.IntraVMClient;
import joshua.nrod.stomp.Listener;
import joshua.nrod.stomp.Message;
import joshua.nrod.stomp.MessageReceiver;
import joshua.nrod.stomp.Queue;
import joshua.nrod.stomp.Receiver;
import joshua.nrod.stomp.Stomp;
import joshua.nrod.stomp.Transmitter;

public class Server {
    private Queue _message_queue = new FileQueue();
    private Map _transactions = new HashMap();
    private Map _listeners = new HashMap();
    private ConnectionListener _connection_listener;
    private Authenticator _authenticator = new AllowAllAuthenticator();

    public Server() {
    }

    public Server(int port) throws IOException {
        this(port, null);
    }

    public Server(int port, Authenticator auth) throws IOException {
        this();
        if (port < 0) {
            port = 61626;
        }
        if (auth != null) {
            this._authenticator = auth;
        }
        this.listen(port);
    }

    public void listen(int port) throws IOException {
        this._connection_listener = new ConnectionListener(port, this);
        this._connection_listener.start();
    }

    protected void disconnect(SocketHandler s) {
        this._connection_listener.disconnect(s);
    }

    public void stop() {
        if (this._connection_listener != null) {
            this._connection_listener.shutdown();
        }
        this.close(-1);
        Thread.yield();
    }

    public void setQueue(Queue q) {
        this._message_queue = q;
    }

    public void close(int port) {
        Iterator i = this._listeners.keySet().iterator();
        while (i.hasNext()) {
            Object k = i.next();
            Object s = this._listeners.get(k);
            if (!(s instanceof SocketHandler)) continue;
            SocketHandler sh = (SocketHandler)s;
            if (port != -1 && !sh.isPort(port)) continue;
            sh.interrupt();
            sh.close();
            this._transactions.remove(s);
            this._listeners.remove(k);
        }
    }

    private String mapToStr(Map m) {
        StringBuffer b = new StringBuffer("[ ");
        Iterator keys = m.keySet().iterator();
        while (keys.hasNext()) {
            String k = keys.next().toString();
            b.append(k + " => " + m.get(k) + ", ");
        }
        b.append("]");
        return b.toString();
    }

    protected void receive(Command c, Map h, String b, Authenticatable y) {
        long id = (int)(Math.random() * 10000.0);
        try {
            String receipt;
            ArrayList trans;
            Map error_headers;
            Map map;
            if (c == Command.COMMIT) {
                map = this._transactions;
                synchronized (map) {
                    trans = (ArrayList)this._transactions.remove(y);
                    trans = new ArrayList(trans);
                    Iterator i = ((Set) trans).iterator();
                    while (i.hasNext()) {
                        Message m = (Message)i.next();
                        try {
                            this.receive(m.command(), m.headers(), m.body(), y);
                        }
                        catch (Exception e) {}
                    }
                }
            }
            if (c == Command.ABORT) {
                map = this._transactions;
                synchronized (map) {
                    this._transactions.remove(y);
                }
            }
            if (this._transactions.get(y) != null) {
                map = this._transactions;
                synchronized (map) {
                    ((List)this._transactions.get(y)).add(new Message(c, h, b));
                }
            }
            if (h == null) {
                h = new HashMap();
            }
            String destination = (String)h.get("destination");
            if (c == Command.SEND) {
                if (y instanceof IntraVMClient || this._authenticator.authorizeSend(y.token(), destination)) {
                    trans = (ArrayList) this._listeners;
                    synchronized (trans) {
                        ArrayList l = (ArrayList)this._listeners.get(destination);
                        if (l != null) {
                            l = new ArrayList(l);
                            Iterator i = l.iterator();
                            while (i.hasNext()) {
                                Listener sh = (Listener)i.next();
                                try {
                                    sh.message(h, b);
                                }
                                catch (Exception e) {}
                            }
                        }
                    }
                } else {
                    error_headers = new HashMap<String, String>();
                    error_headers.put((String)"message:", (String)"authorization refused");
                    error_headers.put("type:", "send");
                    error_headers.put("channel:", destination);
                    y.error(error_headers, "The message:\n-----\n" + b + "\n-----\nAuthentication token refused for this channel");
                }
            } else if (c == Command.SUBSCRIBE) {
                if (y instanceof IntraVMClient || this._authenticator.authorizeSubscribe(y.token(), destination)) {
                    error_headers = this._listeners;
                    synchronized (error_headers) {
                        ArrayList<Authenticatable> l = (ArrayList<Authenticatable>)this._listeners.get(destination);
                        if (l == null) {
                            l = new ArrayList<Authenticatable>();
                            this._listeners.put(destination, l);
                        }
                        if (!l.contains(y)) {
                            l.add(y);
                        }
                    }
                } else {
                    error_headers = new HashMap();
                    error_headers.put("message:", "authorization refused");
                    error_headers.put("type:", "subscription");
                    error_headers.put("channel:", destination);
                    y.error(error_headers, "The message:\n-----\n" + b + "\n-----\nAuthentication token refused for this channel");
                }
            } else {
                if (c == Command.UNSUBSCRIBE) {
                    error_headers = this._listeners;
                    synchronized (error_headers) {
                        List l = (List)this._listeners.get(destination);
                        if (l != null) {
                            l.remove(y);
                        }
                    }
                }
                if (c == Command.BEGIN) {
                    error_headers = this._transactions;
                    synchronized (error_headers) {
                        ArrayList trans2 = new ArrayList();
                        this._transactions.put(y, trans2);
                    }
                }
                if (c == Command.DISCONNECT) {
                    error_headers = this._listeners;
                    synchronized (error_headers) {
                        Iterator i = this._listeners.values().iterator();
                        while (i.hasNext()) {
                            List l = (List)i.next();
                            l.remove(y);
                        }
                    }
                }
            }
            if (h != null && (receipt = (String)h.get("receipt")) != null) {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("receipt-id", receipt);
                y.receive(Command.RECEIPT, headers, null);
            }
        }
        catch (Exception e) {
            // empty catch block
        }
    }

    public Stomp getClient() {
        return new IntraVMClient(this);
    }

    public static final void main(String[] args) {
        if (args.length != 1) {
            System.err.println("A single argument -- a port -- is required");
            System.exit(1);
        }
        int port = Integer.valueOf(args[0]);
        System.out.println("v0.4.1 (75) (c)2005 Sean Russell");
        try {
            new Server(port);
        }
        catch (Exception e) {
            System.err.println("Failed to start server");
            e.printStackTrace(System.err);
        }
    }

    protected class SocketHandler
    extends Receiver
    implements Listener,
    Authenticatable {
        private InputStream _input;
        private OutputStream _output;
        private Socket _socket;
        private Server _server;
        private Object _client_token;
        private boolean _authenticated;

        public SocketHandler(Socket sock, Server s) throws IOException {
            this._authenticated = false;
            this._input = sock.getInputStream();
            this._output = sock.getOutputStream();
            this._socket = sock;
            this._server = s;
            this.setup(this, this._input);
        }

        public Object token() {
            return this._client_token;
        }

        public boolean isClosed() {
            return this._socket.isClosed();
        }

        protected boolean isPort(int port) {
            return this._socket.getPort() == port;
        }

        protected void close() {
            try {
                this._socket.shutdownInput();
                this._input.close();
            }
            catch (IOException e) {
                // empty catch block
            }
            try {
                this._socket.shutdownOutput();
                this._output.close();
            }
            catch (IOException e) {
                // empty catch block
            }
            try {
                this._socket.close();
            }
            catch (IOException e) {
                // empty catch block
            }
        }

        public void disconnect() {
            this.close();
        }

        public void receive(Command c, Map h, String b) {
            if (c == Command.CONNECT) {
                String login = (String)h.get("login");
                String passcode = (String)h.get("passcode");
                try {
                    this._client_token = Server.this._authenticator.connect(login, passcode);
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("session", String.valueOf(this.hashCode()));
                    this.transmit(Command.CONNECTED, headers, null);
                    this._authenticated = true;
                }
                catch (LoginException e) {
                    this.transmit(Command.ERROR, null, "Login failed: " + e.getMessage());
                }
            } else {
                if (!this._authenticated) {
                    this.transmit(Command.ERROR, null, "Not CONNECTed, or not authorized");
                    return;
                }
                if (c == Command.DISCONNECT) {
                    String receipt;
                    if (h != null && (receipt = (String)h.get("receipt")) != null) {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("receipt-id", receipt);
                        this.receive(Command.RECEIPT, headers, null);
                    }
                    this._server.disconnect(this);
                    this.interrupt();
                    Thread.yield();
                    this.close();
                } else if (c == Command.ERROR) {
                    this.error(h, b);
                } else {
                    this._server.receive(c, h, b, this);
                }
            }
        }

        public void message(Map headers, String body) {
            this.transmit(Command.MESSAGE, headers, body);
        }

        public void receipt(Map headers) {
            this.transmit(Command.RECEIPT, headers, null);
        }

        public void error(Map headers, String message) {
            this.transmit(Command.ERROR, headers, message);
        }

        private void transmit(Command c, Map h, String b) {
            try {
                Transmitter.transmit(c, h, b, this._output);
            }
            catch (Exception e) {
                this.interrupt();
                Thread.yield();
                this.close();
            }
        }
    }

    private class ConnectionListener
    extends Thread {
        private int _port;
        private Server _server;
        private ServerSocket _serve_sock;
        private List _handlers;

        protected ConnectionListener(int port, Server server) {
            this._handlers = new ArrayList();
            this._port = port;
            this._server = server;
        }

        public void run() {
            Socket sock = null;
            try {
                this._serve_sock = new ServerSocket(this._port);
                while (!this.isInterrupted()) {
                    sock = this._serve_sock.accept();
                    try {
                        SocketHandler handler = new SocketHandler(sock, this._server);
                        handler.start();
                        this._handlers.add(handler);
                    }
                    catch (IOException e) {
                        e.printStackTrace(System.err);
                    }
                }
            }
            catch (SocketException e) {
            }
            catch (IOException e) {
                e.printStackTrace(System.err);
            }
            catch (Exception e) {
                e.printStackTrace(System.err);
            }
            Iterator i = this._handlers.iterator();
            while (i.hasNext()) {
                try {
                    Thread t = (Thread)i.next();
                    t.interrupt();
                    Thread.yield();
                }
                catch (Exception e) {}
            }
        }

        protected void shutdown() {
            this.interrupt();
            try {
                this._serve_sock.close();
            }
            catch (Exception e) {
                // empty catch block
            }
        }

        protected void disconnect(SocketHandler h) {
            this._handlers.remove(h);
        }
    }

}

