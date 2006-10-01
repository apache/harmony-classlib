
package javax.security.sasl;

import java.util.Map;

import javax.security.auth.callback.CallbackHandler;

class myClientFactory01 implements SaslClientFactory {
    public myClientFactory01() {
        super();
    }

    public String[] getMechanismNames(Map<String, ?> map) {
        return new String[] { "a1", "a2", "a3", "a4", "a5" };
    }

    public SaslClient createSaslClient(String[] mech, String prot, String auth,
            String srvName, Map<String, ?> prop, CallbackHandler ch) throws SaslException {
        return null;
    }
}
