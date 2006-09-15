/* Copyright 2005 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.sql.internal.common;

/**
 * A class containing sets of utility functions relating to Classes,
 * ClassLoaders and associated aspects of Java.
 */
public class ClassUtils {

    /**
     * Gets the ClassLoader of method that called the method which called this
     * method. ie if C2.m2() calls this method, and C1.m1() calls C2.m2(), this
     * method returns the ClassLoader for class C1.
     * 
     * @return The ClassLoader of the caller's caller. TODO - needs completing
     */
    public static ClassLoader getCallerClassLoader() {
        return null;
    }

    /**
     * Finds if a supplied Object belongs to the given ClassLoader.
     * 
     * @param theObject
     *            the object to check
     * @param theClassLoader
     *            the ClassLoader
     * @return true if the Object does belong to the ClassLoader, false
     *         otherwise
     */
    public static boolean isClassFromClassLoader(Object theObject,
            ClassLoader theClassLoader) {

        if ((theObject == null) || (theClassLoader == null)) {
            return false;
        }

        Class objectClass = theObject.getClass();

        try {
            Class checkClass = Class.forName(objectClass.getName(), true,
                    theClassLoader);
            if (checkClass == objectClass) {
                return true;
            }
        } catch (Throwable t) {
            // Empty
        }
        return false;
    }
}
