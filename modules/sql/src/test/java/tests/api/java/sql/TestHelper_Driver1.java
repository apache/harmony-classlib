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

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

/**
 * A simple implementation of a class implementing a JDBC Driver, for use in the
 * testing of the java.sql.DriverManager class
 * 
 */
public class TestHelper_Driver1 implements Driver {

	int majorVersion = 1;

	int minorVersion = 0;

	String baseURL = "jdbc:mikes1";

	String[] dataSources = { "data1", "data2", "data3" };

	static Driver theDriver;

	static {
		theDriver = new TestHelper_Driver1();
		/*
		 * System.out.println("Driver1 classloader: " +
		 * theDriver.getClass().getClassLoader() ); System.out.println("Driver1
		 * object is: " + theDriver );
		 */
		try {
			DriverManager.registerDriver(theDriver);
		} catch (SQLException e) {
			System.out.println("Failed to register driver!");
		}
	} // end static block initializer

	protected TestHelper_Driver1() {
		super();
	} // end constructor TestHelper_Driver1()

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Driver#acceptsURL(java.lang.String) @param url a url
	 *      specification for a datasource or a general url for this driver
	 */
	public boolean acceptsURL(String url) throws SQLException {
		// Check on the supplied String...
		if (url == null)
			return false;
		// Everything's fine if the quoted url starts with the base url for this
		// driver
		if (url.startsWith(baseURL))
			return true;
		return false;
	} // end method acceptsURL

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Driver#connect(java.lang.String, java.util.Properties)
	 */
	static String validuser = "theuser";

	static String validpassword = "thepassword";

	static String userProperty = "user";

	static String passwordProperty = "password";

	public Connection connect(String url, Properties info) throws SQLException {
		// Does the URL have the right form?
		if (this.acceptsURL(url)) {
			// The datasource name is the remainder of the url after the ":"
			String datasource = url.substring(baseURL.length() + 1);
			// Loop over the valid datasources supported by this driver
			for (int i = 0; i < dataSources.length; i++) {
				if (datasource.equals(dataSources[i])) {
					// Check for user and password, except for datasource =
					// data1
					// which is set up not to require a user/password
					// combination
					if (datasource.equals("data1")) {
						// do nothing...
					} else {
						if (info == null)
							throw new SQLException("Properties bundle is null");
						String user = (String) info.get(userProperty);
						String password = (String) info.get(passwordProperty);
						if (user == null || password == null) {
							throw new SQLException(
									"Userid and/or password not supplied");
						} else {
							if (!user.equals(validuser)
									|| !password.equals(validpassword)) {
								throw new SQLException(
										"Userid and/or password not valid");
							} // end if
						} // end if
					} // end if
					// It all checks out - so return a connection
					Connection connection = new TestHelper_Connection1();
					return connection;
				} // end if
			} // end for
		} // end if
		return null;
	} // end method connect(String, Properties)

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Driver#getMajorVersion()
	 */
	public int getMajorVersion() {

		return majorVersion;
	} // end method getMajorVersion()

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Driver#getMinorVersion()
	 */
	public int getMinorVersion() {

		return minorVersion;
	} // end method getMinorVersion()

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Driver#getPropertyInfo(java.lang.String,
	 *      java.util.Properties)
	 */
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info)
			throws SQLException {
		DriverPropertyInfo[] theInfos = {
				new DriverPropertyInfo(userProperty, "*"),
				new DriverPropertyInfo(passwordProperty, "*"), };
		return theInfos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Driver#jdbcCompliant()
	 */
	public boolean jdbcCompliant() {
		// Basic version here returns false
		return false;
	}

}

