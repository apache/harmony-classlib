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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Represents the ASN.1 element's ObjectIdentifier
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 */
class ASNObjectIdentifier extends ASNAbstractElement {

    private byte[] content;

    /** Top level ITU-T's OID assignments */
    private static final int ITU_T = 0;

    /** Top level ISO's OID assignments */
    private static final int ISO = 1;

    /** Top level Joint ISO/ITU-T OID's assignment */
    private static final int ITU_T_AND_ISO = 2;

    /*
     * CONVERSION'S METHODS
     */

    /**
     * Parse <code>intArray</code> an oid's integers array representation into
     * the ASN.1 representation as a byte array. For example,
     * [1,2,840,113549,1,1,1] into [42,-122,72,-122,-9,13,1,1,1]
     * 
     * @param intArray
     *            the oid's integers array representation
     * @return the ASN.1 representation
     */
    private final static byte[] toByteArray(int[] intArray) {
        int i = 0;
        Integer n1 = Integer.valueOf(intArray[i++]);
        Integer n2 = Integer.valueOf(intArray[i++]);
        n1 = Integer.valueOf(n1.intValue() * 40 + n2.intValue());

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        os.write(n1.byteValue());

        int v;
        int remainder;
        for (; i < intArray.length; i++) { // i == 2 at the first iteration
            n1 = Integer.valueOf(intArray[i]);
            // in the case that OID contains any cero
            if (intArray[i] == 0) {
                os.write(n1);
            } else {
                while (n1 > 0) {
                    v = n1;
                    remainder = 1;
                    while (v >= 128) {
                        remainder *= 128;
                        v /= 128;
                    }
                    // is the final value of the octect "i" ?
                    n1 %= remainder;
                    // if == 0 set to "1" the most significant value
                    n2 = n1 != 0 ? v | 0x80 : v;
                    os.write(n2.byteValue());
                }
            }
        }
        return os.toByteArray();
    }

    /**
     * Consumes an <code>is</code> and returns an oid's integers array
     * notation
     * 
     * @param is
     *            the input stream to consume
     * @return the oid's integers array representation
     * @throws IOException
     *             if cannot parse the given <code>is</code> because
     *             it's malformed
     */
    private static final int[] toIntArray(InputStream is) throws IOException {
        int v = 0;
        int i = 0;

        // determine the array size
        is.mark(Integer.MAX_VALUE);
        while (is.available() != 0) {
            v += is.read() >= 0 ? 1 : 0;
        }
        is.reset();
        int[] intArray = new int[v + 1];

        byte b = (byte) is.read();
        if (b < 40) {
            intArray[i++] = ITU_T;
            intArray[i++] = b;
        } else if (b < 80) {
            intArray[i++] = ISO;
            intArray[i++] = b - 40;
        } else if (b < 119) {
            intArray[i++] = ITU_T_AND_ISO;
            intArray[i++] = b - 80;
        } else {
            throw new IOException("Invalid ObjectIdentifier");
        }

        while (is.available() != 0) {
            b = (byte) is.read();
            if (b >= 0) { // 1 byte
                intArray[i++] = b & 0xff;
            } else { // more than 1 byte
                v = 0;
                do {
                    v |= b & 0x7f;
                    v <<= 7;
                    b = (byte) is.read();
                    // the last one cannot be negative.
                    if (is.available() == 0 && b < 0) {
                        throw new IOException("Unrecognized encoded format");
                    }
                } while (b < 0); // while it's not the last one

                v |= b & 0xff; // the last one it's positive
                intArray[i++] = v;
            }
        }
        return intArray;
    }

    /**
     * Parse an oid's string notation into an oid's integers array notation. For
     * example, "1.2.840.113549.1.1.1" into [1,2,840,113549,1,1,1]
     * 
     * @param string
     *            the oid's string notation
     * @return the oid's integers array notation
     */
    private final static int[] toIntArray(String string) {
        int[] intArray;
        String str[] = string.split("\\.");
        if (str.length == 1) {
            throw new NumberFormatException("Invalid ObjectIdentifier");
        } else {
            intArray = new int[str.length];
            for (int i = 0; i < str.length; i++) {
                try {
                    if (str[i].equals("")) {
                        throw new NumberFormatException("Invalid "
                                + "ObjectIdentifier");
                    }
                    intArray[i] = Integer.parseInt(str[i]);
                    if (intArray[i] < 0) {
                        throw new NumberFormatException("Invalid "
                                + "ObjectIdentifier");
                    }
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Invalid "
                            + "ObjectIdentifier");
                }
            }
            if (intArray[0] < 0 || intArray[0] > 2 || intArray[1] < 0
                    || intArray[1] > 39) {
                throw new NumberFormatException("Invalid " + "ObjectIdentifier");
            }
        }
        return intArray;
    }

    /**
     * Returns the oid's string notation from <code>is</code>
     * 
     * @param is
     *            the input stream to consume
     * @return the oid's string notation
     */
    private final static String toString(InputStream is) {
        try {
            return toString(toIntArray(is));
        } catch (IOException e) {
            // this shouldn't get raised because it was previously checked at
            // either of the constructors
            throw new AssertionError(e);
        }
    }

    /**
     * Returns the oid's string notation from <code>intArray</code> oid's
     * integers array notation
     * 
     * @param intArray
     *            the oid's integers array notation
     * @return the oid's string notation
     */
    private final static String toString(int[] intArray) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < intArray.length; i++) {
            sb.append(String.valueOf(intArray[i])
                    + (i != intArray.length - 1 ? "." : ""));
        }
        return sb.toString();
    }

    /**
     * Constructor. Decodes the input stream <code>is</code>
     * 
     * @param is
     *            the input stream to consume
     * @throws IOException
     *             if cannot parse <code>is</code>
     */
    protected ASNObjectIdentifier(InputStream is) throws IOException {
        super(is);
        if (tag != Tag.OID) {
            throw new IOException("Unexpected TAG");
        }

        // set content
        content = getContent(is);

        // to check content
        toIntArray(new ByteArrayInputStream(content));
    }

    /**
     * Constructor. Encodes <code>algName</code>.
     * 
     * @param name
     *            could be algorithm name or its OID representation
     * @throws NoSuchAlgorithmException
     *             if cannot get the OID from <code>algName</code>
     */
    public ASNObjectIdentifier(String name) {
        // set tag
        tag = Tag.OID;

        // set algOIDArray
        if (name.endsWith(".")) {
            throw new NumberFormatException("Invalid ObjectIdentifier");
        }

        // set algOIDString
        if (name.startsWith("OID.")) {
            content = toByteArray(toIntArray(name.substring(4)));
        } else {
            content = toByteArray(toIntArray(name));
        }
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof ASNObjectIdentifier) {
            ASNObjectIdentifier asnObj = (ASNObjectIdentifier) obj;
            return Arrays.equals(asnObj.content, content);
        } 
        return false;
    }

    @Override
    public final byte[] getEncoded() throws IOException {
        return makeASNEncoded(content);
    }

    @Override
    public final int hashCode() {
        return Arrays.hashCode(content);
    }

    /**
     * @return the algorithm's oid in dot separated notation (for example,
     *         "1.2.840.113549.1.1.1") always without the starting substring
     *         "OID."
     */
    @Override
    public final String toString() {
        return toString(new ByteArrayInputStream(content));
    }
}
