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
/**
 * @author Hugo Beilis
 * @author Leonardo Soler
 * @author Gabriel Miretti
 * @version 1.0
 */
package org.apache.harmony.jndi.tests.javax.naming.ldap.main;

import org.apache.harmony.jndi.tests.javax.naming.ldap.LdapNameTest;
import org.apache.harmony.jndi.tests.javax.naming.ldap.TestBasicControl;
import org.apache.harmony.jndi.tests.javax.naming.ldap.TestControlFactory;
import org.apache.harmony.jndi.tests.javax.naming.ldap.TestInitialLdapContext;
import org.apache.harmony.jndi.tests.javax.naming.ldap.TestManageReferralControl;
import org.apache.harmony.jndi.tests.javax.naming.ldap.TestPagedResultsControls;
import org.apache.harmony.jndi.tests.javax.naming.ldap.TestPagedResultsResponseControl;
import org.apache.harmony.jndi.tests.javax.naming.ldap.TestRdn;
import org.apache.harmony.jndi.tests.javax.naming.ldap.TestSortControl;
import org.apache.harmony.jndi.tests.javax.naming.ldap.TestSortKey;
import org.apache.harmony.jndi.tests.javax.naming.ldap.TestSortResponseControl;
import org.apache.harmony.jndi.tests.javax.naming.ldap.TestStartTlsRequest;
import org.apache.harmony.jndi.tests.javax.naming.ldap.TestStartTlsResponse;
import org.apache.harmony.jndi.tests.javax.naming.ldap.TestUnsolicitedNotificationEvent;
import org.apache.harmony.jndi.tests.javax.naming.ldap.integration.TestLdapNameIntegration;
import org.apache.harmony.jndi.tests.javax.naming.ldap.integration.TestRdnIntegration;
import org.apache.harmony.jndi.tests.javax.naming.ldap.whitebox.TestControlFactoryWhiteBoxDevelopment;
import org.apache.harmony.jndi.tests.javax.naming.ldap.whitebox.TestInitialLdapContextWhiteBoxDevelopment;
import org.apache.harmony.jndi.tests.javax.naming.ldap.whitebox.TestLdapNameWhiteBoxDevelopment;
import org.apache.harmony.jndi.tests.javax.naming.ldap.whitebox.TestLdapReferralException;
import org.apache.harmony.jndi.tests.javax.naming.ldap.whitebox.TestRdnWhiteBoxDevelopment;
import org.apache.harmony.jndi.tests.javax.naming.ldap.whitebox.TestStartTlsRequestWhiteBoxDevelopment;
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
	        suite.addTest(new TestSuite(TestSortControl.class));
	        suite.addTest(new TestSuite(TestSortResponseControl.class));
	        suite.addTest(new TestSuite(TestRdn.class));
	        suite.addTest(new TestSuite(LdapNameTest.class));
	        suite.addTest(new TestSuite(TestPagedResultsControls.class));
	        suite.addTest(new TestSuite(TestPagedResultsResponseControl.class));
	        
	        //Extras
	        suite.addTest(new TestSuite(org.apache.harmony.jndi.tests.javax.naming.ldap.extras.TestSerialization.class));
	        suite.addTest(new TestSuite(org.apache.harmony.jndi.tests.javax.naming.ldap.extras.TestRdnParser.class));
	      
	        //Integration
	        suite.addTest(new TestSuite(TestLdapNameIntegration.class));
	        suite.addTest(new TestSuite(TestRdnIntegration.class));
	        
	        //WhiteBox
	        suite.addTest(new TestSuite(TestControlFactoryWhiteBoxDevelopment.class));
	        suite.addTest(new TestSuite(TestInitialLdapContextWhiteBoxDevelopment.class));
	        suite.addTest(new TestSuite(TestLdapNameWhiteBoxDevelopment.class));
	        suite.addTest(new TestSuite(TestLdapReferralException.class));
	        suite.addTest(new TestSuite(TestRdnWhiteBoxDevelopment.class));
	        suite.addTest(new TestSuite(TestStartTlsRequestWhiteBoxDevelopment.class));   
	               
	        return suite;
	    }

}
	

