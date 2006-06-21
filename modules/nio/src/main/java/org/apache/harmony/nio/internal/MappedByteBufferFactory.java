/* Copyright 2004, 2006 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.nio.internal;

import java.lang.reflect.Constructor;
import java.nio.MappedByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;

import org.apache.harmony.luni.platform.PlatformAddress;

class MappedByteBufferFactory {

	static final Constructor constructor;

	static {
		constructor = (Constructor) AccessController
				.doPrivileged(new PrivilegedAction<Object>() {
					public Object run() {
						try {
							Class wrapperClazz = ClassLoader
									.getSystemClassLoader().loadClass(
											"java.nio.MappedByteBufferAdapter"); //$NON-NLS-1$
							Constructor result = wrapperClazz
									.getConstructor(new Class[] {
											PlatformAddress.class, int.class,
											int.class, int.class });
							result.setAccessible(true);
							return result;
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
				});
	}

	static MappedByteBuffer getBuffer(PlatformAddress addr, int mapmode,
			long size, int offset) throws Exception {
		// FIXME: the long->int cast of size may be dangerous, but JavaSpec
		// specifies that the mmap size cannot be larger than
		// Integer.MAX_VALUE, may since the direct buffer only supports size of
		// 32 bits. Maybe int the later version(i.e. 64 bit platform)
		// this issue can be handled more elegantly
		return (MappedByteBuffer) constructor.newInstance(new Object[] { addr,
				new Integer((int) size), new Integer(offset),
				new Integer(mapmode) });
	}
}
