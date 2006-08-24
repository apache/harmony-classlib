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
@SuppressWarnings("serial")
final class MiniEnumSet<E extends Enum<E>> extends EnumSet<E> {
    
    private int size = 0;
    
    final private E[] enums;

    static final private int MAX_ELEMENTS = 64;
    
    private long bits = 0;
    
    MiniEnumSet(Class<E> elementType) {
        super(elementType);
        enums = elementType.getEnumConstants();
    }

    @Override
    public Iterator<E> iterator() {
        throw new NotYetImplementedException();
    }

    @Override
    public int size() {
        return size;
    }
    
    @Override
    public void clear() {
        bits = 0;
        size = 0;
    }
    
    @Override
    public boolean add(E element) {
        if (!isValidType(element.getDeclaringClass())) {
            throw new ClassCastException();
        }
        long mask = 1l << element.ordinal();
        if ((bits & mask) == mask) {
            return false;
        }
        bits |= mask;

        size++;
        return true;
    }
    
    @Override
    public boolean addAll(Collection<? extends E> collection) {
        if (0 == collection.size()) {
            return false;
        }
        if (collection instanceof EnumSet) {
            EnumSet<E> set = (EnumSet<E>) collection;
            if (!isValidType(set.elementClass)) {
                throw new ClassCastException();
            }
            MiniEnumSet<E> miniSet = (MiniEnumSet<E>) set;
            long oldBits = bits;
            bits |= miniSet.bits;
            size = Long.bitCount(bits);
            return (oldBits != bits);
        }
        return super.addAll(collection);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object object) {
        if (null == object) {
            return false;
        }
        if (!isValidType(object.getClass())) {
            return false;
        }
        E element = (E) object;
        int ordinal = element.ordinal();
        return (bits & (1l << ordinal)) != 0;
    }
    
    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection.size() == 0) {
            return true;
        }
        if (collection instanceof MiniEnumSet) {
            MiniEnumSet<E> set = (MiniEnumSet<E>) collection;
            return isValidType(set.elementClass ) && ((bits & set.bits) == set.bits);
        }
        return !(collection instanceof EnumSet) && super.containsAll(collection);  
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object object) {
        if (!contains(object)) {
            return false;
        }
        E element = (E) object;
        int ordinal = element.ordinal();
        bits -= (1l << ordinal);
        size--;
        return true;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object object) {
        if (!(object instanceof EnumSet)) {
            return super.equals(object);
        }
        EnumSet<E> set =(EnumSet<E>)object; 
        if( !isValidType(set.elementClass) ) {
            return size == 0 && set.size() == 0;
        }
        return bits == ((MiniEnumSet)set).bits;
    }
    
    @Override
    void complement() {
        if (0 != enums.length) {
            bits = ~bits;
            bits &= (-1l >>> (MAX_ELEMENTS - enums.length));
            size = enums.length - size;
        }
    }
}
