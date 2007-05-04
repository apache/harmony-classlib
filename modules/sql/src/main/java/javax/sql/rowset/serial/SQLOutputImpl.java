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

public class SQLOutputImpl implements SQLOutput {
    public SQLOutputImpl(Vector<?> attributes, Map<String,?> map) throws NotImplementedException {
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

    public void writeBoolean(boolean theFlag) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }

    public void writeByte(byte theByte) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }

    public void writeBytes(byte[] theBytes) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

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

    public void writeDouble(double theDouble) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }

    public void writeFloat(float theFloat) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }

    public void writeInt(int theInt) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }

    public void writeLong(long theLong) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }

    public void writeObject(SQLData theObject) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }

    public void writeRef(Ref theRef) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

    }

    public void writeShort(short theShort) throws SQLException,
            NotImplementedException {
        throw new NotImplementedException();

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
