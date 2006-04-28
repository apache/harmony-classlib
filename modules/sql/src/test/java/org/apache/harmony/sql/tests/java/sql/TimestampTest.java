/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.sql.tests.java.sql;

import java.sql.Timestamp;
import java.util.TimeZone;

import junit.framework.TestCase;

/**
 * JUnit Testcase for the java.sql.Timestamp class
 * 
 */

public class TimestampTest extends TestCase {

	static long TIME_TEST1 = 38720231; // 10:45:20.231 GMT

	static long TIME_TEST2 = 80279000; // 22:17:59.000 GMT

	static long TIME_TEST3 = -38720691; // 13:14:39.309 GMT

	static long TIME_COMPARE = 123498845;

	static long TIME_EARLY = -2347889122L;// A time well before the Epoch

	static long TIME_LATE = 2347889122L; // A time well after the Epoch

	static String STRING_TEST1 = "1970-01-01 10:45:20.231"; // "1970-01-01
															// 10:45:20.231000000";

	static String STRING_TEST2 = "1970-01-01 22:17:59.0"; // "1970-01-01
															// 22:17:59.000000000";

	static String STRING_TEST3 = "1969-12-31 13:14:39.309"; // "1969-12-31
															// 13:14:39.309000000";

	static String STRING_INVALID1 = "ABCDEFGHI";

	static String STRING_INVALID2 = "233104";

	static String STRING_INVALID3 = "21-43-48";

	// A timepoint in the correct format but with numeric values out of range
	// ...this is accepted despite being a crazy date specification
	// ...it is treated as the correct format date 3000-06-08 12:40:06.875 !!
	static String STRING_OUTRANGE = "2999-15-99 35:99:66.875";

	static long[] TIME_ARRAY = { TIME_TEST1, TIME_TEST2, TIME_TEST3 };

	static int[] YEAR_ARRAY = { 70, 70, 69 };

	static int[] MONTH_ARRAY = { 0, 0, 11 };

	static int[] DATE_ARRAY = { 1, 1, 31 };

	static int[] HOURS_ARRAY = { 10, 22, 13 };

	static int[] MINUTES_ARRAY = { 45, 17, 14 };

	static int[] SECONDS_ARRAY = { 20, 59, 39 };

	static int[] NANOS_ARRAY = { 231000000, 000000000, 309000000 };

	static int[] NANOS_ARRAY2 = { 137891990, 635665198, 109985421 };

	static String[] STRING_NANOS_ARRAY = { "1970-01-01 10:45:20.13789199",
			"1970-01-01 22:17:59.635665198", "1969-12-31 13:14:39.109985421" };

	static String[] STRING_GMT_ARRAY = { STRING_TEST1, STRING_TEST2,
			STRING_TEST3 };

	static String[] STRING_LA_ARRAY = { "02:45:20", "14:17:59", "05:14:40" };

	static String[] STRING_JP_ARRAY = { "19:45:20", "07:17:59", "22:14:40" };

	static String[] INVALID_STRINGS = { STRING_INVALID1, STRING_INVALID2,
			STRING_INVALID3 };

	// Timezones
	static String TZ_LONDON = "GMT"; // GMT (!) PS London != GMT (?!?)

	static String TZ_PACIFIC = "America/Los_Angeles"; // GMT - 8

	static String TZ_JAPAN = "Asia/Tokyo"; // GMT + 9

	static String[] TIMEZONES = { TZ_LONDON, TZ_PACIFIC, TZ_JAPAN };

	static String[][] STRING_ARRAYS = { STRING_GMT_ARRAY, STRING_LA_ARRAY,
			STRING_JP_ARRAY };

	/*
	 * Constructor test
	 */
	public void testTimestamplong() {
		Timestamp theTimestamp = new Timestamp(TIME_TEST1);

		// The Timestamp should have been created
		assertNotNull(theTimestamp);
	} // end method testTimestamplong

