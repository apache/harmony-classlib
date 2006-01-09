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
* @author Vladimir N. Molotkov
* @version $Revision$
*/

package com.openintel.drl.security.spec;

import java.security.spec.EncodedKeySpec;

/**
 * Support class for abstract base class testing
 */

public class MyEncodedKeySpec extends EncodedKeySpec {

    /**
     * Constructor
     * @param encodedKey
     */
    public MyEncodedKeySpec(byte[] encodedKey) {
        super(encodedKey);
    }

    /**
     * Returns format - "My"
     * @see java.security.spec.EncodedKeySpec#getFormat()
     */
    public String getFormat() {
        return "My";
    }

}
