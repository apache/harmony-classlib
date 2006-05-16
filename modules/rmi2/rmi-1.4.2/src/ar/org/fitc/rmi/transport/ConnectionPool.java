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
import java.rmi.ConnectIOException;
import java.rmi.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import ar.org.fitc.rmi.utils.PropertiesReader;

/*
 * NOTE: 
 * This class has been modified in order to support 
 * Java VM 1.4.2 using the javac's target jsr14 
 */

/**
 * Reuses the connections that have been created for a specified
 * {@link ar.org.fitc.rmi.transport.Endpoint} and have been released. Maintains
 * a pool of connections by a Map, that contains for each
 * {@link ar.org.fitc.rmi.transport.Endpoint} a corresponding Map with a
 * {@link ar.org.fitc.rmi.transport.AbstractClientConnection}-Time) where
 * <code>Time</code> is the current time in which the connection was released.
 * <br>
 * The Connection Pool is implemented through a  {@link java.util.Timer}. 
 * The keep alive time for which a connection is maintained in the map can be 
 * obtained via the property <EM>ar.org.fitc.rmi.transport.connectionTimeout</EM>, 
 * where the default value is <EM>15000</EM>. <br>
 * When the connections are removed from the table because are evicted, such
 * connection is closed and lets the server know.
 * 
 * @author Marcelo Arcidiacono
 * @author Gustavo Petri
 * @author Diego Raúl Mercado
 */
public final class ConnectionPool {

    /**
     * The table used for storing the {@link ar.org.fitc.rmi.transport.Endpoint}
     * as key and a corresponding list that contains pairs
     * {@link AbstractClientConnection} and time in
     * milliseconds at the moment that the connection has been released.
     */
    private Map<Endpoint, HashMap<AbstractClientConnection, Long>> storedEpConnections;

    /**
     * Value of maximum time of the connection keep alive in the table.
     */
    private static Long connTimeOut;

    /**
     * Timer used for schedule tasks for future execution in a background
     * thread.
     */
    private Timer ripperTimer;

    static {
        connTimeOut = PropertiesReader.readLong(
                "ar.org.fitc.rmi.transport.connectionTimeout", 15000);
    }

    /**
     * Constructor for the {@link ConnectionPool}. In order to guarantee
     * serial access creates a synchronized (thread-safe) map backed by the
     * specified map. The timer is set as daemon. The delay before task is to be
     * executed and the time between successive task executions is the
     * {@link #connTimeOut}.
     */
    public ConnectionPool() {
        storedEpConnections = 
            new Hashtable<Endpoint, HashMap<AbstractClientConnection, Long>>();
        this.ripperTimer = new Timer(true);
        try {
            ripperTimer.schedule(new RipperTask(), connTimeOut, connTimeOut);
        } catch (Exception e) {
            // Should never happen since the ripperTask method is local.
            throw new AssertionError(e);
        }
    }

    /**
     * Returns a {@link ar.org.fitc.rmi.transport.AbstractClientConnection} for
     * the received {@link ar.org.fitc.rmi.transport.Endpoint}.If a
     * {@link ar.org.fitc.rmi.transport.AbstractClientConnection} exists in the
     * table for the specified {@link ar.org.fitc.rmi.transport.Endpoint} and
     * the connection is not evicted, the connection is usable and it is backed.
     * If no usable connection was found in the pool, a new
     * {@link ar.org.fitc.rmi.transport.AbstractClientConnection} is created and
     * backed. The {@link ar.org.fitc.rmi.transport.AbstractClientConnection}
     * found in the table, usable or not, is removed from the table.
     * 
     * @param ep
     *            the {@link ar.org.fitc.rmi.transport.Endpoint} for which a
     *            connection is required.
     * @return a {@link ar.org.fitc.rmi.transport.AbstractClientConnection} for
     *         the specified {@link ar.org.fitc.rmi.transport.Endpoint}
     * @throws IOException
     */
    public synchronized final AbstractClientConnection getClientConnection(
            Endpoint ep) throws IOException {
        AbstractClientConnection returnCC = null;

        if (storedEpConnections.containsKey(ep)) {
            Long checkTime = System.currentTimeMillis() - connTimeOut;

            Map<Endpoint, Set<AbstractClientConnection>> epConn2clean = 
                new HashMap<Endpoint, Set<AbstractClientConnection>>();

            Map<AbstractClientConnection, Long> storedCC = 
                storedEpConnections.get(ep);

            // iterate over the connections of the current EP
            for (Iterator iter = storedCC.entrySet().iterator(); iter.hasNext();) {
                Map.Entry pairs = (Map.Entry) iter.next();
                AbstractClientConnection cc = (AbstractClientConnection) pairs
                        .getKey();
                Long ccTime = (Long) pairs.getValue();

                // Anyway (usable or not) must be removed.
                addConn2clean(epConn2clean, ep, cc);

                if (ccTime < checkTime) {
                    // Close the Connection and let the server know.
                    cc.releaseConnection();
                } else {
                    returnCC = cc;
                    try {
                        returnCC.establishConnection();
                    } catch (Exception e) {
                        returnCC.releaseConnection();
                        returnCC = null;
                        continue;
                    }
                    break; // returnCC must be usable.
                }
            }
            // Clean up the evicted connections from the Pool.
            removeStoredCC(epConn2clean);
        }
        if (returnCC == null) {
            // No usable connection was found in the Pool.
            try {
                returnCC = ClientConnectionFactory.getClientConnection(ep);
                returnCC.establishConnection();
            } catch (java.net.UnknownHostException e) {
                throw new UnknownHostException("The host " + ep.getHost()
                        + " is unreacheable", e);
            } catch (java.net.ConnectException e) {
                throw new java.rmi.ConnectException("Cannot connect to "
                        + ep.getHost(), e);
            } catch (IOException e) {
                throw new ConnectIOException(
                        "I/O exception Creating Connection", e);
            }
        }
        return returnCC;
    }

