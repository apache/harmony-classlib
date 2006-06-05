package ar.org.fitc.test.util;

public class Net {

	public Net() {
		super();
		// TODO Auto-generated constructor stub
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
	
}
