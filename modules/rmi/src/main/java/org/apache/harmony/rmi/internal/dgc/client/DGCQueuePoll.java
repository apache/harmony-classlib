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
package org.apache.harmony.rmi.internal.dgc.client;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.rmi.Remote;
import java.rmi.server.ObjID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.apache.harmony.rmi.internal.transport.Endpoint;
import org.apache.harmony.rmi.internal.utils.Pair;

/*
 * NOTE: 
 * This class has been modified in order to support 
 * Java VM 1.4.2 using the javac's target jsr14 
 */

/**
 * This class sends the corresponding "clean" call to the remote server’s
 * garbage collector when a stub with a "live" reference is collected by the
 * local garbage collector.
 * 
 * @author Gonzalo Ortega
 * 
 */
final class DGCQueuePoll extends Thread {

    private ReferenceQueue<Remote> stubsQueue;

    private Map<WeakReference, Pair<Endpoint, ObjID>> referenceDataTable;

    private DGCClient dgc;

    private Map<Endpoint, ArrayList<ObjID>> queuedCleanCalls;

    /**
     * Creates a new instance of <code>DGCQueuePoll</code>.
     * 
     * @param cdgc
     *            The client garbage collector which has instantiated this
     *            class.
     */
    public DGCQueuePoll(DGCClient cdgc) {
        stubsQueue = new ReferenceQueue<Remote>();
//        referenceDataTable = new Hashtable<WeakReference, Pair<Endpoint, ObjID>>();
        referenceDataTable = new Hashtable<WeakReference, Pair<Endpoint, ObjID>>();
        queuedCleanCalls = new HashMap<Endpoint, ArrayList<ObjID>>();
        dgc = cdgc;
    }

    /**
     * Adds a stub to the internal reference queue, in order to be detected when
     * it is garbage collected by the local garbage collector.
     * 
     * @param stub
     *            The stub that the clean call will be sent for.
     * @param data
     *            The <code>Endpoint / ObjID</code> pair representing the
     *            internal data of the remote reference contained inside the
     *            stub.
     * @return A weak reference to the stub.
     */
    public final WeakReference<Remote> addToReferenceQueue(Remote stub,
            Pair<Endpoint, ObjID> data) {
        WeakReference<Remote> weakStub;
        weakStub = new WeakReference<Remote>(stub, stubsQueue);
        referenceDataTable.put(weakStub, data);
        return weakStub;
    }

    /**
     * Waits for the local garbage collector to collect a stub, and then removes
     * the reference corresponding to that stub from the Client DGC's "live"
     * references table, stops sending dirty calls for that reference, and sends
     * a clean call for that reference to te remote server's garbage collector.
     * 
     */
    @SuppressWarnings("unchecked")
    public final void run() {
        while (true) {
            try {
                WeakReference<Remote> remove = (WeakReference<Remote>) stubsQueue
                        .remove();
                do {
                    Pair<Endpoint, ObjID> data = referenceDataTable.get(remove);
                    if (dgc.isLiveReference(data)) {
                        dgc.cancelDirtyCall(data.getFirst(), data.getSecond());
                        enqueueCleanCall(data.getFirst(), data.getSecond());
                    }
                    referenceDataTable.remove(remove);
                } while ((remove = (WeakReference<Remote>) stubsQueue.poll()) 
                        != null);
                sendQueuedCalls();
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * Groups the clean calls to be sent by <code>Endpoint</code>, in order
     * to send only one clean call for all the objects exported in the same
     * <code>Endpoint</code>.
     * 
     * @param ep
     *            The <code>Endpoint</code> of the reference.
     * @param objID
     *            The <code>ObjID</code> of the reference.
     */
    private final void enqueueCleanCall(Endpoint ep, ObjID objID) {
        ArrayList<ObjID> objIDList;
        objIDList = queuedCleanCalls.get(ep);
        if (objIDList == null) {
            objIDList = new ArrayList<ObjID>();
            queuedCleanCalls.put(ep, objIDList);
        }
        objIDList.add(objID);
    }

    /**
     * Sends all the grouped clean calls to the remote server's garbage
     * collector.
     */
    private final void sendQueuedCalls() {
        if (!queuedCleanCalls.isEmpty()) {
            ArrayList<ObjID> objIDList;
            for (Endpoint ep : queuedCleanCalls.keySet()) {
                objIDList = queuedCleanCalls.get(ep);
                ObjID[] objArray = new ObjID[objIDList.size()];
                objIDList.toArray(objArray);
                dgc.sendClean(ep, objArray, false);
            }
            queuedCleanCalls.clear();
        }
    }
}