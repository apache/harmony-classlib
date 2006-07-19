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
package java.awt;

import java.awt.Point;

public class PointTest extends SerializeTestCase {

    public PointTest(String name) {
        super(name);
        serializePath = getSerializePath(Point.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCreate1() {
        assertEquals(new Point(0, 0), new Point());
    }

    public void testCreate2() {
        assertEquals(new Point(1, 2), new Point(new Point(1, 2)));
    }

    public void testGetX() {
        assertEquals(1, (int)new Point(1, 2).getX());
    }

    public void testGetY() {
        assertEquals(2, (int)new Point(1, 2).getY());
    }

    public void testGetLocation() {
        assertEquals(new Point(1, 2), new Point(1, 2).getLocation());
    }

    public void testSetLocation1() {
        Point p = new Point(1, 2);
        p.setLocation(3, 4);
        assertEquals(new Point(3, 4), p);
    }

    public void testSetLocation2() {
        Point p = new Point(1, 2);
        p.setLocation(3.0, 4.0);
        assertEquals(new Point(3, 4), p);
        p.setLocation(5.3, 6.7);
        assertEquals(new Point(5, 7), p);
        p.setLocation(7.5, 8.5);
        assertEquals(new Point(8, 9), p);
    }

    public void testSetLocation3() {
        Point p = new Point(1, 2);
        p.setLocation(new Point(3, 4));
        assertEquals(new Point(3, 4), p);
    }

    public void testMove() {
        Point p = new Point(1, 2);
        p.move(3, 4);
        assertEquals(new Point(3, 4), p);
    }

    public void testTranslate() {
        Point p = new Point(1, 2);
        p.translate(3, 4);
        assertEquals(new Point(4, 6), p);
    }

    public void testToString() {
        assertEquals("java.awt.Point[x=1,y=2]", new Point(1, 2).toString());
    }

    public void testEquals() {
        assertTrue(new Point(1, 2).equals(new Point(1, 2)));
        assertFalse(new Point(3, 2).equals(new Point(1, 2)));
        assertFalse(new Point(1, 3).equals(new Point(1, 2)));
        assertFalse(new Point(3, 3).equals(new Point(1, 2)));
    }

    public void testSerializeRead() {
        assertTrue(checkRead(new Point()));
        assertTrue(checkRead(new Point(1, 2)));
    }

    public void testSerializeWrite() {
        assertTrue(checkWrite(new Point()));
        assertTrue(checkWrite(new Point(1, 2)));
    }

    public void createSerializeTemplates() {
        saveSerialized(new Point());
        saveSerialized(new Point(1, 2));
    }

    public static void main(String[] args) {
//        junit.textui.TestRunner.run(PointTest.class);
        new PointTest("").createSerializeTemplates();
    }

}
