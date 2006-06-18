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
 * @author Alexey A. Petrenko
 * @version $Revision$
 */
package org.apache.harmony.awt.gl.linux;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;

import org.apache.harmony.awt.gl.CommonGraphics2DFactory;
import org.apache.harmony.awt.gl.MultiRectArea;
import org.apache.harmony.awt.gl.font.FontManager;
import org.apache.harmony.awt.gl.font.LinuxFont;
import org.apache.harmony.awt.gl.font.LinuxFontManager;
import org.apache.harmony.awt.wtk.NativeWindow;
import org.apache.harmony.awt.wtk.WindowFactory;
import org.apache.harmony.awt.wtk.linux.LinuxWindowFactory;


/**
 * Graphics2D factory for Linux
 *
 */
public class LinuxGraphics2DFactory extends CommonGraphics2DFactory {
    static {
        inst = new LinuxGraphics2DFactory();
    }
    public Graphics2D getGraphics2D(NativeWindow nw, int tx, int ty, MultiRectArea clip) {
        return new XGraphics2D(nw, tx, ty, clip);
    }

    public Graphics2D getGraphics2D(NativeWindow nw, int tx, int ty, int width, int height) {
        return new XGraphics2D(nw, tx, ty, width, height);
    }

    public GraphicsEnvironment createGraphicsEnvironment(WindowFactory wf) {
        return new XGraphicsEnvironment((LinuxWindowFactory) wf);
    }

    public FontManager getFontManager() {
        return LinuxFontManager.inst;
    }

    public Font embedFont(String fontFilePath) {
        return LinuxFont.embedFont(fontFilePath);
    }

}