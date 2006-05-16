/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the License);
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an AS IS BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package ar.org.fitc.test.rmi.integration.fase2.test;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.util.logging.Logger;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestListener;
import junit.framework.TestResult;
import ar.org.fitc.test.rmi.integration.fase2.Net;

/**
 * Generates a log.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 *
 * @version 1.0
 */
class Listener implements TestListener {

	
	/**
	 * Prints a message that receive as parameter.
	 * 
	 * @param msg a message
	 */
	private void printMSG(String msg) {
		if (msg != null && !msg.startsWith("Failed with:")) {
			msg = "Failed with: " +msg;
		}
		System.out.println("\t\t" + msg);
	}
	
	/**
	 * Adds an error trace.
	 * 
	 * @param arg a testing argument
	 * @param arg1 a throwable argument
	 */
	public void addError(Test arg, Throwable arg1) {
		arg1.printStackTrace();
		printMSG(arg1.getMessage());
	}
	  
	/**
	 * Adds a failure trace.
	 * 
	 * @param arg a testing argument
	 * @param arg1 an assertion failure error
	 */
	public void addFailure(Test arg, AssertionFailedError arg1) {
		arg1.printStackTrace();
		printMSG(arg1.getMessage());
	}
	  
	/**
	 * Indicates the end of the test
	 * 
	 * @param a testing argument
	 */
	public void endTest(Test arg) {		
	}
	
	/**
	 * Indicates the start of the test
	 * 
	 * @param a testing argument
	 * */
	public void startTest(Test arg) {
	}
}

/**
 * Testing class for <code>WeakReference</code>'s.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 *
 */
public class ITCTestCase extends TestCase {
	
	public static String arrayToString(Object[] arg) {
		if (arg.length == 0) {
			return "[]";
		} else {
			String result = "[" + arg[0].toString();
			int i = 1;
			while(i < arg.length) {
				result += ", " + arg[i].toString();
			} 
			result += "]";
			return result;
		}
	}
	
	/**
	 * For log purpose.
	 */
	transient final protected static Logger log = Logger.getLogger("ar.org.fitc.test.rmi.integration.fase2.test");

	/**
	 * The remote interface to a simple remote object. 
	 */
	protected static Registry registry;
	
	/**
	 * If registry is null, creates it. 
	 *
	 */
	public synchronized static void initRegistry() {
		if (registry == null) {
			try {
				try {
					registry = LocateRegistry.createRegistry(Net.getRegistryPort());
				} catch (ExportException e) {
					registry = LocateRegistry.getRegistry(Net.getRegistryPort());
				}
		    } catch (RemoteException e) {
		        e.printStackTrace();
		    }
		}
	}
		
	/**
	 * Creates a log.
	 *  
	 */
	public TestResult run() {
		System.out.println(this.getClass() + " start");
		TestResult r = createResult();
		r.addListener(new Listener());
		for (Method m : this.getClass().getMethods()) {
			String name = m.getName(); 
			if (name.startsWith("test") && m.getParameterTypes().length == 0) {
					System.out.println("\t" + name);
					setName(name);
					run(r);		
			}
		}
		System.out.println();
		System.out.println();
		return r;
	}
	public TestResult run(String name) {
		System.out.println(this.getClass() + " start");
		TestResult r = createResult();
		r.addListener(new Listener());
		System.out.println("\t" + name);
		setName(name);
		run(r);
		System.out.println();
		System.out.println();
		return r;
	}

	/**
     * Default constructor.
     *
     */
	public ITCTestCase() {
		super();
	}
	
	/**
	 * Constructs a <code>ITCTestCase</code> with a name. 
	 * 
	 * @param arg0 a name.
	 */
	public ITCTestCase(String arg0) {
		super(arg0);
		//TODO puesto por conveniencia temporal...
		// permite ejecutar los test desde el plugin de eclipse. 
		//sacra cuendo el proyecto este en marcha
		System.setProperty("java.rmi.server.codebase",
		"ftp://proftp:1@10.100.2.230/");
		System.setProperty("java.rmi.server.hostname", "10.100.2.230");
	}
	
	/**
	 * Creates a new <code>WeakReference</code> and forces the 
	 * garbage collector 
	 *
	 */
	@SuppressWarnings("unchecked")
	public static void forceGC() {
		for (int i= 0; i < 100; i++) {
			System.gc();
		}
		WeakReference ref = new WeakReference(new Integer(234523));
		while (ref.get() != null) {
			System.gc();
		}
	}
	
	/**
	 * Creates a new <code>WeakReference</code> and forces the 
	 * garbage collector.
	 *  
	 * @param WeakReference the specified <code>WeakReference</code>
	 * @param times the number of executions 
	 */
	public static void forceGC(WeakReference ref, int times) {
		for (int i = times; ref.get() != null || i ==0; i--) {
			System.gc();
		}
	}
	
	/**
	 * Verifies the <code>WeakReference</code>.
	 * 
	 * @param ref the specified <code>WeakReference</code>
	 */
	public static void assertNull(WeakReference ref) {
		forceGC();
		assertNull(ref.get());
	}

	/**
	 * Verifies the <code>WeakReference</code>.
	 * 
	 * @param ref the specified <code>WeakReference</code>
	 * @param message if this assert fail this message'll be tell 
	 */
	public static void assertNull(String message, WeakReference ref) {
		forceGC();
		assertNull(message, ref.get());
	}
	
	/**
	 * Verifies the <code>WeakReference</code>.
	 * 
	 * @param ref the specified <code>WeakReference</code>
	 */
	public static void assertNotNull(WeakReference ref) {
		forceGC();
		assertNotNull(ref.get());
	}
	
	/**
	 * Verifies the <code>WeakReference</code>.
	 * 
	 * @param ref the specified <code>WeakReference</code>
	 * @param message if this assert fail this message'll be tell
	 */
	public static void assertNotNull(String message, WeakReference ref) {
		forceGC();
		assertNotNull(message, ref.get());
	}
}

