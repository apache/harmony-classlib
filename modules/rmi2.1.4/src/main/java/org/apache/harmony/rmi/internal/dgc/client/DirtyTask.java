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

import java.rmi.server.ObjID;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.harmony.rmi.internal.transport.Endpoint;
import org.apache.harmony.rmi.internal.utils.Pair;

/**
 * This class groups all the references with the same granted lease time. A
 * <code>Timer</code> should run this task in order to send the dirties for
 * renewing the leases periodically.
 * 
 * @author Gonzalo Ortega
 * 
 */
final class DirtyTask extends TimerTask {

    private Map<Endpoint, Set<ObjID>> dirtyDataMap;

    private DGCClient dgc;

    private long period;

    private Timer taskScheduler;

    private Map<Long, DirtyTask> dirtyTaskTable;

    /**
     * Creates a new instance of DirtyTask.
     * 
     * @param dgc
     *            The client DGC which has instantiated this DirtyTask.
     * @param period
     *            The period in milliseconds of the lease granted to the
     *            references grouped in this task.
     * @param taskScheduler
     *            The <code>Timer</code> object which will run this scheduled
     *            <code>DirtyTask</code>.
     * @param dirtyTaskTable
     *            The table where all the <code>DirtyTasks</code> are
     *            registered, in order to mantain a reference for adding or
     *            removing references to the scheduled <code>DirtyTask</code>.
     */
    public DirtyTask(DGCClient dgc, long period, Timer taskScheduler,
            Map<Long, DirtyTask> dirtyTaskTable) {
        dirtyDataMap = new HashMap<Endpoint, Set<ObjID>>();
        this.dgc = dgc;
        this.period = period;
        this.taskScheduler = taskScheduler;
        this.dirtyTaskTable = dirtyTaskTable;
    }

    /**
     * Adds a new reference to this <code>DirtyTask</code>. The lease of all
     * the references registered in this task will be renewed periodically (a
     * dirty call to the remote server DGC will be sent for each reference).
     * 
     * @param data
     *            The <code>Endpoint / ObjID</code> pair representing a
     *            reference.
     */
    public final void scheduleDirty(Pair<Endpoint, ObjID> data) {
        synchronized (this) {
            Set<ObjID> objIDSet = dirtyDataMap.get(data.getFirst());
            if (objIDSet == null) {
                objIDSet = new HashSet<ObjID>();
                dirtyDataMap.put(data.getFirst(), objIDSet);
            }
            objIDSet.add(data.getSecond());
        }
    }

    /**
     * Removes a reference from this <code>DirtyTask</code>. No more dirty
     * calls will be sent for the removed reference.
     * 
     * @param ep
     *            The <code>Endpoint</code> of the reference.
     * @param objID
     *            The <code>ObjID</code> of the reference.
     */
    public final void cancelDirty(Endpoint ep, ObjID objID) {
        synchronized (this) {
            Set<ObjID> objIDSet = dirtyDataMap.get(ep);
            if (objIDSet != null) {
                objIDSet.remove(objID);
                if (objIDSet.isEmpty()) {
                    dirtyDataMap.remove(ep);
                }
            }
        }
    }

    /**
     * Returns true if this <code>DirtyTask</code> doesn't have any reference.
     * 
     * @return true if this <code>DirtyTask</code> doesn't have any
     *         references, otherwise returns false.
     */
    public final boolean isEmpty() {
        return dirtyDataMap.isEmpty();
    }

    /**
     * Sends the dirty calls for all the references grouped by this
     * <code>DirtyTask</code>. This method should be called by a
     * <code>Timer</code> that schedules the <code>DirtyTask</code>s by
     * granted lease time. The dirty calls for the references are sent grouped
     * by <code>Endpoint</code>. If a dirty call fails, all the references
     * grouped by that <code>Endpoint</code> are removed from this
     * <code>DirtyTask</code>, and a new <code>DirtyRetryTask</code> is
     * instantiated for those references initiating a dirty retry process.
     * 
     */
    @Override
    public final void run() {
        synchronized (this) {
            Iterator<Endpoint> iter = dirtyDataMap.keySet().iterator();
            while (iter.hasNext()) {
                Endpoint ep = iter.next();
                Set<ObjID> objIDSet = dirtyDataMap.get(ep);
                ObjID[] objArray = new ObjID[objIDSet.size()];
                objIDSet.toArray(objArray);
                if (dgc.sendDirty(ep, objArray) == null) {
                    // If the dirty call has failed, stop sending scheduled
                    // dirties
                    // for this Endpoint and initializes the dirty retry
                    // process.
                    iter.remove();
                    DirtyRetryTask retryTask = new DirtyRetryTask(dgc, period,
                            taskScheduler, ep, objArray, 1);
                    synchronized (dirtyTaskTable) {
                        taskScheduler.schedule(retryTask, 2000);
                        if (dirtyDataMap.isEmpty()) {
                            this.cancel();
                            //FIXME: taskScheduler.purge();
                            dirtyTaskTable.remove(period);
                        }
                    }
                }
            }
        }
    }
}

