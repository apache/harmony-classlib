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

/**
 * 
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 * @ar.org.fitc.spec_ref
 */
public class PSource { 
    private String pSrcName;
    
    /**
     * 
     * 
     * @author Diego Raúl Mercado
     * @author Sergio Daniel Liendo
     * @version 1.2
     * @ar.org.fitc.spec_ref
     */
    public static final class PSpecified extends PSource { 
        /** Indicates the algorithm's name of this object */
        private final static String ALGORITHM = "PSpecified";
        
        private byte[] p;
        
        /** @ar.org.fitc.spec_ref */
        public static final PSpecified DEFAULT = new PSpecified(new byte[0]);

        /** @ar.org.fitc.spec_ref */
        public PSpecified(byte[] p) {
        	super(ALGORITHM);
        	this.p = p.clone();
        }

        /** @ar.org.fitc.spec_ref */
        public byte[] getValue() {
        	return p.clone();
        }
    }
    
    /** @ar.org.fitc.spec_ref */
    protected PSource (String pSrcName) {
    	this.pSrcName = pSrcName;
    }

    /** @ar.org.fitc.spec_ref */
    public String getAlgorithm() {
        return pSrcName;
    }
}
