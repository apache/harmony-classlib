/*
 *  Copyright 2005 - 2006 The Apache Software Foundation or its licensors, as applicable.
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

import java.awt.Component;
import java.io.Serializable;

import javax.swing.border.Border;

public class DefaultListCellRenderer extends JLabel implements ListCellRenderer, Serializable {

    public static class UIResource extends DefaultListCellRenderer implements javax.swing.plaf.UIResource {
    }

    protected static Border noFocusBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);

    public DefaultListCellRenderer() {
        setBorder(noFocusBorder);
        setHorizontalAlignment(JLabel.LEADING);
    }

    public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
        setText(value != null ? value.toString() : null);
        if (isSelected) {
            setForeground(list.getSelectionForeground());
            setBackground(list.getSelectionBackground());
        } else {
            setForeground(list.getForeground());
            setBackground(list.getBackground());
        }

        setOpaque(true);
        setFont(list.getFont());
        setEnabled(list.isEnabled());
        setBorder(cellHasFocus ? UIManager.getBorder("List.focusCellHighlightBorder") : noFocusBorder);
        setComponentOrientation(list.getComponentOrientation());

        return this;
    }

    public void firePropertyChange(final String propertyName, final boolean oldValue, final boolean newValue) {
    }

    public void firePropertyChange(final String propertyName, final byte oldValue, final byte newValue) {
    }

    public void firePropertyChange(final String propertyName, final char oldValue, final char newValue) {
    }

    public void firePropertyChange(final String propertyName, final double oldValue, final double newValue) {
    }

    public void firePropertyChange(final String propertyName, final float oldValue, final float newValue) {
    }

    public void firePropertyChange(final String propertyName, final int oldValue, final int newValue) {
    }

    public void firePropertyChange(final String propertyName, final long oldValue, final long newValue) {
    }

    public void firePropertyChange(final String propertyName, final short oldValue, final short newValue) {
    }

    protected void firePropertyChange(final String propertyName, final Object oldValue, final Object newValue) {
    }
}
