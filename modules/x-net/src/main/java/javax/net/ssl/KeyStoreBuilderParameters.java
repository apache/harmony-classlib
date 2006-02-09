/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
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
* @author Boris V. Kuznetsov
* @version $Revision$
*/

package javax.net.ssl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.security.KeyStore;

/**
 * @com.intel.drl.spec_ref
 * 
 */
public class KeyStoreBuilderParameters implements ManagerFactoryParameters {
    
    private List ksbuilders;
    public KeyStoreBuilderParameters(KeyStore.Builder builder) {
    	if (builder == null) {
    		throw new NullPointerException("builder parameter is null");
    	}
        //      FIXME for 1.5 classes
        //      new ArrayList() -> Collections.emptyList();
        ksbuilders = new ArrayList();
        ksbuilders.add(builder);         
    }
    public KeyStoreBuilderParameters(List parameters) {
    	if (parameters == null) {
    		throw new NullPointerException("Builders list is null");
    	}
    	if (parameters.isEmpty()) {
    		throw new IllegalArgumentException("Builders list is empty");
    	}
        ksbuilders = new ArrayList(parameters);
    }
    
    public List getParameters() {
        return Collections.unmodifiableList(ksbuilders);
    }
}
