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

import java.lang.ref.WeakReference;
import java.rmi.Remote;
import java.rmi.dgc.VMID;
import java.rmi.server.ObjID;
import java.rmi.server.Unreferenced;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * A container class for the information needed by the Server Distributed
 * Garbage Collector for each exported Object.
 * 
 * @author Gustavo Petri
 */
final class DGCData {
    /*
     * Here we can grant that the vmIdExpirationTimeMap variable only be acceced
     * in an ordered way.
     */

    /**
     * The object's {@link java.rmi.server.ObjID}
     */
    private ObjID objID;

    /**
     * A Strong Reference to the exported Object
     */
    private Remote StrongRef;

    /**
     * A Weak Reference to the exported Object
     */
    private WeakReference<Remote> weakRef;

    /**
     * The mapping of {@link java.rmi.dgc.VMID} to the lease time assigned to
     * that VM.
     */
    private Map<VMID, Long> vmIdExpirationTimeMap;

    /**
     * The Reference Counter (Not necesarily used).
     */
    private int dgcCount;

    /**
     * Creates a {@link ar.org.fitc.rmi.dgc.server.DGCData}
     * 
     * @param objID
     *            the {@link ObjID} for this Object.
     * @param weakRef
     *            a <code>WeakReference</code> for the exported Object
     */
    public DGCData(ObjID objID, WeakReference<Remote> weakRef) {
        this.StrongRef = null;
        this.objID = objID;
        this.weakRef = weakRef;
        this.vmIdExpirationTimeMap = Collections
                .synchronizedMap(new HashMap<VMID, Long>());
        this.dgcCount = 0;
    }

    /**
     * Returns the {@link ObjID} for this Object
     * 
     * @return the {@link ObjID} for this Object
     */
    public final ObjID getObjID() {
        return objID;
    }

    /**
     * Returns the Strong Reference for this Object
     * 
     * @return a Strong Reference for this Object
     */
    public final Object getStrongRef() {
        return StrongRef;
    }

    /**
     * Returns the <code>WeakReference</code> for this Object
     * 
     * @return a <code>WeakReference</code> for this Object
     */
    public final WeakReference<Remote> getWeakRef() {
        return weakRef;
    }

    /**
     * Returns the expirationTime map for this object.
     * 
     * @return the expirationTime map for this object
     */
    public final Map<VMID, Long> getExpirationTimeMap() {
        return vmIdExpirationTimeMap;
    }

    /**
     * A per {@link java.rmi.dgc.DGC#dirty} implementation
     * of the dirty method
     * 
     * @param vmid
     *            The {@link java.rmi.dgc.VMID} updated.
     * @param expirationTime
     *            The time in which the lease time expires
     * @see java.rmi.dgc.DGC
     */
    public final void dirty(VMID vmid, Long expirationTime) {

        if (vmIdExpirationTimeMap.isEmpty()) {
            StrongRef = weakRef.get();
            if (StrongRef == null) {
                /*
                 * Somehow throw an exception. There should have been a problem
                 * in the network.
                 */
            }
        }
        if (!vmIdExpirationTimeMap.containsKey(vmid)) {
            dgcCount++;
        }
        vmIdExpirationTimeMap.put(vmid, expirationTime);
        return;
    }

    /**
     * A per {@link java.rmi.dgc.DGC#clean(ObjID[], long, VMID, boolean)}
     * implementation of the dirty method
     * 
     * @param vmid
     *            The {@link java.rmi.dgc.VMID} updated.
     * @see java.rmi.dgc.DGC
     */
    public final void clean(VMID vmid) {

        if (vmIdExpirationTimeMap.containsKey(vmid)) {
            vmIdExpirationTimeMap.remove(vmid);
            dgcCount--;
            if (vmIdExpirationTimeMap.isEmpty()) {
                if (StrongRef instanceof Unreferenced) {
                    ((Unreferenced) StrongRef).unreferenced();
                }
                StrongRef = null;
            }
        }
    }

    /**
     * Eliminates the VMID's which's lease times had expired.
     * 
     * @param time
     *            The time to be taken as bound.
     * @return the eliminated VMIDs.
     */
    public final Set<VMID> removeOlderThan(long time) {
        /*
         * The return value is not necesary, is there just for possible
         * extensions.
         */

        HashSet<VMID> retVMIDs = new HashSet<VMID>();
        synchronized (vmIdExpirationTimeMap) {
            Iterator<VMID> iter = vmIdExpirationTimeMap.keySet().iterator();

            while (iter.hasNext()) {
                VMID currentVMID = iter.next();
                Long iterTime = vmIdExpirationTimeMap.get(currentVMID);
                
                if (iterTime < time) {
                    iter.remove();
                    dgcCount--;
                    if (vmIdExpirationTimeMap.isEmpty()) {
                        if (StrongRef instanceof Unreferenced) {
                            ((Unreferenced) StrongRef).unreferenced();
                        }
                        StrongRef = null;
                    }
                    retVMIDs.add(currentVMID);
                }
            }
        }

        return retVMIDs;
    }
}
