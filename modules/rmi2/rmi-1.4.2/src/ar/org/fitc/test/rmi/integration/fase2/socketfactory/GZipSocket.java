package ar.org.fitc.test.rmi.integration.fase2.socketfactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketImpl;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


/**
 * A Socket that compress data
 * 
 * @author Osvaldo Demo
 * 
 * @version 1.0
 */

public class GZipSocket extends Socket {

    private InputStream in;

    private OutputStream out;


    public GZipSocket(Proxy proxy) {
        super(proxy);
    }

    public GZipSocket(SocketImpl impl) throws SocketException {
        super(impl);
    }

    public GZipSocket(InetAddress address, int port) throws IOException {
        super(address, port);
    }

    public GZipSocket(String host, int port, InetAddress localAddr,
            int localPort) throws IOException {
        super(host, port, localAddr, localPort);
    }

    public GZipSocket(InetAddress address, int port, InetAddress localAddr,
            int localPort) throws IOException {
        super(address, port, localAddr, localPort);
    }

    public GZipSocket() {
        super();
    }

    public GZipSocket(String host, int port) throws IOException {
        super(host, port);
    }

    public InputStream getInputStream() throws IOException {
        if (in == null) {
            in = new ZipInputStream(super.getInputStream());
        }
        return in;
    }

    public OutputStream getOutputStream() throws IOException {
        if (out == null) {
            out = new ZipOutputStream(super.getOutputStream());
        }
        return out;
    }

    public synchronized void close() throws IOException {
        OutputStream o = getOutputStream();
        o.flush();
        super.close();
    }
}