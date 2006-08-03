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
 * @author Sergey Burlak
 * @version $Revision$
 */

package org.apache.harmony.x.swing.plaf.resources.basic;

import java.util.Collections;
import java.util.ResourceBundle;

import javax.swing.BasicSwingTestCase;

public class BasicResourceBundleTest extends BasicSwingTestCase {
    private static final String RESOURCE_BUNDLE = "org.apache.harmony.x.swing.plaf.resources.basic.BasicResourceBundle";

    public void testKeys() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE);
        assertFalse(Collections.list(bundle.getKeys()).isEmpty());
        if (isHarmony()) {
            assertEquals(94, Collections.list(bundle.getKeys()).size());
        }
    }
}
