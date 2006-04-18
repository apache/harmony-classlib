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

package tests.api.java.lang;

public class CharacterTest extends junit.framework.TestCase {

	/**
	 * @tests java.lang.Character#Character(char)
	 */
	public void test_ConstructorC() {
		// Test for method java.lang.Character(char)
		assertTrue("Constructor failed", new Character('T').charValue() == 'T');
	}

	/**
	 * @tests java.lang.Character#charValue()
	 */
	public void test_charValue() {
		// Test for method char java.lang.Character.charValue()
		assertTrue("Incorrect char value returned", new Character('T')
				.charValue() == 'T');
	}

	/**
	 * @tests java.lang.Character#compareTo(java.lang.Character)
	 */
	public void test_compareToLjava_lang_Character() {
		// Test for method int
		// java.lang.Character.compareTo(java.lang.Character)
		Character c = new Character('c');
		Character x = new Character('c');
		Character y = new Character('b');
		Character z = new Character('d');

		assertTrue("Returned false for same Character", c.compareTo(c) == 0);
		assertTrue("Returned false for identical Character",
				c.compareTo(x) == 0);
		assertTrue("Returned other than less than for lesser char", c
				.compareTo(y) > 0);
		assertTrue("Returned other than greater than for greater char", c
				.compareTo(z) < 0);
	}

	/**
	 * @tests java.lang.Character#digit(char, int)
	 */
	public void test_digitCI() {
		// Test for method int java.lang.Character.digit(char, int)
		assertTrue("Returned incorrect digit", Character.digit('1', 10) == 1);
		assertTrue("Returned incorrect digit", Character.digit('F', 16) == 15);
	}

	/**
	 * @tests java.lang.Character#equals(java.lang.Object)
	 */
	public void test_equalsLjava_lang_Object() {
		// Test for method boolean java.lang.Character.equals(java.lang.Object)
		assertTrue("Equality test failed", new Character('A')
				.equals(new Character('A')));
		assertTrue("Equality test failed", !(new Character('A')
				.equals(new Character('a'))));
	}

	/**
	 * @tests java.lang.Character#forDigit(int, int)
	 */
	public void test_forDigitII() {
		// Test for method char java.lang.Character.forDigit(int, int)

		char hexChars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		for (int i = 0; i < hexChars.length; i++) {
			assertTrue("Returned incorrect char for " + Integer.toString(i),
					Character.forDigit(i, hexChars.length) == hexChars[i]);
		}

		char decimalChars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9' };
		for (int i = 0; i < decimalChars.length; i++) {
			assertTrue(
					"Returned incorrect char for " + Integer.toString(i),
					Character.forDigit(i, decimalChars.length) == decimalChars[i]);
		}

	}

	/**
	 * @tests java.lang.Character#getNumericValue(char)
	 */
	public void test_getNumericValueC() {
		// Test for method int java.lang.Character.getNumericValue(char)
		assertTrue("Returned incorrect numeric value 1", Character
				.getNumericValue('1') == 1);
		assertTrue("Returned incorrect numeric value 2", Character
				.getNumericValue('F') == 15);
		assertTrue("Returned incorrect numeric value 3", Character
				.getNumericValue('\u221e') == -1);
		assertTrue("Returned incorrect numeric value 4", Character
				.getNumericValue('\u00be') == -2);
		assertTrue("Returned incorrect numeric value 5", Character
				.getNumericValue('\u2182') == 10000);
		assertTrue("Returned incorrect numeric value 6", Character
				.getNumericValue('\uff12') == 2);
	}

