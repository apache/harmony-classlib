/*
 *  Copyright 2005 - 2006 The Apache Software Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/**
 * @author Sergey I. Salishev, Artem A. Aliev, Andrey Y. Chernyshev
 * @version $Revision$
 */
package org.apache.harmony.misc.accessors;

/**
 * Factory for all accessors. This class is used to get the instances of various
 * accessor classes methods. 
 * Each factory method checks security permission before it returns 
 * an appropriate accessor singelton.
 */
public class AccessorFactory {

    /*
     * Used for security checking before returning an instance of accessors.
     * <code>classLoader</code> is bootstrap class loader.
     */
    private static ClassLoader classLoader = AccessorFactory.class.getClassLoader();

    private static void checkPermissions() throws SecurityException {
        Class callerClass = ThreadStackAccessor.getInstance().getCallerClass(5);
        ClassLoader callingClassLoader = callerClass.getClassLoader();
        if (!(classLoader == null ? callingClassLoader == null : classLoader.equals(callingClassLoader))) {
            throw new SecurityException("User code is not allowed to get accessors, caller class: " + callerClass);
        }
    }

    private AccessorFactory() {
    }

    /**
     * Returns the {@link MemoryAccessor}.
     *
     * @throws SecurityException if a requester class is loaded by diffrent
     * class loader from the one used for loading the accessors (i.e. boot class loader).
     */
    public static MemoryAccessor getMemoryAccessor() {
        checkPermissions();
        return MemoryAccessor.getInstance();
    }
    
    /**
     * Returns the {@link ObjectAccessor}.
     *
     * @throws SecurityException if a requester class is loaded by diffrent
     * class loader from the one used for loading the accessors (i.e. boot class loader).
     */
    public static ObjectAccessor getObjectAccessor() {
        checkPermissions();
        return ObjectAccessor.getInstance();
    }

    /**
     * Returns the {@link ArrayAccessor}.
     *
     * @throws SecurityException if a requester class is loaded by diffrent
     * class loader from the one used for loading the accessors (i.e. boot class loader).
     */
    public static ArrayAccessor getArrayAccessor() {
        checkPermissions();
        return ArrayAccessor.getInstance();
    }

    /**
     * Returns the {@link StringAccessor}.
     *
     * @throws SecurityException if a requester class is loaded by diffrent
     * class loader from the one used for loading the accessors (i.e. boot class loader).
     */
    public static StringAccessor getStringAccessor() {
        checkPermissions();
        return StringAccessor.getInstance();
    }

    /**
     * Returns the {@link ThreadStackAccessor}.
     *
     * @throws SecurityException if a requester class is loaded by diffrent
     * class loader from the one used for loading the accessors (i.e. boot class loader).
     */
    public static ThreadStackAccessor getThreadStackAccessor() {
        checkPermissions();
        return ThreadStackAccessor.getInstance();
    }
}