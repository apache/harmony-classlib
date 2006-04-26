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

import javax.naming.NamingException;
import javax.naming.ldap.Control;
import javax.naming.ldap.ControlFactory;

public class MockControlFactory extends ControlFactory {
	public static String ID_PREFIX = "LDAPv3 Control:";

	public static String ID_PREFIX_SPI1 = "LDAPv3 Control(SPI1):";

	public static String ID_PREFIX_SPI2 = "LDAPv3 Control(SPI2):";

	public Control getControlInstance(Control c) throws NamingException {
		return new MockControl(ID_PREFIX + c.getID(), c.getEncodedValue(), c
				.isCritical());
	}

	public static class ControlFactorySPI1 extends ControlFactory {
		public Control getControlInstance(Control c) throws NamingException {
			return new MockControl(ID_PREFIX_SPI1 + c.getID(), c
					.getEncodedValue(), c.isCritical());
		}

	}

	public static class ControlFactorySPI2 extends ControlFactory {
		public Control getControlInstance(Control c) throws NamingException {
			return new MockControl(ID_PREFIX_SPI2 + c.getID(), c
					.getEncodedValue(), c.isCritical());
		}

	}
}
