/* Copyright 1998, 2005 The Apache Software Foundation or its licensors, as applicable
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
package tests.api.java.text;

import java.text.Annotation;

public class AnnotationTest extends junit.framework.TestCase {

	Annotation ant;

	class Foo {
		public String name;

		public Foo(String s) {
			name = s;
		}
	}

	class Dummy {
		public String name;

		public int value;

		public Dummy(String s, int i) {
			name = s;
			value = i;
		}
	}

	Foo foo;
	Dummy dummy;

	/**
	 * @tests java.text.Annotation#Annotation(java.lang.Object)
	 */
	public void test_ConstructorLjava_lang_Object() {
		assertTrue("Used in testing.", true);
	}

	/**
	 * @tests java.text.Annotation#getValue()
	 */
	public void test_getValue() {
		assertTrue("getValue error", ant.getValue() == foo);
	}

	/**
	 * @tests java.text.Annotation#toString()
	 */
	public void test_toString() {
		ant = new Annotation("HelloWorld");
		assertTrue("toString error.", ant.toString().equals(
				"java.text.Annotation[value=HelloWorld]"));
	}

	protected void setUp() {
		foo = new Foo("HelloWorld");
		dummy = new Dummy("HelloWorld", 5);
		ant = new Annotation(foo);

	}

	protected void tearDown() {
	}
}
