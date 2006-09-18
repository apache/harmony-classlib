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

package org.apache.harmony.kernel.vm;

/**
 * <p>
 * This class must be implemented by the VM to support the Threading subsystem.
 * </p>
 */
public class Threads {
    /**
     * <p>
     * Unparks the {@link Thread} that's passed.
     * </p>
     * 
     * @param thread The {@link Thread} to unpark.
     */
    public static void unpark(Thread thread) {
        return;
    }

    /**
     * <p>
     * Parks the {@link Thread#currentThread() current thread} either for a set
     * number of nanoseconds or until a future point in time.
     * </p>
     * 
     * @param timestamp If <code>true</code> <code>nanosOrTimestamp</code>
     *        should be consider as a timestamp based on
     *        {@link System#currentTimeMillis()}. If <code>false</code>,
     *        then <code>nanosOrTimestamp</code> should be considered as a
     *        relative number of nanoseconds from when this method was called;
     *        the value <code>0L</code> can be used in conjunction with this
     *        to indicate that time is not a factor when parking the thread.
     * @param nanosOrTimestamp Either a relative number of nanoseconds or a
     *        timestamp in milliseconds as defined by
     *        {@link System#currentTimeMillis()}.
     */
    public static void park(boolean timestamp, long nanosOrTimestamp) {

    }

    private Threads() {
    }
}
