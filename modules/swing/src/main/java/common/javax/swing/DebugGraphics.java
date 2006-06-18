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
 * @author Anton Avtamonov
 * @version $Revision$
 */

package javax.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.PrintStream;
import java.text.AttributedCharacterIterator;

public class DebugGraphics extends Graphics {
    public static final int NONE_OPTION = -1;
    public static final int LOG_OPTION = 1;
    public static final int FLASH_OPTION = 2;
    public static final int BUFFERED_OPTION = 4;

    private Graphics originalGraphics;
    private JComponent component;
    private int debugOptions;
    private boolean drawingBuffer;

    private static Color flashColor;
    private static int flashCount;
    private static int flashTime;
    private static PrintStream logStream = System.out;


    public static void setFlashColor(final Color flashColor) {
        DebugGraphics.flashColor = flashColor;
    }

    public static Color flashColor() {
        return flashColor;
    }

    public static void setFlashCount(final int flashCount) {
        DebugGraphics.flashCount = flashCount;
    }

    public static int flashCount() {
        return flashCount;
    }

    public static void setFlashTime(final int flashTime) {
        DebugGraphics.flashTime = flashTime;
    }

    public static int flashTime() {
        return flashTime;
    }

    public static void setLogStream(final PrintStream stream) {
        logStream = stream;
    }

    public static PrintStream logStream() {
        return logStream;
    }


