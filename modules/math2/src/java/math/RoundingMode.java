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

import java.util.Hashtable;

/**
 * @ar.org.fitc.spec_ref
 * @author Daniel Fridlender
 * @author Matthias Gallé
 * @author Mariano Heredia
 * @author Miguel Vasquez 
 */
public enum RoundingMode {
    /** @ar.org.fitc.spec_ref */
    UP,
    
    /** @ar.org.fitc.spec_ref */    
    DOWN,
    
    /** @ar.org.fitc.spec_ref */    
    CEILING,
    
    /** @ar.org.fitc.spec_ref */
    FLOOR,
    
    /** @ar.org.fitc.spec_ref */
    HALF_UP,
    
    /** @ar.org.fitc.spec_ref */
    HALF_DOWN,
    
    /** @ar.org.fitc.spec_ref */
    HALF_EVEN,
    
    /** @ar.org.fitc.spec_ref */
    UNNECESSARY;
    
    /**
     * It's initialized maping each rounding mode of BigDecimal with the 
     * corresponding rounding mode of this enum. 
     */
    private static Hashtable<Integer, RoundingMode> rmValues;
    
    /* To initialize rmValues. A hashtable maps the rounding modes of BigDecimal 
     * to the rounding modes of this enum. */
    static {    
        rmValues = new Hashtable<Integer, RoundingMode>(8);
        rmValues.put(BigDecimal.ROUND_UP, UP);
        rmValues.put(BigDecimal.ROUND_DOWN, DOWN);
        rmValues.put(BigDecimal.ROUND_CEILING, CEILING);
        rmValues.put(BigDecimal.ROUND_FLOOR, FLOOR);
        rmValues.put(BigDecimal.ROUND_HALF_UP, HALF_UP);
        rmValues.put(BigDecimal.ROUND_HALF_DOWN, HALF_DOWN);
        rmValues.put(BigDecimal.ROUND_HALF_EVEN, HALF_EVEN);
        rmValues.put(BigDecimal.ROUND_UNNECESSARY, UNNECESSARY);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public static RoundingMode valueOf(int rm) {
        RoundingMode enumRM = rmValues.get(rm);
        
        if (enumRM != null) {
            return enumRM;            
        } else {
            throw new IllegalArgumentException("argument out of range");
        }
    }
    
}