    /**
     * Adds a {@link ar.org.fitc.rmi.transport.StreamClientConnection} on the
     * table for a specified {@link ar.org.fitc.rmi.transport.Endpoint} with the
     * current time in milliseconds. <br>
     * Notice that is only applicable to StreamOpClientConnection
     * 
     * @param ep
     *            the specific {@link ar.org.fitc.rmi.transport.Endpoint}
     * @param clConn
     *            the corresponding
     *            {@link ar.org.fitc.rmi.transport.StreamClientConnection} to
     *            the specified {@link ar.org.fitc.rmi.transport.Endpoint}.
     */
    public synchronized final void releaseClientConnection(Endpoint ep,
            StreamClientConnection clConn) { // only applicable to stream
        // connections

        if (storedEpConnections.containsKey(ep)) {
            storedEpConnections.get(ep).put(clConn, System.currentTimeMillis());
        } else {
            HashMap<AbstractClientConnection, Long> ccMap = new HashMap<AbstractClientConnection, Long>();
            ccMap.put(clConn, System.currentTimeMillis());
            storedEpConnections.put(ep, ccMap);
        }
    }

    /**
     * Iterate over <code>epConn2clean</code> and remove it from storedEpConnections. 
     * If the current endPoint has no more connections available, then removes
     * it from <code>storedEpConnections</code>
     * 
     * @param epConn2clean
     *            it has all the connections to be remove
     */
    @SuppressWarnings("unchecked")
    private synchronized final void removeStoredCC(
            Map<Endpoint, Set<AbstractClientConnection>> epConn2clean) {
        for (Iterator iter = epConn2clean.entrySet().iterator(); iter.hasNext();) {
            Map.Entry pairs = (Map.Entry) iter.next();
            Endpoint ep = (Endpoint) pairs.getKey();
            Set<AbstractClientConnection> cc2remove = (Set<AbstractClientConnection>) pairs
                    .getValue();

            HashMap<AbstractClientConnection, Long> storedConnectionsMap = storedEpConnections
                    .get(ep);

            // for each connection in setCC2remove remove it from
            // storedEpConnections
            for (AbstractClientConnection cc : cc2remove) {
                if (storedConnectionsMap.remove(cc) == null) {
                    throw new AssertionError(); // type - safety
                }
            }

            // if EP has not more cc eliminate it from storedEpConnections
            if (storedConnectionsMap.isEmpty()) {
                storedEpConnections.remove(ep);
            }
        }
    }

    /**
     * Verifies that <code>epConn2Clean</code> contains a sets of connections.
     * If not instantiates it and then adds the <code>cc</code> to this set.
     * <br>
     * After that, we insert or update the <code>ep</code> with the set of
     * connections <code>cc2clean</code>
     * 
     * @param epConn2clean
     *            the map which stores end points' connections to be cleaned
     * @param ep
     *            the current end point of the connections
     * @param cc
     *            the cc to be inserted in the Set
     */
    private static final void addConn2clean(
            Map<Endpoint, Set<AbstractClientConnection>> epConn2clean,
            Endpoint ep, AbstractClientConnection cc) {
        Set<AbstractClientConnection> cc2clean = epConn2clean.get(ep);
        if (cc2clean == null) {
            cc2clean = new HashSet<AbstractClientConnection>();
        }
        cc2clean.add(cc);
        epConn2clean.put(ep, cc2clean); // insert or replace the value....
    }

    /**
     * Checks the pool for evicted connections.
     */
    private final class RipperTask extends TimerTask {

        public synchronized final void run() {
            Long checkTime = System.currentTimeMillis() - connTimeOut;

            Map<Endpoint, Set<AbstractClientConnection>> epConn2clean = new HashMap<Endpoint, Set<AbstractClientConnection>>();

            // iterate storedEpConnections
            for (Iterator iter = storedEpConnections.entrySet().iterator(); iter
                    .hasNext();) {

                Map.Entry pairs = (Map.Entry) iter.next();
                Endpoint ep = (Endpoint) pairs.getKey();
                HashMap ccMap = (HashMap) pairs.getValue();

                // iterate over the connection of the current EP
                for (Iterator iter2 = ccMap.entrySet().iterator(); iter2
                        .hasNext();) {
                    Map.Entry pairs2 = (Map.Entry) iter2.next();
                    AbstractClientConnection cc = (AbstractClientConnection) pairs2
                            .getKey();
                    Long time = (Long) pairs2.getValue();

                    // checks cc life...
                    if (time < checkTime) {
                        cc.releaseConnection();
                        addConn2clean(epConn2clean, ep, cc);
                    }
                }
            }
            if (!epConn2clean.isEmpty()) {
                removeStoredCC(epConn2clean);
            }
        }
    }
}
