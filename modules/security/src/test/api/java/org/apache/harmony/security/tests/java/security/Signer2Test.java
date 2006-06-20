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

package org.apache.harmony.security.tests.java.security;

import java.security.IdentityScope;
import java.security.KeyManagementException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signer;

import org.apache.harmony.security.tests.java.security.IdentityScope2Test.IdentityScopeSubclass;

public class Signer2Test extends junit.framework.TestCase {

	public class SignerImpl extends Signer {
		public SignerImpl() {
			super();
		}

		public SignerImpl(String name) {
			super(name);
		}

		public SignerImpl(String name, IdentityScope scope)
				throws KeyManagementException {
			super(name, scope);
		}
	}

	/**
	 * @tests java.security.Signer#Signer()
	 */
	public void test_Constructor() {
		new SignerImpl();
	}

	/**
	 * @tests java.security.Signer#Signer(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		new SignerImpl("test");
	}

	/**
	 * @tests java.security.Signer#Signer(java.lang.String,
	 *        java.security.IdentityScope)
	 */
	public void test_ConstructorLjava_lang_StringLjava_security_IdentityScope() throws Exception {
		new SignerImpl("test", new IdentityScopeSubclass());
	}

	/**
	 * @tests java.security.Signer#getPrivateKey()
	 */
	public void test_getPrivateKey() throws Exception {
		SignerImpl signer = new SignerImpl("test");
		KeyPairGenerator gen = KeyPairGenerator.getInstance("DSA");
		KeyPair pair = gen.genKeyPair();
		signer.setKeyPair(pair);
		signer.getPrivateKey();
	}

	/**
	 * @tests java.security.Signer#toString()
	 */
	public void test_toString() {
		SignerImpl signer = new SignerImpl("test");
		assertEquals("Unexpected return from toString method",
				"[Signer]test", signer.toString());
	}
}