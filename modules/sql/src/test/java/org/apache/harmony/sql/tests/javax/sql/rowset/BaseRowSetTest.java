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

package org.apache.harmony.sql.tests.javax.sql.rowset;

import java.sql.SQLException;
import java.sql.Types;
import javax.sql.rowset.BaseRowSet;
import junit.framework.TestCase;

public class BaseRowSetTest extends TestCase {
    
    public void testGetParams() throws Exception {
        BaseRowSetImpl brs = new BaseRowSetImpl();
        Object[] params = brs.getParams();
        assertNotNull(params);
        assertEquals(0, params.length);
    }
    
    public void testSetNullintint() throws Exception {
        BaseRowSetImpl brs = new BaseRowSetImpl();
        try {
            brs.setNull(1, Types.BINARY);
            fail("sql exception expected");
        } catch (SQLException e) {
        }
        
        brs.initParams();
        
        try {
            brs.setNull(0, Types.BINARY);
            fail("sql exception expected");
        } catch (SQLException e) {
        }
        
        brs.setNull(1, Types.BINARY);
        Object[] params = brs.getParams();
        assertNotNull(params);
        Object[] param = (Object[])params[0];
        assertNotNull(param);
        assertEquals(2, param.length);
        assertNull(param[0]);
        assertEquals(Integer.valueOf(Types.BINARY), param[1]);
    }
    
    public void testSetNullintintString() throws Exception {
        BaseRowSetImpl brs = new BaseRowSetImpl();
        try {
            brs.setNull(1, Types.BINARY, "java.lang.Boolean");
            fail("sql exception expected");
        } catch (SQLException e) {
        }
        
        brs.initParams();
        
        try {
            brs.setNull(0, Types.BINARY, "java.lang.Boolean");
            fail("sql exception expected");
        } catch (SQLException e) {
        }
        
        brs.setNull(1, Types.BINARY, "java.lang.Boolean");
        Object[] params = brs.getParams();
        assertNotNull(params);
        Object[] param = (Object[])params[0];
        assertNotNull(param);
        assertEquals(3, param.length);
        assertNull(param[0]);
        assertEquals(Integer.valueOf(Types.BINARY), param[1]);
        assertEquals("java.lang.Boolean", param[2]);
    }
    
    public void testSetBoolean() throws Exception {
        BaseRowSetImpl brs = new BaseRowSetImpl();
        try {
            brs.setBoolean(1, true);
            fail("sql exception expected");
        } catch (SQLException e) {
        }
        
        brs.initParams();
        
        try {
            brs.setBoolean(0, true);
            fail("sql exception expected");
        } catch (SQLException e) {
        }
        
        brs.setBoolean(1, true);
        Object[] params = brs.getParams();
        assertNotNull(params);
        assertEquals(1, params.length);
        assertEquals(Boolean.TRUE, params[0]);
    }
    
    public void testSetByte() throws Exception {
        BaseRowSetImpl brs = new BaseRowSetImpl();
        try {
            brs.setByte(1, (byte)1);
            fail("sql exception expected");
        } catch (SQLException e) {
        }
        
        brs.initParams();
        
        try {
            brs.setByte(0, (byte)1);
            fail("sql exception expected");
        } catch (SQLException e) {
        }
        
        brs.setByte(1, (byte)1);
        Object[] params = brs.getParams();
        assertNotNull(params);
        assertEquals(1, params.length);
        assertEquals(Byte.valueOf((byte)1), params[0]);
    }
    
    public void testSetShort() throws Exception {
        BaseRowSetImpl brs = new BaseRowSetImpl();
        try {
            brs.setShort(1, (short)1);
            fail("sql exception expected");
        } catch (SQLException e) {
        }
        
        brs.initParams();
        
        try {
            brs.setShort(0, (short)1);
            fail("sql exception expected");
        } catch (SQLException e) {
        }
        
        brs.setShort(1, (byte)1);
        Object[] params = brs.getParams();
        assertNotNull(params);
        assertEquals(1, params.length);
        assertEquals(Short.valueOf((short)1), params[0]);
    }
    
    private static final class BaseRowSetImpl extends BaseRowSet {
        private static final long serialVersionUID = 1L;

        @Override
        protected void initParams() {
            super.initParams();
        }
    }
}
