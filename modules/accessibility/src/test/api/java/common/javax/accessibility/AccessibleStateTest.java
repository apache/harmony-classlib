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
 * @author Dennis Ushakov
 * @version $Revision$
 */

package javax.accessibility;

import javax.swing.BasicSwingTestCase;

public class AccessibleStateTest extends BasicSwingTestCase {
    private AccessibleState state;

    protected void tearDown() throws Exception {
        state = null;
    }

    public void testAccessibleState() throws Exception {
        String key = "something_unexpected";
        state = new AccessibleState(key);
        assertEquals(key, state.key);
        assertEquals(key, state.toDisplayString());
    }
}

