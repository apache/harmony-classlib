/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
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

	transient int size;

    transient Entry<K, V> root;

    private Comparator<? super K> comparator;

    transient int modCount;

    transient Set<Map.Entry<K, V>> entrySet;

    static final int SMALL_LIMIT=127;
    transient K[] small_keys;
    transient V[] small_values;
    transient Entry<K, V>[] small_entries;
    transient int small_left=0;
    transient int small_right=-1;

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

		@SuppressWarnings("unchecked")
        Entry<K, V> clone(Entry<K, V> parent) {
			Entry<K, V> clone = (Entry<K, V>) super.clone();
			clone.parent = parent;
			if (left != null) {
                clone.left = left.clone(clone);
            }
			if (right != null) {
                clone.right = right.clone(clone);
            }
			return clone;
		}
	}

    @SuppressWarnings("unchecked")
    private static <T> Comparable<T> toComparable(T obj) {
        return (Comparable<T>)obj;
    }

    private static class AbstractMapIterator <K,V> {
        TreeMap<K, V> backingMap;
        int expectedModCount;
        TreeMap.Entry<K, V> node;
        TreeMap.Entry<K, V> lastNode;

        AbstractMapIterator(TreeMap<K, V> map, Entry<K, V> startNode) {
            backingMap = map;
            expectedModCount = map.modCount;
            node = startNode;
        }

        public boolean hasNext() {
            return node != null;
        }

        final public void remove() {
            if (expectedModCount == backingMap.modCount) {
                if (lastNode != null) {
                    backingMap.rbDelete(lastNode);
                    lastNode = null;
                    expectedModCount++;
                } else {
                    throw new IllegalStateException();
                }
            } else {
                throw new ConcurrentModificationException();
            }
        }
    }

        private static class UnboundedIterator<K, V> extends AbstractMapIterator<K, V> {

            public UnboundedIterator(TreeMap<K, V> treeMap, Entry<K, V> entry) {
                super(treeMap, entry);
            }

            final void makeNext() {
                if (expectedModCount != backingMap.modCount) {
                    throw new ConcurrentModificationException();
                } else if (node == null) {
                    throw new NoSuchElementException();
                }
                lastNode = node;
                node = TreeMap.successor(node);
            }

       }

        private static class UnboundedEntryIterator <K, V> extends UnboundedIterator<K, V> implements Iterator<Map.Entry<K, V>> {

            UnboundedEntryIterator(TreeMap<K, V> map, Entry<K, V> startNode) {
                super(map, startNode);
            }

            UnboundedEntryIterator(TreeMap<K, V> map) {
                super(map, map.root == null ? null : TreeMap.minimum(map.root));
            }

            public Map.Entry<K, V> next() {
                makeNext();
                return lastNode;
            }
        }

        static class UnboundedKeyIterator <K, V> extends UnboundedIterator<K, V> implements Iterator<K> {
            public UnboundedKeyIterator(TreeMap<K, V> treeMap, Entry<K, V> entry) {
                super(treeMap, entry);
            }

            public UnboundedKeyIterator(TreeMap<K, V> map) {
                super(map, map.root == null ? null : TreeMap.minimum(map.root));
            }

            public K next() {
                makeNext();
                return lastNode.key;
            }
        }

        static class UnboundedValueIterator <K, V> extends UnboundedIterator<K, V> implements Iterator<V> {

            public UnboundedValueIterator(TreeMap<K, V> treeMap, Entry<K, V> startNode) {
                super(treeMap, startNode);
            }

            public UnboundedValueIterator(TreeMap<K, V> map) {
                super(map, map.root == null ? null : TreeMap.minimum(map.root));
            }

            public V next() {
                makeNext();
                return lastNode.value;
            }
        }

        private static class BoundedIterator<K, V> extends AbstractMapIterator<K, V> {
            private final TreeMap.Entry<K, V> finishNode;

            BoundedIterator(TreeMap<K, V> map, Entry<K, V> startNode, Entry<K, V> finishNode) {
                super(map, startNode);
                this.finishNode = finishNode;
            }

            final void makeNext() {
                if (expectedModCount != backingMap.modCount) {
                    throw new ConcurrentModificationException();
                } else if (node == null) {
                    throw new NoSuchElementException();
                }
                lastNode = node;
                if(node!=finishNode) {
                    node = TreeMap.successor(node);
                } else {
                    node = null;
                }
            }
        }


    private static class BoundedEntryIterator<K, V> extends
            BoundedIterator<K, V> implements Iterator<Map.Entry<K, V>> {

        BoundedEntryIterator(TreeMap<K, V> map, Entry<K, V> startNode, Entry<K, V> finishNode) {
            super(map, startNode, finishNode);
        }

        public Map.Entry<K, V> next() {
            makeNext();
            return lastNode;
        }
    }

    private static class BoundedKeyIterator<K, V> extends
            BoundedIterator<K, V> implements Iterator<K> {

        BoundedKeyIterator(TreeMap<K, V> map, Entry<K, V> startNode, Entry<K, V> finishNode) {
            super(map, startNode, finishNode);
        }

        public K next() {
            makeNext();
            return lastNode.key;
        }
    }

    private static class BoundedValueIterator<K, V> extends
              BoundedIterator<K, V> implements Iterator<V> {

        BoundedValueIterator(TreeMap<K, V> map, Entry<K, V> startNode, Entry<K, V> finishNode) {
            super(map, startNode, finishNode);
        }

        public V next() {
            makeNext();
            return lastNode.value;
        }
    }

    private static class SmallMapIterator <K,V> {
        TreeMap<K, V> backingMap;
        int expectedModCount;
        int index;
        int lastIndex;
        int endIndex;

        SmallMapIterator(TreeMap<K, V> map, int startIndex, int endIndex) {
            backingMap = map;
            expectedModCount = map.modCount;
            index = startIndex;
            this.endIndex = endIndex;
        }

        public boolean hasNext() {
            return index<=endIndex;
        }

        final void makeNext() {
                if (expectedModCount != backingMap.modCount) {
                    throw new ConcurrentModificationException();
                } else if (index>endIndex) {
                    throw new NoSuchElementException();
                }
                lastIndex = index++;
            }

        final public void remove() {
            if (expectedModCount == backingMap.modCount) {
                if (lastIndex != -1) {
                    if(backingMap.smallDelete(lastIndex)) {
                        endIndex--;
                        index--;
                    }
                    lastIndex = -1;
                    expectedModCount++;
                } else {
                    throw new IllegalStateException();
                }
            } else {
                throw new ConcurrentModificationException();
            }
        }
    }

       private static class SmallEntryIterator<K, V> extends
               SmallMapIterator<K, V> implements Iterator<Map.Entry<K, V>> {

           SmallEntryIterator(TreeMap<K, V> map, int startIndex, int endIndex) {
               super(map, startIndex, endIndex);
           }

           public Map.Entry<K, V> next() {
               makeNext();
               if(backingMap.small_entries==null) {
                   backingMap.small_entries = (Entry<K,V>[])new Entry[backingMap.small_keys.length];
               }
               if(backingMap.small_entries[lastIndex]==null) {
                   backingMap.small_entries[lastIndex]=new Entry<K,V>(backingMap.small_keys[lastIndex],backingMap.small_values[lastIndex]);
               }
               return backingMap.small_entries[lastIndex];
           }
       }

    private static class SmallKeyIterator<K, V> extends
            SmallMapIterator<K, V> implements Iterator<K> {

        SmallKeyIterator(TreeMap<K, V> map, int startIndex, int endIndex) {
            super(map, startIndex, endIndex);
        }

        public K next() {
            makeNext();
            return backingMap.small_keys[lastIndex];
        }
    }

    private static class SmallValueIterator<K, V> extends
            SmallMapIterator<K, V> implements Iterator<V> {

        SmallValueIterator(TreeMap<K, V> map, int startIndex, int endIndex) {
            super(map, startIndex, endIndex);
        }

        public V next() {
            makeNext();
            return backingMap.small_values[lastIndex];
        }
    }

        static final class SubMap<K,V> extends AbstractMap<K,V> implements SortedMap<K,V>, Serializable {
            private static final long serialVersionUID = -6520786458950516097L;

            private TreeMap<K,V> backingMap;

            boolean hasStart, hasEnd;

            K startKey, endKey;

            transient Set<Map.Entry<K,V>> entrySet = null;
            transient int firstEntryModCount = -1;
            transient int lastEntryModCount = -1;
            transient TreeMap.Entry<K,V> firstEntry;
            transient TreeMap.Entry<K,V> lastEntry;

            SubMap(K start, TreeMap<K,V> map) {
                backingMap = map;
                hasStart = true;
                startKey = start;
            }

            SubMap(K start, TreeMap<K,V> map, K end) {
                backingMap = map;
                hasStart = hasEnd = true;
                startKey = start;
                endKey = end;
            }

            SubMap(TreeMap<K,V> map, K end) {
                backingMap = map;
                hasEnd = true;
                endKey = end;
            }

            private void checkRange(K key) {
                Comparator<? super K> cmp = backingMap.comparator;
                if (cmp == null) {
                    Comparable<K> object = toComparable(key);
                    if (hasStart && object.compareTo(startKey) < 0) {
                        throw new IllegalArgumentException();
                    }
                    if (hasEnd && object.compareTo(endKey) > 0) {
                        throw new IllegalArgumentException();
                    }
                } else {
                    if (hasStart
                            && backingMap.comparator().compare(key, startKey) < 0) {
                        throw new IllegalArgumentException();
                    }
                    if (hasEnd && backingMap.comparator().compare(key, endKey) > 0) {
                        throw new IllegalArgumentException();
                    }
                }
            }

            private boolean isInRange(K key) {
                Comparator<? super K> cmp = backingMap.comparator;
                if (cmp == null) {
                    Comparable<K> object = toComparable(key);
                    if (hasStart && object.compareTo(startKey) < 0) {
                        return false;
                    }
                    if (hasEnd && object.compareTo(endKey) >= 0) {
                        return false;
                    }
                } else {
                    if (hasStart && cmp.compare(key, startKey) < 0) {
                        return false;
                    }
                    if (hasEnd && cmp.compare(key, endKey) >= 0) {
                        return false;
                    }
                }
                return true;
            }

            private boolean checkUpperBound(K key) {
                if (hasEnd) {
                    Comparator<? super K> cmp = backingMap.comparator;
                    if (cmp == null) {
                        return (toComparable(key).compareTo(endKey) < 0);
                    }
                    return (cmp.compare(key, endKey) < 0);
                }
                return true;
            }

            private boolean checkLowerBound(K key) {
                if (hasStart) {
                    Comparator<? super K> cmp = backingMap.comparator;
                    if (cmp == null) {
                        return (toComparable(key).compareTo(startKey) >= 0);
                    }
                    return (cmp.compare(key, startKey) >= 0);
                }
                return true;
            }

            public Comparator<? super K> comparator() {
                return backingMap.comparator();
            }

            @SuppressWarnings("unchecked")
            @Override
            public boolean containsKey(Object key) {
                if (isInRange((K)key)) {
                    return backingMap.containsKey(key);
                }
                return false;
            }

            @Override
            public Set<Map.Entry<K,V>> entrySet() {
                if(entrySet==null) {
                    entrySet = new SubMapEntrySet<K,V>(this);
                }
                return entrySet;
            }

            public K firstKey() {
                if(backingMap.size>0) {
                    if(backingMap.isSmall()) {
                        if(!hasStart) {
                            K kres = backingMap.small_keys[backingMap.small_left];
                            if(checkUpperBound(kres)) {
                                 return kres;
                            }

                        } else {
                            int idx = backingMap.smallFindAfter(startKey);
                            if(idx>=backingMap.small_left && idx <=backingMap.small_right){
                                K kres = backingMap.small_keys[idx];
                                if(checkUpperBound(kres)) {
	                             return kres;
	                        }
                            }
                        }
                    } else {
                        TreeMap.Entry<K,V> node = firstEntry();
                        if (node != null ) {
                            return node.key;
                        }
                    }
                }
                throw new NoSuchElementException();
            }

            TreeMap.Entry<K,V> firstEntry() {
                if(firstEntryModCount == backingMap.modCount) {
                    return firstEntry;
                }
                TreeMap.Entry<K,V> node;
                if (!hasStart) {
                    TreeMap.Entry<K,V> root = backingMap.root;
                    node = (root == null) ? null : minimum(root);
                } else {
                    node = backingMap.findAfter(startKey);
                }
                if (node != null && !checkUpperBound(node.key)) {
                    node = null;
                }
                firstEntry = node;
                firstEntryModCount = backingMap.modCount;
                return node;
            }

            @SuppressWarnings("unchecked")
            @Override
            public V get(Object key) {
                if (isInRange((K)key)) {
                    return backingMap.get(key);
                }
                return null;
            }

            public SortedMap<K,V> headMap(K endKey) {
                checkRange(endKey);
                if (hasStart) {
                    return new SubMap<K,V>(startKey, backingMap, endKey);
                }
                return new SubMap<K,V>(backingMap, endKey);
            }

            @Override
            public boolean isEmpty() {
                 if (!hasStart) {
                     return firstEntry()==null;
                 } else {
                     return lastEntry()==null;
                 }
            }

            @Override
            public Set<K> keySet() {
                if (keySet == null) {
                    keySet = new SubMapKeySet<K,V>(this);
                }
                return keySet;
            }

            public K lastKey() {
                if (backingMap.size > 0) {
                    if (backingMap.isSmall()) {
                        if (!hasEnd) {
                            K kres = backingMap.small_keys[backingMap.small_right];
                            if(checkLowerBound(kres)) {
                                 return kres;
                            }
                        } else {
                            int idx = backingMap.smallFindBefore(endKey);
                            if (idx>=backingMap.small_left && idx <= backingMap.small_right) {
                                K kres = backingMap.small_keys[idx] ;
                                if(checkLowerBound(kres)) {
	                             return kres;
	                        }
                            }
                        }
                    } else {
                        TreeMap.Entry<K, V> node = lastEntry();
                        if (node != null) {
                            return node.key;
                        }
                    }
                }
                throw new NoSuchElementException();
            }

            TreeMap.Entry<K,V> lastEntry() {
                if(lastEntryModCount == backingMap.modCount) {
                    return lastEntry;
                }
                TreeMap.Entry<K,V> node;
                if (!hasEnd) {
                    TreeMap.Entry<K,V> root = backingMap.root;
                    node = (root == null) ? null : maximum(root);
                } else {
                    node = backingMap.findBefore(endKey);
                }
                if (node != null && !checkLowerBound(node.key)) {
                    node = null;
                }
                lastEntry = node;
                lastEntryModCount = backingMap.modCount;
                return node;
            }

            @Override
            public V put(K key, V value) {
                if (isInRange(key)) {
                    return backingMap.put(key, value);
                }
                throw new IllegalArgumentException();
            }

            @SuppressWarnings("unchecked")
            @Override
            public V remove(Object key) {
                if (isInRange((K)key)) {
                    return backingMap.remove(key);
                }
                return null;
            }

            public SortedMap<K,V> subMap(K startKey, K endKey) {
                checkRange(startKey);
                checkRange(endKey);
                Comparator<? super K> c = backingMap.comparator();
                if (c == null) {
                    if (toComparable(startKey).compareTo(endKey) <= 0) {
                        return new SubMap<K,V>(startKey, backingMap, endKey);
                    }
                } else {
                    if (c.compare(startKey, endKey) <= 0) {
                        return new SubMap<K,V>(startKey, backingMap, endKey);
                    }
                }
                throw new IllegalArgumentException();
            }

            public SortedMap<K,V> tailMap(K startKey) {
                checkRange(startKey);
                if (hasEnd) {
                    return new SubMap<K,V>(startKey, backingMap, endKey);
                }
                return new SubMap<K,V>(startKey, backingMap);
            }

            @Override
            public Collection<V> values() {
                if(valuesCollection==null) {
                    valuesCollection = new SubMapValuesCollection<K,V>(this);
                }
                return valuesCollection;
            }

            public int size() {
                if(backingMap.isSmall()) {
                    int start,end;
                    if (!hasStart) {
                        start = backingMap.small_left;
                    } else {
                        start = backingMap.smallFindAfter(startKey);
                    }
                    System.out.println(start);
                    if (!hasEnd) {
                        end = backingMap.small_right;
                    } else {
                        end = backingMap.smallFindBefore(endKey);
                    }
                    System.out.println(end);
                    if(backingMap.small_left<=start && end <=backingMap.small_right && end-start>=0) {
                        return end-start+1;
                    } else {
                        return 0;
                    }
                }
                TreeMap.Entry<K,V> entry = firstEntry();
                if(entry!=null) {
                    int cnt=1;
                    if(hasEnd) {
                        TreeMap.Entry<K,V> last = lastEntry();
                        while(entry!=last){
                            entry = successor(entry);
                            cnt++;
                        }
                    } else {
                        while((entry=successor(entry))!=null) cnt++;
                    }
                    return cnt;
                }
                return 0;
            }

        }

        static class SubMapEntrySet<K,V> extends AbstractSet<Map.Entry<K,V>> implements Set<Map.Entry<K,V>> {
            SubMap<K,V> subMap;

            SubMapEntrySet(SubMap<K,V> map) {
                subMap = map;
            }

            @Override
            public boolean isEmpty() {
                return subMap.isEmpty();
            }

            @Override
            public Iterator<Map.Entry<K,V>> iterator() {
                if(subMap.backingMap.isSmall()) {
                   int start, end;
                   if(subMap.hasStart) {
                       start = subMap.backingMap.smallFindAfter(subMap.startKey);
                   } else {
                       start = subMap.backingMap.small_left;
                   }
                    if(subMap.hasEnd) {
                        end = subMap.backingMap.smallFindBefore(subMap.endKey);
                    } else {
                        end = subMap.backingMap.small_right;
                    }
                   return new SmallEntryIterator<K,V>(subMap.backingMap,start,end);
                }
                TreeMap.Entry<K,V> startNode = subMap.firstEntry();
                if (subMap.hasEnd) {
                    TreeMap.Entry<K,V> lastNode = subMap.lastEntry();
                    return new BoundedEntryIterator<K,V>(subMap.backingMap, startNode, lastNode);
                }
                return new UnboundedEntryIterator<K,V>(subMap.backingMap, startNode);
            }

            @Override
            public int size() {
                return subMap.size();
            }

            @SuppressWarnings("unchecked")
            @Override
            public boolean contains(Object object) {
                if (object instanceof Map.Entry) {
                    Map.Entry<K,V> entry = (Map.Entry<K,V>) object;
                    K key = entry.getKey();
                    if (subMap.isInRange(key)) {
                        V v1 = subMap.get(key), v2 = entry.getValue();
                        return v1 == null ? v2 == null : v1.equals(v2);
                    }
                }
                return false;
            }

        }

        static class SubMapKeySet<K,V> extends  AbstractSet<K> implements Set<K> {
            SubMap<K,V> subMap;

            SubMapKeySet(SubMap<K,V> map) {
                subMap = map;
            }

            @Override
            public boolean contains(Object object) {
                return subMap.containsKey(object);
            }

            @Override
            public boolean isEmpty() {
                return subMap.isEmpty();
            }

            @Override
            public int size() {
                return subMap.size();
            }

            @Override
            public Iterator<K> iterator() {
                if(subMap.backingMap.isSmall()) {
                   int start, end;
                   if(subMap.hasStart) {
                       start = subMap.backingMap.smallFindAfter(subMap.startKey);
                   } else {
                       start = subMap.backingMap.small_left;
                   }
                    if(subMap.hasEnd) {
                        end = subMap.backingMap.smallFindBefore(subMap.endKey);
                    } else {
                        end = subMap.backingMap.small_right;
                    }
                   return new SmallKeyIterator<K,V>(subMap.backingMap,start,end);
                }
                TreeMap.Entry<K,V> startNode = subMap.firstEntry();
                if (subMap.hasEnd) {
                    TreeMap.Entry<K,V> lastNode = subMap.lastEntry();
                    return new BoundedKeyIterator<K,V>(subMap.backingMap, startNode, lastNode);
                }
                return new UnboundedKeyIterator<K,V>(subMap.backingMap, startNode);
            }
        }

        static class SubMapValuesCollection<K,V> extends AbstractCollection<V> {
            SubMap<K,V> subMap;

            public SubMapValuesCollection(SubMap<K,V> subMap) {
                this.subMap = subMap;
            }

            @Override
            public boolean isEmpty() {
                return subMap.isEmpty();
            }

            @Override
            public Iterator<V> iterator() {
                if(subMap.backingMap.isSmall()) {
                   int start, end;
                   if(subMap.hasStart) {
                       start = subMap.backingMap.smallFindAfter(subMap.startKey);
                   } else {
                       start = subMap.backingMap.small_left;
                   }
                    if(subMap.hasEnd) {
                        end = subMap.backingMap.smallFindBefore(subMap.endKey);
                    } else {
                        end = subMap.backingMap.small_right;
                    }
                   return new SmallValueIterator<K,V>(subMap.backingMap,start,end); 
                }
                TreeMap.Entry<K,V> startNode = subMap.firstEntry();
                if (subMap.hasEnd) {
                    TreeMap.Entry<K,V> lastNode = subMap.lastEntry();
                    return new BoundedValueIterator<K,V>(subMap.backingMap, startNode, lastNode);
                }
                return new UnboundedValueIterator<K,V>(subMap.backingMap, startNode);
            }

            @Override
            public int size() {
                return subMap.size();
            }
        }

    private void createSmall(){
        small_keys = (K[])new Object[SMALL_LIMIT];
        small_values = (V[])new Object[SMALL_LIMIT];
    }

    private void clearSmall(){
        small_keys = null;
        small_values = null;
        small_entries = null;
        small_right = -1;
        small_left = 0;
    }

    private boolean isSmall(){
        return small_keys!=null;
    }

	/**
	 * Constructs a new empty instance of TreeMap.
	 *
	 */
	public TreeMap() {
        createSmall();
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
        createSmall();
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
        this.comparator = map.comparator();
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
	@Override
    public void clear() {
		root = null;
		size = 0;
		modCount++;
        clearSmall();
        createSmall();
	}

	/**
	 * Answers a new TreeMap with the same mappings, size and comparator as this
	 * TreeMap.
	 *
	 * @return a shallow copy of this TreeMap
	 *
	 * @see java.lang.Cloneable
	 */
	@SuppressWarnings("unchecked")
    @Override
    public Object clone() {
		try {
			TreeMap<K, V> clone = (TreeMap<K, V>) super.clone();
			clone.entrySet = null;
			if (root != null) {
                clone.root = root.clone(null);
            } else if(isSmall()) {
                clone.createSmall();
                clone.small_entries = null;
                System.arraycopy(small_keys,0,clone.small_keys,0,small_keys.length);
                System.arraycopy(small_values,0,clone.small_values,0,small_values.length);
                clone.small_left = small_left;
                clone.small_right = small_right;
            }
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
    @Override
    public boolean containsKey(Object key) {
		return isSmall() ? smallFind(key)>=0 : find(key) != null;
	}

	/**
	 * Searches this TreeMap for the specified value.
	 *
	 * @param value
	 *            the object to search for
	 * @return true if <code>value</code> is a value of this TreeMap, false
	 *         otherwise
	 */
    @Override
    public boolean containsValue(Object value) {
        if (root != null) {
            return containsValue(root, value);
        } else if (size > 0 & isSmall()) {
            if (value == null) {
                for (int i = small_left; i <= small_right; i++) {
                    if (small_values[i] == null) {
                        return true;
                    }
                }
            } else {
                for (int i = small_left; i <= small_right; i++) {
                    if (value.equals(small_values[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

	private boolean containsValue(Entry<K, V> node, Object value) {
		if (value == null ? node.value == null : value.equals(node.value)) {
            return true;
        }
		if (node.left != null) {
            if (containsValue(node.left, value)) {
                return true;
            }
        }
		if (node.right != null) {
            if (containsValue(node.right, value)) {
                return true;
            }
        }
		return false;
	}

	/**
	 * Answers a Set of the mappings contained in this TreeMap. Each element in
	 * the set is a Map.Entry. The set is backed by this TreeMap so changes to
	 * one are reflected by the other. The set does not support adding.
	 *
	 * @return a Set of the mappings
	 */
	@Override
    public Set<Map.Entry<K, V>> entrySet() {
        if (entrySet == null) {
            entrySet = new AbstractSet<Map.Entry<K, V>>() {
                 @Override
                public int size() {
                    return size;
                }

                @Override
                public void clear() {
                    TreeMap.this.clear();
                }

                @SuppressWarnings("unchecked")
                @Override
                public boolean contains(Object object) {
                    if (object instanceof Map.Entry) {
                        Map.Entry<K, V> entry = (Map.Entry<K, V>) object;
                        Object v1 = get(entry.getKey()), v2 = entry.getValue();
                        return v1 == null ? v2 == null : v1.equals(v2);
                    }
                    return false;
                }

                @Override
                public Iterator<Map.Entry<K, V>> iterator() {
                    if(isSmall()) {
                       return new SmallEntryIterator<K,V>(TreeMap.this,TreeMap.this.small_left,TreeMap.this.small_right);
                    }
                    return new UnboundedEntryIterator<K, V>(TreeMap.this);
                }
            };
        }
        return entrySet;
    }

    /** @return the non-negative index of the element, or a negative index which
      *         is the -index - 1 where the element would be inserted
      */
    private int smallFind(Object keyObj) {
        if (small_left > small_right) {
            return -1;
        }
        K key = (K) keyObj;
        if (comparator == null) {
            Comparable<K> object = toComparable(key);
            int low = small_left, mid = 0, high = small_right, result = 0;
            while (low <= high) {
                mid = (low + high) >> 1;
                if ((result = object.compareTo(small_keys[mid])) > 0) {
                    low = mid + 1;
                } else if (result == 0) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
            return -mid - (result <= 0 ? 1 : 2);
        } else {
            int low = small_left, mid = 0, high = small_right, result = 0;
            while (low <= high) {
                mid = (low + high) >> 1;
                if ((result = comparator.compare(key, small_keys[mid])) > 0) {
                    low = mid + 1;
                } else if (result == 0) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
            return -mid - (result <= 0 ? 1 : 2);
        }
    }

	@SuppressWarnings("unchecked")
    private Entry<K, V> find(Object keyObj) {
		int result;
        K key = (K)keyObj;
		Comparable<K> object = null;
		if (comparator == null) {                                                        
            object = toComparable(key);
    		Entry<K, V> x = root;
    		while (x != null) {
    			result = object.compareTo(x.key);
    			if (result == 0) {
                    return x;
                }
    			x = result < 0 ? x.left : x.right;
    		}
        } else {
    		Entry<K, V> x = root;
    		while (x != null) {
    			result = comparator.compare(key, x.key);
    			if (result == 0) {
                    return x;
                }
    			x = result < 0 ? x.left : x.right;
    		}
        }
		return null;
	}

    int smallFindAfter(Object keyObj) {
        int idx = smallFind(keyObj);
        if(idx>=0) {
            return idx;
        } else {
            idx = -idx - 1;
            return (idx>small_right) ? -1 : idx;
        }
    }

    int smallFindBefore(Object keyObj) {
        int idx = smallFind(keyObj);
        if(idx>=0) {
            return (idx==small_left) ? -1 : idx-1;
        } else {
            idx = -idx - 1;
            return (idx<=small_left) ? -1 : idx-1;
        }
    }

	@SuppressWarnings("unchecked")
    Entry<K, V> findAfter(Object keyObj) {
        K key = (K)keyObj;
		int result;
		Comparable<K> object = null;
		if (comparator == null) {
            object = toComparable(key);
        }
		Entry<K, V> x = root, last = null;
		while (x != null) {
			result = object != null ? object.compareTo(x.key) : comparator
					.compare(key, x.key);
			if (result == 0) {
                return x;
            }
			if (result < 0) {
				last = x;
				x = x.left;
			} else {
                x = x.right;
            }
		}
		return last;
	}

	Entry<K, V> findBefore(K key) {
		int result;
        Comparable<K> object = null;
		if (comparator == null) {
            object = toComparable(key);
        }
		Entry<K, V> x = root, last = null;
		while (x != null) {
			result = object != null ? object.compareTo(x.key) : comparator
					.compare(key, x.key);
			if (result <= 0) {
                x = x.left;
            } else {
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
		if (root != null) {
            return minimum(root).key;
        } else if(size>0 & isSmall()) {
            return small_keys[small_left];
        }
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
	@Override
    public V get(Object key) {
        if(isSmall()) {
            int idx = smallFind(key);
            if(idx >= 0) {
                return small_values[idx];
            }
        } else {
		    Entry<K, V> node = find(key);
		    if (node != null) {
                return node.value;
            }
        }
		return null;
	}

	/**
	 * Answers a SortedMap of the specified portion of this TreeMap which
	 * contains keys less than the end key. The returned SortedMap is backed by
	 * this TreeMap so changes to one are reflected by the other.
	 *
	 * @param endKey
	 *            the end key
	 * @return a sub-map where the keys are less than <code>endKey</code>
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
		if (comparator == null) {
            toComparable(endKey).compareTo(endKey);
        } else {
            comparator.compare(endKey, endKey);
        }
		return new SubMap<K, V>(this, endKey);
	}

	/**
	 * Answers a Set of the keys contained in this TreeMap. The set is backed by
	 * this TreeMap so changes to one are reflected by the other. The set does
	 * not support adding.
	 *
	 * @return a Set of the keys
	 */
	@Override
    public Set<K> keySet() {
		if (keySet == null) {
			keySet = new AbstractSet<K>() {
				@Override
                public boolean contains(Object object) {
					return containsKey(object);
				}

				@Override
                public int size() {
					return size;
				}

				@Override
                public void clear() {
					TreeMap.this.clear();
				}

				@Override
                public Iterator<K> iterator() {
                    if(isSmall()) {
                       return new SmallKeyIterator<K,V>(TreeMap.this,TreeMap.this.small_left,TreeMap.this.small_right);
                    }
                    return new UnboundedKeyIterator<K,V> (TreeMap.this);
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
		if (root != null) {
            return maximum(root).key;
        } else if(size>0 && isSmall()) {
            return small_keys[small_right];
        }
		throw new NoSuchElementException();
	}

	private void leftRotate(Entry<K, V> x) {
		Entry<K, V> y = x.right;
		x.right = y.left;
		if (y.left != null) {
            y.left.parent = x;
        }
		y.parent = x.parent;
		if (x.parent == null) {
			root = y;
		} else {
			if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
		}
		y.left = x;
		x.parent = y;
	}

	static <K, V> Entry<K, V> maximum(Entry<K, V> x) {
		while (x.right != null) {
            x = x.right;
        }
		return x;
	}

	static <K, V> Entry<K, V> minimum(Entry<K, V> x) {
		while (x.left != null) {
            x = x.left;
        }
		return x;
	}

	static <K, V> Entry<K, V> predecessor(Entry<K, V> x) {
		if (x.left != null) {
            return maximum(x.left);
        }
		Entry<K, V> y = x.parent;
		while (y != null && x == y.left) {
			x = y;
			y = y.parent;
		}
		return y;
	}

    private Entry<K, V> smallDeflate(int from, int to){
        int elem = (from+to)>>1;
        Entry<K, V> entry = small_entries==null? null:small_entries[elem];
        if(entry == null) {
            entry= new Entry<K, V>(small_keys[elem],small_values[elem]);
        }
        if(from<=elem-1) {
            entry.left = smallDeflate(from,elem-1);
            entry.left.parent = entry;
        }
        if(elem+1<=to) {
            entry.right = smallDeflate(elem+1,to);
            entry.right.parent = entry;
        }
        return entry;
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
	@Override
    public V put(K key, V value) {
        if(isSmall()) {
            if(small_left>small_right) {
                small_left = small_right = 0;
                small_keys[0] = key;
                small_values[0] = value;
                modCount++;
                size++;
                return null;
            } else {
                int idx = smallFind(key);
                if(idx>=0) {
                    V res = small_values[idx];
                    small_values[idx] = value;
                    if(small_entries!=null && small_entries[idx]!=null) {
                        small_entries[idx].value = value;
                    }
                    return res;
                }
                if(size == SMALL_LIMIT) {
                    root = smallDeflate(small_left,small_right);
                    clearSmall();
                } else {
                    int newIdx = -idx - 1;

                    if((small_left==0)||((small_right - newIdx) <= (newIdx - small_left) && ((small_right < SMALL_LIMIT-1)))) {
                        // move right
                        System.arraycopy(small_keys,newIdx,small_keys,newIdx+1,small_right-newIdx+1);
                        System.arraycopy(small_values,newIdx,small_values,newIdx+1,small_right-newIdx+1);
                        if(small_entries!=null) {
                            System.arraycopy(small_entries,newIdx,small_entries,newIdx+1,small_right-newIdx+1);
                        }
//                        for(int i=small_right; i >= newIdx; i--){
//                            small_keys[i+1] = small_keys[i];
//                            small_values[i+1] = small_values[i];
//                            if(small_entries!=null) {
//                                small_entries[i+1] = small_entries[i];
//                            }
//                        }
                        small_right++;
                    } else {
                        System.arraycopy(small_keys,small_left,small_keys,small_left-1,newIdx-small_left);
                        System.arraycopy(small_values,small_left,small_values,small_left-1,newIdx-small_left);
                        if(small_entries!=null) {
                            System.arraycopy(small_entries,small_left,small_entries,small_left-1,newIdx-small_left);
                        }
//                        for (int i = small_left; i < newIdx; i++) {
//                            small_keys[i - 1] = small_keys[i];
//                            small_values[i - 1] = small_values[i];
//                            if (small_entries != null) {
//                                small_entries[i - 1] = small_entries[i];
//                            }
//                        }
                        small_left--;
                        newIdx--;
                    }
                    small_keys[newIdx] = key;
                    small_values[newIdx] = value;
                    if(small_entries!=null) {
                        small_keys[newIdx] = null;
                    }
                    size++;
                    modCount++;
                    return null;
                }
            }
        }
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
	@Override
    public void putAll(Map<? extends K, ? extends V> map) {
		super.putAll(map);
	}

	void rbDelete(Entry<K, V> z) {
		Entry<K, V> y = z.left == null || z.right == null ? z : successor(z);
		Entry<K, V> x = y.left != null ? y.left : y.right;
		if (x != null) {
            x.parent = y.parent;
        }
		if (y.parent == null) {
            root = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }
		modCount++;
		if (y != z) {
			z.key = y.key;
			z.value = y.value;
		}
		if (!y.color && root != null) {
			if (x == null) {
                fixup(y.parent);
            } else {
                fixup(x);
            }
		}
        y.left = y.right = y.parent = null;
		size--;
	}

	private Entry<K, V> rbInsert(K object) {
		int result = 0;
		Entry<K, V> y = null;
        if (size != 0) {
            Comparable<K> key = null;
            if (comparator == null) {
                key = toComparable(object);
                Entry<K, V> x = root;
                while (x != null) {
                    y = x;
                    result = key.compareTo(x.key);
                    if (result == 0) {
                        return x;
                    }
                    x = result < 0 ? x.left : x.right;
                }
            } else {
                Entry<K, V> x = root;
                while (x != null) {
                    y = x;
                    result = comparator.compare(object, x.key);
                    if (result == 0) {
                        return x;
                    }
                    x = result < 0 ? x.left : x.right;
                }
            }

        }
		size++;
		modCount++;
		Entry<K, V> z = new Entry<K, V>(object);
		if (y == null) {
            return root = z;
        }
		z.parent = y;
		if (result < 0) {
            y.left = z;
        } else {
            y.right = z;
        }
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
	@Override
    public V remove(Object key) {
		if (size == 0) {
			return null;
		}
        if(isSmall()){
            int idx = smallFind(key);
            if(idx<0) {
                return null;
            }
            V result = small_values[idx];
            smallDelete(idx);
            return result;
        } else {
		    Entry<K, V> node = find(key);
		    if (node == null) {
                return null;
            }
		    V result = node.value;
		    rbDelete(node);
		    return result;
        }
	}

    private boolean smallDelete(int idx) {
        size--;
        modCount++;
        if(idx==small_left) {
            small_keys[idx] = null;
            small_values[idx] = null;
            if(small_entries!=null) {
                small_keys[idx] = null;
            }
            small_left++;
            return false;
        } else if(idx==small_right) {
            small_right--;
            small_keys[idx] = null;
            small_values[idx] = null;
            if(small_entries!=null) {
                small_keys[idx] = null;
            }
        } else {

            System.arraycopy(small_keys,idx+1,small_keys,idx,small_right-idx);
            System.arraycopy(small_values,idx+1,small_values,idx,small_right-idx);
            if(small_entries!=null) {
                System.arraycopy(small_entries,idx+1,small_entries,idx,small_right-idx);
            }
//            for(int i=idx+1; i<=small_right; i++) {
//                small_keys[i-1] = small_keys[i];
//                small_values[i-1] = small_values[i];
//                if(small_entries!=null) {
//                    small_entries[i-1] = small_entries[i];
//                }
//            }
            small_right--;
        }
        return true;
    }

    private void rightRotate(Entry<K, V> x) {
		Entry<K, V> y = x.left;
		x.left = y.right;
		if (y.right != null) {
            y.right.parent = x;
        }
		y.parent = x.parent;
		if (x.parent == null) {
			root = y;
		} else {
			if (x == x.parent.right) {
                x.parent.right = y;
            } else {
                x.parent.left = y;
            }
		}
		y.right = x;
		x.parent = y;
	}

	/**
	 * Answers the number of mappings in this TreeMap.
	 *
	 * @return the number of mappings in this TreeMap
	 */
	@Override
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
	 * @return a sub-map where the keys are greater or equal to
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
			if (toComparable(startKey).compareTo(endKey) <= 0) {
                return new SubMap<K, V>(startKey, this, endKey);
            }
		} else {
			if (comparator.compare(startKey, endKey) <= 0) {
                return new SubMap<K, V>(startKey, this, endKey);
            }
		}
		throw new IllegalArgumentException();
	}

	static <K, V> Entry<K, V> successor(Entry<K, V> x) {
		if (x.right != null) {
            return minimum(x.right);
        }
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
	 * @return a sub-map where the keys are greater or equal to
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
		if (comparator == null) {
            toComparable(startKey).compareTo(startKey);
        } else {
            comparator.compare(startKey, startKey);
        }
		return new SubMap<K, V>(startKey, this);
	}

	/**
	 * Answers a Collection of the values contained in this TreeMap. The
	 * collection is backed by this TreeMap so changes to one are reflected by
	 * the other. The collection does not support adding.
	 *
	 * @return a Collection of the values
	 */
	@Override
    public Collection<V> values() {
		if (valuesCollection == null) {
			valuesCollection = new AbstractCollection<V>() {
				@Override
                public boolean contains(Object object) {
					return containsValue(object);
				}

				@Override
                public int size() {
					return size;
				}

				@Override
                public void clear() {
					TreeMap.this.clear();
				}

				@Override
                public Iterator<V> iterator() {
                    if(isSmall()) {
                       return new SmallValueIterator<K,V>(TreeMap.this,TreeMap.this.small_left,TreeMap.this.small_right);
                    }
                    return new UnboundedValueIterator<K,V> (TreeMap.this);
				}
			};
		}
		return valuesCollection;
	}

	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
		stream.writeInt(size);
		if (size > 0) {
            if(isSmall()) {
                for(int i=small_left; i<=small_right; i++){
                    stream.writeObject(small_keys[i]);
                    stream.writeObject(small_values[i]);
                }
            } else {
			    Entry<K, V> node = minimum(root);
		    	while (node != null) {
	    			stream.writeObject(node.key);
    				stream.writeObject(node.value);
				    node = successor(node);
			    }
            }
		}
	}

	@SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream stream) throws IOException,
			ClassNotFoundException {
		stream.defaultReadObject();
		size = stream.readInt();
        if(size>0 && size<SMALL_LIMIT) {
            createSmall();
            for(int i=0;i<size; i++) {
                small_keys[i] = (K)stream.readObject();
                small_values[i] = (V)stream.readObject();
            }
            small_left=0;
            small_right = size-1;
        } else {
		    Entry<K, V> last = null;
		    for (int i = size; --i >= 0;) {
		    	Entry<K, V> node = new Entry<K, V>((K)stream.readObject());
	    		node.value = (V)stream.readObject();
    			if (last == null) {
                    root = node;
                } else {
	    			node.parent = last;
    				last.right = node;
				    balance(node);
			    }
			    last = node;
		    }
        }
	}
}
