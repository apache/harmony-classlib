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
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.ExportException;
import java.rmi.server.RMIFailureHandler;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.RMISocketFactory;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import ar.org.fitc.rmi.utils.Pair;
import ar.org.fitc.rmi.utils.PropertiesReader;

/**
 * Implements a {@link ThreadPoolExecutor} that executes each submitted task using one
 * of possibly several pooled threads. The {@link ThreadPoolExecutor} uses a
 * {@link BlockingQueue} to transfer and hold submitted tasks.
 * <p>
 * <li>The pool size is configured through the property <EM>ar.org.fitc.rmi.server.threadPoolCoreSize</EM>,
 * the default value is <EM>10</EM>.
 * <li>The maximum pool size is configured through the property <EM>ar.org.fitc.rmi.server.threadPoolMaxSize</EM>,
 * the default value is <EM>Integer.MAX_VALUE</EM>.
 * <li>The keep alive time of the thread waiting in the pool is configured
 * through the property <EM>ar.org.fitc.rmi.server.threadPoolKeepAliveTime</EM> ,
 * the default value is <EM>60000</EM> in milliseconds.
 * <li>Finally, the capacity of the queue is configured through the property
 * <EM>ar.org.fitc.rmi.server.threadPoolQueueSize</EM>, the default value is
 * <EM>0</EM>.
 * 
 * <p>
 * This class has an internal class:
 * {@link ar.org.fitc.rmi.transport.MultiThreadedServer.ServerRequestTask}
 * 
 * @author Marcelo Arcidiacono
 * @author Gustavo Petri
 */
final class MultiThreadedServer extends Thread {

    /**
     * The blocking queue which is used to transfer and hold submitted tasks.
     */
    private static BlockingQueue<Runnable> queue;

    /**
     * The thread pool that executes each submitted task.
     */
    private static ThreadPoolExecutor threadPool = null;

    static {
        Integer poolSize;
        Integer maxPoolSize;
        Long keepAliveTime;
        Integer queueCapacity;
        
        poolSize = PropertiesReader.readInt(
                "ar.org.fitc.rmi.server.threadPoolCoreSize", 10);

        maxPoolSize = PropertiesReader.readInt(
                "ar.org.fitc.rmi.server.threadPoolMaxSize", Integer.MAX_VALUE);
        
        keepAliveTime = PropertiesReader.readLong(
                "ar.org.fitc.rmi.server.threadPoolKeepAliveTime", 60000);        

        queueCapacity = PropertiesReader.readInt(
                "ar.org.fitc.rmi.server.threadPoolQueueSize", 0);

        queue = (queueCapacity == 0) ? new SynchronousQueue<Runnable>()
                : new ArrayBlockingQueue<Runnable>(queueCapacity);
        
        threadPool = new ThreadPoolExecutor(poolSize, maxPoolSize,
                keepAliveTime, TimeUnit.MILLISECONDS, queue,
                new SimpleThreadFactory());
        threadPool.prestartAllCoreThreads();
    }

    /**
     * Indicates if the serverSocket will not accept any incoming call, the
     * default value is <code>true</code>.
     */
    private volatile boolean notFinish;

    /**
     * The specified port number.
     */
    private int port;

    /**
     * The server socket that waits for requests.
     */
    private ServerSocket serverSocket;

    /**
     * Associated with a remote object to obtain the
     * {@link java.net.ServerSocket} which is used to accept incoming calls from
     * clients.
     */
    private RMIServerSocketFactory ssf;

    /**
     * Constructor for the {@link MultiThreadedServer}. In order to
     * guarantee serial access creates a synchronized (thread-safe) map backed
     * by the specified map.
     * 
     * @param ssf
     *            the specified {@link java.rmi.server.RMIServerSocketFactory}
     * @param port
     *            the specified number of port
     * @throws ExportException
     *             if the exportation is not successful
     */
    public MultiThreadedServer(RMIServerSocketFactory ssf, int port)
            throws ExportException {
        this.ssf = ssf;
        this.port = port;
        try {
            this.serverSocket = createServerSocket();
        } catch (IOException e) {
            throw new ExportException("Exception happened during exportation",
                    e);
        }
        this.setName("MultiThreadedServer" + serverSocket);
        this.notFinish = true;
    }

    /**
     * Executes a task on one of the several pooled threads.
     * 
     * @param client
     *            the client socket
     * @param clientEP
     *            the {@link ar.org.fitc.rmi.transport.EndpointID} associated to
     *            client
     * @throws IOException
     *             if the connection failed
     */
    private final void serverRequests(Socket client, EndpointID clientEP)
            throws IOException {
        AbstractServerConnection server = ServerConnectionFactory
                .getServerConnection(client.getInputStream(), 
                        client.getOutputStream(), clientEP, client);
        threadPool.execute(new ServerRequestTask(client, server));
    }

