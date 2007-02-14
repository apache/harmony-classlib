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
package org.apache.harmony.jndi.tests.javax.naming.ldap;

import javax.naming.ldap.UnsolicitedNotification;
import javax.naming.ldap.UnsolicitedNotificationEvent;
import org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockUnsolicitedNotification;
import junit.framework.TestCase;

/**
 * <p>This class has all test for the class UnsolicitedNotificationEvent.</p>
 * <p>Here we are gonna test all its methods as we see in the next table:</p>
 * <table class="t" cellspacing="0">
	<tbody><th>Constructors:</th>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="80" name="sas9nt21" readonly="readonly" value="UnsolicitedNotificationEvent(Object src, UnsolicitedNotification notice)" id="f10"></td>
			
		</tr>
		
	</tbody>
	<table>
	<tbody><th>Method Summary:</th>
		<tr><TD>Return</TD><TD>Method</TD></tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="void" id="f00"></TD>
			<td class="c0" id="c10"><input class="a0" size="80" name="sas9nt21" readonly="readonly" value="dispatch(UnsolicitedNotificationListener listener)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="UnsolicitedNotification" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="80" name="sas9nt21" readonly="readonly" value="getNotification()" id="f10"></td>
			
		</tr>
		
	</tbody>
	</table> 
 *
 */
public class TestUnsolicitedNotificationEvent extends TestCase {

	/**
	 * <p>This method is not implemted.</p>
	 * @param args Possible parameter to help us initiate all tests.
	 */
	public static void main(String[] args) {
	}

	/**
	 * <p>Constructor method of the test class.</p>
	 * <p>Here in this case we do not do anything else of initiate the inherited constructor.</p>
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.UnsolicitedNotificationEvent.UnsolicitedNotificationEvent(Object, UnsolicitedNotification)'</p>
	 * <p>This is the constructor method that constructs a new instance of UnsolicitedNotificationEvent. In this case we are sending 
	 * two null arguments. This is not specified in the API.</p>
	 * <p>The expected result is an Illegal argument exception.</p>
	 */
	public void testUnsolicitedNotificationEvent001() {

		try{
			UnsolicitedNotificationEvent une=new UnsolicitedNotificationEvent(null,null);
			fail("The arguments could not be null.");
		} catch (IllegalArgumentException e) {
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.UnsolicitedNotificationEvent.UnsolicitedNotificationEvent(Object, UnsolicitedNotification)'</p>
	 * <p>This is the constructor method that constructs a new instance of UnsolicitedNotificationEvent. In this case we are sending 
	 * one null arguments. This is not specified in the API.</p>
	 * <p>The expected result is an Illegal Argument exception.</p>
	 */
	public void testUnsolicitedNotificationEvent002() {
		try{
			Object x=new Object();
			UnsolicitedNotificationEvent une=new UnsolicitedNotificationEvent(x,null);
			fail("The arguments could not be null.");
		} catch (IllegalArgumentException e) {
            
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.UnsolicitedNotificationEvent.UnsolicitedNotificationEvent(Object, UnsolicitedNotification)'</p>
	 * <p>This is the constructor method that constructs a new instance of UnsolicitedNotificationEvent. In this case we are sending 
	 * one null arguments. This is not specified in the API.</p>
	 * <p>The expected result is an Illegal Argument exception.</p>
	 */
	public void testUnsolicitedNotificationEvent003() {

		try{
			MockUnsolicitedNotification u=new MockUnsolicitedNotification();
			UnsolicitedNotificationEvent une=new UnsolicitedNotificationEvent(null,u);
			fail("The arguments could not be null.");
		} catch (IllegalArgumentException e) {
			
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.UnsolicitedNotificationEvent.UnsolicitedNotificationEvent(Object, UnsolicitedNotification)'</p>
	 * <p>This is the constructor method that constructs a new instance of UnsolicitedNotificationEvent. In this case we are sending 
	 * one null arguments. This is not specified in the API.</p>
	 * <p>The expected result is an Illegal Argument exception.</p>
	 */
	public void testUnsolicitedNotificationEvent004() {

		try{
			Object x=new Object();
			MockUnsolicitedNotification u = new MockUnsolicitedNotification();
			UnsolicitedNotificationEvent une=new UnsolicitedNotificationEvent(x,u);
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.UnsolicitedNotificationEvent.getNotification()'</p>
	 * <p>Here we are testing if the method returns the unsolicited notification. In this case we create a notification
	 * with an object and a null notification as the parameters.</p>
	 * <p>The expected result is a not null notification.</p>
	 */
	public void testGetNotification001() {

		try{
			Object x=new Object();
			MockUnsolicitedNotification u=null;
			UnsolicitedNotificationEvent une=new UnsolicitedNotificationEvent(x,u);
			UnsolicitedNotification ret = une.getNotification();//because the notification was null, but see here the error of create a UNE with a null
			fail("The notification must not be null.");
		} catch (Throwable e) {
			
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.UnsolicitedNotificationEvent.getNotification()'</p>
	 * <p>Here we are testing if the method returns the unsolicited notification. In this case we create a notification
	 * with an object and a null notification as the parameters.</p>
	 * <p>The expected result is a not null notification.</p>
	 */
	public void testGetNotification002() {

		try{
			Object x=new Object();
			MockUnsolicitedNotification u = new MockUnsolicitedNotification();
			UnsolicitedNotificationEvent une=new UnsolicitedNotificationEvent(x,u);
			assertEquals(u,une.getNotification());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.UnsolicitedNotificationEvent.dispatch(UnsolicitedNotificationListener)'</p>
	 * <p>Here this method invokes the notificationReceived() method on a listener using this event. In this case we are 
	 * sending as a parameter a null listener.</p>
	 * <p>The expected result is a null pointer exception.</p>
	 */
	public void testDispatch001() {
        Object x=new Object();
        UnsolicitedNotificationEvent une=new UnsolicitedNotificationEvent(x,new MockUnsolicitedNotification());
		try{
			une.dispatch(null);
			fail("Failed notification is null.");
		} catch (NullPointerException e) {
			
		} catch (IllegalArgumentException e) {
            fail("Should not raise IllegalArgument");
        }

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.UnsolicitedNotificationEvent.dispatch(UnsolicitedNotificationListener)'</p>
	 * <p>Here this method invokes the notificationReceived() method on a listener using this event. In this case we are 
	 * sending as a parameter a non null listener.</p>
	 * <p>The expected result is a null pointer exception.</p>
	 */
	public void testDispatch002() {
		try{
			Object x=new Object();
			MockUnsolicitedNotification u=new MockUnsolicitedNotification();
			MockUnsolicitedNotification f=new MockUnsolicitedNotification();			
			UnsolicitedNotificationEvent une=new UnsolicitedNotificationEvent(x,u);
			une.dispatch(f);
			assertTrue(f.getFlag());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	

}
