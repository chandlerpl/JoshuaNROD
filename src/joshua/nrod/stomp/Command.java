
package joshua.nrod.stomp;

public class Command {
    public static final String ENCODING = "US-ASCII";
    private String _command;
    public static Command SEND = new Command("SEND");
    public static Command SUBSCRIBE = new Command("SUBSCRIBE");
    public static Command UNSUBSCRIBE = new Command("UNSUBSCRIBE");
    public static Command BEGIN = new Command("BEGIN");
    public static Command COMMIT = new Command("COMMIT");
    public static Command ABORT = new Command("ABORT");
    public static Command DISCONNECT = new Command("DISCONNECT");
    public static Command CONNECT = new Command("CONNECT");
    public static Command MESSAGE = new Command("MESSAGE");
    public static Command RECEIPT = new Command("RECEIPT");
    public static Command CONNECTED = new Command("CONNECTED");
    public static Command ERROR = new Command("ERROR");

    private Command(String msg) {
        this._command = msg;
    }

    public static Command valueOf(String v) {
        if ((v = v.trim()).equals("SEND")) {
            return SEND;
        }
        if (v.equals("SUBSCRIBE")) {
            return SUBSCRIBE;
        }
        if (v.equals("UNSUBSCRIBE")) {
            return UNSUBSCRIBE;
        }
        if (v.equals("BEGIN")) {
            return BEGIN;
        }
        if (v.equals("COMMIT")) {
            return COMMIT;
        }
        if (v.equals("ABORT")) {
            return ABORT;
        }
        if (v.equals("CONNECT")) {
            return CONNECT;
        }
        if (v.equals("MESSAGE")) {
            return MESSAGE;
        }
        if (v.equals("RECEIPT")) {
            return RECEIPT;
        }
        if (v.equals("CONNECTED")) {
            return CONNECTED;
        }
        if (v.equals("DISCONNECT")) {
            return DISCONNECT;
        }
        if (v.equals("ERROR")) {
            return ERROR;
        }
        throw new Error("Unrecognised command " + v);
    }

    public String toString() {
        return this._command;
    }
}

