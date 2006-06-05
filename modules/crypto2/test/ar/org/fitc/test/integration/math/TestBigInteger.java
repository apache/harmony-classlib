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

package ar.org.fitc.test.integration.math;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
/**
 * This class uses some of the BigInteger methods. 
 * Makes use of all constructors and then it probes some methods.
 */ 
class TestBigInteger {
	private final String AUX = "82369129806141089640982136408762408764098346980234";

	public static final int QUANTITY = 4;

	private PrintWriter pw;

	public TestBigInteger(BigInteger[][] list, boolean flag, String file)
			throws FileNotFoundException {
		pw = new PrintWriter(file);

		if (flag) {
			list[0][0] = new BigInteger("9999999999999999999999999999999999999"
					.getBytes());
			list[0][1] = new BigInteger(1, "1231218729361".getBytes());
			list[0][2] = new BigInteger(
					"-555555555555555555555555555555555555555555555555555555555");
			list[0][3] = new BigInteger(
					"11111111111111111111111111111111111111111111111111111", 15);
		}

		copyOutput(list);
		pw.close();
	}

	private void copyOutput(BigInteger[][] list) {

		for (int i = 0; i < list[0].length; i++) {
			try {
				list[1][i] = integration(list[0][i]);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}

		writeInputOutPut(list);
	}

	public void writeInputOutPut(BigInteger[][] list) {
		pw.println("---------------- INPUT -> OUTPUT -----------------------");

		for (int i = 0; i < list[0].length; i++) {
			pw.println("Input: " + list[0][i] + " -> Output: " + list[1][i]);
		}
	}

	public static BigInteger[][] transformation(BigDecimal[][] list) {
		BigInteger[][] result = new BigInteger[2][list[0].length];

		for (int i = 0; i < list[0].length; i++) {
			result[0][i] = list[0][i].toBigInteger();
		}

		return result;
	}

	private BigInteger integration(BigInteger input) {
		if (input == null) {
			return null;
		}

		BigInteger aux = new BigInteger(AUX);
		BigInteger[] aux2;
		BigInteger x = input;

		pw.println("---------------- Paso a Paso ----------------");

		pw.println("Input: " + input);
		x = x.add(aux);
		pw.println("add(" + aux + "): " + x);
		x = x.subtract(aux);
		pw.println("subtract(" + aux + "):" + x);
		x = x.multiply(aux);
		pw.println("multiply(" + aux + "):" + x);
		x = x.divide(aux);
		pw.println("divide(" + aux + "):" + x);

		try {
			x = x.max(x.modInverse(aux));
		} catch (Throwable e) {
		}

		aux2 = x.divideAndRemainder(aux);
		x = aux2[1].add(aux2[0].multiply(aux));
		pw.println("x = " + aux2[1] + " + (" + aux2[0] + " * " + aux + ") = "
				+ x);
		x = BigInteger.valueOf(x.longValue());
		pw.println("BigInteger.valueOf(x):" + x);
		x = x.and(aux);
		pw.println("and(" + aux + "):" + x);
		x = x.not();
		pw.println("not():" + x);
		x = x.andNot(aux);
		pw.println("andNot(" + aux + "):" + x);
		x = x.or(aux);
		pw.println("or(" + aux + "):" + x);
		x = x.xor(aux);
		pw.println("xor(" + aux + "):" + x);
		x = x.negate();
		pw.println("negate(" + aux + "):" + x);
		x = x.abs();
		pw.println("x = abs(" + aux + "):" + x);
		x = x.clearBit(x.bitCount());
		pw.println("x = clearBit(x.bitCount()):" + x);
		x = x.flipBit(x.bitLength());
		pw.println("flipBit(x.bitLength()):" + x);
		x = x.gcd(aux);
		pw.println("gcd(" + aux + "):" + x);
		x = x.nextProbablePrime();
		pw.println("nextProbablePrime():" + x);
		x = x.setBit(9);
		pw.println("setBit(9):" + x);
		x = x.shiftLeft(12);
		pw.println("shiftLeft(12):" + x);
		x = x.shiftRight(10);
		pw.println("shiftRight(10):" + x);
		x = x.mod(aux);
		pw.println("mod(" + aux + "):" + x);
		x = x.pow(100);
		pw.println("pow(100):" + x);
		x = x.remainder(aux);
		pw.println("x = remainder(" + aux + "):" + x);
		x = x.min(x.modPow(BigInteger.valueOf(10), aux));
		pw.println("min(BigInteger.valueOf(10), " + aux + ")):" + x);

		return x;
	}

	public static void main(String[] args) throws FileNotFoundException {
		BigInteger[][] list = new BigInteger[2][4];

		new TestBigInteger(list, true, "c:\\asd.rtf");

		System.out.println("Input:");
		System.out.println(Arrays.toString(list[0]));
		System.out.println("Output:");
		System.out.println(Arrays.toString(list[1]));
	}
}
