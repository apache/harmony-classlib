/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.concurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Enumeration;

/**
 * <p>
 * A utility class for loading Service Provider implementations that are
 * registered using the <a
 * href="http://java.sun.com/j2se/1.5.0/docs/guide/jar/jar.html#Service%20Provider">Service
 * Providers<a> mechanism defined by the <a
 * href="http://java.sun.com/j2se/1.5.0/docs/guide/jar/jar.html">JAR File
 * Specification</a>.
 * </p>
 */
class ServiceLoader {

    /**
     * <p>
     * Loads a Service Provider for the given interface (SPI).
     * </p>
     * 
     * @param <T> The SPI type.
     * @param spiClass The class that defines the Service Provider.
     * @return An instance of an implementation or <code>null</code> if a
     *         provider couldn't be found or constructed.
     * @throws NullPointerException if <code>spiClass</code> is
     *         <code>null</code>.
     * @throws IllegalArgumentException if <code>spiClass</code> is neither an
     *         interface nor an abstract class.
     */
    static <T> T load(Class<T> spiClass) {
        if (spiClass == null) {
            throw new NullPointerException();
        }

        if (!spiClass.isInterface() || !Modifier.isAbstract(spiClass.getModifiers())) {
            throw new IllegalArgumentException(
                    "The SPI class must be an interface or an abstract class.");
        }

        String spiResURI = "META-INF/services/" + spiClass.getName();
        ClassLoader classLoader = spiClass.getClassLoader();
        Enumeration<URL> resources;
        try {
            resources = classLoader.getResources(spiResURI);
        } catch (IOException e) {
            return null;
        }

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(resource.openStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    int cmtIdx = line.indexOf('#');
                    if (cmtIdx != -1) {
                        line = line.substring(0, cmtIdx);
                        line = line.trim();
                    }

                    if (line.length() == 0) {
                        continue;
                    }

                    Class<?> implClass;
                    try {
                        implClass = classLoader.loadClass(line);
                    } catch (ClassNotFoundException e) {
                        // TODO determine how to log this
                        e.printStackTrace();
                        continue;
                    }

                    if (!spiClass.isAssignableFrom(implClass)) {
                        continue;
                    }

                    Object impl;
                    try {
                        impl = implClass.newInstance();
                    } catch (Exception e) {
                        // TODO determine how to log this
                        e.printStackTrace();
                        continue;
                    }

                    // TODO When Class.cast is implemented, use this instead.
                    // return spi.cast(impl);
                    return (T) impl;
                }
            } catch (IOException e) {
                // TODO determine how to log this
                e.printStackTrace();
                continue;
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                    }
                }
            }
        }

        return null;
    }

    private ServiceLoader() {
    }
}
