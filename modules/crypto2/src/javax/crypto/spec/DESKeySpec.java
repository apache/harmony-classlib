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

import java.security.InvalidKeyException;
import java.security.spec.KeySpec;

/**
 * 
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 * @ar.org.fitc.spec_ref
 */
public class DESKeySpec implements KeySpec {
    /** @ar.org.fitc.spec_ref */
    public static final int DES_KEY_LEN = 8;
    
    /** Represents 1 in hexadecimal */
    private static final byte C_01 = 0x01;
    
    /** Represents 1F in hexadecimal */
    private static final byte C_1F = 0x1F;
    
    /** Represents E0 in hexadecimal */
    private static final byte C_E0 = (byte)0xE0;
    
    /** Represents FE in hexadecimal */
    private static final byte C_FE = (byte)0xFE;
    
    /** Represents 0E in hexadecimal */
    private static final byte C_0E = 0x0E;
    
    /** Represents F1 in hexadecimal */
    private static final byte C_F1 = (byte)0xF1;
    
    private static final byte[][] KEYS = new byte[][] {
    	//weak...
    	{C_01, C_01, C_01, C_01, C_01, C_01, C_01, C_01},
    	{C_1F, C_1F, C_1F, C_1F, C_0E, C_0E, C_0E, C_0E}, 
    	{C_E0, C_E0, C_E0, C_E0, C_F1, C_F1, C_F1, C_F1}, 
    	{C_FE, C_FE, C_FE, C_FE, C_FE, C_FE, C_FE, C_FE},
    	//semi-weak...
    	{C_01, C_FE, C_01, C_FE, C_01, C_FE, C_01, C_FE},
    	{C_FE, C_01, C_FE, C_01, C_FE, C_01, C_FE, C_01},
    	{C_1F, C_E0, C_1F, C_E0, C_0E, C_F1, C_0E, C_F1},
    	{C_E0, C_1F, C_E0, C_1F, C_F1, C_0E, C_F1, C_0E},
    	{C_01, C_E0, C_01, C_E0, C_01, C_F1, C_01, C_F1}, 
    	{C_E0, C_01, C_E0, C_01, C_F1, C_01, C_F1, C_01},
    	{C_1F, C_FE, C_1F, C_FE, C_0E, C_FE, C_0E, C_FE},
    	{C_FE, C_1F, C_FE, C_1F, C_FE, C_0E, C_FE, C_0E},
    	{C_01, C_1F, C_01, C_1F, C_01, C_0E, C_01, C_0E},
    	{C_1F, C_01, C_1F, C_01, C_0E, C_01, C_0E, C_01},
    	{C_E0, C_FE, C_E0, C_FE, C_F1, C_FE, C_F1, C_FE},
    	{C_FE, C_E0, C_FE, C_E0, C_FE, C_F1, C_FE, C_F1}
    };
    
    /** Encapsulates the key */
    private byte[] key;
    
    /**
     * Returns <code>true</code> if the given <code>key</code>, starting from 
     * <code>offset</code> inclusive to <code>offset+len</code>, is parity 
     * adjusted
     * 
     * @param key the key to probe 
     * @param offset the starting position
     * @param len the numbers of bytes to read
     * @return true if the key is parity adjusted
     * @throws InvalidKeyException If the key size is invalid (<code>key.length 
     * - offset < len </code>)
     */
    static boolean isParityAdjusted(byte[] key, int offset, int len)	
            throws InvalidKeyException {    	    	
		int byteKey = 0;
			
    	if (key == null) {    	
    		throw new NullPointerException("key cannot be null");
    	}	
    	if ((key.length - offset) < len) {
    		throw new InvalidKeyException("Wrong key size");    		
    	}
    	
    	for(int i = offset; i < len; i++){    		
    		byteKey = key[i];
    			
    		byteKey ^= byteKey >> 1;
    		byteKey ^= byteKey >> 2;
    		byteKey ^= byteKey >> 4;
    			
    		if ((byteKey & 1) == 0){
    			return false;
    		}    		
    	}   	 
    	return true;
    }

    /** @ar.org.fitc.spec_ref */
    public static boolean isParityAdjusted(byte[] key, int offset)
            throws InvalidKeyException {
        return isParityAdjusted(key, offset, DES_KEY_LEN);
    }

