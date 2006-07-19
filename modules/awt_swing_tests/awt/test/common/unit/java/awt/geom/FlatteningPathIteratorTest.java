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
 * @author Denis M. Kishenko
 * @version $Revision$
 */
package java.awt.geom;

import java.awt.Rectangle;

import junit.framework.TestCase;

public class FlatteningPathIteratorTest extends TestCase {

    PathIterator p;
    FlatteningPathIterator fp;

    public FlatteningPathIteratorTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        p = new Rectangle(1, 2, 3, 4).getPathIterator(null);
        fp = new FlatteningPathIterator(p, 10, 5);
    }

    protected void tearDown() throws Exception {
        fp = null;
        super.tearDown();
    }

    public void testCreate() {
        try {
            new FlatteningPathIterator(p, -1, 5);
            fail("FlatteningPathIterator should throw exception IllegalArgumentException");
        } catch(IllegalArgumentException e) {
        }

        try {
            new FlatteningPathIterator(p, 1, -5);
            fail("FlatteningPathIterator should throw exception IllegalArgumentException");
        } catch(IllegalArgumentException e) {
        }
    }

    public void testGetFlatness() {
        assertEquals("Flatness", 10, fp.getFlatness(), 0.0);
    }

    public void testGetRecursionLimit() {
        assertEquals("Limit", 5, fp.getRecursionLimit(), 0.0);
    }

    public void testGetWindingRule() {
        assertEquals("Rule", p.getWindingRule(), fp.getWindingRule());
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(FlatteningPathIteratorTest.class);
    }

}
