package ar.org.fitc.test.rmi.integration.fase2.socketfactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.UnknownHostException;

/**
 * A Socket that delays the data channel
 * 
 * @author Osvaldo Demo
 * 
 * @version 1.0
 */
public class DelaySocket extends Socket {
    public DelaySocket() {
        super();
    }

    public DelaySocket(Proxy proxy) {
        super(proxy);
    }

    public DelaySocket(SocketImpl impl) throws SocketException {
        super(impl);
    }

    public DelaySocket(String host, int port) throws UnknownHostException,
            IOException {
        super(host, port);
    }

    public DelaySocket(InetAddress address, int port) throws IOException {
        super(address, port);
    }

    public DelaySocket(String host, int port, InetAddress localAddr,
            int localPort) throws IOException {
        super(host, port, localAddr, localPort);
    }

    public DelaySocket(InetAddress address, int port, InetAddress localAddr,
            int localPort) throws IOException {
        super(address, port, localAddr, localPort);
    }
    
    public OutputStream getOutputStream() throws IOException { 
        return super.getOutputStream();
    }
    public InputStream getInputStream() throws IOException { 
        return super.getInputStream();
    }
}