    public DebugGraphics(final Graphics g, final JComponent c) {
        if (g != null) {
            originalGraphics = g.create();
        } else {
            originalGraphics = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB).createGraphics();
        }
        component = c;
    }

    public DebugGraphics(final Graphics g) {
        this(g, null);
    }

    public DebugGraphics() {
        this(null, null);
    }



    public int getDebugOptions() {
        return debugOptions;
    }

    public void setDebugOptions(final int options) {
        debugOptions = options;
    }


    public boolean isDrawingBuffer() {
        return drawingBuffer;
    }


    public void clearRect(final int x, final int y, final int width, final int height) {
        originalGraphics.clearRect(x, y, width, height);
    }

    public void clipRect(final int x, final int y, final int width, final int height) {
        originalGraphics.clipRect(x, y, width, height);
    }

    public void copyArea(final int x, final int y, final int width, final int height, final int destX, final int destY) {
        originalGraphics.copyArea(x, y, width, height, destX, destY);
    }

    public Graphics create() {
        return new DebugGraphics(originalGraphics, component);
    }

    public Graphics create(final int x, final int y, final int width, final int height) {
        return originalGraphics.create(x, y, width, height);
    }

    public void dispose() {
        originalGraphics.dispose();
    }

    public void draw3DRect(final int x, final int y, final int width, final int height, final boolean raised) {
        originalGraphics.draw3DRect(x, y, width, height, raised);
    }

    public void drawArc(final int x, final int y, final int width, final int height, final int startAngle, final int arcAngle) {
        originalGraphics.drawArc(x, y, width, height, startAngle, arcAngle);
    }

    public void drawBytes(final byte[] data, final int offset, final int length, final int x, final int y) {
        originalGraphics.drawBytes(data, offset, length, x, y);
    }

    public void drawChars(final char[] data, final int offset, final int length, final int x, final int y) {
        originalGraphics.drawChars(data, offset, length, x, y);
    }

    public boolean drawImage(final Image img, final int x, final int y, final Color bgcolor, final ImageObserver observer) {
        return originalGraphics.drawImage(img, x, y, bgcolor, observer);
    }

    public boolean drawImage(final Image img, final int x, final int y, final ImageObserver observer) {
        return originalGraphics.drawImage(img, x, y, observer);
    }

    public boolean drawImage(final Image img, final int x, final int y, final int width, final int height, final Color bgcolor, final ImageObserver observer) {
        return originalGraphics.drawImage(img, x, y, width, height, bgcolor, observer);
    }

    public boolean drawImage(final Image img, final int x, final int y, final int width, final int height, final ImageObserver observer) {
        return originalGraphics.drawImage(img, x, y, width, height, observer);
    }

    public boolean drawImage(final Image img, final int dx1, final int dy1, final int dx2, final int dy2, final int sx1, final int sy1, final int sx2, final int sy2, final Color bgcolor, final ImageObserver observer) {
        return originalGraphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
    }

    public boolean drawImage(final Image img, final int dx1, final int dy1, final int dx2, final int dy2, final int sx1, final int sy1, final int sx2, final int sy2, final ImageObserver observer) {
        return originalGraphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
    }

    public void drawLine(final int x1, final int y1, final int x2, final int y2) {
        originalGraphics.drawLine(x1, y1, x2, y2);
    }

    public void drawOval(final int x, final int y, final int width, final int height) {
        originalGraphics.drawOval(x, y, width, height);
    }

    public void drawPolygon(final int[] xPoints, final int[] yPoints, final int nPoints) {
        originalGraphics.drawPolygon(xPoints, yPoints, nPoints);
    }

    public void drawPolyline(final int[] xPoints, final int[] yPoints, final int nPoints) {
        originalGraphics.drawPolyline(xPoints, yPoints, nPoints);
    }

    public void drawRect(final int x, final int y, final int width, final int height) {
        originalGraphics.drawRect(x, y, width, height);
    }

    public void drawRoundRect(final int x, final int y, final int width, final int height, final int arcWidth, final int arcHeight) {
        originalGraphics.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
    }

    public void drawString(final AttributedCharacterIterator iterator, final int x, final int y) {
        originalGraphics.drawString(iterator, x, y);
    }

    public void drawString(final String str, final int x, final int y) {
        originalGraphics.drawString(str, x, y);
    }

    public void fill3DRect(final int x, final int y, final int width, final int height, final boolean raised) {
        originalGraphics.fill3DRect(x, y, width, height, raised);
    }

    public void fillArc(final int x, final int y, final int width, final int height, final int startAngle, final int arcAngle) {
        originalGraphics.fillArc(x, y, width, height, startAngle, arcAngle);
    }

    public void fillOval(final int x, final int y, final int width, final int height) {
        originalGraphics.fillOval(x, y, width, height);
    }

    public void fillPolygon(final int[] xPoints, final int[] yPoints, final int nPoints) {
        originalGraphics.fillPolygon(xPoints, yPoints, nPoints);
    }

    public void fillRect(final int x, final int y, final int width, final int height) {
        originalGraphics.fillRect(x, y, width, height);
    }

    public void fillRoundRect(final int x, final int y, final int width, final int height, final int arcWidth, final int arcHeight) {
        originalGraphics.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
    }

    public Shape getClip() {
        return originalGraphics.getClip();
    }

    public Rectangle getClipBounds() {
        return originalGraphics.getClipBounds();
    }

    public Color getColor() {
        return originalGraphics.getColor();
    }

    public Font getFont() {
        return originalGraphics.getFont();
    }

    public FontMetrics getFontMetrics() {
        return originalGraphics.getFontMetrics();
    }

    public FontMetrics getFontMetrics(final Font f) {
        return originalGraphics.getFontMetrics(f);
    }

    public void setClip(final int x, final int y, final int width, final int height) {
        originalGraphics.setClip(x, y, width, height);
    }

    public void setClip(final Shape shape) {
        originalGraphics.setClip(shape);
    }

    public void setColor(final Color c) {
        originalGraphics.setColor(c);
    }

    public void setFont(final Font font) {
        originalGraphics.setFont(font);
    }

    public void setPaintMode() {
        originalGraphics.setPaintMode();
    }

    public void setXORMode(final Color c) {
        originalGraphics.setXORMode(c);
    }

    public void translate(final int x, final int y) {
        originalGraphics.translate(x, y);
    }
}
