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
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.ServerError;
import java.rmi.ServerException;
import java.rmi.UnmarshalException;
import java.rmi.server.ObjID;
import java.rmi.server.RemoteServer;
import java.rmi.server.RemoteStub;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.ServerRef;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import ar.org.fitc.rmi.transport.Endpoint;
import ar.org.fitc.rmi.transport.TransportManager;
import ar.org.fitc.rmi.utils.MethodHashGenerator;
import ar.org.fitc.rmi.utils.RemoteUtils;

/*
 * NOTE: 
 * This class has been modified in order to support 
 * Java VM 1.4.2 using the javac's target jsr14 
 */

/**
 * This class is the implementation of the <code>ServerRef</code> interface
 * for the <code>UnicastRemoteObject</code>, for those remote objects
 * exported without custom socket factories.
 * 
 * @author Gonzalo Ortega
 * 
 */
public class UnicastServerRefImpl extends UnicastRemoteRefImpl implements
        ServerRef {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * A table for storing the relations between the methods of the remote
     * object and its hashes.
     */
    private Map<Long, Method> methodHashMap;

    /**
     * The reference to the real object, used for dispatching received calls
     */
    private WeakReference<Remote> object; // Weak reference to the "real"
                                            // server object

    /**
     * Creates a new <code>UnicastServerRefImpl</code> starting from the
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
    public UnicastServerRefImpl(Remote objectToExport, ObjID objID, Endpoint ep) {
        super(objID, ep);
        this.object = new WeakReference<Remote>(objectToExport);
        this.methodHashMap = new HashMap<Long, Method>();
        buildMethodHashMap(objectToExport);
    }

    /**
     * Creates a new empty <code>UnicastServerRefImpl</code>
     * 
     */
    public UnicastServerRefImpl() {
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
    public String getRefClass(ObjectOutput out) {
        return ReferenceTypes.UNICAST_SERVER_REF.toString();
    }

    /**
     * This method isn't necessary in our design. Is not used at the moment.
     * 
     * @param obj
     *            the object being exported
     * @param data
     *            data needed for exportation
     * @return null
     * @throws RemoteException
     *             if exportation fails
     */
    public RemoteStub exportObject(Remote obj, Object data)
            throws RemoteException {
        // REVIEW: This method isn't necessary in our design. Review...
        return null;
    }

    /**
     * This method isn't necessary in our design. Is not used at the moment.
     * 
     * @return null
     * @throws ServerNotActiveException
     *             if called outside of servicing a remote method invocation
     */
    public String getClientHost() throws ServerNotActiveException {
        // REVIEW: This method isn't necessary in our design. Review...
        return null;
    }

    /**
     * Dispatches the call received by the Transport Layer to the exported
     * object
     * 
     * @param args
     *            the arguments for the invocation of the method
     * @param methodHash
     *            a hash that represents the method being invoked
     * @return the result of the invocation in the remote object
     * @throws Exception
     *             if any problem arises during execution of the method
     */
    public Object executeCall(Object[] args, long methodHash) throws Exception {
        Object ret = null;
        Object server = this.object.get();
        if (server == null) {
            throw new NoSuchObjectException(
                    "Invalid Remote server object. (object not found)");
        }

        Method method = methodHashMap.get(new Long(methodHash));
        if (method == null) {
            throw new UnmarshalException(
                    "Method not found for this Remote Object");
        }

        logCall(server.getClass().getName() + this.objId + " : "
                + method);
        try {
            ret = method.invoke(server, args);
        } catch (IllegalArgumentException e) {
            logException(e);
            throw e;
        } catch (IllegalAccessException e) {
            logException(e);
            throw e;
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            logException(t);
            if (t instanceof Error) {
                throw new ServerError(t.getMessage(), (Error) t);
            }
            if (t instanceof Exception) {
                if (t instanceof RemoteException) {
                    throw new ServerException(t.getMessage(),
                            (RemoteException) t);
                } else {
                    throw (Exception) t;
                }
            }
        }

        return ret;
    }

    /**
     * Returns the number of arguments needed for the invocation of the method
     * represented by <code>methodHash</code>
     * 
     * @param methodHash
     *            The method identificator received in the request
     * @return the number of arguments
     * @throws NoSuchObjectException
     *             if there is not method for the received hash
     */
    public int getArgsCount(long methodHash) throws NoSuchObjectException {
        Method method = methodHashMap.get(new Long(methodHash));
        if (method == null) {
            throw new NoSuchObjectException(
                    "Method not found for this Remote Object");
        }
        return method.getParameterTypes().length;
    }

    /**
     * Returns true if the method represented by <code>methodHash</code>
     * returns any value. (the return type of the method is different than
     * <code>void</code>)
     * 
     * @param methodHash
     *            The method identificator received in the request
     * @return true if the return type of the method is different than
     *         <code>void</code>, otherwise returns false
     * @throws NoSuchObjectException
     *             if there is not method for the received hash
     */
    public boolean sendReturnValue(long methodHash)
            throws NoSuchObjectException {
        Method method = methodHashMap.get(new Long(methodHash));
        if (method == null) {
            throw new NoSuchObjectException(
                    "Method not found for this Remote Object");
        }
        return !(method.getReturnType().equals(Void.TYPE));
    }

    /**
     * Fills the <code>methodHashMap</code> table with all the remote methods
     * and its hashes implemented by the object being exported.
     * 
     * @param obj
     *            The object to inspect
     */
    private void buildMethodHashMap(Remote obj) {
        Set<Method> methodsSet = RemoteUtils.getRemoteMethods(obj.getClass());
        long methodHash;
        for (Method method : methodsSet) {
            methodHash = MethodHashGenerator.getMethodHash(method);
            method.setAccessible(true);
            methodHashMap.put(new Long(methodHash), method);
        }
        return;
    }

    /**
     * Sends call information to the logger.
     * 
     * @param msg
     *            The message to be logged
     */
    private void logCall(String msg) {
        Logger logger = Logger.getLogger("ar.org.fitc.rmi.server");
        try {
            logger.log(Level.FINER, "RMI "
                    + TransportManager.getTransportManager()
                            .getClientConnection() + " ["
                    + RemoteServer.getClientHost() + " : " + msg + "]");
        } catch (ServerNotActiveException e) {
            logger.log(Level.FINER, "RMI (No Connection Info) [" + " : " + msg
                    + "]");
        }
    }

    /**
     * Sends exception or error information to the logger.
     * 
     * @param e
     *            The exception or error to be logged
     */
    private void logException(Throwable e) {
        Logger logger = Logger.getLogger("ar.org.fitc.rmi.server");
        try {
            logger.log(Level.FINE, "RMI "
                    + TransportManager.getTransportManager()
                            .getClientConnection() + " ["
                    + RemoteServer.getClientHost() + "] exception: " + e);
        } catch (ServerNotActiveException e1) {
            logger.log(Level.FINE, "RMI (No Connection Info) ["
                    + "] exception: " + e);
        }

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
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        return;
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
    public void writeExternal(ObjectOutput out) throws IOException {
        return;
    }
}
