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

package java.math;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;

/**
 * @ar.org.fitc.spec_ref
 * @author Daniel Fridlender
 * @author Matthias Gallé
 * @author Mariano Heredia
 * @author Miguel Vasquez 
 */
public class MathContext implements Serializable {

    /* Fields */

    /** @ar.org.fitc.spec_ref */
    public static final MathContext DECIMAL128 = new MathContext(34,
            RoundingMode.HALF_EVEN);

    /** @ar.org.fitc.spec_ref */
    public static final MathContext DECIMAL32 = new MathContext(7,
            RoundingMode.HALF_EVEN);

    /** @ar.org.fitc.spec_ref */
    public static final MathContext DECIMAL64 = new MathContext(16,
            RoundingMode.HALF_EVEN);

    /** @ar.org.fitc.spec_ref */
    public static final MathContext UNLIMITED = new MathContext(0,
            RoundingMode.HALF_UP);

    /** @ar.org.fitc.spec_ref */
    private static final long serialVersionUID = 5579720004786848255L;

    /**
     * The number of digits to be used for an operation; 
     * results are rounded to this precision.
     */
    private int precision;

    /**
     * A {@code RoundingMode} object which specifies 
     * the algorithm to be used for rounding.
     */
    private RoundingMode roundingMode;

    /** 
     * An array of {@code char} containing: 
     * {@code 'p','r','e','c','i','s','i','o','n','='}.
     * It's used to improve the methods related to {@code String} conversion.
     * @see #MathContext(String)
     * @see #toString() 
     */
    private final static char[] chPrecision = { 'p', 'r', 'e', 'c', 'i', 's',
            'i', 'o', 'n', '=' };

    /** 
     * An array of {@code char} containing: 
     * {@code 'r','o','u','n','d','i','n','g','M','o','d','e','='}.
     * It's used to improve the methods related to {@code String} conversion.
     * @see #MathContext(String)
     * @see #toString() 
     */
    private final static char[] chRoundingMode = { 'r', 'o', 'u', 'n', 'd',
            'i', 'n', 'g', 'M', 'o', 'd', 'e', '=' };

    /* Constructors */

    /** @ar.org.fitc.spec_ref */
    public MathContext(int setPrecision) {
        this(setPrecision, RoundingMode.HALF_UP);
    }

    /** @ar.org.fitc.spec_ref */
    public MathContext(int setPrecision, RoundingMode setRoundingMode) {
        if (setPrecision < 0) {
            throw new IllegalArgumentException("Digits < 0");
        }
        if (setRoundingMode == null) {
            throw new NullPointerException("null RoundingMode");
        }
        precision = setPrecision;
        roundingMode = setRoundingMode;
    }

    /** @ar.org.fitc.spec_ref */
    public MathContext(String val) {
        char[] charVal = val.toCharArray();
        int i; // Index of charVal
        int j; // Index of chRoundingMode
        int digit; // It will contain the digit parsed

        if ((charVal.length < 27) || (charVal.length > 45)) {
            throw new IllegalArgumentException("bad string format");
        }
        // Parsing "precision=" String
        for (i = 0; (i < chPrecision.length) && (charVal[i] == chPrecision[i]); i++)
            ;

        if (i < chPrecision.length) {
            throw new IllegalArgumentException("bad string format");
        }
        // Parsing the value for "precision="...
        digit = Character.digit(charVal[i], 10);
        if (digit == -1) {
            throw new IllegalArgumentException("bad string format");
        }
        this.precision = this.precision * 10 + digit;
        i++;

        do {
            digit = Character.digit(charVal[i], 10);
            if (digit == -1) {
                if (charVal[i] == ' ') {
                    // It parsed all the digits
                    i++;
                    break;
                } else {// It isn't  a valid digit, and isn't a white space
                    throw new IllegalArgumentException("bad string format");
                }
            }
            // Acumulating the value parsed
            this.precision = this.precision * 10 + digit;
            if (this.precision < 0) {
                throw new IllegalArgumentException("bad string format");
            }
            i++;
        } while (true);
        // Parsing "roundingMode="
        for (j = 0; (j < chRoundingMode.length)
                && (charVal[i] == chRoundingMode[j]); i++, j++)
            ;

        if (j < chRoundingMode.length) {
            throw new IllegalArgumentException("bad string format");
        }
        // Parsing the value for "roundingMode"...
        this.roundingMode = RoundingMode.valueOf(String.valueOf(charVal, i,
                charVal.length - i));
    }

    /* Public Methods */

    /** @ar.org.fitc.spec_ref */
    public int getPrecision() {
        return precision;
    }

    /** @ar.org.fitc.spec_ref */
    public RoundingMode getRoundingMode() {
        return roundingMode;
    }

    /** @ar.org.fitc.spec_ref */
    @Override
    public boolean equals(Object x) {
        return ((x instanceof MathContext)
                && (((MathContext) x).getPrecision() == precision) && (((MathContext) x)
                .getRoundingMode() == roundingMode));
    }

    /** @ar.org.fitc.spec_ref */
    @Override
    public int hashCode() {
        // Make place for the necessary bits to represent 8 rounding modes
        return ((precision << 3) | roundingMode.ordinal());
    }

    /** @ar.org.fitc.spec_ref */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(45);

        sb.append(chPrecision);
        sb.append(precision);
        sb.append(' ');
        sb.append(chRoundingMode);
        sb.append(roundingMode);
        return sb.toString();
    }

    /** @ar.org.fitc.spec_ref */
    private void readObject(ObjectInputStream s) throws IOException,
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
