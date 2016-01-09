package joshua.nrod.stomp;

import javax.security.auth.login.LoginException;

public interface Authenticator {
    public Object connect(String var1, String var2) throws LoginException;

    public boolean authorizeSend(Object var1, String var2);

    public boolean authorizeSubscribe(Object var1, String var2);
}

