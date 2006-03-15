/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tests.api.java.sql;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.security.Permission;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLPermission;
import java.util.Enumeration;
import java.util.Properties;

import junit.framework.TestCase;

/**
 * JUnit Testcase for the java.sql.DriverManager class
 * 
 */
public class DriverManagerTest extends TestCase {

	// Set of driver names to use
	static final String DRIVER1 = "tests.api.java.sql.TestHelper_Driver1";

	static final String DRIVER2 = "tests.api.java.sql.TestHelper_Driver2";

	static final String DRIVER3 = "tests.api.java.sql.TestHelper_Driver3";

	static final String DRIVER4 = "tests.api.java.sql.TestHelper_Driver4";

	static final String DRIVER5 = "tests.api.java.sql.TestHelper_Driver5";

	static final String INVALIDDRIVER1 = "abc.klm.Foo";

	static String[] driverNames = { DRIVER1, DRIVER2 };

	static int numberLoaded;

	static String baseURL1 = "jdbc:mikes1";

	static String baseURL4 = "jdbc:mikes4";

	static final String JDBC_PROPERTY = "jdbc.drivers";

	static TestHelper_ClassLoader testClassLoader = new TestHelper_ClassLoader();

	// Static initializer to load the drivers so that they are available to all
	// the
	// test methods as needed.
	public void setUp() {
		numberLoaded = loadDrivers();
	} // end setUp()

	/**
	 * Test for the method DriverManager.deregisterDriver
	 */
	public void testDeregisterDriver() {
		// First get one of the drivers loaded by the test
		Driver aDriver;
		try {
			aDriver = DriverManager.getDriver(baseURL4);
		} catch (SQLException e) {
			fail(
					"testDeregisterDriver: Got exception when getting valid driver.");
			return;
		} // end try

		// printClassLoader( aDriver );

		// Deregister this driver
		try {
			DriverManager.deregisterDriver(aDriver);
		} catch (Exception e) {
			fail(
					"testDeregisterDriver: Got exception when deregistering valid driver.");
		} // end try

		assertFalse("testDeregisterDriver: Driver was not deregistered.",
				isDriverLoaded(aDriver));

		// Re-register this driver (so subsequent tests have it available)
		try {
			DriverManager.registerDriver(aDriver);
		} catch (Exception e) {
			fail(
					"testDeregisterDriver: Got exception when reregistering valid driver.");
		} // end try

		assertTrue("testDeregisterDriver: Driver did not reload.",
				isDriverLoaded(aDriver));

		// Test deregistering a null driver
		try {
			DriverManager.deregisterDriver(null);
		} catch (SQLException e) {
			fail(
					"testDeregisterDriver: Got exception when deregistering null driver.");
		} // end try

		// Test deregistering a driver which was not loaded by this test's
		// classloader
		// TODO - need to load a driver with a different classloader!!
		try {
			aDriver = DriverManager.getDriver(baseURL1);
		} catch (SQLException e) {
			fail(
					"testDeregisterDriver: Got exception when getting valid driver1.");
			return;
		} // end try

		TestHelper_DriverManager testHelper;
		try {
			Class driverClass = Class.forName(
					"tests.api.java.sql.TestHelper_DriverManager", true,
					testClassLoader);

			// Give the Helper class one of our drivers....
			Class[] methodClasses = { Class.forName("java.sql.Driver") };
			Method theMethod = driverClass.getDeclaredMethod("setDriver",
					methodClasses);
			Object[] args = { aDriver };
			theMethod.invoke(null, args);
		} catch (Exception e) {
			System.out
					.println("testDeregisterDriver: Got exception allocating TestHelper");
			e.printStackTrace();
			return;
		} // end try

		// Check that the driver was not deregistered
		assertTrue(
				"testDeregisterDriver: Driver was incorrectly deregistered.",
				DriverManagerTest.isDriverLoaded(aDriver));

	} // end method testDeregisterDriver()

	static void printClassLoader(Object theObject) {
		Class theClass = theObject.getClass();
		ClassLoader theClassLoader = theClass.getClassLoader();
		System.out.println("ClassLoader is: " + theClassLoader.toString()
				+ " for object: " + theObject.toString());
	} // end method printClassLoader( Object )

