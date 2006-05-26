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
package java.rmi.registry;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;

import org.apache.harmony.rmi.internal.registry.RegistryImpl;

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public final class LocateRegistry {

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static Registry createRegistry(int port) throws RemoteException {
        RegistryImpl registry = new RegistryImpl();
        registry.exportObject(port, null, null, false);
        return registry;
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static Registry createRegistry(int port, RMIClientSocketFactory csf,
            RMIServerSocketFactory ssf) throws RemoteException {
        RegistryImpl registry = new RegistryImpl();
        registry.exportObject(port, csf, ssf, true);
        return registry;
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static Registry getRegistry() throws RemoteException {
        return (Registry) RegistryImpl.createStub(Registry.REGISTRY_PORT, null,
                null, false);
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static Registry getRegistry(int port) throws RemoteException {
        return (Registry) RegistryImpl.createStub(port, null, null, false);
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static Registry getRegistry(String host) throws RemoteException {
        return (Registry) RegistryImpl.createStub(Registry.REGISTRY_PORT, host,
                null, false);
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static Registry getRegistry(String host, int port)
            throws RemoteException {
        return (Registry) RegistryImpl.createStub(port, host, null, false);
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static Registry getRegistry(String host, int port,
            RMIClientSocketFactory csf) throws RemoteException {
        return (Registry) RegistryImpl.createStub(port, host, csf, true);
    }
}
