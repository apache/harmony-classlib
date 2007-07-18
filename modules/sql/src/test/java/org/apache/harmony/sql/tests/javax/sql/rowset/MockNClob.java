package org.apache.harmony.sql.tests.javax.sql.rowset;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.SQLException;

public class MockNClob implements NClob {

    public Reader characterStreamReader = new StringReader("xys");

    public InputStream asciiInputStream = new ByteArrayInputStream("hello"
            .getBytes());

    public InputStream getAsciiStream() throws SQLException {
        return asciiInputStream;
    }

    public Reader getCharacterStream() throws SQLException {
        return characterStreamReader;
    }

    public String getSubString(long pos, int length) throws SQLException {
        return null;
    }

    public long length() throws SQLException {
        return 3;
    }

    public long position(Clob searchstr, long start) throws SQLException {
        return 0;
    }

    public long position(String searchstr, long start) throws SQLException {
        return 0;
    }

    public OutputStream setAsciiStream(long pos) throws SQLException {
        return null;
    }

    public Writer setCharacterStream(long pos) throws SQLException {
        return null;
    }

    public int setString(long pos, String str) throws SQLException {
        return 0;
    }

    public int setString(long pos, String str, int offset, int len)
            throws SQLException {
        return 0;
    }

    public void truncate(long len) throws SQLException {
    }

    public void free() throws SQLException {
    }

    public Reader getCharacterStream(long pos, long length) throws SQLException {
        return null;
    }

}