	/**
	 * @tests java.lang.Character#getType(char)
	 */
	public void test_getTypeC() {
		// Test for method int java.lang.Character.getType(char)
		assertTrue("Returned incorrect type for: \n",
				Character.getType('\n') == Character.CONTROL);
		assertTrue("Returned incorrect type for: 1",
				Character.getType('1') == Character.DECIMAL_DIGIT_NUMBER);
		assertTrue("Returned incorrect type for: ' '",
				Character.getType(' ') == Character.SPACE_SEPARATOR);
		assertTrue("Returned incorrect type for: a",
				Character.getType('a') == Character.LOWERCASE_LETTER);
		assertTrue("Returned incorrect type for: A",
				Character.getType('A') == Character.UPPERCASE_LETTER);
		assertTrue("Returned incorrect type for: <",
				Character.getType('<') == Character.MATH_SYMBOL);
		assertTrue("Returned incorrect type for: ;",
				Character.getType(';') == Character.OTHER_PUNCTUATION);
		assertTrue("Returned incorrect type for: _",
				Character.getType('_') == Character.CONNECTOR_PUNCTUATION);
		assertTrue("Returned incorrect type for: $",
				Character.getType('$') == Character.CURRENCY_SYMBOL);
		assertTrue("Returned incorrect type for: \u2029", Character
				.getType('\u2029') == Character.PARAGRAPH_SEPARATOR);

		assertTrue("Wrong constant for FORMAT", Character.FORMAT == 16);
		assertTrue("Wrong constant for PRIVATE_USE",
				Character.PRIVATE_USE == 18);
	}

	/**
	 * @tests java.lang.Character#hashCode()
	 */
	public void test_hashCode() {
		// Test for method int java.lang.Character.hashCode()
		assertTrue("Incorrect hash returned",
				new Character('Y').hashCode() == 89);
	}

	/**
	 * @tests java.lang.Character#isDefined(char)
	 */
	public void test_isDefinedC() {
		// Test for method boolean java.lang.Character.isDefined(char)
		assertTrue("Defined character returned false", Character.isDefined('v'));
		assertTrue("Undefined character returned true", Character
				.isDefined('\u6039'));
	}

	/**
	 * @tests java.lang.Character#isDigit(char)
	 */
	public void test_isDigitC() {
		// Test for method boolean java.lang.Character.isDigit(char)
		assertTrue("Digit returned false", Character.isDigit('1'));
		assertTrue("Non-Digit returned false", !Character.isDigit('A'));
	}

	/**
	 * @tests java.lang.Character#isIdentifierIgnorable(char)
	 */
	public void test_isIdentifierIgnorableC() {
		// Test for method boolean
		// java.lang.Character.isIdentifierIgnorable(char)
		assertTrue("Ignorable whitespace returned false", Character
				.isIdentifierIgnorable('\u0007'));
		assertTrue("Ignorable non - whitespace  control returned false",
				Character.isIdentifierIgnorable('\u000f'));
		assertTrue("Ignorable join control returned false", Character
				.isIdentifierIgnorable('\u200e'));

		// the spec is wrong, and our implementation is correct
		assertTrue("Ignorable bidi control returned false", Character
				.isIdentifierIgnorable('\u202b'));

		assertTrue("Ignorable format control returned false", Character
				.isIdentifierIgnorable('\u206c'));
		assertTrue("Ignorable zero-width no-break returned false", Character
				.isIdentifierIgnorable('\ufeff'));

		assertTrue("Non-Ignorable returned true", !Character
				.isIdentifierIgnorable('\u0065'));
	}

	/**
	 * @tests java.lang.Character#isISOControl(char)
	 */
	public void test_isISOControlC() {
		// Test for method boolean java.lang.Character.isISOControl(char)
		for (int i = 0; i < 32; i++)
			assertTrue("ISOConstrol char returned false", Character
					.isISOControl((char) i));

		for (int i = 127; i < 160; i++)
			assertTrue("ISOConstrol char returned false", Character
					.isISOControl((char) i));
	}

	/**
	 * @tests java.lang.Character#isJavaIdentifierPart(char)
	 */
	public void test_isJavaIdentifierPartC() {
		// Test for method boolean
		// java.lang.Character.isJavaIdentifierPart(char)
		assertTrue("letter returned false", Character.isJavaIdentifierPart('l'));
		assertTrue("currency returned false", Character
				.isJavaIdentifierPart('$'));
		assertTrue("digit returned false", Character.isJavaIdentifierPart('9'));
		assertTrue("connecting char returned false", Character
				.isJavaIdentifierPart('_'));
		assertTrue("ignorable control returned true", !Character
				.isJavaIdentifierPart('\u200b'));
		assertTrue("semi returned true", !Character.isJavaIdentifierPart(';'));
	}

