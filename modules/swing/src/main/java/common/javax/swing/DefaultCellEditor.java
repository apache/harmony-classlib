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
 * @author Anton Avtamonov
 * @version $Revision$
 */
package javax.swing;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.EventObject;

import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeCellEditor;

import org.apache.harmony.x.swing.StringConstants;

public class DefaultCellEditor extends AbstractCellEditor implements TableCellEditor, TreeCellEditor {
    protected class EditorDelegate implements ActionListener, ItemListener, Serializable {
        protected Object value;

        public Object getCellEditorValue() {
            return value;
        }

        public void setValue(final Object value) {
            this.value = value;
        }

        public boolean isCellEditable(final EventObject event) {
            if (event instanceof MouseEvent) {
                return ((MouseEvent)event).getClickCount() >= clickCountToStart;
            }

            return true;
        }

        public boolean shouldSelectCell(final EventObject event) {
            return true;
        }

        public boolean startCellEditing(final EventObject event) {
            return true;
        }

        public boolean stopCellEditing() {
            fireEditingStopped();
            return true;
        }

        public void cancelCellEditing() {
            fireEditingCanceled();
        }

        public void actionPerformed(final ActionEvent e) {
            DefaultCellEditor.this.stopCellEditing();
        }

        public void itemStateChanged(final ItemEvent e) {
            DefaultCellEditor.this.stopCellEditing();
        }
    }

    protected JComponent editorComponent;
    protected EditorDelegate delegate;
    protected int clickCountToStart;


    public DefaultCellEditor(final JTextField textField) {
        editorComponent = textField;
        delegate = new EditorDelegate() {
            public Object getCellEditorValue() {
                return textField.getText();
            }

            public void setValue(final Object value) {
                textField.setText(value != null ? value.toString() : null);
            }
        };
        textField.addActionListener(delegate);
        setClickCountToStart(2);
    }

    public DefaultCellEditor(final JCheckBox checkBox) {
        editorComponent = checkBox;
        delegate = new EditorDelegate() {
            public Object getCellEditorValue() {
                return Boolean.valueOf(checkBox.isSelected());
            }

            public void setValue(final Object value) {
                checkBox.setSelected(value != null ? Boolean.valueOf(value.toString()).booleanValue() : false);
            }
        };
        checkBox.setFocusable(false);
        checkBox.addActionListener(delegate);
        setClickCountToStart(1);
    }

    public DefaultCellEditor(final JComboBox comboBox) {
        editorComponent = comboBox;
        delegate = new EditorDelegate() {
            public Object getCellEditorValue() {
                return comboBox.getSelectedItem();
            }

            public void setValue(final Object value) {
                comboBox.setSelectedItem(value);
            }
        };
        comboBox.addActionListener(delegate);
        setClickCountToStart(1);
        
        comboBox.putClientProperty(StringConstants.IS_TABLE_EDITOR, Boolean.TRUE);
    }

    public Component getComponent() {
        return editorComponent;
    }

    public void setClickCountToStart(final int count) {
        clickCountToStart = count;
    }

    public int getClickCountToStart() {
        return clickCountToStart;
    }

    public Object getCellEditorValue() {
        return delegate.getCellEditorValue();
    }

    public boolean isCellEditable(final EventObject event) {
        return delegate.isCellEditable(event);
    }

    public boolean shouldSelectCell(final EventObject event) {
        return delegate.shouldSelectCell(event);
    }

    public boolean stopCellEditing() {
        return delegate.stopCellEditing();
    }

    public void cancelCellEditing() {
        delegate.cancelCellEditing();
    }

    public Component getTreeCellEditorComponent(final JTree tree,
                                                final Object value,
                                                final boolean isSelected,
                                                final boolean expanded,
                                                final boolean leaf,
                                                final int row) {

        delegate.setValue(value);
        return getComponent();
    }

    public Component getTableCellEditorComponent(final JTable table,
                                                 final Object value,
                                                 final boolean isSelected,
                                                 final int row,
                                                 final int column) {

        delegate.setValue(value);
        return getComponent();
    }
}
