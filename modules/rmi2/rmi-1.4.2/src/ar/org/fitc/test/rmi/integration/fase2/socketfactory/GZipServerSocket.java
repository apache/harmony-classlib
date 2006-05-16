package ar.org.fitc.test.rmi.integration.fase2.socketfactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * A Server Socket that compress data
 * 
 * @author Osvaldo Demo
 * 
 * @version 1.0
 */

public class GZipServerSocket extends ServerSocket {
    
    public GZipServerSocket() throws IOException {
        super();
    }
    
    public GZipServerSocket(int port, int backlog) throws IOException {
        super(port, backlog);
    }
    
    public GZipServerSocket(int port, int backlog, InetAddress bindAddr)
    throws IOException {
        super(port, backlog, bindAddr);
    }
    
    public GZipServerSocket(int port) throws IOException {    
        super(port);
    }
    
    public Socket accept() throws IOException {
        Socket socket = new GZipSocket();
        implAccept(socket);
        return socket;
    }
}


