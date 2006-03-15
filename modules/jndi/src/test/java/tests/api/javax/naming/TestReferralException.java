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

package tests.api.javax.naming;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ReferralException;

import tests.api.javax.naming.util.Log;
import junit.framework.TestCase;

public class TestReferralException extends TestCase {

	/*
	 * -------------------------------------------------------------------
	 * Constants
	 * -------------------------------------------------------------------
	 */

	private static Log log = new Log(TestReferralException.class);

	/*
	 * -------------------------------------------------------------------
	 * Constructors
	 * -------------------------------------------------------------------
	 */

	/**
	 * Constructor for TestReferralException.
	 * 
	 * @param arg0
	 */
	public TestReferralException(String arg0) {
		super(arg0);
	}

	/*
	 * -------------------------------------------------------------------
	 * Methods
	 * -------------------------------------------------------------------
	 */

	public void testAllCoveragePurpose() throws NamingException {
		log.setMethod("testAllCoveragePurpose()");
		ReferralException ex = new MockReferralException();
		ex = new MockReferralException("message");

		ex.getReferralContext();
		ex.getReferralContext(null);
		ex.getReferralInfo();
		ex.skipReferral();
		ex.retryReferral();
	}

	public static class MockReferralException extends ReferralException {

		/**
		 * 
		 */
		public MockReferralException() {
			super();
		}

		/**
		 * @param s
		 */
		public MockReferralException(String s) {
			super(s);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.naming.ReferralException#getReferralContext()
		 */
		public Context getReferralContext() throws NamingException {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.naming.ReferralException#getReferralContext(java.util.Hashtable)
		 */
		public Context getReferralContext(Hashtable h) throws NamingException {
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
