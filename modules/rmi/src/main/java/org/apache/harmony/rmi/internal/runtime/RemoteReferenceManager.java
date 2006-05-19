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
package org.apache.harmony.rmi.internal.runtime;

import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;
import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.StubNotFoundException;
import java.rmi.server.ObjID;
import java.rmi.server.RemoteObjectInvocationHandler;
import java.rmi.server.RemoteRef;
import java.rmi.server.RemoteStub;
import java.rmi.server.ServerRef;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.apache.harmony.rmi.internal.dgc.server.DGCImpl;
import org.apache.harmony.rmi.internal.server.UnicastServerRefImpl;
import org.apache.harmony.rmi.internal.transport.TransportManager;
import org.apache.harmony.rmi.internal.utils.Pair;
import org.apache.harmony.rmi.internal.utils.PropertiesReader;
import org.apache.harmony.rmi.internal.utils.RemoteUtils;

/*
 * NOTE: 
 * This class has been modified in order to support 
 * Java VM 1.4.2 using the javac's target jsr14 
 */

/**
 * Implements a singleton which provides neccessary funtionality for
 * remote object exportation, remote object management, stub generation,
 * call execution, etc. 
 * 
 * @author Gonzalo Ortega
 * 
 */
public final class RemoteReferenceManager {

    private static RemoteReferenceManager rrm;

    private Map<ObjID, ServerRef> serverRefsMap;

    private Map<Remote, Pair<Remote, ObjID>> exportedRemotes;

    private DGCImpl dgc;

    /**
     * Implements the singleton behavior. If there is an instance of
     * <code>RemoteReferenceManager</code> is returned, else a new instance is
     * created and returned.
     * 
     * @return the only <code>RemoteReferenceManager</code> instance
     */
    public final static synchronized RemoteReferenceManager getRemoteReferenceManager() {
        if (rrm == null) {
            rrm = new RemoteReferenceManager();
        }
        return rrm;
    }
    
    /**
     * Creates a new instance of <code>RemoteReferenceManager</code>. This
     * created instance should be the one and only. (<code>RemoteReferenceManager</code>
     * is a Singleton object)
     */
    private RemoteReferenceManager() {
        // Initialize internal tables
        serverRefsMap = new Hashtable<ObjID, ServerRef>();
        exportedRemotes = Collections
                .synchronizedMap(new WeakHashMap<Remote, Pair<Remote, ObjID>>());

        // Export the Distributed Garbage Collector
        dgc = new DGCImpl();
        ObjID objID = new ObjID(ObjID.DGC_ID);
        UnicastServerRefImpl sref = new UnicastServerRefImpl(dgc, objID, null);
        storeExportData(null, objID, sref, null);
    }

    /**
     * Stores the data generated during object exportation into the
     * <code>RemoteReferenceManager</code> internal tables
     * 
     * @param obj
     *            The remote object being exported
     * @param objID
     *            The <code>ObjID</code> generated for <code>obj</code>
     * @param sref
     *            The <code>ServerRef</code> generated for <code>obj</code>
     * @param stub
     *            The stub generated for <code>obj</code>
     */
    public final void storeExportData(Remote obj, ObjID objID, ServerRef sref,
            Remote stub) {
        serverRefsMap.put(objID, sref);
        exportedRemotes.put(obj, new Pair<Remote, ObjID>(stub, objID));
        dgc.register(objID, obj);
    }

    /**
     * Returns <code>true</code> if <code>obj</code> has been exported to
     * the RMI Runtime.
     * 
     * @param obj
     *            The remote object that could be exported
     * @return <code>true</code> if <code>obj</code> has been exported to
     *         the RMI Runtime
     */
    public final boolean isExported(Remote obj) {
        if ((exportedRemotes.containsKey(obj))
                && !(obj instanceof RemoteObjectInvocationHandler)) {
            return true;
        }
        return false;
    }

    /**
     * Receives a dispatch request from the Transport Layer and forwards it to
     * the appropriate dispatcher object.
     * 
     * @param objID
     *            The <code>ObjID</code> received in the request
     * @param hash
     *            The method identificator received in the request
     * @param args
     *            The arguments received in the request
     * @return The result of the invocation in the remote object
     * @throws Exception
     *             If the invocation of the method throws an exception
     */
    public final Object executeCall(ObjID objID, long hash, Object[] args)
            throws Exception {
        if (objID == null) {
            throw new NoSuchObjectException(
                    "Remote server not available (ObjID == null).");
        }
        ServerRef sref = serverRefsMap.get(objID);
        if (sref == null) {
            throw new NoSuchObjectException(
                    "Remote server not available (ObjID not found in lookup).");
        }

        /*
         * REVIEW: For call execution the instance of ServerRef needed is of
         * type UnicastServerRefImpl, instead of ServerRef... (ServerRef doesn't
         * specify the dispach method) Review the casting or the type stored in
         * the container when the implementation of the Activation framework
         * takes place.
         */
        if (sref instanceof UnicastServerRefImpl) {
            return ((UnicastServerRefImpl) sref).executeCall(args, hash);
        }
        throw new NoSuchObjectException(
                "Remote server not available (No UnicastServerImpl reference).");
    }

