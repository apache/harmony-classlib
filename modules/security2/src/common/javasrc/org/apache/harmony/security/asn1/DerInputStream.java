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
* @author Stepan M. Mishura
* @version $Revision$
*/

package org.apache.harmony.security.asn1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;


/**
 * Decodes ASN.1 types encoded with DER (X.690)
 * 
 * @see http://asn1.elibel.tm.fr/en/standards/index.htm
 */

public final class DerInputStream extends BerInputStream {

    public DerInputStream(byte[] encoded) throws IOException {
        super(encoded);
    }

    public DerInputStream(InputStream in) throws IOException {
        super(in);
    }

    /**
     * @see org.apache.harmony.security.asn1.BerInputStream#next()
     */
    protected final int next() throws IOException {

        int tag = super.next();

        if (length == INDEFINIT_LENGTH) {
            throw new ASN1Exception(
                    "DER: only definite length encoding MUST be used");
        }

        // FIXME add check: length encoding uses minimum number of octets

        return tag;
    }

    /**
     * @see org.apache.harmony.security.asn1.BerInputStream#readBoolean()
     */
    public void readBoolean() throws IOException {

        super.readBoolean();

        // check encoded content
        if (buffer[contentOffset] != 0 && buffer[contentOffset] != (byte) 0xFF) {
            throw new ASN1Exception(
                    "DER: ASN.1 boolean type wrong content at ["
                            + contentOffset + "]");
        }
    }

    /**
     * @see org.apache.harmony.security.asn1.BerInputStream#readBitString()
     */
    public void readBitString() throws IOException {

        if ((tag & ASN1Constants.PC_CONSTRUCTED) != 0) {
            throw new ASN1Exception(
                    "DER: ASN.1 Bitstring type MUST have primitive encoding");
        }

        super.readBitString();

        //check: unused bits values: MUST be 0
        if (length > 1 && buffer[contentOffset] != 0) {

            byte finalOctet = buffer[offset - 1];
            for (int i = 0; i < buffer[contentOffset]; i++) {
                if ((finalOctet & 0x01) != 0) {
                    throw new ASN1Exception(
                            "DER: ASN.1 Bitstring wrong content at ["
                                    + contentOffset
                                    + "]. Unused bits in final octet MUST be zero");
                }
                finalOctet = (byte) (finalOctet >> 1);
            }
        }
    }

    /**
     * @see org.apache.harmony.security.asn1.BerInputStream#readSequence(org.apache.harmony.security.asn1.ASN1Sequence)
     */
    public void readSequence(ASN1Sequence sequence) throws IOException {
        //
        // According to ASN.1 DER spec. sequence MUST not include
        // any encoding which value is equal to its default value
        //
        // Verification of this assertion is not implemented
        //
        super.readSequence(sequence);
    }

    /**
     * @see org.apache.harmony.security.asn1.BerInputStream#readSetOf(org.apache.harmony.security.asn1.ASN1SetOf)
     */
    public void readSetOf(ASN1SetOf setOf) throws IOException {
        //
        // According to ASN.1 DER spec. set of MUST appear in
        // ascending order (short component are padded for comparision)
        //
        // Verification of this assertion is not implemented
        //
        super.readSetOf(setOf);
    }

    /**
     * @see org.apache.harmony.security.asn1.BerInputStream#readString()
     */
    public void readString() throws IOException {

        if ((tag & ASN1Constants.PC_CONSTRUCTED) != 0) {
            throw new ASN1Exception(
                    "DER: ASN.1 string type MUST have primitive encoding");
        }
        super.readString();
    }

