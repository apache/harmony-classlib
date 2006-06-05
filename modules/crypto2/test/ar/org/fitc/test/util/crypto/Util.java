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

package ar.org.fitc.test.util.crypto;

import java.awt.color.ColorSpace;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.SignatureException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.DESKeySpec;

import org.bouncycastle.asn1.x509.KeyUsage;

import ar.org.fitc.test.crypto.definitions.DefinitionCipher;
import ar.org.fitc.test.crypto.definitions.DefinitionCipherInitedNonBlock;
import ar.org.fitc.test.crypto.definitions.DefinitionCipherWrapModeInited;
import ar.org.fitc.test.crypto.definitions.DefinitionKeyGenerator;
import ar.org.fitc.test.util.CertGen;
import ar.org.fitc.test.util.Messages;


class IOExcepctionTesting implements Serializable, Messages {

    private static final long serialVersionUID = 1L;

    /*
     * Should Allways throws an IOException when instanciated
     */
    public IOExcepctionTesting() throws IOException {
        throw new IOException(msgThrowTest);
    }
}

public class Util {

    private static char[] arrayChar = {'0','1','2','3','4','5','6','7','8','9'};
    public static String[] backups = {"Mariano","Sergio","Diego","Diego","Jorge","Hugo","Osvaldo","Gonzalo","Horacio","Gustavo","Miguel","Matthias"};

    @SuppressWarnings("unused")
    private final static String[] ROUNDINGMODES = {"CEILING",
        "DOWN",
        "FLOOR",
        "HALF_DOWN",
        "HALF_EVEN",
        "HALF_UP",
        "UNNECESSARY",
    "UP"};

    public static final int TEST_EXCEPT = 0;

    public static final int TEST_RET = 1;

    public static String doubletoString(double number) {
        boolean debug = false;
        String temp = Double.toString(number);
        if (temp.indexOf('+') != -1) {
            return temp;
        } else {
            if (debug) {
                System.out.println(temp.indexOf('E'));
                System.out.println(temp.substring(0,temp.indexOf('E')+1));
                System.out.println(temp.substring(temp.indexOf('E')+1,temp.length()));
            }
            return temp.substring(0,temp.indexOf('E')+1)+"+"+temp.substring(temp.indexOf('E')+1,temp.length());
        }
    }

    /**
     * Convert the passed in String to a byte array by
     * taking the bottom 8 bits of each character it contains.
     *
     * @param string the string to be converted
     * @return a byte array representation
     */
    public static byte[] toByteArray(String string)
    {
        byte[]  bytes = new byte[string.length()];
        char[]  chars = string.toCharArray();

        for (int i = 0; i != chars.length; i++)
        {
            bytes[i] = (byte)chars[i];
        }

        return bytes;
    }

    public static String generateCharArrayDeclaration(char[] keyarray,String varName) {
        StringBuffer sb = new StringBuffer();
        sb.append("char[] "+varName+ " = {");
        for (int i=0 ; i < keyarray.length ; i++) {
            sb.append("'"+keyarray[i]+"'");
            if (i != keyarray.length-1)
                sb.append(",");
        }
        sb.append("};");
        return sb.toString();
    }

    public static BigDecimal generateRandomBigDecimal(BigInteger bg,int scale) {
        return new BigDecimal(bg,0);
    }

    public static ArrayList generateRandomBigDecimalArrayList(int quantity) {
        return generateRandomBigDecimalArrayList(quantity,getRandomInt(512,64));
    }

    @SuppressWarnings({"unused","unchecked"})
    public static ArrayList generateRandomBigDecimalArrayList(int quantity,int bits) {
        ArrayList lista = new ArrayList<BigDecimal>(quantity);
        for (int i=0; i < quantity ; i++ )
            lista.add(new BigDecimal(new BigInteger(rndnegate(),generateRandomByteArray(bits/8)),rndnegate()*getRandomInt(1000,1)));
        return lista;
    }