	/*
	 * Constructor test
	 */
	public void testTimestampintintintintintintint() {
		int[][] initParms = { { 99, 2, 14, 17, 52, 3, 213577212 }, // 0 valid
				{ 0, 0, 1, 0, 0, 0, 0 }, // 1 valid
				{ 106, 11, 31, 23, 59, 59, 999999999 }, // 2 valid
				{ 106, 11, 31, 23, 59, 59, 1999999999 }, // 3 invalid - Nanos
															// out of range
				{ 106, 11, 31, 23, 59, 59, -999999999 }, // 4 invalid - Nanos
															// out of range
				{ 106, 11, 31, 23, 59, 61, 999999999 }, // 5 Seconds out of
														// range
				{ 106, 11, 31, 23, 59, -1, 999999999 }, // 6 Seconds out of
														// range
				{ 106, 11, 31, 23, 61, 59, 999999999 }, // 7 Minutes out of
														// range
				{ 106, 11, 31, 23, -1, 59, 999999999 }, // 8 Minutes out of
														// range
				{ 106, 11, 31, 25, 59, 59, 999999999 }, // 9 Hours out of range
				{ 106, 11, 31, -1, 59, 59, 999999999 }, // 10 Hours out of range
				{ 106, 11, 35, 23, 59, 59, 999999999 }, // 11 Days out of range
				{ 106, 11, -1, 23, 59, 59, 999999999 }, // 12 Days out of range
				{ 106, 15, 31, 23, 59, 59, 999999999 }, // 13 Months out of
														// range
				{ 106, -1, 31, 23, 59, 59, 999999999 }, // 14 Months out of
														// range
				{ -10, 11, 31, 23, 59, 59, 999999999 }, // 15 valid - Years
														// negative
		};

		Exception[] theExceptions = { null, null, null,
				new IllegalArgumentException(), new IllegalArgumentException(),
				null, null, null, null, null, null, null, null, null, null,
				null };

		for (int i = 0; i < initParms.length; i++) {
			try {
				Timestamp theTimestamp = new Timestamp(initParms[i][0],
						initParms[i][1], initParms[i][2], initParms[i][3],
						initParms[i][4], initParms[i][5], initParms[i][6]);
				assertNotNull("Timestamp not generated: ", theTimestamp);
				if (theExceptions[i] != null)
					fail(i + ": Did not get exception");
			} catch (Exception e) {
				assertEquals(i + ": Incorrect exception generated: ",
						theExceptions[i].getClass(), e.getClass());
			} // end try
		} // end for

	} // end method testTimestampintintintintintintint

