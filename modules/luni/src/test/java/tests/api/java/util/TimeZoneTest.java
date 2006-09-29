/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package tests.api.java.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import tests.support.Support_TimeZone;

public class TimeZoneTest extends junit.framework.TestCase {

	private static final int ONE_HOUR = 3600000;

	/**
	 * @tests java.util.TimeZone#getDefault()
	 */
	public void test_getDefault() {
		assertTrue("returns identical", TimeZone.getDefault() != TimeZone
				.getDefault());
	}

	/**
	 * @tests java.util.TimeZone#getDSTSavings()
	 */
	public void test_getDSTSavings() {
		// Test for method int java.util.TimeZone.getDSTSavings()

		// test on subclass SimpleTimeZone
		TimeZone st1 = TimeZone.getTimeZone("EST");
		assertEquals("T1A. Incorrect daylight savings returned, ", ONE_HOUR,
				st1.getDSTSavings());

		// a SimpleTimeZone with daylight savings different then 1 hour
		st1 = TimeZone.getTimeZone("Australia/Lord_Howe");
		assertEquals("T1B. Incorrect daylight savings returned, ", 1800000, st1
				.getDSTSavings());

		// test on subclass Support_TimeZone, an instance with daylight savings
		TimeZone tz1 = new Support_TimeZone(-5 * ONE_HOUR, true);
		assertEquals("T2. Incorrect daylight savings returned, ", ONE_HOUR, tz1
				.getDSTSavings());

		// an instance without daylight savings
		tz1 = new Support_TimeZone(3 * ONE_HOUR, false);
		assertEquals("T3. Incorrect daylight savings returned, ", 0, tz1
				.getDSTSavings());
	}

	/**
	 * @tests java.util.TimeZone#getOffset(long)
	 */
	public void test_getOffset_long() {
		// Test for method int java.util.TimeZone.getOffset(long time)

		// test on subclass SimpleTimeZone
		TimeZone st1 = TimeZone.getTimeZone("EST");
		long time1 = new GregorianCalendar(1998, Calendar.NOVEMBER, 11)
				.getTimeInMillis();
		assertEquals("T1. Incorrect offset returned, ", -(5 * ONE_HOUR), st1
				.getOffset(time1));

		long time2 = new GregorianCalendar(1998, Calendar.JUNE, 11)
				.getTimeInMillis();
		st1 = TimeZone.getTimeZone("EST");
		assertEquals("T2. Incorrect offset returned, ", -(4 * ONE_HOUR), st1
				.getOffset(time2));

		// test on subclass Support_TimeZone, an instance with daylight savings
		TimeZone tz1 = new Support_TimeZone(-5 * ONE_HOUR, true);
		assertEquals("T3. Incorrect offset returned, ", -(5 * ONE_HOUR), tz1
				.getOffset(time1));
		assertEquals("T4. Incorrect offset returned, ", -(4 * ONE_HOUR), tz1
				.getOffset(time2));

		// an instance without daylight savings
		tz1 = new Support_TimeZone(3 * ONE_HOUR, false);
		assertEquals("T5. Incorrect offset returned, ", (3 * ONE_HOUR), tz1
				.getOffset(time1));
		assertEquals("T6. Incorrect offset returned, ", (3 * ONE_HOUR), tz1
				.getOffset(time2));
	}

