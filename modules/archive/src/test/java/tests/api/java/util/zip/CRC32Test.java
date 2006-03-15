/* Copyright 1998, 2005 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tests.api.java.util.zip;



import java.util.zip.CRC32;

public class CRC32Test extends junit.framework.TestCase {

	/**
	 * @tests java.util.zip.CRC32#CRC32()
	 */
	public void test_Constructor() {
		// test methods of java.util.zip.CRC32()
		CRC32 crc = new CRC32();
		assertTrue("Constructor of CRC32 failed", crc.getValue() == 0);
	}

	/**
	 * @tests java.util.zip.CRC32#getValue()
	 */
	public void test_getValue() {
		// test methods of java.util.zip.crc32.getValue()
		CRC32 crc = new CRC32();
		assertTrue(
				"getValue() should return a zero as a result of constructing a CRC32 instance",
				crc.getValue() == 0);

		crc.reset();
		crc.update(Integer.MAX_VALUE);
		// System.out.print("value of crc " + crc.getValue());
		// Ran JDK and discovered that the value of the CRC should be
		// 4278190080
		assertTrue(
				"update(max) failed to update the checksum to the correct value ",
				crc.getValue() == 4278190080L);

		crc.reset();
		byte byteEmpty[] = new byte[10000];
		crc.update(byteEmpty);
		// System.out.print("value of crc"+crc.getValue());
		// Ran JDK and discovered that the value of the CRC should be
		// 1295764014
		assertTrue(
				"update(byte[]) failed to update the checksum to the correct value ",
				crc.getValue() == 1295764014L);

		crc.reset();
		crc.update(1);
		// System.out.print("value of crc"+crc.getValue());
		// Ran JDK and discovered that the value of the CRC should be
		// 2768625435
		// assertTrue("update(int) failed to update the checksum to the correct
		// value ",crc.getValue() == 2768625435L);
		crc.reset();
		assertTrue("reset failed to reset the checksum value to zero", crc
				.getValue() == 0);
	}

	/**
	 * @tests java.util.zip.CRC32#reset()
	 */
	public void test_reset() {
		// test methods of java.util.zip.crc32.reset()
		CRC32 crc = new CRC32();
		crc.update(1);
		// System.out.print("value of crc"+crc.getValue());
		// Ran JDK and discovered that the value of the CRC should be
		// 2768625435
		assertTrue(
				"update(int) failed to update the checksum to the correct value ",
				crc.getValue() == 2768625435L);
		crc.reset();
		assertTrue("reset failed to reset the checksum value to zero", crc
				.getValue() == 0);

	}

	/**
	 * @tests java.util.zip.CRC32#update(int)
	 */
	public void test_updateI() {
		// test methods of java.util.zip.crc32.update(int)
		CRC32 crc = new CRC32();
		crc.update(1);
		// System.out.print("value of crc"+crc.getValue());
		// Ran JDK and discovered that the value of the CRC should be
		// 2768625435
		assertTrue(
				"update(1) failed to update the checksum to the correct value ",
				crc.getValue() == 2768625435L);

		crc.reset();
		crc.update(Integer.MAX_VALUE);
		// System.out.print("value of crc " + crc.getValue());
		// Ran JDK and discovered that the value of the CRC should be
		// 4278190080
		assertTrue(
				"update(max) failed to update the checksum to the correct value ",
				crc.getValue() == 4278190080L);

		crc.reset();
		crc.update(Integer.MIN_VALUE);
		// System.out.print("value of crc " + crc.getValue());
		// Ran JDK and discovered that the value of the CRC should be
		// 3523407757
		assertTrue(
				"update(min) failed to update the checksum to the correct value ",
				crc.getValue() == 3523407757L);
	}

	/**
	 * @tests java.util.zip.CRC32#update(byte[])
	 */
	public void test_update$B() {
		// test methods of java.util.zip.crc32.update(byte[])
		byte byteArray[] = { 1, 2 };
		CRC32 crc = new CRC32();
		crc.update(byteArray);
		// System.out.print("value of crc"+crc.getValue());
		// Ran JDK and discovered that the value of the CRC should be
		// 3066839698
		assertTrue(
				"update(byte[]) failed to update the checksum to the correct value ",
				crc.getValue() == 3066839698L);

		crc.reset();
		byte byteEmpty[] = new byte[10000];
		crc.update(byteEmpty);
		// System.out.print("value of crc"+crc.getValue());
		// Ran JDK and discovered that the value of the CRC should be
		// 1295764014
		assertTrue(
				"update(byte[]) failed to update the checksum to the correct value ",
				crc.getValue() == 1295764014L);
	}

	/**
	 * @tests java.util.zip.CRC32#update(byte[], int, int)
	 */
	public void test_update$BII() {
		// test methods of java.util.zip.update(byte[],int,int)
		byte[] byteArray = { 1, 2, 3 };
		CRC32 crc = new CRC32();
		int off = 2;// accessing the 2nd element of byteArray
		int len = 1;
		int lenError = 3;
		int offError = 4;
		crc.update(byteArray, off, len);
		// System.out.print("value of crc"+crc.getValue());
		// Ran JDK and discovered that the value of the CRC should be
		// 1259060791
		assertTrue(
				"update(byte[],int,int) failed to update the checksum to the correct value ",
				crc.getValue() == 1259060791L);
		int r = 0;
		try {
			crc.update(byteArray, off, lenError);
		} catch (ArrayIndexOutOfBoundsException e) {
			r = 1;
		}
		assertTrue(
				"update(byte[],int,int) failed b/c lenError>byte[].length-off",
				r == 1);

		try {
			crc.update(byteArray, offError, len);
		} catch (ArrayIndexOutOfBoundsException e) {
			r = 2;
		}
		assertTrue("update(byte[],int,int) failed b/c offError>byte[].length",
				r == 2);
	}

	protected void setUp() {

	}

	protected void tearDown() {
	}

}
