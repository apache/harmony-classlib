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
 * Represents the ASN.1 element's Octect String (with the encrypted data as
 * content)
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 */
class ASNOctetString extends ASNAbstractElement {
    private byte[] content;
    
    /**
     * Constructor. Encodes <code>encrypted</code>.
     * 
     * @param encrypted
     *            the encrypted data is copied to protect against subsequent
     *            modification
     */
    public ASNOctetString(byte[] encrypted) {
        tag = Tag.OCTET_STRING;
        content = encrypted.clone();
    }

    /**
     * Constructor. Decodes the input stream <code>is</code>
     * 
     * @param is
     *            the input stream to consume
     * @throws IOException
     *             if cannot parse <code>is</code>
     */
    public ASNOctetString(InputStream is) throws IOException {
        super(is);
        if (tag != Tag.OCTET_STRING) {
            throw new IOException("Unexpected TAG");
        }
        // Read contents
        content = getContent(is);
    }
    
    @Override
    public final byte[] getEncoded() throws IOException {
        return makeASNEncoded(content);
    }
    
    /**
     * @return the content. Returns a new array each time this method is
     *         called
     */
    public final byte[] getContent() {
        return content.clone();
    }
    
}