	/**
	 * @tests java.util.TimeZone#getTimeZone(java.lang.String)
	 */
	public void test_getTimeZoneLjava_lang_String() {
		assertEquals(
				"Must return GMT when given an invalid TimeZone id SMT-8.",
				TimeZone.getTimeZone("SMT-8").getID(), "GMT");
		assertEquals(
				"Must return GMT when given an invalid TimeZone time GMT+28:70.",
				TimeZone.getTimeZone("GMT+28:70").getID(), "GMT");
		assertEquals(
				"Must return GMT when given an invalid TimeZone time GMT+28:30.",
				TimeZone.getTimeZone("GMT+28:30").getID(), "GMT");
		assertEquals(
				"Must return GMT when given an invalid TimeZone time GMT+8:70.",
				TimeZone.getTimeZone("GMT+8:70").getID(), "GMT");
		assertEquals(
				"Must return GMT when given an invalid TimeZone time GMT+3:.",
				TimeZone.getTimeZone("GMT+3:").getID(), "GMT");
		assertEquals(
				"Must return GMT when given an invalid TimeZone time GMT+3:0.",
				TimeZone.getTimeZone("GMT+3:0").getID(), "GMT");
		assertEquals(
				"Must return GMT when given an invalid TimeZone time GMT+2360.",
				TimeZone.getTimeZone("GMT+2360").getID(), "GMT");
		assertEquals(
				"Must return GMT when given an invalid TimeZone time GMT+892.",
				TimeZone.getTimeZone("GMT+892").getID(), "GMT");
		assertEquals(
				"Must return GMT when given an invalid TimeZone time GMT+082.",
				TimeZone.getTimeZone("GMT+082").getID(), "GMT");
		assertEquals(
				"Must return GMT when given an invalid TimeZone time GMT+28.",
				TimeZone.getTimeZone("GMT+28").getID(), "GMT");
		assertEquals(
				"Must return GMT when given an invalid TimeZone time GMT+30.",
				TimeZone.getTimeZone("GMT+30").getID(), "GMT");
		assertEquals("Must return GMT when given TimeZone GMT.", TimeZone
				.getTimeZone("GMT").getID(), "GMT");
		assertEquals("Must return GMT when given TimeZone GMT+.", TimeZone
				.getTimeZone("GMT+").getID(), "GMT");
		assertEquals("Must return GMT when given TimeZone GMT-.", TimeZone
				.getTimeZone("GMT-").getID(), "GMT");
		assertEquals(
				"Must return GMT when given an invalid TimeZone time GMT-8.45.",
				TimeZone.getTimeZone("GMT-8.45").getID(), "GMT");
		assertEquals(
				"Must return GMT when given an invalid TimeZone time GMT-123:23.",
				TimeZone.getTimeZone("GMT-123:23").getID(), "GMT");
		assertEquals(
				"Must return proper GMT formatted string for GMT+8:30 (eg. GMT+08:20).",
				TimeZone.getTimeZone("GMT+8:30").getID(), "GMT+08:30");
		assertEquals(
				"Must return proper GMT formatted string for GMT+3 (eg. GMT+08:20).",
				TimeZone.getTimeZone("GMT+3").getID(), "GMT+03:00");
		assertEquals(
				"Must return proper GMT formatted string for GMT+3:02 (eg. GMT+08:20).",
				TimeZone.getTimeZone("GMT+3:02").getID(), "GMT+03:02");
		assertEquals(
				"Must return proper GMT formatted string for GMT+2359 (eg. GMT+08:20).",
				TimeZone.getTimeZone("GMT+2359").getID(), "GMT+23:59");
		assertEquals(
				"Must return proper GMT formatted string for GMT+520 (eg. GMT+08:20).",
				TimeZone.getTimeZone("GMT+520").getID(), "GMT+05:20");
		assertEquals(
				"Must return proper GMT formatted string for GMT+052 (eg. GMT+08:20).",
				TimeZone.getTimeZone("GMT+052").getID(), "GMT+00:52");
		assertEquals(
				"Must return proper GMT formatted string for GMT-0 (eg. GMT+08:20).",
				TimeZone.getTimeZone("GMT-0").getID(), "GMT-00:00");
	}

	/**
	 * @tests java.util.TimeZone#setDefault(java.util.TimeZone)
	 */
	public void test_setDefaultLjava_util_TimeZone() {
		TimeZone oldDefault = TimeZone.getDefault();
		TimeZone zone = new SimpleTimeZone(45, "TEST");
		TimeZone.setDefault(zone);
		assertTrue("timezone not set", TimeZone.getDefault().equals(zone));
		TimeZone.setDefault(null);
		assertTrue("default not restored", TimeZone.getDefault().equals(
				oldDefault));
	}

	protected void setUp() {
	}

	protected void tearDown() {
	}
}
