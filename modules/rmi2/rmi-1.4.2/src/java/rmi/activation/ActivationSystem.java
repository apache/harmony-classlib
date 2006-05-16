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

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public interface ActivationSystem extends Remote {

    int SYSTEM_PORT = 1098;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    ActivationMonitor activeGroup(ActivationGroupID id,
            ActivationInstantiator group, long incarnation)
            throws UnknownGroupException, ActivationException, RemoteException;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    ActivationDesc getActivationDesc(ActivationID id)
            throws ActivationException, UnknownObjectException, RemoteException;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    ActivationGroupDesc getActivationGroupDesc(ActivationGroupID id)
            throws ActivationException, UnknownGroupException, RemoteException;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    ActivationGroupID registerGroup(ActivationGroupDesc desc)
            throws ActivationException, RemoteException;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    ActivationID registerObject(ActivationDesc desc)
            throws ActivationException, UnknownGroupException, RemoteException;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    ActivationDesc setActivationDesc(ActivationID id, ActivationDesc desc)
            throws ActivationException, UnknownObjectException,
            UnknownGroupException, RemoteException;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    ActivationGroupDesc setActivationGroupDesc(ActivationGroupID id,
            ActivationGroupDesc desc) throws ActivationException,
            UnknownGroupException, RemoteException;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    void unregisterGroup(ActivationGroupID id) throws ActivationException,
            UnknownGroupException, RemoteException;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    void unregisterObject(ActivationID id) throws ActivationException,
            UnknownObjectException, RemoteException;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    void shutdown() throws RemoteException;
}
