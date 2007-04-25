package org.apache.harmony.sql.tests.javax.sql.rowset;


import java.sql.SQLException;
import java.sql.Types;

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
        metaDataImpl.setAutoIncrement(1, true);
        assertTrue(metaDataImpl.isAutoIncrement(1));
        // original records have been overwritten
        metaDataImpl.setColumnCount(19);
        assertEquals(19, metaDataImpl.getColumnCount());
        assertFalse(metaDataImpl.isAutoIncrement(1));
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#getColumnClassName(int)}
     */
    public void test_getColumnClassNameI() throws SQLException {
        try {
            metaDataImpl.getColumnClassName(1);
            fail ("should throw SQLException");
        } catch (SQLException e) {            
            // expected
        }
        
        metaDataImpl.setColumnCount(12);
        assertEquals("java.lang.String", metaDataImpl.getColumnClassName(12));
        
        metaDataImpl.setColumnTypeName(12, null);
        assertEquals("java.lang.String", metaDataImpl.getColumnClassName(12));
        metaDataImpl.setColumnType(12, Types.BLOB);
        assertEquals("[B", metaDataImpl.getColumnClassName(12));
        metaDataImpl.setColumnType(12, Types.FLOAT);
        assertEquals("java.lang.Double", metaDataImpl.getColumnClassName(12));
        metaDataImpl.setColumnType(12, Types.BIGINT);
        assertEquals("java.lang.Long", metaDataImpl.getColumnClassName(12));
        metaDataImpl.setColumnType(12, Types.BIT);
        assertEquals("java.lang.Boolean", metaDataImpl.getColumnClassName(12));
        metaDataImpl.setColumnType(12, Types.DECIMAL);
        assertEquals("java.math.BigDecimal", metaDataImpl.getColumnClassName(12));
        metaDataImpl.setColumnType(12, Types.TINYINT);
        assertEquals("java.lang.Byte", metaDataImpl.getColumnClassName(12));
        
        try {
            metaDataImpl.getColumnClassName(0);
            fail ("should throw SQLException");
        } catch (SQLException e) {            
            // expected
        }
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#getColumnLabel(int)}
     */
    public void test_getColumnLabelI() throws SQLException {
        try {
            metaDataImpl.getColumnLabel(1);
            fail ("should throw SQLException");
        } catch (SQLException e) {            
            // expected
        }
        
        metaDataImpl.setColumnCount(3);
        assertNull(metaDataImpl.getColumnLabel(1));
        metaDataImpl.setColumnLabel(1, null);
        assertEquals("", metaDataImpl.getColumnLabel(1));
        metaDataImpl.setColumnLabel(1, "err");
        assertEquals("err", metaDataImpl.getColumnLabel(1));
        
        try {
            metaDataImpl.getColumnLabel(11);
            fail ("should throw SQLException");
        } catch (SQLException e) {            
            // expected
        }
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#getColumnName(int)}
     */
    public void test_getColumnNameI() throws SQLException {
        try {
            metaDataImpl.getColumnName(1);
            fail ("should throw SQLException");
        } catch (SQLException e) {            
            // expected
        }
        
        metaDataImpl.setColumnCount(13);
        assertNull(metaDataImpl.getColumnName(12));
        metaDataImpl.setColumnName(12, null);
        assertEquals("", metaDataImpl.getColumnName(12));
        metaDataImpl.setColumnName(12, "ColumnName");
        assertEquals("ColumnName", metaDataImpl.getColumnName(12));
        
        try {
            metaDataImpl.getColumnName(0);
            fail ("should throw SQLException");
        } catch (SQLException e) {            
            // expected
        }
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#getColumnType(int)}
     */
    public void test_getColumnTypeI() throws SQLException {
        try {
            metaDataImpl.getColumnType(1);
            fail ("should throw SQLException");
        } catch (SQLException e) {            
            // expected
        }
        
        metaDataImpl.setColumnCount(13);
        metaDataImpl.setColumnType(13, Types.ARRAY);
        assertEquals(Types.ARRAY, metaDataImpl.getColumnType(13));
        
        try {
            metaDataImpl.getColumnType(14);
            fail ("should throw SQLException");
        } catch (SQLException e) {            
            // expected
        }
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#getColumnTypeName(int)}
     */
    public void test_getColumnTypeNameI() throws SQLException {
        try {
            metaDataImpl.getColumnTypeName(223);
            fail ("should throw SQLException");
        } catch (SQLException e) {            
            // expected
        }
        
        metaDataImpl.setColumnCount(21);
        metaDataImpl.setColumnType(14, Types.BIGINT);
        metaDataImpl.setColumnTypeName(14, null);
        assertEquals("", metaDataImpl.getColumnTypeName(14));
        metaDataImpl.setColumnTypeName(14, "haha");
        assertEquals("haha", metaDataImpl.getColumnTypeName(14));
        
        try {
            metaDataImpl.getColumnTypeName(22);
            fail ("should throw SQLException");
        } catch (SQLException e) {            
            // expected
        }
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#getSchemaName(int)}
     */
    public void test_getSchemaNameI() throws SQLException {
        try {
            metaDataImpl.getSchemaName(352);
            fail ("should throw SQLException");
        } catch (SQLException e) {            
            // expected
        }
        
        metaDataImpl.setColumnCount(67);
        metaDataImpl.setSchemaName(67, null);
        assertEquals("", metaDataImpl.getSchemaName(67));
        metaDataImpl.setSchemaName(67, "a \u0053");
        assertEquals("a S", metaDataImpl.getSchemaName(67));
        
        try {
            metaDataImpl.getSchemaName(Integer.MIN_VALUE);
            fail ("should throw SQLException");
        } catch (SQLException e) {            
            // expected
        }
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
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#isCurrency(int)}
     */
    public void test_isCurrencyI() throws SQLException {
        try {
            metaDataImpl.isCurrency(1);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        
        metaDataImpl.setColumnCount(5);
        assertFalse(metaDataImpl.isCurrency(1));
        metaDataImpl.setCurrency(1, true);
        assertTrue(metaDataImpl.isCurrency(1));
        metaDataImpl.setCurrency(1, true);
        assertTrue(metaDataImpl.isCurrency(1));
                
        try {
            metaDataImpl.isCurrency(0);
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        
        metaDataImpl.setColumnCount(6);
        assertFalse(metaDataImpl.isCurrency(1));
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#isReadOnly(int)}
     */
    public void test_isReadOnlyI() throws SQLException {
        try {
            metaDataImpl.isReadOnly(1);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        
        metaDataImpl.setColumnCount(11);
        assertFalse(metaDataImpl.isReadOnly(1));
        assertFalse(metaDataImpl.isReadOnly(11));
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#isWritable(int)}
     */
    public void test_isWritableI() throws SQLException {
        try {
            metaDataImpl.isWritable(3);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        
        metaDataImpl.setColumnCount(3);
        assertTrue(metaDataImpl.isWritable(1));
        
        assertTrue(metaDataImpl.isWritable(3));
        assertFalse(metaDataImpl.isReadOnly(3));
        
        try {
            metaDataImpl.isWritable(4);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#isDefinitelyWritable(int)}
     */
    public void test_isDefinitelyWritableI() throws SQLException {
        metaDataImpl.setColumnCount(2);
        assertTrue(metaDataImpl.isDefinitelyWritable(1));
        assertTrue(metaDataImpl.isDefinitelyWritable(2));
        
        // RI fails here, which does not comply to the spec
        try {
            metaDataImpl.isDefinitelyWritable(-1);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#isSearchable(int)}
     */
    public void test_isSearchableI() throws SQLException {
        try {
            metaDataImpl.isSearchable(Integer.MAX_VALUE);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        
        metaDataImpl.setColumnCount(1);
        assertFalse(metaDataImpl.isSearchable(1));
        metaDataImpl.setSearchable(1, true);
        assertTrue(metaDataImpl.isSearchable(1));
        metaDataImpl.setSearchable(1, false);
        assertFalse(metaDataImpl.isSearchable(1));
        
        try {
            metaDataImpl.isSearchable(2);
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#isSigned(int)}
     */
    public void test_isSignedI() throws SQLException {
        try {
            metaDataImpl.isSigned(2);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        
        metaDataImpl.setColumnCount(35);
        assertFalse(metaDataImpl.isSigned(35));
        metaDataImpl.setSigned(35, true);
        assertTrue(metaDataImpl.isSigned(35));
        metaDataImpl.setSigned(35, false);
        assertFalse(metaDataImpl.isSigned(35));
        
        try {
            metaDataImpl.isSigned(36);
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
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#setColumnName(int, String)}
     */
    public void test_setColumnNameILjava_lang_String() throws SQLException {
        try {
            metaDataImpl.setColumnName(1, null);
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        
        metaDataImpl.setColumnCount(4);
        assertNull(metaDataImpl.getColumnName(1));
        metaDataImpl.setColumnName(1, "ate dsW");
        assertEquals("ate dsW", metaDataImpl.getColumnName(1));
        metaDataImpl.setColumnName(1, null);
        assertEquals("", metaDataImpl.getColumnName(1));
        
        try {
            metaDataImpl.setColumnName(5, "exception");
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#setColumnLabel(int, String)}
     */
    public void test_setColumnLabelILjava_lang_String() throws SQLException {
        try {
            metaDataImpl.setColumnLabel(1, null);
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        
        metaDataImpl.setColumnCount(3);
        assertNull(metaDataImpl.getColumnLabel(3));
        metaDataImpl.setColumnLabel(3, null);
        assertEquals("", metaDataImpl.getColumnLabel(3));
        
        try {
            metaDataImpl.setColumnLabel(4, "exception");
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#setColumnType(int, int)}
     */
    public void test_setColumnTypeII() throws SQLException {
       try {
           metaDataImpl.setColumnType(1, Types.BIGINT);
           fail ("should throw SQLException");
       } catch (SQLException e) {
           // expected
       }
       
       metaDataImpl.setColumnCount(2);
       assertEquals(0, metaDataImpl.getColumnType(1));
       metaDataImpl.setColumnType(1, Types.CLOB);
       assertEquals(Types.CLOB, metaDataImpl.getColumnType(1));
       metaDataImpl.setColumnType(1, Types.BOOLEAN);
       assertEquals(Types.BOOLEAN, metaDataImpl.getColumnType(1));
       
       try {
           metaDataImpl.setColumnType(1, 66);
           fail ("should throw SQLException");
       } catch (SQLException e) {
           // expected
       }       
       
       try {
           metaDataImpl.setColumnType(3, 58);
           fail ("should throw SQLException");
       } catch (SQLException e) {
           // expected
       }
    } 
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#setColumnTypeName(int, String)}
     */
    public void test_setColumnTypeNameILjava_lang_String() throws SQLException {
        try {
            metaDataImpl.setColumnTypeName(1, "aa");
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        
        metaDataImpl.setColumnCount(2);
        assertNull(metaDataImpl.getColumnTypeName(2));
        metaDataImpl.setColumnTypeName(2, null);
        assertEquals("", metaDataImpl.getColumnTypeName(2));
        metaDataImpl.setColumnTypeName(2, "");
        assertEquals("", metaDataImpl.getColumnTypeName(2));
        metaDataImpl.setColumnTypeName(2, "java.lang.String");
        assertEquals(0, metaDataImpl.getColumnType(2));
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#setCurrency(int, boolean)}
     */
    public void test_setCurrencyIZ() throws SQLException {
        try {
            metaDataImpl.setCurrency(12, false);
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        
        metaDataImpl.setColumnCount(7);
        assertFalse(metaDataImpl.isCurrency(4));
        metaDataImpl.setCurrency(4, false);
        assertFalse(metaDataImpl.isCurrency(4));
        metaDataImpl.setCurrency(4, true);
        assertTrue(metaDataImpl.isCurrency(4));
        
        try {
            metaDataImpl.setCurrency(8, true);
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#setSchemaName(int, String)}
     */
    public void test_setSchemaNameILjava_lang_String() throws SQLException {
        try {
            metaDataImpl.setSchemaName(-12, "asw");
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        
        metaDataImpl.setColumnCount(7);
        assertEquals("", metaDataImpl.getSchemaName(3));
        metaDataImpl.setSchemaName(3, "schema name");
        assertEquals("schema name", metaDataImpl.getSchemaName(3));
        metaDataImpl.setSchemaName(3, null);
        assertEquals("", metaDataImpl.getSchemaName(3));
        
        try {
            metaDataImpl.setSchemaName(Integer.MIN_VALUE, null);
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#setSearchable(int, boolean)}
     */
    public void test_setSearchableIZ() throws SQLException {
        try {
            metaDataImpl.setSearchable(-22, true);
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        
        metaDataImpl.setColumnCount(8);
        assertFalse(metaDataImpl.isSearchable(2));
        metaDataImpl.setSearchable(2, true);
        assertTrue(metaDataImpl.isSearchable(2));
        metaDataImpl.setSearchable(2, false);
        assertFalse(metaDataImpl.isSearchable(2));        
        
        try {
            metaDataImpl.setSearchable(Integer.MIN_VALUE, true);
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
    }
    
    /**
     * @tests {@link javax.sql.rowset.RowSetMetaDataImpl#setSigned(int, boolean)}
     */
    public void test_setSignedIZ() throws SQLException {
        try {
            metaDataImpl.setSigned(34, true);
            fail ("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        
        metaDataImpl.setColumnCount(12);
        assertFalse(metaDataImpl.isSigned(12));
        metaDataImpl.setSigned(12, true);
        assertTrue(metaDataImpl.isSigned(12));
        metaDataImpl.setSigned(12, false);
        assertFalse(metaDataImpl.isSigned(12));        
        
        try {
            metaDataImpl.setSigned(0, true);
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
