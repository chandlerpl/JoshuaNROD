
package joshua.nrod.stomp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.login.LoginException;
import joshua.nrod.stomp.Command;
import joshua.nrod.stomp.MessageReceiver;
import joshua.nrod.stomp.Receiver;
import joshua.nrod.stomp.Stomp;
import joshua.nrod.stomp.Transmitter;

public class Client
extends Stomp
implements MessageReceiver {
    private Thread _listener;
    private OutputStream _output;
    private InputStream _input;
    private Socket _socket;

    public Client(String server, int port, String login, String pass) throws IOException, LoginException {
        this._socket = new Socket(server, port);
        this._input = this._socket.getInputStream();
        this._output = this._socket.getOutputStream();
        this._listener = new Receiver(this, this._input);
        this._listener.start();
        HashMap<String, String> header = new HashMap<String, String>();
        header.put("login", login);
        header.put("passcode", pass);
        this.transmit(Command.CONNECT, header, null);
        try {
            String error = null;
            while (!this.isConnected() && (error = this.nextError()) == null) {
                Thread.sleep(100);
            }
            if (error != null) {
                throw new LoginException(error);
            }
        }
        catch (InterruptedException e) {
            // empty catch block
        }
    }

    public boolean isClosed() {
        return this._socket.isClosed();
    }

    public void disconnect(Map header) {
        if (!this.isConnected()) {
            return;
        }
        this.transmit(Command.DISCONNECT, header, null);
        this._listener.interrupt();
        Thread.yield();
        try {
            this._input.close();
        }
        catch (IOException e) {
            // empty catch block
        }
        try {
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
        this._connected = false;
    }

    public void transmit(Command c, Map h, String b) {
        try {
            Transmitter.transmit(c, h, b, this._output);
        }
        catch (Exception e) {
            this.receive(Command.ERROR, null, e.getMessage());
        }
    }
}

