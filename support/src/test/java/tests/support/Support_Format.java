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

import java.text.AttributedCharacterIterator;
import java.text.FieldPosition;
import java.text.Format;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.Iterator;
import java.util.Vector;

public class Support_Format extends junit.framework.TestCase {

	protected String text = null;

	public Support_Format(String p1) {
		super(p1);
	}

	protected void t_FormatWithField(int count, Format format, Object object,
			String text, Format.Field field, int begin, int end) {
		StringBuffer buffer = new StringBuffer();
		FieldPosition pos = new FieldPosition(field);
		format.format(object, buffer, pos);

		// System.out.println(buffer);
		// System.out.println(pos);

		if (text == null)
			assertEquals("Test " + count + ": incorrect formatted text",
					this.text, buffer.toString());
		else
			assertEquals("Test " + count + ": incorrect formatted text", text,
					buffer.toString());

		assertEquals("Test " + count + ": incorrect begin index for field "
				+ field, begin, pos.getBeginIndex());
		assertEquals("Test " + count + ": incorrect end index for field"
				+ field, end, pos.getEndIndex());
	}

	protected void t_Format(int count, Object object, Format format,
			Vector expectedResults) {
		// System.out.println(format.format(object));
		Vector results = findFields(format.formatToCharacterIterator(object));
		assertTrue("Test " + count
				+ ": Format returned incorrect CharacterIterator for "
				+ format.format(object), compare(results, expectedResults));
	}

	/**
	 * compares two vectors regardless of the order of their elements
	 */
	protected static boolean compare(Vector vector1, Vector vector2) {
		return vector1.size() == vector2.size() && vector1.containsAll(vector2);
	}

	/**
	 * finds attributes with regards to char index in this
	 * AttributedCharacterIterator, and puts them in a vector
	 * 
	 * @param iterator
	 * @return a vector, each entry in this vector are of type FieldContainer ,
	 *         which stores start and end indexes and an attribute this range
	 *         has
	 */
	protected static Vector findFields(AttributedCharacterIterator iterator) {
		Vector result = new Vector();
		while (iterator.getIndex() != iterator.getEndIndex()) {
			int start = iterator.getRunStart();
			int end = iterator.getRunLimit();

			Iterator it = iterator.getAttributes().keySet().iterator();
			while (it.hasNext()) {
				AttributedCharacterIterator.Attribute attribute = (AttributedCharacterIterator.Attribute) it
						.next();
				Object value = iterator.getAttribute(attribute);
				result.add(new FieldContainer(start, end, attribute, value));
				// System.out.println(start + " " + end + ": " + attribute + ",
				// " + value );
				// System.out.println("v.add(new FieldContainer(" + start +"," +
				// end +"," + attribute+ "," + value+ "));");
			}
			iterator.setIndex(end);
		}
		return result;
	}

	protected static class FieldContainer {
		int start, end;

		AttributedCharacterIterator.Attribute attribute;

		Object value;

		// called from support_decimalformat and support_simpledateformat tests
		public FieldContainer(int start, int end,
				AttributedCharacterIterator.Attribute attribute) {
			this(start, end, attribute, attribute);
		}

		// called from support_messageformat tests
		public FieldContainer(int start, int end, Attribute attribute, int value) {
			this(start, end, attribute, new Integer(value));
		}

		// called from support_messageformat tests
		public FieldContainer(int start, int end, Attribute attribute,
				Object value) {
			this.start = start;
			this.end = end;
			this.attribute = attribute;
			this.value = value;
		}

		public boolean equals(Object obj) {
			if (!(obj instanceof FieldContainer))
				return false;

			FieldContainer fc = (FieldContainer) obj;
			return (start == fc.start && end == fc.end
					&& attribute == fc.attribute && value.equals(fc.value));
		}
	}
}
