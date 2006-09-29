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
 * @author Denis M. Kishenko
 * @version $Revision$
 */
package java.awt.geom;

import junit.framework.TestCase;

public class AreaTest extends TestCase {

    public AreaTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public void testConstructor() {
        // Regression test HARMONY-1404
        try {
            new Area(null);
            fail("Expected NPE");
        } catch (NullPointerException e) {
            // expected
        }
    }
    
    public void testContainsPoint() {
        // Regression test HARMONY-1404
        try {
            Area a = new Area();
            a.contains((Point2D)null);
            fail("Expected NPE");
        } catch (NullPointerException e) {
            // expected
        }
    }

    public void testContainsRect() {
        // Regression test HARMONY-1404
        try {
            Area a = new Area();
            a.contains((Rectangle2D)null);
            fail("Expected NPE");
        } catch (NullPointerException e) {
            // expected
        }
    }

    public void testIntersectsRect() {
        // Regression test HARMONY-1404
        try {
            Area a = new Area();
            a.intersects((Rectangle2D)null);
            fail("Expected NPE");
        } catch (NullPointerException e) {
            // expected
        }
    }

}
