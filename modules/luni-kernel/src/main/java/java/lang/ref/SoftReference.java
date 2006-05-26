/* Copyright 1998, 2005 The Apache Software Foundation or its licensors, as applicable
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

package java.lang.ref;

/**
 * SoftReference objects are used to detect referents which are no longer
 * visible and who's memory is to be reclaimed.
 * 
 * @since JDK1.2
 */
public class SoftReference<T> extends Reference<T> {

    /**
     * Constructs a new instance of this class.
     * 
     * @param r
     *            referent to track.
     * @param q
     *            queue to register to the reference object with.
     */
    public SoftReference(T r, ReferenceQueue<? super T> q) {
        initReference(r, q);
    }

    /**
     * Constructs a new instance of this class.
     * 
     * @param r
     *            referent to track.
     */
    public SoftReference(T r) {
        initReference(r);
    }

    /**
     * Return the referent of the reference object.
     * 
     * @return Object Referent to which reference refers, or null if object has
     *         been cleared.
     */
    public T get() {
        return super.get();
    }
}
