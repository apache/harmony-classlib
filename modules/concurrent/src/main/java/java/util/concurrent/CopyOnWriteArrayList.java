/*
 * Copyright 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package java.util.concurrent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public class CopyOnWriteArrayList<E> implements List<E>, RandomAccess, Cloneable, Serializable {

    private static final long serialVersionUID = 8673264195747942595L;

    public CopyOnWriteArrayList() {
        super();
    }

    public CopyOnWriteArrayList(Collection<? extends E> c) {
        super();
    }

    public CopyOnWriteArrayList(E[] array) {
        super();
    }

    public boolean add(E e) {
        return false;
    }

    public void add(int index, E e) {
    }

    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    public int addAllAbsent(Collection<? extends E> c) {
        return 0;
    }

    public boolean addIfAbsent(E e) {
        return false;
    }

    public void clear() {
    }

    public Object clone() {
        return null;
    }

    public boolean contains(Object o) {
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean equals(Object o) {
        return false;
    }

    public E get(int index) {
        return null;
    }

    public int hashCode() {
        return 0;
    }

    public int indexOf(E e, int index) {
        return 0;
    }

    public int indexOf(Object o) {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public Iterator<E> iterator() {
        return null;
    }

    public int lastIndexOf(E e, int index) {
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator<E> listIterator() {
        return null;
    }

    public ListIterator<E> listIterator(int index) {
        return null;
    }

    public E remove(int index) {
        return null;
    }

    public boolean remove(Object o) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public E set(int index, E e) {
        return null;
    }

    public int size() {
        return 0;
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    public Object[] toArray() {
        return null;
    }

    public <T> T[] toArray(T[] arg0) {
        return null;
    }

    public String toString() {
        return null;
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {

    }

    private void writeObject(ObjectOutputStream s) throws IOException {

    }
}
