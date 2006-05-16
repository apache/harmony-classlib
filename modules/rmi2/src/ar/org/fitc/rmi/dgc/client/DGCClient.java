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
package ar.org.fitc.rmi.dgc.client;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.rmi.Remote;
import java.rmi.UnmarshalException;
import java.rmi.dgc.Lease;
import java.rmi.dgc.VMID;
import java.rmi.server.ObjID;
import java.rmi.server.RemoteObjectInvocationHandler;
import java.rmi.server.RemoteRef;
import java.rmi.server.RemoteStub;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

import ar.org.fitc.rmi.dgc.DGCScheduledGC;
import ar.org.fitc.rmi.transport.Endpoint;
import ar.org.fitc.rmi.transport.TransportManager;
import ar.org.fitc.rmi.utils.Pair;
import ar.org.fitc.rmi.utils.PropertiesReader;

/**
 * Implements the client's side of the RMI Garbage Collector. This class takes
 * care of scheduling and sending the dirty and clean calls to the server side
 * of RMI DGC, when stubs of remote objects are received.
 * 
 * @author Gonzalo Ortega
 * 
 */
public final class DGCClient {

	private static DGCClient dgcClient;

    private VMID vmID;

    private long sequenceNumber;

    private long leaseValue;

    private ObjID dgcObjID;

    private Map<Remote, WeakReference<Remote>> stubsCacheTable;

    private Map<RemoteRef, Pair<Endpoint, ObjID>> deserializedRemoteRefTable;

    private ConcurrentHashMap<Pair<Endpoint, ObjID>, Long> liveReferences;

    private Map<Long, DirtyTask> dirtyTaskTable;

    private Timer taskScheduler;

    private DGCQueuePoll unreferencedPoll;
    
    /** The method hash for the "clean" method in the DGC */
    private static final long SEND_CLEAN_OP_NUM = -5803803475088455571L;
    
    /** The method hash for the "dirty" method in the DGC */
    private static final long SEND_DIRTY_OP_NUM = -8139341527526761862L;

    /**
     * Implements the singleton behavior. If there is an instance of
     * <code>DGCClient</code> is returned, else a new instance is created and
     * returned.
     * 
     * @return the only <code>DGCClient</code> instance
     */
    public static synchronized final DGCClient getDGCClient() {
        if (dgcClient == null) {
            dgcClient = new DGCClient();
        }
        return dgcClient;
    }

    /**
     * Creates a new instance of DGCClient. Launches the scheduler (as a daemon)
     * that sends the dirty calls, and starts the monitor (as a daemon) that
     * will look for unreferenced stubs.
     */
    @Deprecated
    private DGCClient() {
        stubsCacheTable = new WeakHashMap<Remote, WeakReference<Remote>>();
        deserializedRemoteRefTable = new WeakHashMap<RemoteRef, Pair<Endpoint, ObjID>>();
        dirtyTaskTable = new HashMap<Long, DirtyTask>();
        liveReferences = new ConcurrentHashMap<Pair<Endpoint, ObjID>, Long>();

        /*
         * RMI API states that a client should use a null value if it is unable
         * to generate a VMID. The only way to know if the generated VMID is
         * somehow "unique" is calling the deprecated VMID.isUnique() method, as
         * long the VMID constructor doesn't throw any exception. (it always
         * success)
         */
        if (VMID.isUnique()) {
            vmID = new VMID();
        }
        sequenceNumber = 0;
        leaseValue = 600000L;
        dgcObjID = new ObjID(ObjID.DGC_ID);

        
        
        String propertyValue = 
            PropertiesReader.readString("java.rmi.dgc.leaseValue");
        if (propertyValue != null) {
            try {
                leaseValue = Long.parseLong(propertyValue);
            } catch (NumberFormatException e) {
            }
        }

        taskScheduler = new Timer("rmi.dgc.client.DirtyScheduler", true);

        unreferencedPoll = new DGCQueuePoll(this);
        unreferencedPoll.setDaemon(true);
        unreferencedPoll.setName("rmi.dgc.client.unreferencedStubPoll");
        unreferencedPoll.start();

        DGCScheduledGC.startGC();
    }

