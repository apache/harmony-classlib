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
import java.nio.channels.SelectionKey;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.channels.spi.SelectorProvider;

import junit.framework.TestCase;

public class AbstractSelectableChannelTest extends TestCase {

    private MockSelectableChannel testChannel;

    protected void setUp() throws Exception {
        super.setUp();
        testChannel = new MockSelectableChannel(SelectorProvider.provider());
    }

    protected void tearDown() throws Exception {
        if (testChannel.isOpen()) {
            testChannel.close();
        }
    }

    /**
     * @tests AbstractSelectableChannel#implCloseChannel()
     */
    public void test_implClose() throws IOException {
        testChannel.isImplCloseSelectableChannelCalled = false;
        testChannel.implCloseSelectableChannelCount = 0;
        testChannel.close();
        assertFalse(testChannel.isOpen());
        assertTrue(testChannel.isImplCloseSelectableChannelCalled);
        assertEquals(1, testChannel.implCloseSelectableChannelCount);

        testChannel = new MockSelectableChannel(SelectorProvider.provider());
        testChannel.isImplCloseSelectableChannelCalled = false;
        testChannel.implCloseSelectableChannelCount = 0;
        // close twice.
        // make sure implCloseSelectableChannelCount is called only once.
        testChannel.close();
        testChannel.close();
        assertFalse(testChannel.isOpen());
        assertTrue(testChannel.isImplCloseSelectableChannelCalled);
        assertEquals(1, testChannel.implCloseSelectableChannelCount);
    }

    /**
     * @tests AbstractSelectableChannel#provider()
     */
    public void test_provider() {
        SelectorProvider provider = testChannel.provider();
        assertSame(SelectorProvider.provider(), provider);
        testChannel = new MockSelectableChannel(null);
        provider = testChannel.provider();
        assertNull(provider);
    }

    /**
     * @tests AbstractSelectableChannel#isBlocking()
     */
    public void test_isBlocking() throws IOException {
        assertTrue(testChannel.isBlocking());
        testChannel.configureBlocking(false);
        assertFalse(testChannel.isBlocking());
        testChannel.configureBlocking(true);
        assertTrue(testChannel.isBlocking());
    }

    /**
     * 
     * @tests AbstractSelectableChannel#blockingLock()
     */
    public void test_blockingLock() {
        Object gotObj = testChannel.blockingLock();
        assertNotNull(gotObj);
    }

    private class MockSelectableChannel extends AbstractSelectableChannel {

        private boolean isImplCloseSelectableChannelCalled = false;

        private int implCloseSelectableChannelCount = 0;
        
        public MockSelectableChannel(SelectorProvider arg0) {
            super(arg0);
        }

        public void implClose() throws IOException {
            super.implCloseChannel();
        }

        protected void implCloseSelectableChannel() throws IOException {
            isImplCloseSelectableChannelCalled = true;
            ++implCloseSelectableChannelCount;
        }

        protected void implConfigureBlocking(boolean arg0) throws IOException {
            // for test only, do nothing
        }

        public int validOps() {
            return SelectionKey.OP_ACCEPT;
        }

    }
}
