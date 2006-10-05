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
 * @version $Revision: 1.1.2.8 $
 */
package org.apache.harmony.rmi.activation;

import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import java.rmi.ConnectException;
import java.rmi.ConnectIOException;
import java.rmi.MarshalException;
import java.rmi.MarshalledObject;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.activation.ActivationDesc;
import java.rmi.activation.ActivationException;
import java.rmi.activation.ActivationGroup;
import java.rmi.activation.ActivationGroupDesc;
import java.rmi.activation.ActivationGroupID;
import java.rmi.activation.ActivationID;
import java.rmi.activation.ActivationInstantiator;
import java.rmi.activation.ActivationMonitor;
import java.rmi.activation.ActivationSystem;
import java.rmi.activation.Activator;
import java.rmi.activation.UnknownGroupException;
import java.rmi.activation.UnknownObjectException;
import java.rmi.activation.ActivationGroupDesc.CommandEnvironment;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ObjID;
import java.rmi.server.RemoteObject;
import java.rmi.server.RemoteServer;
import java.rmi.server.RemoteStub;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;

import org.apache.harmony.rmi.common.GetBooleanPropAction;
import org.apache.harmony.rmi.common.GetLongPropAction;
import org.apache.harmony.rmi.common.GetStringPropAction;
import org.apache.harmony.rmi.common.RMIConstants;
import org.apache.harmony.rmi.common.RMILog;
import org.apache.harmony.rmi.common.RMIProperties;
import org.apache.harmony.rmi.remoteref.UnicastServerRef;
import org.apache.harmony.rmi.server.ExportManager;
import org.apache.harmony.rmi.transport.RMIObjectOutputStream;


/**
 * Represents rmid - RMI Activation Daemon. Implements
 * all 3 remote interfaces that are essential for Activation:
 * <code>ActivationSystem</code>, <code>ActivationMonitor</code>
 * and <code>Activator</code>. This is done to avoid
 * the multiplication of references pointing to Hashtables that keep
 * information about ActivationGroupIDs and ActivationIDs and their
 * mappings to ActivationGroupDescriptors and ActivationDescriptors.
 *
 * RMID is partially crash proof, which means it saves its state into two
 * files: snapshot.rmid and delta.rmid. snapshot.rmid contains the
 * snapshot of the structure that contains information about Activation
 * Groups and Activatable Objects registered in this RMID and delta.rmid
 * reflects the changes occured since last snapshot.
 *
 * The objects that are saved in Snapshot:
 * <UL>
 * <LI>ActivationID: UID uid, String refType, UnicastRef2 RemoteRef</LI>
 * <LI>ActivationDesc: ActivationGroupID groupID, String className, String
 * location, MarshalledObject data, boolean restart</LI>
 * <LI>ActivationGroupID: ActivationSystem system, UID uid</LI>
 * <LI>ActivationGroupDesc: String className, String location,
 * MarshalledObject data, ActivationGroupDesc.CommandEnvironment env,
 * Properties props</LI>
 * </UL>
 *
 * @author  Victor A. Martynov
 * @version $Revision: 1.1.2.8 $
 */
