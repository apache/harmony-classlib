/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.harmony.sql.internal.rowset;

import java.util.BitSet;

public class CachedRow {
    private Object[] columnData;

    private Object[] originalColumnData;

    private BitSet mask;

    private boolean isDelete;

    private boolean insert;

    public CachedRow(Object[] columnData) {
        this.columnData = columnData;
        this.originalColumnData = columnData;
        mask = new BitSet(columnData.length);
    }

    public boolean getUpdateMask(int i) {
        return mask.get(i);
    }

    public void setUpdateMask(int i) {
        mask.set(i);
    }

    public void setDelete() {
        this.isDelete = true;
    }

    public boolean getDelete() {
        return isDelete;
    }

    public void setInsert() {
        this.insert = true;
    }

    public boolean getInsert() {
        return this.insert;
    }

    public void updateString(int columnIndex, String x) {
        this.columnData[columnIndex - 1] = x;
        setUpdateMask(columnIndex - 1);
    }

    public void updateInt(int columnIndex, int x) {
        this.columnData[columnIndex - 1] = x;
        setUpdateMask(columnIndex - 1);
    }

    public String getString(int columnIndex) {
        return (String) this.columnData[columnIndex - 1];
    }

    public int getInt(int columnIndex) {
        return (Integer) this.columnData[columnIndex - 1];
    }
}
