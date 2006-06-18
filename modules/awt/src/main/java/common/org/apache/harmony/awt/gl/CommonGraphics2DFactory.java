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
 * @author Alexey A. Petrenko, Ilya S. Okomin
 * @version $Revision$
 */
package org.apache.harmony.awt.gl;


import java.awt.*;
import java.awt.peer.FontPeer;
import java.io.IOException;

import org.apache.harmony.awt.gl.font.FontMetricsImpl;
import org.apache.harmony.awt.gl.font.FontProperty;
import org.apache.harmony.awt.wtk.GraphicsFactory;


/**
 * Common GraphicsFactory implementation
 *
 */
public abstract class CommonGraphics2DFactory implements GraphicsFactory {
    
    // static instanse of CommonGraphics2DFactory
    public static CommonGraphics2DFactory inst = null;

    /**
     * Returns FontMetrics object that keeps metrics of the specified font.
     * 
     * @param font specified Font
     * @return FontMetrics object corresponding to the specified Font object
     */
    public FontMetrics getFontMetrics(Font font) {
        FontMetrics fm;
        for (int i=0; i < cacheFM.length; i++){
            fm = cacheFM[i];
            if (fm == null){
                break;
            }

            if (fm.getFont().equals(font)){
                return fm;
            }
        }
        fm = new FontMetricsImpl(font);

        System.arraycopy(cacheFM, 0, cacheFM, 1, cacheFM.length -1);
        cacheFM[0] = fm;

        return fm;
    }
    
    /**
     * Returns native dependent FontPeer object applicable to the specified font.
     * 
     * @param font specified Font
     * @return FontPeer object
     */
    public FontPeer createFontPeer(Font font) {
        StringBuffer init = new StringBuffer();
        init.append(font.getName());
        init.append(font.getStyle());
        init.append(font.getSize());
        return getFontManager().setFont(init.toString(), font);
    }

    /**
     * Manages resources of the FontPeer corresponding to the specified font.
     * If there is no other Font objects that links on the applicaple FontPeer than
     * this font peer is to be disposed, otherwise links counter in the FontPeer 
     * object is decreased by one. 
     * 
     * @param font specified Font
     */
    public void freeFontPeer(Font font) {
        StringBuffer init = new StringBuffer();
        init.append(font.getName());
        init.append(font.getStyle());
        init.append(font.getSize());
        getFontManager().deleteFont(init.toString());
    }

    /**
     * Returns native dependent FontPeer object applicable to the specified 
     * FontProperty object and size of the font. 
     * !! This constructor mostly used for LinuxFont creation where font 
     * families names in font properties lowercased. 
     * 
     * @param font specified Font
     * @return FontPeer object
     */
    public FontPeer createFontPeer(FontProperty fp, int size){
        StringBuffer init = new StringBuffer();
        init.append(fp.getName());
        init.append(fp.getStyle());
        init.append(size);
        return getFontManager().setFont(init.toString(), fp, size);
    }
    
    /**
     * Embeds font from gile with specified path into the system. 
     * 
     * @param fontFilePath path to the font file 
     * @return Font object that was created from the file.
     */
    public abstract Font embedFont(String fontFilePath);

}