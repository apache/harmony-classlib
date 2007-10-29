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

package java.awt.image;

import java.awt.Image;
import java.awt.image.BufferedImage;

import junit.framework.TestCase;

public class BufferedImageTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(BufferedImageTest.class);
    }
    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Constructor for BufferedImageTest.
     * @param name
     */
    public BufferedImageTest(String name) {
        super(name);
    }
    
    public final void testGetWritableTile(){
        BufferedImage bi = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        bi.getWritableTile(1, 1);
        
        //Regression test for HARMONY-1658
        BufferedImage img = new BufferedImage(10, 16, BufferedImage.TYPE_4BYTE_ABGR);
        try {
            img.isTileWritable(1,1);
            fail("IllegalArgumentException is expected");
        } catch (IllegalArgumentException iae) {
        }
    }

    public void testGetProperty() {
        // Regression test HARMONY-1656
        BufferedImage img = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        assertEquals("Image.UndefinedProperty",
                     Image.UndefinedProperty, img.getProperty("XXX"));
    }

}
