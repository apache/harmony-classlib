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

package java.awt;

import java.awt.image.BufferedImage;

import junit.framework.TestCase;

public class Graphics2DTest extends TestCase {
    private Frame frame;
    
    protected void setUp() throws Exception {
        super.setUp();
        frame = new Frame();
        frame.addNotify();
    }

    protected void tearDown() throws Exception {
        if (frame != null) {
            frame.dispose();
        }        
        super.tearDown();
    }
    
    public void testSetPaintScreen() {
        // regression test for HARMONY-1448        
        Graphics2D g2d = (Graphics2D) frame.getGraphics();
        Paint paint = g2d.getPaint();
        assertNotNull(paint);
        g2d.setPaint(null);
        assertNotNull(g2d.getPaint());        
    }
    
    public void testSetPaintImage() {
        // regression test for HARMONY-1448
        int imgType = BufferedImage.TYPE_INT_ARGB;
        BufferedImage img = new BufferedImage(100, 100, imgType);
        Graphics2D g2d = (Graphics2D) img.getGraphics();
        Paint paint = g2d.getPaint();
        assertNotNull(paint);
        g2d.setPaint(null);
        assertNotNull(g2d.getPaint());        
    }

}
