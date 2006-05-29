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

package org.apache.harmony.security.asn1;

import java.io.IOException;


/**
 * Implicitly tagged ASN.1 type.
 * 
 * @see http://asn1.elibel.tm.fr/en/standards/index.htm
 */
public class ASN1Implicit extends ASN1Type {

    private final ASN1Type type;

    private final int strTag;

    /**
     * Constructs implicitly tagged ASN.1 type
     * with context-specific tag class and specified tag number. 
     * 
     * @param tagNumber - ASN.1 tag number
     * @param type - ASN.1 type to be tagged
     * @throws IllegalArgumentException - if tagNumber or type is invalid
     */
    public ASN1Implicit(int tagNumber, ASN1Type type) {
        this(CLASS_CONTEXTSPECIFIC, tagNumber, type);
    }

    /**
     * Constructs implicitly tagged ASN.1 type
     * 
     * @param tagClass - ASN.1 tag class.
     * @param tagNumber - ASN.1 tag number
     * @param type - ASN.1 type to be tagged
     * @throws IllegalArgumentException - if tagNumber, tagClass or type is invalid
     */
    public ASN1Implicit(int tagClass, int tagNumber, ASN1Type type) {
        super(tagClass, (type.tag & PC_CONSTRUCTED) > 0, tagNumber);

        if ((type instanceof ASN1Choice) || (type instanceof ASN1Any)) {
            // According to X.680:
            // 'The IMPLICIT alternative shall not be used if the type
            // defined by "Type" is an untagged choice type or an 
            // untagged open type'
            throw new IllegalArgumentException(
                    "Implicit tagging can not be used for ASN.1 ANY or CHOICE type");
        }

        strTag = (type instanceof ASN1StringType) ? tagClass | PC_CONSTRUCTED
                | tagNumber : -1;

        this.type = type;
    }

    //
    //
    // Decode
    //
    //

    public boolean checkTag(int tag) {
        if (strTag > 0) {
            return super.checkTag(tag) || tag == strTag;
        }
        return super.checkTag(tag);
    }

    public Object decode(BerInputStream in) throws IOException {
        if (!checkTag(in.tag)) {
            throw new ASN1Exception(
                    "ASN.1 implicitly tagged type is expected at ["
                            + in.tagOffset + "]. Expected tag: "
                            + Integer.toHexString(tag)
                            + ", but encountered tag "
                            + Integer.toHexString(in.tag));
        }

        if (strTag > 0 && (in.tag & ASN1Constants.PC_CONSTRUCTED) != 0) {
            in.tag = strTag;
        } else {
            in.tag = type.tag;
        }

        in.content = type.decode(in);

        if (in.isVerify) {
            return null;
        }
        return getDecodedObject(in);
    }

    /**
     * TODO
     */
    public Object getDecodedObject(BerInputStream in) throws IOException {
        return in.content;
    }

    //
    //
    // Encode
    //
    //

    public void encodeContent(BerOutputStream out) {
        type.encodeContent(out);
    }

    public void setEncodingContent(BerOutputStream out) {
        type.setEncodingContent(out);
    }
}