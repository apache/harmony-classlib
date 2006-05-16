package ar.org.fitc.test.rmi.integration.fase2.test;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import ar.org.fitc.test.rmi.integration.fase2.AbstractITCRemote;
import ar.org.fitc.test.rmi.integration.fase2.interfaces.ITCRemote;

public class ExportationPerformanceTestCase extends PerformanceTestCase {

	public ExportationPerformanceTestCase() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExportationPerformanceTestCase(String arg0) {
		super(arg0);
		
	}

	@Override
	protected long oneTest() throws RemoteException {
		ITCRemote o = new AbstractITCRemote();
		long startTime = System.currentTimeMillis();
		UnicastRemoteObject.exportObject(o, 0);
		long stopTime = System.currentTimeMillis();
		UnicastRemoteObject.unexportObject(o, false);
		return stopTime - startTime;
		
	}

}
