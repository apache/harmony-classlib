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
 * @author Roman I. Chernyatchik
 * @version $Revision$
 */
package javax.swing.text;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.text.Position.Bias;

import org.apache.harmony.awt.text.TextUtils;

public class ComponentView extends View {

    private static final int EMPTY_SPAN = 0;
    private boolean notInitialized = true;
    private boolean compInHierarchy = false;
    private Component component;

    public ComponentView(final Element element) {
        super(element);
    }

    public final Component getComponent() {
        return component;
    }

    public float getPreferredSpan(final int axis) {
        isAxisValid(axis);
        if (getParent() != null) {
            if (axis == View.X_AXIS) {
                return component.getPreferredSize().width + 2;
            }
            return component.getPreferredSize().height;
        }
        return EMPTY_SPAN;
    }

    public float getMinimumSpan(final int axis) {
        isAxisValid(axis);
        if (getParent() != null) {
            if (axis == View.X_AXIS) {
                return component.getMinimumSize().width + 2;
            }
            return component.getMinimumSize().height;
        }
        return EMPTY_SPAN;
    }

    public float getMaximumSpan(final int axis) {
        isAxisValid(axis);
        if (getParent() != null) {
            if (axis == View.X_AXIS) {
                return component.getMaximumSize().width + 2;
            }
            return component.getMaximumSize().height;
        }
        return EMPTY_SPAN;
    }

    public float getAlignment(final int axis) {
        if (component != null) {
            if (axis == View.X_AXIS) {
                return component.getAlignmentX();
            }
            if (axis == View.Y_AXIS) {
                return component.getAlignmentY();
            }
        }
        return View.ALIGN_CENTER;
    }

    public Shape modelToView(final int pos, final Shape shape,
                             final Bias bias) throws BadLocationException {
        return TextUtils.modelToIconOrComponentView(this, pos, shape, bias);
    }

    public int viewToModel(final float x,
                           final float y,
                           final Shape shape,
                           final Bias[] biasReturn) {

        final Rectangle bounds = shape.getBounds();
        if (x > bounds.width / 2 + bounds.x - 1) {
            biasReturn[0] = Position.Bias.Backward;
            return getEndOffset();
        }
        biasReturn[0] = Position.Bias.Forward;
        return getStartOffset();
    }

    public void setParent(final View parent) {
        if (getParent() != null) {
            if (parent == null) {
                if (component.getParent() != null) {
                    component.getParent().remove(component);
                    compInHierarchy = false;
                }
                super.setParent(parent);
            } else {
                if (component.getParent() == null) {
                    getContainer().add(component);
                    compInHierarchy = true;
                }
            }
        } else {
            super.setParent(parent);

            if (parent != null) {
                if (notInitialized) {
                    component = createComponent();
                    notInitialized = false;
                }
                getContainer().add(component);
                compInHierarchy = true;
            }
        }
    }

    public void paint(final Graphics g, final Shape shape)  {
       if (component != null && compInHierarchy) {
           final Rectangle bounds = shape.getBounds();
           final int height = Math.min(bounds.height,
                                       component.getPreferredSize().height);
           component.setBounds(bounds.x + 1,
                               (bounds.height - height) + bounds.y,
                               bounds.width - 1,
                               height);
       }
    }

    protected Component createComponent() {
        return StyleConstants.getComponent(getAttributes());
    }

    private void isAxisValid(final int axis) {
        if (axis != X_AXIS && axis != Y_AXIS) {
            throw new IllegalArgumentException("Invalid axis: " + axis);
        }
    }
}