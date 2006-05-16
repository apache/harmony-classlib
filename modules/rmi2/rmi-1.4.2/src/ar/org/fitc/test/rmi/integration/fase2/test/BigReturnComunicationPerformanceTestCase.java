package ar.org.fitc.test.rmi.integration.fase2.test;

import java.rmi.RemoteException;

import ar.org.fitc.test.rmi.integration.fase2.TimeWaitBigReturn;
import ar.org.fitc.test.rmi.integration.fase2.executor.ServerExecutor;

public class BigReturnComunicationPerformanceTestCase extends
		PerformanceTestCase {

	public BigReturnComunicationPerformanceTestCase() {
		super();
	}

	public BigReturnComunicationPerformanceTestCase(String arg0) {
		super(arg0);
	}

	protected ServerExecutor object;
	protected void setUp() throws Exception {
		object = new TimeWaitBigReturn();
		super.setUp();
	}
	
	@Override
	protected long oneTest() throws RemoteException {
		long startTime = System.currentTimeMillis();
		Object result = executor.execute(object, 1);
		long stopTime = System.currentTimeMillis();
		Object[] arr = (Object[]) result;
		long clientTime= ((Long)arr[0]).longValue();
		return stopTime - startTime - clientTime;
	}

}
