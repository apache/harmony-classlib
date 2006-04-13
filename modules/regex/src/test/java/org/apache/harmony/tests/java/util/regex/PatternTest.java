/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * @author Nikolay A. Kuznetsov
 * @version $Revision: 1.12.2.2 $
 */
package org.apache.harmony.tests.java.util.regex;

import junit.framework.TestCase;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author Nikolay A. Kuznetsov
 * @version $Revision: 1.12.2.2 $
 */
public class PatternTest extends TestCase {
    String[] testPatterns = {
        "(a|b)*abb",
        "(1*2*3*4*)*567",
        "(a|b|c|d)*aab",
        "(1|2|3|4|5|6|7|8|9|0)(1|2|3|4|5|6|7|8|9|0)*",
        "(abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ)*",
        "(a|b)*(a|b)*A(a|b)*lice.*",
        "(a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z)(a|b|c|d|e|f|g|h|" +
        "i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z)*(1|2|3|4|5|6|7|8|9|0)*|while|for|struct|if|do"

    };
    
	/**
	 * Constructor for PatternTest.
	 * @param name
	 */
	public PatternTest(String name) {
		super(name);
	}

	public void testMatcher() {
	}
     	 
	/*
	 * Class under test for String[] split(CharSequence, int)
	 */
    public void testSplitCharSequenceint() {
        //splitting CharSequence which ends with pattern
     	//bug6193
     	assertEquals(",,".split(",", 3).length, 3);
     	assertEquals(",,".split(",", 4).length, 3);
     	//bug6193
        //bug5391
    	assertEquals(Pattern.compile("o").split("boo:and:foo",5).length, 5);
     	assertEquals(Pattern.compile("b").split("ab", -1).length, 2);
     	//bug5391
     	String s[];
     	Pattern pat = Pattern.compile("x");
     	s = pat.split("zxx:zzz:zxx",10);
     	assertEquals(s.length, 5);
     	s = pat.split("zxx:zzz:zxx",3);
     	assertEquals(s.length, 3);
     	s = pat.split("zxx:zzz:zxx",-1);
     	assertEquals(s.length, 5);
     	s = pat.split("zxx:zzz:zxx",0);
     	assertEquals(s.length, 3);
     	//other splitting
     	//negative limit
     	pat = Pattern.compile("b");
     	s = pat.split("abccbadfebb",-1);
     	assertEquals(s.length, 5);
     	s = pat.split("",-1);
     	assertEquals(s.length, 1);
     	pat = Pattern.compile("");
     	s = pat.split("",-1);
    	assertEquals(s.length, 1);
     	s = pat.split("abccbadfe", -1);
     	assertEquals(s.length, 11);
     	//zero limit    
     	pat = Pattern.compile("b");
     	s = pat.split("abccbadfebb", 0);
     	assertEquals(s.length, 3);
     	s = pat.split("", 0);
     	assertEquals(s.length, 1);
    	pat = Pattern.compile("");
     	s = pat.split("", 0);
     	assertEquals(s.length, 1);
    	s = pat.split("abccbadfe", 0);
     	assertEquals(s.length, 10);
     	//positive limit    
     	pat = Pattern.compile("b");
     	s = pat.split("abccbadfebb", 12);
    	assertEquals(s.length, 5);
     	s = pat.split("", 6);
     	assertEquals(s.length, 1);
     	pat = Pattern.compile("");
     	s = pat.split("", 11);
     	assertEquals(s.length, 1);
     	s = pat.split("abccbadfe", 15);
     	assertEquals(s.length, 11);
     	    
     	pat = Pattern.compile("b");
        s = pat.split("abccbadfebb", 5);
     	assertEquals(s.length, 5);
    	s = pat.split("", 1);
     	assertEquals(s.length, 1);
        pat = Pattern.compile("");
        s = pat.split("", 1);
        assertEquals(s.length, 1);
        s = pat.split("abccbadfe", 11);
        assertEquals(s.length, 11);
     	    
        pat = Pattern.compile("b");
        s = pat.split("abccbadfebb", 3);
        assertEquals(s.length, 3);
        pat = Pattern.compile("");
        s = pat.split("abccbadfe", 5);
        assertEquals(s.length, 5);	 	    
    }
      	
