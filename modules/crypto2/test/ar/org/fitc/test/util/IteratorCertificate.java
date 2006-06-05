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

package ar.org.fitc.test.util;

import java.security.InvalidKeyException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;

import org.bouncycastle.asn1.x509.KeyUsage;

public class IteratorCertificate extends CertGen implements
        Iterator<Certificate> {

    final static private int[] all = { KeyUsage.encipherOnly, KeyUsage.cRLSign,
            KeyUsage.keyCertSign, KeyUsage.keyAgreement,
            KeyUsage.dataEncipherment, KeyUsage.keyEncipherment,
            KeyUsage.nonRepudiation, KeyUsage.digitalSignature,
            KeyUsage.decipherOnly };

    final private TreeSet<Integer> set = new TreeSet<Integer>();

    private Iterator<Integer> count;

    private int uses = 0;

    @SuppressWarnings("unchecked")
    public void initSet() {
        CopyOnWriteArraySet<Integer> s = new CopyOnWriteArraySet<Integer>();
        for (int i = 0; i < all.length; i++) {
            s.add((Integer) all[i]);
        }
        for (int i = 0; i < all.length; i++) {
            for (Iterator<Integer> it = s.iterator(); it.hasNext();) {
                int e = (int) it.next();
                s.add((Integer) (e | all[i]));
            }
        }
        set.addAll(s);
    }

    public boolean isKeyEnciphermentOn() {
        return (uses & all[5]) == all[5];
    }
    public boolean isDataEnciphermentOn() {
        return (uses & all[4]) == all[4];
    }

    public IteratorCertificate() {
        super();
        initSet();
        reset();
        setCriticalkeyusage(false);

    }

    private int nextKeyUse() {
        int result = count.next();
        uses = result;
        return result;

    }

    private void reset() {
        count = set.iterator();

    }

    private boolean atTop() {
        return !count.hasNext();
    }

    public IteratorCertificate(String file) {

    }

    public boolean hasNext() {
        if (atTop() && isCriticalkeyusage()) {
            return false;
        }
        return true;
    }

    public String keyUses2String() {
        return keyUses2String(uses);
    }

    public static String keyUses2String(int keyUses) {
        String result = "";
        if ((keyUses & all[0]) == all[0]) {
            result += " KeyUsage.encipherOnly";
        }
        if ((keyUses & all[1]) == all[1]) {
            result += " KeyUsage.cRLSign";
        }
        if ((keyUses & all[2]) == all[2]) {
            result += " KeyUsage.keyCertSign";
        }
        if ((keyUses & all[3]) == all[3]) {
            result += " KeyUsage.keyAgreement";
        }
        if ((keyUses & all[4]) == all[4]) {
            result += " KeyUsage.dataEncipherment";
        }
        if ((keyUses & all[5]) == all[5]) {
            result += " KeyUsage.ketEncipherment";
        }
        if ((keyUses & all[6]) == all[6]) {
            result += " KeyUsage.nonRepudiation";
        }
        if ((keyUses & all[7]) == all[7]) {
            result += " KeyUsage.digitalSignaute";
        }
        if ((keyUses & all[8]) == all[8]) {
            result += " KeyUsage.decipherOnley";
        }
        return result;
    }

    public static void main(String[] args) {

            System.out.println("KeyUsage.encipherOnly " + Integer.toHexString(IteratorCertificate.all[0]));
            System.out.println("KeyUsage.cRLSign " + Integer.toHexString(IteratorCertificate.all[1]));
            System.out.println("KeyUsage.keyCertSign " + Integer.toHexString(IteratorCertificate.all[2]));
            System.out.println("KeyUsage.keyAgreement " + Integer.toHexString(IteratorCertificate.all[3]));
            System.out.println("KeyUsage.dataEncipherment " + Integer.toHexString(IteratorCertificate.all[4]));
            System.out.println("KeyUsage.ketEncipherment " + Integer.toHexString(IteratorCertificate.all[5]));
            System.out.println("KeyUsage.nonRepudiation " + Integer.toHexString(IteratorCertificate.all[6]));
            System.out.println("KeyUsage.digitalSignaute " + Integer.toHexString(IteratorCertificate.all[7]));
            System.out.println("KeyUsage.decipherOnley " + Integer.toHexString(IteratorCertificate.all[8]));


//        IteratorCertificate c = new IteratorCertificate();
//        while (!c.atTop()) {
//            System.out.println(c.nextKeyUse());
//        }
    }

    public Certificate next() {
        if (atTop()) {
            reset();
            setCriticalkeyusage(true);
        }
        setKeyusageparameters(nextKeyUse());

        try {
            return getCertificate();
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SignatureException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public void remove() {

    }

}
