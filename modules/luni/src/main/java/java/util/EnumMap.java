/* Licensed to the Apache Software Foundation (ASF) under one or more
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

package java.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;

public class EnumMap<K extends Enum<K>, V> extends AbstractMap<K, V> implements
        Serializable, Cloneable {

    private static final long serialVersionUID = 458661240069192865L;

    private Class<K> keyType;

    transient Enum[] keys;

    transient Object[] values;

    transient boolean[] hasMapping;

    private transient int mappingsCount;

    transient int enumSize;

    private transient EnumMapEntrySet<K, V> entrySet = null;

    private static class Entry<KT extends Enum<KT>, VT> extends
            MapEntry<KT, VT> {
        private final EnumMap<KT, VT> enumMap;

        private final int ordinal;

        Entry(KT theKey, VT theValue, EnumMap<KT, VT> em) {
            super(theKey, theValue);
            enumMap = em;
            ordinal = ((Enum) theKey).ordinal();
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object object) {
            if (!enumMap.hasMapping[ordinal]) {
                return false;
            }
            boolean isEqual = false;
            if (object instanceof Map.Entry) {
                Map.Entry<KT, VT> entry = (Map.Entry<KT, VT>) object;
                Object enumKey = entry.getKey();
                if (key.equals(enumKey)) {
                    Object theValue = entry.getValue();
                    isEqual = enumMap.values[ordinal] == null ? null == theValue
                            : enumMap.values[ordinal].equals(theValue);
                }
            }
            return isEqual;
        }

        @Override
        public int hashCode() {
            return (enumMap.keys[ordinal] == null ? 0 : enumMap.keys[ordinal]
                    .hashCode())
                    ^ (enumMap.values[ordinal] == null ? 0
                            : enumMap.values[ordinal].hashCode());
        }

        @SuppressWarnings("unchecked")
        @Override
        public KT getKey() {
            checkEntryStatus();
            return (KT) enumMap.keys[ordinal];
        }

        @SuppressWarnings("unchecked")
        @Override
        public VT getValue() {
            checkEntryStatus();
            return (VT) enumMap.values[ordinal];
        }

        @SuppressWarnings("unchecked")
        @Override
        public VT setValue(VT value) {
            checkEntryStatus();
            return enumMap.put((KT) enumMap.keys[ordinal], value);
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder(enumMap.keys[ordinal]
                    .toString());
            result.append("="); //$NON-NLS-1$
            result.append(enumMap.values[ordinal].toString());
            return result.toString();
        }

        private void checkEntryStatus() {
            if (!enumMap.hasMapping[ordinal]) {
                throw new IllegalStateException();
            }
        }
    }

    private static class EnumMapIterator<E, KT extends Enum<KT>, VT> implements
            Iterator<E> {
        int position = 0;

        int prePosition = -1;

        final EnumMap<KT, VT> enumMap;

        final MapEntry.Type<E, KT, VT> type;

        EnumMapIterator(MapEntry.Type<E, KT, VT> value, EnumMap<KT, VT> em) {
            enumMap = em;
            type = value;
        }

        public boolean hasNext() {
            int length = enumMap.enumSize;
            for (; position < length; position++) {
                if (enumMap.hasMapping[position]) {
                    break;
                }
            }
            return position != length;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            prePosition = position++;
            return type.get(new MapEntry(enumMap.keys[prePosition],
                    enumMap.values[prePosition]));
        }

        public void remove() {
            checkStatus();
            if (enumMap.hasMapping[prePosition]) {
                enumMap.remove(enumMap.keys[prePosition]);
            }
            prePosition = -1;
        }

        @Override
        @SuppressWarnings("unchecked")
        public String toString() {
            if (-1 == prePosition) {
                return super.toString();
            }
            return type.get(
                    new MapEntry(enumMap.keys[prePosition],
                            enumMap.values[prePosition])).toString();
        }

        private void checkStatus() {
            if (-1 == prePosition) {
                throw new IllegalStateException();
            }
        }
    }

    private static class EnumMapKeySet<KT extends Enum<KT>, VT> extends
            AbstractSet<KT> {
        private final EnumMap<KT, VT> enumMap;

        EnumMapKeySet(EnumMap<KT, VT> em) {
            enumMap = em;
        }

        @Override
        public void clear() {
            enumMap.clear();
        }

        @Override
        public boolean contains(Object object) {
            return enumMap.containsKey(object);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Iterator iterator() {
            return new EnumMapIterator<KT, KT, VT>(
                    new MapEntry.Type<KT, KT, VT>() {
                        public KT get(MapEntry<KT, VT> entry) {
                            return entry.key;
                        }
                    }, enumMap);
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean remove(Object object) {
            if (contains(object)) {
                enumMap.remove(object);
                return true;
            }
            return false;
        }

        @Override
        public int size() {
            return enumMap.size();
        }
    }

    private static class EnumMapValueCollection<KT extends Enum<KT>, VT>
            extends AbstractCollection<VT> {
        private final EnumMap<KT, VT> enumMap;

        EnumMapValueCollection(EnumMap<KT, VT> em) {
            enumMap = em;
        }

        @Override
        public void clear() {
            enumMap.clear();
        }

        @Override
        public boolean contains(Object object) {
            return enumMap.containsValue(object);
        }

        @SuppressWarnings("unchecked")
        @Override
        public Iterator iterator() {
            return new EnumMapIterator<VT, KT, VT>(
                    new MapEntry.Type<VT, KT, VT>() {
                        public VT get(MapEntry<KT, VT> entry) {
                            return entry.value;
                        }
                    }, enumMap);
        }

        @Override
        public boolean remove(Object object) {
            if (null == object) {
                for (int i = 0; i < enumMap.enumSize; i++) {
                    if (enumMap.hasMapping[i] && null == enumMap.values[i]) {
                        enumMap.remove(enumMap.keys[i]);
                        return true;
                    }
                }
            } else {
                for (int i = 0; i < enumMap.enumSize; i++) {
                    if (enumMap.hasMapping[i]
                            && object.equals(enumMap.values[i])) {
                        enumMap.remove(enumMap.keys[i]);
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public int size() {
            return enumMap.size();
        }
    }

    private static class EnumMapEntryIterator<E, KT extends Enum<KT>, VT>
            extends EnumMapIterator<E, KT, VT> {
        EnumMapEntryIterator(MapEntry.Type<E, KT, VT> value, EnumMap<KT, VT> em) {
            super(value, em);
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            prePosition = position++;
            return type.get(new Entry<KT, VT>((KT) enumMap.keys[prePosition],
                    (VT) enumMap.values[prePosition], enumMap));
        }
    }

    private static class EnumMapEntrySet<KT extends Enum<KT>, VT> extends
            AbstractSet<Map.Entry<KT, VT>> {
        private final EnumMap<KT, VT> enumMap;

        EnumMapEntrySet(EnumMap<KT, VT> em) {
            enumMap = em;
        }

        @Override
        public void clear() {
            enumMap.clear();
        }

        @Override
        public boolean contains(Object object) {
            boolean isEqual = false;
            if (object instanceof Map.Entry) {
                Object enumKey = ((Map.Entry) object).getKey();
                Object enumValue = ((Map.Entry) object).getValue();
                if (enumMap.containsKey(enumKey)) {
                    VT value = enumMap.get(enumKey);
                    isEqual = (value == null ? null == enumValue : value
                            .equals(enumValue));
                }
            }
            return isEqual;
        }

        @Override
        public Iterator<Map.Entry<KT, VT>> iterator() {
            return new EnumMapEntryIterator<Map.Entry<KT, VT>, KT, VT>(
                    new MapEntry.Type<Map.Entry<KT, VT>, KT, VT>() {
                        public Map.Entry<KT, VT> get(MapEntry<KT, VT> entry) {
                            return entry;
                        }
                    }, enumMap);
        }

        @Override
        public boolean remove(Object object) {
            if (contains(object)) {
                enumMap.remove(((Map.Entry) object).getKey());
                return true;
            }
            return false;
        }

        @Override
        public int size() {
            return enumMap.size();
        }

        @Override
        public Object[] toArray() {
            Object[] entryArray = new Object[enumMap.size()];
            return toArray(entryArray);
        }

        @SuppressWarnings("unchecked")
        @Override
        public Object[] toArray(Object[] array) {
            int size = enumMap.size();
            int index = 0;
            Object[] entryArray = array;
            if (size > array.length) {
                Class<?> clazz = array.getClass().getComponentType();
                entryArray = (Object[]) Array.newInstance(clazz, size);
            }
            Iterator<Map.Entry<KT, VT>> iter = iterator();
            for (; index < size; index++) {
                Map.Entry<KT, VT> entry = iter.next();
                entryArray[index] = new MapEntry<KT, VT>(entry.getKey(), entry
                        .getValue());
            }
            if (index < array.length) {
                entryArray[index] = null;
            }
            return entryArray;
        }
    }

    /**
     * Constructs an empty EnumMap.
     * 
     * @param keyType
     *           the Class that is to be used for the key type for this map
     * @throws NullPointerException
     *             if the keyType is null
     */
    public EnumMap(Class<K> keyType) {
        initialization(keyType);
    }

    /**
     * Constructs an EnumMap using the same key type and contents as the given
     * EnumMap.
     * 
     * @param map
     *            the EnumMap from which the initial contents of this EnumMap
     *            are copied
     * @throws NullPointerException
     *             if the map is null
     */
    public EnumMap(EnumMap<K, ? extends V> map) {
        initialization(map);
    }

    /**
     * Constructs an EnumMap with the same contents as the given Map. If the Map
     * is an EnumMap, this is equivalent to calling
     * {@link EnumMap#EnumMap(EnumMap)}}. Otherwise, the given map cannot be
     * empty so that the key type of this EnumMap can be inferred.
     * 
     * @param map
     *            the Map from which the initial contents of this EnumMap are
     *            copied
     * @throws IllegalArgumentException
     *             if the map is empty and is not of type <code>EnumMap</code>
     * @throws NullPointerException
     *             if the map is null
     */
    @SuppressWarnings("unchecked")
    public EnumMap(Map<K, ? extends V> map) {
        if (map instanceof EnumMap) {
            initialization((EnumMap<K, V>) map);
        } else {
            if (0 == map.size()) {
                throw new IllegalArgumentException();
            }
            Iterator<K> iter = map.keySet().iterator();
            K enumKey = iter.next();
            Class clazz = enumKey.getClass();
            if (clazz.isEnum()) {
                initialization(clazz);
            } else {
                initialization(clazz.getSuperclass());
            }
            putAllImpl(map);
        }
    }

    /**
     * Clears this map.
     */
    @Override
    public void clear() {
        Arrays.fill(values, null);
        Arrays.fill(hasMapping, false);
        mappingsCount = 0;
    }

    /**
     * Clones this map to create a shallow copy.
     * 
     * @return a shallow copy of this map
     */
    @SuppressWarnings("unchecked")
    @Override
    public EnumMap<K, V> clone() {
        try {
            EnumMap<K, V> enumMap = (EnumMap<K, V>) super.clone();
            enumMap.initialization(this);
            return enumMap;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * Returns true if the given object is present as a key in this map.
     * 
     * @param key
     *            the key to look for
     * @return true if this map contains the key
     */
    @Override
    public boolean containsKey(Object key) {
        if (isValidKeyType(key)) {
            int keyOrdinal = ((Enum) key).ordinal();
            return hasMapping[keyOrdinal];
        }
        return false;
    }

    /**
     * Returns true if the given object is present as a value in this map.
     * 
     * @param value
     *            the value to look for
     * @return true if this map contains the value.
     */
    @Override
    public boolean containsValue(Object value) {
        if (null == value) {
            for (int i = 0; i < enumSize; i++) {
                if (hasMapping[i] && null == values[i]) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < enumSize; i++) {
                if (hasMapping[i] && value.equals(values[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a Set of <code>Map.Entry</code>s that represent the entries in
     * this EnumMap. Making changes to this Set will change the original EnumMap
     * and vice-versa. Entries can be removed from the Set, or their values can
     * be changed, but new entries cannot be added.
     * 
     * The order of the entries in the Set will be the order that the Enum keys
     * were declared in.
     * 
     * @return a Set of <code>Map.Entry</code>s representing the entries in
     *         this EnumMap
     */
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        if (null == entrySet) {
            entrySet = new EnumMapEntrySet<K, V>(this);
        }
        return entrySet;
    }

    /**
     * Returns true if this EnumMap is equal to the given object.
     * 
     * @param object
     *            the object
     * @return true if this EnumMap is equal to the given object.
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof EnumMap)) {
            return super.equals(object);
        }
        EnumMap<K, V> enumMap = (EnumMap<K, V>) object;
        if (keyType != enumMap.keyType || size() != enumMap.size()) {
            return false;
        }
        return Arrays.equals(hasMapping, enumMap.hasMapping)
                && Arrays.equals(values, enumMap.values);
    }

    /**
     * Returns the value stored in this map for the given key in this map, or null
     * if this map has no entry for that key.
     * 
     * @param key
     *            the key to get the value for.
     * @return the value for the given key.
     */
    @Override
    @SuppressWarnings("unchecked")
    public V get(Object key) {
        if (!isValidKeyType(key)) {
            return null;
        }
        int keyOrdinal = ((Enum) key).ordinal();
        return (V) values[keyOrdinal];
    }

    /**
     * Returns a Set containing the keys for this EnumMap. Making changes to
     * this Set will change the original EnumMap and vice-versa. Entries can be
     * removed from the Set, but new entries cannot be added.
     * 
     * The order of the Set will be the order that the Enum keys were declared
     * in.
     * 
     * @return a Set containing the keys for this EnumMap.
     */
    @Override
    public Set<K> keySet() {
        if (null == keySet) {
            keySet = new EnumMapKeySet<K, V>(this);
        }
        return keySet;
    }

    /**
     * Stores a value in this map for the given key. If the map already has an
     * entry for this key the current value will be overwritten.
     * 
     * @param key
     *            the key
     * @param value
     *            the value to store for the given key
     * @return the value stored for the given key, or null if this map has no
     *         entry for the key
     * @throws NullPointerException
     *             if the key is null
     */
    @Override
    @SuppressWarnings("unchecked")
    public V put(K key, V value) {
        return putImpl(key, value);
    }

    /**
     * Add all the entries in the given map to this map
     * 
     * @param map
     *            the map whose entries to copy
     * @throws NullPointerException
     *             if the given map or any of its keys are null
     */
    @Override
    @SuppressWarnings("unchecked")
    public void putAll(Map<? extends K, ? extends V> map) {
        putAllImpl(map);
    }

    /**
     * Removes the entry for the given key from this map, if there is one.
     * 
     * @param key
     *            the key to remove
     * @return the value that had been stored for the key, or null if there was
     *         not one.
     */
    @Override
    @SuppressWarnings("unchecked")
    public V remove(Object key) {
        if (!isValidKeyType(key)) {
            return null;
        }
        int keyOrdinal = ((Enum) key).ordinal();
        if (hasMapping[keyOrdinal]) {
            hasMapping[keyOrdinal] = false;
            mappingsCount--;
        }
        V oldValue = (V) values[keyOrdinal];
        values[keyOrdinal] = null;
        return oldValue;
    }

    /**
     * Returns the size of this map
     * 
     * @return the number of entries in the map
     */
    @Override
    public int size() {
        return mappingsCount;
    }

    /**
     * Returns a Collection containing the values for this EnumMap. Making
     * changes to this Collection will change the original EnumMap and
     * vice-versa. Values can be removed from the Collection, but new entries
     * cannot be added.
     * 
     * The order of the values in the Collection will be the order that their
     * corresponding Enum keys were declared in.
     * 
     * @return a Collection containing the values for this EnumMap
     */
    @Override
    public Collection<V> values() {
        if (null == valuesCollection) {
            valuesCollection = new EnumMapValueCollection<K, V>(this);
        }
        return valuesCollection;
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        stream.defaultReadObject();
        initialization(keyType);
        int elementCount = stream.readInt();
        Enum<K> enumKey;
        Object value;
        for (int i = elementCount; i > 0; i--) {
            enumKey = (Enum<K>) stream.readObject();
            value = stream.readObject();
            putImpl((K) enumKey, (V) value);
        }
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(mappingsCount);
        Iterator<Map.Entry<K, V>> iterator = entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<K, V> entry = iterator.next();
            stream.writeObject(entry.getKey());
            stream.writeObject(entry.getValue());
        }
    }

    private boolean isValidKeyType(Object key) {
        if (null != key && keyType.isInstance(key)) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private void initialization(EnumMap enumMap) {
        keyType = enumMap.keyType;
        keys = enumMap.keys;
        enumSize = enumMap.enumSize;
        values = enumMap.values.clone();
        hasMapping = enumMap.hasMapping.clone();
        mappingsCount = enumMap.mappingsCount;
    }

    private void initialization(Class<K> type) {
        keyType = type;
        keys = keyType.getEnumConstants();
        enumSize = keys.length;
        values = new Object[enumSize];
        hasMapping = new boolean[enumSize];
    }

    @SuppressWarnings("unchecked")
    private void putAllImpl(Map map) {
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            putImpl((K) entry.getKey(), (V) entry.getValue());
        }
    }

    @SuppressWarnings("unchecked")
    private V putImpl(K key, V value) {
        if (null == key) {
            throw new NullPointerException();
        }
        if (!isValidKeyType(key)) {
            throw new ClassCastException();
        }
        int keyOrdinal = key.ordinal();
        if (!hasMapping[keyOrdinal]) {
            hasMapping[keyOrdinal] = true;
            mappingsCount++;
        }
        V oldValue = (V) values[keyOrdinal];
        values[keyOrdinal] = value;
        return oldValue;
    }

}