    /**
     * Returns a stub for the exported object <code>obj</code>
     * 
     * @param obj
     *            the remote object that the stub is requested for
     * @return the stub corresponding to <code>obj</code>
     * @throws NoSuchObjectException
     *             if there is no stub correspondig to <code>obj</code>
     */
    public final Remote getStub(Remote obj) throws NoSuchObjectException {
        if (obj == null) {
            throw new NoSuchObjectException(
                    "Invalid (null) Remote server object.");
        }
        Pair<Remote, ObjID> data;
        if ((data = exportedRemotes.get(obj)) != null) {
            return data.getFirst();
        }
        throw new NoSuchObjectException("Stub not found for this object.");
    }

    /**
     * Removes the remote object <code>obj</code> from internal tables, and
     * delegates the unexportation to the Transport Layer, in order to perform
     * the neccessary cleanup.
     * 
     * @param obj
     *            The remote object that will be unexported
     * @param force
     *            if true, unexports the object even if there are pending calls;
     *            if false, only unexports the object if there are no pending
     *            calls
     * @return true if the remote object was unexported, false otherwise
     * @throws NoSuchObjectException
     *             if the remote object was not exported
     */
    public final boolean unexportObject(Remote obj, boolean force)
            throws NoSuchObjectException {
        if (obj == null) {
            throw new NoSuchObjectException(
                    "Invalid (null) Remote server object.");
        }
        Pair<Remote, ObjID> data;
        if ((data = exportedRemotes.get(obj)) == null) {
            throw new NoSuchObjectException("Object not exported.");
        }
        ObjID objID = data.getSecond();

        TransportManager tm = TransportManager.getTransportManager();
        if (tm.unexport(objID, force)) {
            // Remove data for this object from the internal tables
            serverRefsMap.remove(objID);
            exportedRemotes.remove(obj);
            return true;
        }
        return false;
    }

    /**
     * Removes the received <code>objID</code> from internal tables, and
     * delegates the unexportation to the Transport Layer, in order to perform
     * the neccessary cleanup.
     * 
     * @param objID
     *            The <code>objID</code> that will be removed
     * @return true if the remote object was unexported, false otherwise
     * @throws NoSuchObjectException
     *             if the <code>objID</code> was not found
     */
    public final boolean unexportObjectDGC(ObjID objID)
            throws NoSuchObjectException {
        if (objID == null) {
            throw new NoSuchObjectException(
                    "Invalid (null) Remote server object.");
        }
        if (!(serverRefsMap.containsKey(objID))) {
            return true;
        }
        TransportManager tm = TransportManager.getTransportManager();
        if (tm.unexport(objID, true)) {
            // Remove data for this object from the internal tables
            serverRefsMap.remove(objID);
            // Removing data from exportedRemotes is not needed, because it will
            // be
            // automatically removed when GC collects the remote object
            return true;
        }
        return false;
    }

    /**
     * Returns the number of arguments received by the method represented by
     * <code>methodHash</code> in the remote object referenced by
     * <code>objID</code>
     * 
     * @param objID
     *            The <code>ObjID</code> received in the request
     * @param methodHash
     *            The method identificator received in the request
     * @return the number of arguments of the method
     * @throws NoSuchObjectException
     *             if there isn't any exported object with the received
     *             <code>ObjID</code>
     */
    public final int getArgsCount(ObjID objID, long methodHash)
            throws NoSuchObjectException {
        if (objID == null) {
            throw new NoSuchObjectException(
                    "Remote server not available (ObjID == null).");
        }
        ServerRef sref = serverRefsMap.get(objID);
        if (sref == null) {
            throw new NoSuchObjectException(
                    "Remote server not available (ObjID not found in lookup).");
        }

        /*
         * REVIEW: For call execution the instance of ServerRef needed is of
         * type UnicastServerRefImpl, instead of ServerRef... (ServerRef doesn't
         * specify the getArgsCount method) Review the casting or the type
         * stored in the container when the implementation of the Activation
         * framework takes place.
         */
        if (sref instanceof UnicastServerRefImpl) {
            return ((UnicastServerRefImpl) sref).getArgsCount(methodHash);
        } 
        throw new NoSuchObjectException(
                    "Remote server not available (No UnicastServerImpl reference).");
    }

    /**
     * Returns true if the method represented by <code>methodHash</code> in
     * the remote object referenced by <code>objID</code> returns any value.
     * (the return type of the method is different than <code>void</code>)
     * 
     * @param objID
     *            The <code>ObjID</code> received in the request
     * @param methodHash
     *            The method identificator received in the request
     * @return true if the return type of the method is different than
     *         <code>void</code>, otherwise returns false
     * @throws NoSuchObjectException
     *             if there isn't any exported object with the received
     *             <code>ObjID</code>
     */
    public final boolean sendReturnValue(ObjID objID, long methodHash)
            throws NoSuchObjectException {
        if (objID == null) {
            throw new NoSuchObjectException(
                    "Remote server not available (ObjID == null).");
        }
        ServerRef sref = serverRefsMap.get(objID);
        if (sref == null) {
            throw new NoSuchObjectException(
                    "Remote server not available (ObjID not found in lookup).");
        }

        /*
         * REVIEW: For call execution the instance of ServerRef needed is of
         * type UnicastServerRefImpl, instead of ServerRef... (ServerRef doesn't
         * specify the sendReturnValue method) Review the casting or the type
         * stored in the container when the implementation of the Activation
         * framework takes place.
         */
        if (sref instanceof UnicastServerRefImpl) {
            return ((UnicastServerRefImpl) sref).sendReturnValue(methodHash);
        } 
        throw new NoSuchObjectException(
                    "Remote server not available (No UnicastServerImpl reference).");
    }

