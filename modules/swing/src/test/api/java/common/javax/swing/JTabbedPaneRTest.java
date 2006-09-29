/*
 *  Copyright 2005 - 2006 The Apache Software Foundation or its licensors, as applicable.
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
 * @author Vadim L. Bogdanov
 * @version $Revision$
 */
package javax.swing;

public class JTabbedPaneRTest extends SwingTestCase {
    private JTabbedPane pane;

    public JTabbedPaneRTest(final String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();

        pane = new JTabbedPane();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testAddNull() {
        final JComponent comp1 = new JLabel();
        final JComponent comp3 = new JLabel();
        pane.add("tab1", comp1);
        pane.add("tab2", null);
        pane.add("tab3", comp3);

        assertEquals(2, pane.getComponentCount());
        assertSame(comp1, pane.getComponentAt(0));
        assertNull(pane.getComponentAt(1));
        assertSame(comp3, pane.getComponentAt(2));
    }
}
