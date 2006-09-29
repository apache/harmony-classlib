/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.xnet.tests.javax.net.ssl;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.KeyStoreBuilderParameters;

import junit.framework.TestCase;


/**
 * Tests for <code>KeyStoreBuilderParameters</code> class constructors and
 * methods.
 *  
 */
public class KeyStoreBuilderParametersTest extends TestCase {

    /*
     * Class under test for void KeyStoreBuilderParameters(KeyStore.Builder)
     */
    public final void testKeyStoreBuilderParametersBuilder() {
        // should not throw NullPointerException
        new KeyStoreBuilderParameters((KeyStore.Builder) null);
    }

    /*
     * Class under test for void KeyStoreBuilderParameters(List)
     */
    public final void testKeyStoreBuilderParametersList() {
        try {
            new KeyStoreBuilderParameters((List) null);
            fail("No expected NullPointerException");
        } catch (NullPointerException e) {
        }

        try {
            new KeyStoreBuilderParameters(new ArrayList());
            fail("No expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

    }

    public final void testGetParameters() {
        List ksbuilders;
        KeyStore.Builder builder = new myBuilder();
        List result;
        KeyStoreBuilderParameters param = new KeyStoreBuilderParameters(builder);
        result = param.getParameters();
        try {
            result.add(new myBuilder());
            fail("The list is modifiable");
        } catch (UnsupportedOperationException e) {
        }
        if (result.size() != 1) {
            fail("incorrect size");
        }
        if (!result.contains(builder)) {
            fail("incorrect list");
        }
        
        ksbuilders = new ArrayList();
        ksbuilders.add(builder);
        ksbuilders.add(new myBuilder());  
        param = new KeyStoreBuilderParameters(ksbuilders);
        result = param.getParameters();
        try {
            result.add(new Object());
            fail("The list is modifiable");
        } catch (UnsupportedOperationException e) {
        }
        if (result.size() != 2) {
            fail("incorrect size");
        }
        if (!result.containsAll(ksbuilders)) {
            fail("incorrect list");
        }
    }
}

class myBuilder extends KeyStore.Builder {
    public KeyStore getKeyStore() throws KeyStoreException {
        return null;
    }

    public KeyStore.ProtectionParameter getProtectionParameter(String alias)
            throws KeyStoreException {
        return null;
    }
}
