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
 * This class represents ASN.1 Set type.
 * 
 * @see http://asn1.elibel.tm.fr/en/standards/index.htm
 */

public class ASN1Set extends ASN1TypeCollection {

    public ASN1Set(ASN1Type[] type) {
        super(TAG_SET, type);

        if (!hasDistinctTags(type)) {
            throw new RuntimeException("ASN1 set type: " + getClass().getName()
                    + " MUST have alternatives with distinct tags");
        }
    }

    //
    //
    // Decode
    //

    public void verify(BerInputStream in) throws IOException {
        if (tag != in.tag) {
            throw new ASN1Exception("ASN.1 Set is expected at [" + in.tagOffset
                    + "]. Expected tag: " + Integer.toHexString(tag)
                    + ", but encountered tag " + Integer.toHexString(in.tag));
        }
        in.readSet(this);
    }

    //
    //
    // Encode
    //
    //
    public final void encodeContent(BerOutputStream out) {
        out.encodeSet(this);
    }

    public final void setEncodingContent(BerOutputStream out) {
        out.getSetLength(this);
    }
}