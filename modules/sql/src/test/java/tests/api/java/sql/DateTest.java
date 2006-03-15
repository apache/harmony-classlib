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

package tests.api.java.sql;

import java.sql.Date;
import java.util.Calendar;
import java.util.TimeZone;

import junit.framework.TestCase;

/**
 * JUnit Testcase for the java.sql.Date class
 * 
 */
public class DateTest extends TestCase {

	// A calendar object created in the GMT time zone
	static Calendar aCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

	// Some interesting millisecond time values
	// These millisecond times are all in GMT, effectively
	static long TIME_AN_HOUR = 3600000; // 1000 * 60 * 60 ms

	static long TIME_EPOCH = 0;

	static long TIME_NOW = System.currentTimeMillis();

	static long TIME_NEGATIVE = -3600001;

	static long TIME_TESTDATE1 = getTime(1999, Calendar.DECEMBER, 31, 23, 59,
			59);

	static long TIME_TESTDATE2 = getTime(2010, Calendar.JUNE, 10, 20, 3, 16);

	static long TIME_TESTDATE3 = getTime(1931, Calendar.APRIL, 21, 1, 25, 1);

	static long TIME_LOWERLIMIT = Long.MIN_VALUE;

	static long TIME_UPPERLIMIT = Long.MAX_VALUE;

	// Date strings
	static String SQL_DATESTRING1 = "1999-12-31";

	static String SQL_DATESTRING2 = "2010-06-10";

	static String SQL_DATESTRING3 = "1931-04-21";

	static String SQL_EPOCHSTRING = "1970-01-01";

	static String SQL_DATEDAY1 = "1970-01-02";

	static String SQL_NEGATIVE = "1969-12-31";

	static long[] TIME_ARRAY = new long[] { TIME_TESTDATE1, TIME_TESTDATE2,
			TIME_TESTDATE3, TIME_NEGATIVE, TIME_EPOCH };

	// Date string array for London (GMT)
	static String[] SQL_DATEARRAY = new String[] { SQL_DATESTRING1,
			SQL_DATESTRING2, SQL_DATESTRING3, SQL_NEGATIVE, SQL_EPOCHSTRING };

	// Date string array for New York - sometimes a day earlier than London
	static String[] SQL_NYARRAY = new String[] { "1999-12-31", "2010-06-10",
			"1931-04-20", "1969-12-31", "1969-12-31" };

	// Date string for Tokyo
	static String[] SQL_JAPANARRAY = new String[] { "2000-01-01", "2010-06-11",
			"1931-04-21", "1970-01-01", "1970-01-01" };

	static String[][] SQL_TZ_DATEARRAYS = new String[][] { SQL_DATEARRAY,
			SQL_NYARRAY, SQL_JAPANARRAY };

	// Timezones
	static String TZ_LONDON = "Europe/London"; // Note: != GMT

	static String TZ_PACIFIC = "America/Los_Angeles"; // GNT - 8

	static String TZ_JAPAN = "Asia/Tokyo"; // GMT + 9

	static String[] TIMEZONES = { TZ_LONDON, TZ_PACIFIC, TZ_JAPAN };

	/*
	 * Helper method to create a long milliseconds time from a supplied date and
	 * time
	 */
	static private long getTime(int year, int month, int date, int hour,
			int minute, int second) {
		aCal.set(year, month, date, hour, minute, second);
		return aCal.getTimeInMillis();
	} // end method getTime( int, int, int, int, int, int )

	/*
	 * Test of the Date(int, int, int) constructor - now deprecated but still
	 * funtioning
	 */
	public void testDateintintint() {

		int init1[] = { 99, 8099, 9000, 99999, 99, 99, -1, -100 };
		int init2[] = { 11, 0, 0, 0, 999, 0, 0, -111 };
		int init3[] = { 31, 0, 0, 0, 0, 999, 0, -999 };

		Exception theExceptions[] = { null, null, null, null, null, null, null,
				null };

		for (int i = 0; i < init1.length; i++) {
			try {
				Date theDate = new Date(init1[i], init2[i], init3[i]);

				assertTrue(theDate != null);
				if (theExceptions[i] != null) {
					fail(i + "Exception expected - none thrown.");
				} // end if
			} catch (Exception e) {
				if (theExceptions[i] != null) {
					assertEquals(i + "Incorrect exception generated: ",
							theExceptions[i].getClass(), e.getClass());
				} else {
					fail(i + "Exception: " + e.getClass()
							+ " not expected");
				} // end if
			} // end try
		} // end for

	} // end method testDateintintint

