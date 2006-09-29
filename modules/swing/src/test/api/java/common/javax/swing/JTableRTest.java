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

import javax.swing.table.AbstractTableModel;

public class JTableRTest extends BasicSwingTestCase {
    private JTable table;

    protected void setUp() throws Exception {
        table = new JTable();
        propertyChangeController = new PropertyChangeController();
        table.addPropertyChangeListener(propertyChangeController);
    }

    protected void tearDown() throws Exception {
        table = null;
    }

    public void testSelectAllClearSelection() throws Exception {
        table = new JTable(3, 4);
        assertEquals(0, getSelectedIndices(table.getSelectionModel()).length);
        assertEquals(0, getSelectedIndices(table.getColumnModel().getSelectionModel()).length);

        table.selectAll();
        assertEquals(3, getSelectedIndices(table.getSelectionModel()).length);
        assertEquals(4, getSelectedIndices(table.getColumnModel().getSelectionModel()).length);

        table.clearSelection();
        assertEquals(0, getSelectedIndices(table.getSelectionModel()).length);
        assertEquals(0, getSelectedIndices(table.getColumnModel().getSelectionModel()).length);

        table.setCellSelectionEnabled(false);
        table.selectAll();
        assertEquals(3, getSelectedIndices(table.getSelectionModel()).length);
        assertEquals(4, getSelectedIndices(table.getColumnModel().getSelectionModel()).length);

        table.clearSelection();
        table.setCellSelectionEnabled(true);
        table.setRowSelectionInterval(1, 2);
        table.setColumnSelectionInterval(1, 2);
        assertEquals(2, getSelectedIndices(table.getSelectionModel()).length);
        assertEquals(1, table.getSelectionModel().getAnchorSelectionIndex());
        assertEquals(2, table.getSelectionModel().getLeadSelectionIndex());
        assertEquals(1, table.getColumnModel().getSelectionModel().getAnchorSelectionIndex());
        assertEquals(2, table.getColumnModel().getSelectionModel().getLeadSelectionIndex());

        ((AbstractTableModel)table.getModel()).fireTableRowsInserted(0, 3);
        assertEquals(2, getSelectedIndices(table.getSelectionModel()).length);
        assertEquals(5, table.getSelectionModel().getAnchorSelectionIndex());
        assertEquals(6, table.getSelectionModel().getLeadSelectionIndex());
        assertEquals(1, table.getColumnModel().getSelectionModel().getAnchorSelectionIndex());
        assertEquals(2, table.getColumnModel().getSelectionModel().getLeadSelectionIndex());

        ((AbstractTableModel)table.getModel()).fireTableRowsDeleted(0, 2);
        assertEquals(2, getSelectedIndices(table.getSelectionModel()).length);
        assertEquals(2, table.getSelectionModel().getAnchorSelectionIndex());
        assertEquals(3, table.getSelectionModel().getLeadSelectionIndex());
        assertEquals(1, table.getColumnModel().getSelectionModel().getAnchorSelectionIndex());
        assertEquals(2, table.getColumnModel().getSelectionModel().getLeadSelectionIndex());

        ((AbstractTableModel)table.getModel()).fireTableCellUpdated(0, 2);
        assertEquals(2, getSelectedIndices(table.getSelectionModel()).length);
        assertEquals(2, table.getSelectionModel().getAnchorSelectionIndex());
        assertEquals(3, table.getSelectionModel().getLeadSelectionIndex());
        assertEquals(1, table.getColumnModel().getSelectionModel().getAnchorSelectionIndex());
        assertEquals(2, table.getColumnModel().getSelectionModel().getLeadSelectionIndex());

        ((AbstractTableModel)table.getModel()).fireTableDataChanged();
        assertEquals(0, getSelectedIndices(table.getSelectionModel()).length);
        assertEquals(2, table.getSelectionModel().getAnchorSelectionIndex());
        assertEquals(3, table.getSelectionModel().getLeadSelectionIndex());
        assertEquals(1, table.getColumnModel().getSelectionModel().getAnchorSelectionIndex());
        assertEquals(2, table.getColumnModel().getSelectionModel().getLeadSelectionIndex());

        table.clearSelection();
        table.setRowSelectionInterval(1, 2);
        assertEquals(2, getSelectedIndices(table.getSelectionModel()).length);
        assertEquals(1, table.getSelectionModel().getAnchorSelectionIndex());
        assertEquals(2, table.getSelectionModel().getLeadSelectionIndex());
        assertEquals(1, table.getColumnModel().getSelectionModel().getAnchorSelectionIndex());
        assertEquals(2, table.getColumnModel().getSelectionModel().getLeadSelectionIndex());

        ((AbstractTableModel)table.getModel()).fireTableStructureChanged();
        assertEquals(0, getSelectedIndices(table.getSelectionModel()).length);
        assertEquals(1, table.getSelectionModel().getAnchorSelectionIndex());
        assertEquals(2, table.getSelectionModel().getLeadSelectionIndex());
        if (isHarmony()) {
            assertEquals(-1, table.getColumnModel().getSelectionModel().getAnchorSelectionIndex());
        } else {
            assertEquals(0, table.getColumnModel().getSelectionModel().getAnchorSelectionIndex());
        }
        assertEquals(0, table.getColumnModel().getSelectionModel().getLeadSelectionIndex());
    }

    private int[] getSelectedIndices(final ListSelectionModel selModel) {
        int count = 0;
        for (int i = selModel.getMinSelectionIndex(); i <= selModel.getMaxSelectionIndex(); i++) {
            if (selModel.isSelectedIndex(i)) {
                count++;
            }
        }

        int[] result = new int[count];
        count = 0;
        for (int i = selModel.getMinSelectionIndex(); i <= selModel.getMaxSelectionIndex(); i++) {
            if (selModel.isSelectedIndex(i)) {
                result[count++] = i;
            }
        }

        return result;
    }
}


