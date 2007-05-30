/* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.luni.tests.java.util;

import java.util.Vector;

import junit.framework.TestCase;

public class VectorTest extends TestCase {

    class SubVector<E> extends Vector<E> {

        private static final long serialVersionUID = 1L;

        public SubVector() {
            super();
        }

        public synchronized boolean add(E obj) {
            super.addElement(obj);
            return true;
        }

        public synchronized void addElement(E obj) {
            super.add(obj);
        }

        /**
         * @tests java.util.Vector#add(Object)
         */
        @SuppressWarnings("nls")
        public void test_add() {
            SubVector<String> subvector = new SubVector<String>();
            subvector.add("foo");
            subvector.addElement("bar");
            assertEquals("Expected two elements in vector", 2, subvector.size());
        }

    }

    /**
     * @tests java.util.Vector#toString()
     */
    public void test_toString() {
        // Ensure toString works with self-referencing elements.
        Vector<Object> vec = new Vector<Object>(3);
        vec.add(null);
        vec.add(new Object());
        vec.add(vec);
        assertNotNull(vec.toString());
    }
}
