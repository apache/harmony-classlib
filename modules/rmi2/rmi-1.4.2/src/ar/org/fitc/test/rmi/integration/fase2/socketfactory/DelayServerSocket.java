package ar.org.fitc.test.rmi.integration.fase2.socketfactory;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * A Server Socket that delays the data channel
 * 
 * @author Osvaldo Demo
 * 
 * @version 1.0
 */
public class DelayServerSocket extends ServerSocket {

    public DelayServerSocket(int arg0) throws IOException {
        super(arg0);
    }

    public DelayServerSocket() throws IOException {
        super();
    }

}
