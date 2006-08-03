/*
 *  Copyright 2005 - 2006 The Apache Software Software Foundation or its licensors, as applicable.
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
 * @author Anton Avtamonov
 * @version $Revision$
 */
package javax.swing.tree;

import javax.swing.event.TreeExpansionEvent;

import junit.framework.TestCase;

public class ExpandVetoExceptionTest extends TestCase {
    private ExpandVetoException eve;
    private TreeExpansionEvent event;

    public ExpandVetoExceptionTest(final String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        event = new TreeExpansionEvent(this, new TreePath("path"));
    }

    protected void tearDown() throws Exception {
        eve = null;
        event = null;
    }

    public void testExpandVetoException() throws Exception {
        eve = new ExpandVetoException(event);
        assertSame(event, eve.event);
        assertNull(eve.getMessage());

        eve = new ExpandVetoException(event, "message");
        assertSame(event, eve.event);
        assertEquals("message", eve.getMessage());
    }
}
