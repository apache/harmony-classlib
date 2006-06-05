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

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.ShortBufferException;

import ar.org.fitc.test.util.crypto.Util;

/**
 * A MAC generator for use with test cases generators
 *
 * @author odemo
 *
 */

public class MacGenerator {

    /**
     * Indicates the use of the method Mac.update(byte[] input, int offset)
     */
    public static final int BYTEARRAYINTINTUPD = 2;

    /**
     * Indicates the use of the method Mac.update(byte[] input)
     */
    public static final int BYTEARRAYUPD = 0;

    /**
     * Indicates the use of the method Mac.update(ByteBuffer input)
     */
    public static final int BYTEBUFFERUPD = 3;

    /**
     * Indicates the use of the method Mac.update(byte input)
     */
    public static final int BYTEUPD = 1;

    /**
     * Indicates the use of the method Mac.doFinal()
     */
    public static final int DF = 4;

    /**
     * Indicates the use of the method Mac.doFinal(byte input)
     */
    public static final int DFBYTE = 5;

    /**
     * Indicates the use of the method Mac.doFinal(byte output,int outOffset)
     */
    public static final int DFBYTEINT = 6;

    private String algorithm;

    private int doFinalType = 0;

    private Exception exception = null;

    private String exceptionName;

    private boolean exceptocurred;

    private byte[] hash;

    private byte[] inputArray;

    private byte inputByte;

    private ByteBuffer inputByteBuffer;

    private int inputLength;

    private int inputOffset;

    private K key;

    private Mac mac;

    private int n = 0;

    private int outOffset = 0;

    private String provider;

    private static byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,
            (byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };

    private int updateType = 0;

    MacGenerator(String algorithm, String provider)
            throws NoSuchAlgorithmException, NoSuchProviderException {
        this.algorithm = algorithm;
        this.provider = provider;
        this.updateType = BYTEARRAYUPD;
        this.doFinalType = DF;
        exceptocurred = generateMac();
    }

    MacGenerator(String algorithm, String provider, int updateParameters)
            throws NoSuchAlgorithmException, NoSuchProviderException {
        this.algorithm = algorithm;
        this.provider = provider;
        this.updateType = updateParameters;
        this.doFinalType = DF;
        exceptocurred = generateMac();
    }

    MacGenerator(String algorithm, String provider, int updateParameters,
            int dofinalParameters) throws NoSuchAlgorithmException,
            NoSuchProviderException {
        this.algorithm = algorithm;
        this.provider = provider;
        this.updateType = updateParameters;
        this.doFinalType = dofinalParameters;
        exceptocurred = generateMac();
    }

    /**
     * @return true if an exception was raised, false otherwise
     */
    public boolean exceptionOcurred() {
        return exceptocurred;
    }

    private void fillInputRandomByteArray(int size) {
        inputArray = new byte[size];
        for (int x = 0; x < inputArray.length; x++) {
            inputArray[x] = (byte) Util.getRandomInt(256, 0);
        }
    }

