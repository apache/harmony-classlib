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

package org.apache.harmony.security.tests.java.security.cert;

import java.io.IOException;
import java.security.cert.X509CertSelector;

import junit.framework.TestCase;

/**
 * X509CertSelectorTest
 */
public class X509CertSelectorTest extends TestCase {

    /**
     * @tests addSubjectAlternativeName(int type, String name)
     */
    public void testAddSubjectAlternativeName() {
        // Regression for HARMONY-727
        int[] types = { 0, 2, 3, 4, 5, 6, 7, 8 };
        for (int i = 0; i < types.length; i++) {
            try {
                new X509CertSelector().addSubjectAlternativeName(types[i],
                        "0xDFRF");
                fail("IOException expected");
            } catch (IOException e) {
            }
        }
    }
    
    /**
     * @tests addPathToName(int type, String name)
     */
    public void testAddPathToName() {
        // Regression for HARMONY-724
        for (int type = 0; type <= 8; type++) {
            try {
                new X509CertSelector().addPathToName(type, (String) null);
                fail("IOException expected!");
            } catch (IOException ioe) {
                // expected
            }
        }
    }

}
