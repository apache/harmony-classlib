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
package ar.org.fitc.rmi.registry;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.StubNotFoundException;
import java.rmi.registry.Registry;
import java.rmi.server.ObjID;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.ServerRef;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import ar.org.fitc.rmi.runtime.RemoteReferenceManager;
import ar.org.fitc.rmi.server.UnicastRemoteRef2Impl;
import ar.org.fitc.rmi.server.UnicastRemoteRefImpl;
import ar.org.fitc.rmi.server.UnicastServerRef2Impl;
import ar.org.fitc.rmi.server.UnicastServerRefImpl;
import ar.org.fitc.rmi.transport.Endpoint;
import ar.org.fitc.rmi.transport.TransportManager;

/**
 * This class is the implementation for the specified <code>Registry</code>
 * interface.
 * 
 * @author Gonzalo Ortega
 * 
 */
public final class RegistryImpl implements Registry {

    private Map<String, Remote> registryTable;

    private Set<String> localIPs;

    /**
     * Creates a new instance of RegistryImpl
     */
    public RegistryImpl() {
        registryTable = new ConcurrentHashMap<String, Remote>();
        localIPs = new HashSet<String>();
        setLocalIPs();
    }

    /**
     * Returns all keys present in the registry in a String array.
     * 
     * @return The list of keys bound to the registry
     * @throws RemoteException
     *             if remote communication with the registry failed
     * @throws AccessException
     *             if registry denies the caller access to perform this
     *             operation
     */
    public final String[] list() throws RemoteException, AccessException {
        Set<String> keys = registryTable.keySet();
        String[] result = new String[keys.size()];
        keys.toArray(result);
        return result;
    }

    /**
     * Returns the associated stub for the received key, or an exception if no
     * stub is present for that key
     * 
     * @param name
     *            The key used to bind the remote stub in the registry
     * @return The remote object associated with <code>name</code>
     * @throws RemoteException
     *             if remote communication with the registry failed
     * @throws NotBoundException
     *             If the key is not found in the registry
     */
    public final Remote lookup(String name) throws RemoteException,
            NotBoundException, AccessException {
        if (name == null) {
            throw new NullPointerException("The String name is null.");
        }
        if (!(registryTable.containsKey(name))) {
            throw new NotBoundException(
                    "There isn't any object bound in the registry with that key.");
        }
        return registryTable.get(name);
    }

    /**
     * Puts the received key <code>name</code> and the stub <code>obj</code>
     * in this registry, or throws an exception if the received key is already
     * present in the registry.
     * 
     * @param name
     *            The key that will be used to lookup the remote object
     * @param obj
     *            The remote object associated with <code>name</code>
     * @throws RemoteException
     *             if remote communication with the registry failed
     * @throws AlreadyBoundException
     *             if <code>name</code> is already present in the table
     * @throws AccessException
     *             if registry denies the caller access to perform this
     *             operation
     */
    public final void bind(String name, Remote obj) throws RemoteException,
            AlreadyBoundException, AccessException {

        checkAccessPrivilegies();
        if (name == null || obj == null) {
            throw new NullPointerException(
                    "Either String name or Remote object are null.");
        }
        if (registryTable.containsKey(name)) {
            throw new AlreadyBoundException("The key " + name
                    + " already exists in the registry.");
        }
        registryTable.put(name, obj);

    }

    /**
     * Puts the received key <code>name</code> and the stub <code>obj</code>
     * in this registry.
     * 
     * @param name
     *            The key that will be used to lookup the remote object
     * @param obj
     *            The remote object associated with <code>name</code>
     * @throws RemoteException
     *             if remote communication with the registry failed
     * @throws AccessException
     *             if registry denies the caller access to perform this
     *             operation
     */
    public final void rebind(String name, Remote obj) throws RemoteException,
            AccessException {
        checkAccessPrivilegies();
        if (name == null || obj == null) {
            throw new NullPointerException(
                    "Either String name or Remote object are null.");
        }
        registryTable.put(name, obj);
    }

    /**
     * Removes the stub associated to the key received <code>name</code> from
     * this registry
     * 
     * @param name
     *            the name of the binding to remove
     * @throws RemoteException
     *             if remote communication with the registry failed
     * @throws NotBoundException
     *             if <code>name</code> is not found in the registry
     * @throws AccessException
     *             if registry denies the caller access to perform this
     *             operation
     */
    public final void unbind(String name) throws RemoteException, NotBoundException,
            AccessException {

        checkAccessPrivilegies();
        if (name == null) {
            throw new NullPointerException("The String name is null.");
        }
        if (!(registryTable.containsKey(name))) {
            throw new NotBoundException(
                    "There isn't any object bound in the registry with that key.");
        }
        registryTable.remove(name);
    }

