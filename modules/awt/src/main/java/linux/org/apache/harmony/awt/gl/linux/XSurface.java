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
/**
 * @author Oleg V. Khaschansky
 * @version $Revision$
 */

package org.apache.harmony.awt.gl.linux;

import org.apache.harmony.awt.nativebridge.NativeBridge;
import org.apache.harmony.awt.nativebridge.linux.X11;
import org.apache.harmony.awt.nativebridge.linux.X11Defs;
import org.apache.harmony.misc.accessors.LockedArray;

import java.awt.image.*;
import java.awt.geom.Rectangle2D;

import org.apache.harmony.awt.gl.*;

public class XSurface extends Surface {
    private static final X11 x11 = X11.getInstance();

    //int width, height; // XXX - todo - use from superclass

    XGraphics2D g2d;

    private BufferedImage lastSnapshot = null;
    boolean needServerData = true;

    private Rectangle2D roi; // Rectangle of interest

    private ImageSurface imageSurface;

    // Cached parameters for XCreateImage
    boolean cachedXCIParams = false;
    int depthXCI;
    int offsetXCI;
    int formatXCI;
    int bitmapPadXCI;
    int bytesPerLineXCI;


    XSurface(XGraphics2D g2d, int width, int height) {
        this.g2d = g2d;
        this.width = width;
        this.height = height;
        roi = new Rectangle2D.Float(0, 0, width, height);
    }

    void setRoi(Rectangle2D roi) {
        if (roi.getX() < 0 || roi.getY() < 0) {
            double x = roi.getX();
            double y = roi.getY();
            double width = roi.getWidth();
            double height = (int) roi.getHeight();
            if (x < 0) {
                width += x;
                x = 0;
            }
            if (y < 0) {
                height += y;
                y = 0;
            }

            this.roi = new Rectangle2D.Double(x, y, width, height);
        } else {
            this.roi = roi;
        }
    }

    public ColorModel getColorModel() {
        return g2d.xConfig.getColorModel();
    }

    public WritableRaster getRaster() {
        if (needServerData) {
            long pixmap = x11.XCreatePixmap(
                    g2d.display,
                    x11.XRootWindow(g2d.display, g2d.xConfig.dev.screen),
                    (int) roi.getWidth(), (int) roi.getHeight(),
                    g2d.xConfig.info.get_depth()
            );

            x11.XCopyArea(
                    g2d.display,
                    g2d.drawable,
                    pixmap,
                    g2d.imageGC,
                    (int) (g2d.getTransform().getTranslateX() + roi.getX()),
                    (int) (g2d.getTransform().getTranslateY() + roi.getY()),
                    (int) roi.getWidth(), (int) roi.getHeight(),
                    0, 0
            );

            if (!cachedXCIParams) {
                long xImagePtr = x11.XGetImage(
                        g2d.display,
                        pixmap,
                        0, 0,
                        1, 1,
                        ~(0L), // All bits set to 1, should be same as XAllPlanes() result
                        X11Defs.ZPixmap
                );

                if (xImagePtr == 0) // Check obtained XImage pointer
                    return null;

                X11.XImage xTmpImage = x11.createXImage(xImagePtr);
                depthXCI = xTmpImage.get_depth();
                formatXCI = xTmpImage.get_format();
                offsetXCI = xTmpImage.get_xoffset();
                bitmapPadXCI = xTmpImage.get_bitmap_pad();
                bytesPerLineXCI = xTmpImage.get_bytes_per_line();
                xTmpImage.get_f().destroy_image(xTmpImage);

                cachedXCIParams = true;
            }

            X11.Visual visual = g2d.xConfig.info.get_visual();

            long xImagePtr = x11.XCreateImage(
                    g2d.display,
                    visual.lock(),
                    depthXCI,
                    formatXCI,
                    offsetXCI,
                    Utils.memaccess.malloc(height*width*bytesPerLineXCI),
                    width, height,
                    bitmapPadXCI,
                    0
            );
            visual.unlock();

            X11.XImage xImage = x11.createXImage(xImagePtr);
            xImage.set_byte_order(X11Defs.LSBFirst);

            xImage = x11.XGetSubImage(
                    g2d.display,
                    pixmap,
                    0, 0,
                    (int) roi.getWidth(), (int) roi.getHeight(),
                    ~(0L), // All bits set to 1, should be same as XAllPlanes() result
                    X11Defs.ZPixmap,
                    xImage,
                    (int) (roi.getX()),
                    (int) (roi.getY())
            );

            lastSnapshot = XVolatileImage.biFromXImage(xImage, g2d.xConfig);

            // Cleanup
            xImage.get_f().destroy_image(xImage);
        } else {
            lastSnapshot = g2d.xConfig.createCompatibleImage(width, height);
        }
        return lastSnapshot.getRaster();
    }

