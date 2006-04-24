/*
 *  Copyright 2005-2006 The Apache Software Foundation or its licensors, as applicable.
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
 * @author Elena Semukhina
 * @version $Revision$
 */

package java.math;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * @com.intel.drl.spec_ref
 */
public class MathContext implements Serializable {
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static final MathContext DECIMAL128 = 
        new MathContext(34, RoundingMode.HALF_EVEN);

    /**
     * @com.intel.drl.spec_ref
     */
    public static final MathContext DECIMAL32 = 
        new MathContext(7, RoundingMode.HALF_EVEN);

    /**
     * @com.intel.drl.spec_ref
     */
    public static final MathContext DECIMAL64 = 
        new MathContext(16, RoundingMode.HALF_EVEN);
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static final MathContext UNLIMITED = 
        new MathContext(0, RoundingMode.HALF_UP);

    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = 5579720004786848255L; 

    final int precision;
    final RoundingMode roundingMode;

    /**
     * @com.intel.drl.spec_ref
     */
    public MathContext(int requiredPrecision) {
        this(requiredPrecision, RoundingMode.HALF_UP);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public MathContext(int requiredPrecision, RoundingMode rm) {
        if (rm == null) {
            throw new NullPointerException("null roundingMode");
        }
        if (requiredPrecision < 0) {
            throw new IllegalArgumentException("negative precision");
        }
        this.precision = requiredPrecision;
        this.roundingMode = rm;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public MathContext(String value) {
        if (!value.startsWith("precision=")) {
            throw new IllegalArgumentException("the argument should start " +
                                               "with \"precision=\"");
        }
        int spacePosition = value.indexOf(' ');
        if (spacePosition == -1) {
            throw new IllegalArgumentException("the argument's words should " +
                                               "be separated by the space");
        }
        if (value.lastIndexOf(" ") != spacePosition) {
            throw new IllegalArgumentException("there should be only one " +
                                               "space symbol in the argument");
        }
        try {
            precision = Integer.parseInt(value.substring(10, spacePosition));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("bad precision value");
        }
        int shift = spacePosition + 1;
        int valLen = value.length();
        if (valLen - shift < 14) {
            throw new IllegalArgumentException("bad roundingMode value");
        }
        if (!value.substring(shift, shift + 13).equals("roundingMode=")) {
            throw new IllegalArgumentException
                ("the second word in the argument should " +
                 "be \"roundingMode=<rounding mode>\"");
        }
        shift += 13;
        try {
            roundingMode = RoundingMode.valueOf(value.substring(shift, valLen));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("bad roundingMode value");
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean equals(Object obj) {
        return (obj instanceof MathContext &&
            this.precision == ((MathContext)obj).precision && 
            this.roundingMode.equals(((MathContext)obj).roundingMode));
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int getPrecision() {
        return precision;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public RoundingMode getRoundingMode() {
        return roundingMode;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int hashCode() {
        return precision * roundingMode.ordinal() + roundingMode.hashCode();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String toString() {
        return "precision=" + precision + " roundingMode=" + 
               roundingMode.toString();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    private void readObject(ObjectInputStream s)
        throws IOException,
        ClassNotFoundException {
        s.defaultReadObject();
        if (precision < 0) {
            throw new StreamCorruptedException("bad precision value");
        }
        if (roundingMode == null) {
            throw new StreamCorruptedException("null roundingMode");
        }
    }
}

