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
 * @author Alexey A. Ivanov
 * @version $Revision$
 */
package javax.swing.text;

import junit.framework.TestCase;

/**
 * Tests the Position interface (actually checks Bias values only as
 * the Position interface itself is too simple and there is nothing to test).
 *
 */
public class PositionTest extends TestCase {

    public PositionTest() {
        super();
    }

    public PositionTest(final String name) {
        super(name);
    }

    /*
    public void testGetOffset() {
        // Too simple to break
    }
    */

    public void testBiasForward() {
        assertEquals("Forward", Position.Bias.Forward.toString());
    }

    public void testBiasBackward() {
        assertEquals("Backward", Position.Bias.Backward.toString());
    }

}
