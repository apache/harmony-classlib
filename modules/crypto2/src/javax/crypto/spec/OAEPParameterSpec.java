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

package javax.crypto.spec;

import javax.crypto.spec.PSource;
import javax.crypto.spec.OAEPParameterSpec;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;

/**
 * 
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 * @ar.org.fitc.spec_ref
 */
public class OAEPParameterSpec implements AlgorithmParameterSpec { 
    /** Encapsulates the message digest's name */
    private String mdName;

    /** Encapsulates the mask generation function's name */
    private String mgfName;

    /** Encapsulates the algorithm's parameter of the mask generation function */
    private AlgorithmParameterSpec mgfSpec;

    private PSource pSrc;
    
    /** @ar.org.fitc.spec_ref */
    public static final OAEPParameterSpec DEFAULT = new OAEPParameterSpec(
            "SHA-1", "MGF1", MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT);

    /** @ar.org.fitc.spec_ref */
    public OAEPParameterSpec(String mdName, String mgfName, 
    		AlgorithmParameterSpec mgfSpec, PSource pSrc) {
	    if (mdName == null || mgfName == null || pSrc == null) {
            throw new NullPointerException(
                    "mdName, mgfName and pSrc cannot be null");
        }
        this.mdName = mdName;
        this.mgfName = mgfName;
        this.mgfSpec = mgfSpec;
        this.pSrc = pSrc;
    }

    /** @ar.org.fitc.spec_ref */
    public String getDigestAlgorithm() {
        return this.mdName;
    }
    
    /** @ar.org.fitc.spec_ref */
    public String getMGFAlgorithm() {
        return this.mgfName;
    }
    
    /** @ar.org.fitc.spec_ref */
    public AlgorithmParameterSpec getMGFParameters() {
        return this.mgfSpec;
    }
    
    /** @ar.org.fitc.spec_ref */
    public PSource getPSource() {
        return this.pSrc;
    }
}
