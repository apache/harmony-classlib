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
package ar.org.fitc.rmi.server;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Method;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ObjID;
import java.rmi.server.Operation;
import java.rmi.server.RemoteCall;
import java.rmi.server.RemoteObject;
import java.rmi.server.RemoteRef;

import ar.org.fitc.rmi.dgc.client.DGCClient;
import ar.org.fitc.rmi.transport.Endpoint;
import ar.org.fitc.rmi.transport.TransportManager;

/**
 * This class is the implementation of the <code>RemoteRef</code> interface
 * for the <code>UnicastRemoteObject</code>, for those remote objects
 * exported without custom socket factories.
 * 
 * @author Gonzalo Ortega
 * 
 */
public class UnicastRemoteRefImpl implements RemoteRef {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected ObjID objId;

    protected Endpoint ep;

    private transient boolean localReference;

    /**
     * Creates a new <code>UnicastRemoteRefImpl</code>
     */
    public UnicastRemoteRefImpl() {
    	super();
    }

    /**
     * Creates a new <code>UnicastRemoteRefImpl</code> with this
     * <code>ObjID</code> and <code>Endpoint</code>
     * 
     * @param objId
     *            The object identifier for this remote reference
     * @param ep
     *            The endpoint that will be used to contact the remote object
     */
    public UnicastRemoteRefImpl(ObjID objId, Endpoint ep) {
        this.objId = objId;
        this.ep = ep;
        this.localReference = true;
    }

    @SuppressWarnings("unused")
    @Deprecated
    public void done(@SuppressWarnings("unused") RemoteCall call) throws RemoteException {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the string representing the reference type, to be serialized in
     * the stream <code>out</code>.
     * 
     * @param out
     *            the output stream to which the reference will be serialized
     * @return the string representing this type of reference
     */
    public String getRefClass(@SuppressWarnings("unused") ObjectOutput out) {
        return ReferenceTypes.UNICAST_REF.toString();
    }

    /**
     * Invokes a method in the remote object. This method delegates the
     * invocation in the RMI Runtime.
     * 
     * @param obj
     *            The object where the method was executed (Probably a stub)
     * @param method
     *            The method executed in <code>obj</code>
     * @param args
     *            An array containing all the arguments used in the invocation
     *            of <code>method</code>
     * @param opnum
     *            The hash that represents the method
     * @return The result of the invocation of the method in the remote object
     * @throws Exception
     *             if the invocation fails
     */
    public Object invoke(@SuppressWarnings("unused") Remote obj, Method method, Object[] args, long opnum)
            throws Exception {

    	if (args == null) {
    		if (method.getParameterTypes().length != 0) {
    			throw new IllegalArgumentException("Wrong requiered arguments.");
    		}
    	} else {
    		if (args.length != method.getParameterTypes().length) {
    			throw new IllegalArgumentException("Wrong requiered arguments.");
    		}
    	}
    	
        boolean waitReturn = !(method.getReturnType().equals(Void.TYPE));
        TransportManager tm = TransportManager.getTransportManager();
        try {
            Object response = tm.invoke(objId, ep, args, opnum, waitReturn);
            if (!localReference) {
                DGCClient dgcClient = DGCClient.getDGCClient();
                dgcClient.checkLiveness(this);
            }
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    @SuppressWarnings("unused") 
    @Deprecated
    public void invoke(RemoteCall call) throws Exception {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unused")
    @Deprecated
    public RemoteCall newCall(RemoteObject obj, Operation[] op, int opnum,
            long hash) throws RemoteException {
        throw new UnsupportedOperationException();
    }

    /**
     * Implements the <code>equals</code> method in a suitable way for remote
     * references. Two remote references that refer to the same remote object
     * should return <code>true</code> during equality tests.
     * 
     * @param obj
     *            The remote reference that will be tested
     * @return <code>true</code> if the <code>obj</code> reference refers to
     *         the same remote object than this reference.
     */
    public boolean remoteEquals(RemoteRef obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj instanceof UnicastRemoteRefImpl) {
            if (objId.equals(((UnicastRemoteRefImpl) obj).objId)
                    && ep.equals(((UnicastRemoteRefImpl) obj).ep)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the hash code for this reference. Two references that refer to
     * the same remote object will have the same hash code.
     * 
     * @return the hash code for this reference
     */
    public int remoteHashCode() {
        return objId.hashCode() ^ ep.hashCode();
    }

    /**
     * Returns a String that represents this reference.
     * 
     * @return string representing this reference.
     */
    public String remoteToString() {
        return "[" + getRefClass(null) + ": [" + objId.toString() + ", "
                + ep.toString() + "]]";
    }

    /**
     * Implements the <code>equals</code> method in a suitable way for remote
     * references. Two remote references that refer to the same remote object
     * should return <code>true</code> during equality tests.
     * 
     * @param obj
     *            The remote reference that will be tested
     * @return <code>true</code> if the <code>obj</code> reference refers to
     *         the same remote object than this reference.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj instanceof RemoteRef) {
            return remoteEquals((RemoteRef) obj);
        }
        return false;
    }

    /**
     * Returns the hash code for this reference. Two references that refer to
     * the same remote object will have the same hash code.
     * 
     * @return the hash code for this reference
     */
    @Override
    public int hashCode() {
        return remoteHashCode();
    }

    /**
     * Reads a reference from an <code>inputStream</code> during reference
     * deserialization.
     * 
     * @param in
     *            the stream to read data from to restore the reference
     * @throws IOException
     *             if an I/O Error occur during deserialization
     * @throws ClassNotFoundException
     *             If the class for this restored object is not found
     */
    @SuppressWarnings("unused")
    public void readExternal(ObjectInput in) throws IOException, 
            ClassNotFoundException {
        String host = in.readUTF();
        int port = in.readInt();
        Endpoint ep = new Endpoint(host, port, null);
        this.ep = ep;
        this.objId = ObjID.read(in);
        // This value is read without any purpose, only because is specified.
        in.readBoolean();
        DGCClient dgcClient = DGCClient.getDGCClient();
        dgcClient.registerRemoteRef(this, ep, objId);
    }

    /**
     * Sends the components of the reference during serialization.
     * 
     * @param out
     *            the stream to write the reference to
     * @throws IOException
     *             if an I/O Error occur during serialization
     */
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(ep.getHost());
        out.writeInt(ep.getPort());
        objId.write(out);
        // This value is written without any purpose, only because is specified.
        out.writeBoolean(false);
    }
}
