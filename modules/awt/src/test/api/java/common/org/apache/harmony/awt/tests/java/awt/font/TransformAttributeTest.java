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

package org.apache.harmony.awt.tests.java.awt.font;

import java.awt.font.TransformAttribute;
import java.awt.geom.AffineTransform;

import junit.framework.TestCase;

public class TransformAttributeTest extends TestCase {

    public TransformAttributeTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'java.awt.font.TransformAttribute.TransformAttribute(AffineTransform)'
     */
    public final void testTransformAttribute() {
        TransformAttribute ta;
        AffineTransform at = AffineTransform.getRotateInstance(1);
        ta = new TransformAttribute(at);
        assertEquals(at, ta.getTransform());

        // check if transform cloned
        at.translate(10, 10);
        assertFalse(at.equals(ta.getTransform()));
        
        try{
            ta = new TransformAttribute(null);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    /*
     * Test method for 'java.awt.font.TransformAttribute.getTransform()'
     */
    public final void testGetTransform() {
        AffineTransform at = AffineTransform.getRotateInstance(1);
        TransformAttribute ta = new TransformAttribute(at);
        assertEquals(at, ta.getTransform());
    }

    /*
     * Test method for 'java.awt.font.TransformAttribute.isIdentity()'
     */
    public final void testIsIdentity() {
        TransformAttribute ta = new TransformAttribute(new AffineTransform());
        assertTrue(ta.isIdentity());

        AffineTransform at = AffineTransform.getRotateInstance(1);
        ta = new TransformAttribute(at);
        assertFalse(ta.isIdentity());
}

}
