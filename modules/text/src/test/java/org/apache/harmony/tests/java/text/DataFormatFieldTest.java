/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.tests.java.text;

import java.text.DateFormat;
import java.util.Calendar;

import junit.framework.TestCase;

public class DataFormatFieldTest extends TestCase{

	public void test_ConstructorLjava_lang_StringLjava_lang_String() {
		// Regression for HARMONY-178
		MyField field = new MyField("day of month", Calendar.ERA);

		assertEquals("field has wrong name", "day of month", field.getName());
		assertEquals("field has wrong Calendar field number", Calendar.ERA,
				field.getCalendarField());

		DateFormat.Field realField = DateFormat.Field
				.ofCalendarField(Calendar.ERA);
		assertSame("Modified calendar field with the same field number",
				DateFormat.Field.ERA, realField);

		DateFormat.Field realField2 = DateFormat.Field
				.ofCalendarField(Calendar.DAY_OF_MONTH);
		assertSame("Modified calendar field with the same field number",
				DateFormat.Field.DAY_OF_MONTH, realField2);
	}

	static class MyField extends DateFormat.Field {
		protected MyField(String fieldName, int calendarField) {
			super(fieldName, calendarField);
		}

		protected String getName() {
			return super.getName();
		}
	}

}