	/*
	 * Class under test for String[] split(CharSequence)
	 */
    public void testSplitCharSequence(){
        String s[];
        Pattern pat = Pattern.compile("b");
        s = pat.split("abccbadfebb");
        assertEquals(s.length, 3);
        s = pat.split("");
        assertEquals(s.length, 1);
        pat = Pattern.compile("");
        s = pat.split("");
        assertEquals(s.length, 1);
        s = pat.split("abccbadfe");
        assertEquals(s.length, 10);	  
        //bug6544
        String s1 = "";
        String[] arr = s1.split(":");
        assertEquals(arr.length, 1);	  
        //bug6544
	}


	public void testPattern() {
	}

	public void testFlags() {
	}

	/*
	 * Class under test for Pattern compile(String, int)
	 */
	public void testCompileStringint() {
	}

	/*
	 * Class under test for Pattern compile(String)
	 */
	public void testQuantCompileNeg() {
        String[] patterns = {"5{,2}", "{5asd", "{hgdhg", "{5,hjkh", "{,5hdsh", "{5,3shdfkjh}"};
        for (int i=0; i<patterns.length; i++) { 
            try {
                Pattern.compile(patterns[i]);
                fail("PatternSyntaxException was expected, but compilation succeeds");
            } catch (PatternSyntaxException pse) {
                continue;
            } catch (Throwable e) {
                fail("PatternSyntaxException was expected, but other one was thrown: " + e);
            }
        }
	}
    
    public void testQuantCompilePos() {
        String[] patterns = {/*"(abc){1,3}",*/ "abc{2,}", "abc{5}"};
        for (int i=0; i<patterns.length; i++) {
            Pattern.compile(patterns[i]);
        }
    }
    
    public void testQuantComposition() {
        String pattern = "(a{1,3})aab";
        java.util.regex.Pattern pat = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher mat = pat.matcher("aaab");
        mat.matches();
        mat.start(1);
        mat.group(1);
    }

	public void testMatches() {
        String[][] posSeq = {
              {"abb", "ababb", "abababbababb", "abababbababbabababbbbbabb"},
              {"213567", "12324567", "1234567", "213213567", "21312312312567", "444444567"},
              {"abcdaab", "aab", "abaab", "cdaab", "acbdadcbaab"},
              {"213234567", "3458", "0987654", "7689546432", "0398576", "98432", "5"},
              {"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", 
               "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" +
               "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"},
              {"ababbaAabababblice", "ababbaAliceababab", "ababbAabliceaaa", "abbbAbbbliceaaa", "Alice"},
              {"a123", "bnxnvgds156", "for", "while", "if", "struct"}
              
        };
        
        for (int i=0; i<testPatterns.length; i++) {
            for (int j=0; j<posSeq[i].length; j++) {
                assertTrue("Incorrect match: " + testPatterns[i] + " vs " + 
                        posSeq[i][j], Pattern.matches(testPatterns[i], posSeq[i][j]));
            }
        }
    }
    
