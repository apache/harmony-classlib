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
* @author Alexander Y. Kleymenov
* @version $Revision$
*/

package com.openintel.drl.security.provider.cert;

import java.math.BigInteger;
import java.util.Date;
import java.util.Set;
import java.security.cert.CRLException;
import java.security.cert.X509CRLEntry;
import javax.security.auth.x500.X500Principal;

import com.openintel.drl.security.x509.Extensions;
import com.openintel.drl.security.x509.TBSCertList;

/**
 * X509CRLEntryImpl
 */
public class X509CRLEntryImpl extends X509CRLEntry {

    private final TBSCertList.RevokedCertificate rcert;
    private final Extensions extensions;
    private final X500Principal issuer;

    private byte[] encoding;
    
    public X509CRLEntryImpl(TBSCertList.RevokedCertificate rcert, 
            X500Principal issuer) {
        this.rcert = rcert;
        this.extensions = rcert.getCrlEntryExtensions();
        this.issuer = issuer;
    }

    /**
     * getEncoded
     */
    public byte[] getEncoded() throws CRLException {
        if (encoding == null) {
            encoding = rcert.getEncoded();
        }
        byte[] result = new byte[encoding.length];
        System.arraycopy(encoding, 0, result, 0, encoding.length);
        return result;
    }

    /**
     * getSerialNumber
     */
    public BigInteger getSerialNumber() {
        return rcert.getUserCertificate();
    }

    public X500Principal getCertificateIssuer() {
        return issuer;
    }

    /**
     * getRevocationDate
     */
    public Date getRevocationDate() {
        return rcert.getRevocationDate();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean hasExtensions() {
        return (extensions != null) && (extensions.size() != 0);
    }

    /**
     * toString
     * FIXME: recognize and print the extensions 
     */
    public String toString() {
        // FIXME
        return "X509CRLEntryImpl:...";
    }

    // 
    // ----- java.security.cert.X509Extension methods implementations ----
    //

    public Set getNonCriticalExtensionOIDs() {
        if (extensions == null) {
            return null;
        }
        return extensions.getNonCriticalExtensions();
    }

    public Set getCriticalExtensionOIDs() {
        if (extensions == null) {
            return null;
        }
        return extensions.getCriticalExtensions();
    }

    public byte[] getExtensionValue(String oid) {
        if (extensions == null) {
            return null;
        }
        return extensions.getExtensionByOID(oid).getRawExtnValue();
    }

    public boolean hasUnsupportedCriticalExtension() {
        if (extensions == null) {
            return false;
        }
        return extensions.hasUnsupportedCritical();
    }


    /**
     * The main method.
     */
    public static void main(String[] args) {
    }
}

