package ar.org.fitc.test.rmi.integration.fase2.socketfactory;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.rmi.server.RMIServerSocketFactory;
  

/**
 * A Server Socket Factory that compress data
 * 
 * @author Osvaldo Demo
 * 
 * @version 1.0
 */
public class GZipServerSocketFactory implements RMIServerSocketFactory, Serializable {
   
    private static final long serialVersionUID = 3546154586009446002L;

    public ServerSocket createServerSocket(int port)
        throws IOException {
            GZipServerSocket server = new GZipServerSocket(port);
            return server;
    }
}


