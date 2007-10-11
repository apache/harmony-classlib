package javax.net.ssl;

public class SSLParameters {

    protected String[] enabledCipherSuites;

    private String[] enabledProtocols;

    private boolean need_client_auth;

    private boolean want_client_auth;

    public SSLParameters() {
        enabledCipherSuites = null;
        enabledProtocols = null;
        need_client_auth = false;
        want_client_auth = false;
    };

    public SSLParameters(String[] cipherSuites) {
        this.enabledCipherSuites = cipherSuites;
    }

    public SSLParameters(String[] cipherSuites, String[] protocols) {
        // input maybe is null
        this.enabledCipherSuites = cipherSuites;
        this.enabledCipherSuites = protocols;
    }

    public String[] getCipherSuites() {
        if (enabledCipherSuites == null)
            return null;
        return this.enabledCipherSuites.clone();
    }

    public boolean getNeedClientAuth() {
        return this.need_client_auth;
    }

    public String[] getProtocols() {
        if (enabledProtocols == null)
            return null;
        else
            return enabledProtocols.clone();
    }

    public boolean getWantClientAuth() {
        return this.want_client_auth;
    }

    public void setCipherSuites(String[] cipherSuites) {
        this.enabledCipherSuites = cipherSuites.clone();
    }

    public void setNeedClientAuth(boolean needClientAuth) {
        this.need_client_auth = needClientAuth;
    }

    public void setProtocols(String[] protocols) {

        this.enabledProtocols = protocols.clone();
    }

    public void setWantClientAuth(boolean wantClientAuth) {
        this.want_client_auth = wantClientAuth;
    }
}
