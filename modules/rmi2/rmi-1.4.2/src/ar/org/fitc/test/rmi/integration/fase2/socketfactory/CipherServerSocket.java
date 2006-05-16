package ar.org.fitc.test.rmi.integration.fase2.socketfactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class CipherServerSocket extends ServerSocket {

	public CipherServerSocket() throws IOException {
		super();
	}

	public CipherServerSocket(int port) throws IOException {
		super(port);
	}

	public CipherServerSocket(int port, int backlog) throws IOException {
		super(port, backlog);
	}

	public CipherServerSocket(int port, int backlog, InetAddress bindAddr)
			throws IOException {
		super(port, backlog, bindAddr);
	}
    
	public Socket accept() throws IOException{
		Socket s = new CipherSocket();
		implAccept(s);
		return s;
	}
}
