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

package java.sql;

import java.util.Properties;
import java.util.Enumeration;
import java.util.Iterator;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Vector;
import org.apache.harmony.sql.internal.common.ClassUtils;
import org.apache.harmony.kernel.vm.VM;

/**
 * Provides facilities for managing JDBC Drivers.
 * <p>
 * The DriverManager class will load JDBC drivers during its initialization,
 * from the list of drivers referenced by the System Property "jdbc.drivers".
 * 
 */
public class DriverManager {

	// Facilities for logging. The Print Stream is deprecated but is maintained
	// here for compatibility.
	private static PrintStream thePrintStream = null;

	private static PrintWriter thePrintWriter = null;

	// Login timeout value - by default set to 0 -> "wait forever"
	private static int loginTimeout = 0;

	// Set to hold Registered Drivers - initial capacity 10 drivers (will expand
	// automatically
	// if necessary.
	private static HashSet theDriverSet = new HashSet(10);

	// Permission for setting log
	private static SQLPermission logPermission = new SQLPermission("setLog"); //$NON-NLS-1$

	/*
	 * Load drivers on initialization
	 */
	static {
		// System.out.println("Clear version of java.sql.DriverManager loaded")
		// ;
		loadInitialDrivers();
	} // end static initializer

	/*
	 * Loads the set of JDBC drivers defined by the Property "jdbc.drivers" if
	 * it is defined.
	 */
	private static void loadInitialDrivers() {
		String theDriverList = System.getProperty("jdbc.drivers", null); //$NON-NLS-1$
		if (theDriverList == null)
			return;

		// System.out.println( "Loading initial drivers: " + theDriverList );

		// Get the names of the drivers as an array of Strings from the system
		// property by
		// splitting the property at the separator character ':'
		String[] theDriverNames = theDriverList.split(":"); //$NON-NLS-1$

		for (int i = 0; i < theDriverNames.length; i++) {
			// System.out.println( "Driver: " + (i + 1) + " " +
			// theDriverNames[i] );

			try {
				// Load the driver class
				Class.forName(theDriverNames[i], true, ClassLoader
						.getSystemClassLoader());
			} catch (ClassNotFoundException e) {
				// System.out.println("DriverManager: Unable to load driver: " +
				// theDriverNames[i] );
			} catch (Throwable t) {
				// System.out.println("DriverManager: Exception loading driver:
				// " + theDriverNames[i] );
				// t.getMessage();
				// t.printStackTrace();
			} // end try
		} // end for

	} // end method loadInitialDrivers()

	/*
	 * A private constructor to prevent allocation
	 */
	private DriverManager() {
		super();
	} // end method DriverManager()

	/**
	 * Removes a driver from the DriverManager's registered driver list. This
	 * will only succeed where the caller's classloader loaded the driver that
	 * is to be removed. If the driver was loaded by a different classloader,
	 * the removal of the driver will fail silently.
	 * <p>
	 * If the removal succeeds, the DriverManager will not in future use this
	 * driver when asked to get a Connection.
	 * 
	 * @param driver
	 * @throws SQLException
	 *             if there is an exception accessing the database.
	 */
	public static void deregisterDriver(Driver driver) throws SQLException {
		if (driver == null)
			return;
		ClassLoader callerClassLoader = VM.callerClassLoader();

		if (!ClassUtils.isClassFromClassLoader(driver, callerClassLoader)) {
			throw new SecurityException(
					"DriverManager: calling class not authorized to deregister JDBC driver");
		} // end if
		synchronized (theDriverSet) {
			theDriverSet.remove(driver);
		} // end synchronized

	} // end method deregisterDriver( Driver );

	/**
	 * Attempts to establish a connection to the given database URL.
	 * 
	 * @param url
	 *            a URL string representing the database target to connect with
	 * @return a Connection to the database identified by the URL. null if no
	 *         connection can be made.
	 * @throws SQLException
	 *             if there is an error while attempting to connect to the
	 *             database identified by the URL
	 */
	public static Connection getConnection(String url) throws SQLException {
		// Call the getConnection method with an empty Properties parameter
		return getConnection(url, new Properties());
	} // end method getConnection( String )