	/*
	 * Test of the Date( long ) constructor
	 */
	public void testDatelong() {

		long init1[] = { TIME_TESTDATE1, TIME_TESTDATE2, TIME_TESTDATE3,
				TIME_NEGATIVE, TIME_LOWERLIMIT, TIME_UPPERLIMIT, TIME_EPOCH,
				TIME_NOW };

		Exception theExceptions[] = { null, null, null, null, null, null, null,
				null };

		for (int i = 0; i < init1.length; i++) {
			try {
				Date theDate = new Date(init1[i]);

				assertTrue(theDate != null);
				if (theExceptions[i] != null) {
					fail(i + "Exception expected - none thrown.");
				} // end if
			} catch (Exception e) {
				if (theExceptions[i] != null) {
					assertEquals(i + "Incorrect exception generated: ",
							theExceptions[i].getClass(), e.getClass());
				} else {
					fail(i + "Exception: " + e.getClass()
							+ " not expected");
				} // end if
			} // end try
		} // end for

	} // end method testDatelong

	/*
	 * Test of the (deprecated) int Date.getHours() method - which always throws
	 * an IllegalArgumentException
	 */
	public void testGetHours() {
		Date theDate = new Date(TIME_TESTDATE1);

		try {
			int theHours = theDate.getHours();

			// If it worked, it should get the Hours setting
			assertTrue(theHours == 23);
			assertTrue(false);
		} catch (IllegalArgumentException ie) {
			/*
			 * System.out.println("getHours: IllegalArgumentException thrown as
			 * expected");
			 */
		} // end try
	} // end method testGetHours()

	/*
	 * Test of the (deprecated) int Date.getMinutes() method - which always
	 * throws an IllegalArgumentException
	 */
	public void testGetMinutes() {
		Date theDate = new Date(TIME_TESTDATE1);

		try {
			int theMinutes = theDate.getMinutes();

			// If it worked, it should get the Hours setting
			assertTrue(theMinutes == 59);
			assertTrue(false);
		} catch (IllegalArgumentException ie) {
			/*
			 * System.out.println("getMinutes: IllegalArgumentException thrown
			 * as expected");
			 */
		} // end try
	} // end method testGetMinutes()

	/*
	 * Test of the (deprecated) int Date.getSeconds() method - which always
	 * throws an IllegalArgumentException
	 */
	public void testGetSeconds() {
		Date theDate = new Date(TIME_TESTDATE1);

		try {
			int theSeconds = theDate.getSeconds();

			// If it worked, it should get the Hours setting
			assertTrue(theSeconds == 23);
			assertTrue(false);
		} catch (IllegalArgumentException ie) {
			/*
			 * System.out.println("getSeconds: IllegalArgumentException thrown
			 * as expected");
			 */
		} // end try
	} // end method testGetSeconds()

	/*
	 * Test of the (deprecated) Date.setHours( int ) method - which always
	 * throws an IllegalArgumentException
	 */
	public void testSetHours() {
		Date theDate = new Date(TIME_TESTDATE1);

		try {
			theDate.setHours(22);

			// If it worked, this is incorrect
			assertTrue(false);
		} catch (IllegalArgumentException ie) {
			/*
			 * System.out.println("setHours: IllegalArgumentException thrown as
			 * expected");
			 */
		} // end try
	} // end method testSetHours( int )

	/*
	 * Test of the (deprecated) Date.setMinutes( int ) method - which always
	 * throws an IllegalArgumentException
	 */
	public void testSetMinutes() {
		Date theDate = new Date(TIME_TESTDATE1);

		try {
			theDate.setMinutes(54);

			// If it worked, this is incorrect
			assertTrue(false);
		} catch (IllegalArgumentException ie) {
			/*
			 * System.out.println("setMinutes: IllegalArgumentException thrown
			 * as expected");
			 */
		} // end try

	} // end method testSetMinutes( int )

