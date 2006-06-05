/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.tests.java.nio.channels.spi;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;

import junit.framework.TestCase;

public class AbstractSelectorTest extends TestCase {

    /**
     * @tests AbstractSelector#provider()
     */
    public void test_provider() throws IOException {
        Selector mockSelector = new MockAbstractSelector(SelectorProvider
                .provider());
        assertTrue(mockSelector.isOpen());
        assertSame(SelectorProvider.provider(), mockSelector.provider());
        mockSelector = new MockAbstractSelector(null);
        assertNull(mockSelector.provider());
    }

    /**
     * @tests AbstractSelector#close()
     */
    public void test_close() throws IOException {
        MockAbstractSelector mockSelector = new MockAbstractSelector(
                SelectorProvider.provider());
        mockSelector.close();
        assertTrue(mockSelector.isImplCloseSelectorCalled);
    }

    /**
     * 
     * @tests AbstractSelector#begin/end()
     */
    public void test_begin_end() throws IOException {
        MockAbstractSelector mockSelector = new MockAbstractSelector(
                SelectorProvider.provider());     
        try {
            mockSelector.superBegin();
        } finally {
            mockSelector.superEnd();
        }
        
        mockSelector = new MockAbstractSelector(SelectorProvider.provider());
        try {
            mockSelector.superBegin();
            mockSelector.close();
        } finally {
            mockSelector.superEnd();
        }
       
        try {
            // begin twice
            mockSelector.superBegin();
            mockSelector.superBegin();
        } finally {
            mockSelector.superEnd();
        }
        
        try {
            mockSelector.superBegin();
        } finally {
            // end twice
            mockSelector.superEnd();
            mockSelector.superEnd();
        }

        mockSelector.close();
        try {
            mockSelector.superBegin();
        } finally {
            mockSelector.superEnd();
        }
    }
}
