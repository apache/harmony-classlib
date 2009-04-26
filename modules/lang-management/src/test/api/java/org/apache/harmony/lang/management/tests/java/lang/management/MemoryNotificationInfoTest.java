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

import junit.framework.TestCase;

import org.apache.harmony.lang.management.ManagementUtils;

public class MemoryNotificationInfoTest extends TestCase {

    public void test_Constructor_NullPoolName_NullUsage() {
        try {
            new MemoryNotificationInfo((String) null, (MemoryUsage) null,
                    -4294901761L);
            fail("should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    public void test_Constructor_NullUsage() {
        try {
            new MemoryNotificationInfo("poolName", (MemoryUsage) null,
                    -4294901761L);
            fail("should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    public void test_from_NullCompositeData() {
        assertNull(MemoryNotificationInfo.from(null));
    }

    public void test_from() {
        final MemoryUsage memoryUsage = new MemoryUsage(1, 2, 3, 4);
        final MemoryNotificationInfo memoryNotifyInfo = new MemoryNotificationInfo("Lloyd", memoryUsage, 42);
        
        CompositeData compositeData = ManagementUtils
                .toMemoryNotificationInfoCompositeData(memoryNotifyInfo);
        MemoryNotificationInfo fromInfo = MemoryNotificationInfo
                .from(compositeData);
        assertEquals(memoryNotifyInfo.getPoolName(), fromInfo.getPoolName());
        assertEquals(memoryNotifyInfo.getCount(), fromInfo.getCount());

        MemoryUsage fromUsage = fromInfo.getUsage();
        assertEquals(memoryUsage.getInit(), fromUsage.getInit());
        assertEquals(memoryUsage.getMax(), fromUsage.getMax());
        assertEquals(memoryUsage.getUsed(), fromUsage.getUsed());
        assertEquals(memoryUsage.getCommitted(), fromUsage.getCommitted());
    }

    public void test_getPoolName() {
        final MemoryUsage memoryUsage = new MemoryUsage(1, 2, 3, 4);
        final MemoryNotificationInfo memoryNotifyInfo = new MemoryNotificationInfo("Lloyd", memoryUsage, 42);
        assertEquals("Lloyd", memoryNotifyInfo.getPoolName());
    }

    public void test_getUsage() {
        final MemoryUsage memoryUsage = new MemoryUsage(1, 2, 3, 4);
        final MemoryNotificationInfo memoryNotifyInfo = new MemoryNotificationInfo("Lloyd", memoryUsage, 42);
        assertEquals(memoryUsage, memoryNotifyInfo.getUsage());
    }

    public void test_get() {
        final MemoryUsage memoryUsage = new MemoryUsage(1, 2, 3, 4);
        final MemoryNotificationInfo memoryNotifyInfo = new MemoryNotificationInfo("Lloyd", memoryUsage, 42);
        assertEquals(42, memoryNotifyInfo.getCount());
    }
}