    void putImage(MultiRectArea clip) {
        putImage(
                clip,
                lastSnapshot.getRaster(),
                (int) g2d.getTransform().getTranslateX(),
                (int) g2d.getTransform().getTranslateY(),
                width, height
        );
    }

    void putImage(
            MultiRectArea clip, Raster r,
            int dstX, int dstY,
            int dstWidth, int dstHeight
    ) {
        if (r == null) // Just blit last snapshot
            r = lastSnapshot.getRaster();

        Object data;
        AwtImageBackdoorAccessor dbAccess = AwtImageBackdoorAccessor.getInstance();
        data = dbAccess.getData(r.getDataBuffer());
        LockedArray lockedData = Utils.arraccess.lockArrayShort(data);

        SampleModel sm = r.getSampleModel();
        int scanlineStride;
        if (sm instanceof ComponentSampleModel) {
            scanlineStride = ((ComponentSampleModel) sm).getScanlineStride();
        } else if (sm instanceof SinglePixelPackedSampleModel) {
            scanlineStride = ((SinglePixelPackedSampleModel) sm).getScanlineStride();
        } else if (sm instanceof MultiPixelPackedSampleModel) {
            scanlineStride = ((MultiPixelPackedSampleModel) sm).getScanlineStride();
        } else {
            return;
        }

        int pad;
        if (data instanceof byte[]) {
            pad = 8;
        } else if (data instanceof short[]) {
            pad = 16;
            scanlineStride *= 2;
        } else if (data instanceof int[]) {
            pad = 32;
            scanlineStride *= 4;
        } else {
            return;
        }

        X11.Visual visual = g2d.xConfig.info.get_visual();

        long xImagePtr = x11.XCreateImage(
                g2d.display,
                visual.lock(),
                g2d.xConfig.info.get_depth(),
                X11Defs.ZPixmap,
                0,
                lockedData.getAddress(),
                r.getWidth(),
                r.getHeight(),
                pad,
                scanlineStride
        );
        visual.unlock();

        g2d.setXClip(clip, g2d.imageGC);

        X11.XImage xImage = x11.createXImage(xImagePtr);
        xImage.set_byte_order(X11Defs.LSBFirst); // Set byte order explicitly

        x11.XPutImage(
                g2d.display,
                g2d.drawable,
                g2d.imageGC,
                xImagePtr,
                0, 0,
                dstX, dstY,
                dstWidth, dstHeight
        );

        g2d.resetXClip(g2d.imageGC);

        lockedData.release();

        xImage.set_data(NativeBridge.getInstance().createInt8Pointer(0, true));
        xImage.get_f().destroy_image(xImage);
    }

    public void dispose() {
        return;
    }

    public XGraphics2D getGraphics() {
        return g2d;
    }

    /*
    public int getWidth() { // XXX - todo - use from superclass
        return this.width;
    }

    public int getHeight() { // XXX - todo - use from superclass
        return this.height;
    }
    */

    public long lock() {
        return 0;
    }

    public void unlock() {
    }

    public boolean isNativeDrawable() {
        return true;
    }

    public int getSurfaceType() {
        return BufferedImage.TYPE_CUSTOM;
    }

    public Surface getImageSurface() {
        if (imageSurface == null) {
            imageSurface = new ImageSurface(getColorModel(), getRaster());
        } else {
            imageSurface.setRaster(getRaster());
        }

        return imageSurface;
    }

    protected void finalize() throws Throwable {
        imageSurface.dispose();
    }
}