    /**
     * @ar.org.fitc.spec_ref
     * According to FIPS PUB 74 these are the <i>weak</i> and <i>semi-weak</i>
     * keys for the DES algorithm:
     * <DIV align="center">
     * <table><tr>
     * <th>WEAK KEYS</th><th></th><th>SEMI-WEAK KEYS</th></tr>
     * <tr VALIGN=top>
     * 
     * <td><table border=1>
     * <tr><td>0101</td><td>0101</td><td>0101</td><td>0101</td></tr>
     * <tr><td>1F1F</td><td>1F1F</td><td>0E0E</td><td>0E0E</td></tr>
     * <tr><td>E0E0</td><td>E0E0</td><td>F1F1</td><td>F1F1</td></tr>
     * <tr><td>FEFE</td><td>FEFE</td><td>FEFE</td><td>FEFE</td></tr>
     * </table></td>
     * 
     * <td width="15%" > </td>
     *  
     * <td><table border=1>
     * <tr><td>01FE</td><td>01FE</td><td>01FE</td><td>01FE</td></tr>
     * <tr><td>FE01</td><td>FE01</td><td>FE01</td><td>FE01</td></tr>
     * <tr><td>1FE0</td><td>1FE0</td><td>0EF1</td><td>0EF1</td></tr>
     * <tr><td>E01F</td><td>E01F</td><td>F10E</td><td>F10E</td></tr>
     * <tr><td>E001</td><td>E001</td><td>F101</td><td>F101</td></tr>
     * <tr><td>1FFE</td><td>1FFE</td><td>0EFE</td><td>0EFE</td></tr>
     * <tr><td>FE1F</td><td>FE1F</td><td>FE0E</td><td>FE0E</td></tr>
     * <tr><td>011F</td><td>011F</td><td>010E</td><td>010E</td></tr>
     * <tr><td>1F01</td><td>1F01</td><td>0E01</td><td>0E01</td></tr>
     * <tr><td>E0FE</td><td>E0FE</td><td>F1FE</td><td>F1FE</td></tr>
     * <tr><td>FEE0</td><td>FEE0</td><td>FEF1</td><td>FEF1</td></tr>
     * <tr><td>01E0</td><td>01E0</td><td>01F1</td><td>01F1</td></tr>
     * </table></td>
     * </tr></table>
     * </DIV>
     * <br>
     * <br>
     * Only parity adjusted keys are correctly checked.
     * @ar.org.fitc.ref <i>"Guidelines for implementing and using the NBS
     *                  (National Bureau of Standards) Data Encryption Standard"</i>
     *                  - Federal Information Processing Standards Publication 74 -
     *                  NIST, 1981 April 1
     */
    public static boolean isWeak(byte[] key, int offset)
            throws InvalidKeyException {
        if (key == null) {
            throw new InvalidKeyException("key cannot be null");
        }
        if ((key.length - offset) < 8) {
    		throw new InvalidKeyException("Wrong key size");
    	}

    	/*
    	 * Evaluate the following expressions:
         *  - the first octet equals the third octect
         *  - the second octect equals the fourth octect
         *  - the first octect and the second octect has any of these values: 
         *    C_01, C_1F, C_E0 o C_FE 
    	 */
		if (((key[offset] == key[offset+2]) && ((key[offset+1] == key[offset+3]))) 
				&& (((key[offset] ==  C_01) || (key[offset] == C_1F) 
					|| (key[offset] == C_E0) || (key[offset] == C_FE)) 
					&& ((key[offset+1] ==  C_01) || (key[offset+1] == C_1F) 
							|| (key[offset+1] == C_E0) || (key[offset+1] == C_FE)))) {
		    byte[] weakKey;    
		    KEY:
		        for (int j = KEYS.length; --j >= 0; ) {
		            weakKey = KEYS[j];
		            for (int i = DES_KEY_LEN; --i >= 0; ) {
		                if (weakKey[i] != key[offset+i]) {
		                    continue KEY;
		                }
		            }
		            return true;
		        }
			} 
		return false;  
    }

    /** @ar.org.fitc.spec_ref */
    public DESKeySpec(byte[] key) throws InvalidKeyException {
    	this(key, 0);
    }

    /** @ar.org.fitc.spec_ref */
    public DESKeySpec(byte[] key, int offset) throws InvalidKeyException {
    	if ((key.length - offset) < 8) {
    		throw new InvalidKeyException("Wrong key size");
    	}
    	
    	this.key = new byte[DES_KEY_LEN];
    	System.arraycopy(key, offset, this.key, 0, DES_KEY_LEN);
    }

    /** @ar.org.fitc.spec_ref */
    public byte[] getKey() {
    	return key.clone();
    }
}