	static boolean isDriverLoaded(Driver theDriver) {
		Enumeration driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			if ((Driver) driverList.nextElement() == theDriver)
				return true;
		} // end while
		return false;
	} // end method isDriverLoaded( Driver )

	/*
	 * Class under test for Connection getConnection(String)
	 */
	// valid connection - data1 does not require a user and password...
	static String validConnectionURL = "jdbc:mikes1:data1";

	// invalid connection - data2 requires a user & password
	static String invalidConnectionURL1 = "jdbc:mikes1:data2";

	// invalid connection - URL is gibberish
	static String invalidConnectionURL2 = "xyz1:abc3:456q";

	// invalid connection - URL is null
	static String invalidConnectionURL3 = null;

	static String[] invalidConnectionURLs = { invalidConnectionURL1,
			invalidConnectionURL2, invalidConnectionURL3 };

	static String[] exceptionMessages = {
			"Userid and/or password not supplied", "No suitable driver",
			"The url cannot be null" };

	public void testGetConnectionString() {
		Connection theConnection = null;
		// validConnection - no user & password required
		try {
			theConnection = DriverManager.getConnection(validConnectionURL);
			assertTrue(theConnection != null);
		} catch (SQLException e) {
			assertTrue(false);
		} // end try

		// invalid connections
		for (int i = 0; i < invalidConnectionURLs.length; i++) {
			theConnection = null;
			try {
				theConnection = DriverManager
						.getConnection(invalidConnectionURLs[i]);
				assertFalse(theConnection != null);
			} catch (SQLException e) {
				assertTrue(theConnection == null);
				// System.out.println("testGetConnectionString: exception
				// message: " +
				// e.getMessage() );
				assertTrue(e.getMessage().equals(exceptionMessages[i]));
			} // end try
		} // end for
	} // end method testGetConnectionString()

	/*
	 * Class under test for Connection getConnection(String, Properties)
	 */
	public void testGetConnectionStringProperties() {
		String validURL1 = "jdbc:mikes1:data2";
		String validuser1 = "theuser";
		String validpassword1 = "thepassword";
		String invalidURL1 = "xyz:abc1:foo";
		String invalidURL2 = "jdbc:mikes1:crazyone";
		String invalidURL3 = "";
		String invaliduser1 = "jonny nouser";
		String invalidpassword1 = "whizz";
		Properties nullProps = null;
		Properties validProps = new Properties();
		validProps.setProperty("user", validuser1);
		validProps.setProperty("password", validpassword1);
		Properties invalidProps1 = new Properties();
		invalidProps1.setProperty("user", invaliduser1);
		invalidProps1.setProperty("password", invalidpassword1);
		String[] invalidURLs = { null, validURL1, validURL1, invalidURL1,
				invalidURL2, invalidURL3 };
		Properties[] invalidProps = { validProps, nullProps, invalidProps1,
				validProps, validProps, validProps };
		String[] excMessage = { "The url cannot be null",
				"Properties bundle is null",
				"Userid and/or password not valid", "No suitable driver",
				"No suitable driver", "No suitable driver" };

		Connection theConnection = null;
		Properties theProperties = null;
		// validConnection - user & password required
		try {
			theConnection = DriverManager.getConnection(validURL1, validProps);
			assertTrue(theConnection != null);
		} catch (SQLException e) {
			assertTrue(false);
		} // end try

		// invalid Connections
		for (int i = 0; i < invalidURLs.length; i++) {
			theConnection = null;
			try {
				theConnection = DriverManager.getConnection(invalidURLs[i],
						invalidProps[i]);
				assertFalse(theConnection != null);
			} catch (SQLException e) {
				// System.out.println("testGetConnectionStringStringString:
				// exception message: " +
				// e.getMessage() );
				assertTrue(e.getMessage().equals(excMessage[i]));
			} // end try
		} // end for
	} // end method testGetConnectionStringProperties()

	/*
	 * Class under test for Connection getConnection(String, String, String)
	 */
	public void testGetConnectionStringStringString() {
		String validURL1 = "jdbc:mikes1:data2";
		String validuser1 = "theuser";
		String validpassword1 = "thepassword";
		String invalidURL1 = "xyz:abc1:foo";
		String invaliduser1 = "jonny nouser";
		String invalidpassword1 = "whizz";
		String[] invalid1 = { null, validuser1, validpassword1 };
		String[] invalid2 = { validURL1, null, validpassword1 };
		String[] invalid3 = { validURL1, validuser1, null };
		String[] invalid4 = { invalidURL1, validuser1, validpassword1 };
		String[] invalid5 = { validURL1, invaliduser1, invalidpassword1 };
		String[] invalid6 = { validURL1, validuser1, invalidpassword1 };
		String[][] invalid = { invalid1, invalid2, invalid3, invalid4,
				invalid5, invalid6 };
		String[] excMessage = { "The url cannot be null",
				"Userid and/or password not supplied",
				"Userid and/or password not supplied", "No suitable driver",
				"Userid and/or password not valid",
				"Userid and/or password not valid" };

		Connection theConnection = null;
		// validConnection - user & password required
		try {
			theConnection = DriverManager.getConnection(validURL1, validuser1,
					validpassword1);
			assertTrue(theConnection != null);
		} catch (SQLException e) {
			assertTrue(false);
		} // end try

		// invalid Connections
		for (int i = 0; i < invalid.length; i++) {
			theConnection = null;
			String[] theData = invalid[i];
			try {
				theConnection = DriverManager.getConnection(theData[0],
						theData[1], theData[2]);
				assertFalse(theConnection != null);
			} catch (SQLException e) {
				// System.out.println("testGetConnectionStringStringString:
				// exception message: " +
				// e.getMessage() );
				assertTrue(e.getMessage().equals(excMessage[i]));
			} // end try
		} // end for
	} // end method testGetConnectionStringStringString()

	static String validURL1 = "jdbc:mikes1";

	static String validURL2 = "jdbc:mikes2";

	static String invalidURL1 = "xyz:acb";

	static String invalidURL2 = null;

	static String[] validURLs = { validURL1, validURL2 };

	static String[] invalidURLs = { invalidURL1, invalidURL2 };

	static String exceptionMsg1 = "No suitable driver";

	public void testGetDriver() {
		// valid URLs
		for (int i = 0; i < validURLs.length; i++) {
			try {
				Driver validDriver = DriverManager.getDriver(validURLs[i]);
			} catch (SQLException e) {
				fail(
						"DriverManagerTest: getDriver failed for valid driver"
								+ i);
			} // end try
		} // end for

		// invalid URLs
		for (int i = 0; i < invalidURLs.length; i++) {
			try {
				Driver invalidDriver = DriverManager.getDriver(invalidURLs[i]);
			} catch (SQLException e) {
				assertTrue(true);
				// System.out.println("DriverManagerTest: getDriver failed for
				// invalid driver");
				// System.out.println("DriverManagerTest: exception message = "
				// + e.getMessage() );
				assertTrue(e.getMessage().equals(exceptionMsg1));
			} // end try
		} // end for

	} // end method testGetDriver()

	public void testGetDrivers() {
		// Load a driver manager
		Enumeration driverList = DriverManager.getDrivers();
		int i = 0;
		while (driverList.hasMoreElements()) {
			Driver theDriver = (Driver) driverList.nextElement();
			i++;
		} // end while

		// Check that all the drivers are in the list...
		assertEquals("testGetDrivers: Don't see all the loaded drivers - ", i,
				numberLoaded);
	} // end method testGetDrivers()

	static int timeout1 = 25;

	public void testGetLoginTimeout() {
		int theTimeout = DriverManager.getLoginTimeout();
		// System.out.println("Default Login Timeout: " + theTimeout );

		DriverManager.setLoginTimeout(timeout1);

		assertTrue(DriverManager.getLoginTimeout() == timeout1);
	} // end method testGetLoginTimeout()

	public void testGetLogStream() {
		assertTrue(DriverManager.getLogStream() == null);

		DriverManager.setLogStream(testPrintStream);

		assertTrue(DriverManager.getLogStream() == testPrintStream);

		DriverManager.setLogStream(null);
	} // end method testGetLogStream()

	public void testGetLogWriter() {
		assertTrue(DriverManager.getLogWriter() == null);

		DriverManager.setLogWriter(testPrintWriter);

		assertTrue(DriverManager.getLogWriter() == testPrintWriter);

		DriverManager.setLogWriter(null);
	} // end method testGetLogWriter()

	static String testMessage = "DriverManagerTest: test message for print stream";

	public void testPrintln() {
		// System.out.println("testPrintln");
		DriverManager.println(testMessage);

		DriverManager.setLogWriter(testPrintWriter);
		DriverManager.println(testMessage);

		String theOutput = outputStream.toString();
		// System.out.println("testPrintln: output= " + theOutput );
		assertTrue(theOutput.startsWith(testMessage));

		DriverManager.setLogWriter(null);

		DriverManager.setLogStream(testPrintStream);
		DriverManager.println(testMessage);

		theOutput = outputStream2.toString();
		// System.out.println("testPrintln: output= " + theOutput );
		assertTrue(theOutput.startsWith(testMessage));

		DriverManager.setLogStream(null);
	} // end method testPrintln()

	public void testRegisterDriver() {
		String EXTRA_DRIVER_NAME = "tests.api.java.sql.TestHelper_Driver3";

		try {
			DriverManager.registerDriver(null);
			fail(
					"testRegisterDriver: Expected exception not thrown when registering null driver");
		} catch (Exception e) {

		} // end try

		Driver theDriver = null;
		// Load another Driver that isn't in the basic set
		try {
			Class driverClass = Class.forName(EXTRA_DRIVER_NAME);
			theDriver = (Driver) driverClass.newInstance();
			DriverManager.registerDriver(theDriver);
		} catch (ClassNotFoundException cfe) {
			fail("testRegisterDriver: Could not load extra driver");
		} catch (Exception e) {
			fail(
					"testRegisterDriver: Exception while registering additional driver");
		} // end try

		assertTrue("testRegisterDriver: driver not in loaded set",
				isDriverLoaded(theDriver));

	} // end testRegisterDriver()

	static int validTimeout1 = 15;

	static int validTimeout2 = 0;

	static int[] validTimeouts = { validTimeout1, validTimeout2 };

	static int invalidTimeout1 = -10;

	public void testSetLoginTimeout() {
		// Valid timeouts
		for (int i = 0; i < validTimeouts.length; i++) {
			DriverManager.setLoginTimeout(validTimeouts[i]);

			assertTrue(DriverManager.getLoginTimeout() == validTimeouts[i]);
		} // end for
		// Invalid timeouts
		try {
			DriverManager.setLoginTimeout(invalidTimeout1);
			assertTrue(DriverManager.getLoginTimeout() == invalidTimeout1);
		} catch (IllegalArgumentException e) {
			System.out.println("DriverManagerTest: exception message = "
					+ e.getMessage());
		} // end try
	} // end testSetLoginTimeout()

	static ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();

	static PrintStream testPrintStream = new PrintStream(outputStream2);

	public void testSetLogStream() {
		// System.out.println("testSetLogStream");
		DriverManager.setLogStream(testPrintStream);

		assertTrue(DriverManager.getLogStream() == testPrintStream);

		DriverManager.setLogStream(null);

		assertTrue(DriverManager.getLogStream() == null);

		// Now let's deal with the case where there is a SecurityManager in
		// place
		TestSecurityManager theSecManager = new TestSecurityManager();
		System.setSecurityManager(theSecManager);

		theSecManager.setLogAccess(false);

		try {
			DriverManager.setLogStream(testPrintStream);
			fail("testSetLogStream: Did not get security exception ");
		} catch (SecurityException s) {

		} catch (Throwable t) {
			fail(
					"testSetLogStream: Got exception but not get security exception ");
		} // end try

		theSecManager.setLogAccess(true);

		try {
			DriverManager.setLogStream(testPrintStream);
		} catch (SecurityException s) {
			fail(
					"testSetLogStream: Got security exception but should not have");
		} catch (Throwable t) {
			fail(
					"testSetLogStream: Got exception but not get security exception ");
		} // end try

		System.setSecurityManager(null);
	} // end method testSetLogStream()

	static ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	static PrintWriter testPrintWriter = new PrintWriter(outputStream);

	/**
	 * Test for the setLogWriter method
	 */
	public void testSetLogWriter() {
		// System.out.println("testSetLogWriter");
		DriverManager.setLogWriter(testPrintWriter);

		assertTrue("testDriverManager: Log writer not set:", DriverManager
				.getLogWriter() == testPrintWriter);

		DriverManager.setLogWriter(null);

		assertTrue("testDriverManager: Log writer not null:", DriverManager
				.getLogWriter() == null);

		// Now let's deal with the case where there is a SecurityManager in
		// place
		TestSecurityManager theSecManager = new TestSecurityManager();
		System.setSecurityManager(theSecManager);

		theSecManager.setLogAccess(false);

		try {
			DriverManager.setLogWriter(testPrintWriter);
			fail("testSetLogWriter: Did not get security exception ");
		} catch (SecurityException s) {

		} catch (Throwable t) {
			fail(
					"testSetLogWriter: Got exception but not get security exception ");
		} // end try

		theSecManager.setLogAccess(true);

		try {
			DriverManager.setLogWriter(testPrintWriter);
		} catch (SecurityException s) {
			fail(
					"testSetLogWriter: Got security exception but should not have");
		} catch (Throwable t) {
			fail(
					"testSetLogWriter: Got exception but not get security exception ");
		} // end try

		System.setSecurityManager(null);
	} // end method testSetLogWriter()

	/*
	 * Method which loads a set of JDBC drivers ready for use by the various
	 * tests @return the number of drivers loaded
	 */
	static boolean driversLoaded = false;

	private static int loadDrivers() {
		if (driversLoaded)
			return numberLoaded;
		/*
		 * First define a value for the System property "jdbc.drivers" - before
		 * the DriverManager class is loaded - this property defines a set of
		 * drivers which the DriverManager will load during its initialization
		 * and which will be loaded on the System ClassLoader - unlike the ones
		 * loaded later by this method which are loaded on the Application
		 * ClassLoader.
		 */
		int numberLoaded = 0;
		String theSystemDrivers = DRIVER4 + ":" + DRIVER5 + ":"
				+ INVALIDDRIVER1;
		System.setProperty(JDBC_PROPERTY, theSystemDrivers);
		numberLoaded += 2;

		/*
		 * Next, dynamically load a set of drivers
		 */
		for (int i = 0; i < driverNames.length; i++) {
			try {
				Class driverClass = Class.forName(driverNames[i]);
				// System.out.println("Loaded driver - classloader = " +
				// driverClass.getClassLoader());
				numberLoaded++;
			} catch (ClassNotFoundException e) {
				System.out.println("DriverManagerTest: failed to load Driver: "
						+ driverNames[i]);
			} // end try
		} // end for
		/*
		 * System.out.println("DriverManagerTest: number of drivers loaded: " +
		 * numberLoaded);
		 */
		driversLoaded = true;
		return numberLoaded;
	} // end method loadDrivers()

	class TestSecurityManager extends SecurityManager {

		boolean logAccess = true;

		SQLPermission sqlPermission = new SQLPermission("setLog");

		RuntimePermission setManagerPermission = new RuntimePermission(
				"setSecurityManager");

		TestSecurityManager() {
			super();
		} // end method TestSecurityManager()

		void setLogAccess(boolean allow) {
			logAccess = allow;
		} // end method setLogAccess( boolean )

		public void checkPermission(Permission thePermission) {
			if (thePermission.equals(sqlPermission)) {
				if (!logAccess) {
					throw new SecurityException("Cannot set the sql Log Writer");
				} // end if
				return;
			} // end if

			if (thePermission.equals(setManagerPermission)) {
				return;
			} // end if
			// super.checkPermission( thePermission );
		} // end method checkPermission( Permission )

	} // end class TestSecurityManager

} // end class DriverManagerTest


