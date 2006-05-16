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
import java.rmi.server.ExportException;
import java.rmi.server.ObjID;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UID;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import ar.org.fitc.rmi.utils.Pair;
import ar.org.fitc.rmi.utils.ReversibleHashSet;

/**
 * Serves as a Transport Service Provider for the RMI subsystem. It is
 * implemented as a singleton, and it provides the methods necesary to handle
 * exportation (and unexportation) of objects, and also invocation of methods of
 * remote objects.
 * <p>
 * This class contains a mapping of ObjIDs to ports, which will be used to
 * validate the invocation of a method for a certain object in a certain port.
 * 
 * @author Gustavo Petri
 * 
 */
public final class TransportManager {

    /**
     * This is a Singleton.
     */
    private static TransportManager transportManager;

    /**
     * A {@link ar.org.fitc.rmi.utils.ReversibleHashSet} for storing the port
     * and the corresponding {@link java.rmi.server.ObjID}.
     */
    private ReversibleHashSet<Integer, ObjID> portObjIDMap;

    /**
     * Serves as a cache for
     * {@link ar.org.fitc.rmi.transport.MultiThreadedServer} and its
     * {@link ar.org.fitc.rmi.transport.Endpoint}, indexed by port.
     */
    private HashMap<Integer, Pair<MultiThreadedServer, Endpoint>> serverMap;

    /**
     * Indexes the ClientHost and
     * {@link ar.org.fitc.rmi.transport.StreamClientConnection} by ThreadID.
     */
    private Map<Long, Pair<String, Integer>> clientConnectionMap;

    /**
     * Mapping from {@link java.rmi.server.ObjID} to the ThreadID of the threads
     * executing methods for that {@link java.rmi.server.ObjID}.
     */
    private Map<ObjID, Set<Long>> executingThreads;

    /**
     * A pool of connections.
     */
    private ConnectionPool connPool;

    private Map<Integer, RMIServerSocketFactory> portsInUse;

    /**
     * The getter for the Singleton.
     * 
     * @return the singleton
     */
    public final static synchronized TransportManager getTransportManager() {
        if (transportManager == null) {
            transportManager = new TransportManager();
        }
        return transportManager;
    }

    /**
     * The constructor for the TransportManager.
     */
    private TransportManager() {
        this.portObjIDMap = new ReversibleHashSet<Integer, ObjID>();
        this.serverMap = new HashMap<Integer, Pair<MultiThreadedServer, Endpoint>>();
        // This Map needs to be synchronized.
        this.clientConnectionMap = new ConcurrentHashMap<Long, Pair<String, Integer>>();
        this.portsInUse = new HashMap<Integer, RMIServerSocketFactory>();
        this.executingThreads = new ConcurrentHashMap<ObjID, Set<Long>>();
        this.connPool = new ConnectionPool();
        transportManager = this;
    }

    /**
     * Obtains a ClientConnection and sends a DGCack message
     * 
     * @param uid
     *            the specified {@link UID}
     * @param ep
     *            The {@link Endpoint} to acknowledge
     */
    protected void acknowledgeDGC(UID uid, Endpoint ep) {
        AbstractClientConnection cc;

        try {
            cc = connPool.getClientConnection(ep);
            cc.acknowledgeDGC(uid);
        } catch (IOException e) {
            // FIXME REVIEW: What to do when an exception is thrown here. May be
            // a logger could be useful.
        }

    }
    
    /**
     * Obtains the {@link #clientConnectionMap}
     * 
     * @return the {@link #clientConnectionMap}
     */
    protected Map<Long, Pair<String, Integer>> getClientConnectionMap() {
        return clientConnectionMap;
    }

