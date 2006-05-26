/*
 * Copyright 2005-2006 The Apache Software Foundation or its licensors, as applicable
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
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author  Victor A. Martynov
 * @version $Revision: 1.1.2.4 $
 */
package org.apache.harmony.rmi.activation;

import java.io.BufferedInputStream;
import java.lang.reflect.Constructor;
import java.rmi.MarshalledObject;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.activation.Activatable;
import java.rmi.activation.ActivationDesc;
import java.rmi.activation.ActivationException;
import java.rmi.activation.ActivationGroup;
import java.rmi.activation.ActivationGroupDesc;
import java.rmi.activation.ActivationGroupID;
import java.rmi.activation.ActivationID;
import java.rmi.activation.UnknownObjectException;
import java.rmi.server.RMIClassLoader;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.Hashtable;

import org.apache.harmony.rmi.common.RMILog;
import org.apache.harmony.rmi.transport.RMIObjectInputStream;


/**
 * The implementation of ActivationGroup.
 *
 * @author  Victor A. Martynov
 * @version $Revision: 1.1.2.4 $
 */
public class ActivationGroupImpl extends ActivationGroup {

    private static final long serialVersionUID = 5526311808915869570L;

    private ActivationGroupID groupID;

    private boolean isGroupActive = true;

    private static RMILog rlog = RMILog.getActivationLog();

    private Hashtable active_objects = new Hashtable();

    /**
     *
     */
    private Class[] special_constructor_parameters = { ActivationID.class,
            MarshalledObject.class };

    /**
     * This main method is used to start new VMs for ActivationGroups. Four
     * parameters needed to create ActivationGroup are: <br>
     * ActivationGroupID <br>
     * ActivationGroupDesc <br>
     * incarnation The parameters needed to create ActivationGroup correctly are
     * passed through the stardard input stream in the following order: <br>
     * ActivationGroupID -> ActivationGroupDesc -> incarnation
     */
    public static void main(String args[]) {
        rlog.log(RMILog.VERBOSE, "ActivationGroupImpl.main: ");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        try {
            rlog.log(RMILog.VERBOSE, "System.in.available = "
                    + System.in.available());

            RMIObjectInputStream ois = new RMIObjectInputStream(
                    new BufferedInputStream(System.in));
            rlog.log(RMILog.VERBOSE, "ois = " + ois);
            ActivationGroupID agid = (ActivationGroupID) ois.readObject();
            rlog.log(RMILog.VERBOSE, "agid = " + agid);
            ActivationGroupDesc agdesc = (ActivationGroupDesc) ois.readObject();
            rlog.log(RMILog.VERBOSE, "agdesc = " + agdesc);
            long incarnation = ois.readLong();
            rlog.log(RMILog.VERBOSE, "incarnation=" + incarnation);
            ActivationGroup.createGroup(agid, agdesc, incarnation);
        } catch (Throwable t) {
            rlog.log(RMILog.VERBOSE, ": " + "Exception: " + t);
            t.printStackTrace();
        }
    }

    /**
     * Method from ActivationInstantiator interface.
     */
    public MarshalledObject newInstance(final ActivationID aid,
            final ActivationDesc adesc) throws ActivationException {

        rlog.log(RMILog.VERBOSE, "ActivationGroupImpl" + ": "
                + "ActivationGroupImpl.newInstance started.");
        // Checking that we try to activate object in correct group.
        if (!groupID.equals(adesc.getGroupID())) {
            throw new ActivationException(
                    "Attempt to activate object from different group.");
        }

        if (isGroupActive == false) {
            throw new ActivationException(
                    "Attempt to activate object in inactive group.");
        }

        /**
         */

        ActiveObject ao = (ActiveObject) active_objects.get(aid);
        rlog.log(RMILog.VERBOSE, "ActivationGroupImpl" + ": "
                + "active object = " + ao);

        if (ao != null) {
            return ao.remote_object_stub;
        }
        try {

            rlog.log(RMILog.VERBOSE, "Ready to load active class: [location="
                    + adesc.getLocation() + "; name=" + adesc.getClassName()
                    + "]");
            final Class aclass = RMIClassLoader.loadClass(adesc.getLocation(),
                    adesc.getClassName());
            rlog.log(RMILog.VERBOSE, "active class = " + aclass);

            Remote rmt = (Remote) AccessController
                    .doPrivileged(new PrivilegedExceptionAction() {

                        public Object run() throws Exception {
                            Constructor aconstructor = aclass
                                    .getDeclaredConstructor(special_constructor_parameters);
                            rlog.log(RMILog.VERBOSE,
                                    "Activatable Constructor: " + aconstructor);

                            aconstructor.setAccessible(true);

                            Object parameters[] = new Object[] { aid,
                                    adesc.getData() };
                            return (Remote) aconstructor
                                    .newInstance(parameters);
                        }
                    });

            rlog.log(RMILog.VERBOSE, "rmt.getClass = " + rmt.getClass());

            rlog.log(RMILog.VERBOSE, "newInstance: Remote Object = " + rmt);

            ao = new ActiveObject(rmt);
            rlog.log(RMILog.VERBOSE, "active object  = " + ao);

            active_objects.put(aid, ao);
            rlog.log(RMILog.VERBOSE, "ao was put into Hashtable");
            rlog.log(RMILog.VERBOSE, "calling newInstance of the superclass.");

            super.activeObject(aid, ao.remote_object_stub);
            return ao.remote_object_stub;
        } catch (Throwable t) {
            rlog.log(RMILog.VERBOSE, "Exception: " + t, t);
            return null;
        }
    }

    /**
     * Constructor
     */
    public ActivationGroupImpl(ActivationGroupID agid, MarshalledObject data)
            throws RemoteException, ActivationException {
        super(agid);
        groupID = agid;
        rlog.log(RMILog.VERBOSE, "ActivationGroup was constructed.");
    }

    public boolean inactiveObject(ActivationID id) throws ActivationException,
            UnknownObjectException, RemoteException {
        ActiveObject ao = (ActiveObject) active_objects.get(id);
        if (ao == null) {
            throw new UnknownObjectException(
                    "Object was not registered or already deactivated.");
        }

        Activatable.unexportObject(ao.getImpl(), false);
        super.inactiveObject(id);
        active_objects.remove(id);
        return true;
    }

    public void activeObject(ActivationID id, Remote obj)
            throws ActivationException, UnknownObjectException, RemoteException {
        ActiveObject ao = new ActiveObject(obj);
        active_objects.put(id, ao);
        super.activeObject(id, ao.getStub());
    }

    private class ActiveObject {
        Remote remote_object_impl;

        MarshalledObject remote_object_stub;

        ActiveObject(Remote rmt) {

            rlog.log(RMILog.VERBOSE, "ActiveObject" + ": "
                    + "ActiveObject.<init>:");
            remote_object_impl = rmt;
            try {

                remote_object_stub = new MarshalledObject(rmt);
                rlog.log(RMILog.VERBOSE, "ActiveObject" + ": "
                        + "remote_object_impl = " + remote_object_impl
                        + "; remote_object_stub=" + remote_object_stub);
            } catch (Throwable t) {
                rlog.log(RMILog.VERBOSE, "ActiveObject" + ": "
                        + "Failed to marshal remote stub: " + t);
                t.printStackTrace();
            }
        }

        public Remote getImpl() {
            return remote_object_impl;
        }

        public MarshalledObject getStub() {
            return remote_object_stub;
        }
    }
}
