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

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.InvalidAlgorithmParameterException;
import java.nio.ByteBuffer;
import java.security.spec.AlgorithmParameterSpec;

/**
 * 
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 * @ar.org.fitc.spec_ref
 */
public abstract class MacSpi {

    /** @ar.org.fitc.spec_ref */
    public MacSpi() {
    }

    /** @ar.org.fitc.spec_ref */
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("This operation is not supported");
    }

    /** @ar.org.fitc.spec_ref */
    protected abstract byte[] engineDoFinal();

    /** @ar.org.fitc.spec_ref */
    protected abstract int engineGetMacLength();

    /** @ar.org.fitc.spec_ref */
    protected abstract void engineInit(Key key, AlgorithmParameterSpec params)
            throws InvalidKeyException, InvalidAlgorithmParameterException;

    /** @ar.org.fitc.spec_ref */
    protected abstract void engineReset();

    /** @ar.org.fitc.spec_ref */
    protected abstract void engineUpdate(byte input);

    /** @ar.org.fitc.spec_ref */
    protected abstract void engineUpdate(byte[] input, int offset, int len);

    /** @ar.org.fitc.spec_ref */
    protected void engineUpdate(ByteBuffer input) {
	    byte [] b = new byte[input.remaining()];
		input.get(b);
		engineUpdate(b,0,b.length);	
    }
}
