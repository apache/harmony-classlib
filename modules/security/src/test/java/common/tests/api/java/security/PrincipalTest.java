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

package tests.api.java.security;

import java.security.Principal;

import javax.security.auth.x500.X500Principal;

public class PrincipalTest extends junit.framework.TestCase {

	/**
	 * @tests java.security.Principal#equals(java.lang.Object)
	 */
	public void test_equalsLjava_lang_Object() {
		Principal xp1 = new X500Principal(
				"C=US, ST=California, L=San Diego, O=Apache, OU=Project Harmony, CN=Test cert");
		assertNotNull(xp1);
		assertEquals(
				"Unexpected name",
				"C=US,ST=California,L=San Diego,O=Apache,OU=Project Harmony,CN=Test cert",
				xp1.getName());
	}
}