    /**
     * Generates a new key based on the parameter algorithm
     * @return an instance of Key
     * @param algorithm the algorithm name of the key
     * @param provider the provider where look up the algorithm
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    public static Key getInstanceKey(String algorithm,String provider) throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyGenerator kg = KeyGenerator.getInstance(algorithm, provider);
        kg.init(new SecureRandom());
        return kg.generateKey();
    }

    @SuppressWarnings("unchecked")
    public static ArrayList generateRandomBigDecimalForDivisorArrayList(int quantity,int bits,boolean periodicdivision) {
        ArrayList lista = new ArrayList<BigDecimal>(quantity);
        int probable = 0;
        if (periodicdivision) {
            probable = 1;
        } else {
            probable = 0;
        }
        for (int i=0; i < quantity ; i++ ) {
            lista.add(generateRandomBigDecimal(generateRandomBigInteger(getRandomInt(1000,1),getRandomInt(1000,1),bits,probable),rndnegate()*getRandomInt(100,1)));
        }
        return lista;
    }

    public static BigInteger generateRandomBigInteger(int k, int l,int bits,int probable) {
        BigInteger q;
        BigInteger negativefactor;

        if (getRandomBoolean())
            negativefactor= BigInteger.ONE.negate();
        else
            negativefactor = BigInteger.ONE;

        if (probable == 1) {
            // causa que p/q sea periódico
            q = new BigInteger(rndnegate(),generateRandomByteArray(bits/8));
            if (q.mod(BigInteger.valueOf(5)).compareTo(BigInteger.ZERO) == 0) {
                q = q.add(BigInteger.ONE);
            }
        } else {
            // causa que p/q NO sea periódico
            q = BigInteger.valueOf(2).pow(k).multiply(BigInteger.valueOf(5).pow(l)).multiply(negativefactor);
        }
        return q;
    }

    public static byte[] generateRandomByteArray(int length) {
        byte[] array = new byte[length];
        int i=0;
        for (i=0 ; i < length ; i++) {
            array[i] = (byte)getRandomInt();
        }
        return array;
    }

    @SuppressWarnings("unused")
    public static char[] generateRandomCharArray(int length) {
        Random random = new Random(System.currentTimeMillis());
        int negativeoffset = 0;
        int division = getRandomInt(15,2);
        boolean negative = getRandomBoolean();
        if (negative) { negativeoffset=1; }
        char[] array = new char[length+negativeoffset];
        int i=0;
        for (i=0 ; i < length+negativeoffset; i++) {
            array[i] = arrayChar[getRandomInt(9,0)];
        }
        if (negative)
            array[0] = '-';
        array[length/division] = '.';
        return array;
    }

    public static ArrayList generateRandomCharArrayList(int quantity) {
        return generateRandomCharArrayList(quantity,(getRandomInt(512,64)/8));
    }

    @SuppressWarnings("unchecked")
    public static ArrayList generateRandomCharArrayList(int quantity,int arrayLength) {
        ArrayList lista = new ArrayList<char[]>(quantity);
        char [] array = new char[arrayLength];
        for (int i=0; i < quantity ; i++ ) {
            for (int j=0; j < arrayLength ; j++) {
                array[j] = (char)getRandomInt();
            }
            lista.add(array);
        }
        return lista;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList generateRandomDoubleArrayList(int quantity) {
        Random rnd = new Random(System.currentTimeMillis());
        ArrayList lista = new ArrayList(quantity);
        for (int i=0; i < quantity ; i++ )
            lista.add((double)(Math.random()*getRandomInt()*rnd.nextGaussian()));
        return lista;
    }

    @SuppressWarnings("unused")
    public static String generateRandomIntArray(int quantity) {
        StringBuffer sb = new StringBuffer();
        sb.append("\n\nprivate static int[] RandomInts = {");
        for (int i=0 ; i < quantity; i++) {
            if (i != 0)
                sb.append("\t\t\t\t   ");
            sb.append(getRandomInt());
            if (i != (quantity-1))
                sb.append(",\n");
        }
        sb.append("};\n\n");
        return sb.toString();
    }

    @SuppressWarnings("unused")
    public static int[] generateRandomIntArray2(int quantity) {
        int[] array = new int[quantity];
        for (int i=0 ; i < quantity; i++) {
            array[i] = getRandomInt();
        }
        return array;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList generateRandomIntArrayList(int quantity) {
        ArrayList lista = new ArrayList(quantity);
        int[] numbers = new int[quantity];
        numbers = generateRandomIntArray2(quantity);
        for (int i=0; i < quantity ; i++ )
            lista.add(numbers[i]);
        return lista;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList generateRandomLongArrayList(int quantity) {
        ArrayList lista = new ArrayList(quantity);
        for (int i=0; i < quantity ; i++ )
            lista.add((long)(Math.random()*getRandomInt()));
        return lista;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList generateRandomMathContextArrayList(int quantity) {
        ArrayList lista = new ArrayList<MathContext>(quantity);
        for (int i=0; i < quantity ; i++ )
            lista.add(new MathContext("precision="+Util.getRandomInt(Integer.MAX_VALUE,1) +" roundingMode="+getRandomRoundingMode()));
        return lista;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList generatestBigDecimalCharArrayMathContext001teRandomDoubleArrayList(int quantity) {
        ArrayList lista = new ArrayList(quantity);
        for (int i=0; i < quantity ; i++ )
            lista.add((Math.random()*getRandomInt()));
        return lista;
    }

    public static void generateTestCertificateFiles() throws InvalidKeyException, NoSuchProviderException, SecurityException, SignatureException {
        String filename = null;
        try {
            FileOutputStream fos = new  FileOutputStream(filename);
            @SuppressWarnings("unused") BufferedOutputStream bos = new BufferedOutputStream(fos);
            CertGen cg = new CertGen();
            cg.setIssuer("ITC Testing Group");
            cg.setCriticalkeyusage(true);
            cg.setKeyPairLength(1024);
            cg.setKeyusageparameters(KeyUsage.keyEncipherment | KeyUsage.keyAgreement);
            cg.getCertificate().toString();
        } catch (FileNotFoundException e) {
        }

    }

    public static AlgorithmParameters getAlgorithmParameters(String alg, String pro) throws NoSuchAlgorithmException, NoSuchProviderException {
        AlgorithmParameters apg = AlgorithmParameters.getInstance(alg, pro);
        return apg;
    }

    /**
     * Generates a new Cipher, based on DefinitionCipher interface. It is not inited.
     * @return an initialized instance of Cipher
     * @throws Exception
     */
    public static Cipher getInstanceCipher() throws Exception {
        Cipher cipher = Cipher.getInstance(DefinitionCipher.transformation, DefinitionCipher.providerString);
        return cipher;
    }

