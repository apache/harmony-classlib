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
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.io.PrintStream;
import java.text.AttributedCharacterIterator;
import java.util.HashMap;

import org.apache.harmony.x.swing.Utilities;

public class DebugGraphics extends Graphics {
    private abstract static class FlashAction extends AbstractAction {
        private Graphics g;
        public FlashAction(final Graphics g) {
            this.g = g;
        }
        
        public void actionPerformed(final ActionEvent e) {
            for (int i = 0; i < flashCount; i++) {
                Color oldColor = g.getColor();
                paint(g);
                sleep(flashTime);
                g.setColor(flashColor);
                paint(g);
                sleep(flashTime);
                g.setColor(oldColor);
            }
        }        
        
        private void sleep(final int millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                
            }
        }
        public abstract void paint(final Graphics g);
    }
    
    private abstract class BufferedDrawAction extends AbstractAction {        
        public void actionPerformed(final ActionEvent e) {
            if (externalFrames == null) {
                externalFrames = new HashMap();
            }
            Frame externalFrame = (Frame)externalFrames.get(component);
            if (externalFrame == null || !externalFrame.isDisplayable()) {
                externalFrame = new Frame();
                externalFrames.put(component, externalFrame);                            
                externalFrame.setVisible(true);
            }
            if (component != null) {
                externalFrame.setSize(Utilities.addInsets(component.getSize(), externalFrame.getInsets()));
            } else {
                externalFrame.setSize(200, 200);
            }
            
            Graphics g = externalFrame.getGraphics();
            g.clearRect(0, 0, 200, 200);
            externalFrame.paint(g);
            g.translate(externalFrame.getInsets().left, externalFrame.getInsets().top);
            ((Graphics2D)g).setTransform(((Graphics2D)originalGraphics).getTransform());
            
            g.setColor(originalGraphics.getColor());                        
            
            if (isFlashing()) {
                new FlashAction(g) {
                    public void paint(Graphics g) {
                        BufferedDrawAction.this.paint(g);
                    }                    
                }.actionPerformed(null);
            }         
            g.drawRect(0, 0, 40, 40);
            paint(g);
        }                
        public abstract void paint(final Graphics g);
    }
    
    public static final int NONE_OPTION = -1;
    public static final int LOG_OPTION = 1;
    public static final int FLASH_OPTION = 2;
    public static final int BUFFERED_OPTION = 4;

    private static int debugGraphicsCount; 
    private static HashMap externalFrames;
    
    private Graphics originalGraphics;
    private JComponent component;
    private int debugOptions;
    private int thisNumber;
    private boolean drawingBuffer;
    
    private static Color flashColor = Color.RED;
    private static int flashCount = 2;
    private static int flashTime = 100;
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
        originalGraphics = g.create();
        component = c;
        thisNumber = debugGraphicsCount++;
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
        boolean wasLogging = isLogging();
        if (options == NONE_OPTION) {
            debugOptions = 0;
        } else {
            debugOptions |= options;
        }
        if (isLogging() && !wasLogging) {
            log("logging enabled");
        }
        if (!isLogging() && wasLogging) {
            log("logging disabled");
        }
    }

    public boolean isDrawingBuffer() {
        return drawingBuffer;
    }

    public void clearRect(final int x, final int y, final int width, final int height) {
        if (isLogging()) {
            log("Clearing rect: " + new Rectangle(x, y, width, height));
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.fillRect(x, y, width, height);    
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
            new BufferedDrawAction() {
                public void paint(Graphics g) {
                    g.fillRect(x, y, width, height);
                }                
            }.actionPerformed(null);
        }
        originalGraphics.clearRect(x, y, width, height);
    }

    public void clipRect(final int x, final int y, final int width, final int height) {
        if (isLogging()) {
            log("Setting clipRect: " + new Rectangle(x, y, width, height));
        }
        if (isFlashing()) {
        }
        if (isBuffered()) {
        }
        originalGraphics.clipRect(x, y, width, height);
    }

    public void copyArea(final int x, final int y, final int width, final int height, final int destX, final int destY) {
        if (isLogging()) {
            log("Copying area from: " + new Rectangle(x, y, width, height) + " to : " + new Point(destX, destY));
        }
        if (isFlashing()) {
            fillRect(x, y, width, height);
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.fillRect(x, y, width, height);    
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
        }
        originalGraphics.copyArea(x, y, width, height, destX, destY);
    }

    public Graphics create() {
        DebugGraphics result = new DebugGraphics(originalGraphics, component);
        result.setDebugOptions(debugOptions);
        return result;
    }

    public Graphics create(final int x, final int y, final int width, final int height) {
        DebugGraphics result = new DebugGraphics(originalGraphics.create(x, y, width, height), component);
        result.setDebugOptions(debugOptions);
        return result;
    }

    public void dispose() {
        originalGraphics.dispose();
    }

    public void draw3DRect(final int x, final int y, final int width, final int height, final boolean raised) {
        if (isLogging()) {
            log("Drawing 3D rect: " + new Rectangle(x, y, width, height) + ", raised bezel: " + raised);
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.draw3DRect(x, y, width, height, raised);    
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
        }
        originalGraphics.draw3DRect(x, y, width, height, raised);
    }

    public void drawArc(final int x, final int y, final int width, final int height, final int startAngle, final int arcAngle) {
        if (isLogging()) {
            log("Drawing arc: " + new Rectangle(x, y, width, height) + ", startAngle: " + startAngle + ", arcAngle: " + arcAngle);
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.drawArc(x, y, width, height, startAngle, arcAngle);    
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
        }
        originalGraphics.drawArc(x, y, width, height, startAngle, arcAngle);
    }

    public void drawBytes(final byte[] data, final int offset, final int length, final int x, final int y) {
        if (isLogging()) {
            log("Drawing bytes at: " + new Point(x, y));
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.drawBytes(data, offset, length, x, y);    
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
        }
        originalGraphics.drawBytes(data, offset, length, x, y);
    }

    public void drawChars(final char[] data, final int offset, final int length, final int x, final int y) {
        if (isLogging()) {
            log("Drawing chars at: " + new Point(x, y));
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.drawChars(data, offset, length, x, y);    
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
        }
        originalGraphics.drawChars(data, offset, length, x, y);
    }

    public boolean drawImage(final Image img, final int x, final int y, final Color bgcolor, final ImageObserver observer) {
        if (isLogging()) {
            log("Drawing image: " + img + " at " + new Point(x, y) + ", bgcolor: " + bgcolor);
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.fillRect(x, y, img.getHeight(observer), img.getHeight(observer));    
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
        }
        return originalGraphics.drawImage(img, x, y, bgcolor, observer);
    }

    public boolean drawImage(final Image img, final int x, final int y, final ImageObserver observer) {
        if (isLogging()) {
            log("Drawing image: " + img + " at " + new Point(x, y));
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.fillRect(x, y, img.getHeight(observer), img.getHeight(observer));    
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
        }
        return originalGraphics.drawImage(img, x, y, observer);
    }

    public boolean drawImage(final Image img, final int x, final int y, final int width, final int height,
                             final Color bgcolor, final ImageObserver observer) {

        if (isLogging()) {
            log("Drawing image: " + img + " at " + new Rectangle(x, y, width, height) + ", bgcolor: " + bgcolor);
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.fillRect(x, y, img.getHeight(observer), img.getHeight(observer));    
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
        }
        return originalGraphics.drawImage(img, x, y, width, height, bgcolor,
                                          observer);
    }

    public boolean drawImage(final Image img, final int x, final int y, final int width, final int height,
                             final ImageObserver observer) {
        if (isLogging()) {
            log("Drawing image: " + img + " at " + new Rectangle(x, y, width, height));
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.fillRect(x, y, img.getHeight(observer), img.getHeight(observer));    
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
        }
        return originalGraphics.drawImage(img, x, y, width, height, observer);
    }

    public boolean drawImage(final Image img, final int dx1, final int dy1, final int dx2, final int dy2, 
                             final int sx1, final int sy1, final int sx2, final int sy2,
                             final Color bgcolor, final ImageObserver observer) {
        if (isLogging()) {
            log("Drawing image: " + img + ", destination: " + new Rectangle(dx1, dy1, dx2, dy2) + ", source: "
                + new Rectangle(sx1, sy1, sx2, sy2) + ", bgcolor: " + bgcolor);
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.fillRect(dx1, dy1, dx2 - dx1, dy2 - dy1);    
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
        }
        return originalGraphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
    }

    public boolean drawImage(final Image img, final int dx1, final int dy1, final int dx2, final int dy2,
                             final int sx1, final int sy1, final int sx2, final int sy2,
                             final ImageObserver observer) {

        if (isLogging()) {
            log("Drawing image: " + img + ", destination: " + new Rectangle(dx1, dy1, dx2, dy2) + ", source: "
                + new Rectangle(sx1, sy1, sx2, sy2));
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.fillRect(dx1, dy1, dx2 - dx1, dy2 - dy1);    
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
        }
        return originalGraphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
    }

    public void drawLine(final int x1, final int y1, final int x2, final int y2) {
        if (isLogging()) {
            log("Drawing line: from (" + x1 + ", " + y1 + ") to (" + x2 + ", " + y2 + ")");
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.drawLine(x1, y1, x2, y2);
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {            
            new BufferedDrawAction() {
                public void paint(Graphics g) {
                    g.drawLine(x1, y1, x2, y2);                    
                }                
            }.actionPerformed(null);
        }
        originalGraphics.drawLine(x1, y1, x2, y2);
    }

    public void drawOval(final int x, final int y, final int width, final int height) {
        if (isLogging()) {
            log("Drawing oval: " + new Rectangle(x, y, width, height));
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.drawOval(x, y, width, height);
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
        }
        originalGraphics.drawOval(x, y, width, height);
    }

    public void drawPolygon(final int[] xPoints, final int[] yPoints, final int nPoints) {
        if (isLogging()) {
            log("Drawing polygon: " + new Polygon(xPoints, yPoints, nPoints));
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.drawPolygon(xPoints, yPoints, nPoints);
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
        }
        originalGraphics.drawPolygon(xPoints, yPoints, nPoints);
    }

    public void drawPolyline(final int[] xPoints, final int[] yPoints, final int nPoints) {
        if (isLogging()) {
            log("Drawing polyline: " + new Polygon(xPoints, yPoints, nPoints));
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.drawPolyline(xPoints, yPoints, nPoints);
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
        }
        originalGraphics.drawPolyline(xPoints, yPoints, nPoints);
    }

    public void drawRect(final int x, final int y, final int width, final int height) {
        if (isLogging()) {
            log("Drawing rectangle: " + new Rectangle(x, y, width, height));
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.drawRect(x, y, width, height);
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
        }
        originalGraphics.drawRect(x, y, width, height);
    }

    public void drawRoundRect(final int x, final int y, final int width, final int height, final int arcWidth, final int arcHeight) {
        if (isLogging()) {
            log("Drawing round rectangle: " + new Rectangle(x, y, width, height) + ", arcWidth " + arcWidth + ", arcHeight " + arcHeight);
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
        }
        originalGraphics.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
    }

    public void drawString(final AttributedCharacterIterator iterator, final int x, final int y) {
        if (isLogging()) {
            log("Drawing string: " + iterator + " at " + new Point(x, y));
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.drawString(iterator, x, y);
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
        }
        originalGraphics.drawString(iterator, x, y);
    }

    public void drawString(final String str, final int x, final int y) {
        if (isLogging()) {
            log("Drawing string: " + str + " at " + new Point(x, y));
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.drawString(str, x, y);
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
        }
        originalGraphics.drawString(str, x, y);
    }

    public void fill3DRect(final int x, final int y, final int width, final int height, final boolean raised) {
        if (isLogging()) {
            log("Filling 3D rect: " + new Rectangle(x, y, width, height) + ", raised bezel: " + raised);
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.fill3DRect(x, y, width, height, raised);
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
        }
        originalGraphics.fill3DRect(x, y, width, height, raised);
    }

    public void fillArc(final int x, final int y, final int width, final int height, final int startAngle, final int arcAngle) {
        if (isLogging()) {
            log("Filling arc: " + new Rectangle(x, y, width, height) + ", startAngle: " + startAngle + ", arcAngle: " + arcAngle);
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.fillArc(x, y, width, height, startAngle, arcAngle);    
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
        }
        originalGraphics.fillArc(x, y, width, height, startAngle, arcAngle);
    }

    public void fillOval(final int x, final int y, final int width, final int height) {
        if (isLogging()) {
            log("Filling oval: " + new Rectangle(x, y, width, height));
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.fillOval(x, y, width, height);    
                }                
            }.actionPerformed(null);          
        }
        if (isBuffered()) {
        }
        originalGraphics.fillOval(x, y, width, height);
    }

    public void fillPolygon(final int[] xPoints, final int[] yPoints, final int nPoints) {
        if (isLogging()) {
            log("Filling polygon: " + new Polygon(xPoints, yPoints, nPoints));
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.fillPolygon(xPoints, yPoints, nPoints);   
                }                
            }.actionPerformed(null);           
        }
        if (isBuffered()) {
        }
        originalGraphics.fillPolygon(xPoints, yPoints, nPoints);
    }

    public void fillRect(final int x, final int y, final int width, final int height) {
        if (isLogging()) {
            log("Filling rectangle: " + new Rectangle(x, y, width, height));
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.fillRect(x, y, width, height);    
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
            new BufferedDrawAction() {
                public void paint(Graphics g) {
                    g.fillRect(x, y, width, height);
                }                
            }.actionPerformed(null);
        }
        originalGraphics.fillRect(x, y, width, height);
    }    

    public void fillRoundRect(final int x, final int y, final int width, final int height, final int arcWidth,final int arcHeight) {
        if (isLogging()) {
            log("Filling round rectangle: " + new Rectangle(x, y, width, height) + ", arcWidth " + arcWidth + ", arcHeight " + arcHeight);
        }
        if (isFlashing()) {
            new FlashAction(originalGraphics) {
                public void paint(Graphics g) {
                    g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);    
                }                
            }.actionPerformed(null);
        }
        if (isBuffered()) {
        }
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
        if (isLogging()) {
            log("Setting new clipRect: " + new Rectangle(x, y, width, height));
        }
        if (isFlashing()) {
        }
        if (isBuffered()) {
        }
        originalGraphics.setClip(x, y, width, height);
    }

    public void setClip(final Shape shape) {
        if (isLogging()) {
            log("Setting clip: " + shape);
        }
        if (isFlashing()) {
        }
        if (isBuffered()) {
        }
        originalGraphics.setClip(shape);
    }

    public void setColor(final Color c) {
        if (isLogging()) {
            log("Setting color: " + c);
        }
        if (isFlashing()) {
        }
        if (isBuffered()) {
        }
        originalGraphics.setColor(c);
    }

    public void setFont(final Font font) {
        if (isLogging()) {
            log("Setting font: " + font);
        }
        if (isFlashing()) {
        }
        if (isBuffered()) {
        }
        originalGraphics.setFont(font);
    }

    public void setPaintMode() {
        if (isLogging()) {
            log("Setting paint mode");
        }
        if (isFlashing()) {
        }
        if (isBuffered()) {
        }       
        originalGraphics.setPaintMode();
    }

    public void setXORMode(final Color c) {
        if (isLogging()) {
            log("Setting XOR mode, color : " + c);
        }
        if (isFlashing()) {
        }
        if (isBuffered()) {
        }       
        originalGraphics.setXORMode(c);
    }

    public void translate(final int x, final int y) {
        if (isLogging()) {
            log("Translating by: " + new Point(x, y));
        }
        if (isFlashing()) {
        }
        if (isBuffered()) {
        }
        originalGraphics.translate(x, y);
    }

    private String getName() {
        return "DebugGraphics(" + thisNumber + ", mode " + debugOptions + ")";
    }

    private void log(final String text) {
        logStream.println(getName() + " " + text);
    }
    
    private boolean isLogging() {
        return (debugOptions & LOG_OPTION) != 0;
    }

    private boolean isFlashing() {
        return (debugOptions & FLASH_OPTION) != 0;
    }

    private boolean isBuffered() {
//        return (debugOptions & BUFFERED_OPTION) != 0;
        return false;
    }
}
