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
* @author Vera Y. Petrashkova
* @version $Revision$
*/

package javax.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Additional class for verification of MacGeneratorSpi and MacSpi
 * 
 */

public class MyMacSpi extends MacSpi {
   
    private int length = 0;
    protected int engineGetMacLength() {
        return length;
    }

    protected void engineInit(Key key, AlgorithmParameterSpec params)
            throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (params == null) {
            if (!(key instanceof SecretKeySpec)) {
                throw new IllegalArgumentException("params is null and key is SecretKeySpec");
            }
        }
    }

    protected void engineUpdate(byte input) {
    }

    protected void engineUpdate(byte[] input, int offset, int len) {
        if (offset >= 0 && len >= 0) {
            length = len;
        }
    }

    protected byte[] engineDoFinal() {
        return new byte[length];
    }

    protected void engineReset() {
        length++;
    }  
}