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
 * TreeMap is an implementation of NavigableMap. All optional operations are
 * supported, adding and removing. The values can be any objects. The keys can
 * be any objects which are comparable to each other either using their natural
 * order or a specified Comparator.
 * 
 * @param <K>
 *            type of key
 * @param <V>
 *            type of value
 * 
 * @since 1.2
 */
public class TreeMap<K, V> extends AbstractMap<K, V> implements
        NavigableMap<K, V>, Cloneable, Serializable {
    private static final long serialVersionUID = 919286545866124006L;

    transient int size;

    transient Entry<K, V> root;

    Comparator<? super K> comparator;

    transient int modCount;

    transient Set<Map.Entry<K, V>> entrySet;

    transient NavigableMap<K, V> descendingMap;

    transient NavigableSet<K> navigableKeySet;

    /**
     * Entry is an internal class which is used to hold the entries of a
     * TreeMap.
     */
    static class Entry<K, V> extends MapEntry<K, V> {
        Entry<K, V> parent, left, right;

        boolean color;

        Entry(K theKey) {
            super(theKey);
        }

        Entry(K theKey, V theValue) {
            super(theKey, theValue);
        }

        @SuppressWarnings("unchecked")
        Entry<K, V> clone(Entry<K, V> theParent) {
            Entry<K, V> clone = (Entry<K, V>) super.clone();
            clone.parent = theParent;
            if (left != null) {
                clone.left = left.clone(clone);
            }
            if (right != null) {
                clone.right = right.clone(clone);
            }
            return clone;
        }
    }

    /*
     * It is a "work-around" method because of a RI's bug. The RI's bug is that:
     * if the TreeMap has no comparator or its comparator is not null-tolerable,
     * it can not put a null-key entry into this TreeMap.But RI can do it when
     * the TreeMap is empty, and can not do it when the TreeMap is not empty.It
     * is best for Harmony to follow RI's behavior for legacy reason. This
     * method is to check if this TreeMap is constructed by that bug.It can be
     * easily removed when the bug is fixed in the future.
     */
    boolean isRootWithIllegalNullKey() {
        if (null != root) {
            if (null == root.key && null == comparator) {
                return true;
            } else if (null != comparator) {
                try {
                    comparator.compare(root.key, root.key);
                } catch (NullPointerException e) {
                    return true;
                }
            }
        }
        return false;
    }

    private static abstract class AbstractSubMapIterator<K, V> {
        final NavigableSubMap<K, V> subMap;

        int expectedModCount;

        TreeMap.Entry<K, V> node;

        TreeMap.Entry<K, V> lastNode;
        
        TreeMap.Entry<K, V> boundaryNode;
        
        boolean getToEnd = false;

        AbstractSubMapIterator(final NavigableSubMap<K, V> map) {
            subMap = map;
            expectedModCount = subMap.backingMap.modCount;
            node = getStartNode();
            
            boundaryNode = getBoundaryNode();
			if (null == boundaryNode) {
				node = null;
			}
        }

        public final void remove() {
            if (expectedModCount == subMap.backingMap.modCount) {
                if (lastNode != null) {
                    if (lastNode.key == this.boundaryNode.key) {
                        node = null;
                    }
                    subMap.backingMap.rbDelete(lastNode);
                    if( null != node && node.key == lastNode.key) {
                        node = lastNode;
                    }
                    lastNode = null;
                    expectedModCount++;
                } else {
                    throw new IllegalStateException();
                }
            } else {
                throw new ConcurrentModificationException();
            }
        }

        TreeMap.Entry<K, V> getNext() {
            if (hasNext()) {
                if (expectedModCount == subMap.backingMap.modCount) {
                    lastNode = node;
					if (node.key == this.boundaryNode.key) {
						node = null;
					} else {
						node = getRealNext(node);
					}
					return lastNode;
                }
                throw new ConcurrentModificationException();
            }
            throw new NoSuchElementException();
        }

        abstract TreeMap.Entry<K, V> getStartNode();

        abstract TreeMap.Entry<K, V> getRealNext(TreeMap.Entry<K, V> entry);

        abstract boolean hasNext();
        
        abstract TreeMap.Entry<K, V> getBoundaryNode();
    }

    private abstract static class AscendingSubMapIterator<K, V> extends
            AbstractSubMapIterator<K, V> {    	
    	
    	AscendingSubMapIterator(NavigableSubMap<K, V> map) {
            super(map);            
        }
        
        final TreeMap.Entry<K, V> getBoundaryNode() {
			if (subMap.hasEnd) {
				return subMap.endInclusive ? subMap
						.smallerOrEqualEntry(subMap.endKey) : subMap
						.smallerEntry(subMap.endKey);
			}
			return subMap.theBiggestEntry();
		}

        @Override
        final TreeMap.Entry<K, V> getStartNode() {
            if (subMap.hasStart) {
                return subMap.startInclusive ? subMap
                        .biggerOrEqualEntry(subMap.startKey) : subMap
                        .biggerEntry(subMap.startKey);
            }
            return subMap.theSmallestEntry();
        }

        @Override
        final Entry<K, V> getRealNext(Entry<K, V> entry) {
            return TreeMap.successor(entry);            
        }

        @Override
        public final boolean hasNext() {
//            if (null != node) {
//                if (subMap.backingMap.root == node
//                        && subMap.backingMap.isRootWithIllegalNullKey()) {
//                    return true;
//                }
//                return subMap.checkUpperBound(node.key);
//            }
//            return false;
        	return null!=node;
        }

    }

    static class AscendingSubMapEntryIterator<K, V> extends
            AscendingSubMapIterator<K, V> implements Iterator<Map.Entry<K, V>> {

        AscendingSubMapEntryIterator(NavigableSubMap<K, V> map) {
            super(map);
        }

        public final Map.Entry<K, V> next() {
            return getNext();
        }
    }

    static class AscendingSubMapKeyIterator<K, V> extends
            AscendingSubMapIterator<K, V> implements Iterator<K> {

        AscendingSubMapKeyIterator(NavigableSubMap<K, V> map) {
            super(map);
        }

        public final K next() {
            return getNext().key;
        }
    }

    private abstract static class DescendingSubMapIterator<K, V> extends
            AbstractSubMapIterator<K, V> {

    	DescendingSubMapIterator(NavigableSubMap<K, V> map) {
            super(map);            
        }

        @Override
        final TreeMap.Entry<K, V> getStartNode() {
            if (subMap.hasEnd) {
                return subMap.endInclusive ? subMap
                        .smallerOrEqualEntry(subMap.endKey) : subMap
                        .smallerEntry(subMap.endKey);
            }
            return subMap.theBiggestEntry();
        }
        
        final TreeMap.Entry<K, V> getBoundaryNode() {
			if (subMap.hasStart) {
				return subMap.startInclusive ? subMap
						.biggerOrEqualEntry(subMap.startKey) : subMap
						.biggerEntry(subMap.startKey);
			}
			return subMap.theSmallestEntry();
		}

        @Override
        final Entry<K, V> getRealNext(Entry<K, V> entry) {
            return TreeMap.predecessor(entry);
        }

        @Override
        public final boolean hasNext() {
//            if (null != node) {
//                if (subMap.backingMap.root == node
//                        && subMap.backingMap.isRootWithIllegalNullKey()) {
//                    return true;
//                }
//                return subMap.checkLowerBound(node.key);
//            }
//            return false;
        	return node != null;
        }
    }

    static class DescendingSubMapEntryIterator<K, V> extends
            DescendingSubMapIterator<K, V> implements Iterator<Map.Entry<K, V>> {

        DescendingSubMapEntryIterator(NavigableSubMap<K, V> map) {
            super(map);
        }

        public final Map.Entry<K, V> next() {
            return getNext();
        }
    }

    static class DescendingSubMapKeyIterator<K, V> extends
            DescendingSubMapIterator<K, V> implements Iterator<K> {

        DescendingSubMapKeyIterator(NavigableSubMap<K, V> map) {
            super(map);
        }

        public final K next() {
            return getNext().key;
        }
    }

    /*
     * Entry set of sub-maps, must override methods which check the range. add
     * or addAll operations are disabled by default.
     */
    static abstract class SubMapEntrySet<K, V> extends
            AbstractSet<Map.Entry<K, V>> {
        final NavigableSubMap<K, V> subMap;

        SubMapEntrySet(NavigableSubMap<K, V> map) {
            subMap = map;
        }

        @Override
        public boolean isEmpty() {
            return 0 == size();
        }

        @Override
        public int size() {
            int size = 0;
            Iterator<Map.Entry<K, V>> it = iterator();
            while (it.hasNext()) {
                size++;
                it.next();
            }
            return size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean contains(Object object) {
            if (object instanceof Map.Entry) {
                Map.Entry<K, V> entry = (Map.Entry<K, V>) object;
                K key = entry.getKey();
                if (subMap.isInRange(key)) {
                    V v1 = subMap.get(key), v2 = entry.getValue();
                    return (v1 == null) ? v2 == null : v1.equals(v2);
                }
            }
            return false;
        }

        @SuppressWarnings("unchecked")
        @Override
        /*
         * Must added, because it need check the range, and used in
         * clear(),removeAll() methods.
         */
        public boolean remove(Object object) {
            if (object instanceof Map.Entry) {
                TreeMap.Entry<K, V> entry = (TreeMap.Entry<K, V>) object;
                K key = entry.getKey();
                if (subMap.isInRange(key)) {
                    TreeMap.Entry<K, V> node = subMap.backingMap.find(key);
                    if (node == null) {
                        return false;
                    }
                    V v1 = node.value, v2 = entry.getValue();
                    if ((v1 == null) ? (v2 == null) : v1.equals(v2)) {
                        subMap.backingMap.rbDelete(node);
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public abstract Iterator<Map.Entry<K, V>> iterator();
    }

    static class AscendingSubMapEntrySet<K, V> extends SubMapEntrySet<K, V> {

        AscendingSubMapEntrySet(NavigableSubMap<K, V> map) {
            super(map);
        }

        @Override
        public final Iterator<Map.Entry<K, V>> iterator() {
            return new AscendingSubMapEntryIterator<K, V>(subMap);
        }
    }

    static class DescendingSubMapEntrySet<K, V> extends SubMapEntrySet<K, V> {

        DescendingSubMapEntrySet(NavigableSubMap<K, V> map) {
            super(map);
        }

        @Override
        public final Iterator<Map.Entry<K, V>> iterator() {
            return new DescendingSubMapEntryIterator<K, V>(subMap);
        }
    }

    static abstract class SubMapKeySet<K> extends AbstractSet<K> implements
            NavigableSet<K> {
        final NavigableSubMap<K, K> subMap;

        NavigableSet<K> descendingSet;

        SubMapKeySet(NavigableSubMap<K, K> map) {
            subMap = map;
        }

        public Comparator<? super K> comparator() {
            return subMap.backingMap.comparator();
        }

        @Override
        public boolean contains(Object object) {
            return subMap.backingMap.containsKey(object);
        }

        @Override
        public boolean isEmpty() {
            return size() == 0;
        }

        @Override
        public int size() {
            Iterator<K> it = iterator();
            int size = 0;
            while (it.hasNext()) {
                it.next();
                size++;
            }
            return size;
        }

        @Override
        public boolean remove(Object object) {
            return null != subMap.remove(object);
        }

        @Override
        public abstract Iterator<K> iterator();

        public abstract Iterator<K> descendingIterator();

        public K first() {
            return subMap.firstKey();
        }

        public K last() {
            return subMap.lastKey();
        }

        public K pollFirst() {
            Map.Entry<K, K> entry = subMap.pollFirstEntry();
            return (null == entry) ? null : entry.getKey();
        }

        public K pollLast() {
            Map.Entry<K, K> entry = subMap.pollLastEntry();
            return (null == entry) ? null : entry.getKey();
        }

        public K higher(K key) {
            return subMap.higherKey(key);
        }

        public K lower(K key) {
            return subMap.lowerKey(key);
        }

        public K ceiling(K key) {
            return subMap.ceilingKey(key);
        }

        public K floor(K key) {
            return subMap.floorKey(key);
        }

        public NavigableSet<K> descendingSet() {
            return (null != descendingSet) ? descendingSet
                    : (descendingSet = new TreeSet<K>(subMap.descendingMap()));
        }

        public SortedSet<K> subSet(K start, K end) {
            return subSet(start, true, end, false);
        }

        public SortedSet<K> headSet(K end) {
            return headSet(end, false);
        }

        public SortedSet<K> tailSet(K start) {
            return tailSet(start, true);
        }

        @SuppressWarnings("unchecked")
        public NavigableSet<K> subSet(K start, boolean startInclusive, K end,
                boolean endInclusive) {
            // copied from TreeSet
            if (subMap.backingMap.keyCompare(start, end) <= 0) {
                return new TreeSet<K>(subMap.subMap(start, startInclusive, end,
                        endInclusive));
            }
            throw new IllegalArgumentException();
        }

        @SuppressWarnings("unchecked")
        public NavigableSet<K> headSet(K end, boolean endInclusive) {
            // copied from TreeSet
            // Check for errors
            subMap.backingMap.keyCompare(end, end);
            return new TreeSet<K>(subMap.headMap(end, endInclusive));
        }

        @SuppressWarnings("unchecked")
        public NavigableSet<K> tailSet(K start, boolean startInclusive) {
            // copied from TreeSet
            // Check for errors
            subMap.backingMap.keyCompare(start, start);
            return new TreeSet<K>(subMap.tailMap(start, startInclusive));
        }
    }

    static class AscendingSubMapKeySet<K> extends SubMapKeySet<K> {
        AscendingSubMapKeySet(NavigableSubMap<K, K> map) {
            super(map);
        }

        @Override
        public final Iterator<K> iterator() {
            return new AscendingSubMapKeyIterator<K, K>(subMap);
        }

        @Override
        public final Iterator<K> descendingIterator() {
            return new DescendingSubMapKeyIterator<K, K>(subMap);
        }
    }

    static class DescendingSubMapKeySet<K> extends SubMapKeySet<K> {
        DescendingSubMapKeySet(NavigableSubMap<K, K> map) {
            super(map);
        }

        @Override
        public final Iterator<K> iterator() {
            return new DescendingSubMapKeyIterator<K, K>(subMap);
        }

        @Override
        public final Iterator<K> descendingIterator() {
            return new AscendingSubMapKeyIterator<K, K>(subMap);
        }
    }

    static abstract class NavigableSubMap<K, V> extends AbstractMap<K, V>
            implements NavigableMap<K, V>, Serializable {

        final TreeMap<K, V> backingMap;

        final K startKey, endKey;

        final boolean hasStart, hasEnd;

        final boolean startInclusive, endInclusive;

        NavigableSubMap(final K start, final boolean startKeyInclusive,
                final TreeMap<K, V> map, final K end,
                final boolean endKeyInclusive) {
            backingMap = map;
            hasStart = hasEnd = true;
            startKey = start;
            endKey = end;
            startInclusive = startKeyInclusive;
            endInclusive = endKeyInclusive;
        }

        NavigableSubMap(final K start, final boolean startKeyInclusive,
                final TreeMap<K, V> map) {
            backingMap = map;
            hasStart = true;
            hasEnd = false;
            startKey = start;
            endKey = null;
            startInclusive = startKeyInclusive;
            endInclusive = false;
        }
        
        NavigableSubMap(final TreeMap<K, V> map, final K end,
                final boolean endKeyInclusive) {
            backingMap = map;
            hasStart = false;
            hasEnd = true;
            startKey = null;
            endKey = end;
            startInclusive = false;
            endInclusive = endKeyInclusive;
        }

        // the whole TreeMap
        NavigableSubMap(final TreeMap<K, V> map) {
            backingMap = map;
            hasStart = hasEnd = false;
            startKey = endKey = null;
            startInclusive = endInclusive = false;
        }

        /*
         * The basic public methods.
         */

        public Comparator<? super K> comparator() {
            return backingMap.comparator();
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean containsKey(Object key) {
            checkNull(key);
            if (isInRange((K) key)) {
                return backingMap.containsKey(key);
            }
            return false;
        }
        
        private void checkNull (Object key) {
            if (null == key && null ==comparator()){
                throw new NullPointerException();
            }
        }

        @Override
        public boolean isEmpty() {
            return size() == 0;
        }

        @Override
        public int size() {
            return entrySet().size();
        }

        @Override
        public V put(K key, V value) {
            checkNull(key);
            if (isInRange(key)) {
                return backingMap.put(key, value);
            }
            throw new IllegalArgumentException();
        }

        @SuppressWarnings("unchecked")
        @Override
        public V get(Object key) {
            checkNull(key);
            if (isInRange((K) key)) {
                return backingMap.get(key);
            }
            return null;
        }

        @SuppressWarnings("unchecked")
        @Override
        public V remove(Object key) {
            checkNull(key);
            if (isInRange((K) key)) {
                return backingMap.remove(key);
            }
            return null;
        }

        /*
         * The navigable methods.
         */

        public abstract Map.Entry<K, V> firstEntry();

        public abstract Map.Entry<K, V> lastEntry();

        public abstract Map.Entry<K, V> pollFirstEntry();

        public abstract Map.Entry<K, V> pollLastEntry();

        public abstract Map.Entry<K, V> higherEntry(K key);

        public abstract Map.Entry<K, V> lowerEntry(K key);

        public abstract Map.Entry<K, V> ceilingEntry(K key);

        public abstract Map.Entry<K, V> floorEntry(K key);

        public K firstKey() {
            Map.Entry<K, V> node = firstEntry();
            if (node != null) {
                return node.getKey();
            }
            throw new NoSuchElementException();
        }

        public K lastKey() {
            Map.Entry<K, V> node = lastEntry();
            if (node != null) {
                return node.getKey();
            }
            throw new NoSuchElementException();
        }

        public K higherKey(K key) {
            Map.Entry<K, V> entry = higherEntry(key);
            return (null == entry) ? null : entry.getKey();
        }

        public K lowerKey(K key) {
            Map.Entry<K, V> entry = lowerEntry(key);
            return (null == entry) ? null : entry.getKey();
        }

        public K ceilingKey(K key) {
            Map.Entry<K, V> entry = ceilingEntry(key);
            return (null == entry) ? null : entry.getKey();
        }

        public K floorKey(K key) {
            Map.Entry<K, V> entry = floorEntry(key);
            return (null == entry) ? null : entry.getKey();
        }

        /*
         * The sub-collection methods.
         */

        public abstract NavigableSet<K> navigableKeySet();

        @Override
        public abstract Set<Map.Entry<K, V>> entrySet();

        @Override
        public Set<K> keySet() {
            return navigableKeySet();
        }

        public NavigableSet<K> descendingKeySet() {
            return descendingMap().navigableKeySet();
        }

        public SortedMap<K, V> subMap(K start, K end) {
            return subMap(start, true, end, false);
        }

        public SortedMap<K, V> headMap(K end) {
            return headMap(end, false);
        }

        public SortedMap<K, V> tailMap(K start) {
            return tailMap(start, true);
        }

        public abstract NavigableMap<K, V> subMap(K start,
                boolean startKeyInclusive, K end, boolean endKeyInclusive);

        public abstract NavigableMap<K, V> headMap(K end, boolean inclusive);

        public abstract NavigableMap<K, V> tailMap(K start, boolean inclusive);

        /**
         * 
         * @param key
         * @return false if the key bigger than the end key (if any)
         */
        final boolean checkUpperBound(K key) {
            if (hasEnd) {
                int result = backingMap.keyCompare(key, endKey);
                return endInclusive ? result <= 0 : result < 0;
            }
            return true;
        }

        /**
         * 
         * @param key
         * @return false if the key smaller than the start key (if any)
         */
        final boolean checkLowerBound(K key) {
            if (hasStart) {
                int result = -backingMap.keyCompare(startKey, key);
                return startInclusive ? result >= 0 : result > 0;
            }
            return true;
        }

        final boolean isInRange(K key) {
            return checkUpperBound(key) && checkLowerBound(key);
        }

        final TreeMap.Entry<K, V> theSmallestEntry() {
            if (backingMap.isRootWithIllegalNullKey()) {
                return backingMap.root;
            }
            TreeMap.Entry<K, V> result = null;
            if (!hasStart) {
                result = backingMap.findSmallestEntry();
            } else {
                result = startInclusive ? backingMap.findCeilingEntry(startKey)
                        : backingMap.findHigherEntry(startKey);
            }
            return (null != result && checkUpperBound(result.getKey())) ? result
                    : null;
        }

        final TreeMap.Entry<K, V> theBiggestEntry() {
            if (backingMap.isRootWithIllegalNullKey()) {
                return backingMap.root;
            }
            TreeMap.Entry<K, V> result = null;
            if (!hasEnd) {
                result = backingMap.findBiggestEntry();
            } else {
                result = endInclusive ? backingMap.findFloorEntry(endKey)
                        : backingMap.findLowerEntry(endKey);
            }
            return (null != result && checkLowerBound(result.getKey())) ? result
                    : null;
        }
        
        final TreeMap.Entry<K, V> smallerOrEqualEntry(K key) {
            if (backingMap.isRootWithIllegalNullKey()) {
                return backingMap.root;
            }
            TreeMap.Entry<K, V> result = findFloorEntry(key);
            return (null != result && checkLowerBound(result.getKey())) ? result
                    : null;
        }
        
        TreeMap.Entry<K, V> findFloorEntry(K key) {
            int result;
            boolean belowUpper = false;
            boolean aboveLower  = false;
            TreeMap.Entry<K, V> node = backingMap.root, last = null;
            while (node != null) {
                belowUpper = checkUpperBound(node.key);
                aboveLower  = checkLowerBound(node.key);
                result = backingMap.keyCompare(key, node.key);
                if (result == 0 && aboveLower && belowUpper) {
                    return node;
                } else if (0 < result && belowUpper || result == 0 && !aboveLower) {
                    last = node;
                    node = node.right;
                } else if( 0 > result && aboveLower || result == 0 && !belowUpper){
                    node = node.left;
                } else {
                    node = null;
                }
            }
            return last;
        }

        final TreeMap.Entry<K, V> biggerOrEqualEntry(K key) {
            if (backingMap.isRootWithIllegalNullKey()) {
                return backingMap.root;
            }
            TreeMap.Entry<K, V> result = findCeilingEntry(key);
            return (null != result && checkUpperBound(result.getKey())) ? result
                    : null;
        }
        
        TreeMap.Entry<K, V> findCeilingEntry(K key) {
            int result;
            boolean belowUpper = false;
            boolean aboveLower  = false;
            TreeMap.Entry<K, V> node = backingMap.root, last = null;
            while (node != null) {
                belowUpper = checkUpperBound(node.key);
                aboveLower  = checkLowerBound(node.key);
                result = backingMap.keyCompare(key, node.key);
                if (result == 0 && aboveLower && belowUpper) {
                    return node;
                }
                else if (0 > result && aboveLower || result == 0 && !belowUpper) {
                    last = node;
                    node = node.left;
                }
                else if (0 < result && belowUpper || result == 0 && !aboveLower){
                    node = node.right;
                } else {
                    node = null;
                }
            }
            return last;
        }

        final TreeMap.Entry<K, V> smallerEntry(K key) {
            if (backingMap.isRootWithIllegalNullKey()) {
                return backingMap.root;
            }
            TreeMap.Entry<K, V> result = findLowerEntry(key);
            return (null != result && checkLowerBound(result.getKey())) ? result
                    : null;
        }
        
        TreeMap.Entry<K, V> findLowerEntry(K key) {
            int result = -1;
            boolean belowUpper = true;
            TreeMap.Entry<K, V> node = backingMap.root, last = null;
            while (node != null) {
                belowUpper = checkUpperBound(node.key);
                result = backingMap.keyCompare(key, node.key);
                if (result > 0 && belowUpper) {
                    last = node;
                    node = node.right;
                } else {
                    node = node.left;
                }
            }
            return last;
        }

        final TreeMap.Entry<K, V> biggerEntry(K key) {
            if (backingMap.isRootWithIllegalNullKey()) {
                return backingMap.root;
            }
            TreeMap.Entry<K, V> result = findHigherEntry(key);
            return (null != result && checkUpperBound(result.getKey())) ? result
                    : null;
        }
        
        TreeMap.Entry<K, V> findHigherEntry(K key) {
            int result = -1;
            boolean aboveLower  = true;
            TreeMap.Entry<K, V> node = backingMap.root, last = null;
            while (node != null) {
                aboveLower = checkLowerBound(node.key);
                result = backingMap.keyCompare(key, node.key);
                if (result < 0 && aboveLower) {
                    last = node;
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
            return last;
        }

    }

    static class AscendingSubMap<K, V> extends NavigableSubMap<K, V> implements
            Serializable {

        private static final long serialVersionUID = 912986545866124060L;

        AscendingSubMap(K start, boolean startKeyInclusive, TreeMap<K, V> map,
                K end, boolean endKeyInclusive) {
            super(start, startKeyInclusive, map, end, endKeyInclusive);
        }

        AscendingSubMap(TreeMap<K, V> map, K end, boolean endKeyInclusive) {
            super(map, end, endKeyInclusive);
        }

        AscendingSubMap(K start, boolean startKeyInclusive, TreeMap<K, V> map) {
            super(start, startKeyInclusive, map);
        }

        AscendingSubMap(TreeMap<K, V> map) {
            super(map);
        }

        @Override
        public Map.Entry<K, V> firstEntry() {
            return backingMap.newImmutableEntry(theSmallestEntry());
        }

        @Override
        public Map.Entry<K, V> lastEntry() {
            return backingMap.newImmutableEntry(theBiggestEntry());
        }

        @Override
        public Map.Entry<K, V> pollFirstEntry() {
            TreeMap.Entry<K, V> node = theSmallestEntry();
            SimpleImmutableEntry<K, V> result = backingMap
                    .newImmutableEntry(node);
            if (null != node) {
                backingMap.rbDelete(node);
            }
            return result;
        }

        @Override
        public Map.Entry<K, V> pollLastEntry() {
            TreeMap.Entry<K, V> node = theBiggestEntry();
            SimpleImmutableEntry<K, V> result = backingMap
                    .newImmutableEntry(node);
            if (null != node) {
                backingMap.rbDelete(node);
            }
            return result;
        }

        @Override
        public Map.Entry<K, V> higherEntry(K key) {
            return backingMap.newImmutableEntry(biggerEntry(key));
        }

        @Override
        public Map.Entry<K, V> lowerEntry(K key) {
            return backingMap.newImmutableEntry(smallerEntry(key));
        }

        @Override
        public Map.Entry<K, V> ceilingEntry(K key) {
            return backingMap.newImmutableEntry(biggerOrEqualEntry(key));
        }

        @Override
        public Map.Entry<K, V> floorEntry(K key) {
            return backingMap.newImmutableEntry(smallerOrEqualEntry(key));
        }

        @Override
        public Set<Map.Entry<K, V>> entrySet() {
            return new AscendingSubMapEntrySet<K, V>(this);
        }

        @SuppressWarnings("unchecked")
        @Override
        public NavigableSet<K> navigableKeySet() {
            return new AscendingSubMapKeySet(this);
        }

        public NavigableMap<K, V> descendingMap() {
            if (hasStart && hasEnd) {
                return new DescendingSubMap<K, V>(startKey, startInclusive,
                        backingMap, endKey, endInclusive);
            }
            if (hasStart) {
                return new DescendingSubMap<K, V>(startKey, startInclusive,
                        backingMap);
            }
            if (hasEnd) {
                return new AscendingSubMap<K, V>(backingMap, endKey, endInclusive);
            }
            return new DescendingSubMap<K, V>(backingMap);
        }

        @Override
        public NavigableMap<K, V> subMap(K start, boolean startKeyInclusive,
                K end, boolean endKeyInclusive) {
            if (backingMap.keyCompare(start, end) <= 0) {
                if ((hasStart && backingMap.keyCompare(start, startKey) < 0)
                        || (hasEnd && backingMap.keyCompare(end, endKey) > 0)) {
                    throw new IllegalArgumentException();
                }
                K inclusiveStart = startKeyInclusive ? start : higherKey(start);
                K inclusiveEnd = endKeyInclusive ? end : lowerKey(end);
                boolean legalStart = (null == inclusiveStart)
                        || checkLowerBound(inclusiveStart);
                boolean legalEnd = (null == inclusiveEnd)
                        || checkUpperBound(inclusiveEnd);
                if (legalStart && legalEnd) {
                    return new AscendingSubMap<K, V>(start, startKeyInclusive,
                            backingMap, end, endKeyInclusive);
                }
            }
            throw new IllegalArgumentException();
        }

        @Override
        public NavigableMap<K, V> headMap(K end, boolean inclusive) {
            if (hasEnd && backingMap.keyCompare(end, endKey) > 0) {
                throw new IllegalArgumentException();
            }
            K inclusiveEnd = inclusive ? end : lowerKey(end);
            if (null == inclusiveEnd || checkUpperBound(inclusiveEnd)) {
                if (this.hasStart) {
                    return new AscendingSubMap<K, V>(this.startKey,
                            this.startInclusive, backingMap, end, inclusive);
                }
                return new AscendingSubMap<K, V>(backingMap, end, inclusive);
            }
            throw new IllegalArgumentException();
        }

        @Override
        public NavigableMap<K, V> tailMap(K start, boolean inclusive) {
            if (hasStart && backingMap.keyCompare(start, startKey) < 0) {
                throw new IllegalArgumentException();
            }
            K inclusiveStart = inclusive ? start : higherKey(start);
            if (null == inclusiveStart || checkLowerBound(inclusiveStart)) {
                if (this.hasEnd) {
                    return new AscendingSubMap<K, V>(start, inclusive,
                            backingMap, this.endKey, this.endInclusive);
                }
                return new AscendingSubMap<K, V>(start, inclusive, backingMap);

            }
            throw new IllegalArgumentException();
        }
    }

    static class DescendingSubMap<K, V> extends NavigableSubMap<K, V> implements
            Serializable {
        private static final long serialVersionUID = 912986545866120460L;

        private final Comparator<? super K> reverseComparator = Collections
                .reverseOrder(backingMap.comparator);

        DescendingSubMap(K start, boolean startKeyInclusive, TreeMap<K, V> map,
                K end, boolean endKeyInclusive) {
            super(start, startKeyInclusive, map, end, endKeyInclusive);
        }

        DescendingSubMap(K start, boolean startKeyInclusive, TreeMap<K, V> map) {
            super(start, startKeyInclusive, map);
        }

        DescendingSubMap(TreeMap<K, V> map, K end, boolean endKeyInclusive) {
            super(map, end, endKeyInclusive);
        }

        DescendingSubMap(TreeMap<K, V> map) {
            super(map);
        }

        @Override
        public Comparator<? super K> comparator() {
            return reverseComparator;
        }

        @Override
        public Map.Entry<K, V> firstEntry() {
            return backingMap.newImmutableEntry(theBiggestEntry());
        }

        @Override
        public Map.Entry<K, V> lastEntry() {
            return backingMap.newImmutableEntry(theSmallestEntry());
        }

        @Override
        public Map.Entry<K, V> pollFirstEntry() {
            TreeMap.Entry<K, V> node = theBiggestEntry();
            SimpleImmutableEntry<K, V> result = backingMap
                    .newImmutableEntry(node);
            if (null != node) {
                backingMap.rbDelete(node);
            }
            return result;
        }

        @Override
        public Map.Entry<K, V> pollLastEntry() {
            TreeMap.Entry<K, V> node = theSmallestEntry();
            SimpleImmutableEntry<K, V> result = backingMap
                    .newImmutableEntry(node);
            if (null != node) {
                backingMap.rbDelete(node);
            }
            return result;
        }

        @Override
        public Map.Entry<K, V> higherEntry(K key) {
            return backingMap.newImmutableEntry(smallerEntry(key));
        }

        @Override
        public Map.Entry<K, V> lowerEntry(K key) {
            return backingMap.newImmutableEntry(biggerEntry(key));
        }

        @Override
        public Map.Entry<K, V> ceilingEntry(K key) {
            return backingMap.newImmutableEntry(smallerOrEqualEntry(key));
        }

        @Override
        public Map.Entry<K, V> floorEntry(K key) {
            return backingMap.newImmutableEntry(biggerOrEqualEntry(key));
        }

        @Override
        public Set<Map.Entry<K, V>> entrySet() {
            return new DescendingSubMapEntrySet<K, V>(this);
        }

        @SuppressWarnings("unchecked")
        @Override
        public NavigableSet<K> navigableKeySet() {
            return new DescendingSubMapKeySet(this);
        }

        public NavigableMap<K, V> descendingMap() {
            if (hasStart && hasEnd) {
                return new AscendingSubMap<K, V>(startKey, startInclusive,
                        backingMap, endKey, endInclusive);
            }
            if (hasStart) {
                return new AscendingSubMap<K, V>(startKey, startInclusive,
                        backingMap);
            }
            if (hasEnd) {
                return new AscendingSubMap<K, V>(backingMap, endKey, endInclusive);
            }
            return new AscendingSubMap<K, V>(backingMap);
        }

        int keyCompare(K left, K right) {
            return (null != reverseComparator) ? reverseComparator.compare(
                    left, right) : toComparable(left).compareTo(right);
        }

        @Override
        public NavigableMap<K, V> subMap(K start, boolean startKeyInclusive,
                K end, boolean endKeyInclusive) {
            if (this.keyCompare(start, end) <= 0) {
                K inclusiveStart = startKeyInclusive ? start : higherKey(start);
                K inclusiveEnd = endKeyInclusive ? end : lowerKey(end);
                boolean legalStart = (null == inclusiveStart)
                        || checkLowerBound(inclusiveStart);
                boolean legalEnd = (null == inclusiveEnd)
                        || checkUpperBound(inclusiveEnd);
                if (legalStart && legalEnd) {
                    return new DescendingSubMap<K, V>(end, endKeyInclusive,
                            backingMap, start, startKeyInclusive);
                }
            }
            throw new IllegalArgumentException();
        }

        @Override
        public NavigableMap<K, V> headMap(K end, boolean inclusive) {
            // check for error
            this.keyCompare(end, end);
            K inclusiveEnd = inclusive ? end : lowerKey(end);
            if (null == inclusiveEnd || checkLowerBound(inclusiveEnd)) {
                if (this.hasEnd) {
                    return new DescendingSubMap<K, V>(end, inclusive,
                            backingMap, this.endKey, this.endInclusive);
                }
                return new DescendingSubMap<K, V>(end, inclusive, backingMap);
            }
            throw new IllegalArgumentException();
        }

        @Override
        public NavigableMap<K, V> tailMap(K start, boolean inclusive) {
            // check for error
            this.keyCompare(start, start);
            K inclusiveStart = inclusive ? start : higherKey(start);
            if (null == inclusiveStart || checkUpperBound(inclusiveStart)) {
                if (this.hasStart) {
                    return new DescendingSubMap<K, V>(this.startKey,
                            this.startInclusive, backingMap, start, inclusive);
                }
                return new DescendingSubMap<K, V>(backingMap, start, inclusive);

            }
            throw new IllegalArgumentException();
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
     * @param theComparator
     *            the Comparator
     */
    public TreeMap(Comparator<? super K> theComparator) {
        this.comparator = theComparator;
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
    public TreeMap(Map<? extends K, ? extends V> map) {
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
    public TreeMap(SortedMap<K, ? extends V> map) {
        this(map.comparator());
        Iterator<? extends Map.Entry<K, ? extends V>> it = map.entrySet()
                .iterator();
        if (it.hasNext()) {
            Map.Entry<K, ? extends V> entry = it.next();
            Entry<K, V> last = new Entry<K, V>(entry.getKey(), entry.getValue());
            root = last;
            size = 1;
            while (it.hasNext()) {
                entry = it.next();
                Entry<K, V> x = new Entry<K, V>(entry.getKey(), entry
                        .getValue());
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
            clone.navigableKeySet = null;
            clone.descendingMap = null;
            clone.valuesCollection = null;
            clone.keySet = null;
            if (root != null) {
                clone.root = root.clone(null);
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
        return find(key) != null;
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

    @SuppressWarnings("unchecked")
    Entry<K, V> find(Object keyObj) {
		int result;
		K key = (K) keyObj;
		Entry<K, V> x = root;
		if (null != comparator) {
			while (x != null) {
				result = comparator.compare(key, x.key);
				if (result == 0) {
					return x;
				}
				x = result < 0 ? x.left : x.right;
			}
			return null;
		} else {
			Comparable<K> targetKey = (Comparable<K>) key;
			while (x != null) {
				result = targetKey.compareTo(x.key);
				if (result == 0) {
					return x;
				}
				x = result < 0 ? x.left : x.right;
			}
			return null;
		}
	}

    TreeMap.Entry<K, V> findSmallestEntry() {
        return (null == root) ? null : minimum(root);
    }

    TreeMap.Entry<K, V> findBiggestEntry() {
        return (null == root) ? null : maximum(root);
    }

    TreeMap.Entry<K, V> findCeilingEntry(K key) {
		int result;
		Entry<K, V> node = root, last = null;
		if (null != comparator) {
			while (node != null) {
				result = comparator.compare(key, node.key);
				if (result == 0) {
					return node;
				}
				if (result < 0) {
					last = node;
					node = node.left;
				} else {
					node = node.right;
				}
			}
			return last;
		} else {
			Comparable<K> targetKey = (Comparable<K>) key;
			while (node != null) {
				result = targetKey.compareTo(node.key);
				if (result == 0) {
					return node;
				}
				if (result < 0) {
					last = node;
					node = node.left;
				} else {
					node = node.right;
				}
			}
			return last;
		}
	}

    TreeMap.Entry<K, V> findFloorEntry(K key) {
		int result;
		Entry<K, V> node = root, last = null;
		if (comparator != null) {
			while (node != null) {
				result = comparator.compare(key, node.key);
				if (0 == result) {
					return node;
				} else if (0 < result) {
					last = node;
					node = node.right;
				} else {
					node = node.left;
				}
			}
			return last;
		} else {
			Comparable<K> targetKey = (Comparable<K>) key;
			while (node != null) {
				result = targetKey.compareTo(node.key);
				if (0 == result) {
					return node;
				} else if (0 < result) {
					last = node;
					node = node.right;
				} else {
					node = node.left;
				}
			}
			return last;
		}
	}

    TreeMap.Entry<K, V> findLowerEntry(K key) {
		int result;
		Entry<K, V> node = root, last = null;
		if (comparator != null) {
			while (node != null) {
				result = comparator.compare(key, node.key);
				if (result <= 0) {
					node = node.left;
				} else {
					last = node;
					node = node.right;
				}
			}
			return last;
		} else {
			Comparable<K> targetKey = (Comparable<K>) key;
			while (node != null) {
				result = targetKey.compareTo(node.key);
				if (result <= 0) {
					node = node.left;
				} else {
					last = node;
					node = node.right;
				}
			}
			return last;

		}
	}

    TreeMap.Entry<K, V> findHigherEntry(K key) {
		int result;
		Entry<K, V> node = root, last = null;
		if (comparator != null) {
			while (node != null) {
				result = comparator.compare(key, node.key);
				if (result < 0) {
					last = node;
					node = node.left;
				} else {
					node = node.right;
				}
			}
			return last;
		} else {
			Comparable<K> targetKey = (Comparable<K>) key;
			while (node != null) {
				result = targetKey.compareTo(node.key);
				if (result < 0) {
					last = node;
					node = node.left;
				} else {
					node = node.right;
				}
			}
			return last;
		}
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
        Entry<K, V> node = find(key);
        if (node != null) {
            return node.value;
        }
        return null;
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
        return navigableKeySet();
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
        size--;
        y.parent = y.left = y.right = null;
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
        Entry<K, V> node = find(key);
        if (node == null) {
            return null;
        }
        V result = node.value;
        rbDelete(node);
        return result;
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
                    return new SubMapValueIterator<K,V> (TreeMap.this);
                }
            };
        }
        return valuesCollection;
    }    

    /**
     * {@inheritDoc}
     * 
     * @see java.util.NavigableMap#firstEntry()
     * @since 1.6
     */
    public Map.Entry<K, V> firstEntry() {
        return newImmutableEntry(findSmallestEntry());
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.NavigableMap#lastEntry()
     * @since 1.6
     */
    public Map.Entry<K, V> lastEntry() {
        return newImmutableEntry(findBiggestEntry());
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.NavigableMap#pollFirstEntry()
     * @since 1.6
     */
    public Map.Entry<K, V> pollFirstEntry() {
        Entry<K, V> node = findSmallestEntry();
        SimpleImmutableEntry<K, V> result = newImmutableEntry(node);
        if (null != node) {
            rbDelete(node);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.NavigableMap#pollLastEntry()
     * @since 1.6
     */
    public Map.Entry<K, V> pollLastEntry() {
        Entry<K, V> node = findBiggestEntry();
        SimpleImmutableEntry<K, V> result = newImmutableEntry(node);
        if (null != node) {
            rbDelete(node);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.NavigableMap#higherEntry(Object)
     * @since 1.6
     */
    public Map.Entry<K, V> higherEntry(K key) {
        return newImmutableEntry(findHigherEntry(key));
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.NavigableMap#higherKey(Object)
     * @since 1.6
     */
    public K higherKey(K key) {
        Map.Entry<K, V> entry = higherEntry(key);
        return (null == entry) ? null : entry.getKey();
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.NavigableMap#lowerEntry(Object)
     * @since 1.6
     */
    public Map.Entry<K, V> lowerEntry(K key) {
        return newImmutableEntry(findLowerEntry(key));
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.NavigableMap#lowerKey(Object)
     * @since 1.6
     */
    public K lowerKey(K key) {
        Map.Entry<K, V> entry = lowerEntry(key);
        return (null == entry) ? null : entry.getKey();
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.NavigableMap#ceilingEntry(java.lang.Object)
     * @since 1.6
     */
    public Map.Entry<K, V> ceilingEntry(K key) {
        return newImmutableEntry(findCeilingEntry(key));
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.NavigableMap#ceilingKey(java.lang.Object)
     * @since 1.6
     */
    public K ceilingKey(K key) {
        Map.Entry<K, V> entry = ceilingEntry(key);
        return (null == entry) ? null : entry.getKey();
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.NavigableMap#floorEntry(Object)
     * @since 1.6
     */
    public Map.Entry<K, V> floorEntry(K key) {
        return newImmutableEntry(findFloorEntry(key));
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.NavigableMap#floorKey(Object)
     * @since 1.6
     */
    public K floorKey(K key) {
        Map.Entry<K, V> entry = floorEntry(key);
        return (null == entry) ? null : entry.getKey();
    }

    final AbstractMap.SimpleImmutableEntry<K, V> newImmutableEntry(
            TreeMap.Entry<K, V> entry) {
        return (null == entry) ? null : new SimpleImmutableEntry<K, V>(entry);
    }

    @SuppressWarnings("unchecked")
    private static <T> Comparable<T> toComparable(T obj) {
        return (Comparable<T>) obj;
    }

    int keyCompare(K left, K right) {
        return (null != comparator) ? comparator.compare(left, right)
                : toComparable(left).compareTo(right);
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
                    return new AscendingSubMapEntryIterator<K, V>(
                            new AscendingSubMap<K, V>(TreeMap.this));
                }
            };
        }
        return entrySet;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.NavigableMap#navigableKeySet()
     * @since 1.6
     */
    @SuppressWarnings("unchecked")
    public NavigableSet<K> navigableKeySet() {
        return (null != navigableKeySet) ? navigableKeySet
                : (navigableKeySet = (new AscendingSubMap<K, V>(this))
                        .navigableKeySet());
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.NavigableMap#descendingKeySet()
     * @since 1.6
     */
    public NavigableSet<K> descendingKeySet() {
        return descendingMap().navigableKeySet();
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.NavigableMap#descendingMap()
     * @since 1.6
     */
    public NavigableMap<K, V> descendingMap() {
        return (null != descendingMap) ? descendingMap
                : (descendingMap = new DescendingSubMap<K, V>(this));
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.NavigableMap#subMap(Object, boolean, Object, boolean)
     * @since 1.6
     */
    public NavigableMap<K, V> subMap(K start, boolean startInclusive, K end,
            boolean endInclusive) {
        if (keyCompare(start, end) <= 0) {
            return new AscendingSubMap<K, V>(start, startInclusive, this, end,
                    endInclusive);
        }
        throw new IllegalArgumentException();
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.NavigableMap#headMap(Object, boolean)
     * @since 1.6
     */
    public NavigableMap<K, V> headMap(K end, boolean inclusive) {
        // check for error
        keyCompare(end, end);
        return new AscendingSubMap<K, V>(this, end, inclusive);
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.NavigableMap#tailMap(Object, boolean)
     * @since 1.6
     */
    public NavigableMap<K, V> tailMap(K start, boolean inclusive) {
        // check for error
        keyCompare(start, start);
        return new AscendingSubMap<K, V>(start, inclusive, this);
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
        return subMap(startKey, true, endKey, false);
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
        return headMap(endKey, false);
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
        return tailMap(startKey, true);
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

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        stream.defaultReadObject();
        size = stream.readInt();
        Entry<K, V> last = null;
        for (int i = size; --i >= 0;) {
            Entry<K, V> node = new Entry<K, V>((K) stream.readObject());
            node.value = (V) stream.readObject();
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
    
    
    private static class SubMapIterator<K, V> {
        TreeMap<K, V> backingMap;

        int expectedModCount;

        TreeMap.Entry<K, V> node;

        TreeMap.Entry<K, V> lastNode;

        private final TreeMap.Entry<K, V> endEntry;

        SubMapIterator(TreeMap<K, V> map) {
            this(map, map.root == null ? null : TreeMap.minimum(map.root));
        }

        SubMapIterator(TreeMap<K, V> map, Entry<K, V> startNode) {
            this(map, startNode, null);
        }

        SubMapIterator(TreeMap<K, V> map, Entry<K, V> startNode,
                Entry<K, V> endNode) {
            backingMap = map;
            expectedModCount = map.modCount;
            node = startNode;
            endEntry = endNode;
            if (node == endEntry) {
                node = null;
            }
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
        
        final void getNext() {
            if (expectedModCount != backingMap.modCount) {
                throw new ConcurrentModificationException();
            } else if (node == null) {
                throw new NoSuchElementException();
            }
            lastNode = node;
            Entry<K,V> nextNode = TreeMap.successor(node);
            node = nextNode == endEntry? null: nextNode;            
        }
        
        public boolean hasNext() {
            return node != null;
        }
    }
    
    private static class SubMapValueIterator<K, V> extends SubMapIterator<K, V>
            implements Iterator<V> {

        SubMapValueIterator(TreeMap<K, V> map) {
            super(map);
        }

        SubMapValueIterator(TreeMap<K, V> map, Entry<K, V> startNode) {
            super(map, startNode);
        }

        SubMapValueIterator(TreeMap<K, V> map, Entry<K, V> startNode,
                Entry<K, V> endNode) {
            super(map, startNode, endNode);
        }

        public V next() {
            getNext();
            return lastNode.value;
        }
    }

}
