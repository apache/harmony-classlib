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

package org.apache.harmony.jndi.provider.ldap.asn1;

import java.io.UnsupportedEncodingException;

public class Utils {

    private static final String CODING_CHARSET = "UTF-8"; //$NON-NLS-1$

    /**
     * Convert <code>bytes</code> to <code>String</code> using UTF-8
     * charset. when <code>bytes</code> is <code>null</code>, empty String
     * will be returned.
     * 
     * @param bytes
     *            bytes to be decoded into a UTF-8 string
     * @return UTF-8 String composed of bytes, never be empty string
     */
    public static String getString(byte[] bytes) {
        try {
            return (bytes == null) ? "" : new String(bytes, CODING_CHARSET); //$NON-NLS-1$
        } catch (UnsupportedEncodingException e) {
            // never reached, because UTF-8 is supported by all java platform
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * Encodes <code>s</code> into a sequence of bytes using UTF-8 charset.
     * 
     * @param s
     *            String to be encoded
     * @return UTF-8 String
     */
    public static byte[] getBytes(String s) {
        try {
            return (s == null) ? new byte[0] : s.getBytes(CODING_CHARSET);
        } catch (UnsupportedEncodingException e) {
            // never reached, because UTF-8 is supported by all java platform
            return new byte[0];
        }
    }
}
