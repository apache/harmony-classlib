package ar.org.fitc.test.rmi.integration.fase2;

import java.rmi.RemoteException;

import ar.org.fitc.test.rmi.integration.fase2.executor.AbstractServerExecutor;

public class TimeWaitBigReturn extends AbstractServerExecutor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TimeWaitBigReturn() throws RemoteException {
		super();
	}

	@Override
	public Object execute(Object... arguments) throws RemoteException {
		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		Object[] result = new Object[1000];
//		for (int i=0; i< 26; i++) {
//			result[i] = null;
//		}
		result[3] = this;
		result[4] = new TimeWaitBigReturn();
		result[7] = 5;
		result[15] = "Overhead result";
		result[18] = result[4];
		result[20] = null;
		result[23] = 32.5d;
		result[25] = result;
		result[0] = new Long(System.currentTimeMillis() - startTime);
		return result;
		
		
	}

}
