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

package javax.swing.plaf.synth;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.JComponent;

/**
 * SynthStyle contains all the information about component drawing
 */
public abstract class SynthStyle {

    /**
     * The default isOpaque value
     */
    private boolean isOpaque = true;

    /**
     * The default graphics utils
     */
    private static final SynthGraphicsUtils gUtils = new SynthGraphicsUtils();

    /**
     * The default Insets
     */
    private static final Insets insets = new Insets(0, 0, 0, 0);

    static int getCommonComponentState(JComponent c) {
        int result = c.isEnabled() ? SynthConstants.ENABLED
                : SynthConstants.DISABLED;
        if (c.isFocusOwner()) {
            result |= SynthConstants.FOCUSED;
        }
        return result;
    }

    /**
     * The default implementation returns null
     */
    @SuppressWarnings("unused")
    public Object get(SynthContext context, Object key) {
        return null;
    }

    /**
     * Returns boolean value from the properties table or the default value
     */
    public boolean getBoolean(SynthContext context, Object key,
            boolean defaultValue) {

        Object result = get(context, key);
        return (result instanceof Boolean) ? ((Boolean) result).booleanValue()
                : defaultValue;
    }

    /**
     * Verifies the colors defined in component than calls getColorForState
     */
    public Color getColor(SynthContext context, ColorType type) {
        JComponent c = context.getComponent();
        if (c.isEnabled()) {

            if (type == ColorType.BACKGROUND) {

                if (c.getBackground() != null) {
                    return c.getBackground();
                }

            } else if (type == ColorType.FOREGROUND) {

                if (c.getForeground() != null) {
                    return c.getForeground();
                }
            }
        }

        return getColorForState(context, type);
    }

    public Font getFont(SynthContext context) {

        Font result = context.getComponent().getFont();

        if (result == null) {
            return getFontForState(context);
        }

        return result;
    }

    protected abstract Font getFontForState(SynthContext context);

    /**
     * The default implementation just casts Object returned by get(Object, key
     * method)
     */
    public Icon getIcon(SynthContext context, Object key) {

        try {

            return (Icon) get(context, key);

        } catch (ClassCastException e) {

            return null;

        }

    }

    @SuppressWarnings("unused")
    public Insets getInsets(SynthContext context, Insets modified) {

        if (modified == null) {
            return (Insets) insets.clone();
        }

        modified.set(insets.top, insets.left, insets.bottom, insets.right);

        return modified;
    }

    public int getInt(SynthContext context, Object key, int defaultValue) {

        Object value = get(context, key);

        return (value instanceof Integer) ? ((Integer) value).intValue()
                : defaultValue;
    }

    /**
     * @return The default implementation returns null
     */
    public SynthPainter getPainter(@SuppressWarnings("unused")
    SynthContext context) {
        return null;
    }

    public String getString(SynthContext context, Object key,
            String defaultValue) {
        Object value = get(context, key);
        return (value instanceof String) ? (String) value : defaultValue;
    }

    /** The default implementation is empty */
    @SuppressWarnings("unused")
    public void installDefaults(SynthContext context) {
        // Empty
    }

    @SuppressWarnings("unused")
    public boolean isOpaque(SynthContext context) {
        return isOpaque;
    }

    /** The default implementation is empty */
    @SuppressWarnings("unused")
    public void uninstallDefaults(SynthContext context) {
        // Empty
    }

    protected abstract Color getColorForState(SynthContext context,
            ColorType type);

    /**
     * @return GraphicsUtils used for specified context. The default
     *         implementation returns default GraphicsUtils for any context.
     */
    public SynthGraphicsUtils getGraphicsUtils(@SuppressWarnings("unused")
    SynthContext context) {
        return gUtils;
    }
}
