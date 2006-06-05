/* Copyright 1998, 2002 The Apache Software Foundation or its licensors, as applicable
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
 * TreeSet is an implementation of SortedSet. All optional operations are
 * supported, adding and removing. The elements can be any objects which are
 * comparable to each other either using their natural order or a specified
 * Comparator.
 * @since 1.2
 */
public class TreeSet<E> extends AbstractSet<E> implements SortedSet<E>, Cloneable,
		Serializable {
	
	private static final long serialVersionUID = -2479143000061671589L;

	private transient TreeMap<E, E> backingMap;

	private static final class SubSet<E> extends TreeMap.SubMap.SubMapSet<E, E, E>
			implements SortedSet<E> {
		private SubSet(TreeMap<E, E> map) {
			super(map, new MapEntry.Type<E, E, E>() {
				public E get(MapEntry<E, E> entry) {
					return entry.key;
				}
			});
		}

		private SubSet(E start, TreeMap<E, E> map) {
			this(map);
			hasStart = true;
			startKey = start;
		}

		private SubSet(E start, TreeMap<E, E> map, E end) {
			this(map);
			hasStart = hasEnd = true;
			startKey = start;
			endKey = end;
		}

		private SubSet(TreeMap<E, E> map, E end) {
			this(map);
			hasEnd = true;
			endKey = end;
		}

		public boolean add(E object) {
			checkRange(object);
			return this.backingMap.put(object, object) != null;
		}

		public Comparator<? super E> comparator() {
			return this.backingMap.comparator();
		}

		public boolean contains(Object object) {
			if (checkRange((E)object, hasStart, hasEnd))
				return this.backingMap.containsKey(object);
			return false;
		}

		public E first() {
			if (!hasStart)
				return this.backingMap.firstKey();
			TreeMap.Entry<E, E> node = this.backingMap.findAfter(startKey);
			if (node != null && checkRange(node.key, false, hasEnd))
				return node.key;
			throw new NoSuchElementException();
		}

		public SortedSet<E> headSet(E end) {
			checkRange(end);
			if (hasStart)
				return new SubSet<E>(startKey, this.backingMap, end);
			return new SubSet<E>(this.backingMap, end);
		}

		public E last() {
			if (!hasEnd)
				return this.backingMap.lastKey();
			TreeMap.Entry<E, E> node = this.backingMap.findBefore(endKey);
			if (node != null && checkRange(node.key, hasStart, false))
				return node.key;
			throw new NoSuchElementException();
		}

		public boolean remove(Object object) {
			if (checkRange((E)object, hasStart, hasEnd))
				return this.backingMap.remove(object) != null;
			return false;
		}

		public SortedSet<E> subSet(E start, E end) {
			checkRange(start);
			checkRange(end);
			Comparator<? super E> c = this.backingMap.comparator();
			if (c == null) {
				if (((Comparable<E>) startKey).compareTo(endKey) <= 0)
					return new SubSet<E>(start, this.backingMap, end);
			} else {
				if (c.compare(startKey, endKey) <= 0)
					return new SubSet<E>(start, this.backingMap, end);
			}
			throw new IllegalArgumentException();
		}

		public SortedSet<E> tailSet(E start) {
			checkRange(start);
			if (hasEnd)
				return new SubSet<E>(start, this.backingMap, endKey);
			return new SubSet<E>(start, this.backingMap);
		}
	}

	/**
	 * Constructs a new empty instance of TreeSet which uses natural ordering.
	 * 
	 */
	public TreeSet() {
		backingMap = new TreeMap<E, E>();
	}

	/**
	 * Constructs a new instance of TreeSet which uses natural ordering and
	 * containing the unique elements in the specified collection.
	 * 
	 * @param collection
	 *            the collection of elements to add
	 * 
	 * @exception ClassCastException
	 *                when an element in the Collection does not implement the
	 *                Comparable interface, or the elements in the Collection
	 *                cannot be compared
	 */
	public TreeSet(Collection<? extends E> collection) {
		this();
		addAll(collection);
	}

	/**
	 * Constructs a new empty instance of TreeSet which uses the specified
	 * Comparator.
	 * 
	 * @param comparator
	 *            the Comparator
	 */
	public TreeSet(Comparator<? super E> comparator) {
		backingMap = new TreeMap<E, E>(comparator);
	}

	/**
	 * Constructs a new instance of TreeSet containing the elements in the
	 * specified SortedSet and using the same Comparator.
	 * 
	 * @param set
	 *            the SortedSet of elements to add
	 */
	public TreeSet(SortedSet<E> set) {
		this(set.comparator());
		Iterator<E> it = set.iterator();
		if (it.hasNext()) {
			E object = it.next();
			TreeMap.Entry<E, E> last = new TreeMap.Entry<E, E>(object, object);
			backingMap.root = last;
			backingMap.size = 1;
			while (it.hasNext()) {
				object = it.next();
				TreeMap.Entry<E, E> x = new TreeMap.Entry<E, E>(object, object);
				x.parent = last;
				last.right = x;
				backingMap.size++;
				backingMap.balance(x);
				last = x;
			}
		}
	}

	/**
	 * Adds the specified object to this TreeSet.
	 * 
	 * @param object
	 *            the object to add
	 * @return true when this TreeSet did not already contain the object, false
	 *         otherwise
	 * 
	 * @exception ClassCastException
	 *                when the object cannot be compared with the elements in
	 *                this TreeSet
	 * @exception NullPointerException
	 *                when the object is null and the comparator cannot handle
	 *                null
	 */
	public boolean add(E object) {
		return backingMap.put(object, object) == null;
	}

	/**
	 * Adds the objects in the specified Collection to this TreeSet.
	 * 
	 * @param collection
	 *            the Collection of objects
	 * @return true if this TreeSet is modified, false otherwise
	 * 
	 * @exception ClassCastException
	 *                when an object in the Collection cannot be compared with
	 *                the elements in this TreeSet
	 * @exception NullPointerException
	 *                when an object in the Collection is null and the
	 *                comparator cannot handle null
	 */
	public boolean addAll(Collection<? extends E> collection) {
		return super.addAll(collection);
	}

	/**
	 * Removes all elements from this TreeSet, leaving it empty.
	 * 
	 * @see #isEmpty
	 * @see #size
	 */
	public void clear() {
		backingMap.clear();
	}

	/**
	 * Answers a new TreeSet with the same elements, size and comparator as this
	 * TreeSet.
	 * 
	 * @return a shallow copy of this TreeSet
	 * 
	 * @see java.lang.Cloneable
	 */
	public Object clone() {
		try {
			TreeSet<E> clone = (TreeSet<E>) super.clone();
			clone.backingMap = (TreeMap<E, E>) backingMap.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	/**
	 * Answers the Comparator used to compare elements in this TreeSet.
	 * 
	 * @return a Comparator or null if the natural ordering is used
	 */
	public Comparator<? super E> comparator() {
		return backingMap.comparator();
	}

	/**
	 * Searches this TreeSet for the specified object.
	 * 
	 * @param object
	 *            the object to search for
	 * @return true if <code>object</code> is an element of this TreeSet,
	 *         false otherwise
	 * 
	 * @exception ClassCastException
	 *                when the object cannot be compared with the elements in
	 *                this TreeSet
	 * @exception NullPointerException
	 *                when the object is null and the comparator cannot handle
	 *                null
	 */
	public boolean contains(Object object) {
		return backingMap.containsKey(object);
	}

	/**
	 * Answers the first element in this TreeSet.
	 * 
	 * @return the first element
	 * 
	 * @exception NoSuchElementException
	 *                when this TreeSet is empty
	 */
	public E first() {
		return backingMap.firstKey();
	}

	/**
	 * Answers a SortedSet of the specified portion of this TreeSet which
	 * contains elements less than the end element. The returned SortedSet is
	 * backed by this TreeSet so changes to one are reflected by the other.
	 * 
	 * @param end
	 *            the end element
	 * @return a subset where the elements are less than <code>end</code>
	 * 
	 * @exception ClassCastException
	 *                when the end object cannot be compared with the elements
	 *                in this TreeSet
	 * @exception NullPointerException
	 *                when the end object is null and the comparator cannot
	 *                handle null
	 */
	public SortedSet<E> headSet(E end) {
		// Check for errors
		Comparator<? super E> c = backingMap.comparator();
		if (c == null)
			((Comparable<E>) end).compareTo(end);
		else
			c.compare(end, end);
		return new SubSet<E>(backingMap, end);
	}

	/**
	 * Answers if this TreeSet has no elements, a size of zero.
	 * 
	 * @return true if this TreeSet has no elements, false otherwise
	 * 
	 * @see #size
	 */
	public boolean isEmpty() {
		return backingMap.isEmpty();
	}

	/**
	 * Answers an Iterator on the elements of this TreeSet.
	 * 
	 * @return an Iterator on the elements of this TreeSet
	 * 
	 * @see Iterator
	 */
	public Iterator<E> iterator() {
		return backingMap.keySet().iterator();
	}

	/**
	 * Answers the last element in this TreeSet.
	 * 
	 * @return the last element
	 * 
	 * @exception NoSuchElementException
	 *                when this TreeSet is empty
	 */
	public E last() {
		return backingMap.lastKey();
	}

	/**
	 * Removes an occurrence of the specified object from this TreeSet.
	 * 
	 * @param object
	 *            the object to remove
	 * @return true if this TreeSet is modified, false otherwise
	 * 
	 * @exception ClassCastException
	 *                when the object cannot be compared with the elements in
	 *                this TreeSet
	 * @exception NullPointerException
	 *                when the object is null and the comparator cannot handle
	 *                null
	 */
	public boolean remove(Object object) {
		return backingMap.remove(object) != null;
	}

	/**
	 * Answers the number of elements in this TreeSet.
	 * 
	 * @return the number of elements in this TreeSet
	 */
	public int size() {
		return backingMap.size();
	}

	/**
	 * Answers a SortedSet of the specified portion of this TreeSet which
	 * contains elements greater or equal to the start element but less than the
	 * end element. The returned SortedSet is backed by this TreeSet so changes
	 * to one are reflected by the other.
	 * 
	 * @param start
	 *            the start element
	 * @param end
	 *            the end element
	 * @return a subset where the elements are greater or equal to
	 *         <code>start</code> and less than <code>end</code>
	 * 
	 * @exception ClassCastException
	 *                when the start or end object cannot be compared with the
	 *                elements in this TreeSet
	 * @exception NullPointerException
	 *                when the start or end object is null and the comparator
	 *                cannot handle null
	 */
	public SortedSet<E> subSet(E start, E end) {
		Comparator<? super E> c = backingMap.comparator();
		if (c == null) {
			if (((Comparable<E>) start).compareTo(end) <= 0)
				return new SubSet<E>(start, backingMap, end);
		} else {
			if (c.compare(start, end) <= 0)
				return new SubSet<E>(start, backingMap, end);
		}
		throw new IllegalArgumentException();
	}

	/**
	 * Answers a SortedSet of the specified portion of this TreeSet which
	 * contains elements greater or equal to the start element. The returned
	 * SortedSet is backed by this TreeSet so changes to one are reflected by
	 * the other.
	 * 
	 * @param start
	 *            the start element
	 * @return a subset where the elements are greater or equal to
	 *         <code>start</code>
	 * 
	 * @exception ClassCastException
	 *                when the start object cannot be compared with the elements
	 *                in this TreeSet
	 * @exception NullPointerException
	 *                when the start object is null and the comparator cannot
	 *                handle null
	 */
	public SortedSet<E> tailSet(E start) {
		// Check for errors
		Comparator<? super E> c = backingMap.comparator();
		if (c == null)
			((Comparable<E>) start).compareTo(start);
		else
			c.compare(start, start);
		return new SubSet<E>(start, backingMap);
	}

	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
		stream.writeObject(backingMap.comparator());
		stream.writeInt(backingMap.size);
		if (backingMap.size > 0) {
			TreeMap.Entry<E, E> node = TreeMap.minimum(backingMap.root);
			while (node != null) {
				stream.writeObject(node.key);
				node = TreeMap.successor(node);
			}
		}
	}

	private void readObject(ObjectInputStream stream) throws IOException,
			ClassNotFoundException {
		stream.defaultReadObject();
		backingMap = new TreeMap<E, E>((Comparator<? super E>) stream.readObject());
		backingMap.size = stream.readInt();
		TreeMap.Entry<E, E> last = null;
		for (int i = backingMap.size; --i >= 0;) {
			TreeMap.Entry<E, E> node = new TreeMap.Entry<E, E>((E)stream.readObject());
			node.value = node.key;
			if (last == null)
				backingMap.root = node;
			else {
				node.parent = last;
				last.right = node;
				backingMap.balance(node);
			}
			last = node;
		}
	}
}
