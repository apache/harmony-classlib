/* Copyright 2005 The Apache Software Foundation or its licensors, as applicable
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

package tests.support;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Sample java.util.TimeZone subclass to test getDSTSavings() and getOffset(long)
 * APIs
 * 
 */
public class Support_TimeZone extends TimeZone {

	int rawOffset;

	boolean useDaylightTime;

	public Support_TimeZone(int rawOffset, boolean useDaylightTime) {
		this.rawOffset = rawOffset;
		this.useDaylightTime = useDaylightTime;
	}

	public int getRawOffset() {
		return rawOffset;
	}

	/**
	 * let's assume this timezone has daylight savings from the 4th month till
	 * the 10th month of the year to ame things simple.
	 */
	public boolean inDaylightTime(java.util.Date p1) {
		if (!useDaylightTime)
			return false;
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(p1);
		int month = cal.get(Calendar.MONTH);

		if (month > 4 && month < 10)
			return true;
		else
			return false;
	}

	public boolean useDaylightTime() {
		return useDaylightTime;
	}

	/*
	 * return 0 to keep it simple, since this subclass is not used to test this
	 * method..
	 */
	public int getOffset(int p1, int p2, int p3, int p4, int p5, int p6) {
		return 0;
	}

	public void setRawOffset(int p1) {
		rawOffset = p1;
	}
}
