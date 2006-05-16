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
import java.rmi.Remote;
import java.rmi.server.ObjID;

import ar.org.fitc.rmi.transport.Endpoint;

/**
 * This class is the implementation of the <code>ServerRef</code> interface
 * for the <code>UnicastRemoteObject</code>, for those remote objects
 * exported with custom socket factories.
 * 
 * @author Gonzalo Ortega
 * 
 */
public final class UnicastServerRef2Impl extends UnicastServerRefImpl {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new <code>UnicastServerRef2Impl</code> starting from the
     * object being exported and the <code>ObjID</code> and
     * <code>Endpoint</code> generated during exportation.
     * 
     * @param objectToExport
     *            The object being exported
     * @param objID
     *            The <code>ObjID</code> created during exportation of the
     *            remote object
     * @param ep
     *            The <code>Endpoint</code> generated during exportation of
     *            the remote object
     */
    public UnicastServerRef2Impl(Remote objectToExport, ObjID objID, Endpoint ep) {
        super(objectToExport, objID, ep);
    }

    /**
     * Creates a new empty <code>UnicastServerRef2Impl</code>
     * 
     */
    public UnicastServerRef2Impl() {
        super();
    }

    /**
     * Returns the string representing the reference type, to be serialized in
     * the stream <code>out</code>.
     * 
     * @param out
     *            the output stream to which the reference will be serialized
     * @return the string representing this type of reference
     */
    public final String getRefClass(ObjectOutput out) {
        return ReferenceTypes.UNICAST_SERVER_REF2.toString();
    }

    /**
     * Reads a reference from an <code>inputStream</code> during reference
     * deserialization. Since the server references aren't sent to the clients,
     * this method does nothing. (Simply returns)
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
    }

    /**
     * Sends the components of the reference during serialization. Since the
     * server references aren't sent to the clients, this method does nothing.
     * (Simply returns)
     * 
     * @param out
     *            the stream to write the reference to
     * @throws IOException
     *             if an I/O Error occur during serialization
     */
    @Override
    public final void writeExternal(ObjectOutput out) throws IOException {
    }

}
