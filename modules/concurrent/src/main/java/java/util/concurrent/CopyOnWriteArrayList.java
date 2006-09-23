/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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
        throw new RuntimeException("Not yet implemented.");
    }

    public CopyOnWriteArrayList(Collection<? extends E> c) {
        super();
        throw new RuntimeException("Not yet implemented.");
    }

    public CopyOnWriteArrayList(E[] array) {
        super();
        throw new RuntimeException("Not yet implemented.");
    }

    public boolean add(E e) {
        throw new RuntimeException("Not yet implemented.");
    }

    public void add(int index, E e) {
        throw new RuntimeException("Not yet implemented.");
    }

    public boolean addAll(Collection<? extends E> c) {
        throw new RuntimeException("Not yet implemented.");
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        throw new RuntimeException("Not yet implemented.");
    }

    public int addAllAbsent(Collection<? extends E> c) {
        throw new RuntimeException("Not yet implemented.");
    }

    public boolean addIfAbsent(E e) {
        throw new RuntimeException("Not yet implemented.");
    }

    public void clear() {
        throw new RuntimeException("Not yet implemented.");
    }

    @Override
    public Object clone() {
        throw new RuntimeException("Not yet implemented.");
    }

    public boolean contains(Object o) {
        throw new RuntimeException("Not yet implemented.");
    }

    public boolean containsAll(Collection<?> c) {
        throw new RuntimeException("Not yet implemented.");
    }

    @Override
    public boolean equals(Object o) {
        throw new RuntimeException("Not yet implemented.");
    }

    public E get(int index) {
        throw new RuntimeException("Not yet implemented.");
    }

    @Override
    public int hashCode() {
        throw new RuntimeException("Not yet implemented.");
    }

    public int indexOf(E e, int index) {
        throw new RuntimeException("Not yet implemented.");
    }

    public int indexOf(Object o) {
        throw new RuntimeException("Not yet implemented.");
    }

    public boolean isEmpty() {
        throw new RuntimeException("Not yet implemented.");
    }

    public Iterator<E> iterator() {
        throw new RuntimeException("Not yet implemented.");
    }

    public int lastIndexOf(E e, int index) {
        throw new RuntimeException("Not yet implemented.");
    }

    public int lastIndexOf(Object o) {
        throw new RuntimeException("Not yet implemented.");
    }

    public ListIterator<E> listIterator() {
        throw new RuntimeException("Not yet implemented.");
    }

    public ListIterator<E> listIterator(int index) {
        throw new RuntimeException("Not yet implemented.");
    }

    public E remove(int index) {
        throw new RuntimeException("Not yet implemented.");
    }

    public boolean remove(Object o) {
        throw new RuntimeException("Not yet implemented.");
    }

    public boolean removeAll(Collection<?> c) {
        throw new RuntimeException("Not yet implemented.");
    }

    public boolean retainAll(Collection<?> c) {
        throw new RuntimeException("Not yet implemented.");
    }

    public E set(int index, E e) {
        throw new RuntimeException("Not yet implemented.");
    }

    public int size() {
        throw new RuntimeException("Not yet implemented.");
    }

    public List<E> subList(int fromIndex, int toIndex) {
        throw new RuntimeException("Not yet implemented.");
    }

    public Object[] toArray() {
        throw new RuntimeException("Not yet implemented.");
    }

    public <T> T[] toArray(T[] arg0) {
        throw new RuntimeException("Not yet implemented.");
    }

    @Override
    public String toString() {
        throw new RuntimeException("Not yet implemented.");
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        throw new RuntimeException("Not yet implemented.");
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        throw new RuntimeException("Not yet implemented.");
    }
}
