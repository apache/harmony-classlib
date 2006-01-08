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
* @author Vladimir N. Molotkov
* @version $Revision$
*/

package java.security.spec;

/**
 * @com.intel.drl.spec_ref
 * 
 */
public class PSSParameterSpec implements AlgorithmParameterSpec {   
    /**
     * @com.intel.drl.spec_ref
     */
    public static final PSSParameterSpec DEFAULT = new PSSParameterSpec(20);

    // Message digest algorithm name
    private final String mdName;
    // Mask generation function algorithm name
    private final String mgfName;
    // Mask generation function parameters
    private final AlgorithmParameterSpec mgfSpec;
    // Trailer field value
    private final int trailerField;
    // Salt length in bits
    private final int saltLen;

    /**
     * @com.intel.drl.spec_ref
     */
    public PSSParameterSpec(int saltLen) {
        if (saltLen < 0) {
            throw new IllegalArgumentException("invalid saltLen");
        }
        this.saltLen = saltLen;
        this.mdName = "SHA-1";
        this.mgfName = "MGF1";
        this.mgfSpec = MGF1ParameterSpec.SHA1;
        this.trailerField = 1;
    }

    /**
     * @com.intel.drl.spec_ref     * 
     */
    public PSSParameterSpec(String mdName, String mgfName,
            AlgorithmParameterSpec mgfSpec, int saltLen, int trailerField) {

        if (mdName == null) {
            throw new NullPointerException("mdName is null");
        }
        if (mgfName == null) {
            throw new NullPointerException("mgfName is null");
        }
        if (saltLen < 0) {
            throw new IllegalArgumentException("invalid saltLen");
        }
        if (trailerField < 0) {
            throw new IllegalArgumentException("invalid trailerField");
        }
        this.mdName = mdName;
        this.mgfName = mgfName;
        this.mgfSpec = mgfSpec;
        this.saltLen = saltLen;
        this.trailerField = trailerField;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int getSaltLength() {
        return saltLen;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getDigestAlgorithm() {
        return mdName;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getMGFAlgorithm() {
        return mgfName;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public AlgorithmParameterSpec getMGFParameters() {
        return mgfSpec;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int getTrailerField() {
        return trailerField;
    }
}
