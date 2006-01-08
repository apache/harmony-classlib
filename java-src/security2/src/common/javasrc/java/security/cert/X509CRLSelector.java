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

package java.security.cert;

import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.CRLSelector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import javax.security.auth.x500.X500Principal;
import com.openintel.drl.security.asn1.ASN1Integer;
import com.openintel.drl.security.asn1.ASN1OctetString;

/**
 * @com.intel.drl.spec_ref
 */
public class X509CRLSelector implements CRLSelector {

    // issuerNames criterion
    private ArrayList issuerNames;
    // minCRLNumber criterion
    private BigInteger minCRL;
    // maxCRLNumber criterion
    private BigInteger maxCRL;
    // dateAndTime criterion
    private long dateAndTime = -1;
    // the certificate being checked
    private X509Certificate certificateChecking;
    
    /**
     * @com.intel.drl.spec_ref
     */
    public X509CRLSelector() {}

    /**
     * @com.intel.drl.spec_ref
     */
    public void setIssuers(Collection/*FIXME<X500Principal>*/ issuers) {
        if (issuers == null) {
            issuerNames = null;
            return;
        }
        issuerNames = new ArrayList(issuers); 
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public void setIssuerNames(Collection/*FIXME<?>*/names) throws IOException {
        if (names == null) {
            issuerNames = null;
            return;
        }
        Iterator it = names.iterator();
        issuerNames = new ArrayList(names.size());
        while (it.hasNext()) {
            Object princ = it.next();
            if (princ instanceof String) {
                //addIssuer(new X500Principal((String) princ));
                issuerNames.add(new X500Principal((String) princ));
            } else if (princ instanceof byte[]) {
                //addIssuer(new X500Principal((byte[]) princ));
                issuerNames.add(new X500Principal((byte[]) princ));
            } else {
                throw new IOException(
                        "The name is not a String or byte array");
            }
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void addIssuer(X500Principal issuer) {
        if (issuerNames == null) {
            issuerNames = new ArrayList(); 
        }
        if (! issuerNames.contains(issuer)) {
            issuerNames.add(issuer);
        }
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public void addIssuerName(String name) throws IOException {
        if (issuerNames == null) {
            issuerNames = new ArrayList(); 
        }
        addIssuer(new X500Principal(name));
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public void addIssuerName(byte[] name) throws IOException {
        if (issuerNames == null) {
            issuerNames = new ArrayList(); 
        }
        addIssuer(new X500Principal(name));
    }
     
    /**
     * @com.intel.drl.spec_ref
     */
    public void setMinCRLNumber(BigInteger minCRL) {
        this.minCRL = minCRL;
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public void setMaxCRLNumber(BigInteger maxCRL) {
        this.maxCRL = maxCRL;
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public void setDateAndTime(Date dateAndTime) {
        if (dateAndTime == null) {
            this.dateAndTime = -1;
            return;
        }
        this.dateAndTime = dateAndTime.getTime();
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public void setCertificateChecking(X509Certificate cert) {
        this.certificateChecking = cert;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Collection/*FIXME<X500Principal>*/ getIssuers() {
        if (issuerNames == null) {
            return null;
        }
        return Collections.unmodifiableCollection(issuerNames);
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public Collection/*FIXME<Object>*/ getIssuerNames() {
        if (issuerNames == null) {
            return null;
        }
        ArrayList result = new ArrayList(issuerNames.size());
        int size = issuerNames.size();
        for (int i=0; i<size; i++) {
            result.add(((X500Principal) issuerNames.get(i)).getName());
        }
        return result;
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public BigInteger getMinCRL() {
        return minCRL;
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public BigInteger getMaxCRL() {
        return maxCRL;
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public Date getDateAndTime() {
        if (dateAndTime == -1) {
            return null;
        }
        return new Date(dateAndTime);
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public X509Certificate getCertificateChecking() {
        return certificateChecking;
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("X509CRLSelector:\n[");
        if (issuerNames != null) {
            result.append("\n  IssuerNames:\n  [");
            int size = issuerNames.size();
            for (int i=0; i<size; i++) {
                result.append("\n    " 
                    + ((X500Principal) issuerNames.get(i)).getName()); 
            }
            result.append("\n  ]");
        };
        if (minCRL != null) {
            result.append("\n  minCRL: " + minCRL);
        };
        if (maxCRL != null) {
            result.append("\n  maxCRL: " + maxCRL);
        };
        if (dateAndTime != -1) {
            result.append("\n  dateAndTime: " + (new Date(dateAndTime)));
        }
        if (certificateChecking != null) {
            result.append("\n  certificateChecking: " + certificateChecking);
        };
        result.append("\n]");
        return result.toString();
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public boolean match(CRL crl) {
        if (! (crl instanceof X509CRL)) {
            return false;
        }
        X509CRL crlist = (X509CRL) crl;
        if ((issuerNames != null) && 
                // the search speed depends on the class of issuerNames
                !(issuerNames.contains(crlist.getIssuerX500Principal()))) {
            return false;
        };
        if ((minCRL != null) || (maxCRL != null)) {
            try {
                // As specified in rfc 3280 (http://www.ietf.org/rfc/rfc3280.txt)
                // CRL Number Extension's OID is 2.5.29.20 .
                byte[] bytes = crlist.getExtensionValue("2.5.29.20");
                bytes = (byte[]) ASN1OctetString.getInstance().decode(bytes);
                BigInteger crlNumber = new BigInteger((byte[])
                        ASN1Integer.getInstance().decode(bytes));
                if ((minCRL != null) && (crlNumber.compareTo(minCRL) < 0)) {
                    return false;
                }
                if ((maxCRL != null) && (crlNumber.compareTo(maxCRL) > 0)) {
                    return false;
                }
            } catch (IOException e) {
                //FIXME
                //e.printStackTrace();
                return false;
            }
        };
        if (dateAndTime != -1) {
            Date thisUp = crlist.getThisUpdate();
            Date nextUp = crlist.getNextUpdate();
            if ((thisUp == null) || (nextUp == null)) {
                return false;
            }
            if ((dateAndTime < thisUp.getTime()) 
                                || (dateAndTime > nextUp.getTime())) {
                return false;
            }
        };
        return true;
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public Object clone() {
        X509CRLSelector result = new X509CRLSelector();
        if (issuerNames != null) {
            result.setIssuers(issuerNames);
        };
        result.minCRL = minCRL;
        result.maxCRL = maxCRL;
        result.dateAndTime = dateAndTime;
        result.certificateChecking = certificateChecking;
        return result;
    }
}