	/*
	 * Test of the (deprecated) Date.setSeconds( int ) method - which always
	 * throws an IllegalArgumentException
	 */
	public void testSetSeconds() {
		Date theDate = new Date(TIME_TESTDATE1);

		try {
			theDate.setSeconds(36);

			// If it worked, this is incorrect
			assertTrue(false);
		} catch (IllegalArgumentException ie) {
			/*
			 * System.out.println("setSeconds: IllegalArgumentException thrown
			 * as expected");
			 */
		} // end try
	} // end method testSetSeconds( int )

	/*
	 * Test of the String Date.toString() method This method is sensitive to the
	 * time zone setting and this test sets the time zone before calling the
	 * toString() method.
	 */
	public void testToString() {
		// This test is set up for GMT time zone, so need to set the time zone
		// to GMT first
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));

		for (int i = 0; i < TIME_ARRAY.length; i++) {
			Date theDate = new Date(TIME_ARRAY[i]);

			String theString = theDate.toString();

			assertTrue(theString.equals(SQL_DATEARRAY[i]));
		} // end for

	} // end method testToString()

	/*
	 * Test of the void setTime(int) method This does depend on the Time Zone
	 * settings and sets up the time zone to one of a group of specific time
	 * zones and tests the method using each of these time zones in turn.
	 */
	public void testSetTimelong() {

		// Loop over the array of test timezones
		for (int i = 0; i < TIMEZONES.length; i++) {
			testSetTimelong(TIMEZONES[i], SQL_TZ_DATEARRAYS[i]);
		} // end for

	} // end method testSetTimelong()

	/*
	 * Internal method for testing Date.setTime with a specific time zone
	 */
	private void testSetTimelong(String timeZoneName, String[] dateArray) {

		if (timeZoneName != null) {
			TimeZone.setDefault(TimeZone.getTimeZone(timeZoneName));
			/*
			 * System.out.println("Timezone set to: " +
			 * TimeZone.getDefault().getDisplayName() );
			 */
		} // end if

		Date theDate = new Date(TIME_TESTDATE1);

		// Loop over the array of test times & dates
		for (int i = 0; i < dateArray.length; i++) {
			theDate.setTime(TIME_ARRAY[i]);
			String theString = theDate.toString();
			assertTrue(theString.equals(dateArray[i]));
		} // end for

	} // end method testSetTimelong()

	/*
	 * Test of the Date.valueOf( String ) method This test is not dependent on
	 * the default Time Zone setting
	 */
	public void testValueOf() {
		String SQL_NOTVALID1 = "ABCDEF"; // Invalid date string
		String SQL_NOTVALID2 = "12321.43.56"; // Invalid date string
		String SQL_NOTVALID3 = null; // Invalid date string
		String[] SQL_INVALIDARRAY = { SQL_NOTVALID1, SQL_NOTVALID2,
				SQL_NOTVALID3 };
		Exception[] theExceptions = { new IllegalArgumentException(),
				new IllegalArgumentException(), new IllegalArgumentException() };

		Date theDate;

		// Cases where the input date string is a valid format
		for (int i = 0; i < SQL_DATEARRAY.length; i++) {
			theDate = Date.valueOf(SQL_DATEARRAY[i]);

			assertTrue(theDate.toString().equals(SQL_DATEARRAY[i]));
		} // end for

		// Cases where the input date string has an invalid format
		for (int i = 0; i < SQL_INVALIDARRAY.length; i++) {
			try {
				theDate = Date.valueOf(SQL_INVALIDARRAY[i]);
				// Shouldn't get here
				assertTrue(false);
			} catch (Exception e) {
				// System.out.println("DateTest.testValueOf: Exception thrown: "
				// + e.toString() );
				// System.out.println("DateTest.testValueOf: Exception data: " +
				// e.getMessage() );
				assertTrue(e.getClass().equals(theExceptions[i].getClass()));
				assertTrue(e.getMessage() == theExceptions[i].getMessage());
			} // end try
		} // end for

	} // end method testValueOf()

} // end class DateTest


