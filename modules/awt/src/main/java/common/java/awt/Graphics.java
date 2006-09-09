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
package java.awt;

import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public abstract class Graphics {

    // Constructors

    protected Graphics() {
    }

    // Public methods

    public Graphics create(int x, int y, int width, int height) {
        Graphics res = create();
        res.translate(x, y);
        res.clipRect(0, 0, width, height);
        return res;
    }

    public void draw3DRect(int x, int y, int width, int height, boolean raised) {
        Color colorUp = getColor();
        Color colorDown = colorUp.darker();

        int []xpoints1 = {x, x, x+width+1};
        int []ypoints1 = {y+width+1, y, y};
        int []xpoints2 = {x+width+1, x+width+1, x};
        int []ypoints2 = {y, y+width+1, y+width+1};
        if (raised) {
            drawPolyline(xpoints1, ypoints1, 3);
            setColor(colorDown);
            drawPolyline(xpoints2, ypoints2, 3);
            setColor(colorUp);
        } else {
            drawPolyline(xpoints2, ypoints2, 3);
            setColor(colorDown);
            drawPolyline(xpoints1, ypoints1, 3);
            setColor(colorUp);
        }
    }

    public void drawBytes(byte[] bytes, int off, int len, int x, int y) {
        drawString(new String(bytes, off, len), x, y);
    }

    public void drawChars(char[] chars, int off, int len, int x, int y) {
        drawString(new String(chars, off, len), x, y);
    }

    public void drawPolygon(Polygon p) {
        drawPolygon(p.xpoints, p.ypoints, p.npoints);
    }

    public void drawRect(int x, int y, int width, int height) {
        int []xpoints = {x, x, x+width, x+width};
        int []ypoints = {y, y+height, y+height, y};

        drawPolygon(xpoints, ypoints, 4);
    }

    public void fill3DRect(int x, int y, int width, int height, boolean raised) {
        Color colorUp = getColor();
        Color colorDown = colorUp.darker();

        int []xpoints = {x+width+1, x+width+1, x};
        int []ypoints = {y, y+width+1, y+width+1};
        if (raised) {
            fillRect(x, y, width, height);
            setColor(colorDown);
            drawPolyline(xpoints, ypoints, 3);
            setColor(colorUp);
        } else {
            setColor(colorDown);
            fillRect(x, y, width, height);
            setColor(colorUp);
            drawPolyline(xpoints, ypoints, 3);
        }
    }

    public void fillPolygon(Polygon p) {
        fillPolygon(p.xpoints, p.ypoints, p.npoints);
    }

    @Override
    public void finalize() {
    }

    public Rectangle getClipBounds(Rectangle r) {
        Shape clip = getClip();

        if (clip != null) {
            // TODO: Can we get shape bounds without creating Rectangle object?
            Rectangle b = clip.getBounds();
            r.x = b.x;
            r.y = b.y;
            r.width = b.width;
            r.height = b.height;
        }

        return r;
    }

    /**
     * @deprecated Use {@link #getClipBounds()}
     */
    @Deprecated
    public Rectangle getClipRect() {
        return getClipBounds();
    }

    public FontMetrics getFontMetrics() {
        return getFontMetrics(getFont());
    }

    public boolean hitClip(int x, int y, int width, int height) {
        // TODO: Create package private method Rectangle.intersects(int, int, int, int);
        return getClipBounds().intersects(new Rectangle(x, y, width, height));
    }

    @Override
    public String toString() {
        // TODO: Think about string representation of Graphics.
        return "Graphics";
    }

    // Abstract methods

    public abstract void clearRect(int x, int y, int width, int height);

    public abstract void clipRect(int x, int y, int width, int height);

    public abstract void copyArea(int sx, int sy, int width, int height, int dx, int dy);

    public abstract Graphics create();

    public abstract void dispose();

    public abstract void drawArc(int x, int y, int width, int height, int sa, int ea);

    public abstract boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer);

    public abstract boolean drawImage(Image img, int x, int y, ImageObserver observer);

    public abstract boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer);

    public abstract boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer);

    public abstract boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer);

    public abstract boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer);

    public abstract void drawLine(int x1, int y1, int x2, int y2);

    public abstract void drawOval(int x, int y, int width, int height);

    public abstract void drawPolygon(int[] xpoints, int[] ypoints, int npoints);

    public abstract void drawPolyline(int[] xpoints, int[] ypoints, int npoints);

    public abstract void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight);

    public abstract void drawString(AttributedCharacterIterator iterator, int x, int y);

    public abstract void drawString(String str, int x, int y);

    public abstract void fillArc(int x, int y, int width, int height, int sa, int ea);

    public abstract void fillOval(int x, int y, int width, int height);

    public abstract void fillPolygon(int[] xpoints, int[] ypoints, int npoints);

    public abstract void fillRect(int x, int y, int width, int height);

    public abstract void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight);

    public abstract Shape getClip();

    public abstract Rectangle getClipBounds();

    public abstract Color getColor();

    public abstract Font getFont();

    public abstract FontMetrics getFontMetrics(Font font);

    public abstract void setClip(int x, int y, int width, int height);

    public abstract void setClip(Shape clip);

    public abstract void setColor(Color c);

    public abstract void setFont(Font font);

    public abstract void setPaintMode();

    public abstract void setXORMode(Color color);

    public abstract void translate(int x, int y);
}