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
import java.sql.SQLInput;
import java.sql.Struct;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;

import org.apache.harmony.luni.util.NotImplementedException;
import org.apache.harmony.sql.internal.nls.Messages;

/**
 * A concrete implementation of SQLInput. The readXXX methods will be called
 * by SQLData.readSQL, which read different objects such as Array, BigDecimal
 * from this SQLInputImpl object.
 * 
 * Different JDBC drivers may have their own implementation of SQLInput and
 * won't use this class.
 */
public class SQLInputImpl implements SQLInput {
    
    private Object[] attributes;
    private Map<String,Class<?>> map;
    private int readPosition = 0;
    
    /**
     * Constructs a new SQLInputImpl object using an array of attributes and a
     * custom name-type map. 
     * 
     * @param attributes -
     *            the array of given attribute objects.
     * @param map -
     *            the UDT(user defined type) name-type map
     * @throws SQLException -
     *             if the attributes or the map is null
     */
    public SQLInputImpl(Object[] attributes, Map<String,Class<?>> map)throws SQLException {
        if (null == attributes || null == map) {
            throw new SQLException(Messages.getString("sql.34")); //$NON-NLS-1$
        }
        this.attributes = attributes;
        this.map = map;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readArray()
     */
    public Array readArray() throws SQLException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        Object o = attributes[readPosition++];
        if(o == null) {
            return null;
        }
        return (Array) o;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readAsciiStream()
     */
    public InputStream readAsciiStream() throws SQLException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        Object o = attributes[readPosition++];
        if(o == null) {
            return null;
        }
        return (InputStream) o;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readBigDecimal()
     */
    public BigDecimal readBigDecimal() throws SQLException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        Object o = attributes[readPosition++];
        if(o == null) {
            return null;
        }
        return (BigDecimal) o;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readBinaryStream()
     */
    public InputStream readBinaryStream() throws SQLException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        Object o = attributes[readPosition++];
        if(o == null) {
            return null;
        }
        return (InputStream) o;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readBlob()
     */
    public Blob readBlob() throws SQLException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        Object o = attributes[readPosition++];
        if(o == null) {
            return null;
        }
        return (Blob) o;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readBoolean()
     */
    public boolean readBoolean() throws SQLException, NotImplementedException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        Object o = attributes[readPosition++];
        return (Boolean) o;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readByte()
     */
    public byte readByte() throws SQLException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        Object o = attributes[readPosition++];
        return (Byte) o;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readBytes()
     */
    public byte[] readBytes() throws SQLException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        Object o = attributes[readPosition++];
        if(o == null) {
            return null;
        }
        return (byte[]) o;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readCharacterStream()
     */
    public Reader readCharacterStream() throws SQLException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        Object o = attributes[readPosition++];
        if(o == null) {
            return null;
        }
        return (Reader) o;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readClob()
     */
    public Clob readClob() throws SQLException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        Object o = attributes[readPosition++];
        if(o == null) {
            return null;
        }
        return (Clob) o;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readDate()
     */
    public Date readDate() throws SQLException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        Object o = attributes[readPosition++];
        if(o == null) {
            return null;
        }
        return (Date) o;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readDouble()
     */
    public double readDouble() throws SQLException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        Object o = attributes[readPosition++];
        return (Double) o;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readFloat()
     */
    public float readFloat() throws SQLException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        Object o = attributes[readPosition++];
        return (Float) o;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readInt()
     */
    public int readInt() throws SQLException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        Object o = attributes[readPosition++];
        return (Integer) o;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readLong()
     */
    public long readLong() throws SQLException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        Object o = attributes[readPosition++];
        return (Long) o;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readObject()
     */
    public Object readObject() throws SQLException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        Object o = attributes[readPosition++];
        if (o instanceof Struct) {
            Struct structuredType = (Struct)o;
            String typeName = structuredType.getSQLTypeName();
            Class c = map.get(typeName);
            if(c != null) {
                try {
                    SQLData data = (SQLData)c.newInstance();
                    SQLInputImpl input = new SQLInputImpl(structuredType.getAttributes(), map);
                    data.readSQL(input, typeName);
                    return data;
                } catch (IllegalAccessException e) {
                    throw new SQLException(e.getMessage());
                } catch (InstantiationException e) {
                    throw new SQLException(e.getMessage());
                }
                
            }
        }
        return o;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readRef()
     */
    public Ref readRef() throws SQLException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        Object o = attributes[readPosition++];
        if(o == null) {
            return null;
        }
        return (Ref) o;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readShort()
     */
    public short readShort() throws SQLException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        Object o = attributes[readPosition++];
        return (Short) o;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readString()
     */
    public String readString() throws SQLException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        Object o = attributes[readPosition++];
        if(o == null) {
            return null;
        }
        return (String) o;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readTime()
     */
    public Time readTime() throws SQLException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        Object o = attributes[readPosition++];
        if(o == null) {
            return null;
        }
        return (Time) o;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readTimestamp()
     */
    public Timestamp readTimestamp() throws SQLException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        Object o = attributes[readPosition++];
        if(o == null) {
            return null;
        }
        return (Timestamp) o;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#readURL()
     */
    public URL readURL() throws SQLException {
        if(readPosition >= attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        } else {
            throw new SQLException(Messages.getString("sql.37"));
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.SQLInput#wasNull()
     */
    public boolean wasNull() throws SQLException, NotImplementedException {
        if(readPosition > attributes.length) {
            throw new SQLException(Messages.getString("sql.35"));
        }
        return attributes[readPosition - 1] == null;
    }

}
