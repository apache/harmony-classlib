/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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

public class AreaTest extends PathIteratorTestCase {

    public AreaTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
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
    
    public void testGetPathIterator() {
        // Regression test HARMONY-1860
        Area a = new Area();
        PathIterator path = a.getPathIterator(null);
        checkPathRule(path, PathIterator.WIND_NON_ZERO);
        checkPathDone(path, true);
    }
    
    public void testCreateTransformedArea() {
        // Regression test HARMONY-1880
        AffineTransform t = AffineTransform.getScaleInstance(2, 3);
        Area a1 = new Area();        
        Area a2 = a1.createTransformedArea(t);
        PathIterator path = a2.getPathIterator(null);
        checkPathRule(path, PathIterator.WIND_NON_ZERO);
        checkPathDone(path, true);
    }

}
