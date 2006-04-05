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

package org.apache.harmony.security.asn1;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;


/**
 * This class represents ASN.1 UTCTime type
 * 
 * According to X.680 specification this type is defined as follows:
 *     UTCTime ::= [UNIVERSAL 23] IMPLICIT VisibleString
 * 
 * @see http://asn1.elibel.tm.fr/en/standards/index.htm
 */
public class ASN1UTCTime extends ASN1Time {

    // default implementation
    private static final ASN1UTCTime ASN1 = new ASN1UTCTime();

    /**
     * Constructs ASN.1 UTCTime type
     * 
     * The constructor is provided for inheritance purposes
     * when there is a need to create a custom ASN.1 UTCTime type.
     * To get a default implementation it is recommended to use
     * getInstance() method.
     */
    public ASN1UTCTime() {
        super(TAG_UTCTIME);
    }

    /**
     * Returns ASN.1 UTCTime type default implementation
     * 
     * The default implementation works with encoding
     * that is represented as Date object.
     *
     * @return ASN.1 UTCTime type default implementation
     */
    public static ASN1UTCTime getInstance() {
        return ASN1;
    }

    //
    //
    // Decode
    //
    //

    //
    //
    // Decode
    //
    //

    public void verify(BerInputStream in) throws IOException {
        if (!checkTag(in.tag)) {
            //FIXME message: what about constr tag?
            throw new ASN1Exception("ASN.1 GeneralizedTime is expected at ["
                    + in.tagOffset + "]. Expected tag: "
                    + Integer.toHexString(tag) + ", but encountered tag "
                    + Integer.toHexString(in.tag));
        }
        in.readUTCTime();
    }

    //
    //
    // Encode
    //
    //
    public void encodeContent(BerOutputStream out) {
        out.encodeUTCTime();
    }

    // FIXME support only one format for encoding, do we need others?
    //
    // According to X.680 coordinated universal time format:
    // two digit year, seconds always presented,
    // no fractional-secons elements, 'Z' at the end
    private final static String UTC_PATTERN = "yyMMddHHmmss'Z'";

    public void setEncodingContent(BerOutputStream out) {
        SimpleDateFormat sdf = new SimpleDateFormat(UTC_PATTERN);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        out.content = sdf.format(out.content).getBytes();
        out.length = ((byte[]) out.content).length;
    }
}