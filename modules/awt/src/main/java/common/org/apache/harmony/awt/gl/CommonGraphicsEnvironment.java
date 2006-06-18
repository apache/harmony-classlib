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
 * @author Alexey A. Petrenko, Oleg V. Khaschansky
 * @version $Revision$
 */
package org.apache.harmony.awt.gl;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Locale;

import org.apache.harmony.awt.gl.image.BufferedImageGraphics2D;


/**
 * Common GraphicsEnvironment implementation
 *
 */
public abstract class CommonGraphicsEnvironment extends GraphicsEnvironment {

    public Graphics2D createGraphics(BufferedImage bufferedImage) {
        return new BufferedImageGraphics2D(bufferedImage);
    }

    public String[] getAvailableFontFamilyNames(Locale locale) {
        Font[] fonts = getAllFonts();
        ArrayList familyNames = new ArrayList();

        for (int i=0;i<fonts.length;i++) {
            String name = fonts[i].getFamily(locale);
            if (!familyNames.contains(name)) {
                familyNames.add(name);
            }
        }

        String res[] = new String[familyNames.size()];
        return (String[]) familyNames.toArray(res);
    }

    public Font[] getAllFonts() {
        return CommonGraphics2DFactory.inst.getFontManager().getAllFonts();
    }

    public String[] getAvailableFontFamilyNames() {
        return CommonGraphics2DFactory.inst.getFontManager().getAllFamilies();
    }
}
