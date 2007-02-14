/*
 *  Copyright 2006 The Apache Software Foundation or its licensors, as applicable.
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
 * @author Hugo Beilis
 * @author Leonardo Soler
 * @author Gabriel Miretti
 * @version 1.0
 */
package org.apache.harmony.jndi.tests.javax.naming.ldap;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * <p>This class runs all test of the package javax.naming.ldap</p>
 *
 */
public class AllTestLdap {

		/**
		 * <p>Main method to initialize the suite of test.</p>
		 * @param args
		 */
	    public static void main(String[] args) {
	        junit.textui.TestRunner.run(AllTestLdap.suite());
	    }

	    /**
	     * <p>Here we run all test of the package.</p>
	     * 
	     */
	    public static Test suite() {
	        TestSuite suite = new TestSuite("Test for javax.naming.ldap");
	        suite.addTest(new TestSuite(TestBasicControl.class));
	        suite.addTest(new TestSuite(TestControlFactory.class));
	        suite.addTest(new TestSuite(TestInitialLdapContext.class));
	        suite.addTest(new TestSuite(TestManageReferralControl.class));
	        suite.addTest(new TestSuite(TestSortKey.class));
	        suite.addTest(new TestSuite(TestStartTlsRequest.class));
	        suite.addTest(new TestSuite(TestStartTlsResponse.class));
	        suite.addTest(new TestSuite(TestUnsolicitedNotificationEvent.class));
	        return suite;
	    }

}
	

