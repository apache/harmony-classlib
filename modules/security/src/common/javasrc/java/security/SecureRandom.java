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

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.apache.harmony.security.fortress.Engine;
import org.apache.harmony.security.fortress.Services;


/**
 * @com.intel.drl.spec_ref
 * 
 */

public class SecureRandom extends Random {
    
    /**
     * @com.intel.drl.spec_ref
     * 
     */
    private static final long serialVersionUID = 4940670005562187L;
    
    // The service name.
    private static final transient String SERVICE = "SecureRandom";
    
    // Used to access common engine functionality
    private static transient Engine engine = new Engine(SERVICE);
    
    /**
     * @com.intel.drl.spec_ref
     */
    private Provider provider;
    
    /**
     * @com.intel.drl.spec_ref
     */
    private SecureRandomSpi secureRandomSpi; 
    
    /**
     * @com.intel.drl.spec_ref
     */
    private String algorithm;
    
    /**
     * @com.intel.drl.spec_ref
     */
    private byte[] state;
    
    /**
     * @com.intel.drl.spec_ref
     */
    private byte[] randomBytes;
    
    /**
     * @com.intel.drl.spec_ref
     */
    private int randomBytesUsed;
    
    /**
     * @com.intel.drl.spec_ref
     */
    private long counter;
    
    // Internal SecureRandom used for getSeed(int)
    private static transient SecureRandom internalSecureRandom;
    
    /**
     * @com.intel.drl.spec_ref
     * 
     */
    public SecureRandom() {
        super(0);
        Provider.Service service = findService();
        if (service == null) {
            this.provider = null;
            this.secureRandomSpi = new DummySecureRandom();
            this.algorithm = "java.util.Random";
        } else {
            try {
                this.provider = service.getProvider();
                this.secureRandomSpi = (SecureRandomSpi)service.newInstance(null);
                this.algorithm = service.getAlgorithm();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }            
        }    
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     */
    public SecureRandom(byte[] seed) {
        this();
        setSeed(seed);
    }
    
    //Find SecureRandom service.
    private Provider.Service findService() {
        Set s;
        Provider.Service service;
        for (Iterator it1 = Services.getProvidersList().iterator(); it1.hasNext();) {
            service = ((Provider)it1.next()).getService("SecureRandom");
            if (service != null) {
                return service;
            }
        }
        return null;
    }
    
    // Returns secureRandomSpi
    private SecureRandomSpi getSpi() {
        return secureRandomSpi;
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     */
    protected SecureRandom(SecureRandomSpi secureRandomSpi,
                           Provider provider) {
        this(secureRandomSpi, provider, null);
    }
    
    // Constructor
    private SecureRandom(SecureRandomSpi secureRandomSpi,
                         Provider provider,
                         String algorithm) {
        super(0);
        this.provider = provider;
        this. algorithm = algorithm;
        this.secureRandomSpi = secureRandomSpi;
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     */
    public static SecureRandom getInstance(String algorithm)
                                throws NoSuchAlgorithmException {
        if (algorithm == null) {
            throw new NullPointerException("Algorithm is null");
        }
        synchronized (engine) {
            engine.getInstance(algorithm, null);
            return new SecureRandom((SecureRandomSpi)engine.spi, engine.provider, algorithm);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     */
    public static SecureRandom getInstance(String algorithm, String provider)
                                throws NoSuchAlgorithmException, NoSuchProviderException {
        if ((provider == null) || (provider.length() == 0)) {
            throw new IllegalArgumentException(
                    "Provider is null or empty string");
        }
        Provider p = Security.getProvider(provider);
        if (p == null) {
            throw new NoSuchProviderException("Provider "+ provider + " is not available"); 
        }
        return getInstance(algorithm, p);    
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     */
    public static SecureRandom getInstance(String algorithm, Provider provider)
                                throws NoSuchAlgorithmException {
        if (provider == null) {
            throw new IllegalArgumentException("Provider is null");
        }
        if (algorithm == null) {
            throw new NullPointerException("Algorithm is null");
        }
        synchronized (engine) {
            engine.getInstance(algorithm, provider, null);
            return new SecureRandom((SecureRandomSpi)engine.spi, provider, algorithm);
        }
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
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     */
    public synchronized void setSeed(byte[] seed) {
        secureRandomSpi.engineSetSeed(seed);
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     */
    public void setSeed(long seed) {
        if (seed == 0) {    // skip call from Random
            return;
        }
        byte[] byteSeed = {
                (byte)((seed >> 56) & 0xFF),
                (byte)((seed >> 48) & 0xFF),
                (byte)((seed >> 40) & 0xFF),
                (byte)((seed >> 32) & 0xFF),
                (byte)((seed >> 24) & 0xFF),
                (byte)((seed >> 16) & 0xFF),
                (byte)((seed >> 8) & 0xFF),
                (byte)((seed) & 0xFF)
        };
        setSeed(byteSeed);
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     */
    public synchronized void nextBytes(byte[] bytes) {
        secureRandomSpi.engineNextBytes(bytes);
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     */
    protected final int next(int numBits) {
        if (numBits < 0) {
            numBits = 0;
        } else {
            if (numBits > 32) {
                numBits = 32;
            }
        }
        int bytes = (numBits+7)/8;
        byte[] next = new byte[bytes];
        int ret = 0;
         
        nextBytes(next);
        for (int i = 0; i < bytes; i++) {
            ret = (next[i] & 0xFF) | (ret << 8);
        }    
        ret = ret >> (bytes*8 - numBits);
        return ret;
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     */
    public static byte[] getSeed(int numBytes) {
        if (internalSecureRandom == null) {
            internalSecureRandom = new SecureRandom();
        }
        return internalSecureRandom.generateSeed(numBytes);
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     */
    public byte[] generateSeed(int numBytes) {
        return secureRandomSpi.engineGenerateSeed(numBytes);
    }
    
    /**
     * 
     * Dummy SecureRandom based on Random
     * 
     */
    private class DummySecureRandom extends SecureRandomSpi {
        
        Random rand;
        
        // Creates SecureRandomSpi object
        public DummySecureRandom() {
            rand = new Random();
        }
        
        // Creates SecureRandomSpi object
        public DummySecureRandom(long seed) {
            rand = new Random(seed);
        }

        // Sets the seed of this random number generator 
        public void engineSetSeed(byte[] seed) {
            long l = 0;
            int length = 8;
            
            if (seed.length < 8) {
                length = seed.length;
            }
            for (int i = 0; i < length; i++) {
                l = (l << 8) | (seed[i] & 0xFF);
            }        
            rand.setSeed(l);
        }
        
        // Generates random bytes and places them into a byte array
        public void engineNextBytes(byte[] bytes) {
            rand.nextBytes(bytes);
        }
        
        // Generates random bytes
        //
        // @param numBytes
        // @return An array of random bytes
        public byte[] engineGenerateSeed(int numBytes) {
            if (numBytes < 1) {
                return null;
            }
            byte[] next = new byte[numBytes]; 
            nextBytes(next);
            return next;
        }
    }
}
