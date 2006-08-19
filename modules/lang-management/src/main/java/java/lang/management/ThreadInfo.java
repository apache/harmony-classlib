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

import javax.management.openmbean.CompositeData;

/**
 * <p>
 * Thread information.
 * </p>
 * 
 * @since 1.5
 */
public class ThreadInfo {

    public static ThreadInfo from(CompositeData cd) {
        return null;
    }

    ThreadInfo() {
        super();
    }

    public long getBlockedCount() {
        return -1;
    }

    public long getBlockedTime() {
        return -1;
    }

    public String getLockName() {
        return null;
    }

    public long getLockOwnerId() {
        return -1;
    }

    public String getLockOwnerName() {
        return null;
    }

    public StackTraceElement[] getStackTrace() {
        return null;
    }

    public long getThreadId() {
        return -1;
    }

    public String getThreadName() {
        return null;
    }

    public Thread.State getThreadState() {
        return null;
    }

    public long getWaitedCount() {
        return -1;
    }

    public long getWaitedTime() {
        return -1;
    }

    public boolean isInNative() {
        return false;
    }

    public boolean isSuspended() {
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
