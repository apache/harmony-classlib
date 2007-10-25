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
package org.apache.harmony.awt.gl.image;

import java.awt.Frame;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;

import junit.framework.TestCase;

public class ImageDecoderTest extends TestCase {

    private final int      EXP_WIDTH  = 320;
    private final int      EXP_HEIGHT = 182;

    private final String[] IMG_TYPES  = { "jpg", "gif", "png" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    public void testDecodeImage() throws InterruptedException {
        final Class<? extends ImageDecoderTest> c = getClass();

        for (String type : IMG_TYPES) {
            final String res = "/images/utest." + type; //$NON-NLS-1$
            final URL path = c.getResource(res);

            assertNotNull("Resource not found: " + res, path); //$NON-NLS-1$
            setName(type);
            decodeImage(path);
        }
    }

    private void decodeImage(final URL path) throws InterruptedException {
        final Image im = Toolkit.getDefaultToolkit().createImage(path);
        final BufferedImage bim = new BufferedImage(EXP_WIDTH, EXP_HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        final Frame f = new Frame();
        final MediaTracker t = new MediaTracker(f);

        t.addImage(im, 0);
        t.waitForAll();

        assertEquals(EXP_WIDTH, im.getWidth(null));
        assertEquals(EXP_HEIGHT, im.getHeight(null));

        bim.getGraphics().drawImage(im, 0, 0, null);
        int rgbVal = bim.getRGB(0, 0);
        assertEquals(0xFFFFFFFF, rgbVal);
    }
}
