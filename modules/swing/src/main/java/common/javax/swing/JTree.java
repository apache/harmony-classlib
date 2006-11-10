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
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Vector;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleAction;
import javax.accessibility.AccessibleComponent;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleSelection;
import javax.accessibility.AccessibleStateSet;
import javax.accessibility.AccessibleText;
import javax.accessibility.AccessibleValue;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.plaf.TreeUI;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class JTree extends JComponent implements Scrollable, Accessible {
    private static final long serialVersionUID = -3884445419090632712L;
    
    protected class AccessibleJTree extends AccessibleJComponent implements AccessibleSelection, TreeSelectionListener, TreeModelListener, TreeExpansionListener {
        private static final long serialVersionUID = -8714565563782619758L;
        
        protected class AccessibleJTreeNode extends AccessibleContext implements Accessible, AccessibleComponent, AccessibleSelection, AccessibleAction {
            public AccessibleJTreeNode(JTree t, TreePath p, Accessible ap) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public AccessibleContext getAccessibleContext() {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public String getAccessibleName() {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public void setAccessibleName(final String s) {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public String getAccessibleDescription() {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public void setAccessibleDescription(final String s) {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public AccessibleRole getAccessibleRole() {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public AccessibleStateSet getAccessibleStateSet() {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public Accessible getAccessibleParent() {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public int getAccessibleIndexInParent() {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public int getAccessibleChildrenCount() {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public Accessible getAccessibleChild(final int i) {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public Locale getLocale() {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public void addPropertyChangeListener(final PropertyChangeListener l) {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public void removePropertyChangeListener(final PropertyChangeListener l) {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public AccessibleAction getAccessibleAction() {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public AccessibleComponent getAccessibleComponent() {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public AccessibleSelection getAccessibleSelection() {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public AccessibleText getAccessibleText() {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
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

            protected Point getLocationInJTree() {
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

            public int getAccessibleActionCount() {
                throw new UnsupportedOperationException("Not implemented");
            }

            public String getAccessibleActionDescription(final int i) {
                throw new UnsupportedOperationException("Not implemented");
            }

            public boolean doAccessibleAction(final int i) {
                throw new UnsupportedOperationException("Not implemented");
            }
        }

        public AccessibleJTree() {
//            throw new UnsupportedOperationException("Not implemented");
        }

        public void valueChanged(final TreeSelectionEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void fireVisibleDataPropertyChange() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void treeNodesChanged(final TreeModelEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void treeNodesInserted(final TreeModelEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void treeNodesRemoved(final TreeModelEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void treeStructureChanged(final TreeModelEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void treeCollapsed(final TreeExpansionEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void treeExpanded(final TreeExpansionEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override
        public AccessibleRole getAccessibleRole() {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override
        public Accessible getAccessibleAt(final Point p) {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override
        public int getAccessibleChildrenCount() {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override
        public Accessible getAccessibleChild(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override
        public int getAccessibleIndexInParent() {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override
        public AccessibleSelection getAccessibleSelection() {
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
    }

    public static class DynamicUtilTreeNode extends DefaultMutableTreeNode {
        private static final long serialVersionUID = -2795134038906279615L;
        
        protected boolean hasChildren;
        protected Object childValue;
        protected boolean loadedChildren;

        public static void createChildren(final DefaultMutableTreeNode parent, final Object children) {
            loadChildren(parent, children);
        }

        public DynamicUtilTreeNode(final Object value, final Object children) {
            super(value, false);
            childValue = children;
            loadedChildren = !(children instanceof Object[])
                             && !(children instanceof Vector)
                             && !(children instanceof Hashtable);
            setAllowsChildren(!loadedChildren);
        }

        @Override
        public boolean isLeaf() {
            return !getAllowsChildren();
        }

        @Override
        public int getChildCount() {
            loadChildrenIfRequired();
            return super.getChildCount();
        }

        protected void loadChildren() {
            loadedChildren = true;
            loadChildren(this, childValue);
        }

        @Override
        public TreeNode getChildAt(final int index) {
            loadChildrenIfRequired();
            return super.getChildAt(index);
        }

        @SuppressWarnings("unchecked")
        @Override
        public Enumeration children() {
            loadChildrenIfRequired();
            return super.children();
        }


        private void loadChildrenIfRequired() {
            if (!loadedChildren) {
                loadChildren();
            }
        }

        private static void loadChildren(final DefaultMutableTreeNode node, final Object nodeChildren) {
            boolean hasChildren = false;
            if (nodeChildren instanceof Object[]) {
                Object[] children = (Object[])nodeChildren;
                for (Object element : children) {
                    node.add(new DynamicUtilTreeNode(element, element));
                    hasChildren = true;
                }
            } else if (nodeChildren instanceof Vector) {
                for (Iterator<?> it = ((Vector)nodeChildren).iterator(); it.hasNext(); ) {
                    Object child = it.next();
                    node.add(new DynamicUtilTreeNode(child, child));
                    hasChildren = true;
                }
            } else if (nodeChildren instanceof Hashtable) {
                for (Iterator<?> it = ((Hashtable)nodeChildren).keySet().iterator(); it.hasNext(); ) {
                    Object child = it.next();
                    node.add(new DynamicUtilTreeNode(child, child));
                    hasChildren = true;
                }
            }
            if (hasChildren) {
                node.setAllowsChildren(true);
            }
        }
    }

    protected static class EmptySelectionModel extends DefaultTreeSelectionModel {
        private static final long serialVersionUID = -2866787372484669512L;
        
        protected static final EmptySelectionModel sharedInstance = new EmptySelectionModel();

        public static EmptySelectionModel sharedInstance() {
            return sharedInstance;
        }

        @Override
        public void setSelectionPaths(final TreePath[] pPaths) {
        }

        @Override
        public void addSelectionPaths(final TreePath[] paths) {
        }

        @Override
        public void removeSelectionPaths(final TreePath[] paths) {
        }
    }

    protected class TreeModelHandler implements TreeModelListener {
        public void treeNodesChanged(final TreeModelEvent e) {
        }

        public void treeNodesInserted(final TreeModelEvent e) {
        }

        public void treeNodesRemoved(final TreeModelEvent e) {
            TreePath parentPath = e.getTreePath();

            Object[] children = e.getChildren();
            if (parentPath == null || children == null) {
                return;
            }
            for (Object element : children) {
                TreePath childPath = parentPath.pathByAddingChild(element);
                removeDescendantToggledPaths(getDescendantToggledPaths(childPath));
            }

            removeDescendantSelectedPaths(parentPath, false);
        }

        public void treeStructureChanged(final TreeModelEvent e) {
            TreePath parentPath = e.getTreePath();
            if (parentPath == null) {
                return;
            }

            Object parentPathToggleStatus = togglePaths.get(parentPath);
            removeDescendantToggledPaths(getDescendantToggledPaths(parentPath));
            togglePaths.put(parentPath, parentPathToggleStatus);
            removeDescendantSelectedPaths(parentPath, false);
        }
    }

    protected class TreeSelectionRedirector implements Serializable, TreeSelectionListener {
        private static final long serialVersionUID = -5457497600720267892L;
        
        public void valueChanged(final TreeSelectionEvent e) {
            fireValueChanged((TreeSelectionEvent)e.cloneWithSource(JTree.this));
        }
    }


    public static final String CELL_RENDERER_PROPERTY  = "cellRenderer";
    public static final String TREE_MODEL_PROPERTY = "model";
    public static final String ROOT_VISIBLE_PROPERTY = "rootVisible";
    public static final String SHOWS_ROOT_HANDLES_PROPERTY = "showsRootHandles";
    public static final String ROW_HEIGHT_PROPERTY = "rowHeight";
    public static final String CELL_EDITOR_PROPERTY = "cellEditor";
    public static final String EDITABLE_PROPERTY = "editable";
    public static final String LARGE_MODEL_PROPERTY = "largeModel";
    public static final String SELECTION_MODEL_PROPERTY = "selectionModel";
    public static final String VISIBLE_ROW_COUNT_PROPERTY = "visibleRowCount";
    public static final String INVOKES_STOP_CELL_EDITING_PROPERTY = "invokesStopCellEditing";
    public static final String SCROLLS_ON_EXPAND_PROPERTY = "scrollsOnExpand";
    public static final String TOGGLE_CLICK_COUNT_PROPERTY = "toggleClickCount";
    public static final String LEAD_SELECTION_PATH_PROPERTY = "leadSelectionPath";
    public static final String ANCHOR_SELECTION_PATH_PROPERTY = "anchorSelectionPath";
    public static final String EXPANDS_SELECTED_PATHS_PROPERTY = "expandsSelectedPaths";

    protected transient TreeModel treeModel;
    protected transient TreeSelectionModel selectionModel;
    protected boolean rootVisible;
    protected transient TreeCellRenderer cellRenderer;
    protected int rowHeight;
    protected boolean showsRootHandles;
    protected transient TreeSelectionRedirector selectionRedirector;
    protected transient TreeCellEditor cellEditor;
    protected boolean editable;
    protected boolean largeModel;
    protected int visibleRowCount;
    protected boolean invokesStopCellEditing;
    protected boolean scrollsOnExpand;
    protected int toggleClickCount;
    protected transient TreeModelListener treeModelListener = createTreeModelListener();

    private boolean expandsSelectedPaths;
    private boolean dragEnabled;
    private TreePath leadSelectionPath;
    private TreePath anchorSelectionPath;
    private final Map<TreePath, Object> togglePaths = new HashMap<TreePath, Object>();


    private static final String UI_CLASS_ID = "TreeUI";

    public JTree() {
        this(getDefaultTreeModel());
    }

    public JTree(final Object[] value) {
        this(createTreeModel(value));
        setRootVisible(false);
        setShowsRootHandles(true);
    }

    public JTree(final Vector<?> value) {
        this(createTreeModel(value));
        setRootVisible(false);
        setShowsRootHandles(true);
    }

    public JTree(final Hashtable<?,?> value) {
        this(createTreeModel(value));
        setRootVisible(false);
        setShowsRootHandles(true);
    }

    public JTree(final TreeNode root) {
        this(new DefaultTreeModel(root));
    }

    public JTree(final TreeNode root, final boolean asksAllowsChildren) {
        this(new DefaultTreeModel(root));
        ((DefaultTreeModel)getModel()).setAsksAllowsChildren(asksAllowsChildren);
    }

    public JTree(final TreeModel model) {
        setModel(model);
        selectionModel = new DefaultTreeSelectionModel();
        updateUI();
    }

    public TreeUI getUI() {
        return (TreeUI)ui;
    }

    public void setUI(final TreeUI ui) {
        super.setUI(ui);
    }

    @Override
    public void updateUI() {
        setUI((TreeUI)UIManager.getUI(this));
    }

    @Override
    public String getUIClassID() {
        return UI_CLASS_ID;
    }

    public TreeCellRenderer getCellRenderer() {
        return cellRenderer;
    }

    public void setCellRenderer(final TreeCellRenderer renderer) {
        TreeCellRenderer oldValue = cellRenderer;
        cellRenderer = renderer;
        firePropertyChange(CELL_RENDERER_PROPERTY, oldValue, renderer);
    }

    public void setEditable(final boolean flag) {
        boolean oldValue = editable;
        editable = flag;
        firePropertyChange(EDITABLE_PROPERTY, oldValue, flag);
    }

    public boolean isEditable() {
        return editable;
    }

    public void setCellEditor(final TreeCellEditor editor) {
        TreeCellEditor oldValue = cellEditor;
        cellEditor = editor;
        firePropertyChange(CELL_EDITOR_PROPERTY, oldValue, editor);
    }

    public TreeCellEditor getCellEditor() {
        return cellEditor;
    }

    public TreeModel getModel() {
        return treeModel;
    }

    public void setModel(final TreeModel model) {
        TreeModel oldValue = treeModel;
        if (treeModel != null) {
            treeModel.removeTreeModelListener(treeModelListener);
        }
        clearToggledPaths();
        if (getSelectionModel() != null) {
            clearSelection();
        }
        treeModel = model;
        if (treeModel != null) {
            treeModel.addTreeModelListener(treeModelListener);
            Object root = treeModel.getRoot();
            if (root != null && !treeModel.isLeaf(root)) {
                togglePaths.put(new TreePath(root), Boolean.TRUE);
            }
        }
        firePropertyChange(TREE_MODEL_PROPERTY, oldValue, model);
    }

    public boolean isRootVisible() {
        return rootVisible;
    }

    public void setRootVisible(final boolean visible) {
        boolean oldValue = rootVisible;
        rootVisible = visible;
        firePropertyChange(ROOT_VISIBLE_PROPERTY, oldValue, visible);
    }

    public void setShowsRootHandles(final boolean showHandles) {
        boolean oldValue = showsRootHandles;
        showsRootHandles = showHandles;
        firePropertyChange(SHOWS_ROOT_HANDLES_PROPERTY, oldValue, showHandles);
    }

    public boolean getShowsRootHandles() {
        return showsRootHandles;
    }

    public void setRowHeight(final int height) {
        int oldValue = rowHeight;
        rowHeight = height;
        firePropertyChange(ROW_HEIGHT_PROPERTY, oldValue, height);
    }

    public int getRowHeight() {
        return rowHeight;
    }

    public boolean isFixedRowHeight() {
        return getRowHeight() > 0;
    }

    public void setLargeModel(final boolean large) {
        boolean oldValue = largeModel;
        largeModel = large;
        firePropertyChange(LARGE_MODEL_PROPERTY, oldValue, large);
    }

    public boolean isLargeModel() {
        return largeModel;
    }

    public void setInvokesStopCellEditing(final boolean invokesStop) {
        boolean oldValue = invokesStopCellEditing;
        invokesStopCellEditing = invokesStop;
        firePropertyChange(INVOKES_STOP_CELL_EDITING_PROPERTY, oldValue, invokesStop);
    }

    public boolean getInvokesStopCellEditing() {
        return invokesStopCellEditing;
    }

    public void setScrollsOnExpand(final boolean scroll) {
        boolean oldValue = scrollsOnExpand;
        scrollsOnExpand = scroll;
        firePropertyChange(SCROLLS_ON_EXPAND_PROPERTY, oldValue, scroll);
    }

    public boolean getScrollsOnExpand() {
        return scrollsOnExpand;
    }

    public void setToggleClickCount(final int clickCount) {
        int oldValue = toggleClickCount;
        toggleClickCount = clickCount;
        firePropertyChange(TOGGLE_CLICK_COUNT_PROPERTY, oldValue, toggleClickCount);
    }

    public int getToggleClickCount() {
        return toggleClickCount;
    }

    public void setExpandsSelectedPaths(final boolean expand) {
        boolean oldValue = expandsSelectedPaths;
        expandsSelectedPaths = expand;
        firePropertyChange(EXPANDS_SELECTED_PATHS_PROPERTY, oldValue, expand);
    }

    public boolean getExpandsSelectedPaths() {
        return expandsSelectedPaths;
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

    public boolean isPathEditable(final TreePath path) {
        return isEditable();
    }

    @Override
    public String getToolTipText(final MouseEvent event) {
        if (event == null) {
            return null;
        }

        TreePath path = getPathForLocation(event.getX(), event.getY());
        if (path != null) {
            Object node = path.getLastPathComponent();
            boolean isLeaf = getModel().isLeaf(node);

            Component renderer = getCellRenderer().getTreeCellRendererComponent(this, node, isPathSelected(path), isExpanded(path), isLeaf, getRowForPath(path), path.equals(getLeadSelectionPath()));
            if (renderer instanceof JComponent) {
                return ((JComponent)renderer).getToolTipText(SwingUtilities.convertMouseEvent(this, event, renderer));
            }
        }
        return super.getToolTipText(event);
    }

    public String convertValueToText(final Object value, final boolean selected,
                                     final boolean expanded, final boolean leaf,
                                     final int row, final boolean hasFocus) {

        return value != null ? value.toString() : "";
    }

    public int getRowCount() {
        return getUI().getRowCount(this);
    }

    public void setSelectionPath(final TreePath path) {
        getSelectionModel().setSelectionPath(path);
    }

    public void setSelectionPaths(final TreePath[] paths) {
        getSelectionModel().setSelectionPaths(paths);
    }

    public void setLeadSelectionPath(final TreePath path) {
        TreePath oldValue = leadSelectionPath;
        leadSelectionPath = path;
        firePropertyChange(LEAD_SELECTION_PATH_PROPERTY, oldValue, path);
    }

    public void setAnchorSelectionPath(final TreePath path) {
        TreePath oldValue = anchorSelectionPath;
        anchorSelectionPath = path;
        firePropertyChange(ANCHOR_SELECTION_PATH_PROPERTY, oldValue, path);
    }

    public void setSelectionRow(final int row) {
        setSelectionRows(new int[] {row});
    }

    public void setSelectionRows(final int[] rows) {
        getSelectionModel().setSelectionPaths(rowsToPaths(rows));
    }

    public void addSelectionPath(final TreePath path) {
        getSelectionModel().addSelectionPath(path);
    }

    public void addSelectionPaths(final TreePath[] paths) {
        getSelectionModel().addSelectionPaths(paths);
    }

    public void addSelectionRow(final int row) {
        addSelectionRows(new int[] {row});
    }

    public void addSelectionRows(final int[] rows) {
        getSelectionModel().addSelectionPaths(rowsToPaths(rows));
    }

    public Object getLastSelectedPathComponent() {
        TreePath selectionPath = getSelectionPath();
        return selectionPath != null ? selectionPath.getLastPathComponent() : null;
    }

    public TreePath getLeadSelectionPath() {
        return leadSelectionPath;
    }

    public TreePath getAnchorSelectionPath() {
        return anchorSelectionPath;
    }

    public TreePath getSelectionPath() {
        return getSelectionModel().getSelectionPath();
    }

    public TreePath[] getSelectionPaths() {
        return getSelectionModel().getSelectionPaths();
    }

    public int[] getSelectionRows() {
        return getSelectionModel().getSelectionRows();
    }

    public int getSelectionCount() {
        return getSelectionModel().getSelectionCount();
    }

    public int getMinSelectionRow() {
        return getSelectionModel().getMinSelectionRow();
    }

    public int getMaxSelectionRow() {
        return getSelectionModel().getMaxSelectionRow();
    }

    public int getLeadSelectionRow() {
        return getRowForPath(getLeadSelectionPath());
    }

    public boolean isPathSelected(final TreePath path) {
        return getSelectionModel().isPathSelected(path);
    }

    public boolean isRowSelected(final int row) {
        return getSelectionModel().isRowSelected(row);
    }

    public Enumeration<TreePath> getExpandedDescendants(final TreePath parent) {
        final Enumeration<TreePath> toggled = getDescendantToggledPaths(parent);
        if (toggled == null) {
            return null;
        }

        return new Enumeration<TreePath>() {
            private TreePath nextElement = getNextExpandedPath();

            public TreePath nextElement() {
                if (nextElement == null) {
                    throw new NoSuchElementException("No next element in enumeration");
                }

                TreePath currentValue = nextElement;
                nextElement = getNextExpandedPath();
                return currentValue;
            }

            public boolean hasMoreElements() {
                return nextElement != null;
            }

            private TreePath getNextExpandedPath() {
                while (toggled.hasMoreElements()) {
                    TreePath nextPath = toggled.nextElement();
                    if (isExpanded(nextPath)) {
                        return nextPath;
                    }
                }
                return null;
            }
        };
    }

    public boolean hasBeenExpanded(final TreePath path) {
        return togglePaths.containsKey(path);
    }

    public boolean isExpanded(final TreePath path) {
        Boolean state = (Boolean)togglePaths.get(path);
        if (state == null || !state.booleanValue()) {
            return false;
        }
        TreePath parentPath = path.getParentPath();
        return parentPath == null || isExpanded(parentPath);
    }

    public boolean isExpanded(final int row) {
        return isExpanded(getPathForRow(row));
    }

    public boolean isCollapsed(final TreePath path) {
        return !isExpanded(path);
    }

    public boolean isCollapsed(final int row) {
        return !isExpanded(row);
    }

    public void makeVisible(final TreePath path) {
        expandPath(path.getParentPath());
    }

    public boolean isVisible(final TreePath path) {
        return getRowForPath(path) >= 0;
    }

    public Rectangle getPathBounds(final TreePath path) {
        return getUI().getPathBounds(this, path);
    }

    public Rectangle getRowBounds(final int row) {
        return getPathBounds(getPathForRow(row));
    }

    public void scrollPathToVisible(final TreePath path) {
        if (path == null) {
            return;
        }
        makeVisible(path);
        Rectangle pathBounds = getPathBounds(path);
        if (pathBounds != null) {
            scrollRectToVisible(pathBounds);
        }
    }

    public void scrollRowToVisible(final int row) {
        scrollPathToVisible(getPathForRow(row));
    }

    public TreePath getPathForRow(final int row) {
        return getUI().getPathForRow(this, row);
    }

    public int getRowForPath(final TreePath path) {
        return getUI().getRowForPath(this, path);
    }

    public void expandPath(final TreePath path) {
        if (path == null || getModel() == null) {
            return;
        }
        if (getModel().isLeaf(path.getLastPathComponent())) {
            return;
        }
        setExpandedState(path, true);
    }

    public void expandRow(final int row) {
        expandPath(getPathForRow(row));
    }

    public void collapsePath(final TreePath path) {
        setExpandedState(path, false);
    }

    public void collapseRow(final int row) {
        collapsePath(getPathForRow(row));
    }

    public TreePath getPathForLocation(final int x, final int y) {
        TreePath closestPath = getClosestPathForLocation(x, y);
        if (closestPath ==  null) {
            return null;
        }
        Rectangle pathBounds = getPathBounds(closestPath);
        return pathBounds.contains(x, y) ? closestPath : null;
    }

    public int getRowForLocation(final int x, final int y) {
        return getRowForPath(getPathForLocation(x, y));
    }

    public TreePath getClosestPathForLocation(final int x, final int y) {
        return getUI().getClosestPathForLocation(this, x, y);
    }

    public int getClosestRowForLocation(final int x, final int y) {
        return getRowForPath(getClosestPathForLocation(x, y));
    }

    public boolean isEditing() {
        return getUI().isEditing(this);
    }

    public boolean stopEditing() {
        return getUI().stopEditing(this);
    }

    public void cancelEditing() {
        getUI().cancelEditing(this);
    }

    public void startEditingAtPath(final TreePath path) {
        getUI().startEditingAtPath(this, path);
    }

    public TreePath getEditingPath() {
        return getUI().getEditingPath(this);
    }

    public void setSelectionModel(final TreeSelectionModel model) {
        TreeSelectionModel oldValue = selectionModel;
        selectionModel.removeTreeSelectionListener(selectionRedirector);

        selectionModel = model != null ? model : EmptySelectionModel.sharedInstance();
        if (selectionRedirector != null) {
            selectionModel.addTreeSelectionListener(selectionRedirector);
        }
        firePropertyChange(SELECTION_MODEL_PROPERTY, oldValue, selectionModel);
    }

    public TreeSelectionModel getSelectionModel() {
        return selectionModel;
    }

    public void setSelectionInterval(final int index0, final int index1) {
        clearSelection();
        addSelectionInterval(index0, index1);
    }

    public void addSelectionInterval(final int index0, final int index1) {
        addSelectionPaths(getPathBetweenRows(index0, index1));
    }

    public void removeSelectionInterval(final int index0, final int index1) {
        removeSelectionPaths(getPathBetweenRows(index0, index1));
    }

    public void removeSelectionPath(final TreePath path) {
        getSelectionModel().removeSelectionPath(path);
    }

    public void removeSelectionPaths(final TreePath[] paths) {
        getSelectionModel().removeSelectionPaths(paths);
    }

    public void removeSelectionRow(final int row) {
        removeSelectionPath(getPathForRow(row));
    }

    public void removeSelectionRows(final int[] rows) {
        removeSelectionPaths(rowsToPaths(rows));
    }

    public void clearSelection() {
        getSelectionModel().clearSelection();
    }

    public boolean isSelectionEmpty() {
        return getSelectionModel().isSelectionEmpty();
    }

    public void addTreeExpansionListener(final TreeExpansionListener l) {
        listenerList.add(TreeExpansionListener.class, l);
    }

    public void removeTreeExpansionListener(final TreeExpansionListener l) {
        listenerList.remove(TreeExpansionListener.class, l);
    }

    public TreeExpansionListener[] getTreeExpansionListeners() {
        return listenerList.getListeners(TreeExpansionListener.class);
    }

    public void addTreeWillExpandListener(final TreeWillExpandListener l) {
        listenerList.add(TreeWillExpandListener.class, l);
    }

    public void removeTreeWillExpandListener(final TreeWillExpandListener l) {
        listenerList.remove(TreeWillExpandListener.class, l);
    }

    public TreeWillExpandListener[] getTreeWillExpandListeners() {
        return listenerList.getListeners(TreeWillExpandListener.class);
    }

    public void fireTreeExpanded(final TreePath path) {
        TreeExpansionListener[] listeners = getTreeExpansionListeners();
        if (listeners.length == 0) {
            return;
        }

        TreeExpansionEvent event = new TreeExpansionEvent(this, path);
        for (TreeExpansionListener element : listeners) {
            element.treeExpanded(event);
        }
    }

    public void fireTreeCollapsed(final TreePath path) {
        TreeExpansionListener[] listeners = getTreeExpansionListeners();
        if (listeners.length == 0) {
            return;
        }

        TreeExpansionEvent event = new TreeExpansionEvent(this, path);
        for (TreeExpansionListener element : listeners) {
            element.treeCollapsed(event);
        }
    }

    public void fireTreeWillExpand(final TreePath path) throws ExpandVetoException {
        TreeWillExpandListener[] listeners = getTreeWillExpandListeners();
        if (listeners.length == 0) {
            return;
        }

        TreeExpansionEvent event = new TreeExpansionEvent(this, path);
        for (TreeWillExpandListener element : listeners) {
            element.treeWillExpand(event);
        }
    }

    public void fireTreeWillCollapse(final TreePath path) throws ExpandVetoException {
        TreeWillExpandListener[] listeners = getTreeWillExpandListeners();
        if (listeners.length == 0) {
            return;
        }

        TreeExpansionEvent event = new TreeExpansionEvent(this, path);
        for (TreeWillExpandListener element : listeners) {
            element.treeWillCollapse(event);
        }
    }

    public void addTreeSelectionListener(final TreeSelectionListener l) {
        if (selectionRedirector == null) {
            selectionRedirector = new TreeSelectionRedirector();
            selectionModel.addTreeSelectionListener(selectionRedirector);
        }
        listenerList.add(TreeSelectionListener.class, l);
    }

    public void removeTreeSelectionListener(final TreeSelectionListener l) {
        listenerList.remove(TreeSelectionListener.class, l);
    }

    public TreeSelectionListener[] getTreeSelectionListeners() {
        return listenerList.getListeners(TreeSelectionListener.class);
    }

    protected void fireValueChanged(final TreeSelectionEvent e) {
        TreeSelectionListener[] listeners = getTreeSelectionListeners();
        for (TreeSelectionListener element : listeners) {
            element.valueChanged(e);
        }
    }

    public void treeDidChange() {
        revalidate();
        repaint();
    }

    public void setVisibleRowCount(final int count) {
        int oldValue = visibleRowCount;
        visibleRowCount = count;
        firePropertyChange(VISIBLE_ROW_COUNT_PROPERTY, oldValue, count);
    }

    public int getVisibleRowCount() {
        return visibleRowCount;
    }

    public TreePath getNextMatch(final String prefix, final int startingRow, final Position.Bias bias) {
        if (prefix == null) {
            throw new IllegalArgumentException("Prefix must be specified");
        }
        if (startingRow < 0 || startingRow >= getRowCount()) {
            throw new IllegalArgumentException("Illegal startingRow is specified. Must be in the valid range");
        }
        if (bias == Position.Bias.Forward) {
            int rowCount = getRowCount();
            for (int i = startingRow; i < rowCount; i++) {
                TreePath path = getPathForRow(i);
                if (pathMatches(prefix, path, i)) {
                    return path;
                }
            }
            for (int i = 0; i < startingRow; i++) {
                TreePath path = getPathForRow(i);
                if (pathMatches(prefix, path, i)) {
                    return path;
                }
            }
        } else {
            for (int i = startingRow; i >= 0; i--) {
                TreePath path = getPathForRow(i);
                if (pathMatches(prefix, path, i)) {
                    return path;
                }
            }
            for (int i = getRowCount() - 1; i > startingRow; i--) {
                TreePath path = getPathForRow(i);
                if (pathMatches(prefix, path, i)) {
                    return path;
                }
            }
        }
        return null;
    }

    public Dimension getPreferredScrollableViewportSize() {
        int width = getPreferredSize().width;

        int height;
        if (isFixedRowHeight()) {
            height = getRowHeight();
        } else {
            Rectangle rootBounds = getModel() != null ? getPathBounds(new TreePath(getModel().getRoot()))
                                                      : null;
            height = rootBounds != null ? rootBounds.height : 16;
        }

        return new Dimension(width, getVisibleRowCount() * height);
    }

    public int getScrollableUnitIncrement(final Rectangle visibleRect,
                                          final int orientation, final int direction) {

        if (orientation == SwingConstants.HORIZONTAL) {
            return 4;
        }

        TreePath closestPath = getClosestPathForLocation(visibleRect.x, visibleRect.y);
        if (closestPath == null) {
            return 0;
        }

        Rectangle pathBounds = getPathBounds(closestPath);
        if (direction >= 0) {
            return pathBounds.y + pathBounds.height - visibleRect.y;
        }
        
        int increment = visibleRect.y - pathBounds.y;
        if (increment > 0) {
            return increment;
        }

        int row = getRowForPath(closestPath);
        if (row == 0) {
            return 0;
        }
        pathBounds = getRowBounds(row - 1);
        return pathBounds.height;
    }

    public int getScrollableBlockIncrement(final Rectangle visibleRect,
                                           final int orientation, final int direction) {
        return orientation == SwingConstants.VERTICAL ? visibleRect.height : visibleRect.width;
    }

    public boolean getScrollableTracksViewportWidth() {
        Component parent = getParent();
        if (!(parent instanceof JViewport)) {
            return false;
        }

        return parent.getSize().width > getPreferredSize().width;
    }

    public boolean getScrollableTracksViewportHeight() {
        Component parent = getParent();
        if (!(parent instanceof JViewport)) {
            return false;
        }

        return parent.getSize().height > getPreferredSize().height;
    }

    @Override
    public AccessibleContext getAccessibleContext() {
        if (accessibleContext == null) {
            accessibleContext = new AccessibleJTree();
        }

        return accessibleContext;
    }


    protected void setExpandedState(final TreePath path, final boolean state) {
        doSetExpandedState(path, state);
        if (!state) {
            if (removeDescendantSelectedPaths(path, false)) {
                addSelectionPath(path);
            }
        }
        getSelectionModel().resetRowSelection();
    }

    protected Enumeration<TreePath> getDescendantToggledPaths(final TreePath parent) {
        if (parent == null) {
            return null;
        }

        final Iterator<TreePath> toggled = (new HashSet<TreePath>(togglePaths.keySet())).iterator();
        return new Enumeration<TreePath>() {
            private TreePath nextElement = getNextDescendPath();

            public TreePath nextElement() {
                if (nextElement == null) {
                    throw new NoSuchElementException("No next element in enumeration");
                }

                TreePath currentValue = nextElement;
                nextElement = getNextDescendPath();
                return currentValue;
            }

            public boolean hasMoreElements() {
                return nextElement != null;
            }

            private TreePath getNextDescendPath() {
                while (toggled.hasNext()) {
                    TreePath nextPath = toggled.next();
                    if (parent.isDescendant(nextPath)) {
                        return nextPath;
                    }
                }
                return null;
            }
        };
    }

    protected void removeDescendantToggledPaths(final Enumeration<TreePath> toRemove) {
        if (toRemove == null) {
            return;
        }
        while(toRemove.hasMoreElements()) {
            togglePaths.remove(toRemove.nextElement());
        }
    }

    protected void clearToggledPaths() {
        togglePaths.clear();
    }

    protected TreeModelListener createTreeModelListener() {
        return new TreeModelHandler();
    }

    protected boolean removeDescendantSelectedPaths(final TreePath path,
                                                    final boolean includePath) {

        if (path == null) {
            return false;
        }
        TreePath[] selectedPaths = getSelectionPaths();
        if (selectedPaths == null) {
            return false;
        }

        List<TreePath> toRemove = new LinkedList<TreePath>();
        for (TreePath selectedPath : selectedPaths) {
            if (path.isDescendant(selectedPath)
                && (includePath || !path.equals(selectedPath))) {

                toRemove.add(selectedPath);
            }
        }
        if (toRemove.isEmpty()) {
            return false;
        }

        removeSelectionPaths(toRemove.toArray(new TreePath[toRemove.size()]));
        return true;
    }

    protected static TreeModel getDefaultTreeModel() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("JTree");

        DefaultMutableTreeNode colorsNode = new DefaultMutableTreeNode("towns");
        colorsNode.add(new DefaultMutableTreeNode("Saint-Petersburg"));
        colorsNode.add(new DefaultMutableTreeNode("New-York"));
        colorsNode.add(new DefaultMutableTreeNode("Munchen"));
        colorsNode.add(new DefaultMutableTreeNode("Oslo"));
        root.add(colorsNode);

        DefaultMutableTreeNode sportsNode = new DefaultMutableTreeNode("animals");
        sportsNode.add(new DefaultMutableTreeNode("dog"));
        sportsNode.add(new DefaultMutableTreeNode("tiger"));
        sportsNode.add(new DefaultMutableTreeNode("wolf"));
        sportsNode.add(new DefaultMutableTreeNode("bear"));
        root.add(sportsNode);

        DefaultMutableTreeNode foodNode = new DefaultMutableTreeNode("computers");
        foodNode.add(new DefaultMutableTreeNode("notebook"));
        foodNode.add(new DefaultMutableTreeNode("desktop"));
        foodNode.add(new DefaultMutableTreeNode("server"));
        foodNode.add(new DefaultMutableTreeNode("mainframe"));
        root.add(foodNode);

        return new DefaultTreeModel(root);
    }

    protected static TreeModel createTreeModel(final Object value) {
        return new DefaultTreeModel(new DynamicUtilTreeNode("root", value));
    }

    protected TreePath[] getPathBetweenRows(final int index0, final int index1) {
        int minRow = Math.max(Math.min(index0, index1), 0);
        int maxRow = Math.min(Math.max(index0, index1), getRowCount() - 1);

        if (minRow > maxRow) {
            return null;
        }

        TreePath[] paths = new TreePath[maxRow - minRow + 1];
        for (int i = minRow; i <= maxRow; i++) {
            paths[i - minRow] = getPathForRow(i);
        }

        return paths;
    }


    private void doSetExpandedState(final TreePath path, final boolean state) {
        if (path == null) {
            return;
        }

        doSetExpandedState(path.getParentPath(), true);

        if (isExpanded(path) == state) {
            return;
        }

        try {
            if (state) {
                fireTreeWillExpand(path);
            } else {
                fireTreeWillCollapse(path);
            }
        } catch (ExpandVetoException e) {
            return;
        }

        togglePaths.put(path, Boolean.valueOf(state));

        if (state) {
            fireTreeExpanded(path);
        } else {
            fireTreeCollapsed(path);
        }
    }

    private TreePath[] rowsToPaths(final int[] rows) {
        if (rows == null || rows.length == 0) {
            return new TreePath[0];
        }

        List<TreePath> paths = new ArrayList<TreePath>();
        for (int row : rows) {
            TreePath path = getPathForRow(row);
            if (path != null) {
                paths.add(path);
            }
        }
        return paths.toArray(new TreePath[paths.size()]);
    }

    private boolean pathMatches(final String prefix, final TreePath path, final int row) {
        if (path == null) {
            return false;
        }
        boolean isLeaf = getModel() != null && getModel().isLeaf(path.getLastPathComponent());
        boolean isFocused = path.equals(getLeadSelectionPath());
        String value = convertValueToText(path.getLastPathComponent(), isPathSelected(path),isExpanded(path), isLeaf, row, isFocused);

        return value != null && value.toUpperCase().startsWith(prefix.toUpperCase());
    }
}