    /**
     * Generates a new Cipher intialized for encription, based on DefinitionCipher interface
     * @return an initialized instance of Cipher
     * @throws Exception
     */
    public static Cipher getInstanceCipherInited() throws Exception {
        Cipher cipher = Cipher.getInstance(DefinitionCipher.transformation, DefinitionCipher.providerString);
        cipher.init(DefinitionCipher.opmode, getInstanceKey(), new SecureRandom());
        return cipher;
    }

    /**
     * Generates a new Cipher intialized for encription, based on transformation parameter
     * @return an initialized instance of Cipher
     * @params transformation transformation string
     * @throws Exception
     */
    public static Cipher getInstanceCipherInited(String transformation) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation, DefinitionCipher.providerString);
        cipher.init(DefinitionCipher.opmode, getInstanceKey(), new SecureRandom());
        return cipher;
    }

    public static Cipher getInstanceCipherInited(String transformation, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, key, new SecureRandom());
        return null;
    }


    /**
     * Generates a new Cipher intialized for encription, based on DefinitionCipherInitedNonBlock interface
     * @return an initialized instance of Cipher
     * @param key the key object for initializing the cipher
     * @throws Exception
     */
    public static Cipher getInstanceCipherInitedNonBlock(Key key) throws Exception {
        Cipher cipher = Cipher.getInstance(DefinitionCipherInitedNonBlock.transformation, DefinitionCipherInitedNonBlock.providerString);
        cipher.init(DefinitionCipherInitedNonBlock.opmode, key, new SecureRandom());
        return cipher;
    }



    /**
     * Generates a new Cipher intialized for wrapping, based on DefinitionCipherWrapModeInited interface
     * @return an initialized instance of Cipher
     * @throws Exception
     */
    public static Cipher getInstanceCipherWrapModeInited() throws Exception {
        Cipher cipher = Cipher.getInstance(DefinitionCipherWrapModeInited.transformation, DefinitionCipherWrapModeInited.providerString);
        cipher.init(DefinitionCipherWrapModeInited.opmode, getInstanceKey(), new SecureRandom());
        return cipher;
    }

    /**
     * Generates a new key based on DefinitionKeyGenerator interface
     * @return an instance of Key
     * @throws Exception
     */
    public static Key getInstanceKey() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(DefinitionKeyGenerator.algorithm, "BC");
        kg.init(new SecureRandom());
        return kg.generateKey();
    }

    /**
     * Generates a new key based on the parameter algorithm
     * @return an instance of Key
     * @param algorithm the algorithm name of the key
     * @throws NoSuchAlgorithmException
     */
    public static Key getInstanceKey(String algorithm) throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance(algorithm);
        kg.init(new SecureRandom());
        return kg.generateKey();
    }

    public static KeySpec getInstanceKeySpec(String name) throws InvalidKeyException {
        if (name.equals("DES")) {
            return (KeySpec) new DESKeySpec(new byte[] {0x12, 0x12, 0x32, 0x32, 0x32, 0x32, 0x32, 0x32});
        }
        return null;
    }

    /**
     * Brings an instance of provider
     * @return an instance of provider
     * @param name the name of the provider
     */
    public static Provider getInstanceProvider(String name) {
        return Security.getProvider(name);
    }

    /**
     * Generate an new object wich implemements serializable interface
     * @return an initialized instance of ColorSpace
     * @throws Exception
     */
    public static Serializable getInstanceSerializedObject() throws Exception {
        return ColorSpace.getInstance(ColorSpace.CS_LINEAR_RGB);
    }

    /**
     * Generate an new object wich implemements serializable interface that allways throws IOException
     * @return an initialized instance of IOExceptionTesting
     * @throws Exception
     */
    public static Serializable getInstanceSerializedObjectIOException() throws Exception {
        return new IOExcepctionTesting();
    }

    /**
     * For testing getInstance Methods
     * @param transformation transformation String
     * @param obj testing Object
     * @param type testing type (TEST_EXCEPT=Exception triggering,TEST_RET=Returning instance)
     * @return 0 for succesfull test, 1 when NoSuchAlgorithmException triggers, 2 when NoSuchPaddingException triggers
     * 3 when NoSuchProviderException triggers
     *
     */

    public static int getInstanceTest(String transformation,String provider, Object obj,Provider providerclass,int type) {
        try {
            if (obj instanceof Cipher) {
                Cipher cipher = (Cipher) obj;
                if (provider == "" ) {
                    cipher = Cipher.getInstance(transformation);
                } else if (providerclass == null && provider != "") {
                    cipher = Cipher.getInstance(transformation,provider);
                } else if (providerclass != null) {
                    cipher = Cipher.getInstance(transformation,providerclass);
                }
                if (type == TEST_RET) {
                    if (cipher instanceof Cipher) {
                        return 0;
                    }
                }
            } else if (obj instanceof KeyGenerator) {
                KeyGenerator kg = (KeyGenerator) obj;
                if (provider == "") {
                    kg = KeyGenerator.getInstance(transformation);
                } else {
                    kg = KeyGenerator.getInstance(transformation,provider);
                }
                if (type == TEST_RET) {
                    if (kg instanceof KeyGenerator) {
                        return 0;
                    }
                }
            }
            return 0;
        } catch (NoSuchAlgorithmException e) {
            return 1;
        } catch (NoSuchPaddingException e) {
            return 2;
        } catch (NoSuchProviderException e) {
            return 3;
        }
    }

    @SuppressWarnings("unused")
    public static String getRandomBackupScheme() {
        String names[] = new String[11];

        return null;
    }

    public static boolean getRandomBoolean() {
        if (getRandomInt(5000,1) < 2500)
            return true;
        else
            return false;
    }

    public static int getRandomInt() {
        Random rnd = new Random(System.currentTimeMillis());
        return rnd.nextInt();
    }

    public static int getRandomInt(int upper, int lower) {
        return lower + (int)((upper - lower)* Math.random());
    }

    /**
     * it brings a random RoundingMode (UNNECESSARY is weighted)
     *
     * @return any RoundingMode
     */
    public static String getRandomRoundingMode() {
        int random = getRandomInt(1400,0);
        if (random <= 1400 && random > 700) {
            return ROUNDINGMODES[6];
        } else if (random <= 700 && random > 600) {
            return ROUNDINGMODES[7];
        } else if (random <= 600 && random > 500) {
            return ROUNDINGMODES[5];
        } else if (random <= 500 && random > 400) {
            return ROUNDINGMODES[4];
        } else if (random <= 400 && random > 300) {
            return ROUNDINGMODES[3];
        } else if (random <= 300 && random > 200) {
            return ROUNDINGMODES[2];
        } else if (random <= 200 && random > 100) {
            return ROUNDINGMODES[1];
        } else if (random <= 100 && random > 0) {
            return ROUNDINGMODES[0];
        } else {
            return ROUNDINGMODES[6];
        }
    }

    public static String intToString(int i) {
        if (i < 10) {
            return "00"+i;
        } else {
            if (i < 100) {
                return "0"+i;
            }
        }
        return ""+i;
    }

    public static int rndnegate() {
        if (getRandomBoolean()) {
            return 1;
        } else {
            return -1;
        }

    }
    /**
     * Returns a byte array from a string of hexadecimal digits.
     */
    public static byte[] hexFromString(String hex)
    {
        int len = hex.length();
        byte[] buf = new byte[((len + 1) / 2)];

        int i = 0, j = 0;
        if ((len % 2) == 1)
            buf[j++] = (byte) fromDigit(hex.charAt(i++));

        while (i < len)
        {
            buf[j++] = (byte) ((fromDigit(hex.charAt(i++)) << 4) |
                                fromDigit(hex.charAt(i++)));
        }
        return buf;
    }


    /**
     * Returns the number from 0 to 15 corresponding to the hex digit <i>ch</i>.
     */
    static int fromDigit(char ch)
    {
        if (ch >= '0' && ch <= '9')
            return ch - '0';
        if (ch >= 'A' && ch <= 'F')
            return ch - 'A' + 10;
        if (ch >= 'a' && ch <= 'f')
            return ch - 'a' + 10;

        throw new IllegalArgumentException("invalid hex digit '" + ch + "'");
    }


    /**
     * Compares two byte arrays for equality.
     *
     * @return true if the arrays have identical contents
     */
    public static boolean areEqual (byte[] a, byte[] b)
    {
        int aLength = a.length;
        if (aLength != b.length)
            return false;
        for (int i = 0; i < aLength; i++)
            if (a[i] != b[i])
                return false;
        return true;
    }

    /**
     * Returns a string of hexadecimal digits from a byte array. Each
     * byte is converted to 2 hex symbols.
     */
    static String toString( byte[] ba )
    {
        int length = ba.length;
        char[] buf = new char[length * 2];
        for (int i = 0, j = 0, k; i < length; )
        {
            k = ba[i++];
            buf[j++] = HEX_DIGITS[(k >>> 4) & 0x0F];
            buf[j++] = HEX_DIGITS[ k        & 0x0F];
        }
        return new String(buf);
    }

    private static final char[] HEX_DIGITS =
    {
        '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'
    };

    public static ArrayList generateRandomBigIntegerArrayList(int default_quantity) {
        return null;
    }
    
    public static void printBinary(byte data) {
        System.out.println(Integer.toHexString(data));
        System.out.println(Integer.toBinaryString(data));
    }
}

