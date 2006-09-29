/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author  Victor A. Martynov
 * @version $Revision: 1.6.2.3 $
 */
package java.rmi.activation;

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * @com.intel.drl.spec_ref
 *
 * @author  Victor A. Martynov
 * @version $Revision: 1.6.2.3 $
 */
public interface ActivationSystem extends Remote {

    public static final int SYSTEM_PORT = 1098;

    /**
     * @com.intel.drl.spec_ref
     */
    public ActivationMonitor activeGroup(ActivationGroupID gID,
                                         ActivationInstantiator aInst,
                                         long incarnation)
            throws UnknownGroupException, ActivationException, RemoteException;

    /**
     * @com.intel.drl.spec_ref
     */
    public ActivationGroupDesc setActivationGroupDesc(ActivationGroupID gID,
                                                      ActivationGroupDesc gDesc)
            throws ActivationException, UnknownGroupException, RemoteException;

    /**
     * @com.intel.drl.spec_ref
     */
    public ActivationDesc setActivationDesc(ActivationID aID,
                                            ActivationDesc aDesc)
            throws ActivationException, UnknownObjectException,
                   UnknownGroupException, RemoteException;

    /**
     * @com.intel.drl.spec_ref
     */
    public ActivationID registerObject(ActivationDesc aDesc)
            throws ActivationException, UnknownGroupException, RemoteException;

    /**
     * @com.intel.drl.spec_ref
     */
    public ActivationGroupID registerGroup(ActivationGroupDesc gDesc)
            throws ActivationException, RemoteException;

    /**
     * @com.intel.drl.spec_ref
     */
    public ActivationGroupDesc getActivationGroupDesc(ActivationGroupID gID)
            throws ActivationException, UnknownGroupException, RemoteException;

    /**
     * @com.intel.drl.spec_ref
     */
    public ActivationDesc getActivationDesc(ActivationID aID)
            throws ActivationException, UnknownObjectException, RemoteException;

    /**
     * @com.intel.drl.spec_ref
     */
    public void unregisterObject(ActivationID aID)
            throws ActivationException, UnknownObjectException, RemoteException;

    /**
     * @com.intel.drl.spec_ref
     */
    public void unregisterGroup(ActivationGroupID gID)
            throws ActivationException, UnknownGroupException, RemoteException;

    /**
     * @com.intel.drl.spec_ref
     */
    public void shutdown() throws RemoteException;
}
