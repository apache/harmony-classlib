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

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.SecureRandom;

import javax.net.SocketFactory;

/**
 * @com.intel.drl.spec_ref
 * 
 */

public abstract class SSLContextSpi {
    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public SSLContextSpi() {
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected abstract void engineInit(KeyManager[] km, TrustManager[] tm,
            SecureRandom sr) throws KeyManagementException;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected abstract SSLSocketFactory engineGetSocketFactory();

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected abstract SSLServerSocketFactory engineGetServerSocketFactory();

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected abstract SSLEngine engineCreateSSLEngine(String host, int port);

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected abstract SSLEngine engineCreateSSLEngine();

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected abstract SSLSessionContext engineGetServerSessionContext();

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected abstract SSLSessionContext engineGetClientSessionContext();

    protected SSLParameters engineGetDefaultSSLParameters() {
        // Initially, a default set of cipher suites will be enabled on a new
        // socket that represents the minimum suggested configuration
        SSLParameters defaultSSLParameters = new SSLParameters();
        SocketFactory sslSocketFactory = SSLSocketFactory.getDefault();
        SSLSocket sslSocket = null;
        try {
            sslSocket = (SSLSocket) sslSocketFactory.createSocket();
            if (sslSocket == null)
                return null;
            defaultSSLParameters.setCipherSuites(sslSocket
                    .getEnabledCipherSuites());
            defaultSSLParameters.setProtocols(sslSocket.getEnabledProtocols());
        } catch (IOException e1) {
            e1.printStackTrace();
            throw new UnsupportedOperationException(
                    "the default SSL parameters could not be obtained");
        } finally {
            try {
                if (sslSocket != null) {
                    sslSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return defaultSSLParameters;
    };

    protected SSLParameters engineGetSupportedSSLParameters() {
        SSLParameters supportSSLParameters = new SSLParameters();
        SocketFactory sslSocketFactory = SSLSocketFactory.getDefault();
        if (sslSocketFactory == null)
            return null;
        SSLSocket sslSocket = null;
        try {
            sslSocket = (SSLSocket) sslSocketFactory.createSocket();
            if (sslSocket == null)
                return null;
            supportSSLParameters.setCipherSuites(sslSocket
                    .getSupportedCipherSuites());
            supportSSLParameters
                    .setProtocols(sslSocket.getSupportedProtocols());
        } catch (IOException e1) {
            e1.printStackTrace();
            throw new UnsupportedOperationException(
                    "the supported SSL parameters could not be obtained");
        } finally {
            try {
                if (sslSocket != null) {
                    sslSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return supportSSLParameters;
    };
}