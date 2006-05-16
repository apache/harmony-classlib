package ar.org.fitc.test.rmi.integration.fase2.socketfactory;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;


/**
 * A RMI Socket Factory that compress data
 * 
 * @author Osvaldo Demo
 * 
 * @version 1.0
 */

public class GZipRMISocketFactory extends RMISocketFactory implements Serializable {
    
    private static final long serialVersionUID = 1372758287532040991L;
    
    public GZipRMISocketFactory() {
        super();
    }
    @Override
    public Socket createSocket(String arg0, int arg1) throws IOException {
        return new GZipSocket(arg0,arg1);
    }
    
    @Override
    public ServerSocket createServerSocket(int arg0) throws IOException {
        return new GZipServerSocket(arg0);
    }
    
}
