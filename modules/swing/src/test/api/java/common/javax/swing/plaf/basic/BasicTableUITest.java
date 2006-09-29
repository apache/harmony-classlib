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
package javax.swing.plaf.basic;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BasicSwingTestCase;
import javax.swing.CellRendererPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class BasicTableUITest extends BasicSwingTestCase {
    private BasicTableUI ui;

    public BasicTableUITest(final String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        ui = new BasicTableUI();
    }

    protected void tearDown() throws Exception {
        ui = null;
    }


    public void testBasicTableUI() throws Exception {
        assertNull(ui.table);
        assertNull(ui.rendererPane);
        assertNull(ui.focusListener);
        assertNull(ui.keyListener);
        assertNull(ui.mouseInputListener);
    }

    public void testCreateKeyListener() throws Exception {
        assertNull(ui.createKeyListener());
    }

    public void testCreateFocusListener() throws Exception {
        assertTrue(ui.createFocusListener() instanceof BasicTableUI.FocusHandler);
        assertNotSame(ui.createFocusListener(), ui.createFocusListener());
        assertNull(ui.focusListener);
    }

    public void testCreateMouseInputListener() throws Exception {
        assertTrue(ui.createMouseInputListener() instanceof BasicTableUI.MouseInputHandler);
        assertNotSame(ui.createMouseInputListener(), ui.createMouseInputListener());
        assertNull(ui.mouseInputListener);
    }

    public void testCreateUI() throws Exception {
        assertSame(BasicTableUI.createUI(null).getClass(), BasicTableUI.class);
        assertNotSame(BasicTableUI.createUI(null), BasicTableUI.createUI(null));
    }

    public void testInstallUI() throws Exception {
        JTable table = new JTable();
        ui.installUI(table);
        assertSame(table, ui.table);
        assertNotNull(ui.rendererPane);
    }

    public void testUninstallUI() throws Exception {
        JTable table = new JTable();
        ui.installUI(table);

        ui.uninstallUI(null);
        assertNull(ui.table);
        assertNull(ui.rendererPane);
    }

    public void testGetMinimumMaximumPreferredSize() throws Exception {
        JTable table = new JTable();
        ui.table = table;

        assertEquals(new Dimension(), ui.getMinimumSize(null));

        TableColumn column1 = new TableColumn();
        column1.setMinWidth(20);
        column1.setPreferredWidth(50);
        column1.setMaxWidth(100);
        table.addColumn(column1);
        assertEquals(new Dimension(20, 0), ui.getMinimumSize(null));
        assertEquals(new Dimension(100, 0), ui.getMaximumSize(null));
        assertEquals(new Dimension(50, 0), ui.getPreferredSize(null));

        TableColumn column2 = new TableColumn();
        column2.setMinWidth(10);
        column2.setPreferredWidth(20);
        column2.setMaxWidth(40);
        table.addColumn(column2);
        assertEquals(new Dimension(30, 0), ui.getMinimumSize(null));
        assertEquals(new Dimension(140, 0), ui.getMaximumSize(null));
        assertEquals(new Dimension(70, 0), ui.getPreferredSize(null));

        table.setRowHeight(30);
        ((DefaultTableModel)table.getModel()).addRow(new Object[] {"1"});
        assertEquals(new Dimension(30, 30), ui.getMinimumSize(null));
        assertEquals(new Dimension(140, 30), ui.getMaximumSize(null));
        assertEquals(new Dimension(70, 30), ui.getPreferredSize(null));

        ((DefaultTableModel)table.getModel()).addRow(new Object[] {"2", "2"});
        table.setRowHeight(1, 20);
        assertEquals(new Dimension(30, 50), ui.getMinimumSize(null));
        assertEquals(new Dimension(140, 50), ui.getMaximumSize(null));
        assertEquals(new Dimension(70, 50), ui.getPreferredSize(null));
    }

    public void testPaint() throws Exception {
        ui.table = new JTable();
        ui.paint(null, null);

        DefaultTableModel model = (DefaultTableModel)ui.table.getModel();
        model.addColumn("column1");
        model.addRow(new Object[] {"1"});
        ui.rendererPane = new CellRendererPane();
        Graphics g = createTestGraphics();
        g.setClip(0, 0, 100, 100);
        ui.paint(g, null);
    }

    public void testInstallDefaults() throws Exception {
        ui.table = new JTable();
        ui.installDefaults();
        assertSame(UIManager.getFont("Table.font"), ui.table.getFont());
        assertSame(UIManager.getColor("Table.gridColor"), ui.table.getGridColor());
        assertSame(UIManager.getColor("Table.foreground"), ui.table.getForeground());
        assertSame(UIManager.getColor("Table.background"), ui.table.getBackground());
        assertSame(UIManager.getColor("Table.selectionForeground"), ui.table.getSelectionForeground());
        assertSame(UIManager.getColor("Table.selectionBackground"), ui.table.getSelectionBackground());
    }
}
