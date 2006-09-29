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

package java.lang.management;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

/**
 * <p>
 * The factory for retrieving all managed object for the JVM as well as the
 * JVM's MBean server.
 * </p>
 * 
 * @since 1.5
 */
public class ManagementFactory {
    /**
     * <p>
     * The String value of the {@link ObjectName} for {@link ClassLoadingMXBean}.
     * </p>
     */
    public static final String CLASS_LOADING_MXBEAN_NAME = "java.lang:type=ClassLoading";

    /**
     * <p>
     * The String value of the {@link ObjectName} for {@link CompilationMXBean}.
     * </p>
     */
    public static final String COMPILATION_MXBEAN_NAME = "java.lang:type=Compilation";

    /**
     * <p>
     * The String value of the {@link ObjectName} for
     * {@link GarbageCollectorMXBean}.
     * </p>
     */
    public static final String GARBAGE_COLLECTOR_MXBEAN_DOMAIN_TYPE = "java.lang:type=GarbageCollector";

    /**
     * <p>
     * The String value of the {@link ObjectName} for
     * {@link MemoryManagerMXBean}.
     * </p>
     */
    public static final String MEMORY_MANAGER_MXBEAN_DOMAIN_TYPE = "java.lang:type=MemoryManager";

    /**
     * <p>
     * The String value of the {@link ObjectName} for {@link MemoryMXBean}.
     * </p>
     */
    public static final String MEMORY_MXBEAN_NAME = "java.lang:type=Memory";

    /**
     * <p>
     * The String value of the {@link ObjectName} for {@link MemoryPoolMXBean}.
     * </p>
     */
    public static final String MEMORY_POOL_MXBEAN_DOMAIN_TYPE = "java.lang:type=MemoryPool";

    /**
     * <p>
     * The String value of the {@link ObjectName} for
     * {@link OperatingSystemMXBean}.
     * </p>
     */
    public static final String OPERATING_SYSTEM_MXBEAN_NAME = "java.lang:type=OperatingSystem";

    /**
     * <p>
     * The String value of the {@link ObjectName} for {@link RuntimeMXBean}.
     * </p>
     */
    public static final String RUNTIME_MXBEAN_NAME = "java.lang:type=Runtime";

    /**
     * <p>
     * The String value of the {@link ObjectName} for {@link ThreadMXBean}.
     * </p>
     */
    public static final String THREAD_MXBEAN_NAME = "java.lang:type=Threading";

    public static ClassLoadingMXBean getClassLoadingMXBean() {
        return null;
    }

    public static CompilationMXBean getCompilationMXBean() {
        return null;
    }

    public static List<GarbageCollectorMXBean> getGarbageCollectorMXBeans() {
        return Collections.emptyList();
    }

    public static List<MemoryManagerMXBean> getMemoryManagerMXBeans() {
        return Collections.emptyList();
    }

    public static MemoryMXBean getMemoryMXBean() {
        return null;
    }

    public static List<MemoryPoolMXBean> getMemoryPoolMXBeans() {
        return null;
    }

    public static OperatingSystemMXBean getOperatingSystemMXBean() {
        return null;
    }

    public static MBeanServer getPlatformMBeanServer() {
        return null;
    }

    public static RuntimeMXBean getRuntimeMXBean() {
        return null;
    }

    public static ThreadMXBean getThreadMXBean() {
        return null;
    }

    public static <T> T newPlatformMXBeanProxy(MBeanServerConnection connection,
            String mxbeanName, Class<T> mxbeanInterface) throws IOException {
        return null;
    }

    private ManagementFactory() {
    }
}
