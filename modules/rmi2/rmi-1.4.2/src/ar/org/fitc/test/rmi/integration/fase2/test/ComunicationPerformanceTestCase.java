package ar.org.fitc.test.rmi.integration.fase2.test;

import java.rmi.RemoteException;

import ar.org.fitc.test.rmi.integration.fase2.TimeWait;
import ar.org.fitc.test.rmi.integration.fase2.executor.ServerExecutor;

public class ComunicationPerformanceTestCase extends PerformanceTestCase {

	public ComunicationPerformanceTestCase() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ComunicationPerformanceTestCase(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	protected ServerExecutor object;
	protected void setUp() throws Exception {
		object = new TimeWait();
		super.setUp();
	}
	
	@Override
	protected long oneTest() throws RemoteException {
		long startTime = System.currentTimeMillis();
		long clientTime= (Long)executor.execute(object, 1);
		long stopTime = System.currentTimeMillis();
		return stopTime - startTime - clientTime;
	}

}
