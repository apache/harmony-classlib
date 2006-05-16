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
import java.rmi.server.ObjID;
import java.rmi.server.RMIClientSocketFactory;

import ar.org.fitc.rmi.dgc.client.DGCClient;
import ar.org.fitc.rmi.transport.Endpoint;

/**
 * This class is the implementation of the <code>RemoteRef</code> interface
 * for the <code>UnicastRemoteObject</code>, for those remote objects
 * exported with custom socket factories.
 * 
 * @author Gonzalo Ortega
 * 
 */
public class UnicastRemoteRef2Impl extends UnicastRemoteRefImpl {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new <code>UnicastRemoteRef2Impl</code>
     */
    public UnicastRemoteRef2Impl() {
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
    public UnicastRemoteRef2Impl(ObjID objId, Endpoint ep) {
        super(objId, ep);
    }

    /**
     * Returns the string representing the reference type, to be serialized in
     * the stream <code>out</code>.
     * 
     * @param out
     *            the output stream to which the reference will be serialized
     * @return the string representing this type of reference
     */
    @Override
	public final String getRefClass(ObjectOutput out) {
        return ReferenceTypes.UNICAST_REF2.toString();
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
    @Override
    public final void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        RMIClientSocketFactory csf = null;
        int readCsf = in.readByte();
        String host = in.readUTF();
        int port = in.readInt();
        if (readCsf == 0x01) {
            csf = (RMIClientSocketFactory) in.readObject();
        }
        Endpoint ep = new Endpoint(host, port, csf);
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
    @Override
    public final void writeExternal(ObjectOutput out) throws IOException {
        RMIClientSocketFactory csf = ep.getCsf();
        if (csf == null) {
            out.writeByte(0x00);
        } else {
            out.writeByte(0x01);
        }
        out.writeUTF(ep.getHost());
        out.writeInt(ep.getPort());
        if (csf != null) {
            out.writeObject(csf);
        }
        objId.write(out);
        // This value is written without any purpose, only because is specified.
        out.writeBoolean(false);
    }
}
