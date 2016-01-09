
package joshua.nrod.stomp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import joshua.nrod.stomp.Command;
import joshua.nrod.stomp.MessageReceiver;

public class Receiver
extends Thread {
    private MessageReceiver _receiver;
    private BufferedReader _input;
    private InputStream _stream;

    protected Receiver() {
    }

    public Receiver(MessageReceiver m, InputStream input) {
        this.setup(m, input);
    }

    protected void setup(MessageReceiver m, InputStream input) {
        this._receiver = m;
        try {
            this._stream = input;
            this._input = new BufferedReader(new InputStreamReader(input, "US-ASCII"));
        }
        catch (UnsupportedEncodingException e) {
            // empty catch block
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void run() {
        try {
            while (!this.isInterrupted()) {
                if (this._input.ready()) {
                    String command = this._input.readLine();
                    if (command.length() <= 0) continue;
                    try {
                        int b;
                        String header;
                        Command c = Command.valueOf(command);
                        HashMap<String, String> headers = new HashMap<String, String>();
                        while ((header = this._input.readLine()).length() > 0) {
                            int ind = header.indexOf(58);
                            String k = header.substring(0, ind);
                            String v = header.substring(ind + 1, header.length());
                            headers.put(k.trim(), v.trim());
                        }
                        StringBuffer body = new StringBuffer();
                        while ((b = this._input.read()) != 0) {
                            body.append((char)b);
                        }
                        try {
                            this._receiver.receive(c, headers, body.toString());
                        }
                        catch (Exception e) {}
                        continue;
                    }
                    catch (Error e) {
                        try {
                            while (this._input.read() != 0) {
                            }
                        }
                        catch (Exception ex) {
                            // empty catch block
                        }
                        try {
                            this._receiver.receive(Command.ERROR, null, e.getMessage() + "\n");
                            continue;
                        }
                        catch (Exception ex) {
                            continue;
                        }
                    }
                }
                if (this._receiver.isClosed()) {
                    this._receiver.disconnect();
                    return;
                }
                try {
                    Thread.sleep(200);
                }
                catch (InterruptedException e) {
                    this.interrupt();
                }
            }
            return;
        }
        catch (IOException e) {
            System.err.println("Stomp exiting because of exception");
            e.printStackTrace(System.err);
            this._receiver.receive(Command.ERROR, null, e.getMessage());
            return;
        }
        catch (Exception e) {
            System.err.println("Stomp exiting because of exception");
            e.printStackTrace(System.err);
            this._receiver.receive(Command.ERROR, null, e.getMessage());
        }
    }
}

