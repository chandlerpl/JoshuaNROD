
package joshua.nrod.stomp;

import java.util.Map;
import joshua.nrod.stomp.Authenticatable;
import joshua.nrod.stomp.Command;
import joshua.nrod.stomp.Listener;
import joshua.nrod.stomp.Server;
import joshua.nrod.stomp.Stomp;

public class IntraVMClient
extends Stomp
implements Listener,
Authenticatable {
    private Server _server;

    protected IntraVMClient(Server server) {
        this._server = server;
        this._connected = true;
    }

    public boolean isClosed() {
        return false;
    }

    public Object token() {
        return "IntraVMClient";
    }

    public void transmit(Command c, Map h, String b) {
        this._server.receive(c, h, b, this);
    }

    public void disconnect(Map h) {
        this._server.receive(Command.DISCONNECT, null, null, this);
        this._server = null;
    }

    public void message(Map headers, String body) {
        this.receive(Command.MESSAGE, headers, body);
    }

    public void receipt(Map headers) {
        this.receive(Command.RECEIPT, headers, null);
    }

    public void error(Map headers, String body) {
        this.receive(Command.ERROR, headers, body);
    }
}