    private boolean generateMac() throws NoSuchAlgorithmException,
            NoSuchProviderException {

        key = new K(algorithm, salt);
        try {
            mac = Mac.getInstance(algorithm, provider);

            hash = new byte[mac.getMacLength() * Util.getRandomInt(4, 1)];

            if (n < 25) { // TODO change this number when generating more than
                            // 30 testcases
                mac.init(key);
            }

            if (doFinalType == DF || doFinalType == DFBYTEINT) {
                if (updateType == BYTEARRAYUPD) {
                    fillInputRandomByteArray(Util.getRandomInt(50, 15));
                    for (int i = 0; i < 100; i++) {
                        mac.update(inputArray);
                    }
                } else if (updateType == BYTEUPD) {
                    inputByte = (byte) Util.getRandomInt(256, 0);
                    for (int i = 0; i < 100; i++) {
                        mac.update(inputByte);
                    }
                } else if (updateType == BYTEARRAYINTINTUPD) {
                    fillInputRandomByteArray(Util.getRandomInt(50, 15));
                    /*
                     * FIXME if we do not want to capture IllegalStateException
                     * something have to be done here
                     */
                    inputLength = Util.getRandomInt(inputArray.length
                            + inputArray.length / 4, 0);
                    inputOffset = Util.getRandomInt(inputArray.length / 4, 0);

                    for (int i = 0; i < 100; i++) {
                        mac.update(inputArray, inputOffset, inputLength);
                    }
                } else if (updateType == BYTEBUFFERUPD) {
                    fillInputRandomByteArray(Util.getRandomInt(50, 15));
                    inputByteBuffer = ByteBuffer.wrap(inputArray);
                    for (int i = 0; i < 100; i++) {
                        mac.update(inputByteBuffer);
                    }
                }
            }

            if (doFinalType == DF) {
                hash = mac.doFinal();
            } else if (doFinalType == DFBYTE) {
                hash = mac.doFinal(inputArray);
            } else if (doFinalType == DFBYTEINT) {

                if (hash.length > mac.getMacLength()) {
                    outOffset = Util.getRandomInt(getHashLength()
                            - mac.getMacLength(), 1);
                }

                if (Util.getRandomInt(10000, 1) < 2500) {
                    outOffset = getHashLength()
                            + Util.getRandomInt(getHashLength() * 2, 1);
                }
                mac.doFinal(hash, outOffset);
            }

        } catch (IllegalStateException e) {
            exceptionName = String.valueOf("IllegalStateException");
            exception = e;
            return true;
        } catch (InvalidKeyException e) {
            exceptionName = String.valueOf("InvalidKeyException");
            exception = e;
            return true;
        } catch (IllegalArgumentException e) {
            exceptionName = String.valueOf("IllegalArgumentException");
            exception = e;
            return true;
        } catch (ShortBufferException e) {
            exceptionName = String.valueOf("ShortBufferException");
            exception = e;
            return true;
        }
        return false;
    }

    /**
     * @return A String representation of the algorithm name
     */
    public String getAlgorithm() {
        return algorithm;
    }


    /**
     * @return the encoded key used to create the hash
     */
    public String getEncodedKey() {
        String s = Arrays.toString(key.getEncoded()).replace('[', '{');
        s = s.replace(']', '}');
        return s;
    }

    /**
     * @return the exception or null if no exception was raised
     */

    public Exception getException() {
        return exception;
    }

    /**
     * @return A String representation of the exception raised
     */
    public String getExceptionName() {
        return exceptionName;
    }

    /**
     * @return the computed hash
     */
    public String getHash() {
        // System.err.println(Arrays.toString(hash));
        return Arrays.toString(hash);
    }

    /**
     * @return an int representing the length of the hash
     */
    public int getHashLength() {
        return hash.length;
    }

    /**
     * @return the mac initialization string
     */
    public String getInit() {
        if (exceptocurred) {
            return "";
        } else {
            return "mac.init(key);";
        }

    }

    /**
     * @return the input data procesed by the mac to generate the hash
     */
    public String getInputArray() {
        String s = Arrays.toString(inputArray);
        s = s.replace('[', '{');
        s = s.replace(']', '}');
        return s;
    }

    /**
     * @return the input data procesed by the mac to generate the hash
     */
    public byte getInputByte() {
        return inputByte;
    }

    /**
     * @return the length of the input data
     */
    public int getInputLength() {
        return inputLength;
    }

    /**
     * @return the offset of the input data array
     */
    public int getInputOffset() {
        return inputOffset;
    }

    /**
     * @return a string representing the mac instance (with the purpose of generating code)
     */
    public String getInstance() {
        return "Mac.getInstance(\"" + algorithm + "\",\"" + provider + "\");";
    }

    /**
     * @return A String representing the algorithm set on the key
     */
    public String getKeyAlgorithm() {
        return key.getAlgorithm();
    }

    /**
     * @return the offset of the hash array
     */
    public int getOutOffset() {
        return outOffset;
    }

    /**
     * @return A String representing the provider
     */
    public String getProvider() {
        return provider;
    }

    /**
     * Forces the generation of another random data and
     * hash based on the parameters established before
     */
    public void next() {
        try {
            exceptocurred = generateMac();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        n++;
    }
}