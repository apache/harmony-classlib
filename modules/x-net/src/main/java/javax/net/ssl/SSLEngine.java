/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
* @author Vera Y. Petrashkova
* @version $Revision$
*/

package javax.net.ssl;

import java.nio.ByteBuffer;

/**
 * An engine for securing communication via SSL, TLS and other protocols.
 * 
 * @since 1.5
 */
public abstract class SSLEngine {
    private final String peerHost;
    private final int peerPort;

    /**
     * Constructs an instance without any peer host information.
     */
    protected SSLEngine() {
        super();
        peerHost = null;
        peerPort = -1;
    }

    /**
     * Constructs an instance with the given peer host and port.
     * 
     * @param host name of the peer host
     * @param port value of the port of the peer host 
     */
    protected SSLEngine(String host, int port) {
        super();
        this.peerHost = host;
        this.peerPort = port;
    }

    public String getPeerHost() {
        return peerHost;
    }

    public int getPeerPort() {
        return peerPort;
    }
    
    public abstract void beginHandshake() throws SSLException;

    public abstract void closeInbound() throws SSLException;

    public abstract void closeOutbound();

    public abstract Runnable getDelegatedTask();

    public abstract String[] getEnabledCipherSuites();

    public abstract String[] getEnabledProtocols();

    public abstract boolean getEnableSessionCreation();

    public abstract SSLEngineResult.HandshakeStatus getHandshakeStatus();

    public abstract boolean getNeedClientAuth();

    public abstract SSLSession getSession();

    public abstract String[] getSupportedCipherSuites();

    public abstract String[] getSupportedProtocols();

    public abstract boolean getUseClientMode();

    public abstract boolean getWantClientAuth();

    public abstract boolean isInboundDone();

    public abstract boolean isOutboundDone();

    public abstract void setEnabledCipherSuites(String[] suites);

    public abstract void setEnabledProtocols(String[] protocols);

    public abstract void setEnableSessionCreation(boolean flag);

    public abstract void setNeedClientAuth(boolean need);

    public abstract void setUseClientMode(boolean mode);

    public abstract void setWantClientAuth(boolean want);

    public abstract SSLEngineResult unwrap(ByteBuffer src, ByteBuffer[] dsts,
            int offset, int length) throws SSLException;

    public abstract SSLEngineResult wrap(ByteBuffer[] srcs, int offset,
            int length, ByteBuffer dst) throws SSLException;

    /**
     * implementation behavior follows RI:
     * jdk 1.5 does not throw IllegalArgumentException when parameters are null
     * and does not throw ReadOnlyBufferException if dst is read only byte buffer
     *  
     */
    public SSLEngineResult unwrap(ByteBuffer src, ByteBuffer dst)
            throws SSLException {
//        if (src == null) {
//            throw new IllegalArgumentException("Byte buffer src is null");
//        }
//        if (dst == null) {
//            throw new IllegalArgumentException("Byte buffer dst is null");
//        }
//        if (dst.isReadOnly()) {
//            throw new ReadOnlyBufferException();
//        }
        return unwrap(src, new ByteBuffer[] { dst }, 0, 1);
    }

    /**
     * implementation behavior follows RI:
     * jdk 1.5 does not throw IllegalArgumentException when src is null or if
     * dsts contains null elements
     * It does not throw ReadOnlyBufferException when dsts contains read only elements
     */
    public SSLEngineResult unwrap(ByteBuffer src, ByteBuffer[] dsts)
            throws SSLException {
//        if (src == null) {
//            throw new IllegalArgumentException("Byte buffer src is null");
//        }
        if (dsts == null) {
            throw new IllegalArgumentException("Byte buffer array dsts is null");
        }
//        for (int i = 0; i < dsts.length; i++) {
//            if (dsts[i] == null) {
//                throw new IllegalArgumentException("Byte buffer dsts[" + i
//                        + "]  is null");
//            }
//            if (dsts[i].isReadOnly()) {
//                throw new ReadOnlyBufferException();
//            }
//        }
        return unwrap(src, dsts, 0, dsts.length);
    }

    /**
     * implementation behavior follows RI: jdk 1.5 does not throw
     * IllegalArgumentException when dst is null or if srcs contains null
     * elements It does not throw ReadOnlyBufferException for read only dst
     *  
     */
    public SSLEngineResult wrap(ByteBuffer[] srcs, ByteBuffer dst)
            throws SSLException {
        if (srcs == null) {
            throw new IllegalArgumentException("Byte buffer array srcs is null");
        }
//        for (int i = 0; i < srcs.length; i++) {
//            if (srcs[i] == null) {
//                throw new IllegalArgumentException("Byte buffer srcs[" + i
//                        + "]  is null");
//            }
//        }
//        if (dst == null) {
//            throw new IllegalArgumentException("Byte buffer array dst is null");
//        }
//        if (dst.isReadOnly()) {
//            throw new ReadOnlyBufferException();
//        }
        return wrap(srcs, 0, srcs.length, dst);
    }

    /**
     * implementation behavior follows RI:
     * jdk 1.5 does not throw IllegalArgumentException when parameters are null
     * and does not throw ReadOnlyBufferException if dst is read only byte buffer
     *  
     */
    public SSLEngineResult wrap(ByteBuffer src, ByteBuffer dst)
            throws SSLException {
//        if (src == null) {
//            throw new IllegalArgumentException("Byte buffer src is null");
//        }
//        if (dst == null) {
//            throw new IllegalArgumentException("Byte buffer dst is null");
//        }
//        if (dst.isReadOnly()) {
//            throw new ReadOnlyBufferException();
//        }
        return wrap(new ByteBuffer[] { src }, 0, 1, dst);
    }
}