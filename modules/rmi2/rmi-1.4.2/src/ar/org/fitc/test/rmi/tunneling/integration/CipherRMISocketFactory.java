package ar.org.fitc.test.rmi.tunneling.integration;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

public class CipherRMISocketFactory extends RMISocketFactory {

	public CipherRMISocketFactory() {
		super();
	}

	@Override
	public Socket createSocket(String host, int port) throws IOException {
		return new CipherSocket(host, port); 
	}

	@Override
	public ServerSocket createServerSocket(int port) throws IOException {
		return new CipherServerSocket(port);
	}

}
