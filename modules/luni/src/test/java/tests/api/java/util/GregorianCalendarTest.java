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

import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;

public class GregorianCalendarTest extends junit.framework.TestCase {

	/**
	 * @tests java.util.GregorianCalendar#GregorianCalendar()
	 */
	public void test_Constructor() {
		// Test for method java.util.GregorianCalendar()
		assertTrue("Constructed incorrect calendar", (new GregorianCalendar()
				.isLenient()));
	}

	/**
	 * @tests java.util.GregorianCalendar#GregorianCalendar(int, int, int)
	 */
	public void test_ConstructorIII() {
		// Test for method java.util.GregorianCalendar(int, int, int)
		GregorianCalendar gc = new GregorianCalendar(1972, Calendar.OCTOBER, 13);
		assertTrue("Incorrect calendar constructed 1",
				gc.get(Calendar.YEAR) == 1972);
		assertTrue("Incorrect calendar constructed 2",
				gc.get(Calendar.MONTH) == Calendar.OCTOBER);
		assertTrue("Incorrect calendar constructed 3", gc
				.get(Calendar.DAY_OF_MONTH) == 13);
		assertTrue("Incorrect calendar constructed 4", gc.getTimeZone().equals(
				TimeZone.getDefault()));
	}

	/**
	 * @tests java.util.GregorianCalendar#GregorianCalendar(int, int, int, int,
	 *        int)
	 */
	public void test_ConstructorIIIII() {
		// Test for method java.util.GregorianCalendar(int, int, int, int, int)
		GregorianCalendar gc = new GregorianCalendar(1972, Calendar.OCTOBER,
				13, 19, 9);
		assertTrue("Incorrect calendar constructed",
				gc.get(Calendar.YEAR) == 1972);
		assertTrue("Incorrect calendar constructed",
				gc.get(Calendar.MONTH) == Calendar.OCTOBER);
		assertTrue("Incorrect calendar constructed", gc
				.get(Calendar.DAY_OF_MONTH) == 13);
		assertTrue("Incorrect calendar constructed", gc.get(Calendar.HOUR) == 7);
		assertTrue("Incorrect calendar constructed",
				gc.get(Calendar.AM_PM) == 1);
		assertTrue("Incorrect calendar constructed",
				gc.get(Calendar.MINUTE) == 9);
		assertTrue("Incorrect calendar constructed", gc.getTimeZone().equals(
				TimeZone.getDefault()));
	}

	/**
	 * @tests java.util.GregorianCalendar#GregorianCalendar(int, int, int, int,
	 *        int, int)
	 */
	public void test_ConstructorIIIIII() {
		// Test for method java.util.GregorianCalendar(int, int, int, int, int,
		// int)
		GregorianCalendar gc = new GregorianCalendar(1972, Calendar.OCTOBER,
				13, 19, 9, 59);
		assertTrue("Incorrect calendar constructed",
				gc.get(Calendar.YEAR) == 1972);
		assertTrue("Incorrect calendar constructed",
				gc.get(Calendar.MONTH) == Calendar.OCTOBER);
		assertTrue("Incorrect calendar constructed", gc
				.get(Calendar.DAY_OF_MONTH) == 13);
		assertTrue("Incorrect calendar constructed", gc.get(Calendar.HOUR) == 7);
		assertTrue("Incorrect calendar constructed",
				gc.get(Calendar.AM_PM) == 1);
		assertTrue("Incorrect calendar constructed",
				gc.get(Calendar.MINUTE) == 9);
		assertTrue("Incorrect calendar constructed",
				gc.get(Calendar.SECOND) == 59);
		assertTrue("Incorrect calendar constructed", gc.getTimeZone().equals(
				TimeZone.getDefault()));
	}