    public void testTimeZoneIssue() {
        Pattern p = Pattern.compile("GMT(\\+|\\-)(\\d+)(:(\\d+))?");
        Matcher m = p.matcher("GMT-9:45");
        assertTrue(m.matches());
        assertEquals("-", m.group(1));
        assertEquals("9", m.group(2));
        assertEquals(":45", m.group(3));
        assertEquals("45", m.group(4));
    }
    
    
    public void testCompileRanges() {
        String[] correctTestPatterns = {
            "[^]*abb]*",
            "[^a-d[^m-p]]*abb",
            "[a-d\\d]*abb",
            "[abc]*abb",
            "[a-e&&[de]]*abb",
            "[^abc]*abb",
            "[a-e&&[^de]]*abb",
            "[a-z&&[^m-p]]*abb",
            "[a-d[m-p]]*abb",
            "[a-zA-Z]*abb",
            "[+*?]*abb",
            "[^+*?]*abb"
        };
            
        String[] inputSecuence = {
            "kkkk",
            "admpabb",
            "abcabcd124654abb",
            "abcabccbacababb",
            "dededededededeedabb",
            "gfdhfghgdfghabb",
            "accabacbcbaabb",
            "acbvfgtyabb",
            "adbcacdbmopabcoabb",
            "jhfkjhaSDFGHJkdfhHNJMjkhfabb",
            "+*??+*abb",
            "sdfghjkabb"
        };
        
        Pattern pat;
        
        for (int i=0; i<correctTestPatterns.length; i++) {
            assertTrue("pattern: " + correctTestPatterns[i] 
                     + " input: " + inputSecuence[i], 
                     Pattern.matches(correctTestPatterns[i], inputSecuence[i]));
            
        }
        
        String[] wrongInputSecuence = {
                "]",
                "admpkk",
                "abcabcd124k654abb",
                "abwcabccbacababb",
                "abababdeababdeabb",
                "abcabcacbacbabb",
                "acdcbecbaabb",
                "acbotyabb",
                "adbcaecdbmopabcoabb",
                "jhfkjhaSDFGHJk;dfhHNJMjkhfabb",
                "+*?a?+*abb",
                "sdf+ghjkabb"
            };
        
        for (int i=0; i<correctTestPatterns.length; i++) {
            assertFalse("pattern: " + correctTestPatterns[i] 
                     + " input: " + wrongInputSecuence[i], 
                     Pattern.matches(correctTestPatterns[i], wrongInputSecuence[i]));
            
        }
    }
    
    public void testRangesSpecialCases() {
        String neg_patterns[] = {
             "[a-&&[b-c]]",
             "[a-\\w]",
             "[b-a]",
             "[]"
        };
        
        for (int i=0; i<neg_patterns.length; i++) {
            try {
                Pattern.compile(neg_patterns[i]);
                fail("PatternSyntaxException was expected: " + neg_patterns[i]);
            } catch (PatternSyntaxException pse) {}
        }
        
        String pos_patterns[] = {
            "[-]+", "----",
            "[a-]+", "a-a-a-a-aa--",
            "[\\w-a]+", "123-2312--aaa-213",
            "[a-]]+", "-]]]]]]]]]]]]]]]"
        };
        
        for (int i=0; i<pos_patterns.length; i++) {
            String pat = pos_patterns[i++];
            String inp = pos_patterns[i];
            assertTrue("pattern: " + pat + " input: " + inp, Pattern.matches(pat, inp));
        }
    }
    
    public void testZeroSymbols() {
        assertTrue(Pattern.matches("[\0]*abb", "\0\0\0\0\0\0abb"));
    }
    
    public void testEscapes() {
        Pattern pat = Pattern.compile("\\Q{]()*?");
        Matcher mat = pat.matcher("{]()*?");
        
        assertTrue(mat.matches());
    }
    
    public void testBug181() {
        Pattern.compile("[\\t-\\r]");
    }
    
