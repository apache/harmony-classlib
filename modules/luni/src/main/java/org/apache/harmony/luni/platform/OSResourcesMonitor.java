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

package org.apache.harmony.luni.platform;

public class OSResourcesMonitor {
    private static final int MAX_ITERATION_TIMES = 2;

    private static final int SLEEP_TIME = 50;

    private static final long FREE_MEMORY_THRESHOLD = 64 * 1024 * 1024; // 64M.

    private static final int MEMORY_LOAD_THRESHOLD = 80; // 80%
                                                                            // left.

    public static void ensurePhysicalMemoryCapacity() {
        if (!isSystemPhysicalMemoryLow()) {           
            return;
        }
        synchronized (OSResourcesMonitor.class) {
            int iteration = 0;
            while (isSystemPhysicalMemoryLow()) {               
                if (iteration > MAX_ITERATION_TIMES) {                    
                    return;
                }                
                iteration++;
                System.gc();                
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException e) {

                }
            }

        }
    }

    private native static boolean isSystemPhysicalMemoryLow();   
}
