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
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;

import org.apache.harmony.luni.util.NotImplementedException;

public class SerialClob implements Clob, Serializable, Cloneable {

    // required by serialized form
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -1662519690087375313L;

    // required by serialized form
    @SuppressWarnings("unused")
    private char[] buf;

    // required by serialized form
    @SuppressWarnings("unused")
    private Clob clob;

    // required by serialized form
    @SuppressWarnings("unused")
    private long len;

    // required by serialized form
    @SuppressWarnings("unused")
    private long origLen;

    public SerialClob(char[] ch) throws SerialException, SQLException,
            NotImplementedException {
        throw new NotImplementedException();
    }

    public SerialClob(Clob clob) throws SerialException, SQLException,
            NotImplementedException {
        throw new NotImplementedException();
    }

    public long length() throws SerialException, NotImplementedException {
        throw new NotImplementedException();
    }

    public InputStream getAsciiStream() throws SerialException,
            NotImplementedException {
        throw new NotImplementedException();
    }

    public Reader getCharacterStream() throws SerialException,
            NotImplementedException {
        throw new NotImplementedException();
    }

    public String getSubString(long pos, int length) throws SerialException,
            NotImplementedException {
        throw new NotImplementedException();
    }

    public long position(Clob searchstr, long start) throws SerialException,
            SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public long position(String searchstr, long start) throws SerialException,
            SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public OutputStream setAsciiStream(long pos) throws SerialException,
            SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public Writer setCharacterStream(long pos) throws SerialException,
            SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public int setString(long pos, String str) throws SerialException,
            NotImplementedException {
        throw new NotImplementedException();
    }

    public int setString(long pos, String str, int offset, int len)
            throws SerialException, NotImplementedException {
        throw new NotImplementedException();
    }

    public void truncate(long len) throws SerialException,
            NotImplementedException {
        throw new NotImplementedException();
    }
}