	/**
	 * @tests java.lang.Character#isJavaIdentifierStart(char)
	 */
	public void test_isJavaIdentifierStartC() {
		// Test for method boolean
		// java.lang.Character.isJavaIdentifierStart(char)
		assertTrue("letter returned false", Character
				.isJavaIdentifierStart('l'));
		assertTrue("currency returned false", Character
				.isJavaIdentifierStart('$'));
		assertTrue("connecting char returned false", Character
				.isJavaIdentifierStart('_'));
		assertTrue("digit returned true", !Character.isJavaIdentifierStart('9'));
		assertTrue("ignorable control returned true", !Character
				.isJavaIdentifierStart('\u200b'));
		assertTrue("semi returned true", !Character.isJavaIdentifierStart(';'));
	}

	/**
	 * @tests java.lang.Character#isJavaLetter(char)
	 */
	public void test_isJavaLetterC() {
		// Test for method boolean java.lang.Character.isJavaLetter(char)
		assertTrue("letter returned false", Character.isJavaLetter('l'));
		assertTrue("currency returned false", Character.isJavaLetter('$'));
		assertTrue("connecting char returned false", Character
				.isJavaLetter('_'));

		assertTrue("digit returned true", !Character.isJavaLetter('9'));
		assertTrue("ignirable control returned true", !Character
				.isJavaLetter('\u200b'));
		assertTrue("semi returned true", !Character.isJavaLetter(';'));
	}

	/**
	 * @tests java.lang.Character#isJavaLetterOrDigit(char)
	 */
	public void test_isJavaLetterOrDigitC() {
		// Test for method boolean java.lang.Character.isJavaLetterOrDigit(char)
		assertTrue("letter returned false", Character.isJavaLetterOrDigit('l'));
		assertTrue("currency returned false", Character
				.isJavaLetterOrDigit('$'));
		assertTrue("digit returned false", Character.isJavaLetterOrDigit('9'));
		assertTrue("connecting char returned false", Character
				.isJavaLetterOrDigit('_'));
		assertTrue("semi returned true", !Character.isJavaLetterOrDigit(';'));
	}

	/**
	 * @tests java.lang.Character#isLetter(char)
	 */
	public void test_isLetterC() {
		// Test for method boolean java.lang.Character.isLetter(char)
		assertTrue("Letter returned false", Character.isLetter('L'));
		assertTrue("Non-Letter returned true", !Character.isLetter('9'));
	}

	/**
	 * @tests java.lang.Character#isLetterOrDigit(char)
	 */
	public void test_isLetterOrDigitC() {
		// Test for method boolean java.lang.Character.isLetterOrDigit(char)
		assertTrue("Digit returned false", Character.isLetterOrDigit('9'));
		assertTrue("Letter returned false", Character.isLetterOrDigit('K'));
		assertTrue("Control returned true", !Character.isLetterOrDigit('\n'));
		assertTrue("Puncutation returned true", !Character.isLetterOrDigit('?'));
	}

	/**
	 * @tests java.lang.Character#isLowerCase(char)
	 */
	public void test_isLowerCaseC() {
		// Test for method boolean java.lang.Character.isLowerCase(char)
		assertTrue("lower returned false", Character.isLowerCase('a'));
		assertTrue("upper returned true", !Character.isLowerCase('T'));
	}

	/**
	 * @tests java.lang.Character#isSpace(char)
	 */
	public void test_isSpaceC() {
		// Test for method boolean java.lang.Character.isSpace(char)
		assertTrue("space returned false", Character.isSpace('\n'));
		assertTrue("non-space returned true", !Character.isSpace('T'));
	}

	/**
	 * @tests java.lang.Character#isSpaceChar(char)
	 */
	public void test_isSpaceCharC() {
		// Test for method boolean java.lang.Character.isSpaceChar(char)
		assertTrue("space returned false", Character.isSpaceChar('\u0020'));
		assertTrue("non-space returned true", !Character.isSpaceChar('\n'));
	}

