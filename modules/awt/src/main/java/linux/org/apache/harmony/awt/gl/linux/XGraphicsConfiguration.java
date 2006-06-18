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
 * @author Oleg V. Khaschansky
 * @version $Revision$
 *
 * @date: Nov 15, 2005
 */

package org.apache.harmony.awt.gl.linux;

import org.apache.harmony.awt.nativebridge.Int8Pointer;
import org.apache.harmony.awt.nativebridge.NativeBridge;
import org.apache.harmony.awt.nativebridge.linux.X11;
import org.apache.harmony.awt.nativebridge.linux.X11Defs;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.awt.geom.AffineTransform;


public class XGraphicsConfiguration extends GraphicsConfiguration {
    private static final X11 x11 = X11.getInstance();

    XGraphicsDevice dev;
    X11.XVisualInfo info;
    ColorModel cm = null;

    long xcolormap;

    XGraphicsConfiguration(XGraphicsDevice dev, X11.XVisualInfo info) {
        this.dev = dev;
        this.info = info;
        xcolormap = x11.XDefaultColormap(dev.display, dev.screen);
    }

    public GraphicsDevice getDevice() {
        return dev;
    }

    public Rectangle getBounds() {
        return new Rectangle(dev.getDisplayWidth(), dev.getDisplayHeight());
    }

    public AffineTransform getDefaultTransform() {
        return new AffineTransform();
    }

    public AffineTransform getNormalizingTransform() {
        return new AffineTransform(); // XXX - todo
    }

    public BufferedImage createCompatibleImage(int width, int height) {
        return new BufferedImage(
                getColorModel(),
                getColorModel().createCompatibleWritableRaster(width, height),
                false,
                null
        );
    }

    public BufferedImage createCompatibleImage(int width, int height, int transparency) {
        return new BufferedImage(
                getColorModel(transparency),
                getColorModel(transparency).createCompatibleWritableRaster(width, height),
                false,
                null
        );
    }

    public ColorModel getColorModel() {
        if (cm == null) {
            switch (info.get_class()) {
                case X11Defs.TrueColor:
                case X11Defs.DirectColor:
                    cm = new DirectColorModel(
                            info.get_depth(),
                            (int) info.get_red_mask(),
                            (int) info.get_green_mask(),
                            (int) info.get_blue_mask()
                    );
                    break;
                case X11Defs.StaticGray:
                    // This should be enough
                    cm = new ComponentColorModel(
                            ColorSpace.getInstance(ColorSpace.CS_GRAY),
                            new int[]{info.get_depth()},
                            false,
                            false,
                            Transparency.OPAQUE,
                            DataBuffer.TYPE_BYTE
                    );
                    break;
                case X11Defs.PseudoColor:
                case X11Defs.GrayScale:
                case X11Defs.StaticColor: {
                    // Always use default colormap to create ColorModel.
                    // This should be sufficient for the first time.
                    int defCmapSize =
                            ((XGraphicsConfiguration)
                            dev.getDefaultConfiguration()).info.get_colormap_size();

                    // Create array of XColor and fill pixel values
                    Int8Pointer xColors =
                            NativeBridge.getInstance().createInt8Pointer(
                                    X11.XColor.sizeof * defCmapSize,
                                    true
                            );
                    int xColorsIdx = 0;
                    for (int i = 0; i < defCmapSize; i++, xColorsIdx += X11.XColor.sizeof) {
                        X11.XColor color = x11.createXColor(xColors.getElementPointer(xColorsIdx));
                        color.set_pixel(i);
                    }

                    // Get the rgb values from the default colormap and create cm
                    x11.XQueryColors(dev.display, xcolormap, xColors.lock(), defCmapSize);
                    xColors.unlock();

                    byte redVals[] = new byte[defCmapSize];
                    byte greenVals[] = new byte[defCmapSize];
                    byte blueVals[] = new byte[defCmapSize];

                    xColorsIdx = 0;
                    for (int i = 0; i < defCmapSize; i++, xColorsIdx += X11.XColor.sizeof) {
                        X11.XColor color = x11.createXColor(xColors.getElementPointer(xColorsIdx));
                        redVals[i] = (byte) ((color.get_red() >> 8) & 0xFF);
                        greenVals[i] = (byte) ((color.get_green() >> 8) & 0xFF);
                        blueVals[i] = (byte) ((color.get_blue() >> 8) & 0xFF);
                    }

                    cm = new IndexColorModel(
                            info.get_depth(),
                            defCmapSize,
                            redVals, greenVals, blueVals,
                            -1
                    );

                    xColors.free();
                    break;
                }
                default:
                    throw new InternalError("Unknown visual class");
            }
        }

        return cm;
    }

    public ColorModel getColorModel(int transparency) {
        switch (transparency) {
            case Transparency.OPAQUE:
                return getColorModel();
            case Transparency.TRANSLUCENT:
            case Transparency.BITMASK:
                // Transparency unsupported yet, return default model again
                return getColorModel();
            default:
                throw new IllegalArgumentException("Invalid transparency");
        }
    }

    public VolatileImage createCompatibleVolatileImage(int width, int height) {
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException(
                    "Dimensions of the image should be positive"
            );

        return new XVolatileImage(this, width, height);
    }

    public VolatileImage createCompatibleVolatileImage(
            int width, int height,
            int transparency
    ) {
        // XXX - todo - implement transparency
        return createCompatibleVolatileImage(width, height);
    }
}