    /**
     * Obtains the the {@link #executingThreads}
     * 
     * @return the the {@link #executingThreads}
     */
    protected Map<ObjID, Set<Long>> getExecutingThreads() {
        return executingThreads;
    }

    
    /**
     * Creates a
     * {@link java.net.ServerSocket} and listens in the port argument Port for 
     * new connectios corresponding to the objects with objID ID. When a new 
     * connection is received it constructs a new {@link MultiThreadedServer} to
     * handle the requests.
     * 
     * @param objID
     *            the specified {@link java.rmi.server.ObjID}
     * @param port
     *            the port of the connection
     * @param ssf
     *            the specified {@link java.rmi.server.RMIServerSocketFactory}
     * @param csf
     *            the specified {@link java.rmi.server.RMIClientSocketFactory}
     * @return the {@link ar.org.fitc.rmi.transport.Endpoint} of the connection
     * @throws ExportException
     *             if the exportation fails
     */
    public final Endpoint export(ObjID objID, int port,
            RMIServerSocketFactory ssf, RMIClientSocketFactory csf)
            throws ExportException {
        Endpoint ep;

        if (serverMap.containsKey(port)) {
            if (portsInUse.get(port) != ssf) {
                throw new ExportException("Port Already in use");
            }
            portObjIDMap.insert(port, objID);
            return serverMap.get(port).getSecond();
        } else if (port > 0) {
            portsInUse.put(port, ssf);
        } else if (!serverMap.isEmpty() && port == 0) {
            int newPort = serverMap.keySet().iterator().next();
            portObjIDMap.insert(newPort, objID);
            portsInUse.put(newPort, ssf);
            return serverMap.get(newPort).getSecond();
        }
        MultiThreadedServer multiThreadedServer = new MultiThreadedServer(ssf,
                port);
        portObjIDMap.insert(multiThreadedServer.getLocalPort(), objID);
        ep = new Endpoint(multiThreadedServer.getLocalPort(), csf);
        serverMap
                .put(multiThreadedServer.getLocalPort(),
                        new Pair<MultiThreadedServer, Endpoint>(
                                multiThreadedServer, ep));
        multiThreadedServer.start();
        return ep;
    }

    /**
     * Unexports an object.
     * 
     * @param objID
     *            the specified {@link java.rmi.server.ObjID}
     * @param force
     *            <code>true</code> if the object must to be unexported
     * @return <code>true</code> if the object has been exported, <code>false
     * </code>
     *         if not.
     */
    public final boolean unexport(ObjID objID, boolean force) {
        boolean ret = true;
        int port;

        if (executingThreads.containsKey(objID) && !force) {
            ret = false;
        } else {
            port = portObjIDMap.getKeyFromValue(objID);
            if (portObjIDMap.getValues(port).size() > 1) {
                portsInUse.remove(portObjIDMap.getKeyFromValue(objID));
                portObjIDMap.removeValue(objID);
            } else {
                portObjIDMap.removeValue(objID);
                try {
                    serverMap.get(port).getFirst().stopServing();
                    serverMap.remove(port);
                } catch (IOException e) {
                    // FIXME REVIEW: What to do with this exception?
                    // May be some logging would be useful here.
                }
            }
        }
        return ret;
    }

    /**
     * Obtains a ClientConnection and forwards the request to it. If the 
     * used connection is reusable, it is returned to the pool, otherwise is
     * closed. 
     * 
     * @param objID
     *            the specified {@link java.rmi.server.ObjID}
     * @param ep
     *            the {@link ar.org.fitc.rmi.transport.Endpoint} of the
     *            connection
     * @param args
     *            the arguments of the invocation
     * @param methodHash
     *            the specified hash
     * @param waitReturn
     *            this parameter indicates whether or not to wait for a return
     *            value
     * @return the return value of the remote method call
     * @throws Exception
     *             if any exception is thrown on the server side
     */
    public final Object invoke(ObjID objID, Endpoint ep, Object[] args,
            long methodHash, boolean waitReturn) throws Exception {
        Object obj;
        AbstractClientConnection cc = connPool.getClientConnection(ep);
        obj = cc.invoke(objID, methodHash, args, waitReturn);
        if (cc.isReusable()) {
            // only applicable if the connection is reusable
            connPool.releaseClientConnection(ep, (StreamClientConnection) cc);
        } else {
        	cc.releaseConnection();
        }
        return obj;
    }

    /**
     * Obtains the <code>ClientHost</code>
     * 
     * @return the <code>ClientHost</code>
     * @throws ServerNotActiveException
     *             if there is no Client executing methods
     */
    public final String getClientHost() throws ServerNotActiveException {
        Pair<String, Integer> ret = clientConnectionMap.get(Thread
                .currentThread().getId());

        if (ret == null) {
            throw new ServerNotActiveException(
                    "There is no Client executing methods");
        }
        return ret.getFirst();
    }

    /**
     * Obtains the <code>ClientConnection</code>
     * 
     * @return the <code>ClientConnection</code>
     * @throws ServerNotActiveException
     *             if there is no Client executing methods
     */
    public final String getClientConnection() throws ServerNotActiveException {
        Pair<String, Integer> ret = clientConnectionMap.get(Thread
                .currentThread().getId());

        if (ret == null) {
            throw new ServerNotActiveException(
                    "There is no Client executing methods");
        }
        return "TCP Connection (" + ret.getSecond() + ")";
    }
}