    /**
     * Stores the data received during the deserialization of a stub. The
     * <code>Endpoint</code> and <code>ObjID</code> from the remote
     * reference are stored in order to be used to send dirty and clean calls
     * grouped by Endpoint.
     * 
     * @param ref
     *            The reference received from the stream.
     * @param ep
     *            The <code>Endpoint</code> that corresponds to the reference.
     * @param objID
     *            The <code>ObjID</code> that corresponds to the reference.
     */
    public synchronized final void registerRemoteRef(RemoteRef ref, Endpoint ep,
            ObjID objID) {
        if (!deserializedRemoteRefTable.containsKey(ref)) {
            deserializedRemoteRefTable.put(ref, new Pair<Endpoint, ObjID>(ep,
                    objID));
        }
        return;
    }

    /**
     * If the received stub is the first instance of that stub in the client’s
     * system, it is cached and returned. The method
     * <code>activateReference</code> is called in order to start the lease
     * renewal process. If there is a previous instance of this stub in the
     * system, the received stub is discarded and the cached instance is
     * returned.
     * 
     * @param stub
     *            The stub received during deserialization process.
     * @return The unique instance of this stub in the client's system.
     * @throws UnmarshalException
     *             If there is missing information or the dirty call for the
     *             received stub fails.
     */
    public synchronized Remote getStubInstance(Remote stub)
            throws UnmarshalException {
        // Get internal RemoteRef from stub
        RemoteRef ref = null;
        if (stub instanceof RemoteStub) {
            ref = ((RemoteStub) stub).getRef();
        }
        if (Proxy.isProxyClass(stub.getClass())) {
            InvocationHandler ih = Proxy.getInvocationHandler(stub);
            if (ih instanceof RemoteObjectInvocationHandler) {
                ref = ((RemoteObjectInvocationHandler) ih).getRef();
            }
        }
        // Check whether there is a stub already registered in the DGC
        WeakReference<Remote> weakStub;
        if ((weakStub = stubsCacheTable.get(stub)) != null) {
            Remote cachedStub = weakStub.get();
            if (cachedStub != null) {
                checkLiveness(ref);
                return cachedStub;
            }
        }
        Pair<Endpoint, ObjID> data = activateReference(ref);
        weakStub = unreferencedPoll.addToReferenceQueue(stub, data);
        stubsCacheTable.put(stub, weakStub);
        return stub;
    }

    /**
     * Sends the first dirty for a received reference in order to get the
     * appropiate lease time from the server, and then schedules the dirty call
     * for lease renewal. If the first dirty message fails, a clean "strong"
     * message is sent for that remote object. If the first dirty call
     * succeeded, the reference becomes a "live" reference for the client DGC.
     * 
     * @param ref
     *            The reference received inside the stub
     * @return The <code>Endpoint / ObjID</code> pair contained inside the
     *         reference
     * @throws UnmarshalException
     *             If the first dirty call fails
     */
    private final Pair<Endpoint, ObjID> activateReference(RemoteRef ref)
            throws UnmarshalException {
        // Get necessary data previously stored during RemoteRef
        // deserialization.
        Pair<Endpoint, ObjID> data = deserializedRemoteRefTable.get(ref);
        if (data == null) {
            throw new UnmarshalException(
                    "Impossible to get a stub for this object.");
        }
        Lease lease = sendDirty(data.getFirst(),
                new ObjID[] { data.getSecond() });

        if (lease != null) {
            enqueueDirtyCall(data, lease.getValue());
            liveReferences.put(data, new Long(lease.getValue()));
            return data;
        } else {
            sendClean(data.getFirst(), new ObjID[] { data.getSecond() }, true);
            throw new UnmarshalException(
                    "Impossible to get a stub for this object.");
        }
    }

    /**
     * Checks whether the received reference is a "live" reference for the
     * client garbage collector. The client DGC sends dirties for all "live"
     * references in order to renew the lease with the remote server. If the DGC
     * is not sending dirties for a reference (because of persistent network
     * problems, for instance) that reference is no longer considered "live". If
     * the received reference is not "live", the method
     * <code>activateReference</code> will be called in order to restart the
     * lease renewal process.
     * 
     * @param ref
     *            The reference to be tested
     */
    public synchronized final void checkLiveness(RemoteRef ref) {
        Pair<Endpoint, ObjID> dataRef = deserializedRemoteRefTable.get(ref);
        if (liveReferences.containsKey(dataRef)) {
            return;
        }
        try {
            activateReference(ref);
        } catch (UnmarshalException e) {
            // An Exception was thrown trying to bring the reference back to
            // life
        }
    }

