/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package ar.org.fitc.test.rmi.integration.fase2.clientExecutor;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Provides network utilities on the <code>Registry</code>. 
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 *
 * @version 1.0
 */
public class Net {

	/**
	 * The port number on which the <code>Registry</code> is exported.
	 */
	private static int port;
	
	/**
	 * Sets the port value. 
	 */
	static {
		String sport = System.getProperty("ar.org.fitc.test.port");
		if (sport != null) {
			port = Integer.parseInt(sport);
		} else {
			port = 1099;
		}
	}
	
	/**
	 * Default constructor.
	 * 
	 */
	public Net() {
		super();
	}
	
	/**
	 * Returns the port on which the <code>Registry</code> is exported.
	 * 
	 * @return the registry port 
	 */

	public static int getRegistryPort() {
		return port;
	}

	/**
	 * Returns the IP address of the local host.
	 * 
	 * @return an IP address
	 * @throws UnknownHostException if the IP address of a host 
	 * could not be determined. 
	 */
    public static String ip() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        byte[] ipAddr = address.getAddress();
        String ip = new String(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2]
                + "." + ipAddr[3]);
        return ip;
    }

}
