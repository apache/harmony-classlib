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

/**
 * This abstract class is the super class for all constructured ASN.1 types
 * 
 * @see http://asn1.elibel.tm.fr/en/standards/index.htm
 */

public abstract class ASN1Constructured extends ASN1Type {

    public ASN1Constructured(int tagNumber) {
        super(CLASS_UNIVERSAL, true, tagNumber);
    }

    public ASN1Constructured(int tagClass, int tagNumber) {
        super(tagClass, true, tagNumber);
    }
}