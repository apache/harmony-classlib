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

package javax.net.ssl;

import java.security.Principal;
import java.security.cert.Certificate;
import javax.security.cert.X509Certificate;

public interface SSLSession {

    public int getApplicationBufferSize();

    public String getCipherSuite();

    public long getCreationTime();

    public byte[] getId();

    public long getLastAccessedTime();

    public Certificate[] getLocalCertificates();

    public Principal getLocalPrincipal();

    public int getPacketBufferSize();

    public X509Certificate[] getPeerCertificateChain() throws SSLPeerUnverifiedException;

    public Certificate[] getPeerCertificates() throws SSLPeerUnverifiedException;

    public String getPeerHost();

    public int getPeerPort();

    public Principal getPeerPrincipal() throws SSLPeerUnverifiedException;

    public String getProtocol();

    public SSLSessionContext getSessionContext();

    public Object getValue(String name);

    public String[] getValueNames();

    public void invalidate();

    public boolean isValid();

    public void putValue(String name, Object value);

    public void removeValue(String name);
}
