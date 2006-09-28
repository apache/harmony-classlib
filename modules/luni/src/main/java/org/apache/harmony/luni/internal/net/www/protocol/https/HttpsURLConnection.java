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

    @Override
    public String getCipherSuite() {
        if (sslSocket == null) {
            throw new IllegalStateException("Connection has not been established yet.");
        }
        return sslSocket.getSession().getCipherSuite();
    }

    @Override
    public Certificate[] getLocalCertificates() {
        if (sslSocket == null) {
            throw new IllegalStateException("Connection has not been established yet.");
        }
        return sslSocket.getSession().getLocalCertificates();
    }

    @Override
    public Certificate[] getServerCertificates() throws SSLPeerUnverifiedException {
        if (sslSocket == null) {
            throw new IllegalStateException("Connection has not been established yet.");
        }
        return sslSocket.getSession().getPeerCertificates();
    }

    @Override
    public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
        if (sslSocket == null) {
            throw new IllegalStateException("Connection has not been established yet.");
        }
        return sslSocket.getSession().getPeerPrincipal();
    }

    @Override
    public Principal getLocalPrincipal() {
        if (sslSocket == null) {
            throw new IllegalStateException("Connection has not been established yet.");
        }
        return sslSocket.getSession().getLocalPrincipal();
    }

    @Override
    public void disconnect() {
        httpsEngine.disconnect();
    }

    @Override
    public InputStream getErrorStream() {
        return httpsEngine.getErrorStream();
    }

    @Override
    public String getRequestMethod() {
        return httpsEngine.getRequestMethod();
    }

    @Override
    public int getResponseCode() throws IOException {
        return httpsEngine.getResponseCode();
    }

    @Override
    public String getResponseMessage() throws IOException {
        return httpsEngine.getResponseMessage();
    }

    @Override
    public void setRequestMethod(String method) throws ProtocolException {
        httpsEngine.setRequestMethod(method);
    }

    @Override
    public boolean usingProxy() {
        return httpsEngine.usingProxy();
    }

    @Override
    public boolean getInstanceFollowRedirects() {
        return httpsEngine.getInstanceFollowRedirects();
    }

    @Override
    public void setInstanceFollowRedirects(boolean followRedirects) {
        httpsEngine.setInstanceFollowRedirects(followRedirects);
    }

    @Override
    public void connect() throws IOException {
        httpsEngine.connect();
    };

    @Override
    public boolean getAllowUserInteraction() {
        return httpsEngine.getAllowUserInteraction();
    }

    @Override
    public Object getContent() throws IOException {
        return httpsEngine.getContent();
    }

    @Override
    public Object getContent(Class[] types) throws IOException {
        return httpsEngine.getContent(types);
    }

    @Override
    public String getContentEncoding() {
        return httpsEngine.getContentEncoding();
    }

    @Override
    public int getContentLength() {
        return httpsEngine.getContentLength();
    }

    @Override
    public String getContentType() {
        return httpsEngine.getContentType();
    }

    @Override
    public long getDate() {
        return httpsEngine.getDate();
    }

    @Override
    public boolean getDefaultUseCaches() {
        return httpsEngine.getDefaultUseCaches();
    }

    @Override
    public boolean getDoInput() {
        return httpsEngine.getDoInput();
    }

    @Override
    public boolean getDoOutput() {
        return httpsEngine.getDoOutput();
    }

    @Override
    public long getExpiration() {
        return httpsEngine.getExpiration();
    }

    @Override
    public String getHeaderField(int pos) {
        return httpsEngine.getHeaderField(pos);
    }

    @Override
    public Map<String, List<String>> getHeaderFields() {
        return httpsEngine.getHeaderFields();
    }

    @Override
    public Map<String, List<String>> getRequestProperties() {
        return httpsEngine.getRequestProperties();
    }

    @Override
    public void addRequestProperty(String field, String newValue) {
        httpsEngine.addRequestProperty(field, newValue);
    }

    @Override
    public String getHeaderField(String key) {
        return httpsEngine.getHeaderField(key);
    }

    @Override
    public long getHeaderFieldDate(String field, long defaultValue) {
        return httpsEngine.getHeaderFieldDate(field, defaultValue);
    }

    @Override
    public int getHeaderFieldInt(String field, int defaultValue) {
        return httpsEngine.getHeaderFieldInt(field, defaultValue);
    }

    @Override
    public String getHeaderFieldKey(int posn) {
        return httpsEngine.getHeaderFieldKey(posn);
    }

    @Override
    public long getIfModifiedSince() {
        return httpsEngine.getIfModifiedSince();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return httpsEngine.getInputStream();
    }

    @Override
    public long getLastModified() {
        return httpsEngine.getLastModified();
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return httpsEngine.getOutputStream();
    }

    @Override
    public Permission getPermission() throws IOException {
        return httpsEngine.getPermission();
    }

    @Override
    public String getRequestProperty(String field) {
        return httpsEngine.getRequestProperty(field);
    }

    @Override
    public URL getURL() {
        return httpsEngine.getURL();
    }

    @Override
    public boolean getUseCaches() {
        return httpsEngine.getUseCaches();
    }

    @Override
    public void setAllowUserInteraction(boolean newValue) {
        httpsEngine.setAllowUserInteraction(newValue);
    }

    @Override
    public void setDefaultUseCaches(boolean newValue) {
        httpsEngine.setDefaultUseCaches(newValue);
    }

    @Override
    public void setDoInput(boolean newValue) {
        httpsEngine.setDoInput(newValue);
    }

    @Override
    public void setDoOutput(boolean newValue) {
        httpsEngine.setDoOutput(newValue);
    }

    @Override
    public void setIfModifiedSince(long newValue) {
        httpsEngine.setIfModifiedSince(newValue);
    }

    @Override
    public void setRequestProperty(String field, String newValue) {
        httpsEngine.setRequestProperty(field, newValue);
    }

    @Override
    public void setUseCaches(boolean newValue) {
        httpsEngine.setUseCaches(newValue);
    }

    @Override
    public void setConnectTimeout(int timeout) {
        httpsEngine.setConnectTimeout(timeout);
    }

    @Override
    public int getConnectTimeout() {
        return httpsEngine.getConnectTimeout();
    }

    @Override
    public void setReadTimeout(int timeout) {
        httpsEngine.setReadTimeout(timeout);
    }

    @Override
    public int getReadTimeout() {
        return httpsEngine.getReadTimeout();
    }

    @Override
    public String toString() {
        return httpsEngine.toString();
    }

    /**
     * HttpsEngine
     */
    private class HttpsEngine extends HttpURLConnection {

        protected HttpsEngine(URL url, int port) {
            super(url, port);
        }

        protected HttpsEngine(URL url, int port, Proxy proxy) {
            super(url, port, proxy);
        }

        @Override
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
            sslSocket = (SSLSocket) getSSLSocketFactory().createSocket(socket, hostname,
                    url.getPort(), true);
            sslSocket.setUseClientMode(true);
            sslSocket.startHandshake();
            if (!getHostnameVerifier().verify(hostname, sslSocket.getSession())) {
                throw new IOException("Hostname <" + hostname + "> was not verified.");
            }
            return sslSocket;
        }
    }
}
