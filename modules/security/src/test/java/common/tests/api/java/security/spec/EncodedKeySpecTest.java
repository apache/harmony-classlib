/* Copyright 2005 The Apache Software Foundation or its licensors, as applicable
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

package tests.api.java.security.spec;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;
import java.util.Vector;

import junit.framework.TestCase;

public class EncodedKeySpecTest extends TestCase {

	private static final String KEYFACTORY_ID = "KeyFactory.";

	/**
	 * @tests java.security.spec.EncodedKeySpec#getEncoded()
	 */
	public void test_getEncoded() {
		
		fail("Takes ages. Problem with SecureRandom and stub math ?");
		
		Provider[] providers = Security.getProviders();
		if (providers == null) {
			fail("No providers found");
		}

		boolean atLeastOneProviderFound = false;
		
		// Loop through each of the providers that support KeyFactory algorithms
		for (int j = 0; j < providers.length; j++) {
			String providerName = providers[j].getName();
			String[] keyfactAlgs = getKeyFactoryAlgorithms(providerName);
			
			// Loop through each of the KeyFactory algorithms supported by 
			// the current provider
			for (int i = 0; i < keyfactAlgs.length; i++) {
				// If we reach here then at least one of the installed providers
				// must support one of the KeyFactory algorithms
				atLeastOneProviderFound = true;
				try {
					KeyFactory fact = KeyFactory.getInstance(keyfactAlgs[i],
							providerName);
					KeyPairGenerator keyGen = KeyPairGenerator
							.getInstance(keyfactAlgs[i]);
					// We don't use getInstance
					SecureRandom random = new SecureRandom();
					keyGen.initialize(1024, random);
					KeyPair keys = keyGen.generateKeyPair();

					// check public key encoding
					byte[] encoded = keys.getPublic().getEncoded();
					Key key = fact.generatePublic(new X509EncodedKeySpec(
							encoded));
					assertTrue(
							"public key encodings were different for algorithm "
									+ keyfactAlgs[i], isEqual(key, keys
									.getPublic()));

					// check private key encoding
					encoded = keys.getPrivate().getEncoded();
					key = fact
							.generatePrivate(new PKCS8EncodedKeySpec(encoded));
					assertTrue(
							"private key encodings were different for algorithm "
									+ keyfactAlgs[i], isEqual(key, keys
									.getPrivate()));
				} catch (NoSuchAlgorithmException e) {
					fail("getInstance did not find expected algorithm "
							+ keyfactAlgs[i]);
				} catch (NoSuchProviderException e) {
					fail("getInstance did not find provider " + providerName);
				} catch (InvalidKeySpecException e) {
					fail("invalid key spec for algorithm " + keyfactAlgs[i]);
				}
			}// end for
		}// end for each provider supporting KeyFactory algorithms
		
		if (!atLeastOneProviderFound) {
			fail("No providers supported KeyFactory algorithms");
		}
	}

	/*
	 * Returns the key algorithms that the given provider supports.
	 */
	private String[] getKeyFactoryAlgorithms(String providerName) {
		Vector algs = new Vector();

		Provider provider = Security.getProvider(providerName);
		if (provider == null)
			return new String[0];
		Enumeration e = provider.keys();
		while (e.hasMoreElements()) {
			String algorithm = (String) e.nextElement();
			if (algorithm.startsWith(KEYFACTORY_ID)) {
				algs.addElement(algorithm.substring(KEYFACTORY_ID.length()));
			}
		}
		return (String[]) algs.toArray(new String[algs.size()]);
	}

	protected void setUp() {
	}
	
	protected void tearDown() {
	}

	private boolean isEqual(Key key1, Key key2) {
		if (key1 instanceof RSAPublicKey && key2 instanceof RSAPublicKey) {
			RSAPublicKey rsa1 = ((RSAPublicKey) key1);
			RSAPublicKey rsa2 = ((RSAPublicKey) key2);
			return rsa1.getModulus().equals(rsa2.getModulus())
					&& rsa1.getPublicExponent()
							.equals(rsa2.getPublicExponent());
		} else if (key1 instanceof RSAPrivateCrtKey
				&& key2 instanceof RSAPrivateCrtKey) {
			RSAPrivateCrtKey rsa1 = ((RSAPrivateCrtKey) key1);
			RSAPrivateCrtKey rsa2 = ((RSAPrivateCrtKey) key2);
			return rsa1.getModulus().equals(rsa2.getModulus())
					&& rsa1.getPublicExponent()
							.equals(rsa2.getPublicExponent())
					&& rsa1.getPrivateExponent().equals(
							rsa2.getPrivateExponent())
					&& rsa1.getPrimeP().equals(rsa2.getPrimeP())
					&& rsa1.getPrimeQ().equals(rsa2.getPrimeQ())
					&& rsa1.getPrimeExponentP()
							.equals(rsa2.getPrimeExponentP())
					&& rsa1.getPrimeExponentQ()
							.equals(rsa2.getPrimeExponentQ())
					&& rsa1.getCrtCoefficient()
							.equals(rsa2.getCrtCoefficient());
		} else if (key1 instanceof DSAPublicKey && key2 instanceof DSAPublicKey) {
			DSAPublicKey dsa1 = ((DSAPublicKey) key1);
			DSAPublicKey dsa2 = ((DSAPublicKey) key2);
			return dsa1.getY().equals(dsa2.getY())
					&& dsa1.getParams().getG().equals(dsa2.getParams().getG())
					&& dsa1.getParams().getP().equals(dsa2.getParams().getP())
					&& dsa1.getParams().getQ().equals(dsa2.getParams().getQ());

		} else if (key1 instanceof DSAPrivateKey
				&& key2 instanceof DSAPrivateKey) {
			DSAPrivateKey dsa1 = ((DSAPrivateKey) key1);
			DSAPrivateKey dsa2 = ((DSAPrivateKey) key2);
			return dsa1.getX().equals(dsa2.getX())
					&& dsa1.getParams().getG().equals(dsa2.getParams().getG())
					&& dsa1.getParams().getP().equals(dsa2.getParams().getP())
					&& dsa1.getParams().getQ().equals(dsa2.getParams().getQ());
		} else {
			return false;
		}
	}
}