    /**
     * @see org.apache.harmony.security.asn1.BerInputStream#readUTCTime()
     */
    public void readUTCTime() throws IOException {

        if ((tag & ASN1Constants.PC_CONSTRUCTED) != 0) {
            // It is a string type and it can be encoded as primitive or constructed.
            throw new ASN1Exception(
                    "DER: ASN.1 UTCTime type MUST have primitive encoding");
        }

        //FIXME it should check format and invoke BerInputStream
        //FIXME: any other optimizations?
        readContent();
        String timeStr = new String(buffer, contentOffset, length);

        // FIXME store string somewhare to allow a custom time type perform additional checks

        // check syntax: MUST be YYMMDDHHMMSS'Z'
        if (timeStr.length() != 13 || // invalid length 
                timeStr.charAt(12) != 'Z') // the last char MUST be Z 
        {
            throw new ASN1Exception("ASN.1 UTCTime wrongly encoded at ["
                    + contentOffset + "] ");
        }

        if (times == null) {
            times = new int[7];
        }

        times[0] = ASN1Time.getTimeValue(timeStr, 0, 2) + 1900; //year
        if (Calendar.getInstance().get(Calendar.YEAR) - times[0] > 80) {
            times[0] += 100;
        }

        times[1] = ASN1Time.getTimeValue(timeStr, 2, 2); //month
        times[2] = ASN1Time.getTimeValue(timeStr, 4, 2); //day
        times[3] = ASN1Time.getTimeValue(timeStr, 6, 2); //hour
        times[4] = ASN1Time.getTimeValue(timeStr, 8, 2); //minute
        times[5] = ASN1Time.getTimeValue(timeStr, 10, 2); //second

        //FIXME check all time values for valid numbers!!!
    }

    /**
     * @see org.apache.harmony.security.asn1.BerInputStream#readGeneralizedTime()
     */
    public void readGeneralizedTime() throws IOException {

        if ((tag & ASN1Constants.PC_CONSTRUCTED) != 0) {
            // It is a string type and it can be encoded as primitive or constructed.
            throw new ASN1Exception(
                    "DER: ASN.1 GeneralizedTime type MUST have primitive encoding");
        }

        //FIXME it should check format and invoke BerInputStream
        //FIXME: any other optimizations?
        readContent();
        String timeStr = new String(buffer, contentOffset, length);

        //FIXME store string somewhere to allow a custom time type perform additional checks

        int len = timeStr.length();
        // check syntax: MUST be YYYYMMDDHHMMSS[(./,)DDD]'Z'
        if (timeStr.charAt(len - 1) != 'Z' // the last char MUST be Z
                || len < 15 || len == 16 || len > 19) // invalid length
        {
            throw new IOException("ASN.1 GeneralizedTime wrongly encoded at ["
                    + contentOffset + "].");
        }

        // check content: milliseconds
        if (len > 16) {
            char char14 = timeStr.charAt(14);
            if (char14 != '.' && char14 != ',') {
                throw new IOException(
                        "ASN.1 GeneralizedTime wrongly encoded at ["
                                + contentOffset + "].");
            }
        }

        if (times == null) {
            times = new int[7];
        }
        times[0] = ASN1Time.getTimeValue(timeStr, 0, 4); //year
        times[1] = ASN1Time.getTimeValue(timeStr, 4, 2); //month
        times[2] = ASN1Time.getTimeValue(timeStr, 6, 2); //day
        times[3] = ASN1Time.getTimeValue(timeStr, 8, 2); //hour
        times[4] = ASN1Time.getTimeValue(timeStr, 10, 2); //minute
        times[5] = ASN1Time.getTimeValue(timeStr, 12, 2); //second

        //FIXME check all values for valid numbers!!!

        if (len > 16) {
            //FIXME optimize me
            times[6] = ASN1Time.getTimeValue(timeStr, 15, len - 16); //millisecond

            // the fractional-seconds elements, if present MUST
            // omit all trailing zeros
            if (times[6] % 10 == 0) {
                throw new IOException(
                        "DER ASN.1 GeneralizedTime wrongly encoded at ["
                                + contentOffset
                                + "]. Trailing zeros MUST be omitted");
            }

            if (len == 17) {
                times[6] = times[6] * 100;
            } else if (len == 18) {
                times[6] = times[6] * 10;
            }
        }
    }
}