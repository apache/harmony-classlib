/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.sql.tests.javax.sql;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Ref;
import java.sql.ResultSetMetaData;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

import javax.sql.RowSet;
import javax.sql.RowSetListener;

public class Impl_RowSet implements RowSet {

	public Impl_RowSet() {
		super();
	} // end constructor

	public String getUrl() {

		return null;
	} // end method getUrl

	public void setUrl(String parm1) {

	} // end method setUrl

	public String getDataSourceName() {

		return null;
	} // end method getDataSourceName

	public void setDataSourceName(String parm1) {

	} // end method setDataSourceName

	public String getUsername() {

		return null;
	} // end method getUsername

	public void setUsername(String parm1) {

	} // end method setUsername

	public String getPassword() {

		return null;
	} // end method getPassword

	public void setPassword(String parm1) {

	} // end method setPassword

	public int getTransactionIsolation() {

		return -1678466229;
	} // end method getTransactionIsolation

	public void setTransactionIsolation(int parm1) {

	} // end method setTransactionIsolation

	public Map getTypeMap() {

		return null;
	} // end method getTypeMap

	public void setTypeMap(Map parm1) {

	} // end method setTypeMap

	public String getCommand() {

		return null;
	} // end method getCommand

	public void setCommand(String parm1) {

	} // end method setCommand

	public boolean isReadOnly() {

		return false;
	} // end method isReadOnly

	public void setReadOnly(boolean parm1) {

	} // end method setReadOnly

	public int getMaxFieldSize() {

		return 1160759662;
	} // end method getMaxFieldSize

	public void setMaxFieldSize(int parm1) {

	} // end method setMaxFieldSize

	public int getMaxRows() {

		return 1352057609;
	} // end method getMaxRows

	public void setMaxRows(int parm1) {

	} // end method setMaxRows

	public boolean getEscapeProcessing() {

		return false;
	} // end method getEscapeProcessing

	public void setEscapeProcessing(boolean parm1) {

	} // end method setEscapeProcessing

	public int getQueryTimeout() {

		return 1902594213;
	} // end method getQueryTimeout

	public void setQueryTimeout(int parm1) {

	} // end method setQueryTimeout

	public void setType(int parm1) {

	} // end method setType

	public void setConcurrency(int parm1) {

	} // end method setConcurrency

	public void setNull(int parm1, int parm2) {

	} // end method setNull

	public void setNull(int parm1, int parm2, String parm3) {

	} // end method setNull

	public void setBoolean(int parm1, boolean parm2) {

	} // end method setBoolean

	public void setByte(int parm1, byte parm2) {

	} // end method setByte

	public void setShort(int parm1, short parm2) {

	} // end method setShort

	public void setInt(int parm1, int parm2) {

	} // end method setInt

	public void setLong(int parm1, long parm2) {

	} // end method setLong

	public void setFloat(int parm1, float parm2) {

	} // end method setFloat

	public void setDouble(int parm1, double parm2) {

	} // end method setDouble

	public void setBigDecimal(int parm1, BigDecimal parm2) {

	} // end method setBigDecimal

	public void setString(int parm1, String parm2) {

	} // end method setString

	public void setBytes(int parm1, byte[] parm2) {

	} // end method setBytes

	public void setDate(int parm1, Date parm2) {

	} // end method setDate

	public void setTime(int parm1, Time parm2) {

	} // end method setTime

	public void setTimestamp(int parm1, Timestamp parm2) {

	} // end method setTimestamp

	public void setAsciiStream(int parm1, InputStream parm2, int parm3) {

	} // end method setAsciiStream

	public void setBinaryStream(int parm1, InputStream parm2, int parm3) {

	} // end method setBinaryStream

	public void setCharacterStream(int parm1, Reader parm2, int parm3) {

	} // end method setCharacterStream

	public void setObject(int parm1, Object parm2, int parm3, int parm4) {

	} // end method setObject

	public void setObject(int parm1, Object parm2, int parm3) {

	} // end method setObject

	public void setObject(int parm1, Object parm2) {

	} // end method setObject

