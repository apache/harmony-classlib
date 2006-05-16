package ar.org.fitc.test.rmi.integration.fase2.test;

import java.rmi.RemoteException;

import ar.org.fitc.test.rmi.integration.fase2.AbstractITCRemote;
import ar.org.fitc.test.rmi.integration.fase2.ITCRemoteUnicast;

public class OverheadComunicationPerformanceTestCase extends
		ComunicationPerformanceTestCase {

	public OverheadComunicationPerformanceTestCase() {
		super();
	}

	public OverheadComunicationPerformanceTestCase(String arg0) {
		super(arg0);
	}

	protected long oneTest() throws RemoteException {
		long startTime = System.currentTimeMillis();
		long clientTime = (Long) executor.execute(object, 1, "This overhead",
				new AbstractITCRemote(), new ITCRemoteUnicast(),
				"more overhead", new ITCRemoteUnicast(), object, true, 'a',
				2.345d, object, startTime, executor);
		long stopTime = System.currentTimeMillis();
		return stopTime - startTime - clientTime;
	}

}
