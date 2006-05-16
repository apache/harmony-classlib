package ar.org.fitc.test.rmi.integration.fase2.test;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

import ar.org.fitc.test.rmi.integration.fase2.ITCRemoteUnicast;
import ar.org.fitc.test.rmi.integration.fase2.interfaces.ITCRemote;

public class PropertysTestCase extends ITCTestCase {

	public PropertysTestCase() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PropertysTestCase(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	protected void setUp() throws Exception {
		store = new Properties(System.getProperties());
		super.setUp();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		restoreProperty();
	}
	private Properties store = new Properties(System.getProperties()); 
	
	protected String setProperty(String key, String value) {
		return System.setProperty(key, value);
	}
	
	protected String getProperty(String key) {
		return System.getProperty(key);
	}
	
	protected void restoreProperty(String property) {
		System.setProperty(property, store.getProperty(property));
	}
	protected void restoreProperty() {
		System.setProperties(store);
	}
	
	public void testServerHostName() throws RemoteException, MalformedURLException, AlreadyBoundException, NotBoundException {
		setProperty("java.rmi.server.hostname","200.200.200.200");
		System.out.println(UnicastRemoteObject.toStub(new ITCRemoteUnicast()));
		
		LocateRegistry.createRegistry(1099);
		
		Naming.bind("hole", new ITCRemoteUnicast());
		System.out.println(arrayToString(Naming.list("")));
		ITCRemote o = (ITCRemote) Naming.lookup("hole");
		System.out.println(o.getString());
		System.out.println(o.toString());
		
	}
}
