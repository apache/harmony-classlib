package ar.org.fitc.test.rmi.integration.fase2.socketfactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;


/**
 * A Client Socket Factory that delays the data channel
 * 
 * @author Osvaldo Demo
 * 
 * @version 1.0
 */
public class DelayRMISocketFactory extends RMISocketFactory {

    public DelayRMISocketFactory() {
        super();
    }
    
    @Override
    public Socket createSocket(String arg0, int arg1) throws IOException {
        return new DelaySocket(arg0,arg1);
    }

    @Override
    public ServerSocket createServerSocket(int arg0) throws IOException {
        return new DelayServerSocket(arg0);
    }

    
}
