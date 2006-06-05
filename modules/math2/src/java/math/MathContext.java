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

/**
 * @ar.org.fitc.spec_ref
 * @author Daniel Fridlender
 * @author Matthias Gallé
 * @author Mariano Heredia
 * @author Miguel Vasquez 
 */
public final class MathContext {
    
    /* Fields */
    /**@ar.org.fitc.spec_ref*/
    public static final MathContext DECIMAL128 = new MathContext(34, RoundingMode.HALF_EVEN);
    
    /**@ar.org.fitc.spec_ref*/
    public static final MathContext DECIMAL32  = new MathContext(7,  RoundingMode.HALF_EVEN);
    
    /**@ar.org.fitc.spec_ref*/
    public static final MathContext DECIMAL64  = new MathContext(16, RoundingMode.HALF_EVEN);
    
    /**@ar.org.fitc.spec_ref*/
    public static final MathContext UNLIMITED  = new MathContext(0,  RoundingMode.HALF_UP);
    
    private int precision;
    private RoundingMode roundingMode;
    
    /**@see #MathContext(String)*/
    private final static char[] chPrecision = {'p','r','e','c','i','s','i','o','n','='};
    
    /**@see #MathContext(String)*/
    private final static char[] chRoundingMode = {'r','o','u','n','d','i','n','g','M','o','d','e','='};
    
    
    /* Constructors */
    /**@ar.org.fitc.spec_ref*/
    public MathContext(int setPrecision) {
        if (setPrecision < 0) {
            throw new IllegalArgumentException("Digits < 0");
        }
        precision = setPrecision;
        roundingMode = RoundingMode.HALF_UP; // default value
    }
    /**@ar.org.fitc.spec_ref*/
    public MathContext(int setPrecision, RoundingMode setRoundingMode) {
        if (setPrecision < 0) {
            throw new IllegalArgumentException("Digits < 0");
        }
        if( setRoundingMode == null ){
            throw new NullPointerException( "null RoundingMode");
        }
        precision = setPrecision;
        roundingMode = setRoundingMode;
    }
    
    /**
     * @see #chPrecision
     * @see #chRoundingMode
     * @ar.org.fitc.spec_ref
     */
    public MathContext(String val) {
        // val must be = "precision={digit} roundingMode={roundingMode}"
        char[]  charVal = val.toCharArray();
        int i;      // Index of charVal
        int j;      // Index of chRoundingMode
        int digit;  // It will contain the digit parsed
        
        if ((charVal.length < 27) || (charVal.length > 45)) {
            throw new IllegalArgumentException("bad string format");
        }
        // Parsing "precision=" string
        for (i = 0; (i < chPrecision.length) && (charVal[i] == chPrecision[i]); i++);
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
                    // parsed all the digit
                    i++;
                    break;
                } else {   // it isn't  a valid digit, and isn't a white space
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
        for (j = 0; (j < chRoundingMode.length) && (charVal[i] == chRoundingMode[j]); i++, j++);
        if (j < chRoundingMode.length) {
            throw new IllegalArgumentException("bad string format");
        }
        // Parsing the value for "roundingMode"...
        this.roundingMode = RoundingMode.valueOf(String.valueOf(charVal, i, charVal.length - i));
    }
    
    /* Methods */
    /**@ar.org.fitc.spec_ref*/
    public boolean equals(Object x) {
        return ((x instanceof MathContext)
        && (((MathContext)x).getPrecision() == precision)
        && (((MathContext)x).getRoundingMode() == roundingMode));
    }
    
    /**@ar.org.fitc.spec_ref*/
    public int getPrecision() {
        return precision;
    }
    
    /**@ar.org.fitc.spec_ref*/
    public RoundingMode getRoundingMode() {
        return roundingMode;
    }
    
    /**@ar.org.fitc.spec_ref*/
    public int hashCode() {
        // Make place for the necessary bits to represent 8 rounding modes
        return ((precision << 3) | roundingMode.ordinal());
    }
    
    /** @ar.org.fitc.spec_ref*/
    public String toString() {
        StringBuffer sb = new StringBuffer(45);
        
        sb.append(chPrecision);
        sb.append(precision);
        sb.append(' ');
        sb.append(chRoundingMode);
        sb.append(roundingMode);
        return sb.toString();
    }   
}
