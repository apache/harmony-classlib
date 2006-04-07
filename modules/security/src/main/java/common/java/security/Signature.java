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
* @author Boris V. Kuznetsov
* @version $Revision$
*/

package java.security;

import java.nio.ByteBuffer;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Iterator;
import java.util.Set;

import org.apache.harmony.security.fortress.Engine;


/**
 * @com.intel.drl.spec_ref
 * 
 */

public abstract class Signature extends SignatureSpi {
    
    // The service name.
    private static final String SERVICE = "Signature";

    // Used to access common engine functionality
    private static Engine engine = new Engine(SERVICE);

    // The provider
    private Provider provider;

    // The algorithm.
    private String algorithm;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected static final int UNINITIALIZED = 0;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected static final int SIGN = 2;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected static final int VERIFY = 3;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected int state = UNINITIALIZED;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected Signature(String algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static Signature getInstance(String algorithm)
            throws NoSuchAlgorithmException {
        if (algorithm == null) {
            throw new NullPointerException("Algorithm is null");
        }
        Signature result;
        synchronized (engine) {
            engine.getInstance(algorithm, null);
            if (engine.spi instanceof Signature) {
                result = (Signature) engine.spi;
                result.algorithm = algorithm;
                result.provider = engine.provider;
            } else {
                result = new SignatureImpl((SignatureSpi) engine.spi,
                        engine.provider, algorithm);
            }
        }
        return result;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static Signature getInstance(String algorithm, String provider)
            throws NoSuchAlgorithmException, NoSuchProviderException {
        if ((provider == null) || (provider.length() == 0)) {
            throw new IllegalArgumentException(
                    "Provider is null or empty string");
        }
        Provider p = Security.getProvider(provider);
        if (p == null) {
            throw new NoSuchProviderException("Provider " + provider
                    + " is not available");
        }
        return getInstance(algorithm, p);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static Signature getInstance(String algorithm, Provider provider)
            throws NoSuchAlgorithmException {
        if (provider == null) {
            throw new IllegalArgumentException("Provider is null");
        }
        if (algorithm == null) {
            throw new NullPointerException("Algorithm is null");
        }
        Signature result;
        synchronized (engine) {
            engine.getInstance(algorithm, provider, null);
            if (engine.spi instanceof Signature) {
                result = (Signature) engine.spi;
                result.algorithm = algorithm;
                result.provider = provider;
            } else {
                result = new SignatureImpl((SignatureSpi) engine.spi, provider,
                        algorithm);
            }
        }
        return result;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final Provider getProvider() {
        return provider;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final String getAlgorithm() {
        return algorithm;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void initVerify(PublicKey publicKey)
            throws InvalidKeyException {
        engineInitVerify(publicKey);
        state = VERIFY;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void initVerify(Certificate certificate)
            throws InvalidKeyException {
        if (certificate instanceof X509Certificate) {
            Set ce = ((X509Certificate) certificate).getCriticalExtensionOIDs();
            boolean critical = false;
            if (ce != null && !ce.isEmpty()) {
                for (Iterator i = ce.iterator(); i.hasNext();) {
                    if ("2.5.29.15".equals(i.next())) { 
                        //KeyUsage OID = 2.5.29.15
                        critical = true;
                        break;
                    }
                }
                if (critical) {
                    boolean[] keyUsage = ((X509Certificate) certificate)
                            .getKeyUsage();
                    // As specified in RFC 3280 -
                    // Internet X.509 Public Key Infrastructure
                    // Certificate and Certificate Revocation List (CRL) Profile.
                    // (http://www.ietf.org/rfc/rfc3280.txt)
                    //
                    // KeyUsage ::= BIT STRING { digitalSignature (0), <skipped> }
                    if ((keyUsage != null) && (!keyUsage[0])) { // digitalSignature
                        throw new InvalidKeyException(
                                "The public key in the certificate cannot be used for digital signature purposes");
                    }
                }
            }
        }
        engineInitVerify(certificate.getPublicKey());
        state = VERIFY;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void initSign(PrivateKey privateKey)
            throws InvalidKeyException {
        engineInitSign(privateKey);
        state = SIGN;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void initSign(PrivateKey privateKey, SecureRandom random)
            throws InvalidKeyException {
        engineInitSign(privateKey, random);
        state = SIGN;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final byte[] sign() throws SignatureException {
        if (state != SIGN) {
            throw new SignatureException(
                    "Signature object is not initialized properly.");
        }
        return engineSign();
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final int sign(byte[] outbuf, int offset, int len)
            throws SignatureException {
        if (state != SIGN) {
            throw new SignatureException(
                    "Signature object is not initialized properly.");
        }
        if (outbuf == null || offset < 0 || len < 0 ||
                offset + len > outbuf.length) {
            throw new IllegalArgumentException(
                    "Incorrect offset/len parameters");
        }
        return engineSign(outbuf, offset, len);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final boolean verify(byte[] signature) throws SignatureException {
        if (state != VERIFY) {
            throw new SignatureException(
                    "Signature object is not initialized properly.");
        }
        return engineVerify(signature);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final boolean verify(byte[] signature, int offset, int length)
            throws SignatureException {
        if (state != VERIFY) {
            throw new SignatureException(
                    "Signature object is not initialized properly.");
        }
        if (signature == null || offset < 0 || length < 0 ||
                offset + length > signature.length) {
            throw new IllegalArgumentException(
                    "Incorrect offset/length parameters");
        }
        return engineVerify(signature, offset, length);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void update(byte b) throws SignatureException {
        if (state == UNINITIALIZED) {
            throw new SignatureException(
                    "Signature object is not initialized properly.");
        }
        engineUpdate(b);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void update(byte[] data) throws SignatureException {
        if (state == UNINITIALIZED) {
            throw new SignatureException(
                    "Signature object is not initialized properly.");
        }
        engineUpdate(data, 0, data.length);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void update(byte[] data, int off, int len)
            throws SignatureException {
        if (state == UNINITIALIZED) {
            throw new SignatureException(
                    "Signature object is not initialized properly.");
        }
        if (data == null || off < 0 || len < 0 ||
                off + len > data.length) {
            throw new IllegalArgumentException(
                    "Incorrect offset/len parameters");
        }
        engineUpdate(data, off, len);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void update(ByteBuffer data) throws SignatureException {
        if (state == UNINITIALIZED) {
            throw new SignatureException(
                    "Signature object is not initialized properly.");
        }
        engineUpdate(data);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public String toString() {
        return "SIGNATURE " + algorithm + " state: " + stateToString(state);
    }

    // Convert state to string
    private String stateToString(int state) {
        switch (state) {
        case UNINITIALIZED:
            return "UNINITIALIZED";
        case SIGN:
            return "SIGN";
        case VERIFY:
            return "VERIFY";
        default:
            return "";
        }
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     * @deprecated Use {@link Signature#setParameter(AlgorithmParameterSpec) setParameter}
     */
    public final void setParameter(String param, Object value)
            throws InvalidParameterException {
        engineSetParameter(param, value);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void setParameter(AlgorithmParameterSpec params)
            throws InvalidAlgorithmParameterException {
        engineSetParameter(params);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final AlgorithmParameters getParameters() {
        return engineGetParameters();
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * @deprecated There is no generally accepted parameter naming convention.
     */
    public final Object getParameter(String param)
            throws InvalidParameterException {
        return engineGetParameter(param);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public Object clone() throws CloneNotSupportedException {
        if (this instanceof Cloneable) {
            return super.clone();
        } else {
            throw new CloneNotSupportedException();
        }
    }

    /**
     * 
     * Internal Signature implementation
     * 
     */
    private static class SignatureImpl extends Signature {

        private SignatureSpi spiImpl;

        // Consructor
        public SignatureImpl(SignatureSpi signatureSpi, Provider provider,
                String algorithm) {
            super(algorithm);
            super.provider = provider;
            spiImpl = signatureSpi;
        }

        // engineSign() implementation
        protected byte[] engineSign() throws SignatureException {
            return spiImpl.engineSign();
        }

        //  engineUpdate() implementation
        protected void engineUpdate(byte arg0) throws SignatureException {
            spiImpl.engineUpdate(arg0);
        }

        // engineVerify() implementation
        protected boolean engineVerify(byte[] arg0) throws SignatureException {
            return spiImpl.engineVerify(arg0);
        }

        // engineUpdate() implementation
        protected void engineUpdate(byte[] arg0, int arg1, int arg2)
                throws SignatureException {
            spiImpl.engineUpdate(arg0, arg1, arg2);
        }

        // engineInitSign() implementation
        protected void engineInitSign(PrivateKey arg0)
                throws InvalidKeyException {
            spiImpl.engineInitSign(arg0);
        }

        // engineInitVerify() implementation
        protected void engineInitVerify(PublicKey arg0)
                throws InvalidKeyException {
            spiImpl.engineInitVerify(arg0);
        }

        // engineGetParameter() implementation
        protected Object engineGetParameter(String arg0)
                throws InvalidParameterException {
            return spiImpl.engineGetParameter(arg0);
        }

        // engineSetParameter() implementation
        protected void engineSetParameter(String arg0, Object arg1)
                throws InvalidParameterException {
            spiImpl.engineSetParameter(arg0, arg1);
        }

        // Returns a clone if the spiImpl is cloneable
        public Object clone() throws CloneNotSupportedException {
            if (spiImpl instanceof Cloneable) {
                SignatureSpi spi = (SignatureSpi) spiImpl.clone();
                return new SignatureImpl(spi, getProvider(), getAlgorithm());
            } else {
                throw new CloneNotSupportedException();
            }
        }
    }
}