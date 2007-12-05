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
 *
 * @date: Nov 22, 2005
 */

package org.apache.harmony.awt.gl.linux;

import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.image.IndexColorModel;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import org.apache.harmony.awt.gl.CommonGraphics2D;
import org.apache.harmony.awt.gl.MultiRectArea;
import org.apache.harmony.awt.gl.Surface;
import org.apache.harmony.awt.gl.Utils;
import org.apache.harmony.awt.gl.XORComposite;
import org.apache.harmony.awt.gl.font.FontManager;
import org.apache.harmony.awt.gl.font.LinuxNativeFont;
import org.apache.harmony.awt.wtk.NativeWindow;
import org.apache.harmony.awt.nativebridge.Int8Pointer;
import org.apache.harmony.awt.nativebridge.NativeBridge;
import org.apache.harmony.awt.nativebridge.linux.X11;
import org.apache.harmony.awt.nativebridge.linux.Xft;
import org.apache.harmony.awt.nativebridge.linux.X11Defs;

public class XGraphics2D extends CommonGraphics2D {
    private static final X11 x11 = X11.getInstance();
    private static final Xft xft = Xft.getInstance();

    long drawable; // X11 window or pixmap
    long display;

    long xftDraw;

    // Context related
    long gc; // X11 GC for basic drawing
    long imageGC; // X11 GC for image operations
    int argb;

    XGraphicsConfiguration xConfig;

    boolean nativeLines = true;
    boolean nativePaint = true;
    boolean transparentColor = false;
    boolean scalingTransform = false;
    boolean simpleComposite = true;

    boolean indexModel = false;

    public XGraphics2D(long drawable, int tx, int ty, MultiRectArea clip) {
        super(tx, ty, clip);
        this.drawable = drawable;
        xConfig = (XGraphicsConfiguration) getDeviceConfiguration();
        display = xConfig.dev.display;
        gc = createGC(display, drawable);

        X11.Visual visual = xConfig.info.get_visual();
        xftDraw = createXftDraw(display, drawable, visual.lock());
        visual.unlock();

        imageGC = createGC(display, drawable);

        //xSetForeground(argb); // Set default foregroung to black

        blitter = XBlitter.getInstance();
        Rectangle bounds = clip.getBounds();
        dstSurf = new XSurface(this, bounds.width, bounds.height);
        if (!FontManager.IS_FONTLIB) {
            jtr = DrawableTextRenderer.inst;
        }

        //setTransformedClip(clip);
        setClip(clip);

        if (xConfig.getColorModel() instanceof IndexColorModel) {
            indexModel = true;
        }
    }

    public XGraphics2D(long drawable, int tx, int ty, int width, int height) {
        this(drawable, tx, ty, new MultiRectArea(new Rectangle(width, height)));
    }

    public XGraphics2D(NativeWindow nwin, int tx, int ty, MultiRectArea clip) {
        this(nwin.getId(), tx, ty, clip);
    }

    public XGraphics2D(NativeWindow nwin, int tx, int ty, int width, int height) {
        this(nwin.getId(), tx, ty, new MultiRectArea(new Rectangle(width, height)));
    }

    public Graphics create() {
        XGraphics2D res = new XGraphics2D(
                drawable,
                origPoint.x, origPoint.y,
                dstSurf.getWidth(), dstSurf.getHeight()
        );
        copyInternalFields(res);
        return res;
    }

    public long createXftDraw(long display, long drawable, long visual){
        long draw = LinuxNativeFont.createXftDrawNative(display, drawable, visual);
        LinuxNativeFont.xftDrawSetSubwindowModeNative(draw, X11Defs.IncludeInferiors);
        return draw;
    }

    private static final long createGC(long display, long win) {
        return x11.XCreateGC(display, win, 0, 0);
    }

