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
package java.rmi.server;

import java.net.MalformedURLException;

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public abstract class RMIClassLoaderSpi {

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public RMIClassLoaderSpi() {
        // Constructor
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public abstract Class<?> loadProxyClass(String codebase,
            String[] interfaces, ClassLoader defaultLoader)
            throws MalformedURLException, ClassNotFoundException;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public abstract String getClassAnnotation(Class cl);

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public abstract Class<?> loadClass(String codebase, String name,
            ClassLoader defaultLoader) throws MalformedURLException,
            ClassNotFoundException;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public abstract ClassLoader getClassLoader(String codebase)
            throws MalformedURLException;

}
