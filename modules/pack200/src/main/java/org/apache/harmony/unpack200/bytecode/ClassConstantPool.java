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
package org.apache.harmony.unpack200.bytecode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.harmony.unpack200.Segment;

/**
 * The Class constant pool
 */
public class ClassConstantPool {

    protected HashSet entriesContainsSet = new HashSet();
    protected HashSet othersContainsSet = new HashSet();

    private final HashSet mustStartClassPool = new HashSet();

    protected Map indexCache = null;

    public String toString() {
        return entries.toString();
    }

    private final List others = new ArrayList(500);
    private final List entries = new ArrayList(500);

    private boolean resolved;

    public ClassFileEntry add(ClassFileEntry entry) {
        if (entry instanceof ConstantPoolEntry) {
            if (!entriesContainsSet.contains(entry)) {
                entriesContainsSet.add(entry);
                entries.add(entry);
            }
        } else {
            if (!othersContainsSet.contains(entry)) {
                othersContainsSet.add(entry);
                others.add(entry);
            }
        }
        return entry;
    }

    public void addNestedEntries() {
        boolean added = true;
        while (added) {
            int entriesOriginalSize = entries.size();
            int othersOriginalSize = others.size();
            addNested(entries);
            addNested(others);
            added = !(entries.size() == entriesOriginalSize && others.size() == othersOriginalSize);
        }
    }

    private void addNested(List classFileEntries) {
        for (int classFileIndex = 0; classFileIndex < classFileEntries.size(); classFileIndex++) {
            ClassFileEntry entry = (ClassFileEntry) classFileEntries.get(classFileIndex);
            ClassFileEntry[] nestedEntries = entry.getNestedClassFileEntries();

            // Add all nestedEntries to the newEntries list
            for(int nestedEntriesIndex = 0; nestedEntriesIndex < nestedEntries.length; nestedEntriesIndex++) {
                add(nestedEntries[nestedEntriesIndex]);
            }

            // If the entry is a bytecode that needs to start the
            // class pool, add all the nestedEntries to the
            // mustStartClassPool as well.
            if(entry instanceof ByteCode) {
                if(((ByteCode)entry).nestedMustStartClassPool()) {
                    for(int nestedEntriesIndex = 0; nestedEntriesIndex < nestedEntries.length; nestedEntriesIndex++) {
                        mustStartClassPool.add(nestedEntries[nestedEntriesIndex]);
                    }
                }
            }
        }
    }

    protected void initializeIndexCache() {
        indexCache = new HashMap();
        for (int index = 0; index < entries.size(); index++) {
            ClassFileEntry indexEntry = (ClassFileEntry) entries.get(index);
            if (indexCache.containsKey(indexEntry)) {
                // key is already in there - do nothing
                // This will happen if a long or double
                // is the entry - they take up 2 slots.
            } else {
                indexCache.put(indexEntry, new Integer(index));
            }
        }
    }

    public int indexOf(ClassFileEntry entry) {
        if (!resolved)
            throw new IllegalStateException(
                    "Constant pool is not yet resolved; this does not make any sense");
        if (null == indexCache) {
            initializeIndexCache();
        }
        Integer entryIndex = ((Integer) indexCache.get(entry));
        // If the entry isn't found, answer -1. Otherwise answer the entry.
        if (entryIndex != null) {
            return entryIndex.intValue() + 1;
        }
        return -1;
    }

    public int size() {
        return entries.size();
    }

    public ClassFileEntry get(int i) {
        if (!resolved)
            throw new IllegalStateException(
                    "Constant pool is not yet resolved; this does not make any sense");
        return (ClassFileEntry) entries.get(--i);
    }

    public void resolve(Segment segment) {
        initialSort();
        Iterator it;
        resolved = true;
        it = entries.iterator();
        while (it.hasNext()) {
            ClassFileEntry entry = (ClassFileEntry) it.next();
            entry.resolve(this);
        }
        it = others.iterator();
        while (it.hasNext()) {
            ClassFileEntry entry = (ClassFileEntry) it.next();
            entry.resolve(this);
        }

        sortClassPool(segment);
    }

