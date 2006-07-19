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
package javax.swing;

import javax.swing.tree.TreePath;

public class JTree_EmptySelectionModelTest extends BasicSwingTestCase {
    private JTree.EmptySelectionModel model;

    public JTree_EmptySelectionModelTest(final String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        model = new JTree.EmptySelectionModel();
    }

    public void testEmptySelectionModel() throws Exception {
        assertNotNull(model.sharedInstance);
    }

    public void testSharedInstance() throws Exception {
        assertSame(model.sharedInstance(), model.sharedInstance());
        assertSame(model.sharedInstance(), model.sharedInstance);
    }

    public void testSetAddRemoveSelectionPaths() throws Exception {
        model.setSelectionPaths(new TreePath[] {new TreePath("any")});
        assertNull(model.getSelectionPaths());

        model.addSelectionPaths(new TreePath[] {new TreePath("any2")});
        assertNull(model.getSelectionPaths());

        model.removeSelectionPaths(new TreePath[] {new TreePath("any2")});
        assertNull(model.getSelectionPaths());
    }

}