	public void setRef(int parm1, Ref parm2) {

	} // end method setRef

	public void setBlob(int parm1, Blob parm2) {

	} // end method setBlob

	public void setClob(int parm1, Clob parm2) {

	} // end method setClob

	public void setArray(int parm1, Array parm2) {

	} // end method setArray

	public void setDate(int parm1, Date parm2, Calendar parm3) {

	} // end method setDate

	public void setTime(int parm1, Time parm2, Calendar parm3) {

	} // end method setTime

	public void setTimestamp(int parm1, Timestamp parm2, Calendar parm3) {

	} // end method setTimestamp

	public void clearParameters() {

	} // end method clearParameters

	public void execute() {

	} // end method execute

	public void addRowSetListener(RowSetListener parm1) {

	} // end method addRowSetListener

	public void removeRowSetListener(RowSetListener parm1) {

	} // end method removeRowSetListener

	public boolean next() {

		return false;
	} // end method next

	public void close() {

	} // end method close

	public boolean wasNull() {

		return false;
	} // end method wasNull

	public String getString(int parm1) {

		return null;
	} // end method getString

	public boolean getBoolean(int parm1) {

		return true;
	} // end method getBoolean

	public byte getByte(int parm1) {

		return 20;
	} // end method getByte

	public short getShort(int parm1) {

		return -14773;
	} // end method getShort

	public int getInt(int parm1) {

		return 1336230020;
	} // end method getInt

	public long getLong(int parm1) {

		return 2893649549072652188L;
	} // end method getLong

	public float getFloat(int parm1) {

		return -3.022368858327717E38F;
	} // end method getFloat

	public double getDouble(int parm1) {

		return -9.31296588344137E307;
	} // end method getDouble

	public BigDecimal getBigDecimal(int parm1, int parm2) {

		return null;
	} // end method getBigDecimal

	public byte[] getBytes(int parm1) {

		return null;
	} // end method getBytes

	public Date getDate(int parm1) {

		return null;
	} // end method getDate

	public Time getTime(int parm1) {

		return null;
	} // end method getTime

	public Timestamp getTimestamp(int parm1) {

		return null;
	} // end method getTimestamp

	public InputStream getAsciiStream(int parm1) {

		return null;
	} // end method getAsciiStream

	public InputStream getUnicodeStream(int parm1) {

		return null;
	} // end method getUnicodeStream

	public InputStream getBinaryStream(int parm1) {

		return null;
	} // end method getBinaryStream

	public String getString(String parm1) {

		return null;
	} // end method getString

	public boolean getBoolean(String parm1) {

		return true;
	} // end method getBoolean

	public byte getByte(String parm1) {

		return 74;
	} // end method getByte

	public short getShort(String parm1) {

		return -976;
	} // end method getShort

	public int getInt(String parm1) {

		return -1946722832;
	} // end method getInt

	public long getLong(String parm1) {

		return -5026748552689033827L;
	} // end method getLong

	public float getFloat(String parm1) {

		return 2.9913193284709667E38F;
	} // end method getFloat

	public double getDouble(String parm1) {

		return 9.101225161150468E307;
	} // end method getDouble

	public BigDecimal getBigDecimal(String parm1, int parm2) {

		return null;
	} // end method getBigDecimal

	public byte[] getBytes(String parm1) {

		return null;
	} // end method getBytes

	public Date getDate(String parm1) {

		return null;
	} // end method getDate

	public Time getTime(String parm1) {

		return null;
	} // end method getTime

	public Timestamp getTimestamp(String parm1) {

		return null;
	} // end method getTimestamp

	public InputStream getAsciiStream(String parm1) {

		return null;
	} // end method getAsciiStream

	public InputStream getUnicodeStream(String parm1) {

		return null;
	} // end method getUnicodeStream

	public InputStream getBinaryStream(String parm1) {

		return null;
	} // end method getBinaryStream

	public SQLWarning getWarnings() {

		return null;
	} // end method getWarnings

	public void clearWarnings() {

	} // end method clearWarnings

	public String getCursorName() {

		return null;
	} // end method getCursorName

	public ResultSetMetaData getMetaData() {

		return null;
	} // end method getMetaData

