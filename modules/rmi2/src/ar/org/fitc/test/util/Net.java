package ar.org.fitc.test.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Net {

	public Net() {
		super();

	}

	static {
		// seting port
		String sport = System.getProperty("ar.org.fitc.test.port");
		if (sport != null) {
			port = Integer.parseInt(sport);
		} else {
			port = 1099;
		}
	}
	private static int port;
	public static int getRegistryPort() {
		return port;
	}
    
    public static String ip() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        byte[] ipAddr = address.getAddress();
        String ip = new String(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2]
                + "." + ipAddr[3]);
        return ip;
    }
	
}
