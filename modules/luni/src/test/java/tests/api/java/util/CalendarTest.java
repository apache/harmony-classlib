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
import java.util.TimeZone;

public class CalendarTest extends junit.framework.TestCase {

	/**
	 * @tests java.util.Calendar#set(int, int)
	 */
	public void test_setII() {
		// Test for correct result defined by the last set field
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("EST"));

		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		assertTrue("Incorrect result 0: " + cal.getTime().getTime(), cal
				.getTime().getTime() == 1009861200000L);

		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		assertTrue("Incorrect result 0a: " + cal.getTime(), cal.getTime()
				.getTime() == 1014958800000L);

		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.DATE, 24);
		assertTrue("Incorrect result 0b: " + cal.getTime(), cal.getTime()
				.getTime() == 1011848400000L);

		cal.set(Calendar.MONTH, Calendar.OCTOBER);
		cal.set(Calendar.DATE, 31);
		cal.set(Calendar.MONTH, Calendar.NOVEMBER);
		cal.set(Calendar.DATE, 26);
		assertTrue("Incorrect month: " + cal.get(Calendar.MONTH), cal
				.get(Calendar.MONTH) == Calendar.NOVEMBER);

		int dow = cal.get(Calendar.DAY_OF_WEEK);
		cal.set(Calendar.DATE, 27);
		assertTrue("Incorrect DAY_OF_WEEK: " + cal.get(Calendar.DAY_OF_WEEK)
				+ " expected: " + dow, cal.get(Calendar.DAY_OF_WEEK) != dow);

		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		assertTrue("Incorrect result 0c1: " + cal.getTime().getTime(), cal
				.getTime().getTime() == 1010379600000L);

		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		assertTrue("Incorrect result 0c2: " + cal.getTime().getTime(), cal
				.getTime().getTime() == 1009861200000L);

		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		assertTrue("Incorrect result 0c3: " + cal.getTime(), cal.getTime()
				.getTime() == 1010034000000L);

		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.WEEK_OF_MONTH, 2);
		assertTrue("Incorrect result 0d: " + cal.getTime(), cal.getTime()
				.getTime() == 1010293200000L);

		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.DAY_OF_WEEK_IN_MONTH, 2);
		assertTrue("Incorrect result 0e: " + cal.getTime(), cal.getTime()
				.getTime() == 1010898000000L);

		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.WEEK_OF_YEAR, 11);
		assertTrue("Incorrect result 0f: " + cal.getTime(), cal.getTime()
				.getTime() == 1015736400000L);

		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.DATE, 24);
		cal.set(Calendar.WEEK_OF_YEAR, 11);
		assertTrue("Incorrect result 0g: " + cal.getTime(), cal.getTime()
				.getTime() == 1011848400000L);

		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.get(Calendar.WEEK_OF_YEAR); // Force fields to compute
		cal.set(Calendar.WEEK_OF_YEAR, 11);
		assertTrue("Incorrect result 0h: " + cal.getTime(), cal.getTime()
				.getTime() == 1015909200000L);

		// WEEK_OF_YEAR has priority over MONTH/DATE
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.DAY_OF_YEAR, 170);
		cal.set(Calendar.WEEK_OF_YEAR, 11);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DATE, 5);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		assertTrue("Incorrect result 1: " + cal.getTime(), cal.getTime()
				.getTime() == 1015822800000L);

		// WEEK_OF_YEAR has priority over MONTH/DATE
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.WEEK_OF_YEAR, 11);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DATE, 5);
		cal.set(Calendar.DAY_OF_YEAR, 170);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		assertTrue("Incorrect result 1a: " + cal.getTime(), cal.getTime()
				.getTime() == 1015822800000L);

		// DAY_OF_WEEK has no effect when other fields not set
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DATE, 11);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		assertTrue("Incorrect result 1b: " + cal.getTime(), cal.getTime()
				.getTime() == 1015822800000L);

		// WEEK_OF_MONTH has priority
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.WEEK_OF_YEAR, 12);
		cal.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
		cal.set(Calendar.WEEK_OF_MONTH, 3);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DATE, 5);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		assertTrue("Incorrect result 2: " + cal.getTime(), cal.getTime()
				.getTime() == 1015822800000L);

		// DAY_OF_WEEK_IN_MONTH has priority over WEEK_OF_YEAR
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.WEEK_OF_YEAR, 12);
		cal.set(Calendar.DAY_OF_WEEK_IN_MONTH, 2);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DATE, 5);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		assertTrue("Incorrect result 3: " + cal.getTime(), cal.getTime()
				.getTime() == 1015822800000L);

		// WEEK_OF_MONTH has priority, MONTH not set
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.WEEK_OF_YEAR, 12);
		cal.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
		cal.set(Calendar.WEEK_OF_MONTH, 3);
		cal.set(Calendar.DATE, 25);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		assertTrue("Incorrect result 4: " + cal.getTime(), cal.getTime()
				.getTime() == 1010984400000L);

		// WEEK_OF_YEAR has priority when MONTH set last and DAY_OF_WEEK set
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.WEEK_OF_YEAR, 11);
		cal.set(Calendar.DATE, 25);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		assertTrue("Incorrect result 5: " + cal.getTime(), cal.getTime()
				.getTime() == 1015822800000L);

		// Use MONTH/DATE when WEEK_OF_YEAR set but not DAY_OF_WEEK
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.WEEK_OF_YEAR, 12);
		cal.set(Calendar.DATE, 11);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		assertTrue("Incorrect result 5a: " + cal.getTime(), cal.getTime()
				.getTime() == 1015822800000L);

		// Use MONTH/DATE when DAY_OF_WEEK is not set
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.WEEK_OF_YEAR, 12);
		cal.set(Calendar.DATE, 11);
		cal.set(Calendar.WEEK_OF_MONTH, 1);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		assertTrue("Incorrect result 5b: " + cal.getTime(), cal.getTime()
				.getTime() == 1015822800000L);

		// WEEK_OF_MONTH has priority
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.WEEK_OF_YEAR, 12);
		cal.set(Calendar.DATE, 5);
		cal.set(Calendar.WEEK_OF_MONTH, 3);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		assertTrue("Incorrect result 5c: " + cal.getTime(), cal.getTime()
				.getTime() == 1015822800000L);

		// DATE has priority when set last
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.WEEK_OF_YEAR, 12);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DATE, 11);
		assertTrue("Incorrect result 6: " + cal.getTime(), cal.getTime()
				.getTime() == 1015822800000L);

		// DATE has priority when set last, MONTH not set
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.WEEK_OF_YEAR, 12);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.DATE, 14);
		assertTrue("Incorrect result 7: " + cal.getTime(), cal.getTime()
				.getTime() == 1010984400000L);

		// DAY_OF_YEAR has priority when MONTH set last and DATE not set
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.DAY_OF_YEAR, 70);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		assertTrue("Incorrect result 8: " + cal.getTime(), cal.getTime()
				.getTime() == 1015822800000L);

		// DAY/MONTH has priority when DATE set after DAY_OF_YEAR
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.DAY_OF_YEAR, 170);
		cal.set(Calendar.DATE, 11);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		assertTrue("Incorrect result 8a: " + cal.getTime(), cal.getTime()
				.getTime() == 1015822800000L);

		// DAY_OF_YEAR has priority when set after DATE
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.DATE, 15);
		cal.set(Calendar.DAY_OF_YEAR, 70);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		assertTrue("Incorrect result 8b: " + cal.getTime(), cal.getTime()
				.getTime() == 1015822800000L);

		// DATE has priority when set last
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.DAY_OF_YEAR, 70);
		cal.set(Calendar.DATE, 14);
		assertTrue("Incorrect result 9: " + cal.getTime(), cal.getTime()
				.getTime() == 1010984400000L);

		// DATE has priority when set last
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.WEEK_OF_YEAR, 15);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		cal.set(Calendar.DATE, 14);
		assertTrue("Incorrect result 9a: " + cal.getTime(), cal.getTime()
				.getTime() == 1010984400000L);

		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.DATE, 14);
		cal.set(Calendar.WEEK_OF_YEAR, 11);
		assertTrue("Incorrect result 9b: " + cal.getTime(), cal.getTime()
				.getTime() == 1015822800000L);

		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.DATE, 14);
		cal.set(Calendar.WEEK_OF_YEAR, 11);
		assertTrue("Incorrect result 9c: " + cal.getTime(), cal.getTime()
				.getTime() == 1010984400000L);

		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.WEEK_OF_MONTH, 1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DATE, 11);
		assertTrue("Incorrect result 9d: " + cal.getTime(), cal.getTime()
				.getTime() == 1015822800000L);

		// DAY_OF_YEAR has priority when DAY_OF_MONTH set last and other fields
		// not set
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.DAY_OF_YEAR, 70);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		assertTrue("Incorrect result 10: " + cal.getTime(), cal.getTime()
				.getTime() == 1015822800000L);

		// MONTH/DATE has priority when DAY_OF_WEEK_IN_MONTH set last but
		// DAY_OF_WEEK not set
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.DATE, 11);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
		assertTrue("Incorrect result 11: " + cal.getTime(), cal.getTime()
				.getTime() == 1015822800000L);

		// MONTH/DATE has priority when WEEK_OF_YEAR set last but DAY_OF_WEEK
		// not set
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.DATE, 11);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.WEEK_OF_YEAR, 15);
		assertTrue("Incorrect result 12: " + cal.getTime(), cal.getTime()
				.getTime() == 1015822800000L);

		// MONTH/DATE has priority when WEEK_OF_MONTH set last but DAY_OF_WEEK
		// not set
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.DATE, 11);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.WEEK_OF_MONTH, 1);
		assertTrue("Incorrect result 13: " + cal.getTime(), cal.getTime()
				.getTime() == 1015822800000L);

		// Ensure last date field set is reset after computing
		cal.clear();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.DAY_OF_YEAR, 111);
		cal.get(Calendar.YEAR);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		assertTrue("Incorrect result 14: " + cal.getTime(), cal.getTime()
				.getTime() == 1016686800000L);

		int hour = cal.get(Calendar.HOUR);
		int ampm = cal.get(Calendar.AM_PM);
		int setAMPMValue = ampm == 0 ? 1 : 0;
		cal.set(Calendar.HOUR, hour);
		cal.set(Calendar.AM_PM, setAMPMValue);
		assertTrue("AM_PM not changed", cal.get(Calendar.AM_PM) == setAMPMValue);
		// setting AM_PM without HOUR should not have any affect
		cal.set(Calendar.AM_PM, ampm);
		assertTrue("AM_PM was changed 1",
				cal.get(Calendar.AM_PM) == setAMPMValue);
		int hourOfDay = cal.get(Calendar.HOUR_OF_DAY);
		cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
		cal.set(Calendar.AM_PM, ampm);
		assertTrue("AM_PM was changed 2",
				cal.get(Calendar.AM_PM) == setAMPMValue);
	}

	/**
	 * @tests java.util.Calendar#setTime(java.util.Date)
	 */
	public void test_setTimeLjava_util_Date() {
		Calendar cal = Calendar.getInstance();
		// Use millisecond time for testing in Core
		cal.setTime(new Date(884581200000L)); // (98, Calendar.JANUARY, 12)
		assertEquals("incorrect millis", 884581200000L, cal.getTime().getTime());
		cal.setTimeZone(TimeZone.getTimeZone("EST"));
		cal.setTime(new Date(943506000000L)); // (99, Calendar.NOVEMBER, 25)
		assertTrue("incorrect fields", cal.get(Calendar.YEAR) == 1999
				&& cal.get(Calendar.MONTH) == Calendar.NOVEMBER
				&& cal.get(Calendar.DATE) == 25);
	}

	protected void setUp() {
	}

	protected void tearDown() {
	}
}
