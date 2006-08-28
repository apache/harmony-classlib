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

import java.io.Serializable;

import org.apache.harmony.luni.util.NotYetImplementedException;

public class EnumMap<K extends Enum<K>, V> extends AbstractMap<K, V> implements
        Map<K, V>, Serializable, Cloneable {
    private static final long serialVersionUID = 458661240069192865L;

    Class<K> keyType;

    transient Enum keys[];

    transient Object values[];

    transient boolean hasMapping[];

    transient int mappingsCount;

    transient int enumSize;

    private static class EnumMapIterator<E, KT extends Enum<KT>, VT> implements
            Iterator<E> {
        private int position = 0;

        private int prePosition = -1;

        private final EnumMap<KT, VT> enumMap;

        private final MapEntry.Type<E, KT, VT> type;

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

        void checkStatus() {
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

    private static class EnumMapValueCollection<KT extends Enum<KT>, VT> extends
            AbstractCollection<VT> {
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

    /**
     * Constructs an empty enum map using the given key type.
     * 
     * @param keyType
     *            the class object of the key type used by this enum map
     * @throws NullPointerException
     *             if the keyType is null
     */
    public EnumMap(Class<K> keyType) {
        initialization(keyType);
    }

    /**
     * Constructs an enum map using the same key type as the given enum map and
     * initially containing the same mappings.
     * 
     * @param map
     *            the enum map from which this enum map is initialized
     * @throws NullPointerException
     *             if the map is null
     */
    public EnumMap(EnumMap<K, ? extends V> map) {
        initialization(map);
    }

    /**
     * Constructs an enum map initialized from the given map. If the given map
     * is an EnumMap instance, this constructor behaves in the exactly the same
     * way as {@link EnumMap#EnumMap(EnumMap)}}. Otherwise, the given map at
     * least should contain one mapping.
     * 
     * @param map
     *            the map from which this enum map is initialized
     * @throws IllegalArgumentException
     *             if the map is not an enum map instance and does not contain
     *             any mappings
     * @throws NullPointerException
     *             if the map is null
     */
    public EnumMap(Map<K, ? extends V> map) {
        if (map instanceof EnumMap) {
            initialization((EnumMap<K, V>) map);
        } else {
            if (0 == map.size()) {
                throw new IllegalArgumentException();
            }
            Iterator<K> iter = map.keySet().iterator();
            K enumKey = iter.next();
            Class clazz=enumKey.getClass();
            if(clazz.isEnum()){
                initialization(clazz);
            }else{
                initialization(clazz.getSuperclass());
            }
            putAllImpl(map);
        }
    }

    /**
     * Removes all mappings in this map.
     */
    @Override
    public void clear() {
        Arrays.fill(values, null);
        Arrays.fill(hasMapping, false);
        mappingsCount = 0;
    }

    /**
     * Answers a shallow copy of this map.
     * 
     * @return a shallow copy of this map
     */
    public EnumMap<K,V> clone() {
        throw new NotYetImplementedException();
    }

    /**
     * Answers true if this map has a mapping for the given key.
     * 
     * @param key
     *            the key whose presence in this map is to be tested
     * @return true if this map has a mapping for the given key.
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
     * Answers true if this map has one or more keys mapped to the given value.
     * 
     * @param value
     *            the value whose presence in this map is to be tested
     * @return true if this map has one or more keys mapped to the given value.
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
     * Answers a {@link Set}} view of the mappings contained in this map. The
     * returned set complies with the general rule specified in
     * {@link Map#entrySet()}}. The set's iterator will return the mappings in
     * the their keys' natural order(the enum constants are declared in this
     * order)
     * 
     * @return a set view of the mappings contained in this map.
     */
    public Set<Map.Entry<K, V>> entrySet() {
        throw new NotYetImplementedException();
    }

    /**
     * Compares the given object with this map. Answers true if the given object
     * is equal to this map.
     * 
     * @param object
     *            the object to be compared with this map
     * @return true if the given object is equal to this map.
     */
    public boolean equals(Object object) {
        throw new NotYetImplementedException();
    }

    /**
     * Answers the value which is mapped to the given key in this map, or null
     * if this map has no mapping for the given key.
     * 
     * @param key
     *            the key whose associated value is to be returned
     * @return the value to which this map maps the given key, or null if this
     *         map has no mapping for the given key.
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
     * Answers a {@link Set}} view of the keys contained in this map. The
     * returned set complies with the general rule specified in
     * {@link Map#keySet()}}. The set's iterator will return the keys in the
     * their natural order(the enum constants are declared in this order)
     * 
     * @return a set view of the keys contained in this map.
     */
    @Override
    public Set<K> keySet() {
        if (null == keySet) {
            keySet = new EnumMapKeySet<K, V>(this);
        }
        return keySet;
    }

    /**
     * Associates the given value with the given key in this map. If the map
     * previously had a mapping for this key, the old value is replaced.
     * 
     * @param key
     *            the key with which the given value is to be associated value
     * @param value
     *            the value to be associated with the given key
     * @return the value to which this map maps the given key, or null if this
     *         map has no mapping for the given key.
     * @throws NullPointerException
     *             if the given key is null
     */
    @Override
    @SuppressWarnings("unchecked")
    public V put(K key, V value) {
        return putImpl(key,value);
    }

    /**
     * Copies all the mappings in the given map to this map. These mappings will
     * replace all mappings that this map had for all of the keys currently in
     * the given map.
     * 
     * @param map
     *            the key whose presence in this map is to be tested
     * @throws NullPointerException
     *             if the given map is null, or if one or more keys in the given
     *             map are null
     */
    @Override
    @SuppressWarnings("unchecked")
    public void putAll(Map<? extends K, ? extends V> map) {
        putAllImpl(map);
    }

    /**
     * Removes the mapping for this key from this map if it is present.
     * 
     * @param key
     *            the key whose mapping is to be removed from this map
     * @return the previous value associated with the given key, or null if this
     *         map has no mapping for this key.
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
     * Answers the number of the mappings in this map.
     * 
     * @return the number of the mappings in this map
     */
    @Override
    public int size() {
        return mappingsCount;
    }

    /**
     * Answers a {@link Collection}} view of the values contained in this map.
     * The returned collection complys with the general rule specified in
     * {@link Map#values()}}. The collection's iterator will return the values
     * in the their corresponding keys' natural order(the enum constants are
     * declared in this order)
     * 
     * @return a collection view of the mappings contained in this map.
     */
    @Override
    public Collection<V> values() {
        if (null == valuesCollection) {
            valuesCollection = new EnumMapValueCollection<K, V>(this);
        }
        return valuesCollection;
    }

    private boolean isValidKeyType(Object key) {
        if (null != key && keyType.isInstance(key)) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private void initialization(EnumMap enumMap){
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
    private void putAllImpl(Map map){
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
