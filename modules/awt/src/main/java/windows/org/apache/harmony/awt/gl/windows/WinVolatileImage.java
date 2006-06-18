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
package org.apache.harmony.awt.gl.windows;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.ImageCapabilities;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import org.apache.harmony.awt.gl.GLVolatileImage;
import org.apache.harmony.awt.gl.Surface;
import org.apache.harmony.awt.gl.windows.BitmapSurface;
import org.apache.harmony.awt.wtk.NativeWindow;


/**
 * Volatile image for Windows
 *
 */
public class WinVolatileImage extends GLVolatileImage {
    private final long hwnd;
    long gi;
    private final int width;
    private final int height;
    Surface surface;
    
    private WinGraphicsConfiguration gc = null;
    
   /***************************************************************************
    *
    *  Constructors
    *
    ***************************************************************************/
    public WinVolatileImage(NativeWindow nw, int width, int height) {
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Illegal size of volatile image.");
        hwnd = nw.getId();
        this.width = width;
        this.height = height;
        gi = WinGDIPGraphics2D.createCompatibleImageInfo(hwnd, width, height);
        surface = new BitmapSurface(gi , width, height);
    }

    public WinVolatileImage(WinGraphicsConfiguration gc, int width, int height) {
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Illegal size of volatile image.");
        
        hwnd = 0;
        this.gc = gc;
        this.width = width;
        this.height = height;
        gi = WinGDIPGraphics2D.createCompatibleImageInfo(((WinGraphicsDevice)gc.getDevice()).getIDBytes(), width, height);
        surface = new BitmapSurface(gi , width, height);
    }

    public boolean contentsLost() {
        return gi == 0;
    }

    public Graphics2D createGraphics() {
        if (gi == 0) {
            if (hwnd != 0 && gc == null) {
                gi = WinGDIPGraphics2D.createCompatibleImageInfo(hwnd, width, height);
            } else if (hwnd == 0 && gc != null) {
                gi = WinGDIPGraphics2D.createCompatibleImageInfo(((WinGraphicsDevice)gc.getDevice()).getIDBytes(), width, height);
            }
        }
        return new WinGDIPGraphics2D(this, width, height);
    }

    public ImageCapabilities getCapabilities() {
        return new ImageCapabilities(false);
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage getSnapshot() {
        return null;
    }

    public int getWidth() {
        return width;
    }

    public int validate(GraphicsConfiguration gc) {
        if (gi != 0)
            return IMAGE_OK;
        
        gi = WinGDIPGraphics2D.createCompatibleImageInfo(((WinGraphicsDevice)gc.getDevice()).getIDBytes(), width, height);        
        return IMAGE_RESTORED;
    }

    public Object getProperty(String name, ImageObserver observer) {
        return UndefinedProperty;
    }

    public int getWidth(ImageObserver observer) {
        return width;
    }

    public int getHeight(ImageObserver observer) {
        return height;
    }

    protected void finalize() throws Throwable {
        flush();
        super.finalize();
    }

    public Surface getImageSurface(){
        return surface;
    }

    public void flush() {
        if (gi != 0) {
            WinGDIPGraphics2D.disposeGraphicsInfo(gi);
            gi = 0;
        }
        super.flush();
    }
}
