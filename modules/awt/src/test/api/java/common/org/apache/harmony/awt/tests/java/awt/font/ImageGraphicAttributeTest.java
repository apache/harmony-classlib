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

package org.apache.harmony.awt.tests.java.awt.font;

import java.awt.Image;
import java.awt.font.GlyphJustificationInfo;
import java.awt.font.GraphicAttribute;
import java.awt.font.ImageGraphicAttribute;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import junit.framework.TestCase;

public class ImageGraphicAttributeTest extends TestCase {
    ImageGraphicAttribute iga;
    int width =10;
    int height = 10;
    Image img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    int alignment =  GraphicAttribute.ROMAN_BASELINE;
    
    public ImageGraphicAttributeTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'java.awt.font.ImageGraphicAttribute.getAdvance()'
     */
    public final void testGetAdvance() {
        float xOrigin = 5;
        float yOrigin = 5;
        float xOrigin1 = 15;

        iga = new ImageGraphicAttribute(img, alignment, xOrigin, yOrigin);
        assertEquals(img.getWidth(null) - xOrigin, iga.getAdvance());

        iga = new ImageGraphicAttribute(img, alignment, xOrigin1, yOrigin);
        assertEquals((float)0, iga.getAdvance());
    }

    /*
     * Test method for 'java.awt.font.ImageGraphicAttribute.getAscent()'
     */
    public final void testGetAscent() {
        float yOrigin = 5;
        float xOrigin = 5;
        float yOrigin1 = -5;

        iga = new ImageGraphicAttribute(img, alignment, xOrigin, yOrigin);
        assertEquals(yOrigin, iga.getAscent());

        iga = new ImageGraphicAttribute(img, alignment, xOrigin, yOrigin1);
        assertEquals((float)0, iga.getAscent());
    }

    /*
     * Test method for 'java.awt.font.ImageGraphicAttribute.getBounds()'
     */
    public final void testGetBounds() {
        float yOrigin = 5;
        float xOrigin = 5;
        iga = new ImageGraphicAttribute(img, alignment, xOrigin, yOrigin);
        assertEquals(new Rectangle2D.Float(-xOrigin, -yOrigin, img.getWidth(null), img.getHeight(null)), 
                iga.getBounds());

        iga = new ImageGraphicAttribute(img, alignment);
        assertEquals(new Rectangle2D.Float(0, 0, img.getWidth(null), img.getHeight(null)), 
                iga.getBounds());

    }

    /*
     * Test method for 'java.awt.font.ImageGraphicAttribute.getDescent()'
     */
    public final void testGetDescent() {
        float yOrigin = 5;
        float xOrigin = 5;
        float yOrigin1 = 15;

        iga = new ImageGraphicAttribute(img, alignment, xOrigin, yOrigin);
        assertEquals(img.getHeight(null) - yOrigin, iga.getDescent());

        iga = new ImageGraphicAttribute(img, alignment, xOrigin, yOrigin1);
        assertEquals((float)0, iga.getDescent());
        
    }

    /*
     * Test method for 'java.awt.font.ImageGraphicAttribute.ImageGraphicAttribute(Image, int, float, float)'
     */
    public final void testImageGraphicAttributeImageInt() {
        ImageGraphicAttribute igAttribute = new ImageGraphicAttribute(img, alignment);
        assertNotNull(igAttribute);
        assertEquals(alignment, igAttribute.getAlignment());
        assertEquals((float)width, igAttribute.getAdvance());
        assertEquals((float)0, igAttribute.getAscent());
        assertEquals((float)height, igAttribute.getDescent());
        assertEquals(new Rectangle2D.Float(0, 0, img.getWidth(null), img.getHeight(null)), 
                igAttribute.getBounds());

        // illegal alignment value
        try {
            iga = new ImageGraphicAttribute(img, -3);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            iga = new ImageGraphicAttribute(img, 3);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    /*
     * Test method for 'java.awt.font.ImageGraphicAttribute.ImageGraphicAttribute(Image, int)'
     */
    public final void testImageGraphicAttributeImageIntFloatFloat() {
        float xOrigin = 5;
        float yOrigin = 5;
        ImageGraphicAttribute igAttribute = new ImageGraphicAttribute(img, alignment, xOrigin, yOrigin);
        assertNotNull(igAttribute);
        assertEquals(alignment, igAttribute.getAlignment());
        assertEquals((float)width - xOrigin, igAttribute.getAdvance());
        assertEquals(yOrigin, igAttribute.getAscent());
        assertEquals((float)height - yOrigin, igAttribute.getDescent());
        assertEquals(new Rectangle2D.Float(-xOrigin, -yOrigin, img.getWidth(null), img.getHeight(null)), 
                igAttribute.getBounds());

        // illegal alignment value
        try {
            iga = new ImageGraphicAttribute(img, -3, xOrigin, yOrigin);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            iga = new ImageGraphicAttribute(img, 3, xOrigin, yOrigin);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    /*
     * Test method for 'java.awt.font.ImageGraphicAttribute.equals(ImageGraphicAttribute)'
     */
    public final void testEqualsImageGraphicAttribute() {
        iga = new ImageGraphicAttribute(img, alignment);
        ImageGraphicAttribute iga1 = new ImageGraphicAttribute(img, alignment);
        assertEquals(iga, iga1);
    }

    /*
     * Test method for 'java.awt.font.ImageGraphicAttribute.equals(Object)'
     */
    public final void testEqualsObject() {
        iga = new ImageGraphicAttribute(img, alignment);
        ImageGraphicAttribute iga1 = new ImageGraphicAttribute(img, alignment);
        assertEquals(iga, (Object)iga1);
    }

    /*
     * Test method for 'java.awt.font.GraphicAttribute.getAlignment()'
     */
    public final void testGetAlignment() {
        iga = new ImageGraphicAttribute(img, alignment);
        assertEquals(alignment, iga.getAlignment());
    }

    /*
     * Test method for 'java.awt.font.GraphicAttribute.getJustificationInfo()'
     */
    public final void testGetJustificationInfo() {
        iga = new ImageGraphicAttribute(img, alignment);
        float advance = iga.getAdvance();
        GlyphJustificationInfo gji = new GlyphJustificationInfo(
                advance,
                false,
                GlyphJustificationInfo.PRIORITY_INTERCHAR,
                advance / 3,
                advance / 3,
                false,
                GlyphJustificationInfo.PRIORITY_WHITESPACE,
                0,
                0);
        equalsGlyphJustificationInfo(gji, iga.getJustificationInfo());

    }
    
    private boolean equalsGlyphJustificationInfo(GlyphJustificationInfo info1, GlyphJustificationInfo info2){
        assertEquals("weight", info1.weight, info2.weight);
        assertEquals("growAbsorb", info1.growAbsorb, info2.growAbsorb);
        assertEquals("growPriority", info1.growPriority, info2.growPriority);
        assertEquals("growLeftLimit", info1.growLeftLimit, info2.growLeftLimit);
        assertEquals("growRightLimit", info1.growRightLimit, info2.growRightLimit);
        assertEquals("shrinkAbsorb", info1.shrinkAbsorb, info2.shrinkAbsorb);
        assertEquals("shrinkPriority", info1.shrinkPriority, info2.shrinkPriority);
        assertEquals("shrinkLeftLimit", info1.shrinkLeftLimit, info2.shrinkLeftLimit);
        assertEquals("shrinkRightLimit", info1.shrinkRightLimit, info2.shrinkRightLimit);
        
        return true;
    }
    
}
