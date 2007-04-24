package org.apache.harmony.sql.tests.javax.sql.rowset;


import java.sql.SQLException;

import javax.sql.rowset.RowSetMetaDataImpl;
import junit.framework.TestCase;

/**
 * Test class for javax.sql.rowset.RowSetMetaDataImpl 
 *
 */
public class RowSetMetaDataImplTest extends TestCase {
    
    private static RowSetMetaDataImpl metaDataImpl = null;
    
    /**
     * @tests javax.sql.rowset.RowSetMetaDataImpl#RowSetMetaDataImpl()
     */
    public void test_Constructor() {
        assertNotNull(metaDataImpl);        
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#getColumnCount()}
     */
    public void test_getColumnCount() throws SQLException {
        assertEquals(0, metaDataImpl.getColumnCount());
        try {
            metaDataImpl.isAutoIncrement(1);
            fail("should throw SQLException");
        } catch (SQLException e) {            
            // expected
        }
        
        metaDataImpl.setColumnCount(4);
        assertEquals(4, metaDataImpl.getColumnCount());
        assertFalse(metaDataImpl.isAutoIncrement(4));
        
        metaDataImpl.setColumnCount(Integer.MAX_VALUE);
        assertFalse(metaDataImpl.isAutoIncrement(4));
        assertEquals(Integer.MAX_VALUE, metaDataImpl.getColumnCount());
        // RI throws ArrayIndexOutOfBoundsException here, which is a RI's bug
        try {
            metaDataImpl.isAutoIncrement(5);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#setColumnCount(int)}
     */
    public void test_setColumnCountI() throws SQLException {
        try {
            metaDataImpl.setColumnCount(-1);
            fail ("should throw SQLException");
        } catch (SQLException e) {            
            // expected
        }
        
        try {
            metaDataImpl.setColumnCount(0);
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        
        metaDataImpl.setColumnCount(18);
        assertEquals(18, metaDataImpl.getColumnCount());
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#isAutoIncrement(int)}
     */
    public void test_isAutoIncrementI() throws SQLException {
        try {
            metaDataImpl.isAutoIncrement(1);
            fail ("should throw SQLException");
        } catch (SQLException e) {            
            // expected
        }
        
        metaDataImpl.setColumnCount(3);      
        assertFalse(metaDataImpl.isAutoIncrement(1));
        assertFalse(metaDataImpl.isAutoIncrement(3));
        metaDataImpl.setAutoIncrement(3, true);
        assertTrue(metaDataImpl.isAutoIncrement(3));
        
        try {
            metaDataImpl.isAutoIncrement(-1);
            fail ("should throw SQLException");
        } catch (SQLException e) {            
            // expected
        }
        
        try {
            metaDataImpl.isAutoIncrement(4);
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#isCaseSensitive(int)}
     */
    public void test_isCaseSensitiveI() throws SQLException {
        metaDataImpl.setColumnCount(5);
        assertFalse(metaDataImpl.isCaseSensitive(2));
        assertFalse(metaDataImpl.isCaseSensitive(5));
        
        metaDataImpl.setCaseSensitive(2, true);
        assertTrue(metaDataImpl.isCaseSensitive(2));
        metaDataImpl.setCaseSensitive(2, false);
        assertFalse(metaDataImpl.isCaseSensitive(2));
        
        try {
            metaDataImpl.isCaseSensitive(0);
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        
        try {
            metaDataImpl.isCaseSensitive(6);
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#setAutoIncrement(int, boolean)}
     */
    public void test_setAutoIncrementIZ() throws SQLException {
        try {
            metaDataImpl.setAutoIncrement(1, true);
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        
        metaDataImpl.setColumnCount(2);
        assertFalse(metaDataImpl.isAutoIncrement(1));
        metaDataImpl.setAutoIncrement(1, false);
        assertFalse(metaDataImpl.isAutoIncrement(1));
        metaDataImpl.setAutoIncrement(1, true);
        assertTrue(metaDataImpl.isAutoIncrement(1));
        
        try {
            metaDataImpl.setAutoIncrement(-1, false);
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#setCaseSensitive(int, boolean)}
     */
    public void test_setCaseSensitiveIZ() throws SQLException {
        try {
            metaDataImpl.setCaseSensitive(2, false);
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        
        metaDataImpl.setColumnCount(9);
        assertFalse(metaDataImpl.isCaseSensitive(9));
        metaDataImpl.setCaseSensitive(9, false);
        assertFalse(metaDataImpl.isCaseSensitive(9));
        metaDataImpl.setCaseSensitive(9, true);
        assertTrue(metaDataImpl.isCaseSensitive(9));
        metaDataImpl.setAutoIncrement(9, false);
        assertTrue(metaDataImpl.isCaseSensitive(9));
        
        try {
            metaDataImpl.setCaseSensitive(10, true);
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        metaDataImpl = new RowSetMetaDataImpl();
    }

    @Override
    protected void tearDown() throws Exception {
        metaDataImpl = null;
        super.tearDown();
    }

}
