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
 * @author Anton Avtamonov, Sergey Burlak
 * @version $Revision$
 */
package javax.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.EventObject;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Vector;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleAction;
import javax.accessibility.AccessibleComponent;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleExtendedTable;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleSelection;
import javax.accessibility.AccessibleStateSet;
import javax.accessibility.AccessibleTable;
import javax.accessibility.AccessibleTableModelChange;
import javax.accessibility.AccessibleText;
import javax.accessibility.AccessibleValue;
import javax.swing.border.Border;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.TableUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.apache.harmony.x.swing.StringConstants;


public class JTable extends JComponent implements TableModelListener, Scrollable, TableColumnModelListener, ListSelectionListener, CellEditorListener, Accessible {
    public static final int AUTO_RESIZE_OFF = 0;
    public static final int AUTO_RESIZE_NEXT_COLUMN = 1;
    public static final int AUTO_RESIZE_SUBSEQUENT_COLUMNS = 2;
    public static final int AUTO_RESIZE_LAST_COLUMN = 3;
    public static final int AUTO_RESIZE_ALL_COLUMNS = 4;

    protected class AccessibleJTable extends AccessibleJComponent implements AccessibleSelection, ListSelectionListener, TableModelListener, TableColumnModelListener, CellEditorListener, PropertyChangeListener, AccessibleExtendedTable {
        protected class AccessibleJTableCell extends AccessibleContext implements Accessible, AccessibleComponent {
            public AccessibleJTableCell(final JTable t, final int r, final int c, final int i) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public AccessibleContext getAccessibleContext() {
                throw new UnsupportedOperationException("Not implemented");
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

            public Accessible getAccessibleParent() {
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

        protected class AccessibleJTableModelChange implements AccessibleTableModelChange {
            protected int type;
            protected int firstRow;
            protected int lastRow;
            protected int firstColumn;
            protected int lastColumn;

            protected AccessibleJTableModelChange(final int type, final int firstRow, final int lastRow, final int firstColumn, final int lastColumn) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public int getType() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public int getFirstRow() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public int getLastRow() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public int getFirstColumn() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public int getLastColumn() {
                throw new UnsupportedOperationException("Not implemented");
            }
        }

        public void propertyChange(final PropertyChangeEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void tableChanged(final TableModelEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void tableRowsInserted(final TableModelEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void tableRowsDeleted(final TableModelEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void columnAdded(final TableColumnModelEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void columnRemoved(final TableColumnModelEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void columnMoved(final TableColumnModelEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void columnMarginChanged(final ChangeEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void columnSelectionChanged(final ListSelectionEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void editingStopped(final ChangeEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void editingCanceled(final ChangeEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void valueChanged(final ListSelectionEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public AccessibleSelection getAccessibleSelection() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public AccessibleRole getAccessibleRole() {
            throw new UnsupportedOperationException("Not implemented");
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

        public int getAccessibleSelectionCount() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public Accessible getAccessibleSelection(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public boolean isAccessibleChildSelected(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void addAccessibleSelection(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void removeAccessibleSelection(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void clearAccessibleSelection() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void selectAllAccessibleSelection() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public int getAccessibleRow(final int index) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public int getAccessibleColumn(final int index) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public int getAccessibleIndex(final int r, final int c) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public AccessibleTable getAccessibleTable() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public Accessible getAccessibleCaption() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void setAccessibleCaption(final Accessible a) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public Accessible getAccessibleSummary() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void setAccessibleSummary(final Accessible a) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public int getAccessibleRowCount() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public int getAccessibleColumnCount() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public Accessible getAccessibleAt(final int r, final int c) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public int getAccessibleRowExtentAt(final int r, final int c) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public int getAccessibleColumnExtentAt(final int r, final int c) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public AccessibleTable getAccessibleRowHeader() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void setAccessibleRowHeader(final AccessibleTable a) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public AccessibleTable getAccessibleColumnHeader() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void setAccessibleColumnHeader(final AccessibleTable a) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public Accessible getAccessibleRowDescription(final int r) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void setAccessibleRowDescription(final int r, final Accessible a) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public Accessible getAccessibleColumnDescription(final int c) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void setAccessibleColumnDescription(final int c, final Accessible a) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public boolean isAccessibleSelected(final int r, final int c) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public boolean isAccessibleRowSelected(final int r) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public boolean isAccessibleColumnSelected(final int c) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public int[] getSelectedAccessibleRows() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public int[] getSelectedAccessibleColumns() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public int getAccessibleRowAtIndex(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public int getAccessibleColumnAtIndex(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public int getAccessibleIndexAt(final int r, final int c) {
            throw new UnsupportedOperationException("Not implemented");
        }
    }


    private class TableEditor extends DefaultCellEditor {
        public TableEditor(final JCheckBox checkBox) {
            super(checkBox);
            setNormalEditorView();
        }

        public TableEditor(final JTextField textField) {
            super(textField);
            setNormalEditorView();
        }

        public boolean isInputValid() {
            return verifyInput(delegate.getCellEditorValue());
        }

        protected boolean verifyInput(final Object value) {
            return true;
        }

        protected void fireEditingStopped() {
            if (!isInputValid()) {
                setErrorEditorView();
                return;
            }

            setNormalEditorView();
            super.fireEditingStopped();
        }

        protected void fireEditingCanceled() {
            setNormalEditorView();
            super.fireEditingCanceled();
        }


        protected void setNormalEditorView() {
            ((JComponent)getComponent()).setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }

        protected void setErrorEditorView() {
            ((JComponent)getComponent()).setBorder(BorderFactory.createLineBorder(Color.RED));
        }
    }

    private class ObjectEditor extends TableEditor {
        public ObjectEditor() {
            super(new JTextField());
        }

        public boolean isCellEditable(final EventObject event) {
            return getObjectConstructor() != null && super.isCellEditable(event);
        }

        public Object getCellEditorValue() {
            Object value = delegate.getCellEditorValue();
            if (value == null) {
                return null;
            }

            try {
                return getObjectConstructor().newInstance(new Object[] {value.toString()});
            } catch (Exception e) {
                return null;
            }
        }

        protected boolean verifyInput(final Object value) {
            if (value == null || value.toString().trim().equals("")) {
                return true;
            }
            try {
                getObjectConstructor().newInstance(new Object[] {value.toString()});
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        private Constructor getObjectConstructor() {
            try {
                return getColumnClass().getConstructor(new Class[] {String.class});
            } catch (NoSuchMethodException e) {
                return null;
            }
        }

        private Class getColumnClass() {
            Class columnClass =  JTable.this.getColumnClass(getEditingColumn());
            return columnClass == Object.class ? String.class : columnClass;
        }
    }

    private class NumberEditor extends ObjectEditor {
        public NumberEditor() {
            ((JTextField)getComponent()).setHorizontalAlignment(JTextField.RIGHT);
        }
    }

    private class BooleanEditor extends TableEditor {
        public BooleanEditor() {
            super(new JCheckBox());
            ((JCheckBox)getComponent()).setHorizontalAlignment(JCheckBox.CENTER);
        }

        protected void setNormalEditorView() {
            ((JComponent)getComponent()).setBorder(null);
        }
    }

    protected TableModel dataModel;
    protected TableColumnModel columnModel;
    protected ListSelectionModel selectionModel;
    protected JTableHeader tableHeader;
    protected int rowHeight;
    protected int rowMargin;
    protected Color gridColor;
    protected boolean showHorizontalLines;
    protected boolean showVerticalLines;
    protected int autoResizeMode;
    protected boolean autoCreateColumnsFromModel = true;
    protected Dimension preferredViewportSize;
    protected boolean rowSelectionAllowed;
    protected boolean cellSelectionEnabled;
    protected transient Component editorComp;
    protected transient TableCellEditor cellEditor;
    protected transient int editingColumn = -1;
    protected transient int editingRow = -1;
    protected transient Hashtable defaultEditorsByColumnClass;
    protected transient Hashtable defaultRenderersByColumnClass;
    protected Color selectionBackground;
    protected Color selectionForeground;

    private boolean dragEnabled;
    private Vector rowHeights = new Vector();
    private boolean surrendersFocusOnKeystroke;
    private boolean wasConsumed;


    private static final String HEADER_PROPERTY = "tableHeader";
    private static final String ROW_HEIGHT_PROPERTY = "rowHeight";
    private static final String ROW_MARGIN_PROPERTY = "rowMargin";
    private static final String GRID_COLOR_PROPERTY = "gridColor";
    private static final String SHOW_HORIZONTAL_LINES_PROPERTY = "showHorizontalLines";
    private static final String SHOW_VERTICAL_LINES_PROPERTY = "showVerticalLines";
    private static final String AUTO_RESIZE_MODE_PROPERTY = "autoResizeMode";
    private static final String AUTO_CREATE_COLUMNS_FROM_MODEL_PROPERTY = "autoCreateColumnsFromModel";
    private static final String ROW_SELECTION_ALLOWED_PROPERTY = "rowSelectionAllowed";
    private static final String COLUMN_SELECTION_ALLOWED_PROPERTY = "columnSelectionAllowed";
    private static final String CELL_SELECTION_ENABLED_PROPERTY = "cellSelectionEnabled";
    private static final String SELECTION_FOREGROUND_PROPERTY = "selectionForeground";
    private static final String SELECTION_BACKGROUND_PROPERTY = "selectionBackground";

    private static final String UI_CLASS_ID = "TableUI";

    public static JScrollPane createScrollPaneForTable(final JTable table) {
        return new JScrollPane(table);
    }


    public JTable() {
        this(null, null, null);
    }

    public JTable(final TableModel model) {
        this(model, null, null);
    }

    public JTable(final TableModel model, final TableColumnModel columnModel) {
        this(model, columnModel, null);
    }

    public JTable(final TableModel model, final TableColumnModel columnModel, final ListSelectionModel selectionModel) {
        setColumnModel(columnModel != null ? columnModel : createDefaultColumnModel());
        setModel(model != null ? model : createDefaultDataModel());
        setSelectionModel(selectionModel != null ? selectionModel : createDefaultSelectionModel());

        initializeLocalVars();

        updateUI();
    }

    public JTable(final int numRows, final int numColumns) {
        this(new DefaultTableModel(numRows, numColumns));
        if (getAutoCreateColumnsFromModel()) {
            createDefaultColumnsFromModel();
        }
    }

    public JTable(final Vector rowData, final Vector columnNames) {
        this(new DefaultTableModel(rowData, columnNames));
        if (getAutoCreateColumnsFromModel()) {
            createDefaultColumnsFromModel();
        }
    }

    public JTable(final Object[][] rowData, final Object[] columnNames) {
        this(new DefaultTableModel(rowData, columnNames));
        if (getAutoCreateColumnsFromModel()) {
            createDefaultColumnsFromModel();
        }
    }


    public void addNotify() {
        configureEnclosingScrollPane();
        super.addNotify();
    }

    public void removeNotify() {
        super.removeNotify();
        unconfigureEnclosingScrollPane();
    }

    public void setTableHeader(final JTableHeader header) {
        JTableHeader oldValue = this.tableHeader;
        if (oldValue != null) {
            oldValue.setTable(null);
        }

        this.tableHeader = header;
        if (header != null) {
            this.tableHeader.setTable(this);
        }

        firePropertyChange(HEADER_PROPERTY, oldValue, header);
    }

    public JTableHeader getTableHeader() {
        return tableHeader;
    }


    public void setRowHeight(final int rowHeight) {
        if (rowHeight <= 0) {
            throw new IllegalArgumentException("Row height must be posittive");
        }

        int oldValue = this.rowHeight;
        this.rowHeight = rowHeight;

        firePropertyChange(ROW_HEIGHT_PROPERTY, oldValue, rowHeight);
    }

    public int getRowHeight() {
        return rowHeight;
    }

    public void setRowHeight(final int row, final int height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Row height must be posittive");
        }

        if (rowHeights.size() <= row) {
            rowHeights.setSize(row + 1);
        }

        if (rowHeights.get(row) == null) {
            rowHeights.set(row, new MutableInteger());
        }
        if (row < getRowCount()) {
            ((MutableInteger)rowHeights.get(row)).setValue(height);
        }
    }

    public int getRowHeight(final int row) {
        int result = rowHeights.size() > row && rowHeights.get(row) != null ? ((MutableInteger)rowHeights.get(row)).getValue() : -1;
        return result != -1 ? result : getRowHeight();
    }

    public void setRowMargin(final int margin) {
        int oldValue = this.rowMargin;
        this.rowMargin = margin;

        firePropertyChange(ROW_MARGIN_PROPERTY, oldValue, margin);
    }

    public int getRowMargin() {
        return rowMargin;
    }

    public void setIntercellSpacing(final Dimension spacing) {
        setRowMargin(spacing.height);
        columnModel.setColumnMargin(spacing.width);
    }

    public Dimension getIntercellSpacing() {
        return new Dimension(columnModel.getColumnMargin(), getRowMargin());
    }

    public void setGridColor(final Color color) {
        Color oldValue = this.gridColor;
        this.gridColor = color;

        firePropertyChange(GRID_COLOR_PROPERTY, oldValue, color);
    }

    public Color getGridColor() {
        return gridColor;
    }

    public void setShowGrid(final boolean show) {
        setShowHorizontalLines(show);
        setShowVerticalLines(show);
    }

    public void setShowHorizontalLines(final boolean show) {
        boolean oldValue = this.showHorizontalLines;
        this.showHorizontalLines = show;

        firePropertyChange(SHOW_HORIZONTAL_LINES_PROPERTY, oldValue, show);
    }

    public boolean getShowHorizontalLines() {
        return showHorizontalLines;
    }

    public void setShowVerticalLines(final boolean show) {
        boolean oldValue = this.showVerticalLines;
        this.showVerticalLines = show;

        firePropertyChange(SHOW_VERTICAL_LINES_PROPERTY, oldValue, show);
    }

    public boolean getShowVerticalLines() {
        return showVerticalLines;
    }

    public void setAutoResizeMode(final int mode) {
        if (mode < 0 || mode > 4) {
            return;
        }

        int oldValue = this.autoResizeMode;
        this.autoResizeMode = mode;

        firePropertyChange(AUTO_RESIZE_MODE_PROPERTY, oldValue, mode);
    }

    public int getAutoResizeMode() {
        return autoResizeMode;
    }

    public void setAutoCreateColumnsFromModel(final boolean autoCreate) {
        boolean oldValue = autoCreateColumnsFromModel;
        autoCreateColumnsFromModel = autoCreate;

        if (getAutoCreateColumnsFromModel()) {
            createDefaultColumnsFromModel();
        }

        firePropertyChange(AUTO_CREATE_COLUMNS_FROM_MODEL_PROPERTY, oldValue, autoCreate);
    }

    public boolean getAutoCreateColumnsFromModel() {
        return autoCreateColumnsFromModel;
    }

    public void createDefaultColumnsFromModel() {
        int columnCount = columnModel.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            columnModel.removeColumn(columnModel.getColumn(0));
        }

        for (int i = 0; i < getModel().getColumnCount(); i++) {
            TableColumn column = new TableColumn(i);
            column.setHeaderValue(getModel().getColumnName(i));
            columnModel.addColumn(column);
        }
    }

    public void setDefaultRenderer(final Class columnClass, final TableCellRenderer renderer) {
        if (renderer != null) {
            defaultRenderersByColumnClass.put(columnClass, renderer);
        } else {
            defaultRenderersByColumnClass.remove(columnClass);
        }
    }

    public TableCellRenderer getDefaultRenderer(final Class columnClass) {
        return (TableCellRenderer)getClosestClass(columnClass, defaultRenderersByColumnClass);
    }

    public void setDefaultEditor(final Class columnClass, final TableCellEditor editor) {
        if (editor != null) {
            defaultEditorsByColumnClass.put(columnClass, editor);
        } else {
            defaultEditorsByColumnClass.remove(columnClass);
        }
    }

    public TableCellEditor getDefaultEditor(final Class columnClass) {
        return (TableCellEditor)getClosestClass(columnClass, defaultEditorsByColumnClass);
    }

    public void setDragEnabled(final boolean enabled) {
        if (enabled && GraphicsEnvironment.isHeadless()) {
            throw new HeadlessException();
        }
        dragEnabled = enabled;
    }

    public boolean getDragEnabled() {
        return dragEnabled;
    }

    public void setSelectionMode(final int mode) {
        getSelectionModel().setSelectionMode(mode);
        getColumnModel().getSelectionModel().setSelectionMode(mode);
    }

    public void setRowSelectionAllowed(final boolean allowed) {
        boolean oldValue = rowSelectionAllowed;
        rowSelectionAllowed = allowed;

        firePropertyChange(ROW_SELECTION_ALLOWED_PROPERTY, oldValue, allowed);
    }

    public boolean getRowSelectionAllowed() {
        return rowSelectionAllowed;
    }

    public void setColumnSelectionAllowed(final boolean allowed) {
        boolean oldValue = getColumnModel().getColumnSelectionAllowed();
        getColumnModel().setColumnSelectionAllowed(allowed);

        firePropertyChange(COLUMN_SELECTION_ALLOWED_PROPERTY, oldValue, allowed);
    }

    public boolean getColumnSelectionAllowed() {
        return getColumnModel().getColumnSelectionAllowed();
    }



    public void setCellSelectionEnabled(final boolean enabled) {
        boolean oldValue = cellSelectionEnabled;
        cellSelectionEnabled = enabled;
        setRowSelectionAllowed(enabled);
        setColumnSelectionAllowed(enabled);

        firePropertyChange(CELL_SELECTION_ENABLED_PROPERTY, oldValue, enabled);
    }

    public boolean getCellSelectionEnabled() {
        return getRowSelectionAllowed() && getColumnSelectionAllowed();
    }


    public void selectAll() {
        int rowLead = getSelectionModel().getLeadSelectionIndex();
        int rowAnchor = getSelectionModel().getAnchorSelectionIndex();
        int columnLead = getColumnModel().getSelectionModel().getLeadSelectionIndex();
        int columnAnchor = getColumnModel().getSelectionModel().getAnchorSelectionIndex();

        getSelectionModel().setValueIsAdjusting(true);
        getColumnModel().getSelectionModel().setValueIsAdjusting(true);
        setRowSelectionInterval(0, getRowCount() - 1);
        getSelectionModel().addSelectionInterval(rowAnchor, rowLead);
        setColumnSelectionInterval(0, getColumnCount() - 1);
        getColumnModel().getSelectionModel().addSelectionInterval(columnAnchor, columnLead);
        getSelectionModel().setValueIsAdjusting(false);
        getColumnModel().getSelectionModel().setValueIsAdjusting(false);
    }

    public void clearSelection() {
        getSelectionModel().clearSelection();
        getColumnModel().getSelectionModel().clearSelection();
    }

    public void setRowSelectionInterval(final int start, final int end) {
        checkSelectionInterval(start, end, getRowCount());
        getSelectionModel().setSelectionInterval(start, end);
    }

    public void setColumnSelectionInterval(final int start, final int end) {
        checkSelectionInterval(start, end, getColumnCount());
        getColumnModel().getSelectionModel().setSelectionInterval(start, end);
    }

    public void addRowSelectionInterval(final int start, final int end) {
        checkSelectionInterval(start, end, getRowCount());
        getSelectionModel().addSelectionInterval(start, end);
    }

    public void addColumnSelectionInterval(final int start, final int end) {
        checkSelectionInterval(start, end, getColumnCount());
        getColumnModel().getSelectionModel().addSelectionInterval(start, end);
    }

    public void removeRowSelectionInterval(final int start, final int end) {
        checkSelectionInterval(start, end, getRowCount());
        getSelectionModel().removeSelectionInterval(start, end);
    }

    public void removeColumnSelectionInterval(final int start, final int end) {
        checkSelectionInterval(start, end, getColumnCount());
        getColumnModel().getSelectionModel().removeSelectionInterval(start, end);
    }

    public int getSelectedRow() {
        return getSelectionModel().getMinSelectionIndex();
    }

    public int getSelectedColumn() {
        return getColumnModel().getSelectionModel().getMinSelectionIndex();
    }

    public int[] getSelectedRows() {
        return getSelectedIndices(getSelectionModel());
    }

    public int[] getSelectedColumns() {
        return getSelectedIndices(getColumnModel().getSelectionModel());
    }

    public int getSelectedRowCount() {
        return getSelectedCount(getSelectionModel());
    }

    public int getSelectedColumnCount() {
        return getSelectedCount(getColumnModel().getSelectionModel());
    }

    public boolean isRowSelected(final int row) {
        return getSelectionModel().isSelectedIndex(row);
    }

    public boolean isColumnSelected(final int column) {
        return getColumnModel().getSelectionModel().isSelectedIndex(column);
    }

    public boolean isCellSelected(final int row, final int column) {
        return getRowSelectionAllowed() && isRowSelected(row) && (!getColumnSelectionAllowed() || isColumnSelected(column))
               || getColumnSelectionAllowed() && isColumnSelected(column) && (!getRowSelectionAllowed() || isRowSelected(row));
    }

    public void changeSelection(final int row, final int column, final boolean toggle, final boolean extend) {
        if (!toggle && !extend) {
            setRowSelectionInterval(row, row);
            setColumnSelectionInterval(column, column);
        } else if (!toggle && extend) {
            setRowSelectionInterval(getSelectionModel().getAnchorSelectionIndex(), row);
            setColumnSelectionInterval(getColumnModel().getSelectionModel().getAnchorSelectionIndex(), column);
        } else if (toggle && !extend) {
            if (isCellSelected(row, column)) {
                removeRowSelectionInterval(row, row);
                removeColumnSelectionInterval(column, column);
            } else {
                addRowSelectionInterval(row, row);
                addColumnSelectionInterval(column, column);
            }
        } else {
            getSelectionModel().setAnchorSelectionIndex(row);
            getColumnModel().getSelectionModel().setAnchorSelectionIndex(column);
        }

        int currentRow = getSelectionModel().getLeadSelectionIndex();
        int currentColumn = getColumnModel().getSelectionModel().getLeadSelectionIndex();

        if (currentRow != -1 && currentColumn != -1) {
            scrollRectToVisible(getCellRect(currentRow, currentColumn, true));
        }
    }

    public Color getSelectionForeground() {
        return selectionForeground;
    }

    public void setSelectionForeground(final Color fg) {
        Color oldValue = selectionForeground;
        selectionForeground = fg;

        firePropertyChange(SELECTION_FOREGROUND_PROPERTY, oldValue, fg);
    }

    public Color getSelectionBackground() {
        return selectionBackground;
    }

    public void setSelectionBackground(final Color bg) {
        Color oldValue = selectionBackground;
        selectionBackground = bg;

        firePropertyChange(SELECTION_BACKGROUND_PROPERTY, oldValue, bg);
    }

    public TableColumn getColumn(final Object identifier) {
        int index = getColumnModel().getColumnIndex(identifier);
        if (index == -1) {
            throw new IllegalArgumentException("Column does not exist");
        }
        return getColumnModel().getColumn(index);
    }

    public int convertColumnIndexToModel(final int viewIndex) {
        if (viewIndex < 0) {
            return viewIndex;
        }

        return getColumnModel().getColumn(viewIndex).getModelIndex();
    }

    public int convertColumnIndexToView(final int modelIndex) {
        if (modelIndex < 0) {
            return modelIndex;
        }

        TableColumnModel columnModel = getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            if (columnModel.getColumn(i).getModelIndex() == modelIndex) {
                return i;
            }
        }

        return -1;
    }

    public int getRowCount() {
        return getModel().getRowCount();
    }

    public int getColumnCount() {
        return getColumnModel().getColumnCount();
    }

    public String getColumnName(final int viewIndex) {
        return getModel().getColumnName(convertColumnIndexToModel(viewIndex));
    }

    public Class getColumnClass(final int viewIndex) {
        return getModel().getColumnClass(convertColumnIndexToModel(viewIndex));
    }

    public Object getValueAt(final int row, final int viewColumn) {
        return getModel().getValueAt(row, convertColumnIndexToModel(viewColumn));
    }

    public void setValueAt(final Object value, final int row, final int viewColumn) {
        getModel().setValueAt(value, row, convertColumnIndexToModel(viewColumn));
    }

    public boolean isCellEditable(final int row, final int viewColumn) {
        return getModel().isCellEditable(row, convertColumnIndexToModel(viewColumn));
    }

    public void addColumn(final TableColumn column) {
        if (column.getHeaderValue() == null) {
            column.setHeaderValue(getModel().getColumnName(column.getModelIndex()));
        }

        getColumnModel().addColumn(column);
    }

    public void removeColumn(final TableColumn column) {
        getColumnModel().removeColumn(column);
    }

    public void moveColumn(final int viewColumn, final int targetViewColumn) {
        getColumnModel().moveColumn(viewColumn, targetViewColumn);
    }

    public int columnAtPoint(final Point p) {
        return getTableHeader().columnAtPoint(p);
    }

    public int rowAtPoint(final Point p) {
        int previousWidth = 0;
        for (int i = 0; i < getRowCount(); i++) {
            int height = getRowHeight(i);
            if (p.y >= previousWidth && p.y < previousWidth + height) {
                return i;
            }
            previousWidth += height;
        }

        return -1;
    }

    public Rectangle getCellRect(final int row, final int viewColumn, final boolean includeSpacing) {
        Rectangle result = new Rectangle();

        boolean useSpacing = includeSpacing;
        if (row >= 0 && row < getRowCount()) {
            for (int i = 0; i < row; i++) {
                result.y += getRowHeight(i);
            }
            result.height = getRowHeight(row);
        } else {
            useSpacing = true;
        }

        if (viewColumn >= 0 && viewColumn < getColumnCount()) {
            TableColumnModel columnModel = getColumnModel();
            if (getComponentOrientation().isLeftToRight()) {
                for (int i = 0; i < viewColumn; i++) {
                    result.x += columnModel.getColumn(i).getWidth();
                }
            } else {
                for (int i = 0; i < columnModel.getColumnCount(); i++) {
                    result.x += columnModel.getColumn(i).getWidth();
                }
                for (int i = 0; i <= viewColumn; i++) {
                    result.x -= columnModel.getColumn(i).getWidth();
                }
            }
            result.width = columnModel.getColumn(viewColumn).getWidth();
        } else {
            useSpacing = true;
        }

        if (!useSpacing) {
            Dimension spacing = getIntercellSpacing();
            result.x += spacing.width / 2;
            result.width -= spacing.width;
            result.y += spacing.height / 2;
            result.height -= spacing.height;
        }

        return result;
    }

    public void sizeColumnsToFit(final boolean lastColumnOnly) {
        int previousSetting = getAutoResizeMode();
        if (lastColumnOnly) {
            setAutoResizeMode(AUTO_RESIZE_LAST_COLUMN);
        } else {
            setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
        }
        doLayout();
        setAutoResizeMode(previousSetting);
    }

    public void sizeColumnsToFit(final int resizingColumn) {
    }

    public String getToolTipText(final MouseEvent me) {
        int row = rowAtPoint(me.getPoint());
        int column = columnAtPoint(me.getPoint());

        if (row == -1 || column == -1) {
            return null;
        }

        TableCellRenderer renderer = getCellRenderer(row, column);
        if (renderer == null) {
            return null;
        }
        Component renderingComponent = renderer.getTableCellRendererComponent(this, getValueAt(row, column), isCellSelected(row, column), false, row, column);
        return renderer instanceof JComponent ? ((JComponent)renderingComponent).getToolTipText() : null;
    }

    public void setSurrendersFocusOnKeystroke(final boolean surrendersFocusOnKeystroke) {
        this.surrendersFocusOnKeystroke = surrendersFocusOnKeystroke;
    }

    public boolean getSurrendersFocusOnKeystroke() {
        return surrendersFocusOnKeystroke;
    }

    public boolean editCellAt(final int row, final int column) {
        return editCellAt(row, column, null);
    }

    public boolean editCellAt(final int row, final int viewColumn, final EventObject e) {
        if (isEditing()) {
            getCellEditor().stopCellEditing();
            if (isEditing()) {
                return false;
            }
        }

        if (row >= getRowCount() || viewColumn > getColumnModel().getColumnCount()) {
            return false;
        }
        if (!isCellEditable(row, viewColumn)) {
            return false;
        }

        TableCellEditor editor = getCellEditor(row, viewColumn);
        if (editor == null) {
            return false;
        }

        editingColumn = viewColumn;
        if (!editor.isCellEditable(e)) {
            editingColumn = -1;
            return false;
        }

        setCellEditor(editor);
        setEditingRow(row);
        setEditingColumn(viewColumn);
        editorComp = prepareEditor(getCellEditor(), row, viewColumn);
        getCellEditor().addCellEditorListener(this);
        add(editorComp);
        editorComp.setBounds(getCellRect(row, viewColumn, false));

        return true;
    }

    public boolean isEditing() {
        return getCellEditor() != null;
    }

    public Component getEditorComponent() {
        return editorComp;
    }

    public int getEditingRow() {
        return editingRow;
    }

    public void setEditingRow(final int row) {
        editingRow = row;
    }

    public int getEditingColumn() {
        return editingColumn;
    }

    public void setEditingColumn(final int column) {
        editingColumn = column;
    }

    public TableUI getUI() {
        return (TableUI)ui;
    }

    public void setUI(final TableUI ui) {
        super.setUI(ui);
    }

    public void updateUI() {
        setUI((TableUI)UIManager.getUI(this));
    }

    public String getUIClassID() {
        return UI_CLASS_ID;
    }

    public void setModel(final TableModel model) {
        if (model == null) {
            throw new IllegalArgumentException("Model must be not null");
        }

        TableModel oldValue = dataModel;
        if (oldValue != null) {
            oldValue.removeTableModelListener(this);
        }

        dataModel = model;
        dataModel.addTableModelListener(this);
        firePropertyChange(StringConstants.MODEL_PROPERTY_CHANGED, oldValue, model);

        if (oldValue != dataModel) {
            tableChanged(new TableModelEvent(dataModel, TableModelEvent.HEADER_ROW, TableModelEvent.HEADER_ROW, TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE));
        }
    }

    public TableModel getModel() {
        return dataModel;
    }

    public void setColumnModel(final TableColumnModel model) {
        if (model == null) {
            throw new IllegalArgumentException("Column model must be not null");
        }

        TableColumnModel oldValue = columnModel;
        if (oldValue != null) {
            oldValue.removeColumnModelListener(this);
            oldValue.removeColumnModelListener(getTableHeader());
        }

        columnModel = model;
        JTableHeader header = getTableHeader();
        if (header != null) {
            columnModel.addColumnModelListener(header);
        }
        columnModel.addColumnModelListener(this);
    }

    public TableColumnModel getColumnModel() {
        return columnModel;
    }

    public void setSelectionModel(final ListSelectionModel model) {
        if (model == null) {
            throw new IllegalArgumentException("Selection model must be not null");
        }

        ListSelectionModel oldValue = selectionModel;
        if (oldValue != null) {
            oldValue.removeListSelectionListener(this);
        }

        selectionModel = model;
        selectionModel.addListSelectionListener(this);
        alignSelectionModelToRows();

        firePropertyChange(StringConstants.SELECTION_MODEL_PROPERTY, oldValue, model);
    }

    public ListSelectionModel getSelectionModel() {
        return selectionModel;
    }

    public void tableChanged(final TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE
            && e.getFirstRow() == TableModelEvent.HEADER_ROW
            && e.getLastRow() == TableModelEvent.HEADER_ROW) {

            if (getAutoCreateColumnsFromModel()) {
                createDefaultColumnsFromModel();
            }
        }

        if (getSelectionModel() != null) {
            updateSelectionModel(getSelectionModel(), e);
        }
        if (getColumnModel().getSelectionModel() != null) {
            updateColumnSelectionModel(getColumnModel().getSelectionModel(), e);
        }

        revalidate();
        repaint();
    }

    public void columnAdded(final TableColumnModelEvent e) {
        if (isEditing()) {
            getCellEditor().cancelCellEditing();
        }
        resizeAndRepaint();
    }

    public void columnRemoved(final TableColumnModelEvent e) {
        if (isEditing()) {
            getCellEditor().cancelCellEditing();
        }
        resizeAndRepaint();
    }

    public void columnMoved(final TableColumnModelEvent e) {
        if (isEditing()) {
            getCellEditor().cancelCellEditing();
        }
        repaint();
    }

    public void columnMarginChanged(final ChangeEvent e) {
        if (isEditing()) {
            getCellEditor().cancelCellEditing();
        }
        resizeAndRepaint();
    }

    public void columnSelectionChanged(final ListSelectionEvent e) {
        repaint();
    }

    public void valueChanged(final ListSelectionEvent e) {
        repaint();
    }

    public void editingStopped(final ChangeEvent e) {
        if (isEditing()) {
            setValueAt(getCellEditor().getCellEditorValue(), getEditingRow(), getEditingColumn());
            cleanUpAfterEditing();
        }
    }

    public void editingCanceled(final ChangeEvent e) {
        if (isEditing()) {
            cleanUpAfterEditing();
        }
    }

    public void setPreferredScrollableViewportSize(final Dimension size) {
        preferredViewportSize = size;
    }

    public Dimension getPreferredScrollableViewportSize() {
        return preferredViewportSize;
    }

    public int getScrollableUnitIncrement(final Rectangle visibleRect, final int orientation, final int direction) {
        if (orientation == SwingConstants.HORIZONTAL) {
            return 100;
        } else {
            return getRowHeight();
        }
    }

    public int getScrollableBlockIncrement(final Rectangle visibleRect, final int orientation, final int direction) {
        if (orientation == SwingConstants.HORIZONTAL) {
            return visibleRect.width;
        } else {
            return visibleRect.height;
        }
    }

    public boolean getScrollableTracksViewportWidth() {
        return getAutoResizeMode() != AUTO_RESIZE_OFF;
    }

    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

    public void setCellEditor(final TableCellEditor editor) {
        cellEditor = editor;
    }

    public TableCellEditor getCellEditor() {
        return cellEditor;
    }

    public TableCellRenderer getCellRenderer(final int row, final int viewColumn) {
        TableCellRenderer result = getColumnModel().getColumn(viewColumn).getCellRenderer();
        if (result == null) {
            result = getDefaultRenderer(getColumnClass(viewColumn));
        }

        return result;
    }

    public Component prepareRenderer(final TableCellRenderer renderer, final int row, final int viewColumn) {
        boolean hasFocus = false;

        return renderer.getTableCellRendererComponent(this, getValueAt(row, viewColumn), isCellSelected(row, viewColumn), hasFocus, row, viewColumn);
    }

    public TableCellEditor getCellEditor(final int row, final int viewColumn) {
        TableCellEditor result = getColumnModel().getColumn(viewColumn).getCellEditor();
        if (result == null) {
            result = getDefaultEditor(getColumnClass(viewColumn));
        }

        return result;
    }

    public Component prepareEditor(final TableCellEditor editor, final int row, final int viewColumn) {
        return editor.getTableCellEditorComponent(this, getValueAt(row, viewColumn), isCellSelected(row, viewColumn), row, viewColumn);
    }

    public void removeEditor() {
        if (!isEditing()) {
            return;
        }

        setCellEditor(null);
        remove(getEditorComponent());
        editorComp = null;
        setEditingRow(-1);
        setEditingColumn(-1);
    }

    public void doLayout() {
        if (getAutoResizeMode() == AUTO_RESIZE_OFF) {
            return;
        }
        TableColumn resizingColumn = getTableHeader().getResizingColumn();
        if (resizingColumn == null) {
            ResizableElements resizable = new ResizableElements() {
                public int getElementsCount() {
                    return getColumnCount();
                }

                public TableColumn getElement(final int i) {
                    return getColumnModel().getColumn(i);
                }
            };
            adjustColumns(getWidth(), resizable);
        } else {
            final int resizingColIndex = getColumnModel().getColumnIndex(resizingColumn.getIdentifier());
            if (resizingColIndex + 1 == getColumnCount()) {
                int remWidth = getWidth();
                for (int i = 0; i < getColumnCount() - 1; i++) {
                    remWidth -= getColumnModel().getColumn(i).getWidth();
                }
                resizingColumn.setWidth(remWidth);

                return;
            }
            if (getAutoResizeMode() == AUTO_RESIZE_NEXT_COLUMN) {
                autoResizeNextColumn(resizingColumn);
                return;
            }
            if (getAutoResizeMode() == AUTO_RESIZE_LAST_COLUMN) {
                autoResizeLastColumn(resizingColumn);
                return;
            }
            if (getAutoResizeMode() == AUTO_RESIZE_SUBSEQUENT_COLUMNS) {
                autoResizeSubsequentColumns(resizingColumn);
                return;
            }
            if (getAutoResizeMode() == AUTO_RESIZE_ALL_COLUMNS) {
                ResizableElements resizable = new ResizableElements() {
                    public int getElementsCount() {
                        return getColumnCount();
                    }

                    public TableColumn getElement(final int i) {
                        return getColumnModel().getColumn(i);
                    }
                };
                adjustColumns(getWidth(), resizable);
            }
        }
    }

    public AccessibleContext getAccessibleContext() {
        if (accessibleContext == null) {
            accessibleContext = new AccessibleJTable();
        }

        return accessibleContext;
    }

// TODO: 1.5 migration
//    public boolean print() throws PrinterException {
//        return false;
//    }
//
//    public boolean print(PrintMode printMode) throws PrinterException {
//        return false;
//    }
//
//    public boolean print(PrintMode printMode, MessageFormat headerFormat, MessageFormat footerFormat) throws PrinterException {
//        return false;
//    }
//
//    public boolean print(PrintMode printMode, MessageFormat headerFormat, MessageFormat footerFormat,
//                         boolean showPrintDialog, PrintRequestAttributeSet attr, boolean interactive) throws PrinterException, HeadlessException {
//
//        return false;
//    }
//
//    public Printable getPrintable(PrintMode printMode, MessageFormat headerFormat, MessageFormat footerFormat) {
//
//        return null;
//    }
//
//    public static enum PrintMode extends Enum {
//
//    }

    protected void initializeLocalVars() {
        dragEnabled = false;
        setRowMargin(1);
        setTableHeader(createDefaultTableHeader());
        setRowHeight(16);
        setShowHorizontalLines(true);
        setShowVerticalLines(true);
        setAutoResizeMode(AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        setPreferredScrollableViewportSize(new Dimension(450, 400));
        setRowSelectionAllowed(true);
        createDefaultRenderers();
        createDefaultEditors();

        ToolTipManager.sharedInstance().registerComponent(this);
    }

    protected void configureEnclosingScrollPane() {
        JScrollPane enclosingScrollPane = getEnclosingScrollPane();
        if (enclosingScrollPane == null) {
            return;
        }
        enclosingScrollPane.setColumnHeaderView(getTableHeader());
        enclosingScrollPane.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));

    }

    protected void unconfigureEnclosingScrollPane() {
        JScrollPane enclosingScrollPane = getEnclosingScrollPane();
        if (enclosingScrollPane == null) {
            return;
        }
        enclosingScrollPane.setColumnHeaderView(null);
        enclosingScrollPane.setBorder(null);
    }

    protected void createDefaultRenderers() {
        defaultRenderersByColumnClass = new Hashtable();
        defaultRenderersByColumnClass.put(Date.class, new DateTableCellRenderer());
        defaultRenderersByColumnClass.put(ImageIcon.class, new IconTableCellRenderer());
        defaultRenderersByColumnClass.put(Icon.class, new IconTableCellRenderer());
        defaultRenderersByColumnClass.put(Float.class, new NumberTableCellRenderer());
        defaultRenderersByColumnClass.put(Double.class, new NumberTableCellRenderer());
        defaultRenderersByColumnClass.put(Number.class, new NumberTableCellRenderer());
        defaultRenderersByColumnClass.put(Boolean.class, new BooleanTableCellRenderer());
        defaultRenderersByColumnClass.put(Object.class, new DefaultTableCellRenderer());
    }

    protected void createDefaultEditors() {
        defaultEditorsByColumnClass = new Hashtable();
        defaultEditorsByColumnClass.put(Number.class, new NumberEditor());
        defaultEditorsByColumnClass.put(Boolean.class, new BooleanEditor());
        defaultEditorsByColumnClass.put(Object.class, new ObjectEditor());
    }

    protected TableModel createDefaultDataModel() {
        return new DefaultTableModel();
    }

    protected TableColumnModel createDefaultColumnModel() {
        return new DefaultTableColumnModel();
    }

    protected ListSelectionModel createDefaultSelectionModel() {
        return new DefaultListSelectionModel();
    }

    protected JTableHeader createDefaultTableHeader() {
        return new JTableHeader(getColumnModel());
    }

    protected void resizeAndRepaint() {
        revalidate();
        repaint();
    }

    protected void processKeyEvent(final KeyEvent event) {
        super.processKeyEvent(event);
        if (event.isConsumed()) {
            wasConsumed = true;
        }

        if (event.getID() == KeyEvent.KEY_RELEASED) {
            wasConsumed = false;
            return;
        }
        if (wasConsumed) {
            return;
        }

        if (event.getKeyCode() == KeyEvent.VK_SHIFT
            || event.getKeyCode() == KeyEvent.VK_ALT
            || event.getKeyCode() == KeyEvent.VK_ALT_GRAPH
            || event.getKeyCode() == KeyEvent.VK_CONTROL
            || event.getKeyCode() == KeyEvent.VK_PRINTSCREEN
            || event.getKeyCode() == KeyEvent.VK_CAPS_LOCK
            || event.getKeyCode() == KeyEvent.VK_NUM_LOCK
            || event.getKeyCode() == KeyEvent.VK_SCROLL_LOCK
            || event.isAltDown()
            || event.isControlDown()) {

            return;
        }

        if (!isEditing()) {
            int currentRow = getSelectionModel().getLeadSelectionIndex();
            int currentColumn = getColumnModel().getSelectionModel().getLeadSelectionIndex();
            if (currentRow == -1 || currentColumn == -1) {
                return;
            }
            if (!editCellAt(currentRow, currentColumn, event)) {
                return;
            }
            if (isEditing() && getSurrendersFocusOnKeystroke()) {
                getEditorComponent().requestFocus();
            }
        }

        if (isEditing() && getEditorComponent() instanceof JComponent) {
            KeyEvent editorEvent = new KeyEvent(getEditorComponent(), event.getID(), event.getWhen(), event.getModifiers(), event.getKeyCode(), event.getKeyChar(), event.getKeyLocation());
            ((JComponent)getEditorComponent()).processKeyEvent(editorEvent);
        }
    }

    private JScrollPane getEnclosingScrollPane() {
        if (getParent() instanceof JViewport
            && ((JViewport)getParent()).getView() == this) {

            if (getParent().getParent() instanceof JScrollPane) {
                return (JScrollPane)getParent().getParent();
            }
        }

        return null;
    }

    private Object getClosestClass(final Class columnClass, final Hashtable classes) {
        Class currentClass = columnClass;
        do {
            Object value = classes.get(currentClass);
            if (value != null) {
                return value;
            }
            currentClass = currentClass.getSuperclass();
        } while(currentClass != null);

        return null;
    }

    private void checkSelectionInterval(final int start, final int end, final int bound) {
        if (start < 0 || end < 0 || start >= bound || end >= bound) {
            throw new IllegalArgumentException("Illegal selection interval is specified. Should be in [0, " + (bound - 1) + "]");
        }
    }

    private int[] getSelectedIndices(final ListSelectionModel selModel) {
        int size = getSelectedCount(selModel);
        int[] result = new int[size];
        if (size == 0) {
            return result;
        }

        int count = 0;
        for (int i = selModel.getMinSelectionIndex(); i <= selModel.getMaxSelectionIndex(); i++) {
            if (selModel.isSelectedIndex(i)) {
                result[count++] = i;
            }
        }

        return result;
    }

    private int getSelectedCount(final ListSelectionModel selModel) {
        if (selModel.isSelectionEmpty()) {
            return 0;
        }

        int result = 0;
        for (int i = selModel.getMinSelectionIndex(); i <= selModel.getMaxSelectionIndex(); i++) {
            if (selModel.isSelectedIndex(i)) {
                result++;
            }
        }

        return result;
    }

    private void updateSelectionModel(final ListSelectionModel model, final TableModelEvent e) {
        if (e.getType() == TableModelEvent.INSERT) {
            model.insertIndexInterval(e.getFirstRow(), e.getLastRow() - e.getFirstRow() + 1, true);
            alignSelectionModelToRows();
        }
        if (e.getType() == TableModelEvent.DELETE) {
            model.removeIndexInterval(e.getFirstRow(), e.getLastRow());
            alignSelectionModelToRows();
        }

        if (e.getType() == TableModelEvent.UPDATE
            && e.getColumn() == TableModelEvent.ALL_COLUMNS) {

            model.clearSelection();
        }
    }

    private void updateColumnSelectionModel(final ListSelectionModel model, final TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE
            && e.getFirstRow() == TableModelEvent.HEADER_ROW
            && e.getLastRow() == TableModelEvent.HEADER_ROW) {

            model.setAnchorSelectionIndex(-1);
            model.setLeadSelectionIndex(0);
            model.clearSelection();
        }
    }

    private void autoResizeSubsequentColumns(final TableColumn resizingColumn) {
        final int resizingColIndex = getColumnModel().getColumnIndex(resizingColumn.getIdentifier());
        ResizableElements resizable = new ResizableElements() {
            public int getElementsCount() {
                return getColumnCount() - (resizingColIndex + 1);
            }

            public TableColumn getElement(final int i) {
                return getColumnModel().getColumn(i + (resizingColIndex + 1));
            }
        };
        int width = 0;
        for (int i = 0; i <= resizingColIndex; i++) {
            width += getColumnModel().getColumn(i).getWidth();
        }
        int minSize = 0;
        for (int i = resizingColIndex + 1; i < getColumnCount(); i++) {
            minSize += getColumnModel().getColumn(i).getMinWidth();
        }
        if (getWidth() - width > minSize) {
            adjustColumns(getWidth() - width, resizable);
        } else {
            width = 0;
            for (int i = 0; i < resizingColIndex; i++) {
                width += getColumnModel().getColumn(i).getWidth();
            }
            for (int i = resizingColIndex + 1; i < getColumnCount(); i++) {
                getColumnModel().getColumn(i).setWidth(getColumnModel().getColumn(i).getMinWidth());
            }
            resizingColumn.setWidth(getWidth() - width - minSize);
        }
    }

    private void autoResizeNextColumn(final TableColumn resizingColumn) {
        int resColIndex = getColumnModel().getColumnIndex(resizingColumn.getIdentifier());
        TableColumn nextColumn = getColumnModel().getColumn(resColIndex + 1);
        int colsSumWidth = getWidth();
        for (int i = 0; i < getColumnCount(); i++) {
            if (i != resColIndex && i != resColIndex + 1) {
                colsSumWidth -= getColumnModel().getColumn(i).getWidth();
            }
        }
        if (resizingColumn.getWidth() + nextColumn.getMinWidth() < colsSumWidth) {
            nextColumn.setWidth(colsSumWidth - resizingColumn.getWidth());
        } else {
            int resizingColWidth = colsSumWidth - nextColumn.getMinWidth();
            resizingColumn.setWidth(resizingColWidth);
            nextColumn.setWidth(nextColumn.getMinWidth());
        }
    }

    private void autoResizeLastColumn(final TableColumn resizingColumn) {
        int resColIndex = getColumnModel().getColumnIndex(resizingColumn.getIdentifier());
        TableColumn lastColumn = getColumnModel().getColumn(getColumnCount() - 1);
        int colsSumWidth = getWidth();
        for (int i = 0; i < getColumnCount(); i++) {
            if (i != resColIndex && i != getColumnCount() - 1) {
                colsSumWidth -= getColumnModel().getColumn(i).getWidth();
            }
        }
        if (resizingColumn.getWidth() + lastColumn.getMinWidth() < colsSumWidth) {
            lastColumn.setWidth(colsSumWidth - resizingColumn.getWidth());
        } else {
            int resizingColWidth = colsSumWidth - lastColumn.getMinWidth();
            resizingColumn.setWidth(resizingColWidth);
            lastColumn.setWidth(lastColumn.getMinWidth());
        }
    }

    private void adjustColumns(final long targetSize, final ResizableElements resizable) {
        if (resizable.getElementsCount() == 0) {
            return;
        }

        long minColsWidth = 0;
        long maxColsWidth = 0;
        long colsWidth = 0;
        for (int i = 0; i < resizable.getElementsCount(); i++) {
            TableColumn column = resizable.getElement(i);
            minColsWidth += column.getMinWidth();
            maxColsWidth += column.getMaxWidth();
            colsWidth += column.getPreferredWidth();
        }
        long colsDelta = targetSize - colsWidth;
        int[] newWidthes = new int[resizable.getElementsCount()];
        int newTableWidth = 0;
        for (int i = 0; i < resizable.getElementsCount(); i++) {
            TableColumn column = resizable.getElement(i);
            int maxWidth = column.getMaxWidth();
            int minWidth = column.getMinWidth();
            int curWidth = column.getPreferredWidth();
            double multiplier = (colsDelta > 0)
                    ? (double)(maxWidth - curWidth) / (double)(maxColsWidth - colsWidth)
                    : (double)(curWidth - minWidth) / (double)(colsWidth - minColsWidth);
            int delta = (int)(colsDelta * multiplier);
            int newWidth = curWidth + delta;
            if (newWidth > maxWidth) {
                newWidth = maxWidth;
            }
            if (newWidth < minWidth) {
                newWidth = minWidth;
            }
            newWidthes[i] = newWidth;
            newTableWidth += newWidthes[i];
        }
        int diff = (int)targetSize - newTableWidth;
        int absDiff = Math.abs(diff);
        while (absDiff != 0) {
            if (diff > 0) {
                adjustNewWidthesToIncreaseSize(newWidthes);
            } else {
                adjustNewWidthesToDecreaseSize(newWidthes);
            }
            absDiff--;
        }
        for (int i = 0; i < resizable.getElementsCount(); i++) {
            resizable.getElement(i).setWidth(newWidthes[i]);
        }
    }

    private void cleanUpAfterEditing() {
        getCellEditor().removeCellEditorListener(this);
        removeEditor();
        repaint(getCellRect(getEditingRow(), getEditingColumn(), false));
        requestFocus();
    }

    private void adjustNewWidthesToDecreaseSize(final int[] widths) {
        int result = widths.length - 1;
        int max = widths[widths.length - 1];
        for (int i = widths.length - 1; i >=0 ; i--) {
            if (widths[i] > max) {
                result = i;
            }
        }

        widths[result]--;
    }

    private void adjustNewWidthesToIncreaseSize(final int[] widths) {
        int result = widths.length - 1;
        int min = widths[widths.length - 1];
        for (int i = widths.length - 1; i >=0 ; i--) {
            if (widths[i] < min) {
                result = i;
            }
        }

        widths[result]++;
    }

    private void alignSelectionModelToRows() {
        if (getRowCount() == 0) {
            if (selectionModel.getAnchorSelectionIndex() >= 0) {
                selectionModel.setValueIsAdjusting(true);
                selectionModel.setAnchorSelectionIndex(-1);
                selectionModel.setLeadSelectionIndex(-1);
                selectionModel.setValueIsAdjusting(false);
            }
        } else if (selectionModel.getLeadSelectionIndex() < 0) {
            selectionModel.removeSelectionInterval(0, 0);
        }
     }

    private interface ResizableElements {
        int getElementsCount();
        TableColumn getElement(int i);
    }

    private class MutableInteger {
        private int value = -1;

        public void setValue(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private class BooleanTableCellRenderer extends JCheckBox implements TableCellRenderer {
        private Border noFocusBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
        private Border focusBorder;
        private Color focusCellBackground;
        private Color focusCellForeground;

        public BooleanTableCellRenderer() {
            updateUI();
        }

        public void updateUI() {
            super.updateUI();
            focusBorder = UIManager.getBorder("Table.focusCellHighlightBorder");
            focusCellBackground = UIManager.getColor("Table.focusCellBackground");
            focusCellForeground = UIManager.getColor("Table.focusCellForeground");
        }

        public Component getTableCellRendererComponent(final JTable table,
                                                       final Object value,
                                                       final boolean isSelected,
                                                       final boolean hasFocus,
                                                       final int row, final int column) {
            setValue(value);
            setFont(table.getFont());
            setHorizontalAlignment(JCheckBox.CENTER);
            if (hasFocus) {
                setBorder(focusBorder);
                if (isSelected) {
                    setBackground(table.getSelectionBackground());
                    setForeground(table.getSelectionForeground());
                } else if (table.isCellEditable(row, column)) {
                    setBackground(focusCellBackground);
                    setForeground(focusCellForeground);
                } else {
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }
            } else {
                setBorder(noFocusBorder);
                if (isSelected) {
                    setBackground(table.getSelectionBackground());
                    setForeground(table.getSelectionForeground());
                } else {
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }
            }

            return this;
        }

        public boolean isOpaque() {
            return true;
        }

        private void setValue(final Object value) {
            if (value == null) {
                setSelected(false);
            } else {
                setSelected(Boolean.valueOf(value.toString()).booleanValue());
            }
        }
    }

    private class DateTableCellRenderer extends DefaultTableCellRenderer.UIResource {
        public Component getTableCellRendererComponent(final JTable table,
                final Object value,
                final boolean isSelected,
                final boolean hasFocus,
                final int row, final int column) {

            return super.getTableCellRendererComponent(table, value != null ? DateFormat.getDateInstance().format((Date)value) : null, isSelected, hasFocus, row, column);
        }
    }

    private class NumberTableCellRenderer extends DefaultTableCellRenderer.UIResource {
        public NumberTableCellRenderer() {
            super();
            setHorizontalAlignment(JLabel.RIGHT);
        }

        public Component getTableCellRendererComponent(final JTable table,
                final Object value,
                final boolean isSelected,
                final boolean hasFocus,
                final int row, final int column) {

            return super.getTableCellRendererComponent(table, value != null ? NumberFormat.getNumberInstance().format(value) : null, isSelected, hasFocus, row, column);
        }
    }

    private class IconTableCellRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(final JTable table,
                final Object value,
                final boolean isSelected,
                final boolean hasFocus,
                final int row, final int column) {

            
            JLabel result = (JLabel)super.getTableCellRendererComponent(table, null, isSelected, hasFocus, row, column);
            if (value != null) {
                if (value instanceof Icon) {
                    result.setIcon((Icon)value);
                } else {
                    result.setIcon(new ImageIcon(value.toString()));
                }
                result.setHorizontalAlignment(SwingConstants.CENTER);
                result.setVerticalAlignment(SwingConstants.CENTER);
            } else {
                result.setIcon(null);
            }

            return result;
        }
    }
}

