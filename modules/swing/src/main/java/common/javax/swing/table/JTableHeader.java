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
package javax.swing.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.util.Locale;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleAction;
import javax.accessibility.AccessibleComponent;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleSelection;
import javax.accessibility.AccessibleStateSet;
import javax.accessibility.AccessibleText;
import javax.accessibility.AccessibleValue;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.plaf.TableHeaderUI;

public class JTableHeader extends JComponent implements TableColumnModelListener, Accessible {
    protected class AccessibleJTableHeader extends AccessibleJComponent {
        protected class AccessibleJTableHeaderEntry extends AccessibleContext implements Accessible, AccessibleComponent {
            public AccessibleJTableHeaderEntry(final int c, final JTableHeader p, final JTable t) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public AccessibleContext getAccessibleContext() {
                return this;
            }

            public String getAccessibleName() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public void setAccessibleName(final String s) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public String getAccessibleDescription() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public void setAccessibleDescription(final String s) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public AccessibleRole getAccessibleRole() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public AccessibleStateSet getAccessibleStateSet() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public int getAccessibleIndexInParent() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public int getAccessibleChildrenCount() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public Accessible getAccessibleChild(final int i) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public Locale getLocale() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public void addPropertyChangeListener(final PropertyChangeListener l) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public void removePropertyChangeListener(final PropertyChangeListener l) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public AccessibleAction getAccessibleAction() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public AccessibleComponent getAccessibleComponent() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public AccessibleSelection getAccessibleSelection() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public AccessibleText getAccessibleText() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public AccessibleValue getAccessibleValue() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public Color getBackground() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public void setBackground(final Color c) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public Color getForeground() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public void setForeground(final Color c) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public Cursor getCursor() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public void setCursor(final Cursor c) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public Font getFont() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public void setFont(final Font f) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public FontMetrics getFontMetrics(final Font f) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public boolean isEnabled() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public void setEnabled(final boolean b) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public boolean isVisible() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public void setVisible(final boolean b) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public boolean isShowing() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public boolean contains(final Point p) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public Point getLocationOnScreen() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public Point getLocation() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public void setLocation(final Point p) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public Rectangle getBounds() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public void setBounds(final Rectangle r) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public Dimension getSize() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public void setSize(final Dimension d) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public Accessible getAccessibleAt(final Point p) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public boolean isFocusTraversable() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public void requestFocus() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public void addFocusListener(final FocusListener l) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public void removeFocusListener(final FocusListener l) {
                throw new UnsupportedOperationException("Not implemented");
            }
        }

        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.TABLE;
        }

