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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import junit.framework.TestCase;

/**
 * Test the internal class java.beans.ProxyPersistenceDelegate.
 */
public class ProxyPersistenceDelegateTest extends TestCase {

	private ProxyPersistenceDelegate pd = null;

	protected void setUp() throws Exception {
		super.setUp();
		pd = new ProxyPersistenceDelegate();
	}

	public void testInstantiate_Normal() throws Exception {
		InvocationHandler h = new Handler();
		Object obj = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
				new Class[] { Runnable.class, Cloneable.class }, h);

		Expression exp = pd.instantiate(obj, new Encoder());
		assertSame(obj, exp.getValue());
		assertSame(Proxy.class, exp.getTarget());
		assertEquals("newProxyInstance", exp.getMethodName());
		assertEquals(3, exp.getArguments().length);
		assertSame(obj.getClass().getClassLoader(), exp.getArguments()[0]);
		Class[] interfaces = (Class[]) exp.getArguments()[1];
		assertSame(Runnable.class, interfaces[0]);
		assertSame(Cloneable.class, interfaces[1]);
		assertSame(h, exp.getArguments()[2]);
	}

	public static class Handler implements InvocationHandler {
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			return null;
		}
	}
}
