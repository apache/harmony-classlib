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

package java.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * HashMap is an implementation of Map. All optional operations are supported,
 * adding and removing. Keys and values can be any objects.
 */
public class HashMap extends AbstractMap implements Map, Cloneable,
		Serializable {
	private static final long serialVersionUID = 362498820763181265L;

	transient int elementCount;

	transient Entry[] elementData;

	final float loadFactor;

	int threshold;

	transient int modCount = 0;

	private static final int DEFAULT_SIZE = 16;

	static class Entry extends MapEntry {
		final int hash;

		Entry next;

		Entry(Object theKey, Object theValue) {
			super(theKey, theValue);
			this.hash = (theKey == null) ? 0 : theKey.hashCode();
		}

		public Object clone() {
			Entry entry = (Entry) super.clone();
			if (next != null)
				entry.next = (Entry) next.clone();
			return entry;
		}

		public String toString() {
			return key + "=" + value;
		}

		public int hashCode() {
			return hash;
		}
	}

	static class HashMapIterator implements Iterator {
		private int position = 0;

		int expectedModCount;

		final MapEntry.Type type;

		boolean canRemove = false;

		Entry entry;

		Entry lastEntry;

		final HashMap associatedMap;

		HashMapIterator(MapEntry.Type value, HashMap hm) {
			associatedMap = hm;
			type = value;
			expectedModCount = hm.modCount;
		}

		public boolean hasNext() {
			if (entry != null)
				return true;
			while (position < associatedMap.elementData.length) {
				if (associatedMap.elementData[position] == null)
					position++;
				else
					return true;
			}
			return false;
		}

		void checkConcurrentMod() throws ConcurrentModificationException {
			if (expectedModCount != associatedMap.modCount)
				throw new ConcurrentModificationException();
		}

		public Object next() {
			checkConcurrentMod();
			if (!hasNext())
				throw new NoSuchElementException();

			Entry result;
			if (entry == null) {
				result = lastEntry = associatedMap.elementData[position++];
				entry = lastEntry.next;
			} else {
				if (lastEntry.next != entry)
					lastEntry = lastEntry.next;
				result = entry;
				entry = entry.next;
			}
			canRemove = true;
			return type.get(result);
		}

		public void remove() {
			checkConcurrentMod();
			if (!canRemove)
				throw new IllegalStateException();

			canRemove = false;
			associatedMap.modCount++;
			if (lastEntry.next == entry) {
				while (associatedMap.elementData[--position] == null)
					;
				associatedMap.elementData[position] = associatedMap.elementData[position].next;
				entry = null;
			} else
				lastEntry.next = entry;
			associatedMap.elementCount--;
			expectedModCount++;
		}
	}

	static class HashMapEntrySet extends AbstractSet {
		private final HashMap associatedMap;

		public HashMapEntrySet(HashMap hm) {
			associatedMap = hm;
		}

		HashMap hashMap() {
			return associatedMap;
		}

		public int size() {
			return associatedMap.elementCount;
		}

		public void clear() {
			associatedMap.clear();
		}

		public boolean remove(Object object) {
			if (contains(object)) {
				associatedMap.remove(((Map.Entry) object).getKey());
				return true;
			}
			return false;
		}

		public boolean contains(Object object) {
			if (object instanceof Map.Entry) {
				Entry entry = associatedMap.getEntry(((Map.Entry) object)
						.getKey());
				return object.equals(entry);
			}
			return false;
		}

		public Iterator iterator() {
			return new HashMapIterator(new MapEntry.Type() {
				public Object get(MapEntry entry) {
					return entry;
				}
			}, associatedMap);
		}
	}

	/**
	 * Create a new element array
	 * 
	 * @param s
	 * @return Reference to the element array
	 */
	Entry[] newElementArray(int s) {
		return new Entry[s];
	}

	/**
	 * Contructs a new empty instance of HashMap.
	 * 
	 */
	public HashMap() {
		this(DEFAULT_SIZE);
	}

	/**
	 * Constructs a new instance of HashMap with the specified capacity.
	 * 
	 * @param capacity
	 *            the initial capacity of this HashMap
	 * 
	 * @exception IllegalArgumentException
	 *                when the capacity is less than zero
	 */
	public HashMap(int capacity) {
		if (capacity >= 0) {
			elementCount = 0;
			elementData = newElementArray(capacity == 0 ? 1 : capacity);
			loadFactor = 0.75f; // Default load factor of 0.75
			computeMaxSize();
		} else
			throw new IllegalArgumentException();
	}

	/**
	 * Constructs a new instance of HashMap with the specified capacity and load
	 * factor.
	 * 
	 * 
	 * @param capacity
	 *            the initial capacity
	 * @param loadFactor
	 *            the initial load factor
	 * 
	 * @exception IllegalArgumentException
	 *                when the capacity is less than zero or the load factor is
	 *                less or equal to zero
	 */
	public HashMap(int capacity, float loadFactor) {
		if (capacity >= 0 && loadFactor > 0) {
			elementCount = 0;
			elementData = newElementArray(capacity == 0 ? 1 : capacity);
			this.loadFactor = loadFactor;
			computeMaxSize();
		} else
			throw new IllegalArgumentException();
	}

	/**
	 * Constructs a new instance of HashMap containing the mappings from the
	 * specified Map.
	 * 
	 * @param map
	 *            the mappings to add
	 */
	public HashMap(Map map) {
		this(map.size() < 6 ? 11 : map.size() * 2);
		putAll(map);
	}

	/**
	 * Removes all mappings from this HashMap, leaving it empty.
	 * 
	 * @see #isEmpty
	 * @see #size
	 */
	public void clear() {
		if (elementCount > 0) {
			elementCount = 0;
			Arrays.fill(elementData, null);
			modCount++;
		}
	}

	/**
	 * Answers a new HashMap with the same mappings and size as this HashMap.
	 * 
	 * @return a shallow copy of this HashMap
	 * 
	 * @see java.lang.Cloneable
	 */
	public Object clone() {
		try {
			HashMap map = (HashMap) super.clone();
			map.elementData = newElementArray(elementData.length);
			Entry entry;
			for (int i = 0; i < elementData.length; i++) {
				if ((entry = elementData[i]) != null)
					map.elementData[i] = (Entry) entry.clone();
			}
			return map;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	private void computeMaxSize() {
		threshold = (int) (elementData.length * loadFactor);
	}

	/**
	 * Searches this HashMap for the specified key.
	 * 
	 * @param key
	 *            the object to search for
	 * @return true if <code>key</code> is a key of this HashMap, false
	 *         otherwise
	 */
	public boolean containsKey(Object key) {
		return getEntry(key) != null;
	}

	/**
	 * Tests two keys for equality. This method just calls key.equals but can be
	 * overridden.
	 * 
	 * @param k1
	 *            first key to compare
	 * @param k2
	 *            second key to compare
	 * @return true iff the keys are considered equal
	 */
	boolean keysEqual(Object k1, Entry entry) {
		return entry.hashCode() == k1.hashCode() && k1.equals(entry.key);
	}

	/**
	 * Searches this HashMap for the specified value.
	 * 
	 * @param value
	 *            the object to search for
	 * @return true if <code>value</code> is a value of this HashMap, false
	 *         otherwise
	 */
	public boolean containsValue(Object value) {
		if (value != null) {
			for (int i = elementData.length; --i >= 0;) {
				Entry entry = elementData[i];
				while (entry != null) {
					if (value.equals(entry.value))
						return true;
					entry = entry.next;
				}
			}
		} else {
			for (int i = elementData.length; --i >= 0;) {
				Entry entry = elementData[i];
				while (entry != null) {
					if (entry.value == null)
						return true;
					entry = entry.next;
				}
			}
		}
		return false;
	}

	/**
	 * Answers a Set of the mappings contained in this HashMap. Each element in
	 * the set is a Map.Entry. The set is backed by this HashMap so changes to
	 * one are relected by the other. The set does not support adding.
	 * 
	 * @return a Set of the mappings
	 */
	public Set entrySet() {
		return new HashMapEntrySet(this);
	}

	/**
	 * Answers the value of the mapping with the specified key.
	 * 
	 * @param key
	 *            the key
	 * @return the value of the mapping with the specified key
	 */
	public Object get(Object key) {
		Entry m = getEntry(key);
		if (m != null) {
			return m.value;
		}
		return null;
	}

	Entry getEntry(Object key) {
		int index = getModuloHash(key);
		return findEntry(key, index);
	}

	int getModuloHash(Object key) {
		if (key == null)
			return 0;
		return (key.hashCode() & 0x7FFFFFFF) % elementData.length;
	}

	Entry findEntry(Object key, int index) {
		Entry m;
		m = elementData[index];
		if (key != null) {
			while(m != null && !keysEqual(key,m)){
				m = m.next;
			}
		} else {
			while (m != null && m.key != null)
				m = m.next;
		}
		return m;
	}

	/**
	 * Answers if this HashMap has no elements, a size of zero.
	 * 
	 * @return true if this HashMap has no elements, false otherwise
	 * 
	 * @see #size
	 */
	public boolean isEmpty() {
		return elementCount == 0;
	}

	/**
	 * Answers a Set of the keys contained in this HashMap. The set is backed by
	 * this HashMap so changes to one are relected by the other. The set does
	 * not support adding.
	 * 
	 * @return a Set of the keys
	 */
	public Set keySet() {
		if (keySet == null) {
			keySet = new AbstractSet() {
				public boolean contains(Object object) {
					return containsKey(object);
				}

				public int size() {
					return HashMap.this.size();
				}

				public void clear() {
					HashMap.this.clear();
				}

				public boolean remove(Object key) {
					if (containsKey(key)) {
						HashMap.this.remove(key);
						return true;
					}
					return false;
				}

				public Iterator iterator() {
					return new HashMapIterator(new MapEntry.Type() {
						public Object get(MapEntry entry) {
							return entry.key;
						}
					}, HashMap.this);
				}
			};
		}
		return keySet;
	}

	/**
	 * Maps the specified key to the specified value.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return the value of any previous mapping with the specified key or null
	 *         if there was no mapping
	 */
	public Object put(Object key, Object value) {
		int index = getModuloHash(key);
		Entry entry = findEntry(key, index);

		if (entry == null) {
			modCount++;
			if (++elementCount > threshold) {
				rehash();
				index = key == null ? 0 : (key.hashCode() & 0x7FFFFFFF)
						% elementData.length;
			}
			entry = createEntry(key, index, null);
		}
		Object result = entry.value;
		entry.value = value;
		return result;
	}

	Entry createEntry(Object key, int index, Object value) {
		Entry entry = new Entry(key, value);
		entry.next = elementData[index];
		elementData[index] = entry;
		return entry;
	}

	/**
	 * Copies every mapping in the specified Map to this HashMap.
	 * 
	 * @param map
	 *            the Map to copy mappings from
	 */
	public void putAll(Map map) {
		super.putAll(map);
	}

	void rehash() {
		int length = elementData.length << 1;
		if (length == 0)
			length = 1;
		Entry[] newData = newElementArray(length);
		for (int i = 0; i < elementData.length; i++) {
			Entry entry = elementData[i];
			while (entry != null) {
				Object key = entry.key;
				int index = key == null ? 0 : (key.hashCode() & 0x7FFFFFFF)
						% length;
				Entry next = entry.next;
				entry.next = newData[index];
				newData[index] = entry;
				entry = next;
			}
		}
		elementData = newData;
		computeMaxSize();
	}

	/**
	 * Removes a mapping with the specified key from this HashMap.
	 * 
	 * @param key
	 *            the key of the mapping to remove
	 * @return the value of the removed mapping or null if key is not a key in
	 *         this HashMap
	 */
	public Object remove(Object key) {
		Entry entry = removeEntry(key);
		if (entry != null) {
			return entry.value;
		}
		return null;
	}

	Entry removeEntry(Object key) {
		int index = 0;
		Entry entry;
		Entry last = null;
		if (key != null) {
			index = (key.hashCode() & 0x7FFFFFFF) % elementData.length;
			entry = elementData[index];
			while (entry != null && !keysEqual(key, entry)) {
				last = entry;
				entry = entry.next;
			}
		} else {
			entry = elementData[0];
			while (entry != null && entry.key != null) {
				last = entry;
				entry = entry.next;
			}
		}
		if (entry == null)
			return null;
		if (last == null)
			elementData[index] = entry.next;
		else
			last.next = entry.next;
		modCount++;
		elementCount--;
		return entry;
	}

	/**
	 * Answers the number of mappings in this HashMap.
	 * 
	 * @return the number of mappings in this HashMap
	 */
	public int size() {
		return elementCount;
	}

	/**
	 * Answers a Collection of the values contained in this HashMap. The
	 * collection is backed by this HashMap so changes to one are relected by
	 * the other. The collection does not support adding.
	 * 
	 * @return a Collection of the values
	 */
	public Collection values() {
		if (valuesCollection == null) {
			valuesCollection = new AbstractCollection() {
				public boolean contains(Object object) {
					return containsValue(object);
				}

				public int size() {
					return HashMap.this.size();
				}

				public void clear() {
					HashMap.this.clear();
				}

				public Iterator iterator() {
					return new HashMapIterator(new MapEntry.Type() {
						public Object get(MapEntry entry) {
							return entry.value;
						}
					}, HashMap.this);
				}
			};
		}
		return valuesCollection;
	}

	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
		stream.writeInt(elementData.length);
		stream.writeInt(elementCount);
		Iterator iterator = entrySet().iterator();
		while (iterator.hasNext()) {
			Entry entry = (Entry) iterator.next();
			stream.writeObject(entry.key);
			stream.writeObject(entry.value);
			entry = entry.next;
		}
	}

	private void readObject(ObjectInputStream stream) throws IOException,
			ClassNotFoundException {
		stream.defaultReadObject();
		int length = stream.readInt();
		elementData = new Entry[length];
		elementCount = stream.readInt();
		for (int i = elementCount; --i >= 0;) {
			Object key = stream.readObject();
			int index = (key.hashCode() & 0x7FFFFFFF) % length;
			createEntry(key, index, stream.readObject());
		}
	}
}
