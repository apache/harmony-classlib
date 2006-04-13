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

package java.security;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;

/**
 * @com.intel.drl.spec_ref
 * 
 */

public abstract class AlgorithmParametersSpi {

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected abstract void engineInit(AlgorithmParameterSpec paramSpec)
            throws InvalidParameterSpecException;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected abstract void engineInit(byte[] params) throws IOException;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected abstract void engineInit(byte[] params, String format)
            throws IOException;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected abstract <T extends AlgorithmParameterSpec> T engineGetParameterSpec(
            Class<T> paramSpec) throws InvalidParameterSpecException;

    //    FIXME 1.5 signature: protected abstract <T extends
    // AlgorithmParameterSpec> T engineGetParameterSpec(Class<T> paramSpec)
    // throws InvalidParameterSpecException;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected abstract byte[] engineGetEncoded() throws IOException;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected abstract byte[] engineGetEncoded(String format)
            throws IOException;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected abstract String engineToString();

}