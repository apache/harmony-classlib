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
package org.apache.harmony.pack200.bytecode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 * This class implements the special set (which is aware of
 * domains) used when generating ClassConstantPools.
 */
public class ClassPoolSet {

    class GeneralComparator implements Comparator {
        /* (non-Javadoc)
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        public int compare(Object o1, Object o2) {            
            if(o1.equals(o2)) {
                return 0;
            }
            return o1.getClass().getName().compareTo(o2.getClass().getName());
        }
    }

    class PoolComparator extends GeneralComparator {
        /* (non-Javadoc)
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        public int compare(Object o1, Object o2) {
            int classCompare = super.compare(o1, o2);
            if(classCompare != 0) {
                return classCompare;
            }
            // If we compare anything other than ConstantPoolEntries
            // with this comparator, it is an error.
            ConstantPoolEntry cpe1 = (ConstantPoolEntry)o1;
            ConstantPoolEntry cpe2 = (ConstantPoolEntry)o2;
            
            // Domains must be the same, need to compare
            // based on comparisonString.
            String compare1 = cpe1.comparisonString();
            String compare2 = cpe2.comparisonString();
            return compare1.compareTo(compare2);
        }
    }

    class SignatureAsciizComparator extends GeneralComparator {
        /* (non-Javadoc)
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        public int compare(Object o1, Object o2) {
            int classCompare = super.compare(o1, o2);
            if(classCompare != 0) {
                return classCompare;
            }
            
            // Now that we've reached this point, we're
            // comparing objects of the same class. Since
            // they have the same class, we can make the
            // statement that they're UTF8s, since only
            // UTF8s will be added to a TreeSet with
            // the SignatureAsciizComparator.
            CPUTF8 cpe1 = (CPUTF8)o1;
            CPUTF8 cpe2 = (CPUTF8)o2;

            String compare1 = cpe1.signatureComparisonString();
            String compare2 = cpe2.signatureComparisonString();
            return compare1.compareTo(compare2);
        }
    }

    class NormalAsciizComparator extends GeneralComparator {
        /* (non-Javadoc)
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        public int compare(Object o1, Object o2) {
            int classCompare = super.compare(o1, o2);
            if(classCompare != 0) {
                return classCompare;
            }
            
            // Now that we've reached this point, we're
            // comparing objects of the same class. Since
            // they have the same class, we can make the
            // statement that they're UTF8s, since only
            // UTF8s will be added to a TreeSet with
            // the NormalAsciizComparator.
            CPUTF8 cpe1 = (CPUTF8)o1;
            CPUTF8 cpe2 = (CPUTF8)o2;

            String compare1 = cpe1.normalComparisonString();
            String compare2 = cpe2.normalComparisonString();
            return compare1.compareTo(compare2);
        }
    }

    protected Comparator[] comparators = new Comparator[ClassConstantPool.NUM_DOMAINS];
    protected TreeSet[] sets = new TreeSet[ClassConstantPool.NUM_DOMAINS];

    public ClassPoolSet() {
        // These are the comparators in numeric order.
        comparators[ClassConstantPool.DOMAIN_UNDEFINED] = new PoolComparator();
        comparators[ClassConstantPool.DOMAIN_INTEGER] = new PoolComparator();
        comparators[ClassConstantPool.DOMAIN_FLOAT] = new PoolComparator();
        comparators[ClassConstantPool.DOMAIN_STRING] = new PoolComparator();
        comparators[ClassConstantPool.DOMAIN_NORMALASCIIZ] = new NormalAsciizComparator();
        comparators[ClassConstantPool.DOMAIN_LONG] = new PoolComparator();
        comparators[ClassConstantPool.DOMAIN_DOUBLE] = new PoolComparator();
        comparators[ClassConstantPool.DOMAIN_CLASSREF] = new PoolComparator();
        comparators[ClassConstantPool.DOMAIN_SIGNATUREASCIIZ] = new SignatureAsciizComparator();
        comparators[ClassConstantPool.DOMAIN_NAMEANDTYPE] = new PoolComparator();
        comparators[ClassConstantPool.DOMAIN_FIELD] = new PoolComparator();
        comparators[ClassConstantPool.DOMAIN_METHOD] = new PoolComparator();
        comparators[ClassConstantPool.DOMAIN_ATTRIBUTEASCIIZ] = new NormalAsciizComparator();
        
        for(int index=0; index < ClassConstantPool.NUM_DOMAINS; index++) {
            sets[index] = new TreeSet(comparators[index]);
        }
    }
    
    
    /**
     * Answer the index of the first set which contains the entry
     * passed in as the parameter.
     * @param entry ConstantPoolEntry to test for (with .contains())
     * @return int index of the first set containing the entry
     */
    protected int findFirstSetContaining(ConstantPoolEntry entry) {
        for(int index=0; index < ClassConstantPool.NUM_DOMAINS; index++) {
            if(sets[index].contains(entry)) {
                return index;
            }
        }
        return -1;
    }
    
