package ar.org.fitc.test.rmi.integration.fase2.test;

import java.rmi.RemoteException;

abstract public class PerformanceTestCase extends ExecutorTestCase {

	public PerformanceTestCase() {
		super();
	}

	public PerformanceTestCase(String arg0) {
		super(arg0);
	}
	
	public static int NumberOfIteration = 10;
	
	protected void setUp() throws Exception {
		super.setUp();
		oneTest();
		oneTest();
	}
	
	abstract protected long oneTest() throws RemoteException;

	public void testPerformance() throws RemoteException {
		System.out.println("\t\tTime one call:" + oneTest());
	}
	
	public void testPerformanceAVG() throws RemoteException {
		long avg = 0;
		
		for (int i=0; i < NumberOfIteration; i++) {
			long x = oneTest();
			avg += x;
			System.out.println("\t\tTime one call:" + x);
		}
		avg = avg / NumberOfIteration;
		System.out.println("\t\tTime avenger of " + NumberOfIteration +" calls:" + avg);
	}
	public void testPerformanceAVGandS2() throws RemoteException {
		long avg = 0;
		double s2 = 0;
		for (int i=0; i < NumberOfIteration; i++) {
			long x = oneTest();
			avg += x;
			s2 += x*x;
			System.out.println("\t\tTime one call:" + x);
			
		}
		avg = avg / NumberOfIteration;
		s2 = (s2 / (NumberOfIteration-1)) - avg * avg;
		s2 = Math.sqrt(s2);
		System.out.println("\t\tTime avenger of " + NumberOfIteration +" calls:" + avg);
		System.out.println("\t\tStandard deviation of this calls:" + s2);
	}

}
