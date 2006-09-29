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
package javax.swing.text;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SizeRequirements;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentEvent.ElementChange;
import javax.swing.text.Position.Bias;

import org.apache.harmony.awt.text.TextKit;
import org.apache.harmony.awt.text.TextUtils;


/**
 * Represents view for displaying bidirectional text.
 */
class PlainViewI18N extends BoxView implements TabExpander {

    /**
     * Represents text fragment with right-to-left orientation.
     */
    class RTLTextView extends BidiTextView {

        public RTLTextView(final Element element,
                           final int start, final int end) {
            super(element, start, end);
        }

        public Shape modelToView(final int pos, final Shape shape,
                                 final Bias bias)
            throws BadLocationException {

            final int start = getStartOffset();
            final int end   = getEndOffset();

            if (pos < start || pos > end) {
                throw new BadLocationException("Invalid position", pos);
            }

            getDocument().getText(pos, end - pos, paintParams.buffer);
            if (paintParams.buffer.last() == '\n') {
                --paintParams.buffer.count;
            }

            Rectangle bounds = shape.getBounds();
            String text = paintParams.buffer.toString();
            return new Rectangle(
                    bounds.x + paintParams.metrics.stringWidth(text),
                    bounds.y,
                    1, paintParams.metrics.getHeight());
        }

        public int viewToModel(final float x, final float y,
                               final Shape shape, final Bias[] biasReturn) {
            biasReturn[0] = Position.Bias.Forward;

            final Rectangle bounds = shape.getBounds();

            final int start = getStartOffset();
            final int end   = getEndOffset();
            final Segment buffer = paintParams.buffer;
            try {
                getDocument().getText(start, end - start, buffer);
            } catch (final BadLocationException e) { }

            final int bufferCount  = buffer.count;
            buffer.offset += buffer.count;
            buffer.count  = 0;
            int width = 0;
            while (bounds.x + width < x && buffer.count < bufferCount) {
                --buffer.offset;
                ++buffer.count;
                width = paintParams.metrics.stringWidth(buffer.toString());
            }
            if (buffer.count >= bufferCount && bounds.x + width <= x) {
                return end;
            }
            int resultOffset = end - buffer.count;
            if (resultOffset == end) {
                biasReturn[0] = Position.Bias.Backward;
            }
            return resultOffset;
        }

        void drawLine(final Segment textBuffer, final TextPaintParams tpp,
                      final int start, final int end,
                      final Graphics g, final int x, final int y) {
            int currX = x;

            try {

                if (tpp.selStart == tpp.selEnd
                    || tpp.selStart <= start && end <= tpp.selEnd) {

                    drawUnselectedText(g, currX, y, start, end);
                    return;
                }

                if (tpp.selEnd < start || tpp.selStart > end) {
                    drawUnselectedText(g, currX, y, start, end);
                } else if (start < tpp.selStart) {
                    if (tpp.selEnd < end) {
                        currX = drawUnselectedText(g, currX, y,
                                                   tpp.selEnd, end);
                        currX = drawSelectedText(g, currX, y,
                                                 tpp.selStart, tpp.selEnd);
                    } else { // end <= selEnd
                        currX = drawSelectedText(g, currX, y,
                                                 tpp.selStart, end);
                    }
                    drawUnselectedText(g, currX, y, start, tpp.selStart);
                } else { // selStart <= start && garanteed selEnd < end
                    currX = drawUnselectedText(g, currX, y, tpp.selEnd, end);
                    drawSelectedText(g, currX, y, start, tpp.selEnd);
                }

            } catch (final BadLocationException e) { }
        }
    }

    /**
     * Represents text fragment with left-to-right orientation.
     */
    class LTRTextView extends BidiTextView {

        public LTRTextView(final Element element,
                           final int start, final int end) {
            super(element, start, end);
        }

        public Shape modelToView(final int pos, final Shape shape,
                                 final Bias bias) throws BadLocationException {
            final int start = getStartOffset();

            if (pos < start || pos > getEndOffset()) {
                throw new BadLocationException("Invalid position", pos);
            }

            getDocument().getText(start, pos - start, paintParams.buffer);
            Rectangle bounds = shape.getBounds();
            return new Rectangle(
                    TextUtils.getTabbedTextWidth(paintParams.buffer,
                                                 paintParams.metrics,
                                                 bounds.x,
                                                 PlainViewI18N.this,
                                                 pos) + bounds.x,
                    bounds.y,
                    1, paintParams.metrics.getHeight());
        }

