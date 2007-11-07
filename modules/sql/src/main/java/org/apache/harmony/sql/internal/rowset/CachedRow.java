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


import java.sql.Blob;
import java.sql.SQLException;
import java.util.BitSet;

public class CachedRow implements Cloneable{
    private Object[] columnData;

    private Object[] originalColumnData;

    private BitSet mask;

    private boolean isDelete;

    private boolean insert;

    private boolean nonUpdateable = false;

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
    
    public void setUnavailable(){
        setDelete();
        setInsert();
        mask.clear();
        mask.flip(0,columnData.length);
    }

    public void setNonUpdateable() {
        // setDelete();
        // setInsert();
        // mask.clear();
        // mask.flip(0,columnData.length);
        nonUpdateable = true;
    }

    public boolean getNonUpdateable() {
        return nonUpdateable;
    }

    public void setDelete() {
        this.isDelete = true;
    }
    
    public void unDoDelete() {
        this.isDelete = false;
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

    public void updateString(int columnIndex, String x) throws SQLException {
        if (nonUpdateable)
            throw new SQLException("Not Updateable of the CurrentRow");
        this.columnData[columnIndex - 1] = x;
        setUpdateMask(columnIndex - 1);
    }

    public void updateInt(int columnIndex, int x) {
        this.columnData[columnIndex - 1] = x;
        setUpdateMask(columnIndex - 1);
    }
    
    public CachedRow getOriginal() {
        return new CachedRow(originalColumnData);
    }

    public String getString(int columnIndex) {
        return (String) this.columnData[columnIndex - 1];
    }
    
    public Object getObject(int columnIndex) {
        return this.columnData[columnIndex - 1];
    }

    public int getInt(int columnIndex) {
        return (Integer) this.columnData[columnIndex - 1];
    }

    public Blob getBLOb(int columnIndex) {
        return (Blob) this.columnData[columnIndex - 1];
    }

    public boolean getBoolean(int columnIndex) {
        return (Boolean) this.columnData[columnIndex - 1];
    }

    public byte getByte(int columnIndex) {
        return (Byte) this.columnData[columnIndex - 1];
    }

    public byte[] getBytes(int columnIndex) {
        return (byte[]) this.columnData[columnIndex - 1];
    }

    // deep clone
    public CachedRow createClone() throws CloneNotSupportedException {  
        CachedRow cr = (CachedRow) super.clone();

        Object[] cd = new Object[columnData.length];
        for (int i = 0; i < columnData.length; i++) {
            cd[i] = columnData[i];
        }
        cr.columnData = cd;
        cr.insert = insert;
        cr.isDelete = isDelete;
        cr.mask = (BitSet) mask.clone();
        cr.nonUpdateable = nonUpdateable;
        // cr.originalColumnData
        return cr;
    }
}
