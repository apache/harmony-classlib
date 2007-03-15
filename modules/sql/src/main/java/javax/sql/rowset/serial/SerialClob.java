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

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;

import org.apache.harmony.luni.util.NotImplementedException;
import org.apache.harmony.sql.internal.nls.Messages;

public class SerialClob implements Clob, Serializable, Cloneable {

    // required by serialized form
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -1662519690087375313L;

    private char[] buf;

    // required by serialized form
    @SuppressWarnings("unused")
    private Clob clob;

    private long len;

    // required by serialized form
    private long origLen;

    public SerialClob(char[] ch) throws SerialException, SQLException {
        buf = new char[ch.length];
        origLen = ch.length;
        len = origLen;
        System.arraycopy(ch, 0, buf, 0, (int) len);
    }

    public SerialClob(Clob clob) throws SerialException, SQLException {
        Reader characterStream;
        InputStream asciiStream;

        if (clob == null) {
            throw new SQLException(Messages.getString("sql.19"));//$NON-NLS-1$
        }

        characterStream = clob.getCharacterStream();
        asciiStream = clob.getAsciiStream();
        if (characterStream == null || asciiStream == null) {
            throw new SQLException(Messages.getString("sql.20"));//$NON-NLS-1$
        }

        this.clob = clob;
        origLen = clob.length();
        len = origLen;
        buf = new char[(int) len];
        try {
            characterStream.read(buf);
        } catch (IOException e) {
            SerialException se = new SerialException(
                    "SerialClob: " + e.getMessage());

            se.initCause(e);
            throw se;
        }
    }

    public long length() throws SerialException {
        return len;
    }

    public InputStream getAsciiStream() throws SerialException,
            NotImplementedException {
        throw new NotImplementedException();
    }

    public Reader getCharacterStream() throws SerialException,
            NotImplementedException {
        return new CharArrayReader(buf);
    }

    public String getSubString(long pos, int length) throws SerialException {
        if (pos < 1 || pos > len) {
            throw new SerialException(Messages.getString("sql.21")); // $NON-NLS-1$
        }
        if (length < 0 || length > len) {
            throw new SerialException(Messages.getString("sql.22")); // $NON-NLS-1$
        }
        return new String(buf, (int) (pos - 1), length);
    }

    public long position(Clob searchstr, long start) throws SerialException,
            SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public long position(String searchstr, long start) throws SerialException,
            SQLException {
        if (start < 1 || len - (start - 1) < searchstr.length()) {
            return -1;
        }
        char[] pattern = searchstr.toCharArray();
        for (int i = (int) start - 1; i < len; i++) {
            if (match(buf, i, pattern)) {
                return i + 1;
            }
        }
        return -1;
    }

    /*
     * Returns true if the chars array contains exactly the same elements from
     * start position to start + pattern.length as pattern. Otherwise returns
     * false.
     */
    private boolean match(char[] chars, int start, char[] pattern) {
        for (int i = 0; i < pattern.length;) {
            if (chars[start++] != pattern[i++]) {
                return false;
            }
        }
        return true;
    }

    public OutputStream setAsciiStream(long pos) throws SerialException,
            SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public Writer setCharacterStream(long pos) throws SerialException,
            SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    public int setString(long pos, String str) throws SerialException {
        return setString(pos, str, 0, str.length());
    }

    public int setString(long pos, String str, int offset, int length)
            throws SerialException {
        if (pos < 1 || length < 0 || pos > (len - length + 1)) {
            throw new SerialException(Messages.getString("sql.21")); // $NON-NLS-1$
        }
        if (offset < 0  || offset > (str.length() - length)) {
            throw new SerialException(Messages.getString("sql.21")); // $NON-NLS-1$
        }
        if (length > len + offset) {
            throw new SerialException(Messages.getString("sql.23")); // $NON-NLS-1$
        }
        str.getChars(offset, offset+length, buf, (int)pos-1);
        return length;
    }

    public void truncate(long len) throws SerialException,
            NotImplementedException {
        throw new NotImplementedException();
    }
}
