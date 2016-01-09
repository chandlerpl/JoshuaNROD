
package joshua.nrod.stomp;

import java.util.Map;
import joshua.nrod.stomp.Command;

public class Message {
    private Command _command;
    private Map _headers;
    private String _body;

    protected Message(Command c, Map h, String b) {
        this._command = c;
        this._headers = h;
        this._body = b;
    }

    public Map headers() {
        return this._headers;
    }

    public String body() {
        return this._body;
    }

    public Command command() {
        return this._command;
    }
}

