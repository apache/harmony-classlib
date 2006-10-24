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
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.OutputStream;
import java.util.SortedMap;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.Pack200.Packer;

/**
 * This class provides the binding between the standard Pack200 interface and the
 * internal interface for (un)packing. As this uses generics for the SortedMap,
 * this class must be compiled and run on a Java 1.5 system. However, Java 1.5
 * is not necessary to use the internal libraries for unpacking.
 */
public class Pack200PackerAdapter extends Pack200Adapter implements Packer {

	public void pack(JarFile arg0, OutputStream arg1) throws IOException {
		throw new Error("Not yet implemented");
	}

	public void pack(JarInputStream arg0, OutputStream arg1) throws IOException {
		throw new Error("Not yet implemented");
	}

}
