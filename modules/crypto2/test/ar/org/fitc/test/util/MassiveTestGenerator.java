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

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;

import javax.crypto.Mac;

import ar.org.fitc.test.util.crypto.Util;

/**
 * Generates code for massive tests
 * @author Osvaldo Demo
 *
 */

@SuppressWarnings("unused")
public class MassiveTestGenerator {

    private final static String[] WEAK = { "0101 0101 0101 0101",
            "1F1F 1F1F 0E0E 0E0E", "E0E0 E0E0 F1F1 F1F1",
            "FEFE FEFE FEFE FEFE", "01FE 01FE 01FE 01FE",
            "FE01 FE01 FE01 FE01", "1FE0 1FE0 0EF1 0EF1",
            "E01F E01F F10E F10E", "01E0 01E0 01F1 01F1",
            "E001 E001 F101 F101", "1FFE 1FFE 0EFE 0EFE",
            "FE1F FE1F FE0E FE0E", "011F 011F 010E 010E",
            "1F01 1F01 0E01 0E01", "E0FE E0FE F1FE F1FE", "FEE0 FEE0 FEF1 FEF1" };

    private static final int DEFAULT_QUANTITY = 100;

    private static final int CONSTRUCTOR = 0;

    private static final int VALUEOF = 1;

    /**
     * @param args
     * @throws NoSuchAlgorithmException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // System.out.print(generateDESKeySpecisWeakTests(weak));
        // System.out.print(generateDHGenParameterSpecPrimeSizeTests(generateRandomIntArray2(10)));
        // System.out.print(generateDHGenParameterSpecExponentSizeTests(generateRandomIntArray2(10)));
        // System.out.print(generateRandomBigIntegerArray(100));
        // System.out.print(generateBigDecimalTestBigDecimalOneParameter(generateRandomBigIntegerArray(15),"testBigDecimalBigInteger","BigDecimal.BigDecimal(BigInteger
        // val)"));
        // for (int i=0 ; i< 100 ; i++)
        // System.out.print(getRandomRoundingMode()+"\n");
        // System.out.print(generateBigDecimalTestBigDecimalBigIntegerMathContext());
        // System.out.print(generateTestMethods(Util.generateRandomBigIntegerArrayList(4),"bigInt","BigInteger",Util.generateRandomIntArrayList(5),"number","int",Util.generateRandomMathContextArrayList(2),"mc","MathContext","testBigDecimalBigIntegerIntMathContext","java.math.BigDecimal(BigInteger,int,MathContext"));
        // System.out.print(generateTestMethods(Util.generateRandomIntArrayList(16),"number","int","testBigDecimalInt","java.math.BigDecimal(int)"));
        // System.out.print(generateTestMethods(Util.generateRandomIntArrayList(10),"number","int",Util.generateRandomMathContextArrayList(5),"mc","MathContext","testBigDecimalIntMathContext","java.math.BigDecimal(int)"));
        // System.out.print(generateTestMethods(Util.generateRandomLongArrayList(10),"number","long",Util.generateRandomMathContextArrayList(5),"mc","MathContext","testBigDecimalLongMathContext","java.math.BigDecimal(long,MathContext)"));
        // System.out.print(generateTestMethods(Util.generateRandomBigDecimalArrayList(10),"strings","String",Util.generateRandomMathContextArrayList(4),"mc","MathContext","testBigDecimalStringMathContext","java.math.BigDecimal(String,MathContext)"));
        // System.out.print(generateTestMethods(Util.generateRandomDoubleArrayList(10),"number","double",Util.generateRandomMathContextArrayList(5),"mc","MathContext","testBigDecimalDoubleMathContext","java.math.BigDecimal(double,MathContext)"));
        // System.out.print(generateBigDecimalTestBigDecimalCharArray(50,"testBigDecimalCharArray","java.math.BigDecimal(char[]
        // in)"));
        // System.out.print(generateBigDecimalTestBigDecimalCharArrayMathContext(10,"testBigDecimalCharArrayMathContext","java.math.BigDecimal(char[]
        // in,MathContext mc)"));
        // System.out.print(generateBigDecimalTestBigDecimalCharArrayIntInt(15,"testBigDecimalCharArrayIntInt","java.math.BigDecimal(char[]
        // in,int offset,int len)"));
        // System.out.print(generateBigDecimalTestBigDecimalCharArrayIntIntMathContext(10,"testBigDecimalCharArrayIntIntMathContext","java.math.BigDecimal(char[]
        // in,int offset,int len,MathContext mc)"));
//         System.out.print(generateBigDecimalTestLong(Util.generateRandomLongArrayList(25),"testBigDecimalLong","java.math.BigDecimal(long val)"));
        // System.out.println(generateBigDecimalTestFirstParameter(Util.generateRandomDoubleArrayList(25),"testBigDecimalDouble","java.math.BigDecimal(double
        // val)","double"));
        // System.out.println(generateBigDecimalTestFirstParameter(Util.generateRandomBigIntegerArrayList(25),"testBigDecimalString","java.math.BigDecimal(String
        // val)","String",CONSTRUCTOR));
        // System.out.println(generateBigDecimalTestFirstParameter(Util.generateRandomDoubleArrayList(25),"testBigDecimalValueOf","java.math.BigDecimal.valueOf(double
        // val)","double",VALUEOF));
        // System.out.println(generateBigDecimalTestAbs(Util.generateRandomBigDecimalArrayList(25),"testAbs","java.math.BigDecimal.abs()","String",CONSTRUCTOR));
        // System.out.println(generateBigDecimalTestAbsMathContext(Util.generateRandomBigDecimalArrayList(25),"testAbsMathContext","java.math.BigDecimal.abs(MathContext
        // mc)","String",CONSTRUCTOR));
        // System.out.println(generateBigDecimalTestNegate(Util.generateRandomBigDecimalArrayList(30),"testNegate","java.math.BigDecimal.negate()","String",CONSTRUCTOR));
        // System.out.println(generateBigDecimalTestNegateMathContext(Util.generateRandomBigDecimalArrayList(30),"testNegateMathContext","java.math.BigDecimal.negate(MathContext
        // mc)","String",CONSTRUCTOR));
        // System.out.println(generateBigDecimalTestSignum(Util.generateRandomBigDecimalArrayList(30),"testSignum","java.math.BigDecimal.signum()","String",CONSTRUCTOR));
        // System.out.println(generateBigDecimalTestPlusMathContext(Util.generateRandomBigDecimalArrayList(30),"testPlusMathContext","java.math.BigDecimal.plus(MathContext
        // mc)","String",CONSTRUCTOR));
        // System.out.println(generateBigDecimalTestPlus(Util.generateRandomBigDecimalArrayList(30),"testPlus","java.math.BigDecimal.plus()","String",CONSTRUCTOR));
        // System.out.println(generateBigDecimalTestScale(Util.generateRandomBigDecimalArrayList(30),"testScale","java.math.BigDecimal.scale()","String",CONSTRUCTOR));
        // System.out.println(generateBigDecimalTestPrecision(Util.generateRandomBigDecimalArrayList(30,Util.getRandomInt(256,32)),"testPrecision","java.math.BigDecimal.precision()","String",CONSTRUCTOR));
        // System.out.println(generateBigDecimalTestUnscaledValue(Util.generateRandomBigDecimalArrayList(30),"testUnscaledValue","java.math.BigDecimal.unscaledValue()","String",CONSTRUCTOR));
        // System.out.println(generateBigDecimalTestRoundMathContext(Util.generateRandomBigDecimalArrayList(30),"testRoundMathContext","java.math.BigDecimal.round(MathContext
        // mc)","String",CONSTRUCTOR));
        // System.out.println(generateBigDecimalTestAddBigDecimal(Util.generateRandomBigDecimalArrayList(30,64),Util.generateRandomBigDecimalArrayList(30,64),"testAddBigDecimal","java.math.BigDecimal.add(BigDecimal
        // augend)","String",CONSTRUCTOR));
         System.out.println(generateBigDecimalTestGenericOperationBigDecimal(Util.generateRandomBigDecimalArrayList(30,64),Util.generateRandomBigDecimalArrayList(30,64),"testSubtractBigDecimal","java.math.BigDecimal.subtract(BigDecimal subtrahend)","String",CONSTRUCTOR, "subtract"));
        // System.out.println(generateBigDecimalTestGenericOperationBigDecimal(Util.generateRandomBigDecimalArrayList(30,64),Util.generateRandomBigDecimalArrayList(30,64),"testMaxBigDecimal","java.math.BigDecimal.max(BigDecimal
        // val)","String",CONSTRUCTOR, "max"));
        // System.out.println(generateBigDecimalTestGenericOperationBigDecimal(Util.generateRandomBigDecimalArrayList(30,64),Util.generateRandomBigDecimalArrayList(30,64),"testMinBigDecimal","java.math.BigDecimal.min(BigDecimal
        // val)","String",CONSTRUCTOR, "min"));
        // System.out.println(generateBigDecimalTestGenericOperationBigDecimal(Util.generateRandomBigDecimalArrayList(30,64),null,"testPowInt","pow(int
        // n)","String",CONSTRUCTOR, "pow"));
        // System.out.println(generateBigDecimalTestGenericOperationBigDecimal(Util.generateRandomBigDecimalArrayList(30,64),Util.generateRandomBigDecimalArrayList(30,64),"testMultiplyBigDecimal","multiply(BigDecimal
        // multiplicand)","String",CONSTRUCTOR, "multiply"));
        // System.out.println(generateBigDecimalTestGenericOperationBigDecimal(Util.generateRandomBigDecimalArrayList(30,64),Util.generateRandomBigDecimalForDivisorArrayList(30,64,true),"testDivideBigDecimal","divide(BigDecimal
        // divisor)","String",CONSTRUCTOR, "divide"));
        // System.out.println(generateBigDecimalTestCompareTo(Util.generateRandomBigDecimalArrayList(30,64),Util.generateRandomBigDecimalArrayList(30,64),"testCompareToBigDecimal","compareTo(BigDecimal
        // val)","String",CONSTRUCTOR));
        // System.out.println(generateBigDecimalTestGenericOperationBigDecimal(Util.generateRandomBigDecimalArrayList(30,64),Util.generateRandomBigDecimalForDivisorArrayList(30,64,false),"testRemainderBigDecimal","remainder(BigDecimal
        // divisor)","String",CONSTRUCTOR, "remainder"));
        // System.out.println(generateBigDecimalTestGenericOperationBigDecimal(Util.generateRandomBigDecimalArrayList(30,64),Util.generateRandomBigDecimalForDivisorArrayList(30,64,false),"testdivideToIntegralValueBigDecimal","divideToIntegralValue(BigDecimal
        // divisor)","String",CONSTRUCTOR, "divideToIntegralValue"));
        // System.out.println(generateBigDecimalTestGenericOperationBigDecimal(Util.generateRandomBigDecimalArrayList(30,64),null,"testMovePointLeftInt","movePointLeft(int
        // n)","String",CONSTRUCTOR, "movePointLeft",false));
        // System.out.println(generateBigDecimalTestGenericOperationBigDecimal(Util.generateRandomBigDecimalArrayList(30,64),null,"testMovePointRightInt","movePointRight(int
        // n)","String",CONSTRUCTOR, "movePointRight",false));
        // System.out.println(generateBigDecimalTestGenericOperationBigDecimal(Util.generateRandomBigDecimalArrayList(30,64),Util.generateRandomBigDecimalForDivisorArrayList(30,64,false),"testDivideAndRemainderBigDecimal","divideAndRemainder(BigDecimal
        // divisor)","String",CONSTRUCTOR, "divideAndRemainder",false));
        // System.out.println(generateBigDecimalTestGenericOperationBigDecimal(Util.generateRandomBigDecimalArrayList(30,64),null,"testToString","toString()","String",CONSTRUCTOR,
        // "toString",false));
        // System.out.println(generateBigDecimalTestGenericOperationBigDecimal(Util.generateRandomBigDecimalArrayList(30,64),null,"testtoPlainString","toPlainString()","String",CONSTRUCTOR,
        // "toPlainString",false));
        // System.out.println(generateBigDecimalTestGenericOperationBigDecimal(Util.generateRandomBigDecimalArrayList(30,64),null,"testToEngineeringString","toEngineeringString()","String",CONSTRUCTOR,
        // "toEngineeringString",false));
        // System.out.println(generateBigDecimalTestGenericOperationBigDecimal(Util.generateRandomBigDecimalArrayList(30,64),Util.generateRandomBigDecimalForDivisorArrayList(30,64,false),"testHashCode","hashCode()","String",CONSTRUCTOR,
        // "hashCode"));
        // System.out.println(generateBigDecimalTestGenericOperationBigDecimal(Util.generateRandomBigDecimalArrayList(30,64),Util.generateRandomBigDecimalForDivisorArrayList(30,64,false),"testEqualsObject","equals(Object
        // x)","String",CONSTRUCTOR, "equals"));
        // System.out.println(generateBigDecimalTestGenericOperationBigDecimal(Util.generateRandomBigDecimalArrayList(30,64),Util.generateRandomBigDecimalArrayList(30,64),"testSubtractBigDecimal","java.math.BigDecimal.subtract(BigDecimal
        // subtrahend)","String",CONSTRUCTOR, "subtract"));
        // System.out.println(generateBigDecimalTestGenericOperationBigDecimal(Util.generateRandomBigDecimalArrayList(30,64),Util.generateRandomBigDecimalArrayList(30,64),"testAddBigDecimalMathContext","add(BigDecimal
        // augend,MathContext mc)","String",CONSTRUCTOR, "add",true));
        // System.out.println(generateBigDecimalTestGenericOperationBigDecimal(Util.generateRandomBigDecimalArrayList(30,64),Util.generateRandomBigDecimalArrayList(30,64),"testMultiplyBigDecimalMathContext","multiply(BigDecimal
        // multiplicand,MathContext mc)","String",CONSTRUCTOR,
        // "multiply",true));
        // System.out.println(generateBigDecimalTestGenericOperationBigDecimal(Util.generateRandomBigDecimalArrayList(30,64),Util.generateRandomBigDecimalForDivisorArrayList(30,64,false),"testRemainderBigDecimalMathContext","remainder(BigDecimal
        // divisor,MathContext mc)","String",CONSTRUCTOR, "remainder",true));
        // System.out.println(generateMacTests(30,"testUpdateByteArray","update(byte[]
        // input)",MacGenerator.byteArrayUpd));
        // System.out.println(generateMacTests(30,"testUpdateByte","update(byte
        // input)",MacGenerator.byteUpd,"HmacMD5","BC"));
        // System.out.println(generateMacTests(30,"testUpdateByte","update(byte
        // input)",MacGenerator.byteUpd,"Hmac335","BC"));
        // System.out.println(generateMacTests(30,"testUpdateByteArrayIntInt","update(byte[]
        // input,int offset,int
        // len)",MacGenerator.byteArrayIntIntUpd,"HmacMD5","BC",true));
        // System.out.println(generateMacTests(30,"testDoFinalByteInt","doFinal(Byte[]
        // output,int
        // outOffset)",MacGenerator.BYTEARRAYUPD,"HmacMD5","BC",true,0,MacGenerator.DFBYTEINT));
        // System.out.println(Security.getProvider("CryptixCrypto"));
//         System.out.println(generateMacTests(30, "testDoFinalByteArrayInt",
//         "javax.crypto.Mac.doFinal(Byte[],int)",
//         MacGenerator.BYTEARRAYUPD, "HmacMD5", "BC", true, 5,
//         MacGenerator.DFBYTEINT));
//        System.out.println(generateMacTests(30, "testUpdateByte",
//              "javax.crypto.Mac.update(byte)",
//              MacGenerator.BYTEUPD, "HmacMD5", "BC", true, 2,
//              MacGenerator.DF));
    }

    private static String generateDESKeySpecisWeakTests(String[] keyarray) {
        return generateDESKeySpecisWeakTests(keyarray, 0);
    }

    private static String generateDESKeySpecisWeakTests(String[] keyarray,
            int startAt) {
        int offset = 0;
        StringBuffer sb = new StringBuffer();
        StringBuffer doc = new StringBuffer();
        sb
                .append("/*\n * Test method for 'javax.crypto.spec.DESKeySpec.isWeak(byte[] key, int offset)'\n */\n\n");