	/*
	 * Method test for setTime
	 */
	public void testSetTimelong() {
		// First set the timezone to GMT
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));

		Timestamp theTimestamp = new Timestamp(TIME_TEST1);

		for (int i = 0; i < TIME_ARRAY.length; i++) {
			theTimestamp.setTime(TIME_ARRAY[i]);

			assertTrue(theTimestamp.getTime() == TIME_ARRAY[i]);
			assertTrue(theTimestamp.getNanos() == NANOS_ARRAY[i]);
		} // end for

	} // end method testsetTimelong

	/*
	 * Method test for getTime
	 */
	public void testGetTime() {
		// First set the timezone to GMT
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));

		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);

			assertTrue(theTimestamp.getTime() == TIME_ARRAY[i]);
		} // end for

	} // end method testgetTime

	/*
	 * Method test for getYear
	 */
	public void testGetYear() {

		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);
			int theYear = theTimestamp.getYear();
			assertTrue(theYear == YEAR_ARRAY[i]);
		} // end for

	} // end method testgetYear

	/*
	 * Method test for getMonth
	 */
	public void testGetMonth() {
		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);
			int theMonth = theTimestamp.getMonth();
			assertTrue(theMonth == MONTH_ARRAY[i]);
		} // end for

	} // end method testgetMonth

	/*
	 * Method test for getDate
	 */
	public void testGetDate() {
		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);
			int theDate = theTimestamp.getDate();
			assertTrue(theDate == DATE_ARRAY[i]);
		} // end for

	} // end method testgetDate

	/*
	 * Method test for getHours
	 */
	public void testGetHours() {
		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);
			int theHours = theTimestamp.getHours();
			assertTrue(theHours == HOURS_ARRAY[i]);
		} // end for

	} // end method testgetHours

	/*
	 * Method test for getMinutes
	 */
	public void testGetMinutes() {
		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);
			int theMinutes = theTimestamp.getMinutes();
			assertTrue(theMinutes == MINUTES_ARRAY[i]);
		} // end for

	} // end method testgetMinutes

	/*
	 * Method test for getSeconds
	 */
	public void testGetSeconds() {
		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);
			int theSeconds = theTimestamp.getSeconds();
			assertTrue(theSeconds == SECONDS_ARRAY[i]);
		} // end for

	} // end method testgetSeconds

	/*
	 * Method test for valueOf
	 */
	static String theExceptionMessage = "Timestamp format must be yyyy-mm-dd hh:mm:ss.fffffffff";

	public void testValueOfString() {
		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);
			Timestamp theTimestamp2 = Timestamp.valueOf(STRING_GMT_ARRAY[i]);
			// System.out.println("testValueOfString: " +
			// theTimestamp2.toString() );
			assertTrue(theTimestamp2.equals(theTimestamp));
		} // end for

		// Test for a string in correct format but with number values out of
		// range
		Timestamp theTimestamp = Timestamp.valueOf(STRING_OUTRANGE);
		/*
		 * System.out.println("testValueOfString: outrange timestamp: " +
		 * theTimestamp.toString() );
		 */

		// Now test some truly invalid strings that should cause exceptions
		for (int i = 0; i < INVALID_STRINGS.length; i++) {
			try {
				Timestamp theTimestamp2 = Timestamp.valueOf(INVALID_STRINGS[i]);
				assertTrue(false);
			} catch (IllegalArgumentException e) {
				/*
				 * System.out.println("testValueOfString: exception message: " +
				 * e.getMessage() );
				 */
				assertTrue(e.getMessage().equals(theExceptionMessage));
			} // end try

		} // end for

	} // end method testvalueOfString

	/*
	 * Method test for valueOf
	 */
	public void testValueOfString1() {

		Timestamp theReturn;
		Timestamp[] theReturns = {};
		long[] theReturnTime = { 38720231, 38720231, 80279000, -38720691,
				38720000, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int[] theReturnNanos = { 231000000, 231987654, 0, 309000000, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0 };
		String[] parm1 = { "1970-01-01 10:45:20.231",
				"1970-01-01 10:45:20.231987654", "1970-01-01 22:17:59.0",
				"1969-12-31 13:14:39.309", "1970-01-01 10:45:20", null,
				"ABCDEFGHI", "233104", "1970-01-01 22:17:59.",
				"1970-01-01 10:45:20.231987654690645322",
				"1970-01-01 10:45:20&231987654",
				"1970-01-01 10:45:20.-31987654",
				"1970-01-01 10:45:20.ABCD87654", "21-43-48" };
		Exception[] theExceptions = {
				null,
				null,
				null,
				null,
				null,
				new IllegalArgumentException("null string"),
				new IllegalArgumentException(
						"Timestamp format must be yyyy-mm-dd hh:mm:ss.fffffffff"),
				new IllegalArgumentException(
						"Timestamp format must be yyyy-mm-dd hh:mm:ss.fffffffff"),
				new IllegalArgumentException(
						"Timestamp format must be yyyy-mm-dd hh:mm:ss.fffffffff"),
				new IllegalArgumentException(
						"Timestamp format must be yyyy-mm-dd hh:mm:ss.fffffffff"),
				new NumberFormatException("For input string \"20&231987654\""),
				new IllegalArgumentException(
						"Timestamp format must be yyyy-mm-dd hh:mm:ss.fffffffff"),
				new IllegalArgumentException(
						"Timestamp format must be yyyy-mm-dd hh:mm:ss.fffffffff"),
				new IllegalArgumentException(
						"Timestamp format must be yyyy-mm-dd hh:mm:ss.fffffffff") };

		int loopCount = parm1.length;
		for (int i = 0; i < loopCount; i++) {
			try {
				theReturn = Timestamp.valueOf(parm1[i]);
				if (theExceptions[i] != null)
					assertTrue(false);
				assertEquals(i + " Times do not match: ", theReturn.getTime(),
						theReturnTime[i]);
				assertEquals(i + " Nanos do not match: ", theReturn.getNanos(),
						theReturnNanos[i]);
			} catch (Exception e) {
				if (theExceptions[i] != null) {
					assertEquals(i + " Unexpected exception: ", e.getClass(),
							theExceptions[i].getClass());
				} else {
					assertEquals(i + " Exception when none expected: ", e
							.getClass(), null);
				} // end if
			} // end try
		} // end for

	} // end method testValueOfString

	/*
	 * Method test for toString
	 */
	public void testToString() {
		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);
			String theString = theTimestamp.toString();
			assertTrue(theString.equals(STRING_GMT_ARRAY[i]));
		} // end for

	} // end method testtoString

	/*
	 * Method test for getNanos
	 */
	public void testGetNanos() {
		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);

			assertTrue(theTimestamp.getNanos() == NANOS_ARRAY[i]);
		} // end for

	} // end method testgetNanos

	/*
	 * Method test for setNanos
	 */
	public void testSetNanosint() {
		int[] NANOS_INVALID = { -137891990, 1635665198, -1 };
		Exception[] theExceptions = { new IllegalArgumentException(),
				new IllegalArgumentException(), new IllegalArgumentException() };

		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);

			theTimestamp.setNanos(NANOS_ARRAY2[i]);

			assertTrue(theTimestamp.getNanos() == NANOS_ARRAY2[i]);
			// Also check that these Timestamps with detailed nanos values
			// convert to
			// strings correctly
			assertTrue(theTimestamp.toString().equals(STRING_NANOS_ARRAY[i]));
		} // end for

		for (int i = 0; i < NANOS_INVALID.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);
			int originalNanos = theTimestamp.getNanos();
			try {
				theTimestamp.setNanos(NANOS_INVALID[i]);
				fail("Should have got exception and did not");
			} catch (Exception e) {
				assertEquals("Exception mismatch: ", e.getClass(),
						theExceptions[i].getClass());
			} // end try

			assertTrue(theTimestamp.getNanos() == originalNanos);
		} // end for

	} // end method testsetNanosint

	/*
	 * Method test for equals
	 */
	public void testEqualsTimestamp() {
		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);

			Timestamp theTimestamp2 = new Timestamp(TIME_ARRAY[i]);

			assertTrue(theTimestamp.equals(theTimestamp2));
		} // end for

		Timestamp theTest = new Timestamp(TIME_COMPARE);

		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);

			assertFalse(theTimestamp.equals(theTest));
		} // end for
	} // end method testequalsTimestamp

	/*
	 * Method test for equals
	 */
	public void testEqualsObject() {
		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);

			Object theTimestamp2 = new Timestamp(TIME_ARRAY[i]);

			assertTrue(theTimestamp.equals(theTimestamp2));
		} // end for

		Object theTest = new Timestamp(TIME_COMPARE);

		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);

			assertFalse(theTimestamp.equals(theTest));
		} // end for

		Object nastyTest = new String("Test ");
		Timestamp theTimestamp = new Timestamp(TIME_ARRAY[1]);
		assertFalse(theTimestamp.equals(nastyTest));

	} // end method testequalsObject

	/*
	 * Method test for before
	 */
	public void testBeforeTimestamp() {
		Timestamp theTest = new Timestamp(TIME_LATE);

		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);

			assertTrue(theTimestamp.before(theTest));
		} // end for

		theTest = new Timestamp(TIME_EARLY);

		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);

			assertFalse(theTimestamp.before(theTest));
		} // end for

		for (int i = 0; i < TIME_ARRAY.length; i++) {
			theTest = new Timestamp(TIME_ARRAY[i]);
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);

			assertFalse(theTimestamp.before(theTest));
			theTest.setNanos(theTest.getNanos() + 1);
			assertTrue(theTimestamp.before(theTest));
		} // end for

	} // end method testbeforeTimestamp

	/*
	 * Method test for after
	 */
	public void testAfterTimestamp() {
		Timestamp theTest = new Timestamp(TIME_LATE);

		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);

			assertFalse(theTimestamp.after(theTest));
		} // end for

		theTest = new Timestamp(TIME_EARLY);

		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);

			assertTrue(theTimestamp.after(theTest));
		} // end for

		for (int i = 0; i < TIME_ARRAY.length; i++) {
			theTest = new Timestamp(TIME_ARRAY[i]);
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);

			assertFalse(theTimestamp.after(theTest));
			theTimestamp.setNanos(theTimestamp.getNanos() + 1);
			assertTrue(theTimestamp.after(theTest));
		} // end for

	} // end method testafterTimestamp

	/*
	 * Method test for compareTo
	 */
	public void testCompareToTimestamp() {
		Timestamp theTest = new Timestamp(TIME_EARLY);
		Timestamp theTest2 = new Timestamp(TIME_LATE);

		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);
			Timestamp theTimestamp2 = new Timestamp(TIME_ARRAY[i]);

			assertTrue(theTimestamp.compareTo(theTest) > 0);
			assertTrue(theTimestamp.compareTo(theTest2) < 0);
			assertEquals(0, theTimestamp.compareTo(theTimestamp2));
		} // end for

	} // end method testcompareToTimestamp

	/*
	 * Method test for compareTo
	 */
	public void testCompareToObject() {
		Object theTest = new Timestamp(TIME_EARLY);
		Object theTest2 = new Timestamp(TIME_LATE);

		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Timestamp theTimestamp = new Timestamp(TIME_ARRAY[i]);
			Object theTimestamp2 = new Timestamp(TIME_ARRAY[i]);

			assertTrue(theTimestamp.compareTo(theTest) > 0);
			assertTrue(theTimestamp.compareTo(theTest2) < 0);
			assertEquals(0, theTimestamp.compareTo(theTimestamp2));
		} // end for

		Object nastyTest = new String("Test ");
		Timestamp theTimestamp = new Timestamp(TIME_ARRAY[1]);
		try {
			theTimestamp.compareTo(nastyTest);
			fail(
					"testCompareToObject: Did not get expected ClassCastException");
		} catch (ClassCastException e) {
			// Should get here
			/*
			 * System.out.println("testCompareToObject: ClassCastException as
			 * expected"); System.out.println("Exception message: " +
			 * e.getMessage());
			 */
		} // end try

	} // end method testcompareToObject

} // end class TimestampTest