    /**
     * @param set
     * @param testEntry
     * @return
     */
    protected ConstantPoolEntry getStoredEntry(int set, ConstantPoolEntry testEntry) {
        TreeSet searchSet = sets[set];
        Iterator iterator = searchSet.iterator();
        while(iterator.hasNext()) {
            ConstantPoolEntry storedEntry = (ConstantPoolEntry)iterator.next();
            if(0 == (comparators[set].compare(storedEntry, testEntry))) {
                return storedEntry;
            }
        }
        // Not found. Should not happen.
        throw new Error("Tried to find a stored entry which wasn't in the set");
    }

    /**
     * Add the parameter to the receiver. If the parameter
     * already exists in a higher precedence domain, answer
     * that. If the parameter exists in a lower precedence
     * domain, remove it from the lower domain and add it to
     * the higher one.
     * 
     * @param entry ConstantPoolEntry to add
     * @return ConstantPoolEntry from the domain where it's stored
     */
    public ConstantPoolEntry add(Object entry) {
        ConstantPoolEntry cpEntry = (ConstantPoolEntry)entry;
        int entryDomain = cpEntry.getDomain();
        int currentStoredDomain = findFirstSetContaining(cpEntry);
        
        if(currentStoredDomain < 0) {
            // Entry is not currently stored; just store it.
            sets[entryDomain].add(cpEntry);
            return cpEntry;
        }
        
        if(currentStoredDomain <= entryDomain) {
            // Entry is either already in this place
            // or in a higher-precedence (numerically lower)
            // domain; answer the value in the stored
            // domain.
            return getStoredEntry(currentStoredDomain, cpEntry);
        }
        
        // Entry is in a lower-precedence (numerically higher)
        // domain. Need to remove it from the lower-precedence
        // domain and add it to the higher precedence domain.
        sets[currentStoredDomain].remove(cpEntry);
        sets[entryDomain].add(cpEntry);
        return cpEntry;
    }


    public Iterator iterator() {
        return partialIterator(ClassConstantPool.DOMAIN_INTEGER, ClassConstantPool.DOMAIN_ATTRIBUTEASCIIZ);
    }

    /**
     * Answer an iterator which iterates over the sets
     * from sets[start] to sets[stop] only.
     * @param start int the first set over which iteration will occur.
     * @param stop int the last set over which iteration will occur
     * @return Iterator
     */
    public Iterator partialIterator(int start, int stop) {
        // Make sure there's nothing in the undefined domain
        if(sets[ClassConstantPool.DOMAIN_UNDEFINED].size() > 0) {
            throw new Error("Trying to get iterator but have undefined elements");
        }
        List someElements = new ArrayList();
        for(int index=start; index <= stop; index++) {
            someElements.addAll(sets[index]);
        }
        return someElements.iterator();        
    }
}
