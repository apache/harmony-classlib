/* 
*  Copyright 2005 The Apache Software Foundation or its licensors, as applicable. 
* 
*  Licensed under the Apache License, Version 2.0 (the "License"); 
*  you may not use this file except in compliance with the License. 
*  You may obtain a copy of the License at 
* 
*    http://www.apache.org/licenses/LICENSE-2.0 
* 
*  Unless required by applicable law or agreed to in writing, software 
*  distributed under the License is distributed on an "AS IS" BASIS, 
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
*  See the License for the specific language governing permissions and 
*  limitations under the License. 
*/
package org.apache.harmony.rmi.internal.utils;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * A Collection of utility methods used to get the Remote interfaces and
 * methods from a Class.
 * 
 * @author Gonzalo Ortega
 */
public final class RemoteUtils {
	
	/**
	 * Map used to cache the remote interfaces of a class, saving time when
	 * exporting multiple instances of the same class. 
	 */
	static Map<Class, Set<Class>> remoteInterfacesCache;

	static {
		remoteInterfacesCache = Collections.synchronizedMap(new WeakHashMap<Class, Set<Class>>());
	}
	
    /**
     * Returns a set containing all the remote interfaces implemented by a class
     * 
     * @param inspect
     *            the class that implements the remote interfaces
     * @return A <code>Set</code> containing all the remote interfaces
     *         implemented by a class
     */
    public final static Set<Class> getRemoteInterfaces(Class inspect) {
    	if (remoteInterfacesCache.containsKey(inspect)) {
    		return remoteInterfacesCache.get(inspect);
    	}
        LinkedHashSet<Class> classSet = new LinkedHashSet<Class>();
        Class clazz = inspect;
        do {
            getRemoteInterfacesAux(clazz, classSet);
        } while ((clazz = clazz.getSuperclass()) != null);
        remoteInterfacesCache.put(inspect, classSet);
        return classSet;
    }

    /**
     * Auxiliary method needed for <code>getRemoteInterfaces</code>
     * 
     * @param inspect
     *            The class or intarface that implements or extends the remote
     *            interface
     * @param classSet
     *            A <code>Set</code> where the found remote intrefaces are
     *            stored
     * @return <code>true</code> if the received intarface is
     *         <code>Remote</code>
     */
    private final static boolean getRemoteInterfacesAux(Class inspect,
            Set<Class> classSet) {
        boolean isRemote = false;

        Class[] allInterfaces = inspect.getInterfaces();
        for (int x = 0; x < allInterfaces.length; x++) {
            isRemote = isRemote
                    | getRemoteInterfacesAux(allInterfaces[x], classSet);
        }
        if (inspect.equals(java.rmi.Remote.class)) {
            isRemote = true;
        }
        if (isRemote && inspect.isInterface()) {
            classSet.add(inspect);
        }
        return isRemote;
    }

    /**
     * Returns a set containing all the remote methods declared by all the
     * remote interfaces implemented by a class.
     * 
     * @param inspect
     *            the class that implements the remote interfaces
     * @return A <code>Set</code> containing all the remote methods of the
     *         class
     */
    public final static Set<Method> getRemoteMethods(Class inspect) {
        Set<Class> classSet = getRemoteInterfaces(inspect);
        LinkedHashSet<Method> methods = new LinkedHashSet<Method>();

        for (Class interfaz : classSet) {
            Method[] methodArray = interfaz.getDeclaredMethods();
            for (int y = 0; y < methodArray.length; y++) {
                Class[] exceptions = methodArray[y].getExceptionTypes();
                for (Class clazz : exceptions) {
                    if (clazz.equals(java.rmi.RemoteException.class)
                            || clazz.equals(java.io.IOException.class)
                            || clazz.equals(java.lang.Exception.class)) {
                        methods.add(methodArray[y]);
                    }
                }
            }
        }
        return methods;
    }
}
