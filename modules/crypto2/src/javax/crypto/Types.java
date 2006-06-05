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

/**
 * Groups the types of services provided
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 */
enum Types {
    CIPHER("Cipher"), 
    KEY_AGREEMENT("KeyAgreement"), 
    MAC("Mac"), 
    SECRET_KEY_FACTORY("SecretKeyFactory"), 
    EXEMPTION_MECHANISM("ExemptionMechanism"), 
    KEY_GENERATOR("KeyGenerator");

    /** TAG's value */
    private String name;

    /**
     * Constructor. Sets the TAG's value
     * 
     * @param name
     *            the value of this type
     */
    private Types(String name) {
        this.name = name;
    }

    /**
     * @return the value of this object
     */
    public String toString() {
        return name;
    }
}
