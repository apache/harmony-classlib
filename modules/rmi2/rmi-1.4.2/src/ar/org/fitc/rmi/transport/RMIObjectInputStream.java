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
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.rmi.Remote;
import java.rmi.server.RMIClassLoader;
import java.rmi.server.RemoteObjectInvocationHandler;
import java.rmi.server.RemoteStub;

import ar.org.fitc.rmi.dgc.client.DGCClient;
import ar.org.fitc.rmi.utils.Pair;
import ar.org.fitc.rmi.utils.PropertiesReader;

/**
 * This class is used by the RMI Runtime to serialize and deserialize Remote
 * Objects. For that purpose the {@link #resolveClass} and
 * {@link #resolveProxyClass} methods have been overloaded.
 * 
 * @author Gustavo Petri
 * @author Gonzalo Ortega
 */
public final class RMIObjectInputStream extends ObjectInputStream {

    private static boolean useCodebaseOnly = 
        PropertiesReader.readBoolean("java.rmi.server.useCodebaseOnly", false);
        
    private boolean readsRemote;

    /**
     * Delegates the creation of an ObjectInputStream to its parent class.
     * 
     * @throws IOException
     *             if I/O operation fails
     */
    public RMIObjectInputStream() throws IOException {
        super();
        enableResolveObject(true);
    }

    /**
     * Delegates the creation of an ObjectInputStream to its parent class.
     * 
     * @param in
     *            the
     *            {@link InputStream}
     *            to which this instance will be attached.
     * @throws IOException
     *             if de I/O operation fails
     */
    public RMIObjectInputStream(InputStream in) throws IOException {
        super(in);
        enableResolveObject(true);
    }

    /**
     * Returns a pair (object-boolean) from the read object.
     * 
     * @return a pair (object-boolean) from the read object
     * @throws IOException
     *             if de I/O operation fails
     * @throws ClassNotFoundException
     *             if no definition for the class with the specified name could
     *             be found
     */
    public synchronized final Pair<Object, Boolean> readResultObject()
            throws IOException, ClassNotFoundException {
        readsRemote = false;
        Object obj = this.readObject();
        return new Pair<Object, Boolean>(obj, new Boolean(readsRemote));
    }

    /**
     * Returns the Class that corresponds to the desc argument.
     * 
     * @param desc
     *            the descriptor of the needed Class
     * @return the Class for the desc descriptor
     */
    @SuppressWarnings("deprecation")
    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) 
            throws IOException, ClassNotFoundException {
        Class<?> ret = null;
        Object obj = this.readObject();
        try {
            ret = Class.forName(desc.getName(), true, Thread.currentThread()
                    .getContextClassLoader());
        } catch (ClassNotFoundException e) {
            if ((obj != null && obj instanceof String) && (!useCodebaseOnly)) {
                ret = RMIClassLoader.loadClass((String) obj, desc.getName());
            } else {
                ret = RMIClassLoader.loadClass(desc.getName());
            }
        } 
        return ret;
    }

    /**
     * Constructs and returns a Proxy class that implements the required
     * interfaces.
     * 
     * @param interfaces
     *            the interfaces to be implemented
     * @return a proxy class that implements the specified interfaces
     * @throws IOException
     *             if de I/O operation fails
     */
    @Override
    protected Class<?> resolveProxyClass(String[] interfaces)
            throws IOException, ClassNotFoundException {
        Object obj = this.readObject();
        try {
            return super.resolveProxyClass(interfaces);
        } catch (Exception e) {
            return RMIClassLoader
                    .loadProxyClass((String) obj, interfaces, null);
        }
    }

    /**
     * Transforms a locally exported Remote Object to its corresponding stub in *
     * for the serialization proces.
     * 
     * @param obj
     *            the specified Object
     */
    @Override
    protected Object resolveObject(Object obj) throws IOException {
        if (obj instanceof Remote) {
            if (obj instanceof RemoteStub) {
                DGCClient dgcClient = DGCClient.getDGCClient();
                readsRemote = true;
                return dgcClient.getStubInstance((RemoteStub) obj);
            }
            if (Proxy.isProxyClass(obj.getClass())) {
                InvocationHandler ih = Proxy
                        .getInvocationHandler(obj);
                if (ih instanceof RemoteObjectInvocationHandler) {
                    DGCClient dgcClient = DGCClient.getDGCClient();
                    readsRemote = true;
                    return dgcClient.getStubInstance((Remote) obj);
                }
            }
        }
        return obj;
    }
}
