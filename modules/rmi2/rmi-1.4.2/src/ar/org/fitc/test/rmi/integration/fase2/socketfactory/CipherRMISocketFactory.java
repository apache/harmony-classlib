package ar.org.fitc.test.rmi.integration.fase2.socketfactory;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;



public class CipherRMISocketFactory extends RMISocketFactory implements Serializable {

    private static final long serialVersionUID = -7178597099667129705L;

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
