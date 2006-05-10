/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
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
package java.net;

import java.security.Principal;
import java.security.cert.Certificate;
import java.util.List;

import javax.net.ssl.SSLPeerUnverifiedException;

/**
 * A secure cache response, which is orignally retrieved through secure ways. 
 */
public abstract class SecureCacheResponse extends CacheResponse {
	/**
	 * Constructor method
	 */
	public SecureCacheResponse() {
		super();
	}

	/**
	 * <p>
	 * Gets the cipher suite string on the connection 
	 * which is originally used to retrieve the network resource.
	 * </p>
	 * @return the cipher suite string
	 */
	public abstract String getCipherSuite();

	/**
	 * <p>
	 * Gets local certificate chain. When the original connection retrieved the resource data,
	 * certificate chain was sent to the server during handshaking process. This method only 
	 * takes effect when certificate-based cipher suite is enabled.
	 * </p> 
	 * @return the certificate chain that was sent to the server. The certificate chain is represented 
	 * as a <code>List</code> of <code>Certificate</code>. If no certificate chain was sent, 
	 * the method returns null.
	 */
	public abstract List<Certificate> getLocalCertificateChain();

	/**
	 * <p>
	 * Gets server's certificate chain from cache. As part of defining the session,
	 * the certificate chain was established when the original connection retrieved network resource.
	 * This method can only be invoked when certificated-based cypher suites is enable. 
	 * Otherwise, it throws an <code>SSLPeerUnverifiedException</code>.
	 * </p>
	 * @return The server's certificate chain, which is represented as a <code>List</code> of <code>Certificate</code>.
	 * @throws SSLPeerUnverifiedException
	 *             If the peer is unverified.
	 */
	public abstract List<Certificate> getServerCertificateChain()
			throws SSLPeerUnverifiedException;

	/**
	 * <p>
	 * Gets the server's <code>Principle</code>. When the original connection 
	 * retrieved network resource, the principle was established when defining 
	 * the session. 
	 * </p>
	 * @return an <code>Principal</code> represents the server's principal. 
	 * @throws SSLPeerUnverifiedException
	 *             If the peer is unverified.
	 */
	public abstract Principal getPeerPrincipal()
			throws SSLPeerUnverifiedException;

	/**
	 * <p>
	 * Gets the <code>Principle</code> that the original connection sent to the server. 
	 * When the original connection fetched the network resource, the <code>Principle</code> 
	 * was sent to the server during handshaking process.
	 * </p>
	 * @return the <code>principal</code> sent to the server. 
	 *         Returns an <code>X500Principal</code> for X509-based cipher suites.
	 *         If no principal was sent, it returns null.
	 */
	public abstract Principal getLocalPrincipal();
}