    /**
     * Instantiates and returns a stub for a remote object starting from the
     * class generated by <code>rmic</code>. If the class is no found, an
     * exception is thrown.
     * 
     * @param ref
     *            The remote reference for the stub
     * @param obj
     *            The object, that the stub will represent
     * @return a stub for <code>obj</code>
     * @throws StubNotFoundException
     *             if the stub class is not found, or any other problem arises
     *             during stub instantiation.
     */
    public final RemoteStub createRegularStub(RemoteRef ref, Remote obj)
            throws StubNotFoundException {

        Class stubBaseClass = findStubBaseClass(obj.getClass());
        String stubClassName = stubBaseClass.getName() + "_Stub";
        Class[] argsClass = new Class[] { java.rmi.server.RemoteRef.class };
        Object[] args = new Object[] { ref };
        Constructor constructor;
        
        Object stub;
        try {
            Class stubClass = Class.forName(stubClassName, true, stubBaseClass
                    .getClassLoader());
            constructor = stubClass.getConstructor(argsClass);
        } catch (Exception e) {
            throw new StubNotFoundException(stubClassName
                    + ": Stub class not found", e);
        }

        try {
            stub = constructor.newInstance(args);
        } catch (Exception e) {
            throw new StubNotFoundException(
                    "Error during stub instantiation - " + stubClassName, e);
        }

        if (stub instanceof RemoteStub) {
            return (RemoteStub) stub;
        }
        throw new StubNotFoundException(stubClassName
                    + " doesn't inherits from RemoteStub.");
    }

    /**
     * Instantiates and returns a stub either starting from a class generated by
     * <code>rmic</code>, or creating a dynamic proxy class for the remote
     * object.
     * 
     * @param ref
     *            The remote reference for the stub
     * @param obj
     *            The object, that the stub will represent
     * @return a stub for <code>obj</code>
     * @throws StubNotFoundException
     *             If a dynamic proxy can not be instantiated for that object.
     */
    public final Remote createStub(RemoteRef ref, Remote obj)
            throws StubNotFoundException {
        Remote stub = null;
        boolean ignoreStubClasses = false;
        String propertyValue;

        // Verify if the old non-dynamic stubs should be ignored
        propertyValue = PropertiesReader
                .readString("java.rmi.server.ignoreStubClasses");
        if (propertyValue != null) {
            ignoreStubClasses = propertyValue.equalsIgnoreCase("true");
        }

        if (!ignoreStubClasses) {
            try {
                stub = createRegularStub(ref, obj);
                return stub;
            } catch (StubNotFoundException e) {
                // An error has been produced during regular stub instantiation.
                // ignore it and try now a dynamic proxy.
            }
        }

        Set<Class> remoteSet = (RemoteUtils.getRemoteInterfaces(obj.getClass()));
        Class[] remoteInterfaces = new Class[remoteSet.size()];
        remoteSet.toArray(remoteInterfaces);

        try {
            stub = (Remote) Proxy.newProxyInstance(obj.getClass()
                    .getClassLoader(), remoteInterfaces,
                    new RemoteObjectInvocationHandler(ref));
        } catch (IllegalArgumentException ex) {
            throw new StubNotFoundException(
                    "Couldn't create dynamic proxy for " + obj, ex);
        }
        return stub;
    }

    /**
     * Finds the "root class" according to RMI Specification, wich name will be
     * used for constructing the name of the stub class that corresponds to the
     * <code>inspect</code> class.
     * 
     * @param inspect
     *            the class of the object
     * @return the root class of the object
     * @throws StubNotFoundException
     *             If the class does not implement any remote interfaces
     */
    // Returns the apropriate root class for a stub of the class received as
    // parameter.
    private final Class findStubBaseClass(Class inspect)
            throws StubNotFoundException {
        Set<Class> remoteInterfacesList = RemoteUtils
                .getRemoteInterfaces(inspect);

        do {
            // Check if the class implements any Remote Interface directly
            Class[] directInterfacesList = inspect.getInterfaces();
            for (Class directInterface : directInterfacesList) {
                if (remoteInterfacesList.contains(directInterface)) {
                    return inspect;
                }
            }
            // The interfaces implemented directly aren't remote interfaces...
            // check now the interfaces implemented directly by the superclass.
            inspect = inspect.getSuperclass();
        } while (inspect != null);

        throw new StubNotFoundException(
                "The remote object doesn't implement any Remote interfaces.");
    }
}