        public int viewToModel(final float x, final float y,
                               final Shape shape, final Bias[] biasReturn) {
            biasReturn[0] = Position.Bias.Forward;

            final Rectangle bounds = shape.getBounds();

            final Document doc   = getDocument();
            final int      start = getStartOffset();
            final int      end   = getEndOffset();
            try {
                doc.getText(start, end - start, paintParams.buffer);
            } catch (final BadLocationException e) { }

            return start + TextUtils.getTabbedTextOffset(paintParams.buffer,
                                                         paintParams.metrics,
                                                         bounds.x, (int)x,
                                                         PlainViewI18N.this,
                                                         start);
        }

    }

    /**
     * Base class for directional run of text.
     */
    abstract class BidiTextView extends View {
        protected Position start;
        protected Position end;
        protected Element  bidiElement;

        protected int cachedWidth = -1;

        public BidiTextView(final Element element,
                            final int start, final int end) {
            super(element);

            final Document doc = getDocument();
            try {
                this.start = doc.createPosition(start);
                this.end   = doc.createPosition(end);
            } catch (BadLocationException e) { }
        }

        public int getEndOffset() {
            return end.getOffset();
        }

        public int getStartOffset() {
            return start.getOffset();
        }

        public float getAlignment(final int axis) {
            return ALIGN_LEFT;
        }

        protected Segment getText() {
            final int start = getStartOffset();
            int end = getEndOffset();
            if (getParent().getEndOffset() == end) {
                --end;
            }
            try {
                getDocument().getText(start, end - start, paintParams.buffer);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            return paintParams.buffer;
        }

        public float getPreferredSpan(final int axis) {
            if (axis == Y_AXIS) {
                return paintParams.metrics.getHeight();
            }

            if (cachedWidth == -1) {
                LineView parent = (LineView)getParent();
                cachedWidth = TextUtils.getTabbedTextWidth(getText(),
                        paintParams.metrics, parent.accumulatedWidth,
                        PlainViewI18N.this, 0);
                parent.accumulatedWidth += cachedWidth;
            }
            return cachedWidth;
        }

        public void paint(final Graphics g, final Shape shape) {
            Rectangle bounds = shape.getBounds();

            drawLine(paintParams,
                     getStartOffset(), getEndOffset(),
                     g, bounds.x,
                     bounds.y + paintParams.metrics.getHeight()
                     - paintParams.metrics.getDescent());
        }

        protected int drawSelectedText(final Graphics g,
                                       final int x, final int y,
                                       final int start, final int end)
            throws BadLocationException {

            return drawText(g, paintParams.selColor, paintParams,
                            x, y, start, end);
        }

        protected int drawUnselectedText(final Graphics g,
                                         final int x, final int y,
                                         final int start, final int end)
            throws BadLocationException {

            return drawText(g, paintParams.color, paintParams,
                            x, y, start, end);
        }

        public void preferenceChanged(final View child,
                                      final boolean width,
                                      final boolean height) {
            if (width) {
                cachedWidth = -1;
            }
            super.preferenceChanged(child, width, height);
        }

        public void insertUpdate(final DocumentEvent event,
                                 final Shape shape,
                                 final ViewFactory factory) {
            preferenceChanged(this, true, false);
        }

        public void removeUpdate(final DocumentEvent event,
                                 final Shape shape,
                                 final ViewFactory factory) {
            preferenceChanged(this, true, false);
        }
    }

    /**
     * Represents one logical line of text.
     */
    class LineView extends BoxView {
        public LineView(final Element element) {
            super(element, X_AXIS);
        }

        public float getAlignment(final int axis) {
            return ALIGN_LEFT;
        }

        public void paint(final Graphics g, final Shape shape) {
            TextKit textKit = getTextKit();

            if (textKit != null) {
                textKit.paintLayeredHighlights(g, getStartOffset(),
                                               getEndOffset() - 1,
                                               shape, this);
            }

            super.paint(g, shape);
        }

        protected boolean updateChildren(final ElementChange change,
                                         final DocumentEvent event,
                                         final ViewFactory factory) {
            return false;
        }

        protected SizeRequirements
            calculateMinorAxisRequirements(final int axis,
                                           final SizeRequirements r) {
            SizeRequirements result = super.calculateMinorAxisRequirements(axis,
                                                                           r);
            // This view is not resizable along Y (minor axis)
            result.maximum = result.preferred;
            return result;
        }

