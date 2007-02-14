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

package javax.naming.ldap;

import java.io.IOException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

/**
 * 
 * @ar.org.fitc.spec_ref
 * 
 * @version 0.0.1
 * 
 * @author Osvaldo C. Demo
 * 
 */
public abstract class StartTlsResponse implements ExtendedResponse {

    public static final String OID = "1.3.6.1.4.1.1466.20037";

    /**
     * @ar.org.fitc.spec_ref
     */
    protected StartTlsResponse() {

    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public byte[] getEncodedValue() {
        return null;
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public abstract SSLSession negotiate() throws IOException;

    /**
     * @ar.org.fitc.spec_ref
     */
    public abstract SSLSession negotiate(SSLSocketFactory factory)
            throws IOException;

    /**
     * @ar.org.fitc.spec_ref
     */
    public abstract void setEnabledCipherSuites(String[] suites);

    /**
     * @ar.org.fitc.spec_ref
     */
    public abstract void setHostnameVerifier(HostnameVerifier verifier);

    /**
     * @ar.org.fitc.spec_ref
     */
    public abstract void close() throws IOException;

    /**
     * @ar.org.fitc.spec_ref
     */
    public String getID() {
        return OID;
    }
}
