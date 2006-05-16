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
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public abstract class ActivationGroup extends UnicastRemoteObject implements
        ActivationInstantiator {

    private static final long serialVersionUID = -7696947875314805420L;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    protected ActivationGroup(ActivationGroupID groupID) throws RemoteException {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public abstract void activeObject(ActivationID id, Remote obj)
            throws ActivationException, UnknownObjectException, RemoteException;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    protected void activeObject(ActivationID id, MarshalledObject mobj)
            throws ActivationException, UnknownObjectException, RemoteException {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static synchronized ActivationGroupID currentGroupID() {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static synchronized ActivationSystem getSystem()
            throws ActivationException {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    protected void inactiveGroup() throws UnknownGroupException,
            RemoteException {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public boolean inactiveObject(ActivationID id) throws ActivationException,
            UnknownObjectException, RemoteException {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static synchronized void setSystem(ActivationSystem system)
            throws ActivationException {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static synchronized ActivationGroup createGroup(
            ActivationGroupID arg0, ActivationGroupDesc arg1, long arg2)
            throws ActivationException {
        throw new UnsupportedOperationException();
    }
}
