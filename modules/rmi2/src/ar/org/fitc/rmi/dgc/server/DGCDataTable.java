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
import java.rmi.dgc.Lease;
import java.rmi.dgc.VMID;
import java.rmi.server.ObjID;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ar.org.fitc.rmi.utils.PropertiesReader;

/**
 * A table implementation that groups all the exported objects to be eventually
 * Garbage Collected. It provides the the functionality needed by the
 * Distributed Garbage Collector to the
 * {@link ar.org.fitc.rmi.dgc.server.DGCImpl} class.
 * 
 * @author Gustavo Petri
 */
final class DGCDataTable {

    /**
     * Mapping from {@link java.rmi.server.ObjID} to it's corresponding
     * {@link ar.org.fitc.rmi.dgc.server.DGCData} information.
     */
    private Map<ObjID, DGCData> objIdIndex;

    /**
     * Mapping from a {@link java.lang.ref.WeakReference} to the
     * {@link ar.org.fitc.rmi.dgc.server.DGCData} of it's referee. Used for
     * clean up after the object has actually been collected.
     */
    private Map<WeakReference<Remote>, DGCData> weakRefIndex;

    /**
     * The maximun time this server will grant a lease to a client. Specified by
     * the java.rmi.dgc.leaseValue property
     */
    private static Long leaseProperty;

    static {
        leaseProperty = 
            PropertiesReader.readLong("java.rmi.dgc.leaseValue", 600000);
    }

    /**
     * Constructs an instance of this class.
     */
    public DGCDataTable() {
        super();
        objIdIndex = 
            Collections.synchronizedMap(new HashMap<ObjID, DGCData>());
        weakRefIndex = 
            new ConcurrentHashMap<WeakReference<Remote>, DGCData>();
    }

    /**
     * Returns the {@link ar.org.fitc.rmi.dgc.server.DGCData} corresponding to
     * the given {@link java.rmi.server.ObjID}
     * 
     * @param objID
     *            Object Identifier for which the
     *            {@link ar.org.fitc.rmi.dgc.server.DGCData} is needed.
     * @return the {@link ar.org.fitc.rmi.dgc.server.DGCData} for the given
     *         {@link java.rmi.server.ObjID}
     */
    public final DGCData getByObjID(ObjID objID) {
        return objIdIndex.get(objID);
    }

    /**
     * Returns the {@link ar.org.fitc.rmi.dgc.server.DGCData} corresponding to
     * the Object refereed by the given {@link java.lang.ref.WeakReference}
     * 
     * @param wRef
     *            WeakReference for the object whichs
     *            {@link ar.org.fitc.rmi.dgc.server.DGCData} is needed.
     * @return the {@link ar.org.fitc.rmi.dgc.server.DGCData} for the Object
     *         refereed by the given {@link java.rmi.server.ObjID}
     */
    public final DGCData getByWeakRef(WeakReference wRef) {
        return weakRefIndex.get(wRef);
    }

    /**
     * @param ids
     *            ObjID's to be renewed
     * @param lease
     *            The time requested by the client
     * @param vmid
     *            the clients vmid
     * @return the Lease granted by the server
     * @see java.rmi.dgc.DGC
     */
    public final Lease dirty(ObjID[] ids, Lease lease, VMID vmid) {

        Long expirationTime;
        Lease ret;
        if (lease.getValue() <= leaseProperty) {
            expirationTime = System.currentTimeMillis() + lease.getValue();
            ret = lease;
        } else {
            expirationTime = System.currentTimeMillis() + leaseProperty;
            ret = new Lease(vmid, leaseProperty);
        }

        if (vmid == null) {
            vmid = new VMID();
        }

        for (ObjID objID : ids) {
            if (objIdIndex.containsKey(objID)) {
                objIdIndex.get(objID).dirty(vmid, expirationTime);
            } else {
                /*
                 * It shouldn't be possible to make a dirty call on an
                 * unexported object.
                 */
                throw new AssertionError();
            }

        }
        return ret;
    }

    /**
     * @param ids
     *            ObjID's to be renewed
     * @param vmid
     *            the clients vmid
     * @see java.rmi.dgc.DGC
     */
    public final void clean(ObjID[] ids, VMID vmid) {
        for (ObjID objID : ids) {
            if (objIdIndex.containsKey(objID)) {
                objIdIndex.get(objID).clean(vmid);
            } else {
                /*
                 * It shouldn't be possible to make a clean call on an
                 * unexported object.
                 */
                throw new AssertionError();
            }
        }
    }

    /**
     * Registers an exported object to be evetually Garbage Collected.
     * 
     * @param objID
     *            the Objects {@link java.rmi.server.ObjID}
     * @param weakRef
     *            a WeakReference to the exported Object
     */
    public final void register(ObjID objID, WeakReference<Remote> weakRef) {
        DGCData exportedRemote = new DGCData(objID, weakRef);
        weakRefIndex.put(exportedRemote.getWeakRef(), exportedRemote);
        objIdIndex.put(objID, exportedRemote);
    }

    /**
     * Returns the {@link java.rmi.server.ObjID} to
     * {@link ar.org.fitc.rmi.dgc.server.DGCData} map.
     * 
     * @return the {@link java.rmi.server.ObjID} to
     *         {@link ar.org.fitc.rmi.dgc.server.DGCData} map.
     */
    public final Map<ObjID, DGCData> getObjIDMap() {
        return objIdIndex;
    }

    /**
     * Unregisters an exported object from the Distributed Garbage Collector.
     * 
     * @param objID
     *            the Objects {@link java.rmi.server.ObjID} to be unregistered
     */
    public final void remove(ObjID objID) {
        weakRefIndex.remove(objIdIndex.get(objID).getWeakRef());
        objIdIndex.remove(objID);
    }
}
