package ar.org.fitc.test.rmi.integration.fase2;

import java.rmi.RemoteException;

import ar.org.fitc.test.rmi.integration.fase2.executor.AbstractServerExecutor;

public class TimeWait extends AbstractServerExecutor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	public TimeWait() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute(Object... arguments) throws RemoteException {
		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		return (System.currentTimeMillis() - startTime);
	}

}