	public Object getObject(int parm1) {

		return null;
	} // end method getObject

	public Object getObject(String parm1) {

		return null;
	} // end method getObject

	public int findColumn(String parm1) {

		return 567655775;
	} // end method findColumn

	public Reader getCharacterStream(int parm1) {

		return null;
	} // end method getCharacterStream

	public Reader getCharacterStream(String parm1) {

		return null;
	} // end method getCharacterStream

	public BigDecimal getBigDecimal(int parm1) {

		return null;
	} // end method getBigDecimal

	public BigDecimal getBigDecimal(String parm1) {

		return null;
	} // end method getBigDecimal

	public boolean isBeforeFirst() {

		return false;
	} // end method isBeforeFirst

	public boolean isAfterLast() {

		return false;
	} // end method isAfterLast

	public boolean isFirst() {

		return true;
	} // end method isFirst

	public boolean isLast() {

		return false;
	} // end method isLast

	public void beforeFirst() {

	} // end method beforeFirst

	public void afterLast() {

	} // end method afterLast

	public boolean first() {

		return true;
	} // end method first

	public boolean last() {

		return false;
	} // end method last

	public int getRow() {

		return 1158714017;
	} // end method getRow

	public boolean absolute(int parm1) {

		return false;
	} // end method absolute

	public boolean relative(int parm1) {

		return false;
	} // end method relative

	public boolean previous() {

		return false;
	} // end method previous

	public void setFetchDirection(int parm1) {

	} // end method setFetchDirection

	public int getFetchDirection() {

		return 1957979730;
	} // end method getFetchDirection

	public void setFetchSize(int parm1) {

	} // end method setFetchSize

	public int getFetchSize() {

		return -2014456736;
	} // end method getFetchSize

	public int getType() {

		return -1351227638;
	} // end method getType

	public int getConcurrency() {

		return -1448914510;
	} // end method getConcurrency

	public boolean rowUpdated() {

		return false;
	} // end method rowUpdated

	public boolean rowInserted() {

		return true;
	} // end method rowInserted

	public boolean rowDeleted() {

		return true;
	} // end method rowDeleted

	public void updateNull(int parm1) {

	} // end method updateNull

	public void updateBoolean(int parm1, boolean parm2) {

	} // end method updateBoolean

	public void updateByte(int parm1, byte parm2) {

	} // end method updateByte

	public void updateShort(int parm1, short parm2) {

	} // end method updateShort

	public void updateInt(int parm1, int parm2) {

	} // end method updateInt

	public void updateLong(int parm1, long parm2) {

	} // end method updateLong

	public void updateFloat(int parm1, float parm2) {

	} // end method updateFloat

	public void updateDouble(int parm1, double parm2) {

	} // end method updateDouble

	public void updateBigDecimal(int parm1, BigDecimal parm2) {

	} // end method updateBigDecimal

	public void updateString(int parm1, String parm2) {

	} // end method updateString

	public void updateBytes(int parm1, byte[] parm2) {

	} // end method updateBytes

	public void updateDate(int parm1, Date parm2) {

	} // end method updateDate

	public void updateTime(int parm1, Time parm2) {

	} // end method updateTime

	public void updateTimestamp(int parm1, Timestamp parm2) {

	} // end method updateTimestamp

	public void updateAsciiStream(int parm1, InputStream parm2, int parm3) {

	} // end method updateAsciiStream

	public void updateBinaryStream(int parm1, InputStream parm2, int parm3) {

	} // end method updateBinaryStream

	public void updateCharacterStream(int parm1, Reader parm2, int parm3) {

	} // end method updateCharacterStream

	public void updateObject(int parm1, Object parm2, int parm3) {

	} // end method updateObject

	public void updateObject(int parm1, Object parm2) {

	} // end method updateObject

	public void updateNull(String parm1) {

	} // end method updateNull

	public void updateBoolean(String parm1, boolean parm2) {

	} // end method updateBoolean

	public void updateByte(String parm1, byte parm2) {

	} // end method updateByte

	public void updateShort(String parm1, short parm2) {

	} // end method updateShort

