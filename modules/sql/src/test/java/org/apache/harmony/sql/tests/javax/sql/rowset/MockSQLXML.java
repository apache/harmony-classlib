package org.apache.harmony.sql.tests.javax.sql.rowset;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.SQLException;
import java.sql.SQLXML;

import javax.xml.transform.Result;
import javax.xml.transform.Source;

public class MockSQLXML implements SQLXML {
    private String str = "sqlXML";

    public void free() throws SQLException {
        str = null;
    }

    public InputStream getBinaryStream() throws SQLException {
        return null;
    }

    public Reader getCharacterStream() throws SQLException {
        return null;
    }

    public <T extends Source> T getSource(Class<T> sourceClass)
            throws SQLException {
        return null;
    }

    public String getString() throws SQLException {
        return str;
    }

    public OutputStream setBinaryStream() throws SQLException {
        return null;
    }

    public Writer setCharacterStream() throws SQLException {
        return null;
    }

    public <T extends Result> T setResult(Class<T> resultClass)
            throws SQLException {
        return null;
    }

    public void setString(String value) throws SQLException {
        str = value;
    }
}
