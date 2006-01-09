/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
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
* @author Boris V. Kuznetsov
* @version $Revision$
*/

package java.security;

import java.util.HashMap;

import com.openintel.drl.security.test.PerformanceTest;

/**
 * Tests for <code>Provider.Service</code> constructor and methods
 * 
 */
public class ProviderServiceTest extends PerformanceTest {

	public void testService() {
		Provider p = new MyProvider();
		try {
			new Provider.Service(null, "type", "algorithm", 
					"className", null, null);
			fail("provider is null: No expected NullPointerException");
		} catch (NullPointerException e) {	
		}
		try {
			new Provider.Service(p, null, "algorithm", 
					"className", null, null);
			fail("type is null: No expected NullPointerException");
		} catch (NullPointerException e) {	
		}
		try {
			new Provider.Service(p, "type", null, 
					"className", null, null);
			fail("algorithm is null: No expected NullPointerException");
		} catch (NullPointerException e) {	
		}	
		try {
			new Provider.Service(p, "type", "algorithm", 
					null, null, null);
			fail("className is null: No expected NullPointerException");
		} catch (NullPointerException e) {	
		}
		
		Provider.Service s = new Provider.Service(p,
                "type", "algorithm", "className", null, null);
		
		if (!s.getType().equals("type")) {
			fail("getType() failed");
		}
		if (!s.getAlgorithm().equals("algorithm")) {
			fail("getAlgorithm() failed");
		}
		if (s.getProvider()!= p) {
			fail("getProvider() failed");
		}
		if (!s.getClassName().equals("className")) {
			fail("getClassName() failed");
		}
		if (!s.supportsParameter(new Object())) {
			fail("supportsParameter() failed");
		}
	}

	public void testGetAttribute() {
		Provider p = new MyProvider();
		Provider.Service s = new Provider.Service(p,
                "type", "algorithm", "className", null, null);
		try {
			s.getAttribute(null);
			fail("No expected NullPointerException");
		} catch (NullPointerException e) {	
		}
	
		if (s.getAttribute("aaa") != null) {
			fail("getAttribute(aaa) failed");			
		}
		
		HashMap hm = new HashMap();
		hm.put("attribute", "value");
		hm.put("KeySize", "1024");
		hm.put("AAA", "BBB");	
		
		s = new Provider.Service(p, "type", "algorithm", "className", 
		 		null, hm);
		if (s.getAttribute("bbb") != null) {
			fail("getAttribute(bbb) failed");			
		}
		if (!s.getAttribute("attribute").equals("value")) {
			fail("getAttribute(attribute) failed");			
		}
		if (!s.getAttribute("KeySize").equals("1024")) {
			fail("getAttribute(KeySize) failed");			
		}	
	}

	public void testNewInstance() {
		Provider p = new MyProvider();
		Provider.Service s = new Provider.Service(p,
                "SecureRandom", "algorithm", "java.security.RandomImpl", null, null);
		Object o = null;
		try {
			o = s.newInstance(null);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			fail("newInstance() failed");
		}
		if (!(o instanceof RandomImpl)) {
			fail("incorrect instance");
		}
		
		try {
			o = s.newInstance(new Object());
			fail("No expected NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		}
	}

	/*
	 * Class under test for String toString()
	 */
	public void testToString() {
		Provider p = new MyProvider();
		Provider.Service s = new Provider.Service(p,
                "type", "algorithm", "className", null, null);
		if (!s.toString().equals("Provider MyProvider Service type.algorithm className")) {
			fail("first toString() failed");
		}
		
		HashMap hm = new HashMap();
		hm.put("attribute", "value");
		hm.put("KeySize", "1024");
		hm.put("AAA", "BBB");	
		
		s = new Provider.Service(p, "type", "algorithm", "className", 
		 		null, hm);
		if (!s.toString().startsWith("Provider MyProvider Service type.algorithm className\n" +
				"Attributes ")) {
			fail("second toString() failed");
		}
	}
	
	class MyProvider extends Provider {
		MyProvider() {
			super("MyProvider", 1.0, "Provider for testing");
			put("MessageDigest.SHA-1", "SomeClassName");
		}
	}
}
