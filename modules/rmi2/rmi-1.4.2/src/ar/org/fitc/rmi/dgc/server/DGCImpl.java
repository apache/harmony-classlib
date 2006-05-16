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
package ar.org.fitc.rmi.dgc.server;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.dgc.DGC;
import java.rmi.dgc.Lease;
import java.rmi.dgc.VMID;
import java.rmi.server.ObjID;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import ar.org.fitc.rmi.dgc.DGCScheduledGC;
import ar.org.fitc.rmi.runtime.RemoteReferenceManager;
import ar.org.fitc.rmi.utils.Pair;
import ar.org.fitc.rmi.utils.PropertiesReader;

/*
 * NOTE: 
 * This class has been modified in order to support 
 * Java VM 1.4.2 using the javac's target jsr14 
 */

/**
 * Implementation of the {@link java.rmi.dgc.DGC} interface
 * 
 * @author Gustavo Petri
 */
public final class DGCImpl extends Thread implements DGC {

    /**
     * This queue is used for ExportedObjects Garbage Collection purposes. Whan
     * an exported object is collected by the local garbage collector its
     * WeakReference will be queued for cleanup purposes in this queue.
     */
    private ReferenceQueue<Remote> exportedDGCQueue;

    /**
     * This Table holds all the needed information about the exported objects to
     * be collected.
     */
    private DGCDataTable dgcDataTable;

    /**
     * This Table holds the sequence numbers to verify the ordering of the dirty
     * and clean calls.
     */
    private Hashtable<VMID, Pair<Long, Long>> seqNumTable;

    /**
     * Timer used to run Threads executing periodic clean-up actions
     */
    private Timer timer;

    /**
     * Interval used to check if the lease times of the Garbage Collector had
     * expired Specifed by the ar.org.fitc.rmi.dgc.checkInterval
     */
    private static long checkInterval;

    /**
     * Lease Time specified for this Serve Specifed by the
     * java.rmi.dgc.leaseValue property
     */
    private static long leaseProperty;

    /*
     * Properties setup.
     */
    static {
        checkInterval = PropertiesReader.readLong(
                "ar.org.fitc.rmi.dgc.checkInterval", 300000);

        leaseProperty = PropertiesReader.readLong(
                "java.rmi.dgc.leaseValue", 600000);
    }

    /**
     * Starts all the periodic actions and Starts the local Garbage Collector
     */
    public DGCImpl() {

        super("rmi.dgc.server.DGCImpl");
        exportedDGCQueue = new ReferenceQueue<Remote>();
        dgcDataTable = new DGCDataTable();
        seqNumTable = new Hashtable<VMID, Pair<Long, Long>>();
        timer = new Timer(true);
        try {
            timer.schedule(new CleanTask(), checkInterval, checkInterval);
            timer.schedule(new RipSequenceNumbersTask(), 2 * leaseProperty,
                    2 * leaseProperty);
            DGCScheduledGC.startGC();
        } catch (Exception e) {
            // There is no chance that this try will fail unless the clean
            // method be errased.
            e.printStackTrace();
        }
        /*
         * Runs the Thread that removes exported Objects out of scope and not
         * being used remotelly.
         */
        this.setDaemon(true);
        this.start();
    }

    /**
     * Registers a new exported Object for Garbage Collection.
     * 
     * @param objID
     *            The {@link java.rmi.server.ObjID} of the Object
     * @param obj
     *            The Object to be registered
     */
    public final void register(ObjID objID, Remote obj) {
        dgcDataTable.register(objID, new WeakReference<Remote>(obj,
                exportedDGCQueue));
    }

    /**
     * @see java.rmi.dgc.DGC#clean FIXME boolean strong is not used
     */
    public final void clean(ObjID[] ids, long sequenceNum, VMID vmid,
            @SuppressWarnings("unused")
            boolean strong) {
        /*
         * REVIEW: When the strong parameter is not setted and the VMID making
         * the clean call does not hold any reference to other locally exported
         * object the Sequence Number for that VMID could be erased. This would
         * require that there exists a way to recognize every ObjectID used by a
         * particular VMID. We have chosen not to hold that table, but instead
         * erase sequence Numbers for "old enough" VMID's.
         */

        if (seqNumTable.containsKey(vmid)
                && sequenceNum > (Long) seqNumTable.get(vmid).getFirst()) {
            seqNumTable.put(vmid, new Pair<Long, Long>(new Long(sequenceNum),
                    System.currentTimeMillis() + 2 * leaseProperty));
            dgcDataTable.clean(ids, vmid);
        } else {
            // FIXME Just ignore the call?
        }
    }

    /**
     * @see java.rmi.dgc.DGC#dirty
     */
    public final Lease dirty(ObjID[] ids, long sequenceNum, Lease lease) {
        VMID vmid = lease.getVMID();
        if (vmid == null) {
            vmid = new VMID();
        }

        if ((seqNumTable.containsKey(vmid) && sequenceNum > (Long) seqNumTable
                .get(vmid).getFirst())
                || (!seqNumTable.containsKey(vmid))) {
            seqNumTable.put(vmid, new Pair<Long, Long>(new Long(sequenceNum),
                    System.currentTimeMillis() + 2 * leaseProperty));
            return dgcDataTable.dirty(ids, lease, vmid);
        } // else Just ignore the call?
        return null;
    }

    /**
     * Eliminates the VMIDs with expired lease times.
     */

    /**
     * Unexports the objects that have no more references and that have been
     * locally Garbage Collected.
     */
    @Override
    public final void run() {
        RemoteReferenceManager rrm = RemoteReferenceManager
                .getRemoteReferenceManager();

        /*
         * Note that this loop is setted to true since the DGC only needs to
         * stop working when the Client process ends, for that purpose this
         * thread is set as daemon.
         */
        while (true) {
            try {
                WeakReference<?> weak = (WeakReference<?>) exportedDGCQueue
                        .remove();
                ObjID objID = dgcDataTable.getByWeakRef(weak).getObjID();
                
                rrm.unexportObjectDGC(objID);
                dgcDataTable.remove(objID);
            } catch (InterruptedException e) {
                // TODO: handle exception
                e.printStackTrace();
            } catch (NoSuchObjectException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * Cleans up the list of Sequence Numbers
     * 
     * @see java.rmi.dgc.DGC
     */
    private final class RipSequenceNumbersTask extends TimerTask {

        @Override
        public final void run() {
            Long currentTime = System.currentTimeMillis();

            Enumeration<VMID> keys = seqNumTable.keys();

            while (keys.hasMoreElements()) {
                VMID key = keys.nextElement();
                if (seqNumTable.get(key).getSecond() < currentTime) {
                    seqNumTable.remove(key);
                }
            }
        }
    }

    /**
     * Eliminates the VMID's which lease time has expired.
     */
    private final class CleanTask extends TimerTask {
        
        @Override
        public final void run() {
            /*
             * REVIEW: This method might be too expensive in performance.
             */
            
            Long currentTime = System.currentTimeMillis();
            Map<ObjID, DGCData> objIDMap = dgcDataTable.getObjIDMap();
            synchronized (objIDMap) {
                for (ObjID objID : objIDMap.keySet()) {
                    if (objIDMap.containsKey(objID)) {
                        DGCData objData = objIDMap.get(objID);
                        objData.removeOlderThan(currentTime);
                    }
                }
            }
        }
    }
}