	/**
	 * Attempts to establish a connection to the given database URL.
	 * 
	 * @param url
	 *            a URL string representing the database target to connect with
	 * @param info
	 *            a set of Properties to use as arguments to set up the
	 *            connection. Properties are arbitrary string/value pairs.
	 *            Normally, at least the properties "user" and "password" should
	 *            be passed, with appropriate settings for the userid and its
	 *            corresponding password to get access to the database
	 *            concerned.
	 * @return a Connection to the database identified by the URL. null if no
	 *         connection can be made.
	 * @throws SQLException
	 *             if there is an error while attempting to connect to the
	 *             database identified by the URL
	 */
	public static Connection getConnection(String url, Properties info)
			throws SQLException {
		if (url == null)
			throw new SQLException("The url cannot be null");
		synchronized (theDriverSet) {
			// Loop over the drivers in the DriverSet checking to see if one can
			// open a
			// connection to the supplied URL - return the first connection
			// which is returned
			Iterator theIterator = theDriverSet.iterator();
			while (theIterator.hasNext()) {
				Driver theDriver = (Driver) theIterator.next();
				Connection theConnection = theDriver.connect(url, info);
				if (theConnection != null)
					return theConnection;
			} // end while
		} // end synchronized
		// If we get here, none of the drivers are able to resolve the URL
		throw new SQLException("No suitable driver");
	} // end method getConnection( String, Properties )

	/**
	 * Attempts to establish a connection to the given database URL.
	 * 
	 * @param url
	 *            a URL string representing the database target to connect with
	 * @param user
	 *            a userid used to login to the database
	 * @param password
	 *            a password for the userid to login to the database
	 * @return a Connection to the database identified by the URL. null if no
	 *         connection can be made.
	 * @throws SQLException
	 *             if there is an error while attempting to connect to the
	 *             database identified by the URL
	 */
	public static Connection getConnection(String url, String user,
			String password) throws SQLException {
		if (user == null || password == null) {
			throw new SQLException("Userid and/or password not supplied");
		} // end if
		Properties theProperties = new Properties();
		theProperties.setProperty("user", user); //$NON-NLS-1$
		theProperties.setProperty("password", password); //$NON-NLS-1$
		return getConnection(url, theProperties);
	} // end method getConnection( String, String, String )

	/**
	 * Tries to find a driver that can interpret the supplied URL.
	 * 
	 * @param url
	 *            the URL of a database
	 * @return a Driver that can understand the given URL. null if no Driver
	 *         understands the URL
	 * @throws SQLException
	 *             if there is any kind of Database Access problem
	 */
	public static Driver getDriver(String url) throws SQLException {
		ClassLoader callerClassLoader = VM.callerClassLoader();

		synchronized (theDriverSet) {
			// Loop over the drivers in the DriverSet checking to see if one
			// does
			// understand the supplied URL - return the first driver which does
			// understand
			// the URL
			Iterator theIterator = theDriverSet.iterator();
			while (theIterator.hasNext()) {
				Driver theDriver = (Driver) theIterator.next();
				if (theDriver.acceptsURL(url)
						&& ClassUtils.isClassFromClassLoader(theDriver,
								callerClassLoader))
					return theDriver;
			} // end while
		} // end synchronized
		// If no drivers understand the URL, throw an SQLException
		throw new SQLException("No suitable driver");
	} // end method getDriver( String )