        int accumulatedWidth;

        protected SizeRequirements
            calculateMajorAxisRequirements(final int axis,
                                           final SizeRequirements r) {
            accumulatedWidth = 0;
            return super.calculateMajorAxisRequirements(axis, r);
        }

        protected boolean isAfter(final int x, final int y,
                                  final Rectangle innerAlloc) {
            final int lastIndex = getViewCount() - 1;
            return x > getOffset(X_AXIS, lastIndex)
                       + getSpan(X_AXIS, lastIndex);
        }

        protected void loadChildren(final ViewFactory factory) {
            updateChildren();
        }

        private void updateView(final DocumentEvent event, final Shape shape) {
            final AbstractDocument doc = (AbstractDocument)getDocument();
            final ElementChange change =
                event.getChange(doc.getBidiRootElement());

            if (change != null) {
                updateChildren();
                preferenceChanged(this, true, false);

                if (shape != null) {
                    Rectangle rc = shape.getBounds();
                    getComponent().repaint(rc.x, rc.y, rc.width, rc.height);
                }
            } else {
                forwardUpdate(null, event, shape, null);
            }
        }

        public void insertUpdate(final DocumentEvent event,
                                 final Shape shape,
                                 final ViewFactory factory) {
            updateView(event, shape);
        }

        public void removeUpdate(final DocumentEvent event,
                                 final Shape shape,
                                 final ViewFactory factory) {
            updateView(event, shape);
        }

        private void updateChildren() {
            int startOffset = getStartOffset();
            final int endOffset   = getEndOffset();
            final Element element = getElement();
            AbstractDocument doc = (AbstractDocument)getDocument();
            Element bidiRoot = doc.getBidiRootElement();
            final int startIndex = bidiRoot.getElementIndex(startOffset);
            final int endIndex   = bidiRoot.getElementIndex(endOffset);
            List views = new ArrayList();
            for (int i = startIndex; i <= endIndex
                                     && startOffset < endOffset; i++) {
                Element bidi = bidiRoot.getElement(i);
                int level = StyleConstants.getBidiLevel(bidi.getAttributes());
                View child;
                if (TextUtils.isLTR(level)) {
                    child = new LTRTextView(element, startOffset,
                                            Math.min(endOffset,
                                                     bidi.getEndOffset()));
                } else {
                    child = new RTLTextView(element, startOffset,
                                            Math.min(endOffset,
                                                     bidi.getEndOffset()));
                }
                startOffset = child.getEndOffset();
                views.add(child);
            }
            replace(0, getViewCount(),
                    (View[])views.toArray(new View[views.size()]));
        }
    }

    private final TextPaintParams paintParams = new TextPaintParams(this);

    private ViewFactory lineFactory = new ViewFactory() {
        public View create(final Element element) {
            return new LineView(element);
        }
    };

    public PlainViewI18N(final Element element) {
        super(element, Y_AXIS);
    }

    public ViewFactory getViewFactory() {
        return lineFactory;
    }

    public void paint(final Graphics g, final Shape shape) {
        paintParams.updateFields();
        super.paint(g, shape);
    }

    public void setSize(final float width, final float height) {
        paintParams.conditionalUpdateMetrics();
        super.setSize(width, height);

        final int count = getViewCount();
        for (int i = 0; i < count; i++) {
            getView(i).setSize(getSpan(X_AXIS, i), getSpan(Y_AXIS, i));
        }
    }

    public float nextTabStop(final float x, final int tabOffset) {
        return paintParams.nextTabStop(x);
    }

    public float getMaximumSpan(final int axis) {
        paintParams.conditionalUpdateMetrics();
        return super.getMaximumSpan(axis);
    }

    public float getMinimumSpan(final int axis) {
        paintParams.conditionalUpdateMetrics();
        return super.getMinimumSpan(axis);
    }

    public float getPreferredSpan(final int axis) {
        paintParams.conditionalUpdateMetrics();
        return super.getPreferredSpan(axis);
    }

    public void insertUpdate(final DocumentEvent event,
                             final Shape shape,
                             final ViewFactory factory) {
        super.insertUpdate(event, shape, lineFactory);
    }


    public void removeUpdate(final DocumentEvent event,
                             final Shape shape,
                             final ViewFactory factory) {
        super.removeUpdate(event, shape, lineFactory);
    }
}
