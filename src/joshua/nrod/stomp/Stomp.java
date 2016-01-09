
package joshua.nrod.stomp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import joshua.nrod.stomp.Command;
import joshua.nrod.stomp.Listener;
import joshua.nrod.stomp.Message;

public abstract class Stomp<E> {
    private Map _listeners = new HashMap();
    private List _error_listeners = new ArrayList();
    private Stack _queue = new Stack();
    private List _receipts = new ArrayList();
    protected boolean _connected = false;
    private List _errors = new ArrayList();

    public abstract void disconnect(Map var1);

    protected abstract void transmit(Command var1, Map var2, String var3);

    public void disconnect() {
        this.disconnect(null);
    }

    protected void transmit(Command command, Map header) {
        this.transmit(command, header, null);
    }

    protected void transmit(Command command) {
        this.transmit(command, null, null);
    }

    public void begin() {
        this.transmit(Command.BEGIN);
    }

    public void begin(Map header) {
        this.transmit(Command.BEGIN, header);
    }

    public void commit() {
        this.transmit(Command.COMMIT);
    }

    public void commit(Map header) {
        this.transmit(Command.BEGIN, header);
    }

    public void commitW() throws InterruptedException {
        this.commitW(null);
    }

    public void commitW(Map header) throws InterruptedException {
        String receipt = this.addReceipt(header);
        this.transmit(Command.COMMIT, header);
        this.waitOnReceipt(receipt);
    }

    public void abort() {
        this.transmit(Command.ABORT);
    }

    public void abort(Map header) {
        this.transmit(Command.ABORT, header);
    }

    public void subscribe(String name) {
        this.subscribe(name, null, null);
    }

    public void subscribe(String name, Map header) {
        this.subscribe(name, null, header);
    }

    public void subscribe(String name, Listener listener) {
        this.subscribe(name, listener, null);
    }

    public void subscribe(String name, Listener listener, Map headers) {
        Map map = this._listeners;
        synchronized (map) {
            if (listener != null) {
                ArrayList<Listener> list = (ArrayList<Listener>)this._listeners.get(name);
                if (list == null) {
                    list = new ArrayList<Listener>();
                    this._listeners.put(name, list);
                }
                if (!list.contains(listener)) {
                    list.add(listener);
                }
            }
        }
        if (headers == null) {
            headers = new HashMap<String, String>();
        }
        headers.put("destination", name);
        this.transmit(Command.SUBSCRIBE, headers);
    }

    private String addReceipt(Map header) {
        if (header == null) {
            header = new HashMap<String, String>();
        }
        String receipt = String.valueOf(this.hashCode()) + "&" + System.currentTimeMillis();
        header.put("receipt", receipt);
        return receipt;
    }

    public void subscribeW(String name, Listener listener, Map header) throws InterruptedException {
        String receipt = this.addReceipt(header);
        this.subscribe(name, listener, header);
        this.waitOnReceipt(receipt);
    }

    public void subscribeW(String name, Listener listener) throws InterruptedException {
        this.subscribeW(name, listener, null);
    }

    public void unsubscribe(String name) {
        this.unsubscribe(name, (Map)null);
    }

    public void unsubscribe(String name, Listener l) {
        Map map = this._listeners;
        synchronized (map) {
            List list = (List)this._listeners.get(name);
            if (list != null) {
                list.remove(l);
                if (list.size() == 0) {
                    this.unsubscribe(name);
                }
            }
        }
    }

    public void unsubscribe(String name, Map header) {
        if (header == null) {
            header = new HashMap<String, String>();
        }
        Map map = this._listeners;
        synchronized (map) {
            this._listeners.remove(name);
        }
        header.put("destination", name);
        this.transmit(Command.UNSUBSCRIBE, header);
    }

    public void unsubscribeW(String name) throws InterruptedException {
        this.unsubscribe(name, (Map)null);
    }

    public void unsubscribeW(String name, Map header) throws InterruptedException {
        String receipt = this.addReceipt(header);
        this.unsubscribe(name, (Map)null);
        this.waitOnReceipt(receipt);
    }

    public void sendW(String dest, String mesg) throws InterruptedException {
        this.sendW(dest, mesg, null);
    }

