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

import javax.management.openmbean.CompositeData;

/**
 * <p>
 * A memory usage snapshot.
 * </p>
 * 
 * @since 1.5
 */
public class MemoryUsage {

    /**
     * <p>
     * Constructs a MemoryUsage object from the CompositeData passed.
     * </p>
     * 
     * @param cd The CompositeDate object to retrieve data from.
     * @return A MemoryUsage instance.
     * @throws IllegalArgumentException if <code>cd</code> does not contain
     *         MemoryUsage data.
     */
    public static MemoryUsage from(CompositeData cd) {
        try {
            long init = ((Long) cd.get("init")).longValue();
            long used = ((Long) cd.get("used")).longValue();
            long committed = ((Long) cd.get("committed")).longValue();
            long max = ((Long) cd.get("max")).longValue();
            return new MemoryUsage(init, used, committed, max);
        } catch (ClassCastException e) {
            // if any cast fails, then a type was incorrect
            throw new IllegalArgumentException(e);
        }
    }

    private final long init;

    private final long used;

    private final long committed;

    private final long max;

    /**
     * <p>
     * Constructs a new MemoryUsage instance.
     * </p>
     * 
     * @param init The initial amount of memory (bytes) or <code>-1</code> if
     *        undefined.
     * @param used The amount of memory used (bytes).
     * @param committed The amount of memory committed (bytes).
     * @param max The maximum amount of memory available or <code>-1</code> if
     *        undefined.
     * @throws IllegalArgumentException if <code>init</code> or
     *         <code>max</code> are less than -1, <code>used</code> or
     *         <code>committed</code> is negative, <code>used</code> is
     *         greater than <code>committed</code> or <code>committed</code>
     *         is greater than <code>max</code> if defined.
     */
    public MemoryUsage(long init, long used, long committed, long max) {
        super();
        if (init < -1 || max < -1) {
            throw new IllegalArgumentException();
        }
        if (used < 0 || committed < 0 || used > committed) {
            throw new IllegalArgumentException();
        }
        if (max != -1 && committed > max) {
            throw new IllegalArgumentException();
        }
        this.init = init;
        this.used = used;
        this.committed = committed;
        this.max = max;
    }

    public long getCommitted() {
        return committed;
    }

    public long getInit() {
        return init;
    }

    public long getMax() {
        return max;
    }

    public long getUsed() {
        return used;
    }

    @Override
    public String toString() {
        return "init = " + init + " (" + (init >> 10)
          + "K) used = " + used + " (" + (used >> 10)
          + "K) committed = " + committed + " (" + (committed >> 10)
          + "K) max = " + max + " (" + (max >> 10) + "K)";
    }
}
