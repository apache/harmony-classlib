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

package javax.crypto;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

/**
 * 
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 * @ar.org.fitc.spec_ref
 */
public abstract class ExemptionMechanismSpi {

    /** @ar.org.fitc.spec_ref */
    public ExemptionMechanismSpi() {
    }

    /** @ar.org.fitc.spec_ref */
    protected abstract byte[] engineGenExemptionBlob()
            throws ExemptionMechanismException;

    /** @ar.org.fitc.spec_ref */
    protected abstract int engineGenExemptionBlob(byte[] output,
            int outputOffset) throws ShortBufferException,
            ExemptionMechanismException;

    /** @ar.org.fitc.spec_ref */
    protected abstract int engineGetOutputSize(int inputLen);

    /** @ar.org.fitc.spec_ref */
    protected abstract void engineInit(Key key) throws InvalidKeyException,
            ExemptionMechanismException;

    /** @ar.org.fitc.spec_ref */
    protected abstract void engineInit(Key key, AlgorithmParameters params)
            throws InvalidKeyException, InvalidAlgorithmParameterException,
            ExemptionMechanismException;

    /** @ar.org.fitc.spec_ref */
    protected abstract void engineInit(Key key, AlgorithmParameterSpec params)
            throws InvalidKeyException, InvalidAlgorithmParameterException,
            ExemptionMechanismException;
}
