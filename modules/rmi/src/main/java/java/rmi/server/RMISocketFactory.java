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

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.harmony.rmi.internal.transport.RMIDefaultSocketFactory;

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public abstract class RMISocketFactory implements RMIClientSocketFactory,
        RMIServerSocketFactory {

    private static RMIFailureHandler failureHandler;

    private static RMISocketFactory socketFactory;

    private static RMISocketFactory defaultSocketFactory;

    static {
        defaultSocketFactory = new RMIDefaultSocketFactory();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public RMISocketFactory() {
    	super();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public abstract Socket createSocket(String host, int port)
            throws IOException;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public abstract ServerSocket createServerSocket(int port)
            throws IOException;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static RMISocketFactory getDefaultSocketFactory() {
        return defaultSocketFactory;
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static RMIFailureHandler getFailureHandler() {
        return failureHandler;
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static RMISocketFactory getSocketFactory() {
        return socketFactory;
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static void setFailureHandler(RMIFailureHandler fh) {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkSetFactory();
        }
        failureHandler = fh;
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static void setSocketFactory(RMISocketFactory fac)
            throws IOException {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkSetFactory();
        }
        if (socketFactory == null) {
            socketFactory = fac;
        } else {
            throw new IOException("RMI Socket Factory already set");
        }
    }
}
