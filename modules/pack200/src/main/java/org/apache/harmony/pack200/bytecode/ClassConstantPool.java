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
import java.util.Iterator;
import java.util.List;


public class ClassConstantPool {
	public String toString() {
		return entries.toString();
	}
	private List others = new ArrayList();

	private List entries = new ArrayList();

	private boolean resolved;

	public ClassFileEntry add(ClassFileEntry entry) {
		// TODO this should be a set - we don't want duplicates
		// Only add in constant pools, but resolve all types since they may
		// introduce new constant pool entries
		if (entry instanceof ConstantPoolEntry) {
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
		return entries.indexOf(entry) + 1;
	}

	public int size() {
		return entries.size();
	}

	public ClassFileEntry get(int i) {
		if (!resolved)
			throw new IllegalStateException("Constant pool is not yet resolved; this does not make any sense");
		return (ClassFileEntry) entries.get(--i);
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
	}

}