	/**
	 * @tests java.util.GregorianCalendar#GregorianCalendar(java.util.Locale)
	 */
	public void test_ConstructorLjava_util_Locale() {
		// Test for method java.util.GregorianCalendar(java.util.Locale)
		Date date = new Date();
		GregorianCalendar gcJapan = new GregorianCalendar(Locale.JAPAN);
		gcJapan.setTime(date);
		GregorianCalendar gcJapan2 = new GregorianCalendar(Locale.JAPAN);
		gcJapan2.setTime(date);
		GregorianCalendar gcItaly = new GregorianCalendar(Locale.ITALY);
		gcItaly.setTime(date);
		assertTrue("Locales not created correctly", gcJapan.equals(gcJapan2)
				&& !gcJapan.equals(gcItaly));
	}

	/**
	 * @tests java.util.GregorianCalendar#GregorianCalendar(java.util.TimeZone)
	 */
	public void test_ConstructorLjava_util_TimeZone() {
		// Test for method java.util.GregorianCalendar(java.util.TimeZone)
		Date date = new Date();
		TimeZone.getDefault();
		GregorianCalendar gc1 = new GregorianCalendar(TimeZone
				.getTimeZone("EST"));
		gc1.setTime(date);
		GregorianCalendar gc2 = new GregorianCalendar(TimeZone
				.getTimeZone("CST"));
		gc2.setTime(date);
		// CST is 1 hour before EST, add 1 to the CST time and convert to 0-12
		// value
		assertTrue("Incorrect calendar returned",
				gc1.get(Calendar.HOUR) == ((gc2.get(Calendar.HOUR) + 1) % 12));
	}

	/**
	 * @tests java.util.GregorianCalendar#GregorianCalendar(java.util.TimeZone,
	 *        java.util.Locale)
	 */
	public void test_ConstructorLjava_util_TimeZoneLjava_util_Locale() {
		// Test for method java.util.GregorianCalendar(java.util.TimeZone,
		// java.util.Locale)
		Date date = new Date();
		TimeZone.getDefault();
		GregorianCalendar gc1 = new GregorianCalendar(TimeZone
				.getTimeZone("EST"), Locale.JAPAN);
		gc1.setTime(date);
		GregorianCalendar gc2 = new GregorianCalendar(TimeZone
				.getTimeZone("EST"), Locale.JAPAN);
		gc2.setTime(date);
		GregorianCalendar gc3 = new GregorianCalendar(TimeZone
				.getTimeZone("CST"), Locale.ITALY);
		gc3.setTime(date);
		// CST is 1 hour before EST, add 1 to the CST time and convert to 0-12
		// value
		assertTrue("Incorrect calendar returned",
				gc1.get(Calendar.HOUR) == ((gc3.get(Calendar.HOUR) + 1) % 12));
		assertTrue("Locales not created correctly", gc1.equals(gc2)
				&& !gc1.equals(gc3));
	}

