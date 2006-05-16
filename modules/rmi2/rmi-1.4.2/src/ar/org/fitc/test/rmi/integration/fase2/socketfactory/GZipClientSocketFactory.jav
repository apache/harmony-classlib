package ar.org.fitc.test.rmi.integration.fase2.socketfactory;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.rmi.server.RMIClientSocketFactory;

/**
 * A Client Socket Factory that compress data
 * 
 * @author Osvaldo Demo
 * 
 * @version 1.0
 */
public class GZipClientSocketFactory implements RMIClientSocketFactory, Serializable {
    
    private static final long serialVersionUID = -5171232459222429076L;

    public Socket createSocket(String host, int port)
        throws IOException {
            GZipSocket socket = new GZipSocket(host, port);
            return socket;
    }
}
