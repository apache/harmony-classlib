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
package org.apache.harmony.rmi.internal.transport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.rmi.MarshalException;
import java.rmi.server.ObjID;
import java.rmi.server.UID;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.harmony.rmi.internal.runtime.RemoteReferenceManager;
import org.apache.harmony.rmi.internal.transport.jrmp.Message;
import org.apache.harmony.rmi.internal.transport.jrmp.ReturnMessage;
import org.apache.harmony.rmi.internal.transport.jrmp.ServerProtocolHandler;
import org.apache.harmony.rmi.internal.utils.Pair;

/*
 * NOTE: 
 * This class has been modified in order to support 
 * Java VM 1.4.2 using the javac's target jsr14 
 */

/**
 *
 * The server side view of a Connection. Provides methods to interact with the 
 * {@link AbstractClientConnection} 
 * 
 * @author Gustavo Petri
 */
abstract class AbstractServerConnection {

	/**
	 * The local {@link java.io.InputStream} to interact with the client.
	 */
	protected DataInputStream in;

	/**
	 * The local {@link java.io.OutputStream} to interact with the client.
	 */
	protected DataOutputStream out;

	/**
	 * The server protocolHandler neeeded to implement the JRMP protocol.
	 */
	protected ServerProtocolHandler protocolHandler;

	/**
	 * The connection identifier.
	 */
	protected int connectionID;

	/**
	 * The connection identifier generator.
	 */
	protected static int connectionIDGenerator = 0;

	/**
	 * The underlying socket of this connection
	 */
	protected Socket sock;

	/**
	 * The table for storing the {@link java.rmi.server.ObjID} and a counter.
	 */
	protected static Map<ObjID, Integer> executingCounters;

	/**
	 * The table for storing the {@link java.rmi.server.UID} and the result,
	 * when the message sent to the client in response includes a remote object,
	 * and a dgcAck message is necessary.
	 */
	protected static Map<UID, Pair<Long, Object>> dgcAckWaitingMap;

	/**
	 * Time for next cleanup check of the table dgcAckWaitingMap
	 */
	protected static long dgcAckMapNextCleanup;

	/**
	 * The min time for dgcAck strong reference maintainance of the result, if
	 * the corresponing dgcAck has never arrived.
	 */
	protected static final long dgcAckMapTimeOut = 30000;

	static {
		executingCounters = new Hashtable<ObjID, Integer>();
		dgcAckWaitingMap = new Hashtable<UID, Pair<Long, Object>>();
		dgcAckMapNextCleanup = System.currentTimeMillis() + dgcAckMapTimeOut;
	}

	/**
	 * Creates a new connection to the specified
	 * {@link org.apache.harmony.rmi.internal.transport.EndpointID}
	 * 
	 * @param in
	 *            the specified {@link java.io.InputStream}
	 * @param out
	 *            the specified {@link java.io.OutputStream}
	 * @param clientEP
	 *            the specified {@link org.apache.harmony.rmi.internal.transport.EndpointID}
	 * @param sock
	 *            the socket of connection
	 */
	public AbstractServerConnection(InputStream in, OutputStream out,
			EndpointID clientEP, Socket sock) {
		this.connectionID = ++connectionIDGenerator;
		this.sock = sock;
		this.out = new DataOutputStream(new BufferedOutputStream(out));
		this.in = new DataInputStream(new BufferedInputStream(in));
		this.protocolHandler = new ServerProtocolHandler(this.in, this.out,
				clientEP);
		// this.clientEP = clientEP;
	}

	/**
	 * Unregisters the thread currently executing a remote call into the
	 * Transport Manager, noting that the client has finished executing the
	 * remote method on it.
	 * 
	 * @param objID
	 *            the Object identifier in which the client is currently
	 *            executing a method.
	 */
	private final void unregisterThread(ObjID objID) {
		Map<ObjID, Set<Integer>> executingThreads = TransportManager
				.getTransportManager().getExecutingThreads();

		if (objID != null) {
			synchronized (executingThreads) {
				if (executingThreads.get(objID).size() <= 1) {
					executingThreads.remove(objID);
				} else {
					executingThreads.get(objID).remove(
							Thread.currentThread().hashCode());
				}
				if (executingCounters.get(objID) == 1) {
					executingCounters.remove(objID);
				} else {
					executingCounters.put(objID,
							executingCounters.get(objID) - 1);
				}
			}
		}
	}

	/**
	 * Registers the thread currently executing a remote call into the Transport
	 * Manager, in order to know if a client is executing a method, to avoid
	 * unexporting the object in which the method is being executed, unless the
	 * <code>force</code> parameter is specified.
	 * 
	 * @param objID
	 *            the Object identifier in which the client is currently
	 *            executing a method.
	 */
	private final void registerThread(ObjID objID) {
		Map<ObjID, Set<Integer>> executingThreads = TransportManager
				.getTransportManager().getExecutingThreads();

		synchronized (executingThreads) {
			if (executingThreads.containsKey(objID)) {
				executingThreads.get(objID).add(Thread.currentThread().hashCode());
			} else {
				Set<Integer> threadSet = new HashSet<Integer>();
				threadSet.add(Thread.currentThread().hashCode());
				executingThreads.put(objID, threadSet);
			}
		}
		/*
         * XXX - Compilance 1.4.2 
         * Here must use the putIfAbsent method if executingCounters is a 
         * ConcurrentHashMap (i.e.: executingCounters.putIfAbsent(objID, 0))  
		 */
        executingCounters.put(objID, 0);
        
		executingCounters.put(objID, executingCounters.get(objID) + 1);
	}

