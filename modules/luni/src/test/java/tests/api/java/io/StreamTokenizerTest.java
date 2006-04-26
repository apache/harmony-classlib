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

package tests.api.java.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.StreamTokenizer;
import java.io.StringBufferInputStream;

import tests.support.Support_StringReader;

public class StreamTokenizerTest extends junit.framework.TestCase {
	Support_StringReader r;

	StreamTokenizer st;

	String testString;

	/**
	 * @tests java.io.StreamTokenizer#StreamTokenizer(java.io.InputStream)
	 */
	public void test_ConstructorLjava_io_InputStream() {
		st = new StreamTokenizer(new StringBufferInputStream(
				"/comments\n d 8 'h'"));
		try {
			assertTrue(
					"nextTokent() should return the character d skiping the comments",
					st.nextToken() == StreamTokenizer.TT_WORD
							&& st.sval.equals("d"));
			assertTrue("the next token returned should be the digit 8", st
					.nextToken() == StreamTokenizer.TT_NUMBER
					&& st.nval == 8.0);
			assertTrue("the next token returned should be the quote character",
					st.nextToken() == 39 && st.sval.equals("h"));
		} catch (IOException e) {
			fail(
					"IOException occured while trying to read an input stream - constructor");
		}
	}

	/**
	 * @tests java.io.StreamTokenizer#StreamTokenizer(java.io.Reader)
	 */
	public void test_ConstructorLjava_io_Reader() {
		setTest("/testing\n d 8 'h' ");
		try {
			assertTrue(
					"nextTokent() should return the character d skiping the comments",
					st.nextToken() == StreamTokenizer.TT_WORD
							&& st.sval.equals("d"));
			assertTrue("the next token returned should be the digit 8", st
					.nextToken() == StreamTokenizer.TT_NUMBER
					&& st.nval == 8.0);
			assertTrue("the next token returned should be the quote character",
					st.nextToken() == 39 && st.sval.equals("h"));
		} catch (IOException e) {
			fail(
					"IOException occured while trying to read an input stream - constructor");
		}
	}

	/**
	 * @tests java.io.StreamTokenizer#commentChar(int)
	 */
	public void test_commentCharI() {
		setTest("*comment \n / 8 'h' ");
		st.ordinaryChar('/');
		st.commentChar('*');
		try {
			assertEquals("nextTokent() did not return the character / skiping the comments starting with *",
					47, st.nextToken());
			assertTrue("the next token returned should be the digit 8", st
					.nextToken() == StreamTokenizer.TT_NUMBER
					&& st.nval == 8.0);
			assertTrue("the next token returned should be the quote character",
					st.nextToken() == 39 && st.sval.equals("h"));
		} catch (IOException e) {
			fail(
					"IOException occured while trying to read an input stream - constructor");
		}
	}

	/**
	 * @tests java.io.StreamTokenizer#eolIsSignificant(boolean)
	 */
	public void test_eolIsSignificantZ() {
		setTest("d 8\n");
		try {
			// by default end of line characters are not significant
			assertTrue("nextToken did not return d",
					st.nextToken() == StreamTokenizer.TT_WORD
							&& st.sval.equals("d"));
			assertTrue("nextToken did not return 8",
					st.nextToken() == StreamTokenizer.TT_NUMBER
							&& st.nval == 8.0);
			assertTrue("nextToken should be the end of file",
					st.nextToken() == StreamTokenizer.TT_EOF);
		} catch (IOException e) {
			fail(
					"IOException occured while trying to read an input stream - constructor");
		}
		setTest("d\n");
		st.eolIsSignificant(true);
		try {
			// end of line characters are significant
			assertTrue("nextToken did not return d",
					st.nextToken() == StreamTokenizer.TT_WORD
							&& st.sval.equals("d"));
			assertTrue("nextToken is the end of line",
					st.nextToken() == StreamTokenizer.TT_EOL);
		} catch (IOException e) {
			fail(
					"IOException occured while trying to read an input stream - constructor");
		}
	}

	/**
	 * @tests java.io.StreamTokenizer#lineno()
	 */
	public void test_lineno() {
		setTest("d\n 8\n");
		try {
			assertEquals("the lineno should be 1", 1, st.lineno());
			st.nextToken();
			st.nextToken();
			assertEquals("the lineno should be 2", 2, st.lineno());
			st.nextToken();
			assertEquals("the next line no should be 3", 3, st.lineno());
		} catch (IOException e) {
			fail(
					"IOException occured while trying to read an input stream - constructor");
		}
	}

