/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package javax.sql.rowset;

import java.sql.SQLException;
import java.sql.Types;
import org.apache.harmony.sql.internal.nls.Messages;


class SqlUtil{
    /*
     * FIXME:Validate column types is defined by java.sql.Types, current implementation is ugly, need to find 
     * an elegant and effient way to check all constants defined in java.util.sql.Types 
     */
    static void validateType(int type) throws SQLException {
        switch(type){
            case Types.ARRAY:
            case Types.BIGINT:
            case Types.BINARY:
            case Types.BIT:
            case Types.BLOB:
            case Types.BOOLEAN:
            case Types.CHAR:
            case Types.CLOB:
            case Types.DATALINK:
            case Types.DATE:
            case Types.DECIMAL:
            case Types.DISTINCT:
            case Types.DOUBLE:
            case Types.FLOAT:
            case Types.INTEGER:
            case Types.JAVA_OBJECT:
            case Types.LONGVARBINARY:
            case Types.LONGVARCHAR:
            case Types.NULL:
            case Types.NUMERIC:
            case Types.OTHER:
            case Types.REAL:
            case Types.REF:
            case Types.SMALLINT:
            case Types.STRUCT:
            case Types.TIME:
            case Types.TIMESTAMP:
            case Types.TINYINT:
            case Types.VARBINARY:
            case Types.VARCHAR:
                return;
        }
        throw new SQLException(Messages.getString("sql.28")); //$NON-NLS-1$
    }

    static String getClassNameByType(int type){ 
        String className = null;
        switch (type) {        
        case Types.BINARY:
        case Types.BLOB:
        case Types.LONGVARBINARY:
        case Types.VARBINARY:
            className = new byte[0].getClass().getName();
            break;
        case Types.DOUBLE:
        case Types.FLOAT:
            className = Double.class.getName();
            break;
        case Types.BIGINT:
            className = Long.class.getName();
            break;
        case Types.BIT:
            className = Boolean.class.getName();
            break;
        case Types.DECIMAL:
        case Types.NUMERIC:
            className = java.math.BigDecimal.class.getName();
            break;
        case Types.CLOB:
            className = new char[0].getClass().getName();
            break;
        case Types.DATE:
            className = java.sql.Date.class.getName();
            break;
        case Types.INTEGER:
            className = Integer.class.getName();
            break;
        case Types.REAL:
            className = Float.class.getName();
            break;
        case Types.SMALLINT:
            className = Short.class.getName();
            break;
        case Types.TIME:
            className = java.sql.Time.class.getName();
            break;
        case Types.TIMESTAMP:
            className = java.sql.Timestamp.class.getName();
            break;
        case Types.TINYINT:
            className = Byte.class.getName();
            break;
        default:
            className = String.class.getName();
        }
        return className;
    }
}
