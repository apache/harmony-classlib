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

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @com.intel.drl.spec_ref
 * 
 */
public class EllipticCurve {
    // Underlying finite field
    private final ECField field;
    // The first coefficient of the equation defining this elliptic curve
    private final BigInteger a;
    // The second coefficient of the equation defining this elliptic curve
   private final BigInteger b;
    // Bytes used during this elliptic curve generation,
    // if it was generated randomly
    private final byte[] seed;
    // Hash code
    private volatile int hash;

    /**
     * @com.intel.drl.spec_ref
     */
    public EllipticCurve(ECField field, BigInteger a,
            BigInteger b, byte[] seed) {
        this.field = field;
        if (this.field == null) {
            throw new NullPointerException("the field parameter is null");
        }
        this.a = a;
        if (this.a == null) {
            throw new NullPointerException("the a parameter is null");
        }
        this.b = b;
        if (this.b == null) {
            throw new NullPointerException("the b parameter is null");
        }
        // make defensive copy
        if (seed == null) {
            this.seed = null;
        } else {
            this.seed = new byte[seed.length];
            System.arraycopy(seed, 0, this.seed, 0, this.seed.length);
        }
        // check parameters
        // Check invariant: a and b must be in the field.
        if (this.field instanceof ECFieldFp) {
            BigInteger p = ((ECFieldFp)this.field).getP();
            if (this.a.signum() < 0 ||
                this.a.compareTo(p) >= 0) {
                throw new IllegalArgumentException("the a is not in the field");
            }
            if (this.b.signum() < 0 ||
                    this.b.compareTo(p) >= 0) {
                    throw new IllegalArgumentException(
                            "the a is not in the field");
                }
        } else {
            int fieldSizeInBits = this.field.getFieldSize();
            if (!(this.a.bitLength() <= fieldSizeInBits)) {
                throw new IllegalArgumentException("the a is not in the field");
            }
            if (!(this.b.bitLength() <= fieldSizeInBits)) {
                throw new IllegalArgumentException("the b is not in the field");
            }
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public EllipticCurve(ECField field, BigInteger a, BigInteger b) {
        this(field, a, b, null);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigInteger getA() {
        return a;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigInteger getB() {
        return b;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public ECField getField() {
        return field;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public byte[] getSeed() {
        // return copy
        byte[] ret = new byte[seed.length];
        System.arraycopy(seed, 0, ret, 0, ret.length);
        return ret;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EllipticCurve)) {
            return false;
        }
        EllipticCurve otherEc = (EllipticCurve)other;
        return this.field.equals(otherEc.field) &&
               this.a.equals(otherEc.a) &&
               this.b.equals(otherEc.b) &&
               Arrays.equals(this.seed, otherEc.seed);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int hashCode() {
        // hash init is delayed
        if (hash == 0) {
            int hash0 = 11;
            hash0 = hash0 * 31 + field.hashCode();
            hash0 = hash0 * 31 + a.hashCode();
            hash0 = hash0 * 31 + b.hashCode();
            if (seed != null) {
                for (int i=0; i<seed.length; i++) {
                    hash0 = hash0*31 + seed[i];
                }
            } else {
                hash0 = hash0 * 31;
            }
            hash = hash0;
        }
        return hash;
    }
}