        sb.append("private static String[] weak = {");
        for (int i = 0; i < keyarray.length; i++) {
            if (i != 0) {
                sb.append("\t\t\t\t");
            }

            sb.append("\"" + keyarray[i] + "\"");

            if (i != keyarray.length - 1) {
                sb.append(",\n");
            }

        }
        sb.append("};\n\n");

        doc.append("\n ------ Documentación Automática ------\n");

        for (int i = 0; i < keyarray.length; i++) {
            if (startAt == 0) {
                offset = i + 1;
            } else {
                offset = i + startAt;
            }
            doc.append("\n*'''testIsWeak" + Util.intToString(offset)
                    + "():''' Se prueba la clave \"" + WEAK[i]
                    + "\" ''Debe devolver: ''true''");
            sb.append("\rpublic final void testIsWeak"
                    + Util.intToString(offset) + "() {");
            sb.append("\r  offset = 0;");
            sb.append("\r  try {");
            sb
                    .append("\r\t assertTrue(\"Must be a week key\",DESKeySpec.isWeak(Hex.decode(weak["
                            + i + "]),offset));");
            sb.append("\r  } catch (Throwable e) {");
            sb.append("\r\t fail(msgNoException+e);");
            sb.append("\r  }\n}\n");
        }
        return sb.toString() + doc.toString();

    }

    @SuppressWarnings("unused")
    private static String generateDHGenParameterSpecPrimeSizeTests(
            int[] primesize) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        sb
                .append("\n--------- Tests for DHGenParameterSpec.getPrimeSize() -----------");
        sb.append("\n\nprivate static int[] primeSizeTests = {");
        for (int i = 0; i < primesize.length; i++) {
            if (i != 0) {
                sb.append("\t\t\t\t");
            }

            sb.append(primesize[i]);

            if (i != primesize.length - 1) {
                sb.append(",\n");
            }
        }
        sb.append("};\n\n");
        sb
                .append("/*\n * Test method for 'javax.crypto.spec.DHGenParameterSpec.getPrimeSize(int primeSize)'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        for (int i = 0; i < primesize.length; i++) {
            doc.append("\n*'''testGetPrimeSize" + Util.intToString(i + 1)
                    + "():''' ''Debe devolver: " + primesize[i] + "''");
            sb.append("\rpublic final void testGetPrimeSize"
                    + Util.intToString(i + 1) + "() {");
            sb.append("\r  primeSize = primeSizeTests[" + i + "];");
            sb.append("\r  testDHGenParameterSpec001();");
            sb
                    .append("\r  assertEquals(\"Not the same number\",primeSize,dhgps.getPrimeSize());");
            sb.append("\r}\n");
        }
        return sb.toString() + doc.toString();
    }

    @SuppressWarnings("unused")
    private static String generateDHGenParameterSpecExponentSizeTests(
            int[] exponentsize) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        sb
                .append("\n--------- Tests for DHGenParameterSpec.getExponentSize() -----------");
        sb.append("\n\nprivate static int[] exponentSizeTests = {");
        for (int i = 0; i < exponentsize.length; i++) {
            if (i != 0) {
                sb.append("\t\t\t\t");
            }

            sb.append(exponentsize[i]);

            if (i != exponentsize.length - 1) {
                sb.append(",\n");
            }
        }
        sb.append("};\n\n");
        sb
                .append("\n\n/*\n * Test method for 'javax.crypto.spec.DESKeySpec.isWeak(byte[] key, int offset)'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        for (int i = 0; i < exponentsize.length; i++) {
            doc.append("\n*'''testGetExponentSize" + Util.intToString(i + 1)
                    + "():''' ''Debe devolver: " + exponentsize[i] + "''");
            sb.append("\rpublic final void testGetExponentSize"
                    + Util.intToString(i + 1) + "() {");
            sb.append("\r  exponentSize = exponentSizeTests[" + i + "];");
            sb.append("\r  testDHGenParameterSpec001();");
            sb
                    .append("\r  assertEquals(\"Not the same number\",exponentSize,dhgps.getExponentSize());");
            sb.append("\r}\n");
        }
        return sb.toString() + doc.toString();
    }

    @SuppressWarnings("unused")
    private static String generateBigDecimalTestBigDecimalOneParameter(
            ArrayList numbers, String testmethodname, String testclass) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        for (int i = 0; i < numbers.size(); i++) {
            doc.append("\n*'''" + testmethodname + Util.intToString(i + 1)
                    + "():''' Se prueba con BigInteger: " + numbers.get(i) + ""
                    + " ''Debe devolver una instancia de BigDecimal''");
            sb.append("\rpublic final void " + testmethodname
                    + Util.intToString(i + 1) + "() {");
            sb.append("\r  bigInt = new BigInteger(\"" + numbers.get(i)
                    + "\");");
            sb.append("\r  bigDec = new BigDecimal(bigInt);");
            sb
                    .append("\r  assertTrue(msgNotInstance+\"BigDecimal\",bigDec instanceof BigDecimal);");
            sb.append("\r}\n");
        }
        return sb.toString() + doc.toString();
    }

    private static String generateBigDecimalTestBigDecimalBigIntegerMathContext() {
        return generateBigDecimalTestBigDecimalBigIntegerMathContext(Util
                .generateRandomBigIntegerArrayList(DEFAULT_QUANTITY),
                "testBigDecimalBigIntegerMathContext",
                "java.math.BigDecimal.BigDecimal(BigInteger,MathContext)");
    }

    private static String generateBigDecimalTestBigDecimalBigIntegerMathContext(
            ArrayList numbers, String testmethodname, String testclass) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        String mathcontext = new String();
        sb.append("\n--------- Tests for " + testclass + " -----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        for (int i = 0; i < numbers.size(); i++) {
            mathcontext = "\"precision=" + Util.getRandomInt(99999, 2000)
                    + " roundingMode=" + Util.getRandomRoundingMode() + "\"";
            doc.append("\n*'''" + testmethodname + Util.intToString(i + 1)
                    + "():''' Se prueba con BigInteger: " + numbers.get(i)
                    + " y un MathContext: " + mathcontext
                    + " ''Debe devolver una instancia de BigDecimal''");
            sb.append("\rpublic final void " + testmethodname
                    + Util.intToString(i + 1) + "() {");
            sb.append("\r  bigInt = new BigInteger(\"" + numbers.get(i)
                    + "\");");
            sb.append("\r  mc = new MathContext(" + mathcontext + ");");
            sb.append("\r  bigDec = new BigDecimal(bigInt,mc);");
            sb
                    .append("\r  assertTrue(msgNotInstance+\"BigDecimal\",bigDec instanceof BigDecimal);");
            sb.append("\r}\n");
        }

        // ArithmeticException testing
        String temp = new String();
        temp = "" + Util.getRandomInt(Integer.MAX_VALUE, 100000000);
        doc.append("\n*'''" + testmethodname
                + Util.intToString(numbers.size() + 1)
                + "():''' Se prueba con BigInteger: " + temp
                + " y un MathContext: " + mathcontext
                + " ''Debe lanzar ArithmeticException''");
        sb.append("\rpublic final void " + testmethodname
                + Util.intToString(numbers.size() + 1) + "() {");
        sb.append("\r  bigInt = new BigInteger(\"" + temp + "\");");
        sb.append("\r  mc = new MathContext(\"precision="
                + Util.getRandomInt(temp.length() - 1, 1)
                + " roundingMode=UNNECESSARY\");");
        sb.append("\r  try {");
        sb.append("\r    bigDec = new BigDecimal(bigInt,mc);");
        sb.append("\r    fail(msgRaise+\"ArithmeticException\");");
        sb.append("\r  } catch (ArithmeticException e) {");
        sb.append("\r  } catch (Throwable e) {");
        sb.append("\r    fail(msgRaise+\"ArithmeticException\");");
        sb.append("\r  }\n}\n");
        return sb.toString() + doc.toString();
    }

    /**
     * Generates compilable java code with the given directives
     *
     * @param param1values
     *            an Arraylist of values for the 1st parameter
     * @param param1
     *            the name of the 1st parameter
     * @param param1type
     * @param param3values
     *            an Arraylist of values for the 3rd parameter
     * @param param3
     *            the name of the 3rd parameter
     * @param param3type
     * @param testmethodname
     *            generic test method name
     * @param testclass
     *            the name of class and method under test
     * @return java code
     */
    @SuppressWarnings("unused")
    private static String generateTestMethods(ArrayList param1values,
            String param1, String param1type,
            // ArrayList param2values,String param2,String param2type,
            ArrayList param3values, String param3, String param3type,
            String testmethodname, String testclass) {

        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();

        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Formato wiki ------\n");

        int numerator = 1;
        for (int i = 0; i < param1values.size(); i++) {
            // for (int j=0 ; j < param2values.size() ; j++) {
            for (int k = 0; k < param3values.size(); k++) {
                doc.append("\n*'''" + testmethodname
                        + Util.intToString(numerator) + "():''' Se prueba con "
                        + param1type + ": " + param1values.get(i));
                /*
                 * if(param2 != "") { doc.append(","+param2type+":
                 * "+param2values.get(j)); }
                 */
                if (param3 != "") {
                    doc.append("," + param3type + ": " + param3values.get(k));
                }
                doc.append(" ''Debe devolver una instancia de BigDecimal''");
                sb.append("\rpublic final void " + testmethodname
                        + Util.intToString(numerator) + "() {");
                // sb.append("\r "+param1+"= new
                // "+param1type+"(\""+param1values.get(i).toString()+ "\");");
                sb.append("\r  " + param3 + "= new " + param3type + "(\""
                        + param3values.get(k) + "\");");
                sb.append("\r  bigDec = new BigDecimal(" + param1values.get(i));
                /*
                 * if(param2 != "") { sb.append(","+param2values.get(j)); }
                 */
                if (param3 != "") {
                    sb.append("," + param3 + ");");
                }
                sb
                        .append("\r  assertTrue(msgNotInstance+\"BigDecimal\",bigDec instanceof BigDecimal);");
                sb.append("\r}\n");
                /*
                 * numerator++; }
                 */
                numerator++;
            }
        }
        return sb.toString() + doc.toString();
    }

    @SuppressWarnings("unused")
    private static String generateBigDecimalTestBigDecimalCharArray(
            int quantity, String testmethodname, String testclass) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        for (int i = 0; i < quantity; i++) {
            int size = Util.getRandomInt(512, 128) / 8;
            char[] chararray = new char[size];
            chararray = Util.generateRandomCharArray(size);
            doc.append("\n*'''" + testmethodname + Util.intToString(i + 1)
                    + "():''' Se prueba con char[]: "
                    + java.util.Arrays.toString(chararray)
                    + " ''Debe devolver una instancia de BigDecimal''");
            sb.append("\rpublic final void " + testmethodname
                    + Util.intToString(i + 1) + "() {");
            sb.append("\r  "
                    + Util.generateCharArrayDeclaration(chararray, "array"));
            sb.append("\r  bigDec = new BigDecimal(array);");
            sb
                    .append("\r  assertTrue(msgNotInstance+\"BigDecimal\",bigDec instanceof BigDecimal);");
            sb.append("\r}\n");
        }
        return sb.toString() + doc.toString();
    }

    @SuppressWarnings("unused")
    private static String generateBigDecimalTestBigDecimalCharArrayMathContext(
            int quantity, String testmethodname, String testclass) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        int numerator = 1;
        ArrayList lista = Util.generateRandomMathContextArrayList(quantity / 3);
        for (int i = 0; i < quantity; i++) {
            for (int j = 0; j < lista.size(); j++) {
                int size = Util.getRandomInt(512, 128) / 8;
                char[] chararray = new char[size];
                chararray = Util.generateRandomCharArray(size);
                doc.append("\n*'''" + testmethodname
                        + Util.intToString(numerator)
                        + "():''' Se prueba con char[]: "
                        + java.util.Arrays.toString(chararray)
                        + " y MathContext:\"" + lista.get(j)
                        + "\" ''Debe devolver una instancia de BigDecimal''");
                sb.append("\rpublic final void " + testmethodname
                        + Util.intToString(numerator) + "() {");
                sb
                        .append("\r  "
                                + Util.generateCharArrayDeclaration(chararray,
                                        "array"));
                sb
                        .append("\r  mc = new MathContext(\"" + lista.get(j)
                                + "\");");
                sb.append("\r  bigDec = new BigDecimal(array,mc);");
                sb
                        .append("\r  assertTrue(msgNotInstance+\"BigDecimal\",bigDec instanceof BigDecimal);");
                sb.append("\r}\n");
                numerator++;
            }
            numerator++;
        }
        return sb.toString() + doc.toString();
    }

    @SuppressWarnings("unused")
    private static String generateBigDecimalTestBigDecimalCharArrayIntInt(
            int quantity, String testmethodname, String testclass) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");

        int numerator = 1;
        String exception = "NumberFormatException";
        int int1size = quantity / 8;
        int int2size = quantity / 7;

        for (int i = 0; i < quantity; i++) {
            for (int j = 0; j < int1size; j++) {
                for (int k = 0; k < int2size; k++) {
                    int size = Util.getRandomInt(512, 128) / 8;
                    int int1 = Util.getRandomInt(size + size / 2, 0);
                    int int2 = Util.getRandomInt(size + size / 2, size / 4);
                    char[] chararray = new char[size];
                    chararray = Util.generateRandomCharArray(size);

                    doc.append("\n*'''" + testmethodname
                            + Util.intToString(numerator)
                            + "():''' Se prueba con char[]: "
                            + java.util.Arrays.toString(chararray)
                            + ", int(offset):\"" + int1 + "\" e int(len):\""
                            + int2 + "\" ");
                    if (int1 + int2 > size) {
                        doc.append("''Debe lanzar " + exception + "''");
                    } else {
                        doc
                                .append("''Debe devolver una instancia de BigDecimal''");
                    }
                    sb.append("\rpublic final void " + testmethodname
                            + Util.intToString(numerator) + "() {");
                    sb.append("\r  "
                            + Util.generateCharArrayDeclaration(chararray,
                                    "array"));
                    sb.append("\r  int offset = " + int1 + ";");
                    sb.append("\r  int len = " + int2 + ";");

                    if (int1 + int2 > size) {
                        sb.append("\r  try {");
                    }
                    sb.append("\r  " + setspace(int1 + int2, size)
                            + "bigDec = new BigDecimal(array,offset,len);");

                    if (int1 + int2 > size) {
                        sb.append("\r     fail(msgRaise+\"" + exception
                                + "\");");
                        sb.append("\r  } catch (" + exception + " e) {");
                        sb.append("\r  }");
                    } else {
                        sb
                                .append("\r  assertTrue(msgNotInstance+\"BigDecimal\",bigDec instanceof BigDecimal);");
                    }
                    sb.append("\r}\n");
                    numerator++;
                }
            }
        }
        return sb.toString() + doc.toString();
    }

    private static String setspace(int i, int size) {
        if (i > size) {
            return "   ";
        } else {
            return "";
        }
    }

    @SuppressWarnings("unused")
    private static String generateBigDecimalTestBigDecimalCharArrayIntIntMathContext(
            int quantity, String testmethodname, String testclass) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        doc.append("\n==" + testclass + "==");

        int numerator = 1;
        String exception = "NumberFormatException";
        String exception2 = "ArithmeticException";
        int int1size = quantity / 5;
        int int2size = quantity / 6;
        int mcsize = quantity / 5;

        for (int i = 0; i < quantity; i++) {
            for (int j = 0; j < int1size; j++) {
                for (int k = 0; k < int2size; k++) {
                    for (int l = 0; l < mcsize; l++) {
                        int size = Util.getRandomInt(512, 128) / 8;
                        int int1 = Util.getRandomInt(size + size / 5, 0);
                        int int2 = Util.getRandomInt(size + size / 4, 1);
                        // String roundingmode = "UNNECESSARY";
                        String roundingmode = Util.getRandomRoundingMode();
                        int precision = Util.getRandomInt(size * 2, 1);

                        char[] chararray = new char[size];
                        chararray = Util.generateRandomCharArray(size);

                        doc.append("\n*'''" + testmethodname
                                + Util.intToString(numerator)
                                + "():''' Se prueba con char[]: "
                                + java.util.Arrays.toString(chararray)
                                + ", int(offset):\"" + int1 + "\", int(len):\""
                                + int2 + "\" y " + "MathContext: \"precision="
                                + precision + "roundingMode=" + roundingmode
                                + "d\"");
                        if (int1 + int2 > size) {
                            doc.append("''Debe lanzar " + exception + "''");
                        } else if (precision < int1 + int2
                                && roundingmode == "UNNECESSARY") {
                            doc.append("''Debe lanzar " + exception2 + "''");
                        } else {
                            doc
                                    .append("''Debe devolver una instancia de BigDecimal''");
                        }
                        sb.append("\rpublic final void " + testmethodname
                                + Util.intToString(numerator) + "() {");
                        sb.append("\r  "
                                + Util.generateCharArrayDeclaration(chararray,
                                        "array"));
                        sb.append("\r  int offset = " + int1 + ";");
                        sb.append("\r  int len = " + int2 + ";");
                        sb
                                .append("\r  MathContext mc = new MathContext(\"precision="
                                        + precision
                                        + " roundingMode="
                                        + roundingmode + "\");");

                        if (int1 + int2 > size
                                || (precision < int1 + int2 && roundingmode == "UNNECESSARY")) {
                            sb.append("\r  try {");
                        }
                        sb
                                .append("\r  "
                                        + setspace(int1 + int2, size)
                                        + "bigDec = new BigDecimal(array,offset,len,mc);");

                        if (int1 + int2 > size) {
                            sb.append("\r     fail(msgRaise+\"" + exception
                                    + "\");");
                            sb.append("\r  } catch (" + exception + " e) {");
                            sb.append("\r  }");
                        } else if (precision < int1 + int2
                                && roundingmode == "UNNECESSARY") {
                            sb.append("\r     fail(msgRaise+\"" + exception2
                                    + "\");");
                            sb.append("\r  } catch (" + exception2 + " e) {");
                            sb.append("\r  }");
                        } else {
                            sb
                                    .append("\r  assertTrue(msgNotInstance+\"BigDecimal\",bigDec instanceof BigDecimal);");
                        }

                        sb.append("\r}\n");
                        numerator++;
                    }
                }
            }
        }
        return sb.toString() + doc.toString();
    }

    /*
     * Metodo más genérico para constructores con un parámetro
     */
    @SuppressWarnings("unused")
    private static String generateBigDecimalTestFirstParameter(
            ArrayList numbers, String testmethodname, String testclass,
            String type, int createmethod) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        doc.append("\n ==" + testclass + "==\n");
        for (int i = 0; i < numbers.size(); i++) {
            doc.append("\n*'''" + testmethodname + Util.intToString(i + 1)
                    + "():''' Se prueba con " + type + ": " + numbers.get(i)
                    + "" + " ''Debe devolver una instancia de BigDecimal''");
            sb.append("\rpublic final void " + testmethodname
                    + Util.intToString(i + 1) + "() {");
            sb.append("\r  " + type + " number = " + ifstring(type)
                    + numbers.get(i) + ifstring(type) + ";");
            sb.append("\r  bigDec = ");

            if (createmethod == CONSTRUCTOR) {
                sb.append("new BigDecimal(");
            } else if (createmethod == VALUEOF) {
                sb.append("BigDecimal.valueOf(");
            }

            sb.append("number);");
            sb
                    .append("\r  assertTrue(msgNotInstance+\"BigDecimal\",bigDec instanceof BigDecimal);");
            if (type == "double") {
                sb.append("\r  assertEquals(msgNotSame,Util.DoubletoString("
                        + numbers.get(i) + "),bigDec.toString());");
            } else {
                sb.append("\r  assertEquals(msgNotSame,\"" + numbers.get(i)
                        + "\",bigDec.toString());");
            }

            sb.append("\r}\n");
        }
        return sb.toString() + doc.toString();
    }

    private static String ifstring(String type) {
        if (type == "String") {
            return "\"";
        } else {
            return "";
        }
    }

    @SuppressWarnings("unused")
    private static String generateBigDecimalTestAbs(ArrayList numbers,
            String testmethodname, String testclass, String type,
            int createmethod) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        BigDecimal bi;
        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        doc.append("\n ==" + testclass + "==\n");
        for (int i = 0; i < numbers.size(); i++) {
            bi = new BigDecimal("" + numbers.get(i));
            doc.append("\n*'''" + testmethodname + Util.intToString(i + 1)
                    + "():''' Se prueba con el número: " + numbers.get(i) + ""
                    + " ''Debe devolver el valor absoluto''");
            sb.append("\rpublic final void " + testmethodname
                    + Util.intToString(i + 1) + "() {");
            sb.append("\r  " + type + " number = " + ifstring(type)
                    + numbers.get(i) + ifstring(type) + ";");
            sb.append("\r  bigDec = ");

            if (createmethod == CONSTRUCTOR) {
                sb.append("new BigDecimal(");
            } else if (createmethod == VALUEOF) {
                sb.append("BigDecimal.valueOf(");
            }

            sb.append("number);");

            if (type == "double") {
                sb.append("\r  assertEquals(msgNotSame,Util.DoubletoString("
                        + numbers.get(i) + "),bigDec.toString());");
            } else {
                sb.append("\r  assertEquals(msgNotSame,\""
                        + bi.abs().toString() + "\",bigDec.abs().toString());");
            }

            sb.append("\r}\n");
        }
        return sb.toString() + doc.toString();
    }

    @SuppressWarnings("unused")
    private static String generateBigDecimalTestSignum(ArrayList numbers,
            String testmethodname, String testclass, String type,
            int createmethod) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        BigDecimal bi;
        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        doc.append("\n ==" + testclass + "==\n");
        for (int i = 0; i < numbers.size(); i++) {
            bi = new BigDecimal("" + numbers.get(i));
            doc.append("\n*'''" + testmethodname + Util.intToString(i + 1)
                    + "():''' Se prueba con " + type + ": " + numbers.get(i)
                    + "" + " ''Debe devolver: " + bi.signum() + "''");
            sb.append("\rpublic final void " + testmethodname
                    + Util.intToString(i + 1) + "() {");
            sb.append("\r  " + type + " number = " + ifstring(type)
                    + numbers.get(i) + ifstring(type) + ";");
            sb.append("\r  bigDec = ");
            if (createmethod == CONSTRUCTOR) {
                sb.append("new BigDecimal(");
            } else if (createmethod == VALUEOF) {
                sb.append("BigDecimal.valueOf(");
            }
            sb.append("number);");
            sb.append("\r  if (log) { System.out.println(\"" + testmethodname
                    + Util.intToString(i + 1)
                    + "-> signum = \"+bigDec.signum()); }");
            sb.append("\r  assertEquals(msgNotSame," + bi.signum()
                    + ",bigDec.signum());");
            sb
                    .append("\r  if (log) { System.out.println(\"---Test PASSED---\"); }");
            sb.append("\r}\n");
        }
        return sb.toString() + doc.toString();
    }

    @SuppressWarnings("unused")
    private static String generateBigDecimalTestAbsMathContext(
            ArrayList numbers, String testmethodname, String testclass,
            String type, int createmethod) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        BigDecimal bi;
        MathContext mc;
        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        doc.append("\n ==" + testclass + "==\n");
        for (int i = 0; i < numbers.size(); i++) {
            String mathcontext = "precision=" + Util.getRandomInt(99999, 2000)
                    + " roundingMode=" + Util.getRandomRoundingMode();
            mc = new MathContext(mathcontext);
            bi = new BigDecimal("" + numbers.get(i));
            doc.append("\n*'''" + testmethodname + Util.intToString(i + 1)
                    + "():''' Se prueba con el número: " + numbers.get(i)
                    + " y mc: " + mathcontext
                    + " ''Debe devolver el valor absoluto''");
            sb.append("\rpublic final void " + testmethodname
                    + Util.intToString(i + 1) + "() {");
            sb.append("\r  MathContext mc = new MathContext(\"" + mathcontext
                    + "\");");
            sb.append("\r  " + type + " number = " + ifstring(type)
                    + numbers.get(i) + ifstring(type) + ";");
            sb.append("\r  bigDec = ");

            if (createmethod == CONSTRUCTOR) {
                sb.append("new BigDecimal(");
            } else if (createmethod == VALUEOF) {
                sb.append("BigDecimal.valueOf(");
            }

            sb.append("number,mc);");

            sb.append("\r  if (log) { ");
            sb.append("\r\t\tSystem.out.println(\"" + testmethodname
                    + Util.intToString(i + 1) + "\");");
            sb.append("\r\t\tSystem.out.println(\"-> expected result: "
                    + bi.abs(mc) + "\");");
            sb
                    .append("\r\t\tSystem.out.println(\"-> actual result:\"+ bigDec.abs(mc));");
            sb
                    .append("\r\t\tSystem.out.println(\"-> mc: \"+mc.toString());\r   }");
            sb.append("\r  assertEquals(msgNotSame,\"" + bi.abs(mc).toString()
                    + "\",bigDec.abs(mc).toString());");
            sb
                    .append("\r  if (log) { System.out.println(\"---Test PASSED---\"); }");
            sb.append("\r}\n");
        }
        return sb.toString() + doc.toString();
    }

    @SuppressWarnings("unused")
    private static String generateBigDecimalTestNegateMathContext(
            ArrayList numbers, String testmethodname, String testclass,
            String type, int createmethod) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        BigDecimal bi;
        MathContext mc;
        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        doc.append("\n ==" + testclass + "==\n");
        for (int i = 0; i < numbers.size(); i++) {
            int precision = Util.getRandomInt(99999, 0);
            // int precision = Util.getRandomInt(8,1);
            String mathcontext = "precision=" + Util.getRandomInt(99999, 2000)
                    + " roundingMode=" + Util.getRandomRoundingMode();
            mc = new MathContext(mathcontext);
            String bigdc = "" + numbers.get(i);
            doc.append("\n*'''" + testmethodname + Util.intToString(i + 1)
                    + "():''' Se prueba con el número: " + numbers.get(i)
                    + " y mc: " + mathcontext);
            bi = new BigDecimal(bigdc);
            if (precision < bigdc.length()) {
                doc.append(" ''Debe lanzar ArithmeticException''");
            } else {
                doc.append(" ''Debe devolver: " + bi.negate(mc) + "''");
            }

            sb.append("\rpublic final void " + testmethodname
                    + Util.intToString(i + 1) + "() {");
            if (precision < bigdc.length()) {
                sb
                        .append("\r  ArithmeticException exp = new ArithmeticException();");
            }
            sb.append("\r  MathContext mc = new MathContext(\"" + mathcontext
                    + "\");");
            sb.append("\r  " + type + " number = " + ifstring(type)
                    + numbers.get(i) + ifstring(type) + ";");

            if (precision < bigdc.length()) {
                sb.append("\r try {");
            }
            sb.append("\r  if (log) { System.out.println(\"" + testmethodname
                    + Util.intToString(i + 1) + "\"); }");
            sb.append("\r  bigDec = ");

            if (createmethod == CONSTRUCTOR) {
                sb.append("new BigDecimal(");
            } else if (createmethod == VALUEOF) {
                sb.append("BigDecimal.valueOf(");
            }

            sb.append("number,mc);");

            if (precision < bigdc.length()) {
                sb.append("\r  System.out.println(\"---Test FAILED---\");");
                sb.append("\r  fail(msgRaise+exp);");
            }

            if (precision < bigdc.length()) {
                sb.append("\r } catch (ArithmeticException e) {");
            }

            sb.append("\r  if (log) { ");
            if (precision < bigdc.length()) {
                sb
                        .append("\r\t\tSystem.out.println(\"-> expected exception: \"+ exp);");
                sb.append("\r\t\tSystem.out.println(\"-> actual: \"+ e);");
                sb
                        .append("\r\t\tSystem.out.println(\"---Test PASSED---\"); \r}");
            } else {
                sb.append("\r\t\tSystem.out.println(\"-> expected result: "
                        + bi.negate(mc).toString() + "\");");
                sb
                        .append("\r\t\tSystem.out.println(\"-> actual result: \"+ bigDec.negate(mc));");
                sb
                        .append("\r\t\tSystem.out.println(\"-> mc: \"+mc.toString());\r   }");
            }

            if (precision >= bigdc.length()) {
                sb.append("\r  assertEquals(msgNotSame,\""
                        + bi.negate(mc).toString()
                        + "\",bigDec.negate(mc).toString());");
                sb
                        .append("\r  if (log) { System.out.println(\"---Test PASSED---\"); }");
            } else {
                sb.append("\r  }");
            }

            sb.append("\r}\n");
        }
        return sb.toString() + doc.toString();
    }

    @SuppressWarnings("unused")
    private static String generateBigDecimalTestNegate(ArrayList numbers,
            String testmethodname, String testclass, String type,
            int createmethod) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        BigDecimal bi;
        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        doc.append("\n ==" + testclass + "==\n");
        for (int i = 0; i < numbers.size(); i++) {
            bi = new BigDecimal("" + numbers.get(i));
            doc.append("\n*'''" + testmethodname + Util.intToString(i + 1)
                    + "():''' Se prueba con el número: " + numbers.get(i));
            doc.append(" ''Debe devolver: " + bi.negate().toString() + "''");

            sb.append("\rpublic final void " + testmethodname
                    + Util.intToString(i + 1) + "() {");
            sb.append("\r  BigDecimal bi = new BigDecimal(\"" + numbers.get(i)
                    + "\");");
            sb.append("\r  " + type + " number = " + ifstring(type)
                    + numbers.get(i) + ifstring(type) + ";");

            sb.append("\r  if (log) { System.out.println(\"" + testmethodname
                    + Util.intToString(i + 1) + "\"); }");
            sb.append("\r  bigDec = ");

            if (createmethod == CONSTRUCTOR) {
                sb.append("new BigDecimal(");
            } else if (createmethod == VALUEOF) {
                sb.append("BigDecimal.valueOf(");
            }
            sb.append("number);");
            sb.append("\r  if (log) { ");
            sb
                    .append("\r\t\tSystem.out.println(\"-> expected result: \"+ bi.negate());");
            sb
                    .append("\r\t\tSystem.out.println(\"-> actual result: \"+ bigDec.negate());");
            sb
                    .append("\r\t\tSystem.out.println(\"-> mc: \"+mc.toString());\r   }");
            sb.append("\r  assertEquals(msgNotSame,\"" + bi.negate().toString()
                    + "\",bigDec.negate().toString());");
            sb
                    .append("\r  if (log) { System.out.println(\"---Test PASSED---\"); }");
            sb.append("\r  }");
        }
        return sb.toString() + doc.toString();
    }

    private static String generateBigDecimalTestPlusMathContext(
            ArrayList numbers, String testmethodname, String testclass,
            String type, int createmethod) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        BigDecimal bi;
        MathContext mc;
        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        doc.append("\n ==" + testclass + "==\n");
        for (int i = 0; i < numbers.size(); i++) {
            int precision = Util.getRandomInt(99999, 0);
            // int precision = Util.getRandomInt(8,0);
            String mathcontext = "precision=" + precision + " roundingMode="
                    + Util.getRandomRoundingMode();
            // String mathcontext = "\"precision="+precision +"
            // roundingMode=UNNECESSARY\"";
            mc = new MathContext(mathcontext);
            String bigdc = "" + numbers.get(i);
            bi = new BigDecimal(bigdc);
            doc.append("\n*'''" + testmethodname + Util.intToString(i + 1)
                    + "():''' Se prueba con el número: " + numbers.get(i)
                    + " y mc: " + mathcontext);

            if (precision < bigdc.length()) {
                doc.append(" ''Debe lanzar ArithmeticException''");
            } else {
                doc.append(" ''Debe devolver: " + bi.plus(mc) + "''");
            }

            sb.append("\rpublic final void " + testmethodname
                    + Util.intToString(i + 1) + "() {");
            if (precision < bigdc.length()) {
                sb
                        .append("\r  ArithmeticException exp = new ArithmeticException();");
            }
            sb.append("\r  MathContext mc = new MathContext(\"" + mathcontext
                    + "\");");
            sb.append("\r  " + type + " number = " + ifstring(type)
                    + numbers.get(i) + ifstring(type) + ";");

            if (precision < bigdc.length()) {
                sb.append("\r try {");
            }
            sb.append("\r  if (log) { System.out.println(\"" + testmethodname
                    + Util.intToString(i + 1) + "\"); }");
            sb.append("\r  bigDec = ");

            if (createmethod == CONSTRUCTOR) {
                sb.append("new BigDecimal(");
            } else if (createmethod == VALUEOF) {
                sb.append("BigDecimal.valueOf(");
            }

            sb.append("number,mc);");

            if (precision < bigdc.length()) {
                sb.append("\r  System.out.println(\"---Test FAILED---\");");
                sb.append("\r  fail(msgRaise+exp);");
            }

            if (precision < bigdc.length()) {
                sb.append("\r } catch (ArithmeticException e) {");
            }

            sb.append("\r  if (log) { ");
            if (precision < bigdc.length()) {
                sb
                        .append("\r\t\tSystem.out.println(\"-> expected exception: \"+ exp);");
                sb.append("\r\t\tSystem.out.println(\"-> actual: \"+ e);");
                sb
                        .append("\r\t\tSystem.out.println(\"---Test PASSED---\"); \r}");
            } else {
                sb.append("\r\t\tSystem.out.println(\"-> expected result: "
                        + bi.plus(mc).toString() + "\");");
                sb
                        .append("\r\t\tSystem.out.println(\"-> actual result: \"+ bigDec.plus(mc));");
                sb
                        .append("\r\t\tSystem.out.println(\"-> mc: \"+mc.toString());\r   }");
            }

            if (precision >= bigdc.length()) {
                sb.append("\r  assertEquals(msgNotSame,\""
                        + bi.plus(mc).toString()
                        + "\",bigDec.plus(mc).toString());");
                sb
                        .append("\r  if (log) { System.out.println(\"---Test PASSED---\"); }");
            } else {
                sb.append("\r  }");
            }

            sb.append("\r}\n");
        }
        return sb.toString() + doc.toString();
    }

    private static String generateBigDecimalTestPlus(ArrayList numbers,
            String testmethodname, String testclass, String type,
            int createmethod) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        BigDecimal bi;
        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        doc.append("\n ==" + testclass + "==\n");
        for (int i = 0; i < numbers.size(); i++) {
            bi = new BigDecimal("" + numbers.get(i));
            doc.append("\n*'''" + testmethodname + Util.intToString(i + 1)
                    + "():''' Se prueba con el número: " + numbers.get(i));
            doc.append(" ''Debe devolver: " + bi.plus().toString() + "''");

            sb.append("\rpublic final void " + testmethodname
                    + Util.intToString(i + 1) + "() {");
            sb.append("\r  " + type + " number = " + ifstring(type)
                    + numbers.get(i) + ifstring(type) + ";");
            sb.append("\r  if (log) { System.out.println(\"" + testmethodname
                    + Util.intToString(i + 1) + "\"); }");
            sb.append("\r  bigDec = ");

            if (createmethod == CONSTRUCTOR) {
                sb.append("new BigDecimal(");
            } else if (createmethod == VALUEOF) {
                sb.append("BigDecimal.valueOf(");
            }
            sb.append("number);");
            sb.append("\r  if (log) { ");
            sb.append("\r\t\tSystem.out.println(\"-> expected result: "
                    + bi.plus().toString() + "\");");
            sb
                    .append("\r\t\tSystem.out.println(\"-> actual result: \"+ bigDec.plus()); \r  }");
            sb.append("\r  assertEquals(msgNotSame,\"" + bi.plus().toString()
                    + "\",bigDec.plus().toString());");
            sb
                    .append("\r  if (log) { System.out.println(\"---Test PASSED---\"); }");
            sb.append("\r}");
        }
        return sb.toString() + doc.toString();
    }

    private static String generateBigDecimalTestScale(ArrayList numbers,
            String testmethodname, String testclass, String type,
            int createmethod) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        BigDecimal bi;
        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        doc.append("\n ==" + testclass + "==\n");
        for (int i = 0; i < numbers.size(); i++) {
            doc.append("\n*'''" + testmethodname + Util.intToString(i + 1)
                    + "():''' Se prueba con el número: " + numbers.get(i));
            bi = new BigDecimal("" + numbers.get(i));
            doc.append(" ''Debe devolver la escala: " + bi.scale() + "''");

            sb.append("\rpublic final void " + testmethodname
                    + Util.intToString(i + 1) + "() {");
            sb.append("\r  " + type + " number = " + ifstring(type)
                    + numbers.get(i) + ifstring(type) + ";");
            sb.append("\r  if (log) { System.out.println(\"" + testmethodname
                    + Util.intToString(i + 1) + "\"); }");
            sb.append("\r  bigDec = ");
            if (createmethod == CONSTRUCTOR) {
                sb.append("new BigDecimal(");
            } else if (createmethod == VALUEOF) {
                sb.append("BigDecimal.valueOf(");
            }
            sb.append("number);");
            sb.append("\r  if (log) { ");
            sb.append("\r\t\tSystem.out.println(\"-> expected result: "
                    + bi.scale() + "\");");
            sb
                    .append("\r\t\tSystem.out.println(\"-> actual result: \"+ bigDec.scale()); \r  }");
            sb.append("\r  assertEquals(msgNotSame," + bi.scale()
                    + ",bigDec.scale());");
            sb
                    .append("\r  if (log) { System.out.println(\"---Test PASSED---\"); }");
            sb.append("\r  }");
        }
        return sb.toString() + doc.toString();
    }

    private static String generateBigDecimalTestPrecision(ArrayList numbers,
            String testmethodname, String testclass, String type,
            int createmethod) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        BigDecimal bi;
        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        doc.append("\n ==" + testclass + "==\n");
        for (int i = 0; i < numbers.size(); i++) {
            doc.append("\n*'''" + testmethodname + Util.intToString(i + 1)
                    + "():''' Se prueba con el número: " + numbers.get(i));
            bi = new BigDecimal("" + numbers.get(i));
            doc.append(" ''Debe devolver la precisión: " + bi.precision()
                    + "''");

            sb.append("\rpublic final void " + testmethodname
                    + Util.intToString(i + 1) + "() {");
            sb.append("\r  " + type + " number = " + ifstring(type)
                    + numbers.get(i) + ifstring(type) + ";");
            sb.append("\r  if (log) { System.out.println(\"" + testmethodname
                    + Util.intToString(i + 1) + "\"); }");
            sb.append("\r  bigDec = ");
            if (createmethod == CONSTRUCTOR) {
                sb.append("new BigDecimal(");
            } else if (createmethod == VALUEOF) {
                sb.append("BigDecimal.valueOf(");
            }
            sb.append("number);");
            sb.append("\r  if (log) { ");
            sb.append("\r\t\tSystem.out.println(\"-> expected result: "
                    + bi.precision() + "\");");
            sb
                    .append("\r\t\tSystem.out.println(\"-> actual result: \"+ bigDec.precision()); \r  }");
            sb.append("\r  assertEquals(msgNotSame," + bi.precision()
                    + ",bigDec.precision());");
            sb
                    .append("\r  if (log) { System.out.println(\"---Test PASSED---\"); }");
            sb.append("\r  }");
        }
        return sb.toString() + doc.toString();
    }

    private static String generateBigDecimalTestToBigInteger(ArrayList numbers,
            String testmethodname, String testclass, String type,
            int createmethod) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        BigDecimal bi;
        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        doc.append("\n ==" + testclass + "==\n");
        for (int i = 0; i < numbers.size(); i++) {
            bi = new BigDecimal("" + numbers.get(i));
            doc.append("\n*'''" + testmethodname + Util.intToString(i + 1)
                    + "():''' Se prueba con el número: " + numbers.get(i));
            doc.append(" ''Debe devolver: " + bi.toBigInteger().toString()
                    + "''");

            sb.append("\rpublic final void " + testmethodname
                    + Util.intToString(i + 1) + "() {");
            sb.append("\r  " + type + " number = " + ifstring(type)
                    + numbers.get(i) + ifstring(type) + ";");
            sb.append("\r  if (log) { System.out.println(\"" + testmethodname
                    + Util.intToString(i + 1) + "\"); }");
            sb.append("\r  bigDec = ");

            if (createmethod == CONSTRUCTOR) {
                sb.append("new BigDecimal(");
            } else if (createmethod == VALUEOF) {
                sb.append("BigDecimal.valueOf(");
            }
            sb.append("number);");
            sb.append("\r  if (log) { ");
            sb.append("\r\t\tSystem.out.println(\"-> expected result: "
                    + bi.toBigInteger() + "\");");
            sb
                    .append("\r\t\tSystem.out.println(\"-> actual result: \"+ bigDec.toBigInteger()); \r  }");
            sb.append("\r  assertEquals(msgNotSame,\"" + bi.toBigInteger()
                    + "\",bigDec.toBigInteger().toString());");
            sb
                    .append("\r  if (log) { System.out.println(\"---Test PASSED---\"); }");
            sb.append("\r}");
        }
        return sb.toString() + doc.toString();
    }

    private static String generateBigDecimalTestUlp(ArrayList numbers,
            String testmethodname, String testclass, String type,
            int createmethod) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        BigDecimal bi;
        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        doc.append("\n ==" + testclass + "==\n");
        for (int i = 0; i < numbers.size(); i++) {
            bi = new BigDecimal("" + numbers.get(i));
            doc.append("\n*'''" + testmethodname + Util.intToString(i + 1)
                    + "():''' Se prueba con el número: " + numbers.get(i));
            doc.append(" ''Debe devolver: " + bi.plus().toString() + "''");

            sb.append("\rpublic final void " + testmethodname
                    + Util.intToString(i + 1) + "() {");
            sb.append("\r  " + type + " number = " + ifstring(type)
                    + numbers.get(i) + ifstring(type) + ";");
            sb.append("\r  if (log) { System.out.println(\"" + testmethodname
                    + Util.intToString(i + 1) + "\"); }");
            sb.append("\r  bigDec = ");

            if (createmethod == CONSTRUCTOR) {
                sb.append("new BigDecimal(");
            } else if (createmethod == VALUEOF) {
                sb.append("BigDecimal.valueOf(");
            }
            sb.append("number);");
            sb.append("\r  if (log) { ");
            sb.append("\r\t\tSystem.out.println(\"-> expected result: "
                    + bi.ulp().toString() + "\");");
            sb
                    .append("\r\t\tSystem.out.println(\"-> actual result: \"+ bigDec.ulp().toString()); \r  }");
            sb.append("\r  assertEquals(msgNotSame,\"" + bi.ulp().toString()
                    + "\",bigDec.ulp().toString());");
            sb
                    .append("\r  if (log) { System.out.println(\"---Test PASSED---\"); }");
            sb.append("\r}");
        }
        return sb.toString() + doc.toString();
    }

    private static String generateBigDecimalTestUnscaledValue(
            ArrayList numbers, String testmethodname, String testclass,
            String type, int createmethod) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        BigDecimal bi;
        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        doc.append("\n ==" + testclass + "==\n");
        for (int i = 0; i < numbers.size(); i++) {
            bi = new BigDecimal("" + numbers.get(i));
            doc.append("\n*'''" + testmethodname + Util.intToString(i + 1)
                    + "():''' Se prueba con el número: " + numbers.get(i));
            doc.append(" ''Debe devolver: " + bi.unscaledValue().toString()
                    + "''");

            sb.append("\rpublic final void " + testmethodname
                    + Util.intToString(i + 1) + "() {");
            sb.append("\r  " + type + " number = " + ifstring(type)
                    + numbers.get(i) + ifstring(type) + ";");
            sb.append("\r  if (log) { System.out.println(\"" + testmethodname
                    + Util.intToString(i + 1) + "\"); }");
            sb.append("\r  bigDec = ");

            if (createmethod == CONSTRUCTOR) {
                sb.append("new BigDecimal(");
            } else if (createmethod == VALUEOF) {
                sb.append("BigDecimal.valueOf(");
            }
            sb.append("number);");
            sb.append("\r  if (log) { ");
            sb.append("\r\t\tSystem.out.println(\"-> expected result: "
                    + bi.unscaledValue().toString() + "\");");
            sb
                    .append("\r\t\tSystem.out.println(\"-> actual result: \"+ bigDec.unscaledValue().toString()); \r  }");
            sb.append("\r  assertEquals(msgNotSame,\""
                    + bi.unscaledValue().toString()
                    + "\",bigDec.unscaledValue().toString());");
            sb
                    .append("\r  if (log) { System.out.println(\"---Test PASSED---\"); }");
            sb.append("\r}");
        }
        return sb.toString() + doc.toString();
    }

    private static String generateBigDecimalTestRoundMathContext(
            ArrayList numbers, String testmethodname, String testclass,
            String type, int createmethod) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        BigDecimal bi;
        MathContext mc;
        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        doc.append("\n ==" + testclass + "==\n");
        for (int i = 0; i < numbers.size(); i++) {
            int precision = Util.getRandomInt(1000, 1);
            // String mathcontext = "precision="+precision +"
            // roundingMode="+Util.getRandomRoundingMode();
            String mathcontext = "precision=" + precision
                    + " roundingMode=UNNECESSARY";
            mc = new MathContext(mathcontext);
            String bigdc = "" + numbers.get(i);
            bi = new BigDecimal(bigdc);
            doc.append("\n*'''" + testmethodname + Util.intToString(i + 1)
                    + "():''' Se prueba con el número: " + numbers.get(i)
                    + " y mc: " + mathcontext);

            if (precision < bigdc.length()) {
                doc.append(" ''Debe lanzar ArithmeticException''");
            } else {
                doc.append(" ''Debe devolver: " + bi.round(mc) + "''");
            }

            sb.append("\rpublic final void " + testmethodname
                    + Util.intToString(i + 1) + "() {");
            if (precision < bigdc.length()) {
                sb
                        .append("\r  ArithmeticException exp = new ArithmeticException();");
            }
            sb.append("\r  MathContext mc = new MathContext(\"" + mathcontext
                    + "\");");
            sb.append("\r  " + type + " number = " + ifstring(type)
                    + numbers.get(i) + ifstring(type) + ";");

            if (precision < bigdc.length()) {
                sb.append("\r try {");
            }
            sb.append("\r  if (log) { System.out.println(\"" + testmethodname
                    + Util.intToString(i + 1) + "\"); }");
            sb.append("\r  bigDec = ");

            if (createmethod == CONSTRUCTOR) {
                sb.append("new BigDecimal(");
            } else if (createmethod == VALUEOF) {
                sb.append("BigDecimal.valueOf(");
            }

            sb.append("number,mc);");

            if (precision < bigdc.length()) {
                sb.append("\r  System.out.println(\"---Test FAILED---\");");
                sb.append("\r  fail(msgRaise+exp);");
            }

            if (precision < bigdc.length()) {
                sb.append("\r } catch (ArithmeticException e) {");
            }

            sb.append("\r  if (log) { ");
            if (precision < bigdc.length()) {
                sb
                        .append("\r\t\tSystem.out.println(\"-> expected exception: \"+ exp);");
                sb.append("\r\t\tSystem.out.println(\"-> actual: \"+ e);");
                sb
                        .append("\r\t\tSystem.out.println(\"---Test PASSED---\"); \r}");
            } else {
                sb.append("\r\t\tSystem.out.println(\"-> expected result: "
                        + bi.round(mc).toString() + "\");");
                sb
                        .append("\r\t\tSystem.out.println(\"-> actual result: \"+ bigDec.round(mc));");
                sb
                        .append("\r\t\tSystem.out.println(\"-> mc: \"+mc.toString());\r   }");
            }

            if (precision >= bigdc.length()) {
                sb.append("\r  assertEquals(msgNotSame,\""
                        + bi.round(mc).toString()
                        + "\",bigDec.round(mc).toString());");
                sb
                        .append("\r  if (log) { System.out.println(\"---Test PASSED---\"); }");
            } else {
                sb.append("\r  }");
            }
            sb.append("\r}\n");
        }
        return sb.toString() + doc.toString();
    }

    private static String generateBigDecimalTestAddBigDecimal(
            ArrayList numbers, ArrayList augends, String testmethodname,
            String testclass, String type, int createmethod) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        BigDecimal bi;
        BigDecimal bi2;
        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        doc.append("\n ==" + testclass + "==\n");
        for (int i = 0; i < numbers.size(); i++) {
            bi = new BigDecimal("" + numbers.get(i));
            bi2 = new BigDecimal("" + augends.get(i));
            doc.append("\n*'''" + testmethodname + Util.intToString(i + 1)
                    + "():''' Se prueba sumar el número: " + numbers.get(i)
                    + " con: " + augends.get(i));
            doc.append(" ''Debe dar como resultado: " + bi.add(bi2).toString()
                    + "''");

            sb.append("\rpublic final void " + testmethodname
                    + Util.intToString(i + 1) + "() {");
            sb.append("\r  " + type + " number = " + ifstring(type)
                    + numbers.get(i) + ifstring(type) + ";");
            sb.append("\r  " + type + " number2 = " + ifstring(type)
                    + augends.get(i) + ifstring(type) + ";");
            sb.append("\r  if (log) { System.out.println(\"" + testmethodname
                    + Util.intToString(i + 1) + "\"); }");
            sb.append("\r  bigDec = ");

            if (createmethod == CONSTRUCTOR) {
                sb.append("new BigDecimal(");
            } else if (createmethod == VALUEOF) {
                sb.append("BigDecimal.valueOf(");
            }
            sb.append("number);");
            sb.append("\r  BigDecimal bd = new BigDecimal(number2);");
            sb.append("\r  if (log) { ");
            sb.append("\r\t\tSystem.out.println(\"-> expected result: "
                    + bi.add(bi2).toString() + "\");");
            sb
                    .append("\r\t\tSystem.out.println(\"-> actual result: \"+ bigDec.add(bd).toString()); \r  }");
            sb.append("\r  assertEquals(msgNotSame,\"" + bi.add(bi2).toString()
                    + "\",bigDec.add(bd).toString());");
            sb
                    .append("\r  if (log) { System.out.println(\"---Test PASSED---\"); }");
            sb.append("\r}");
        }
        return sb.toString() + doc.toString();
    }

    private static String generateBigDecimalTestGenericOperationBigDecimal(
            ArrayList numbers, ArrayList augends, String testmethodname,
            String testclass, String type, int createmethod, String operation) {
        return generateBigDecimalTestGenericOperationBigDecimal(numbers,
                augends, testmethodname, testclass, type, createmethod,
                operation, false);
    }

    private static String generateBigDecimalTestGenericOperationBigDecimal(
            ArrayList numbers, ArrayList augends, String testmethodname,
            String testclass, String type, int createmethod, String operation,
            boolean mathcontext) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        BigDecimal bi = null;
        BigDecimal bi2 = null;
        String result = new String();
        String opvar = new String();
        boolean exception = false;
        String exceptionName = new String();
        MathContext mc = null;
        int n = 0;
        int precision = 0;
        RoundingMode roundingMode = null;
        BigDecimal[] bc = null;
        boolean equals = true;
        String logvar = new String();

        logvar = "log" + testmethodname;

        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        sb.append("\r  boolean " + logvar + " = false;\n");
        doc.append("\n ------ Documentación Automática ------\n");
        doc.append("\n==" + testclass + "==\n");

        for (int i = 0; i < numbers.size(); i++) {

            bi = new BigDecimal("" + numbers.get(i));
            if (augends != null) {
                bi2 = new BigDecimal("" + augends.get(i));
            }

            // To force raising ArithmeticException in the last 5 test cases
            if (operation == "divideAndRemainder"
                    || operation == "divideToIntegralValue"
                    || operation == "remainder") {
                if (i > numbers.size() - 6) {
                    bi2 = BigDecimal.ZERO;
                }
            }

            try {
                if (mathcontext) {
                    precision = Util.getRandomInt(99999, 1);
                    roundingMode = RoundingMode
                            .valueOf(Util.getRandomInt(7, 0));
                    // To force raising ArithmeticException in the last 5 test
                    // cases
                    if (i > numbers.size() - 6) {
                        precision = Util.getRandomInt(10, 1);
                        roundingMode = RoundingMode.valueOf(7);
                    }
                    mc = new MathContext(precision, roundingMode);
                }

                if (operation == "add") { // done MC

                    if (mathcontext) {
                        result = bi.add(bi2, mc).toEngineeringString();
                    } else {
                        result = bi.add(bi2).toEngineeringString();
                    }

                } else if (operation == "divide") { // done

                    result = bi.divide(bi2).toEngineeringString();

                } else if (operation == "divideAndRemainder") { // done
                    bc = new BigDecimal[2];
                    bc = bi.divideAndRemainder(bi2);
                    result = " operation=" + bc[0].toEngineeringString()
                            + " remainder=" + bc[1].toEngineeringString();
                } else if (operation == "max") { // done
                    result = bi.max(bi2).toEngineeringString();
                } else if (operation == "divideToIntegralValue") { // done
                    result = bi.divideToIntegralValue(bi2)
                            .toEngineeringString();
                } else if (operation == "min") { // done
                    result = bi.min(bi2).toEngineeringString();
                } else if (operation == "multiply") { // done MC
                    if (mathcontext) {
                        result = bi.multiply(bi2, mc).toEngineeringString();
                    } else {
                        result = bi.multiply(bi2).toEngineeringString();
                    }
                } else if (operation == "pow") { // done MC

                    n = Util.getRandomInt(80, 1) * Util.rndnegate();
                    if (mathcontext) {
                        result = bi.pow(n, mc).toEngineeringString();
                    } else {
                        result = bi.pow(n).toEngineeringString();
                    }

                } else if (operation == "remainder") { // done
                    if (mathcontext) {
                        result = bi.remainder(bi2, mc).toEngineeringString();
                    } else {
                        result = bi.remainder(bi2).toEngineeringString();
                    }
                } else if (operation == "subtract") { // done
                    result = bi.subtract(bi2).toEngineeringString();
                } else if (operation == "movePointLeft") {
                    n = Util.getRandomInt(1000 * Util.rndnegate(), 1);
                    result = bi.movePointLeft(n).toEngineeringString();
                } else if (operation == "movePointRight") {
                    n = Util.getRandomInt(1000 * Util.rndnegate(), 1);
                    result = bi.movePointRight(n).toEngineeringString();
                } else if (operation == "toPlainString") {
                    result = bi.toPlainString();
                } else if (operation == "toEngineeringString") {
                    result = bi.toEngineeringString();
                } else if (operation == "toString") {
                    result = bi.toString();
                } else if (operation == "hashCode" || operation == "equals") {
                    if (i > numbers.size() - numbers.size() / 2) {
                        bi2 = bi;
                        equals = true;
                    } else {
                        equals = false;
                    }
                    if (operation == "hashCode") {
                        result = "bd.hashCode()";
                    } else if (operation == "equals") {
                        result = "bigDec.equals(bd)";
                    }
                }

            } catch (ArithmeticException e) {
                exception = true;
                ArithmeticException exp = new ArithmeticException();
                exceptionName = "ArithmeticException";
            } catch (IllegalArgumentException e) {
                exception = true;
                IllegalArgumentException exp = new IllegalArgumentException();
                exceptionName = "IllegalArgumentException";
            }

            doc.append("\n*'''" + testmethodname + Util.intToString(i + 1)
                    + "():''' Se prueba realizar la operación \"" + operation
                    + "\" con");

            if (augends != null) {
                doc.append(" los ");
            } else {
                doc.append(" el ");
            }

            doc.append("número");

            if (augends != null) {
                doc.append("s");
            }

            doc.append(": " + bi.toEngineeringString());
            if (augends != null) {
                doc.append(" y " + bi2.toEngineeringString());
            } else {
                if (operation != "toString"
                        && operation != "toEngineeringString"
                        && operation != "toPlainString") {
                    doc.append(" y n=" + n);
                }
            }

            if (mathcontext) {
                doc.append("\rcon Mathcontext: precision=" + precision
                        + " roundingMode=" + roundingMode.toString() + "\r");
            }

            if (exception) {
                doc.append("''Debe lanzar la excepción: " + exceptionName
                        + "''");
            } else {
                doc.append("''Debe dar como resultado: ");
                if (operation == "hashCode" || operation == "equals") {
                    if (equals) {
                        if (operation == "hashCode") {
                            doc.append("HashCodes iguales");
                        } else {
                            doc.append("verdadero");
                        }
                    } else if (operation == "hashCode") {
                        doc.append("HashCodes distintos");
                    } else {
                        doc.append("falso");
                    }
                } else {
                    doc.append(result + "''");
                }

            }

            sb.append("\rpublic final void " + testmethodname
                    + Util.intToString(i + 1) + "() {");

            if (exception) {
                sb.append("\r  " + exceptionName + " exp = new "
                        + exceptionName + "();");
            }

            sb.append("\r  " + type + " number = " + ifstring(type) + bi
                    + ifstring(type) + ";");

            if (augends != null) {
                sb.append("\r  " + type + " number2 = " + ifstring(type) + bi2
                        + ifstring(type) + ";");
            }

            if (mathcontext) {
                sb.append("\r  MathContext mc = new MathContext(\"precision="
                        + precision + " roundingMode="
                        + roundingMode.toString() + "\");" + "\r");
            }

            sb.append("\r  if (" + logvar + ") { System.out.println(\""
                    + testmethodname + Util.intToString(i + 1) + "\"); }");
            sb.append("\r  bigDec = ");

            if (createmethod == CONSTRUCTOR) {
                sb.append("new BigDecimal(");
            } else if (createmethod == VALUEOF) {
                sb.append("BigDecimal.valueOf(");
            }
            sb.append("number);");

            if (augends != null) {
                sb.append("\r  BigDecimal bd = new BigDecimal(number2);");
            }

            if (operation == "pow" || operation == "movePointLeft"
                    || operation == "movePointRight") {
                sb.append("\r  int n = " + n + ";");
                opvar = "n";
            } else {
                opvar = "bd";
            }

            if (!exception) {
                sb.append("\r  if (" + logvar + ") { ");
                sb.append("\r\t\tSystem.out.println(\"-> expected result: ");
                if (operation == "hashCode" || operation == "equals") {
                    if (equals) {
                        if (operation == "hashCode") {
                            sb.append("Same hashcodes");
                        } else {
                            sb.append("Same Object");
                        }
                    } else {
                        if (operation == "hashCode") {
                            sb.append("Different hashcodes");
                        } else {
                            sb.append("Different Object");
                        }
                    }
                } else {
                    sb.append(result);
                }
                sb.append("\");");

                if (bc != null) {
                    sb
                            .append("\r\t\tSystem.out.println(\"-> actual result: operation=\"+bigDec."
                                    + operation
                                    + "("
                                    + opvar
                                    + ")[0].toEngineeringString()+\"remainder= \"+bigDec."
                                    + operation
                                    + "("
                                    + opvar
                                    + ")[1].toEngineeringString()); \r  }");
                } else {
                    if (operation == "toString"
                            || operation == "toEngineeringString"
                            || operation == "toPlainString") {
                        sb
                                .append("\r\t\tSystem.out.println(\"-> actual result: \"+ bigDec."
                                        + operation + "()); \r  }");
                    } else if (operation == "hashCode" || operation == "equals") {
                        sb
                                .append("\r\t\tSystem.out.println(\"-> actual result: ");
                        if (equals) {
                            if (operation == "hashCode") {
                                sb.append("HashCodes are the same");
                            } else {
                                sb.append("Objects are same");
                            }
                        } else if (operation == "hashCode") {
                            sb.append("HashCodes are NOT the same");
                        } else {
                            sb.append("Objects are NOT same");
                        }
                        sb.append("\"); \r  }");
                    } else {
                        if (mathcontext) {
                            sb
                                    .append("\r\t\tSystem.out.println(\"-> actual result: \"+ bigDec."
                                            + operation
                                            + "("
                                            + opvar
                                            + ",mc).toEngineeringString()); \r  }");
                        } else {
                            sb
                                    .append("\r\t\tSystem.out.println(\"-> actual result: \"+ bigDec."
                                            + operation
                                            + "("
                                            + opvar
                                            + ").toEngineeringString()); \r  }");
                        }
                    }
                }
            }
            if (exception) {
                sb.append("\r  try {");
                if (mathcontext) {
                    sb.append("\r    bigDec." + operation + "(" + opvar
                            + ",mc).toEngineeringString();");
                } else {
                    sb.append("\r    bigDec." + operation + "(" + opvar
                            + ").toEngineeringString();");
                }
                sb.append("\r    fail(msgRaise+\"" + exceptionName + "\");");
                sb.append("\r  } catch (" + exceptionName + " e) {");
                sb.append("\r  if (" + logvar + ") { ");
                sb
                        .append("\r\t\tSystem.out.println(\"-> expected exception: \"+ exp);");
                sb.append("\r\t\tSystem.out.println(\"-> actual: \"+ e);");
                sb
                        .append("\r\t\tSystem.out.println(\"---Test PASSED---\"); \r  }");
                sb.append("\r  }");
                sb.append("\r  if (" + logvar
                        + ") { System.out.println(\"---Test FAILED---\"); }");

            } else {
                if (bc != null) {
                    sb.append("\r  assertEquals(msgNotSame,\""
                            + bc[0].toEngineeringString() + "\",bigDec."
                            + operation + "(" + opvar
                            + ")[0].toEngineeringString());");
                    sb.append("\r  assertEquals(msgNotSame,\""
                            + bc[1].toEngineeringString() + "\",bigDec."
                            + operation + "(" + opvar
                            + ")[1].toEngineeringString());");
                } else {

                    if (operation == "toString"
                            || operation == "toEngineeringString"
                            || operation == "toPlainString") {
                        sb.append("\r  assertEquals(msgNotSame,\"" + result
                                + "\",bigDec." + operation + "());");
                    } else if (operation == "hashCode") {
                        if (equals) {
                            sb.append("\r  assertEquals(msgNotSame," + result
                                    + ",bigDec." + operation + "());");
                        } else {
                            sb.append("\r  assertFalse(msgNotSame," + result
                                    + " == bigDec." + operation + "());");
                        }
                    } else if (operation == "equals") {
                        if (equals) {
                            sb.append("\r  assertTrue(msgNotSame," + result
                                    + ");");
                        } else {
                            sb.append("\r  assertFalse(msgNotSame," + result
                                    + ");");
                        }
                    } else {
                        if (mathcontext) {
                            sb.append("\r  assertEquals(msgNotSame,\"" + result
                                    + "\",bigDec." + operation + "(" + opvar
                                    + ",mc).toEngineeringString());");
                        } else {
                            sb.append("\r  assertEquals(msgNotSame,\"" + result
                                    + "\",bigDec." + operation + "(" + opvar
                                    + ").toEngineeringString());");
                        }
                    }
                }
                sb.append("\r  if (" + logvar
                        + ") { System.out.println(\"---Test PASSED---\"); }");
            }
            sb.append("\r}");
        }
        return sb.toString() + doc.toString();
    }

    /**
     * @param numbers
     * @param augends
     * @param testmethodname
     * @param testclass
     * @param type
     * @param createmethod
     * @return the generated code
     */
    public static String generateBigDecimalTestCompareTo(ArrayList numbers,
            ArrayList augends, String testmethodname, String testclass,
            String type, int createmethod) {
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        BigDecimal bi;
        BigDecimal bi2;
        sb.append("\n--------- Tests for " + testclass + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testclass + "'\n */\n\n");
        doc.append("\n ------ Documentación Automática ------\n");
        doc.append("\n==" + testclass + "==\n");
        for (int i = 0; i < numbers.size(); i++) {
            bi = new BigDecimal("" + numbers.get(i));
            bi2 = new BigDecimal("" + augends.get(i));
            if (i > numbers.size() - 6) {
                bi2 = bi;
            }
            doc.append("\n*'''" + testmethodname + Util.intToString(i + 1)
                    + "():''' Se prueba compara el número: " + bi.toString()
                    + " con: " + bi2.toString());
            doc.append(" ''Debe dar como resultado: " + bi.compareTo(bi2)
                    + "''");

            sb.append("\rpublic final void " + testmethodname
                    + Util.intToString(i + 1) + "() {");
            sb.append("\r  " + type + " number = " + ifstring(type)
                    + numbers.get(i) + ifstring(type) + ";");
            sb.append("\r  " + type + " number2 = " + ifstring(type)
                    + augends.get(i) + ifstring(type) + ";");
            sb.append("\r  if (log) { System.out.println(\"" + testmethodname
                    + Util.intToString(i + 1) + "\"); }");
            sb.append("\r  bigDec = ");

            if (createmethod == CONSTRUCTOR) {
                sb.append("new BigDecimal(");
            } else if (createmethod == VALUEOF) {
                sb.append("BigDecimal.valueOf(");
            }
            sb.append("number);");
            sb.append("\r  BigDecimal bd = new BigDecimal(number2);");
            sb.append("\r  if (log) { ");
            sb.append("\r\t\tSystem.out.println(\"-> expected result: "
                    + bi.compareTo(bi2) + "\");");
            sb
                    .append("\r\t\tSystem.out.println(\"-> actual result: \"+ bigDec.compareTo(bd)); \r  }");
            sb.append("\r  assertEquals(msgNotSame," + bi.compareTo(bi2)
                    + ",bigDec.compareTo(bd));");
            sb
                    .append("\r  if (log) { System.out.println(\"---Test PASSED---\"); }");
            sb.append("\r}");
        }
        return sb.toString() + doc.toString();
    }

    /**
     * @param mac
     *            the mac used
     * @param key
     * @param bf
     * @return the computed mac hash
     *
     */
    public static byte[] getHash(Mac mac, Key key, ByteBuffer bf) {
        try {
            mac.init(key);
            mac.update(bf);
        } catch (InvalidKeyException e) {
            System.err.println("Wrong Key");
        }

        System.out.println("Algorithm: " + mac.getAlgorithm());
        System.out.println("MacLength: " + mac.getMacLength() + " bytes");
        System.out.println("Hash: " + mac.doFinal());

        return mac.doFinal();
    }

    /**
     * This class generates code for testing update() methods of the Mac class.
     *
     * @param size
     *            number of test methods to generate
     * @param testmethodname
     *            name of the test methods (automatically add a number at the
     *            end)
     * @param testFunction
     *            name of the function to test
     * @param params
     *            indicates which update method to use for testing
     * @param algorithm
     *            the Mac algorithm to use in the tests
     * @param provider
     *            the Provider to use
     * @param providerInstalledCheck
     *            if true, indicates that the test methods will check if the
     *            provider is installed
     * @param j
     *            test methods starting numeration
     * @param doFinalParams
     *            indicates wich dofinal to use for testing
     * @return The generated test cases
     */

    public static String generateMacTests(int size, String testmethodname,
            String testFunction, int params, String algorithm, String provider,
            boolean providerInstalledCheck, int j, int doFinalParams) {
        j= j-1;
        StringBuffer doc = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        sb.append("\n--------- Tests for " + testFunction + "-----------\n");
        sb.append("\n\n/*\n * Test method for " + testFunction + "'\n */\n\n");
//        doc.append("\n ------ Documentación Automática ------\n");
//        doc.append("\n==" + testFunction + "==\n");

        MacGenerator macgen;

        try {
            macgen = new MacGenerator(algorithm, provider, params,
                    doFinalParams);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("El algoritmo especificado no existe.");
            return "";
        } catch (NoSuchProviderException e) {
            System.err.println("El Proveedor especificado no existe.");
            return "";
        }

        sb.append("\rboolean log" + testmethodname + " = true;");
        sb.append("\rpublic static byte[] salt = " + macgen.getEncodedKey()
                + ";\r");

        for (int i = 0 + j; i < size + j; i++) {

            sb.append("\n\r/**");

            sb.append("\r * {@ar.org.fitc.testmethod_ref "+testFunction+"}");
            sb.append("\r * ");
            sb.append("\r * <br>MAC algorithm used: " + macgen.getAlgorithm()
                    + ", Provider: " + macgen.getProvider() + "" +
                            "\r * <br>Input: ");

//            doc.append("\n*'''" + testmethodname + Util.intToString(i + 1)
//                    + "():''' ");

//            doc.append("MAC: " + macgen.getAlgorithm() + " Provider: "
//                    + macgen.getProvider() + ", \rentrada: ");

            if (params == MacGenerator.BYTEARRAYUPD
                    || params == MacGenerator.BYTEBUFFERUPD) {

                sb.append(macgen.getInputArray());
//                doc.append(macgen.getInputArray());
            } else if (params == MacGenerator.BYTEUPD) {
                sb.append(macgen.getInputByte());
//                doc.append(macgen.getInputByte());
            }

            if (doFinalParams == MacGenerator.DFBYTEINT) {
                sb.append(" \r * <br>Output Array of " + macgen.getHashLength() + " bytes ");
//                doc.append(" \rArreglo de salida de " + macgen.getHashLength()
//                        + " bytes");
            }

            if (doFinalParams == MacGenerator.DFBYTEINT) {
                sb.append("\r * <br>outOffset: "+macgen.getOutOffset());
//                doc.append(" y outOffset: " + macgen.getOutOffset());
            }

//            doc.append("\r");
            if (!macgen.exceptionOcurred()) {
                sb.append(" \r * <br>Expected result: "+macgen.getHash());
//                doc.append(" ''Debe dar como resultado: " + macgen.getHash()
//                        + "''");
            } else {
                sb.append("\r * <br>");
                if (macgen.getExceptionName() == "IllegalArgumentException") {
                    sb.append("length and offset are greater than the input buffer size. ");
//                    doc
//                            .append(" (length y offset son mayores que el tamaño del buffer de entrada) ");
                } else if (macgen.getExceptionName() == "IllegalStateException") {
                    sb.append("Mac not initialized. ");
//                    doc.append(" (No se incializa el mac) ");
                }
                sb.append("Should throw "+macgen.getExceptionName());
//                doc.append(" ''Debe lanzar: " + macgen.getExceptionName()
//                        + "''");
            }

            sb.append("\r *\r */");

            sb
                    .append("\rpublic final void "
                            + testmethodname
                            + Util.intToString(i + 1)
                            + "() "
                            + "throws NoSuchAlgorithmException, NoSuchProviderException,"
                            + " InvalidKeyException");

            if (doFinalParams == MacGenerator.DFBYTEINT) {
                sb.append(", ShortBufferException, IllegalStateException");
            }

            sb.append(" {");

            if (providerInstalledCheck) {
                sb.append("\r  if (Security.getProvider(\"" + provider
                        + "\") == null) {"
                        + "\r    System.err.println(\"Warning: provider "
                        + provider + " not installed. Test \\\""
                        + testmethodname + Util.intToString(i + 1)
                        + "\\\" was not excecuted.\"); \r    return;\r  }");
            }
            if (params == MacGenerator.BYTEARRAYUPD
                    || params == MacGenerator.BYTEARRAYINTINTUPD) {
                sb.append("\r  byte[] input = " + macgen.getInputArray() + ";");
                if (params == MacGenerator.BYTEARRAYINTINTUPD) {
                    sb.append("\r  int offset = " + macgen.getInputOffset()
                            + ";");
                    sb.append("\r  int length = " + macgen.getInputLength()
                            + ";");
                }
            } else if (params == MacGenerator.BYTEUPD) {
                sb.append("\r  byte input = " + macgen.getInputByte() + ";");
            } else if (params == MacGenerator.BYTEBUFFERUPD) {
                sb.append("\r  byte[] input = " + macgen.getInputArray() + ";");
                sb
                        .append("\r  ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);");
            }

            if (doFinalParams == MacGenerator.DFBYTEINT) {
                sb.append("\r  int outOffset = " + macgen.getOutOffset() + ";");
            }

            if (!macgen.exceptionOcurred()) {
                sb.append("\r  String hash = \"" + macgen.getHash() + "\";");
            }

            sb.append("\r  Mac mac = " + macgen.getInstance());
            sb.append("\r  if (log" + testmethodname
                    + ") { System.out.println(\"" + testmethodname
                    + Util.intToString(i + 1) + "\"); }");

            if (!macgen.exceptionOcurred()) {
                sb.append("\r  " + macgen.getInit());
            } else if (macgen.exceptionOcurred()
                    && !macgen.getExceptionName().equals(
                            "IllegalStateException")) {
                sb.append("\r  " + macgen.getInit());
            }

            if (!macgen.exceptionOcurred()) {

                if (doFinalParams == MacGenerator.DF
                        || doFinalParams == MacGenerator.DFBYTEINT) {
                    sb.append("\r  for (int i=0 ; i<100 ; i++) {");
                    if (params == MacGenerator.BYTEARRAYINTINTUPD) {
                        sb.append("\r    mac.update(input,offset,length);");
                    } else {
                        if (params == MacGenerator.BYTEBUFFERUPD) {
                            sb.append("\r    mac.update(inputByteBuffer);");
                        } else {
                            sb.append("\r    mac.update(input);");
                        }
                    }
                    sb.append("\r  }");
                }
                if (doFinalParams == MacGenerator.DF) {
                    sb.append("\r  byte[] result = mac.doFinal();");
                } else if (doFinalParams == MacGenerator.DFBYTE) {
                    sb.append("\r  byte[] result = mac.doFinal(input);");
                } else if (doFinalParams == MacGenerator.DFBYTEINT) {
                    sb.append("\r  byte[] result = new byte["
                            + macgen.getHashLength() + "];");
                    sb.append("\r  mac.doFinal(result,outOffset);");
                }

                sb.append("\r  if (log" + testmethodname + ") { ");
                sb.append("\r\t\tSystem.out.println(\"-> expected result: "
                        + macgen.getHash() + "\");");
                sb
                        .append("\r\t\tSystem.out.println(\"-> actual result: \"+ Arrays.toString(result)); \r  }");
                sb
                        .append("\r  assertEquals(msgNotSame,hash,Arrays.toString(result));");
                sb.append("\r  if (log" + testmethodname
                        + ") { System.out.println(\"---Test PASSED---\"); }");
            } else {
                sb.append("\r  try {");

                if (doFinalParams == MacGenerator.DF
                        || doFinalParams == MacGenerator.DFBYTEINT) {
                    sb.append("\r  " + macgen.getInit());
                    sb.append("\r  for (int i=0 ; i<100 ; i++) {");
                    if (params == MacGenerator.BYTEARRAYINTINTUPD) {
                        sb.append("\r    mac.update(input,offset,length);");
                    } else {
                        if (params == MacGenerator.BYTEBUFFERUPD) {
                            sb.append("\r    mac.update(inputByteBuffer);");
                        } else {
                            sb.append("\r    mac.update(input);");
                        }
                    }
                    sb.append("\r  }");
                }

                if (testFunction.contains("update")) {
                    if (params == MacGenerator.BYTEARRAYINTINTUPD) {
                        sb.append("\r    mac.update(input,offset,length);");
                    } else {
                        if (params == MacGenerator.BYTEBUFFERUPD) {
                            sb.append("\r    mac.update(inputByteBuffer);");
                        } else {
                            sb.append("\r    mac.update(input);");
                        }
                    }
                } else if (testFunction.contains("doFinal")) {
                    if (doFinalParams == MacGenerator.DF) {
                        sb.append("\r  byte[] result = mac.doFinal();");
                    } else if (doFinalParams == MacGenerator.DFBYTE) {
                        sb.append("\r  byte[] result = mac.doFinal(input);");
                    } else if (doFinalParams == MacGenerator.DFBYTEINT) {
                        sb.append("\r  byte[] result = new byte["
                                + macgen.getHash().length() + "];");
                        sb.append("\r  mac.doFinal(result,outOffset);");
                    }
                }
                sb.append("\r     fail(\"Should raise "
                        + macgen.getExceptionName() + "\");");
                sb
                        .append("\r  } catch (" + macgen.getExceptionName()
                                + " e) {");
                sb.append("\r       if (log" + testmethodname + ") { "
                        + "\r\t\tSystem.out.println(\"Raised "
                        + macgen.getException() + "\");"
                        + "\r\t\tSystem.out.println(\"---Test PASSED---\"); "
                        + "\r\t\treturn; \r\t\t}");
                sb.append("\r  } catch (Throwable e) {");
                sb.append("\r     fail(\"Should raise "
                        + macgen.getExceptionName() + "\");");
                sb.append("\r  }");
            }

            sb.append("\r}");
            macgen.next();
        }
        return sb.toString() + doc.toString();
    }
}