public class Rmid extends RemoteServer implements ActivationSystem,
        ActivationMonitor, Activator, RmidMBean {

    private static final long serialVersionUID = -4936383024184263236L;

    /**
     * Standard logger for RMI Activation.
     *
     * @see org.apache.harmony.rmi.common.RMILog#getActivationLog()
     */
    private static RMILog rLog = RMILog.getActivationLog();

    /**
     * Internal registry in which the Activation System is registered.
     */
    private static Registry internalRegistry = null;

    /**
     * Port for internal registry.
     *
     * @see java.rmi.activation.ActivationSystem#SYSTEM_PORT
     */
    private static int port = ActivationSystem.SYSTEM_PORT;

    /**
     * The stub for <code>this </code> object.
     */
    private Remote thisStub;

    /**
     * Indicates whether this instance of RMID should start monitor.
     */
    private static boolean startMonitor = false;

    /**
     * The instance of RmidMonitor of this Rmid.
     */
    private static RmidMonitor rmidMonitor = null;

    /**
     * Mapping from ActivationID to its ActivationGroupID.
     */
    public static Hashtable groupIDByActivationID;

    /**
     * Mapping from ActivationGroupID to ActivationGroupInfo.
     */
    static Hashtable groupInfoByGroupId;

    /**
     * The timeout that is given to the ActivationGroup VM to
     * start(milliseconds).
     *
     * @see org.apache.harmony.rmi.common.RMIConstants#DEFAULT_ACTIVATION_EXECTIMEOUT
     * @see org.apache.harmony.rmi.common.RMIProperties#ACTIVATION_EXECTIMEOUT_PROP
     */
    private static long groupStartTimeout =
        RMIConstants.DEFAULT_ACTIVATION_EXECTIMEOUT;

    /**
     * Represents the interval between the snapshots of the RMID
     * current state.
     *
     * @see org.apache.harmony.rmi.common.RMIConstants#DEFAULT_SNAPSHOTINTERVAL
     * @see org.apache.harmony.rmi.common.RMIProperties#ACTIVATION_SNAPSHOTINTERVAL_PROP
     */
    private static long snapshotInterval =
        RMIConstants.DEFAULT_SNAPSHOTINTERVAL;

    /**
     * Indicates whether the debug information about logging - snapshots and
     * deltas of the RMID state - should be printed.
     *
     * @see org.apache.harmony.rmi.common.RMIProperties#ACTIVATION_LOG_DEBUG_PROP
     */
    private static boolean loggingDebug = false;

    /**
     * The maximum amount of activation groups(VMs).
     *
     * @see org.apache.harmony.rmi.common.RMIConstants#MAX_CONCURRENT_STARTING_GROUPS
     * @see org.apache.harmony.rmi.common.RMIProperties#MAXSTARTGROUP_PROP
     */
    private static long maxConcurrentStartingGroups =
        RMIConstants.MAX_CONCURRENT_STARTING_GROUPS;

    /**
     * Indicates whether common activation debug information should be printed.
     * @see org.apache.harmony.rmi.common.RMIProperties#ACTIVATION_DEBUGEXEC_PROP
     */
    private static boolean activationDebug;

    /**
     * Arguments passed to every activation group VM of this Activation System.
     * These arguments are passed in the RMID command line using "-C" option.
     */
    private static String[] groupArgs = null;

    /**
     * Represents the amount of deltas that
     * happened after last snapshot. When this value exceeds
     * snapshotInterval the snapshot is made and this
     * variable is reset to 0.
     */
    private static int deltasCounter = 0;

    /**
     * The flag that indicates that the RMID is in restore phase. No
     * changes that are made to the RMID database during this flag is set
     * are recorded in DELTA_FILE.
     */
    private static boolean restoreLock = false;

    /**
     * This variable represents the amount of groups that can be started
     * immediately. The initial value of this variable is
     * maxConcurrentStartingGroups and when a process of group start is
     * initiated this value is decremented. As soon as group finishes its
     * starting procedures the value is increased.
     */
    private static long startingGroups = maxConcurrentStartingGroups;

    /**
     * The log level of RMID persistence state activities.
     */
    private static Level persistenceDebugLevel = RMILog.SILENT;

    /**
     * The log level of the general debugging information.
     */
    public static Level commonDebugLevel = RMILog.SILENT;

    /**
     * The folder to hold RMID logging information: snapshot and delta files.
     *
     * @see org.apache.harmony.rmi.common.RMIConstants#DEFAULT_LOG_FOLDER
     */
    private static String logFolder = RMIConstants.DEFAULT_LOG_FOLDER;

    private class Lock {}
    private Object lock = new Lock();

    /**
     * The name of the monitor class for RMID.
     *
     * @see RmidMonitor
     * @see org.apache.harmony.rmi.common.RMIConstants#DEFAULT_ACTIVATION_MONITOR_CLASS_NAME
     * @see org.apache.harmony.rmi.common.RMIProperties#ACTIVATION_MONITOR_CLASS_NAME_PROP
     */
    static String monitorClassName;

    /**
     * Initializes activation system. Called in {@link #main(String[]) main}.
     */
    private Rmid(int port) {
        try {
            /*
             * The process of starting RMID should not be interrupted by any
             * incoming calls so we put this whole process into synchronized
             * block on the global lock object.
             */
            synchronized (lock) {
                internalRegistry = LocateRegistry.createRegistry(port);
                rLog.log(commonDebugLevel, "Registry created: "
                        + internalRegistry);
                rLog
                        .log(commonDebugLevel,
                                "Creating Activation System on port "
                                        + port + ".");

                UnicastServerRef usr = new UnicastServerRef(port, null,
                        null, new ObjID(RMIConstants.ACTIVATION_SYSTEM_ID));

                thisStub = ExportManager.exportObject(this, usr, false);

                rLog.log(commonDebugLevel, "stub's ref = "
                        + ((RemoteObject) thisStub).getRef());
                this.ref = ((RemoteStub) thisStub).getRef();

                String activationSystemURL = "rmi://:" + port
                        + "/java.rmi.activation.ActivationSystem";
                rLog.log(commonDebugLevel, "URL = " + activationSystemURL);
                rLog.log(commonDebugLevel, "Stub = " + thisStub);
                Naming.rebind(activationSystemURL, thisStub);
                rLog.log(commonDebugLevel, "Rebind was successful.");

                groupIDByActivationID = new Hashtable();
                groupInfoByGroupId = new Hashtable();

                if (startMonitor) {
                    rmidMonitor = RmidMonitorFactory
                            .getRmidMonitor(monitorClassName);
                    rLog.log(commonDebugLevel, "RmidMonitor created: "
                            + rmidMonitor);
                    /*
                     * Failed to obtain RmidMonitor.
                     */
                    if (rmidMonitor == null) {
                        startMonitor = false;
                    }
                }

                restore();
            }
        } catch (Throwable t) {
            rLog.log(commonDebugLevel, "Exception in RMID: " + t);
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Waits until the startup procedure of RMID is completed.
     */
    private void waitStartup() {
        synchronized (lock) {
            //This block was intentionally left empty.
        }
    }

    /**
     * Main method to start activation system.
     *
     * @see RMIConstants#RMID_USAGE
     */
    public static void main(String args[]) {

        /* Setting the security manager. */
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        /*
         * Reading properties.
         */
        groupStartTimeout = ((Long) AccessController
                .doPrivileged(new GetLongPropAction(
                        RMIProperties.ACTIVATION_EXECTIMEOUT_PROP,
                        groupStartTimeout))).longValue();
        snapshotInterval = ((Long) AccessController
                .doPrivileged(new GetLongPropAction(
                        RMIProperties.ACTIVATION_SNAPSHOTINTERVAL_PROP,
                        snapshotInterval))).longValue();
        loggingDebug = ((Boolean) AccessController
                .doPrivileged(new GetBooleanPropAction(
                        RMIProperties.ACTIVATION_LOG_DEBUG_PROP,
                        loggingDebug))).booleanValue();
        maxConcurrentStartingGroups = ((Long) AccessController
                .doPrivileged(new GetLongPropAction(
                        RMIProperties.MAXSTARTGROUP_PROP,
                        maxConcurrentStartingGroups))).longValue();
        activationDebug = ((Boolean) AccessController
                .doPrivileged(new GetBooleanPropAction(
                        RMIProperties.ACTIVATION_DEBUGEXEC_PROP, false)))
                .booleanValue();
        monitorClassName = (String) AccessController
                .doPrivileged(new GetStringPropAction(
                        RMIProperties.ACTIVATION_MONITOR_CLASS_NAME_PROP,
                        RMIConstants.DEFAULT_ACTIVATION_MONITOR_CLASS_NAME));

        if (loggingDebug) {
            persistenceDebugLevel = RMILog.VERBOSE;
        }

        if (activationDebug) {
            commonDebugLevel = RMILog.VERBOSE;
        }

        rLog.log(commonDebugLevel,
                "\nThe following properties were set on RMID: " + "\n"
                        + RMIProperties.ACTIVATION_EXECTIMEOUT_PROP
                        + " = " + groupStartTimeout + "\n"
                        + RMIProperties.ACTIVATION_SNAPSHOTINTERVAL_PROP
                        + " = " + snapshotInterval + "\n"
                        + RMIProperties.ACTIVATION_LOG_DEBUG_PROP + " = "
                        + loggingDebug + "\n"
                        + RMIProperties.MAXSTARTGROUP_PROP + " = "
                        + maxConcurrentStartingGroups + "\n"
                        + RMIProperties.ACTIVATION_DEBUGEXEC_PROP + " = "
                        + activationDebug + "\n"
                        + RMIProperties.ACTIVATION_MONITOR_CLASS_NAME_PROP
                        + " = " + monitorClassName);

        /*
         * The ArrayList for temporary holding of "-C" options.
         */
        ArrayList tmpGroupArgs = new ArrayList();

        /*
         * Parsing command line arguments.
         */
        for (int i = 0; i < args.length; i++) {

            String argument = args[i];

            if (argument.equals("-port")) {
                if (i + 1 >= args.length) {
                    System.out.println(
                            "Insufficient arguments: "
                           +"port should be specified.");
                    printUsage();
                    System.exit(1);
                }

                try {
                    port = Integer.parseInt(args[i + 1]);
                } catch (NumberFormatException nfe) {
                    System.out.println("Malformed port number.");
                    printUsage();
                    System.exit(1);
                }
                i++;
            } else if (argument.equals("-log")) {
                if (i + 1 >= args.length) {
                    System.out
                            .println("Insufficient arguments: "
                                    +"log folder should be specified.");
                    printUsage();
                    System.exit(1);
                }
                logFolder = args[i + 1];
                i++;
            } else if (argument.equals("-stop")) {
                try {
                    ActivationSystem system = ActivationGroup.getSystem();
                    system.shutdown();
                    rLog.log(commonDebugLevel, "RMID was shut down");
                    return;
                } catch (Throwable t) {
                    t.printStackTrace();
                    System.exit(1);
                }
            } else if (argument.equals("-C")) {
                tmpGroupArgs.add(args[i].substring(2));
            } else if (argument.equals("-help")) {
                printUsage();
                return;
            } else if (argument.equals("-monitor")) {
                rLog.log(commonDebugLevel, "Monitor option selected.");
                startMonitor = true;
            } else {
                /*
                 * Illegal option found.
                 */
                System.out.println("Illegal option: " + argument);
                printUsage();
                System.exit(1);
            }
        }

        /*
         * Extracting collected "-C" options from ArrayList.
         */
        groupArgs = (String[]) tmpGroupArgs
                .toArray(new String[tmpGroupArgs.size()]);

        /*
         * Adding separator at the end of log folder.
         */
        if (!logFolder.endsWith(File.separator)) {
            logFolder = logFolder + File.separator;
        }

        final File dir = new File(logFolder);

        /*
         * Creating log folder.
         */
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                if (!dir.exists() && !dir.mkdir()) {
                    System.out.println("Cannot create log folder: "
                            + logFolder);
                    System.exit(1);
                }
                return null;
            }
        });

        try {
            Rmid rmid = new Rmid(port);

            rLog.log(commonDebugLevel, "RMID instace created: " + rmid);
            Thread.sleep(Long.MAX_VALUE);
        } catch (Throwable t) {
            rLog.log(commonDebugLevel, "Exception: " + t);
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Prints the usage syntax for RMID.
     */
    private static void printUsage() {
        System.out.println(RMIConstants.RMID_USAGE);
    }

    /* *********************************************************************
     *
     * Next methods belong to ActivationSystem remote interface.
     *
     *********************************************************************/

    public ActivationGroupID registerGroup(ActivationGroupDesc agdesc)
            throws ActivationException {
        waitStartup();
        ActivationGroupID agid = new ActivationGroupID(this);
        ActivationGroupInfo agi = new ActivationGroupInfo(agid, agdesc);
        if (groupInfoByGroupId.containsKey(agid)) {
            throw new ActivationException(
                    "This group is already registered.");
        }
        groupInfoByGroupId.put(agid, agi);
        if (!restoreLock) {
            writeDelta(Delta.PUT, "group", agid, agdesc);
            rLog.log(persistenceDebugLevel, "Delta was saved: "
                    + Delta.PUT + "," + "group" + ", " + agid + ", "
                    + agdesc);
        }

        return agid;
    }

    public ActivationMonitor activeGroup(ActivationGroupID gID,
            ActivationInstantiator group, long incarnation)
            throws UnknownGroupException, ActivationException {
        waitStartup();
        rLog.log(commonDebugLevel, "Rmid.activeGroup: " + gID + "; "
                + group + "; " + incarnation);
        rLog.log(commonDebugLevel, "groupID2groupInfo_H = "
                + groupInfoByGroupId);

        ActivationGroupInfo agi = (ActivationGroupInfo) groupInfoByGroupId
                .get(gID);
        rLog
                .log(commonDebugLevel, "Rmid.activeGroup group info =  "
                        + agi);

        if (agi == null) {
            throw new UnknownGroupException("Group is not registered: "
                    + gID);
        } else if (agi.isActive()) {
            throw new ActivationException("Group is already active: "
                    + gID);
        }

        rLog.log(commonDebugLevel, "ready to execute agi.active()");

        agi.active(group, incarnation);
        rLog.log(commonDebugLevel, "Rmid.activeGroup finished. ");

        return this;
    }

    /**
     * This method is absent in Java Remote Method Invocation
     * specification.
     *
     * @param aID
     * @throws UnknownObjectException  if <code>ActivationID</code>
     * is not registered
     * @throws ActivationException  for general failure
     * @throws RemoteException  if remote call fails
     */
    public ActivationDesc getActivationDesc(ActivationID aID)
            throws UnknownObjectException {
        waitStartup();
        ActivationGroupID agid = (ActivationGroupID) groupIDByActivationID
                .get(aID);
        ActivationGroupInfo info = (ActivationGroupInfo) groupInfoByGroupId
                .get(agid);
        ActivationDesc adesc = info.getActivationDesc(aID);

        if (adesc == null) {
            throw new UnknownObjectException(
                    "No ActivationDesc for ActivationID " + aID);
        }
        return adesc;
    }

    /**
     * This method is absent in Java Remote Method Invocation
     * specification.
     *
     * @throws UnknownGroupException - if agid is not registered
     * @throws ActivationException - for general failure
     * @throws RemoteException - if remote call fails
     */
    public ActivationGroupDesc getActivationGroupDesc(
            ActivationGroupID agid) throws UnknownObjectException {
        waitStartup();
        ActivationGroupInfo agi = (ActivationGroupInfo) groupInfoByGroupId
                .get(agid);
        if (agi == null) {
            throw new UnknownObjectException(
                    "No ActivationGroupDesc for ActivationGroupID " + agid);
        }
        return agi.getActivationGroupDesc();
    }

    public ActivationID registerObject(ActivationDesc adesc) {
        waitStartup();
        rLog.log(commonDebugLevel,
                "ActivationSystemImpl.registerObject():");
        ActivationGroupID agid = adesc.getGroupID();
        rLog.log(commonDebugLevel, "agid : " + agid);
        rLog.log(commonDebugLevel, "Activator stub = " + thisStub);
        ActivationID aid = new ActivationID((Activator) thisStub);
        rLog.log(commonDebugLevel, "aid : " + aid);
        rLog.log(commonDebugLevel, "agid : " + agid);

        ActivationGroupInfo info = (ActivationGroupInfo) groupInfoByGroupId
                .get(agid);
        rLog.log(commonDebugLevel, "ActivationGroupInfo = " + info);

        info.registerObject(aid, adesc);
        rLog.log(commonDebugLevel, "Activation desc was added.");
        return aid;

    }

    public ActivationDesc setActivationDesc(ActivationID id,
            ActivationDesc desc) {
        waitStartup();
        ActivationGroupID agid = (ActivationGroupID) groupIDByActivationID
                .get(id);
        ActivationGroupInfo agi = (ActivationGroupInfo) groupInfoByGroupId
                .get(agid);
        return agi.setActivationDesc(id, desc);
    }

    public ActivationGroupDesc setActivationGroupDesc(
            ActivationGroupID id, ActivationGroupDesc desc) {
        waitStartup();
        ActivationGroupInfo agi = (ActivationGroupInfo) groupInfoByGroupId
                .get(id);
        return agi.setActivationGroupDesc(id, desc);
    }

    public void shutdown() {
        synchronized (lock) {
            rLog.log(commonDebugLevel, "The Rmid is going to shutdown");

            Enumeration enumeration = groupInfoByGroupId.elements();
            while (enumeration.hasMoreElements()) {
                try {
                    ActivationGroupInfo agi = (ActivationGroupInfo) enumeration
                            .nextElement();
                    agi.shutdown();
                } catch (Throwable t) {
                    rLog.log(commonDebugLevel,
                            "Exception in Rmid.shutdown: " + t);
                    t.printStackTrace();
                }
            }
            rLog.log(commonDebugLevel, "...... Done.");
            System.exit(0);
        }
    }

    public void unregisterGroup(ActivationGroupID id)
            throws ActivationException, UnknownGroupException,
            RemoteException {
        waitStartup();
        ActivationGroupInfo agi = (ActivationGroupInfo) groupInfoByGroupId
                .remove(id);
        if (agi == null) {
            throw new UnknownGroupException(
                    "Attempt to unregister unknown group " + id);
        }
        agi.unregister();
    }

    /**
     * @param aID the ActivationID of the object that should be removed.
     */
    public void unregisterObject(ActivationID aID) {
        waitStartup();
        ActivationGroupID gID = (ActivationGroupID) groupIDByActivationID
                .get(aID);
        ActivationGroupInfo gInfo = (ActivationGroupInfo) groupInfoByGroupId
                .get(gID);
        gInfo.unregisterObject(aID);
    }

    /* *********************************************************************
     *
     * Next methods belong to ActivationMonitor remote interface.
     *
     *********************************************************************/

    public void activeObject(ActivationID id, MarshalledObject obj)
            throws RemoteException, UnknownObjectException {
        waitStartup();

        rLog
                .log(commonDebugLevel, "Rmid.activeObject: " + id + "; "
                        + obj);
        ActivationGroupID agid = (ActivationGroupID) groupIDByActivationID
                .get(id);
        rLog.log(commonDebugLevel, "agid = " + agid);

        ActivationGroupInfo agi = (ActivationGroupInfo) groupInfoByGroupId
                .get(agid);
        rLog.log(commonDebugLevel, "agi = " + agi);

        ObjectInfo oi = (ObjectInfo) agi.objectInfoByActivationID.get(id);
        rLog.log(commonDebugLevel, "oi=" + oi);

        oi.active();
        rLog.log(commonDebugLevel, "Rmid.activeObject finished.");
    }

    public void inactiveGroup(ActivationGroupID id, long incarnation) {
        waitStartup();

        ActivationGroupInfo agi = (ActivationGroupInfo) groupInfoByGroupId
                .get(id);
        agi.inactive(incarnation);
    }

    public void inactiveObject(ActivationID aID) {
        waitStartup();
        ActivationGroupID gID = (ActivationGroupID) groupIDByActivationID
                .get(aID);
        ActivationGroupInfo gInfo = (ActivationGroupInfo) groupInfoByGroupId
                .get(gID);
        gInfo.inactiveObject(aID);
    }

    /* *********************************************************************
     *
     * Next methods belong to Activator remote interface.
     *
     *********************************************************************/

    public MarshalledObject activate(ActivationID id, boolean force)
            throws ActivationException, UnknownObjectException,
            RemoteException {
        waitStartup();
        rLog.log(commonDebugLevel, "ActivatorImpl.activate(" + id + ", "
                + force + ")");

        ActivationGroupID agid = (ActivationGroupID) groupIDByActivationID
                .get(id);
        rLog.log(commonDebugLevel, "agid: " + agid);
        ActivationGroupInfo info = (ActivationGroupInfo) groupInfoByGroupId
                .get(agid);
        rLog.log(commonDebugLevel, "info = " + info);

        return info.activateObject(id, force);
    }

    /**
     * This class holds all the information needed about ActivationGroup.
     * It contains the following information: ActivationGroupID
     * ActivationGroupDesc and the array of activatable objects within this
     * group. The ActivationSystem holds the array of such objects.
     */
    private class ActivationGroupInfo {

        private ActivationGroupID agid;

        private ActivationGroupDesc agdesc;

        private Hashtable objectInfoByActivationID;

        private long incarnation;

        private boolean isActive;

        private ActivationInstantiator activationInstantiator;

        private Process process;

        public ActivationGroupInfo(ActivationGroupID agid,
                ActivationGroupDesc agdesc) {
            this.agdesc = agdesc;
            this.agid = agid;
            objectInfoByActivationID = new Hashtable();
            incarnation = 0;
            isActive = false;

            if (startMonitor) {
                rmidMonitor.addGroup(agid);
            }
        }

        public synchronized void inactiveObject(ActivationID aID) {
            ObjectInfo oi = (ObjectInfo) objectInfoByActivationID.get(aID);
            oi.inactive();
        }

        public synchronized void unregisterObject(ActivationID aID) {
            objectInfoByActivationID.remove(aID);
            groupIDByActivationID.remove(aID);

            if (startMonitor) {
                rmidMonitor.removeObject(aID);
            }
        }

        public synchronized ActivationDesc setActivationDesc(
                ActivationID id, ActivationDesc desc) {
            ObjectInfo oi = (ObjectInfo) objectInfoByActivationID.get(id);
            ActivationDesc oldDesc = oi.getActivationDesc();
            oi.setActivationDesc(desc);
            return oldDesc;
        }

        public synchronized ActivationGroupDesc setActivationGroupDesc(
                ActivationGroupID id, ActivationGroupDesc desc) {
            ActivationGroupDesc oldDesc = agdesc;
            agdesc = desc;
            return oldDesc;
        }

        public synchronized void registerObject(ActivationID id,
                ActivationDesc desc) {

            groupIDByActivationID.put(id, agid);

            ObjectInfo oi = new ObjectInfo(id, desc);

            objectInfoByActivationID.put(id, oi);

            if (!restoreLock) {
                writeDelta(Delta.PUT, "object", id, desc);
                rLog
                        .log(persistenceDebugLevel,
                                "New delta was generated.");
            }
        }

        public synchronized MarshalledObject activateObject(
                ActivationID id, boolean force)
                throws ActivationException, RemoteException {

            rLog.log(commonDebugLevel, "GroupInfo: id=" + id + "; force="
                    + force);

            if (!isActive) {
                activateGroup();
                rLog.log(commonDebugLevel, "Group was activated.");
            } else {
                rLog.log(commonDebugLevel, "Group was reused.");
            }

            ObjectInfo oi = (ObjectInfo) objectInfoByActivationID.get(id);
            rLog.log(commonDebugLevel, "activation_instantiator = "
                    + activationInstantiator);

            Exception signalException = null;
            try {
                return oi.activate(activationInstantiator);
            } catch (ConnectException ce) {
            } catch (ConnectIOException cioe) {
            } catch (MarshalException me) {
            } catch (Exception e) {
                signalException = e;
            }

            if (signalException == null) {
                rLog.log(commonDebugLevel,
                        "The group seems to be dead: Killing "
                                + "process, reactivating group.");

                if (process != null) {
                    process.destroy();
                }
                isActive = false;
                activationInstantiator = null;
                activateGroup();
                return oi.activate(activationInstantiator);
            }
            throw new ActivationException("Exception: ", signalException);
        }

        public synchronized void activateGroup() {

            /*
             * Constructing an appropriate commandline to start activation
             * group.
             *
             */
            String args[];
            ArrayList al = new ArrayList();
            CommandEnvironment ce = agdesc.getCommandEnvironment();

            if (ce != null) {

                String[] options = ce.getCommandOptions();
                String cmd = ce.getCommandPath();
                if (cmd != null) {
                    al.add(cmd);
                } else {
                    al.add("java");
                }
                al.addAll(Arrays.asList(options));
            } else {

                /*
                 * Getting properties that affect group VM execution.
                 */
                String javaVmNameVal = (String) AccessController.doPrivileged(
                        new GetStringPropAction("java.vm.name"));

                String javaHomeVal = (String) AccessController.doPrivileged(
                        new GetStringPropAction("java.home"));

                String javaClassPathVal = (String) AccessController.doPrivileged(
                        new GetStringPropAction("java.class.path"));

                String policy = (String) AccessController.doPrivileged(
                        new GetStringPropAction("java.security.policy"));

                String bootClassPathVal = (String)
                        AccessController.doPrivileged(new GetStringPropAction(
                            (javaVmNameVal.equals("J9")
                                           ? "org.apache.harmony.boot.class.path"
                                            : "sun.boot.class.path")));

                String executable = new File(new File(
                        javaHomeVal, "bin"), "java").getPath();

                // Add name of Java executable to run.
                al.add(executable);

                if (bootClassPathVal != null) {
                    al.add("-Xbootclasspath:" + bootClassPathVal);
                }

                if (javaClassPathVal != null) {
                    al.add("-classpath");
                    al.add(javaClassPathVal);
                }

                if (policy != null) {
                    // Apply security policy.
                    al.add("-Djava.security.policy=" + policy);
                }
            }

            /*
             * Passing the "-C" options to the ActivationGroup VM.
             */
            for (int i = 0; i < groupArgs.length; i++) {
                rLog.log(commonDebugLevel,
                        "Option was passed through \'-C\': "
                                + groupArgs[i]);
                al.add(groupArgs[i]);
            }

            al.add("org.apache.harmony.rmi.activation.ActivationGroupImpl");
            args = (String[]) al.toArray(new String[al.size()]);
            rLog.log(commonDebugLevel, "args = " + Arrays.asList(args));

            try {
                final String[] argsLocal = args;
                process = (Process) AccessController
                        .doPrivileged(new PrivilegedExceptionAction() {
                            public Object run() throws IOException {
                                return Runtime.getRuntime()
                                        .exec(argsLocal);
                            }
                        });

                startingGroups--;
                InputStream in = process.getInputStream();
                InputStream err = process.getErrorStream();

                new DebugThread(in).start();
                new DebugThread(err).start();

                rLog.log(commonDebugLevel, "ActivationGroup started: "
                        + process);
                incarnation++;

                OutputStream os = process.getOutputStream();
                RMIObjectOutputStream oos = new RMIObjectOutputStream(
                        new BufferedOutputStream(os));

                oos.writeObject(agid);

                rLog.log(commonDebugLevel, "Agid written: " + agid);
                oos.writeObject(agdesc);
                rLog.log(commonDebugLevel, "Agdesc written: " + agdesc);

                oos.writeLong(incarnation);
                rLog.log(commonDebugLevel, "incarnation written: "
                        + incarnation);

                oos.flush();
                rLog.log(commonDebugLevel, "flushed ");

                oos.close();
                os.close();
                rLog.log(commonDebugLevel, "closed ");

                if (activationInstantiator == null) {
                    try {
                        this.wait(groupStartTimeout);
                    } catch (InterruptedException t) {
                    }
                }
                startingGroups++;
            } catch (Throwable t) {
                rLog.log(commonDebugLevel,
                        "Cannot start ActivationGroup.\n Exception: " + t);
                t.printStackTrace();
            }
        }

        /**
         * Callback from ActivationGroup.createGroup that the groups was
         * created.
         */
        public synchronized void active(ActivationInstantiator ai,
                long incarnation) {
            rLog.log(commonDebugLevel,
                    "ActivationGroupInfo.activeGroup[ActInst=" + ai
                            + "; incarn=" + incarnation + "]");
            activationInstantiator = ai;
            notify();

            if (this.incarnation != incarnation) {
                throw new RuntimeException(
                        "Different incarnations of this group happened.");
            }
            activationInstantiator = ai;
            isActive = true;

            if (startMonitor) {
                rmidMonitor.activeGroup(agid);
            }
            rLog.log(commonDebugLevel,
                    "ActivationGroupInfo.activeGroup finished.");
        }

        public ActivationGroupDesc getActivationGroupDesc() {
            return agdesc;
        }

        public boolean isActive() {
            return isActive;
        }

        public ActivationDesc getActivationDesc(ActivationID aid) {
            return (ActivationDesc) ((ObjectInfo) objectInfoByActivationID
                    .get(aid)).getActivationDesc();
        }

        /**
         * Shut the activation group down.
         *
         * @return The exit value of activation group's VM.
         */
        public synchronized int shutdown() {
            if (process != null) {
                process.destroy();
                int val = process.exitValue();
                process = null;
                return val;
            }
            return 0;
        }

        public synchronized void unregister() {
            Enumeration keys = objectInfoByActivationID.keys();

            while (keys.hasMoreElements()) {
                ActivationID id = (ActivationID) keys.nextElement();

                objectInfoByActivationID.remove(id);
                groupIDByActivationID.remove(id);
            }
        }

        public synchronized void inactive(long incarnation) {
            isActive = false;
        }

        public String toString() {
            return "GroupInfo[ ActivationGroupID = " + agid + "]";
        }
    }

    /**
     * Structure to hold just 1 activated object.
     */
    private class ObjectInfo {

        boolean isActive;

        private ActivationDesc desc;

        private ActivationID id;

        MarshalledObject cachedInstance = null;

        public ObjectInfo(ActivationID id, ActivationDesc desc) {
            this.id = id;
            this.desc = desc;
            isActive = false;

            if (startMonitor) {
                rmidMonitor.addObject(id, desc.getGroupID());
            }
        }

        public synchronized MarshalledObject activate(
                ActivationInstantiator ai) throws ActivationException,
                RemoteException {

            rLog.log(commonDebugLevel,
                    "ObjectInfo.activate started. Act Inst = " + ai);

            if (cachedInstance != null) {
                rLog.log(commonDebugLevel,
                        "Subsequent call to activate, returning "
                                + "cached instance.");
                return cachedInstance;
            }

            MarshalledObject mo = ai.newInstance(id, desc);
            rLog.log(commonDebugLevel, "ObjectInfo.activate completed: "
                    + mo);

            return mo;
        }

        public ActivationDesc getActivationDesc() {
            return desc;
        }

        public synchronized void setActivationDesc(ActivationDesc desc) {
            this.desc = desc;
        }

        public void active() {
            if (startMonitor) {
                rmidMonitor.activeObject(id);
            }
        }

        public void inactive() {
            /*
             * When the object is being deactivated, the cached instance of
             * its stub becomes invalid.
             */
            cachedInstance = null;

            if (startMonitor) {
                rmidMonitor.inactiveObject(id);
            }
        }
    }

    /**
     * DebugThread - the thread that consumes the contents of the given
     * InputStream and prints it to console. Usually it is used to read
     * information from error and input streams of the ActivationGroup.
     */
    private class DebugThread extends Thread {
        InputStream is = null;

        public DebugThread(InputStream is) {
            this.is = is;
        }

        public void run() {
            try {
                byte tmp[] = new byte[1000];

                while (true) {
                    int c = is.read(tmp);
                    if (c == -1) break;
                    byte buf[] = new byte[c];
                    System.arraycopy(tmp, 0, buf, 0, c);
                    if (c != 0) System.out.print(new String(buf));
                }
            } catch (Throwable t) {
            }
        }
    }

    private synchronized void snapshot() {
        try {
            File f = new File(logFolder
                    + RMIConstants.DEFAULT_SNAPSHOT_FILE);
            FileOutputStream fos = new FileOutputStream(f, false);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(new Snapshot());
            out.close();
            fos.close();
        } catch (Throwable t) {
            t.printStackTrace();
            throw new RuntimeException(t);
        }
    }

    private synchronized void writeDelta(final int op, final String name,
            final Object key, final Object val) {
        deltasCounter++;

        AccessController.doPrivileged(new PrivilegedAction() {

            public Object run() {
                if (deltasCounter < snapshotInterval) {

                    try {
                        File f = new File(logFolder
                                + RMIConstants.DEFAULT_DELTA_FILE);
                        FileOutputStream fos = new FileOutputStream(f,
                                true);
                        ObjectOutputStream out = new ObjectOutputStream(
                                fos);
                        out.writeObject(new Delta(op, name, key, val));
                        out.close();
                        fos.close();
                        rLog.log(persistenceDebugLevel,
                                "Delta was written.");
                    } catch (Throwable t) {
                        t.printStackTrace();
                        System.exit(1);
                    }
                } else {
                    File df = new File(logFolder
                            + RMIConstants.DEFAULT_DELTA_FILE);
                    df.delete();
                    snapshot();
                    deltasCounter = 0;

                }
                return null;
            }
        });
    }

    private synchronized void restore() throws Exception {
        restoreLock = true;
        final File sf = new File(logFolder
                + RMIConstants.DEFAULT_SNAPSHOT_FILE);
        final File df = new File(logFolder
                + RMIConstants.DEFAULT_DELTA_FILE);

        try {

            AccessController.doPrivileged(new PrivilegedExceptionAction() {
                public Object run() throws Exception {

                    if (sf.exists()) {
                        FileInputStream fis = new FileInputStream(sf);
                        ObjectInputStream in = new ObjectInputStream(fis);
                        in.readObject();
                        in.close();
                        fis.close();
                    }

                    try {
                        if (df.exists()) {
                            FileInputStream fis = new FileInputStream(df);
                            while (true) {
                                ObjectInputStream in = new ObjectInputStream(
                                        fis);
                                Delta d = (Delta) in.readObject();
                                rLog.log(persistenceDebugLevel,
                                        "A delta was restored: " + d);
                            }
                        }
                    } catch (EOFException eofe) {
                        // This section was intentionally left empty.
                        // Indicates that End of File reached -
                        //meaning all deltas were read.
                        return null;
                    }
                    return null;
                }
            });
        } catch (Throwable t) {
            rLog.log(persistenceDebugLevel, "Exception in restore: " + t);
            t.printStackTrace();
            /*
             * Returning Activation System into initial state:
             */
            groupIDByActivationID = new Hashtable();
            groupInfoByGroupId = new Hashtable();
            System.gc();
        }

        restoreLock = false;
    }

    class Delta implements Serializable {

        private static final long serialVersionUID = 103662164369676173L;

        private static final int PUT = 0;

        private static final int REMOVE = 1;

        public int op;

        public String name;

        public MarshalledObject mkey;

        public MarshalledObject mval;

        public Delta(int op, String name, Object key, Object val)
                throws Exception {
            this.op = op;
            this.name = name;
            mkey = new MarshalledObject(key);
            mval = new MarshalledObject(val);
        }

        public String toString() {
            try {
                return "Delta: " + op + "," + name + ", " + mkey.get()
                        + ", " + mval.get();
            } catch (Throwable t) {
                t.printStackTrace();
                return "" + t;
            }
        }

        private synchronized void writeObject(ObjectOutputStream out)
                throws IOException {
            out.writeInt(op);
            out.writeUTF(name);
            out.writeObject(mkey);
            out.writeObject(mval);
        }

        private synchronized void readObject(ObjectInputStream in)
                throws IOException, ClassNotFoundException {
            op = in.readInt();
            name = in.readUTF();
            mkey = (MarshalledObject) in.readObject();
            mval = (MarshalledObject) in.readObject();
            rLog.log(persistenceDebugLevel, "Delta: Data read: " + op
                    + ", " + name + ", " + mkey.get() + ", " + mval.get());

            if (op == PUT) {
                if (name.equals("group")) {
                    ActivationGroupID agid = (ActivationGroupID) mkey
                            .get();
                    rLog.log(persistenceDebugLevel, "Restore agid: "
                            + agid);
                    ActivationGroupDesc agdesc = (ActivationGroupDesc) mval
                            .get();

                    ActivationGroupInfo agi = new ActivationGroupInfo(
                            agid, agdesc);
                    rLog.log(persistenceDebugLevel, "Restore agi: " + agi);
                    groupInfoByGroupId.put(agid, agi);
                    rLog.log(persistenceDebugLevel,
                            "The data were put into groupID2groupInfo_H");
                }
                if (name.equals("object")) {
                    rLog.log(persistenceDebugLevel,
                            "Trying to restore ActivationID:");
                    ActivationID aid = (ActivationID) mkey.get();
                    rLog.log(persistenceDebugLevel, "aid = " + aid);
                    ActivationDesc adesc = (ActivationDesc) mval.get();
                    rLog.log(persistenceDebugLevel, "adesc = " + adesc);

                    ActivationGroupID agid = adesc.getGroupID();
                    rLog.log(persistenceDebugLevel, "agid = " + agid);

                    ActivationGroupInfo agi =
                        (ActivationGroupInfo) groupInfoByGroupId.get(agid);
                    rLog.log(persistenceDebugLevel, "agi = " + agi);

                    groupIDByActivationID.put(aid, agid);

                    agi.registerObject(aid, adesc);
                    rLog.log(persistenceDebugLevel,
                            "Object was registered.");
                    rLog.log(persistenceDebugLevel,
                            "Object was put into hashtable.");
                }
            }

            if (op == REMOVE) {
                if (name.equals("object")) {
                    groupIDByActivationID.remove(mkey.get());
                }

                if (name.equals("group")) {
                    groupInfoByGroupId.remove(mkey.get());
                }
            }
        }
    }

    class Snapshot implements Serializable {

        private static final long serialVersionUID = 2006016754895450831L;

        private synchronized void writeObject(ObjectOutputStream out)
                throws IOException {

            Hashtable h0 = new Hashtable();
            Hashtable h1 = new Hashtable();

            Enumeration e0 = groupInfoByGroupId.keys();
            while (e0.hasMoreElements()) {
                ActivationGroupID agid = (ActivationGroupID) e0
                        .nextElement();
                MarshalledObject mo_agid = new MarshalledObject(agid);
                ActivationGroupInfo agi =
                    (ActivationGroupInfo) groupInfoByGroupId.get(agid);
                ActivationGroupDesc agdesc = agi.getActivationGroupDesc();
                h0.put(mo_agid, agdesc);
                Enumeration e1 = agi.objectInfoByActivationID.keys();
                while (e1.hasMoreElements()) {
                    ActivationID aid = (ActivationID) e1.nextElement();
                    ObjectInfo oi = (ObjectInfo) agi.objectInfoByActivationID
                            .get(aid);
                    ActivationDesc adesc = oi.getActivationDesc();
                    h1.put(aid, adesc);
                }
            }
            out.writeObject(h0);
            out.writeObject(h1);
        }

        private synchronized void readObject(ObjectInputStream in)
                throws IOException, ClassNotFoundException {
            Hashtable h0 = (Hashtable) in.readObject();
            Hashtable h1 = (Hashtable) in.readObject();

            rLog.log(persistenceDebugLevel, "Restore: ");
            rLog.log(persistenceDebugLevel, "h0 = " + h0);
            rLog.log(persistenceDebugLevel, "h1 = " + h1);

            Enumeration e0 = h0.keys();
            while (e0.hasMoreElements()) {

                MarshalledObject mo_agid = (MarshalledObject) e0
                        .nextElement();
                ActivationGroupID agid = (ActivationGroupID) mo_agid.get();
                rLog.log(persistenceDebugLevel, "Restore agid: " + agid);

                ActivationGroupDesc agdesc = (ActivationGroupDesc) h0
                        .get(mo_agid);

                rLog.log(persistenceDebugLevel, "Restore agdesc: "
                        + agdesc);
                ActivationGroupInfo agi = new ActivationGroupInfo(agid,
                        agdesc);
                rLog.log(persistenceDebugLevel, "Restore agi: " + agi);
                groupInfoByGroupId.put(agid, agi);
                rLog.log(persistenceDebugLevel,
                        "The data were put into groupID2groupInfo_H");
            }

            Enumeration e1 = h1.keys();
            while (e1.hasMoreElements()) {
                ActivationID aid = (ActivationID) e1.nextElement();
                ActivationDesc adesc = (ActivationDesc) h1.get(aid);
                ActivationGroupID agid = adesc.getGroupID();
                ActivationGroupInfo agi =
                    (ActivationGroupInfo) groupInfoByGroupId.get(agid);
                agi.registerObject(aid, adesc);
                groupIDByActivationID.put(aid, agid);
            }
        }
    }
}