	/**
	 * Returns the ID of this connection.
	 * 
	 * @return the ID of this connection
	 */
	public final int getConnectionID() {
		return connectionID;
	}

	/**
	 * Establishes a connection.
	 * 
	 * @throws ProtocolException
	 *             if there is an error in the underlying protocol
	 */
	public abstract void establishConnection() throws ProtocolException;

	/**
	 * Handles the incoming message.
	 * 
	 * @throws Exception If an exception occurs during message handling.
	 */
	public abstract void serve() throws Exception;


	/**
	 * Returns a <code>true</code> value if the {@link java.rmi.server.ObjID ObjID}
	 * exists in the table {@link #executingCounters}, <code>false</code> if
	 * not.
	 * 
	 * @param objID
	 *            the specified {@link java.rmi.server.ObjID}
	 * @return a boolean value
	 */
	public final static boolean isExcecuting(ObjID objID) {
		if (executingCounters.containsKey(objID)) {
			return true;
		}
		return false;
	}

	/**
	 * Closes the connection.
	 */
	public final void releaseConnection() {
		try {
			this.sock.shutdownOutput();
			this.sock.shutdownInput();
			this.sock.close();
		} catch (IOException e) {
			// FIXME REVIEW: What to do when an exception is thrown here. May be
			// a logger could be useful.
		} finally {
			this.sock = null;
		}
	}

	/**
	 * Registers the thread currently executing the remote method, executes the
	 * call via RemoteReferenceManager, unregisters the thread and returns the
	 * result of the invocation.
	 * 
	 * @param msg
	 *            the {@link Message} object containing the data for the
	 *            call.
	 * @return a {@link ReturnMessage} object containing the result of
	 *         the invocation.
	 */
	protected ReturnMessage executeMethod(Message msg) {
		ObjID objID;
		ReturnMessage ret;
		objID = msg.getObjID();
		registerThread(objID);
		RemoteReferenceManager refMgr = RemoteReferenceManager
				.getRemoteReferenceManager();
		try {
			boolean sendReturn = refMgr.sendReturnValue(objID, msg.getHash());
			Object result = refMgr.executeCall(objID, msg.getHash(), msg
					.getArguments());
			ret = new ReturnMessage(result, sendReturn);
		} catch (Exception e) {
			ret = new ReturnMessage(e);
		}
		unregisterThread(objID);
		return ret;
	}

	/**
	 * It does periodic cleanups in the {@link #dgcAckWaitingMap}, to
	 * free the memory that could be still allocated due to lost dgcAcks
	 */
	protected void doDgcAckWaitingMapCleanUp() {
		long time = System.currentTimeMillis();
		synchronized (dgcAckWaitingMap) {
			if (time > dgcAckMapNextCleanup) {
				Iterator<Map.Entry<UID, Pair<Long, Object>>> iter = dgcAckWaitingMap
						.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<UID, Pair<Long, Object>> mapEntry = iter.next();
					if (time > mapEntry.getValue().getFirst()) {
						iter.remove();
					}
				}
				dgcAckMapNextCleanup = time + dgcAckMapTimeOut;
			}
		}
	}

	/**
	 * Receives a {@link Message} object containing the method call
	 * request, executes the call, and writes the results to the client.
	 * 
	 * @param msg The {@link Message} object containing the call execution request.
     *  
	 * @throws MarshalException if an exception occurs when writing the result
	 * of the call.
	 */
	protected void handleCall(Message msg) throws MarshalException {
		UID dgcAck = null;
		ReturnMessage methodExecuted = executeMethod(msg);

		try {
			dgcAck = methodExecuted.write(out);
			out.flush();
		} catch (IOException e) {
			throw new MarshalException("Error writing method result", e);
		}
		if (dgcAck != null) {
			dgcAckWaitingMap.put(dgcAck, new Pair<Long, Object>(new Long(System
					.currentTimeMillis()
					+ dgcAckMapTimeOut), methodExecuted.getResult()));
		}
	}

	/**
	 * Removes the received DGCack message from the {@link #dgcAckWaitingMap}	 
     *  
	 * @param msg
	 *            the received message containing the DGCack
	 */
	protected void handleDGCAck(Message msg) {
		dgcAckWaitingMap.remove(msg.getUID());
		doDgcAckWaitingMapCleanUp();
	}
	
	/**
     * sends an exception to the client 
     * 
     * @param e 
     *          the exception to be sent
     */
    protected void handleException(Exception e) {
        try {
            new ReturnMessage(e).write(out);
            out.flush();
        } catch (IOException e1) {
            /*
             *  FIXME: May be logging would be useful.
             *  When a connection on the client is closed due to pool release
             *  the out.flush method will fail.
             */ 
        }
    }
}