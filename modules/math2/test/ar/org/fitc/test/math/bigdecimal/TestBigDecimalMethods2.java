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
 * abs()
 * abs(MathContext) 
 * negate() 
 * negate(MathContext) 
 * plus() 
 * plus(MathContext) 
 * signum() 
 * precision() 
 * round(MathContext) 
 * movePointLeft(int) 
 * movePointRight(int) 
 * stripTrailingZeros() 
 * compareTo(BigDecimal val) 
 * max(BigDecimal val) 
 * min(BigDecimal val) 
 * byteValueExact() 
 * intValueExact() 
 * longValueExact() 
 * shortValueExact()
 * toBigInteger() 
 * toBigIntegerExact()
 * toEngineeringString()
 * toPlainString() 
 * ulp() 
 * 
 */
public class TestBigDecimalMethods2 extends TestCase implements Messages {
    private boolean log = false;

    private BigDecimal bigDec = null;

    public TestBigDecimalMethods2(String name) {
        super(name);
    }

    /*
     * Test method for 'java.math.BigDecimal.abs()'
     */

    public final void testAbs001() {
        String number = "-2.227823424231842704801185168002598479813863821264428128716647287995852850367470834582439128320118913395681167976488033500E-1357322412";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "2.227823424231842704801185168002598479813863821264428128716647287995852850367470834582439128320118913395681167976488033500E-1357322412",
                bigDec.abs().toString());
    }

    public final void testAbs002() {
        String number = "2.227823424231842704801185168002598479813863821264428128716647287995852850367470834582439128320118913395681167976488033500E-1357322412";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "2.227823424231842704801185168002598479813863821264428128716647287995852850367470834582439128320118913395681167976488033500E-1357322412",
                bigDec.abs().toString());
    }

    public final void testAbs003() {
        String number = "-2.227823424231842704801185168002598479813863821264428128716647287995852850367470834582439128320118913395681168058412429295E-1357707161";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "2.227823424231842704801185168002598479813863821264428128716647287995852850367470834582439128320118913395681168058412429295E-1357707161",
                bigDec.abs().toString());
    }

    public final void testAbs004() {
        String number = "2.420226356324592756579469341602822893979606605828174194378539553777312869262843315750922507584129183188944541574457454575E-1357707161";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "2.420226356324592756579469341602822893979606605828174194378539553777312869262843315750922507584129183188944541574457454575E-1357707161",
                bigDec.abs().toString());
    }

    public final void testAbs005() {
        String number = "-2.420226356324592756579469341602822893979606605828174194378539553777312869262843315750922507584129183188944541574457454575E+1357707401";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "2.420226356324592756579469341602822893979606605828174194378539553777312869262843315750922507584129183188944541574457454575E+1357707401",
                bigDec.abs().toString());
    }

    public final void testAbs006() {
        String number = "-2.420226356324592756579469341602822893979606605828174194378539553777312869262843315750922507584129183188944541574457454575E+1357707401";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "2.420226356324592756579469341602822893979606605828174194378539553777312869262843315750922507584129183188944541574457454575E+1357707401",
                bigDec.abs().toString());
    }

    public final void testAbs007() {
        String number = "-2.420226356324590705921883279891824277245629629844556540979255120961249292124586922737527416703594908819414701932667123382E-1356552914";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "2.420226356324590705921883279891824277245629629844556540979255120961249292124586922737527416703594908819414701932667123382E-1356552914",
                bigDec.abs().toString());
    }

    public final void testAbs008() {
        String number = "1.843017560046342601244616820802149651482378252136935997392862756432932812576725872245472369792098373809154420780549191350E+1356553154";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "1.843017560046342601244616820802149651482378252136935997392862756432932812576725872245472369792098373809154420780549191350E+1356553154",
                bigDec.abs().toString());
    }

    public final void testAbs009() {
        String number = "1.843017560046342601244616820802149651482378252136935997392862756432932812576725872245472369792098373809154420780549191350E+1356553154";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "1.843017560046342601244616820802149651482378252136935997392862756432932812576725872245472369792098373809154420780549191350E+1356553154",
                bigDec.abs().toString());
    }

    public final void testAbs010() {
        String number = "-1.843017560046342601244616820802149651482378252136935997392893407988412325238295425592990594948563738496847595307415488969E-1356937663";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "1.843017560046342601244616820802149651482378252136935997392893407988412325238295425592990594948563738496847595307415488969E-1356937663",
                bigDec.abs().toString());
    }

    public final void testAbs011() {
        String number = "2.035420492139092653022900994402374065648121036700682063054755022214392831472098353413955749056108643602417794378518612425E+1356937903";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "2.035420492139092653022900994402374065648121036700682063054755022214392831472098353413955749056108643602417794378518612425E+1356937903",
                bigDec.abs().toString());
    }

    public final void testAbs012() {
        String number = "2.035420492139092653022900994402374065648121036700682063054755022214392831472098353413955749056108643602417794378518612425E+1356937903";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "2.035420492139092653022900994402374065648121036700682063054755022214392831472098353413955749056108643602417794378518612425E+1356937903",
                bigDec.abs().toString());
    }

    public final void testAbs013() {
        String number = "-2.035420492139092653022900994402374065648121036700682063054755022214392831472098353413955749056108643602417617766768519465E+1358861647";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "2.035420492139092653022900994402374065648121036700682063054755022214392831472098353413955749056108643602417617766768519465E+1358861647",
                bigDec.abs().toString());
    }

    public final void testAbs014() {
        String number = "-4.15185274515934322258402690400484262147129166690188878533556994581045303932119564626727292096022161132831490395618224425E-1358861408";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "4.15185274515934322258402690400484262147129166690188878533556994581045303932119564626727292096022161132831490395618224425E-1358861408",
                bigDec.abs().toString());
    }

    public final void testAbs015() {
        String number = "4.15185274515934322258402690400484262147129166690188878533556994581045303932119564626727292096022161132831490395618224425E+1358861646";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "4.15185274515934322258402690400484262147129166690188878533556994581045303932119564626727292096022161132831490395618224425E+1358861646",
                bigDec.abs().toString());
    }

    public final void testAbs016() {
        String number = "4.15185274515934322258402690400484262147129166690188878533556994581045303932119564626727292096022161132831490395618224425E-1358861408";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "4.15185274515934322258402690400484262147129166690188878533556994581045303932119564626727292096022161132831490395618224425E-1358861408",
                bigDec.abs().toString());
    }

    public final void testAbs017() {
        String number = "4.15185274515934322258443433306141984918269438790497932264872396756718804948092201530180026514634698527479771523425516604E+1359246395";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "4.15185274515934322258443433306141984918269438790497932264872396756718804948092201530180026514634698527479771523425516604E+1359246395",
                bigDec.abs().toString());
    }

    public final void testAbs018() {
        String number = "-6.07588206608684374036686864000708676312871951253934944195449260362505322827492045795210671360032430926094863993587645500E-1359246157";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "6.07588206608684374036686864000708676312871951253934944195449260362505322827492045795210671360032430926094863993587645500E-1359246157",
                bigDec.abs().toString());
    }

    public final void testAbs019() {
        String number = "-6.07588206608684374036686864000708676312871951253934944195449260362505322827492045795210671360032430926094863993587645500E+1359246395";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "6.07588206608684374036686864000708676312871951253934944195449260362505322827492045795210671360032430926094863993587645500E+1359246395",
                bigDec.abs().toString());
    }

    public final void testAbs020() {
        String number = "-6.07588206608684374036686864000708676312871951253934944195449260362505237727977100347185065523513230254054566537562554882E-1358091911";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "6.07588206608684374036686864000708676312871951253934944195449260362505237727977100347185065523513230254054566537562554882E-1358091911",
                bigDec.abs().toString());
    }

    public final void testAbs021() {
        String number = "2.0252940220289479134556228800023622543762398375131164806514975345416844094249734859840355712001081030869828799786254850E-1358091912";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "2.0252940220289479134556228800023622543762398375131164806514975345416844094249734859840355712001081030869828799786254850E-1358091912",
                bigDec.abs().toString());
    }

    public final void testAbs022() {
        String number = "-2.0252940220289479134556228800023622543762398375131164806514975345416844094249734859840355712001081030869828799786254850E+1358092148";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "2.0252940220289479134556228800023622543762398375131164806514975345416844094249734859840355712001081030869828799786254850E+1358092148",
                bigDec.abs().toString());
    }

    public final void testAbs023() {
        String number = "2.0252940220289479134556228800023622543762398375131164806514975345416844094249734859840355712001081030869828799786259990E-1358476660";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "2.0252940220289479134556228800023622543762398375131164806514975345416844094249734859840355712001081030869828799786259990E-1358476660",
                bigDec.abs().toString());
    }

    public final void testAbs024() {
        String number = "2.22782342423184270480118516800259847981386382126442812871664728799585285036747083458243912832011891339568116797648803350E+1358476897";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "2.22782342423184270480118516800259847981386382126442812871664728799585285036747083458243912832011891339568116797648803350E+1358476897",
                bigDec.abs().toString());
    }

    public final void testAbs025() {
        String number = "2.22782342423184270480118516800259847981386382126442812871664728799585285036747083458243912832011891339568116797648803350E+1358476897";
        bigDec = new BigDecimal(number);
        assertEquals(
                msgNotSame,
                "2.22782342423184270480118516800259847981386382126442812871664728799585285036747083458243912832011891339568116797648803350E+1358476897",
                bigDec.abs().toString());
    }

    /*
     * Test method for 'java.math.BigDecimal.abs(MathContext)'
     */

    public final void testAbsMathContext001() {
        MathContext mc = new MathContext(
                "precision=13303 roundingMode=HALF_EVEN");
        String number = "-2.4668106254591737389792001120816312789517389802511416242997653755318939033996799014487572929E+1072807452";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext001");
            System.out
                    .println("-> expected result: 2.4668106254591737389792001120816312789517389802511416242997653755318939033996799014487572929E+1072807452");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "2.4668106254591737389792001120816312789517389802511416242997653755318939033996799014487572929E+1072807452",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext002() {
        MathContext mc = new MathContext(
                "precision=28763 roundingMode=UNNECESSARY");
        String number = "-2.4668106254591737389792001120816312789517389802511416242997649221603851158917527779219638408E-1073961517";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext002");
            System.out
                    .println("-> expected result: 2.4668106254591737389792001120816312789517389802511416242997649221603851158917527779219638408E-1073961517");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "2.4668106254591737389792001120816312789517389802511416242997649221603851158917527779219638408E-1073961517",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext003() {
        MathContext mc = new MathContext(
                "precision=9429 roundingMode=UNNECESSARY");
        String number = "1.7382706998054281269490736541093360307639196959282656005428398501157387091313806559431657608E+1073961699";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext003");
            System.out
                    .println("-> expected result: 1.7382706998054281269490736541093360307639196959282656005428398501157387091313806559431657608E+1073961699");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "1.7382706998054281269490736541093360307639196959282656005428398501157387091313806559431657608E+1073961699",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext004() {
        MathContext mc = new MathContext(
                "precision=5557 roundingMode=UNNECESSARY");
        String number = "1.7382706998054281269490736541093360307639196959282656005428398501157387091313806559431657608E-1073961517";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext004");
            System.out
                    .println("-> expected result: 1.7382706998054281269490736541093360307639196959282656005428398501157387091313806559431657608E-1073961517");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "1.7382706998054281269490736541093360307639196959282656005428398501157387091313806559431657608E-1073961517",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext005() {
        MathContext mc = new MathContext(
                "precision=81528 roundingMode=UNNECESSARY");
        String number = "1.7382706998054281269490736541093360307639196959282656005428398501157387091313806559431657608E-1073961517";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext005");
            System.out
                    .println("-> expected result: 1.7382706998054281269490736541093360307639196959282656005428398501157387091313806559431657608E-1073961517");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "1.7382706998054281269490736541093360307639196959282656005428398501157387091313806559431657608E-1073961517",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext006() {
        MathContext mc = new MathContext("precision=11311 roundingMode=HALF_UP");
        String number = "-1.7382706998054281269490736541093360307639196959282656005428398501157387091313806559431657608E+1073576950";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext006");
            System.out
                    .println("-> expected result: 1.7382706998054281269490736541093360307639196959282656005428398501157387091313806559431657608E+1073576950");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "1.7382706998054281269490736541093360307639196959282656005428398501157387091313806559431657608E+1073576950",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext007() {
        MathContext mc = new MathContext("precision=15976 roundingMode=FLOOR");
        String number = "1.9811173416900099976257824734334344468265261240358909417951483585877904405541470711116962715E-1073576768";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext007");
            System.out
                    .println("-> expected result: 1.9811173416900099976257824734334344468265261240358909417951483585877904405541470711116962715E-1073576768");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "1.9811173416900099976257824734334344468265261240358909417951483585877904405541470711116962715E-1073576768",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext008() {
        MathContext mc = new MathContext("precision=35093 roundingMode=CEILING");
        String number = "-1.9811173416900099976257824734334344468265261240358909417951483585877904405541470711116962715E+1073576950";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext008");
            System.out
                    .println("-> expected result: 1.9811173416900099976257824734334344468265261240358909417951483585877904405541470711116962715E+1073576950");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "1.9811173416900099976257824734334344468265261240358909417951483585877904405541470711116962715E+1073576950",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext009() {
        MathContext mc = new MathContext(
                "precision=33743 roundingMode=HALF_EVEN");
        String number = "-1.9811173416900099976257824734334344468265261240358909417951483585877904405541470711116962715E+1073576950";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext009");
            System.out
                    .println("-> expected result: 1.9811173416900099976257824734334344468265261240358909417951483585877904405541470711116962715E+1073576950");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "1.9811173416900099976257824734334344468265261240358909417951483585877904405541470711116962715E+1073576950",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext010() {
        MathContext mc = new MathContext("precision=75850 roundingMode=HALF_UP");
        String number = "1.9811173416900099976257824734334344468265261240358909417951483585877904405541470711116962715E+1073576950";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext010");
            System.out
                    .println("-> expected result: 1.9811173416900099976257824734334344468265261240358909417951483585877904405541470711116962715E+1073576950");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "1.9811173416900099976257824734334344468265261240358909417951483585877904405541470711116962715E+1073576950",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext011() {
        MathContext mc = new MathContext("precision=48896 roundingMode=FLOOR");
        String number = "-1.9858604401643182372874369425608582440152489058348679992414825091438852009334979776579566330E+1071653205";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext011");
            System.out
                    .println("-> expected result: 1.9858604401643182372874369425608582440152489058348679992414825091438852009334979776579566330E+1071653205");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "1.9858604401643182372874369425608582440152489058348679992414825091438852009334979776579566330E+1071653205",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext012() {
        MathContext mc = new MathContext(
                "precision=43620 roundingMode=UNNECESSARY");
        String number = "-3.1953505511129193510093265700539265271395582645740176480566909009480490976679791469543488250E-1071653023";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext012");
            System.out
                    .println("-> expected result: 3.1953505511129193510093265700539265271395582645740176480566909009480490976679791469543488250E-1071653023");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "3.1953505511129193510093265700539265271395582645740176480566909009480490976679791469543488250E-1071653023",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext013() {
        MathContext mc = new MathContext("precision=58961 roundingMode=DOWN");
        String number = "-3.1953505511129193510093265700539265271395582645740176480566909009480490976679791469543488250E-1071653023";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext013");
            System.out
                    .println("-> expected result: 3.1953505511129193510093265700539265271395582645740176480566909009480490976679791469543488250E-1071653023");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "3.1953505511129193510093265700539265271395582645740176480566909009480490976679791469543488250E-1071653023",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext014() {
        MathContext mc = new MathContext(
                "precision=66631 roundingMode=HALF_DOWN");
        String number = "3.1953505511129193510093265700539265271395582645740176480566909009480490976679791469543488250E+1071653205";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext014");
            System.out
                    .println("-> expected result: 3.1953505511129193510093265700539265271395582645740176480566909009480490976679791469543488250E+1071653205");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "3.1953505511129193510093265700539265271395582645740176480566909009480490976679791469543488250E+1071653205",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext015() {
        MathContext mc = new MathContext("precision=46800 roundingMode=UP");
        String number = "3.1953505511129193510093265700539265248606472485099193928038077923386481469690541464366157069E+1071268456";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext015");
            System.out
                    .println("-> expected result: 3.1953505511129193510093265700539265248606472485099193928038077923386481469690541464366157069E+1071268456");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "3.1953505511129193510093265700539265248606472485099193928038077923386481469690541464366157069E+1071268456",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext016() {
        MathContext mc = new MathContext("precision=74357 roundingMode=UP");
        String number = "1.661582286578718062524849816428041794112570297578489176989479268492985530787349156416261389E+1071268455";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext016");
            System.out
                    .println("-> expected result: 1.661582286578718062524849816428041794112570297578489176989479268492985530787349156416261389E+1071268455");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "1.661582286578718062524849816428041794112570297578489176989479268492985530787349156416261389E+1071268455",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext017() {
        MathContext mc = new MathContext(
                "precision=23108 roundingMode=UNNECESSARY");
        String number = "-1.661582286578718062524849816428041794112570297578489176989479268492985530787349156416261389E+1071268455";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext017");
            System.out
                    .println("-> expected result: 1.661582286578718062524849816428041794112570297578489176989479268492985530787349156416261389E+1071268455");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "1.661582286578718062524849816428041794112570297578489176989479268492985530787349156416261389E+1071268455",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext018() {
        MathContext mc = new MathContext(
                "precision=71616 roundingMode=UNNECESSARY");
        String number = "1.661582286578718062524849816428041794112570297578489176989479268492985530787349156416261389E-1071268275";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext018");
            System.out
                    .println("-> expected result: 1.661582286578718062524849816428041794112570297578489176989479268492985530787349156416261389E-1071268275");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "1.661582286578718062524849816428041794112570297578489176989479268492985530787349156416261389E-1071268275",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext019() {
        MathContext mc = new MathContext(
                "precision=77746 roundingMode=UNNECESSARY");
        String number = "1.661582286578718062524849816428041794112570297578489176989479268492989216085114219394290900E-1072422522";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext019");
            System.out
                    .println("-> expected result: 1.661582286578718062524849816428041794112570297578489176989479268492989216085114219394290900E-1072422522");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "1.661582286578718062524849816428041794112570297578489176989479268492989216085114219394290900E-1072422522",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext020() {
        MathContext mc = new MathContext(
                "precision=44411 roundingMode=UNNECESSARY");
        String number = "2.7096572673437556096559089314057296950143454083587669655520738840039456348224463166172878036E+1072422703";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext020");
            System.out
                    .println("-> expected result: 2.7096572673437556096559089314057296950143454083587669655520738840039456348224463166172878036E+1072422703");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "2.7096572673437556096559089314057296950143454083587669655520738840039456348224463166172878036E+1072422703",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext021() {
        MathContext mc = new MathContext(
                "precision=64728 roundingMode=HALF_DOWN");
        String number = "2.7096572673437556096559089314057296950143454083587669655520738840039456348224463166172878036E-1072422521";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext021");
            System.out
                    .println("-> expected result: 2.7096572673437556096559089314057296950143454083587669655520738840039456348224463166172878036E-1072422521");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "2.7096572673437556096559089314057296950143454083587669655520738840039456348224463166172878036E-1072422521",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext022() {
        MathContext mc = new MathContext("precision=58346 roundingMode=DOWN");
        String number = "-2.7096572673437556096559089314057296950143454083587669655520738840039456348224463166172878036E-1072422521";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext022");
            System.out
                    .println("-> expected result: 2.7096572673437556096559089314057296950143454083587669655520738840039456348224463166172878036E-1072422521");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "2.7096572673437556096559089314057296950143454083587669655520738840039456348224463166172878036E-1072422521",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext023() {
        MathContext mc = new MathContext(
                "precision=93861 roundingMode=HALF_DOWN");
        String number = "-2.7096572673437556096559089314057296950143454083587669655520738840039456348224463166172878036E+1072422703";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext023");
            System.out
                    .println("-> expected result: 2.7096572673437556096559089314057296950143454083587669655520738840039456348224463166172878036E+1072422703");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "2.7096572673437556096559089314057296950143454083587669655520738840039456348224463166172878036E+1072422703",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext024() {
        MathContext mc = new MathContext("precision=63201 roundingMode=FLOOR");
        String number = "-2.7106058870386172575882398252312144544520899647185623770413407141151645868983164979265398759E+1072037954";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext024");
            System.out
                    .println("-> expected result: 2.7106058870386172575882398252312144544520899647185623770413407141151645868983164979265398759E+1072037954");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "2.7106058870386172575882398252312144544520899647185623770413407141151645868983164979265398759E+1072037954",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testAbsMathContext025() {
        MathContext mc = new MathContext("precision=69828 roundingMode=DOWN");
        String number = "-2.9525039092283374803326177507298281110769518364663923068043823924759973662452127317858183143E-1072037772";
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("testAbsMathContext025");
            System.out
                    .println("-> expected result: 2.9525039092283374803326177507298281110769518364663923068043823924759973662452127317858183143E-1072037772");
            System.out.println("-> actual result:" + bigDec.abs(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "2.9525039092283374803326177507298281110769518364663923068043823924759973662452127317858183143E-1072037772",
                bigDec.abs(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    /*
     * Test method for 'java.math.BigDecimal.negate()'
     */
    public final void testNegate001() {
        BigDecimal bi = new BigDecimal(
                "9.0031684642139085098612274647216291158898552146351789229175659226E-1617390822");
        String number = "9.0031684642139085098612274647216291158898552146351789229175659226E-1617390822";
        if (log) {
            System.out.println("testNegate001");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());
        }
        assertEquals(
                msgNotSame,
                "-9.0031684642139085098612274647216291158898552146351789229175659226E-1617390822",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate002() {
        BigDecimal bi = new BigDecimal(
                "9.0031684642139085095030162791013097731928736379890311239822883386E-1619314566");
        String number = "9.0031684642139085095030162791013097731928736379890311239822883386E-1619314566";
        if (log) {
            System.out.println("testNegate002");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());
        }
        assertEquals(
                msgNotSame,
                "-9.0031684642139085095030162791013097731928736379890311239822883386E-1619314566",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate003() {
        BigDecimal bi = new BigDecimal(
                "2.3953383987358105209722531786874059115670256993066072363725634106E+1619314694");
        String number = "2.3953383987358105209722531786874059115670256993066072363725634106E+1619314694";
        if (log) {
            System.out.println("testNegate003");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());
        }
        assertEquals(
                msgNotSame,
                "-2.3953383987358105209722531786874059115670256993066072363725634106E+1619314694",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate004() {
        BigDecimal bi = new BigDecimal(
                "2.3953383987358105209722531786874059115670256993066072363725634106E+1619314694");
        String number = "2.3953383987358105209722531786874059115670256993066072363725634106E+1619314694";
        if (log) {
            System.out.println("testNegate004");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "-2.3953383987358105209722531786874059115670256993066072363725634106E+1619314694",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate005() {
        BigDecimal bi = new BigDecimal(
                "-2.3953383987358105209722531786874059115670256993066072363725634106E+1619314694");
        String number = "-2.3953383987358105209722531786874059115670256993066072363725634106E+1619314694";
        if (log) {
            System.out.println("testNegate005");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "2.3953383987358105209722531786874059115670256993066072363725634106E+1619314694",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate006() {
        BigDecimal bi = new BigDecimal(
                "2.3953383987358105209722531786874059115670256993066072363725634106E-1619314566");
        String number = "2.3953383987358105209722531786874059115670256993066072363725634106E-1619314566";
        if (log) {
            System.out.println("testNegate006");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "-2.3953383987358105209722531786874059115670256993066072363725634106E-1619314566",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate007() {
        BigDecimal bi = new BigDecimal(
                "2.3953383987358105209722531786874059115670256993066072363725634106E+1619314694");
        String number = "2.3953383987358105209722531786874059115670256993066072363725634106E+1619314694";
        if (log) {
            System.out.println("testNegate007");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "-2.3953383987358105209722531786874059115670256993066072363725634106E+1619314694",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate008() {
        BigDecimal bi = new BigDecimal(
                "-2.3953383987365241831113819014400727888590809903479983158657371469E+1619699443");
        String number = "-2.3953383987365241831113819014400727888590809903479983158657371469E+1619699443";
        if (log) {
            System.out.println("testNegate008");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "2.3953383987365241831113819014400727888590809903479983158657371469E+1619699443",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate009() {
        BigDecimal bi = new BigDecimal(
                "3.1800182190113346571528188751539699170803617042518751241497824589E+1619699443");
        String number = "3.1800182190113346571528188751539699170803617042518751241497824589E+1619699443";
        if (log) {
            System.out.println("testNegate009");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "-3.1800182190113346571528188751539699170803617042518751241497824589E+1619699443",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate010() {
        BigDecimal bi = new BigDecimal(
                "3.1800182190113346571528188751539699170803617042518751241497824589E+1619699443");
        String number = "3.1800182190113346571528188751539699170803617042518751241497824589E+1619699443";
        if (log) {
            System.out.println("testNegate010");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "-3.1800182190113346571528188751539699170803617042518751241497824589E+1619699443",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate011() {
        BigDecimal bi = new BigDecimal(
                "3.1800182190113346571528188751539699170803617042518751241497824589E+1619699443");
        String number = "3.1800182190113346571528188751539699170803617042518751241497824589E+1619699443";
        if (log) {
            System.out.println("testNegate011");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "-3.1800182190113346571528188751539699170803617042518751241497824589E+1619699443",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate012() {
        BigDecimal bi = new BigDecimal(
                "3.1800182190113346571528188751539699170803617042518751241497824589E-1619699315");
        String number = "3.1800182190113346571528188751539699170803617042518751241497824589E-1619699315";
        if (log) {
            System.out.println("testNegate012");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "-3.1800182190113346571528188751539699170803617042518751241497824589E-1619699315",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate013() {
        BigDecimal bi = new BigDecimal(
                "3.1800182190113346571528188751539694557549667976648615598593610515E+1618545197");
        String number = "3.1800182190113346571528188751539694557549667976648615598593610515E+1618545197";
        if (log) {
            System.out.println("testNegate013");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "-3.1800182190113346571528188751539694557549667976648615598593610515E+1618545197",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate014() {
        BigDecimal bi = new BigDecimal(
                "-7.846798202755241361805656964665640055133360049452678877772190483E+1618545196");
        String number = "-7.846798202755241361805656964665640055133360049452678877772190483E+1618545196";
        if (log) {
            System.out.println("testNegate014");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "7.846798202755241361805656964665640055133360049452678877772190483E+1618545196",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate015() {
        BigDecimal bi = new BigDecimal(
                "7.846798202755241361805656964665640055133360049452678877772190483E+1618545196");
        String number = "7.846798202755241361805656964665640055133360049452678877772190483E+1618545196";
        if (log) {
            System.out.println("testNegate015");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "-7.846798202755241361805656964665640055133360049452678877772190483E+1618545196",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate016() {
        BigDecimal bi = new BigDecimal(
                "-7.846798202755241361805656964665640055133360049452678877772190483E-1618545070");
        String number = "-7.846798202755241361805656964665640055133360049452678877772190483E-1618545070";
        if (log) {
            System.out.println("testNegate016");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "7.846798202755241361805656964665640055133360049452678877772190483E-1618545070",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate017() {
        BigDecimal bi = new BigDecimal(
                "7.846798202755241361805656964665640055133360049452678877772190483E+1618545196");
        String number = "7.846798202755241361805656964665640055133360049452678877772190483E+1618545196";
        if (log) {
            System.out.println("testNegate017");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "-7.846798202755241361805656964665640055133360049452678877772190483E+1618545196",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate018() {
        BigDecimal bi = new BigDecimal(
                "-7.846798202755241361805656964665640055133360049452678877772190483E+1618545196");
        String number = "-7.846798202755241361805656964665640055133360049452678877772190483E+1618545196";
        if (log) {
            System.out.println("testNegate018");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "7.846798202755241361805656964665640055133360049452678877772190483E+1618545196",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate019() {
        BigDecimal bi = new BigDecimal(
                "7.846798202755241361805656964764680572466353781159237050891118118E-1618929819");
        String number = "7.846798202755241361805656964764680572466353781159237050891118118E-1618929819";
        if (log) {
            System.out.println("testNegate019");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "-7.846798202755241361805656964764680572466353781159237050891118118E-1618929819",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate020() {
        BigDecimal bi = new BigDecimal(
                "-1.5693596405510482723611313929331280110266720098905357755544380966E-1618929818");
        String number = "-1.5693596405510482723611313929331280110266720098905357755544380966E-1618929818";
        if (log) {
            System.out.println("testNegate020");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "1.5693596405510482723611313929331280110266720098905357755544380966E-1618929818",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate021() {
        BigDecimal bi = new BigDecimal(
                "1.5693596405510482723611313929331280110266720098905357755544380966E+1618929946");
        String number = "1.5693596405510482723611313929331280110266720098905357755544380966E+1618929946";
        if (log) {
            System.out.println("testNegate021");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "-1.5693596405510482723611313929331280110266720098905357755544380966E+1618929946",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate022() {
        BigDecimal bi = new BigDecimal(
                "1.5693596405510482723611313929331280110266720098905357755544380966E+1618929946");
        String number = "1.5693596405510482723611313929331280110266720098905357755544380966E+1618929946";
        if (log) {
            System.out.println("testNegate022");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "-1.5693596405510482723611313929331280110266720098905357755544380966E+1618929946",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate023() {
        BigDecimal bi = new BigDecimal(
                "-1.5693596405510482723611313929331280110266720098905357755544380966E-1618929818");
        String number = "-1.5693596405510482723611313929331280110266720098905357755544380966E-1618929818";
        if (log) {
            System.out.println("testNegate023");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "1.5693596405510482723611313929331280110266720098905357755544380966E-1618929818",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate024() {
        BigDecimal bi = new BigDecimal(
                "1.5693596405510482723611313929331280110266720098905357755544380966E-1618929818");
        String number = "1.5693596405510482723611313929331280110266720098905357755544380966E-1618929818";
        if (log) {
            System.out.println("testNegate024");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "-1.5693596405510482723611313929331280110266720098905357755544380966E-1618929818",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate025() {
        BigDecimal bi = new BigDecimal(
                "1.5693596405510482723611312904480857383675583079800657725748355875E-1608541597");
        String number = "1.5693596405510482723611312904480857383675583079800657725748355875E-1608541597";
        if (log) {
            System.out.println("testNegate025");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "-1.5693596405510482723611312904480857383675583079800657725748355875E-1608541597",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate026() {
        BigDecimal bi = new BigDecimal(
                "1.4454628268233339350694631250699863259456189564781250564317192995E-1608541597");
        String number = "1.4454628268233339350694631250699863259456189564781250564317192995E-1608541597";
        if (log) {
            System.out.println("testNegate026");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "-1.4454628268233339350694631250699863259456189564781250564317192995E-1608541597",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate027() {
        BigDecimal bi = new BigDecimal(
                "-1.4454628268233339350694631250699863259456189564781250564317192995E+1608541725");
        String number = "-1.4454628268233339350694631250699863259456189564781250564317192995E+1608541725";
        if (log) {
            System.out.println("testNegate027");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "1.4454628268233339350694631250699863259456189564781250564317192995E+1608541725",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate028() {
        BigDecimal bi = new BigDecimal(
                "1.4454628268233339350694631250699863259456189564781250564317192995E+1608541725");
        String number = "1.4454628268233339350694631250699863259456189564781250564317192995E+1608541725";
        if (log) {
            System.out.println("testNegate028");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "-1.4454628268233339350694631250699863259456189564781250564317192995E+1608541725",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate029() {
        BigDecimal bi = new BigDecimal(
                "1.4454628268233339350694631250699863259456189564781250564317192995E-1608541597");
        String number = "1.4454628268233339350694631250699863259456189564781250564317192995E-1608541597";
        if (log) {
            System.out.println("testNegate029");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "-1.4454628268233339350694631250699863259456189564781250564317192995E-1608541597",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegate030() {
        BigDecimal bi = new BigDecimal(
                "1.4454628268233339350694631250699863259456189564781250564317192995E+1608541725");
        String number = "1.4454628268233339350694631250699863259456189564781250564317192995E+1608541725";
        if (log) {
            System.out.println("testNegate030");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: " + bi.negate());
            System.out.println("-> actual result: " + bigDec.negate());

        }
        assertEquals(
                msgNotSame,
                "-1.4454628268233339350694631250699863259456189564781250564317192995E+1608541725",
                bigDec.negate().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    /*
     * Test method for 'java.math.BigDecimal.negate(MathContext)'
     */
    public final void testNegateMathContext001() {
        MathContext mc = new MathContext("precision=63773 roundingMode=FLOOR");
        BigDecimal bi = new BigDecimal(
                "-5.9689871196945709372288097798648988906236497086708289160684837E+542932834");
        String number = "-5.9689871196945709372288097798648988906236497086708289160684837E+542932834";
        if (log) {
            System.out.println("testNegateMathContext001");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext002() {
        MathContext mc = new MathContext(
                "precision=53930 roundingMode=UNNECESSARY");
        BigDecimal bi = new BigDecimal(
                "-5.9689871196945709372288097798648988906236497086708289160684837E-542932712");
        String number = "-5.9689871196945709372288097798648988906236497086708289160684837E-542932712";
        if (log) {
            System.out.println("testNegateMathContext002");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext003() {
        MathContext mc = new MathContext("precision=49434 roundingMode=FLOOR");
        BigDecimal bi = new BigDecimal(
                "5.9689871196945709372288097798648988906236497086708289160684837E-542932712");
        String number = "5.9689871196945709372288097798648988906236497086708289160684837E-542932712";
        if (log) {
            System.out.println("testNegateMathContext003");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext004() {
        MathContext mc = new MathContext(
                "precision=16508 roundingMode=UNNECESSARY");
        BigDecimal bi = new BigDecimal(
                "5.9689871196945709372288097799035865927068503851187032024430648E-542547963");
        String number = "5.9689871196945709372288097799035865927068503851187032024430648E-542547963";
        if (log) {
            System.out.println("testNegateMathContext004");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext005() {
        MathContext mc = new MathContext("precision=84230 roundingMode=UP");
        BigDecimal bi = new BigDecimal(
                "-9.0341426676458370941841445316874145371601184779882816026982456E+542548085");
        String number = "-9.0341426676458370941841445316874145371601184779882816026982456E+542548085";
        if (log) {
            System.out.println("testNegateMathContext005");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext006() {
        MathContext mc = new MathContext("precision=23602 roundingMode=FLOOR");
        BigDecimal bi = new BigDecimal(
                "9.0341426676458370941841445316874145371601184779882816026982456E+542548085");
        String number = "9.0341426676458370941841445316874145371601184779882816026982456E+542548085";
        if (log) {
            System.out.println("testNegateMathContext006");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext007() {
        MathContext mc = new MathContext("precision=56890 roundingMode=CEILING");
        BigDecimal bi = new BigDecimal(
                "9.0341426676458370941841445316874145371601184779882816026982456E+542548085");
        String number = "9.0341426676458370941841445316874145371601184779882816026982456E+542548085";
        if (log) {
            System.out.println("testNegateMathContext007");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext008() {
        MathContext mc = new MathContext(
                "precision=14197 roundingMode=UNNECESSARY");
        BigDecimal bi = new BigDecimal(
                "-9.0341426676458370941841445316874145371601184779882816026982456E+542548085");
        String number = "-9.0341426676458370941841445316874145371601184779882816026982456E+542548085";
        if (log) {
            System.out.println("testNegateMathContext008");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext009() {
        MathContext mc = new MathContext(
                "precision=61581 roundingMode=UNNECESSARY");
        BigDecimal bi = new BigDecimal(
                "9.0341426676458370941841445316874145371601184779882816026982456E+542548085");
        String number = "9.0341426676458370941841445316874145371601184779882816026982456E+542548085";
        if (log) {
            System.out.println("testNegateMathContext009");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext010() {
        MathContext mc = new MathContext(
                "precision=97465 roundingMode=UNNECESSARY");
        BigDecimal bi = new BigDecimal(
                "9.0341426676458370941841445316874145372520916579903357438327546E-528312253");
        String number = "9.0341426676458370941841445316874145372520916579903357438327546E-528312253";
        if (log) {
            System.out.println("testNegateMathContext010");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext011() {
        MathContext mc = new MathContext(
                "precision=37236 roundingMode=UNNECESSARY");
        BigDecimal bi = new BigDecimal(
                "-4.03309940519903441704649309450331006123219574910191142977600250E-528312252");
        String number = "-4.03309940519903441704649309450331006123219574910191142977600250E-528312252";
        if (log) {
            System.out.println("testNegateMathContext011");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext012() {
        MathContext mc = new MathContext("precision=17638 roundingMode=UP");
        BigDecimal bi = new BigDecimal(
                "-4.03309940519903441704649309450331006123219574910191142977600250E+528312376");
        String number = "-4.03309940519903441704649309450331006123219574910191142977600250E+528312376";
        if (log) {
            System.out.println("testNegateMathContext012");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext013() {
        MathContext mc = new MathContext(
                "precision=66922 roundingMode=UNNECESSARY");
        BigDecimal bi = new BigDecimal(
                "4.03309940519903441704649309450331006123219574910191142977600250E-528312252");
        String number = "4.03309940519903441704649309450331006123219574910191142977600250E-528312252";
        if (log) {
            System.out.println("testNegateMathContext013");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext014() {
        MathContext mc = new MathContext(
                "precision=19390 roundingMode=UNNECESSARY");
        BigDecimal bi = new BigDecimal(
                "4.03309940519903441704649309450331006123219574910191142977600250E-528312252");
        String number = "4.03309940519903441704649309450331006123219574910191142977600250E-528312252";
        if (log) {
            System.out.println("testNegateMathContext014");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext015() {
        MathContext mc = new MathContext("precision=46331 roundingMode=UP");
        BigDecimal bi = new BigDecimal(
                "4.03309940519903441704649309450331006123219574910191142977600250E+528312376");
        String number = "4.03309940519903441704649309450331006123219574910191142977600250E+528312376";
        if (log) {
            System.out.println("testNegateMathContext015");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext016() {
        MathContext mc = new MathContext("precision=23230 roundingMode=UP");
        BigDecimal bi = new BigDecimal(
                "4.03309940519903441704649309450331006123219574910191139002650126E+527927628");
        String number = "4.03309940519903441704649309450331006123219574910191139002650126E+527927628";
        if (log) {
            System.out.println("testNegateMathContext016");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext017() {
        MathContext mc = new MathContext("precision=25834 roundingMode=FLOOR");
        BigDecimal bi = new BigDecimal(
                "-2.2585356669114592735460361329218536342900296194970704006745614E-527927505");
        String number = "-2.2585356669114592735460361329218536342900296194970704006745614E-527927505";
        if (log) {
            System.out.println("testNegateMathContext017");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext018() {
        MathContext mc = new MathContext("precision=17360 roundingMode=DOWN");
        BigDecimal bi = new BigDecimal(
                "2.2585356669114592735460361329218536342900296194970704006745614E+527927627");
        String number = "2.2585356669114592735460361329218536342900296194970704006745614E+527927627";
        if (log) {
            System.out.println("testNegateMathContext018");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext019() {
        MathContext mc = new MathContext(
                "precision=54138 roundingMode=UNNECESSARY");
        BigDecimal bi = new BigDecimal(
                "2.2585356669114592735460361329218536342900296194970704006745614E-527927505");
        String number = "2.2585356669114592735460361329218536342900296194970704006745614E-527927505";
        if (log) {
            System.out.println("testNegateMathContext019");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext020() {
        MathContext mc = new MathContext("precision=47594 roundingMode=HALF_UP");
        BigDecimal bi = new BigDecimal(
                "-2.2585356669114592735460361329218536342900296194970704006745614E+527927627");
        String number = "-2.2585356669114592735460361329218536342900296194970704006745614E+527927627";
        if (log) {
            System.out.println("testNegateMathContext020");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext021() {
        MathContext mc = new MathContext(
                "precision=3191 roundingMode=UNNECESSARY");
        BigDecimal bi = new BigDecimal(
                "-2.2585356669114592735460361329218536342900296194970704006745614E+527927627");
        String number = "-2.2585356669114592735460361329218536342900296194970704006745614E+527927627";
        if (log) {
            System.out.println("testNegateMathContext021");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext022() {
        MathContext mc = new MathContext("precision=40113 roundingMode=DOWN");
        BigDecimal bi = new BigDecimal(
                "2.2585356669114592735460361329218536342900296194970704006745614E-529081751");
        String number = "2.2585356669114592735460361329218536342900296194970704006745614E-529081751";
        if (log) {
            System.out.println("testNegateMathContext022");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext023() {
        MathContext mc = new MathContext("precision=33787 roundingMode=CEILING");
        BigDecimal bi = new BigDecimal(
                "-3.42006829560878118565542614413880693192490199523842089245005012E-529081750");
        String number = "-3.42006829560878118565542614413880693192490199523842089245005012E-529081750";
        if (log) {
            System.out.println("testNegateMathContext023");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext024() {
        MathContext mc = new MathContext("precision=23200 roundingMode=DOWN");
        BigDecimal bi = new BigDecimal(
                "3.42006829560878118565542614413880693192490199523842089245005012E+529081874");
        String number = "3.42006829560878118565542614413880693192490199523842089245005012E+529081874";
        if (log) {
            System.out.println("testNegateMathContext024");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext025() {
        MathContext mc = new MathContext("precision=69245 roundingMode=HALF_UP");
        BigDecimal bi = new BigDecimal(
                "3.42006829560878118565542614413880693192490199523842089245005012E-529081750");
        String number = "3.42006829560878118565542614413880693192490199523842089245005012E-529081750";
        if (log) {
            System.out.println("testNegateMathContext025");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out.println("-> expected result: " + bi.negate(mc));
            System.out.println("-> actual result: " + bigDec.negate(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(msgNotSame, bi.negate(mc), bigDec.negate(mc));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testNegateMathContext026() {
        ArithmeticException exp = new ArithmeticException();
        MathContext mc = new MathContext("precision=1 roundingMode=UNNECESSARY");
        String number = "-1.81466344373793733045623810048211657992111089441175236666374179094934923084477624344169587179529686036593666046085163473241E+1138280481";
        try {
            if (log) {
                System.out.println("testNegateMathContext026");
            }
            bigDec = new BigDecimal(number, mc);
            System.out.println("---Test FAILED---");
            fail(msgRaise + exp);
        } catch (ArithmeticException e) {
            if (log) {
                System.out.println("-> expected exception: " + exp);
                System.out.println("-> actual: " + e);
                System.out.println("---Test PASSED---");
            }
        }
    }

    public final void testNegateMathContext027() {
        ArithmeticException exp = new ArithmeticException();
        MathContext mc = new MathContext("precision=1 roundingMode=UNNECESSARY");
        String number = "2.30721494989537746300864558489869108018541242289494229475818599134988687921692979523301332271116241070000073600697594813468E+1152516190";
        try {
            if (log) {
                System.out.println("testNegateMathContext027");
            }
            bigDec = new BigDecimal(number, mc);
            System.out.println("---Test FAILED---");
            fail(msgRaise + exp);
        } catch (ArithmeticException e) {
            if (log) {
                System.out.println("-> expected exception: " + exp);
                System.out.println("-> actual: " + e);
                System.out.println("---Test PASSED---");
            }
        }
    }

    public final void testNegateMathContext028() {
        ArithmeticException exp = new ArithmeticException();
        MathContext mc = new MathContext("precision=2 roundingMode=UNNECESSARY");
        String number = "7.2586537749517493218249524019284663196844435776470094666549671637973969233791049737667834871811874414637466418433937382428E+1152516189";
        try {
            if (log) {
                System.out.println("testNegateMathContext028");
            }
            bigDec = new BigDecimal(number, mc);
            System.out.println("---Test FAILED---");
            fail(msgRaise + exp);
        } catch (ArithmeticException e) {
            if (log) {
                System.out.println("-> expected exception: " + exp);
                System.out.println("-> actual: " + e);
                System.out.println("---Test PASSED---");
            }
        }
    }

    public final void testNegateMathContext029() {
        ArithmeticException exp = new ArithmeticException();
        MathContext mc = new MathContext("precision=5 roundingMode=UNNECESSARY");
        String number = "-7.2586537749517493218249524019284663196844435776470094666549671637973969233791049737667834871811874418657482163218696177929E+1152131440";
        try {
            if (log) {
                System.out.println("testNegateMathContext029");
            }
            bigDec = new BigDecimal(number, mc);
            System.out.println("---Test FAILED---");
            fail(msgRaise + exp);
        } catch (ArithmeticException e) {
            if (log) {
                System.out.println("-> expected exception: " + exp);
                System.out.println("-> actual: " + e);
                System.out.println("---Test PASSED---");
            }
        }
    }

    public final void testNegateMathContext030() {
        ArithmeticException exp = new ArithmeticException();
        MathContext mc = new MathContext("precision=4 roundingMode=UNNECESSARY");
        String number = "2.69607140212493546239212517785914463302565047169746065904327351798189028582652470454194815238158390682939160982754624563304E+1154439934";
        try {
            if (log) {
                System.out.println("testNegateMathContext030");
            }
            bigDec = new BigDecimal(number, mc);
            System.out.println("---Test FAILED---");
            fail(msgRaise + exp);
        } catch (ArithmeticException e) {
            if (log) {
                System.out.println("-> expected exception: " + exp);
                System.out.println("-> actual: " + e);
                System.out.println("---Test PASSED---");
            }
        }
    }

    /*
     * Test method for 'java.math.BigDecimal.plus()'
     */
    public final void testPlus001() {
        String number = "3.64592186229992711301002975743099229535390495718812793251750626E+1124793376";
        if (log) {
            System.out.println("testPlus001");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 3.64592186229992711301002975743099229535390495718812793251750626E+1124793376");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "3.64592186229992711301002975743099229535390495718812793251750626E+1124793376",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }

    }

    public final void testPlus002() {
        String number = "3.64592186229992711301002975743099229535390495718812793251750626E-1124793252";
        if (log) {
            System.out.println("testPlus002");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 3.64592186229992711301002975743099229535390495718812793251750626E-1124793252");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "3.64592186229992711301002975743099229535390495718812793251750626E-1124793252",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlus003() {
        String number = "3.64592186229992711301002975743099229535390495718812875176146421E-1124408503";
        if (log) {
            System.out.println("testPlus003");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 3.64592186229992711301002975743099229535390495718812875176146421E-1124408503");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "3.64592186229992711301002975743099229535390495718812875176146421E-1124408503",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlus004() {
        String number = "-3.95243741709505372870556323261324386000755183411987320118048245E-1124408503";
        if (log) {
            System.out.println("testPlus004");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -3.95243741709505372870556323261324386000755183411987320118048245E-1124408503");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "-3.95243741709505372870556323261324386000755183411987320118048245E-1124408503",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlus005() {
        String number = "-3.95243741709505372870556323261324386000755183411987320118048245E+1124408627";
        if (log) {
            System.out.println("testPlus005");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -3.95243741709505372870556323261324386000755183411987320118048245E+1124408627");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "-3.95243741709505372870556323261324386000755183411987320118048245E+1124408627",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlus006() {
        String number = "-3.95243741709505372870556323261324386000755183411987320118048245E-1124408503";
        if (log) {
            System.out.println("testPlus006");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -3.95243741709505372870556323261324386000755183411987320118048245E-1124408503");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "-3.95243741709505372870556323261324386000755183411987320118048245E-1124408503",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlus007() {
        String number = "-3.95243741709505372870556323261324386000755183411987320118048245E+1124408627";
        if (log) {
            System.out.println("testPlus007");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -3.95243741709505372870556323261324386000755183411987320118048245E+1124408627");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "-3.95243741709505372870556323261324386000755183411987320118048245E+1124408627",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlus008() {
        String number = "3.95243741709505372870556323261324386000755183411987320118048245E-1124408503";
        if (log) {
            System.out.println("testPlus008");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 3.95243741709505372870556323261324386000755183411987320118048245E-1124408503");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "3.95243741709505372870556323261324386000755183411987320118048245E-1124408503",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlus009() {
        String number = "3.95243741709505372870556323261324386000755171837559666025780565E-1122484759";
        if (log) {
            System.out.println("testPlus009");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 3.95243741709505372870556323261324386000755171837559666025780565E-1122484759");
            System.out.println("-> actual result: " + bigDec.plus());
            assertEquals(
                    msgNotSame,
                    "3.95243741709505372870556323261324386000755171837559666025780565E-1122484759",
                    bigDec.plus().toString());
            if (log) {
                System.out.println("---Test PASSED---");
            }
        }
    }

    public final void testPlus010() {
        String number = "1.37125379776767170179580765213112542081894655469464988612384085E-1122484759";
        if (log) {
            System.out.println("testPlus010");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 1.37125379776767170179580765213112542081894655469464988612384085E-1122484759");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "1.37125379776767170179580765213112542081894655469464988612384085E-1122484759",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlus011() {
        String number = "1.37125379776767170179580765213112542081894655469464988612384085E+1122484883";
        if (log) {
            System.out.println("testPlus011");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 1.37125379776767170179580765213112542081894655469464988612384085E+1122484883");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "1.37125379776767170179580765213112542081894655469464988612384085E+1122484883",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlus012() {
        String number = "1.37125379776767170179580765213112542081894655469464988612384085E+1122484883";
        if (log) {
            System.out.println("testPlus012");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 1.37125379776767170179580765213112542081894655469464988612384085E+1122484883");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "1.37125379776767170179580765213112542081894655469464988612384085E+1122484883",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlus013() {
        String number = "1.37125379776767170179580765213112542081894655469464988612384085E+1122484883";
        if (log) {
            System.out.println("testPlus013");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 1.37125379776767170179580765213112542081894655469464988612384085E+1122484883");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "1.37125379776767170179580765213112542081894655469464988612384085E+1122484883",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlus014() {
        String number = "-1.37125379776767170179580765213112542081894655469464988612384085E-1122484759";
        if (log) {
            System.out.println("testPlus014");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -1.37125379776767170179580765213112542081894655469464988612384085E-1122484759");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "-1.37125379776767170179580765213112542081894655469464988612384085E-1122484759",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlus015() {
        String number = "-1.37125379776767170179580765213112542081894655469464988612388968E+1122100134";
        if (log) {
            System.out.println("testPlus015");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -1.37125379776767170179580765213112542081894655469464988612388968E+1122100134");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "-1.37125379776767170179580765213112542081894655469464988612388968E+1122100134",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlus016() {
        String number = "1.67776935256279831749134112731337698547259343162639515478681704E-1122100010";
        if (log) {
            System.out.println("testPlus016");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 1.67776935256279831749134112731337698547259343162639515478681704E-1122100010");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "1.67776935256279831749134112731337698547259343162639515478681704E-1122100010",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlus017() {
        String number = "-1.67776935256279831749134112731337698547259343162639515478681704E-1122100010";
        if (log) {
            System.out.println("testPlus017");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -1.67776935256279831749134112731337698547259343162639515478681704E-1122100010");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "-1.67776935256279831749134112731337698547259343162639515478681704E-1122100010",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlus018() {
        String number = "1.67776935256279831749134112731337698547259343162639515478681704E+1122100134";
        if (log) {
            System.out.println("testPlus018");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 1.67776935256279831749134112731337698547259343162639515478681704E+1122100134");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "1.67776935256279831749134112731337698547259343162639515478681704E+1122100134",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlus019() {
        String number = "1.67776935256279831749134112731337698547259343162639515478681704E-1122100010";
        if (log) {
            System.out.println("testPlus019");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 1.67776935256279831749134112731337698547259343162639515478681704E-1122100010");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "1.67776935256279831749134112731337698547259343162639515478681704E-1122100010",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlus020() {
        String number = "1.67776935256279831749134112731337698547259343162639515478681704E-1122100010";
        if (log) {
            System.out.println("testPlus020");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 1.67776935256279831749134112731337698547259343162639515478681704E-1122100010");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "1.67776935256279831749134112731337698547259343162639515478681704E-1122100010",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlus021() {
        String number = "1.67776935256279831749134112731337698547259343162639515478681704E-1122100010";
        if (log) {
            System.out.println("testPlus021");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 1.67776935256279831749134112731337698547259343162639515478681704E-1122100010");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "1.67776935256279831749134112731337698547259343162639515478681704E-1122100010",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }

    }

    public final void testPlus022() {
        String number = "7.4209029055662233273655472938860905126672401783475170307878446E-1123254257";
        if (log) {
            System.out.println("testPlus022");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 7.4209029055662233273655472938860905126672401783475170307878446E-1123254257");
            System.out.println("-> actual result: " + bigDec.plus());
            assertEquals(
                    msgNotSame,
                    "7.4209029055662233273655472938860905126672401783475170307878446E-1123254257",
                    bigDec.plus().toString());
            if (log) {
                System.out.println("---Test PASSED---");
            }
        }
    }

    public final void testPlus023() {
        String number = "7.4209029055662233273655472938860905126672401783475170307878446E-1123254257";
        if (log) {
            System.out.println("testPlus023");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 7.4209029055662233273655472938860905126672401783475170307878446E-1123254257");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "7.4209029055662233273655472938860905126672401783475170307878446E-1123254257",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }

    }

    public final void testPlus024() {
        String number = "7.4209029055662233273655472938860905126672401783475170307878446E+1123254379";
        if (log) {
            System.out.println("testPlus024");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 7.4209029055662233273655472938860905126672401783475170307878446E+1123254379");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "7.4209029055662233273655472938860905126672401783475170307878446E+1123254379",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }

    }

    public final void testPlus025() {
        String number = "-7.4209029055662233273655472938860905126672401783475170307878446E+1123254379";
        if (log) {
            System.out.println("testPlus025");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -7.4209029055662233273655472938860905126672401783475170307878446E+1123254379");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "-7.4209029055662233273655472938860905126672401783475170307878446E+1123254379",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }

    }

    public final void testPlus026() {
        String number = "-7.4209029055662233273655472938860905126672401783475170307878446E-1123254257";
        if (log) {
            System.out.println("testPlus026");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -7.4209029055662233273655472938860905126672401783475170307878446E-1123254257");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "-7.4209029055662233273655472938860905126672401783475170307878446E-1123254257",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }

    }

    public final void testPlus027() {
        String number = "7.4209029055662233273655472938860905126672401783475170307878446E-1123254257";
        if (log) {
            System.out.println("testPlus027");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 7.4209029055662233273655472938860905126672401783475170307878446E-1123254257");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "7.4209029055662233273655472938860905126672401783475170307878446E-1123254257",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlus028() {
        String number = "-7.4209029063174466317115712440617508200938623271802322867601986E-1122869509";
        if (log) {
            System.out.println("testPlus028");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -7.4209029063174466317115712440617508200938623271802322867601986E-1122869509");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "-7.4209029063174466317115712440617508200938623271802322867601986E-1122869509",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlus029() {
        String number = "-1.06473824297254508610027417694887385616529967776290461746086466E-1122869508";
        if (log) {
            System.out.println("testPlus029");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -1.06473824297254508610027417694887385616529967776290461746086466E-1122869508");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "-1.06473824297254508610027417694887385616529967776290461746086466E-1122869508",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }

    }

    public final void testPlus030() {
        String number = "1.06473824297254508610027417694887385616529967776290461746086466E-1122869508";
        if (log) {
            System.out.println("testPlus030");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 1.06473824297254508610027417694887385616529967776290461746086466E-1122869508");
            System.out.println("-> actual result: " + bigDec.plus());
        }
        assertEquals(
                msgNotSame,
                "1.06473824297254508610027417694887385616529967776290461746086466E-1122869508",
                bigDec.plus().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }

    }

    /*
     * Test method for 'java.math.BigDecimal.plus(MathContext)'
     */

    public final void testPlusMathContext001() {
        MathContext mc = new MathContext("precision=27599 roundingMode=FLOOR");
        String number = "-5.4839301217259748362823202442143416864788213685981842E-1911879226";
        if (log) {
            System.out.println("testPlusMathContext001");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -5.4839301217259748362823202442143416864788213685981842E-1911879226");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-5.4839301217259748362823202442143416864788213685981842E-1911879226",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext002() {
        MathContext mc = new MathContext("precision=12685 roundingMode=CEILING");
        String number = "-5.4839301217259748362823202442143416864788213685981842E-1911879226";
        if (log) {
            System.out.println("testPlusMathContext002");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -5.4839301217259748362823202442143416864788213685981842E-1911879226");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-5.4839301217259748362823202442143416864788213685981842E-1911879226",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext003() {
        MathContext mc = new MathContext(
                "precision=46820 roundingMode=UNNECESSARY");
        String number = "5.4839301217259748362823202442143416864788213685981861E-1912263975";
        if (log) {
            System.out.println("testPlusMathContext003");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: 5.4839301217259748362823202442143416864788213685981861E-1912263975");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "5.4839301217259748362823202442143416864788213685981861E-1912263975",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext004() {
        MathContext mc = new MathContext("precision=63324 roundingMode=FLOOR");
        String number = "-6.1975922608546975889491975362696327278699008617719205E-1912263975";
        if (log) {
            System.out.println("testPlusMathContext004");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -6.1975922608546975889491975362696327278699008617719205E-1912263975");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-6.1975922608546975889491975362696327278699008617719205E-1912263975",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext005() {
        MathContext mc = new MathContext(
                "precision=23697 roundingMode=UNNECESSARY");
        String number = "-6.1975922608546975889491975362696327278699008617719205E-1912263975";
        if (log) {
            System.out.println("testPlusMathContext005");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -6.1975922608546975889491975362696327278699008617719205E-1912263975");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-6.1975922608546975889491975362696327278699008617719205E-1912263975",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext006() {
        MathContext mc = new MathContext(
                "precision=10592 roundingMode=UNNECESSARY");
        String number = "6.1975922608546975889491975362696327278699008617719205E-1912263975";
        if (log) {
            System.out.println("testPlusMathContext006");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: 6.1975922608546975889491975362696327278699008617719205E-1912263975");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "6.1975922608546975889491975362696327278699008617719205E-1912263975",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext007() {
        MathContext mc = new MathContext(
                "precision=13745 roundingMode=UNNECESSARY");
        String number = "-6.1975922608546975889491975362696327278699008617719205E-1912263975";
        if (log) {
            System.out.println("testPlusMathContext007");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -6.1975922608546975889491975362696327278699008617719205E-1912263975");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-6.1975922608546975889491975362696327278699008617719205E-1912263975",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext008() {
        MathContext mc = new MathContext(
                "precision=21718 roundingMode=UNNECESSARY");
        String number = "6.1975922608546975889491975362696327278699008617719205E-1912263975";
        if (log) {
            System.out.println("testPlusMathContext008");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: 6.1975922608546975889491975362696327278699008617719205E-1912263975");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "6.1975922608546975889491975362696327278699008617719205E-1912263975",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext009() {
        MathContext mc = new MathContext("precision=38703 roundingMode=DOWN");
        String number = "6.1975922608546975889491975362696327278699008617719205E+1912264079";
        if (log) {
            System.out.println("testPlusMathContext009");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: 6.1975922608546975889491975362696327278699008617719205E+1912264079");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "6.1975922608546975889491975362696327278699008617719205E+1912264079",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext010() {
        MathContext mc = new MathContext("precision=34913 roundingMode=DOWN");
        String number = "6.1975922608546975889491975362696327278699008617719205E-1911109728";
        if (log) {
            System.out.println("testPlusMathContext010");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: 6.1975922608546975889491975362696327278699008617719205E-1911109728");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "6.1975922608546975889491975362696327278699008617719205E-1911109728",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext011() {
        MathContext mc = new MathContext(
                "precision=61349 roundingMode=UNNECESSARY");
        String number = "4.0566058434685293309485656601037596036966623822507116E-1911109728";
        if (log) {
            System.out.println("testPlusMathContext011");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: 4.0566058434685293309485656601037596036966623822507116E-1911109728");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "4.0566058434685293309485656601037596036966623822507116E-1911109728",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext012() {
        MathContext mc = new MathContext(
                "precision=41734 roundingMode=UNNECESSARY");
        String number = "4.0566058434685293309485656601037596036966623822507116E-1911109728";
        if (log) {
            System.out.println("testPlusMathContext012");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: 4.0566058434685293309485656601037596036966623822507116E-1911109728");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "4.0566058434685293309485656601037596036966623822507116E-1911109728",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext013() {
        MathContext mc = new MathContext(
                "precision=24224 roundingMode=UNNECESSARY");
        String number = "4.0566058434685293309485656601037596036966623822507116E+1911109832";
        if (log) {
            System.out.println("testPlusMathContext013");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: 4.0566058434685293309485656601037596036966623822507116E+1911109832");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "4.0566058434685293309485656601037596036966623822507116E+1911109832",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext014() {
        MathContext mc = new MathContext(
                "precision=88091 roundingMode=HALF_EVEN");
        String number = "4.0566058434685293309485656601037596036966623822507116E+1911109832";
        if (log) {
            System.out.println("testPlusMathContext014");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: 4.0566058434685293309485656601037596036966623822507116E+1911109832");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "4.0566058434685293309485656601037596036966623822507116E+1911109832",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext015() {
        MathContext mc = new MathContext(
                "precision=62125 roundingMode=HALF_EVEN");
        String number = "-4.0566058434685293309485656601037596036966623822507116E+1911109832";
        if (log) {
            System.out.println("testPlusMathContext015");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -4.0566058434685293309485656601037596036966623822507116E+1911109832");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-4.0566058434685293309485656601037596036966623822507116E+1911109832",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext016() {
        MathContext mc = new MathContext("precision=4992 roundingMode=UP");
        String number = "-4.0566058434685293309485656601037596036966623822507116E-1911109728";
        if (log) {
            System.out.println("testPlusMathContext016");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -4.0566058434685293309485656601037596036966623822507116E-1911109728");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-4.0566058434685293309485656601037596036966623822507116E-1911109728",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext017() {
        MathContext mc = new MathContext("precision=7769 roundingMode=UP");
        String number = "-4.0593935861995009042011706495258505843270962865209215E-1911494477";
        if (log) {
            System.out.println("testPlusMathContext017");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -4.0593935861995009042011706495258505843270962865209215E-1911494477");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-4.0593935861995009042011706495258505843270962865209215E-1911494477",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext018() {
        MathContext mc = new MathContext(
                "precision=16184 roundingMode=UNNECESSARY");
        String number = "-4.7702679825972520836154429521590506450877418754244479E+1911494581";
        if (log) {
            System.out.println("testPlusMathContext018");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -4.7702679825972520836154429521590506450877418754244479E+1911494581");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-4.7702679825972520836154429521590506450877418754244479E+1911494581",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext019() {
        MathContext mc = new MathContext(
                "precision=97551 roundingMode=UNNECESSARY");
        String number = "-4.7702679825972520836154429521590506450877418754244479E+1911494581";
        if (log) {
            System.out.println("testPlusMathContext019");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -4.7702679825972520836154429521590506450877418754244479E+1911494581");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-4.7702679825972520836154429521590506450877418754244479E+1911494581",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext020() {
        MathContext mc = new MathContext(
                "precision=33995 roundingMode=HALF_DOWN");
        String number = "4.7702679825972520836154429521590506450877418754244479E+1911494581";
        if (log) {
            System.out.println("testPlusMathContext020");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: 4.7702679825972520836154429521590506450877418754244479E+1911494581");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "4.7702679825972520836154429521590506450877418754244479E+1911494581",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext021() {
        MathContext mc = new MathContext(
                "precision=50109 roundingMode=HALF_DOWN");
        String number = "4.7702679825972520836154429521590506450877418754244479E+1911494581";
        if (log) {
            System.out.println("testPlusMathContext021");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: 4.7702679825972520836154429521590506450877418754244479E+1911494581");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "4.7702679825972520836154429521590506450877418754244479E+1911494581",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext022() {
        MathContext mc = new MathContext(
                "precision=46964 roundingMode=HALF_EVEN");
        String number = "4.7702679825972520836154429521590506450877418754244479E+1911494581";
        if (log) {
            System.out.println("testPlusMathContext022");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: 4.7702679825972520836154429521590506450877418754244479E+1911494581");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "4.7702679825972520836154429521590506450877418754244479E+1911494581",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext023() {
        MathContext mc = new MathContext("precision=34206 roundingMode=UP");
        String number = "4.7702679825972520836154429521590506450877418754244479E+1911494581";
        if (log) {
            System.out.println("testPlusMathContext023");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: 4.7702679825972520836154429521590506450877418754244479E+1911494581");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "4.7702679825972520836154429521590506450877418754244479E+1911494581",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext024() {
        MathContext mc = new MathContext(
                "precision=23264 roundingMode=HALF_EVEN");
        String number = "-4.7702679820637832769054947123027060118988987178304066E-1925730186";
        if (log) {
            System.out.println("testPlusMathContext024");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -4.7702679820637832769054947123027060118988987178304066E-1925730186");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-4.7702679820637832769054947123027060118988987178304066E-1925730186",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext025() {
        MathContext mc = new MathContext("precision=64452 roundingMode=DOWN");
        String number = "2.4790369043418790355796790145078530911479603447087682E+1925730290";
        if (log) {
            System.out.println("testPlusMathContext025");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: 2.4790369043418790355796790145078530911479603447087682E+1925730290");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "2.4790369043418790355796790145078530911479603447087682E+1925730290",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext026() {
        MathContext mc = new MathContext("precision=2453 roundingMode=HALF_UP");
        String number = "-2.4790369043418790355796790145078530911479603447087682E-1925730186";
        if (log) {
            System.out.println("testPlusMathContext026");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -2.4790369043418790355796790145078530911479603447087682E-1925730186");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-2.4790369043418790355796790145078530911479603447087682E-1925730186",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext027() {
        MathContext mc = new MathContext(
                "precision=63452 roundingMode=UNNECESSARY");
        String number = "-2.4790369043418790355796790145078530911479603447087682E-1925730186";
        if (log) {
            System.out.println("testPlusMathContext027");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -2.4790369043418790355796790145078530911479603447087682E-1925730186");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-2.4790369043418790355796790145078530911479603447087682E-1925730186",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext028() {
        MathContext mc = new MathContext(
                "precision=24761 roundingMode=UNNECESSARY");
        String number = "-2.4790369043418790355796790145078530911479603447087682E+1925730290";
        if (log) {
            System.out.println("testPlusMathContext028");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -2.4790369043418790355796790145078530911479603447087682E+1925730290");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-2.4790369043418790355796790145078530911479603447087682E+1925730290",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext029() {
        MathContext mc = new MathContext("precision=7212 roundingMode=UP");
        String number = "-2.4790369043418790355796790145078530911479603447087682E+1925730290";
        if (log) {
            System.out.println("testPlusMathContext029");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -2.4790369043418790355796790145078530911479603447087682E+1925730290");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-2.4790369043418790355796790145078530911479603447087682E+1925730290",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPlusMathContext030() {
        MathContext mc = new MathContext(
                "precision=51877 roundingMode=UNNECESSARY");
        String number = "-2.4790369043418790355796790145078530911479603447087682E+1925730290";
        if (log) {
            System.out.println("testPlusMathContext030");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -2.4790369043418790355796790145078530911479603447087682E+1925730290");
            System.out.println("-> actual result: " + bigDec.plus(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-2.4790369043418790355796790145078530911479603447087682E+1925730290",
                bigDec.plus(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    /*
     * Test method for 'java.math.BigDecimal.signum()'
     */

    public final void testSignum001() {
        String number = "-6.761009739114215551580942766839599339494437303751186E+404370721";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum001-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, -1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum002() {
        String number = "-6.761009739114215551580942766839599339494437303751186E+404370721";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum002-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, -1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum003() {
        String number = "-6.761009739114215551580942766839606284151029759111794E+406294465";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum003-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, -1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum004() {
        String number = "4.2819728347723365160012637523317462483464769590424178E-406294362";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum004-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, 1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum005() {
        String number = "-4.2819728347723365160012637523317462483464769590424178E+406294466";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum005-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, -1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum006() {
        String number = "-4.2819728347723365160012637523317462483464769590424178E+406294466";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum006-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, -1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum007() {
        String number = "-4.2819728347723365160012637523317462483464769590424178E+406294466";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum007-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, -1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum008() {
        String number = "-4.2819728347723365160012637523317462483464769590424178E-406294362";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum008-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, -1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum009() {
        String number = "4.2819728347723365160012637523317462483464769590424178E+406294466";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum009-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, 1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum010() {
        String number = "4.2819728347723365160012637523317462488833766793250181E+406679215";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum010-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, 1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum011() {
        String number = "-4.9956349739010592686681410443870372897375564522161541E+406679215";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum011-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, -1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum012() {
        String number = "-4.9956349739010592686681410443870372897375564522161541E-406679111";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum012-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, -1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum013() {
        String number = "-4.9956349739010592686681410443870372897375564522161541E+406679215";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum013-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, -1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum014() {
        String number = "-4.9956349739010592686681410443870372897375564522161541E+406679215";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum014-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, -1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum015() {
        String number = "4.9956349739010592686681410443870372897375564522161541E-406679111";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum015-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, 1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum016() {
        String number = "-4.9956349739010592686681410443870372897375564522161541E+406679215";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum016-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, -1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum017() {
        String number = "4.9956349739010592686676797189921307027239921617947467E+405524969";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum017-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, 1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum018() {
        String number = "2.8170873912975898131587261528498330581226822098963275E-405524865";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum018-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, 1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum019() {
        String number = "-2.8170873912975898131587261528498330581226822098963275E-405524865";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum019-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, -1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum020() {
        String number = "2.8170873912975898131587261528498330581226822098963275E-405524865";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum020-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, 1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum021() {
        String number = "-2.8170873912975898131587261528498330581226822098963275E+405524969";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum021-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, -1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum022() {
        String number = "2.8170873912975898131587261528498330581226822098963275E+405524969";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum022-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, 1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum023() {
        String number = "2.8170873912975898131587261528498330581226822098963275E-405524865";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum023-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, 1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum024() {
        String number = "3.5683106956436137633343864602764552069553974658686815E+405909717";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum024-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, 1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum025() {
        String number = "3.5683106956436137633343864602764552069553974658686815E+405909717";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum025-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, 1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum026() {
        String number = "3.5683106956436137633343864602764552069553974658686815E-405909613";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum026-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, 1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum027() {
        String number = "3.5683106956436137633343864602764552069553974658686815E-405909613";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum027-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, 1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum028() {
        String number = "3.5683106956436137633343864602764552069553974658686815E+405909717";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum028-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, 1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum029() {
        String number = "-3.5683106956436137633343864602764552069553974658686815E-405909613";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum029-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, -1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testSignum030() {
        String number = "3.5683106956436137633343864602764552069553974658686815E-405909613";
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("testSignum030-> signum = " + bigDec.signum());
        }
        assertEquals(msgNotSame, 1, bigDec.signum());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    /*
     * Test method for 'java.math.BigDecimal.precision()'
     */
    public final void testPrecision001() {
        String number = "-8.3012884240853882098547480702413478032371E-233598667";
        if (log) {
            System.out.println("testPrecision001");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 41");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 41, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision002() {
        String number = "-8.3012884240853882098547480702413478032371E+233598747";
        if (log) {
            System.out.println("testPrecision002");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 41");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 41, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision003() {
        String number = "-8.3012884240853882094158457735981690127878E+233213998";
        if (log) {
            System.out.println("testPrecision003");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 41");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 41, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision004() {
        String number = "2.049700845453182274038209400059592050182E-233213919";
        if (log) {
            System.out.println("testPrecision004");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 40");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 40, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision005() {
        String number = "-2.049700845453182274038209400059592050182E+233213997";
        if (log) {
            System.out.println("testPrecision005");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 40");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 40, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision006() {
        String number = "-2.049700845453182274038209400059592050182E-233213919";
        if (log) {
            System.out.println("testPrecision006");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 40");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 40, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision007() {
        String number = "2.049700845453182274038209400059592050182E+233213997";
        if (log) {
            System.out.println("testPrecision007");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 40");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 40, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision008() {
        String number = "2.049700845453182274038209400059592050182E+233213997";
        if (log) {
            System.out.println("testPrecision008");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 40");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 40, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision009() {
        String number = "2.049700845453182274038209400059592050182E+233213997";
        if (log) {
            System.out.println("testPrecision009");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 40");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 40, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision010() {
        String number = "2.049700845453182274038209400059592050182E+233213997";
        if (log) {
            System.out.println("testPrecision010");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 40");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 40, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision011() {
        String number = "2.049700845453182274038209400059592050182E-233213919";
        if (log) {
            System.out.println("testPrecision011");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 40");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 40, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision012() {
        String number = "2.049700853088912948354132383192674887270E+231290253";
        if (log) {
            System.out.println("testPrecision012");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 40");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 40, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision013() {
        String number = "-3.4844914372704098658649559801013064853094E+231290254";
        if (log) {
            System.out.println("testPrecision013");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 41");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 41, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision014() {
        String number = "3.4844914372704098658649559801013064853094E+231290254";
        if (log) {
            System.out.println("testPrecision014");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 41");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 41, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision015() {
        String number = "-3.4844914372704098658649559801013064853094E+231290254";
        if (log) {
            System.out.println("testPrecision015");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 41");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 41, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision016() {
        String number = "-3.4844914372704098658649559801013064853094E+231290254";
        if (log) {
            System.out.println("testPrecision016");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 41");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 41, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision017() {
        String number = "-3.4844914372704098658649559801013064853094E-231290174";
        if (log) {
            System.out.println("testPrecision017");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 41");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 41, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision018() {
        String number = "-3.4844914372704098658649559801013064853094E+231290254";
        if (log) {
            System.out.println("testPrecision018");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 41");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 41, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision019() {
        String number = "-3.4844914372704098658649559801013064853094E-231290174";
        if (log) {
            System.out.println("testPrecision019");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 41");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 41, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision020() {
        String number = "-3.4844914372704098658649559801013066103161E+230905505";
        if (log) {
            System.out.println("testPrecision020");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 41");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 41, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision021() {
        String number = "4.1335633716639175859770556234535106345337E-230905425";
        if (log) {
            System.out.println("testPrecision021");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 41");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 41, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision022() {
        String number = "4.1335633716639175859770556234535106345337E+230905505";
        if (log) {
            System.out.println("testPrecision022");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 41");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 41, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision023() {
        String number = "4.1335633716639175859770556234535106345337E-230905425";
        if (log) {
            System.out.println("testPrecision023");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 41");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 41, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision024() {
        String number = "-4.1335633716639175859770556234535106345337E+230905505";
        if (log) {
            System.out.println("testPrecision024");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 41");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 41, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision025() {
        String number = "4.1335633716639175859770556234535106345337E+230905505";
        if (log) {
            System.out.println("testPrecision025");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 41");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 41, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision026() {
        String number = "4.1335633716639175859770556234535106345337E-230905425";
        if (log) {
            System.out.println("testPrecision026");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 41");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 41, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision027() {
        String number = "-4.1335633716639175859770556234535106345337E+230905505";
        if (log) {
            System.out.println("testPrecision027");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 41");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 41, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision028() {
        String number = "4.1335633716639175859770556234535106345337E-230905425";
        if (log) {
            System.out.println("testPrecision028");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 41");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 41, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision029() {
        String number = "2.1863475684833944256407566933968981868608E+232059752";
        if (log) {
            System.out.println("testPrecision029");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 41");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 41, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testPrecision030() {
        String number = "-2.1863475684833944256407566933968981868608E-232059672";
        if (log) {
            System.out.println("testPrecision030");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 41");
            System.out.println("-> actual result: " + bigDec.precision());
        }
        assertEquals(msgNotSame, 41, bigDec.precision());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    /*
     * Test method for 'java.math.BigDecimal.round(MathContext)'
     */
    public final void testRoundMathContext001() {
        MathContext mc = new MathContext(
                "precision=879 roundingMode=UNNECESSARY");
        String number = "-4.03770397207700551995025415928025498243323774391228998534690422416321282807357060405E-38561506";
        if (log) {
            System.out.println("testRoundMathContext001");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -4.03770397207700551995025415928025498243323774391228998534690422416321282807357060405E-38561506");
            System.out.println("-> actual result: " + bigDec.round(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-4.03770397207700551995025415928025498243323774391228998534690422416321282807357060405E-38561506",
                bigDec.round(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testRoundMathContext002() {
        MathContext mc = new MathContext(
                "precision=897 roundingMode=UNNECESSARY");
        String number = "-4.03770397207700551995025415928025498243323774391228998534690422416321282807357060405E-38561506";
        if (log) {
            System.out.println("testRoundMathContext002");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -4.03770397207700551995025415928025498243323774391228998534690422416321282807357060405E-38561506");
            System.out.println("-> actual result: " + bigDec.round(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-4.03770397207700551995025415928025498243323774391228998534690422416321282807357060405E-38561506",
                bigDec.round(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testRoundMathContext003() {
        ArithmeticException exp = new ArithmeticException();
        MathContext mc = new MathContext(
                "precision=23 roundingMode=UNNECESSARY");
        String number = "4.03770397207700551995025415928025498243323774391228998534690422416321282807357060405E-38561506";
        try {
            if (log) {
                System.out.println("testRoundMathContext003");
            }
            bigDec = new BigDecimal(number, mc);
            System.out.println("---Test FAILED---");
            fail(msgRaise + exp);
        } catch (ArithmeticException e) {
            if (log) {
                System.out.println("-> expected exception: " + exp);
                System.out.println("-> actual: " + e);
                System.out.println("---Test PASSED---");
            }
        }
    }

    public final void testRoundMathContext004() {
        MathContext mc = new MathContext(
                "precision=99 roundingMode=UNNECESSARY");
        String number = "-4.03770397207700551995025415928025498243323774391228998534690422416321282807357060405E-38561506";
        if (log) {
            System.out.println("testRoundMathContext004");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -4.03770397207700551995025415928025498243323774391228998534690422416321282807357060405E-38561506");
            System.out.println("-> actual result: " + bigDec.round(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-4.03770397207700551995025415928025498243323774391228998534690422416321282807357060405E-38561506",
                bigDec.round(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testRoundMathContext005() {
        MathContext mc = new MathContext(
                "precision=249 roundingMode=UNNECESSARY");
        String number = "-4.03770397207700551995025541962381910713149307094138201763129835922438442242089240905E+38176924";
        if (log) {
            System.out.println("testRoundMathContext005");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -4.03770397207700551995025541962381910713149307094138201763129835922438442242089240905E+38176924");
            System.out.println("-> actual result: " + bigDec.round(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-4.03770397207700551995025541962381910713149307094138201763129835922438442242089240905E+38176924",
                bigDec.round(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testRoundMathContext006() {
        MathContext mc = new MathContext(
                "precision=763 roundingMode=UNNECESSARY");
        String number = "-5.56136584833247930106355761561242667391747840199239941377969827101725540470510668105E+38176924";
        if (log) {
            System.out.println("testRoundMathContext006");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -5.56136584833247930106355761561242667391747840199239941377969827101725540470510668105E+38176924");
            System.out.println("-> actual result: " + bigDec.round(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-5.56136584833247930106355761561242667391747840199239941377969827101725540470510668105E+38176924",
                bigDec.round(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testRoundMathContext007() {
        MathContext mc = new MathContext(
                "precision=680 roundingMode=UNNECESSARY");
        String number = "5.56136584833247930106355761561242667391747840199239941377969827101725540470510668105E+38176924";
        if (log) {
            System.out.println("testRoundMathContext007");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: 5.56136584833247930106355761561242667391747840199239941377969827101725540470510668105E+38176924");
            System.out.println("-> actual result: " + bigDec.round(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "5.56136584833247930106355761561242667391747840199239941377969827101725540470510668105E+38176924",
                bigDec.round(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testRoundMathContext008() {
        MathContext mc = new MathContext(
                "precision=720 roundingMode=UNNECESSARY");
        String number = "-5.56136584833247930106355761561242667391747840199239941377969827101725540470510668105E+38176924";
        if (log) {
            System.out.println("testRoundMathContext008");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -5.56136584833247930106355761561242667391747840199239941377969827101725540470510668105E+38176924");
            System.out.println("-> actual result: " + bigDec.round(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-5.56136584833247930106355761561242667391747840199239941377969827101725540470510668105E+38176924",
                bigDec.round(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testRoundMathContext009() {
        MathContext mc = new MathContext(
                "precision=473 roundingMode=UNNECESSARY");
        String number = "5.56136584833247930106355396061609071229253795360803252015495527933985778109787344655E+39331170";
        if (log) {
            System.out.println("testRoundMathContext009");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: 5.56136584833247930106355396061609071229253795360803252015495527933985778109787344655E+39331170");
            System.out.println("-> actual result: " + bigDec.round(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "5.56136584833247930106355396061609071229253795360803252015495527933985778109787344655E+39331170",
                bigDec.round(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testRoundMathContext010() {
        MathContext mc = new MathContext(
                "precision=411 roundingMode=UNNECESSARY");
        String number = "1.14274640719160533583497759224912876861318049356008207132459553514053193247365205775E-39331004";
        if (log) {
            System.out.println("testRoundMathContext010");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: 1.14274640719160533583497759224912876861318049356008207132459553514053193247365205775E-39331004");
            System.out.println("-> actual result: " + bigDec.round(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "1.14274640719160533583497759224912876861318049356008207132459553514053193247365205775E-39331004",
                bigDec.round(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testRoundMathContext011() {
        MathContext mc = new MathContext(
                "precision=893 roundingMode=UNNECESSARY");
        String number = "1.14274640719160533583497759224912876861318049356008207132459553514053193247365205775E+39331170";
        if (log) {
            System.out.println("testRoundMathContext011");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: 1.14274640719160533583497759224912876861318049356008207132459553514053193247365205775E+39331170");
            System.out.println("-> actual result: " + bigDec.round(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "1.14274640719160533583497759224912876861318049356008207132459553514053193247365205775E+39331170",
                bigDec.round(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testRoundMathContext012() {
        MathContext mc = new MathContext(
                "precision=931 roundingMode=UNNECESSARY");
        String number = "-1.14274640719160533583497759224912876861318049356008207132459553514053193247365205775E+39331170";
        if (log) {
            System.out.println("testRoundMathContext012");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -1.14274640719160533583497759224912876861318049356008207132459553514053193247365205775E+39331170");
            System.out.println("-> actual result: " + bigDec.round(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-1.14274640719160533583497759224912876861318049356008207132459553514053193247365205775E+39331170",
                bigDec.round(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testRoundMathContext013() {
        MathContext mc = new MathContext(
                "precision=360 roundingMode=UNNECESSARY");
        String number = "-1.14274640719160533583497759224912876861318049356008207132459553514053193247365205775E-39331004";
        if (log) {
            System.out.println("testRoundMathContext013");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -1.14274640719160533583497759224912876861318049356008207132459553514053193247365205775E-39331004");
            System.out.println("-> actual result: " + bigDec.round(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-1.14274640719160533583497759224912876861318049356008207132459553514053193247365205775E-39331004",
                bigDec.round(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testRoundMathContext014() {
        MathContext mc = new MathContext(
                "precision=478 roundingMode=UNNECESSARY");
        String number = "2.59022518963430542789261587576469187552320911873618602833574987965187238027361133090E+38946421";
        if (log) {
            System.out.println("testRoundMathContext014");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: 2.59022518963430542789261587576469187552320911873618602833574987965187238027361133090E+38946421");
            System.out.println("-> actual result: " + bigDec.round(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "2.59022518963430542789261587576469187552320911873618602833574987965187238027361133090E+38946421",
                bigDec.round(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testRoundMathContext015() {
        ArithmeticException exp = new ArithmeticException();
        MathContext mc = new MathContext("precision=7 roundingMode=UNNECESSARY");
        String number = "-4.66835005629961014334360754862972216641641557015940853358475155941272984568663814832501862078930023605030274758E-282602840";
        try {
            if (log) {
                System.out.println("testRoundMathContext015");
            }
            bigDec = new BigDecimal(number, mc);
            System.out.println("---Test FAILED---");
            fail(msgRaise + exp);
        } catch (ArithmeticException e) {
            if (log) {
                System.out.println("-> expected exception: " + exp);
                System.out.println("-> actual: " + e);
                System.out.println("---Test PASSED---");
            }
        }
    }

    public final void testRoundMathContext016() {
        ArithmeticException exp = new ArithmeticException();
        MathContext mc = new MathContext(
                "precision=33 roundingMode=UNNECESSARY");
        String number = "4.66835005629961014334360754862972216641641557015940853358475155941272984568663753746656467551546158540367578051E-292991061";
        try {
            if (log) {
                System.out.println("testRoundMathContext016");
            }
            bigDec = new BigDecimal(number, mc);
            System.out.println("---Test FAILED---");
            fail(msgRaise + exp);
        } catch (ArithmeticException e) {
            if (log) {
                System.out.println("-> expected exception: " + exp);
                System.out.println("-> actual: " + e);
                System.out.println("---Test PASSED---");
            }
        }
    }

    public final void testRoundMathContext017() {
        ArithmeticException exp = new ArithmeticException();
        MathContext mc = new MathContext(
                "precision=80 roundingMode=UNNECESSARY");
        String number = "4.59761747968900998965658319183230213359192442515699325277286138427011272681259817638070015683794720217075270595E+292991281";
        try {
            if (log) {
                System.out.println("testRoundMathContext017");
            }
            bigDec = new BigDecimal(number, mc);
            System.out.println("---Test FAILED---");
            fail(msgRaise + exp);
        } catch (ArithmeticException e) {
            if (log) {
                System.out.println("-> expected exception: " + exp);
                System.out.println("-> actual: " + e);
                System.out.println("---Test PASSED---");
            }
        }
    }

    public final void testRoundMathContext018() {
        MathContext mc = new MathContext(
                "precision=132 roundingMode=UNNECESSARY");
        String number = "4.59761747968900998965658319183230213359192442515699325277286138427011272681259817638070015683794720217075270595E+292991281";
        if (log) {
            System.out.println("testRoundMathContext018");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: 4.59761747968900998965658319183230213359192442515699325277286138427011272681259817638070015683794720217075270595E+292991281");
            System.out.println("-> actual result: " + bigDec.round(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "4.59761747968900998965658319183230213359192442515699325277286138427011272681259817638070015683794720217075270595E+292991281",
                bigDec.round(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testRoundMathContext019() {
        ArithmeticException exp = new ArithmeticException();
        MathContext mc = new MathContext(
                "precision=50 roundingMode=UNNECESSARY");
        String number = "-4.59761747968900998965658319183230213359192442515699325277286138427011272681259817638070015683794720217075270595E-292991061";
        try {
            if (log) {
                System.out.println("testRoundMathContext019");
            }
            bigDec = new BigDecimal(number, mc);
            System.out.println("---Test FAILED---");
            fail(msgRaise + exp);
        } catch (ArithmeticException e) {
            if (log) {
                System.out.println("-> expected exception: " + exp);
                System.out.println("-> actual: " + e);
                System.out.println("---Test PASSED---");
            }
        }
    }

    public final void testRoundMathContext020() {
        ArithmeticException exp = new ArithmeticException();
        MathContext mc = new MathContext(
                "precision=36 roundingMode=UNNECESSARY");
        String number = "4.59761747968900998965658319183230221986838307256906043111986982211600485277846910372072826466908822767465322198E+292606532";
        try {
            if (log) {
                System.out.println("testRoundMathContext020");
            }
            bigDec = new BigDecimal(number, mc);
            System.out.println("---Test FAILED---");
            fail(msgRaise + exp);
        } catch (ArithmeticException e) {
            if (log) {
                System.out.println("-> expected exception: " + exp);
                System.out.println("-> actual: " + e);
                System.out.println("---Test PASSED---");
            }
        }
    }

    public final void testRoundMathContext021() {
        ArithmeticException exp = new ArithmeticException();
        MathContext mc = new MathContext(
                "precision=62 roundingMode=UNNECESSARY");
        String number = "5.04559046488947762967440411821596234148036834350562336458149916017335447968151799869471709519651641674123630294E+292606532";
        try {
            if (log) {
                System.out.println("testRoundMathContext021");
            }
            bigDec = new BigDecimal(number, mc);
            System.out.println("---Test FAILED---");
            fail(msgRaise + exp);
        } catch (ArithmeticException e) {
            if (log) {
                System.out.println("-> expected exception: " + exp);
                System.out.println("-> actual: " + e);
                System.out.println("---Test PASSED---");
            }
        }
    }

    public final void testRoundMathContext022() {
        ArithmeticException exp = new ArithmeticException();
        MathContext mc = new MathContext(
                "precision=110 roundingMode=UNNECESSARY");
        String number = "5.04559046488947762967440411821596234148036834350562336458149916017335447968151799869471709519651641674123630294E-292606312";
        try {
            if (log) {
                System.out.println("testRoundMathContext022");
            }
            bigDec = new BigDecimal(number, mc);
            System.out.println("---Test FAILED---");
            fail(msgRaise + exp);
        } catch (ArithmeticException e) {
            if (log) {
                System.out.println("-> expected exception: " + exp);
                System.out.println("-> actual: " + e);
                System.out.println("---Test PASSED---");
            }
        }
    }

    public final void testRoundMathContext023() {
        ArithmeticException exp = new ArithmeticException();
        MathContext mc = new MathContext(
                "precision=44 roundingMode=UNNECESSARY");
        String number = "-5.04559046488947762967440411821596234148036834350562336458149916017335447968151799869471709519651641674123630294E+293760778";
        try {
            if (log) {
                System.out.println("testRoundMathContext023");
            }
            bigDec = new BigDecimal(number, mc);
            System.out.println("---Test FAILED---");
            fail(msgRaise + exp);
        } catch (ArithmeticException e) {
            if (log) {
                System.out.println("-> expected exception: " + exp);
                System.out.println("-> actual: " + e);
                System.out.println("---Test PASSED---");
            }
        }
    }

    public final void testRoundMathContext024() {
        ArithmeticException exp = new ArithmeticException();
        MathContext mc = new MathContext(
                "precision=94 roundingMode=UNNECESSARY");
        String number = "-3.67809398375120799172526655346584170687353954012559460221828910741609018145007854110456012547035776173660216476E+293760778";
        try {
            if (log) {
                System.out.println("testRoundMathContext024");
            }
            bigDec = new BigDecimal(number, mc);
            System.out.println("---Test FAILED---");
            fail(msgRaise + exp);
        } catch (ArithmeticException e) {
            if (log) {
                System.out.println("-> expected exception: " + exp);
                System.out.println("-> actual: " + e);
                System.out.println("---Test PASSED---");
            }
        }
    }

    public final void testRoundMathContext025() {
        ArithmeticException exp = new ArithmeticException();
        MathContext mc = new MathContext(
                "precision=85 roundingMode=UNNECESSARY");
        String number = "3.67809398375120799172526655346584170687353954012559460221828910741609018145007854110456012547035776173660216476E-293760558";
        try {
            if (log) {
                System.out.println("testRoundMathContext025");
            }
            bigDec = new BigDecimal(number, mc);
            System.out.println("---Test FAILED---");
            fail(msgRaise + exp);
        } catch (ArithmeticException e) {
            if (log) {
                System.out.println("-> expected exception: " + exp);
                System.out.println("-> actual: " + e);
                System.out.println("---Test PASSED---");
            }
        }
    }

    public final void testRoundMathContext026() {
        ArithmeticException exp = new ArithmeticException();
        MathContext mc = new MathContext(
                "precision=61 roundingMode=UNNECESSARY");
        String number = "3.67809398375120799172526655346584170687353954012559460221828910741609018145007854110456012547035776173660216476E+293760778";
        try {
            if (log) {
                System.out.println("testRoundMathContext026");
            }
            bigDec = new BigDecimal(number, mc);
            System.out.println("---Test FAILED---");
            fail(msgRaise + exp);
        } catch (ArithmeticException e) {
            if (log) {
                System.out.println("-> expected exception: " + exp);
                System.out.println("-> actual: " + e);
                System.out.println("---Test PASSED---");
            }
        }
    }

    public final void testRoundMathContext027() {
        ArithmeticException exp = new ArithmeticException();
        MathContext mc = new MathContext(
                "precision=51 roundingMode=UNNECESSARY");
        String number = "-3.67809398375120799172526655346584170687353954012559460221828910742056782127033253288827239517843460922329313456E+293376030";
        try {
            if (log) {
                System.out.println("testRoundMathContext027");
            }
            bigDec = new BigDecimal(number, mc);
            System.out.println("---Test FAILED---");
            fail(msgRaise + exp);
        } catch (ArithmeticException e) {
            if (log) {
                System.out.println("-> expected exception: " + exp);
                System.out.println("-> actual: " + e);
                System.out.println("---Test PASSED---");
            }
        }
    }

    public final void testRoundMathContext028() {
        ArithmeticException exp = new ArithmeticException();
        MathContext mc = new MathContext(
                "precision=11 roundingMode=UNNECESSARY");
        String number = "4.14964449448854234963876226544864192570348050680836314096422360836687097394367835406668321847937798760026910896E-293375810";
        try {
            if (log) {
                System.out.println("testRoundMathContext028");
            }
            bigDec = new BigDecimal(number, mc);
            System.out.println("---Test FAILED---");
            fail(msgRaise + exp);
        } catch (ArithmeticException e) {
            if (log) {
                System.out.println("-> expected exception: " + exp);
                System.out.println("-> actual: " + e);
                System.out.println("---Test PASSED---");
            }
        }
    }

    public final void testRoundMathContext029() {
        ArithmeticException exp = new ArithmeticException();
        MathContext mc = new MathContext(
                "precision=13 roundingMode=UNNECESSARY");
        String number = "4.14964449448854234963876226544864192570348050680836314096422360836687097394367835406668321847892303573202964239E+291452285";
        try {
            if (log) {
                System.out.println("testRoundMathContext029");
            }
            bigDec = new BigDecimal(number, mc);
            System.out.println("---Test FAILED---");
            fail(msgRaise + exp);
        } catch (ArithmeticException e) {
            if (log) {
                System.out.println("-> expected exception: " + exp);
                System.out.println("-> actual: " + e);
                System.out.println("---Test PASSED---");
            }
        }
    }

    public final void testRoundMathContext030() {
        MathContext mc = new MathContext(
                "precision=143 roundingMode=UNNECESSARY");
        String number = "-3.5366288305300076843512178398710016412245572509054438608700328933114216401685626027292592025129195817547211298E-291067317";
        if (log) {
            System.out.println("testRoundMathContext030");
        }
        bigDec = new BigDecimal(number, mc);
        if (log) {
            System.out
                    .println("-> expected result: -3.5366288305300076843512178398710016412245572509054438608700328933114216401685626027292592025129195817547211298E-291067317");
            System.out.println("-> actual result: " + bigDec.round(mc));
            System.out.println("-> mc: " + mc.toString());
        }
        assertEquals(
                msgNotSame,
                "-3.5366288305300076843512178398710016412245572509054438608700328933114216401685626027292592025129195817547211298E-291067317",
                bigDec.round(mc).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    /*
     * Test method for 'java.math.BigDecimal.movePointLeft(int)'
     */
    public final void testMovePointLeftInt001() {
        String number = "-4.123389851770370361E+541";
        if (log) {
            System.out.println("testMovePointLeftInt001");
        }
        bigDec = new BigDecimal(number);
        int n = 558;
        if (log) {
            System.out.println("-> expected result: -4.123389851770370361E-17");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(msgNotSame, "-4.123389851770370361E-17", bigDec
                .movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt002() {
        String number = "-4.123389851770370361E-132";
        if (log) {
            System.out.println("testMovePointLeftInt002");
        }
        bigDec = new BigDecimal(number);
        int n = -745;
        if (log) {
            System.out
                    .println("-> expected result: -41233898517703703610000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "-41233898517703703610000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt003() {
        String number = "4.123389851770370361E+750";
        if (log) {
            System.out.println("testMovePointLeftInt003");
        }
        bigDec = new BigDecimal(number);
        int n = -971;
        if (log) {
            System.out
                    .println("-> expected result: 412338985177037036100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "412338985177037036100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt004() {
        String number = "4.123389851770370361E-312";
        if (log) {
            System.out.println("testMovePointLeftInt004");
        }
        bigDec = new BigDecimal(number);
        int n = -728;
        if (log) {
            System.out
                    .println("-> expected result: 412338985177037036100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "412338985177037036100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt005() {
        String number = "4.123389851770370361E-360";
        if (log) {
            System.out.println("testMovePointLeftInt005");
        }
        bigDec = new BigDecimal(number);
        int n = 805;
        if (log) {
            System.out
                    .println("-> expected result: 4.123389851770370361E-1165");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(msgNotSame, "4.123389851770370361E-1165", bigDec
                .movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt006() {
        String number = "-4.123389851770370361E+741";
        if (log) {
            System.out.println("testMovePointLeftInt006");
        }
        bigDec = new BigDecimal(number);
        int n = -429;
        if (log) {
            System.out
                    .println("-> expected result: -4123389851770370361000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "-4123389851770370361000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt007() {
        String number = "4.123389851770370361E+976";
        if (log) {
            System.out.println("testMovePointLeftInt007");
        }
        bigDec = new BigDecimal(number);
        int n = -637;
        if (log) {
            System.out
                    .println("-> expected result: 412338985177037036100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "412338985177037036100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt008() {
        String number = "4.123410824415693900E+266";
        if (log) {
            System.out.println("testMovePointLeftInt008");
        }
        bigDec = new BigDecimal(number);
        int n = -727;
        if (log) {
            System.out
                    .println("-> expected result: 4123410824415693900000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "4123410824415693900000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt009() {
        String number = "5.497853135693827148E+726";
        if (log) {
            System.out.println("testMovePointLeftInt009");
        }
        bigDec = new BigDecimal(number);
        int n = 73;
        if (log) {
            System.out
                    .println("-> expected result: 549785313569382714800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "549785313569382714800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt010() {
        String number = "-5.497853135693827148E+19";
        if (log) {
            System.out.println("testMovePointLeftInt010");
        }
        bigDec = new BigDecimal(number);
        int n = 10;
        if (log) {
            System.out.println("-> expected result: -5497853135.693827148");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(msgNotSame, "-5497853135.693827148", bigDec.movePointLeft(
                n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt011() {
        String number = "5.497853135693827148E+27";
        if (log) {
            System.out.println("testMovePointLeftInt011");
        }
        bigDec = new BigDecimal(number);
        int n = -474;
        if (log) {
            System.out
                    .println("-> expected result: 5497853135693827148000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "5497853135693827148000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt012() {
        String number = "54978531356.93827148";
        if (log) {
            System.out.println("testMovePointLeftInt012");
        }
        bigDec = new BigDecimal(number);
        int n = -852;
        if (log) {
            System.out
                    .println("-> expected result: 54978531356938271480000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "54978531356938271480000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt013() {
        String number = "-5.497853135693827148E+137";
        if (log) {
            System.out.println("testMovePointLeftInt013");
        }
        bigDec = new BigDecimal(number);
        int n = -709;
        if (log) {
            System.out
                    .println("-> expected result: -5497853135693827148000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "-5497853135693827148000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt014() {
        String number = "-5.497853135693827148E-976";
        if (log) {
            System.out.println("testMovePointLeftInt014");
        }
        bigDec = new BigDecimal(number);
        int n = -59;
        if (log) {
            System.out
                    .println("-> expected result: -5.497853135693827148E-917");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(msgNotSame, "-5.497853135693827148E-917", bigDec
                .movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt015() {
        String number = "-5.497853135693827148E+671";
        if (log) {
            System.out.println("testMovePointLeftInt015");
        }
        bigDec = new BigDecimal(number);
        int n = 181;
        if (log) {
            System.out
                    .println("-> expected result: -54978531356938271480000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "-54978531356938271480000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt016() {
        String number = "5.497853135693827148E+414";
        if (log) {
            System.out.println("testMovePointLeftInt016");
        }
        bigDec = new BigDecimal(number);
        int n = 999;
        if (log) {
            System.out.println("-> expected result: 5.497853135693827148E-585");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(msgNotSame, "5.497853135693827148E-585", bigDec
                .movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt017() {
        String number = "-5.497853135693827148E+957";
        if (log) {
            System.out.println("testMovePointLeftInt017");
        }
        bigDec = new BigDecimal(number);
        int n = 714;
        if (log) {
            System.out
                    .println("-> expected result: -5497853135693827148000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "-5497853135693827148000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt018() {
        String number = "-5.497853135693827148E-435";
        if (log) {
            System.out.println("testMovePointLeftInt018");
        }
        bigDec = new BigDecimal(number);
        int n = -394;
        if (log) {
            System.out.println("-> expected result: -5.497853135693827148E-41");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(msgNotSame, "-5.497853135693827148E-41", bigDec
                .movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt019() {
        String number = "-5.497853135693827148E+255";
        if (log) {
            System.out.println("testMovePointLeftInt019");
        }
        bigDec = new BigDecimal(number);
        int n = 261;
        if (log) {
            System.out
                    .println("-> expected result: -0.000005497853135693827148");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(msgNotSame, "-0.000005497853135693827148", bigDec
                .movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt020() {
        String number = "-5.497853135693827148E+173";
        if (log) {
            System.out.println("testMovePointLeftInt020");
        }
        bigDec = new BigDecimal(number);
        int n = -775;
        if (log) {
            System.out
                    .println("-> expected result: -5497853135693827148000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "-5497853135693827148000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt021() {
        String number = "-5.497853135693827148E+801";
        if (log) {
            System.out.println("testMovePointLeftInt021");
        }
        bigDec = new BigDecimal(number);
        int n = -225;
        if (log) {
            System.out
                    .println("-> expected result: -5497853135693827148000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "-5497853135693827148000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt022() {
        String number = "5.497853135693827148E-107";
        if (log) {
            System.out.println("testMovePointLeftInt022");
        }
        bigDec = new BigDecimal(number);
        int n = -488;
        if (log) {
            System.out
                    .println("-> expected result: 5497853135693827148000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "5497853135693827148000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt023() {
        String number = "5.497853135693827148E+366";
        if (log) {
            System.out.println("testMovePointLeftInt023");
        }
        bigDec = new BigDecimal(number);
        int n = -220;
        if (log) {
            System.out
                    .println("-> expected result: 54978531356938271480000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "54978531356938271480000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt024() {
        String number = "-1.374463283923456787E+123";
        if (log) {
            System.out.println("testMovePointLeftInt024");
        }
        bigDec = new BigDecimal(number);
        int n = -814;
        if (log) {
            System.out
                    .println("-> expected result: -13744632839234567870000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "-13744632839234567870000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt025() {
        String number = "1.374463283923456787E+301";
        if (log) {
            System.out.println("testMovePointLeftInt025");
        }
        bigDec = new BigDecimal(number);
        int n = -402;
        if (log) {
            System.out
                    .println("-> expected result: 13744632839234567870000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "13744632839234567870000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt026() {
        String number = "-1.374463283923456787E-663";
        if (log) {
            System.out.println("testMovePointLeftInt026");
        }
        bigDec = new BigDecimal(number);
        int n = 42;
        if (log) {
            System.out
                    .println("-> expected result: -1.374463283923456787E-705");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(msgNotSame, "-1.374463283923456787E-705", bigDec
                .movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt027() {
        String number = "-1.374463283923456787E+797";
        if (log) {
            System.out.println("testMovePointLeftInt027");
        }
        bigDec = new BigDecimal(number);
        int n = -146;
        if (log) {
            System.out
                    .println("-> expected result: -13744632839234567870000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "-13744632839234567870000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt028() {
        String number = "1.374463283923456787E+771";
        if (log) {
            System.out.println("testMovePointLeftInt028");
        }
        bigDec = new BigDecimal(number);
        int n = 473;
        if (log) {
            System.out
                    .println("-> expected result: 13744632839234567870000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "13744632839234567870000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt029() {
        String number = "1.374463283923456787E+121";
        if (log) {
            System.out.println("testMovePointLeftInt029");
        }
        bigDec = new BigDecimal(number);
        int n = -726;
        if (log) {
            System.out
                    .println("-> expected result: 13744632839234567870000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "13744632839234567870000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointLeftInt030() {
        String number = "-1.374463283923456787E+298";
        if (log) {
            System.out.println("testMovePointLeftInt030");
        }
        bigDec = new BigDecimal(number);
        int n = -259;
        if (log) {
            System.out
                    .println("-> expected result: -137446328392345678700000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointLeft(n).toString());
        }
        assertEquals(
                msgNotSame,
                "-137446328392345678700000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointLeft(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    /*
     * Test method for 'java.math.BigDecimal.movePointRight(int)'
     */

    public final void testMovePointRightInt001() {
        String number = "-1.5263776468834178003E-798";
        if (log) {
            System.out.println("testMovePointRightInt001");
        }
        bigDec = new BigDecimal(number);
        int n = 894;
        if (log) {
            System.out
                    .println("-> expected result: -1526377646883417800300000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(
                msgNotSame,
                "-1526377646883417800300000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt002() {
        String number = "-1.5263776468834178003E+942";
        if (log) {
            System.out.println("testMovePointRightInt002");
        }
        bigDec = new BigDecimal(number);
        int n = 8;
        if (log) {
            System.out
                    .println("-> expected result: -152637764688341780030000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(
                msgNotSame,
                "-152637764688341780030000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt003() {
        String number = "-1.5263776468834178003E+931";
        if (log) {
            System.out.println("testMovePointRightInt003");
        }
        bigDec = new BigDecimal(number);
        int n = -306;
        if (log) {
            System.out
                    .println("-> expected result: -15263776468834178003000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(
                msgNotSame,
                "-15263776468834178003000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt004() {
        String number = "-1.5263776468834178003E+323";
        if (log) {
            System.out.println("testMovePointRightInt004");
        }
        bigDec = new BigDecimal(number);
        int n = 98;
        if (log) {
            System.out
                    .println("-> expected result: -15263776468834178003000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(
                msgNotSame,
                "-15263776468834178003000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt005() {
        String number = "-1.5263776468834178003E+828";
        if (log) {
            System.out.println("testMovePointRightInt005");
        }
        bigDec = new BigDecimal(number);
        int n = -939;
        if (log) {
            System.out
                    .println("-> expected result: -1.5263776468834178003E-111");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(msgNotSame, "-1.5263776468834178003E-111", bigDec
                .movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt006() {
        String number = "1.5263776468834178003E-522";
        if (log) {
            System.out.println("testMovePointRightInt006");
        }
        bigDec = new BigDecimal(number);
        int n = 364;
        if (log) {
            System.out
                    .println("-> expected result: 1.5263776468834178003E-158");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(msgNotSame, "1.5263776468834178003E-158", bigDec
                .movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt007() {
        String number = "-1.5263776468834178003E-953";
        if (log) {
            System.out.println("testMovePointRightInt007");
        }
        bigDec = new BigDecimal(number);
        int n = -602;
        if (log) {
            System.out
                    .println("-> expected result: -1.5263776468834178003E-1555");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(msgNotSame, "-1.5263776468834178003E-1555", bigDec
                .movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt008() {
        String number = "-1.5263776468834178003E-695";
        if (log) {
            System.out.println("testMovePointRightInt008");
        }
        bigDec = new BigDecimal(number);
        int n = 620;
        if (log) {
            System.out
                    .println("-> expected result: -1.5263776468834178003E-75");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(msgNotSame, "-1.5263776468834178003E-75", bigDec
                .movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt009() {
        String number = "-1.5263775774632718898E+676";
        if (log) {
            System.out.println("testMovePointRightInt009");
        }
        bigDec = new BigDecimal(number);
        int n = 142;
        if (log) {
            System.out
                    .println("-> expected result: -152637757746327188980000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(
                msgNotSame,
                "-152637757746327188980000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt010() {
        String number = "-3.617008641903833650E-525";
        if (log) {
            System.out.println("testMovePointRightInt010");
        }
        bigDec = new BigDecimal(number);
        int n = -388;
        if (log) {
            System.out
                    .println("-> expected result: -3.617008641903833650E-913");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(msgNotSame, "-3.617008641903833650E-913", bigDec
                .movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt011() {
        String number = "-3.617008641903833650E+203";
        if (log) {
            System.out.println("testMovePointRightInt011");
        }
        bigDec = new BigDecimal(number);
        int n = 109;
        if (log) {
            System.out
                    .println("-> expected result: -3617008641903833650000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(
                msgNotSame,
                "-3617008641903833650000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt012() {
        String number = "3.617008641903833650E+940";
        if (log) {
            System.out.println("testMovePointRightInt012");
        }
        bigDec = new BigDecimal(number);
        int n = 691;
        if (log) {
            System.out
                    .println("-> expected result: 361700864190383365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(
                msgNotSame,
                "361700864190383365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt013() {
        String number = "-3.617008641903833650E-698";
        if (log) {
            System.out.println("testMovePointRightInt013");
        }
        bigDec = new BigDecimal(number);
        int n = -171;
        if (log) {
            System.out
                    .println("-> expected result: -3.617008641903833650E-869");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(msgNotSame, "-3.617008641903833650E-869", bigDec
                .movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt014() {
        String number = "-3.617008641903833650E+160";
        if (log) {
            System.out.println("testMovePointRightInt014");
        }
        bigDec = new BigDecimal(number);
        int n = 959;
        if (log) {
            System.out
                    .println("-> expected result: -3617008641903833650000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(
                msgNotSame,
                "-3617008641903833650000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt015() {
        String number = "3.617008641903833650E+710";
        if (log) {
            System.out.println("testMovePointRightInt015");
        }
        bigDec = new BigDecimal(number);
        int n = -55;
        if (log) {
            System.out
                    .println("-> expected result: 36170086419038336500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(
                msgNotSame,
                "36170086419038336500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt016() {
        String number = "-3.617008641903833650E-70";
        if (log) {
            System.out.println("testMovePointRightInt016");
        }
        bigDec = new BigDecimal(number);
        int n = -216;
        if (log) {
            System.out
                    .println("-> expected result: -3.617008641903833650E-286");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(msgNotSame, "-3.617008641903833650E-286", bigDec
                .movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt017() {
        String number = "-3.617008641903833650E+944";
        if (log) {
            System.out.println("testMovePointRightInt017");
        }
        bigDec = new BigDecimal(number);
        int n = -206;
        if (log) {
            System.out
                    .println("-> expected result: -3617008641903833650000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(
                msgNotSame,
                "-3617008641903833650000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt018() {
        String number = "-3.617008641903833650E+241";
        if (log) {
            System.out.println("testMovePointRightInt018");
        }
        bigDec = new BigDecimal(number);
        int n = -612;
        if (log) {
            System.out
                    .println("-> expected result: -3.617008641903833650E-371");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(msgNotSame, "-3.617008641903833650E-371", bigDec
                .movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt019() {
        String number = "-3.617008641903833650E-36";
        if (log) {
            System.out.println("testMovePointRightInt019");
        }
        bigDec = new BigDecimal(number);
        int n = -107;
        if (log) {
            System.out
                    .println("-> expected result: -3.617008641903833650E-143");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(msgNotSame, "-3.617008641903833650E-143", bigDec
                .movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt020() {
        String number = "-3.617008641903833650E-629";
        if (log) {
            System.out.println("testMovePointRightInt020");
        }
        bigDec = new BigDecimal(number);
        int n = 122;
        if (log) {
            System.out
                    .println("-> expected result: -3.617008641903833650E-507");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(msgNotSame, "-3.617008641903833650E-507", bigDec
                .movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt021() {
        String number = "-3.617008641903833650E-406";
        if (log) {
            System.out.println("testMovePointRightInt021");
        }
        bigDec = new BigDecimal(number);
        int n = -125;
        if (log) {
            System.out
                    .println("-> expected result: -3.617008641903833650E-531");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(msgNotSame, "-3.617008641903833650E-531", bigDec
                .movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt022() {
        String number = "-3.617008641903833650E+882";
        if (log) {
            System.out.println("testMovePointRightInt022");
        }
        bigDec = new BigDecimal(number);
        int n = -714;
        if (log) {
            System.out
                    .println("-> expected result: -3617008641903833650000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(
                msgNotSame,
                "-3617008641903833650000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt023() {
        String number = "3.617008641903833650E+356";
        if (log) {
            System.out.println("testMovePointRightInt023");
        }
        bigDec = new BigDecimal(number);
        int n = 77;
        if (log) {
            System.out
                    .println("-> expected result: 36170086419038336500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(
                msgNotSame,
                "36170086419038336500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt024() {
        String number = "3.617008641903833650E-538";
        if (log) {
            System.out.println("testMovePointRightInt024");
        }
        bigDec = new BigDecimal(number);
        int n = -584;
        if (log) {
            System.out
                    .println("-> expected result: 3.617008641903833650E-1122");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(msgNotSame, "3.617008641903833650E-1122", bigDec
                .movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt025() {
        String number = "-3.617008641903838533E-332";
        if (log) {
            System.out.println("testMovePointRightInt025");
        }
        bigDec = new BigDecimal(number);
        int n = -194;
        if (log) {
            System.out
                    .println("-> expected result: -3.617008641903838533E-526");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(msgNotSame, "-3.617008641903838533E-526", bigDec
                .movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt026() {
        String number = "4.991471925827290437E+683";
        if (log) {
            System.out.println("testMovePointRightInt026");
        }
        bigDec = new BigDecimal(number);
        int n = 97;
        if (log) {
            System.out
                    .println("-> expected result: 4991471925827290437000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(
                msgNotSame,
                "4991471925827290437000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt027() {
        String number = "-4.991471925827290437E-284";
        if (log) {
            System.out.println("testMovePointRightInt027");
        }
        bigDec = new BigDecimal(number);
        int n = 69;
        if (log) {
            System.out
                    .println("-> expected result: -4.991471925827290437E-215");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(msgNotSame, "-4.991471925827290437E-215", bigDec
                .movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt028() {
        String number = "-4.991471925827290437E+504";
        if (log) {
            System.out.println("testMovePointRightInt028");
        }
        bigDec = new BigDecimal(number);
        int n = 933;
        if (log) {
            System.out
                    .println("-> expected result: -4991471925827290437000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(
                msgNotSame,
                "-4991471925827290437000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt029() {
        String number = "4.991471925827290437E-269";
        if (log) {
            System.out.println("testMovePointRightInt029");
        }
        bigDec = new BigDecimal(number);
        int n = -173;
        if (log) {
            System.out.println("-> expected result: 4.991471925827290437E-442");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(msgNotSame, "4.991471925827290437E-442", bigDec
                .movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMovePointRightInt030() {
        String number = "4.991471925827290437E+339";
        if (log) {
            System.out.println("testMovePointRightInt030");
        }
        bigDec = new BigDecimal(number);
        int n = 602;
        if (log) {
            System.out
                    .println("-> expected result: 499147192582729043700000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: "
                    + bigDec.movePointRight(n).toString());
        }
        assertEquals(
                msgNotSame,
                "499147192582729043700000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.movePointRight(n).toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    /*
     * Test method for 'java.math.BigDecimal.stripTrailingZeros()'
     */
    /**
     * This method test that for a BigDecimal whose value is 0 the returning
     * value of <b>StripTrailingZeros()</b> should be 0
     */
    public void testStripTrailingZeros001() {
        bigDec = new BigDecimal("0");
        assertEquals(msgNotSame, "0", bigDec.stripTrailingZeros().toString());
    }

    
    public void testStripTrailingZeros002() {
        bigDec = new BigDecimal(
                "4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");
        assertEquals(
                msgNotSame,
                "4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444",
                bigDec.stripTrailingZeros().toString());
    }

    
    public void testStripTrailingZeros003() {
        bigDec = new BigDecimal(
                "-4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");
        assertEquals(
                msgNotSame,
                "-4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444",
                bigDec.stripTrailingZeros().toString());
    }

    
    public void testStripTrailingZeros004() {
        bigDec = new BigDecimal(
                "969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        assertEquals(msgNotSame, "9.693583160302274182E+506", bigDec
                .stripTrailingZeros().toString());
    }

    
    public void testStripTrailingZeros005() {
        bigDec = new BigDecimal(
                "0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000012397");
        assertEquals(msgNotSame, "1.2397E-7637", bigDec.stripTrailingZeros()
                .toString());
    }

    
    public void testStripTrailingZeros006() {
        bigDec = new BigDecimal(
                "-0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000012397");
        assertEquals(msgNotSame, "-1.2397E-7637", bigDec.stripTrailingZeros()
                .toString());
    }

    
    public void testStripTrailingZeros007() {
        bigDec = new BigDecimal("9.693583160302274182E-804");
        assertEquals(msgNotSame, "9.693583160302274182E-804", bigDec
                .stripTrailingZeros().toString());
    }

    /**
     * This method test that for a BigDecimal whose value is
     * -9.693583160302274182E+506 the returning value of <b>StripTrailingZeros()</b>
     * should be -9.693583160302274182E+506
     */
    public void testStripTrailingZeros008() {
        bigDec = new BigDecimal("-9.693583160302274182E+506");
        assertEquals(msgNotSame, "-9.693583160302274182E+506", bigDec
                .stripTrailingZeros().toString());
    }

    /**
     * This method test that for a BigDecimal whose value is
     * -9.693583160302274182E-751 the returning value of <b>StripTrailingZeros()</b>
     * should be -9.693583160302274182E-751
     */
    public void testStripTrailingZeros009() {
        bigDec = new BigDecimal("-9.693583160302274182E-751");
        assertEquals(msgNotSame, "-9.693583160302274182E-751", bigDec
                .stripTrailingZeros().toString());
    }

    /**
     * This method test that for a BigDecimal whose value is
     * 1.3872923614502094470E-185 the returning value of <b>StripTrailingZeros()</b>
     * should be 1.387292361450209447E-185
     */
    public void testStripTrailingZeros010() {
        bigDec = new BigDecimal("1.3872923614502094470E-185");
        assertEquals(msgNotSame, "1.387292361450209447E-185", bigDec
                .stripTrailingZeros().toString());
    }

    /**
     * This method test that for a BigDecimal whose value is
     * -1.3889313184910721216E+64 the returning value of <b>StripTrailingZeros()</b>
     * should be -1.3889313184910721216E+64
     */
    public void testStripTrailingZeros011() {
        bigDec = new BigDecimal("-1.3889313184910721216E+64");
        assertEquals(msgNotSame, "-1.3889313184910721216E+64", bigDec
                .stripTrailingZeros().toString());
    }

    
    public void testStripTrailingZeros012() {
        bigDec = new BigDecimal(
                "-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        assertEquals(msgNotSame, "-1E+7650", bigDec.stripTrailingZeros()
                .toString());
    }

    
    public void testStripTrailingZeros013() {
        bigDec = new BigDecimal(
                "1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        assertEquals(msgNotSame, "1E+7650", bigDec.stripTrailingZeros()
                .toString());
    }

    
    public void testStripTrailingZeros014() {
        bigDec = new BigDecimal("0.9");
        assertEquals(msgNotSame, "0.9", bigDec.stripTrailingZeros().toString());
    }

    /**
     * This method test that for a BigDecimal whose value is 90 the returning
     * value of <b>StripTrailingZeros()</b> should be 9E+1
     */
    public void testStripTrailingZeros015() {
        bigDec = new BigDecimal("90");
        assertEquals(msgNotSame, "9E+1", bigDec.stripTrailingZeros().toString());
    }

    
    public void testStripTrailingZeros016() {
        bigDec = new BigDecimal(
                "1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001");
        assertEquals(
                msgNotSame,
                "1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001",
                bigDec.stripTrailingZeros().toString());
    }

    
    public void testStripTrailingZeros017() {
        bigDec = new BigDecimal(
                "-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001");
        assertEquals(
                msgNotSame,
                "-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001",
                bigDec.stripTrailingZeros().toString());
    }

    /**
     * This method test that for a BigDecimal whose value is
     * 8.6935831607039302274182E+9999 the returning value of
     * <b>StripTrailingZeros()</b> should be 8.6935831607039302274182E+9999
     */
    public void testStripTrailingZeros018() {
        bigDec = new BigDecimal("8.6935831607039302274182E+9999");
        assertEquals(msgNotSame, "8.6935831607039302274182E+9999", bigDec
                .stripTrailingZeros().toString());
    }

    
    public void testStripTrailingZeros019() {
        bigDec = new BigDecimal(
                "96935831603022741820000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000969358316030227418200000098745630000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000008945000000000000000005876000000000000000078600E-378");
        assertEquals(
                msgNotSame,
                "96935831603022741820000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000009693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000009693583160302274182000000987456300000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000089450000000000000000058760000000000000000786",
                bigDec.stripTrailingZeros().toString());
    }

    
    public void testStripTrailingZeros020() {
        bigDec = new BigDecimal(
                "-96935831603022741820000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000969358316030227418200000000000065487900000000000009693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000067800000000000798546000000000000000007000000000000E-387");
        assertEquals(
                msgNotSame,
                "-96935831603022741820000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000969358316030227418200000000000065487900000000000009693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000067800000000000798546000000000000000007",
                bigDec.stripTrailingZeros().toString());
    }

    /*
     * Test method for 'java.math.BigDecimal.compareTo(BigDecimal)'
     */
    public final void testCompareToBigDecimal001() {
        String number = "5.570193308531903821E+212";
        String number2 = "1.3889313184910721216E+276";
        if (log) {
            System.out.println("testCompareToBigDecimal001");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, -1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal002() {
        String number = "-5.570193308531903821E+477";
        String number2 = "-1.3889313184910721216E-245";
        if (log) {
            System.out.println("testCompareToBigDecimal002");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, -1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal003() {
        String number = "-5.570193308531903821E-472";
        String number2 = "1.3889313184910721216E-722";
        if (log) {
            System.out.println("testCompareToBigDecimal003");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, -1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal004() {
        String number = "-5.570193308531903821E+593";
        String number2 = "-1.3889313184910721216E-105";
        if (log) {
            System.out.println("testCompareToBigDecimal004");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, -1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal005() {
        String number = "5.570193308531903821E-700";
        String number2 = "1.3889313184910721216E-11";
        if (log) {
            System.out.println("testCompareToBigDecimal005");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, -1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal006() {
        String number = "-5.570193310131989676E+219";
        String number2 = "1.3889313184910721216E+318";
        if (log) {
            System.out.println("testCompareToBigDecimal006");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, -1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal007() {
        String number = "-1.2442509728149187756E-909";
        String number2 = "1.3889313184910721216E+827";
        if (log) {
            System.out.println("testCompareToBigDecimal007");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, -1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal008() {
        String number = "-1.2442509728149187756E-551";
        String number2 = "-1.3889313184910721216E-172";
        if (log) {
            System.out.println("testCompareToBigDecimal008");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: 1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, 1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal009() {
        String number = "1.2442509728149187756E-891";
        String number2 = "1.3889313184906905222E-61";
        if (log) {
            System.out.println("testCompareToBigDecimal009");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, -1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal010() {
        String number = "-1.2442509728149187756E-299";
        String number2 = "-9.693583160302274182E+361";
        if (log) {
            System.out.println("testCompareToBigDecimal010");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: 1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, 1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal011() {
        String number = "1.2442509728149187756E+881";
        String number2 = "9.693583160302274182E-956";
        if (log) {
            System.out.println("testCompareToBigDecimal011");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: 1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, 1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal012() {
        String number = "1.2442509728149187756E+306";
        String number2 = "9.693583160302274182E-30";
        if (log) {
            System.out.println("testCompareToBigDecimal012");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: 1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, 1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal013() {
        String number = "-1.2442509728149187756E+130";
        String number2 = "-9.693583160302274182E+990";
        if (log) {
            System.out.println("testCompareToBigDecimal013");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: 1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, 1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal014() {
        String number = "1.2442509728149187756E+883";
        String number2 = "9.693583160302274182E+955";
        if (log) {
            System.out.println("testCompareToBigDecimal014");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, -1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal015() {
        String number = "1.2442509728149187756E+228";
        String number2 = "-9.693583160302274182E+410";
        if (log) {
            System.out.println("testCompareToBigDecimal015");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: 1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, 1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal016() {
        String number = "1.2442509728149187756E-309";
        String number2 = "-9.693583160302274182E-760";
        if (log) {
            System.out.println("testCompareToBigDecimal016");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: 1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, 1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal017() {
        String number = "-1.2442509728149187756E+258";
        String number2 = "9.693583160302274182E-384";
        if (log) {
            System.out.println("testCompareToBigDecimal017");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, -1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal018() {
        String number = "-1.2442509728149187756E-7";
        String number2 = "9.693583160302274182E+122";
        if (log) {
            System.out.println("testCompareToBigDecimal018");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, -1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal019() {
        String number = "-1.2442509728149187756E+869";
        String number2 = "-9.693583160302274182E-624";
        if (log) {
            System.out.println("testCompareToBigDecimal019");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, -1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal020() {
        String number = "1.2442509728149187756E-181";
        String number2 = "-9.693583160302274182E+227";
        if (log) {
            System.out.println("testCompareToBigDecimal020");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: 1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, 1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal021() {
        String number = "1.2442509728149187756E+818";
        String number2 = "1.1068046444225730969E-25";
        if (log) {
            System.out.println("testCompareToBigDecimal021");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: 1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, 1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal022() {
        String number = "1.2442509728149187756E+353";
        String number2 = "-1.1068046444225730969E-646";
        if (log) {
            System.out.println("testCompareToBigDecimal022");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: 1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, 1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal023() {
        String number = "-1.3889313184910721216E+212";
        String number2 = "1.1068046444225730969E+545";
        if (log) {
            System.out.println("testCompareToBigDecimal023");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, -1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal024() {
        String number = "-1.3889313184910721216E+456";
        String number2 = "-1.1068046444225730969E+402";
        if (log) {
            System.out.println("testCompareToBigDecimal024");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, -1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal025() {
        String number = "1.3889313184910721216E+492";
        String number2 = "1.0851025925711500950E+508";
        if (log) {
            System.out.println("testCompareToBigDecimal025");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, -1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal026() {
        String number = "-1.3889313184910721216E+389";
        String number2 = "-1.0851025925711500950E+301";
        if (log) {
            System.out.println("testCompareToBigDecimal026");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, -1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal027() {
        String number = "-1.3889313184910721216E+434";
        String number2 = "1.0851025925711500950E+297";
        if (log) {
            System.out.println("testCompareToBigDecimal027");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, -1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal028() {
        String number = "-1.3889313184910721216E-920";
        String number2 = "1.0851025925711500950E-297";
        if (log) {
            System.out.println("testCompareToBigDecimal028");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, -1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal029() {
        String number = "1.3889313184910721216E-73";
        String number2 = "1.0851025925711500950E+850";
        if (log) {
            System.out.println("testCompareToBigDecimal029");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, -1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testCompareToBigDecimal030() {
        String number = "-1.3889313184910721216E+991";
        String number2 = "-1.0851025925711500950E+601";
        if (log) {
            System.out.println("testCompareToBigDecimal030");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -1");
            System.out.println("-> actual result: " + bigDec.compareTo(bd));
        }
        assertEquals(msgNotSame, -1, bigDec.compareTo(bd));
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    /*
     * Test method for 'java.math.BigDecimal.min(BigDecimal)'
     */
    public final void testMinBigDecimal001() {
        String number = "-1.4612714913291487946E-179";
        String number2 = "1.1791448172606497699E-321";
        if (log) {
            System.out.println("testMinBigDecimal001");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: -1.4612714913291487946E-179");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-1.4612714913291487946E-179", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal002() {
        String number = "-1.0416984888683040912E-949";
        String number2 = "-1.1791448172606497699E+966";
        if (log) {
            System.out.println("testMinBigDecimal002");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: -1.1791448172606497699E+966");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-1.1791448172606497699E+966", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal003() {
        String number = "1.0416984888683040912E-823";
        String number2 = "1.1791448172606497699E-694";
        if (log) {
            System.out.println("testMinBigDecimal003");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.0416984888683040912E-823");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "1.0416984888683040912E-823", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal004() {
        String number = "-1.0416984888683040912E+342";
        String number2 = "1.1791448172595970819E-119";
        if (log) {
            System.out.println("testMinBigDecimal004");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: -1.0416984888683040912E+342");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-1.0416984888683040912E+342", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal005() {
        String number = "-1.0416984888683040912E+790";
        String number2 = "-2.17020518514230019E+935";
        if (log) {
            System.out.println("testMinBigDecimal005");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -2.17020518514230019E+935");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-2.17020518514230019E+935", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal006() {
        String number = "1.0416984888683040912E-60";
        String number2 = "2.17020518514230019E+156";
        if (log) {
            System.out.println("testMinBigDecimal006");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: 1.0416984888683040912E-60");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "1.0416984888683040912E-60", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal007() {
        String number = "-1.0416984888683040912E+530";
        String number2 = "2.17020518514230019E-565";
        if (log) {
            System.out.println("testMinBigDecimal007");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: -1.0416984888683040912E+530");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-1.0416984888683040912E+530", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal008() {
        String number = "-1.0416984888683040912E-73";
        String number2 = "-2.17020518514230019E-428";
        if (log) {
            System.out.println("testMinBigDecimal008");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: -1.0416984888683040912E-73");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-1.0416984888683040912E-73", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal009() {
        String number = "1.0416984888683040912E+367";
        String number2 = "-0.217020518514230019";
        if (log) {
            System.out.println("testMinBigDecimal009");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -0.217020518514230019");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-0.217020518514230019", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal010() {
        String number = "1.0416984888683040912E+739";
        String number2 = "-2.17020518514230019E+427";
        if (log) {
            System.out.println("testMinBigDecimal010");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -2.17020518514230019E+427");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-2.17020518514230019E+427", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal011() {
        String number = "1.0416984888683040912E-245";
        String number2 = "2.17020518514230019E+323";
        if (log) {
            System.out.println("testMinBigDecimal011");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.0416984888683040912E-245");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "1.0416984888683040912E-245", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal012() {
        String number = "-1.0416984888683040912E-698";
        String number2 = "-2.17020518514230019E-289";
        if (log) {
            System.out.println("testMinBigDecimal012");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -2.17020518514230019E-289");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-2.17020518514230019E-289", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal013() {
        String number = "-1.0416984888683040912E+551";
        String number2 = "-2.17020518514230019E+557";
        if (log) {
            System.out.println("testMinBigDecimal013");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -2.17020518514230019E+557");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-2.17020518514230019E+557", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal014() {
        String number = "-1.0416984888683040912E-590";
        String number2 = "2.17020518514230019E-210";
        if (log) {
            System.out.println("testMinBigDecimal014");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: -1.0416984888683040912E-590");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-1.0416984888683040912E-590", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal015() {
        String number = "1.0416984888683040912E-748";
        String number2 = "2.17020518514230019E-329";
        if (log) {
            System.out.println("testMinBigDecimal015");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.0416984888683040912E-748");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "1.0416984888683040912E-748", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal016() {
        String number = "-1.0416984888683040912E+837";
        String number2 = "2.17020518514230019E-795";
        if (log) {
            System.out.println("testMinBigDecimal016");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: -1.0416984888683040912E+837");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-1.0416984888683040912E+837", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal017() {
        String number = "-1.0416984888683040912E+751";
        String number2 = "-2.17020518514230019E+831";
        if (log) {
            System.out.println("testMinBigDecimal017");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -2.17020518514230019E+831");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-2.17020518514230019E+831", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal018() {
        String number = "1.0422353885885866915E+796";
        String number2 = "-2.17020518514230019E-285";
        if (log) {
            System.out.println("testMinBigDecimal018");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -2.17020518514230019E-285");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-2.17020518514230019E-285", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal019() {
        String number = "1.1791448172606497699E+988";
        String number2 = "-2.17020518514230019E+931";
        if (log) {
            System.out.println("testMinBigDecimal019");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -2.17020518514230019E+931");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-2.17020518514230019E+931", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal020() {
        String number = "-1.1791448172606497699E+204";
        String number2 = "-2.17020518514230019E+479";
        if (log) {
            System.out.println("testMinBigDecimal020");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -2.17020518514230019E+479");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-2.17020518514230019E+479", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal021() {
        String number = "-1.1791448172606497699E-412";
        String number2 = "-1.591483802437686806E-10";
        if (log) {
            System.out.println("testMinBigDecimal021");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: -1.591483802437686806E-10");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-1.591483802437686806E-10", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal022() {
        String number = "1.1791448172606497699E-78";
        String number2 = "1.591483802437686806E+157";
        if (log) {
            System.out.println("testMinBigDecimal022");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: 1.1791448172606497699E-78");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "1.1791448172606497699E-78", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal023() {
        String number = "-1.1791448172606497699E-39";
        String number2 = "-1.591483802437686806E+331";
        if (log) {
            System.out.println("testMinBigDecimal023");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: -1.591483802437686806E+331");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-1.591483802437686806E+331", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal024() {
        String number = "1.1791448172606497699E-207";
        String number2 = "1.591483802437686806E-967";
        if (log) {
            System.out.println("testMinBigDecimal024");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: 1.591483802437686806E-967");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "1.591483802437686806E-967", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal025() {
        String number = "1.1791448172606497699E-554";
        String number2 = "1.591483802437686806E-210";
        if (log) {
            System.out.println("testMinBigDecimal025");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.1791448172606497699E-554");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "1.1791448172606497699E-554", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal026() {
        String number = "1.1791448172606497699E+537";
        String number2 = "1.591483802437686806E-734";
        if (log) {
            System.out.println("testMinBigDecimal026");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: 1.591483802437686806E-734");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "1.591483802437686806E-734", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal027() {
        String number = "-1.1791448172606497699E-341";
        String number2 = "1.591483802437686806E+369";
        if (log) {
            System.out.println("testMinBigDecimal027");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: -1.1791448172606497699E-341");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-1.1791448172606497699E-341", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal028() {
        String number = "1.1791448172606497699E+827";
        String number2 = "-1.591483802437686806E+725";
        if (log) {
            System.out.println("testMinBigDecimal028");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: -1.591483802437686806E+725");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-1.591483802437686806E+725", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal029() {
        String number = "-1.1791448172606497699E-817";
        String number2 = "1.591483802437686806E-84";
        if (log) {
            System.out.println("testMinBigDecimal029");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: -1.1791448172606497699E-817");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-1.1791448172606497699E-817", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMinBigDecimal030() {
        String number = "-1.1791448172606497699E+173";
        String number2 = "-1.591483802437686806E-428";
        if (log) {
            System.out.println("testMinBigDecimal030");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: -1.1791448172606497699E+173");
            System.out
                    .println("-> actual result: " + bigDec.min(bd).toString());
        }
        assertEquals(msgNotSame, "-1.1791448172606497699E+173", bigDec.min(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    /*
     * Test method for 'java.math.BigDecimal.max(BigDecimal)'
     */
    public final void testMaxBigDecimal001() {
        String number = "-1.519143629599610133E-725";
        String number2 = "1.6855260271271864809E-500";
        if (log) {
            System.out.println("testMaxBigDecimal001");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.6855260271271864809E-500");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.6855260271271864809E-500", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal002() {
        String number = "-1.519143629599610133E-832";
        String number2 = "1.6855260271271864809E-539";
        if (log) {
            System.out.println("testMaxBigDecimal002");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.6855260271271864809E-539");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.6855260271271864809E-539", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal003() {
        String number = "-1.519143629599610133E+805";
        String number2 = "-1.6855260271271864809E-25";
        if (log) {
            System.out.println("testMaxBigDecimal003");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: -1.6855260271271864809E-25");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "-1.6855260271271864809E-25", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal004() {
        String number = "1.519143629599610133E-895";
        String number2 = "1.6855260271271864809E-12";
        if (log) {
            System.out.println("testMaxBigDecimal004");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: 1.6855260271271864809E-12");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.6855260271271864809E-12", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal005() {
        String number = "1.519143629599610133E+630";
        String number2 = "1.6855260271271864809E-750";
        if (log) {
            System.out.println("testMaxBigDecimal005");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: 1.519143629599610133E+630");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.519143629599610133E+630", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal006() {
        String number = "1.519143629599610133E+184";
        String number2 = "1.6855260271271864809E-953";
        if (log) {
            System.out.println("testMaxBigDecimal006");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: 1.519143629599610133E+184");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.519143629599610133E+184", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal007() {
        String number = "1.519143629599610133E+932";
        String number2 = "-1.6855260271271864809E+319";
        if (log) {
            System.out.println("testMaxBigDecimal007");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: 1.519143629599610133E+932");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.519143629599610133E+932", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal008() {
        String number = "-1.519143629599610133E+358";
        String number2 = "-1.6855260271271864809E+859";
        if (log) {
            System.out.println("testMaxBigDecimal008");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: -1.519143629599610133E+358");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "-1.519143629599610133E+358", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal009() {
        String number = "-1.519143629599610133E-505";
        String number2 = "-1.6855260271271864809E-383";
        if (log) {
            System.out.println("testMaxBigDecimal009");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: -1.519143629599610133E-505");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "-1.519143629599610133E-505", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal010() {
        String number = "-8.463800222054970741E+1004";
        String number2 = "1.6855260271271864809E-190";
        if (log) {
            System.out.println("testMaxBigDecimal010");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.6855260271271864809E-190");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.6855260271271864809E-190", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal011() {
        String number = "8.463800222054970741E+190";
        String number2 = "-1.6855260271271864809E+361";
        if (log) {
            System.out.println("testMaxBigDecimal011");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: 8.463800222054970741E+190");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "8.463800222054970741E+190", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal012() {
        String number = "8.463800222054970741E-482";
        String number2 = "-1.6855260271271864809E-147";
        if (log) {
            System.out.println("testMaxBigDecimal012");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: 8.463800222054970741E-482");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "8.463800222054970741E-482", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal013() {
        String number = "8.463800222054970741E-403";
        String number2 = "1.6855260271271864809E-152";
        if (log) {
            System.out.println("testMaxBigDecimal013");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.6855260271271864809E-152");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.6855260271271864809E-152", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal014() {
        String number = "-1.085102592571150095E-341";
        String number2 = "1.6855260271271864809E-239";
        if (log) {
            System.out.println("testMaxBigDecimal014");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.6855260271271864809E-239");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.6855260271271864809E-239", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal015() {
        String number = "-1.141335773800748758E+664";
        String number2 = "1.6855260271204492773E-747";
        if (log) {
            System.out.println("testMaxBigDecimal015");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.6855260271204492773E-747");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.6855260271204492773E-747", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal016() {
        String number = "154.80796987348408022";
        String number2 = "1.6565899579919558117E-290";
        if (log) {
            System.out.println("testMaxBigDecimal016");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out.println("-> expected result: 154.80796987348408022");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "154.80796987348408022", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal017() {
        String number = "1.5480796987348408022E+502";
        String number2 = "1.6565899579919558117E+757";
        if (log) {
            System.out.println("testMaxBigDecimal017");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.6565899579919558117E+757");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.6565899579919558117E+757", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal018() {
        String number = "1.5480796987348408022E+681";
        String number2 = "-1.6565899579919558117E-869";
        if (log) {
            System.out.println("testMaxBigDecimal018");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.5480796987348408022E+681");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.5480796987348408022E+681", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal019() {
        String number = "1.5480796987348408022E+1018";
        String number2 = "1.6565899579919558117E-957";
        if (log) {
            System.out.println("testMaxBigDecimal019");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.5480796987348408022E+1018");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.5480796987348408022E+1018", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal020() {
        String number = "-1.5480796987348408022E-124";
        String number2 = "-1.6565899579919558117E+532";
        if (log) {
            System.out.println("testMaxBigDecimal020");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: -1.5480796987348408022E-124");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "-1.5480796987348408022E-124", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal021() {
        String number = "1.5480796987348408022E-716";
        String number2 = "1.6565899579919558117E+620";
        if (log) {
            System.out.println("testMaxBigDecimal021");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.6565899579919558117E+620");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.6565899579919558117E+620", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal022() {
        String number = "-1.5480796987348408022E+168";
        String number2 = "-1.6565899579919558117E-50";
        if (log) {
            System.out.println("testMaxBigDecimal022");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: -1.6565899579919558117E-50");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "-1.6565899579919558117E-50", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal023() {
        String number = "-1.5480796987348408022E-51";
        String number2 = "1.6565899579919558117E+706";
        if (log) {
            System.out.println("testMaxBigDecimal023");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.6565899579919558117E+706");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.6565899579919558117E+706", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal024() {
        String number = "1.5480796987348408022E+366";
        String number2 = "1.6565899579919558117E-601";
        if (log) {
            System.out.println("testMaxBigDecimal024");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.5480796987348408022E+366");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.5480796987348408022E+366", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal025() {
        String number = "1.5480796987348408022E-657";
        String number2 = "1.6565899579919558117E-557";
        if (log) {
            System.out.println("testMaxBigDecimal025");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.6565899579919558117E-557");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.6565899579919558117E-557", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal026() {
        String number = "1.5480796987348408022E+945";
        String number2 = "1.6565899579919558117E+407";
        if (log) {
            System.out.println("testMaxBigDecimal026");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.5480796987348408022E+945");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.5480796987348408022E+945", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal027() {
        String number = "1.5480796987348408022E+792";
        String number2 = "-1.6565899579919558117E-133";
        if (log) {
            System.out.println("testMaxBigDecimal027");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.5480796987348408022E+792");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.5480796987348408022E+792", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal028() {
        String number = "1.5480796987349658089E-754";
        String number2 = "-1.6565899579919558117E-657";
        if (log) {
            System.out.println("testMaxBigDecimal028");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.5480796987349658089E-754");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.5480796987349658089E-754", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal029() {
        String number = "1.6855260271271864809E+962";
        String number2 = "-1.6565899579919558117E-554";
        if (log) {
            System.out.println("testMaxBigDecimal029");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.6855260271271864809E+962");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.6855260271271864809E+962", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testMaxBigDecimal030() {
        String number = "1.6855260271271864809E+659";
        String number2 = "-1.7940362863843014904E+92";
        if (log) {
            System.out.println("testMaxBigDecimal030");
        }
        bigDec = new BigDecimal(number);
        BigDecimal bd = new BigDecimal(number2);
        if (log) {
            System.out
                    .println("-> expected result: 1.6855260271271864809E+659");
            System.out
                    .println("-> actual result: " + bigDec.max(bd).toString());
        }
        assertEquals(msgNotSame, "1.6855260271271864809E+659", bigDec.max(bd)
                .toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    /*
     * Test method for 'java.math.BigDecimal.toEngineeringString()'
     */
    public final void testToEngineeringString001() {
        String number = "1.44702422144914966E+940";
        if (log) {
            System.out.println("testToEngineeringString001");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 14.4702422144914966E+939");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "14.4702422144914966E+939", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString002() {
        String number = "-1.591483802437686806E-564";
        if (log) {
            System.out.println("testToEngineeringString002");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -1.591483802437686806E-564");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "-1.591483802437686806E-564", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString003() {
        String number = "1.591483802437686806E-754";
        if (log) {
            System.out.println("testToEngineeringString003");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 159.1483802437686806E-756");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "159.1483802437686806E-756", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString004() {
        String number = "-1.591483802437686806E-722";
        if (log) {
            System.out.println("testToEngineeringString004");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -15.91483802437686806E-723");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "-15.91483802437686806E-723", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString005() {
        String number = "-1.591483802437686806E-795";
        if (log) {
            System.out.println("testToEngineeringString005");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -1.591483802437686806E-795");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "-1.591483802437686806E-795", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString006() {
        String number = "1.591483802437686806E+51";
        if (log) {
            System.out.println("testToEngineeringString006");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1.591483802437686806E+51");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "1.591483802437686806E+51", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString007() {
        String number = "-1.591483802437686806E-759";
        if (log) {
            System.out.println("testToEngineeringString007");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -1.591483802437686806E-759");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "-1.591483802437686806E-759", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString008() {
        String number = "1.591483802437686806E+823";
        if (log) {
            System.out.println("testToEngineeringString008");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 15.91483802437686806E+822");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "15.91483802437686806E+822", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString009() {
        String number = "1.591483802437686806E-699";
        if (log) {
            System.out.println("testToEngineeringString009");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1.591483802437686806E-699");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "1.591483802437686806E-699", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString010() {
        String number = "-1.591483802437686806E-670";
        if (log) {
            System.out.println("testToEngineeringString010");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -159.1483802437686806E-672");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "-159.1483802437686806E-672", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString011() {
        String number = "-1.591483802437686806E+56";
        if (log) {
            System.out.println("testToEngineeringString011");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: -159.1483802437686806E+54");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "-159.1483802437686806E+54", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString012() {
        String number = "1.591483802437686806E-437";
        if (log) {
            System.out.println("testToEngineeringString012");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 15.91483802437686806E-438");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "15.91483802437686806E-438", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString013() {
        String number = "-1.591483802437686806E-425";
        if (log) {
            System.out.println("testToEngineeringString013");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -15.91483802437686806E-426");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "-15.91483802437686806E-426", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString014() {
        String number = "-1.591483802437686806E-298";
        if (log) {
            System.out.println("testToEngineeringString014");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -159.1483802437686806E-300");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "-159.1483802437686806E-300", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString015() {
        String number = "1.591483802437686806E+256";
        if (log) {
            System.out.println("testToEngineeringString015");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 15.91483802437686806E+255");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "15.91483802437686806E+255", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString016() {
        String number = "-8.463800222054970741E-222";
        if (log) {
            System.out.println("testToEngineeringString016");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -8.463800222054970741E-222");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "-8.463800222054970741E-222", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString017() {
        String number = "8.463800222054970741E+773";
        if (log) {
            System.out.println("testToEngineeringString017");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 846.3800222054970741E+771");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "846.3800222054970741E+771", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString018() {
        String number = "-8.463800222054970741E+77";
        if (log) {
            System.out.println("testToEngineeringString018");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: -846.3800222054970741E+75");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "-846.3800222054970741E+75", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString019() {
        String number = "-8.463800222054970741E+477";
        if (log) {
            System.out.println("testToEngineeringString019");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -8.463800222054970741E+477");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "-8.463800222054970741E+477", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString020() {
        String number = "8.463800222054970741E+677";
        if (log) {
            System.out.println("testToEngineeringString020");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 846.3800222054970741E+675");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "846.3800222054970741E+675", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString021() {
        String number = "8.463800222054970741E-350";
        if (log) {
            System.out.println("testToEngineeringString021");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 84.63800222054970741E-351");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "84.63800222054970741E-351", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString022() {
        String number = "-8.463800222054970741E+848";
        if (log) {
            System.out.println("testToEngineeringString022");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -846.3800222054970741E+846");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "-846.3800222054970741E+846", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString023() {
        String number = "-84638002220.54970741";
        if (log) {
            System.out.println("testToEngineeringString023");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: -84638002220.54970741");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "-84638002220.54970741", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString024() {
        String number = "8.463800222054970741E+373";
        if (log) {
            System.out.println("testToEngineeringString024");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 84.63800222054970741E+372");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "84.63800222054970741E+372", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString025() {
        String number = "8.463800222054970741E+756";
        if (log) {
            System.out.println("testToEngineeringString025");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 8.463800222054970741E+756");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "8.463800222054970741E+756", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString026() {
        String number = "8.463800222054970741E-56";
        if (log) {
            System.out.println("testToEngineeringString026");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 84.63800222054970741E-57");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "84.63800222054970741E-57", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString027() {
        String number = "-8.463800222054970741E+577";
        if (log) {
            System.out.println("testToEngineeringString027");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -84.63800222054970741E+576");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "-84.63800222054970741E+576", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString028() {
        String number = "-8.463800222054970741E+539";
        if (log) {
            System.out.println("testToEngineeringString028");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -846.3800222054970741E+537");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "-846.3800222054970741E+537", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString029() {
        String number = "-8.463800222054970741E+867";
        if (log) {
            System.out.println("testToEngineeringString029");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -8.463800222054970741E+867");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "-8.463800222054970741E+867", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testToEngineeringString030() {
        String number = "8.463800222054970741E-827";
        if (log) {
            System.out.println("testToEngineeringString030");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 84.63800222054970741E-828");
            System.out.println("-> actual result: "
                    + bigDec.toEngineeringString());
        }
        assertEquals(msgNotSame, "84.63800222054970741E-828", bigDec
                .toEngineeringString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    /*
     * Test method for 'java.math.BigDecimal.toPlainString()'
     */
    public final void testtoPlainString001() {
        String number = "-1.2514849900987264429E-415";
        if (log) {
            System.out.println("testtoPlainString001");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000012514849900987264429");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "-0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000012514849900987264429",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString002() {
        String number = "1.2514849900987264429E+147";
        if (log) {
            System.out.println("testtoPlainString002");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 1251484990098726442900000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "1251484990098726442900000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString003() {
        String number = "1.2514849900987264429E+674";
        if (log) {
            System.out.println("testtoPlainString003");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 125148499009872644290000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "125148499009872644290000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString004() {
        String number = "1.2514849982911660224E+744";
        if (log) {
            System.out.println("testtoPlainString004");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 1251484998291166022400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "1251484998291166022400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString005() {
        String number = "1.3889313184910721216E-788";
        if (log) {
            System.out.println("testtoPlainString005");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013889313184910721216");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013889313184910721216",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString006() {
        String number = "1.3889313184910721216E+612";
        if (log) {
            System.out.println("testtoPlainString006");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 1388931318491072121600000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "1388931318491072121600000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString007() {
        String number = "-1.3889313184910721216E+286";
        if (log) {
            System.out.println("testtoPlainString007");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -13889313184910721216000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "-13889313184910721216000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString008() {
        String number = "-1.3889313184910721216E-960";
        if (log) {
            System.out.println("testtoPlainString008");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013889313184910721216");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "-0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013889313184910721216",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString009() {
        String number = "1.3889313184910721216E-80";
        if (log) {
            System.out.println("testtoPlainString009");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 0.000000000000000000000000000000000000000000000000000000000000000000000000000000013889313184910721216");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "0.000000000000000000000000000000000000000000000000000000000000000000000000000000013889313184910721216",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString010() {
        String number = "-1.3889313184910721216E+767";
        if (log) {
            System.out.println("testtoPlainString010");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -138893131849107212160000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "-138893131849107212160000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString011() {
        String number = "-1.3889313184910721216E-153";
        if (log) {
            System.out.println("testtoPlainString011");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013889313184910721216");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "-0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013889313184910721216",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString012() {
        String number = "1.3889313184910721216E+778";
        if (log) {
            System.out.println("testtoPlainString012");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 13889313184910721216000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "13889313184910721216000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString013() {
        String number = "-1.3889313184910721216E-645";
        if (log) {
            System.out.println("testtoPlainString013");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013889313184910721216");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "-0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013889313184910721216",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString014() {
        String number = "-1.3889313184910721216E-805";
        if (log) {
            System.out.println("testtoPlainString014");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013889313184910721216");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "-0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013889313184910721216",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString015() {
        String number = "-1.3889313184910721216E-951";
        if (log) {
            System.out.println("testtoPlainString015");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013889313184910721216");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "-0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013889313184910721216",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString016() {
        String number = "1.3889313184910721216E-324";
        if (log) {
            System.out.println("testtoPlainString016");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013889313184910721216");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013889313184910721216",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString017() {
        String number = "-1.3889313184910721216E+64";
        if (log) {
            System.out.println("testtoPlainString017");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -13889313184910721216000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "-13889313184910721216000000000000000000000000000000000000000000000",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString018() {
        String number = "-1.3889313184910721216E-284";
        if (log) {
            System.out.println("testtoPlainString018");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013889313184910721216");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "-0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013889313184910721216",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString019() {
        String number = "1.3889313184910721216E+181";
        if (log) {
            System.out.println("testtoPlainString019");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 13889313184910721216000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "13889313184910721216000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString020() {
        String number = "-1.3889313184910721216E-88";
        if (log) {
            System.out.println("testtoPlainString020");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000013889313184910721216");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "-0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000013889313184910721216",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString021() {
        String number = "1.3872923614502094470E-185";
        if (log) {
            System.out.println("testtoPlainString021");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013872923614502094470");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013872923614502094470",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString022() {
        String number = "-9.693583160302274182E-751";
        if (log) {
            System.out.println("testtoPlainString022");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009693583160302274182");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "-0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009693583160302274182",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString023() {
        String number = "-9.693583160302274182E+98";
        if (log) {
            System.out.println("testtoPlainString023");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString024() {
        String number = "-9.693583160302274182E+167";
        if (log) {
            System.out.println("testtoPlainString024");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString025() {
        String number = "-9.693583160302274182E+126";
        if (log) {
            System.out.println("testtoPlainString025");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString026() {
        String number = "-9.693583160302274182E+389";
        if (log) {
            System.out.println("testtoPlainString026");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString027() {
        String number = "9.693583160302274182E+506";
        if (log) {
            System.out.println("testtoPlainString027");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString028() {
        String number = "9.693583160302274182E-804";
        if (log) {
            System.out.println("testtoPlainString028");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: 0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009693583160302274182");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009693583160302274182",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString029() {
        String number = "-9.693583160302274182E-626";
        if (log) {
            System.out.println("testtoPlainString029");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009693583160302274182");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "-0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009693583160302274182",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testtoPlainString030() {
        String number = "-9.693583160302274182E-279";
        if (log) {
            System.out.println("testtoPlainString030");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out
                    .println("-> expected result: -0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009693583160302274182");
            System.out.println("-> actual result: " + bigDec.toPlainString());
        }
        assertEquals(
                msgNotSame,
                "-0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009693583160302274182",
                bigDec.toPlainString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    /*
     * Test method for 'java.math.BigDecimal.toBigInteger()'
     */
    /**
     * This method test that for a Biginteger whose value is 0 the returning
     * value of <b>ToBigInteger()</b> should be 0
     */
    public void testToBigInteger001() {
        bigDec = new BigDecimal("0");
        assertEquals(msgNotSame, "0", bigDec.toBigInteger().toString());
    }

    
    public void testToBigInteger002() {
        bigDec = new BigDecimal(
                "4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");
        assertEquals(
                msgNotSame,
                "4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444",
                bigDec.toBigInteger().toString());
    }

    
    public void testToBigInteger003() {
        bigDec = new BigDecimal(
                "-4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");
        assertEquals(
                msgNotSame,
                "-4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444",
                bigDec.toBigInteger().toString());
    }

    
    public void testToBigInteger004() {
        bigDec = new BigDecimal(
                "969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        assertEquals(
                msgNotSame,
                "969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toBigInteger().toString());
    }

    /**
     * This method test that for a Biginteger whose value is
     * -9.693583160302274182E-279 the returning value of <b>ToBigInteger()</b>
     * should be 0
     */
    public void testToBigInteger005() {
        bigDec = new BigDecimal("-9.693583160302274182E-279");
        assertEquals(msgNotSame, "0", bigDec.toBigInteger().toString());
    }

    /**
     * This method test that for a Biginteger whose value is
     * 9.693583160302274182E-804 the returning value of <b>ToBigInteger()</b>
     * should be 0
     */
    public void testToBigInteger006() {
        bigDec = new BigDecimal("9.693583160302274182E-804");
        assertEquals(msgNotSame, "0", bigDec.toBigInteger().toString());
    }

    
    public void testToBigInteger007() {
        bigDec = new BigDecimal("-9.693583160302274182E+506");
        assertEquals(
                msgNotSame,
                "-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toBigInteger().toString());
    }

    /**
     * This method test that for a Biginteger whose value is
     * -9.693583160302274182E-751 the returning value of <b>ToBigInteger()</b>
     * should be 0
     */
    public void testToBigInteger008() {
        bigDec = new BigDecimal("-9.693583160302274182E-751");
        assertEquals(msgNotSame, "0", bigDec.toBigInteger().toString());
    }

    /**
     * This method test that for a Biginteger whose value is
     * 1.3872923614502094470E-185 the returning value of <b>ToBigInteger()</b>
     * should be 0
     */
    public void testToBigInteger009() {
        bigDec = new BigDecimal("1.3872923614502094470E-185");
        assertEquals(msgNotSame, "0", bigDec.toBigInteger().toString());
    }

    /**
     * This method test that for a Biginteger whose value is
     * -1.3889313184910721216E+64 the returning value of <b>ToBigInteger()</b>
     * should be
     * -13889313184910721216000000000000000000000000000000000000000000000
     */
    public void testToBigInteger010() {
        bigDec = new BigDecimal("-1.3889313184910721216E+64");
        assertEquals(
                msgNotSame,
                "-13889313184910721216000000000000000000000000000000000000000000000",
                bigDec.toBigInteger().toString());
    }

    /**
     * This method test that for a Biginteger whose value is
     * -1.3889313184910721216E-805 the returning value of <b>ToBigInteger()</b>
     * should be 0
     */
    public void testToBigInteger011() {
        bigDec = new BigDecimal("-1.3889313184910721216E-805");
        assertEquals(msgNotSame, "0", bigDec.toBigInteger().toString());
    }

    
    public void testToBigInteger012() {
        bigDec = new BigDecimal("1.3889313184910721216E+778");
        assertEquals(
                msgNotSame,
                "13889313184910721216000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toBigInteger().toString());
    }

    /**
     * This method test that for a Biginteger whose value is
     * 2.38893131870394910721216E-9999 the returning value of <b>ToBigInteger()</b>
     * should be 0
     */
    public void testToBigInteger013() {
        bigDec = new BigDecimal("2.38893131870394910721216E-9999");
        assertEquals(msgNotSame, "0", bigDec.toBigInteger().toString());
    }

    /**
     * This method test that for a Biginteger whose value is
     * -4.6935831603022747039182E-1098 the returning value of <b>ToBigInteger()</b>
     * should be 0
     */
    public void testToBigInteger014() {
        bigDec = new BigDecimal("-4.6935831603022747039182E-1098");
        assertEquals(msgNotSame, "0", bigDec.toBigInteger().toString());
    }

    
    public void testToBigInteger015() {
        bigDec = new BigDecimal("8.6935831607039302274182E+9999");
        assertEquals(
                msgNotSame,
                "8693583160703930227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toBigInteger().toString());
    }

    
    public void testToBigInteger016() {
        bigDec = new BigDecimal(
                "96935831603022741820000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000969358316030227418200000098745630000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000008945000000000000000005876000000000000000078600E-378");
        assertEquals(
                msgNotSame,
                "96935831603022741820000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toBigInteger().toString());
    }

    
    public void testToBigInteger017() {
        bigDec = new BigDecimal(
                "-96935831603022741820000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000969358316030227418200000000000065487900000000000009693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000067800000000000798546000000000000000007000000000000E-387");
        assertEquals(
                msgNotSame,
                "-96935831603022741820000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toBigInteger().toString());
    }

    /*
     * Test method for 'java.math.BigDecimal.toBigIntegerExact()'
     */
    /**
     * This method test that for a BigInteger whose value is 0 the returning
     * value of <b>toBigIntegerExact()</b> should be 0
     */
    public void testToBigIntegerExact001() {
        bigDec = new BigDecimal("0");
        assertEquals(msgNotSame, "0", bigDec.toBigIntegerExact().toString());
    }

    
    public void testToBigIntegerExact002() {
        bigDec = new BigDecimal(
                "4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");
        assertEquals(
                msgNotSame,
                "4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444",
                bigDec.toBigIntegerExact().toString());
    }

    
    public void testToBigIntegerExact003() {
        bigDec = new BigDecimal(
                "-4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");
        assertEquals(
                msgNotSame,
                "-4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444",
                bigDec.toBigIntegerExact().toString());
    }

    
    public void testToBigIntegerExact004() {
        bigDec = new BigDecimal(
                "969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        assertEquals(
                msgNotSame,
                "969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toBigIntegerExact().toString());
    }

    /**
     * This method test that for a BigInteger whose value is
     * -9.693583160302274182E-279 the returning value of the method
     * <b>ToBigIntegerExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testToBigIntegerExact005() {
        try {
            bigDec = new BigDecimal("-9.693583160302274182E-279");
            bigDec.toBigIntegerExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is
     * 9.693583160302274182E-804 the returning value of the method
     * <b>ToBigIntegerExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testToBigIntegerExact006() {
        try {
            bigDec = new BigDecimal("9.693583160302274182E-804");
            bigDec.toBigIntegerExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    
    public void testToBigIntegerExact007() {
        bigDec = new BigDecimal("-9.693583160302274182E+506");
        assertEquals(
                msgNotSame,
                "-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toBigIntegerExact().toString());
    }

    /**
     * This method test that for a BigInteger whose value is
     * -9.693583160302274182E-751 the returning value of the method
     * <b>ToBigIntegerExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testToBigIntegerExact008() {
        try {
            bigDec = new BigDecimal("-9.693583160302274182E-751");
            bigDec.toBigIntegerExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is
     * 1.3872923614502094470E-185 the returning value of the method
     * <b>ToBigIntegerExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testToBigIntegerExact009() {
        try {
            bigDec = new BigDecimal("1.3872923614502094470E-185");
            bigDec.toBigIntegerExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is
     * -1.3889313184910721216E+64 the returning value of <b>toBigIntegerExact()</b>
     * should be
     * -13889313184910721216000000000000000000000000000000000000000000000
     */
    public void testToBigIntegerExact010() {
        bigDec = new BigDecimal("-1.3889313184910721216E+64");
        assertEquals(
                msgNotSame,
                "-13889313184910721216000000000000000000000000000000000000000000000",
                bigDec.toBigIntegerExact().toString());
    }

    /**
     * This method test that for a BigInteger whose value is
     * -1.3889313184910721216E-805 the returning value of the method
     * <b>ToBigIntegerExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testToBigIntegerExact011() {
        try {
            bigDec = new BigDecimal("-1.3889313184910721216E-805");
            bigDec.toBigIntegerExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    public void testToBigIntegerExact012() {
        bigDec = new BigDecimal("1.3889313184910721216E+778");
        assertEquals(
                msgNotSame,
                "13889313184910721216000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toBigIntegerExact().toString());
    }

    /**
     * This method test that for a BigInteger whose value is
     * 2.38893131870394910721216E-9999 the returning value of the method
     * <b>ToBigIntegerExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testToBigIntegerExact013() {
        try {
            bigDec = new BigDecimal("2.38893131870394910721216E-9999");
            bigDec.toBigIntegerExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is
     * -4.6935831603022747039182E-1098 the returning value of the method
     * <b>ToBigIntegerExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testToBigIntegerExact014() {
        try {
            bigDec = new BigDecimal("-4.6935831603022747039182E-1098");
            bigDec.toBigIntegerExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    
    public void testToBigIntegerExact015() {
        bigDec = new BigDecimal("8.6935831607039302274182E+9999");
        assertEquals(
                msgNotSame,
                "8693583160703930227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                bigDec.toBigIntegerExact().toString());
    }

    
    public void testToBigIntegerExact016() {
        try {
            bigDec = new BigDecimal(
                    "96935831603022741820000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000969358316030227418200000098745630000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000008945000000000000000005876000000000000000078600E-378");
            bigDec.toBigIntegerExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    
    public void testToBigIntegerExact017() {
        try {
            bigDec = new BigDecimal(
                    "-96935831603022741820000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000969358316030227418200000000000065487900000000000009693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000067800000000000798546000000000000000007000000000000E-387");
            bigDec.toBigIntegerExact();

            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is 0 the returning
     * value of <b>longValueExact()</b> should be 0
     */
    public void testLongValueExact001() {
        bigDec = new BigDecimal("0");
        assertEquals(msgNotSame, 0, bigDec.longValueExact());
    }

    /**
     * This method test that for a BigInteger whose value is 9223372036854775807
     * the returning value of <b>longValueExact()</b> should be
     * 9223372036854775807
     */
    public void testLongValueExact002() {
        bigDec = new BigDecimal("9223372036854775807");
        assertEquals(msgNotSame, 9223372036854775807L, bigDec.longValueExact());
    }

    /**
     * This method test that for a BigInteger whose value is
     * -9223372036854775808 the returning value of <b>longValueExact()</b>
     * should be -9223372036854775808
     */
    public void testLongValueExact003() {
        bigDec = new BigDecimal("-9223372036854775808");
        assertEquals(msgNotSame, -9223372036854775808L, bigDec.longValueExact());
    }

    /**
     * This method test that for a BigInteger whose value is 9223372036854775806
     * the returning value of <b>longValueExact()</b> should be
     * 9223372036854775806
     */
    public void testLongValueExact004() {
        bigDec = new BigDecimal("9223372036854775806");
        assertEquals(msgNotSame, 9223372036854775806L, bigDec.longValueExact());
    }

    /**
     * This method test that for a BigInteger whose value is
     * -9223372036854775807 the returning value of <b>longValueExact()</b>
     * should be -9223372036854775807
     */
    public void testLongValueExact005() {
        bigDec = new BigDecimal("-9223372036854775807");
        assertEquals(msgNotSame, -9223372036854775807L, bigDec.longValueExact());
    }

    /**
     * This method test that for a BigInteger whose value is 9223372036854775808
     * the returning value of the method <b>longValueExact()</b> should throw
     * <i>ArithmeticException</i>
     */
    public void testLongValueExact006() {
        try {
            bigDec = new BigDecimal("9223372036854775808");
            bigDec.longValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is
     * -9223372036854775809 the returning value of the method
     * <b>longValueExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testLongValueExact007() {
        try {
            bigDec = new BigDecimal("-9223372036854775809");
            bigDec.longValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is -0.01E-100000000
     * the returning value of the method <b>longValueExact()</b> should throw
     * <i>ArithmeticException</i>
     */
    public void testLongValueExact008() {
        try {
            bigDec = new BigDecimal("-0.01E-100000000");
            bigDec.longValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is 0.01E-100000000 the
     * returning value of the method <b>longValueExact()</b> should throw
     * <i>ArithmeticException</i>
     */
    public void testLongValueExact009() {
        try {
            bigDec = new BigDecimal("0.01E-100000000");
            bigDec.longValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is
     * -1.3889313184910721216E+640 the returning value of the method
     * <b>longValueExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testLongValueExact010() {
        try {
            bigDec = new BigDecimal("-1.3889313184910721216E+640");
            bigDec.longValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is
     * -1.3889313184910721216E-805 the returning value of the method
     * <b>longValueExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testLongValueExact011() {
        try {
            bigDec = new BigDecimal("-1.3889313184910721216E-805");
            bigDec.longValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is
     * -1.3889313184910721216E+640 the returning value of the method
     * <b>longValueExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testLongValueExact012() {
        try {
            bigDec = new BigDecimal("-1.3889313184910721216E+640");
            bigDec.longValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is
     * -1.3889313184910721216E-805 the returning value of the method
     * <b>longValueExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testLongValueExact013() {
        try {
            bigDec = new BigDecimal("-1.3889313184910721216E-805");
            bigDec.longValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    
    public void testLongValueExact014() {
        try {
            bigDec = new BigDecimal(
                    "9786654333763546378654366786664532156786513568765163874687695432187653387863512378632137866453341678432138745333333241377775644433337853331788888.678354415378334551335438743453333488888888864321111003215043540323570085600034335350800546876795378544453153456879997805013604568709542643768307863607960378");
            bigDec.longValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    
    public void testLongValueExact015() {
        try {
            bigDec = new BigDecimal(
                    "-9786654333763546378654366786664532156786513568765163874687695432187653387863512378632137866453341678432138745333333241377775644433337853331788888.678354415378334551335438743453333488888888864321111003215043540323570085600034335350800546876795378544453153456879997805013604568709542643768307863607960378");
            bigDec.longValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /*
     * Test method for 'java.math.BigDecimal.intValueExact()'
     */
    /**
     * This method test that for a BigInteger whose value is 0 the returning
     * value of <b>intValueExact()</b> should be 0
     */
    public void testIntValueExact001() {
        bigDec = new BigDecimal("0");
        assertEquals(msgNotSame, 0, bigDec.intValueExact());
    }

    /**
     * This method test that for a BigInteger whose value is 2147483647 the
     * returning value of <b>intValueExact()</b> should be 2147483647
     */
    public void testIntValueExact002() {
        bigDec = new BigDecimal("2147483647");
        assertEquals(msgNotSame, 2147483647, bigDec.intValueExact());
    }

    /**
     * This method test that for a BigInteger whose value is -2147483648 the
     * returning value of <b>intValueExact()</b> should be -2147483648
     */
    public void testIntValueExact003() {
        bigDec = new BigDecimal("-2147483648");
        assertEquals(msgNotSame, -2147483648, bigDec.intValueExact());
    }

    /**
     * This method test that for a BigInteger whose value is 2147483646 the
     * returning value of <b>intValueExact()</b> should be 2147483646
     */
    public void testIntValueExact004() {
        bigDec = new BigDecimal("2147483646");
        assertEquals(msgNotSame, 2147483646, bigDec.intValueExact());
    }

    /**
     * This method test that for a BigInteger whose value is -2147483647 the
     * returning value of <b>intValueExact()</b> should be -2147483647
     */
    public void testIntValueExact005() {
        bigDec = new BigDecimal("-2147483647");
        assertEquals(msgNotSame, -2147483647, bigDec.intValueExact());
    }

    /**
     * This method test that for a BigInteger whose value is 2147483648 the
     * returning value of the method <b>intValueExact()</b> should throw
     * <i>ArithmeticException</i>
     */
    public void testIntValueExact006() {
        try {
            bigDec = new BigDecimal("2147483648");
            bigDec.intValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is -2147483649 the
     * returning value of the method <b>intValueExact()</b> should throw
     * <i>ArithmeticException</i>
     */
    public void testIntValueExact007() {
        try {
            bigDec = new BigDecimal("-2147483649");
            bigDec.intValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is -0.01E-100000000
     * the returning value of the method <b>intValueExact()</b> should throw
     * <i>ArithmeticException</i>
     */
    public void testIntValueExact008() {
        try {
            bigDec = new BigDecimal("-0.01E-100000000");
            bigDec.intValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is 0.01E-100000000 the
     * returning value of the method <b>intValueExact()</b> should throw
     * <i>ArithmeticException</i>
     */
    public void testIntValueExact009() {
        try {
            bigDec = new BigDecimal("0.01E-100000000");
            bigDec.intValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is
     * -1.3889313184910721216E+940 the returning value of the method
     * <b>intValueExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testIntValueExact010() {
        try {
            bigDec = new BigDecimal("-1.3889313184910721216E+940");
            bigDec.intValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is
     * -1.3889313184910721216E-1005 the returning value of the method
     * <b>intValueExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testIntValueExact011() {
        try {
            bigDec = new BigDecimal("-1.3889313184910721216E-1005");
            bigDec.intValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is
     * -1.3889313184910721216E+940 the returning value of the method
     * <b>intValueExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testIntValueExact012() {
        try {
            bigDec = new BigDecimal("-1.3889313184910721216E+940");
            bigDec.intValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is
     * -1.3889313184910721216E-1005 the returning value of the method
     * <b>intValueExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testIntValueExact013() {
        try {
            bigDec = new BigDecimal("-1.3889313184910721216E-1005");
            bigDec.intValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /*
     * Test method for 'java.math.BigDecimal.shortValueExact()'
     */
    /**
     * This method test that for a BigInteger whose value is 0 the returning
     * value of <b>shortValueExact()</b> should be 0
     */
    public void testShortValueExact001() {
        bigDec = new BigDecimal("0");
        assertEquals(msgNotSame, 0, bigDec.shortValueExact());
    }

    /**
     * This method test that for a BigInteger whose value is 32767 the returning
     * value of <b>shortValueExact()</b> should be 32767
     */
    public void testShortValueExact002() {
        bigDec = new BigDecimal("32767");
        assertEquals(msgNotSame, 32767, bigDec.shortValueExact());
    }

    /**
     * This method test that for a BigInteger whose value is -32768 the
     * returning value of <b>shortValueExact()</b> should be -32768
     */
    public void testShortValueExact003() {
        bigDec = new BigDecimal("-32768");
        assertEquals(msgNotSame, -32768, bigDec.shortValueExact());
    }

    /**
     * This method test that for a BigInteger whose value is 32766 the returning
     * value of <b>shortValueExact()</b> should be 32766
     */
    public void testShortValueExact004() {
        bigDec = new BigDecimal("32766");
        assertEquals(msgNotSame, 32766, bigDec.shortValueExact());
    }

    /**
     * This method test that for a BigInteger whose value is -32767 the
     * returning value of <b>shortValueExact()</b> should be -32767
     */
    public void testShortValueExact005() {
        bigDec = new BigDecimal("-32767");
        assertEquals(msgNotSame, -32767, bigDec.shortValueExact());
    }

    /**
     * This method test that for a BigInteger whose value is 32768 the returning
     * value of the method <b>shortValueExact()</b> should throw
     * <i>ArithmeticException</i>
     */
    public void testShortValueExact006() {
        try {
            bigDec = new BigDecimal("32768");
            bigDec.shortValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is -32769 the
     * returning value of the method <b>shortValueExact()</b> should throw
     * <i>ArithmeticException</i>
     */
    public void testShortValueExact007() {
        try {
            bigDec = new BigDecimal("-32769");
            bigDec.shortValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is -0.01E-100000000
     * the returning value of the method <b>shortValueExact()</b> should throw
     * <i>ArithmeticException</i>
     */
    public void testShortValueExact008() {
        try {
            bigDec = new BigDecimal("-0.01E-100000000");
            bigDec.shortValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is 0.01E-100000000 the
     * returning value of the method <b>shortValueExact()</b> should throw
     * <i>ArithmeticException</i>
     */
    public void testShortValueExact009() {
        try {
            bigDec = new BigDecimal("0.01E-100000000");
            bigDec.shortValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is
     * -1.3889313184910721216E+940 the returning value of the method
     * <b>shortValueExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testShortValueExact010() {
        try {
            bigDec = new BigDecimal("-1.3889313184910721216E+940");
            bigDec.shortValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is
     * -1.3889313184910721216E-1005 the returning value of the method
     * <b>shortValueExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testShortValueExact011() {
        try {
            bigDec = new BigDecimal("-1.3889313184910721216E-1005");
            bigDec.shortValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is
     * -1.3889313184910721216E+940 the returning value of the method
     * <b>shortValueExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testShortValueExact012() {
        try {
            bigDec = new BigDecimal("-1.3889313184910721216E+940");
            bigDec.shortValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is
     * -1.3889313184910721216E-1005 the returning value of the method
     * <b>shortValueExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testShortValueExact013() {
        try {
            bigDec = new BigDecimal("-1.3889313184910721216E-1005");
            bigDec.shortValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /*
     * Test method for 'java.math.BigDecimal.byteValueExact()'
     */
    /**
     * This method test that for a BigInteger whose value is 0 the returning
     * value of <b>byteValueExact()</b> should be 0
     */
    public void testByteValueExact001() {
        bigDec = new BigDecimal("0");
        assertEquals(msgNotSame, 0, bigDec.byteValueExact());
    }

    /**
     * This method test that for a BigInteger whose value is 127 the returning
     * value of <b>byteValueExact()</b> should be 127
     */
    public void testByteValueExact002() {
        bigDec = new BigDecimal("127");
        assertEquals(msgNotSame, 127, bigDec.byteValueExact());
    }

    /**
     * This method test that for a BigInteger whose value is -128 the returning
     * value of <b>byteValueExact()</b> should be -128
     */
    public void testByteValueExact003() {
        bigDec = new BigDecimal("-128");
        assertEquals(msgNotSame, -128, bigDec.byteValueExact());
    }

    /**
     * This method test that for a BigInteger whose value is 126 the returning
     * value of <b>byteValueExact()</b> should be 126
     */
    public void testByteValueExact004() {
        bigDec = new BigDecimal("126");
        assertEquals(msgNotSame, 126, bigDec.byteValueExact());
    }

    /**
     * This method test that for a BigInteger whose value is -127 the returning
     * value of <b>byteValueExact()</b> should be -127
     */
    public void testByteValueExact005() {
        bigDec = new BigDecimal("-127");
        assertEquals(msgNotSame, -127, bigDec.byteValueExact());
    }

    /**
     * This method test that for a BigInteger whose value is 128 the returning
     * value of the method <b>byteValueExact()</b> should throw
     * <i>ArithmeticException</i>
     */
    public void testByteValueExact006() {
        try {
            bigDec = new BigDecimal("128");
            bigDec.byteValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is -129 the returning
     * value of the method <b>byteValueExact()</b> should throw
     * <i>ArithmeticException</i>
     */
    public void testByteValueExact007() {
        try {
            bigDec = new BigDecimal("-129");
            bigDec.byteValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is -0.01E-100000000
     * the returning value of the method <b>byteValueExact()</b> should throw
     * <i>ArithmeticException</i>
     */
    public void testByteValueExact008() {
        try {
            bigDec = new BigDecimal("-0.01E-100000000");
            bigDec.byteValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is 0.01E-100000000 the
     * returning value of the method <b>byteValueExact()</b> should throw
     * <i>ArithmeticException</i>
     */
    public void testByteValueExact009() {
        try {
            bigDec = new BigDecimal("0.01E-100000000");
            bigDec.byteValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is
     * 1.3889313184910721216E+940 the returning value of the method
     * <b>byteValueExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testByteValueExact010() {
        try {
            bigDec = new BigDecimal("1.3889313184910721216E+940");
            bigDec.byteValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is
     * 1.3889313184910721216E-1005 the returning value of the method
     * <b>byteValueExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testByteValueExact011() {
        try {
            bigDec = new BigDecimal("1.3889313184910721216E-1005");
            bigDec.byteValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is
     * -1.3889313184910721216E+940 the returning value of the method
     * <b>byteValueExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testByteValueExact012() {
        try {
            bigDec = new BigDecimal("-1.3889313184910721216E+940");
            bigDec.byteValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /**
     * This method test that for a BigInteger whose value is
     * -1.3889313184910721216E-1005 the returning value of the method
     * <b>byteValueExact()</b> should throw <i>ArithmeticException</i>
     */
    public void testByteValueExact013() {
        try {
            bigDec = new BigDecimal("-1.3889313184910721216E-1005");
            bigDec.byteValueExact();
            fail(msgRaise + "ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    /*
     * Test method for 'java.math.BigDecimal.ulp()'
     */
    public final void testUlp001() {
        String number = "5.903274854004009467754255123E+1481169160";
        if (log) {
            System.out.println("testUlp001");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E+1481169133");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E+1481169133", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp002() {
        String number = "-5.903274854004009467754255123E+1495404869";
        if (log) {
            System.out.println("testUlp002");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E+1495404842");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E+1495404842", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp003() {
        String number = "6.6489516776676738215758452438E-1495404814";
        if (log) {
            System.out.println("testUlp003");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E-1495404842");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E-1495404842", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp004() {
        String number = "-6.6489516776676738215758452438E-1495404814";
        if (log) {
            System.out.println("testUlp004");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E-1495404842");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E-1495404842", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp005() {
        String number = "-6.6489516776676738215758452438E-1495404814";
        if (log) {
            System.out.println("testUlp005");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E-1495404842");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E-1495404842", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp006() {
        String number = "6.6489516776676738215758452438E-1495404814";
        if (log) {
            System.out.println("testUlp006");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E-1495404842");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E-1495404842", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp007() {
        String number = "-6.6489516776676738215758452438E-1495404814";
        if (log) {
            System.out.println("testUlp007");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E-1495404842");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E-1495404842", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp008() {
        String number = "6.6489516776676738215758452438E-1495404814";
        if (log) {
            System.out.println("testUlp008");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E-1495404842");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E-1495404842", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp009() {
        String number = "-6.6489516776676738215758452438E-1495404814";
        if (log) {
            System.out.println("testUlp009");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E-1495404842");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E-1495404842", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp010() {
        String number = "6.6489516776676738215758452438E+1495404870";
        if (log) {
            System.out.println("testUlp010");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E+1495404842");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E+1495404842", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp011() {
        String number = "6.6489516776676738215758452438E+1495404870";
        if (log) {
            System.out.println("testUlp011");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E+1495404842");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E+1495404842", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp012() {
        String number = "6.6489516776676738215758452438E+1495404870";
        if (log) {
            System.out.println("testUlp012");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E+1495404842");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E+1495404842", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp013() {
        String number = "6.6489606853502513423422450153E+1495789619";
        if (log) {
            System.out.println("testUlp013");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E+1495789591");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E+1495789591", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp014() {
        String number = "-7.2392791630680747683512707561E+1495789619";
        if (log) {
            System.out.println("testUlp014");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E+1495789591");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E+1495789591", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp015() {
        String number = "-7.2392791630680747683512707561E+1495789619";
        if (log) {
            System.out.println("testUlp015");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E+1495789591");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E+1495789591", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp016() {
        String number = "-7.2392791630680747683512707561E+1495789619";
        if (log) {
            System.out.println("testUlp016");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E+1495789591");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E+1495789591", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp017() {
        String number = "7.2392791630680747683512707561E+1495789619";
        if (log) {
            System.out.println("testUlp017");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E+1495789591");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E+1495789591", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp018() {
        String number = "-7.2392791630680747683512707561E+1495789619";
        if (log) {
            System.out.println("testUlp018");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E+1495789591");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E+1495789591", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp019() {
        String number = "-7.2392791630680747683512707561E+1495789619";
        if (log) {
            System.out.println("testUlp019");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E+1495789591");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E+1495789591", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp020() {
        String number = "-7.2392791630680747683512707561E-1495789563";
        if (log) {
            System.out.println("testUlp020");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E-1495789591");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E-1495789591", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp021() {
        String number = "7.2392791630680747683512707561E-1495789563";
        if (log) {
            System.out.println("testUlp021");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E-1495789591");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E-1495789591", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp022() {
        String number = "-7.2392791630680747683512707561E-1495789563";
        if (log) {
            System.out.println("testUlp022");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E-1495789591");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E-1495789591", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp023() {
        String number = "-7.2392791630680747683512707561E-1495789563";
        if (log) {
            System.out.println("testUlp023");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E-1495789591");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E-1495789591", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp024() {
        String number = "-7.2392791630680747683512707504E-1494635316";
        if (log) {
            System.out.println("testUlp024");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E-1494635344");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E-1494635344", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp025() {
        String number = "-5.4682967068668719280249942192E-1494635316";
        if (log) {
            System.out.println("testUlp025");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E-1494635344");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E-1494635344", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp026() {
        String number = "5.4682967068668719280249942192E-1494635316";
        if (log) {
            System.out.println("testUlp026");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E-1494635344");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E-1494635344", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp027() {
        String number = "-5.4682967068668719280249942192E+1494635372";
        if (log) {
            System.out.println("testUlp027");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E+1494635344");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E+1494635344", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp028() {
        String number = "-5.4682967068668719280249942192E+1494635372";
        if (log) {
            System.out.println("testUlp028");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E+1494635344");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E+1494635344", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp029() {
        String number = "-5.4682967068668719280249942192E-1494635316";
        if (log) {
            System.out.println("testUlp029");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E-1494635344");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E-1494635344", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUlp030() {
        String number = "-5.4682967068668719280249942192E+1494635372";
        if (log) {
            System.out.println("testUlp030");
        }
        bigDec = new BigDecimal(number);
        if (log) {
            System.out.println("-> expected result: 1E+1494635344");
            System.out.println("-> actual result: " + bigDec.ulp().toString());
        }
        assertEquals(msgNotSame, "1E+1494635344", bigDec.ulp().toString());
        if (log) {
            System.out.println("---Test PASSED---");
        }
    }
    
}
