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
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */

package ar.org.fitc.test.integration.crypto;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import ar.org.fitc.test.integration.crypto.agreement.DHKeyAgreement;

/**
 * Extends DHKeyAgreement to send and recieve 
 *
 */
public abstract class X509EncodedKeyHDKeyAgreement extends DHKeyAgreement {

    public X509EncodedKeyHDKeyAgreement() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * implements sendKey to send x509 EncodedKeySpec.
     * 
     * @see X509EncodedKeyHDKeyAgreement#sendX509EncodedKeySpec(X509EncodedKeySpec)
     */
       
    @Override
    public void sendKey(PublicKey key) throws Exception {
        sendX509EncodedKeySpec(new X509EncodedKeySpec(key.getEncoded()));

    }
    
    /**
     * 
     * Move the send operation to this function
     * 
     * @param x509KeySpec key encoded to send
     * @throws Exception free implement without catch.
     */
    
    abstract public  void sendX509EncodedKeySpec(X509EncodedKeySpec x509KeySpec) throws Exception;
    
    /**
     *  recieve a x509 encoded key and return a public key.
     *  
     *  @see X509EncodedKeyHDKeyAgreement#recieveX509EncodedKeySpec()
     */
    @Override
    public PublicKey recieveKey() throws Exception {
        KeyFactory keyFac = KeyFactory.getInstance("DH");
        return keyFac.generatePublic(recieveX509EncodedKeySpec());
    }
    
    /**
     *  Move the recieve operation to this function
     *  
     * @return a public key endoded in a X509.
     * @throws Exception free implement without catch.
     */
    abstract public  X509EncodedKeySpec recieveX509EncodedKeySpec() throws Exception;
}
