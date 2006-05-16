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
package ar.org.fitc.rmi.transport;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.rmi.Remote;
import java.rmi.server.RMIClassLoader;
import java.rmi.server.RemoteObject;
import java.rmi.server.RemoteObjectInvocationHandler;
import java.rmi.server.RemoteStub;
import ar.org.fitc.rmi.runtime.RemoteReferenceManager;

/*
 * NOTE: 
 * This class has been modified in order to support 
 * Java VM 1.4.2 using the javac's target jsr14 
 */

/**
 * This class is used by the RMI Runtime to serialize and deserialize Remote
 * Objects. For that purpose the {@link #replaceObject}, {@link #annotateClass}
 * and {@link #annotateProxyClass} methods have been overloaded.
 * 
 * @author Gustavo Petri
 */

public final class RMIObjectOutputStream extends ObjectOutputStream {

    /**
     * This set holds the {@link java.rmi.server.UID} for which a DGCAck is
     * still pending.
     */
    private boolean writesRemote;

    /**
     * Delegates the creation of an ObjectInputStream to its parent class.
     * 
     * @throws IOException
     *             if the I/O operation fails
     */
    protected RMIObjectOutputStream() throws IOException {
        super();
        enableReplaceObject(true);
    }

    /**
     * Delegates the creation of an ObjectInputStream to its parent class.
     * 
     * @param out
     *            The
     *            {@link OutputStream}
     *            from which the new {@link RMIObjectOutputStream} will be created
     * @throws IOException
     *             if the I/O operation fails
     */
    public RMIObjectOutputStream(OutputStream out) throws IOException {

        super(out);
//        flush();
        enableReplaceObject(true);
    }

    /**
     * Writes an object and returns true if and only if a
     * {@link java.rmi.Remote} object has been writtenRMIObjectOutputStream
     * 
     * @param obj
     *            the specified Object
     * @return true if a {@link java.rmi.Remote} object has been written to the
     *         {@link ObjectOutputStream}
     * @throws IOException
     *             if the I/O operation fails
     */
    public synchronized final boolean writeResultObject(Object obj)
            throws IOException {

        writesRemote = false;
        this.writeObject(obj);
        return writesRemote;
    }

    /**
     * Returns a stub if the object to be serialized is a
     * {@link java.rmi.Remote} instance.
     * 
     * @param obj
     *            the object to be replaced if needed be.
     * @return if the argument was a {@link java.rmi.Remote} object locally
     *         exported a stub for that object is returned, in case it is not a
     *         {@link java.rmi.Remote} the object is returned
     * @throws IOException
     *             if the I/O operation fails
     */
    @Override
    protected final Object replaceObject(Object obj) throws IOException {

        if (obj instanceof Remote) {
            RemoteReferenceManager rrm = RemoteReferenceManager
                    .getRemoteReferenceManager();
            if (rrm.isExported((Remote) obj)) {
                writesRemote = true;
                return RemoteObject.toStub((Remote) obj);
            }
            if (obj instanceof RemoteStub) {
                writesRemote = true;
                return obj;
            }
            if (Proxy.isProxyClass(obj.getClass())) {
                InvocationHandler ih = Proxy.getInvocationHandler(obj);
                if (ih instanceof RemoteObjectInvocationHandler) {
                    writesRemote = true;
                }
            }
        }
        return obj;
    }

    /**
     * Writes the annotation for the class passed as argument on the 
     * {@link RMIObjectOutputStream}
     * 
     * @param cl
     *            the class to be annotated
     * @throws IOException
     *             if the I/O operation fails
     */
    protected final void annotateClass(Class cl) throws IOException {
        Object obj = RMIClassLoader.getClassAnnotation(cl);
        this.writeObject(obj);
    }

    /**
     * Writes the annotation for the Proxy class passed as argument on the
     * RMIOutputStream.
     * 
     * @param cl
     *            the class to be annotated
     * @throws IOException
     *             if the I/O operation fails
     */
    @Override
    protected final void annotateProxyClass(Class<?> cl) throws IOException {
        Object obj = RMIClassLoader.getClassAnnotation(cl);
        this.writeObject(obj);
    }
    
    /**
     * Writes the pending content to the underlying stream.
     */
    @Override
    public final void drain() throws IOException {
        super.drain();
    }
}
