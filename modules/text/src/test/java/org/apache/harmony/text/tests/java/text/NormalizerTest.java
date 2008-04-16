/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.text.tests.java.text;

import java.text.Normalizer.Form;

import junit.framework.TestCase;

public class NormalizerTest extends TestCase {
    /**
     * @tests java.text.Normalizer.Form#values()
     */
    public void test_form_values() throws Exception {
        Form[] forms = Form.values();
        assertEquals(4, forms.length);
        assertEquals(Form.NFD, forms[0]);
        assertEquals(Form.NFC, forms[1]);
        assertEquals(Form.NFKD, forms[2]);
        assertEquals(Form.NFKC, forms[3]);
    }

    /**
     * @tests java.text.Normalizer.Form#valueOf(String)
     */
    public void test_form_valueOfLjava_lang_String() {
        try {
            Form.valueOf(null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        assertEquals(Form.NFC, Form.valueOf("NFC"));
        assertEquals(Form.NFD, Form.valueOf("NFD"));
        assertEquals(Form.NFKC, Form.valueOf("NFKC"));
        assertEquals(Form.NFKD, Form.valueOf("NFKD"));

        try {
            Form.valueOf("not exist");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            Form.valueOf("nfc");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
        
        try {
            Form.valueOf("NFC ");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }
}
