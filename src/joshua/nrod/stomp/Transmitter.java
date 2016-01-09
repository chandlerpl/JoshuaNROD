
package joshua.nrod.stomp;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import joshua.nrod.stomp.Command;

class Transmitter {
    Transmitter() {
    }

    public static void transmit(Command c, Map h, String b, OutputStream out) throws IOException {
        StringBuffer message = new StringBuffer(c.toString());
        message.append("\n");
        if (h != null) {
            Iterator keys = h.keySet().iterator();
            while (keys.hasNext()) {
                String key = (String)keys.next();
                String value = (String)h.get(key);
                message.append(key);
                message.append(":");
                message.append(value);
                message.append("\n");
            }
        }
        message.append("\n");
        if (b != null) {
            message.append(b);
        }
        message.append("\u0000");
        out.write(message.toString().getBytes("US-ASCII"));
    }
}

