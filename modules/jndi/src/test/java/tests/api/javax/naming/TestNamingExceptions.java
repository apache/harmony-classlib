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

import java.lang.reflect.Modifier;

import javax.naming.NamingException;

import tests.api.javax.naming.util.Log;
import junit.framework.TestCase;

public class TestNamingExceptions extends TestCase {

	static private Log log = new Log(TestNamingExceptions.class);

	static private Class nexClasses[] = {
			javax.naming.directory.SchemaViolationException.class,
			javax.naming.directory.AttributeInUseException.class,
			javax.naming.directory.AttributeModificationException.class,
			javax.naming.directory.InvalidSearchFilterException.class,
			javax.naming.directory.InvalidAttributeIdentifierException.class,
			javax.naming.directory.InvalidAttributeValueException.class,
			javax.naming.directory.NoSuchAttributeException.class,
			javax.naming.directory.InvalidAttributesException.class,
			javax.naming.directory.InvalidSearchControlsException.class,
			javax.naming.NamingException.class,
			javax.naming.InvalidNameException.class,
			javax.naming.ldap.LdapReferralException.class,
			javax.naming.NameAlreadyBoundException.class,
			javax.naming.NameNotFoundException.class,
			javax.naming.NotContextException.class,
			javax.naming.OperationNotSupportedException.class,
			javax.naming.NoInitialContextException.class,
			javax.naming.ConfigurationException.class,
			javax.naming.MalformedLinkException.class,
			javax.naming.AuthenticationException.class,
			javax.naming.AuthenticationNotSupportedException.class,
			javax.naming.CannotProceedException.class,
			javax.naming.CommunicationException.class,
			javax.naming.ContextNotEmptyException.class,
			javax.naming.InsufficientResourcesException.class,
			javax.naming.InterruptedNamingException.class,
			javax.naming.LimitExceededException.class,
			javax.naming.LinkException.class,
			javax.naming.LinkLoopException.class,
			javax.naming.NamingSecurityException.class,
			javax.naming.NoPermissionException.class,
			javax.naming.PartialResultException.class,
			javax.naming.ReferralException.class,
			javax.naming.ServiceUnavailableException.class,
			javax.naming.SizeLimitExceededException.class,
			javax.naming.TimeLimitExceededException.class, };

	/**
	 * Constructor for TestNamingExceptions.
	 * 
	 * @param arg0
	 */
	public TestNamingExceptions(String arg0) {
		super(arg0);
	}

	public void testDefaultConstructor() {
		log.setMethod("testDefaultConstructor()");

		for (int i = 0; i < nexClasses.length; i++) {
			try {
				if (Modifier.isAbstract(nexClasses[i].getModifiers())) {
					continue;
				}
				NamingException nex = (NamingException) nexClasses[i]
						.newInstance();
				assertNull(nex.getMessage());
			} catch (Throwable e) {
				log.log("Failed at " + nexClasses[i]);
				log.log(e);
			}
		}
	}

	public void testStringConstructor() {
		log.setMethod("testStringConstructor()");

		for (int i = 0; i < nexClasses.length; i++) {
			try {
				if (Modifier.isAbstract(nexClasses[i].getModifiers())) {
					continue;
				}
				NamingException nex = (NamingException) nexClasses[i]
						.getConstructor(new Class[] { String.class })
						.newInstance(new Object[] { "sample text" });
				assertEquals("sample text", nex.getMessage());
			} catch (Throwable e) {
				log.log("Failed at " + nexClasses[i]);
				log.log(e);
			}
		}
	}

}