	/**
	 * Returns an Enumeration that contains all of the loaded JDBC drivers that
	 * the current caller can access.
	 * 
	 * @return An Enumeration containing all the currently loaded JDBC Drivers
	 */
	public static Enumeration<Driver> getDrivers() {
		Enumeration theEnumeration;

		ClassLoader callerClassLoader = VM.callerClassLoader();
		// Synchronize to avoid clashes with additions and removals of drivers
		// in the
		// DriverSet
		synchronized (theDriverSet) {
			// Create the Enumeration by building a Vector from the elements of
			// the
			// DriverSet
			Vector theVector = new Vector();
			Iterator theIterator = theDriverSet.iterator();
			while (theIterator.hasNext()) {
				Driver theDriver = (Driver) theIterator.next();
				if (ClassUtils.isClassFromClassLoader(theDriver,
						callerClassLoader)) {
					theVector.add(theDriver);
				} // end if
			} // end for
			theEnumeration = theVector.elements();
		} // end synchronized
		return theEnumeration;
	} // end method getDrivers()

	/**
	 * Returns the login timeout when connecting to a database, in seconds.
	 * 
	 * @return the login timeout in seconds
	 */
	public static int getLoginTimeout() {
		return loginTimeout;
	} // end method getLoginTimeout()

	/**
	 * @deprecated Gets the log PrintStream used by the DriverManager and all
	 *             the JDBC Drivers.
	 * @return the PrintStream used for logging activity
	 */
	public static PrintStream getLogStream() {
		return thePrintStream;
	} // end method getLogStream()

	/**
	 * Retrieves the log writer.
	 * 
	 * @return A PrintWriter object used as the log writer. null if no log
	 *         writer is set.
	 */
	public static PrintWriter getLogWriter() {
		return thePrintWriter;
	} // end method getLogWriter()

	/**
	 * Prints a message to the current JDBC log stream. This is either the
	 * PrintWriter or (deprecated) the PrintStream, if set.
	 * 
	 * @param message
	 *            the message to print to the JDBC log stream
	 */
	public static void println(String message) {
		if (thePrintWriter != null) {
			thePrintWriter.println(message);
			thePrintWriter.flush();
		} else if (thePrintStream != null) {
			thePrintStream.println(message);
			thePrintStream.flush();
		} // end if
		// If neither the PrintWriter not the PrintStream are set, then silently
		// do nothing
		// the message is not recorded and no exception is generated.
		return;
	} // end method println( String )

	/**
	 * Registers a given JDBC driver with the DriverManager.
	 * <p>
	 * A newly loaded JDBC driver class should register itself with the
	 * DriverManager by calling this method.
	 * 
	 * @param driver
	 *            the Driver to register with the DriverManager
	 * @throws SQLException
	 *             if a database access error occurs.
	 */
	public static void registerDriver(Driver driver) throws SQLException {
		if (driver == null)
			throw new NullPointerException();
		synchronized (theDriverSet) {
			theDriverSet.add(driver);
		} // end synchronized
	} // end method registerDriver( Driver )

	/**
	 * Set the login timeout when connecting to a database, in seconds.
	 * 
	 * @param seconds
	 *            seconds until timeout. 0 indicates wait forever.
	 */
	public static void setLoginTimeout(int seconds) {
		loginTimeout = seconds;
		return;
	} // end method setLoginTimeout( int )

	/**
	 * @deprecated Sets the Print Stream to use for logging data from the
	 *             DriverManager and the JDBC drivers.
	 *             <p>
	 *             Use setLogWriter instead.
	 * @param out
	 *            the PrintStream to use for logging.
	 */
	public static void setLogStream(PrintStream out) {
		checkLogSecurity();
		thePrintStream = out;
	} // end method setLogStream( PrintStream )

	/**
	 * Sets the PrintWriter that will be used by all loaded drivers, and also
	 * the DriverManager.
	 * 
	 * @param out
	 *            the PrintWriter to be used
	 */
	public static void setLogWriter(PrintWriter out) {
		checkLogSecurity();
		thePrintWriter = out;
	} // end method setLogWriter( PrintWriter )

	/*
	 * Method which checks to see if setting a logging stream is allowed by the
	 * Security manager
	 */
	private static void checkLogSecurity() {
		SecurityManager securityManager = System.getSecurityManager();
		if (securityManager != null) {
			// Throws a SecurityException if setting the log is not permitted
			securityManager.checkPermission(logPermission);
		} // end checkLogSecurity
	} // end method checkLogSecurity

} // end class DriverManager

