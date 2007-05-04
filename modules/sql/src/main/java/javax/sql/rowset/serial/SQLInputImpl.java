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
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;

import org.apache.harmony.luni.util.NotImplementedException;

public class SQLInputImpl implements SQLInput {
    public SQLInputImpl(Object[] attributes, Map<String,Class<?>> map) throws NotImplementedException {
    }

    public Array readArray() throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public InputStream readAsciiStream() throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();
    }

    public BigDecimal readBigDecimal() throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();
    }

    public InputStream readBinaryStream() throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();
    }

    public Blob readBlob() throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public boolean readBoolean() throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public byte readByte() throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public byte[] readBytes() throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public Reader readCharacterStream() throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();
    }

    public Clob readClob() throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public Date readDate() throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public double readDouble() throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public float readFloat() throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public int readInt() throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public long readLong() throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public Object readObject() throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public Ref readRef() throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public short readShort() throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public String readString() throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public Time readTime() throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public Timestamp readTimestamp() throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();
    }

    public URL readURL() throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public boolean wasNull() throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

}
