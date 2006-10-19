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
package org.apache.harmony.archive.internal.pack200;
//NOTE: Do not use generics in this code; it needs to run on JVMs < 1.5
//NOTE: Do not extract strings as messages; this code is still a work-in-progress
//NOTE: Also, don't get rid of 'else' statements for the hell of it ...
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.harmony.archive.internal.pack200.ClassFileEntry.Attribute;

public class ClassFile {
	int major;
	int minor;
	private int magic = 0xCAFEBABE;
	ClassConstantPool pool = new ClassConstantPool();
	int accessFlags;
	int thisClass;
	int superClass;
	int[] interfaces;
	int[] fields;
	int[] methods;
	Attribute[] attributes;
	public void write(DataOutputStream dos) throws IOException {
		dos.writeInt(magic);
		dos.writeShort(minor);
		dos.writeShort(major);
		dos.writeShort(pool.size()+1);
		for(int i=1;i<=pool.size();i++) {
			ConstantPoolEntry entry;
			(entry = (ConstantPoolEntry)pool.get(i)).write(dos);
			// Doubles and longs take up two spaces in the pool, but only one gets written
			if (entry.getTag() == ConstantPoolEntry.CP_Double || entry.getTag() == ConstantPoolEntry.CP_Long)
				i++;
		};
		dos.writeShort(accessFlags);
		dos.writeShort(thisClass);
		dos.writeShort(superClass);
		dos.writeShort(interfaces.length);
		for(int i=0;i<interfaces.length;i++) {
			dos.writeShort(interfaces[i]);
		}
		dos.writeShort(fields.length);
		for(int i=0;i<fields.length;i++) {
			// dos.write
			// write field info
		}
		dos.writeShort(methods.length);
		for(int i=0;i<fields.length;i++) {
			// write method info
		}
		dos.writeShort(attributes.length);
		for(int i=0;i<attributes.length;i++) {
			Attribute a = attributes[i];
			a.write(dos);
		}
	}
}
