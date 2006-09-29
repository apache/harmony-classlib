/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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
* @author Alexander V. Esin
* @version $Revision$
*/
package org.ietf.jgss;

import java.io.IOException;
import java.io.InputStream;

import org.apache.harmony.auth.internal.nls.Messages;
import org.apache.harmony.security.asn1.ASN1Oid;
import org.apache.harmony.security.asn1.ObjectIdentifier;


/**
 * @com.intel.drl.spec_ref
 */
public class Oid {
    
    // ASN.1 decoder/encoder
    private static final ASN1Oid ASN1 = ASN1Oid.getInstance();
    
    //inner representation of Object Identifier
    private final ObjectIdentifier oid;

    //ASN.1 DER encoding
    private byte[] encoding;

    //ctor for internal usage
    Oid(int[] data) {
        oid = new ObjectIdentifier(data);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Oid(byte[] data) throws GSSException {
        try {
            oid = new ObjectIdentifier((int[])ASN1.decode(data));
        } catch (IOException e) {
            GSSException gsse = new GSSException(GSSException.FAILURE);
            gsse.initCause(e);
            throw gsse;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Oid(InputStream derOid) throws GSSException {
        if (derOid == null) {
            throw new NullPointerException();
        }

        try {
            oid = new ObjectIdentifier((int[]) ASN1.decode(derOid));
        } catch (IOException e) {
            GSSException gsse = new GSSException(GSSException.FAILURE);
            gsse.initCause(e);
            throw gsse;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Oid(String strOid) throws GSSException {
        try {
            oid = new ObjectIdentifier(strOid);
        } catch (IllegalArgumentException e) {
            GSSException gsse = new GSSException(GSSException.FAILURE);
            gsse.initCause(e);
            throw gsse;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof Oid)) {
            return false;
        }
        return oid.equals(((Oid) other).oid);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean containedIn(Oid[] oids) {
        if (oids == null) {
            throw new NullPointerException(Messages.getString("auth.0D")); //$NON-NLS-1$
        }
        for (int i = 0; i < oids.length; ++i) {
            if (oid.equals(oids[i].oid)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public byte[] getDER() throws GSSException {
        if (encoding == null) {
            encoding = ASN1.encode(oid.getOid());
        }
        byte[] enc = new byte[encoding.length];
        System.arraycopy(encoding, 0, enc, 0, enc.length);
        return enc;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String toString() {
        return oid.toString();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int hashCode() {
        return oid.hashCode();
    }
}