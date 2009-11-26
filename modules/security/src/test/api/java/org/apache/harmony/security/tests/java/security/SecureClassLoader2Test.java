/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.security.tests.java.security;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.ProtectionDomain;
import java.security.SecureClassLoader;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.jar.JarFile;

import tests.support.Support_GetLocal;

public class SecureClassLoader2Test extends junit.framework.TestCase {

	/**
	 * @tests java.security.SecureClassLoader#getPermissions(java.security.CodeSource)
	 */
	public void test_getPermissionsLjava_security_CodeSource() throws IOException {
		class MyClassLoader extends SecureClassLoader {
			public PermissionCollection getPerms() {
				return super.getPermissions(new CodeSource(null,
						(Certificate[]) null));
			}

			public Class define(String name, byte[] bytes) {
				return defineClass(name, bytes, 0, bytes.length,
						(ProtectionDomain) null);
			}
		}

		MyClassLoader myloader = new MyClassLoader();
		PermissionCollection pc = myloader.getPerms();
		Enumeration e1 = pc.elements();
		int count = 0;
		while (e1.hasMoreElements()) {
			e1.nextElement();
			count++;
		}
		assertEquals("expected no permissions", 0, count);

        File file = Support_GetLocal.getLocalFile("hyts_security.jar");
        JarFile jar = new JarFile(file);
        InputStream in = jar.getInputStream(jar
                .getEntry("packA/SecurityTest.class"));
        byte[] bytes = drain(in);
        Class c = myloader.define("packA.SecurityTest", bytes);
		ProtectionDomain pd = c.getProtectionDomain();
		assertNotNull("Expected dynamic policy", pd.getClassLoader());
		assertNull("Expected null permissions", pd.getPermissions());
	}

    /*
     * Drains the entire content from the given input stream and returns it as a
     * byte[]. The stream is closed after being drained, or if an IOException
     * occurs.
     */
    private byte[] drain(InputStream is) throws IOException {
        try {
            // Initial read
            byte[] buffer = new byte[1024];
            int count = is.read(buffer);
            int nextByte = is.read();

            // Did we get it all in one read?
            if (nextByte == -1) {
                byte[] dest = new byte[count];
                System.arraycopy(buffer, 0, dest, 0, count);
                return dest;
            }

            // Requires additional reads
            ByteArrayOutputStream baos = new ByteArrayOutputStream(count * 2);
            baos.write(buffer, 0, count);
            baos.write(nextByte);
            while (true) {
                count = is.read(buffer);
                if (count == -1) {
                    return baos.toByteArray();
                }
                baos.write(buffer, 0, count);
            }
        } finally {
            is.close();
        }
    }
}