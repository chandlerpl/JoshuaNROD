
package joshua.nrod.stomp;

import java.util.Map;
import joshua.nrod.stomp.MessageReceiver;

public interface Authenticatable
extends MessageReceiver {
    public void error(Map var1, String var2);

    public Object token();
}