    /**
     * Implements the retrial of reconnections when a failure occurs in the 
     * {@link ServerSocket#accept()} method 
     * 
     * @see <a href="http://archives.java.sun.com/cgi-bin/wa?A2=ind9909&L=rmi-users&D=0&I=-3&P=22293">User Archives</a>
     * @param acceptException
     *            the exception which was thrown when the accept method failed
     */
    private final void handleServerSocketFailure(IOException acceptException) {
        RMIFailureHandler fh = RMISocketFactory.getFailureHandler();
        if (fh == null) {
            // There isn't any FailureHandler installed. Retry 10 times every 10
            // seconds.
            int retry = 0;
            while (retry < 10 && notFinish) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                }
                try {
                    serverSocket = createServerSocket();
                    break;
                } catch (IOException e) {
                    retry++;
                }
            }
            if (retry >= 10) {
                notFinish = false;
            }
        } else {
            // There is a FailureHandler installed. Ask to FailureHandler for
            // retries.
            boolean retry = fh.failure(acceptException);
            while (retry && notFinish) {
                try {
                    serverSocket = createServerSocket();
                    break;
                } catch (IOException createSocketException) {
                    retry = fh.failure(createSocketException);
                }
            }
            if (!retry) {
                notFinish = false;
            }
        }
    }

    /**
     * Creates a {@link java.net.ServerSocket}.
     * 
     * @return a {@link java.net.ServerSocket}
     * @throws IOException
     *             if the creation fails
     */
    private final ServerSocket createServerSocket() throws IOException {
        ServerSocket serverSocket;
        RMISocketFactory socketFactory;
        if (ssf == null) {
            socketFactory = RMISocketFactory.getSocketFactory();
            if (socketFactory == null) {
                socketFactory = RMISocketFactory.getDefaultSocketFactory();
            }
            serverSocket = socketFactory.createServerSocket(port);
        } else {
            serverSocket = ssf.createServerSocket(port);
        }
        return serverSocket;
    }

    /**
     * Returns the local port number of the {@link java.net.ServerSocket}.
     * 
     * @return the local port number of the {@link java.net.ServerSocket}
     */
    public final int getLocalPort() {
        return serverSocket.getLocalPort();
    }

    /**
     * Listens for a connection to be made to this socket and accepts it. The
     * method blocks until a connection is made.
     * 
     * @see <a
     *      href="http://archives.java.sun.com/cgi-bin/wa?A2=ind9909&L=rmi-users&D=0&I=-3&P=22293">User Archives</a>
     */
    @Override
    public void run() {
        Socket newClient;
        while (notFinish) {
            try {
                newClient = serverSocket.accept();
                // FIXME Make a private setSocketProperties method
                newClient.setTcpNoDelay(true);
                EndpointID clientEndpointID = new EndpointID(newClient
                        .getInetAddress().getHostAddress(), newClient.getPort());
                try {
                    serverRequests(newClient, clientEndpointID);
                } catch (IOException e) {
                    if (notFinish) {
                        /*
                         * Any recovering acction should be done here if needed
                         * be.
                         */
                    }
                }
            } catch (IOException acceptException) {
                if (notFinish) {
                    // serversocket.accept() has failed. Ask RMIFailureHandler
                    // for retries.
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                    }
                    handleServerSocketFailure(acceptException);
                }
            }
        }
    }

    /**
     * Close the {@link java.net.ServerSocket}.
     * 
     * @throws IOException
     *             if the {@link java.net.ServerSocket} cannot to close
     */
    public final void stopServing() throws IOException {
        this.serverSocket.close();
        this.notFinish = false;
    }

    /**
     * Defines the task submitted in the Thread Pool
     * 
     * @author Marcelo Arcidiacono
     * @author Gustavo Petri
     */
    private final class ServerRequestTask implements Runnable {

        /**
         * The client socket
         */
        private Socket client;

        /**
         * The server connection.SO_REUSEADDR
         */
        private AbstractServerConnection server;

        /**
         * Constructor for the {@link ServerRequestTask}
         * 
         * @param client
         *            the especified client socket
         * @param server
         *            the especified server connection
         */
        public ServerRequestTask(Socket client, AbstractServerConnection server) {
            this.client = client;
            this.server = server;
        }

        /**
         * Serves a connection
         */
        public final void run() {
            Map<Long, Pair<String, Integer>> clientConnectionMap = 
                TransportManager.getTransportManager().getClientConnectionMap();
            clientConnectionMap.put(Thread.currentThread().getId(),
                    new Pair<String, Integer>(client.getInetAddress()
                            .getHostAddress(), new Integer(server.getConnectionID())));
            try {
                server.establishConnection();
                while (true) {
                    server.serve();
                }
            } catch (Exception e) {
                server.releaseConnection();
                server = null;
                // FIXME REVIEW: May be some logging would be useful here.
                // Also any recovery action should be called here.
            }
        }
    }

}

/**
 * A factory of a simple thread
 * 
 * @author Marcelo Arcidiacono
 * @author Gustavo Petri
 */
final class SimpleThreadFactory implements ThreadFactory {
  
    public final Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    }
}