	/**
	 * @tests java.util.GregorianCalendar#add(int, int)
	 */
	public void test_addII() {
		// Test for method void java.util.GregorianCalendar.add(int, int)
		GregorianCalendar gc1 = new GregorianCalendar(1998, 11, 6);
		gc1.add(GregorianCalendar.YEAR, 1);
		assertTrue("Add failed to Increment",
				gc1.get(GregorianCalendar.YEAR) == 1999);

		gc1 = new GregorianCalendar(1999, Calendar.JULY, 31);
		gc1.add(Calendar.MONTH, 7);
		assertTrue("Wrong result year 1", gc1.get(Calendar.YEAR) == 2000);
		assertTrue("Wrong result month 1",
				gc1.get(Calendar.MONTH) == Calendar.FEBRUARY);
		assertTrue("Wrong result date 1", gc1.get(Calendar.DATE) == 29);

		gc1.add(Calendar.YEAR, -1);
		assertTrue("Wrong result year 2", gc1.get(Calendar.YEAR) == 1999);
		assertTrue("Wrong result month 2",
				gc1.get(Calendar.MONTH) == Calendar.FEBRUARY);
		assertTrue("Wrong result date 2", gc1.get(Calendar.DATE) == 28);

		gc1 = new GregorianCalendar(TimeZone.getTimeZone("EST"));
		gc1.set(1999, Calendar.APRIL, 3, 16, 0); // day before DST change
		gc1.add(Calendar.MILLISECOND, 24 * 60 * 60 * 1000);
		assertTrue("Wrong time after MILLISECOND change", gc1
				.get(Calendar.HOUR_OF_DAY) == 17);
		gc1.set(1999, Calendar.APRIL, 3, 16, 0); // day before DST change
		gc1.add(Calendar.SECOND, 24 * 60 * 60);
		assertTrue("Wrong time after SECOND change", gc1
				.get(Calendar.HOUR_OF_DAY) == 17);
		gc1.set(1999, Calendar.APRIL, 3, 16, 0); // day before DST change
		gc1.add(Calendar.MINUTE, 24 * 60);
		assertTrue("Wrong time after MINUTE change", gc1
				.get(Calendar.HOUR_OF_DAY) == 17);
		gc1.set(1999, Calendar.APRIL, 3, 16, 0); // day before DST change
		gc1.add(Calendar.HOUR, 24);
		assertTrue("Wrong time after HOUR change", gc1
				.get(Calendar.HOUR_OF_DAY) == 17);
		gc1.set(1999, Calendar.APRIL, 3, 16, 0); // day before DST change
		gc1.add(Calendar.HOUR_OF_DAY, 24);
		assertTrue("Wrong time after HOUR_OF_DAY change", gc1
				.get(Calendar.HOUR_OF_DAY) == 17);

		gc1.set(1999, Calendar.APRIL, 3, 16, 0); // day before DST change
		gc1.add(Calendar.AM_PM, 2);
		assertTrue("Wrong time after AM_PM change", gc1
				.get(Calendar.HOUR_OF_DAY) == 16);
		gc1.set(1999, Calendar.APRIL, 3, 16, 0); // day before DST change
		gc1.add(Calendar.DATE, 1);
		assertTrue("Wrong time after DATE change", gc1
				.get(Calendar.HOUR_OF_DAY) == 16);
		gc1.set(1999, Calendar.APRIL, 3, 16, 0); // day before DST change
		gc1.add(Calendar.DAY_OF_YEAR, 1);
		assertTrue("Wrong time after DAY_OF_YEAR change", gc1
				.get(Calendar.HOUR_OF_DAY) == 16);
		gc1.set(1999, Calendar.APRIL, 3, 16, 0); // day before DST change
		gc1.add(Calendar.DAY_OF_WEEK, 1);
		assertTrue("Wrong time after DAY_OF_WEEK change", gc1
				.get(Calendar.HOUR_OF_DAY) == 16);
		gc1.set(1999, Calendar.APRIL, 3, 16, 0); // day before DST change
		gc1.add(Calendar.WEEK_OF_YEAR, 1);
		assertTrue("Wrong time after WEEK_OF_YEAR change", gc1
				.get(Calendar.HOUR_OF_DAY) == 16);
		gc1.set(1999, Calendar.APRIL, 3, 16, 0); // day before DST change
		gc1.add(Calendar.WEEK_OF_MONTH, 1);
		assertTrue("Wrong time after WEEK_OF_MONTH change", gc1
				.get(Calendar.HOUR_OF_DAY) == 16);
		gc1.set(1999, Calendar.APRIL, 3, 16, 0); // day before DST change
		gc1.add(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
		assertTrue("Wrong time after DAY_OF_WEEK_IN_MONTH change", gc1
				.get(Calendar.HOUR_OF_DAY) == 16);

		gc1.clear();
		gc1.set(2000, Calendar.APRIL, 1, 23, 0);
		gc1.add(Calendar.DATE, 1);
		assertTrue("Wrong time after DATE change near DST boundary", gc1
				.get(Calendar.MONTH) == Calendar.APRIL
				&& gc1.get(Calendar.DATE) == 2
				&& gc1.get(Calendar.HOUR_OF_DAY) == 23);
	}

	/**
	 * @tests java.util.GregorianCalendar#equals(java.lang.Object)
	 */
	public void test_equalsLjava_lang_Object() {
		// Test for method boolean
		// java.util.GregorianCalendar.equals(java.lang.Object)
		GregorianCalendar gc1 = new GregorianCalendar(1998, 11, 6);
		GregorianCalendar gc2 = new GregorianCalendar(2000, 11, 6);
		GregorianCalendar gc3 = new GregorianCalendar(1998, 11, 6);
		assertTrue("Equality check failed", gc1.equals(gc3));
		assertTrue("Equality check failed", !gc1.equals(gc2));
		gc3.setGregorianChange(new Date());
		assertTrue("Different gregorian change", !gc1.equals(gc3));
	}

	/**
	 * @tests java.util.GregorianCalendar#getActualMaximum(int)
	 */
	public void test_getActualMaximumI() {
		// Test for method int java.util.GregorianCalendar.getActualMaximum(int)
		GregorianCalendar gc1 = new GregorianCalendar(1900, 1, 1);
		GregorianCalendar gc2 = new GregorianCalendar(1996, 1, 1);
		GregorianCalendar gc3 = new GregorianCalendar(1997, 1, 1);
		GregorianCalendar gc4 = new GregorianCalendar(2000, 1, 1);
		GregorianCalendar gc5 = new GregorianCalendar(2000, 9, 9);
		GregorianCalendar gc6 = new GregorianCalendar(2000, 3, 3);
		assertTrue("Wrong actual maximum value for DAY_OF_MONTH for Feb 1900",
				gc1.getActualMaximum(Calendar.DAY_OF_MONTH) == 28);
		assertTrue("Wrong actual maximum value for DAY_OF_MONTH for Feb 1996",
				gc2.getActualMaximum(Calendar.DAY_OF_MONTH) == 29);
		assertTrue("Wrong actual maximum value for DAY_OF_MONTH for Feb 1998",
				gc3.getActualMaximum(Calendar.DAY_OF_MONTH) == 28);
		assertTrue("Wrong actual maximum value for DAY_OF_MONTH for Feb 2000",
				gc4.getActualMaximum(Calendar.DAY_OF_MONTH) == 29);
		assertTrue("Wrong actual maximum value for DAY_OF_MONTH for Oct 2000",
				gc5.getActualMaximum(Calendar.DAY_OF_MONTH) == 31);
		assertTrue("Wrong actual maximum value for DAY_OF_MONTH for Apr 2000",
				gc6.getActualMaximum(Calendar.DAY_OF_MONTH) == 30);
		assertTrue("Wrong actual maximum value for MONTH", gc1
				.getActualMaximum(Calendar.MONTH) == Calendar.DECEMBER);
		assertTrue("Wrong actual maximum value for HOUR_OF_DAY", gc1
				.getActualMaximum(Calendar.HOUR_OF_DAY) == 23);
		assertTrue("Wrong actual maximum value for HOUR", gc1
				.getActualMaximum(Calendar.HOUR) == 11);
		assertTrue("Wrong actual maximum value for DAY_OF_WEEK_IN_MONTH", gc6
				.getActualMaximum(Calendar.DAY_OF_WEEK_IN_MONTH) == 4);
	}

	/**
	 * @tests java.util.GregorianCalendar#getActualMinimum(int)
	 */
	public void test_getActualMinimumI() {
		// Test for method int java.util.GregorianCalendar.getActualMinimum(int)
		GregorianCalendar gc1 = new GregorianCalendar(1900, 1, 1);
		new GregorianCalendar(1996, 1, 1);
		new GregorianCalendar(1997, 1, 1);
		new GregorianCalendar(2000, 1, 1);
		new GregorianCalendar(2000, 9, 9);
		GregorianCalendar gc6 = new GregorianCalendar(2000, 3, 3);
		assertTrue("Wrong actual minimum value for DAY_OF_MONTH for Feb 1900",
				gc1.getActualMinimum(Calendar.DAY_OF_MONTH) == 1);
		assertTrue("Wrong actual minimum value for MONTH", gc1
				.getActualMinimum(Calendar.MONTH) == Calendar.JANUARY);
		assertTrue("Wrong actual minimum value for HOUR_OF_DAY", gc1
				.getActualMinimum(Calendar.HOUR_OF_DAY) == 0);
		assertTrue("Wrong actual minimum value for HOUR", gc1
				.getActualMinimum(Calendar.HOUR) == 0);
		assertTrue("Wrong actual minimum value for DAY_OF_WEEK_IN_MONTH", gc6
				.getActualMinimum(Calendar.DAY_OF_WEEK_IN_MONTH) == -1);
	}

	/**
	 * @tests java.util.GregorianCalendar#getGreatestMinimum(int)
	 */
	public void test_getGreatestMinimumI() {
		// Test for method int
		// java.util.GregorianCalendar.getGreatestMinimum(int)
		GregorianCalendar gc = new GregorianCalendar();
		assertTrue("Wrong greatest minimum value for DAY_OF_MONTH", gc
				.getGreatestMinimum(Calendar.DAY_OF_MONTH) == 1);
		assertTrue("Wrong greatest minimum value for MONTH", gc
				.getGreatestMinimum(Calendar.MONTH) == Calendar.JANUARY);
		assertTrue("Wrong greatest minimum value for HOUR_OF_DAY", gc
				.getGreatestMinimum(Calendar.HOUR_OF_DAY) == 0);
		assertTrue("Wrong greatest minimum value for HOUR", gc
				.getGreatestMinimum(Calendar.HOUR) == 0);

		BitSet result = new BitSet();
		int[] min = { 0, 1, 0, 1, 0, 1, 1, 1, -1, 0, 0, 0, 0, 0, 0, -43200000,
				0 };
		for (int i = 0; i < min.length; i++) {
			if (gc.getGreatestMinimum(i) != min[i])
				result.set(i);
		}
		assertTrue("Wrong greatest min for " + result, result.length() == 0);
	}

	/**
	 * @tests java.util.GregorianCalendar#getGregorianChange()
	 */
	public void test_getGregorianChange() {
		// Test for method java.util.Date
		// java.util.GregorianCalendar.getGregorianChange()
		GregorianCalendar gc = new GregorianCalendar();
		GregorianCalendar returnedChange = new GregorianCalendar(TimeZone
				.getTimeZone("EST"));
		returnedChange.setTime(gc.getGregorianChange());
		assertTrue("Returned incorrect year",
				returnedChange.get(Calendar.YEAR) == 1582);
		assertTrue("Returned incorrect month", returnedChange
				.get(Calendar.MONTH) == Calendar.OCTOBER);
		assertTrue("Returned incorrect day of month", returnedChange
				.get(Calendar.DAY_OF_MONTH) == 4);
	}

	/**
	 * @tests java.util.GregorianCalendar#getLeastMaximum(int)
	 */
	public void test_getLeastMaximumI() {
		// Test for method int java.util.GregorianCalendar.getLeastMaximum(int)
		GregorianCalendar gc = new GregorianCalendar();
		assertTrue("Wrong least maximum value for DAY_OF_MONTH", gc
				.getLeastMaximum(Calendar.DAY_OF_MONTH) == 28);
		assertTrue("Wrong least maximum value for MONTH", gc
				.getLeastMaximum(Calendar.MONTH) == Calendar.DECEMBER);
		assertTrue("Wrong least maximum value for HOUR_OF_DAY", gc
				.getLeastMaximum(Calendar.HOUR_OF_DAY) == 23);
		assertTrue("Wrong least maximum value for HOUR", gc
				.getLeastMaximum(Calendar.HOUR) == 11);

		BitSet result = new BitSet();
		Vector values = new Vector();
		int[] max = { 1, 292269054, 11, 52, 4, 28, 365, 7, 4, 1, 11, 23, 59,
				59, 999, 43200000, 3600000 };
		for (int i = 0; i < max.length; i++) {
			if (gc.getLeastMaximum(i) != max[i]) {
				result.set(i);
				values.add(new Integer(gc.getLeastMaximum(i)));
			}
		}
		assertTrue("Wrong least max for " + result + " = " + values, result
				.length() == 0);
	}

	/**
	 * @tests java.util.GregorianCalendar#getMaximum(int)
	 */
	public void test_getMaximumI() {
		// Test for method int java.util.GregorianCalendar.getMaximum(int)
		GregorianCalendar gc = new GregorianCalendar();
		assertTrue("Wrong maximum value for DAY_OF_MONTH", gc
				.getMaximum(Calendar.DAY_OF_MONTH) == 31);
		assertTrue("Wrong maximum value for MONTH", gc
				.getMaximum(Calendar.MONTH) == Calendar.DECEMBER);
		assertTrue("Wrong maximum value for HOUR_OF_DAY", gc
				.getMaximum(Calendar.HOUR_OF_DAY) == 23);
		assertTrue("Wrong maximum value for HOUR",
				gc.getMaximum(Calendar.HOUR) == 11);

		BitSet result = new BitSet();
		Vector values = new Vector();
		int[] max = { 1, 292278994, 11, 53, 6, 31, 366, 7, 6, 1, 11, 23, 59,
				59, 999, 43200000, 3600000 };
		for (int i = 0; i < max.length; i++) {
			if (gc.getMaximum(i) != max[i]) {
				result.set(i);
				values.add(new Integer(gc.getMaximum(i)));
			}
		}
		assertTrue("Wrong max for " + result + " = " + values,
				result.length() == 0);
	}

	/**
	 * @tests java.util.GregorianCalendar#getMinimum(int)
	 */
	public void test_getMinimumI() {
		// Test for method int java.util.GregorianCalendar.getMinimum(int)
		GregorianCalendar gc = new GregorianCalendar();
		assertTrue("Wrong minimum value for DAY_OF_MONTH", gc
				.getMinimum(Calendar.DAY_OF_MONTH) == 1);
		assertTrue("Wrong minimum value for MONTH", gc
				.getMinimum(Calendar.MONTH) == Calendar.JANUARY);
		assertTrue("Wrong minimum value for HOUR_OF_DAY", gc
				.getMinimum(Calendar.HOUR_OF_DAY) == 0);
		assertTrue("Wrong minimum value for HOUR",
				gc.getMinimum(Calendar.HOUR) == 0);

		BitSet result = new BitSet();
		int[] min = { 0, 1, 0, 1, 0, 1, 1, 1, -1, 0, 0, 0, 0, 0, 0, -43200000,
				0 };
		for (int i = 0; i < min.length; i++) {
			if (gc.getMinimum(i) != min[i])
				result.set(i);
		}
		assertTrue("Wrong min for " + result, result.length() == 0);
	}

	/**
	 * @tests java.util.GregorianCalendar#isLeapYear(int)
	 */
	public void test_isLeapYearI() {
		// Test for method boolean java.util.GregorianCalendar.isLeapYear(int)
		GregorianCalendar gc = new GregorianCalendar(1998, 11, 6);
		assertTrue("Returned incorrect value for leap year", !gc
				.isLeapYear(1998));
		assertTrue("Returned incorrect value for leap year", gc
				.isLeapYear(2000));

	}

	/**
	 * @tests java.util.GregorianCalendar#roll(int, int)
	 */
	public void test_rollII() {
		// Test for method void java.util.GregorianCalendar.roll(int, int)
		GregorianCalendar gc = new GregorianCalendar(1972, Calendar.OCTOBER, 8,
				2, 5, 0);
		gc.roll(Calendar.DAY_OF_MONTH, -1);
		assertTrue("Failed to roll DAY_OF_MONTH down by 1", gc
				.equals(new GregorianCalendar(1972, Calendar.OCTOBER, 7, 2, 5,
						0)));
		gc = new GregorianCalendar(1972, Calendar.OCTOBER, 8, 2, 5, 0);
		gc.roll(Calendar.DAY_OF_MONTH, 25);
		assertTrue("Failed to roll DAY_OF_MONTH up by 25", gc
				.equals(new GregorianCalendar(1972, Calendar.OCTOBER, 2, 2, 5,
						0)));
		gc = new GregorianCalendar(1972, Calendar.OCTOBER, 8, 2, 5, 0);
		gc.roll(Calendar.DAY_OF_MONTH, -10);
		assertTrue("Failed to roll DAY_OF_MONTH down by 10", gc
				.equals(new GregorianCalendar(1972, Calendar.OCTOBER, 29, 2, 5,
						0)));
	}

	/**
	 * @tests java.util.GregorianCalendar#roll(int, boolean)
	 */
	public void test_rollIZ() {
		// Test for method void java.util.GregorianCalendar.roll(int, boolean)
		GregorianCalendar gc = new GregorianCalendar(1972, Calendar.OCTOBER,
				13, 19, 9, 59);
		gc.roll(Calendar.DAY_OF_MONTH, false);
		assertTrue("Failed to roll day_of_month down", gc
				.equals(new GregorianCalendar(1972, Calendar.OCTOBER, 12, 19,
						9, 59)));
		gc = new GregorianCalendar(1972, Calendar.OCTOBER, 13, 19, 9, 59);
		gc.roll(Calendar.DAY_OF_MONTH, true);
		assertTrue("Failed to roll day_of_month up", gc
				.equals(new GregorianCalendar(1972, Calendar.OCTOBER, 14, 19,
						9, 59)));
		gc = new GregorianCalendar(1972, Calendar.OCTOBER, 31, 19, 9, 59);
		gc.roll(Calendar.DAY_OF_MONTH, true);
		assertTrue("Failed to roll day_of_month up", gc
				.equals(new GregorianCalendar(1972, Calendar.OCTOBER, 1, 19, 9,
						59)));

		GregorianCalendar cal = new GregorianCalendar();
		int result;
		try {
			cal.roll(Calendar.ZONE_OFFSET, true);
			result = 0;
		} catch (IllegalArgumentException e) {
			result = 1;
		}
		assertTrue("ZONE_OFFSET roll", result == 1);
		try {
			cal.roll(Calendar.DST_OFFSET, true);
			result = 0;
		} catch (IllegalArgumentException e) {
			result = 1;
		}
		assertTrue("ZONE_OFFSET roll", result == 1);

		cal.set(2004, Calendar.DECEMBER, 31, 5, 0, 0);
		cal.roll(Calendar.WEEK_OF_YEAR, true);
		assertTrue("Wrong year: " + cal.getTime(),
				cal.get(Calendar.YEAR) == 2004);
		assertTrue("Wrong month: " + cal.getTime(),
				cal.get(Calendar.MONTH) == Calendar.JANUARY);
		assertTrue("Wrong date: " + cal.getTime(), cal.get(Calendar.DATE) == 9);
	}

	/**
	 * @tests java.util.GregorianCalendar#setGregorianChange(java.util.Date)
	 */
	public void test_setGregorianChangeLjava_util_Date() {
		// Test for method void
		// java.util.GregorianCalendar.setGregorianChange(java.util.Date)
		GregorianCalendar gc1 = new GregorianCalendar(1582, Calendar.OCTOBER,
				4, 0, 0);
		GregorianCalendar gc2 = new GregorianCalendar(1972, Calendar.OCTOBER,
				13, 0, 0);
		gc1.setGregorianChange(gc2.getTime());
		assertTrue("Returned incorrect value", gc2.getTime().equals(
				gc1.getGregorianChange()));
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
