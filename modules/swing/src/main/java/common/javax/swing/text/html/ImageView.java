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
import java.awt.Component;
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

    //TODO We can load images only synchronously yet
    private boolean synchronous = true;

    private Color color;

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

            return axis == X_AXIS
                   ? getNoImageIcon().getIconWidth()
                     + (metrics != null ? metrics.stringWidth(alt) : 0)
                   : Math.max(getNoImageIcon().getIconHeight(),
                     (metrics != null ? metrics.getHeight() : 0));
        }
        if (!loader.isReady()) {
            return axis == X_AXIS ? getLoadingImageIcon().getIconWidth()
                                  : getLoadingImageIcon().getIconHeight();
        }
        if (axis == X_AXIS) {
            return loader.getWidth();
        }
        return loader.getHeight();
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

        // TODO change layered highlight painting code
        JTextComponent tc = (JTextComponent)getContainer();
        Highlighter hl = tc.getHighlighter();
        if (hl instanceof LayeredHighlighter) {
            ((LayeredHighlighter)hl).paintLayeredHighlights(g,
                                                            getStartOffset(),
                                                            getEndOffset(),
                                                            shape, tc, this);
        }

        if (loader.isError()) {
            getNoImageIcon().paintIcon(null, g, rc.x, rc.y);
            Color oldColor = g.getColor();
            g.setColor(color);

            String alt = getAltText();
            if (alt != null) {
                Font oldFont = g.getFont();

                Font font = getStyleSheet().getFont(getAttributes());
                g.setFont(font);
                FontMetrics metrics = g.getFontMetrics();
                g.drawString(alt, rc.x + getNoImageIcon().getIconWidth(),
                             rc.y + metrics.getAscent());

                g.setFont(oldFont);
            }
            g.drawRect(rc.x, rc.y, rc.width - 1, rc.height - 1);
            g.setColor(oldColor);
            return;
        }

        if (!loader.isReady()) {
            if (!synchronous) {
                getLoadingImageIcon().paintIcon(null, g, rc.x, rc.y);
                return;
            }
        }

        g.drawImage(getImage(), rc.x, rc.y, rc.width, rc.height, loader);
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

        src = (String)getElement().getAttributes()
                      .getAttribute(HTML.Attribute.SRC);

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

    private void createImage(final int desiredWidth, final int desiredHeight) {
        loader = new BackgroundImageLoader(getImageURL(), synchronous,
                                           desiredWidth, desiredHeight) {
            protected void onReady() {
                super.onReady();
                preferenceChanged(ImageView.this, true, true);
            }

            protected void onError() {
                super.onError();
                preferenceChanged(ImageView.this, true, true);
            }
        };
    }
}
