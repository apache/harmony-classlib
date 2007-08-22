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
 * @author Alexey A. Ivanov
 * @version $Revision$
 */
package javax.swing.text.html;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.event.DocumentEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.LayeredHighlighter;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.Position.Bias;

import org.apache.harmony.x.swing.text.html.HTMLIconFactory;

public class ImageView extends View {
    private static Icon loadingImageIcon;
    private static Icon noImageIcon;

    private AttributeSet attrs;

    private BackgroundImageLoader loader;
    private String src;

    private boolean synchronous = false;

    private Color color;
    
    private int border;
    private int vSpace;
    private int hSpace;

    public ImageView(final Element element) {
        super(element);
        if (element != null) { // Fix for HARMONY-1747, for compatibility with RI
            setPropertiesFromAttributes();
        }
    }

    public Image getImage() {
        return loader.getImage();
    }

    public URL getImageURL() {
        URL base = ((HTMLDocument)getDocument()).getBase();
        return HTML.resolveURL(src, base);
    }

    public Icon getLoadingImageIcon() {
        return HTMLIconFactory.getLoadingImageIcon();
    }

    public Icon getNoImageIcon() {
        return HTMLIconFactory.getNoImageIcon();
    }

    public void setLoadsSynchronously(final boolean synchronous) {
        this.synchronous = synchronous;
    }

    public boolean getLoadsSynchronously() {
        return synchronous;
    }

    public float getPreferredSpan(final int axis) {
        if (loader.isError()) {
            String alt = getAltText();
            FontMetrics metrics = null;
            if (alt != null) {
                Font font = getStyleSheet().getFont(getAttributes());
                metrics = Toolkit.getDefaultToolkit().getFontMetrics(font);                
            }
           
            return axis == X_AXIS ? getNoImageIcon().getIconWidth()
                        + 2 * border + 2 * hSpace
                        + ((metrics == null) ? 0 : metrics.stringWidth(alt))
                        : ((metrics == null) ? getNoImageIcon().getIconHeight()
                                : Math.max(getNoImageIcon().getIconHeight(),metrics.getHeight())
                                + 2 * border + 2 * vSpace);
        }
        if (!loader.isReady()) {
            return axis == X_AXIS ? getLoadingImageIcon().getIconWidth()+ 2 * border + 2 * hSpace
                                  : getLoadingImageIcon().getIconHeight() + 2 * border + 2 * vSpace;
        }
        if (axis == X_AXIS) {
            return loader.getWidth() + 2 * border + 2 * hSpace;
        }
        return loader.getHeight() + 2 * border + 2 * vSpace;
    }

    public String getToolTipText(final float x, final float y,
                                 final Shape shape) {
        return getAltText();
    }

    public String getAltText() {
        return (String)getElement().getAttributes()
                       .getAttribute(HTML.Attribute.ALT);
    }

    public void paint(final Graphics g, final Shape shape) {
        
        Rectangle rc = shape.getBounds();
        rc.setSize(rc.width - 2*(hSpace + border), rc.height - 2*(vSpace + border));
        
        
        
        // TODO change layered highlight painting code
        JTextComponent tc = (JTextComponent)getContainer();
        Highlighter hl = tc.getHighlighter();
        if (hl instanceof LayeredHighlighter) {
            ((LayeredHighlighter)hl).paintLayeredHighlights(g,
                                                            getStartOffset(),
                                                            getEndOffset(),
                                                            shape, tc, this);
        }
        
        Color oldColor = g.getColor();
        g.setColor(color);
        g.fillRect(rc.x + hSpace, rc.y + vSpace, rc.width + 2 * border,
                rc.height + 2 * border);
        g.setColor(oldColor);
        g.fillRect(rc.x + hSpace + border, rc.y + vSpace + border, rc.width,
                rc.height);

        if (loader.isError()) {
            
            g.setColor(color);
            
            getNoImageIcon().paintIcon(null, g, rc.x + hSpace + border, rc.y+vSpace + border);

            String alt = getAltText();
            if (alt != null) {
                Font oldFont = g.getFont();

                Font font = getStyleSheet().getFont(getAttributes());
                g.setFont(font);
                FontMetrics metrics = g.getFontMetrics();
                g.drawString(alt, rc.x + hSpace + border
                        + getNoImageIcon().getIconWidth(), rc.y + vSpace + border+ metrics.getAscent());
                
                g.setFont(oldFont);
            }
            
            g.setColor(oldColor);
            return;
        }

        if (!loader.isReady()) {
            if (!synchronous) {
                getLoadingImageIcon().paintIcon(null, g, rc.x, rc.y);
                return;
            }
        }

        g.drawImage(getImage(), rc.x + hSpace + border, rc.y + vSpace + border, rc.width, rc.height, loader);
    }

