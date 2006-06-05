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
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */

package ar.org.fitc.test.integration.cipher;

import java.security.Key;

import junit.extensions.TestSetup;
import junit.framework.Test;
import ar.org.fitc.test.crypto.cipher.TestCipher;
import ar.org.fitc.test.util.crypto.AllTransformation;

public class CipherTestSetup extends TestSetup {
    public String algorithm;
    public String mode;
    public String padding;
    public String providerString;
    public Key key;

    public CipherTestSetup(Test test, AllTransformation t) {
        super(test);
        useTransformation(t);
    }

    public CipherTestSetup(Test test) {
        super(test);
    }
    public void useTransformation(AllTransformation t ) {
        algorithm = t.getAlgorithm();
        mode = t.getMode();
        padding = t.getPadding();
        providerString = t.getProvider();
        key = t.getKeyObject();
    }

    protected void setUp() throws Exception {
        TestCipher.algorithm = algorithm;
        TestCipher.mode = mode;
        TestCipher.padding = padding;
        TestCipher.providerString = providerString;
        TestCipher.key = key;
    }
}