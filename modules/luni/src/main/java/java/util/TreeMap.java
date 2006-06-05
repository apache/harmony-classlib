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
 * TreeMap is an implementation of SortedMap. All optional operations are
 * supported, adding and removing. The values can be any objects. The keys can
 * be any objects which are comparable to each other either using their natural
 * order or a specified Comparator.
 * @since 1.2
 */

public class TreeMap<K, V> extends AbstractMap<K, V> implements SortedMap<K, V>, Cloneable,
		Serializable {
	private static final long serialVersionUID = 919286545866124006L;

	transient int size = 0;

	transient Entry<K, V> root;

	private Comparator<? super K> comparator;

	transient int modCount = 0;

	/**
	 * Entry is an internal class which is used to hold the entries of a
	 * TreeMap.
	 */
	static class Entry<K, V> extends MapEntry<K, V> {
		Entry<K, V> parent, left, right;

		boolean color;

		Entry(K key) {
			super(key);
		}

		Entry(K key, V value) {
			super(key, value);
		}

		Entry<K, V> clone(Entry<K, V> parent) {
			Entry<K, V> clone = (Entry<K, V>) super.clone();
			clone.parent = parent;
			if (left != null)
				clone.left = left.clone(clone);
			if (right != null)
				clone.right = right.clone(clone);
			return clone;
		}
	}

	private static final class TreeMapIterator<E, K, V> implements Iterator<E> {
		private final TreeMap<K, V> backingMap;

		private int expectedModCount;

		private final MapEntry.Type<E, K, V> type;

		private boolean hasEnd = false;

		private Entry<K, V> node, lastNode;

		private K endKey;

		TreeMapIterator(TreeMap<K, V> map, MapEntry.Type<E, K, V> value) {
			backingMap = map;
			type = value;
			expectedModCount = map.modCount;
			if (map.root != null)
				node = TreeMap.minimum(map.root);
		}

		TreeMapIterator(TreeMap<K, V> map, MapEntry.Type<E, K, V> value, Entry<K, V> startNode,
				boolean checkEnd, K end) {
			backingMap = map;
			type = value;
			expectedModCount = map.modCount;
			node = startNode;
			hasEnd = checkEnd;
			endKey = end;
		}

		public boolean hasNext() {
			return node != null;
		}

		public E next() {
			if (expectedModCount == backingMap.modCount) {
				if (node != null) {
					lastNode = node;
					node = TreeMap.successor(node);
					if (hasEnd && node != null) {
						Comparator<? super K> c = backingMap.comparator();
						if (c == null) {
							if (((Comparable<K>)endKey).compareTo(node.key) <= 0)
								node = null;
						} else {
							if (c.compare(endKey, node.key) <= 0)
								node = null;
						}
					}
					return type.get(lastNode);
				} else
					throw new NoSuchElementException();
			} else
				throw new ConcurrentModificationException();
		}

		public void remove() {
			if (expectedModCount == backingMap.modCount) {
				if (lastNode != null) {
					backingMap.rbDelete(lastNode);
					lastNode = null;
					expectedModCount++;
				} else
					throw new IllegalStateException();
			} else
				throw new ConcurrentModificationException();
		}
	}

	static final class SubMap<K, V> extends AbstractMap<K, V> implements SortedMap<K, V> {
		private final TreeMap<K, V> backingMap;

		private boolean hasStart, hasEnd;

		private K startKey, endKey;

		static class SubMapSet<E, K, V> extends AbstractSet<E> implements Set<E> {
			final TreeMap<K, V> backingMap;

			boolean hasStart, hasEnd;

			K startKey, endKey;

			final MapEntry.Type<E, K, V> type;

			SubMapSet(TreeMap<K, V> map, MapEntry.Type<E, K, V> theType) {
				backingMap = map;
				type = theType;
			}

			SubMapSet(boolean starting, K start, TreeMap<K, V> map,
					boolean ending, K end, MapEntry.Type<E, K, V> theType) {
				this(map, theType);
				hasStart = starting;
				startKey = start;
				hasEnd = ending;
				endKey = end;
			}

			void checkRange(K key) {
				if (backingMap.comparator() == null) {
					Comparable<K> object = (Comparable<K>) key;
					if (hasStart && object.compareTo(startKey) < 0)
						throw new IllegalArgumentException();
					if (hasEnd && object.compareTo(endKey) >= 0)
						throw new IllegalArgumentException();
				} else {
					if (hasStart
							&& backingMap.comparator().compare(key, startKey) < 0)
						throw new IllegalArgumentException();
					if (hasEnd
							&& backingMap.comparator().compare(key, endKey) >= 0)
						throw new IllegalArgumentException();
				}
			}

			boolean checkRange(K key, boolean hasStart, boolean hasEnd) {
				if (backingMap.comparator() == null) {
                    Comparable<K> object = (Comparable<K>) key;
					if (hasStart && object.compareTo(startKey) < 0)
						return false;
					if (hasEnd && object.compareTo(endKey) >= 0)
						return false;
				} else {
					if (hasStart
							&& backingMap.comparator().compare(key, startKey) < 0)
						return false;
					if (hasEnd
							&& backingMap.comparator().compare(key, endKey) >= 0)
						return false;
				}
				return true;
			}

			public boolean isEmpty() {
				if (hasStart) {
					TreeMap.Entry<K, V> node = backingMap.findAfter(startKey);
					return node == null || !checkRange(node.key, false, hasEnd);
				}
				return backingMap.findBefore(endKey) == null;
			}

			public Iterator<E> iterator() {
				TreeMap.Entry<K, V> startNode;
				if (hasStart) {
					startNode = backingMap.findAfter(startKey);
					if (startNode != null
							&& !checkRange(startNode.key, false, hasEnd))
						startNode = null;
				} else {
					startNode = backingMap.findBefore(endKey);
					if (startNode != null)
						startNode = minimum(backingMap.root);
				}
				return new TreeMapIterator<E, K, V>(backingMap, type, startNode, hasEnd,
						endKey);
			}

			public int size() {
				int size = 0;
				Iterator<E> it = iterator();
				while (it.hasNext()) {
					size++;
					it.next();
				}
				return size;
			}
		}

        SubMap(K start, TreeMap<K, V> map, K end) {
            backingMap = map;
            hasStart = hasEnd = true;
            startKey = start;
            endKey = end;
        }
        
		SubMap(K start, TreeMap<K, V> map) {
			backingMap = map;
			hasStart = true;
			startKey = start;
		}

		SubMap(TreeMap<K, V> map, K end) {
			backingMap = map;
			hasEnd = true;
			endKey = end;
		}

		private void checkRange(K key) {
			if (backingMap.comparator() == null) {
				Comparable<K> object = (Comparable<K>) key;
				if (hasStart && object.compareTo(startKey) < 0)
					throw new IllegalArgumentException();
				if (hasEnd && object.compareTo(endKey) >= 0)
					throw new IllegalArgumentException();
			} else {
				if (hasStart
						&& backingMap.comparator().compare(key, startKey) < 0)
					throw new IllegalArgumentException();
				if (hasEnd && backingMap.comparator().compare(key, endKey) >= 0)
					throw new IllegalArgumentException();
			}
		}

		private boolean checkRange(K key, boolean hasStart, boolean hasEnd) {
			if (backingMap.comparator() == null) {
                Comparable<K> object = (Comparable<K>) key;
				if (hasStart && object.compareTo(startKey) < 0)
					return false;
				if (hasEnd && object.compareTo(endKey) >= 0)
					return false;
			} else {
				if (hasStart
						&& backingMap.comparator().compare(key, startKey) < 0)
					return false;
				if (hasEnd && backingMap.comparator().compare(key, endKey) >= 0)
					return false;
			}
			return true;
		}

		public Comparator<? super K> comparator() {
			return backingMap.comparator();
		}

		public boolean containsKey(Object key) {
			if (checkRange((K)key, hasStart, hasEnd))
				return backingMap.containsKey(key);
			return false;
		}

		public Set<Map.Entry<K, V>> entrySet() {
			return new SubMapSet<Map.Entry<K, V>, K, V>(hasStart, startKey, backingMap, hasEnd,
					endKey, new MapEntry.Type<Map.Entry<K, V>, K, V>() {
						public Map.Entry<K, V> get(MapEntry<K, V> entry) {
							return entry;
						}
					}) {
				public boolean contains(Object object) {
					if (object instanceof Map.Entry) {
						Map.Entry<K, V> entry = (Map.Entry<K, V>) object;
						Object v1 = get(entry.getKey()), v2 = entry.getValue();
						return v1 == null ? v2 == null : v1.equals(v2);
					}
					return false;
				}
			};
		}

		public K firstKey() {
			if (!hasStart)
				return backingMap.firstKey();
			TreeMap.Entry<K, V> node = backingMap.findAfter(startKey);
			if (node != null && checkRange(node.key, false, hasEnd))
				return node.key;
			throw new NoSuchElementException();
		}

		public V get(Object key) {
			if (checkRange((K)key, hasStart, hasEnd))
				return backingMap.get(key);
			return null;
		}

		public SortedMap<K, V> headMap(K endKey) {
			checkRange(endKey);
			if (hasStart)
				return new SubMap<K, V>(startKey, backingMap, endKey);
			return new SubMap<K, V>(backingMap, endKey);
		}

		public boolean isEmpty() {
			if (hasStart) {
				TreeMap.Entry<K, V> node = backingMap.findAfter(startKey);
				return node == null || !checkRange(node.key, false, hasEnd);
			}
			return backingMap.findBefore(endKey) == null;
		}

		public Set<K> keySet() {
			if (keySet == null) {
				keySet = new SubMapSet<K, K, V>(hasStart, startKey, backingMap, hasEnd,
						endKey, new MapEntry.Type<K, K, V>() {
							public K get(MapEntry<K, V> entry) {
								return entry.key;
							}
						}) {
					public boolean contains(Object object) {
						return containsKey(object);
					}
				};
			}
			return keySet;
		}

		public K lastKey() {
			if (!hasEnd)
				return backingMap.lastKey();
			TreeMap.Entry<K, V> node = backingMap.findBefore(endKey);
			if (node != null && checkRange(node.key, hasStart, false))
				return node.key;
			throw new NoSuchElementException();
		}

		public V put(K key, V value) {
			if (checkRange(key, hasStart, hasEnd))
				return backingMap.put(key, value);
			throw new IllegalArgumentException();
		}

		public V remove(Object key) {
			if (checkRange((K)key, hasStart, hasEnd))
				return backingMap.remove(key);
			return null;
		}

		public SortedMap<K, V> subMap(K startKey, K endKey) {
			checkRange(startKey);
			checkRange(endKey);
			Comparator<? super K> c = backingMap.comparator();
			if (c == null) {
				if (((Comparable<K>) startKey).compareTo(endKey) <= 0)
					return new SubMap<K, V>(startKey, backingMap, endKey);
			} else {
				if (c.compare(startKey, endKey) <= 0)
					return new SubMap<K, V>(startKey, backingMap, endKey);
			}
			throw new IllegalArgumentException();
		}

		public SortedMap<K, V> tailMap(K startKey) {
			checkRange(startKey);
			if (hasEnd)
				return new SubMap<K, V>(startKey, backingMap, endKey);
			return new SubMap<K, V>(startKey, backingMap);
		}

		public Collection<V> values() {
			return new SubMapSet<V, K, V>(hasStart, startKey, backingMap, hasEnd,
					endKey, new MapEntry.Type<V, K, V>() {
						public V get(MapEntry<K, V> entry) {
							return entry.value;
						}
					});
		}
	}

	/**
	 * Constructs a new empty instance of TreeMap.
	 * 
	 */
	public TreeMap() {
		super();
	}

	/**
	 * Constructs a new empty instance of TreeMap which uses the specified
	 * Comparator.
	 * 
	 * @param comparator
	 *            the Comparator
	 */
	public TreeMap(Comparator<? super K> comparator) {
		this.comparator = comparator;
	}

	/**
	 * Constructs a new instance of TreeMap containing the mappings from the
	 * specified Map and using the natural ordering.
	 * 
	 * @param map
	 *            the mappings to add
	 * 
	 * @exception ClassCastException
	 *                when a key in the Map does not implement the Comparable
	 *                interface, or they keys in the Map cannot be compared
	 */
	public TreeMap(Map<? extends K,? extends V> map) {
		this();
		putAll(map);
	}

	/**
	 * Constructs a new instance of TreeMap containing the mappings from the
	 * specified SortedMap and using the same Comparator.
	 * 
	 * @param map
	 *            the mappings to add
	 */
	public TreeMap(SortedMap<K,? extends V> map) {
		this(map.comparator());
		Iterator<? extends Map.Entry<K, ? extends V>> it = map.entrySet().iterator();
		if (it.hasNext()) {
			Map.Entry<K, ? extends V> entry = it.next();
			Entry<K, V> last = new Entry<K, V>(entry.getKey(), entry.getValue());
			root = last;
			size = 1;
			while (it.hasNext()) {
				entry = it.next();
				Entry<K, V> x = new Entry<K, V>(entry.getKey(), entry.getValue());
				x.parent = last;
				last.right = x;
				size++;
				balance(x);
				last = x;
			}
		}
	}

	void balance(Entry<K, V> x) {
		Entry<K, V> y;
		x.color = true;
		while (x != root && x.parent.color) {
			if (x.parent == x.parent.parent.left) {
				y = x.parent.parent.right;
				if (y != null && y.color) {
					x.parent.color = false;
					y.color = false;
					x.parent.parent.color = true;
					x = x.parent.parent;
				} else {
					if (x == x.parent.right) {
						x = x.parent;
						leftRotate(x);
					}
					x.parent.color = false;
					x.parent.parent.color = true;
					rightRotate(x.parent.parent);
				}
			} else {
				y = x.parent.parent.left;
				if (y != null && y.color) {
					x.parent.color = false;
					y.color = false;
					x.parent.parent.color = true;
					x = x.parent.parent;
				} else {
					if (x == x.parent.left) {
						x = x.parent;
						rightRotate(x);
					}
					x.parent.color = false;
					x.parent.parent.color = true;
					leftRotate(x.parent.parent);
				}
			}
		}
		root.color = false;
	}

	/**
	 * Removes all mappings from this TreeMap, leaving it empty.
	 * 
	 * @see Map#isEmpty
	 * @see #size
	 */
	public void clear() {
		root = null;
		size = 0;
		modCount++;
	}

	/**
	 * Answers a new TreeMap with the same mappings, size and comparator as this
	 * TreeMap.
	 * 
	 * @return a shallow copy of this TreeMap
	 * 
	 * @see java.lang.Cloneable
	 */
	public Object clone() {
		try {
			TreeMap<K, V> clone = (TreeMap<K, V>) super.clone();
			if (root != null)
				clone.root = root.clone(null);
			return clone;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	/**
	 * Answers the Comparator used to compare elements in this TreeMap.
	 * 
	 * @return a Comparator or null if the natural ordering is used
	 */
	public Comparator<? super K> comparator() {
		return comparator;
	}

	/**
	 * Searches this TreeMap for the specified key.
	 * 
	 * @param key
	 *            the object to search for
	 * @return true if <code>key</code> is a key of this TreeMap, false
	 *         otherwise
	 * 
	 * @exception ClassCastException
	 *                when the key cannot be compared with the keys in this
	 *                TreeMap
	 * @exception NullPointerException
	 *                when the key is null and the comparator cannot handle null
	 */
	public boolean containsKey(Object key) {
		return find((K)key) != null;
	}

	/**
	 * Searches this TreeMap for the specified value.
	 * 
	 * @param value
	 *            the object to search for
	 * @return true if <code>value</code> is a value of this TreeMap, false
	 *         otherwise
	 */
	public boolean containsValue(Object value) {
		if (root != null)
			return containsValue(root, value);
		return false;
	}

	private boolean containsValue(Entry<K, V> node, Object value) {
		if (value == null ? node.value == null : value.equals(node.value))
			return true;
		if (node.left != null)
			if (containsValue(node.left, value))
				return true;
		if (node.right != null)
			if (containsValue(node.right, value))
				return true;
		return false;
	}

	/**
	 * Answers a Set of the mappings contained in this TreeMap. Each element in
	 * the set is a Map.Entry. The set is backed by this TreeMap so changes to
	 * one are reflected by the other. The set does not support adding.
	 * 
	 * @return a Set of the mappings
	 */
	public Set<Map.Entry<K, V>> entrySet() {
		return new AbstractSet<Map.Entry<K, V>>() {
			public int size() {
				return size;
			}

			public void clear() {
				TreeMap.this.clear();
			}

			public boolean contains(Object object) {
				if (object instanceof Map.Entry) {
					Map.Entry<K, V> entry = (Map.Entry<K, V>) object;
					Object v1 = get(entry.getKey()), v2 = entry.getValue();
					return v1 == null ? v2 == null : v1.equals(v2);
				}
				return false;
			}

			public Iterator<Map.Entry<K, V>> iterator() {
				return new TreeMapIterator<Map.Entry<K, V>, K, V>(TreeMap.this, new MapEntry.Type<Map.Entry<K, V>, K, V>() {
					public Map.Entry<K, V> get(MapEntry<K, V> entry) {
						return entry;
					}
				});
			}
		};
	}

	private Entry<K, V> find(K key) {
		int result;
		Comparable<K> object = null;
		if (comparator == null)
			object = (Comparable<K>) key;
		Entry<K, V> x = root;
		while (x != null) {
			result = object != null ? object.compareTo(x.key) : comparator
					.compare(key, x.key);
			if (result == 0)
				return x;
			x = result < 0 ? x.left : x.right;
		}
		return null;
	}

	Entry<K, V> findAfter(K key) {
		int result;
		Comparable<K> object = null;
		if (comparator == null)
			object = (Comparable<K>) key;
		Entry<K, V> x = root, last = null;
		while (x != null) {
			result = object != null ? object.compareTo(x.key) : comparator
					.compare(key, x.key);
			if (result == 0)
				return x;
			if (result < 0) {
				last = x;
				x = x.left;
			} else
				x = x.right;
		}
		return last;
	}

	Entry<K, V> findBefore(K key) {
		int result;
        Comparable<K> object = null;
		if (comparator == null)
			object = (Comparable<K>) key;
		Entry<K, V> x = root, last = null;
		while (x != null) {
			result = object != null ? object.compareTo(x.key) : comparator
					.compare(key, x.key);
			if (result <= 0)
				x = x.left;
			else {
				last = x;
				x = x.right;
			}
		}
		return last;
	}

	/**
	 * Answer the first sorted key in this TreeMap.
	 * 
	 * @return the first sorted key
	 * 
	 * @exception NoSuchElementException
	 *                when this TreeMap is empty
	 */
	public K firstKey() {
		if (root != null)
			return minimum(root).key;
		throw new NoSuchElementException();
	}

	private void fixup(Entry<K, V> x) {
		Entry<K, V> w;
		while (x != root && !x.color) {
			if (x == x.parent.left) {
				w = x.parent.right;
				if (w == null) {
					x = x.parent;
					continue;
				}
				if (w.color) {
					w.color = false;
					x.parent.color = true;
					leftRotate(x.parent);
					w = x.parent.right;
					if (w == null) {
						x = x.parent;
						continue;
					}
				}
				if ((w.left == null || !w.left.color)
						&& (w.right == null || !w.right.color)) {
					w.color = true;
					x = x.parent;
				} else {
					if (w.right == null || !w.right.color) {
						w.left.color = false;
						w.color = true;
						rightRotate(w);
						w = x.parent.right;
					}
					w.color = x.parent.color;
					x.parent.color = false;
					w.right.color = false;
					leftRotate(x.parent);
					x = root;
				}
			} else {
				w = x.parent.left;
				if (w == null) {
					x = x.parent;
					continue;
				}
				if (w.color) {
					w.color = false;
					x.parent.color = true;
					rightRotate(x.parent);
					w = x.parent.left;
					if (w == null) {
						x = x.parent;
						continue;
					}
				}
				if ((w.left == null || !w.left.color)
						&& (w.right == null || !w.right.color)) {
					w.color = true;
					x = x.parent;
				} else {
					if (w.left == null || !w.left.color) {
						w.right.color = false;
						w.color = true;
						leftRotate(w);
						w = x.parent.left;
					}
					w.color = x.parent.color;
					x.parent.color = false;
					w.left.color = false;
					rightRotate(x.parent);
					x = root;
				}
			}
		}
		x.color = false;
	}

	/**
	 * Answers the value of the mapping with the specified key.
	 * 
	 * @param key
	 *            the key
	 * @return the value of the mapping with the specified key
	 * 
	 * @exception ClassCastException
	 *                when the key cannot be compared with the keys in this
	 *                TreeMap
	 * @exception NullPointerException
	 *                when the key is null and the comparator cannot handle null
	 */
	public V get(Object key) {
		Entry<K, V> node = find((K)key);
		if (node != null)
			return node.value;
		return null;
	}

	/**
	 * Answers a SortedMap of the specified portion of this TreeMap which
	 * contains keys less than the end key. The returned SortedMap is backed by
	 * this TreeMap so changes to one are reflected by the other.
	 * 
	 * @param endKey
	 *            the end key
	 * @return a submap where the keys are less than <code>endKey</code>
	 * 
	 * @exception ClassCastException
	 *                when the end key cannot be compared with the keys in this
	 *                TreeMap
	 * @exception NullPointerException
	 *                when the end key is null and the comparator cannot handle
	 *                null
	 */
	public SortedMap<K, V> headMap(K endKey) {
		// Check for errors
		if (comparator == null)
			((Comparable<K>) endKey).compareTo(endKey);
		else
			comparator.compare(endKey, endKey);
		return new SubMap<K, V>(this, endKey);
	}

	/**
	 * Answers a Set of the keys contained in this TreeMap. The set is backed by
	 * this TreeMap so changes to one are reflected by the other. The set does
	 * not support adding.
	 * 
	 * @return a Set of the keys
	 */
	public Set<K> keySet() {
		if (keySet == null) {
			keySet = new AbstractSet<K>() {
				public boolean contains(Object object) {
					return containsKey(object);
				}

				public int size() {
					return size;
				}

				public void clear() {
					TreeMap.this.clear();
				}

				public Iterator<K> iterator() {
					return new TreeMapIterator<K, K, V>(TreeMap.this,
							new MapEntry.Type<K, K, V>() {
								public K get(MapEntry<K, V> entry) {
									return entry.key;
								}
							});
				}
			};
		}
		return keySet;
	}

	/**
	 * Answer the last sorted key in this TreeMap.
	 * 
	 * @return the last sorted key
	 * 
	 * @exception NoSuchElementException
	 *                when this TreeMap is empty
	 */
	public K lastKey() {
		if (root != null)
			return maximum(root).key;
		throw new NoSuchElementException();
	}

	private void leftRotate(Entry<K, V> x) {
		Entry<K, V> y = x.right;
		x.right = y.left;
		if (y.left != null)
			y.left.parent = x;
		y.parent = x.parent;
		if (x.parent == null) {
			root = y;
		} else {
			if (x == x.parent.left)
				x.parent.left = y;
			else
				x.parent.right = y;
		}
		y.left = x;
		x.parent = y;
	}

	static <K, V> Entry<K, V> maximum(Entry<K, V> x) {
		while (x.right != null)
			x = x.right;
		return x;
	}

	static <K, V> Entry<K, V> minimum(Entry<K, V> x) {
		while (x.left != null)
			x = x.left;
		return x;
	}

	static <K, V> Entry<K, V> predecessor(Entry<K, V> x) {
		if (x.left != null)
			return maximum(x.left);
		Entry<K, V> y = x.parent;
		while (y != null && x == y.left) {
			x = y;
			y = y.parent;
		}
		return y;
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
	 * 
	 * @exception ClassCastException
	 *                when the key cannot be compared with the keys in this
	 *                TreeMap
	 * @exception NullPointerException
	 *                when the key is null and the comparator cannot handle null
	 */
	public V put(K key, V value) {
		MapEntry<K, V> entry = rbInsert(key);
		V result = entry.value;
		entry.value = value;
		return result;
	}

	/**
	 * Copies every mapping in the specified Map to this TreeMap.
	 * 
	 * @param map
	 *            the Map to copy mappings from
	 * 
	 * @exception ClassCastException
	 *                when a key in the Map cannot be compared with the keys in
	 *                this TreeMap
	 * @exception NullPointerException
	 *                when a key in the Map is null and the comparator cannot
	 *                handle null
	 */
	public void putAll(Map<? extends K, ? extends V> map) {
		super.putAll(map);
	}

	void rbDelete(Entry<K, V> z) {
		Entry<K, V> y = z.left == null || z.right == null ? z : successor(z);
		Entry<K, V> x = y.left != null ? y.left : y.right;
		if (x != null)
			x.parent = y.parent;
		if (y.parent == null)
			root = x;
		else if (y == y.parent.left)
			y.parent.left = x;
		else
			y.parent.right = x;
		modCount++;
		if (y != z) {
			z.key = y.key;
			z.value = y.value;
		}
		if (!y.color && root != null) {
			if (x == null)
				fixup(y.parent);
			else
				fixup(x);
		}
		size--;
	}

	private Entry<K, V> rbInsert(K object) {
		int result = 0;
		Comparable<K> key = null;
		if (comparator == null)
			key = (Comparable<K>) object;
		Entry<K, V> y = null, x = root;
		while (x != null) {
			y = x;
			result = key != null ? key.compareTo(x.key) : comparator.compare(
					object, x.key);
			if (result == 0)
				return x;
			x = result < 0 ? x.left : x.right;
		}

		size++;
		modCount++;
		Entry<K, V> z = new Entry<K, V>(object);
		if (y == null)
			return root = z;
		z.parent = y;
		if (result < 0)
			y.left = z;
		else
			y.right = z;
		balance(z);
		return z;
	}

	/**
	 * Removes a mapping with the specified key from this TreeMap.
	 * 
	 * @param key
	 *            the key of the mapping to remove
	 * @return the value of the removed mapping or null if key is not a key in
	 *         this TreeMap
	 * 
	 * @exception ClassCastException
	 *                when the key cannot be compared with the keys in this
	 *                TreeMap
	 * @exception NullPointerException
	 *                when the key is null and the comparator cannot handle null
	 */
	public V remove(Object key) {
		Entry<K, V> node = find((K)key);
		if (node == null)
			return null;
		V result = node.value;
		rbDelete(node);
		return result;
	}

	private void rightRotate(Entry<K, V> x) {
		Entry<K, V> y = x.left;
		x.left = y.right;
		if (y.right != null)
			y.right.parent = x;
		y.parent = x.parent;
		if (x.parent == null) {
			root = y;
		} else {
			if (x == x.parent.right)
				x.parent.right = y;
			else
				x.parent.left = y;
		}
		y.right = x;
		x.parent = y;
	}

	/**
	 * Answers the number of mappings in this TreeMap.
	 * 
	 * @return the number of mappings in this TreeMap
	 */
	public int size() {
		return size;
	}

	/**
	 * Answers a SortedMap of the specified portion of this TreeMap which
	 * contains keys greater or equal to the start key but less than the end
	 * key. The returned SortedMap is backed by this TreeMap so changes to one
	 * are reflected by the other.
	 * 
	 * @param startKey
	 *            the start key
	 * @param endKey
	 *            the end key
	 * @return a submap where the keys are greater or equal to
	 *         <code>startKey</code> and less than <code>endKey</code>
	 * 
	 * @exception ClassCastException
	 *                when the start or end key cannot be compared with the keys
	 *                in this TreeMap
	 * @exception NullPointerException
	 *                when the start or end key is null and the comparator
	 *                cannot handle null
	 */
	public SortedMap<K, V> subMap(K startKey, K endKey) {
		if (comparator == null) {
			if (((Comparable<K>) startKey).compareTo(endKey) <= 0)
				return new SubMap<K, V>(startKey, this, endKey);
		} else {
			if (comparator.compare(startKey, endKey) <= 0)
				return new SubMap<K, V>(startKey, this, endKey);
		}
		throw new IllegalArgumentException();
	}

	static <K, V> Entry<K, V> successor(Entry<K, V> x) {
		if (x.right != null)
			return minimum(x.right);
		Entry<K, V> y = x.parent;
		while (y != null && x == y.right) {
			x = y;
			y = y.parent;
		}
		return y;
	}

	/**
	 * Answers a SortedMap of the specified portion of this TreeMap which
	 * contains keys greater or equal to the start key. The returned SortedMap
	 * is backed by this TreeMap so changes to one are reflected by the other.
	 * 
	 * @param startKey
	 *            the start key
	 * @return a submap where the keys are greater or equal to
	 *         <code>startKey</code>
	 * 
	 * @exception ClassCastException
	 *                when the start key cannot be compared with the keys in
	 *                this TreeMap
	 * @exception NullPointerException
	 *                when the start key is null and the comparator cannot
	 *                handle null
	 */
	public SortedMap<K, V> tailMap(K startKey) {
		// Check for errors
		if (comparator == null)
			((Comparable<K>) startKey).compareTo(startKey);
		else
			comparator.compare(startKey, startKey);
		return new SubMap<K, V>(startKey, this);
	}

	/**
	 * Answers a Collection of the values contained in this TreeMap. The
	 * collection is backed by this TreeMap so changes to one are relected by
	 * the other. The collection does not support adding.
	 * 
	 * @return a Collection of the values
	 */
	public Collection<V> values() {
		if (valuesCollection == null) {
			valuesCollection = new AbstractCollection<V>() {
				public boolean contains(Object object) {
					return containsValue(object);
				}

				public int size() {
					return size;
				}

				public void clear() {
					TreeMap.this.clear();
				}

				public Iterator<V> iterator() {
					return new TreeMapIterator<V, K, V>(TreeMap.this,
							new MapEntry.Type<V, K, V>() {
								public V get(MapEntry<K, V> entry) {
									return entry.value;
								}
							});
				}
			};
		}
		return valuesCollection;
	}

	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
		stream.writeInt(size);
		if (size > 0) {
			Entry<K, V> node = minimum(root);
			while (node != null) {
				stream.writeObject(node.key);
				stream.writeObject(node.value);
				node = successor(node);
			}
		}
	}

	private void readObject(ObjectInputStream stream) throws IOException,
			ClassNotFoundException {
		stream.defaultReadObject();
		size = stream.readInt();
		Entry<K, V> last = null;
		for (int i = size; --i >= 0;) {
			Entry<K, V> node = new Entry<K, V>((K)stream.readObject());
			node.value = (V)stream.readObject();
			if (last == null)
				root = node;
			else {
				node.parent = last;
				last.right = node;
				balance(node);
			}
			last = node;
		}
	}
}
