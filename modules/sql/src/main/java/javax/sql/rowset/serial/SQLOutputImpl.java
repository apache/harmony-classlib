/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package javax.sql.rowset.serial;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Ref;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Struct;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Vector;

import org.apache.harmony.luni.util.NotImplementedException;
import org.apache.harmony.sql.internal.nls.Messages;

public class SQLOutputImpl implements SQLOutput {
    private Vector attributes;

    private Map map;
    
    /**
     * Constructs a new SQLOutputImpl object using a list of attributes and a
     * custom name-type map. JDBC drivers will use this map to identify which
     * SQLData.writeSQL will be invoked.
     * 
     * @param attributes -
     *            the list of given attribute objects.
     * @param map -
     *            the UDT(user defined type) name-type map
     * @throws SQLException -
     *             if the attributes or the map is null
     */
    public SQLOutputImpl(Vector<?> attributes, Map<String, ?> map)
            throws SQLException {
        if (null == attributes || null == map) {
            throw new SQLException(Messages.getString("sql.33")); //$NON-NLS-1$
        }
        this.attributes = attributes;
        this.map = map;
    }

    public void writeArray(Array theArray) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }

    public void writeAsciiStream(InputStream theStream) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }

    public void writeBigDecimal(BigDecimal theBigDecimal) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }

    public void writeBinaryStream(InputStream theStream) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }

    public void writeBlob(Blob theBlob) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }
    
    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLOutput#writeBoolean(boolean)
     */
    @SuppressWarnings({ "boxing", "unchecked" })
    public void writeBoolean(boolean theFlag) throws SQLException,
            NotImplementedException {
        attributes.addElement(theFlag);
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLOutput#writeByte(byte)
     */
    @SuppressWarnings({ "boxing", "unchecked" })
    public void writeByte(byte theByte) throws SQLException {
        attributes.addElement(theByte);
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLOutput#writeBytes(byte[])
     */
    @SuppressWarnings({ "boxing", "unchecked" })
    public void writeBytes(byte[] theBytes) throws SQLException {
        attributes.addElement(theBytes);
    }

    public void writeCharacterStream(Reader theStream) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }

    public void writeClob(Clob theClob) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }

    public void writeDate(Date theDate) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }
    
    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLOutput#writeDouble(double)
     */
    @SuppressWarnings( { "boxing", "unchecked" })
    public void writeDouble(double theDouble) throws SQLException {
        attributes.addElement(theDouble);
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLOutput#writeFloat(float)
     */
    @SuppressWarnings( { "boxing", "unchecked" })
    public void writeFloat(float theFloat) throws SQLException {
        attributes.addElement(theFloat);
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLOutput#writeInt(int)
     */
    @SuppressWarnings( { "boxing", "unchecked" })
    public void writeInt(int theInt) throws SQLException {
        attributes.addElement(theInt);
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLOutput#writeLong(long)
     */
    @SuppressWarnings( { "boxing", "unchecked" })
    public void writeLong(long theLong) throws SQLException {
        attributes.addElement(theLong);
    }

    public void writeObject(SQLData theObject) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }

    public void writeRef(Ref theRef) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLOutput#writeShort(short)
     */
    @SuppressWarnings( { "boxing", "unchecked" })
    public void writeShort(short theShort) throws SQLException {
        attributes.addElement(theShort);
    }

    public void writeString(String theString) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }

    public void writeStruct(Struct theStruct) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }

    public void writeTime(Time theTime) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }

    public void writeTimestamp(Timestamp theTimestamp) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }

    public void writeURL(URL theURL) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }

}