    public void _testBug187() {
        Pattern.compile("|(?idmsux-idmsux)|(?idmsux-idmsux)|[^|\\[-\\0274|\\,-\\\\[^|W\\}\\nq\\x65\\002\\xFE\\05\\06\\00\\x66\\x47i\\,\\xF2\\=\\06\\u0EA4\\x9B\\x3C\\f\\|\\{\\xE5\\05\\r\\u944A\\xCA\\e|\\x19\\04\\x07\\04\\u607B\\023\\0073\\x91Tr\\0150\\x83]]?(?idmsux-idmsux:\\p{Alpha}{7}?)||(?<=[^\\uEC47\\01\\02\\u3421\\a\\f\\a\\013q\\035w\\e])(?<=\\p{Punct}{0,}?)(?=^\\p{Lower})(?!\\b{8,14})(?<![|\\00-\\0146[^|\\04\\01\\04\\060\\f\\u224DO\\x1A\\xC4\\00\\02\\0315\\0351\\u84A8\\xCBt\\xCC\\06|\\0141\\00\\=\\e\\f\\x6B\\0026Tb\\040\\x76xJ&&[\\\\-\\]\\05\\07\\02\\u2DAF\\t\\x9C\\e\\0023\\02\\,X\\e|\\u6058flY\\u954C]]]{5}?)(?<=\\p{Sc}{8}+)[^|\\026-\\u89BA|o\\u6277\\t\\07\\x50&&\\p{Punct}]{8,14}+((?<=^\\p{Punct})|(?idmsux-idmsux)||(?>[\\x3E-\\]])|(?idmsux-idmsux:\\p{Punct})|(?<![\\0111\\0371\\xDF\\u6A49\\07\\u2A4D\\00\\0212\\02Xd-\\xED[^\\a-\\0061|\\0257\\04\\f\\[\\0266\\043\\03\\x2D\\042&&[^\\f-\\]&&\\s]]])|(?>[|\\n\\042\\uB09F\\06\\u0F2B\\uC96D\\x89\\uC166\\xAA|\\04-\\][^|\\a\\|\\rx\\04\\uA770\\n\\02\\t\\052\\056\\0274\\|\\=\\07\\e|\\00-\\x1D&&[^\\005\\uB15B\\uCDAC\\n\\x74\\0103\\0147\\uD91B\\n\\062G\\u9B4B\\077\\}\\0324&&[^\\0302\\,\\0221\\04\\u6D16\\04xy\\uD193\\[\\061\\06\\045\\x0F|\\e\\xBB\\f\\u1B52\\023\\u3AD2\\033\\007\\022\\}\\x66\\uA63FJ-\\0304]]]]{0,0})||(?<![^|\\0154U\\u0877\\03\\fy\\n\\|\\0147\\07-\\=[|q\\u69BE\\0243\\rp\\053\\02\\x33I\\u5E39\\u9C40\\052-\\xBC[|\\0064-\\?|\\uFC0C\\x30\\0060\\x45\\\\\\02\\?p\\xD8\\0155\\07\\0367\\04\\uF07B\\000J[^|\\0051-\\{|\\u9E4E\\u7328\\]\\u6AB8\\06\\x71\\a\\]\\e\\|KN\\u06AA\\0000\\063\\u2523&&[\\005\\0277\\x41U\\034\\}R\\u14C7\\u4767\\x09\\n\\054Ev\\0144\\<\\f\\,Q-\\xE4]]]]]{3}+)|(?>^+)|(?![^|\\|\\nJ\\t\\<\\04E\\\\\\t\\01\\\\\\02\\|\\=\\}\\xF3\\uBEC2\\032K\\014\\uCC5F\\072q\\|\\0153\\xD9\\0322\\uC6C8[^\\t\\0342\\x34\\x91\\06\\{\\xF1\\a\\u1710\\?\\xE7\\uC106\\02pF\\<&&[^|\\]\\064\\u381D\\u50CF\\eO&&[^|\\06\\x2F\\04\\045\\032\\u8536W\\0377\\0017|\\x06\\uE5FA\\05\\xD4\\020\\04c\\xFC\\02H\\x0A\\r]]]]+?)(?idmsux-idmsux)|(?<![|\\r-\\,&&[I\\t\\r\\0201\\xDB\\e&&[^|\\02\\06\\00\\<\\a\\u7952\\064\\051\\073\\x41\\?n\\040\\0053\\031&&[\\x15-\\|]]]]{8,11}?)(?![^|\\<-\\uA74B\\xFA\\u7CD2\\024\\07n\\<\\x6A\\0042\\uE4FF\\r\\u896B\\[\\=\\042Y&&^\\p{ASCII}]++)|(?<![R-\\|&&[\\a\\0120A\\u6145\\<\\050-d[|\\e-\\uA07C|\\016-\\u80D9]]]{1,}+)|(?idmsux-idmsux)|(?idmsux-idmsux)|(?idmsux-idmsux:\\B{6,}?)|(?<=\\D{5,8}?)|(?>[\\{-\\0207|\\06-\\0276\\p{XDigit}])(?idmsux-idmsux:[^|\\x52\\0012\\]u\\xAD\\0051f\\0142\\\\l\\|\\050\\05\\f\\t\\u7B91\\r\\u7763\\{|h\\0104\\a\\f\\0234\\u2D4F&&^\\P{InGreek}]))");
    }
    
