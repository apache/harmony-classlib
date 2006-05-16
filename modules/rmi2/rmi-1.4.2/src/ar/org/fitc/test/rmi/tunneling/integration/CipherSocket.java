package ar.org.fitc.test.rmi.tunneling.integration;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.UnknownHostException;
import javax.crypto.*;

public class CipherSocket extends Socket {

	public CipherSocket() {
		super();
	}

	public CipherSocket(Proxy proxy) {
		super(proxy);
	}

	public CipherSocket(SocketImpl impl) throws SocketException {
		super(impl);
	}

	public CipherSocket(String host, int port) throws UnknownHostException,
			IOException {
		super(host, port);
	}

	public CipherSocket(InetAddress address, int port) throws IOException {
		super(address, port); 
	}

	public CipherSocket(String host, int port, InetAddress localAddr,
			int localPort) throws IOException {
		super(host, port, localAddr, localPort); 
	}

	public CipherSocket(InetAddress address, int port, InetAddress localAddr,
			int localPort) throws IOException {
		super(address, port, localAddr, localPort); 
	}

	public CipherSocket(String host, int port, boolean stream)
			throws IOException {
		super(host, port, stream);
 
	}

	public CipherSocket(InetAddress host, int port, boolean stream)
			throws IOException {
		super(host, port, stream);
	}

	public OutputStream getOutputStream() throws IOException { 
		return new CipherOutputStream(super.getOutputStream(), RemoteCipherImpl.getCipher(Cipher.ENCRYPT_MODE));
	}
	public InputStream getInputStream() throws IOException { 
		return new CipherInputStream(super.getInputStream(), RemoteCipherImpl.getCipher(Cipher.DECRYPT_MODE));
	}
}
