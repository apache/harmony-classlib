/* Copyright 1998, 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.luni.internal.net.www.protocol.https;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import java.net.URL;
import java.security.Permission;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;

import org.apache.harmony.luni.internal.net.www.protocol.http.HttpURLConnection;

/**
 * HttpsURLConnection implementation.
 */
public class HttpsURLConnection extends javax.net.ssl.HttpsURLConnection {

    // Https engine to be wrapped
    private final HttpsEngine httpsEngine;
    // SSLSocket to be used for connection
    private SSLSocket sslSocket;

    protected HttpsURLConnection(URL url, int port) {
        super(url);
        httpsEngine = new HttpsEngine(url, port);
    }

    protected HttpsURLConnection(URL url, int port, Proxy proxy) {
        super(url);
        httpsEngine = new HttpsEngine(url, port, proxy);
    }

    // ---------------------------------------------------------------------
    // ----------- HttpsURLConnection methods implementation ---------------
    // ---------------------------------------------------------------------

    public String getCipherSuite() {
        if (sslSocket == null) {
            throw new IllegalStateException(
                    "Connection has not been established yet.");
        }
        return sslSocket.getSession().getCipherSuite();
    }

    public Certificate[] getLocalCertificates() {
        if (sslSocket == null) {
            throw new IllegalStateException(
                    "Connection has not been established yet.");
        }
        return sslSocket.getSession().getLocalCertificates();
    }

    public Certificate[] getServerCertificates()
                                         throws SSLPeerUnverifiedException {
        if (sslSocket == null) {
            throw new IllegalStateException(
                    "Connection has not been established yet.");
        }
        return sslSocket.getSession().getPeerCertificates();
    }

