/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
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
* @author Boris V. Kuznetsov
* @version $Revision$
*/

package javax.net.ssl;

import java.io.IOException;
import java.security.cert.Certificate;
import java.net.URL;

import org.apache.harmony.security.test.PerformanceTest;


/**
 * Tests for <code>HttpsURLConnection</code> class constructors and methods.
 * 
 */
public class HttpsURLConnectionTest extends PerformanceTest {

    public final void testGetPeerPrincipal() {
        HttpsURLConnection con = new MyHttpsURLConnection(null);
        try {
            con.getPeerPrincipal();
            fail("No expected SSLPeerUnverifiedException");
        } catch (SSLPeerUnverifiedException e) {
        }
    }

    public final void testGetLocalPrincipal() {
        HttpsURLConnection con = new MyHttpsURLConnection(null);
        if (con.getLocalPrincipal() != null) {
            fail("Non null result");
        }
    }

    public final void testGetDefaultHostnameVerifier() {
        HostnameVerifier ver = HttpsURLConnection.getDefaultHostnameVerifier();
        if (!(ver instanceof DefaultHostnameVerifier)) {
            fail("Incorrect instance");
        }
        if (ver.verify("localhost", null)) {
            fail("connection should not be permitted");
        }
    }

    public final void testGetHostnameVerifier() {
        HttpsURLConnection con = new MyHttpsURLConnection(null);
        HostnameVerifier ver = con.getHostnameVerifier();
        if (!(ver instanceof DefaultHostnameVerifier)) {
            fail("Incorrect instance");
        }
        if (ver.verify("localhost", null)) {
            fail("connection should not be permitted");
        }
    }

    public final void testSetDefaultHostnameVerifier() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(null);
            fail("No expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }

    public final void testSetHostnameVerifier() {
        HttpsURLConnection con = new MyHttpsURLConnection(null);
        try {
            con.setHostnameVerifier(null);
            fail("No expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }

    public final void testGetDefaultSSLSocketFactory() {
        SSLSocketFactory sf = HttpsURLConnection.getDefaultSSLSocketFactory();
        if (!sf.equals((SSLSocketFactory) SSLSocketFactory.getDefault())) {
            fail("incorrect DefaultSSLSocketFactory");
        }
    }

    public final void testGetSSLSocketFactory() {
        HttpsURLConnection con = new MyHttpsURLConnection(null);
        SSLSocketFactory sf = con.getSSLSocketFactory();
        if (!sf.equals((SSLSocketFactory) SSLSocketFactory.getDefault())) {
            fail("incorrect DefaultSSLSocketFactory");
        }
    }

    public final void testSetDefaultSSLSocketFactory() {
        try {
            HttpsURLConnection.setDefaultSSLSocketFactory(null);
            fail("No expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }

    public final void testSetSSLSocketFactory() {
        HttpsURLConnection con = new MyHttpsURLConnection(null);
        try {
            con.setSSLSocketFactory(null);
            fail("No expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
}

class MyHttpsURLConnection extends HttpsURLConnection {

    public MyHttpsURLConnection(URL url) {
        super(url);
    }

    /*
     * @see javax.net.ssl.HttpsURLConnection#getCipherSuite()
     */
    public String getCipherSuite() {
        return null;
    }

    /*
     * @see javax.net.ssl.HttpsURLConnection#getLocalCertificates()
     */
    public Certificate[] getLocalCertificates() {
        return null;
    }

    /*
     * @see javax.net.ssl.HttpsURLConnection#getServerCertificates()
     */
    public Certificate[] getServerCertificates()
            throws SSLPeerUnverifiedException {
        return null;
    }

    /*
     * @see java.net.HttpURLConnection#disconnect()
     */
    public void disconnect() {
    }

    /*
     * @see java.net.HttpURLConnection#usingProxy()
     */
    public boolean usingProxy() {
        return false;
    }

    /*
     * @see java.net.URLConnection#connect()
     */
    public void connect() throws IOException {
    }

}

