
package joshua.nrod.stomp;

import javax.security.auth.login.LoginException;
import joshua.nrod.stomp.Authenticator;

public class AllowAllAuthenticator
implements Authenticator {
    public Object connect(String user, String pass) throws LoginException {
        return "";
    }

    public boolean authorizeSend(Object token, String channel) {
        return true;
    }

    public boolean authorizeSubscribe(Object token, String channel) {
        return true;
    }
}

