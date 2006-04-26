/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.jndi.tests.javax.naming.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.Control;
import javax.naming.ldap.LdapReferralException;

import org.apache.harmony.jndi.tests.javax.naming.util.Log;
import junit.framework.TestCase;

public class LdapReferralExceptionTest extends TestCase {

	/*
	 * -------------------------------------------------------------------
	 * Constants
	 * -------------------------------------------------------------------
	 */

	private static Log log = new Log(LdapReferralExceptionTest.class);

	/*
	 * -------------------------------------------------------------------
	 * Constructors
	 * -------------------------------------------------------------------
	 */

	/**
	 * Constructor for LdapReferralExceptionTest.
	 * 
	 * @param arg0
	 */
	public LdapReferralExceptionTest(String arg0) {
		super(arg0);
	}

	/*
	 * -------------------------------------------------------------------
	 * Methods
	 * -------------------------------------------------------------------
	 */

	public void testAllCoveragePurpose() throws NamingException {
		log.setMethod("testAllCoveragePurpose()");
		LdapReferralException ex = new MockLdapReferralException();
		ex = new MockLdapReferralException("message");

		ex.getReferralContext();
		ex.getReferralContext(null);
		ex.getReferralContext(null, null);
		ex.getReferralInfo();
		ex.skipReferral();
		ex.retryReferral();
	}

	public static class MockLdapReferralException extends LdapReferralException {

		/**
		 * 
		 */
		public MockLdapReferralException() {
			super();
		}

		/**
		 * @param s
		 */
		public MockLdapReferralException(String s) {
			super(s);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.naming.ldap.LdapReferralException#getReferralContext()
		 */
		public Context getReferralContext() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.naming.ldap.LdapReferralException#getReferralContext(java.util.Hashtable)
		 */
		public Context getReferralContext(Hashtable h) {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.naming.ldap.LdapReferralException#getReferralContext(java.util.Hashtable,
		 *      javax.naming.ldap.Control[])
		 */
		public Context getReferralContext(Hashtable h, Control[] cs) {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.naming.ReferralException#getReferralInfo()
		 */
		public Object getReferralInfo() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.naming.ReferralException#skipReferral()
		 */
		public boolean skipReferral() {
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.naming.ReferralException#retryReferral()
		 */
		public void retryReferral() {

		}

	}

}
