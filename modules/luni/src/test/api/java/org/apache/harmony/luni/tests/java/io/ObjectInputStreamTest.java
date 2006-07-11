/*
 * Copyright 2006 The Apache Software Foundation or its licensors, as
 * applicable
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

package org.apache.harmony.luni.tests.java.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import junit.framework.TestCase;

public class ObjectInputStreamTest extends TestCase {

	/**
	 * Micro-scenario of de/serialization of an object with non-serializable superclass.
	 * The super-constructor only should be invoked on the deserialized instance.
	 */
	public void test_readObject_Hierarchy() throws Exception {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream(); 

	    ObjectOutputStream oos = new ObjectOutputStream(baos); 
	    oos.writeObject(new B());
	    oos.close(); 

	    ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray())); 
	    B b = (B) ois.readObject();
	    ois.close();
	    
	    assertTrue("should construct super", A.list.contains(b));
	    assertFalse("should not construct self", B.list.contains(b));
	    assertEquals("super field A.s", A.DEFAULT, ((A)b).s);
	    assertNull("transient field B.s", b.s);
	}

	static class A { 
		static final ArrayList<A> list = new ArrayList<A>();  
	    String s;
	    public static final String DEFAULT = "aaa";
	    public A() {
	    	s = DEFAULT;
	    	list.add(this);
	    }
	} 

	static class B extends A implements Serializable { 
        private static final long serialVersionUID = 1L;
        static final ArrayList<A> list = new ArrayList<A>();  
	    transient String s;
	    public B() {
	    	s = "bbb";
	    	list.add(this);
	    }
	} 	
}


