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
* @author Vladimir N. Molotkov, Stepan M. Mishura
* @version $Revision$
*/

package com.openintel.drl.security.asn1;

import java.io.IOException;

/**
 * Thrown by decoder/encoder stream to indicate violation of encoding rules.
 */

public class ASN1Exception extends IOException {

    /**
     * Constructs an ASN1Exception without a message. 
     */
    public ASN1Exception(){
    }

    /**
     * Constructs an ASN1Exception with a message. 
     * 
     * @param message - a string that describes encoding violation 
     */
    public ASN1Exception(String message){
        super(message);
    }
}