    public GraphicsConfiguration getDeviceConfiguration() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        return env.getDefaultScreenDevice().getDefaultConfiguration();
    }

    public void copyArea(int x, int y, int width, int height, int dx, int dy) {
        x += transform.getTranslateX();
        y += transform.getTranslateY();

        x11.XCopyArea(display, drawable, drawable, gc, x, y, width, height, dx+x, dy+y);
    }

    // Caller should free native pointer to rects after using it
    private static final X11.XRectangle createXRects(int[] vertices) {
        int rectsSize = (vertices[0]-1) << 1; // sizeof(XRectangle) = 8

        Int8Pointer rects = NativeBridge.getInstance().createInt8Pointer(rectsSize, true);
        int idx = 0;
        for (int i = 1; i < vertices[0]; i+=4) {
            X11.XRectangle r = x11.createXRectangle(rects.getElementPointer(idx));
            r.set_x((short) vertices[i]);
            r.set_y((short) vertices[i+1]);
            r.set_width((short) (vertices[i+2]-vertices[i]+1));
            r.set_height((short) (vertices[i+3]-vertices[i+1]+1));
            idx += r.size();
        }

        return x11.createXRectangle(rects);
    }

    protected void fillMultiRectAreaColor(MultiRectArea mra) {
        if (transparentColor || !simpleComposite) {
            super.fillMultiRectAreaColor(mra);
        } else {
            int vertices[] = mra.rect;
            int nRects = (vertices[0]-1) >> 2;
            X11.XRectangle xRects = createXRects(vertices);
            x11.XFillRectangles(display, drawable, gc, xRects, nRects);
            xRects.free();
        }
    }

    public void setPaint(Paint paint) {
        if (paint == null)
            return;
            
        if (paint instanceof Color) {
            setColor((Color)paint);
            nativePaint = true;
        } else {
            super.setPaint(paint);
            nativePaint = false;
        }
    }

    public void setColor(Color color) {
        if (color == null)
            return;
        super.setColor(color);

        // Get values for XColor
        int argb_val = color.getRGB();
        if (argb_val != argb) {
            // Check if it is a transparent color
            if ((argb_val & 0xFF000000) != 0xFF000000)
                transparentColor = true;
            else
                transparentColor = false;

            xSetForeground(argb_val);
        }
    }

    private void xSetForeground(int argb_val) {
        // XAllocColor doesn't match closest color,
        // get the exact value from ColorModel
        if (indexModel) {
            IndexColorModel icm = (IndexColorModel) xConfig.getColorModel();
            int pixel = ((int[]) icm.getDataElements(argb_val, new int[]{0}))[0];
            argb_val = icm.getRGB(pixel);
        }

        short xRed = (short) ((argb_val & 0x00FF0000) >> 8);
        short xGreen = (short) (argb_val & 0x0000FF00);
        short xBlue = (short) ((argb_val & 0x000000FF) << 8);

        // Create XColor
        X11.XColor xcolor = x11.createXColor(true);
        xcolor.set_red(xRed);
        xcolor.set_green(xGreen);
        xcolor.set_blue(xBlue);

        // Allocate cmap cell
        x11.XAllocColor(display, xConfig.xcolormap, xcolor);
        x11.XSetForeground(display, gc, xcolor.get_pixel());

        // Cleanup
        xcolor.free();
    }

    public void dispose() {
        super.dispose();

        if (xftDraw != 0) {
            LinuxNativeFont.freeXftDrawNative(this.xftDraw);
            xftDraw = 0;
        }

        if (gc != 0) {
            x11.XFreeGC(display, gc);
            gc = 0;
        }
        if (imageGC != 0) {
            x11.XFreeGC(display, imageGC);
            imageGC = 0;
        }
    }

    void setXClip(MultiRectArea mra, long gc) {
        if (mra == null) {
            resetXClip(gc);
        } else {
            int nRects = mra.getRectCount();
            X11.XRectangle xrects = createXRects(mra.rect);
            x11.XSetClipRectangles(display, gc, 0, 0, xrects, nRects, X11Defs.Unsorted);
            xrects.free();
        }
    }

    void resetXClip(long gc) {
        x11.XSetClipMask(display, gc, X11Defs.None);
    }

    void setXftClip(MultiRectArea mra) {
        if (mra == null){
            resetXftClip();
        } else {

            X11.XRectangle clipXRects = createXRects(mra.rect);
            xft.XftDrawSetClipRectangles(xftDraw, 0, 0, clipXRects, mra.getRectCount());
            clipXRects.free();
        }
    }

    void resetXftClip() {
        xft.XftDrawSetClip(xftDraw, 0);
    }

    protected void setTransformedClip(MultiRectArea clip) {
        super.setTransformedClip(clip);
        if (xftDraw != 0) {
            setXftClip(clip);
        }
        if (gc != 0) {
            setXClip(clip, gc);
        }
    }

    void setGCFunction(int func) {
        x11.XSetFunction(display, gc, func);
    }
    void setImageGCFunction(int func) { // Note: works with imageGC
        x11.XSetFunction(display, imageGC, func);
    }

    Surface getSurface() {
        return dstSurf;
    }

    public void setStroke(Stroke s) {
        super.setStroke(s);
        if (s instanceof BasicStroke) {
            BasicStroke bs = (BasicStroke) s;
            if (bs.getMiterLimit() != 10.f) { // Check if it is same as in xlib
                nativeLines = false;
                return;
            }

            X11.XGCValues gcVals = x11.createXGCValues(true);
            gcVals.set_line_width(Math.round(bs.getLineWidth()));
            gcVals.set_join_style(bs.getLineJoin());
            gcVals.set_cap_style(bs.getEndCap()+1);
            gcVals.set_dash_offset(Math.round(bs.getDashPhase()));

            int n = 0;

            if (bs.getDashArray() == null) {
                gcVals.set_line_style(X11Defs.LineSolid);
                gcVals.set_dashes((byte)1);
            } else {
                gcVals.set_line_style(X11Defs.LineOnOffDash);

                n = bs.getDashArray().length;

                if (n == 1) {
                    gcVals.set_dashes((byte)Math.round(bs.getDashArray()[0]));
                } else {
                    long dashList = Utils.memaccess.malloc(n);
                    float[] dashArray = bs.getDashArray();
                    for (int i = 0; i < n; i++) {
                        Utils.memaccess.setByte(dashList+i, (byte) Math.round(dashArray[i]));
                    }
                    x11.XSetDashes(
                            display,
                            gc,
                            Math.round(bs.getDashPhase()),
                            dashList,
                            bs.getDashArray().length
                    );
                    Utils.memaccess.free(dashList);
                }
            }

            x11.XChangeGC(
                    display,
                    gc,
                    X11Defs.GCLineWidth | X11Defs.GCJoinStyle |
                    X11Defs.GCCapStyle | X11Defs.GCDashOffset |
                    X11Defs.GCLineStyle | (n==1 ? X11Defs.GCDashList : 0),
                    gcVals
            );

            gcVals.free();

            nativeLines = true;
        } else {
            nativeLines = false;
        }
    }

    public void setTransform(AffineTransform transform) {
        super.setTransform(transform);

        if ((transform.getType() & AffineTransform.TYPE_MASK_SCALE) != 0) {
            scalingTransform = true;
        } else {
            scalingTransform = false;
        }
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        if (
                nativeLines && nativePaint &&
                !scalingTransform && !transparentColor &&
                simpleComposite
        ) {
            float points[] = new float[]{x1, y1, x2, y2};
            transform.transform(points, 0, points, 0, 2);
            x11.XDrawLine(
                    display,
                    drawable,
                    gc,
                    (int) points[0], (int) points[1],
                    (int) points[2], (int) points[3]
            );
            if (composite instanceof XORComposite) {
                XORComposite xor = (XORComposite)composite;
                Color xorcolor = xor.getXORColor();
                xSetForeground(xorcolor.getRGB());
                x11.XDrawLine(
                        display,
                        drawable,
                        gc,
                        (int) points[0], (int) points[1],
                        (int) points[2], (int) points[3]
                );
                xSetForeground(fgColor.getRGB());
            }
        } else {
            super.drawLine(x1, y1, x2, y2);
        }
    }

    public void drawPolygon(int[] xpoints, int[] ypoints, int npoints) {
        if (
                nativeLines && nativePaint &&
                !scalingTransform && !transparentColor &&
                simpleComposite
        ) {
            float points[] = new float[npoints<<1];
            int i;
            for (i = 0; i < npoints; i++) {
                points[i<<1] = xpoints[i];
                points[(i<<1) + 1] = ypoints[i];
            }
            transform.transform(points, 0, points, 0, npoints);

            // Create XPoint's
            long xPoints = Utils.memaccess.malloc((npoints+1) << 2); // sizeof XPoint = 4
            long ptr = xPoints;

            for (i = 0; i < npoints; i++) {
                Utils.memaccess.setShort(ptr, (short) points[i<<1]);
                Utils.memaccess.setShort(ptr+2, (short) points[(i<<1)+1]);
                ptr += 4; // sizeof XPoint = 4
            }
            // Add first point again to close path
            Utils.memaccess.setShort(ptr, (short) points[0]);
            Utils.memaccess.setShort(ptr+2, (short) points[1]);

            x11.XDrawLines(
                    display,
                    drawable,
                    gc,
                    xPoints,
                    npoints+1,
                    X11Defs.CoordModeOrigin
            );

            Utils.memaccess.free(xPoints);
        } else {
            super.drawPolygon(xpoints, ypoints, npoints);
        }
    }

    public void drawPolygon(Polygon polygon) {
        drawPolygon(polygon.xpoints, polygon.ypoints, polygon.npoints);
    }

    public void drawPolyline(int[] xpoints, int[] ypoints, int npoints) {
        if (
                nativeLines && nativePaint &&
                !scalingTransform && !transparentColor &&
                simpleComposite
        ) {
            float points[] = new float[npoints<<1];
            for (int i = 0; i < npoints; i++) {
                points[i<<1] = xpoints[i];
                points[(i<<1) + 1] = ypoints[i];
            }
            transform.transform(points, 0, points, 0, npoints);

            // Create XPoint's
            long xPoints = Utils.memaccess.malloc((npoints) << 2); // sizeof XPoint = 4
            long ptr = xPoints;

            for (int i = 0; i < npoints; i++) {
                Utils.memaccess.setShort(ptr, (short) points[i<<1]);
                Utils.memaccess.setShort(ptr+2, (short) points[(i<<1)+1]);
                ptr += 4; // sizeof XPoint = 4
            }

            x11.XDrawLines(
                    display,
                    drawable,
                    gc,
                    xPoints,
                    npoints,
                    X11Defs.CoordModeOrigin
            );

            Utils.memaccess.free(xPoints);
        } else {
            super.drawPolyline(xpoints, ypoints, npoints);
        }
    }

    public void drawRect(int x, int y, int width, int height) {
        if (
                nativeLines && nativePaint &&
                !transparentColor && simpleComposite &&
                (transform.getType() & AffineTransform.TYPE_TRANSLATION) != 0
        ) {
            Point2D rectOrig = new Point2D.Float(x, y);
            transform.transform(rectOrig, rectOrig);
            x11.XDrawRectangle(
                    display,
                    drawable,
                    gc,
                    (int) rectOrig.getX(), (int) rectOrig.getY(),
                    width, height
            );
        } else {
            super.drawRect(x, y, width, height);
        }
    }

    public void drawArc(int x, int y, int width, int height, int sa, int ea) {
        if (
                nativeLines && nativePaint &&
                !transparentColor && simpleComposite &&
                (transform.getType() & AffineTransform.TYPE_TRANSLATION) != 0
        ) {
            Point2D orig = new Point2D.Float(x, y);
            transform.transform(orig, orig);
            x11.XDrawArc(
                    display,
                    drawable,
                    gc,
                    (int) orig.getX(), (int) orig.getY(),
                    width, height,
                    sa << 6, ea << 6
            );
        } else {
            super.drawArc(x, y, width, height, sa, ea);
        }
    }

    public void drawOval(int x, int y, int width, int height) {
        drawArc(x, y, width, height, 0, 360);
    }

    @Override
    public void setXORMode(Color color) {
        super.setXORMode(color);
        x11.XSetFunction(display, gc, X11Defs.GXxor);
        simpleComposite = true;
    }

    @Override
    public void setPaintMode() {
        setComposite(AlphaComposite.SrcOver);
    }

    public void setComposite(Composite composite) {
        super.setComposite(composite);
        if (composite instanceof AlphaComposite) {
            AlphaComposite acomp = (AlphaComposite) composite;
            int rule = acomp.getRule();
            float srca = acomp.getAlpha();

            switch(rule){
                case AlphaComposite.CLEAR:
                case AlphaComposite.SRC_OUT:
                    x11.XSetFunction(display, gc, X11Defs.GXclear);                
                    simpleComposite = true;
                    break;

                case AlphaComposite.SRC:
                case AlphaComposite.SRC_IN:
                    if(srca == 0.0f) x11.XSetFunction(display, gc, X11Defs.GXclear);
                    else x11.XSetFunction(display, gc, X11Defs.GXcopy);
                    simpleComposite = true;
                    break;

                case AlphaComposite.DST:
                case AlphaComposite.DST_OVER:
                    x11.XSetFunction(display, gc, X11Defs.GXnoop);                
                    simpleComposite = true;
                    break;

                case AlphaComposite.SRC_ATOP:
                case AlphaComposite.SRC_OVER:
                    x11.XSetFunction(display, gc, X11Defs.GXcopy);                
                    if(srca == 1.0f){
                        simpleComposite = true;
                    }else{
                        simpleComposite = false;
                    }
                    break;

                case AlphaComposite.DST_IN:
                case AlphaComposite.DST_ATOP:
                    if(srca != 0.0f){
                        x11.XSetFunction(display, gc, X11Defs.GXnoop);                
                    } else {
                        x11.XSetFunction(display, gc, X11Defs.GXclear);                
                    }
                    simpleComposite = true;
                    break;

                case AlphaComposite.DST_OUT:
                case AlphaComposite.XOR:
                    if(srca != 1.0f){
                        x11.XSetFunction(display, gc, X11Defs.GXnoop);                
                    } else {
                        x11.XSetFunction(display, gc, X11Defs.GXclear);                
                    }
                    simpleComposite = true;
                    break;
            }
        } else {
            simpleComposite = false;
        }
    }

    @Override
    public void drawString(String str, float x, float y) {
        AffineTransform at = (AffineTransform)this.getTransform().clone();
        AffineTransform fontTransform = font.getTransform();
        at.concatenate(fontTransform);

        if (!at.isIdentity()){
            // TYPE_TRANSLATION
            if (at.getType() == AffineTransform.TYPE_TRANSLATION){
                jtr.drawString(this, str,
                        (float)(x+fontTransform.getTranslateX()),
                        (float)(y+fontTransform.getTranslateY()));
                return;
            }
            // TODO: we use slow type of drawing strings when Font object
            // in Graphics has transforms, we just fill outlines. New textrenderer
            // is to be implemented.
            Shape sh = font.createGlyphVector(this.getFontRenderContext(), str).getOutline(x, y);
            fill(sh);
        } else {
            jtr.drawString(this, str, x, y);
        }
    }

    @Override
    public void drawGlyphVector(GlyphVector gv, float x, float y) {

        AffineTransform at = gv.getFont().getTransform();

        double[] matrix = new double[6];
        if ((at != null) && (!at.isIdentity())){

            int atType = at.getType();
            at.getMatrix(matrix);

            // TYPE_TRANSLATION
            if ((atType == AffineTransform.TYPE_TRANSLATION) &&
                ((gv.getLayoutFlags() & GlyphVector.FLAG_HAS_TRANSFORMS) == 0)){
                jtr.drawGlyphVector(this, gv, (int)(x+matrix[4]), (int)(y+matrix[5]));
                return;
            }
        } else {
            if (((gv.getLayoutFlags() & GlyphVector.FLAG_HAS_TRANSFORMS) == 0)){
                jtr.drawGlyphVector(this, gv, x, y);
                return;
            }
        }

        // TODO: we use slow type of drawing strings when Font object
        // in Graphics has transforms, we just fill outlines. New textrenderer
        // is to be implemented.

        Shape sh = gv.getOutline(x, y);
        this.fill(sh);

    }
}
