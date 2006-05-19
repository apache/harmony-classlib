/* 
*  Copyright 2005 The Apache Software Foundation or its licensors, as applicable. 
* 
*  Licensed under the Apache License, Version 2.0 (the "License"); 
*  you may not use this file except in compliance with the License. 
*  You may obtain a copy of the License at 
* 
*    http://www.apache.org/licenses/LICENSE-2.0 
* 
*  Unless required by applicable law or agreed to in writing, software 
*  distributed under the License is distributed on an "AS IS" BASIS, 
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
*  See the License for the specific language governing permissions and 
*  limitations under the License. 
*/
package java.rmi.server;

import java.io.PrintStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.LoggingPermission;

import org.apache.harmony.rmi.internal.transport.TransportManager;
import org.apache.harmony.rmi.internal.utils.PrintStreamHandler;
import org.apache.harmony.rmi.internal.utils.PropertiesReader;

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public abstract class RemoteServer extends RemoteObject {

	private static final long serialVersionUID = -4100238210092549637L;

	static private PrintStream logStream;

	private static Logger logger;

	private static Logger serverLog;

	private static PrintStreamHandler logHandler;

	// Initialize loggers
	static {
		logger = Logger.getLogger("org.apache.harmony.rmi.internal.server");
		logger.setUseParentHandlers(false);
		logger.setLevel(Level.ALL);

		String propertyValue = 
            PropertiesReader.readString("java.rmi.server.logCalls");
		if (propertyValue != null) {
			boolean logAllowed = propertyValue.equalsIgnoreCase("true");
			if (logAllowed) {
				PrintStreamHandler errHandler = new PrintStreamHandler();
				errHandler.setLevel(Level.ALL);
				logger.addHandler(errHandler);
			}
		}

		// Implementation-specific logger to log server information
		serverLog = Logger.getLogger("org.apache.harmony.rmi.internal.server.log");
        propertyValue = 
            PropertiesReader.readString("org.apache.harmony.rmi.internal.server.log");

        if (propertyValue  != null
				&& propertyValue.equalsIgnoreCase("true")) {
			serverLog.setLevel(Level.ALL);
		} else {
			serverLog.setLevel(Level.OFF);
		}

	}

	/**
	 * @ar.org.fitc.spec_ref
	 * 
	 */
	protected RemoteServer() {
		super();
	}

	/**
	 * @ar.org.fitc.spec_ref
	 * 
	 */
	protected RemoteServer(RemoteRef ref) {
		super(ref);
	}

	/**
	 * @ar.org.fitc.spec_ref
	 * 
	 */
	public static String getClientHost() throws ServerNotActiveException {
		TransportManager tm = TransportManager.getTransportManager();
		return tm.getClientHost();
	}

	/**
	 * @ar.org.fitc.spec_ref
	 * 
	 */
	public static PrintStream getLog() {
		return logStream;
	}

	/**
	 * @ar.org.fitc.spec_ref
	 * 
	 */
	public static void setLog(OutputStream out) {

		// Check security permission as stated in specification
		SecurityManager security = System.getSecurityManager();
		if (security != null) {
			security.checkPermission(new LoggingPermission("control", null));
		}

		logger.removeHandler(logHandler);
		if (out != null) {
			logStream = new PrintStream(out);
			logHandler = new PrintStreamHandler(logStream);
			logHandler.setLevel(Level.ALL);
			logger.addHandler(logHandler);
		} else {
			logStream = null;
		}
		return;
	}
}
