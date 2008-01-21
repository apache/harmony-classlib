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

import org.apache.harmony.pack200.Segment;
import org.apache.harmony.pack200.SegmentUtils;


public class ClassConstantPool {
    
    class PoolComparator implements Comparator {
        /* (non-Javadoc)
         * Note: this comparator imposes orderings that are inconsistent with equals.
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        public int compare(Object o1, Object o2) {
            // If we compare anything other than ConstantPoolEntries
            // with this comparator, it is an error.
            ConstantPoolEntry cpe1 = (ConstantPoolEntry)o1;
            ConstantPoolEntry cpe2 = (ConstantPoolEntry)o2;

            int domain1 = cpe1.getDomain();
            int domain2 = cpe2.getDomain();
            
            if(domain1 < domain2) {
                return -1;
            }
            if(domain1 > domain2) {
                return 1;
            }
            
            // Domains must be the same, need to compare
            // based on name.
            // TODO: what means name?
            String compare1 = cpe1.comparisonString();
            String compare2 = cpe2.comparisonString();
            return compare1.compareTo(compare2);
//            if(cpe1.creationOrder < cpe2.creationOrder) {
//                return -1;
//            } else {
//                return 1;
//            }
        }
    }
    // These are the domains in sorted order.
    public static int DOMAIN_UNDEFINED = 0;
    public static int DOMAIN_INTEGER = 1;
    public static int DOMAIN_FLOAT = 2;
    public static int DOMAIN_STRING = 3;
    public static int DOMAIN_NORMALASCIIZ = 4;
    public static int DOMAIN_LONG = 5;
    public static int DOMAIN_DOUBLE = 6;
    public static int DOMAIN_CLASSREF = 7;
    public static int DOMAIN_SIGNATUREASCIIZ = 8;
    public static int DOMAIN_NAMEANDTYPE = 9;
    public static int DOMAIN_FIELD = 10;
    public static int DOMAIN_METHOD = 11;
    public static int DOMAIN_ATTRIBUTEASCIIZ = 12;
    public static int NUM_DOMAINS = DOMAIN_ATTRIBUTEASCIIZ + 1;

//    protected SortedSet sortedEntries = new TreeSet(new PoolComparator());
    protected ClassPoolSet classPoolSet = new ClassPoolSet();
	public String toString() {
		return entries.toString();
	}
	private List others = new ArrayList();

	private List entries = new ArrayList();

	private boolean resolved;

	public ClassFileEntry add(ClassFileEntry entry) {
		// We don't want duplicates.
		// Only add in constant pools, but resolve all types since they may
		// introduce new constant pool entries
// This is a handy way to see what's adding a ClassFileEntry - set a breakpoint on the print 
//	    if(entry instanceof CPUTF8) {
//	        System.out.println("AAH:" + ((CPUTF8)entry).comparisonString());
//	        if (((CPUTF8)entry).underlyingString().matches("Code")) {
//	            System.out.println("Adding");
//	        }
//	    }
		if (entry instanceof ConstantPoolEntry) {
            classPoolSet.add(entry);
			if (!entries.contains(entry)) {
				entries.add(entry);
				// TODO This will be a bugger when they're sorted.
				if (entry instanceof CPLong ||entry instanceof CPDouble)
					entries.add(entry); //these get 2 slots because of their size
			}
		} else {
			if (!others.contains(entry))
				others.add(entry);
		}
		ClassFileEntry[] nestedEntries = entry.getNestedClassFileEntries();
		for (int i = 0; i < nestedEntries.length; i++) {
			add(nestedEntries[i]);
		}
		return entry;
	}

	public int indexOf(ClassFileEntry entry) {
		if (!resolved)
			throw new IllegalStateException("Constant pool is not yet resolved; this does not make any sense");
		int entryIndex = entries.indexOf(entry);
		// If the entry isn't found, answer -1. Otherwise answer the entry.
		if(entryIndex != -1) {
		    return entryIndex + 1;
		}
		return -1;
	}

	public int size() {
		return entries.size();
	}

	public ClassFileEntry get(int i) {
		if (!resolved)
			throw new IllegalStateException("Constant pool is not yet resolved; this does not make any sense");
		return (ClassFileEntry) entries.get(--i);
	}

	public void resolve(Segment segment) {
		SegmentUtils.debug("\n\nResolving (Segment.resolve(Segment)");
		// TODO: Be careful here, you're obliterating the original entries.
		// In an ideal world, you wouldn't actually add to it unless you're
		// sure.
        entries = new ArrayList();
      Iterator sortedIterator = classPoolSet.iterator();
      while(sortedIterator.hasNext()) {
          ConstantPoolEntry entry = (ConstantPoolEntry)sortedIterator.next();
          entries.add(entry);
          // need to do this both here and in the sort below
          // so the indices are correct.
          if (entry instanceof CPLong ||entry instanceof CPDouble)
              entries.add(entry); //these get 2 slots because of their size
      }
		resolve();
	}
	
	public void resolve() {
		resolved= true;
		Iterator it = entries.iterator();
		while (it.hasNext()) {
			ClassFileEntry entry = (ClassFileEntry) it.next();
			entry.resolve(this);
		}
		it = others.iterator();
		while (it.hasNext()) {
			ClassFileEntry entry = (ClassFileEntry) it.next();
			entry.resolve(this);
		}
		
		// Now that everything has been resolved, do one
		// final sort of the class pool. This fixes up
		// references, which are sorted by index in the
		// class pool.
		it = entries.iterator();
		ClassPoolSet finalSort = new ClassPoolSet();
		while(it.hasNext()) {
		    finalSort.add(it.next());
		}
		it = finalSort.iterator();
		entries = new ArrayList();
		while(it.hasNext()) {
		    ClassFileEntry entry = (ClassFileEntry) it.next();
		    entries.add(entry);
	        if (entry instanceof CPLong ||entry instanceof CPDouble)
	            entries.add(entry); //these get 2 slots because of their size
		}
	}

}
