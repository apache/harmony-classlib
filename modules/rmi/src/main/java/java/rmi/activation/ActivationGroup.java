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
 * @version $Revision: 1.17.2.3 $
 */
package java.rmi.activation;

import java.lang.reflect.Constructor;
import java.rmi.MarshalledObject;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RMIClassLoader;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;
import java.security.AccessController;

import org.apache.harmony.rmi.common.GetStringPropAction;
import org.apache.harmony.rmi.common.RMILog;


/**
 * @com.intel.drl.spec_ref
 *
 * @author  Victor A. Martynov
 * @version $Revision: 1.17.2.3 $
 */
public abstract class ActivationGroup extends UnicastRemoteObject
        implements ActivationInstantiator {

    /**
     * The ActivationSystem for this VM.
     */
    private static ActivationSystem current_AS;

    /**
     * Current ActivationGroupID
     */
    private static ActivationGroupID current_AGID;

    /**
     * Current ActivationGroup.
     */
    private static ActivationGroup current_AG;

    /**
     * Boolean value that is used to prohibit the recurring creation of
     * ActivationGroup for this VM.
     */
    private static boolean isGroupCreated = false;

    /**
     * Information got from ActivationGroup Serialized form.
     */
    private static final long serialVersionUID = -7696947875314805420L;

    private ActivationGroupID groupID;

    private ActivationMonitor monitor;

    private long incarnation;

    private static RMILog rlog = RMILog.getActivationLog();

    /**
     * @com.intel.drl.spec_ref
     */
    protected ActivationGroup(ActivationGroupID groupID) throws RemoteException {
        /**
         * We need to export this group, so we call the constructor of the
         * superclass.
         */
        super(0);
        this.groupID = groupID;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected void activeObject(ActivationID id, MarshalledObject mobj)
            throws ActivationException, UnknownObjectException, RemoteException {
        try {
            Thread.sleep(500);
        } catch (Throwable t) {
        }
        rlog.log(RMILog.VERBOSE, "ActivationGroup.activeObject: " + id
                + "; " + mobj);
        rlog.log(RMILog.VERBOSE, "monitor: " + monitor);
        monitor.activeObject(id, mobj);
        rlog
                .log(RMILog.VERBOSE,
                        "ActivationGroup.activeObject finished.");
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract void activeObject(ActivationID id, Remote obj)
            throws ActivationException, UnknownObjectException, RemoteException;

    /**
     * @com.intel.drl.spec_ref
     */
    public static synchronized ActivationGroup createGroup(
            ActivationGroupID id, ActivationGroupDesc desc, long incarnation)
            throws ActivationException {
        rlog.log(RMILog.VERBOSE, "ActivationGroup.createGroup [id=" + id
                + ", desc=" + desc + ", incarnation=" + incarnation);

        SecurityManager sm = System.getSecurityManager();

        if(sm != null) {
            sm.checkSetFactory();
        }

        /*
         * Classname of the ActivationGroup implemenation. If the group class
         * name was given in 'desc' assign it to group_CN, use default
         * otherwise.
         */
        String group_CN = (desc.getClassName() == null) ?
                "org.apache.harmony.rmi.activation.ActivationGroupImpl"
                : desc.getClassName();

        rlog.log(RMILog.VERBOSE, "group_CN = " + group_CN);

        if (current_AG != null) {
            throw new ActivationException(
                    "The ActivationGroup for this VM already exists.");
        }
        try {
            rlog.log(RMILog.VERBOSE, "Ready to load ActivationGroupImpl class");
            Class cl = RMIClassLoader.loadClass(desc.getLocation(), group_CN);
            rlog.log(RMILog.VERBOSE, "ag class = " + cl);

            Class[] special_constructor_parameter_classes = {
                    ActivationGroupID.class, MarshalledObject.class };

            Constructor constructor = cl
                    .getConstructor(special_constructor_parameter_classes);
            Object[] constructor_parameters = { id, desc.getData() };
            ActivationGroup ag = (ActivationGroup) constructor
                    .newInstance(constructor_parameters);
            rlog.log(RMILog.VERBOSE, "ag = " + ag);
            current_AS = id.getSystem();
            rlog.log(RMILog.VERBOSE, "current_AS = " + current_AS);

            ag.incarnation = incarnation;
            rlog.log(RMILog.VERBOSE, "ag.incarnation = " + ag.incarnation);

            ag.monitor = current_AS.activeGroup(id, ag, incarnation);
            rlog.log(RMILog.VERBOSE, "ag.monitor = " + ag.monitor);

            current_AG = ag;
            current_AGID = id;
            isGroupCreated = true;

        } catch (Throwable t) {
            rlog.log(RMILog.VERBOSE, "Exception in createGroup: " + t);
            t.printStackTrace();
            throw new ActivationException("Unable to create group.", t);
        }
        rlog.log(RMILog.VERBOSE, "Group created: " + current_AG);

        return current_AG;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static synchronized ActivationGroupID currentGroupID() {
        return current_AGID;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static synchronized ActivationSystem getSystem()
            throws ActivationException {
        rlog.log(RMILog.VERBOSE,
                "---------- ActivationGroup.getSystem() ----------");
        System.out.flush();

        try {
            if (current_AS == null) {

                String port = (String)AccessController.doPrivileged(
                        new GetStringPropAction("java.rmi.activation.port",
                                                ActivationSystem.SYSTEM_PORT+""));

                current_AS = (ActivationSystem) Naming.lookup("//:" + port
                        + "/java.rmi.activation.ActivationSystem");
                rlog.log(RMILog.VERBOSE,
                        "Activation System was got using Naming.lookup() at port "
                                + port);
            }
        } catch (Throwable t) {
            throw new ActivationException("getSystem fails.", t);
        }
        rlog.log(RMILog.VERBOSE, "current_AS = " + current_AS);
        rlog.log(RMILog.VERBOSE, "current_AS.ref = "
                + ((RemoteObject) current_AS).getRef());

        rlog.log(RMILog.VERBOSE,
                "---------- END -> ActivationGroup.getSystem() ----------");

        return current_AS;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected void inactiveGroup() throws UnknownGroupException,
            RemoteException {
        monitor.inactiveGroup(groupID, incarnation);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean inactiveObject(ActivationID id) throws ActivationException,
            UnknownObjectException, RemoteException {
        monitor.inactiveObject(id);
        return true;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static synchronized void setSystem(ActivationSystem system)
            throws ActivationException {
        if (current_AS != null) {
            throw new ActivationException(
                    "The ActivationSystem for this ActivationGroup was already defined.");
        }
        current_AS = system;
    }

    /* ********************************************************************* */
    /* ************** Package protected methods **************************** */
    /* ********************************************************************* */

    static synchronized ActivationGroup getCurrentAG() {
        return current_AG;
    }
}
