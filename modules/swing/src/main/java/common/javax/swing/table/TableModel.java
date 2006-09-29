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
package javax.swing.table;

import javax.swing.event.TableModelListener;

public interface TableModel {
    int getRowCount();
    int getColumnCount();
    String getColumnName(int columnIndex);
    Class getColumnClass(int columnIndex);
    boolean isCellEditable(int rowIndex, int columnIndex);
    Object getValueAt(int rowIndex, int columnIndex);
    void setValueAt(Object value, int rowIndex, int columnIndex);
    void addTableModelListener(TableModelListener l);
    void removeTableModelListener(TableModelListener l);
}
