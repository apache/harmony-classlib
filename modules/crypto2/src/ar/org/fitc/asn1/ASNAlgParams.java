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
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


/**
 * Represents the ASN.1 element's ANY defined by the Object Identifier
 * <code>oid</code>
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 */
class ASNAlgParams extends ASNAbstractElement {
    private AlgorithmParameters ap;

    /**
     * Constructor. Encodes <code>ap</code>.  
     * <br>
     * If <code>ap</code> is null then <code>encoded</code> will 
     * have the <code>Tag.NULL.getByteArrayValue()</code> value. Otherwise, 
     * <code>encoded</code> will have <code>ap.getEncoded()<code> value  
     * 
     * @param ap the algorithm's parameters
     */
    public ASNAlgParams(AlgorithmParameters ap) {
        this.ap = ap;
    }

    /**
     * Constructor. Decodes the input stream <code>is</code> 
     * <br>
     * Consumes <code>len</code> bytes of the <code>is</code>. Sets 
     * <code>tag</code>, <code>ap</code> and <code>contentLength</code>
     * 
     * @param is
     *            the input stream to consume
     * @param algName
     *            the name of the algorithm's parameter
     * @param len
     *            the numbers of bytes to read
     * @throws IOException
     *             if cannot parse <code>is</code>
     */
    protected ASNAlgParams(InputStream is, String algName, int len)
            throws IOException {
        super();

        // content == encoded
        byte[] content = new byte[len];
        is.read(content);

        if (len == 2 && Arrays.equals(Tag.NULL.getByteArrayValue(), content)) {
            // set Tag
            tag = Tag.NULL;
        } else {
            // set algorithm's parameters
            try {
                ap = AlgorithmParameters.getInstance(algName);
                getAlgorithmParameters().init(content);
            } catch (NoSuchAlgorithmException e) {
                throw new IOException("Cannot parse algorithm parameters: "
                        + e.getMessage());
            }
        }
        // set contentLength
        contentLength = content.length;
    }

    @Override
    public final byte[] getEncoded() throws IOException {
        if (ap == null) {
            return Tag.NULL.getByteArrayValue();
        } else {
            return ap.getEncoded();
        }    
    }
    
    public AlgorithmParameters getAlgorithmParameters() {
        return ap;
    }
}
