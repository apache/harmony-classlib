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

package tests.api.java.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTest extends junit.framework.TestCase {

	/**
	 * @tests java.util.Date#Date()
	 */
	public void test_Constructor() {
		// Test for method java.util.Date()
		GregorianCalendar gc = new GregorianCalendar(1998, Calendar.OCTOBER,
				13, 19, 9);
		long oldTime = gc.getTime().getTime();
		long now = new Date().getTime();
		assertTrue("Created incorrect date: " + oldTime + " now: " + now,
				oldTime < now);
	}

	/**
	 * @tests java.util.Date#Date(int, int, int)
	 */
	public void test_ConstructorIII() {
		// Test for method java.util.Date(int, int, int)
		Date d1 = new Date(70, 0, 1); // the epoch + local time
		
		// the epoch + local time
		Date d2 = new Date(0 + d1.getTimezoneOffset() * 60 * 1000);
		
		assertTrue("Created incorrect date", d1.equals(d2));

		Date date = new Date(99, 5, 22);
		Calendar cal = new GregorianCalendar(1999, Calendar.JUNE, 22);
		assertTrue("Wrong time zone", date.equals(cal.getTime()));
	}

	/**
	 * @tests java.util.Date#Date(int, int, int, int, int)
	 */
	public void test_ConstructorIIIII() {
		// Test for method java.util.Date(int, int, int, int, int)
		
		// the epoch + local time + (1 hour and 1 minute)
		Date d1 = new Date(70, 0, 1, 1, 1); 

		// the epoch + local time + (1 hour and 1 minute)
		Date d2 = new Date(0 + d1.getTimezoneOffset() * 60 * 1000 + 60 * 60
				* 1000 + 60 * 1000);

		assertTrue("Created incorrect date", d1.equals(d2));
	}

	/**
	 * @tests java.util.Date#Date(int, int, int, int, int, int)
	 */
	public void test_ConstructorIIIIII() {
		// Test for method java.util.Date(int, int, int, int, int, int)
		
		// the epoch + local time + (1 hour and 1 minute + 1 second)
		Date d1 = new Date(70, 0, 1, 1, 1, 1); 
		
		// the epoch + local time + (1 hour and 1 minute + 1 second)
		Date d2 = new Date(0 + d1.getTimezoneOffset() * 60 * 1000 + 60 * 60
				* 1000 + 60 * 1000 + 1000);
		
		assertTrue("Created incorrect date", d1.equals(d2));
	}

	/**
	 * @tests java.util.Date#Date(long)
	 */
	public void test_ConstructorJ() {
		// Test for method java.util.Date(long)
		assertTrue("Used to test", true);
	}

	/**
	 * @tests java.util.Date#Date(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.util.Date(java.lang.String)
		Date d1 = new Date("January 1, 1970, 00:00:00 GMT"); // the epoch
		Date d2 = new Date(0); // the epoch
		assertTrue("Created incorrect date", d1.equals(d2));
	}

	/**
	 * @tests java.util.Date#after(java.util.Date)
	 */
	public void test_afterLjava_util_Date() {
		// Test for method boolean java.util.Date.after(java.util.Date)
		Date d1 = new Date(0);
		Date d2 = new Date(1900000);
		assertTrue("Older was returned as newer", d2.after(d1));
		assertTrue("Newer was returned as older", !d1.after(d2));
	}

	/**
	 * @tests java.util.Date#before(java.util.Date)
	 */
	public void test_beforeLjava_util_Date() {
		// Test for method boolean java.util.Date.before(java.util.Date)
		Date d1 = new Date(0);
		Date d2 = new Date(1900000);
		assertTrue("Older was returned as newer", !d2.before(d1));
		assertTrue("Newer was returned as older", d1.before(d2));
	}

	/**
	 * @tests java.util.Date#clone()
	 */
	public void test_clone() {
		// Test for method java.lang.Object java.util.Date.clone()
		Date d1 = new Date(100000);
		Date d2 = (Date) d1.clone();
		assertTrue(
				"Cloning date results in same reference--new date is equivalent",
				d1 != d2);
		assertTrue("Cloning date results unequal date", d1.equals(d2));
	}

	/**
	 * @tests java.util.Date#compareTo(java.lang.Object)
	 */
	public void test_compareToLjava_lang_Object() {
		// Test for method int java.util.Date.compareTo(java.lang.Object)
		final int someNumber = 10000;
		Date d1 = new Date(someNumber);
		Date d2 = new Date(someNumber);
		Date d3 = new Date(someNumber + 1);
		Date d4 = new Date(someNumber - 1);
		Integer i = new Integer(0);
		assertTrue("Comparing a date to itself did not answer zero", d1
				.compareTo((Object) d1) == 0);
		assertTrue("Comparing equal dates did not answer zero", d1
				.compareTo((Object) d2) == 0);
		assertTrue(
				"date1.compareTo(date2), where date1 > date2, did not result in 1",
				d1.compareTo((Object) d4) == 1);
		assertTrue(
				"date1.compareTo(date2), where date1 < date2, did not result in -1",
				d1.compareTo((Object) d3) == -1);
		try {
			d1.compareTo(i);
		} catch (ClassCastException e) {
			return;
		}
		fail(
				"Comparing a date to a non-date did not throw ClassCastException");
	}

	/**
	 * @tests java.util.Date#compareTo(java.util.Date)
	 */
	public void test_compareToLjava_util_Date() {
		// Test for method int java.util.Date.compareTo(java.util.Date)
		final int someNumber = 10000;
		Date d1 = new Date(someNumber);
		Date d2 = new Date(someNumber);
		Date d3 = new Date(someNumber + 1);
		Date d4 = new Date(someNumber - 1);
		assertTrue("Comparing a date to itself did not answer zero", d1
				.compareTo(d1) == 0);
		assertTrue("Comparing equal dates did not answer zero", d1
				.compareTo(d2) == 0);
		assertTrue(
				"date1.compareTo(date2), where date1 > date2, did not result in 1",
				d1.compareTo(d4) == 1);
		assertTrue(
				"date1.compareTo(date2), where date1 < date2, did not result in -1",
				d1.compareTo(d3) == -1);

	}

	/**
	 * @tests java.util.Date#equals(java.lang.Object)
	 */
	public void test_equalsLjava_lang_Object() {
		// Test for method boolean java.util.Date.equals(java.lang.Object)
		Date d1 = new Date(0);
		Date d2 = new Date(1900000);
		Date d3 = new Date(1900000);
		assertTrue("Equality test failed", d2.equals(d3));
		assertTrue("Equality test failed", !d1.equals(d2));
	}

	/**
	 * @tests java.util.Date#getDate()
	 */
	public void test_getDate() {
		// Test for method int java.util.Date.getDate()
		Date d = new GregorianCalendar(1998, Calendar.OCTOBER, 13, 19, 9)
				.getTime();
		assertTrue("Returned incorrect date", d.getDate() == 13);
	}

	/**
	 * @tests java.util.Date#getDay()
	 */
	public void test_getDay() {
		// Test for method int java.util.Date.getDay()
		Date d = new GregorianCalendar(1998, Calendar.OCTOBER, 13, 19, 9)
				.getTime();
		assertTrue("Returned incorrect day", d.getDay() == 2);
	}

	/**
	 * @tests java.util.Date#getHours()
	 */
	public void test_getHours() {
		// Test for method int java.util.Date.getHours()
		Date d = new GregorianCalendar(1998, Calendar.OCTOBER, 13, 19, 9)
				.getTime();
		assertTrue("Returned incorrect hours", d.getHours() == 19);
	}

	/**
	 * @tests java.util.Date#getMinutes()
	 */
	public void test_getMinutes() {
		// Test for method int java.util.Date.getMinutes()
		Date d = new GregorianCalendar(1998, Calendar.OCTOBER, 13, 19, 9)
				.getTime();
		assertTrue("Returned incorrect minutes", d.getMinutes() == 9);
	}

	/**
	 * @tests java.util.Date#getMonth()
	 */
	public void test_getMonth() {
		// Test for method int java.util.Date.getMonth()
		Date d = new GregorianCalendar(1998, Calendar.OCTOBER, 13, 19, 9)
				.getTime();
		assertTrue("Returned incorrect month", d.getMonth() == 9);
	}

	/**
	 * @tests java.util.Date#getSeconds()
	 */
	public void test_getSeconds() {
		// Test for method int java.util.Date.getSeconds()
		Date d = new GregorianCalendar(1998, Calendar.OCTOBER, 13, 19, 9)
				.getTime();
		assertTrue("Returned incorrect seconds", d.getSeconds() == 0);
	}

	/**
	 * @tests java.util.Date#getTime()
	 */
	public void test_getTime() {
		// Test for method long java.util.Date.getTime()
		Date d1 = new Date(0);
		Date d2 = new Date(1900000);
		assertTrue("Returned incorrect time", d2.getTime() == 1900000);
		assertTrue("Returned incorrect time", d1.getTime() == 0);
	}

	/**
	 * @tests java.util.Date#getTimezoneOffset()
	 */
	public void test_getTimezoneOffset() {
		// Test for method int java.util.Date.getTimezoneOffset()
		assertTrue("Used to test", true);
	}

	/**
	 * @tests java.util.Date#getYear()
	 */
	public void test_getYear() {
		// Test for method int java.util.Date.getYear()
		Date d = new GregorianCalendar(1998, Calendar.OCTOBER, 13, 19, 9)
				.getTime();
		assertTrue("Returned incorrect year", d.getYear() == 98);
	}

	/**
	 * @tests java.util.Date#hashCode()
	 */
	public void test_hashCode() {
		// Test for method int java.util.Date.hashCode()
		Date d1 = new Date(0);
		Date d2 = new Date(1900000);
		assertTrue("Returned incorrect hash", d2.hashCode() == 1900000);
		assertTrue("Returned incorrect hash", d1.hashCode() == 0);
	}

	/**
	 * @tests java.util.Date#parse(java.lang.String)
	 */
	public void test_parseLjava_lang_String() {
		// Test for method long java.util.Date.parse(java.lang.String)
		Date d = new Date(Date.parse("13 October 1998"));
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(d);
		assertTrue("Parsed incorrect month", cal.get(Calendar.MONTH) == 9);
		assertTrue("Parsed incorrect year", cal.get(Calendar.YEAR) == 1998);
		assertTrue("Parsed incorrect date", cal.get(Calendar.DATE) == 13);

		d = new Date(Date.parse("Jan-12 1999"));
		assertTrue("Wrong parsed date 1", d.equals(new GregorianCalendar(1999,
				0, 12).getTime()));
		d = new Date(Date.parse("Jan12-1999"));
		assertTrue("Wrong parsed date 2", d.equals(new GregorianCalendar(1999,
				0, 12).getTime()));
		d = new Date(Date.parse("Jan12 69-1"));
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));
		cal.clear();
		cal.set(1969, Calendar.JANUARY, 12, 1, 0);
		assertTrue("Wrong parsed date 3", d.equals(cal.getTime()));
		d = new Date(Date.parse("6:45:13 3/2/1200 MST"));
		cal.setTimeZone(TimeZone.getTimeZone("MST"));
		cal.clear();
		cal.set(1200, 2, 2, 6, 45, 13);
		assertTrue("Wrong parsed date 4", d.equals(cal.getTime()));
		d = new Date(Date.parse("Mon, 22 Nov 1999 12:52:06 GMT"));
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));
		cal.clear();
		cal.set(1999, Calendar.NOVEMBER, 22, 12, 52, 06);
		assertTrue("Wrong parsed date 5", d.equals(cal.getTime()));
	}

	/**
	 * @tests java.util.Date#setDate(int)
	 */
	public void test_setDateI() {
		// Test for method void java.util.Date.setDate(int)
		Date d = new GregorianCalendar(1998, Calendar.OCTOBER, 13, 19, 9)
				.getTime();
		d.setDate(23);
		assertTrue("Set incorrect date", d.getDate() == 23);
	}

	/**
	 * @tests java.util.Date#setHours(int)
	 */
	public void test_setHoursI() {
		// Test for method void java.util.Date.setHours(int)
		Date d = new GregorianCalendar(1998, Calendar.OCTOBER, 13, 19, 9)
				.getTime();
		d.setHours(23);
		assertTrue("Set incorrect hours", d.getHours() == 23);
	}

	/**
	 * @tests java.util.Date#setMinutes(int)
	 */
	public void test_setMinutesI() {
		// Test for method void java.util.Date.setMinutes(int)
		Date d = new GregorianCalendar(1998, Calendar.OCTOBER, 13, 19, 9)
				.getTime();
		d.setMinutes(45);
		assertTrue("Set incorrect mins", d.getMinutes() == 45);
	}

	/**
	 * @tests java.util.Date#setMonth(int)
	 */
	public void test_setMonthI() {
		// Test for method void java.util.Date.setMonth(int)
		Date d = new GregorianCalendar(1998, Calendar.OCTOBER, 13, 19, 9)
				.getTime();
		d.setMonth(0);
		assertTrue("Set incorrect month", d.getMonth() == 0);
	}

	/**
	 * @tests java.util.Date#setSeconds(int)
	 */
	public void test_setSecondsI() {
		// Test for method void java.util.Date.setSeconds(int)
		Date d = new GregorianCalendar(1998, Calendar.OCTOBER, 13, 19, 9)
				.getTime();
		d.setSeconds(13);
		assertTrue("Set incorrect seconds", d.getSeconds() == 13);
	}

	/**
	 * @tests java.util.Date#setTime(long)
	 */
	public void test_setTimeJ() {
		// Test for method void java.util.Date.setTime(long)
		Date d1 = new Date(0);
		Date d2 = new Date(1900000);
		d1.setTime(900);
		d2.setTime(890000);
		assertTrue("Returned incorrect time", d2.getTime() == 890000);
		assertTrue("Returned incorrect time", d1.getTime() == 900);
	}

	/**
	 * @tests java.util.Date#setYear(int)
	 */
	public void test_setYearI() {
		// Test for method void java.util.Date.setYear(int)
		Date d = new GregorianCalendar(1998, Calendar.OCTOBER, 13, 19, 9)
				.getTime();
		d.setYear(8);
		assertTrue("Set incorrect year", d.getYear() == 8);
	}

	/**
	 * @tests java.util.Date#toGMTString()
	 */
	public void test_toGMTString() {
		// Test for method java.lang.String java.util.Date.toGMTString()
		assertTrue("Did not convert epoch to GMT string correctly", new Date(0)
				.toGMTString().equals("1 Jan 1970 00:00:00 GMT"));
		assertTrue("Did not convert epoch + 1yr to GMT string correctly",
				new Date((long) 365 * 24 * 60 * 60 * 1000).toGMTString()
						.equals("1 Jan 1971 00:00:00 GMT"));
	}

	/**
	 * @tests java.util.Date#toString()
	 */
	public void test_toString() {
		// Test for method java.lang.String java.util.Date.toString()
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.YEAR, 1970);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date d = cal.getTime();
		String result = d.toString();
		assertTrue("Incorrect result: " + d, result
				.startsWith("Thu Jan 01 00:00:00")
				&& result.endsWith("1970"));

		TimeZone tz = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("EST"));
		try {
			Date d1 = new Date(0);
			assertTrue("Returned incorrect string: " + d1, d1.toString()
					.equals("Wed Dec 31 19:00:00 EST 1969"));
		} finally {
			TimeZone.setDefault(tz);
		}
	}

	/**
	 * @tests java.util.Date#UTC(int, int, int, int, int, int)
	 */
	public void test_UTCIIIIII() {
		// Test for method long java.util.Date.UTC(int, int, int, int, int, int)
		assertTrue("Returned incorrect UTC value for epoch", Date.UTC(70, 0, 1,
				0, 0, 0) == (long) 0);
		assertTrue("Returned incorrect UTC value for epoch +1yr", Date.UTC(71,
				0, 1, 0, 0, 0) == (long) 365 * 24 * 60 * 60 * 1000);
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
	}
}