        public Accessible getAccessibleAt(final Point p) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public int getAccessibleChildrenCount() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public Accessible getAccessibleChild(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }
    }


    private class DefaultHeaderRendererUIResource extends DefaultTableCellRenderer.UIResource {
        private JTable defaultTable;


        public Component getTableCellRendererComponent(final JTable table,
                                                       final Object value,
                                                       final boolean isSelected,
                                                       final boolean hasFocus,
                                                       final int row,
                                                       final int column) {

            JLabel result = (JLabel)super.getTableCellRendererComponent(table != null ? table : getDefaultTable(), value, isSelected, hasFocus, row, column);
            result.setHorizontalAlignment(SwingConstants.CENTER);
            result.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            setFont(JTableHeader.this.getFont());
            setForeground(JTableHeader.this.getForeground());
            setBackground(JTableHeader.this.getBackground());

            return result;
        }

        private JTable getDefaultTable() {
            if (defaultTable == null) {
                defaultTable = new JTable();
            }

            return defaultTable;
        }
    }


    protected JTable table;
    protected TableColumnModel columnModel;
    protected boolean reorderingAllowed;
    protected boolean resizingAllowed;
    protected transient TableColumn resizingColumn;
    protected transient TableColumn draggedColumn;
    protected transient int draggedDistance;
    protected boolean updateTableInRealTime;
    private TableCellRenderer defaultRenderer;

    private static final String UI_CLASS_ID = "TableHeaderUI";
    private static final String TABLE_PROPERTY = "table";
    private static final String REORDERING_ALLOWED_PROPERTY = "reorderingAllowed";
    private static final String RESIZING_ALLOWED_PROPERTY = "resizingAllowed";
    private static final String COLUMN_MODEL_PROPERTY = "columnModel";


    public JTableHeader() {
        this(null);
    }

    public JTableHeader(final TableColumnModel model) {
        columnModel = model != null ? model : createDefaultColumnModel();
        columnModel.addColumnModelListener(this);
        initializeLocalVars();
        ToolTipManager.sharedInstance().registerComponent(this);

        updateUI();
    }

    public void setTable(final JTable table) {
        JTable oldValue = this.table;
        this.table = table;
        firePropertyChange(TABLE_PROPERTY, oldValue, table);
    }

    public JTable getTable() {
        return table;
    }

    public void setReorderingAllowed(final boolean allowed) {
        boolean oldValue = reorderingAllowed;
        reorderingAllowed = allowed;
        firePropertyChange(REORDERING_ALLOWED_PROPERTY, oldValue, allowed);
    }

    public boolean getReorderingAllowed() {
        return reorderingAllowed;
    }

    public void setResizingAllowed(final boolean allowed) {
        boolean oldValue = resizingAllowed;
        resizingAllowed = allowed;
        firePropertyChange(RESIZING_ALLOWED_PROPERTY, oldValue, allowed);
    }

    public boolean getResizingAllowed() {
        return resizingAllowed;
    }

    public TableColumn getDraggedColumn() {
        return draggedColumn;
    }

    public void setDraggedColumn(final TableColumn column) {
        draggedColumn = column;
    }

    public int getDraggedDistance() {
        return draggedDistance;
    }

    public void setDraggedDistance(final int distance) {
        draggedDistance = distance;
    }

    public TableColumn getResizingColumn() {
        return resizingColumn;
    }

    public void setResizingColumn(final TableColumn column) {
        resizingColumn = column;
    }

    public void setUpdateTableInRealTime(final boolean update) {
        updateTableInRealTime = update;
    }

    public boolean getUpdateTableInRealTime() {
        return updateTableInRealTime;
    }

    public void setDefaultRenderer(final TableCellRenderer renderer) {
        defaultRenderer = renderer;
    }

    public TableCellRenderer getDefaultRenderer() {
        return defaultRenderer;
    }

    public int columnAtPoint(final Point p) {
        if (table == null || table.getComponentOrientation().isLeftToRight()) {
            return columnModel.getColumnIndexAtX(p.x);
        } else {
            int width = 0;
            for (int i = 0; i < columnModel.getColumnCount(); i++) {
                width += columnModel.getColumn(i).width;
            }
            int offset = width;
            for (int result = 0; result < columnModel.getColumnCount(); result++) {
                offset -= columnModel.getColumn(result).width;
                if (p.x >= offset && p.x < offset + columnModel.getColumn(result).width) {
                    return result;
                }
            }
            return p.x < 0 ? columnModel.getColumnCount() - 1 : 0;
        }
    }

    public Rectangle getHeaderRect(final int column) {
        if (column >= columnModel.getColumnCount()) {
            return new Rectangle();
        }

        return new Rectangle(getOffset(column), 0, columnModel.getColumn(column).width, getHeight());
    }

    public String getToolTipText(final MouseEvent me) {
        int columnIndex = columnAtPoint(me.getPoint());
        if (columnIndex == -1) {
            return null;
        }

        TableColumn column = columnModel.getColumn(columnIndex);
        if (column.getHeaderRenderer() != null) {
            return getToolTipText(column.getHeaderRenderer(), columnIndex);
        }
        if (getDefaultRenderer() != null) {
            return getToolTipText(getDefaultRenderer(), columnIndex);
        }

        return null;
    }

    public void setColumnModel(final TableColumnModel cm) {
        if (cm == null) {
            throw new IllegalArgumentException("Null model cannot be used");
        }

        columnModel.removeColumnModelListener(this);

        TableColumnModel oldValue = columnModel;
        columnModel = cm;
        columnModel.addColumnModelListener(this);
        firePropertyChange(COLUMN_MODEL_PROPERTY, oldValue, cm);
    }

    public TableColumnModel getColumnModel() {
        return columnModel;
    }


    public TableHeaderUI getUI() {
        return (TableHeaderUI)ui;
    }

    public void setUI(final TableHeaderUI ui) {
        super.setUI(ui);
    }

    public void updateUI() {
        setUI((TableHeaderUI)UIManager.getUI(this));
    }

    public String getUIClassID() {
        return UI_CLASS_ID;
    }

    public void columnAdded(final TableColumnModelEvent e) {
        repaint();
    }

    public void columnRemoved(final TableColumnModelEvent e) {
        repaint();
    }

    public void columnMoved(final TableColumnModelEvent e) {
        repaint();
    }

    public void columnMarginChanged(final ChangeEvent e) {
        repaint();
    }

    public void columnSelectionChanged(final ListSelectionEvent e) {
    }


    protected TableColumnModel createDefaultColumnModel() {
        return new DefaultTableColumnModel();
    }

    protected TableCellRenderer createDefaultRenderer() {
        return new DefaultHeaderRendererUIResource();
    }

    protected void initializeLocalVars() {
        reorderingAllowed = true;
        resizingAllowed = true;
        updateTableInRealTime = true;
        defaultRenderer = createDefaultRenderer();
        table = null;
        resizingColumn = null;
        draggedColumn = null;
        draggedDistance = 0;
    }

    public void resizeAndRepaint() {
        table.doLayout();
        repaint();
    }

    public AccessibleContext getAccessibleContext() {
        if (accessibleContext == null) {
            accessibleContext = new AccessibleJTableHeader();
        }

        return accessibleContext;
    }

    private int getOffset(final int column) {
        int result = 0;
        if (table == null || table.getComponentOrientation().isLeftToRight()) {
            for (int i = 0; i < column; i++) {
                result += columnModel.getColumn(i).width;
            }
        } else {
            for (int i = 0; i < columnModel.getColumnCount(); i++) {
                result += columnModel.getColumn(i).width;
            }
            for (int i = 0; i <= column; i++) {
                result -= columnModel.getColumn(i).width;
            }
        }

        return result;
    }

    private String getToolTipText(final TableCellRenderer renderer, final int column) {
        Component renderingComponent = renderer.getTableCellRendererComponent(getTable(), columnModel.getColumn(column).getHeaderValue(), false, false, -1, columnModel.getColumn(column).getModelIndex());
        if (!(renderingComponent instanceof JComponent)) {
            return null;
        }

        return ((JComponent)renderingComponent).getToolTipText();
    }
}