    public void testOrphanQuantifiers() {
        try {
            Pattern.compile("+++++");
            fail("PatternSyntaxException expected");
        } catch (PatternSyntaxException pse) {
            System.out.println(pse);
        } catch (Throwable e) {
            fail("PatternSyntaxException expected other one was thrown: " + e);
        }
    }
    
    public void testOrphanQuantifiers2() {
        try {
            Pattern pat = Pattern.compile("\\d+*");
            fail("PatternSyntaxException expected");
        } catch (PatternSyntaxException pse) {
        } catch (Throwable e) {
            fail("PatternSyntaxException expected other one was thrown: " + e);
        }
    }
    
    public void testBug197() {
        Object[] vals = {":",  new Integer(2),  new String[] {"boo", "and:foo"}, 
                     ":",  new Integer(5),  new String[] { "boo", "and", "foo" },
                     ":", new Integer(-2), new  String[] { "boo", "and", "foo" },
                     ":", new Integer(3), new  String[] { "boo", "and", "foo" },
                     ":", new Integer(1), new  String[] { "boo:and:foo" },
                     "o", new Integer(5), new  String[] { "b", "", ":and:f", "", "" }, 
                     "o", new Integer(4), new  String[] { "b", "", ":and:f", "o"},
                     "o", new Integer(-2), new  String[] { "b", "", ":and:f", "", "" }, 
                     "o", new Integer(0), new  String[] { "b", "", ":and:f" }};

        for (int i=0; i<vals.length/3;) {
            String[] res = Pattern.compile(vals[i++].toString()).split("boo:and:foo", 
                    ((Integer)vals[i++]).intValue());
            String[] expectedRes = (String[])vals[i++];
            
            assertEquals(expectedRes.length, res.length);
            
            for(int j=0; j<expectedRes.length; j++) {
                assertEquals(expectedRes[j], res[j]);
            }
        }
    }
    
    public void testURIPatterns() {
        String URI_REGEXP_STR    = "^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?";
        String SCHEME_REGEXP_STR = "^[a-zA-Z]{1}[\\w+-.]+$";
        String REL_URI_REGEXP_STR= "^(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?";
        String IPV6_REGEXP_STR     = "^[0-9a-fA-F\\:\\.]+(\\%\\w+)?$";
        String IPV6_REGEXP_STR2    = "^\\[[0-9a-fA-F\\:\\.]+(\\%\\w+)?\\]$";
        String IPV4_REGEXP_STR     = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$";
        String HOSTNAME_REGEXP_STR = "\\w+[\\w\\-\\.]*";
        
        Pattern URI_REGEXP      = Pattern.compile(URI_REGEXP_STR); 
        Pattern REL_URI_REGEXP  = Pattern.compile(REL_URI_REGEXP_STR); 
        Pattern SCHEME_REGEXP   = Pattern.compile(SCHEME_REGEXP_STR); 
        Pattern IPV4_REGEXP     = Pattern.compile(IPV4_REGEXP_STR); 
        Pattern IPV6_REGEXP     = Pattern.compile(IPV6_REGEXP_STR); 
        Pattern IPV6_REGEXP2    = Pattern.compile(IPV6_REGEXP_STR2); 
        Pattern HOSTNAME_REGEXP = Pattern.compile(HOSTNAME_REGEXP_STR);
        
    }
    