    public void sendW(String dest, String mesg, Map header) throws InterruptedException {
        String receipt = this.addReceipt(header);
        this.send(dest, mesg, header);
        this.waitOnReceipt(receipt);
    }

    public void send(String dest, String mesg) {
        this.send(dest, mesg, null);
    }

    public void send(String dest, String mesg, Map header) {
        if (header == null) {
            header = new HashMap<String, String>();
        }
        header.put("destination", dest);
        this.transmit(Command.SEND, header, mesg);
    }

    public Message getNext() {
        Stack stack = this._queue;
        synchronized (stack) {
            return (Message)this._queue.pop();
        }
    }

    public Message getNext(String name) {
        Stack stack = this._queue;
        synchronized (stack) {
            for (int idx = 0; idx < this._queue.size(); ++idx) {
                Message m = (Message)this._queue.get(idx);
                if (!m.headers().get("destination").equals(name)) continue;
                this._queue.remove(idx);
                return m;
            }
        }
        return null;
    }

    public void addErrorListener(Listener l) {
        List list = this._error_listeners;
        synchronized (list) {
            this._error_listeners.add(l);
        }
    }

    public void delErrorListener(Listener l) {
        List list = this._error_listeners;
        synchronized (list) {
            this._error_listeners.remove(l);
        }
    }

    public boolean hasReceipt(String receipt_id) {
        List list = this._receipts;
        synchronized (list) {
            Iterator<E> i = this._receipts.iterator();
            while (i.hasNext()) {
                String o = (String)i.next();
                if (!o.equals(receipt_id)) continue;
                return true;
            }
        }
        return false;
    }

    public void clearReceipt(String receipt_id) {
        List list = this._receipts;
        synchronized (list) {
            Iterator<E> i = this._receipts.iterator();
            while (i.hasNext()) {
                String o = (String)i.next();
                if (!o.equals(receipt_id)) continue;
                i.remove();
            }
        }
    }

    public void clearReceipts() {
        List list = this._receipts;
        synchronized (list) {
            this._receipts.clear();
        }
    }

    public void waitOnReceipt(String receipt_id) throws InterruptedException {
        List list = this._receipts;
        synchronized (list) {
            while (!this.hasReceipt(receipt_id)) {
                this._receipts.wait();
            }
        }
    }

    public boolean waitOnReceipt(String receipt_id, long timeout) throws InterruptedException {
        List list = this._receipts;
        synchronized (list) {
            while (!this.hasReceipt(receipt_id)) {
                this._receipts.wait(timeout);
            }
            if (this._receipts.contains(receipt_id)) {
                return true;
            }
            return false;
        }
    }

    public boolean isConnected() {
        return this._connected;
    }

    public String nextError() {
        List list = this._errors;
        synchronized (list) {
            if (this._errors.size() == 0) {
                return null;
            }
            return (String)this._errors.remove(0);
        }
    }

    public void receive(Command c, Map h, String b) {
        if (c == Command.MESSAGE) {
            String destination = (String)h.get("destination");
            Map map = this._listeners;
            synchronized (map) {
                ArrayList<E> listeners = (ArrayList<E>)this._listeners.get(destination);
                if (listeners != null) {
                    listeners = new ArrayList<E>(listeners);
                    Iterator<E> i = listeners.iterator();
                    while (i.hasNext()) {
                        Listener l = (Listener)i.next();
                        try {
                            l.message(h, b);
                        }
                        catch (Exception e) {}
                    }
                } else {
                    this._queue.push(new Message(c, h, b));
                }
            }
        }
        if (c == Command.CONNECTED) {
            this._connected = true;
        } else {
            if (c == Command.RECEIPT) {
                this._receipts.add(h.get("receipt-id"));
                List destination = this._receipts;
                synchronized (destination) {
                    this._receipts.notify();
                }
            }
            if (c == Command.ERROR) {
                if (this._error_listeners.size() > 0) {
                    List destination = this._error_listeners;
                    synchronized (destination) {
                        Iterator<E> i = this._error_listeners.iterator();
                        while (i.hasNext()) {
                            try {
                                ((Listener)i.next()).message(h, b);
                            }
                            catch (Exception e) {}
                        }
                    }
                }
                List destination = this._errors;
                synchronized (destination) {
                    this._errors.add(b);
                }
            }
        }
    }
}