    /**
     * Schedules a dirty call to be sent periodically. The dirty call will be
     * sent every <code>period</code> / 2 milliseconds.
     * 
     * @param data
     *            An <code>Endpoint / ObjID</code> pair representing a
     *            reference.
     * @param period
     *            The lease period granted by the server.
     */
    void enqueueDirtyCall(Pair<Endpoint, ObjID> data, long period) {
        synchronized (dirtyTaskTable) {
            DirtyTask task = dirtyTaskTable.get(new Long(period));
            if (task != null) {
                task.scheduleDirty(data);
            } else {
                task = new DirtyTask(this, period, taskScheduler,
                        dirtyTaskTable);
                task.scheduleDirty(data);
                taskScheduler.schedule(task, period / 2, period / 2);
                dirtyTaskTable.put(new Long(period), task);
            }
        }
    }

    /**
     * Removes the reference represented by the <code>Endpoint / ObjID</code>
     * pair from the dirty calls scheduler. No more dirties will be sent for
     * that reference, and the reference is erased from the "live" references
     * internal table.
     * 
     * @param ep
     *            The <code>Endpoint</code> of the remote reference.
     * @param objID
     *            The <code>ObjID</code> of the remote reference.
     */
    void cancelDirtyCall(Endpoint ep, ObjID objID) {
        synchronized (dirtyTaskTable) {
            Pair<Endpoint, ObjID> dataRef = new Pair<Endpoint, ObjID>(ep, objID);
            Long period = liveReferences.get(dataRef);
            DirtyTask task = dirtyTaskTable.get(period);
            if (task != null) {
                task.cancelDirty(ep, objID);
                if (task.isEmpty()) {
                    task.cancel();
                    dirtyTaskTable.remove(period);
                    taskScheduler.purge();
                }
            }
            liveReferences.remove(dataRef);
        }
    }

    /**
     * Removes the reference represented by the <code>Endpoint / ObjID</code>
     * pair from the "live" references internal table.
     * 
     * @param dataRef
     *            The <code>Endpoint / ObjID</code> pair representing the
     *            reference
     */
    void removeFromLiveReferences(Pair<Endpoint, ObjID> dataRef) {
        liveReferences.remove(dataRef);
    }

    /**
     * Checks whether the reference represented by the
     * <code>Endpoint / ObjID</code> pair is a "live" reference. The client
     * DGC sends dirties for all "live" references in order to renew the lease
     * with the remote server.
     * 
     * @param dataRef
     *            An <code>Endpoint / ObjID</code> pair representing a
     *            reference
     * @return true if the received pair is a "live" reference
     */
    boolean isLiveReference(Pair<Endpoint, ObjID> dataRef) {
        return liveReferences.containsKey(dataRef);
    }

    /**
     * Sends a clean call to the DGC of the remote server.
     * 
     * @param ep
     *            The <code>Endpoint</code> where the clean call will be sent.
     * @param obj
     *            An array containing the object identifiers that will be sent
     *            in the clean call.
     * @param strong
     *            The flag that indicates a 'strong' clean call
     */
    void sendClean(Endpoint ep, ObjID[] obj, boolean strong) {
    	synchronized(dgcObjID) { 
	        Object[] args = new Object[] { obj, ++sequenceNumber, vmID, strong };
	        TransportManager tm = TransportManager.getTransportManager();
	        try {
	            tm.invoke(dgcObjID, ep, args, SEND_CLEAN_OP_NUM, false);
	        } catch (Exception e) {
	            // The clean call has failed.
	        }
    	}
    }

    /**
     * Sends a dirty call to the DGC of the remote server, and returns the
     * <code>Lease</code> granted by that DGC.
     * 
     * @param ep
     *            The <code>Endpoint</code> where the dirty call will be sent.
     * @param obj
     *            An array containing the object identifiers that will be sent
     *            in the dirty call.
     * @return The <code>Lease</code> returned from the server DGC, or
     *         <code>null</code> if the call has failed.
     */
    Lease sendDirty(Endpoint ep, ObjID[] obj) {
    	synchronized(dgcObjID) { 
	        Lease lease = new Lease(vmID, leaseValue);
	        Object[] args = new Object[] { obj, ++sequenceNumber, lease };
	        TransportManager tm = TransportManager.getTransportManager();
	        Object response;
	        try {
	            response = tm.invoke(dgcObjID, ep, args, SEND_DIRTY_OP_NUM, true);
	            if (response instanceof Lease) {
	                if (vmID == null) {
	                    vmID = ((Lease) response).getVMID();
	                }
	                return (Lease) response;
	            }
	            return null;
	        } catch (Exception e) {
	            return null;
	        }
    	}
    }
}