	/**
	 * @tests java.lang.Character#isTitleCase(char)
	 */
	public void test_isTitleCaseC() {
		// Test for method boolean java.lang.Character.isTitleCase(char)

		char[] tChars = { (char) 0x01c5, (char) 0x01c8, (char) 0x01cb,
				(char) 0x01f2, (char) 0x1f88, (char) 0x1f89, (char) 0x1f8a,
				(char) 0x1f8b, (char) 0x1f8c, (char) 0x1f8d, (char) 0x1f8e,
				(char) 0x1f8f, (char) 0x1f98, (char) 0x1f99, (char) 0x1f9a,
				(char) 0x1f9b, (char) 0x1f9c, (char) 0x1f9d, (char) 0x1f9e,
				(char) 0x1f9f, (char) 0x1fa8, (char) 0x1fa9, (char) 0x1faa,
				(char) 0x1fab, (char) 0x1fac, (char) 0x1fad, (char) 0x1fae,
				(char) 0x1faf, (char) 0x1fbc, (char) 0x1fcc, (char) 0x1ffc };
		byte tnum = 0;
		for (char c = 0; c < 65535; c++) {
			if (Character.isTitleCase(c)) {
				tnum++;
				int i;
				for (i = 0; i < tChars.length; i++)
					if (tChars[i] == c)
						i = tChars.length + 1;
				if (i < tChars.length) {
					fail("Non Title Case char returned true");
				}
			}
		}
		assertTrue("Failed to find all Title Case chars", tnum == tChars.length);
	}

	/**
	 * @tests java.lang.Character#isUnicodeIdentifierPart(char)
	 */
	public void test_isUnicodeIdentifierPartC() {
		// Test for method boolean
		// java.lang.Character.isUnicodeIdentifierPart(char)
		assertTrue("'a' returned false", Character.isUnicodeIdentifierPart('a'));
		assertTrue("'2' returned false", Character.isUnicodeIdentifierPart('2'));
		assertTrue("'+' returned true", !Character.isUnicodeIdentifierPart('+'));
	}

	/**
	 * @tests java.lang.Character#isUnicodeIdentifierStart(char)
	 */
	public void test_isUnicodeIdentifierStartC() {
		// Test for method boolean
		// java.lang.Character.isUnicodeIdentifierStart(char)
		assertTrue("'a' returned false", Character
				.isUnicodeIdentifierStart('a'));
		assertTrue("'2' returned true", !Character
				.isUnicodeIdentifierStart('2'));
		assertTrue("'+' returned true", !Character
				.isUnicodeIdentifierStart('+'));
	}

	/**
	 * @tests java.lang.Character#isUpperCase(char)
	 */
	public void test_isUpperCaseC() {
		// Test for method boolean java.lang.Character.isUpperCase(char)
		assertTrue("Incorrect case value", !Character.isUpperCase('t'));
		assertTrue("Incorrect case value", Character.isUpperCase('T'));
	}

	/**
	 * @tests java.lang.Character#isWhitespace(char)
	 */
	public void test_isWhitespaceC() {
		// Test for method boolean java.lang.Character.isWhitespace(char)
		assertTrue("space returned false", Character.isWhitespace('\n'));
		assertTrue("non-space returned true", !Character.isWhitespace('T'));
	}

	/**
	 * @tests java.lang.Character#toLowerCase(char)
	 */
	public void test_toLowerCaseC() {
		// Test for method char java.lang.Character.toLowerCase(char)
		assertTrue("Failed to change case", Character.toLowerCase('T') == 't');
	}

	/**
	 * @tests java.lang.Character#toString()
	 */
	public void test_toString() {
		// Test for method java.lang.String java.lang.Character.toString()
		assertTrue("Incorrect String returned", new Character('T').toString()
				.equals("T"));
	}

	/**
	 * @tests java.lang.Character#toTitleCase(char)
	 */
	public void test_toTitleCaseC() {
		// Test for method char java.lang.Character.toTitleCase(char)
		assertTrue("Incorrect title case for a",
				Character.toTitleCase('a') == 'A');
		assertTrue("Incorrect title case for A",
				Character.toTitleCase('A') == 'A');
		assertTrue("Incorrect title case for 1",
				Character.toTitleCase('1') == '1');
	}

	/**
	 * @tests java.lang.Character#toUpperCase(char)
	 */
	public void test_toUpperCaseC() {
		// Test for method char java.lang.Character.toUpperCase(char)
		assertTrue("Incorrect upper case for a",
				Character.toUpperCase('a') == 'A');
		assertTrue("Incorrect upper case for A",
				Character.toUpperCase('A') == 'A');
		assertTrue("Incorrect upper case for 1",
				Character.toUpperCase('1') == '1');
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
