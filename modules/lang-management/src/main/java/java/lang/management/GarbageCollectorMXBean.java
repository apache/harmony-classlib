/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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

/**
 * <p>
 * The management interface for the garbage collector system.
 * </p>
 * <p>
 * <code>ObjectName</code> pattern: java.lang:type=GarbageCollector,name=<i>collector_name</i>
 * </p>
 * 
 * @since 1.5
 */
public interface GarbageCollectorMXBean extends MemoryManagerMXBean {
    /**
     * <p>
     * The number of collections that have been executed by this collector. A
     * value of <code>-1</code> means that collection counts are undefined for
     * this collector.
     * </p>
     * 
     * @return The number of collections executed.
     */
    long getCollectionCount();

    /**
     * <p>
     * The approximate, cumulative time (in milliseconds) spent executing
     * collections for this collector.
     * </p>
     * 
     * @return The time spent collecting garbage.
     */
    long getCollectionTime();
}
