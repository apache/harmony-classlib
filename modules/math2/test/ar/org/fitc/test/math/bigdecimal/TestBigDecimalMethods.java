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
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */

package ar.org.fitc.test.math.bigdecimal;

import java.math.BigDecimal;
import java.math.MathContext;
import ar.org.fitc.test.util.Messages;
import junit.framework.TestCase;
/**
 * Test cases for 
 * add(BigDecimal) 
 * add(BigDecimal, MathContext) 
 * divideAndRemainder(BigDecimal) 
 * doubleValue() 
 * equals(Object ) 
 * hashCode() 
 * floatValue()
 * intValue() 
 * longValue()  
 * multiply(BigDecimal ) 
 * multiply(BigDecimal , MathContext )
 * remainder(BigDecimal ) 
 * remainder(BigDecimal , MathContext ) 
 * subtract(BigDecimal )  
 * toString()
 *
 */
public class TestBigDecimalMethods extends TestCase implements Messages{
	private boolean log = false;
	private BigDecimal bigDec = null;
	
	
	public TestBigDecimalMethods(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	

    /*
     * Test method for 'java.math.BigDecimal.hashCode()'
     */
    public final void testHashCode001() {
        String number = "-7.812738666512280684E+886";
        String number2 = "1759218604441600000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testHashCode001"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different hashcodes");
            System.out.println("-> actual result: HashCodes are NOT the same"); 
        }
        assertFalse(msgNotSame,bd.hashCode() == bigDec.hashCode());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode002() {
        String number = "7.812738666512280684E+644";
        String number2 = "-11591269220898191830411672692336373479273639933618096882665747059117441687798841746866600258510026367301059122300003621959529898077789766830392181873321533203125000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testHashCode002"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different hashcodes");
            System.out.println("-> actual result: HashCodes are NOT the same"); 
        }
        assertFalse(msgNotSame,bd.hashCode() == bigDec.hashCode());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode003() {
        String number = "-7.812738666512280684E-856";
        String number2 = "4722366482869645213696000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testHashCode003"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different hashcodes");
            System.out.println("-> actual result: HashCodes are NOT the same"); 
        }
        assertFalse(msgNotSame,bd.hashCode() == bigDec.hashCode());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode004() {
        String number = "-7.812738666512280684E-128";
        String number2 = "-248920611114445668285762562151204969623610086748846685324044664591587953361415337647357792925028544678278663582204899284988641738891601562500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testHashCode004"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different hashcodes");
            System.out.println("-> actual result: HashCodes are NOT the same"); 
        }
        assertFalse(msgNotSame,bd.hashCode() == bigDec.hashCode());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode005() {
        String number = "-7.812738666512280684E-545";
        String number2 = "43135914667441023671467224139231409077819431076064915969765776398745600000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testHashCode005"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different hashcodes");
            System.out.println("-> actual result: HashCodes are NOT the same"); 
        }
        assertFalse(msgNotSame,bd.hashCode() == bigDec.hashCode());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode006() {
        String number = "7.812738666512280684E+301";
        String number2 = "-830767497365572420564879412675215360000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testHashCode006"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different hashcodes");
            System.out.println("-> actual result: HashCodes are NOT the same"); 
        }
        assertFalse(msgNotSame,bd.hashCode() == bigDec.hashCode());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode007() {
        String number = "7.812738666512280684E-799";
        String number2 = "4951760157141521099596496896000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testHashCode007"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different hashcodes");
            System.out.println("-> actual result: HashCodes are NOT the same"); 
        }
        assertFalse(msgNotSame,bd.hashCode() == bigDec.hashCode());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode008() {
        String number = "-7.812738666512280684E-491";
        String number2 = "-2318253844179638366082334538467274695854727986723619376533149411823488337559768349373320051702005273460211824460000724391905979615557953366078436374664306640625000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testHashCode008"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different hashcodes");
            System.out.println("-> actual result: HashCodes are NOT the same"); 
        }
        assertFalse(msgNotSame,bd.hashCode() == bigDec.hashCode());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode009() {
        String number = "-7.812738666512280684E+113";
        String number2 = "107021940869550929675424468122125040655801207134962725745732646894072985904078844642292338460239479354291630578580041819674512159510065004997261428840781805692410980698428040999212806254515006534120873884092023171396617170588567014694590994905551014663456545589216433272830854545988889411563477283067554964310814247213239089741673025027390880586148251974273747724147590435149361910846165796087531894651317611303243011733332679114027996547520160675048828125000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testHashCode009"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different hashcodes");
            System.out.println("-> actual result: HashCodes are NOT the same"); 
        }
        assertFalse(msgNotSame,bd.hashCode() == bigDec.hashCode());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode010() {
        String number = "-3.689348814741910323E+161";
        String number2 = "-15777218104420236108234571305655724593464128702180460095405578613281250000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testHashCode010"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different hashcodes");
            System.out.println("-> actual result: HashCodes are NOT the same"); 
        }
        assertFalse(msgNotSame,bd.hashCode() == bigDec.hashCode());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode011() {
        String number = "3.689348814741910323E+29";
        String number2 = "150958496992861654089662183239530755636676848816657617135048252009824966495685954083445069007106350701658735001230553795990823385196991705302457370057307841745975813079040000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testHashCode011"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different hashcodes");
            System.out.println("-> actual result: HashCodes are NOT the same"); 
        }
        assertFalse(msgNotSame,bd.hashCode() == bigDec.hashCode());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode012() {
        String number = "3.689348814741910323E+508";
        String number2 = "78804012392788958424558080200287227610159478540930893335896586808491443542994421222828532509769831281613255980613632000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testHashCode012"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different hashcodes");
            System.out.println("-> actual result: HashCodes are NOT the same"); 
        }
        assertFalse(msgNotSame,bd.hashCode() == bigDec.hashCode());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode013() {
        String number = "3.689348814741910323E+681";
        String number2 = "436994993873214129706097166956708350993678881411295357199729151951767944417616335439228580716318181998128654620651240845861768505204366709906692902245553277900892247131030458103436298545516643924637451297481464347472084863384057367177715867713536000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testHashCode013"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different hashcodes");
            System.out.println("-> actual result: HashCodes are NOT the same"); 
        }
        assertFalse(msgNotSame,bd.hashCode() == bigDec.hashCode());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode014() {
        String number = "3.689348814741910323E-162";
        String number2 = "-218952885050752667331832747389049395512540928418205589337041919357779856669665718982287216931581497192382812500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testHashCode014"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different hashcodes");
            System.out.println("-> actual result: HashCodes are NOT the same"); 
        }
        assertFalse(msgNotSame,bd.hashCode() == bigDec.hashCode());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode015() {
        String number = "-3.689348814741910323E-204";
        String number2 = "-2672764710092195646140536467151481878815196880105048697961937492160398640987130358670498253559344448149204254150390625000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testHashCode015"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different hashcodes");
            System.out.println("-> actual result: HashCodes are NOT the same"); 
        }
        assertFalse(msgNotSame,bd.hashCode() == bigDec.hashCode());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode016() {
        String number = "3.689348814741910323E+787";
        String number2 = "-953674316406250000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testHashCode016"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different hashcodes");
            System.out.println("-> actual result: HashCodes are NOT the same"); 
        }
        assertFalse(msgNotSame,bd.hashCode() == bigDec.hashCode());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode017() {
        String number = "-3.689348814741910323E-363";
        String number2 = "-3.689348814741910323E-363";
        if (log) { System.out.println("testHashCode017"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same hashcodes");
            System.out.println("-> actual result: HashCodes are the same"); 
        }
       int hc=bigDec.hashCode();
        for(int i=1;i<15;i++){
        assertEquals(msgNotSame,bd.hashCode(),hc);
        }
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode018() {
        String number = "-3.689348814741910323E-97";
        String number2 = "-3.689348814741910323E-97";
        if (log) { System.out.println("testHashCode018"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same hashcodes");
            System.out.println("-> actual result: HashCodes are the same"); 
        }
        int hc=bigDec.hashCode();
        for(int i=1;i<15;i++){
        assertEquals(msgNotSame,bd.hashCode(),hc);
        }
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode019() {
        String number = "-3.689348814741910323E-496";
        String number2 = "-3.689348814741910323E-496";
        if (log) { System.out.println("testHashCode019"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same hashcodes");
            System.out.println("-> actual result: HashCodes are the same"); 
        }
        int hc=bigDec.hashCode();
        for(int i=1;i<15;i++){
        assertEquals(msgNotSame,bd.hashCode(),hc);
        }
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode020() {
        String number = "3.689348814741910323E-39";
        String number2 = "3.689348814741910323E-39";
        if (log) { System.out.println("testHashCode020"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same hashcodes");
            System.out.println("-> actual result: HashCodes are the same"); 
        }
        int hc=bigDec.hashCode();
        for(int i=1;i<15;i++){
        assertEquals(msgNotSame,bd.hashCode(),hc);
        }
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode021() {
        String number = "3.689348814741910323E-189";
        String number2 = "3.689348814741910323E-189";
        if (log) { System.out.println("testHashCode021"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same hashcodes");
            System.out.println("-> actual result: HashCodes are the same"); 
        }
        int hc=bigDec.hashCode();
        for(int i=1;i<15;i++){
        assertEquals(msgNotSame,bd.hashCode(),hc);
        }
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode022() {
        String number = "-3.689348814741910323E+83";
        String number2 = "-3.689348814741910323E+83";
        if (log) { System.out.println("testHashCode022"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same hashcodes");
            System.out.println("-> actual result: HashCodes are the same"); 
        }
        int hc=bigDec.hashCode();
        for(int i=1;i<15;i++){
        assertEquals(msgNotSame,bd.hashCode(),hc);
        }
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode023() {
        String number = "3.689348814741910323E+818";
        String number2 = "3.689348814741910323E+818";
        if (log) { System.out.println("testHashCode023"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same hashcodes");
            System.out.println("-> actual result: HashCodes are the same"); 
        }
        int hc=bigDec.hashCode();
        for(int i=1;i<15;i++){
        assertEquals(msgNotSame,bd.hashCode(),hc);
        }
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode024() {
        String number = "3.689348896666306118E-137";
        String number2 = "3.689348896666306118E-137";
        if (log) { System.out.println("testHashCode024"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same hashcodes");
            System.out.println("-> actual result: HashCodes are the same"); 
        }
        int hc=bigDec.hashCode();
        for(int i=1;i<15;i++){
        assertEquals(msgNotSame,bd.hashCode(),hc);
        }
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode025() {
        String number = "5.063812098665367110E-979";
        String number2 = "5.063812098665367110E-979";
        if (log) { System.out.println("testHashCode025"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same hashcodes");
            System.out.println("-> actual result: HashCodes are the same"); 
        }
        int hc=bigDec.hashCode();
        for(int i=1;i<15;i++){
        assertEquals(msgNotSame,bd.hashCode(),hc);
        }
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode026() {
        String number = "-5.063812098665367110E-38";
        String number2 = "-5.063812098665367110E-38";
        if (log) { System.out.println("testHashCode026"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same hashcodes");
            System.out.println("-> actual result: HashCodes are the same"); 
        }
        int hc=bigDec.hashCode();
        for(int i=1;i<15;i++){
        assertEquals(msgNotSame,bd.hashCode(),hc);
        }
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode027() {
        String number = "5.063812098665367110E-367";
        String number2 = "5.063812098665367110E-367";
        if (log) { System.out.println("testHashCode027"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same hashcodes");
            System.out.println("-> actual result: HashCodes are the same"); 
        }
        int hc=bigDec.hashCode();
        for(int i=1;i<15;i++){
        assertEquals(msgNotSame,bd.hashCode(),hc);
        }
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode028() {
        String number = "5.063812098665367110E-351";
        String number2 = "5.063812098665367110E-351";
        if (log) { System.out.println("testHashCode028"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same hashcodes");
            System.out.println("-> actual result: HashCodes are the same"); 
        }
        int hc=bigDec.hashCode();
        for(int i=1;i<15;i++){
        assertEquals(msgNotSame,bd.hashCode(),hc);
        }
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode029() {
        String number = "5.063812098665367110E-935";
        String number2 = "5.063812098665367110E-935";
        if (log) { System.out.println("testHashCode029"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same hashcodes");
            System.out.println("-> actual result: HashCodes are the same"); 
        }
        int hc=bigDec.hashCode();
        for(int i=1;i<15;i++){
        assertEquals(msgNotSame,bd.hashCode(),hc);
        }
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testHashCode030() {
        String number = "-5.063812098665367110E+964";
        String number2 = "-5.063812098665367110E+964";
        if (log) { System.out.println("testHashCode030"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same hashcodes");
            System.out.println("-> actual result: HashCodes are the same"); 
        }
        int hc=bigDec.hashCode();
        for(int i=1;i<15;i++){
        assertEquals(msgNotSame,bd.hashCode(),hc);
        }
        if (log) { System.out.println("---Test PASSED---"); }
    }
    
    /*
     * Test method for 'java.math.BigDecimal.equals(Object)'
     */
    public final void testEqualsObject001() {
        String number = "1.4251014049101104581E-556";
        String number2 = "29290953396399042086282117922423151436327376879670083944088563430033415153363091850995471257762008520361125930234489472566086748098270902913308121198734722268206176238849393716375256138723030291140929888141143909889924650205703390396591640049271226265741812899942382298737858100649455827680723726730263714393217484192843377361386915925710431349436251662582707466627801281236429857412373778810567907557003896852438264977001280938591269641970652731875787469262383847194709110177948391567931310568931118982164982287280204350317543149362364829357829876244068145751953125000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testEqualsObject001"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different Object");
            System.out.println("-> actual result: Objects are NOT same"); 
        }
        assertFalse(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject002() {
        String number = "-1.4251014049101104581E+306";
        String number2 = "-4591774807899560578002877098524397178979162331140966880893561352650067419745028018951416015625000000000000000000000000000000000000000000";
        if (log) { System.out.println("testEqualsObject002"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different Object");
            System.out.println("-> actual result: Objects are NOT same"); 
        }
        assertFalse(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject003() {
        String number = "-1.0127624197330734220E-948";
        String number2 = "-4951760157141521099596496896000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testEqualsObject003"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different Object");
            System.out.println("-> actual result: Objects are NOT same"); 
        }
        assertFalse(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject004() {
        String number = "-1.0127624197330734220E+532";
        String number2 = "17592186044416000000000000000000000000000000000000000";
        if (log) { System.out.println("testEqualsObject004"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different Object");
            System.out.println("-> actual result: Objects are NOT same"); 
        }
        assertFalse(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject005() {
        String number = "1.0127624197330734220E+798";
        String number2 = "57956346104490959152058363461681867396368199668090484413328735295587208438994208734333001292550131836505295611500018109797649490388948834151960909366607666015625000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testEqualsObject005"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different Object");
            System.out.println("-> actual result: Objects are NOT same"); 
        }
        assertFalse(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject006() {
        String number = "1.0127624197330734220E-658";
        String number2 = "392318858461667547739736838950479151006397215279002157056000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testEqualsObject006"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different Object");
            System.out.println("-> actual result: Objects are NOT same"); 
        }
        assertFalse(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject007() {
        String number = "-1.0127624197330734220E-457";
        String number2 = "-1331998346195134312723990566265599747399727533760146068020210961096082208012516814551212121739900272603387108130595582795926327348673608090089515881060593631819127778674073566270419912085482350955359374354027018393477403615438715423651576204486956442712735811253077476919218411269387426167701220344054659762751611387686506412200602241032831050208017207413954905251912408913832671597100387717117332956923014392143080157302451013428766129526168366822993787957893066377196606034792620974618844710172703297856286657737664625088543432382828761063039104385282741793705696409233496524393558502197265625000000000000000000000000";
        if (log) { System.out.println("testEqualsObject007"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different Object");
            System.out.println("-> actual result: Objects are NOT same"); 
        }
        assertFalse(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject008() {
        String number = "-1.0127624197330734220E+636";
        String number2 = "1015176734926259689309609822332217418729447539880438232642424128495790679155719789572962971622313714491865836423100358338640828536977450681112282543070471911375486599894278026753063528267297522736381130872312284420553118885499101209379801957766176201403141021728515625000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testEqualsObject008"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different Object");
            System.out.println("-> actual result: Objects are NOT same"); 
        }
        assertFalse(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject009() {
        String number = "1.0127624197330734220E-960";
        String number2 = "6427752177035961102167848369364650410088811975131171341205504000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testEqualsObject009"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different Object");
            System.out.println("-> actual result: Objects are NOT same"); 
        }
        assertFalse(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject010() {
        String number = "-1.0127624197330734220E-288";
        String number2 = "-4398046511104000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testEqualsObject010"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different Object");
            System.out.println("-> actual result: Objects are NOT same"); 
        }
        assertFalse(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject011() {
        String number = "-1.0127624197330734220E-752";
        String number2 = "-8580997075163262143727375998851741521586794125179131761743079323981928979247070065153199550826818193721620389239351072546402484999645804765717535363893821440000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testEqualsObject011"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different Object");
            System.out.println("-> actual result: Objects are NOT same"); 
        }
        assertFalse(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject012() {
        String number = "1.0127624197330734220E+564";
        String number2 = "-160000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testEqualsObject012"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different Object");
            System.out.println("-> actual result: Objects are NOT same"); 
        }
        assertFalse(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject013() {
        String number = "-1.0127624197330734220E-149";
        String number2 = "-83798799562141231872337656238786538296746036378702458610772259023261025187959668605011714363543146423062699113665537817835961767574666062165210306288025600000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testEqualsObject013"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different Object");
            System.out.println("-> actual result: Objects are NOT same"); 
        }
        assertFalse(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject014() {
        String number = "-1.0127624197330734220E-484";
        String number2 = "-1236652007365522670302512605098235950175656745506059199570315280464486125532659335851582005306215224947988357130080696696756825171533756049837730775509465839583033860743495680000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testEqualsObject014"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different Object");
            System.out.println("-> actual result: Objects are NOT same"); 
        }
        assertFalse(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject015() {
        String number = "-1.0127624197330734220E-767";
        String number2 = "-322781234760863573706989896500376484291213224103652939103832419567580952752105149328705669160017228929487896496593436672000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testEqualsObject015"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different Object");
            System.out.println("-> actual result: Objects are NOT same"); 
        }
        assertFalse(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject016() {
        String number = "1.0127624197330734220E+954";
        String number2 = "13937965749081639463459823920405225941237760000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testEqualsObject016"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Different Object");
            System.out.println("-> actual result: Objects are NOT same"); 
        }
        assertFalse(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject017() {
        String number = "1.0127624197330734220E-686";
        String number2 = "1.0127624197330734220E-686";
        if (log) { System.out.println("testEqualsObject017"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same Object");
            System.out.println("-> actual result: Objects are same"); 
        }
        assertTrue(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject018() {
        String number = "1.0127624197330734220E-18";
        String number2 = "1.0127624197330734220E-18";
        if (log) { System.out.println("testEqualsObject018"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same Object");
            System.out.println("-> actual result: Objects are same"); 
        }
        assertTrue(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject019() {
        String number = "1.0127645169976057759E+406";
        String number2 = "1.0127645169976057759E+406";
        if (log) { System.out.println("testEqualsObject019"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same Object");
            System.out.println("-> actual result: Objects are same"); 
        }
        assertTrue(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject020() {
        String number = "1.1502087481254191007E+217";
        String number2 = "1.1502087481254191007E+217";
        if (log) { System.out.println("testEqualsObject020"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same Object");
            System.out.println("-> actual result: Objects are same"); 
        }
        assertTrue(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject021() {
        String number = "1.1502087481254191007E+944";
        String number2 = "1.1502087481254191007E+944";
        if (log) { System.out.println("testEqualsObject021"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same Object");
            System.out.println("-> actual result: Objects are same"); 
        }
        assertTrue(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject022() {
        String number = "1.1502087481254191007E+815";
        String number2 = "1.1502087481254191007E+815";
        if (log) { System.out.println("testEqualsObject022"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same Object");
            System.out.println("-> actual result: Objects are same"); 
        }
        assertTrue(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject023() {
        String number = "1.1502087481254191007E-643";
        String number2 = "1.1502087481254191007E-643";
        if (log) { System.out.println("testEqualsObject023"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same Object");
            System.out.println("-> actual result: Objects are same"); 
        }
        assertTrue(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject024() {
        String number = "1.1502087481254191007E-837";
        String number2 = "1.1502087481254191007E-837";
        if (log) { System.out.println("testEqualsObject024"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same Object");
            System.out.println("-> actual result: Objects are same"); 
        }
        assertTrue(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject025() {
        String number = "-1.1502087481254191007E+329";
        String number2 = "-1.1502087481254191007E+329";
        if (log) { System.out.println("testEqualsObject025"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same Object");
            System.out.println("-> actual result: Objects are same"); 
        }
        assertTrue(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject026() {
        String number = "-1.1502087481254191007E-550";
        String number2 = "-1.1502087481254191007E-550";
        if (log) { System.out.println("testEqualsObject026"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same Object");
            System.out.println("-> actual result: Objects are same"); 
        }
        assertTrue(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject027() {
        String number = "-1.1502087481254191007E+991";
        String number2 = "-1.1502087481254191007E+991";
        if (log) { System.out.println("testEqualsObject027"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same Object");
            System.out.println("-> actual result: Objects are same"); 
        }
        assertTrue(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject028() {
        String number = "-1.1502087481254191007E+172";
        String number2 = "-1.1502087481254191007E+172";
        if (log) { System.out.println("testEqualsObject028"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same Object");
            System.out.println("-> actual result: Objects are same"); 
        }
        assertTrue(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject029() {
        String number = "-1.1502087481254191007E-127";
        String number2 = "-1.1502087481254191007E-127";
        if (log) { System.out.println("testEqualsObject029"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same Object");
            System.out.println("-> actual result: Objects are same"); 
        }
        assertTrue(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testEqualsObject030() {
        String number = "-1.1502087481254191007E+902";
        String number2 = "-1.1502087481254191007E+902";
        if (log) { System.out.println("testEqualsObject030"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: Same Object");
            System.out.println("-> actual result: Objects are same"); 
        }
        assertTrue(msgNotSame,bigDec.equals(bd));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    
    /*
     * Test method for 'java.math.BigDecimal.toString()'
     */
    public final void testToString001() {
        String number = "1.953184666628070171E+945";
        if (log) { System.out.println("testToString001"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: 1.953184666628070171E+945");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"1.953184666628070171E+945",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString002() {
        String number = "1.6059518370053021406E+763";
        if (log) { System.out.println("testToString002"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: 1.6059518370053021406E+763");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"1.6059518370053021406E+763",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString003() {
        String number = "1.6059518370053021406E+394";
        if (log) { System.out.println("testToString003"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: 1.6059518370053021406E+394");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"1.6059518370053021406E+394",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString004() {
        String number = "-1.6059518370053021406E-880";
        if (log) { System.out.println("testToString004"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: -1.6059518370053021406E-880");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"-1.6059518370053021406E-880",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString005() {
        String number = "-1.6059518370053021406E-561";
        if (log) { System.out.println("testToString005"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: -1.6059518370053021406E-561");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"-1.6059518370053021406E-561",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString006() {
        String number = "-1.6059518370053021406E-945";
        if (log) { System.out.println("testToString006"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: -1.6059518370053021406E-945");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"-1.6059518370053021406E-945",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString007() {
        String number = "-1.6059518370053021406E-827";
        if (log) { System.out.println("testToString007"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: -1.6059518370053021406E-827");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"-1.6059518370053021406E-827",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString008() {
        String number = "1.6059518370053021406E+257";
        if (log) { System.out.println("testToString008"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: 1.6059518370053021406E+257");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"1.6059518370053021406E+257",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString009() {
        String number = "1.6059518370053021406E+739";
        if (log) { System.out.println("testToString009"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: 1.6059518370053021406E+739");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"1.6059518370053021406E+739",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString010() {
        String number = "1.6059518370053021406E-531";
        if (log) { System.out.println("testToString010"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: 1.6059518370053021406E-531");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"1.6059518370053021406E-531",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString011() {
        String number = "1.6059518370053021406E+164";
        if (log) { System.out.println("testToString011"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: 1.6059518370053021406E+164");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"1.6059518370053021406E+164",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString012() {
        String number = "1.6059518370053021406E-212";
        if (log) { System.out.println("testToString012"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: 1.6059518370053021406E-212");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"1.6059518370053021406E-212",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString013() {
        String number = "-1.6059518370053021406E-27";
        if (log) { System.out.println("testToString013"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: -1.6059518370053021406E-27");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"-1.6059518370053021406E-27",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString014() {
        String number = "-1.6059518370053021406E+853";
        if (log) { System.out.println("testToString014"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: -1.6059518370053021406E+853");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"-1.6059518370053021406E+853",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString015() {
        String number = "1.6059518370053021406E-766";
        if (log) { System.out.println("testToString015"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: 1.6059518370053021406E-766");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"1.6059518370053021406E-766",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString016() {
        String number = "-1.6059518370053021406E-324";
        if (log) { System.out.println("testToString016"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: -1.6059518370053021406E-324");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"-1.6059518370053021406E-324",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString017() {
        String number = "-1.6059518370053021406E+228";
        if (log) { System.out.println("testToString017"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: -1.6059518370053021406E+228");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"-1.6059518370053021406E+228",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString018() {
        String number = "1.6059518370053026289E+247";
        if (log) { System.out.println("testToString018"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: 1.6059518370053026289E+247");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"1.6059518370053026289E+247",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString019() {
        String number = "-0.0017433981653976478193";
        if (log) { System.out.println("testToString019"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: -0.0017433981653976478193");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"-0.0017433981653976478193",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString020() {
        String number = "-1.7433981653976478193E+283";
        if (log) { System.out.println("testToString020"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: -1.7433981653976478193E+283");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"-1.7433981653976478193E+283",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString021() {
        String number = "1.7433981653976478193E-691";
        if (log) { System.out.println("testToString021"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: 1.7433981653976478193E-691");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"1.7433981653976478193E-691",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString022() {
        String number = "-1.7433981653976478193E+321";
        if (log) { System.out.println("testToString022"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: -1.7433981653976478193E+321");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"-1.7433981653976478193E+321",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString023() {
        String number = "1.7433981653976478193E-550";
        if (log) { System.out.println("testToString023"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: 1.7433981653976478193E-550");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"1.7433981653976478193E-550",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString024() {
        String number = "1.7433981653976478193E+871";
        if (log) { System.out.println("testToString024"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: 1.7433981653976478193E+871");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"1.7433981653976478193E+871",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString025() {
        String number = "-1.7433981653976478193E-259";
        if (log) { System.out.println("testToString025"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: -1.7433981653976478193E-259");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"-1.7433981653976478193E-259",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString026() {
        String number = "1.7433981653976478193E+574";
        if (log) { System.out.println("testToString026"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: 1.7433981653976478193E+574");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"1.7433981653976478193E+574",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString027() {
        String number = "-1.7433981653976478193E+695";
        if (log) { System.out.println("testToString027"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: -1.7433981653976478193E+695");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"-1.7433981653976478193E+695",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString028() {
        String number = "-1.7433981653976478193E+349";
        if (log) { System.out.println("testToString028"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: -1.7433981653976478193E+349");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"-1.7433981653976478193E+349",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString029() {
        String number = "1.7433981653976478193E+277";
        if (log) { System.out.println("testToString029"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: 1.7433981653976478193E+277");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"1.7433981653976478193E+277",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testToString030() {
        String number = "1.7433981653976478193E+675";
        if (log) { System.out.println("testToString030"); }
        bigDec = new BigDecimal(number);
        if (log) { 
            System.out.println("-> expected result: 1.7433981653976478193E+675");
            System.out.println("-> actual result: "+ bigDec.toString()); 
        }
        assertEquals(msgNotSame,"1.7433981653976478193E+675",bigDec.toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    
    /*
     * Test method for 'java.math.BigDecimal.intValue()'
     */
    /** This method test that for a BigInteger whose value is 
     * 0 the returning value of 
     * <b>intValue()</b> should be 0
     */
   public void testIntValue001() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,0,bigDec.intValue());
}
    /** This method test that for a BigInteger whose value is 
     * 2147483647 the returning value of 
     * <b>intValue()</b> should be 2147483647
     */
   public void testIntValue002() {
    bigDec= new BigDecimal("2147483647");  
 assertEquals(msgNotSame,2147483647,bigDec.intValue());
}
    /** This method test that for a BigInteger whose value is 
     * -2147483648 the returning value of 
     * <b>intValue()</b> should be -2147483648
     */
   public void testIntValue003() {
    bigDec= new BigDecimal("-2147483648");  
 assertEquals(msgNotSame,-2147483648,bigDec.intValue());
}
    /** This method test that for a BigInteger whose value is 
     * 2147483646 the returning value of 
     * <b>intValue()</b> should be 2147483646
     */
   public void testIntValue004() {
    bigDec= new BigDecimal("2147483646");  
 assertEquals(msgNotSame,2147483646,bigDec.intValue());
}
    /** This method test that for a BigInteger whose value is 
     * -2147483647 the returning value of 
     * <b>intValue()</b> should be -2147483647
     */
   public void testIntValue005() {
    bigDec= new BigDecimal("-2147483647");  
 assertEquals(msgNotSame,-2147483647,bigDec.intValue());
}
    /** This method test that for a BigInteger whose value is 
     * 2147483648 the returning value of 
     * <b>intValue()</b> should be -2147483648
     */
   public void testIntValue006() {
    bigDec= new BigDecimal("2147483648");  
 assertEquals(msgNotSame,-2147483648,bigDec.intValue());
}
    /** This method test that for a BigInteger whose value is 
     * -2147483649 the returning value of 
     * <b>intValue()</b> should be 2147483647
     */
   public void testIntValue007() {
    bigDec= new BigDecimal("-2147483649");  
 assertEquals(msgNotSame,2147483647,bigDec.intValue());
}
    /** This method test that for a BigInteger whose value is 
     * -0.01E-5000 the returning value of 
     * <b>intValue()</b> should be 0
     */
   public void testIntValue008() {
    bigDec= new BigDecimal("-0.01E-5000");  
 assertEquals(msgNotSame,0,bigDec.intValue());
}
    /** This method test that for a BigInteger whose value is 
     * 0.01E-5000 the returning value of 
     * <b>intValue()</b> should be 0
     */
   public void testIntValue009() {
    bigDec= new BigDecimal("0.01E-5000");  
 assertEquals(msgNotSame,0,bigDec.intValue());
}
    /** This method test that for a BigInteger whose value is 
     * 1.3889313184910721216E+940 the returning value of 
     * <b>intValue()</b> should be 0
     */
   public void testIntValue010() {
    bigDec= new BigDecimal("1.3889313184910721216E+940");  
 assertEquals(msgNotSame,0,bigDec.intValue());
}
    /** This method test that for a BigInteger whose value is 
     * 1.3889313184910721216E-1005 the returning value of 
     * <b>intValue()</b> should be 0
     */
   public void testIntValue011() {
    bigDec= new BigDecimal("1.3889313184910721216E-1005");  
 assertEquals(msgNotSame,0,bigDec.intValue());
}
    /** This method test that for a BigInteger whose value is 
     * -1.3889313184910721216E+940 the returning value of 
     * <b>intValue()</b> should be 0
     */
   public void testIntValue012() {
    bigDec= new BigDecimal("-1.3889313184910721216E+940");  
 assertEquals(msgNotSame,0,bigDec.intValue());
}
    /** This method test that for a BigInteger whose value is 
     * 18446744073709551615 the returning value of 
     * <b>intValue()</b> should be -1
     */
   public void testIntValue013() {
    bigDec= new BigDecimal("18446744073709551615");  
 assertEquals(msgNotSame,-1,bigDec.intValue());
}
    /** This method test that for a BigInteger whose value is 
     * 2147483648 the returning value of 
     * <b>intValue()</b> should be -2147483648
     */
   public void testIntValue014() {
    bigDec= new BigDecimal("2147483648");  
 assertEquals(msgNotSame,-2147483648,bigDec.intValue());
}

    
    /*
     * Test method for 'java.math.BigDecimal.longValue()'
     */
   /** This method test that for a BigInteger whose value is 
    * 0 the returning value of 
    * <b>LongValue()</b> should be 0
    */
  public void testLongValue001() {
   bigDec= new BigDecimal("0");  
assertEquals(msgNotSame,0,bigDec.longValue());
}
   /** This method test that for a BigInteger whose value is 
    * 9223372036854775807 the returning value of 
    * <b>LongValue()</b> should be 9223372036854775807
    */
  public void testLongValue002() {
   bigDec= new BigDecimal("9223372036854775807");  
assertEquals(msgNotSame,9223372036854775807L,bigDec.longValue());
}
   /** This method test that for a BigInteger whose value is 
    * -9223372036854775808 the returning value of 
    * <b>LongValue()</b> should be -9223372036854775808
    */
  public void testLongValue003() {
   bigDec= new BigDecimal("-9223372036854775808");  
assertEquals(msgNotSame,-9223372036854775808L,bigDec.longValue());
}
   /** This method test that for a BigInteger whose value is 
    * 9223372036854775806 the returning value of 
    * <b>LongValue()</b> should be 9223372036854775806
    */
  public void testLongValue004() {
   bigDec= new BigDecimal("9223372036854775806");  
assertEquals(msgNotSame,9223372036854775806L,bigDec.longValue());
}
   /** This method test that for a BigInteger whose value is 
    * -9223372036854775807 the returning value of 
    * <b>LongValue()</b> should be -9223372036854775807
    */
  public void testLongValue005() {
   bigDec= new BigDecimal("-9223372036854775807");  
assertEquals(msgNotSame,-9223372036854775807L,bigDec.longValue());
}
   /** This method test that for a BigInteger whose value is 
    * 9223372036854775808 the returning value of 
    * <b>LongValue()</b> should be -9223372036854775808
    */
  public void testLongValue006() {
   bigDec= new BigDecimal("9223372036854775808");  
assertEquals(msgNotSame,-9223372036854775808L,bigDec.longValue());
}
   /** This method test that for a BigInteger whose value is 
    * -9223372036854775809 the returning value of 
    * <b>LongValue()</b> should be 9223372036854775807
    */
  public void testLongValue007() {
   bigDec= new BigDecimal("-9223372036854775809");  
assertEquals(msgNotSame,9223372036854775807L,bigDec.longValue());
}
   /** This method test that for a BigInteger whose value is 
    * -0.01E-10000 the returning value of 
    * <b>LongValue()</b> should be 0
    */
  public void testLongValue008() {
   bigDec= new BigDecimal("-0.01E-10000");  
assertEquals(msgNotSame,0,bigDec.longValue());
}
   /** This method test that for a BigInteger whose value is 
    * 0.01E-10000 the returning value of 
    * <b>LongValue()</b> should be 0
    */
  public void testLongValue009() {
   bigDec= new BigDecimal("0.01E-10000");  
assertEquals(msgNotSame,0,bigDec.longValue());
}
   /** This method test that for a BigInteger whose value is 
    * -1.3889313184910721216E+640 the returning value of 
    * <b>LongValue()</b> should be 0
    */
  public void testLongValue010() {
   bigDec= new BigDecimal("-1.3889313184910721216E+640");  
assertEquals(msgNotSame,0,bigDec.longValue());
}
   /** This method test that for a BigInteger whose value is 
    * -1.3889313184910721216E-805 the returning value of 
    * <b>LongValue()</b> should be 0
    */
  public void testLongValue011() {
   bigDec= new BigDecimal("-1.3889313184910721216E-805");  
assertEquals(msgNotSame,0,bigDec.longValue());
}
   /** This method test that for a BigInteger whose value is 
    * -1.3889313184910721216E+640 the returning value of 
    * <b>LongValue()</b> should be 0
    */
  public void testLongValue012() {
   bigDec= new BigDecimal("-1.3889313184910721216E+640");  
assertEquals(msgNotSame,0,bigDec.longValue());
}
   /** This method test that for a BigInteger whose value is 
    * -1.3889313184910721216E-805 the returning value of 
    * <b>LongValue()</b> should be 0
    */
  public void testLongValue013() {
   bigDec= new BigDecimal("-1.3889313184910721216E-805");  
assertEquals(msgNotSame,0,bigDec.longValue());
}
   /** This method test that for a BigInteger whose value is 
    * 9786654333763546378654366786664532156786513568765163874687695432187653387863512378632137866453341678432138745333333241377775644433337853331788888.678354415378334551335438743453333488888888864321111003215043540323570085600034335350800546876795378544453153456879997805013604568709542643768307863607960378 the returning value of 
    * <b>LongValue()</b> should be -1008344516840500136
    */
  public void testLongValue014() {
   bigDec= new BigDecimal("9786654333763546378654366786664532156786513568765163874687695432187653387863512378632137866453341678432138745333333241377775644433337853331788888.678354415378334551335438743453333488888888864321111003215043540323570085600034335350800546876795378544453153456879997805013604568709542643768307863607960378");  
assertEquals(msgNotSame,-1008344516840500136L,bigDec.longValue());
}
   /** This method test that for a BigInteger whose value is 
    * -9786654333763546378654366786664532156786513568765163874687695432187653387863512378632137866453341678432138745333333241377775644433337853331788888.678354415378334551335438743453333488888888864321111003215043540323570085600034335350800546876795378544453153456879997805013604568709542643768307863607960378 the returning value of 
    * <b>LongValue()</b> should be 1008344516840500136
    */
  public void testLongValue015() {
   bigDec= new BigDecimal("-9786654333763546378654366786664532156786513568765163874687695432187653387863512378632137866453341678432138745333333241377775644433337853331788888.678354415378334551335438743453333488888888864321111003215043540323570085600034335350800546876795378544453153456879997805013604568709542643768307863607960378");  
assertEquals(msgNotSame,1008344516840500136L,bigDec.longValue());
}

    
    /*
     * Test method for 'java.math.BigDecimal.floatValue()'
     */
   /** This method test that for a BigInteger whose value is 
    * 0 the returning value of 
    * <b>floatValue()</b> should be 0.0
    */
  public void testFloatValue001() {
   bigDec= new BigDecimal("0");  
assertEquals(msgNotSame, 0.0f,bigDec.floatValue());
}
   /** This method test that for a BigInteger whose value is 
    * 1.7976931348623157E308 the returning value of 
    * <b>floatValue()</b> should be Infinity
    */
  public void testFloatValue002() {
   bigDec= new BigDecimal("1.7976931348623157E308");  
assertEquals(msgNotSame, Float.POSITIVE_INFINITY,bigDec.floatValue());
}
   /** This method test that for a BigInteger whose value is 
    * 1.401298464324817E-45 the returning value of 
    * <b>floatValue()</b> should be 1.4E-45
    */
  public void testFloatValue003() {
   bigDec= new BigDecimal("1.401298464324817E-45");  
assertEquals(msgNotSame, 1.4E-45f,bigDec.floatValue());
}
   /** This method test that for a BigInteger whose value is 
    * 3.4028234663852885E38 the returning value of 
    * <b>floatValue()</b> should be 3.4028235E38
    */
  public void testFloatValue004() {
   bigDec= new BigDecimal("3.4028234663852885E38");  
assertEquals(msgNotSame, 3.4028235E38f,bigDec.floatValue());
}
   /** This method test that for a BigInteger whose value is 
    * 1.401298464324816E-45 the returning value of 
    * <b>floatValue()</b> should be 1.4E-45
    */
  public void testFloatValue005() {
   bigDec= new BigDecimal("1.401298464324816E-45");  
assertEquals(msgNotSame, 1.4E-45f,bigDec.floatValue());
}
   /** This method test that for a BigInteger whose value is 
    * 3.4028234663852887E38 the returning value of 
    * <b>floatValue()</b> should be 3.4028235E38
    */
  public void testFloatValue006() {
   bigDec= new BigDecimal("3.4028234663852887E38");  
assertEquals(msgNotSame, 3.4028235E38f,bigDec.floatValue());
}
   /** This method test that for a BigInteger whose value is 
    * 1.401298464324818E-45 the returning value of 
    * <b>floatValue()</b> should be 1.4E-45
    */
  public void testFloatValue007() {
   bigDec= new BigDecimal("1.401298464324818E-45");  
assertEquals(msgNotSame, 1.4E-45f,bigDec.floatValue());
}
   /** This method test that for a BigInteger whose value is 
    * -0.01E-5000 the returning value of 
    * <b>floatValue()</b> should be -0.0
    */
  public void testFloatValue008() {
   bigDec= new BigDecimal("-0.01E-5000");  
assertEquals(msgNotSame, -0.0f,bigDec.floatValue());
}
   /** This method test that for a BigInteger whose value is 
    * 0.01E-5000 the returning value of 
    * <b>floatValue()</b> should be 0.0
    */
  public void testFloatValue009() {
   bigDec= new BigDecimal("0.01E-5000");  
assertEquals(msgNotSame, 0.0f,bigDec.floatValue());
}
   /** This method test that for a BigInteger whose value is 
    * -1.3889313184910721216E+640 the returning value of 
    * <b>floatValue()</b> should be -Infinity
    */
  public void testFloatValue010() {
   bigDec= new BigDecimal("-1.3889313184910721216E+640");  
assertEquals(msgNotSame, Float.NEGATIVE_INFINITY,bigDec.floatValue());
}
   /** This method test that for a BigInteger whose value is 
    * -1.3889313184910721216E-805 the returning value of 
    * <b>floatValue()</b> should be -0.0
    */
  public void testFloatValue011() {
   bigDec= new BigDecimal("-1.3889313184910721216E-805");  
assertEquals(msgNotSame, -0.0f,bigDec.floatValue());
}
   /** This method test that for a BigInteger whose value is 
    * -1.3889313184910721216E+640 the returning value of 
    * <b>floatValue()</b> should be -Infinity
    */
  public void testFloatValue012() {
   bigDec= new BigDecimal("-1.3889313184910721216E+640");  
assertEquals(msgNotSame, Float.NEGATIVE_INFINITY,bigDec.floatValue());
}
   /** This method test that for a BigInteger whose value is 
    * -1.3889313184910721216E-805 the returning value of 
    * <b>floatValue()</b> should be -0.0
    */
  public void testFloatValue013() {
   bigDec= new BigDecimal("-1.3889313184910721216E-805");  
assertEquals(msgNotSame, -0.0f,bigDec.floatValue());
}

    
    /*
     * Test method for 'java.math.BigDecimal.doubleValue()'
     */
    /** This method test that for a BigInteger whose value is 
     * 0 the returning value of 
     * <b>doubleValue()</b> should be 0.0
     */
   public void testDoubleValue001() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame, 0.0,bigDec.doubleValue());
}
    /** This method test that for a BigInteger whose value is 
     * 1.7976931348623157E308 the returning value of 
     * <b>doubleValue()</b> should be 1.7976931348623157E308
     */
   public void testDoubleValue002() {
    bigDec= new BigDecimal("1.7976931348623157E308");  
 assertEquals(msgNotSame, 1.7976931348623157E308,bigDec.doubleValue());
}
    /** This method test that for a BigInteger whose value is 
     * 4.9E-324 the returning value of 
     * <b>doubleValue()</b> should be 4.9E-324
     */
   public void testDoubleValue003() {
    bigDec= new BigDecimal("4.9E-324");  
 assertEquals(msgNotSame, 4.9E-324,bigDec.doubleValue());
}
    /** This method test that for a BigInteger whose value is 
     * 7976931348623157E307 the returning value of 
     * <b>doubleValue()</b> should be Infinity
     */
   public void testDoubleValue004() {
    bigDec= new BigDecimal("7976931348623157E307");  
 assertEquals(msgNotSame, Double.POSITIVE_INFINITY,bigDec.doubleValue());
}
    /** This method test that for a BigInteger whose value is 
     * 4.8E-324 the returning value of 
     * <b>doubleValue()</b> should be 4.9E-324
     */
   public void testDoubleValue005() {
    bigDec= new BigDecimal("4.8E-324");  
 assertEquals(msgNotSame, 4.9E-324,bigDec.doubleValue());
}
    /** This method test that for a BigInteger whose value is 
     * 7976931348623157E309 the returning value of 
     * <b>doubleValue()</b> should be Infinity
     */
   public void testDoubleValue006() {
    bigDec= new BigDecimal("7976931348623157E309");  
 assertEquals(msgNotSame, Double.POSITIVE_INFINITY,bigDec.doubleValue());
}
    /** This method test that for a BigInteger whose value is 
     * 5E-324 the returning value of 
     * <b>doubleValue()</b> should be 4.9E-324
     */
   public void testDoubleValue007() {
    bigDec= new BigDecimal("5E-324");  
 assertEquals(msgNotSame, 4.9E-324,bigDec.doubleValue());
}
    /** This method test that for a BigInteger whose value is 
     * -0.01E-5000 the returning value of 
     * <b>doubleValue()</b> should be -0.0
     */
   public void testDoubleValue008() {
    bigDec= new BigDecimal("-0.01E-5000");  
 assertEquals(msgNotSame, -0.0,bigDec.doubleValue());
}
    /** This method test that for a BigInteger whose value is 
     * 0.01E-5000 the returning value of 
     * <b>doubleValue()</b> should be 0.0
     */
   public void testDoubleValue009() {
    bigDec= new BigDecimal("0.01E-5000");  
 assertEquals(msgNotSame, 0.0,bigDec.doubleValue());
}
    /** This method test that for a BigInteger whose value is 
     * 1.3889313184910721216E+640 the returning value of 
     * <b>doubleValue()</b> should be Infinity
     */
   public void testDoubleValue010() {
    bigDec= new BigDecimal("1.3889313184910721216E+640");  
 assertEquals(msgNotSame, Double.POSITIVE_INFINITY,bigDec.doubleValue());
}
    /** This method test that for a BigInteger whose value is 
     * 1.3889313184910721216E-805 the returning value of 
     * <b>doubleValue()</b> should be 0.0
     */
   public void testDoubleValue011() {
    bigDec= new BigDecimal("1.3889313184910721216E-805");  
 assertEquals(msgNotSame, 0.0,bigDec.doubleValue());
}
    /** This method test that for a BigInteger whose value is 
     * -1.3889313184910721216E+640 the returning value of 
     * <b>doubleValue()</b> should be -Infinity
     */
   public void testDoubleValue012() {
    bigDec= new BigDecimal("-1.3889313184910721216E+640");  
 assertEquals(msgNotSame, Double.NEGATIVE_INFINITY,bigDec.doubleValue());
}
    /** This method test that for a BigInteger whose value is 
     * -1.3889313184910721216E-805 the returning value of 
     * <b>doubleValue()</b> should be -0.0
     */
   public void testDoubleValue013() {
    bigDec= new BigDecimal("-1.3889313184910721216E-805");  
 assertEquals(msgNotSame, -0.0,bigDec.doubleValue());
}
    
 

    

    
    /*
     * Test method for 'java.math.BigDecimal.add(BigDecimal)'
     */
    
    public final void testAddBigDecimal001() {
        String number = "7.95741901218843403E-94";
        String number2 = "9.042521604759584125E-649";
        if (log) { System.out.println("testAddBigDecimal001"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 7.957419012188434030000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009042521604759584125E-94");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"7.957419012188434030000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009042521604759584125E-94",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal002() {
        String number = "7.95741901218843403E+405";
        String number2 = "9.042521604759584125E-793";
        if (log) { System.out.println("testAddBigDecimal002"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 7957419012188434030000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009042521604759584125");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"7957419012188434030000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009042521604759584125",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal003() {
        String number = "7.95741901218843403E-170";
        String number2 = "-9.042521604759584125E+22";
        if (log) { System.out.println("testAddBigDecimal003"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -90425216047595841249999.9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999204258098781156597");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"-90425216047595841249999.9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999204258098781156597",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal004() {
        String number = "7.95741901218843403E+992";
        String number2 = "9.042521604759584125E-494";
        if (log) { System.out.println("testAddBigDecimal004"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 795741901218843403000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009042521604759584125");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"795741901218843403000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009042521604759584125",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal005() {
        String number = "7.95741901218843403E+846";
        String number2 = "-9.042521604759584125E-475";
        if (log) { System.out.println("testAddBigDecimal005"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 7957419012188434029999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999990957478395240415875");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"7957419012188434029999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999990957478395240415875",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal006() {
        String number = "7.95741901218843403E-795";
        String number2 = "9.042521604759584125E-328";
        if (log) { System.out.println("testAddBigDecimal006"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 9.0425216047595841250000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000795741901218843403E-328");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"9.0425216047595841250000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000795741901218843403E-328",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal007() {
        String number = "-7.95741901218843403E+342";
        String number2 = "-9.042521604759584125E+37";
        if (log) { System.out.println("testAddBigDecimal007"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -7.95741901218843403000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009042521604759584125E+342");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"-7.95741901218843403000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009042521604759584125E+342",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal008() {
        String number = "-7.95741901218843403E+951";
        String number2 = "-9.042521604759584125E-943";
        if (log) { System.out.println("testAddBigDecimal008"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -7957419012188434030000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009042521604759584125");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"-7957419012188434030000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009042521604759584125",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal009() {
        String number = "-7.95741901218843403E+382";
        String number2 = "-9.042521604759584125E+709";
        if (log) { System.out.println("testAddBigDecimal009"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -9.04252160475958412500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000795741901218843403E+709");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"-9.04252160475958412500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000795741901218843403E+709",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal010() {
        String number = "-7.95741901218843403E-792";
        String number2 = "-9.042521604759584125E+600";
        if (log) { System.out.println("testAddBigDecimal010"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -9042521604759584125000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000795741901218843403");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"-9042521604759584125000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000795741901218843403",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal011() {
        String number = "-7.95741901218843403E-416";
        String number2 = "-9.042521604759584125E-718";
        if (log) { System.out.println("testAddBigDecimal011"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -7.95741901218843403000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009042521604759584125E-416");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"-7.95741901218843403000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009042521604759584125E-416",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal012() {
        String number = "7.95741901218843403E+476";
        String number2 = "9.042521604759584125E+644";
        if (log) { System.out.println("testAddBigDecimal012"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 9.04252160475958412500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000795741901218843403E+644");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"9.04252160475958412500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000795741901218843403E+644",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal013() {
        String number = "7.95741901218867818E+933";
        String number2 = "-9.042521604759584125E-266";
        if (log) { System.out.println("testAddBigDecimal013"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 7957419012188678179999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999990957478395240415875");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"7957419012188678179999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999990957478395240415875",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal014() {
        String number = "7.668058320836127338E-98";
        String number2 = "9.042521604759584125E-429";
        if (log) { System.out.println("testAddBigDecimal014"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 7.6680583208361273380000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009042521604759584125E-98");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"7.6680583208361273380000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009042521604759584125E-98",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal015() {
        String number = "7.668058320836127338E-354";
        String number2 = "9.042521604759584125E+184";
        if (log) { System.out.println("testAddBigDecimal015"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 90425216047595841250000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000007668058320836127338");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"90425216047595841250000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000007668058320836127338",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal016() {
        String number = "7.668058320836127338E-482";
        String number2 = "9.042521604759584125E+845";
        if (log) { System.out.println("testAddBigDecimal016"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 904252160475958412500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000007668058320836127338");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"904252160475958412500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000007668058320836127338",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal017() {
        String number = "-7.668058320836127338E-787";
        String number2 = "-9.042521604759584068E+166";
        if (log) { System.out.println("testAddBigDecimal017"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -90425216047595840680000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000007668058320836127338");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"-90425216047595840680000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000007668058320836127338",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal018() {
        String number = "7.668058320836127338E-424";
        String number2 = "4.919131752989213764E+848";
        if (log) { System.out.println("testAddBigDecimal018"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 491913175298921376400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000007668058320836127338");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"491913175298921376400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000007668058320836127338",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal019() {
        String number = "-7.668058320836127338E+568";
        String number2 = "4.919131752989213764E-342";
        if (log) { System.out.println("testAddBigDecimal019"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -76680583208361273379999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999995080868247010786236");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"-76680583208361273379999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999995080868247010786236",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal020() {
        String number = "-7.668058320836127338E-572";
        String number2 = "4.919131752989213764E+252";
        if (log) { System.out.println("testAddBigDecimal020"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 4919131752989213763999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999992331941679163872662");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"4919131752989213763999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999992331941679163872662",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal021() {
        String number = "-7.668058320836127338E+97";
        String number2 = "-4.919131752989213764E+638";
        if (log) { System.out.println("testAddBigDecimal021"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -4.9191317529892137640000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000007668058320836127338E+638");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"-4.9191317529892137640000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000007668058320836127338E+638",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal022() {
        String number = "7.668058320836127338E-393";
        String number2 = "4.919131752989213764E-17";
        if (log) { System.out.println("testAddBigDecimal022"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 4.9191317529892137640000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000007668058320836127338E-17");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"4.9191317529892137640000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000007668058320836127338E-17",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal023() {
        String number = "-7.668058320836127338E+71";
        String number2 = "-4.919131752989213764E+521";
        if (log) { System.out.println("testAddBigDecimal023"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -4.919131752989213764000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000007668058320836127338E+521");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"-4.919131752989213764000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000007668058320836127338E+521",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal024() {
        String number = "-7.668058320836127338E+336";
        String number2 = "4.919131752989213764E+187";
        if (log) { System.out.println("testAddBigDecimal024"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -7.66805832083612733799999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999995080868247010786236E+336");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"-7.66805832083612733799999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999995080868247010786236E+336",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal025() {
        String number = "7.668058320836127338E+875";
        String number2 = "4.919131752989213764E-262";
        if (log) { System.out.println("testAddBigDecimal025"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 766805832083612733800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000004919131752989213764");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"766805832083612733800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000004919131752989213764",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal026() {
        String number = "7.668058320836127338E+901";
        String number2 = "4.919131752989213764E+335";
        if (log) { System.out.println("testAddBigDecimal026"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 7.66805832083612733800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000004919131752989213764E+901");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"7.66805832083612733800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000004919131752989213764E+901",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal027() {
        String number = "7.668058320836127338E-189";
        String number2 = "4.919131752989213764E+715";
        if (log) { System.out.println("testAddBigDecimal027"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 49191317529892137640000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000007668058320836127338");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"49191317529892137640000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000007668058320836127338",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal028() {
        String number = "-7.668058320836127338E+927";
        String number2 = "4.919131752989213764E-195";
        if (log) { System.out.println("testAddBigDecimal028"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -7668058320836127337999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999995080868247010786236");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"-7668058320836127337999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999995080868247010786236",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal029() {
        String number = "7.668058320836127338E-717";
        String number2 = "-4.919131752989213764E-204";
        if (log) { System.out.println("testAddBigDecimal029"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -4.919131752989213763999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999992331941679163872662E-204");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"-4.919131752989213763999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999992331941679163872662E-204",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimal030() {
        String number = "-7.668058320836127338E-54";
        String number2 = "-4.919131752989213764E+584";
        if (log) { System.out.println("testAddBigDecimal030"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -491913175298921376400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000007668058320836127338");
            System.out.println("-> actual result: "+ bigDec.add(bd).toString()); 
        }
        assertEquals(msgNotSame,"-491913175298921376400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000007668058320836127338",bigDec.add(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    
    
    /*
     * Test method for 'java.math.BigDecimal.add(BigDecimal, MathContext)'
     */
    
    boolean logtestAddBigDecimalMathContext = false;
    
    public final void testAddBigDecimalMathContext001() {
        String number = "-7.95741901218843403E+392";
        String number2 = "1.4395694394777257927E-810";
        MathContext mc = new MathContext("precision=0 roundingMode=DOWN");        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext001"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: -795741901218843402999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999985604305605222742073");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-795741901218843402999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999985604305605222742073",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext002() {
        String number = "7.95741901218843403E-188";
        String number2 = "1.4395694394777257927E-583";
        MathContext mc = new MathContext("precision=0 roundingMode=HALF_DOWN");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext002"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: 79.57419012188434030000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000014395694394777257927E-189");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"79.57419012188434030000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000014395694394777257927E-189",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext003() {
        String number = "-7.95741901218843403E+744";
        String number2 = "1.4395694394777257927E+469";
        MathContext mc = new MathContext("precision=0 roundingMode=UP");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext003"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: -7.957419012188434029999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999985604305605222742073E+744");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-7.957419012188434029999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999985604305605222742073E+744",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext004() {
        String number = "-7.95741901218843403E+345";
        String number2 = "-1.4395694394777257927E+55";
        MathContext mc = new MathContext("precision=0 roundingMode=FLOOR");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext004"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: -7.957419012188434030000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000014395694394777257927E+345");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-7.957419012188434030000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000014395694394777257927E+345",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext005() {
        String number = "-7.95741901218843403E+448";
        String number2 = "1.4395694394777257927E-307";
        MathContext mc = new MathContext("precision=0 roundingMode=HALF_DOWN");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext005"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: -79574190121884340299999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999985604305605222742073");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-79574190121884340299999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999985604305605222742073",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext006() {
        String number = "-7.95741901218843403E-870";
        String number2 = "1.4395694394777257927E+953";
        MathContext mc = new MathContext("precision=0 roundingMode=HALF_UP");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext006"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: 143956943947772579269999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999204258098781156597");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"143956943947772579269999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999204258098781156597",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext007() {
        String number = "7.95741901218843403E+31";
        String number2 = "1.4395694394777257927E-329";
        MathContext mc = new MathContext("precision=0 roundingMode=UP");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext007"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: 79574190121884340300000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000014395694394777257927");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"79574190121884340300000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000014395694394777257927",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext008() {
        String number = "-7.95741901218843403E+527";
        String number2 = "-1.4395694394777257927E+295";
        MathContext mc = new MathContext("precision=0 roundingMode=HALF_UP");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext008"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: -795.741901218843403000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000014395694394777257927E+525");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-795.741901218843403000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000014395694394777257927E+525",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext009() {
        String number = "-7.95741901218843403E+669";
        String number2 = "-1.4395694394777257927E-488";
        MathContext mc = new MathContext("precision=0 roundingMode=HALF_EVEN");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext009"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: -7957419012188434030000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000014395694394777257927");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-7957419012188434030000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000014395694394777257927",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext010() {
        String number = "-7.95741901218886836E-160";
        String number2 = "1.4395694394777257927E+335";
        MathContext mc = new MathContext("precision=0 roundingMode=UP");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext010"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: 143956943947772579269999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999204258098781113164");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"143956943947772579269999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999204258098781113164",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext011() {
        String number = "1.3021231110853801140E+633";
        String number2 = "1.4395694394777257927E-810";
        MathContext mc = new MathContext("precision=0 roundingMode=CEILING");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext011"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: 1302123111085380114000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000014395694394777257927");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"1302123111085380114000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000014395694394777257927",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext012() {
        String number = "-1.3021231110853801140E+441";
        String number2 = "1.4395694394777257927E+896";
        MathContext mc = new MathContext("precision=0 roundingMode=HALF_EVEN");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext012"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: 143.9569439477725792699999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999986978768889146198860E+894");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"143.9569439477725792699999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999986978768889146198860E+894",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext013() {
        String number = "1.3021231110853801140E-256";
        String number2 = "1.4395694394777257927E-155";
        MathContext mc = new MathContext("precision=0 roundingMode=UP");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext013"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: 14.39569439477725792700000000000000000000000000000000000000000000000000000000000000000000000000000000013021231110853801140E-156");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"14.39569439477725792700000000000000000000000000000000000000000000000000000000000000000000000000000000013021231110853801140E-156",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext014() {
        String number = "1.3021231110853801140E+183";
        String number2 = "-1.0272304543006887566E-772";
        MathContext mc = new MathContext("precision=0 roundingMode=HALF_DOWN");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext014"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: 1302123111085380113999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999989727695456993112434");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"1302123111085380113999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999989727695456993112434",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext015() {
        String number = "-1.3021231110853801140E-388";
        String number2 = "1.0272304543006887566E-471";
        MathContext mc = new MathContext("precision=0 roundingMode=CEILING");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext015"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: -130.2123111085380113999999999999999999999999999999999999999999999999999999999999999989727695456993112434E-390");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-130.2123111085380113999999999999999999999999999999999999999999999999999999999999999989727695456993112434E-390",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext016() {
        String number = "-1.3021231110853801140E+559";
        String number2 = "-1.0272304543006887566E-167";
        MathContext mc = new MathContext("precision=0 roundingMode=HALF_DOWN");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext016"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: -13021231110853801140000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010272304543006887566");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-13021231110853801140000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010272304543006887566",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext017() {
        String number = "-1.3021231110853801140E+818";
        String number2 = "-1.0272304543006887566E-918";
        MathContext mc = new MathContext("precision=0 roundingMode=UP");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext017"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: -130212311108538011400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010272304543006887566");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-130212311108538011400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010272304543006887566",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext018() {
        String number = "-1.3021231110853801140E+271";
        String number2 = "1.0272304543006887566E-683";
        MathContext mc = new MathContext("precision=0 roundingMode=FLOOR");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext018"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: -13021231110853801139999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999989727695456993112434");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-13021231110853801139999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999989727695456993112434",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext019() {
        String number = "-1.3021231110853801140E-120";
        String number2 = "-1.0272304543006887566E-373";
        MathContext mc = new MathContext("precision=0 roundingMode=FLOOR");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext019"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: -1.30212311108538011400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010272304543006887566E-120");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-1.30212311108538011400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010272304543006887566E-120",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext020() {
        String number = "-1.3021231110853801140E+449";
        String number2 = "1.0272304543006887566E-498";
        MathContext mc = new MathContext("precision=0 roundingMode=UP");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext020"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: -130212311108538011399999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999989727695456993112434");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-130212311108538011399999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999989727695456993112434",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext021() {
        String number = "-1.3021231110853801140E-260";
        String number2 = "1.0272304543006887566E-375";
        MathContext mc = new MathContext("precision=0 roundingMode=HALF_UP");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext021"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: -13.0212311108538011399999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999989727695456993112434E-261");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-13.0212311108538011399999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999989727695456993112434E-261",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext022() {
        String number = "1.3021231110853801140E-117";
        String number2 = "1.0272304543006887566E-914";
        MathContext mc = new MathContext("precision=0 roundingMode=FLOOR");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext022"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: 1.302123111085380114000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010272304543006887566E-117");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"1.302123111085380114000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010272304543006887566E-117",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext023() {
        String number = "-1.3021231110853801140E+205";
        String number2 = "1.0272304543006887566E-69";
        MathContext mc = new MathContext("precision=0 roundingMode=DOWN");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext023"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: -13021231110853801139999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.9999999999999999999999999999999999999999999999999999999999999999999989727695456993112434");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-13021231110853801139999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.9999999999999999999999999999999999999999999999999999999999999999999989727695456993112434",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext024() {
        String number = "-1.3021231110853801140E-804";
        String number2 = "1.0272304543006887566E-253";
        MathContext mc = new MathContext("precision=0 roundingMode=CEILING");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext024"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: 102.7230454300688756599999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999986978768889146198860E-255");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"102.7230454300688756599999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999986978768889146198860E-255",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext025() {
        String number = "-1.3021231110853801140E-628";
        String number2 = "-1.0272304543006887566E+592";
        MathContext mc = new MathContext("precision=0 roundingMode=CEILING");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext025"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestAddBigDecimalMathContext) { 
            System.out.println("-> expected result: -10272304543006887566000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013021231110853801140");
            System.out.println("-> actual result: "+ bigDec.add(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-10272304543006887566000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013021231110853801140",bigDec.add(bd,mc).toEngineeringString());
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testAddBigDecimalMathContext026() {
        ArithmeticException exp = new ArithmeticException();
        String number = "-1.3021231110853801140E-817";
        String number2 = "-1.0272304543006887566E+251";
        MathContext mc = new MathContext("precision=8 roundingMode=UNNECESSARY");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext026"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        try {
            bigDec.add(bd,mc).toEngineeringString();
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
            if (logtestAddBigDecimalMathContext) { 
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test FAILED---"); }
    }
    public final void testAddBigDecimalMathContext027() {
        ArithmeticException exp = new ArithmeticException();
        String number = "1.4395694394777257927E-162";
        String number2 = "-1.0272304543006887566E-652";
        MathContext mc = new MathContext("precision=7 roundingMode=UNNECESSARY");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext027"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        try {
            bigDec.add(bd,mc).toEngineeringString();
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
            if (logtestAddBigDecimalMathContext) { 
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test FAILED---"); }
    }
    public final void testAddBigDecimalMathContext028() {
        ArithmeticException exp = new ArithmeticException();
        String number = "-1.4395694394777257927E+421";
        String number2 = "-1.0272304543006887566E+781";
        MathContext mc = new MathContext("precision=9 roundingMode=UNNECESSARY");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext028"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        try {
            bigDec.add(bd,mc).toEngineeringString();
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
            if (logtestAddBigDecimalMathContext) { 
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test FAILED---"); }
    }
    public final void testAddBigDecimalMathContext029() {
        ArithmeticException exp = new ArithmeticException();
        String number = "1.4395694394777257927E+150";
        String number2 = "1.0272304543006887566E-227";
        MathContext mc = new MathContext("precision=4 roundingMode=UNNECESSARY");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext029"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        try {
            bigDec.add(bd,mc).toEngineeringString();
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
            if (logtestAddBigDecimalMathContext) { 
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test FAILED---"); }
    }
    public final void testAddBigDecimalMathContext030() {
        ArithmeticException exp = new ArithmeticException();
        String number = "1.4395694394777257927E+50";
        String number2 = "-1.0272304543326904737E+877";
        MathContext mc = new MathContext("precision=7 roundingMode=UNNECESSARY");
        
        if (logtestAddBigDecimalMathContext) { System.out.println("testAddBigDecimalMathContext030"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        try {
            bigDec.add(bd,mc).toEngineeringString();
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
            if (logtestAddBigDecimalMathContext) { 
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (logtestAddBigDecimalMathContext) { System.out.println("---Test FAILED---"); }
    }
    /*
     * Test method for 'java.math.BigDecimal.subtract(BigDecimal)'
     */
    public final void testSubtractBigDecimal001() {
        String number = "1.2804210592339571121E+574";
        String number2 = "1.8302063728033398269E+823";
        if (log) { System.out.println("testSubtractBigDecimal001"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -1.8302063728033398268999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999987195789407660428879E+823");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"-1.8302063728033398268999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999987195789407660428879E+823",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal002() {
        String number = "1.2804210592339571121E-836";
        String number2 = "1.8302063728033398269E+580";
        if (log) { System.out.println("testSubtractBigDecimal002"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -18302063728033398268999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999987195789407660428879");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"-18302063728033398268999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999987195789407660428879",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal003() {
        String number = "-1.2804210592339571121E-179";
        String number2 = "-1.8302063728033398269E-703";
        if (log) { System.out.println("testSubtractBigDecimal003"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -1.280421059233957112099999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999981697936271966601731E-179");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"-1.280421059233957112099999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999981697936271966601731E-179",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal004() {
        String number = "-1.2804210592339571121E-443";
        String number2 = "-1.8302063728033398269E-345";
        if (log) { System.out.println("testSubtractBigDecimal004"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 1.830206372803339826899999999999999999999999999999999999999999999999999999999999999999999999999999987195789407660428879E-345");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"1.830206372803339826899999999999999999999999999999999999999999999999999999999999999999999999999999987195789407660428879E-345",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal005() {
        String number = "1.2804210592339571121E+31";
        String number2 = "1.8302063728033398269E+133";
        if (log) { System.out.println("testSubtractBigDecimal005"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -1.8302063728033398268999999999999999999999999999999999999999999999999999999999999999999999999999999999987195789407660428879E+133");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"-1.8302063728033398268999999999999999999999999999999999999999999999999999999999999999999999999999999999987195789407660428879E+133",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal006() {
        String number = "-1.2804210592339571140E-937";
        String number2 = "-1.8302063728033398269E-911";
        if (log) { System.out.println("testSubtractBigDecimal006"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 1.830206372803339826899999987195789407660428860E-911");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"1.830206372803339826899999987195789407660428860E-911",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal007() {
        String number = "1.4178673876263027908E+699";
        String number2 = "1.8302063728033398269E-832";
        if (log) { System.out.println("testSubtractBigDecimal007"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 1417867387626302790799999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999981697936271966601731");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"1417867387626302790799999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999981697936271966601731",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal008() {
        String number = "-1.4178673876263027908E-551";
        String number2 = "-1.8302063728033398269E+996";
        if (log) { System.out.println("testSubtractBigDecimal008"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 1830206372803339826899999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999985821326123736972092");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"1830206372803339826899999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999985821326123736972092",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal009() {
        String number = "-1.4178673876263027908E+335";
        String number2 = "1.8302063728033398269E-466";
        if (log) { System.out.println("testSubtractBigDecimal009"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -141786738762630279080000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000018302063728033398269");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"-141786738762630279080000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000018302063728033398269",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal010() {
        String number = "1.4178673876263027908E-926";
        String number2 = "1.8302062710446166289E-923";
        if (log) { System.out.println("testSubtractBigDecimal010"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -1.8287884036569903261092E-923");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"-1.8287884036569903261092E-923",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal011() {
        String number = "-1.4178673186373379108E+977";
        String number2 = "-1.229782938247303441E-977";
        if (log) { System.out.println("testSubtractBigDecimal011"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -141786731863733791079999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999998770217061752696559");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"-141786731863733791079999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999998770217061752696559",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal012() {
        String number = "-2.604246222170760228E+441";
        String number2 = "1.229782938247303441E+601";
        if (log) { System.out.println("testSubtractBigDecimal012"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -1.2297829382473034410000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000002604246222170760228E+601");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"-1.2297829382473034410000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000002604246222170760228E+601",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal013() {
        String number = "2.604246222170760228E+683";
        String number2 = "-1.229782938247303441E+165";
        if (log) { System.out.println("testSubtractBigDecimal013"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 2.60424622217076022800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001229782938247303441E+683");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"2.60424622217076022800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001229782938247303441E+683",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal014() {
        String number = "-2.604246222170760228E-102";
        String number2 = "1.229782938247303441E+72";
        if (log) { System.out.println("testSubtractBigDecimal014"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -1229782938247303441000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000002604246222170760228");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"-1229782938247303441000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000002604246222170760228",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal015() {
        String number = "-2.604246222170760228E+853";
        String number2 = "1.229782938247303441E-678";
        if (log) { System.out.println("testSubtractBigDecimal015"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -26042462221707602280000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001229782938247303441");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"-26042462221707602280000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001229782938247303441",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal016() {
        String number = "-2.604246222170760228E-840";
        String number2 = "1.229782938247303441E+616";
        if (log) { System.out.println("testSubtractBigDecimal016"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -12297829382473034410000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000002604246222170760228");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"-12297829382473034410000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000002604246222170760228",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal017() {
        String number = "-2.604246222170760228E-376";
        String number2 = "-1.229782938247303441E-309";
        if (log) { System.out.println("testSubtractBigDecimal017"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 1.2297829382473034409999999999999999999999999999999999999999999999997395753777829239772E-309");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"1.2297829382473034409999999999999999999999999999999999999999999999997395753777829239772E-309",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal018() {
        String number = "2.604246222170760228E-763";
        String number2 = "-1.229782938247303441E-918";
        if (log) { System.out.println("testSubtractBigDecimal018"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 2.60424622217076022800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001229782938247303441E-763");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"2.60424622217076022800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001229782938247303441E-763",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal019() {
        String number = "2.604246222170760228E-481";
        String number2 = "-1.229782938247303441E+364";
        if (log) { System.out.println("testSubtractBigDecimal019"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 12297829382473034410000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000002604246222170760228");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"12297829382473034410000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000002604246222170760228",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal020() {
        String number = "2.604246222170760228E+710";
        String number2 = "-1.229782938247303441E+31";
        if (log) { System.out.println("testSubtractBigDecimal020"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 2.6042462221707602280000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001229782938247303441E+710");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"2.6042462221707602280000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001229782938247303441E+710",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal021() {
        String number = "-2.604246222170760228E-767";
        String number2 = "-1.229782938247303441E+771";
        if (log) { System.out.println("testSubtractBigDecimal021"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 1229782938247303440999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999997395753777829239772");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"1229782938247303440999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999997395753777829239772",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal022() {
        String number = "-3.978709506094217015E-545";
        String number2 = "-1.229782938247303441E+985";
        if (log) { System.out.println("testSubtractBigDecimal022"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 12297829382473034409999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999996021290493905782985");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"12297829382473034409999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999996021290493905782985",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal023() {
        String number = "-3.978709509429132797E-572";
        String number2 = "1.229782938247303441E-386";
        if (log) { System.out.println("testSubtractBigDecimal023"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -1.229782938247303441000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000003978709509429132797E-386");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"-1.229782938247303441000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000003978709509429132797E-386",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal024() {
        String number = "1.8302063728033398269E-196";
        String number2 = "1.229782938247303441E+726";
        if (log) { System.out.println("testSubtractBigDecimal024"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -1229782938247303440999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999981697936271966601731");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"-1229782938247303440999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999981697936271966601731",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal025() {
        String number = "-0.000018302063728033398269";
        String number2 = "1.229782938247303441E-923";
        if (log) { System.out.println("testSubtractBigDecimal025"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -0.00001830206372803339826900000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001229782938247303441");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"-0.00001830206372803339826900000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001229782938247303441",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal026() {
        String number = "1.8302063728033398269E-804";
        String number2 = "1.229782938247303441E+807";
        if (log) { System.out.println("testSubtractBigDecimal026"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -1229782938247303440999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999981697936271966601731");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"-1229782938247303440999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999981697936271966601731",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal027() {
        String number = "1.8302063728033398269E+304";
        String number2 = "8.102099357864587376E-882";
        if (log) { System.out.println("testSubtractBigDecimal027"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 18302063728033398268999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999991897900642135412624");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"18302063728033398268999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999991897900642135412624",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal028() {
        String number = "-1.8302063728033398269E-128";
        String number2 = "8.102099357864587376E-333";
        if (log) { System.out.println("testSubtractBigDecimal028"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -1.8302063728033398269000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000008102099357864587376E-128");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"-1.8302063728033398269000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000008102099357864587376E-128",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal029() {
        String number = "-1.8302063728033398269E+225";
        String number2 = "-8.102099357864587376E+529";
        if (log) { System.out.println("testSubtractBigDecimal029"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 8.10209935786458737599999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999981697936271966601731E+529");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"8.10209935786458737599999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999981697936271966601731E+529",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testSubtractBigDecimal030() {
        String number = "1.8302063728033398269E-162";
        String number2 = "8.102099357864587376E-644";
        if (log) { System.out.println("testSubtractBigDecimal030"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 1.83020637280333982689999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999991897900642135412624E-162");
            System.out.println("-> actual result: "+ bigDec.subtract(bd).toString()); 
        }
        assertEquals(msgNotSame,"1.83020637280333982689999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999991897900642135412624E-162",bigDec.subtract(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    
    
    /*
     * Test method for 'java.math.BigDecimal.multiply(BigDecimal)'
     */
    public final void testMultiplyBigDecimal001() {
        String number = "-4.195730024608447034E+132";
        String number2 = "7.451037802321897319E-390";
        if (log) { System.out.println("testMultiplyBigDecimal001"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -3.1262543021694523345018042392998101846E-257");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"-3.1262543021694523345018042392998101846E-257",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal002() {
        String number = "4.195730024608447034E-672";
        String number2 = "7.451037802321897319E+483";
        if (log) { System.out.println("testMultiplyBigDecimal002"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 3.1262543021694523345018042392998101846E-188");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"3.1262543021694523345018042392998101846E-188",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal003() {
        String number = "4.195730024608447034E-766";
        String number2 = "-7.451037802321897319E-940";
        if (log) { System.out.println("testMultiplyBigDecimal003"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -3.1262543021694523345018042392998101846E-1705");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"-3.1262543021694523345018042392998101846E-1705",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal004() {
        String number = "4.195730024608447034E+796";
        String number2 = "-7.451037802321897319E-475";
        if (log) { System.out.println("testMultiplyBigDecimal004"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -3.1262543021694523345018042392998101846E+322");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"-3.1262543021694523345018042392998101846E+322",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal005() {
        String number = "-4.248572260236260853E+240";
        String number2 = "7.451037802321897319E-668";
        if (log) { System.out.println("testMultiplyBigDecimal005"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -3.1656272516916565086983587781165353107E-427");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"-3.1656272516916565086983587781165353107E-427",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal006() {
        String number = "1.7723341651127325780E-871";
        String number2 = "7.451037802321897319E-406";
        if (log) { System.out.println("testMultiplyBigDecimal006"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 1.32057288626015896463168055056221583820E-1276");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"1.32057288626015896463168055056221583820E-1276",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal007() {
        String number = "6.076574518398440532E-66";
        String number2 = "7.451037802321897319E-400";
        if (log) { System.out.println("testMultiplyBigDecimal007"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 4.5276786445212757947957164845031733708E-465");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"4.5276786445212757947957164845031733708E-465",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal008() {
        String number = "-6.076574518398440532E-796";
        String number2 = "-7.434930810713419310E+269";
        if (log) { System.out.println("testMultiplyBigDecimal008"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 4.5178911110436622967398988669415472920E-526");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"4.5178911110436622967398988669415472920E-526",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal009() {
        String number = "6.076574518398440532E+649";
        String number2 = "-3.327647950551526958E+793";
        if (log) { System.out.println("testMultiplyBigDecimal009"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -2.0220700742522202578699316338557861656E+1443");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"-2.0220700742522202578699316338557861656E+1443",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal010() {
        String number = "-6.076574518398440532E-362";
        String number2 = "-3.327647950551526958E-500";
        if (log) { System.out.println("testMultiplyBigDecimal010"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 2.0220700742522202578699316338557861656E-861");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"2.0220700742522202578699316338557861656E-861",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal011() {
        String number = "-6.076574518398440532E+337";
        String number2 = "3.327647950551526958E-622";
        if (log) { System.out.println("testMultiplyBigDecimal011"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -2.0220700742522202578699316338557861656E-284");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"-2.0220700742522202578699316338557861656E-284",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal012() {
        String number = "-6.076574518398440532E-69";
        String number2 = "3.327647950551526958E+897";
        if (log) { System.out.println("testMultiplyBigDecimal012"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -2.0220700742522202578699316338557861656E+829");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"-2.0220700742522202578699316338557861656E+829",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal013() {
        String number = "6.076574518398440532E+1011";
        String number2 = "-3.327647950551526958E+763";
        if (log) { System.out.println("testMultiplyBigDecimal013"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -2.0220700742522202578699316338557861656E+1775");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"-2.0220700742522202578699316338557861656E+1775",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal014() {
        String number = "6.076574518398440532E+81";
        String number2 = "3.327647950551526958E+654";
        if (log) { System.out.println("testMultiplyBigDecimal014"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 2.0220700742522202578699316338557861656E+736");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"2.0220700742522202578699316338557861656E+736",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal015() {
        String number = "-6.076574518398440532E+829";
        String number2 = "3.327647950551526958E-946";
        if (log) { System.out.println("testMultiplyBigDecimal015"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -2.0220700742522202578699316338557861656E-116");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"-2.0220700742522202578699316338557861656E-116",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal016() {
        String number = "6.076574518398440532E+94";
        String number2 = "3.327647950551526958E+139";
        if (log) { System.out.println("testMultiplyBigDecimal016"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 2.0220700742522202578699316338557861656E+234");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"2.0220700742522202578699316338557861656E+234",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal017() {
        String number = "6.076574518398440532E-922";
        String number2 = "-3.327647950551526958E-621";
        if (log) { System.out.println("testMultiplyBigDecimal017"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -2.0220700742522202578699316338557861656E-1542");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"-2.0220700742522202578699316338557861656E-1542",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal018() {
        String number = "6.076574518398440532E-538";
        String number2 = "-3.327647950551526958E-816";
        if (log) { System.out.println("testMultiplyBigDecimal018"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -2.0220700742522202578699316338557861656E-1353");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"-2.0220700742522202578699316338557861656E-1353",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal019() {
        String number = "6.076574518398440532E+349";
        String number2 = "-0.00003327647950551526958";
        if (log) { System.out.println("testMultiplyBigDecimal019"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -2.0220700742522202578699316338557861656E+345");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"-2.0220700742522202578699316338557861656E+345",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal020() {
        String number = "-6.076574518398440532E-837";
        String number2 = "-3.327647950551526958E-483";
        if (log) { System.out.println("testMultiplyBigDecimal020"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 2.0220700742522202578699316338557861656E-1319");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"2.0220700742522202578699316338557861656E-1319",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal021() {
        String number = "6.076574518398440532E+242";
        String number2 = "-3.327647950551526958E+518";
        if (log) { System.out.println("testMultiplyBigDecimal021"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -2.0220700742522202578699316338557861656E+761");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"-2.0220700742522202578699316338557861656E+761",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal022() {
        String number = "7.451037802321897319E+131";
        String number2 = "3.327647950551526958E+695";
        if (log) { System.out.println("testMultiplyBigDecimal022"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 2.4794430672378415066738447415136425602E+827");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"2.4794430672378415066738447415136425602E+827",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal023() {
        String number = "7.451037802321897319E-674";
        String number2 = "-3.327647950552777025E-884";
        if (log) { System.out.println("testMultiplyBigDecimal023"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -2.4794430672387729363210882542352295975E-1557");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"-2.4794430672387729363210882542352295975E-1557",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal024() {
        String number = "-7.451037802321897319E-152";
        String number2 = "-4.702111234474983745E-932";
        if (log) { System.out.println("testMultiplyBigDecimal024"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 3.5035608558795586507347806130084079655E-1083");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"3.5035608558795586507347806130084079655E-1083",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal025() {
        String number = "-7.451037802321897319E-180";
        String number2 = "4.702111234474983745E+275";
        if (log) { System.out.println("testMultiplyBigDecimal025"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -3.5035608558795586507347806130084079655E+96");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"-3.5035608558795586507347806130084079655E+96",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal026() {
        String number = "7.451037802321897319E-833";
        String number2 = "4.702111234474983745E+857";
        if (log) { System.out.println("testMultiplyBigDecimal026"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 35035608558795586507347806.130084079655");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"35035608558795586507347806.130084079655",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal027() {
        String number = "7.451037802321897319E+265";
        String number2 = "-4.702111234474983745E-815";
        if (log) { System.out.println("testMultiplyBigDecimal027"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -3.5035608558795586507347806130084079655E-549");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"-3.5035608558795586507347806130084079655E-549",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal028() {
        String number = "-7.451037802321897319E+327";
        String number2 = "-4.702111234474983745E-890";
        if (log) { System.out.println("testMultiplyBigDecimal028"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 3.5035608558795586507347806130084079655E-562");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"3.5035608558795586507347806130084079655E-562",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal029() {
        String number = "-7.451037802321897319E+478";
        String number2 = "4.702111234474983745E+847";
        if (log) { System.out.println("testMultiplyBigDecimal029"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -3.5035608558795586507347806130084079655E+1326");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"-3.5035608558795586507347806130084079655E+1326",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimal030() {
        String number = "7.451037802321897319E+20";
        String number2 = "-4.702111234474983745E-772";
        if (log) { System.out.println("testMultiplyBigDecimal030"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -3.5035608558795586507347806130084079655E-751");
            System.out.println("-> actual result: "+ bigDec.multiply(bd).toString()); 
        }
        assertEquals(msgNotSame,"-3.5035608558795586507347806130084079655E-751",bigDec.multiply(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    
    /*
     * Test method for 'java.math.BigDecimal.multiply(BigDecimal, MathContext)'
     */
    
    boolean logtestMultiplyBigDecimalMathContext = false;
    
    public final void testMultiplyBigDecimalMathContext001() {
        String number = "2.821266740684990247E-474";
        String number2 = "-1.1140386617063807642E+490";
        MathContext mc = new MathContext("precision=0 roundingMode=HALF_UP");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext001"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: -31430002241094293.138630459091454067574");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-31430002241094293.138630459091454067574",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext002() {
        String number = "-2.821266740684990247E-727";
        String number2 = "-1.1140386617063807642E-63";
        MathContext mc = new MathContext("precision=0 roundingMode=CEILING");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext002"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: 314.30002241094293138630459091454067574E-792");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"314.30002241094293138630459091454067574E-792",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext003() {
        String number = "-2.821266740684990247E+633";
        String number2 = "-1.1140386617063807642E+353";
        MathContext mc = new MathContext("precision=0 roundingMode=HALF_UP");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext003"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: 314.30002241094293138630459091454067574E+984");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"314.30002241094293138630459091454067574E+984",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext004() {
        String number = "2.821266740684990247E-442";
        String number2 = "-1.1140386617063807642E-74";
        MathContext mc = new MathContext("precision=0 roundingMode=HALF_UP");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext004"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: -3.1430002241094293138630459091454067574E-516");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-3.1430002241094293138630459091454067574E-516",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    
    public final void testMultiplyBigDecimalMathContext005() {
        String number = "2.821266740684990247E-161";
        String number2 = "1.1140386617063807642E+452";
        MathContext mc = new MathContext("precision=0 roundingMode=CEILING");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext005"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: 3.1430002241094293138630459091454067574E+291");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"3.1430002241094293138630459091454067574E+291",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext006() {
        String number = "4.195730024608447034E+563";
        String number2 = "-1.1140386617063807642E-663";
        MathContext mc = new MathContext("precision=0 roundingMode=UP");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext006"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: -467.42054614960743642065810956521433828E-102");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-467.42054614960743642065810956521433828E-102",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext007() {
        String number = "4.195730024608447034E+112";
        String number2 = "1.2514849900987264429E-485";
        MathContext mc = new MathContext("precision=0 roundingMode=HALF_UP");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext007"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: 525.08931483040315909593411009598753586E-375");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"525.08931483040315909593411009598753586E-375",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext008() {
        String number = "-4.195730024608447034E+382";
        String number2 = "-1.2514849900987264429E+845";
        MathContext mc = new MathContext("precision=0 roundingMode=UP");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext008"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: 5.2508931483040315909593411009598753586E+1227");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"5.2508931483040315909593411009598753586E+1227",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext009() {
        String number = "-4.195730024608447034E-555";
        String number2 = "-1.2514849900987264429E+395";
        MathContext mc = new MathContext("precision=0 roundingMode=HALF_UP");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext009"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: 525.08931483040315909593411009598753586E-162");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"525.08931483040315909593411009598753586E-162",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext010() {
        String number = "-4.195730024608447034E+374";
        String number2 = "1.2514849900987264429E-765";
        MathContext mc = new MathContext("precision=0 roundingMode=HALF_EVEN");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext010"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: -525.08931483040315909593411009598753586E-393");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-525.08931483040315909593411009598753586E-393",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext011() {
        String number = "-4.195730024608447034E-817";
        String number2 = "1.2514849900987264429E+616";
        MathContext mc = new MathContext("precision=0 roundingMode=HALF_EVEN");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext011"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: -5.2508931483040315909593411009598753586E-201");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-5.2508931483040315909593411009598753586E-201",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext012() {
        String number = "4.195730024608447034E-27";
        String number2 = "1.2514849900987264429E+527";
        MathContext mc = new MathContext("precision=0 roundingMode=HALF_UP");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext012"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: 525.08931483040315909593411009598753586E+498");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"525.08931483040315909593411009598753586E+498",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext013() {
        String number = "-4.195730024608447034E+995";
        String number2 = "-1.2514849900987264429E+546";
        MathContext mc = new MathContext("precision=0 roundingMode=UP");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext013"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: 525.08931483040315909593411009598753586E+1539");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"525.08931483040315909593411009598753586E+1539",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext014() {
        String number = "-4.195730024608447034E-354";
        String number2 = "1.2514849900987264429E+890";
        MathContext mc = new MathContext("precision=0 roundingMode=FLOOR");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext014"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: -525.08931483040315909593411009598753586E+534");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-525.08931483040315909593411009598753586E+534",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext015() {
        String number = "-4.195730024608447034E-206";
        String number2 = "1.2514849900987264429E-720";
        MathContext mc = new MathContext("precision=0 roundingMode=HALF_DOWN");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext015"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: -52.508931483040315909593411009598753586E-927");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-52.508931483040315909593411009598753586E-927",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext016() {
        String number = "-4.195730024608447034E+72";
        String number2 = "1.2514849900987264429E-469";
        MathContext mc = new MathContext("precision=0 roundingMode=FLOOR");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext016"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: -525.08931483040315909593411009598753586E-399");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-525.08931483040315909593411009598753586E-399",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext017() {
        String number = "4.195730024608447034E+63";
        String number2 = "-1.2514849900987264429E+811";
        MathContext mc = new MathContext("precision=0 roundingMode=FLOOR");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext017"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: -52.508931483040315909593411009598753586E+873");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-52.508931483040315909593411009598753586E+873",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext018() {
        String number = "4.195730024608447034E-618";
        String number2 = "1.2514849900987264429E-340";
        MathContext mc = new MathContext("precision=0 roundingMode=HALF_UP");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext018"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: 525.08931483040315909593411009598753586E-960");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"525.08931483040315909593411009598753586E-960",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext019() {
        String number = "-4.195730024608447034E-459";
        String number2 = "1.2514849900987264429E+491";
        MathContext mc = new MathContext("precision=0 roundingMode=UP");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext019"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: -525089314830403159095934110095987.53586");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-525089314830403159095934110095987.53586",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext020() {
        String number = "-4.195730024608447130E+870";
        String number2 = "1.2514849900987264429E-456";
        MathContext mc = new MathContext("precision=0 roundingMode=CEILING");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext020"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: -5.2508931483040317111019001504376138770E+414");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-5.2508931483040317111019001504376138770E+414",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext021() {
        String number = "-1.1140386617063807642E+923";
        String number2 = "1.2514849900987264429E-544";
        MathContext mc = new MathContext("precision=0 roundingMode=DOWN");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext021"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: -13.9420266351520838794437677508244966418E+378");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-13.9420266351520838794437677508244966418E+378",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext022() {
        String number = "1.1140386617063807642E-140";
        String number2 = "1.2514849900987264429E-446";
        MathContext mc = new MathContext("precision=0 roundingMode=HALF_DOWN");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext022"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: 139.420266351520838794437677508244966418E-588");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"139.420266351520838794437677508244966418E-588",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext023() {
        String number = "-1.1140386617063807642E+329";
        String number2 = "1.2514849900987264372E+422";
        MathContext mc = new MathContext("precision=0 roundingMode=UP");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext023"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: -13.9420266351520838159435640335607930824E+750");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-13.9420266351520838159435640335607930824E+750",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext024() {
        String number = "1.1140386617063807642E+638";
        String number2 = "8.391460049216894068E-804";
        MathContext mc = new MathContext("precision=0 roundingMode=UP");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext024"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: 934.84109229921487284131621913042867656E-168");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"934.84109229921487284131621913042867656E-168",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext025() {
        String number = "-1.1140386617063807642E-952";
        String number2 = "8.391460049216894068E-386";
        MathContext mc = new MathContext("precision=0 roundingMode=DOWN");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext025"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestMultiplyBigDecimalMathContext) { 
            System.out.println("-> expected result: -9.3484109229921487284131621913042867656E-1338");
            System.out.println("-> actual result: "+ bigDec.multiply(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-9.3484109229921487284131621913042867656E-1338",bigDec.multiply(bd,mc).toEngineeringString());
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testMultiplyBigDecimalMathContext026() {
        ArithmeticException exp = new ArithmeticException();
        String number = "-1.1140386617063807642E-170";
        String number2 = "8.391460049216894068E+409";
        MathContext mc = new MathContext("precision=8 roundingMode=UNNECESSARY");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext026"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        try {
            bigDec.multiply(bd,mc).toEngineeringString();
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
            if (logtestMultiplyBigDecimalMathContext) { 
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test FAILED---"); }
    }
    public final void testMultiplyBigDecimalMathContext027() {
        ArithmeticException exp = new ArithmeticException();
        String number = "-1.1140386617063807642E+789";
        String number2 = "-8.391460049216894068E+461";
        MathContext mc = new MathContext("precision=7 roundingMode=UNNECESSARY");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext027"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        try {
            bigDec.multiply(bd,mc).toEngineeringString();
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
            if (logtestMultiplyBigDecimalMathContext) { 
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test FAILED---"); }
    }
    public final void testMultiplyBigDecimalMathContext028() {
        ArithmeticException exp = new ArithmeticException();
        String number = "1.1140386617063807642E-161";
        String number2 = "-8.391460049216894068E-87";
        MathContext mc = new MathContext("precision=5 roundingMode=UNNECESSARY");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext028"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        try {
            bigDec.multiply(bd,mc).toEngineeringString();
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
            if (logtestMultiplyBigDecimalMathContext) { 
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test FAILED---"); }
    }
    public final void testMultiplyBigDecimalMathContext029() {
        ArithmeticException exp = new ArithmeticException();
        String number = "-1.1140386617063807642E+55";
        String number2 = "8.391460049216894068E+483";
        MathContext mc = new MathContext("precision=5 roundingMode=UNNECESSARY");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext029"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        try {
            bigDec.multiply(bd,mc).toEngineeringString();
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
            if (logtestMultiplyBigDecimalMathContext) { 
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test FAILED---"); }
    }
    public final void testMultiplyBigDecimalMathContext030() {
        ArithmeticException exp = new ArithmeticException();
        String number = "1.1140386617063807642E+930";
        String number2 = "-8.391460049216894068E+354";
        MathContext mc = new MathContext("precision=8 roundingMode=UNNECESSARY");
        
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("testMultiplyBigDecimalMathContext030"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        try {
            bigDec.multiply(bd,mc).toEngineeringString();
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
            if (logtestMultiplyBigDecimalMathContext) { 
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (logtestMultiplyBigDecimalMathContext) { System.out.println("---Test FAILED---"); }
    }
        
    /*
     * Test method for 'java.math.BigDecimal.remainder(BigDecimal)'
     */
    public final void testRemainderBigDecimal001() {
        String number = "1.8302063728033398269E-268";
        String number2 = "13877787807814456755295395851135253906250000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal001"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 1.8302063728033398269E-268");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"1.8302063728033398269E-268",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal002() {
        String number = "1.8302063728033398269E+675";
        String number2 = "17105694144590052135299433389769484024417260032672311666956399949826551302317634295491188822779804468154907226562500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal002"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 0E+105");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"0E+105",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal003() {
        String number = "1.8302063728033398269E+477";
        String number2 = "-774518382969863654563606126657270369514043838409758173097552588268883269619537192972536446855402919381611508501510893507874167279798470063104463610130670098400487213054106160547686407674634950818161873529290988480036254032515793769363252226078930817720902268774807453155517578125000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal003"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 1.8302063728033398269E+477");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"1.8302063728033398269E+477",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal004() {
        String number = "-1.8302063728033398269E-274";
        String number2 = "140737488355328000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal004"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -1.8302063728033398269E-274");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"-1.8302063728033398269E-274",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal005() {
        String number = "1.8302063728033398269E-34";
        String number2 = "-21359870359209100823950217061695521146027045223566527699470416078222197257806405500229620869365760000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal005"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 1.8302063728033398269E-34");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"1.8302063728033398269E-34",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal006() {
        String number = "-1.8302063728033398269E+916";
        String number2 = "-1105429575052088912049453038438451145102848046647844017283034044181579750804790663420352960444452892999750053625107156940415372665194489176787584483463433571159839630126953125000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal006"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 0E+114");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"0E+114",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal007() {
        String number = "1.8302063728033398269E-442";
        String number2 = "-6588873714519077015217858782043761879818725863980317218798601890692590181856099745156484606530981618164480052143974047544094161183801228861734774610182246989964482963841874152421951293945312500000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal007"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 1.8302063728033398269E-442");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"1.8302063728033398269E-442",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal008() {
        String number = "1.8302063728033398269E-65";
        String number2 = "81396660557615408619138815804702853969052225614207863286087131491260220188203516498064357920795340463170531135173745610911728535816886780766154248685679077711122529046801935528113748607321948544054748732127521996846810889129351334120652800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal008"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 1.8302063728033398269E-65");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"1.8302063728033398269E-65",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal009() {
        String number = "-1.8302063728033398269E-973";
        String number2 = "201948391736579022185402512712393274796340847387909889221191406250000000000000";
        if (log) { System.out.println("testRemainderBigDecimal009"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -1.8302063728033398269E-973");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"-1.8302063728033398269E-973",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal010() {
        String number = "-1.8302063728022871389E-395";
        String number2 = "1448908652612273978801459086542046684909204991702262110333218382389680210974855218358325032313753295912632390287500452744941237259723720853799022734165191650390625000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal010"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -1.8302063728022871389E-395");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"-1.8302063728022871389E-395",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal011() {
        String number = "-6.727636073941130589E+385";
        String number2 = "-4230758200257591033292257971409734654901789970971399803421752289756197063912392613281210946814177823024583756960149493147238400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal011"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -6.727636073941130589E+385");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"-6.727636073941130589E+385",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal012() {
        String number = "-6.727636073941130589E+194";
        String number2 = "2854495385411919762116571938898990272765493248000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal012"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 0E+7");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"0E+7",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal013() {
        String number = "6.727636073941130589E-893";
        String number2 = "-53976053469340278908664699142502497319475002277726758656398146688553698769765169112321921896701801416003420587163435397481219368417699666835331273606612967341789044439792633056640625000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal013"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 6.727636073941130589E-893");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"6.727636073941130589E-893",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal014() {
        String number = "-6.727636073941130589E+510";
        String number2 = "-6277101735386680763835789423207666416102355444464034512896000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal014"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -6.727636073941130589E+510");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"-6.727636073941130589E+510",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal015() {
        String number = "-6.727636073941130589E-981";
        String number2 = "80083323807324036977183408605459059829359319202459200097972752463463491214369631966379566304015554399617028002770857942196364336897832621696224673508908541925935662249640535057289329739550736612086684284824588897167963937569165869484998103471282455451928679972053646002149644081421017706486905696552924815989626949885860085487365722656250000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal015"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -6.727636073941130589E-981");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"-6.727636073941130589E-981",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal016() {
        String number = "-6.727636073941130589E+48";
        String number2 = "-1142987391282274982215783548305340959451909994822798661215125843227632635906738195675448021860172029649183054353374028232220541362283918751126042293410041241965514017376085261008455839702952026165627685827530513051897287368774414062500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal016"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -6.727636073941130589E+48");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"-6.727636073941130589E+48",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal017() {
        String number = "6.727636073941130589E+141";
        String number2 = "228597478256454996443156709661068191890381998964559732243025168645526527181347639135089604372034405929836610870674805646444108272456783750225208458682008248393102803475217052201691167940590405233125537165506102610379457473754882812500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal017"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 6.727636073941130589E+141");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"6.727636073941130589E+141",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal018() {
        String number = "6.727636073941130589E-471";
        String number2 = "-562949953421312000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal018"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 6.727636073941130589E-471");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"6.727636073941130589E-471",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal019() {
        String number = "-6.727636073941130589E-207";
        String number2 = "3091630018413806675756281512745589875439141863765147998925788201161215313831648339628955013265538062369970892825201741741892062928834390124594326938773664598957584651858739200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal019"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -6.727636073941130589E-207");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"-6.727636073941130589E-207",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal020() {
        String number = "6.727636073941130589E-350";
        String number2 = "-2967364920549937108585388209238111610694051823006232801962431247134065072076503487197849666178566750029071135308800927221639653907914180308580398559570312500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal020"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 6.727636073941130589E-350");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"6.727636073941130589E-350",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal021() {
        String number = "6.727636073941130589E+271";
        String number2 = "-6162975822039154729779129416271767419321925274289242224767804145812988281250000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal021"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 0E+91");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"0E+91",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal022() {
        String number = "-6.727636073941130589E-777";
        String number2 = "69089348440755557003090814902403196568928002915490251080189627761348734425299416463772060027778305812484378351569197308775960791574655573549224030216464598197489976882934570312500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal022"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -6.727636073941130589E-777");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"-6.727636073941130589E-777",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal023() {
        String number = "-6.727636073941130608E-360";
        String number2 = "-3705346855594118253554271520278013051304639509300498049262642688253220148477952000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal023"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -6.727636073941130608E-360");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"-6.727636073941130608E-360",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal024() {
        String number = "8.102099357864587376E-461";
        String number2 = "-7957171782556586274486115970349133441607298412757563479047423630290551952200534008528896000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal024"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: 8.102099357864587376E-461");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"8.102099357864587376E-461",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal025() {
        String number = "-8.102099357864587376E-514";
        String number2 = "-229827867994583522581171992751360791819668288660993382628469464984779725347543815372055206079045780077374875290757418866907179544925032289648657203216694523260365898745517837350483082303763102702637730721557867087811336696354974199808809875607722108419581154811408815586287382807377604001012909579407041565198697185455860514334647365409016668163074026418536589913284165198084459141176174890594877120442163231928134337067604064941406250000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal025"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result: -8.102099357864587376E-514");
            System.out.println("-> actual result: "+ bigDec.remainder(bd).toString()); 
        }
        assertEquals(msgNotSame,"-8.102099357864587376E-514",bigDec.remainder(bd).toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimal026() {
        ArithmeticException exp = new ArithmeticException();
        String number = "-8.102099357864587376E+866";
        String number2 = "4056481920730334084789450257203200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal026"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            try {
                bigDec.remainder(bd).toString();
                fail(msgRaise+"ArithmeticException");
            } catch (ArithmeticException e) {
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (log) { System.out.println("---Test FAILED---"); }
    }
    public final void testRemainderBigDecimal027() {
        ArithmeticException exp = new ArithmeticException();
        String number = "-8.102099357864587376E-196";
        String number2 = "481482486096808963263994485646231829634525412053847048809984698891639709472656250000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal027"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            try {
                bigDec.remainder(bd).toString();
                fail(msgRaise+"ArithmeticException");
            } catch (ArithmeticException e) {
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (log) { System.out.println("---Test FAILED---"); }
    }
    public final void testRemainderBigDecimal028() {
        ArithmeticException exp = new ArithmeticException();
        String number = "8.102099357864587376E-714";
        String number2 = "-31385508676933403819178947116038332080511777222320172564480000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal028"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            try {
                bigDec.remainder(bd).toString();
                fail(msgRaise+"ArithmeticException");
            } catch (ArithmeticException e) {
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (log) { System.out.println("---Test FAILED---"); }
    }
    public final void testRemainderBigDecimal029() {
        ArithmeticException exp = new ArithmeticException();
        String number = "-8.102099357864587376E-305";
        String number2 = "-360663227257255303935895732910032556165961473439707501371182775250116023057856221204873382512201983193686003098967901201860125772561165533542848941930881747231114289282870553688931468722706172064953424748634448498504354633631088663527393818869688928928646384167860938774197165912482887506484985351562500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal029"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            try {
                bigDec.remainder(bd).toString();
                fail(msgRaise+"ArithmeticException");
            } catch (ArithmeticException e) {
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (log) { System.out.println("---Test FAILED---"); }
    }
    public final void testRemainderBigDecimal030() {
        ArithmeticException exp = new ArithmeticException();
        String number = "8.102099357864587376E+412";
        String number2 = "7820637090558987986053067246626861311460871015865156259567651607760106563903284371716754521876518984337600390895591595917613704775178966962521940772354349797454654516566458501688411107378001622274090262189901259489058978278238854441894346042117427290227410153520863867397426179826271260399111884428996564061487006824791023973375558853149414062500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testRemainderBigDecimal030"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            try {
                bigDec.remainder(bd).toString();
                fail(msgRaise+"ArithmeticException");
            } catch (ArithmeticException e) {
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (log) { System.out.println("---Test FAILED---"); }
    }
    
    /*
     * Test method for 'java.math.BigDecimal.remainder(BigDecimal, MathContext)'
     */
    boolean logtestRemainderBigDecimalMathContext = false;
    
    public final void testRemainderBigDecimalMathContext001() {
        String number = "8.897841259083430779E+426";
        String number2 = "-13207363278391631158808494622912915162971183150903038053199017725666847563918121486860431397191429697366859874831428216972058688987567138431263474461573489422709667099975127104218287941910661026325605822705279710103700707318789043470959922469175911912238464522443449958400000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=13568 roundingMode=HALF_DOWN");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext001"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: 10207164281940836973596465151720464973408352162791994090554579783030573005978764446934632287174955219644894675450633800656415448823567777759201029824547379190302114052396656077287117519632602880735526194413104309999473545047174017763255259052001668241733681583835473510400000000000000000000000000000000000000");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"10207164281940836973596465151720464973408352162791994090554579783030573005978764446934632287174955219644894675450633800656415448823567777759201029824547379190302114052396656077287117519632602880735526194413104309999473545047174017763255259052001668241733681583835473510400000000000000000000000000000000000000",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext002() {
        String number = "-8.897841259083430779E-409";
        String number2 = "63382530011411470074835160268800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=913 roundingMode=HALF_EVEN");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext002"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: -889.7841259083430779E-411");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-889.7841259083430779E-411",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext003() {
        String number = "-8.897841259083430779E+814";
        String number2 = "-40740719526689721725368913768187563221029367873318725012722808987087625995266734123667947520000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=16757 roundingMode=HALF_UP");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext003"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: 0.0E+177");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"0.0E+177",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext004() {
        String number = "8.897841259083430779E-978";
        String number2 = "-80083323807324036977183408605459059829359319202459200097972752463463491214369631966379566304015554399617028002770857942196364336897832621696224673508908541925935662249640535057289329739550736612086684284824588897167963937569165869484998103471282455451928679972053646002149644081421017706486905696552924815989626949885860085487365722656250000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=56948 roundingMode=HALF_EVEN");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext004"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: 8.897841259083430779E-978");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"8.897841259083430779E-978",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext005() {
        String number = "-8.897841259083430779E+637";
        String number2 = "235098870164457501593747307444449135563733111354417504301750341255683451890945434570312500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=15210 roundingMode=HALF_UP");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext005"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: 0E+153");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"0E+153",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext006() {
        String number = "8.897841259083430779E+145";
        String number2 = "38685626227668133590597632000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=25262 roundingMode=DOWN");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext006"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: 88.97841259083430779E+144");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"88.97841259083430779E+144",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext007() {
        String number = "8.897841259083430779E+431";
        String number2 = "545018859521043292148486875679655532575564381991767244918406411756340330079430673444484721117101683449355628182112707248792906457082709670603772303299923535330540665329020147804477615214801800806821673311009651685665744480502326041460037231445312500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=67842 roundingMode=CEILING");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext007"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: 889.7841259083430779E+429");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"889.7841259083430779E+429",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext008() {
        String number = "8.897841259083430779E+762";
        String number2 = "-123922941275178184730176980265163259122247014145561307695608414123021323139125950875605831496864467101057841360241742961259866764767755210096714177620907215744077954088656985687629825227941592130905899764686558156805800645202527003098120356172628930835344363003969192504882812500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=38655 roundingMode=FLOOR");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext008"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: 0.0E+33");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"0.0E+33",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext009() {
        String number = "8.897841259083430779E+177";
        String number2 = "-27967679607885704301190218685229334463595448410322902860782665724913148442727445468110629165844363647880233895721679414135153184333079469434028345743715409785657103816385949318619923106913065211176796883038813718238213431256579671499373815533666304000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=62138 roundingMode=HALF_EVEN");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext009"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: 8.897841259083430779E+177");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"8.897841259083430779E+177",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext010() {
        String number = "-1.5842497851538791387E-467";
        String number2 = "411376139330301510538742295639337626245683966408394965837152256000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=40468 roundingMode=CEILING");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext010"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: -15.842497851538791387E-468");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-15.842497851538791387E-468",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext011() {
        String number = "-1.5842497851538791387E-932";
        String number2 = "11150372599265311570767859136324180752990208000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=34155 roundingMode=DOWN");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext011"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: -15.842497851538791387E-933");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-15.842497851538791387E-933",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext012() {
        String number = "1.5842497851538791387E-448";
        String number2 = "161558713389263217748322010169914619837072677910327911376953125000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=31056 roundingMode=HALF_UP");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext012"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: 158.42497851538791387E-450");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"158.42497851538791387E-450",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext013() {
        String number = "1.5842497851538791387E+43";
        String number2 = "-227137101342377153296663689965001416985512925214786893837965687243949777535436851039434703348051114237738288001958180604229563008942080000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=14240 roundingMode=DOWN");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext013"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: 15.842497851538791387E+42");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"15.842497851538791387E+42",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext014() {
        String number = "1.5842497851538791387E-60";
        String number2 = "2079081953128979843706080916136381273557908561675137500451684615159379310910914129045428165882498487279341232994509533877536416843729818994917954648208326474496996556583481398790274105891425326564108556026495558493292787477502159276809834409505128860473632812500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=40211 roundingMode=CEILING");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext014"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: 1.5842497851538791387E-60");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"1.5842497851538791387E-60",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext015() {
        String number = "-1.5842497851538791387E-529";
        String number2 = "-497841222228891336571525124302409939247220173497693370648089329183175906722830675294715585850057089356557327164409798569977283477783203125000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=80395 roundingMode=HALF_UP");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext015"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: -158.42497851538791387E-531");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-158.42497851538791387E-531",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext016() {
        String number = "1.5842497851538791387E-676";
        String number2 = "-2273736754432320594787597656250000000000";
        MathContext mc = new MathContext("precision=17673 roundingMode=HALF_EVEN");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext016"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: 158.42497851538791387E-678");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"158.42497851538791387E-678",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext017() {
        String number = "1.5842497851538791387E+56";
        String number2 = "-227137101342377153296663689965001416985512925214786893837965687243949777535436851039434703348051114237738288001958180604229563008942080000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=72427 roundingMode=HALF_UP");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext017"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: 158.42497851538791387E+54");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"158.42497851538791387E+54",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext018() {
        String number = "-1.5842497851538791387E+722";
        String number2 = "-80695308690215893426747474125094121072803306025913234775958104891895238188026287332176417290004307232371974124148359168000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=19124 roundingMode=FLOOR");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext018"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: 0.0E+36");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"0.0E+36",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext019() {
        String number = "-1.5842497851538791387E-785";
        String number2 = "-46116860184273879040000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=44304 roundingMode=DOWN");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext019"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: -15.842497851538791387E-786");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-15.842497851538791387E-786",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext020() {
        String number = "-1.5842497851538791387E-655";
        String number2 = "-5455865226015270144917465359423896565349283978281558294610784096649552724019268872401764850646631516583473594902919507132114236820167098737006657048824191515931147381449005327443639959902135709513151997354094667339683445208836978375276856133578573589351365882892605345461118612559410897582904198529247886388230600243963930264373666779270475981652038481567559291911833226911058622861723188089312595791556666950218056324310839351004226066539185630506982555475529999880997298318510575512038787932867392708019350150093474304362673899040066605314208171562118110387018532492220401763916015625000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=53436 roundingMode=HALF_DOWN");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext020"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: -158.42497851538791387E-657");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-158.42497851538791387E-657",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext021() {
        String number = "1.5842497851538791387E+485";
        String number2 = "-307828173409331868845930000782371982852185463050511302093346042220669701339821957901673955116288403443801781174272000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=9626 roundingMode=FLOOR");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext021"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: 60327848157207674510827314428533590181845607583464069773187223668085390068045843295722173658262885760531219611648000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"60327848157207674510827314428533590181845607583464069773187223668085390068045843295722173658262885760531219611648000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext022() {
        String number = "-1.5842497851538791387E-138";
        String number2 = "-7695704335233296721148250019559299571304636576262782552333651055516742533495548947541848877907210086095044529356800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=48464 roundingMode=HALF_EVEN");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext022"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: -1.5842497851538791387E-138");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-1.5842497851538791387E-138",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext023() {
        String number = "158424978515.38791387";
        String number2 = "-1461501637330902918203684832716283019655932542976000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=65657 roundingMode=HALF_UP");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext023"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: 158424978515.38791387");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"158424978515.38791387",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext024() {
        String number = "1.5842497851538791387E-243";
        String number2 = "-36028797018963968000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=87265 roundingMode=UP");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext024"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: 1.5842497851538791387E-243");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"1.5842497851538791387E-243",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext025() {
        String number = "-1.5842497851538791387E+63";
        String number2 = "-1842755090244893238399196572748178169393027939656465052918069482541808673043041431682679065028153695088607604995490158642466105776330465152617887818082371115063181312000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        MathContext mc = new MathContext("precision=65061 roundingMode=HALF_DOWN");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext025"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (logtestRemainderBigDecimalMathContext) { 
            System.out.println("-> expected result: -1.5842497851538791387E+63");
            System.out.println("-> actual result: "+ bigDec.remainder(bd,mc).toEngineeringString()); 
        }
        assertEquals(msgNotSame,"-1.5842497851538791387E+63",bigDec.remainder(bd,mc).toEngineeringString());
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test PASSED---"); }
    }
    public final void testRemainderBigDecimalMathContext026() {
        ArithmeticException exp = new ArithmeticException();
        String number = "1.5842497851540041454E+501";
        String number2 = "0";
        MathContext mc = new MathContext("precision=4 roundingMode=UNNECESSARY");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext026"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        try {
            bigDec.remainder(bd,mc).toEngineeringString();
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
            if (logtestRemainderBigDecimalMathContext) { 
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test FAILED---"); }
    }
    public final void testRemainderBigDecimalMathContext027() {
        ArithmeticException exp = new ArithmeticException();
        String number = "1.7216961135462248174E+115";
        String number2 = "0";
        MathContext mc = new MathContext("precision=8 roundingMode=UNNECESSARY");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext027"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        try {
            bigDec.remainder(bd,mc).toEngineeringString();
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
            if (logtestRemainderBigDecimalMathContext) { 
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test FAILED---"); }
    }
    public final void testRemainderBigDecimalMathContext028() {
        ArithmeticException exp = new ArithmeticException();
        String number = "-1.7216961135462248174E+356";
        String number2 = "0";
        MathContext mc = new MathContext("precision=6 roundingMode=UNNECESSARY");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext028"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        try {
            bigDec.remainder(bd,mc).toEngineeringString();
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
            if (logtestRemainderBigDecimalMathContext) { 
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test FAILED---"); }
    }
    public final void testRemainderBigDecimalMathContext029() {
        ArithmeticException exp = new ArithmeticException();
        String number = "-1.7216961135462248174E+254";
        String number2 = "0";
        MathContext mc = new MathContext("precision=5 roundingMode=UNNECESSARY");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext029"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        try {
            bigDec.remainder(bd,mc).toEngineeringString();
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
            if (logtestRemainderBigDecimalMathContext) { 
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test FAILED---"); }
    }
    public final void testRemainderBigDecimalMathContext030() {
        ArithmeticException exp = new ArithmeticException();
        String number = "1.7216961135462248174E-119";
        String number2 = "0";
        MathContext mc = new MathContext("precision=1 roundingMode=UNNECESSARY");
        
        if (logtestRemainderBigDecimalMathContext) { System.out.println("testRemainderBigDecimalMathContext030"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        try {
            bigDec.remainder(bd,mc).toEngineeringString();
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
            if (logtestRemainderBigDecimalMathContext) { 
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (logtestRemainderBigDecimalMathContext) { System.out.println("---Test FAILED---"); }
    }
    
        
    
    /*
     * Test method for 'java.math.BigDecimal.divideAndRemainder(BigDecimal)'
     */
    public final void testDivideAndRemainderBigDecimal001() {
        String number = "5.136152271503443783E+571";
        String number2 = "-132211193758049719790383061606554207965680936592856243856929759054881158247262269165037842087943056969518242405004671660851200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal001"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=-388480893751156191276622661584761711871765504523808063569595233355327487008771413486086629617767898135952769995360693466605895126922374560571881379647564425461081791552542161519060189035511789913874401128612993575 remainder=123003676466752911758278100497191169171137731551828769142223103082977634198085069239209775655820227660976065706760035368960000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"-388480893751156191276622661584761711871765504523808063569595233355327487008771413486086629617767898135952769995360693466605895126922374560571881379647564425461081791552542161519060189035511789913874401128612993575",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"123003676466752911758278100497191169171137731551828769142223103082977634198085069239209775655820227660976065706760035368960000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal002() {
        String number = "5.136152271503443783E-732";
        String number2 = "-4153837486827862102824397063376076800000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal002"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=0E-750 remainder=5.136152271503443783E-732");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"0E-750",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"5.136152271503443783E-732",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal003() {
        String number = "5.136152271503443783E+82";
        String number2 = "-145367744859121378109864761577600906870728272137463612056298039836127857622679584665238210142752713112152504321253235586706920325722931200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal003"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=0E+64 remainder=5.136152271503443783E+82");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"0E+64",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"5.136152271503443783E+82",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal004() {
        String number = "5.136152271503443783E-514";
        String number2 = "15641274181117975972106134493253722622921742031730312519135303215520213127806568743433509043753037968675200781791183191835227409550357933925043881544708699594909309033132917003376822214756003244548180524379802518978117956556477708883788692084234854580454820307041727734794852359652542520798223768857993128122974013649582047946751117706298828125000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal004"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=0E-532 remainder=5.136152271503443783E-514");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"0E-532",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"5.136152271503443783E-514",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal005() {
        String number = "5.136152353427839578E-776";
        String number2 = "-17105694144590052135299433389769484024417260032672311666956399949826551302317634295491188822779804468154907226562500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal005"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=0E-794 remainder=5.136152353427839578E-776");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"0E-794",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"5.136152353427839578E-776",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal006() {
        String number = "-6.510615555426900570E+343";
        String number2 = "26959946667150639794667015087019630673637144422540572481103610249216000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal006"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=0E+325 remainder=-6.510615555426900570E+343");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"0E+325",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"-6.510615555426900570E+343",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal007() {
        String number = "-6.510615555426900570E+114";
        String number2 = "-1601666476146480739543668172109181196587186384049184001959455049269269824287392639327591326080311087992340560055417158843927286737956652433924493470178170838518713244992810701145786594791014732241733685696491777943359278751383317389699962069425649109038573599441072920042992881628420354129738113931058496319792538997717201709747314453125000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal007"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=0E+96 remainder=-6.510615555426900570E+114");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"0E+96",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"-6.510615555426900570E+114",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal008() {
        String number = "-6.510615555426900570E-598";
        String number2 = "-18565347327101170151514378963233428801414757874741396801188926343416545403100314487863295869654510382837994428743146373777131469177623276401802974451684843536990694150700533133909743118992141409591563654834377029046700783193006407729741361560807206877626957251331627162116234578193867366731943186452308784033032569434982903291571532289927298866134180300702408807798150520143129814176445219907259763280369809519556279590416759796641515368242930767413300546277155067542707911343313753604888916015625000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal008"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=0E-616 remainder=-6.510615555426900570E-598");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"0E-616",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"-6.510615555426900570E-598",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal009() {
        String number = "-6.510615555426900570E+507";
        String number2 = "-62230152778611417071440640537801242405902521687211671331011166147896988340353834411839448231257136169569665895551224821247160434722900390625000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal009"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=0E+489 remainder=-6.510615555426900570E+507");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"0E+489",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"-6.510615555426900570E+507",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal010() {
        String number = "6.510615555426900570E+547";
        String number2 = "218497496936607064853048583478354175496839440705647678599864575975883972208808167719614290358159090999064327310325620422930884252602183354953346451122776638950446123565515229051718149272758321962318725648740732173736042431692028683588857933856768000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal010"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=297972088774812514210647181372125434930255111116393574517304268145092129259407619400003435521082920168848279781852395660986553026789735944599807710706895979130245233098145574290683223680553771921492464891111672648125550532798787365097113352029662045834268633215169819058965799 remainder=146106614839769014356259917753261690505715263493513027654893076866164596453034700377881979166306378079522338734442357775585901782810054610391334637298237162680228775254098970244033407939020953144493395438973685248846303517538446061947654523322368000000000000000000000000000");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"297972088774812514210647181372125434930255111116393574517304268145092129259407619400003435521082920168848279781852395660986553026789735944599807710706895979130245233098145574290683223680553771921492464891111672648125550532798787365097113352029662045834268633215169819058965799",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"146106614839769014356259917753261690505715263493513027654893076866164596453034700377881979166306378079522338734442357775585901782810054610391334637298237162680228775254098970244033407939020953144493395438973685248846303517538446061947654523322368000000000000000000000000000",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal011() {
        String number = "6.510615555426900570E+432";
        String number2 = "-102844034832575377634685573909834406561420991602098741459288064000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal011"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=0E+414 remainder=6.510615555426900570E+432");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"0E+414",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"6.510615555426900570E+432",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal012() {
        String number = "-6.510615555426900570E-560";
        String number2 = "-23817051317718446589520242536874132581700120107002038199303870846751188192899823151552628349788604516295066307994130118526061826166445047808000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal012"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=0E-578 remainder=-6.510615555426900570E-560");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"0E-578",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"-6.510615555426900570E-560",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal013() {
        String number = "-6.510615555426900570E+794";
        String number2 = "40825630519695636625451838730669037115402682165131655023854311711911386834746873719136176475616256467548992377693192222471051086238885881422905513321221086766209022788401810073552248479658129323623990586888131397780081623301913076284252546274395376077063196407019208249218313043971591724992171204783460603451085757146163593193692407618481018290004063405713557328852688001689667476976839369235050924168135685464188770955403396268473814600952209730166941881179809570312500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal013"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=-15947372943293463511318694054098367504169789666679207268002346471467824107704400549359702077446613468962527038906389237791092866467585515738012683704915623674248721214556612662796208856437759288212 remainder=-2266184958031625391028334022167461759966011933764767191995590250432843116774990845628914360024888937709551796624441024603622111411970800417750095945848576606848941698068311410763679078896310587024627060091122236333814401135296043451623354290814171432086117546182798305142375398000048887028958725670313032409126301983483865403351629686454168309300571451759408329349512128353130915707983017693941048957829704641554233870368853173693501901198033010587096214294433593750000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"-15947372943293463511318694054098367504169789666679207268002346471467824107704400549359702077446613468962527038906389237791092866467585515738012683704915623674248721214556612662796208856437759288212",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"-2266184958031625391028334022167461759966011933764767191995590250432843116774990845628914360024888937709551796624441024603622111411970800417750095945848576606848941698068311410763679078896310587024627060091122236333814401135296043451623354290814171432086117546182798305142375398000048887028958725670313032409126301983483865403351629686454168309300571451759408329349512128353130915707983017693941048957829704641554233870368853173693501901198033010587096214294433593750000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal014() {
        String number = "-6.510615555426900570E+476";
        String number2 = "-41986725672294304698693534930938919559815138746018929100965938443564346881799425604389210058399706985066412377516727568798247465463490869563870241624638641629264956489539536844116108110489576596877703530322114063718397476900262835380550685672751736003980783765188061955175032596160062531298606813834339845925569534301757812500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal014"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=0E+458 remainder=-6.510615555426900570E+476");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"0E+458",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"-6.510615555426900570E+476",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal015() {
        String number = "-6.510615555426900570E+69";
        String number2 = "-1447401115466452442794637312608598848165874808320507050493219800098914120499200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal015"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=0E+51 remainder=-6.510615555426900570E+69");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"0E+51",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"-6.510615555426900570E+69",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal016() {
        String number = "-6.510615555426900570E+630";
        String number2 = "2156795733372051183573361206961570453890971553803245798488288819937280000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal016"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=-3.01865190786883272959508209154664035493841050893271796269667801561243003853606609366384590960398184618921985986147609834398504768517641945468898967419590917415916919708251953125E+447 remainder=0E+271");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"-3.01865190786883272959508209154664035493841050893271796269667801561243003853606609366384590960398184618921985986147609834398504768517641945468898967419590917415916919708251953125E+447",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"0E+271",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal017() {
        String number = "-6.510615555426900570E-23";
        String number2 = "1186945968219974843434155283695244644277620729202493120784972498853626028830601394879139866471426700011628454123520370888655861563165672123432159423828125000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal017"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=0E-41 remainder=-6.510615555426900570E-23");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"0E-41",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"-6.510615555426900570E-23",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal018() {
        String number = "6.510615555426900570E-19";
        String number2 = "12689709186578246116370122779152717734118094248505477908030301606197383489446497369662037145278921431148322955288754479233010356712218133513903531788380898892193582498678475334413294103341219034204764135903903555256913986068738765117247524472077202517539262771606445312500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal018"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=0E-37 remainder=6.510615555426900570E-19");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"0E-37",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"6.510615555426900570E-19",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal019() {
        String number = "-6.510615555359528534E+523";
        String number2 = "-786273043163712562039433166915143128076759437797809611308969202872594389193033149459185338101223829663902412375591936849547698636131648263506559398248562179509927922069190191757872654871491589390422827478719695781292483328360848340185509774241398409032358072406271576608802516371113057125533249599485772917339709867647870393858397356895174176324261555797614721651883861920608529258650553535745793388403018704536123037095016836133485546396134890469872274467053399166874606077797181763500419632924864435656771288751798465455067344009876251220703125000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal019"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=0E+505 remainder=-6.510615555359528534E+523");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"0E+505",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"-6.510615555359528534E+523",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    
    public final void testDivideAndRemainderBigDecimal020() {
        String number = "6.221254864074593878E+279";
        String number2 = "-388533778445145814183892381364703781328481367810427904250362481947780857041041699635200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal020"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=0E+261 remainder=6.221254864074593878E+279");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"0E+261",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"6.221254864074593878E+279",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal021() {
        String number = "6.221254864074593878E+936";
        String number2 = "-6344854593289123058185061389576358867059047124252738954015150803098691744723248684831018572639460715574161477644377239616505178356109066756951765894190449446096791249339237667206647051670609517102382067951951777628456993034369382558623762236038601258769631385803222656250000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal021"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=-9.80519690814465766021227842282015133210308385419560821913275287094509025322488912522153698651527945500297254277941781289013423261089792E+440 remainder=0E+306");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"-9.80519690814465766021227842282015133210308385419560821913275287094509025322488912522153698651527945500297254277941781289013423261089792E+440",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"0E+306",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal022() {
        String number = "6.221254864074593878E+742";
        String number2 = "1355252715606880542509316001087427139282226562500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal022"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=0E+724 remainder=6.221254864074593878E+742");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"0E+724",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"6.221254864074593878E+742",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal023() {
        String number = "-6.221254864074593878E-852";
        String number2 = "-324518553658426726783156020576256000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal023"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=0E-870 remainder=-6.221254864074593878E-852");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"0E-870",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"-6.221254864074593878E-852",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal024() {
        String number = "6.221254864074593878E+434";
        String number2 = "-10384593717069655257060992658440192000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal024"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=-5.990850517193001566823930147781637679680756660662149510991614231869561990606598556041717529296875E+163 remainder=0E+67");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"-5.990850517193001566823930147781637679680756660662149510991614231869561990606598556041717529296875E+163",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"0E+67",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal025() {
        String number = "-6.221254864074593878E-859";
        String number2 = "20194839173657902218540251271239327479634084738790988922119140625000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal025"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            System.out.println("-> expected result:  operation=0E-877 remainder=-6.221254864074593878E-859");
            System.out.println("-> actual result: operation="+bigDec.divideAndRemainder(bd)[0].toString()+"remainder= "+bigDec.divideAndRemainder(bd)[1].toString()); 
        }
        assertEquals(msgNotSame,"0E-877",bigDec.divideAndRemainder(bd)[0].toString());
        assertEquals(msgNotSame,"-6.221254864074593878E-859",bigDec.divideAndRemainder(bd)[1].toString());
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testDivideAndRemainderBigDecimal026() {
        ArithmeticException exp = new ArithmeticException();
        String number = "-6.221254864074593878E-958";
        String number2 = "100974195868289511092701256356196637398170423693954944610595703125000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal026"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            try {
                bigDec.divideAndRemainder(bd).toString();
                fail(msgRaise+"ArithmeticException");
            } catch (ArithmeticException e) {
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (log) { System.out.println("---Test FAILED---"); }
    }
    public final void testDivideAndRemainderBigDecimal027() {
        ArithmeticException exp = new ArithmeticException();
        String number = "6.221254864074593878E+408";
        String number2 = "-82718061255302767487140869206996285356581211090087890625000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal027"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            try {
                bigDec.divideAndRemainder(bd).toString();
                fail(msgRaise+"ArithmeticException");
            } catch (ArithmeticException e) {
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (log) { System.out.println("---Test FAILED---"); }
    }
    public final void testDivideAndRemainderBigDecimal028() {
        ArithmeticException exp = new ArithmeticException();
        String number = "-6.221254864074598761E+466";
        String number2 = "405648192073033408478945025720320000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal028"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            try {
                bigDec.divideAndRemainder(bd).toString();
                fail(msgRaise+"ArithmeticException");
            } catch (ArithmeticException e) {
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (log) { System.out.println("---Test FAILED---"); }
    }
    public final void testDivideAndRemainderBigDecimal029() {
        ArithmeticException exp = new ArithmeticException();
        String number = "-7.595718147998050665E-187";
        String number2 = "-337350334183376743179154369640640608246718764235792241602488416803460617311032306952012011854386258850021378669771471234257621052610622917720820460041331045886181527748703956604003906250000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal029"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            try {
                bigDec.divideAndRemainder(bd).toString();
                fail(msgRaise+"ArithmeticException");
            } catch (ArithmeticException e) {
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (log) { System.out.println("---Test FAILED---"); }
    }
    public final void testDivideAndRemainderBigDecimal030() {
        ArithmeticException exp = new ArithmeticException();
        String number = "7.595718147998050665E-15";
        String number2 = "27875931498163278926919647840810451882475520000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (log) { System.out.println("testDivideAndRemainderBigDecimal030"); }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) { 
            try {
                bigDec.divideAndRemainder(bd).toString();
                fail(msgRaise+"ArithmeticException");
            } catch (ArithmeticException e) {
                System.out.println("-> expected exception: "+ exp);
                System.out.println("-> actual: "+ e);
                System.out.println("---Test PASSED---"); 
            }
        }
        if (log) { System.out.println("---Test FAILED---"); }
    }
}