    public Shape modelToView(final int pos, final Shape shape, final Bias bias)
        throws BadLocationException {

        Rectangle rc = shape.getBounds();
        if (pos <= getStartOffset()) {
            return new Rectangle(rc.x, rc.y, 0, rc.height);
        }
        return new Rectangle(rc.x + rc.width, rc.y, 0, rc.height);
    }

    public int viewToModel(final float x, final float y, final Shape shape,
                           final Bias[] bias) {

        Rectangle rc = shape.getBounds();
        if (x < rc.x + rc.width/* / 2*/) {
            bias[0] = Bias.Forward;
            return getStartOffset();
        }
        bias[0] = Bias.Backward;
        return getEndOffset();
    }

    public float getAlignment(final int axis) {
        if (axis == Y_AXIS) {
            return 1;
        }
        return super.getAlignment(axis);
    }

    public AttributeSet getAttributes() {
        return attrs;
    }

    public void changedUpdate(final DocumentEvent event, final Shape shape,
                              final ViewFactory factory) {
        setPropertiesFromAttributes();
        super.changedUpdate(event, shape, factory);
    }

    protected StyleSheet getStyleSheet() {
        return ((HTMLDocument)getDocument()).getStyleSheet();
    }

    protected void setPropertiesFromAttributes() {
        attrs = getStyleSheet().getViewAttributes(this);

        AttributeSet elAttrs = getElement().getAttributes();

        src = (String) elAttrs.getAttribute(HTML.Attribute.SRC);
        
        border = getIntProperty(elAttrs,HTML.Attribute.BORDER);
        
        hSpace = getIntProperty(elAttrs,HTML.Attribute.HSPACE);
        
        vSpace = getIntProperty(elAttrs,HTML.Attribute.VSPACE);

        Object size = getAttributes().getAttribute(CSS.Attribute.WIDTH);
        int desiredWidth = -1;
        if (size instanceof CSS.Length) {
            desiredWidth = ((CSS.Length)size).intValue(this);
        }

        size = getAttributes().getAttribute(CSS.Attribute.HEIGHT);
        int desiredHeight = -1;
        if (size instanceof CSS.Length) {
            desiredHeight = ((CSS.Length)size).intValue(this);
        }
        createImage(desiredWidth, desiredHeight);

        color = getStyleSheet().getForeground(getAttributes());
    }
    
    private int getIntProperty(AttributeSet source, HTML.Attribute attr) {
        String result = (String) source.getAttribute(attr);
        // Null verification is added for possibly improved performance:
        // throwing and
        // catching an exception is slower than null verification
        if (result != null) {
            try {
                return Integer.parseInt(result);
            } catch (NumberFormatException nfe) {
                // Ignored, return 0, according to RI's result
            }
        }
        return 0;
    }

    private void createImage(final int desiredWidth, final int desiredHeight) {
        loader = new BackgroundImageLoader(getImageURL(), synchronous,
                                           desiredWidth, desiredHeight) {
            protected void onReady() {
                super.onReady();
                update();
            }

            protected void onError() {
                super.onError();
                update();
            }

            private void update() {
                preferenceChanged(ImageView.this, true, true);
                final Container component = getContainer();
                if (component != null) {
                    component.repaint();
                }
            }
        };
    }
}