    public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
        if (sslSocket == null) {
            throw new IllegalStateException(
                    "Connection has not been established yet.");
        }
        return sslSocket.getSession().getPeerPrincipal();
    }

    public Principal getLocalPrincipal() {
        if (sslSocket == null) {
            throw new IllegalStateException(
                    "Connection has not been established yet.");
        }
        return sslSocket.getSession().getLocalPrincipal();
    }

    // ---------------------------------------------------------------------
    // ------------ HttpURLConnection methods implementation ---------------
    // ---------------------------------------------------------------------

    public void disconnect() {
        httpsEngine.disconnect();
    }

    public InputStream getErrorStream() {
        return httpsEngine.getErrorStream();
    }

    public String getRequestMethod() {
        return httpsEngine.getRequestMethod();
    }

    public int getResponseCode() throws IOException {
        return httpsEngine.getResponseCode();
    }

    public String getResponseMessage() throws IOException {
        return httpsEngine.getResponseMessage();
    }

    public void setRequestMethod(String method) throws ProtocolException {
        httpsEngine.setRequestMethod(method);
    }

    public boolean usingProxy() {
        return httpsEngine.usingProxy();
    }

    public boolean getInstanceFollowRedirects() {
        return httpsEngine.getInstanceFollowRedirects();
    }

    public void setInstanceFollowRedirects(boolean followRedirects) {
        httpsEngine.setInstanceFollowRedirects(followRedirects);
    }

    // ---------------------------------------------------------------------
    // --------------- URLConnection methods implementation ----------------
    // ---------------------------------------------------------------------

    public void connect() throws IOException {
        httpsEngine.connect();
    };

    public boolean getAllowUserInteraction() {
        return httpsEngine.getAllowUserInteraction();
    }

    public Object getContent() throws IOException {
        return httpsEngine.getContent();
    }

    public Object getContent(Class[] types) throws IOException {
        return httpsEngine.getContent(types);
    }

    public String getContentEncoding() {
        return httpsEngine.getContentEncoding();
    }

    public int getContentLength() {
        return httpsEngine.getContentLength();
    }

    public String getContentType() {
        return httpsEngine.getContentType();
    }

    public long getDate() {
        return httpsEngine.getDate();
    }

    public boolean getDefaultUseCaches() {
        return httpsEngine.getDefaultUseCaches();
    }

    public boolean getDoInput() {
        return httpsEngine.getDoInput();
    }

    public boolean getDoOutput() {
        return httpsEngine.getDoOutput();
    }

    public long getExpiration() {
        return httpsEngine.getExpiration();
    }

    public String getHeaderField(int pos) {
        return httpsEngine.getHeaderField(pos);
    }

    public Map<String, List<String>> getHeaderFields() {
        return httpsEngine.getHeaderFields();
    }

    public Map<String, List<String>> getRequestProperties() {
        return httpsEngine.getRequestProperties();
    }

    public void addRequestProperty(String field, String newValue) {
        httpsEngine.addRequestProperty(field, newValue);
    }

    public String getHeaderField(String key) {
        return httpsEngine.getHeaderField(key);
    }

    public long getHeaderFieldDate(String field, long defaultValue) {
        return httpsEngine.getHeaderFieldDate(field, defaultValue);
    }

    public int getHeaderFieldInt(String field, int defaultValue) {
        return httpsEngine.getHeaderFieldInt(field, defaultValue);
    }

    public String getHeaderFieldKey(int posn) {
        return httpsEngine.getHeaderFieldKey(posn);
    }

    public long getIfModifiedSince() {
        return httpsEngine.getIfModifiedSince();
    }

    public InputStream getInputStream() throws IOException {
        return httpsEngine.getInputStream();
    }

    public long getLastModified() {
        return httpsEngine.getLastModified();
    }

    public OutputStream getOutputStream() throws IOException {
        return httpsEngine.getOutputStream();
    }

    public Permission getPermission() throws IOException {
        return httpsEngine.getPermission();
    }

    public String getRequestProperty(String field) {
        return httpsEngine.getRequestProperty(field);
    }

    public URL getURL() {
        return httpsEngine.getURL();
    }

    public boolean getUseCaches() {
        return httpsEngine.getUseCaches();
    }

    public void setAllowUserInteraction(boolean newValue) {
        httpsEngine.setAllowUserInteraction(newValue);
    }

    public void setDefaultUseCaches(boolean newValue) {
        httpsEngine.setDefaultUseCaches(newValue);
    }

    public void setDoInput(boolean newValue) {
        httpsEngine.setDoInput(newValue);
    }

    public void setDoOutput(boolean newValue) {
        httpsEngine.setDoOutput(newValue);
    }

    public void setIfModifiedSince(long newValue) {
        httpsEngine.setIfModifiedSince(newValue);
    }

    public void setRequestProperty(String field, String newValue) {
        httpsEngine.setRequestProperty(field, newValue);

    }

    public void setUseCaches(boolean newValue) {
        httpsEngine.setUseCaches(newValue);
    }

    public void setConnectTimeout(int timeout) {
        httpsEngine.setConnectTimeout(timeout);
    }

    public int getConnectTimeout() {
        return httpsEngine.getConnectTimeout();
    }

    public void setReadTimeout(int timeout) {
        httpsEngine.setReadTimeout(timeout);
    }

    public int getReadTimeout() {
        return httpsEngine.getReadTimeout();
    }

    public String toString() {
        return httpsEngine.toString();
    }

    // ---------------------------------------------------------------------
    // ---------------------------------------------------------------------
    // ---------------------------------------------------------------------

    /*
     * HttpsEngine
     */
    private class HttpsEngine extends HttpURLConnection {

        protected HttpsEngine(URL url, int port) {
            super(url, port);
        }

        protected HttpsEngine(URL url, int port, Proxy proxy) {
            super(url, port, proxy);
        }

        public void connect() throws IOException {
            if (connected) {
                return;
            }
            super.connect();
            // TODO make SSL Tunnel in case of using the proxy
            setUpTransportIO(wrapConnection(socket));
        }

        /**
         * Create the secure socket over the connected socket and
         * verify remote hostname.
         */
        private Socket wrapConnection(Socket socket) throws IOException {
            String hostname = url.getHost();
            // create the wrapper over connected socket
            sslSocket = (SSLSocket) getSSLSocketFactory().createSocket(
                    socket, hostname, url.getPort(), true);
            sslSocket.setUseClientMode(true);
            sslSocket.startHandshake();
            if (!getHostnameVerifier().verify(hostname,
                    sslSocket.getSession())) {
                throw new IOException("Hostname <"
                        + hostname + "> was not verified.");
            }
            return sslSocket;
        }
    }
}