    /**
     * Exports the registry implementation in the received port, with the
     * received socket factories. The exportation procedure is similar to the
     * procedure implemented for <code>UnicastRemoteObject</code>, but using
     * the special well-known <code>ObjID</code> for the registry.
     * 
     * @param port
     *            The port where the registry will listen requests
     * @param csf
     *            The <code>ClientSocketFactory</code> that will be used to
     *            contact this registry
     * @param ssf
     *            The <code>ServerSocketFactory</code> used for exportation
     * @param useType2Ref
     *            Indicates whether the references created during object
     *            exportation should be "UnicastRef" or "UnicastRef2"
     * @return A stub for this registry implementation
     * @throws RemoteException
     */
    public final Remote exportObject(int port, RMIClientSocketFactory csf,
            RMIServerSocketFactory ssf, boolean useType2Ref)
            throws RemoteException {

        if (port == 0) {
            port = Registry.REGISTRY_PORT;
        }
        RemoteReferenceManager rrm = RemoteReferenceManager
                .getRemoteReferenceManager();
        if (rrm.isExported(this)) {
            throw new RemoteException("Object already exported.");
        }
        TransportManager tm = TransportManager.getTransportManager();
        ObjID objID = new ObjID(ObjID.REGISTRY_ID);
        Endpoint ep = tm.export(objID, port, ssf, csf);
        ServerRef sref;
        Remote stub;
        if (useType2Ref) {
            sref = new UnicastServerRef2Impl(this, objID, ep);
            stub = rrm.createStub(new UnicastRemoteRef2Impl(objID, ep), this);
        } else {
            sref = new UnicastServerRefImpl(this, objID, ep);
            stub = rrm.createStub(new UnicastRemoteRefImpl(objID, ep), this);
        }
        rrm.storeExportData(this, objID, sref, stub);
        return stub;
    }

    /**
     * Creates a new stub for a registry, using the <code>createStub</code>
     * method from the <code>RemoteReferenceManager.</code> First instantiates
     * a new <code>RemoteRef</code> with an <code>Endpoint</code> created
     * starting from the received parameters, and the well-known
     * <code>ObjID</code> for the Registry.
     * 
     * @param port
     *            The port that will be used to contact the registry
     * @param host
     *            The host where the registry is located
     * @param csf
     *            The <code>ClientSocketFactory</code> that will be used to
     *            contact the registry
     * @param useType2Ref
     *            Indicates whether the reference used for the new stub should
     *            be "UnicastRef" or "UnicastRef2"
     * @return A stub for the desired registry
     * @throws StubNotFoundException
     *             if the creation of the stub for this registry fails
     */
    public static final Remote createStub(int port, String host,
            RMIClientSocketFactory csf, boolean useType2Ref) {
        ObjID objID = new ObjID(ObjID.REGISTRY_ID);
        Endpoint ep;
        if (host == null) {
            ep = new Endpoint(port, csf);
        } else {
            ep = new Endpoint(host, port, csf);
        }
        Remote stub;
        if (useType2Ref) {
            stub = new RegistryImpl_Stub(new UnicastRemoteRef2Impl(objID, ep));
        } else {
            stub = new RegistryImpl_Stub(new UnicastRemoteRefImpl(objID, ep));
        }
        return stub;
    }

    /**
     * Due to the exportation of multiple registries is not supported at the
     * moment the equals method must return "true" for any instance of the
     * <code>RegistryImpl</code> class in order to avoid the exportation of a
     * new registry.
     * 
     * @param obj
     *            The object that will be compared for equality
     * @return <code>true</code> if <code>obj</code> is instance of the
     *         class <code>RegistryImpl</code>, else return
     *         <code>false</code>
     */
    @Override
    public final boolean equals(Object obj) {
        if (obj != null) {
            if (obj instanceof RegistryImpl) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return The hash code for this object
     */
    @Override
    public final int hashCode() {
        return RegistryImpl.class.hashCode();
    }

    /**
     * Checks if the current client has privilegies to modify the contents of
     * the registry. All non-local clients should be rejected.
     * 
     * @throws AccessException
     *             if registry denies the caller access to perform this
     *             operation
     */
    private final void checkAccessPrivilegies() throws AccessException {
        String hostName;
        try {
            hostName = RemoteServer.getClientHost();
        } catch (ServerNotActiveException e) {
            // if no remote host is currently executing this method,
            // then is localhost, and the access should be granted.
            return;
        }
        if (hostName == null) {
            throw new AccessException("Can not get remote host address.");
        }
        if (!localIPs.contains(hostName)) {
            throw new AccessException(
                    "Registry can not be modified by this host.");
        }
    }

    /**
     * Fills the internal set containing all the local addresses for this host.
     * This map will be used when the <code>checkAccessPrivilegies</code>
     * executes.
     * 
     */
    private final void setLocalIPs() {
        Enumeration<NetworkInterface> netInterfaces;
        Enumeration<InetAddress> inetAddresses;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            return;
        }
        // Get all IPs from all network interfaces
        while (netInterfaces.hasMoreElements()) {
            inetAddresses = netInterfaces.nextElement().getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                localIPs.add(inetAddresses.nextElement().getHostAddress());
            }
        }
    }
}
