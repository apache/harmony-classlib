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

package org.apache.harmony.luni.tests.java.net;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

import org.apache.harmony.luni.internal.net.www.protocol.jar.Handler;

import junit.framework.TestCase;

import tests.support.Support_Configuration;
import tests.support.resource.Support_Resources;

/**
 *  Depends on:
 *    file://<basedir>/src/test/resources/org/apache/harmony/luni/tests/java/net/lf.jar
 */
public class URLClassLoaderImplTest extends TestCase {
    
    private static final char SEP = File.separatorChar;
    private static final URL BASE = URLClassLoaderImplTest.class.getClassLoader().getResource(".."+SEP+URLClassLoaderImplTest.class.getPackage().getName().replace('.', SEP));

    /**
     * @tests java.net.URLClassLoader#URLClassLoader(java.net.URL[],
     *        java.lang.ClassLoader, java.net.URLStreamHandlerFactory)
     */
    public void test_Constructor$Ljava_net_URLLjava_lang_ClassLoaderLjava_net_URLStreamHandlerFactory() {
        class TestFactory implements URLStreamHandlerFactory {
            public URLStreamHandler createURLStreamHandler(String protocol) {
                if ("jar".equals(protocol)) {
                    return new Handler();
                } else {
                    fail("Should be jar Handler. But " + protocol);
                    return null;
                }
            }

        }

        URLClassLoader ucl = null;

        URL[] u = new URL[1];
        try {
            u[0] = new URL(BASE.toString() + SEP + "lf.jar");
            ucl = new URLClassLoader(u, null, new TestFactory());
            URL res = null;
            res = ucl.findResource("swt.dll");

            assertNotNull(res);
            assertEquals("Failed", BASE.toString()+SEP+"lf.jar!"+SEP+"swt.dll", res.getFile());
        } catch (MalformedURLException e) {
            fail("should not be here. " + e);
        }
    }

}