    public void testFindBoundaryCases1() {
        Pattern pat = Pattern.compile(".*\n");
        Matcher mat = pat.matcher("a\n");
        
        mat.find();
        assertEquals("a\n", mat.group());
        
    }
    
    public void testFindBoundaryCases2() {
        Pattern pat = Pattern.compile(".*A");
        Matcher mat = pat.matcher("aAa");
        
        mat.find();
        assertEquals("aA", mat.group());
        
    }
    
    public void testFindBoundaryCases3() {
        Pattern pat = Pattern.compile(".*A");
        Matcher mat = pat.matcher("a\naA\n");
        
        mat.find();
        assertEquals("aA", mat.group());
        
    }
    
    public void testFindBoundaryCases4() {
        Pattern pat = Pattern.compile("A.*");
        Matcher mat = pat.matcher("A\n");
        
        mat.find();
        assertEquals("A", mat.group());
        
    }
    
    public void testFindBoundaryCases5() {
        Pattern pat = Pattern.compile(".*A.*");
        Matcher mat = pat.matcher("\nA\naaa\nA\naaAaa\naaaA\n");
        //Matcher mat = pat.matcher("\nA\n");
        String[] res = {
                "A",
                "A",
                "aaAaa",
                "aaaA"
        };
        int k=0;
        for(;mat.find(); k++) {
            assertEquals(res[k], mat.group());
        }
    }
    
    public void testFindBoundaryCases6() {
        String[] res = {
                "",
                "a",
                "",
                ""
        };
        Pattern pat = Pattern.compile(".*");
        Matcher mat = pat.matcher("\na\n");
        int k=0;        
        
        for(;mat.find();k++){
            assertEquals(res[k], mat.group());
        }
    }
    
    public void _testFindBoundaryCases7() {
        String[] res = {
                "",
                "a",
                "",
                ""
        };
        Pattern pat = Pattern.compile(".*");
        Matcher mat = pat.matcher("\na\n");
        int k=0;        
        
        for(;mat.find();k++){
            System.out.println(mat.group());
            System.out.flush();
        }
    }
    
    public void testBackReferences() {
        Pattern pat = Pattern.compile("(\\((\\w*):(.*):(\\2)\\))");
        Matcher mat = pat.matcher("(start1: word :start1)(start2: word :start2)");
        int k=1;
        for (;mat.find();k++) {
            assertEquals("start" + k, mat.group(2));
            assertEquals(" word ", mat.group(3));
            assertEquals("start" + k, mat.group(4));
            
        }
        
        assertEquals(3, k);
    }
    
    public void _testBackReferences1() {
        Pattern pat = Pattern.compile("(\\((\\w*):(.*):(\\2)\\))");
        Matcher mat = pat.matcher("(start1: word :start1)(start2: word :start2)");
        int k=1;
        for (;mat.find();k++) {
            System.out.println(mat.group(2));
            System.out.println(mat.group(3));
            System.out.println(mat.group(4));
            
        }
        
        assertEquals(3, k);
    }
    
    public void testNewLine() {
        Pattern pat = Pattern.compile("(^$)*\n", Pattern.MULTILINE);
        Matcher mat = pat.matcher("\r\n\n");
        int counter = 0;
        while (mat.find()) counter++;
        assertEquals(2, counter);
    }
    
    public void testFindGreedy() {
        Pattern pat = Pattern.compile(".*aaa", Pattern.DOTALL);
        Matcher mat = pat.matcher("aaaa\naaa\naaaaaa");
        mat.matches();
        assertEquals(15, mat.end());
    }
    
