
package joshua.nrod.stomp;

import java.util.Map;
import joshua.nrod.stomp.Command;

public interface MessageReceiver {
    public void receive(Command var1, Map var2, String var3);

    public void disconnect();

    public boolean isClosed();
}