	public void updateInt(String parm1, int parm2) {

	} // end method updateInt

	public void updateLong(String parm1, long parm2) {

	} // end method updateLong

	public void updateFloat(String parm1, float parm2) {

	} // end method updateFloat

	public void updateDouble(String parm1, double parm2) {

	} // end method updateDouble

	public void updateBigDecimal(String parm1, BigDecimal parm2) {

	} // end method updateBigDecimal

	public void updateString(String parm1, String parm2) {

	} // end method updateString

	public void updateBytes(String parm1, byte[] parm2) {

	} // end method updateBytes

	public void updateDate(String parm1, Date parm2) {

	} // end method updateDate

	public void updateTime(String parm1, Time parm2) {

	} // end method updateTime

	public void updateTimestamp(String parm1, Timestamp parm2) {

	} // end method updateTimestamp

	public void updateAsciiStream(String parm1, InputStream parm2, int parm3) {

	} // end method updateAsciiStream

	public void updateBinaryStream(String parm1, InputStream parm2, int parm3) {

	} // end method updateBinaryStream

	public void updateCharacterStream(String parm1, Reader parm2, int parm3) {

	} // end method updateCharacterStream

	public void updateObject(String parm1, Object parm2, int parm3) {

	} // end method updateObject

	public void updateObject(String parm1, Object parm2) {

	} // end method updateObject

	public void insertRow() {

	} // end method insertRow

	public void updateRow() {

	} // end method updateRow

	public void deleteRow() {

	} // end method deleteRow

	public void refreshRow() {

	} // end method refreshRow

	public void cancelRowUpdates() {

	} // end method cancelRowUpdates

	public void moveToInsertRow() {

	} // end method moveToInsertRow

	public void moveToCurrentRow() {

	} // end method moveToCurrentRow

	public Statement getStatement() {

		return null;
	} // end method getStatement

	public Object getObject(int parm1, Map parm2) {

		return null;
	} // end method getObject

	public Ref getRef(int parm1) {

		return null;
	} // end method getRef

	public Blob getBlob(int parm1) {

		return null;
	} // end method getBlob

	public Clob getClob(int parm1) {

		return null;
	} // end method getClob

	public Array getArray(int parm1) {

		return null;
	} // end method getArray

	public Object getObject(String parm1, Map parm2) {

		return null;
	} // end method getObject

	public Ref getRef(String parm1) {

		return null;
	} // end method getRef

	public Blob getBlob(String parm1) {

		return null;
	} // end method getBlob

	public Clob getClob(String parm1) {

		return null;
	} // end method getClob

	public Array getArray(String parm1) {

		return null;
	} // end method getArray

	public Date getDate(int parm1, Calendar parm2) {

		return null;
	} // end method getDate

	public Date getDate(String parm1, Calendar parm2) {

		return null;
	} // end method getDate

	public Time getTime(int parm1, Calendar parm2) {

		return null;
	} // end method getTime

	public Time getTime(String parm1, Calendar parm2) {

		return null;
	} // end method getTime

	public Timestamp getTimestamp(int parm1, Calendar parm2) {

		return null;
	} // end method getTimestamp

	public Timestamp getTimestamp(String parm1, Calendar parm2) {

		return null;
	} // end method getTimestamp

	public URL getURL(int parm1) {

		return null;
	} // end method getURL

	public URL getURL(String parm1) {

		return null;
	} // end method getURL

	public void updateRef(int parm1, Ref parm2) {

	} // end method updateRef

	public void updateRef(String parm1, Ref parm2) {

	} // end method updateRef

	public void updateBlob(int parm1, Blob parm2) {

	} // end method updateBlob

	public void updateBlob(String parm1, Blob parm2) {

	} // end method updateBlob

	public void updateClob(int parm1, Clob parm2) {

	} // end method updateClob

	public void updateClob(String parm1, Clob parm2) {

	} // end method updateClob

	public void updateArray(int parm1, Array parm2) {

	} // end method updateArray

	public void updateArray(String parm1, Array parm2) {

	} // end method updateArray

} // end class RowSetTest
