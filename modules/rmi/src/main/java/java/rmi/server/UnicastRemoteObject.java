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
package java.rmi.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.NoSuchObjectException;

import org.apache.harmony.rmi.internal.runtime.RemoteReferenceManager;
import org.apache.harmony.rmi.internal.server.UnicastRemoteRef2Impl;
import org.apache.harmony.rmi.internal.server.UnicastRemoteRefImpl;
import org.apache.harmony.rmi.internal.server.UnicastServerRef2Impl;
import org.apache.harmony.rmi.internal.server.UnicastServerRefImpl;
import org.apache.harmony.rmi.internal.transport.Endpoint;
import org.apache.harmony.rmi.internal.transport.TransportManager;

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public class UnicastRemoteObject extends RemoteServer {

    private static final long serialVersionUID = 4974527148936298033L;

    private transient int port;

    private transient RMIClientSocketFactory csf;

    private transient RMIServerSocketFactory ssf;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    protected UnicastRemoteObject() throws RemoteException {
        this(0);
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    protected UnicastRemoteObject(int port) throws RemoteException {
        this.port = port;
        RemoteReferenceManager rrm = RemoteReferenceManager
                .getRemoteReferenceManager();
        TransportManager tm = TransportManager.getTransportManager();
        ObjID objID = new ObjID();
        Endpoint ep = tm.export(objID, port, null, null);
        UnicastServerRefImpl sref = new UnicastServerRefImpl(this, objID, ep);
        Remote stub = rrm.createStub(new UnicastRemoteRefImpl(objID, ep), this);
        this.ref = sref;
        rrm.storeExportData(this, objID, sref, stub);
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    protected UnicastRemoteObject(int port, RMIClientSocketFactory csf,
            RMIServerSocketFactory ssf) throws RemoteException {
        this.port = port;
        this.csf = csf;
        this.ssf = ssf;
        RemoteReferenceManager rrm = RemoteReferenceManager
                .getRemoteReferenceManager();
        TransportManager tm = TransportManager.getTransportManager();
        ObjID objID = new ObjID();
        Endpoint ep = tm.export(objID, port, ssf, csf);
        UnicastServerRef2Impl sref = new UnicastServerRef2Impl(this, objID, ep);
        Remote stub = rrm
                .createStub(new UnicastRemoteRef2Impl(objID, ep), this);
        this.ref = sref;
        rrm.storeExportData(this, objID, sref, stub);
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    @Override
	public Object clone() throws CloneNotSupportedException {
        UnicastRemoteObject clon = (UnicastRemoteObject) super.clone();
        RemoteReferenceManager rrm = RemoteReferenceManager
                .getRemoteReferenceManager();
        TransportManager tm = TransportManager.getTransportManager();
        ObjID objID = new ObjID();
        try {
            Endpoint ep = tm.export(objID, this.port, this.ssf, this.csf);
            UnicastServerRef2Impl sref = new UnicastServerRef2Impl(clon, objID,
                    ep);
            Remote stub = rrm.createStub(new UnicastRemoteRef2Impl(objID, ep),
                    clon);
            clon.ref = sref;
            rrm.storeExportData(clon, objID, sref, stub);
        } catch (RemoteException e) {
            throw new CloneNotSupportedException(
                    "Error during clon exportation");
        }
        return clon;
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static RemoteStub exportObject(Remote obj) throws RemoteException {
        if (obj == null) {
            throw new RemoteException("Invalid (null) Remote server object.");
        }
        RemoteReferenceManager rrm = RemoteReferenceManager
                .getRemoteReferenceManager();
        if (rrm.isExported(obj)) {
            throw new RemoteException("Object already exported.");
        }
        TransportManager tm = TransportManager.getTransportManager();
        ObjID objID = new ObjID();
        Endpoint ep = tm.export(objID, 0, null, null);
        UnicastServerRefImpl sref = new UnicastServerRefImpl(obj, objID, ep);
        RemoteStub stub = rrm.createRegularStub(new UnicastRemoteRefImpl(objID,
                ep), obj);
        rrm.storeExportData(obj, objID, sref, stub);
        return stub;
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static Remote exportObject(Remote obj, int port)
            throws RemoteException {
        if (obj == null) {
            throw new RemoteException("Invalid (null) Remote server object.");
        }
        RemoteReferenceManager rrm = RemoteReferenceManager
                .getRemoteReferenceManager();
        if (rrm.isExported(obj)) {
            throw new RemoteException("Object already exported.");
        }
        TransportManager tm = TransportManager.getTransportManager();
        ObjID objID = new ObjID();
        Endpoint ep = tm.export(objID, port, null, null);
        UnicastServerRefImpl sref = new UnicastServerRefImpl(obj, objID, ep);
        Remote stub = rrm.createStub(new UnicastRemoteRefImpl(objID, ep), obj);
        rrm.storeExportData(obj, objID, sref, stub);
        return stub;
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static Remote exportObject(Remote obj, int port,
            RMIClientSocketFactory csf, RMIServerSocketFactory ssf)
            throws RemoteException {
        if (obj == null) {
            throw new RemoteException("Invalid (null) Remote server object.");
        }
        RemoteReferenceManager rrm = RemoteReferenceManager
                .getRemoteReferenceManager();
        if (rrm.isExported(obj)) {
            throw new RemoteException("Object already exported.");
        }
        TransportManager tm = TransportManager.getTransportManager();
        ObjID objID = new ObjID();
        Endpoint ep = tm.export(objID, port, ssf, csf);
        UnicastServerRef2Impl sref = new UnicastServerRef2Impl(obj, objID, ep);
        Remote stub = rrm.createStub(new UnicastRemoteRef2Impl(objID, ep), obj);
        rrm.storeExportData(obj, objID, sref, stub);
        return stub;
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static boolean unexportObject(Remote obj, boolean force)
            throws NoSuchObjectException {
        RemoteReferenceManager rrm = RemoteReferenceManager
                .getRemoteReferenceManager();
        return rrm.unexportObject(obj, force);
    }

    /**
     * 
     * 
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    /**
     * 
     * 
     */
    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        in.defaultReadObject();
        RemoteReferenceManager rrm = RemoteReferenceManager
                .getRemoteReferenceManager();
        TransportManager tm = TransportManager.getTransportManager();
        ObjID objID = new ObjID();
        Endpoint ep = tm.export(objID, 0, null, null);
        UnicastServerRefImpl sref = new UnicastServerRefImpl(this, objID, ep);
        Remote stub = rrm.createStub(new UnicastRemoteRefImpl(objID, ep), this);
        this.ref = sref;
        rrm.storeExportData(this, objID, sref, stub);
    }
}
