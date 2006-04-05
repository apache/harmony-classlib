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
* @author Alexander Y. Kleymenov
* @version $Revision$
*/

package javax.crypto.spec;

/**
 * @com.intel.drl.spec_ref
 */
public class PSource {

    private String pSrcName;

    private PSource() {}

    /**
     * @com.intel.drl.spec_ref
     */
    protected PSource(String pSrcName) {
        if (pSrcName == null) {
            throw new NullPointerException("pSrcName is null!");
        }
        this.pSrcName = pSrcName;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getAlgorithm() {
        return pSrcName;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static final class PSpecified extends PSource {

        private final byte[] p;

        /**
         * @com.intel.drl.spec_ref
         */
        public static final PSpecified DEFAULT = new PSpecified();

        private PSpecified() {
            p = new byte[0];
        }

        /**
         * @com.intel.drl.spec_ref
         */
        public PSpecified(byte[] p) {
            if (p == null) {
                throw new NullPointerException("encoding input is null!");
            }
            //TODO: It is unknown which name should be used!
            //super("");
            this.p = new byte[p.length];
            System.arraycopy(p, 0, this.p, 0, p.length);
        }

        /**
         * @com.intel.drl.spec_ref
         */
        public byte[] getValue() {
            byte[] result = new byte[p.length];
            System.arraycopy(p, 0, result, 0, p.length);
            return result;
        }
    }
}

