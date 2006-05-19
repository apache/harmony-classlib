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
package java.rmi.activation;

import java.rmi.MarshalledObject;
import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.RemoteServer;

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public abstract class Activatable extends RemoteServer {

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    protected Activatable(ActivationID id, int port) {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    protected Activatable(ActivationID id, int port,
            RMIClientSocketFactory csf, RMIServerSocketFactory ssf) {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    protected Activatable(String location, MarshalledObject data,
            boolean restart, int port) {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    protected Activatable(String location, MarshalledObject data,
            boolean restart, int port, RMIClientSocketFactory csf,
            RMIServerSocketFactory ssf) {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static ActivationID exportObject(Remote obj, String location,
            MarshalledObject data, boolean restart, int port)
            throws ActivationException, RemoteException {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static ActivationID exportObject(Remote obj, String location,
            MarshalledObject data, boolean restart, int port,
            RMIClientSocketFactory csf, RMIServerSocketFactory ssf)
            throws ActivationException, RemoteException {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static Remote exportObject(Remote obj, ActivationID id, int port)
            throws RemoteException {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static Remote exportObject(Remote obj, ActivationID id, int port,
            RMIClientSocketFactory csf, RMIServerSocketFactory ssf)
            throws RemoteException {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static boolean inactive(ActivationID id)
            throws UnknownObjectException, ActivationException, RemoteException {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static boolean unexportObject(Remote obj, boolean force)
            throws NoSuchObjectException {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static Remote register(ActivationDesc desc)
            throws UnknownGroupException, ActivationException, RemoteException {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    protected ActivationID getID() {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static void unregister(ActivationID id)
            throws UnknownObjectException, ActivationException, RemoteException {
        throw new UnsupportedOperationException();
    }
}
