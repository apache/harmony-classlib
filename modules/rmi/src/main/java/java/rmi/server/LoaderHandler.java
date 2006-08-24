/*
 * Copyright 2005-2006 The Apache Software Foundation or its licensors, as applicable
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
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author  Mikhail A. Markov
 * @version $Revision: 1.3.4.2 $
 */
package java.rmi.server;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * @com.intel.drl.spec_ref
 *
 * @author  Mikhail A. Markov
 * @version $Revision: 1.3.4.2 $
 * @deprecated This interface was being used by previous versions of RMI.
 *     It should not be used anymore. There is no replacement.
 */
@Deprecated
public interface LoaderHandler {

    /**
     * @com.intel.drl.spec_ref
     */
    @Deprecated
    public static final String packagePrefix = "org.apache.harmony.rmi";

    /**
     * @com.intel.drl.spec_ref
     */
    @Deprecated
    public Object getSecurityContext(ClassLoader loader);

    /**
     * @com.intel.drl.spec_ref
     */
    @Deprecated
    public Class<?> loadClass(URL codebase, String name)
            throws MalformedURLException, ClassNotFoundException;

    /**
     * @com.intel.drl.spec_ref
     */
    @Deprecated
    public Class<?> loadClass(String name)
            throws MalformedURLException, ClassNotFoundException;
}
