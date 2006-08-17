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

package java.util;

import org.apache.harmony.luni.util.NotYetImplementedException;

/**
 * This is a concrete subclass of EnumSet designed specifically for enum type
 * with less than or equal to 64 elements.
 * 
 */
final class MiniEnumSet<E extends Enum<E>> extends EnumSet<E> {
    
    MiniEnumSet(Class<E> elementType) {
        super(elementType);
    }

    @Override
    public Iterator<E> iterator() {
        throw new NotYetImplementedException();
    }

    @Override
    public int size() {
        throw new NotYetImplementedException();
    }
}
