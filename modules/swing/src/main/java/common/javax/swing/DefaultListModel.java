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

/**
 * @author Anton Avtamonov
 * @version $Revision$
 */

package javax.swing;

import java.util.Enumeration;
import java.util.Vector;

public class DefaultListModel extends AbstractListModel {
    private Vector internalStorage = new Vector();

    public void add(final int index, final Object element) {
        internalStorage.add(index, element);
        fireIntervalAdded(this, index, index);
    }

    public void addElement(final Object element) {
        internalStorage.addElement(element);
        fireIntervalAdded(this, internalStorage.size() - 1, internalStorage.size() - 1);
    }

    public int capacity() {
        return internalStorage.capacity();
    }

    public void clear() {
        int size = getSize();
        if (size > 0) {
            internalStorage.clear();
            fireIntervalRemoved(this, 0, size - 1);
        }
    }

    public boolean contains(final Object element) {
        return internalStorage.contains(element);
    }

    public void copyInto(final Object[] array) {
        internalStorage.copyInto(array);
    }

    public Object elementAt(final int index) {
        return internalStorage.elementAt(index);
    }

    public Enumeration<?> elements() {
        return internalStorage.elements();
    }

    public void ensureCapacity(final int minCapacity) {
        internalStorage.ensureCapacity(minCapacity);
    }

    public Object firstElement() {
        return internalStorage.firstElement();
    }

    public Object get(final int index) {
        return internalStorage.get(index);
    }

    public Object getElementAt(final int index) {
        return get(index);
    }

    public int getSize() {
        return internalStorage.size();
    }

    public int indexOf(final Object element) {
        return internalStorage.indexOf(element);
    }

    public int indexOf(final Object element, final int index) {
        return internalStorage.indexOf(element, index);
    }

    public void insertElementAt(final Object element, final int index) {
        internalStorage.insertElementAt(element, index);
        fireIntervalAdded(this, index, index);
    }

    public boolean isEmpty() {
        return internalStorage.isEmpty();
    }

    public Object lastElement() {
        return internalStorage.lastElement();
    }

    public int lastIndexOf(final Object element) {
        return internalStorage.lastIndexOf(element);
    }

    public int lastIndexOf(final Object element, final int index) {
        return internalStorage.lastIndexOf(element, index);
    }

    public Object remove(final int index) {
        Object result = internalStorage.remove(index);
        fireIntervalRemoved(this, index, index);
        return result;
    }

    public void removeAllElements() {
        clear();
    }

    public boolean removeElement(final Object element) {
        int index = internalStorage.indexOf(element);
        boolean result = internalStorage.removeElement(element);

        if (index != -1) {
            fireIntervalRemoved(this, index, index);
        }

        return result;
    }

    public void removeElementAt(final int index) {
        remove(index);
    }

    public void removeRange(final int fromIndex, final int toIndex) {
        for (int i = 0; i < toIndex - fromIndex + 1; i++) {
            internalStorage.remove(fromIndex);
        }
        fireIntervalRemoved(this, fromIndex, toIndex);
    }

    public Object set(final int index, final Object element) {
        Object result = internalStorage.set(index, element);
        fireContentsChanged(this, index, index);

        return result;
    }

    public void setElementAt(final Object element, final int index) {
        set(index, element);
    }

    public void setSize(final int size) {
        internalStorage.setSize(size);
    }

    public int size() {
        return internalStorage.size();
    }

    public Object[] toArray() {
        return internalStorage.toArray();
    }

    public String toString() {
        return internalStorage.toString();
    }

    public void trimToSize() {
        internalStorage.trimToSize();
    }
}
