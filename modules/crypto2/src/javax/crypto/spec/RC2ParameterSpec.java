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

import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

/**
 * 
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 * @ar.org.fitc.spec_ref
 */
public class RC2ParameterSpec implements AlgorithmParameterSpec {
    private int effectiveKeyBits;
	
    private byte[] iv;

    /** @ar.org.fitc.spec_ref */
    public RC2ParameterSpec(int effectiveKeyBits) {
    	this.effectiveKeyBits = effectiveKeyBits;
    }

    /** @ar.org.fitc.spec_ref */
    public RC2ParameterSpec(int effectiveKeyBits, byte[] iv) {
    	this(effectiveKeyBits, iv, 0);
    }

    /** @ar.org.fitc.spec_ref */
    public RC2ParameterSpec(int effectiveKeyBits, byte[] iv, int offset) {
    	if (iv == null) {
            throw new IllegalArgumentException("IV cannot be null");
        }
    	if ((iv.length - offset) < 8) {
            throw new IllegalArgumentException("IV is too short");
        }
    	this.iv = new byte[8];
    	System.arraycopy(iv, offset, this.iv, 0, 8);
    	this.effectiveKeyBits = effectiveKeyBits;      	
    }

    /** @ar.org.fitc.spec_ref */
    public boolean equals(Object obj) {
        if (obj == this) {
    	    return true;
        } else if (obj instanceof RC2ParameterSpec){
            RC2ParameterSpec s = (RC2ParameterSpec)obj;
    		if (s.effectiveKeyBits == effectiveKeyBits 
                    && Arrays.equals(s.iv, iv)) {
    			return true;
    		} 
    	}
    	return false;    	
    }

    /** @ar.org.fitc.spec_ref */
    public int getEffectiveKeyBits() {
        return effectiveKeyBits;
    }

    /** @ar.org.fitc.spec_ref */
    public byte[] getIV() {
    	return ((iv == null) ? null : iv.clone());
    }

    /** @ar.org.fitc.spec_ref */
    public int hashCode() {
    	return Arrays.hashCode(iv) ^ effectiveKeyBits;
    }
}
