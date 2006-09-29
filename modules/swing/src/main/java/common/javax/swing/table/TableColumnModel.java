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

import java.util.Enumeration;

import javax.swing.ListSelectionModel;
import javax.swing.event.TableColumnModelListener;

public interface TableColumnModel {
    void addColumn(TableColumn column);
    void removeColumn(TableColumn column);
    void moveColumn(int columnIndex, int newIndex);
    void setColumnMargin(int margin);
    int getColumnMargin();
    int getColumnCount();
    public Enumeration getColumns();
    int getColumnIndex(Object columnIdentifier);
    TableColumn getColumn(int columnIndex);
    int getColumnIndexAtX(int xPosition);
    int getTotalColumnWidth();
    void setColumnSelectionAllowed(boolean allowed);
    boolean getColumnSelectionAllowed();
    int[] getSelectedColumns();
    int getSelectedColumnCount();
    void setSelectionModel(ListSelectionModel model);
    ListSelectionModel getSelectionModel();
    void addColumnModelListener(TableColumnModelListener listener);
    void removeColumnModelListener(TableColumnModelListener listener);
}
