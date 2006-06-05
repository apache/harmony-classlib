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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

public class Big {

	public static final String FILE1 = "C:\\TestMath\\IntegrationMath\\BigInteger2.txt";

	public static final String FILE2 = "C:\\TestMath\\IntegrationMath\\BigDecimal2.txt";

	public static final String FILE3 = "C:\\TestMath\\IntegrationMath\\BigDecimalBigInteger2.txt";

	public static final String FILE4 = "C:\\TestMath\\IntegrationMath\\BigIntegerBigDecimal2.txt";

	public static void main(String[] args) {
		System.out.println("Start Test:");
		System.out.flush();
		BigInteger[][] list1 = new BigInteger[2][TestBigInteger.QUANTITY];
		BigInteger[][] list3 = null;
		BigDecimal[][] list2 = new BigDecimal[2][TestBigDecimal.QUANTITY];
		BigDecimal[][] list4 = null;

		try {
			System.out.print("\tCreating list 1, wait...");
			new TestBigInteger(list1, true, FILE1);
			list4 = TestBigDecimal.transformation(list1);
			System.out.print("[OK] - The log was written in file " + FILE1
					+ "\n\tCreating list 2, wait...");
			new TestBigDecimal(list2, true, FILE2);
			list3 = TestBigInteger.transformation(list2);
			System.out.print("[OK] - The log was written in file " + FILE2
					+ "\n\tCreating list 3, wait...");
			new TestBigInteger(list3, false, FILE3);
			System.out.print("[OK] - The log was written in file " + FILE3
					+ "\n\tCreating list 4, wait...");
			new TestBigDecimal(list4, false, FILE4);
			System.out.print("[OK] - The log was written in file " + FILE4);
		} catch (FileNotFoundException e) {
			System.out.print("[ERROR]");
		}


		System.out.println("\tInput BigInteger(list1):");
		System.out.println(Arrays.toString(list1[0]));
		System.out.println("\tOutput BigInteger(list1):");
		System.out.println(Arrays.toString(list1[1]));
		System.out.println("\t----------------------");
		System.out.println("\tInput BigDecimal(list2):");
		System.out.println(Arrays.toString(list2[0]));
		System.out.println("\tOutput BigDecimal(list2):");
		System.out.println(Arrays.toString(list2[1]));
		System.out.println("\t----------------------");
		System.out.println("\tInput BigDecimal->BigInteger(list3): ");
		System.out.println(Arrays.toString(list3[0]));
		System.out.println("\tOutput BigDecimal->BigInteger(list3):");
		System.out.println(Arrays.toString(list3[1]));
		System.out.println("\t----------------------");
		System.out.println("\tInput BigInteger->BigDecimal(list4):");
		System.out.println(Arrays.toString(list4[0]));
		System.out.println("\tOutput BigInteger->BigDecimal(list4):");
		System.out.println(Arrays.toString(list4[1]));
		System.out.println("\t----------------------");
		System.out.println("Finish");
	}
}
