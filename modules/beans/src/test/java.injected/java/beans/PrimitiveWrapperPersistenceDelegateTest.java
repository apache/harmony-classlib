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

package java.beans;

import junit.framework.TestCase;

/**
 * Test the internal class java.beans.PrimitiveWrapperPersistenceDelegate.
 */
public class PrimitiveWrapperPersistenceDelegateTest extends TestCase {

	private PrimitiveWrapperPersistenceDelegate pd = null;

	protected void setUp() throws Exception {
		super.setUp();
		pd = new PrimitiveWrapperPersistenceDelegate();
	}

	public void testMutates() {
		assertFalse(pd.mutatesTo(new Integer(1), null));
		assertFalse(pd.mutatesTo(null, null));
		assertFalse(pd.mutatesTo(new Integer(1), new Integer(2)));
		assertTrue(pd.mutatesTo(new Integer(1), new Integer(1)));
	}

	public void testInitialize() {
		pd.initialize(null, null, null, null);
	}

	public void testInstantiate_Normal() throws Exception {
		Integer obj = new Integer(2);
		Expression exp = pd.instantiate(obj, new Encoder());
		assertSame(obj, exp.getValue());
		assertSame(Integer.class, exp.getTarget());
		assertEquals("new", exp.getMethodName());
		assertEquals(1, exp.getArguments().length);
		assertSame(obj, exp.getArguments()[0]);
	}

}
