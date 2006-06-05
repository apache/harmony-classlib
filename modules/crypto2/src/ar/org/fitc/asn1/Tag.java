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

package ar.org.fitc.asn1;

/**
 * Groups ASN.1 tags
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 */
enum Tag {
    OCTET_STRING(4), SEQUENCE(48), OID(6), NULL(5);

    /** TAG's value */
    private int value;

    /**
     * Constructor. Sets the TAG's value
     * 
     * @param value
     *            the value of this type
     */
    private Tag(int value) {
        this.value = value;
    }

    /**
     * @return the value of this object
     */
    int getIntValue() {
        return value;
    }

    /**
     * @return the DER encoded of the current TAG. Returns a new Array each time
     *         this method is called
     */
    byte[] getByteArrayValue() {
        if (this == NULL) {
            return new byte[] { (byte) getIntValue(), 0 };
        }
        return new byte[] { (byte) getIntValue() };
    }
}
