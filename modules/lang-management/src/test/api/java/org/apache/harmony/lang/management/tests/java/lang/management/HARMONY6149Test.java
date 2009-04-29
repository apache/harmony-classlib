/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.lang.management.tests.java.lang.management;

import java.lang.management.MemoryNotificationInfo;
import java.lang.management.MemoryUsage;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;

import junit.framework.TestCase;

import org.apache.harmony.lang.management.ManagementUtils;

public class HARMONY6149Test extends TestCase {

    private static final String CLASS_NAME = MemoryNotificationInfo.class
            .getName();

    private static final CompositeType memoryUsageCompositeType = ManagementUtils
            .getMemoryUsageCompositeType();

    private CompositeData memoryCompositeData;
    
    protected void setUp() {
        memoryCompositeData = ManagementUtils
                .toMemoryUsageCompositeData(new MemoryUsage(1, 2, 3, 4));
    }

    protected CompositeType getData(String[] typeNames, OpenType[] typeTypes)
            throws Exception {
        return new CompositeType(CLASS_NAME, CLASS_NAME, typeNames, typeNames,
                typeTypes);
    }

    public void test_from_scenario1() throws Exception {
        String[] names = { "poolName", "usage", "count" };
        Object[] values = { null, null, null };
        OpenType[] types = { SimpleType.STRING, memoryUsageCompositeType,
                SimpleType.LONG };
        CompositeType compositeType = getData(names, types);
        CompositeData data = new CompositeDataSupport(compositeType, names,
                values);
        try {
            MemoryNotificationInfo.from(data);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    public void test_from_scenario5() throws Exception {
        String[] names = { "poolName", "usage", "count" };
        Object[] values = { new Long(1), memoryCompositeData, new Long(42) };
        OpenType[] types = { SimpleType.LONG, memoryUsageCompositeType,
                SimpleType.LONG };

        CompositeType compositeType = getData(names, types);
        CompositeData data = new CompositeDataSupport(compositeType, names,
                values);
        try {
            MemoryNotificationInfo.from(data);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    public void test_from_scenario6() throws Exception {
        String[] names = { "poolName", "usage", "count" };
        Object[] values = { "TestPoolName", new Long(1), new Long(42) };
        OpenType[] types = { SimpleType.STRING, SimpleType.LONG,
                SimpleType.LONG };

        CompositeType compositeType = getData(names, types);
        CompositeData data = new CompositeDataSupport(compositeType, names,
                values);
        try {
            MemoryNotificationInfo.from(data);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    public void test_from_scenario7() throws Exception {
        String[] names = { "poolName", "usage", "count" };
        Object[] values = { "TestPoolName", memoryCompositeData, "42" };
        OpenType[] types = { SimpleType.STRING, memoryUsageCompositeType,
                SimpleType.STRING };

        CompositeType compositeType = getData(names, types);
        CompositeData data = new CompositeDataSupport(compositeType, names,
                values);
        try {
            MemoryNotificationInfo.from(data);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    public void test_from_scenario8() throws Exception {
        String[] names = { "poolName" };
        Object[] values = { "TestPoolName" };
        OpenType[] types = { SimpleType.STRING };

        CompositeType compositeType = getData(names, types);
        CompositeData data = new CompositeDataSupport(compositeType, names,
                values);
        try {
            MemoryNotificationInfo.from(data);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    public void test_from_scenario9() throws Exception {
        String[] names = { "usage" };
        Object[] values = { memoryCompositeData };
        OpenType[] types = { memoryUsageCompositeType };

        CompositeType compositeType = getData(names, types);
        CompositeData data = new CompositeDataSupport(compositeType, names,
                values);
        try {
            MemoryNotificationInfo.from(data);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    public void test_from_scenario10() throws Exception {
        String[] names = { "count" };
        Object[] values = { new Long(42) };
        OpenType[] types = { SimpleType.LONG };

        CompositeType compositeType = getData(names, types);
        CompositeData data = new CompositeDataSupport(compositeType, names,
                values);
        try {
            MemoryNotificationInfo.from(data);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    public void test_from_scenario11() throws Exception {
        String[] names = { "notPoolName", "usage", "count" };
        Object[] values = { "TestNotPoolName", memoryCompositeData,
                new Long(42) };
        OpenType[] types = { SimpleType.STRING, memoryUsageCompositeType,
                SimpleType.LONG };

        CompositeType compositeType = getData(names, types);
        CompositeData data = new CompositeDataSupport(compositeType, names,
                values);
        try {
            MemoryNotificationInfo.from(data);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }
}
