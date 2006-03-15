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

package tests.api.java.sql;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

public class Impl_ResultSet implements ResultSet {

	public Impl_ResultSet() {
		super();
	} // end constructor

	public boolean next() {

		return true;
	} // end method next

	public void close() {

	} // end method close

	public boolean wasNull() {

		return true;
	} // end method wasNull

	public String getString(int parm1) {

		return null;
	} // end method getString

	public boolean getBoolean(int parm1) {

		return true;
	} // end method getBoolean

	public byte getByte(int parm1) {

		return -8;
	} // end method getByte

	public short getShort(int parm1) {

		return -12790;
	} // end method getShort

	public int getInt(int parm1) {

		return -1790920813;
	} // end method getInt

	public long getLong(int parm1) {

		return -7236492308370801956L;
	} // end method getLong

	public float getFloat(int parm1) {

		return -1.4686295075661984E37F;
	} // end method getFloat

	public double getDouble(int parm1) {

		return -1.6820553969741622E308;
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

		return false;
	} // end method getBoolean

	public byte getByte(String parm1) {

		return -10;
	} // end method getByte

	public short getShort(String parm1) {

		return 13590;
	} // end method getShort

	public int getInt(String parm1) {

		return 522475063;
	} // end method getInt

	public long getLong(String parm1) {

		return 6637918619301232066L;
	} // end method getLong

	public float getFloat(String parm1) {

		return 8.388008941813566E37F;
	} // end method getFloat

	public double getDouble(String parm1) {

		return 4.221562126032334E307;
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

		return 24975593;
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

		return true;
	} // end method isAfterLast

	public boolean isFirst() {

		return true;
	} // end method isFirst

	public boolean isLast() {

		return true;
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

		return -1536752422;
	} // end method getRow

	public boolean absolute(int parm1) {

		return false;
	} // end method absolute

	public boolean relative(int parm1) {

		return true;
	} // end method relative

	public boolean previous() {

		return true;
	} // end method previous

	public void setFetchDirection(int parm1) {

	} // end method setFetchDirection

	public int getFetchDirection() {

		return -1136843877;
	} // end method getFetchDirection

	public void setFetchSize(int parm1) {

	} // end method setFetchSize

	public int getFetchSize() {

		return -2139697329;
	} // end method getFetchSize

	public int getType() {

		return -631961459;
	} // end method getType

	public int getConcurrency() {

		return 6231768;
	} // end method getConcurrency

	public boolean rowUpdated() {

		return true;
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

} // end class ResultSetTest