    private void initialSort() {
        TreeSet inCpAll = new TreeSet(new Comparator() {
            public int compare(Object arg0, Object arg1) {
                return ((ConstantPoolEntry)arg0).getGlobalIndex() - ((ConstantPoolEntry)arg1).getGlobalIndex();
            }
        });
        TreeSet cpUtf8sNotInCpAll = new TreeSet(new Comparator() {

            public int compare(Object arg0, Object arg1) {
                return ((CPUTF8)arg0).underlyingString().compareTo(((CPUTF8)arg1).underlyingString());
            }

        });
        TreeSet cpClassesNotInCpAll = new TreeSet(new Comparator() {

            public int compare(Object arg0, Object arg1) {
                return ((CPClass)arg0).getName().compareTo(((CPClass)arg1).getName());
            }

        });
        for (Iterator iterator = entries.iterator(); iterator.hasNext();) {
            ConstantPoolEntry entry = (ConstantPoolEntry) iterator.next();
            if(entry.getGlobalIndex() == -1) {
                if (entry instanceof CPUTF8) {
                    cpUtf8sNotInCpAll.add(entry);
                } else if (entry instanceof CPClass) {
                    cpClassesNotInCpAll.add(entry);
                } else {
                    throw new Error("error");
                }
            } else {
                inCpAll.add(entry);
            }
        }
        entries.clear();
        entries.addAll(inCpAll);
        entries.addAll(cpUtf8sNotInCpAll);
        entries.addAll(cpClassesNotInCpAll);
    }

    /**
     * Answer the collection of CPClasses currently held by the ClassPoolSet.
     * This is used to calculate relevant classes when generating the set of
     * relevant inner classes (ic_relevant())
     *
     * @return ArrayList collection of all classes.
     *
     * NOTE: when this list is answered, the classes may not yet be resolved.
     */
    public List allClasses() {
        List classesList = new ArrayList();
        Iterator it = entries.iterator();
        while (it.hasNext()) {
            ConstantPoolEntry entry = (ConstantPoolEntry) it.next();
            if (entry instanceof CPClass) {
                classesList.add(entry);
            }
        }
        return classesList;
    }

    protected void sortClassPool(Segment segment) {
        // Now that everything has been resolved, do one
        // final sort of the class pool. This fixes up
        // references to objects which need to be at the
        // start of the class pool

        // Since we resorted, need to initialize index cache
        initializeIndexCache();

        Iterator it = entries.iterator();
        ArrayList startOfPool = new ArrayList();
        ArrayList finalSort = new ArrayList();
        while (it.hasNext()) {
            ClassFileEntry nextEntry = (ClassFileEntry) it.next();
            if (mustStartClassPool.contains(nextEntry)) {
                startOfPool.add(nextEntry);
            } else {
                finalSort.add(nextEntry);
            }
        }
        entries.clear();
        Iterator itStart = startOfPool.iterator();
        while (itStart.hasNext()) {
            ClassFileEntry entry = (ClassFileEntry) itStart.next();
            entries.add(entry);
            if (entry instanceof CPLong || entry instanceof CPDouble)
                entries.add(entry); // these get 2 slots because of their size
        }
        it = finalSort.iterator();
        while (it.hasNext()) {
            ClassFileEntry entry = (ClassFileEntry) it.next();
            entries.add(entry);
            if (entry instanceof CPLong || entry instanceof CPDouble)
                entries.add(entry); // these get 2 slots because of their size
        }

        // Since we re-sorted, need to initialize index cache
        initializeIndexCache();

        // Now that the indices have been re-sorted, need
        // to re-resolve to update references. This should
        // not add any new entries this time through.
        it = entries.iterator();
        while (it.hasNext()) {
            ClassFileEntry entry = (ClassFileEntry) it.next();
            entry.resolve(this);
        }
        // Also need to re-resolve the others.
        it = others.iterator();
        while (it.hasNext()) {
            ClassFileEntry entry = (ClassFileEntry) it.next();
            entry.resolve(this);
        }
    }

    public ClassFileEntry addWithNestedEntries(ClassFileEntry entry) {
        add(entry);
        ClassFileEntry[] nestedEntries = entry.getNestedClassFileEntries();
        for (int i = 0; i < nestedEntries.length; i++) {
            addWithNestedEntries(nestedEntries[i]);
        }
        return entry;
    }
}
