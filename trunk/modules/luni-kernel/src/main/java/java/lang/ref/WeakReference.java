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

package java.lang.ref;

/**
 * WeakReference objects are used to detect referents which are no longer
 * visible.
 * 
 * @since 1.2
 */
public class WeakReference<T> extends Reference<T> {

    /**
     * Constructs a new instance of this class.
     * 
     * @param r referent to track.
     */
    public WeakReference(T r) {
        super();
        initReference(r);
    }

    /**
     * Constructs a new instance of this class.
     * 
     * @param r referent to track.
     * @param q queue to register to the reference object with.
     */
    public WeakReference(T r, ReferenceQueue<? super T> q) {
        super();
        initReference(r, q);
    }
}