    public void testSOLQuant() {
        Pattern pat = Pattern.compile("$*", Pattern.MULTILINE);
        Matcher mat = pat.matcher("\n\n");
        int counter = 0;
        while(mat.find()) counter++;
        
        assertEquals(3, counter);
    }
    
    public void testIllegalEscape() {
        try {
            Pattern.compile("\\y");
            fail("PatternSyntaxException expected");
        } catch (PatternSyntaxException pse) {
            
        } catch (Exception t) {
            fail("PatternSyntaxException was expected" +
                    "but other one was thrown: " + t);
        }
    }
    
    public void testEmptyFamily() {
        Pattern.compile("\\p{Lower}");
        String a = "*";
    }
    
    public void testNonCaptConstr() {
        //Flags
        Pattern pat = Pattern.compile("(?i)b*(?-i)a*");
        assertTrue(pat.matcher("bBbBaaaa").matches());
        assertFalse(pat.matcher("bBbBAaAa").matches());
        
        //Non-capturing groups
        pat = Pattern.compile("(?i:b*)a*");
        assertTrue(pat.matcher("bBbBaaaa").matches());
        assertFalse(pat.matcher("bBbBAaAa").matches());
        
        pat = Pattern
        //             1                        2               3         4      5        6        7       8                 9                   10        11
        .compile("(?:-|(-?\\d+\\d\\d\\d))?(?:-|-(\\d\\d))?(?:-|-(\\d\\d))?(T)?(?:(\\d\\d):(\\d\\d):(\\d\\d)(\\.\\d+)?)?(?:(?:((?:\\+|\\-)\\d\\d):(\\d\\d))|(Z))?");
        Matcher mat = pat.matcher("-1234-21-31T41:51:61.789+71:81");
        assertTrue(mat.matches());
        assertEquals("-1234", mat.group(1));
        assertEquals("21", mat.group(2));
        assertEquals("31", mat.group(3));
        assertEquals("T", mat.group(4));
        assertEquals("41", mat.group(5));
        assertEquals("51", mat.group(6));
        assertEquals("61", mat.group(7));
        assertEquals(".789", mat.group(8));
        assertEquals("+71", mat.group(9));
        assertEquals("81", mat.group(10));
        
        //positive lookahead
        pat = Pattern.compile(".*\\.(?=log$).*$");
        assertTrue(pat.matcher("a.b.c.log").matches());
        assertFalse(pat.matcher("a.b.c.log.").matches());
        
        //negative lookahead
        pat = Pattern.compile(".*\\.(?!log$).*$");
        assertFalse(pat.matcher("abc.log").matches());
        assertTrue(pat.matcher("abc.logg").matches());
        
        //positive lookbehind
        pat = Pattern.compile(".*(?<=abc)\\.log$");
        assertFalse(pat.matcher("cde.log").matches());
        assertTrue(pat.matcher("abc.log").matches());
        
        //negative lookbehind
        pat = Pattern.compile(".*(?<!abc)\\.log$");
        assertTrue(pat.matcher("cde.log").matches());
        assertFalse(pat.matcher("abc.log").matches());
        
        //atomic group
        pat = Pattern.compile("(?>a*)abb");
        assertFalse(pat.matcher("aaabb").matches());
        pat = Pattern.compile("(?>a*)bb");
        assertTrue(pat.matcher("aaabb").matches());
        
        pat = Pattern.compile("(?>a|aa)aabb");
        assertTrue(pat.matcher("aaabb").matches());
        pat = Pattern.compile("(?>aa|a)aabb");
        assertFalse(pat.matcher("aaabb").matches());
        
        //quantifiers over look aheads
        pat = Pattern.compile(".*(?<=abc)*\\.log$");
        assertTrue(pat.matcher("cde.log").matches());
        pat = Pattern.compile(".*(?<=abc)+\\.log$");
        assertFalse(pat.matcher("cde.log").matches());
        
    }
        
    public static void main(String[] args) {
        junit.textui.TestRunner.run(PatternTest.class);
    }
}
