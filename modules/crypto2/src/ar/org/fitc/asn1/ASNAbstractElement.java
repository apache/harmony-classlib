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

import java.io.IOException;
import java.io.InputStream;

/**
 * Represents an ASN element and encapsulate the major functionality of this 
 * ASN.1 parser. The rest of the ASN elements specializes this class.
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 */
abstract class ASNAbstractElement {

    /** Indicates the content's length (in decimal format) */
    protected int contentLength;

    /** Indicates the content's length (in ASN.1 - DER format) */
    protected byte[] contentLengthByteArray; // contentLength -> byte[]

    /** 
     * Is equals <code>tag</code>'s length + <code>contentLength</code> + 
     * <code>content</code>'s length 
     */
    protected int length; // tag + contentLength + content

    protected Tag tag;

    /**
     * Concatenates a indefinite number of arrays into a new array that is
     * returned
     * 
     * @param args
     *            a indefinite number of arrays to concatenate
     * @return a new array that concatenates <code>args</code>
     */
    protected static final byte[] concat(byte[]... args) {
        int size = 0;
        for (byte[] array : args) {
            size += array.length;
        }
        byte[] b = new byte[size];
        int index = 0;
        for (int i = 0; i < args.length; i++) {
            System.arraycopy(args[i], 0, b, index, args[i].length);
            index += args[i].length;
        }
        return b;
    }

    /**
     * Constructor. Called by ASNAlgParams. Do nothing (delegate the setters)
     */
    protected ASNAbstractElement() {
    }

    /**
     * Constructor. Decodes the input stream <code>is</code> <br>
     * Reads tag, reads contentLength (consume InputStream) and creates
     * <code>encoded</code> with the elements that has read
     * 
     * @param is
     *            the inputStream to consume
     */
    protected ASNAbstractElement(InputStream is) throws IOException {
        setTag(is);
        setContentLength(is);
        setLength();
    }

    /**
     * Gets a new array from <code>is</code> with <code>contentLength</code>
     * size and verifies that we haven't reached at the final of this stream
     * 
     * @throws IOException
     *             if <code>content.length</code> is not equals
     *             <code>encoded.length</code>
     */
    protected final byte[] getContent(InputStream is) throws IOException {
        byte[] content = new byte[contentLength];
        // in the case that we have reached at the final of the stream...
        if (is.read(content) < contentLength) {
            throw new IOException("Invalid size");
        }
        return content;
    }

    /**
     * @return the encoded format of this object 
     */
    protected abstract byte[] getEncoded() throws IOException;

    /**
     * Get the content's length according DER's encoder specification
     * 
     * @param content
     *            content to calculate
     * @return content's length according DER's encoder specification
     */
    private static final byte[] getLength(byte[] content) {
        // short: from 0 to 127 | only one octect
        if (content.length <= 127) {
            return new byte[] { (byte) content.length };
        // long: from 128 to (2 ^ 1008) - 1 | from 2 to 127 octects (octectsNo)
        } else {
            int len = content.length;
            byte[] des = new byte[5]; // octects' number + integer Size < 5 
            
            // we start from the end of the array
            int i;
            for (i = des.length - 1; len > 0; i--) {
                des[i] = (byte) (len & 0xff);
                len >>= 8;
            }
            
            // set octects' number 
            des[i] = (byte) ((des.length - i - 1) | 0x80);
            
            if (i == 0) { // content.length > 8388607 => des has 4 bytes
                return des; 
            } else {      // => des has < 4 bytes
                byte[] ret = new byte[des.length - i];
                System.arraycopy(des, i, ret, 0, des.length - i);
                return ret;
            }
        }
    }

    /**
     * Sets <code>concentLength</code>, <code>length</code> (partially) and
     * <code>byteArrayLength</code>
     * 
     * @param is
     *            the input stream to consume
     * @throws IOException
     *             if the TAG is not supported
     */
    private final void setContentLength(InputStream is) throws IOException {
        if (tag == Tag.NULL) {
            contentLengthByteArray = new byte[] {};
            contentLength = 0;
        } else {
            byte b = (byte) is.read();
            if (b < 0) { // >127
                int octectsNo = b & 0x7f;
                int len = 0;
                contentLengthByteArray = new byte[octectsNo + 1];
                contentLengthByteArray[0] = b;
                for (int i = 1; octectsNo >= i; i++) {
                    b = (byte) is.read();
                    len = (len << 8) | b & 0xff;
                    contentLengthByteArray[i] = b;
                }
                contentLength = len;
                if (contentLength < 1) {
                    throw new IOException("Invalid size");
                }
            } else { // <= 127
                contentLength = b;
                contentLengthByteArray = new byte[++length];
                contentLengthByteArray[0] = b;
            }
        }
    }

    /**
     * Sets <code>length</code> value
     */
    private final void setLength() {
        length = tag.getByteArrayValue().length + contentLengthByteArray.length
                + contentLength;
    }

    /**
     * Given an input stream (<code>is</code>) sets the Tag type 
     * (<code>tag</code>). Notice that it's not called by algParams. 
     * Otherwise it may thrown an <code>IOException</code>
     * 
     * @param is
     *            the input stream to consume
     * @throws IOException
     *             if cannot parse the byte value to a recognized Tag type
     */
    private final void setTag(InputStream is) throws IOException {
        int t = is.read();
        if (t == Tag.SEQUENCE.getIntValue()) {
            tag = Tag.SEQUENCE;
        } else if (t == Tag.OID.getIntValue()) {
            tag = Tag.OID;
        } else if (t == Tag.OCTET_STRING.getIntValue()) {
            tag = Tag.OCTET_STRING;
        } else {
            throw new IOException("Tag invalid or not supported ");
        }
    }
    
    /**
     * Concatenates into a new array <code>tag</code>, content's length and
     * <code>content</code>
     * 
     * @param content
     *            the content to concatenate
     * @return a new array that contains <code>tag</code>, content's length
     *         and <code>content</code>
     */
    protected final byte[] makeASNEncoded(byte[] content) {
        return concat(tag.getByteArrayValue(), getLength(content), content);
    }

    /**
     * Verifies that <code>content.length</code> is equals
     * <code>encoded.length</code>
     * 
     * @throws IOException
     *             if <code>content.length</code> is not equals
     *             <code>encoded.length</code>
     */
    protected final void verifyContentLength(byte[] content) throws IOException {
        // Verify content length
        if (content.length != contentLength) {
            throw new IOException("Invalid size");
        }
    }
}