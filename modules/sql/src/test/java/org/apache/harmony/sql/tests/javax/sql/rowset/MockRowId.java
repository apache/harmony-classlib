package org.apache.harmony.sql.tests.javax.sql.rowset;

import java.sql.RowId;

public class MockRowId implements RowId {

    public byte[] getBytes() {
        return new byte[] { 'a', 'b' };
    }
}
