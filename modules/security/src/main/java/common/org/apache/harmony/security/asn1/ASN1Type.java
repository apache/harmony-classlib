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
import java.io.InputStream;


/**
 * This abstract class is the super class for all ASN.1 types
 * 
 * @see http://asn1.elibel.tm.fr/en/standards/index.htm
 */

public abstract class ASN1Type implements ASN1Constants {

    public final int tagClass;

    public final boolean isConstructed;

    public final int tagNumber;

    /**
     * int representation of encoded ASN.1 tag (class + P/C + number)
     */
    public final int tag;

    /**
     * Constructs a primitive, universal ASN.1 type.
     * 
     * @param tagNumber - ASN.1 tag number
     * @throws IOException TODO
     */
    public ASN1Type(int tagNumber) {
        this(CLASS_UNIVERSAL, false, tagNumber);
    }

    /**
     * Constructs an ASN.1 type.
     * 
     * @param tagClass - tag class. MUST be
     *     CLASS_UNIVERSAL, CLASS_APPLICATION, CLASS_CONTEXTSPECIFIC, CLASS_PRIVATE
     * @param isConstructed - is ASN.1 type is a constructed type.
     * @param tagNumber - ASN.1 tag number.
     * @throws IOException TODO
     */
    public ASN1Type(int tagClass, boolean isConstructed, int tagNumber) {

        if (tagNumber < 0) {
            throw new RuntimeException("Negative tag number");
        }

        if (tagClass != CLASS_UNIVERSAL && tagClass != CLASS_APPLICATION
                && tagClass != CLASS_CONTEXTSPECIFIC
                && tagClass != CLASS_PRIVATE) {
            throw new RuntimeException("Wrong tag class");
        }
        this.tagClass = tagClass;
        this.isConstructed = isConstructed;
        this.tagNumber = tagNumber;

        if (tagNumber < 31) {
            // short form
            if (isConstructed) {
                tag = tagClass | PC_CONSTRUCTED | tagNumber;
            } else {
                tag = tagClass | tagNumber;
            }
        } else {
            // long form
            throw new RuntimeException("Tag long form is not implemented");
        }
    }

    /**
     * Tests whether ASN.1 type alternatives in
     * provided array have distinct tags ot not
     *
     * @param type - an array of ASN.1 types
     * @return - true if alternatives have distinct tags, otherwise false
     */
    public static boolean hasDistinctTags(ASN1Type[] type) {

        for (int i = 0; i < type.length; i++) {

            if ((type[i] instanceof ASN1Choice) || (type[i] instanceof ASN1Any)) {
                return false;
            }

            int curTag = type[i].tag;
            for (int j = i + 1; j < type.length; j++) {
                if (type[j].checkTag(curTag)) {
                    return false;
                }
            }
        }

        if (type[0] instanceof ASN1StringType) {
            int curTag = type[0].tag | PC_CONSTRUCTED;
            for (int j = 1; j < type.length; j++) {
                if (type[j].checkTag(curTag)) {
                    return false;
                }
            }
        }

        return true;
    }

    //
    //
    // Stubs for DER
    //
    //

    public final Object decode(byte[] encoded) throws IOException {
        return decode(new DerInputStream(encoded));
    }

    public final Object decode(InputStream in) throws IOException {
        return decode(new DerInputStream(in));
    }

    public final void verify(byte[] encoded) throws IOException {
        DerInputStream decoder = new DerInputStream(encoded);
        decoder.setVerify();
        verify(decoder);
    }

    public final void verify(InputStream in) throws IOException {
        DerInputStream decoder = new DerInputStream(in);
        decoder.setVerify();
        verify(decoder);
    }

    public final Object getValues(byte[] encoded) throws IOException {

        DerInputStream decoder = new DerInputStream(encoded);

        if (!checkTag(decoder.tag)) {
            throw new ASN1Exception("Mandatory value is missing at ["
                    + decoder.tagOffset + "]. Expected " + this
                    + " but encountered tag " + Integer.toHexString(tag));
        }
        verify(decoder);

        return decoder.content;
    }

    public final Object getValues(InputStream in) throws IOException {

        DerInputStream decoder = new DerInputStream(in);

        if (!checkTag(decoder.tag)) {
            throw new ASN1Exception("Mandatory value is missing at ["
                    + decoder.tagOffset + "]. Expected " + this
                    + " but encountered tag " + Integer.toHexString(tag));
        }
        verify(decoder);

        return decoder.content;
    }

    public final byte[] encode(Object object) {

        DerOutputStream out = new DerOutputStream(this, object);
        return out.encoded;
    }

    //
    //
    // Decode
    //
    //

    /**
     * Decodes ASN.1 type.
     *
     * @param in - BER input stream
     * @throws IOException - if an I/O error occurs or the end of the stream is reached
     */
    public Object decode(BerInputStream in) throws IOException {
        verify(in);
        return getDecodedObject(in);
    }

    /**
     * Verified ASN.1 type.
     *
     * @param in - BER input stream
     * @throws IOException - if an I/O error occurs or the end of the stream is reached
     */
    public void verify(BerInputStream in) throws IOException {
        if (!checkTag(in.tag)) {
            throw new ASN1Exception("Mandatory value is missing at ["
                    + in.tagOffset + "]. Expected " + this
                    + " but encountered tag " + Integer.toHexString(tag));
        }
        in.readContent();
    }

    /**
     * Tests whether provided tag is equal to ASN.1 type tag.
     *
     * @param tag - ASN.1 tag to be verified
     * @return - true if tags are equals, otherwise false
     */
    public boolean checkTag(int tag) {
        return this.tag == tag;
    }

    //FIXME make me public
    protected abstract Object getDecodedObject(BerInputStream in)
            throws IOException;

    //
    //
    // Encode
    //
    //

    public void encodeASN(BerOutputStream out) {

        out.encodeTag(tag);
        encodeContent(out);
    }

    public abstract void encodeContent(BerOutputStream out);

    public abstract void setEncodingContent(BerOutputStream out);

    public int getEncodedLength(BerOutputStream out) { //FIXME name

        //tag length
        int len = 1; //FIXME tag length = 1. what about long form?
        //for (; tag > 0; tag = tag >> 8, len++);

        // length length :-)
        len++;
        if (out.length > 127) {

            len++;
            for (int cur = out.length >> 8; cur > 0; len++) {
                cur = cur >> 8;
            }
        }
        len += out.length;

        return len;
    }

    public String toString() {
        // TODO decide whether this method is necessary
        //FIXME fix performance
        return this.getClass().getName() + "(tag: 0x"
                + Integer.toHexString(0xff & this.tag) + ")";
    }
}