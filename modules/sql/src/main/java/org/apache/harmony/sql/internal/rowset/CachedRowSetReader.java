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

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.RowSetInternal;
import javax.sql.RowSetReader;

public class CachedRowSetReader implements RowSetReader {

    private ResultSet rs;

    private int startRow;

    private ResultSetMetaData metadata;

    public CachedRowSetReader(ResultSet rs, int startRow) throws SQLException {
        this.rs = rs;
        this.startRow = startRow;
        this.metadata = rs.getMetaData();
    }

    public void readData(RowSetInternal theCaller) throws SQLException {
        ArrayList<CachedRow> data = new ArrayList<CachedRow>();
        int columnCount = metadata.getColumnCount();
        while (rs.next()) {
            Object[] columnData = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columnData[i] = rs.getObject(i+1);
            }
            data.add(new CachedRow(columnData));
        }
        ((CachedRowSetImpl) theCaller).setRows(data, columnCount);
    }
}