	/**
	 * @tests java.io.StreamTokenizer#lowerCaseMode(boolean)
	 */
	public void test_lowerCaseModeZ() {
		// SM.
		setTest("HELLOWORLD");
		st.lowerCaseMode(true);
		try {
			st.nextToken();
			assertEquals("sval not converted to lowercase.", "helloworld", st.sval
					);
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.StreamTokenizer#nextToken()
	 */
	public void test_nextToken() {
		// SM.
		setTest("\r\n/* fje fje 43.4 f \r\n f g */  456.459 \r\n"
				+ "Hello  / 	\r\n \r\n \n \r \257 Hi \'Hello World\'");
		st.ordinaryChar('/');
		st.slashStarComments(true);
		try {
			st.nextToken();
			assertTrue("Wrong Token type1: " + (char) st.ttype,
					st.ttype == StreamTokenizer.TT_NUMBER);
			st.nextToken();
			assertTrue("Wrong Token type2: " + st.ttype,
					st.ttype == StreamTokenizer.TT_WORD);
			st.nextToken();
			assertTrue("Wrong Token type3: " + st.ttype, st.ttype == '/');
			st.nextToken();
			assertTrue("Wrong Token type4: " + st.ttype,
					st.ttype == StreamTokenizer.TT_WORD);
			st.nextToken();
			assertTrue("Wrong Token type5: " + st.ttype,
					st.ttype == StreamTokenizer.TT_WORD);
			st.nextToken();
			assertTrue("Wrong Token type6: " + st.ttype, st.ttype == '\'');
			assertTrue("Wrong Token type7: " + st.ttype, st.sval
					.equals("Hello World"));
			st.nextToken();
			assertTrue("Wrong Token type8: " + st.ttype, st.ttype == -1);

		} catch (IOException e) {
			fail(
					"IOException occured while trying to read an input stream - constructor");
		}

		try {
			final PipedInputStream pin = new PipedInputStream();
			PipedOutputStream pout = new PipedOutputStream(pin);
			pout.write("hello\n\r\r".getBytes());
			StreamTokenizer s = new StreamTokenizer(pin);
			s.eolIsSignificant(true);
			assertTrue("Wrong token 1,1",
					s.nextToken() == StreamTokenizer.TT_WORD
							&& s.sval.equals("hello"));
			assertTrue("Wrong token 1,2", s.nextToken() == '\n');
			assertTrue("Wrong token 1,3", s.nextToken() == '\n');
			assertTrue("Wrong token 1,4", s.nextToken() == '\n');
			pout.close();
			assertTrue("Wrong token 1,5",
					s.nextToken() == StreamTokenizer.TT_EOF);
		} catch (IOException e) {
			fail("IOException during test 1 : " + e.getMessage());
		}

		try {
			StreamTokenizer tokenizer = new StreamTokenizer(
					new Support_StringReader("\n \r\n#"));
			tokenizer.ordinaryChar('\n'); // make \n ordinary
			tokenizer.eolIsSignificant(true);
			assertTrue("Wrong token 2,1", tokenizer.nextToken() == '\n');
			assertTrue("Wrong token 2,2", tokenizer.nextToken() == '\n');
			assertEquals("Wrong token 2,3", '#', tokenizer.nextToken());
		} catch (IOException e) {
			fail("IOException during test 2 : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.StreamTokenizer#ordinaryChar(int)
	 */
	public void test_ordinaryCharI() {
		// SM.
		setTest("Ffjein 893");
		try {
			st.ordinaryChar('F');
			st.nextToken();
			assertTrue("OrdinaryChar failed." + (char) st.ttype,
					st.ttype == 'F');
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.StreamTokenizer#ordinaryChars(int, int)
	 */
	public void test_ordinaryCharsII() {
		// SM.
		setTest("azbc iof z 893");
		st.ordinaryChars('a', 'z');
		try {
			assertEquals("OrdinaryChars failed.", 'a', st.nextToken());
			assertEquals("OrdinaryChars failed.", 'z', st.nextToken());
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.StreamTokenizer#parseNumbers()
	 */
	public void test_parseNumbers() {
		// SM
		setTest("9.9 678");
		try {
			assertTrue("Base behavior failed.",
					st.nextToken() == StreamTokenizer.TT_NUMBER);
			st.ordinaryChars('0', '9');
			assertEquals("setOrdinary failed.", '6', st.nextToken());
			st.parseNumbers();
			assertTrue("parseNumbers failed.",
					st.nextToken() == StreamTokenizer.TT_NUMBER);
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.StreamTokenizer#pushBack()
	 */
	public void test_pushBack() {
		// SM.
		setTest("Hello 897");
		try {
			st.nextToken();
			st.pushBack();
			assertTrue("PushBack failed.",
					st.nextToken() == StreamTokenizer.TT_WORD);
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.StreamTokenizer#quoteChar(int)
	 */
	public void test_quoteCharI() {
		// SM
		setTest("<Hello World<    HelloWorldH");
		st.quoteChar('<');
		try {
			assertEquals("QuoteChar failed.", '<', st.nextToken());
			assertEquals("QuoteChar failed.", "Hello World", st.sval);
			st.quoteChar('H');
			st.nextToken();
			assertEquals("QuoteChar failed for word.", "elloWorld", st.sval
					);
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.StreamTokenizer#resetSyntax()
	 */
	public void test_resetSyntax() {
		// SM
		setTest("H 9\' ello World");
		try {
			st.resetSyntax();
			assertTrue("resetSyntax failed1." + (char) st.ttype,
					st.nextToken() == 'H');
			assertTrue("resetSyntax failed1." + (char) st.ttype,
					st.nextToken() == ' ');
			assertTrue("resetSyntax failed2." + (char) st.ttype,
					st.nextToken() == '9');
			assertTrue("resetSyntax failed3." + (char) st.ttype,
					st.nextToken() == '\'');
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.StreamTokenizer#slashSlashComments(boolean)
	 */
	public void test_slashSlashCommentsZ() {
		// SM.
		setTest("// foo \r\n /fiji \r\n -456");
		st.ordinaryChar('/');
		st.slashSlashComments(true);
		try {
			assertEquals("Test failed.", '/', st.nextToken());
			assertTrue("Test failed.",
					st.nextToken() == StreamTokenizer.TT_WORD);
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.StreamTokenizer#slashStarComments(boolean)
	 */
	public void test_slashStarCommentsZ() {
		setTest("/* foo \r\n /fiji \r\n*/ -456");
		st.ordinaryChar('/');
		st.slashStarComments(true);
		try {
			assertTrue("Test failed.",
					st.nextToken() == StreamTokenizer.TT_NUMBER);
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.StreamTokenizer#toString()
	 */
	public void test_toString() {
		setTest("ABC Hello World");
		try {
			st.nextToken();
		} catch (Exception e) {
		}
		assertTrue("toString failed." + st.toString(), st.toString().equals(
				"Token[ABC], line 1"));
	}

	/**
	 * @tests java.io.StreamTokenizer#whitespaceChars(int, int)
	 */
	public void test_whitespaceCharsII() {
		setTest("azbc iof z 893");
		st.whitespaceChars('a', 'z');
		try {
			assertTrue("OrdinaryChar failed.",
					st.nextToken() == StreamTokenizer.TT_NUMBER);
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.StreamTokenizer#wordChars(int, int)
	 */
	public void test_wordCharsII() {
		setTest("A893 -9B87");
		st.wordChars('0', '9');
		try {
			assertTrue("WordChar failed1.",
					st.nextToken() == StreamTokenizer.TT_WORD);
			assertEquals("WordChar failed2.", "A893", st.sval);
			assertTrue("WordChar failed3.",
					st.nextToken() == StreamTokenizer.TT_NUMBER);
			st.nextToken();
			assertEquals("WordChar failed4.", "B87", st.sval);

			setTest("    Hello World");
			st.wordChars(' ', ' ');
			st.nextToken();
			assertEquals("WordChars failed for whitespace.", "Hello World", st.sval
					);

			setTest("    Hello World\r\n  \'Hello World\' Hello\' World");
			st.wordChars(' ', ' ');
			st.wordChars('\'', '\'');
			st.nextToken();
			assertTrue("WordChars failed for whitespace: " + st.sval, st.sval
					.equals("Hello World"));
			st.nextToken();
			assertTrue("WordChars failed for quote1: " + st.sval, st.sval
					.equals("\'Hello World\' Hello\' World"));
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	private void setTest(String s) {
		testString = s;
		r = new Support_StringReader(testString);
		st = new StreamTokenizer(r);
	}

	protected void setUp() {
	}

	protected void tearDown() {
	}
}
