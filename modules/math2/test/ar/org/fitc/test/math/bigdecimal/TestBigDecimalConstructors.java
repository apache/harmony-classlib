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
import java.math.BigInteger;
import java.math.MathContext;
//import java.math.MathContext;

import ar.org.fitc.test.util.Messages;
import junit.framework.TestCase;
/**
 * Test cases for all BigDecimal constructors
 * BigDecimal(BigInteger val) 
 * BigDecimal(BigInteger unscaledVal, int scale) 
 * BigDecimal(BigInteger unscaledVal, int scale, MathContext mc) 
 * BigDecimal(BigInteger val, MathContext mc) 
 * BigDecimal(char[] in) 
 * BigDecimal(char[] in, int offset, int len) 
 * BigDecimal(char[] in, int offset, int len, MathContext mc) 
 * BigDecimal(char[] in, MathContext mc)
 * BigDecimal(double val) 
 * BigDecimal(double val, MathContext mc) 
 * BigDecimal(int val) 
 * BigDecimal(int val, MathContext mc) 
 * BigDecimal(long val) 
 * BigDecimal(long val, MathContext mc) 
 * BigDecimal(String val) 
 * BigDecimal(String val, MathContext mc) 
 *
 */
public class TestBigDecimalConstructors extends TestCase implements Messages {
    private BigDecimal bigDec = null;
    private BigInteger bigInt = null;
    private MathContext mc = null;
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestBigDecimalConstructors.class);
    }
    
    public TestBigDecimalConstructors(String name) {
        super(name);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    /*
     * Test method for 'java.math.BigDecimal.BigDecimal(BigInteger)'
     */
    
    public final void testBigDecimalBigInteger001() {
        bigInt = new BigInteger("77089079937690213141084990824748539289249680275229777575376149105023231217339");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger002() {
        bigInt = new BigInteger("88343168761361153868327347126405166826546295513768410687417800425234677695959");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger003() {
        bigInt = new BigInteger("104978112960099378507306426092817848021685314965156404374847485822729335315121");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger004() {
        bigInt = new BigInteger("83733986863945359078693405648497737787616573147337517842616504489037569925211");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger005() {
        bigInt = new BigInteger("91117941565795211703923926531107374440016721094656828072635143314106628329399");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger006() {
        bigInt = new BigInteger("91724819336688705410903751379236941980738526502785551584722923902845631630943");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger007() {
        bigInt = new BigInteger("60367696714858821724099529070220391956318072598227180287687244301373759769987");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger008() {
        bigInt = new BigInteger("74224046992196307220734228610532940059474525410445688521847972771102641723581");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger009() {
        bigInt = new BigInteger("75023780732441721132688426355685684877517954787999942812563589633986834860421");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger010() {
        bigInt = new BigInteger("90051515204753194570632459110126062974830492654238264130562072551877513349679");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger011() {
        bigInt = new BigInteger("96502171492741104322502990458614411354946081533113006183637704406043725059077");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger012() {
        bigInt = new BigInteger("86620588547464260272339852056471260656045755687094844692410687550666763837643");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger013() {
        bigInt = new BigInteger("105635774829327369028629802467592341858499290781774835007616324752582691982777");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger014() {
        bigInt = new BigInteger("87968984310983599344805623584239075672401576435650206235799813771101745596981");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger015() {
        bigInt = new BigInteger("81219130919608947732779797999108617374690570853013858861964161768440477111683");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger016() {
        bigInt = null;
        try {
            bigDec = new BigDecimal(bigInt);
            fail(msgRaise+"NullPointerException");
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            fail(msgRaise+"NullPointerException");
        }
    }
    
    
    public final void testBigDecimalBigInteger017() {
        bigInt = new BigInteger("-77089079937690213141084990824748539289249680275229777575376149105023231217339");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger018() {
        bigInt = new BigInteger("-88343168761361153868327347126405166826546295513768410687417800425234677695959");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger019() {
        bigInt = new BigInteger("-104978112960099378507306426092817848021685314965156404374847485822729335315121");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger020() {
        bigInt = new BigInteger("-83733986863945359078693405648497737787616573147337517842616504489037569925211");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger021() {
        bigInt = new BigInteger("-91117941565795211703923926531107374440016721094656828072635143314106628329399");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger022() {
        bigInt = new BigInteger("-91724819336688705410903751379236941980738526502785551584722923902845631630943");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger023() {
        bigInt = new BigInteger("-60367696714858821724099529070220391956318072598227180287687244301373759769987");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger024() {
        bigInt = new BigInteger("-74224046992196307220734228610532940059474525410445688521847972771102641723581");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger025() {
        bigInt = new BigInteger("-75023780732441721132688426355685684877517954787999942812563589633986834860421");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger026() {
        bigInt = new BigInteger("-90051515204753194570632459110126062974830492654238264130562072551877513349679");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger027() {
        bigInt = new BigInteger("-96502171492741104322502990458614411354946081533113006183637704406043725059077");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger028() {
        bigInt = new BigInteger("-86620588547464260272339852056471260656045755687094844692410687550666763837643");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger029() {
        bigInt = new BigInteger("-105635774829327369028629802467592341858499290781774835007616324752582691982777");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger030() {
        bigInt = new BigInteger("-87968984310983599344805623584239075672401576435650206235799813771101745596981");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger031() {
        bigInt = new BigInteger("-81219130919608947732779797999108617374690570853013858861964161768440477111683");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    
    public final void testBigDecimalBigInteger032() {
        bigInt = new BigInteger("-9005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger033() {
        bigInt = new BigInteger("9005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679900515152047531945706324591101260629748304926542382641305620725518775133496799005151520475319457063245911012606297483049265423826413056207255187751334967990051515204753194570632459110126062974830492654238264130562072551877513349679");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger034() {
        bigInt = new BigInteger("0");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger035() {
        bigInt = new BigInteger("-105635");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigInteger036() {
        bigInt = new BigInteger("105635");
        bigDec = new BigDecimal(bigInt);
        assertNotNull(msgNotNull,bigDec);
    }

    /*
     * Test method for 'java.math.BigDecimal.BigDecimal(BigInteger, MathContext)'
     */
    
    public final void testBigDecimalBigIntegerMathContext001() {
        bigInt = new BigInteger("278511430822191999274037076925655647697");
        mc = new MathContext("precision=51011 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext002() {
        bigInt = new BigInteger("269415616005595051448085613700356271273");
        mc = new MathContext("precision=40420 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext003() {
        bigInt = new BigInteger("206247933410675161704648326634723714253");
        mc = new MathContext("precision=36785 roundingMode=FLOOR");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext004() {
        bigInt = new BigInteger("204211981592488594741576912596516786823");
        mc = new MathContext("precision=58363 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext005() {
        bigInt = new BigInteger("220005958807661039913992853795533444579");
        mc = new MathContext("precision=61182 roundingMode=FLOOR");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext006() {
        bigInt = new BigInteger("185017049552201911926650150192149353601");
        mc = new MathContext("precision=63150 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext007() {
        bigInt = new BigInteger("287392961772313086462563033648932385953");
        mc = new MathContext("precision=95750 roundingMode=FLOOR");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext008() {
        bigInt = new BigInteger("261489112681513504212350854464408808049");
        mc = new MathContext("precision=75205 roundingMode=HALF_UP");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext009() {
        bigInt = new BigInteger("336712050127258834613292337119594766889");
        mc = new MathContext("precision=95525 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext010() {
        bigInt = new BigInteger("173418767331564101058209098090410423803");
        mc = new MathContext("precision=40124 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext011() {
        bigInt = new BigInteger("284853683485212755775966380607448052347");
        mc = new MathContext("precision=99226 roundingMode=HALF_UP");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext012() {
        bigInt = new BigInteger("316132596802549229559664527498777545747");
        mc = new MathContext("precision=66234 roundingMode=FLOOR");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext013() {
        bigInt = new BigInteger("269777586283976907079942781433135528349");
        mc = new MathContext("precision=9148 roundingMode=DOWN");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext014() {
        bigInt = new BigInteger("187837348939329050532039323787210333413");
        mc = new MathContext("precision=74173 roundingMode=HALF_UP");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext015() {
        bigInt = new BigInteger("208655965878569946464850767143381885661");
        mc = new MathContext("precision=72511 roundingMode=DOWN");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext016() {
        bigInt = new BigInteger("123454");
        mc = new MathContext("precision=4 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(bigInt,mc);
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
        } catch (Throwable e) {
            fail(msgRaise+"ArithmeticException");
        }
    }
    
    public final void testBigDecimalBigIntegerMathContext017() {
        bigInt = new BigInteger("1940101455");
        mc = new MathContext("precision=1 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(bigInt,mc);
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
        } catch (Throwable e) {
            fail(msgRaise+"ArithmeticException");
        }
    }
    

    
    public final void testBigDecimalBigIntegerMathContext018() {
        bigInt = new BigInteger("-278511430822191999274037076925655647697");
        mc = new MathContext("precision=51011 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext019() {
        bigInt = new BigInteger("-269415616005595051448085613700356271273");
        mc = new MathContext("precision=40420 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext020() {
        bigInt = new BigInteger("-206247933410675161704648326634723714253");
        mc = new MathContext("precision=36785 roundingMode=FLOOR");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext021() {
        bigInt = new BigInteger("-204211981592488594741576912596516786823");
        mc = new MathContext("precision=58363 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext022() {
        bigInt = new BigInteger("-220005958807661039913992853795533444579");
        mc = new MathContext("precision=61182 roundingMode=FLOOR");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext023() {
        bigInt = new BigInteger("-185017049552201911926650150192149353601");
        mc = new MathContext("precision=63150 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext024() {
        bigInt = new BigInteger("-287392961772313086462563033648932385953");
        mc = new MathContext("precision=95750 roundingMode=FLOOR");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext025() {
        bigInt = new BigInteger("-261489112681513504212350854464408808049");
        mc = new MathContext("precision=75205 roundingMode=HALF_UP");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext026() {
        bigInt = new BigInteger("-336712050127258834613292337119594766889");
        mc = new MathContext("precision=95525 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext027() {
        bigInt = new BigInteger("-173418767331564101058209098090410423803");
        mc = new MathContext("precision=40124 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext028() {
        bigInt = new BigInteger("-284853683485212755775966380607448052347");
        mc = new MathContext("precision=99226 roundingMode=HALF_UP");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext029() {
        bigInt = new BigInteger("-316132596802549229559664527498777545747");
        mc = new MathContext("precision=66234 roundingMode=FLOOR");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext030() {
        bigInt = new BigInteger("-269777586283976907079942781433135528349");
        mc = new MathContext("precision=9148 roundingMode=DOWN");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext031() {
        bigInt = new BigInteger("-187837348939329050532039323787210333413");
        mc = new MathContext("precision=74173 roundingMode=HALF_UP");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext032() {
        bigInt = new BigInteger("-208655965878569946464850767143381885661");
        mc = new MathContext("precision=72511 roundingMode=DOWN");
        bigDec = new BigDecimal(bigInt,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerMathContext033() {
        bigInt = new BigInteger("-123454");
        mc = new MathContext("precision=4 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(bigInt,mc);
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
        } catch (Throwable e) {
            fail(msgRaise+"ArithmeticException");
        }
    }
    
    public final void testBigDecimalBigIntegerMathContext034() {
        bigInt = new BigInteger("-1940101455");
        mc = new MathContext("precision=1 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(bigInt,mc);
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
        } catch (Throwable e) {
            fail(msgRaise+"ArithmeticException");
        }
    }
    
    
    /*
     * Test method for 'java.math.BigDecimal.BigDecimal(BigInteger, int)'
     */
    
    public final void testBigDecimalBigIntegerInt001() {
        bigInt= new BigInteger("254844528390385394834554994452439562143");
        bigDec = new BigDecimal(bigInt,629287027);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt002() {
        bigInt= new BigInteger("254844528390385394834554994452439562143");
        bigDec = new BigDecimal(bigInt,775353691);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt003() {
        bigInt= new BigInteger("254844528390385394834554994452439562143");
        bigDec = new BigDecimal(bigInt,-1722820917);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt004() {
        bigInt= new BigInteger("254844528390385394834554994452439562143");
        bigDec = new BigDecimal(bigInt,-593582895);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt005() {
        bigInt= new BigInteger("254844528390385394834554994452439562143");
        bigDec = new BigDecimal(bigInt,-268070002);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt006() {
        bigInt= new BigInteger("181043134400443572827109779991064432187");
        bigDec = new BigDecimal(bigInt,629287027);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt007() {
        bigInt= new BigInteger("181043134400443572827109779991064432187");
        bigDec = new BigDecimal(bigInt,775353691);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt008() {
        bigInt= new BigInteger("181043134400443572827109779991064432187");
        bigDec = new BigDecimal(bigInt,-1722820917);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt009() {
        bigInt= new BigInteger("181043134400443572827109779991064432187");
        bigDec = new BigDecimal(bigInt,-593582895);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt010() {
        bigInt= new BigInteger("181043134400443572827109779991064432187");
        bigDec = new BigDecimal(bigInt,-268070002);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt011() {
        bigInt= new BigInteger("277468827147916433191307147455954183861");
        bigDec = new BigDecimal(bigInt,629287027);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt012() {
        bigInt= new BigInteger("277468827147916433191307147455954183861");
        bigDec = new BigDecimal(bigInt,775353691);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt013() {
        bigInt= new BigInteger("277468827147916433191307147455954183861");
        bigDec = new BigDecimal(bigInt,-1722820917);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt014() {
        bigInt= new BigInteger("277468827147916433191307147455954183861");
        bigDec = new BigDecimal(bigInt,-593582895);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt015() {
        bigInt= new BigInteger("277468827147916433191307147455954183861");
        bigDec = new BigDecimal(bigInt,-268070002);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt016() {
        bigInt= new BigInteger("209221485270421846620553522122110387419");
        bigDec = new BigDecimal(bigInt,629287027);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt017() {
        bigInt= new BigInteger("209221485270421846620553522122110387419");
        bigDec = new BigDecimal(bigInt,775353691);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt018() {
        bigInt= new BigInteger("209221485270421846620553522122110387419");
        bigDec = new BigDecimal(bigInt,-1722820917);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt019() {
        bigInt= new BigInteger("209221485270421846620553522122110387419");
        bigDec = new BigDecimal(bigInt,-593582895);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt020() {
        bigInt= new BigInteger("209221485270421846620553522122110387419");
        bigDec = new BigDecimal(bigInt,-268070002);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt021() {
        bigInt= new BigInteger("293061519916880550448821414388059317509");
        bigDec = new BigDecimal(bigInt,629287027);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt022() {
        bigInt= new BigInteger("293061519916880550448821414388059317509");
        bigDec = new BigDecimal(bigInt,775353691);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt023() {
        bigInt= new BigInteger("293061519916880550448821414388059317509");
        bigDec = new BigDecimal(bigInt,-1722820917);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt024() {
        bigInt= new BigInteger("293061519916880550448821414388059317509");
        bigDec = new BigDecimal(bigInt,-593582895);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt025() {
        bigInt= new BigInteger("293061519916880550448821414388059317509");
        bigDec = new BigDecimal(bigInt,-268070002);
        assertNotNull(msgNotNull,bigDec);
    }
    

    public final void testBigDecimalBigIntegerInt026() {
        bigInt= new BigInteger("-254844528390385394834554994452439562143");
        bigDec = new BigDecimal(bigInt,629287027);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt027() {
        bigInt= new BigInteger("-254844528390385394834554994452439562143");
        bigDec = new BigDecimal(bigInt,775353691);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt028() {
        bigInt= new BigInteger("-254844528390385394834554994452439562143");
        bigDec = new BigDecimal(bigInt,-1722820917);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt029() {
        bigInt= new BigInteger("-254844528390385394834554994452439562143");
        bigDec = new BigDecimal(bigInt,-593582895);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt030() {
        bigInt= new BigInteger("-254844528390385394834554994452439562143");
        bigDec = new BigDecimal(bigInt,-268070002);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt031() {
        bigInt= new BigInteger("-181043134400443572827109779991064432187");
        bigDec = new BigDecimal(bigInt,629287027);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt032() {
        bigInt= new BigInteger("-181043134400443572827109779991064432187");
        bigDec = new BigDecimal(bigInt,775353691);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt033() {
        bigInt= new BigInteger("-181043134400443572827109779991064432187");
        bigDec = new BigDecimal(bigInt,-1722820917);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt034() {
        bigInt= new BigInteger("-181043134400443572827109779991064432187");
        bigDec = new BigDecimal(bigInt,-593582895);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt035() {
        bigInt= new BigInteger("-181043134400443572827109779991064432187");
        bigDec = new BigDecimal(bigInt,-268070002);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt036() {
        bigInt= new BigInteger("-277468827147916433191307147455954183861");
        bigDec = new BigDecimal(bigInt,629287027);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt037() {
        bigInt= new BigInteger("-277468827147916433191307147455954183861");
        bigDec = new BigDecimal(bigInt,775353691);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt038() {
        bigInt= new BigInteger("-277468827147916433191307147455954183861");
        bigDec = new BigDecimal(bigInt,-1722820917);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt039() {
        bigInt= new BigInteger("-277468827147916433191307147455954183861");
        bigDec = new BigDecimal(bigInt,-593582895);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt040() {
        bigInt= new BigInteger("-277468827147916433191307147455954183861");
        bigDec = new BigDecimal(bigInt,-268070002);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt041() {
        bigInt= new BigInteger("-209221485270421846620553522122110387419");
        bigDec = new BigDecimal(bigInt,629287027);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt042() {
        bigInt= new BigInteger("-209221485270421846620553522122110387419");
        bigDec = new BigDecimal(bigInt,775353691);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt043() {
        bigInt= new BigInteger("-209221485270421846620553522122110387419");
        bigDec = new BigDecimal(bigInt,-1722820917);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt044() {
        bigInt= new BigInteger("-209221485270421846620553522122110387419");
        bigDec = new BigDecimal(bigInt,-593582895);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt045() {
        bigInt= new BigInteger("-209221485270421846620553522122110387419");
        bigDec = new BigDecimal(bigInt,-268070002);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt046() {
        bigInt= new BigInteger("-293061519916880550448821414388059317509");
        bigDec = new BigDecimal(bigInt,629287027);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt047() {
        bigInt= new BigInteger("-293061519916880550448821414388059317509");
        bigDec = new BigDecimal(bigInt,775353691);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt048() {
        bigInt= new BigInteger("-293061519916880550448821414388059317509");
        bigDec = new BigDecimal(bigInt,-1722820917);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt049() {
        bigInt= new BigInteger("-293061519916880550448821414388059317509");
        bigDec = new BigDecimal(bigInt,-593582895);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerInt050() {
        bigInt= new BigInteger("-293061519916880550448821414388059317509");
        bigDec = new BigDecimal(bigInt,-268070002);
        assertNotNull(msgNotNull,bigDec);
    }
    
    
    /*
     * Test method for 'java.math.BigDecimal.BigDecimal(BigInteger, int, MathContext)'
     */
    
    public final void testBigDecimalBigIntegerIntMathContext001() {
        bigInt= new BigInteger("308650510734066347591134849840340341721");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1823539149,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext002() {
        bigInt= new BigInteger("308650510734066347591134849840340341721");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1823539149,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext004() {
        bigInt= new BigInteger("308650510734066347591134849840340341721");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,861805670,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext005() {
        bigInt= new BigInteger("308650510734066347591134849840340341721");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,861805670,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext007() {
        bigInt= new BigInteger("308650510734066347591134849840340341721");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1185779568,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext008() {
        bigInt= new BigInteger("308650510734066347591134849840340341721");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1185779568,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext010() {
        bigInt= new BigInteger("308650510734066347591134849840340341721");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1877573415,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext011() {
        bigInt= new BigInteger("308650510734066347591134849840340341721");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1877573415,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext013() {
        bigInt= new BigInteger("308650510734066347591134849840340341721");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1275843894,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext014() {
        bigInt= new BigInteger("308650510734066347591134849840340341721");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1275843894,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext017() {
        bigInt= new BigInteger("215421092265303923412653888362987369273");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1823539149,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext018() {
        bigInt= new BigInteger("215421092265303923412653888362987369273");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1823539149,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext020() {
        bigInt= new BigInteger("215421092265303923412653888362987369273");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,861805670,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext021() {
        bigInt= new BigInteger("215421092265303923412653888362987369273");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,861805670,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext023() {
        bigInt= new BigInteger("215421092265303923412653888362987369273");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1185779568,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext024() {
        bigInt= new BigInteger("215421092265303923412653888362987369273");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1185779568,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext026() {
        bigInt= new BigInteger("215421092265303923412653888362987369273");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1877573415,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext027() {
        bigInt= new BigInteger("215421092265303923412653888362987369273");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1877573415,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext029() {
        bigInt= new BigInteger("215421092265303923412653888362987369273");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1275843894,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext030() {
        bigInt= new BigInteger("215421092265303923412653888362987369273");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1275843894,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext033() {
        bigInt= new BigInteger("235172508562351441347035042259524353357");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1823539149,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext034() {
        bigInt= new BigInteger("235172508562351441347035042259524353357");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1823539149,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext036() {
        bigInt= new BigInteger("235172508562351441347035042259524353357");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,861805670,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext037() {
        bigInt= new BigInteger("235172508562351441347035042259524353357");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,861805670,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext039() {
        bigInt= new BigInteger("235172508562351441347035042259524353357");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1185779568,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext040() {
        bigInt= new BigInteger("235172508562351441347035042259524353357");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1185779568,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext042() {
        bigInt= new BigInteger("235172508562351441347035042259524353357");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1877573415,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext043() {
        bigInt= new BigInteger("235172508562351441347035042259524353357");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1877573415,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext045() {
        bigInt= new BigInteger("235172508562351441347035042259524353357");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1275843894,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext046() {
        bigInt= new BigInteger("235172508562351441347035042259524353357");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1275843894,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext049() {
        bigInt= new BigInteger("286531249442933275457238713582764997047");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1823539149,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext050() {
        bigInt= new BigInteger("286531249442933275457238713582764997047");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1823539149,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext052() {
        bigInt= new BigInteger("286531249442933275457238713582764997047");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,861805670,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext053() {
        bigInt= new BigInteger("-286531249442933275457238713582764997047");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,861805670,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext055() {
        bigInt= new BigInteger("-286531249442933275457238713582764997047");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1185779568,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext056() {
        bigInt= new BigInteger("-286531249442933275457238713582764997047");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1185779568,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext058() {
        bigInt= new BigInteger("-286531249442933275457238713582764997047");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1877573415,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext059() {
        bigInt= new BigInteger("-286531249442933275457238713582764997047");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1877573415,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext061() {
        bigInt= new BigInteger("-286531249442933275457238713582764997047");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1275843894,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext062() {
        bigInt= new BigInteger("-286531249442933275457238713582764997047");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1275843894,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext063() {
        try {
            bigInt= new BigInteger("-609480933453452453253455312");
            mc= new MathContext("precision=5 roundingMode=UNNECESSARY");    
            bigDec = new BigDecimal(bigInt,1,mc);
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
        } catch (Throwable e) {
            fail(msgRaise+"ArithmeticException"+e);
        }
    }
    
    
    public final void testBigDecimalBigIntegerIntMathContext064() {
        bigInt= new BigInteger("-308650510734066347591134849840340341721");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1823539149,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext065() {
        bigInt= new BigInteger("-308650510734066347591134849840340341721");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1823539149,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext066() {
        bigInt= new BigInteger("-308650510734066347591134849840340341721");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,861805670,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext067() {
        bigInt= new BigInteger("-308650510734066347591134849840340341721");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,861805670,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext068() {
        bigInt= new BigInteger("-308650510734066347591134849840340341721");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1185779568,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext069() {
        bigInt= new BigInteger("-308650510734066347591134849840340341721");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1185779568,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext070() {
        bigInt= new BigInteger("-308650510734066347591134849840340341721");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1877573415,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext071() {
        bigInt= new BigInteger("-308650510734066347591134849840340341721");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1877573415,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext072() {
        bigInt= new BigInteger("-308650510734066347591134849840340341721");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1275843894,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext073() {
        bigInt= new BigInteger("-308650510734066347591134849840340341721");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1275843894,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext074() {
        bigInt= new BigInteger("-215421092265303923412653888362987369273");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1823539149,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext075() {
        bigInt= new BigInteger("-215421092265303923412653888362987369273");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1823539149,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext076() {
        bigInt= new BigInteger("-215421092265303923412653888362987369273");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,861805670,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext077() {
        bigInt= new BigInteger("-215421092265303923412653888362987369273");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,861805670,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext078() {
        bigInt= new BigInteger("-215421092265303923412653888362987369273");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1185779568,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext079() {
        bigInt= new BigInteger("-215421092265303923412653888362987369273");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1185779568,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext080() {
        bigInt= new BigInteger("-215421092265303923412653888362987369273");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1877573415,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext081() {
        bigInt= new BigInteger("-215421092265303923412653888362987369273");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1877573415,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext082() {
        bigInt= new BigInteger("-215421092265303923412653888362987369273");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1275843894,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext083() {
        bigInt= new BigInteger("-215421092265303923412653888362987369273");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1275843894,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext084() {
        bigInt= new BigInteger("-235172508562351441347035042259524353357");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1823539149,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext085() {
        bigInt= new BigInteger("-235172508562351441347035042259524353357");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1823539149,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext086() {
        bigInt= new BigInteger("-235172508562351441347035042259524353357");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,861805670,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext087() {
        bigInt= new BigInteger("-235172508562351441347035042259524353357");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,861805670,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext088() {
        bigInt= new BigInteger("-235172508562351441347035042259524353357");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1185779568,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext089() {
        bigInt= new BigInteger("-235172508562351441347035042259524353357");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1185779568,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext090() {
        bigInt= new BigInteger("-235172508562351441347035042259524353357");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1877573415,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext091() {
        bigInt= new BigInteger("-235172508562351441347035042259524353357");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1877573415,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext092() {
        bigInt= new BigInteger("-235172508562351441347035042259524353357");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1275843894,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext093() {
        bigInt= new BigInteger("-235172508562351441347035042259524353357");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1275843894,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext094() {
        bigInt= new BigInteger("286531249442933275457238713582764997047");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1823539149,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext095() {
        bigInt= new BigInteger("-286531249442933275457238713582764997047");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1823539149,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext096() {
        bigInt= new BigInteger("-286531249442933275457238713582764997047");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,861805670,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext097() {
        bigInt= new BigInteger("-286531249442933275457238713582764997047");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,861805670,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext098() {
        bigInt= new BigInteger("-286531249442933275457238713582764997047");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1185779568,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext099() {
        bigInt= new BigInteger("-286531249442933275457238713582764997047");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1185779568,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext100() {
        bigInt= new BigInteger("286531249442933275457238713582764997047");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1877573415,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext101() {
        bigInt= new BigInteger("-286531249442933275457238713582764997047");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,1877573415,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext102() {
        bigInt= new BigInteger("-286531249442933275457238713582764997047");
        mc= new MathContext("precision=1363692349 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1275843894,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext103() {
        bigInt= new BigInteger("-286531249442933275457238713582764997047");
        mc= new MathContext("precision=175609435 roundingMode=CEILING");
        bigDec = new BigDecimal(bigInt,-1275843894,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalBigIntegerIntMathContext104() {
        try {
            bigInt= new BigInteger("-609480933453452453253455312");
            mc= new MathContext("precision=5 roundingMode=UNNECESSARY");    
            bigDec = new BigDecimal(bigInt,1,mc);
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
        } catch (Throwable e) {
            fail(msgRaise+"ArithmeticException"+e);
        }
    }
    /*
     * Test method for 'java.math.BigDecimal.BigDecimal(int)'
     */
    public final void testBigDecimalInt001() {
        bigDec = new BigDecimal(430574088);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalInt002() {
        bigDec = new BigDecimal(-947703742);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalInt003() {
        bigDec = new BigDecimal(-628346830);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalInt004() {
        bigDec = new BigDecimal(2076822686);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalInt005() {
        bigDec = new BigDecimal(-1991127435);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalInt006() {
        bigDec = new BigDecimal(1510502954);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalInt007() {
        bigDec = new BigDecimal(-1920302989);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalInt008() {
        bigDec = new BigDecimal(1106501808);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalInt009() {
        bigDec = new BigDecimal(1407390772);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalInt010() {
        bigDec = new BigDecimal(-554843943);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalInt011() {
        bigDec = new BigDecimal(-1054232691);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalInt012() {
        bigDec = new BigDecimal(1578234608);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalInt013() {
        bigDec = new BigDecimal(-2006470894);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalInt014() {
        bigDec = new BigDecimal(-1313138052);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalInt015() {
        bigDec = new BigDecimal(-990703150);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalInt016() {
        bigDec = new BigDecimal(138534872);
        assertNotNull(msgNotNull,bigDec);
    }
    
    /*
     * Test method for 'java.math.BigDecimal.BigDecimal(int, MathContext)'
     */
    public final void testBigDecimalIntMathContext001() {
        mc= new MathContext("precision=210455706 roundingMode=HALF_UP");
        bigDec = new BigDecimal(-413982479,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext002() {
        mc= new MathContext("precision=1077100253 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(-413982479,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext003() {
        mc= new MathContext("precision=732074731 roundingMode=HALF_UP");
        bigDec = new BigDecimal(-413982479,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext004() {
        mc= new MathContext("precision=1678554977 roundingMode=FLOOR");
        bigDec = new BigDecimal(-413982479,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext005() {
        mc= new MathContext("precision=898701807 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(-413982479,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext007() {
        mc= new MathContext("precision=210455706 roundingMode=HALF_UP");
        bigDec = new BigDecimal(-350037541,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext008() {
        mc= new MathContext("precision=1077100253 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(-350037541,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext009() {
        mc= new MathContext("precision=732074731 roundingMode=HALF_UP");
        bigDec = new BigDecimal(-350037541,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext010() {
        mc= new MathContext("precision=1678554977 roundingMode=FLOOR");
        bigDec = new BigDecimal(-350037541,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext011() {
        mc= new MathContext("precision=898701807 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(-350037541,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext013() {
        mc= new MathContext("precision=210455706 roundingMode=HALF_UP");
        bigDec = new BigDecimal(41468274,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext014() {
        mc= new MathContext("precision=1077100253 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(41468274,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext015() {
        mc= new MathContext("precision=732074731 roundingMode=HALF_UP");
        bigDec = new BigDecimal(41468274,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext016() {
        mc= new MathContext("precision=1678554977 roundingMode=FLOOR");
        bigDec = new BigDecimal(41468274,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext017() {
        mc= new MathContext("precision=898701807 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(41468274,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext019() {
        mc= new MathContext("precision=210455706 roundingMode=HALF_UP");
        bigDec = new BigDecimal(1167628304,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext020() {
        mc= new MathContext("precision=1077100253 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(1167628304,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext021() {
        mc= new MathContext("precision=732074731 roundingMode=HALF_UP");
        bigDec = new BigDecimal(1167628304,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext022() {
        mc= new MathContext("precision=1678554977 roundingMode=FLOOR");
        bigDec = new BigDecimal(1167628304,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext023() {
        mc= new MathContext("precision=898701807 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(1167628304,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext025() {
        mc= new MathContext("precision=210455706 roundingMode=HALF_UP");
        bigDec = new BigDecimal(1865578133,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext026() {
        mc= new MathContext("precision=1077100253 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(1865578133,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext027() {
        mc= new MathContext("precision=732074731 roundingMode=HALF_UP");
        bigDec = new BigDecimal(1865578133,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext028() {
        mc= new MathContext("precision=1678554977 roundingMode=FLOOR");
        bigDec = new BigDecimal(1865578133,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext029() {
        mc= new MathContext("precision=898701807 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(1865578133,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext031() {
        mc= new MathContext("precision=210455706 roundingMode=HALF_UP");
        bigDec = new BigDecimal(-2105415265,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext032() {
        mc= new MathContext("precision=1077100253 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(-2105415265,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext033() {
        mc= new MathContext("precision=732074731 roundingMode=HALF_UP");
        bigDec = new BigDecimal(-2105415265,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext034() {
        mc= new MathContext("precision=1678554977 roundingMode=FLOOR");
        bigDec = new BigDecimal(-2105415265,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext035() {
        mc= new MathContext("precision=898701807 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(-2105415265,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext037() {
        mc= new MathContext("precision=210455706 roundingMode=HALF_UP");
        bigDec = new BigDecimal(-990028203,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext038() {
        mc= new MathContext("precision=1077100253 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(-990028203,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext039() {
        mc= new MathContext("precision=732074731 roundingMode=HALF_UP");
        bigDec = new BigDecimal(-990028203,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext040() {
        mc= new MathContext("precision=1678554977 roundingMode=FLOOR");
        bigDec = new BigDecimal(-990028203,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext041() {
        mc= new MathContext("precision=898701807 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(-990028203,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext043() {
        mc= new MathContext("precision=210455706 roundingMode=HALF_UP");
        bigDec = new BigDecimal(-272071432,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext044() {
        mc= new MathContext("precision=1077100253 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(-272071432,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext045() {
        mc= new MathContext("precision=732074731 roundingMode=HALF_UP");
        bigDec = new BigDecimal(-272071432,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext046() {
        mc= new MathContext("precision=1678554977 roundingMode=FLOOR");
        bigDec = new BigDecimal(-272071432,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext047() {
        mc= new MathContext("precision=898701807 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(-272071432,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext049() {
        mc= new MathContext("precision=210455706 roundingMode=HALF_UP");
        bigDec = new BigDecimal(28817532,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext050() {
        mc= new MathContext("precision=1077100253 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(28817532,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext051() {
        mc= new MathContext("precision=732074731 roundingMode=HALF_UP");
        bigDec = new BigDecimal(28817532,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext052() {
        mc= new MathContext("precision=1678554977 roundingMode=FLOOR");
        bigDec = new BigDecimal(28817532,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext053() {
        mc= new MathContext("precision=898701807 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(28817532,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext055() {
        mc= new MathContext("precision=210455706 roundingMode=HALF_UP");
        bigDec = new BigDecimal(725228366,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext056() {
        mc= new MathContext("precision=1077100253 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(725228366,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext057() {
        mc= new MathContext("precision=732074731 roundingMode=HALF_UP");
        bigDec = new BigDecimal(725228366,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext058() {
        mc= new MathContext("precision=1678554977 roundingMode=FLOOR");
        bigDec = new BigDecimal(725228366,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext059() {
        mc= new MathContext("precision=898701807 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(725228366,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalIntMathContext060() {
        mc = new MathContext("precision=1 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(834510306,mc);
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
        } catch (Throwable e) {
            fail(msgRaise+"ArithmeticException");
        }
    }
    
    /*
     * Test method for java.math.BigDecimal(long val)'
     */
    
    public final void testBigDecimalLong001() {
        long longnumber = 1307265556;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong002() {
        long longnumber = -800201334;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong003() {
        long longnumber = -190109334;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong004() {
        long longnumber = 177216786;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong005() {
        long longnumber = 786850183;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong006() {
        long longnumber = -123649915;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong007() {
        long longnumber = 383601053;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong008() {
        long longnumber = -347341415;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong009() {
        long longnumber = 52486753;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong010() {
        long longnumber = 115237527;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong011() {
        long longnumber = 37420239;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong012() {
        long longnumber = 299072244;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong013() {
        long longnumber = -924890262;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong014() {
        long longnumber = -547423585;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong015() {
        long longnumber = 25239896;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong016() {
        long longnumber = 1301855427;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong017() {
        long longnumber = 1822254647;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong018() {
        long longnumber = -181143794;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong019() {
        long longnumber = 482301728;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong020() {
        long longnumber = 1535157336;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong021() {
        long longnumber = 24587180;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong022() {
        long longnumber = 817863675;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong023() {
        long longnumber = 744430002;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong024() {
        long longnumber = 116159015;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLong025() {
        long longnumber = -1170632276;
        bigDec = new BigDecimal(longnumber);
        assertNotNull(msgNotNull,bigDec);
    }
    
    /*
     * Test method for java.math.BigDecimal(long,MathContext)'
     */
    
    public final void testBigDecimalLongMathContext001() {
        mc= new MathContext("precision=356890018 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(-115506630L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext002() {
        mc= new MathContext("precision=1939243279 roundingMode=DOWN");
        bigDec = new BigDecimal(-115506630L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext003() {
        mc= new MathContext("precision=1407685992 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(-115506630L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext004() {
        mc= new MathContext("precision=1556147890 roundingMode=CEILING");
        bigDec = new BigDecimal(-115506630L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext005() {
        mc= new MathContext("precision=1515933339 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(-115506630L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext007() {
        mc= new MathContext("precision=356890018 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(-846911395,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext008() {
        mc= new MathContext("precision=1939243279 roundingMode=DOWN");
        bigDec = new BigDecimal(-846911395L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext009() {
        mc= new MathContext("precision=1407685992 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(-846911395L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext010() {
        mc= new MathContext("precision=1556147890 roundingMode=CEILING");
        bigDec = new BigDecimal(-846911395L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext011() {
        mc= new MathContext("precision=1515933339 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(-846911395L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext013() {
        mc= new MathContext("precision=356890018 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(70432143L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext014() {
        mc= new MathContext("precision=1939243279 roundingMode=DOWN");
        bigDec = new BigDecimal(70432143L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext015() {
        mc= new MathContext("precision=1407685992 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(70432143L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext016() {
        mc= new MathContext("precision=1556147890 roundingMode=CEILING");
        bigDec = new BigDecimal(70432143L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext017() {
        mc= new MathContext("precision=1515933339 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(70432143L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext019() {
        mc= new MathContext("precision=356890018 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(1845065351L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext020() {
        mc= new MathContext("precision=1939243279 roundingMode=DOWN");
        bigDec = new BigDecimal(1845065351L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext021() {
        mc= new MathContext("precision=1407685992 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(1845065351L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext022() {
        mc= new MathContext("precision=1556147890 roundingMode=CEILING");
        bigDec = new BigDecimal(1845065351L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext023() {
        mc= new MathContext("precision=1515933339 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(1845065351L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext025() {
        mc= new MathContext("precision=356890018 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(-803298609L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext026() {
        mc= new MathContext("precision=1939243279 roundingMode=DOWN");
        bigDec = new BigDecimal(-803298609L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext027() {
        mc= new MathContext("precision=1407685992 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(-803298609L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext028() {
        mc= new MathContext("precision=1556147890 roundingMode=CEILING");
        bigDec = new BigDecimal(-803298609L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext029() {
        mc= new MathContext("precision=1515933339 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(-803298609L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext031() {
        mc= new MathContext("precision=356890018 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(-324457456L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext032() {
        mc= new MathContext("precision=1939243279 roundingMode=DOWN");
        bigDec = new BigDecimal(-324457456L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext033() {
        mc= new MathContext("precision=1407685992 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(-324457456L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext034() {
        mc= new MathContext("precision=1556147890 roundingMode=CEILING");
        bigDec = new BigDecimal(-324457456L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext035() {
        mc= new MathContext("precision=1515933339 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(-324457456L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext037() {
        mc= new MathContext("precision=356890018 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(895630341L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext038() {
        mc= new MathContext("precision=1939243279 roundingMode=DOWN");
        bigDec = new BigDecimal(895630341L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext039() {
        mc= new MathContext("precision=1407685992 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(895630341L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext040() {
        mc= new MathContext("precision=1556147890 roundingMode=CEILING");
        bigDec = new BigDecimal(895630341L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext041() {
        mc= new MathContext("precision=1515933339 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(895630341L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext043() {
        mc= new MathContext("precision=356890018 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(249246378L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext044() {
        mc= new MathContext("precision=1939243279 roundingMode=DOWN");
        bigDec = new BigDecimal(249246378L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext045() {
        mc= new MathContext("precision=1407685992 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(249246378L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext046() {
        mc= new MathContext("precision=1556147890 roundingMode=CEILING");
        bigDec = new BigDecimal(249246378L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext047() {
        mc= new MathContext("precision=1515933339 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(249246378L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext049() {
        mc= new MathContext("precision=356890018 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(204101552L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext050() {
        mc= new MathContext("precision=1939243279 roundingMode=DOWN");
        bigDec = new BigDecimal(204101552L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext051() {
        mc= new MathContext("precision=1407685992 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(204101552L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext052() {
        mc= new MathContext("precision=1556147890 roundingMode=CEILING");
        bigDec = new BigDecimal(204101552L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext053() {
        mc= new MathContext("precision=1515933339 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(204101552L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext055() {
        mc= new MathContext("precision=356890018 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(-714760701L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext056() {
        mc= new MathContext("precision=1939243279 roundingMode=DOWN");
        bigDec = new BigDecimal(-714760701L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext057() {
        mc= new MathContext("precision=1407685992 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(-714760701L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext058() {
        mc= new MathContext("precision=1556147890 roundingMode=CEILING");
        bigDec = new BigDecimal(-714760701L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext059() {
        mc= new MathContext("precision=1515933339 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(-714760701L,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalLongMathContext060() {
        mc = new MathContext("precision=1 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(834510306L,mc);
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
        } catch (Throwable e) {
            fail(msgRaise+"ArithmeticException");
        }
    }
    
    /*
     * Test method for 'java.math.BigDecimal.BigDecimal(String)'
     */
    
    public final void testBigDecimalString001() {
        String number = "192115837249031760228932488942734623597";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"192115837249031760228932488942734623597",bigDec.toString());
    }
    
    public final void testBigDecimalString002() {
        String number = "311149353146264503749941073441755899237";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"311149353146264503749941073441755899237",bigDec.toString());
    }
    
    public final void testBigDecimalString003() {
        String number = "219242290443667494745796463852425191471";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"219242290443667494745796463852425191471",bigDec.toString());
    }
    
    public final void testBigDecimalString004() {
        String number = "288981220754573311334418852631762100717";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"288981220754573311334418852631762100717",bigDec.toString());
    }
    
    public final void testBigDecimalString005() {
        String number = "212288206656228357260308034083605998663";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"212288206656228357260308034083605998663",bigDec.toString());
    }
    
    public final void testBigDecimalString006() {
        String number = "314405454959890280631545987847099435781";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"314405454959890280631545987847099435781",bigDec.toString());
    }
    
    public final void testBigDecimalString007() {
        String number = "222631550715033919735668068194625390099";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"222631550715033919735668068194625390099",bigDec.toString());
    }
    
    public final void testBigDecimalString008() {
        String number = "279036966881965043673406834272595131751";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"279036966881965043673406834272595131751",bigDec.toString());
    }
    
    public final void testBigDecimalString009() {
        String number = "172441499947388909403405505805168076607";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"172441499947388909403405505805168076607",bigDec.toString());
    }
    
    public final void testBigDecimalString010() {
        String number = "316195249115505390416042000556424710349";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"316195249115505390416042000556424710349",bigDec.toString());
    }
    
    public final void testBigDecimalString011() {
        String number = "200984279554177734863216197115445571867";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"200984279554177734863216197115445571867",bigDec.toString());
    }
    
    public final void testBigDecimalString012() {
        String number = "187677244754152549995592080121086458999";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"187677244754152549995592080121086458999",bigDec.toString());
    }
    
    public final void testBigDecimalString013() {
        String number = "285140769542737138715240053977793638719";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"285140769542737138715240053977793638719",bigDec.toString());
    }
    
    public final void testBigDecimalString014() {
        String number = "339995005541823634457474238646003616089";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"339995005541823634457474238646003616089",bigDec.toString());
    }
    
    public final void testBigDecimalString015() {
        String number = "247538933954628401563519841887624950823";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"247538933954628401563519841887624950823",bigDec.toString());
    }
    
    public final void testBigDecimalString016() {
        String number = "326564448211050388496899464803923086241";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"326564448211050388496899464803923086241",bigDec.toString());
    }
    
    public final void testBigDecimalString017() {
        String number = "170638061341139458301824949117596632067";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"170638061341139458301824949117596632067",bigDec.toString());
    }
    
    public final void testBigDecimalString018() {
        String number = "188227986105770600371684774829340775391";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"188227986105770600371684774829340775391",bigDec.toString());
    }
    
    public final void testBigDecimalString019() {
        String number = "265473087574996728598494522776523772289";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"265473087574996728598494522776523772289",bigDec.toString());
    }
    
    public final void testBigDecimalString020() {
        String number = "224250026276068084817900918527660428881";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"224250026276068084817900918527660428881",bigDec.toString());
    }
    
    public final void testBigDecimalString021() {
        String number = "251763564398821452294191421185396913047";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"251763564398821452294191421185396913047",bigDec.toString());
    }
    
    public final void testBigDecimalString022() {
        String number = "251480604178215619786575744303697040869";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"251480604178215619786575744303697040869",bigDec.toString());
    }
    
    public final void testBigDecimalString023() {
        String number = "277149143199194556708283337053609457473";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"277149143199194556708283337053609457473",bigDec.toString());
    }
    
    public final void testBigDecimalString024() {
        String number = "281369909880632332558895584726684502153";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"281369909880632332558895584726684502153",bigDec.toString());
    }
    
    public final void testBigDecimalString025() {
        String number = "337976362370712766562980252888391753399";
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
        assertEquals(msgNotSame,"337976362370712766562980252888391753399",bigDec.toString());
    }
    
    /*
     * Test method for java.math.BigDecimal(String,MathContext)'
     */
    
    public final void testBigDecimalStringMathContext001() {
        mc= new MathContext("precision=1672613385 roundingMode=HALF_UP");
        bigDec = new BigDecimal("9.620273992298627620577259884367507364874432377532530692720050260708797786816650368949253964062307534191854471201E+172086306",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext002() {
        mc= new MathContext("precision=812280600 roundingMode=DOWN");
        bigDec = new BigDecimal("9.620273992298627620577259884367507364874432377532530692720050260708797786816650368949253964062307534191854471201E+172086306",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext003() {
        mc= new MathContext("precision=113108240 roundingMode=CEILING");
        bigDec = new BigDecimal("9.620273992298627620577259884367507364874432377532530692720050260708797786816650368949253964062307534191854471201E+172086306",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext004() {
        mc= new MathContext("precision=1898493154 roundingMode=CEILING");
        bigDec = new BigDecimal("9.620273992298627620577259884367507364874432377532530692720050260708797786816650368949253964062307534191854471201E+172086306",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext006() {
        mc= new MathContext("precision=1672613385 roundingMode=HALF_UP");
        bigDec = new BigDecimal("1.5785548581340859312093303982188555911922341584092979354293686057839610961372072094313245118569548472004301937257E+1243677905",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext007() {
        mc= new MathContext("precision=812280600 roundingMode=DOWN");
        bigDec = new BigDecimal("1.5785548581340859312093303982188555911922341584092979354293686057839610961372072094313245118569548472004301937257E+1243677905",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext008() {
        mc= new MathContext("precision=113108240 roundingMode=CEILING");
        bigDec = new BigDecimal("1.5785548581340859312093303982188555911922341584092979354293686057839610961372072094313245118569548472004301937257E+1243677905",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext009() {
        mc= new MathContext("precision=1898493154 roundingMode=CEILING");
        bigDec = new BigDecimal("1.5785548581340859312093303982188555911922341584092979354293686057839610961372072094313245118569548472004301937257E+1243677905",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext011() {
        mc= new MathContext("precision=1672613385 roundingMode=HALF_UP");
        bigDec = new BigDecimal("1.7058332267681671700005587209984446941712288604104375593850486788877423341839010911668812436325419594011788306851E+1022490991",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext012() {
        mc= new MathContext("precision=812280600 roundingMode=DOWN");
        bigDec = new BigDecimal("1.7058332267681671700005587209984446941712288604104375593850486788877423341839010911668812436325419594011788306851E+1022490991",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext013() {
        mc= new MathContext("precision=113108240 roundingMode=CEILING");
        bigDec = new BigDecimal("1.7058332267681671700005587209984446941712288604104375593850486788877423341839010911668812436325419594011788306851E+1022490991",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext014() {
        mc= new MathContext("precision=1898493154 roundingMode=CEILING");
        bigDec = new BigDecimal("1.7058332267681671700005587209984446941712288604104375593850486788877423341839010911668812436325419594011788306851E+1022490991",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext016() {
        mc= new MathContext("precision=1672613385 roundingMode=HALF_UP");
        bigDec = new BigDecimal("9.798756496774511995293940656017543983691668075840411656635217087538400123003099117913765564374037293976464596007E-1266332771",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext017() {
        mc= new MathContext("precision=812280600 roundingMode=DOWN");
        bigDec = new BigDecimal("9.798756496774511995293940656017543983691668075840411656635217087538400123003099117913765564374037293976464596007E-1266332771",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext018() {
        mc= new MathContext("precision=113108240 roundingMode=CEILING");
        bigDec = new BigDecimal("9.798756496774511995293940656017543983691668075840411656635217087538400123003099117913765564374037293976464596007E-1266332771",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext019() {
        mc= new MathContext("precision=1898493154 roundingMode=CEILING");
        bigDec = new BigDecimal("9.798756496774511995293940656017543983691668075840411656635217087538400123003099117913765564374037293976464596007E-1266332771",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext021() {
        mc= new MathContext("precision=1672613385 roundingMode=HALF_UP");
        bigDec = new BigDecimal("1.8181799081931854966570051696789659548037583859960474471988076641797579021111277086987648466975334368585922350603E+657711071",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext022() {
        mc= new MathContext("precision=812280600 roundingMode=DOWN");
        bigDec = new BigDecimal("1.8181799081931854966570051696789659548037583859960474471988076641797579021111277086987648466975334368585922350603E+657711071",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext023() {
        mc= new MathContext("precision=113108240 roundingMode=CEILING");
        bigDec = new BigDecimal("1.8181799081931854966570051696789659548037583859960474471988076641797579021111277086987648466975334368585922350603E+657711071",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext024() {
        mc= new MathContext("precision=1898493154 roundingMode=CEILING");
        bigDec = new BigDecimal("1.8181799081931854966570051696789659548037583859960474471988076641797579021111277086987648466975334368585922350603E+657711071",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext026() {
        mc= new MathContext("precision=1672613385 roundingMode=HALF_UP");
        bigDec = new BigDecimal("1.5135721389891322978864338230990225045105542345187226090575963668988742904289112279122008747456034225968173904991E+1267931910",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext027() {
        mc= new MathContext("precision=812280600 roundingMode=DOWN");
        bigDec = new BigDecimal("1.5135721389891322978864338230990225045105542345187226090575963668988742904289112279122008747456034225968173904991E+1267931910",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext028() {
        mc= new MathContext("precision=113108240 roundingMode=CEILING");
        bigDec = new BigDecimal("1.5135721389891322978864338230990225045105542345187226090575963668988742904289112279122008747456034225968173904991E+1267931910",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext029() {
        mc= new MathContext("precision=1898493154 roundingMode=CEILING");
        bigDec = new BigDecimal("1.5135721389891322978864338230990225045105542345187226090575963668988742904289112279122008747456034225968173904991E+1267931910",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext031() {
        mc= new MathContext("precision=1672613385 roundingMode=HALF_UP");
        bigDec = new BigDecimal("1.1162301972418360473118704038795818771842533301616192892718731429451080629449547612010457233662217485377714243589E+2033010276",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext032() {
        mc= new MathContext("precision=812280600 roundingMode=DOWN");
        bigDec = new BigDecimal("1.1162301972418360473118704038795818771842533301616192892718731429451080629449547612010457233662217485377714243589E+2033010276",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext033() {
        mc= new MathContext("precision=113108240 roundingMode=CEILING");
        bigDec = new BigDecimal("1.1162301972418360473118704038795818771842533301616192892718731429451080629449547612010457233662217485377714243589E+2033010276",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext034() {
        mc= new MathContext("precision=1898493154 roundingMode=CEILING");
        bigDec = new BigDecimal("1.1162301972418360473118704038795818771842533301616192892718731429451080629449547612010457233662217485377714243589E+2033010276",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext036() {
        mc= new MathContext("precision=1672613385 roundingMode=HALF_UP");
        bigDec = new BigDecimal("1.3596070467548816539986286217367390403523973947108065973056075225192600826049563439706768573793776190822831702437E-509721685",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext037() {
        mc= new MathContext("precision=812280600 roundingMode=DOWN");
        bigDec = new BigDecimal("1.3596070467548816539986286217367390403523973947108065973056075225192600826049563439706768573793776190822831702437E-509721685",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext038() {
        mc= new MathContext("precision=113108240 roundingMode=CEILING");
        bigDec = new BigDecimal("1.3596070467548816539986286217367390403523973947108065973056075225192600826049563439706768573793776190822831702437E-509721685",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext039() {
        mc= new MathContext("precision=1898493154 roundingMode=CEILING");
        bigDec = new BigDecimal("1.3596070467548816539986286217367390403523973947108065973056075225192600826049563439706768573793776190822831702437E-509721685",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext041() {
        mc= new MathContext("precision=1672613385 roundingMode=HALF_UP");
        bigDec = new BigDecimal("1.1865391332229231091943263338710196162536238237864766716590760751133608962670168729281480592539581118549594415471E-1555351102",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext042() {
        mc= new MathContext("precision=812280600 roundingMode=DOWN");
        bigDec = new BigDecimal("1.1865391332229231091943263338710196162536238237864766716590760751133608962670168729281480592539581118549594415471E-1555351102",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext043() {
        mc= new MathContext("precision=113108240 roundingMode=CEILING");
        bigDec = new BigDecimal("1.1865391332229231091943263338710196162536238237864766716590760751133608962670168729281480592539581118549594415471E-1555351102",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext044() {
        mc= new MathContext("precision=1898493154 roundingMode=CEILING");
        bigDec = new BigDecimal("1.1865391332229231091943263338710196162536238237864766716590760751133608962670168729281480592539581118549594415471E-1555351102",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext046() {
        mc= new MathContext("precision=1672613385 roundingMode=HALF_UP");
        bigDec = new BigDecimal("1.7007301428130165024566893545350870521769078408752764594495970885121708063871975014250483311751147130316808564117E+1611385823",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext047() {
        mc= new MathContext("precision=812280600 roundingMode=DOWN");
        bigDec = new BigDecimal("1.7007301428130165024566893545350870521769078408752764594495970885121708063871975014250483311751147130316808564117E+1611385823",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext048() {
        mc= new MathContext("precision=113108240 roundingMode=CEILING");
        bigDec = new BigDecimal("1.7007301428130165024566893545350870521769078408752764594495970885121708063871975014250483311751147130316808564117E+1611385823",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext049() {
        mc= new MathContext("precision=1898493154 roundingMode=CEILING");
        bigDec = new BigDecimal("1.7007301428130165024566893545350870521769078408752764594495970885121708063871975014250483311751147130316808564117E+1611385823",mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalStringMathContext050() {
        mc = new MathContext("precision=1 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal("1.7007301428130165024566893545350870521769078408752764594495970885121708063871975014250483311751147130316808564117E+1611385823",mc);
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
        } catch (Throwable e) {
            fail(msgRaise+"ArithmeticException");
        }
    }
    
    public final void testBigDecimalStringMathContext051() {
        mc = new MathContext("precision=9999999 roundingMode=UP");
        try {
            bigDec = new BigDecimal("1+64117E+1611385823",mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        } catch (Throwable e) {
            fail(msgRaise+"NumberFormatException");
        }
    }
    
    public final void testBigDecimalDouble001() {
        double number = 2.80232001989931E8;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble002() {
        double number = -1.438171560950583E9;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble003() {
        double number = -5.1519860480211265E7;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble004() {
        double number = 1.9556358865869468E8;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble005() {
        double number = -1.2775558920467048E9;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble006() {
        double number = -3.28450422437506E8;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble007() {
        double number = -2.6920823552616215E8;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble008() {
        double number = 8.783661214219108E7;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble009() {
        double number = 1.021071975681916E9;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble010() {
        double number = -1.2701051369976935E9;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble011() {
        double number = -1.781437429610748E8;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble012() {
        double number = 4.8990838846773535E7;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble013() {
        double number = -1.0541044738622253E8;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble014() {
        double number = -1.0284838227739881E8;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble015() {
        double number = 2.743497319117667E8;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble016() {
        double number = -6.62800737967507E8;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble017() {
        double number = -5.1623701797659945E8;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble018() {
        double number = -3.972913761394835E8;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble019() {
        double number = -1.6164931208324033E8;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble020() {
        double number = 1.8541906357123607E8;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble021() {
        double number = 4.3620244316200024E8;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble022() {
        double number = -5.560045791646979E8;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble023() {
        double number = 4.726469365907547E8;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble024() {
        double number = 5335196.922424817;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDouble025() {
        double number = 1.340075708448495E9;
        bigDec = new BigDecimal(number);
        assertNotNull(msgNotNull,bigDec);
    }
    

    /*
     * Test method for java.math.BigDecimal(double,MathContext)'
     */
    
    public final void testBigDecimalDoubleMathContext001() {
        mc= new MathContext("precision=1309796138 roundingMode=DOWN");
        bigDec = new BigDecimal(-9.70287316707302E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext002() {
        mc= new MathContext("precision=666040078 roundingMode=FLOOR");
        bigDec = new BigDecimal(-9.70287316707302E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext003() {
        mc= new MathContext("precision=870723346 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(-9.70287316707302E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext004() {
        mc= new MathContext("precision=466273320 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(-9.70287316707302E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext005() {
        mc= new MathContext("precision=1270381998 roundingMode=FLOOR");
        bigDec = new BigDecimal(-9.70287316707302E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext007() {
        mc= new MathContext("precision=1309796138 roundingMode=DOWN");
        bigDec = new BigDecimal(1.609588913464221E9,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext008() {
        mc= new MathContext("precision=666040078 roundingMode=FLOOR");
        bigDec = new BigDecimal(1.609588913464221E9,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext009() {
        mc= new MathContext("precision=870723346 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(1.609588913464221E9,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext010() {
        mc= new MathContext("precision=466273320 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(1.609588913464221E9,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext011() {
        mc= new MathContext("precision=1270381998 roundingMode=FLOOR");
        bigDec = new BigDecimal(1.609588913464221E9,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext013() {
        mc= new MathContext("precision=1309796138 roundingMode=DOWN");
        bigDec = new BigDecimal(7.750155927508142E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext014() {
        mc= new MathContext("precision=666040078 roundingMode=FLOOR");
        bigDec = new BigDecimal(7.750155927508142E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext015() {
        mc= new MathContext("precision=870723346 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(7.750155927508142E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext016() {
        mc= new MathContext("precision=466273320 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(7.750155927508142E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext017() {
        mc= new MathContext("precision=1270381998 roundingMode=FLOOR");
        bigDec = new BigDecimal(7.750155927508142E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext019() {
        mc= new MathContext("precision=1309796138 roundingMode=DOWN");
        bigDec = new BigDecimal(-2.852665189725117E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext020() {
        mc= new MathContext("precision=666040078 roundingMode=FLOOR");
        bigDec = new BigDecimal(-2.852665189725117E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext021() {
        mc= new MathContext("precision=870723346 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(-2.852665189725117E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext022() {
        mc= new MathContext("precision=466273320 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(-2.852665189725117E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext023() {
        mc= new MathContext("precision=1270381998 roundingMode=FLOOR");
        bigDec = new BigDecimal(-2.852665189725117E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext025() {
        mc= new MathContext("precision=1309796138 roundingMode=DOWN");
        bigDec = new BigDecimal(1.9744437300409082E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext026() {
        mc= new MathContext("precision=666040078 roundingMode=FLOOR");
        bigDec = new BigDecimal(1.9744437300409082E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext027() {
        mc= new MathContext("precision=870723346 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(1.9744437300409082E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext028() {
        mc= new MathContext("precision=466273320 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(1.9744437300409082E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext029() {
        mc= new MathContext("precision=1270381998 roundingMode=FLOOR");
        bigDec = new BigDecimal(1.9744437300409082E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext031() {
        mc= new MathContext("precision=1309796138 roundingMode=DOWN");
        bigDec = new BigDecimal(9.295742215019443E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext032() {
        mc= new MathContext("precision=666040078 roundingMode=FLOOR");
        bigDec = new BigDecimal(9.295742215019443E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext033() {
        mc= new MathContext("precision=870723346 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(9.295742215019443E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext034() {
        mc= new MathContext("precision=466273320 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(9.295742215019443E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext035() {
        mc= new MathContext("precision=1270381998 roundingMode=FLOOR");
        bigDec = new BigDecimal(9.295742215019443E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext037() {
        mc= new MathContext("precision=1309796138 roundingMode=DOWN");
        bigDec = new BigDecimal(2.782344558278468E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext038() {
        mc= new MathContext("precision=666040078 roundingMode=FLOOR");
        bigDec = new BigDecimal(2.782344558278468E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext039() {
        mc= new MathContext("precision=870723346 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(2.782344558278468E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext040() {
        mc= new MathContext("precision=466273320 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(2.782344558278468E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext041() {
        mc= new MathContext("precision=1270381998 roundingMode=FLOOR");
        bigDec = new BigDecimal(2.782344558278468E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext043() {
        mc= new MathContext("precision=1309796138 roundingMode=DOWN");
        bigDec = new BigDecimal(1.1749210515272598E9,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext044() {
        mc= new MathContext("precision=666040078 roundingMode=FLOOR");
        bigDec = new BigDecimal(1.1749210515272598E9,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext045() {
        mc= new MathContext("precision=870723346 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(1.1749210515272598E9,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext046() {
        mc= new MathContext("precision=466273320 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(1.1749210515272598E9,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext047() {
        mc= new MathContext("precision=1270381998 roundingMode=FLOOR");
        bigDec = new BigDecimal(1.1749210515272598E9,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext049() {
        mc= new MathContext("precision=1309796138 roundingMode=DOWN");
        bigDec = new BigDecimal(-2.2775303274457833E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext050() {
        mc= new MathContext("precision=666040078 roundingMode=FLOOR");
        bigDec = new BigDecimal(-2.2775303274457833E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext051() {
        mc= new MathContext("precision=870723346 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(-2.2775303274457833E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext052() {
        mc= new MathContext("precision=466273320 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(-2.2775303274457833E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext053() {
        mc= new MathContext("precision=1270381998 roundingMode=FLOOR");
        bigDec = new BigDecimal(-2.2775303274457833E8,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext055() {
        mc= new MathContext("precision=1309796138 roundingMode=DOWN");
        bigDec = new BigDecimal(4.163922930480675E7,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext056() {
        mc= new MathContext("precision=666040078 roundingMode=FLOOR");
        bigDec = new BigDecimal(4.163922930480675E7,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext057() {
        mc= new MathContext("precision=870723346 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(4.163922930480675E7,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext058() {
        mc= new MathContext("precision=466273320 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(4.163922930480675E7,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext059() {
        mc= new MathContext("precision=1270381998 roundingMode=FLOOR");
        bigDec = new BigDecimal(4.163922930480675E7,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalDoubleMathContext060() {
        mc = new MathContext("precision=1 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(4.163922930480675E7,mc);
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
        } catch (Throwable e) {
            fail(msgRaise+"ArithmeticException");
        }
    }
    
    public final void testBigDecimalDoubleMathContext061() {
        mc = new MathContext("precision=500 roundingMode=DOWN");
        try {
            bigDec = new BigDecimal(Double.NaN,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        } catch (Throwable e) {
            fail(msgRaise+"NumberFormatException");
        }
    }
    
    public final void testBigDecimalDoubleMathContext062() {
        mc = new MathContext("precision=500 roundingMode=DOWN");
        try {
            bigDec = new BigDecimal(Double.NEGATIVE_INFINITY,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        } catch (Throwable e) {
            fail(msgRaise+"NumberFormatException");
        }
    }
    
    public final void testBigDecimalDoubleMathContext063() {
        mc = new MathContext("precision=500 roundingMode=DOWN");
        try {
            bigDec = new BigDecimal(Double.POSITIVE_INFINITY,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        } catch (Throwable e) {
            fail(msgRaise+"NumberFormatException");
        }
    }
    
    /*
     * Test method for java.math.BigDecimal(char[] in)'
     */
    
    
    public final void testBigDecimalCharArray001() {
        char[] array = {'2','8','6','5','.','3','1','8','5','3','1','0','5','7','6','3','3','4','0','0','6','6','1','3','2','1','2','4','7','2','1','6','4','0','3','0','1','8','4','3','6','0','2','2','0','0','7','0','5','4','0','5','8','5','5','2','8','5','8','0','0','6','7'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray002() {
        char[] array = {'4','7','0','6','7','3','5','3','7','.','3','3','0','1','2','6','8','2','7','3','8','8','6','3','6','5','7','0','6','7','6','6','5','1','2','0','2','7','8','5','1','4','5','8','3','0','2'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray003() {
        char[] array = {'-','5','.','7','6','1','3','0','7','5','2','0','0','1','0','1','6','5','3','2','0','1','2','2','5','0','5','4','7','3','6','8','5','2','3','1','0','5'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray004() {
        char[] array = {'-','2','8','7','5','7','7','2','4','3','0','6','2','1','4','4','8','2','3','7','.','4','6','2','0','8','7','1','3','6','8','7','5','2','5','4','7','5','6','3','6','7','3','0','7','2','3','0','2','4','3','1','2','1','0','8','3','3','6','8','7','6','1'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray005() {
        char[] array = {'1','0','5','5','4','7','5','6','.','1','0','0','5','8','4','3','3','8','4','7','0','8','7','0','6','6','7','4','6','8','2','1','6','7','5','2','8','2','6','0','6','2','0','6','7','8','7','5','0','0','6','3','2','2','1','0','6','3','6','1','6'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray006() {
        char[] array = {'1','3','.','1','7','1','4','8','1','7','1','0','4','1','5','1','1','0','5','7'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray007() {
        char[] array = {'-','6','2','.','2','0','0','3','0','6','6','4','2','8','6','0','6','0','0','5','5','8','0','2','1','5','3','1'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray008() {
        char[] array = {'-','1','4','7','.','7','2','5','7','8','3','3','3','6','2','8','4','1','5','0','3','1','1','3','8','2','8','3','0','3','2','7','4','7','4','6','2','3','5','6','0','7','6','2','6','1','8','3','2','2','2','2','2','2','1','1','5','6','7','2','5'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray009() {
        char[] array = {'6','4','0','7','0','1','.','2','3','8','0','7','8','0','8','5','0','8','4','2','8','4','8','1','8','0','8','6','0','6','8','0','0','2','5','5','1','8','4','0','8','8','8','5','0','2','7','5','3','4','1','7','7','7','3','3','6','0'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray010() {
        char[] array = {'-','4','2','5','.','1','6','1','3','5','0','7','5','8','6','8','6','7','4','3','5','5','1','7','2','5','7','3','7','7','0','5','8','0','5','4','7','8','1','8','2','3','5','0','0','5','2','5','6','7','5','6','3','8','5','1','3','1','2'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray011() {
        char[] array = {'-','.','1','7','3','6','3','7','3','3','1','5','2','4','7','7','8','2','3'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray012() {
        char[] array = {'-','3','8','.','7','3','4','2','1','7','5','4','5','1','1','7','3','5','3','8','2','2','8','8','7','8','0','0','8','7','8','4'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray013() {
        char[] array = {'-','1','6','3','7','8','1','4','7','0','0','5','5','6','7','4','7','.','7','3','6','7','7','7','2','8','0','0','4','5','0','8','7','8','0','3','8','1','8','0','8','0','6','8','1','6','6','3','1','2','1','5','4'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray014() {
        char[] array = {'1','0','1','5','8','3','3','7','4','2','1','1','1','1','5','0','5','8','5','7','0','3','0','4','3','.','1','4','0','7','4','3','5','3','1','3','8','1','6','3','4','7','7','6','7','6','6','2','5','4','2'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray015() {
        char[] array = {'0','4','4','8','.','6','4','2','2','1','7','5','7','3','3','5','5','8','6','2','1','4','7','0','6','0','3','6','2','8','5','1','3','0','5','6','8','2','7','8','4','6','7','0','7','7','5','5','4','1','5','0','5','7','6','7','6','0'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray016() {
        char[] array = {'-','5','3','0','0','0','0','8','.','7','6','1','4','0','5','3','8','0','6','5','0','0','8','5','0','2','5','1','5','0','7','8','5','3','3','6','6','5','2','7','7','6','4','1','0','6','1','7','4','3','3'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray017() {
        char[] array = {'1','3','5','.','5','7','6','1','8','8','4','5','3','2','5','0','0','6','5','4','0','1','3','4','1','1'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray018() {
        char[] array = {'5','5','8','6','6','.','2','1','2','6','8','2','0','7','5','3','0','4','2','0','2','3','4','5','0','0','7','6','1','7','5','6','0','1','0','3','4','7','7','3','2','2','0','7','8','0','1','2','0','6','8'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray019() {
        char[] array = {'-','8','.','5','3','0','7','3','3','5','8','4','3','4','8','5','0','7','5','4','8','1','1'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray020() {
        char[] array = {'5','4','3','5','7','8','.','8','1','3','1','0','5','6','3','2','8','7','3','4','2','3','5','6','6','7','7','1','8','7','3','7','3','7','6','6','3','4','7'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray021() {
        char[] array = {'-','2','2','5','.','0','4','3','1','8','3','6','8','7','7','2','5','5','1','6','5','7','5','3','1','3','8','4','7','5','1','7','5','1','0','8','4','8','8','8','1','7','1','5','5','0','6','5','7','1','3','4','3','2','5','5','3','4','4','0','1','0','8'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray022() {
        char[] array = {'6','3','3','3','0','7','5','.','3','4','2','7','5','2','3','4','3','3','1','8','6','3','0','8','2','2','6','0','7','2','5','8','1','1','2','7','0','4'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray023() {
        char[] array = {'3','6','6','.','5','5','0','3','8','1','6','3','0','1','1','7','4','4','5','4','4','1','3','2','1','7','5','1','5','5','3','7','8'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray024() {
        char[] array = {'-','0','4','5','3','0','6','4','5','7','0','4','7','8','1','.','5','1','2','7','6','0','8','4','8','8','3','2','6','3','3','4'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray025() {
        char[] array = {'6','8','8','5','1','5','0','0','2','5','8','.','3','6','4','0','8','7','0','3','3','8','0','3','0','5','2','4','0','5','5','0','8','4','2','0','8','4','7','1','5','8','3','1','3','3'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray026() {
        char[] array = {'1','6','8','6','0','.','6','6','6','4','1','1','3','4','1','6','2','4','7','5','0','8','8','4','2','5','6','5','4','8','6','3','6','6','4'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray027() {
        char[] array = {'-','0','4','.','4','8','0','2','2','8','2','7','6','3','6','3','6','2','6','8','4','1','4','8','3','5','4','2','4','1','1','4','1','4','0','4','0','0','2','0','2','6','2','2','7','0','0','4','1','0','0','3','3','7','8'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray028() {
        char[] array = {'-','1','8','8','8','2','5','1','4','5','.','3','7','4','8','7','7','6','2','2','1','6','2','7','5','1','7','3','2','2','1','7'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray029() {
        char[] array = {'-','6','5','3','5','1','4','3','5','2','5','.','4','6','3','2','5','7','6','7','5','8','7','6','7','2','8','3','2','5','6','4','8','2'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray030() {
        char[] array = {'-','7','2','0','4','.','4','1','5','1','4','5','3','2','6','6','6','7','8','2','8','6','0','0','3','8','6','4','2'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray031() {
        char[] array = {'5','2','3','2','4','0','3','7','5','4','1','5','4','8','1','.','2','6','1','8','4','0','4','5','4','1','5','1','3','8','2','5','3','0','1','1','2','8','8','1','8','5','3','5','4'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray032() {
        char[] array = {'7','8','7','3','5','.','7','6','4','0','4','4','0','6','7','3','3','1','5','4','7','8','8','2','6','6','7','2','3','3','6','0','2','4','3','7','4','7','7','8','1','8','0','5','1','7','1','8','3','8','3','2','7','0','4','6','1','4','0','0','0'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray033() {
        char[] array = {'-','3','4','1','.','8','8','3','2','0','6','0','2','6','5','4','1','2','7','8','3','1','4','3','6','2','7','3','4'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray034() {
        char[] array = {'-','5','.','7','4','3','0','4','1','5','7','7','0','4','2','3','0','6','5','0','0','5','2','1','1','6','2','8','6','2','4','8','7'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray035() {
        char[] array = {'0','.','1','2','8','8','4','7','5','0','8','0','1','3','7','1','4','8'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray036() {
        char[] array = {'8','4','6','3','0','5','7','8','.','0','5','7','7','5','6','6','4','7','1','6','5','3','6','4','4','1','8','6','1','3','6','2','0','2','8','1','5','8','2','7','5','8','3','0','2','4','4','6','3','4'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray037() {
        char[] array = {'5','.','2','0','2','3','8','0','0','4','3','8','8','2','7','0','5','7','6'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray038() {
        char[] array = {'4','0','0','1','.','2','8','0','0','0','4','2','7','0','4','4','1','7','3','8','0','7','7','2','5','4','0','3','4','0','5','4','1','3','5','6','7','4','6','1','0','1','8','8','5','4','0','3','7','7','8','7'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray039() {
        char[] array = {'3','.','7','6','1','7','6','3','4','4','1','8','3','7','1','2','1','2','5'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray040() {
        char[] array = {'-','6','8','6','0','3','4','.','6','4','3','8','8','6','4','0','1','3','8','1','0','2','7','3','7','5','1','2','7','5','6','2','0','6','5','7','0','7','6','2','5','4','5','5','2','7','0','6','0','0','8','0','1','7','8','0','0','5','4','5','4'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray041() {
        char[] array = {'8','4','7','4','8','1','3','5','5','1','.','2','3','0','4','3','0','8','0','4','5','1','0','8','7','1','2','6','4','0','4','1','5','8','6','5','0','0','1','0'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray042() {
        char[] array = {'1','0','6','7','5','3','.','0','4','3','7','8','5','4','2','8','5','7','0','2','1','8','4','4','1'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray043() {
        char[] array = {'3','5','8','4','7','2','7','5','3','.','3','4','7','0','7','3','1','1','4','5','2','0','6','2','5','0','0'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray044() {
        char[] array = {'5','4','7','4','3','3','7','6','4','0','8','5','1','6','.','7','2','0','6','3','2','8','5','4','5','5','6','2','7','4','7','1','7','6','5','5','6','5','3','5','8','5','8','6'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray045() {
        char[] array = {'6','2','5','2','6','0','3','.','6','4','0','6','2','0','3','6','2','5','0','4','1','3','8','5','3','1','4','7','5','7','3','5','1','8','7','7','5','3','7','3','8','5','6','7','1','8','2','4','6','6','5','4','4','2'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray046() {
        char[] array = {'-','.','4','0','4','2','8','2','6','7','1','4','4','5','0','6','5','0','1','7','7','6','5','1','3','1'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray047() {
        char[] array = {'5','7','8','1','5','1','6','4','4','8','6','2','3','8','5','4','7','8','4','1','1','3','.','2','6','4','4','0','7','0','8','7','6','0','4','1','2','1','7','7','2','4','2','7','7'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray048() {
        char[] array = {'5','8','2','5','4','1','3','.','3','8','7','1','3','6','6','7','7','5','2','6','5','1','1','7','7','2','4','6','7','3','5','3','7','1','4','4','5','6','6','4','5','5','7','5','2','2','8','0','8','2','2','3','2','0','5','3','8','8','4','5','3','0'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray049() {
        char[] array = {'6','8','5','1','.','7','4','0','5','7','0','5','7','7','6','8','2','8','1','4','0','7','7','3','7','2','0','7','4','5','0','6','8'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray050() {
        char[] array = {'-','3','4','8','1','6','6','7','2','0','2','7','5','5','0','1','5','8','2','6','1','2','1','7','4','7','0','2','.','6','7','6','1','7','8','3','5','5','3','3','3','1','5','1','8','4','5','3','0','4','2','7','4','4','3','0','2','2'};
        bigDec = new BigDecimal(array);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArray051() {
        char[] array = {'-','3','4','A','1','6','.','7','2','0','2','7','5','.','0','1','5','8','2','6','1','2','1','7','4','7','0','2','0','6','7','6','1','7','8','3','5','5','3','3','3','1','5','1','8','4','5','3','0','4','2','7','4','4','3','0','2','2'};
        try {
            bigDec = new BigDecimal(array);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        } catch (Throwable e) {
            fail(msgRaise+"NumberFormatException");
        }
    }
    
    public final void testBigDecimalCharArray052() {
        char[] array = {'-','3','4','A','1','6','a','7','2','0','2','7','5','.','0','1','5','8','2','6','1','2','1','7','4','7','0','2','0','6','7','6','1','7','8','3','5','5','3','3','3','1','5','1','8','4','5','3','0','4','2','7','4','4','3','0','2','2'};
        try {
            bigDec = new BigDecimal(array);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        } catch (Throwable e) {
            fail(msgRaise+"NumberFormatException");
        }
    }
    
    public final void testBigDecimalCharArray053() {
        char[] array = {' ','3','4','A','1','6','9','7','2','0','2','7','5','.','0','1','5','8','2','6','1','2','1','7','4','7','0','2','0','6','7','6','1','7','8','3','5','5','3','3','3','1','5','1','8','4','5','3','0','4','2','7','4','4','3','0','2','2'};
        try {
            bigDec = new BigDecimal(array);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        } catch (Throwable e) {
            fail(msgRaise+"NumberFormatException");
        }
    }
    
    /*
     * test methods for java.math.BigDecimal(char[] in, MathContent) 
     */
    
    public final void testBigDecimalCharArrayMathContext001() {
        char[] array = {'2','5','4','0','2','1','6','4','3','4','6','.','7','5','4','0','5','2','0','5','6','6','6','2','2','1','2','2','2','0','7','1','0','1','4','8','3','0','5','4','8','5','3','7','8','4','7','5','7','8','1','8','8','5','1','4','2'};
        mc = new MathContext("precision=1492903327 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext002() {
        char[] array = {'-','5','8','8','.','2','8','6','2','8','2','1','2','0','4','7','7','1','5','5','5'};
        mc = new MathContext("precision=763292325 roundingMode=HALF_UP");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext003() {
        char[] array = {'-','4','1','2','4','0','6','2','0','1','.','8','1','6','0','1','8','8','6','1','7','6','1','8','2','3','1','8','8','2','1','5','6','5','1','5','7','4','4','3','8','5','2','5','6','6','8','1','6','3','3','6','3','3','4','6','6','3','3','5','1'};
        mc = new MathContext("precision=1415045507 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext005() {
        char[] array = {'-','3','.','5','2','0','7','3','8','6','2','7','4','5','5','0','0','6','5','2','5','5','3'};
        mc = new MathContext("precision=1492903327 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext006() {
        char[] array = {'-','8','4','.','0','7','4','8','5','5','7','3','2','1','5','3','5','4','4','7','7','6','8','3','5','5','0','3','3','2','5','8','6','3','6','8','8','0','0','0','0','6','6'};
        mc = new MathContext("precision=763292325 roundingMode=HALF_UP");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext007() {
        char[] array = {'0','5','.','7','1','5','6','8','8','1','6','3','5','1','4','1','2','5','3','0','3','5','1'};
        mc = new MathContext("precision=1415045507 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext009() {
        char[] array = {'2','0','1','0','.','7','8','6','5','5','5','6','1','2','0','2','3','7','6','7','8','8','4','5','4','6','5','0','5','7','6','1','6','2','8','8','7','1','3','1','0','2','2','8','1','7','4','4','1','3','3','6','6','8','4','1','4','0','0','0','7'};
        mc = new MathContext("precision=1492903327 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext010() {
        char[] array = {'-','6','1','.','0','1','8','3','3','2','4','8','6','3','7','7','1','6','8','8','8','8','1','3','8','5','1','0','4','7','3','1','4','1','2','7','8','3','3','0','7','6','7','7','8','1'};
        mc = new MathContext("precision=763292325 roundingMode=HALF_UP");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext011() {
        char[] array = {'8','3','.','1','0','0','2','0','1','2','4','0','2','7','5','3','8','0','2','4','3','3','2','4','1','0','6','1','1'};
        mc = new MathContext("precision=1415045507 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext013() {
        char[] array = {'1','6','2','.','4','8','8','0','6','1','1','5','7','7','4','6','3','3','4','1','6','2','3','5','0','5','7','6','1','3','1','4','8','2','8','5','8','8','4','7'};
        mc = new MathContext("precision=1492903327 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext014() {
        char[] array = {'2','7','2','3','6','8','3','.','3','1','5','5','5','8','6','4','0','1','0','5','7','5','5'};
        mc = new MathContext("precision=763292325 roundingMode=HALF_UP");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext015() {
        char[] array = {'8','5','4','3','6','.','4','5','3','6','7','3','0','5','6','4','1','6','3','1','8','6','7','4','3','8','8','0','7','1','0','5','1','7','2','3','8','6','5'};
        mc = new MathContext("precision=1415045507 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext017() {
        char[] array = {'0','3','0','0','2','.','3','5','5','6','6','5','0','7','7','0','7','8','5','6','5','8','8','7','2','0','4','0','1','1','6','4','3','8','8','4','7','7','5','8','4','6','8','6','8','6','3','7','4','3','7','0','4','3','1','6','1'};
        mc = new MathContext("precision=1492903327 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext018() {
        char[] array = {'-','6','0','0','1','.','5','7','7','8','6','0','8','1','4','5','5','1','2','2','5','7','4','5','1','3','1','6','6','3','5','1','1','3','3','2','6','0','2','3','4','0','3','8','3','2','2','8','8','7','8','3','0'};
        mc = new MathContext("precision=763292325 roundingMode=HALF_UP");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext019() {
        char[] array = {'0','5','4','3','.','6','5','3','4','4','2','5','2','1','6','5','7','0','2','6','4','7','7','7','7','8','8','7','8','3','1','8','5','4','1','2','8','0','3','1','1','0','6','8','8','1','4','8'};
        mc = new MathContext("precision=1415045507 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext021() {
        char[] array = {'-','7','0','2','.','6','7','5','6','3','1','2','6','2','5','8','7','8','5','0','2','2','6','1','7','4','4','1','6','6','0','6','7','7','1','3','0','8','4','2','0','7','3','6','1','4','7','3','7','1','8','1','2','0','2','7','2','2','8'};
        mc = new MathContext("precision=1492903327 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext022() {
        char[] array = {'2','4','3','5','5','5','3','5','1','6','7','2','1','6','3','2','2','2','3','7','8','0','0','.','8','2','2','7','6','5','4','0','5','0','8','2','6','4','0','3','2','6','5','4','4','0'};
        mc = new MathContext("precision=763292325 roundingMode=HALF_UP");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext023() {
        char[] array = {'2','0','7','8','2','4','2','3','6','3','0','0','1','0','6','.','7','8','6','0','5','2','0','2','6','5','8','1','0','4','4','0','3','6','5','5','4','6','6','5','7','3','5','2','2','4','4','8','4','7','1','5','0','8','4','1','5','6','0','7','8'};
        mc = new MathContext("precision=1415045507 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext025() {
        char[] array = {'7','2','8','4','2','.','2','2','1','4','6','7','7','6','2','0','3','0','8','6','6','0','8','3','8','5','0','8','3','0','2','8','8','5','3','5','2','3','5','5','0','5','6','3','5','0','8','1','4','0','4','4','2','6','5','1','8'};
        mc = new MathContext("precision=1492903327 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext026() {
        char[] array = {'-','5','.','1','2','2','2','1','3','5','3','6','2','8','3','7','3','1','4','3','1','2','3','6','0'};
        mc = new MathContext("precision=763292325 roundingMode=HALF_UP");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext027() {
        char[] array = {'4','2','1','3','1','4','7','2','0','1','8','8','4','.','1','0','4','1','7','6','4','1','1','8','2','1','6','3','3','0','6','0','0','5','6','7','6','6','8','6','0'};
        mc = new MathContext("precision=1415045507 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext029() {
        char[] array = {'7','1','5','3','2','5','6','5','2','8','8','5','0','8','5','2','7','4','.','8','7','0','3','6','3','4','7','3','1','8','1','6','0','6','1','6','0'};
        mc = new MathContext("precision=1492903327 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext030() {
        char[] array = {'-','5','1','.','5','3','5','1','4','5','0','3','2','8','2','8','3','1','8','8','7','7','5','7','6','5','3','8','3','4','3','7','7','0','8','5','6','0','4','8','3','6','3','1','7','1','8'};
        mc = new MathContext("precision=763292325 roundingMode=HALF_UP");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext031() {
        char[] array = {'-','0','5','7','2','2','1','6','7','5','0','3','0','3','2','8','8','2','5','5','7','7','2','.','1','7','8','2','1','8','3','6','5','5','3','7','0','4','0','6','3','6','4','7','2','1','3'};
        mc = new MathContext("precision=1415045507 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext033() {
        char[] array = {'-','4','5','.','6','1','7','1','6','8','7','0','2','5','5','3','8','0','0','2','6','5'};
        mc = new MathContext("precision=1492903327 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext034() {
        char[] array = {'-','3','7','4','3','1','2','.','7','6','4','0','1','5','8','3','0','1','1','4','5','7','1','5','0','6','3','1','5','2','4','3','3','3','4','6','2'};
        mc = new MathContext("precision=763292325 roundingMode=HALF_UP");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext035() {
        char[] array = {'-','0','.','3','7','4','6','8','4','1','3','6','8','7','5','8','1','2','1','3','7','2','4','3','5','7','8','4','8','4','7','3','1'};
        mc = new MathContext("precision=1415045507 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext037() {
        char[] array = {'3','5','1','.','4','8','6','8','8','7','0','2','1','6','6','5','3','3','4','7','1','6','6','2','0','6','8','1','2','7','2','3','0'};
        mc = new MathContext("precision=1492903327 roundingMode=HALF_EVEN");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext038() {
        char[] array = {'-','3','3','6','.','7','7','1','4','5','0','8','1','0','0','5','4','4','2','3','6','4','7','6','6','2','3','5','2','7','2','5','6','3','6','5','2','7','3','4','0','2','0','1','7','7','3','6','7'};
        mc = new MathContext("precision=763292325 roundingMode=HALF_UP");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext039() {
        char[] array = {'8','5','0','0','2','6','8','7','0','8','7','2','2','3','.','5','0','0','0','1','5','3','4','8','1','3','7','1'};
        mc = new MathContext("precision=1415045507 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(array,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayMathContext040() {
        char[] array = {'8','5','0','0','2','6','8','7','0','8','7','2','2','3','.','5','0','0','0','1','5','3','4','8','1','3','7','1'};
        mc = new MathContext("precision=1 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(array,mc);
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }
    
    public final void testBigDecimalCharArrayMathContext041() {
        char[] array = {'8','5','0','.','2','6','8','7','0','8','7','2','2','3','.','5','0','0','0','1','5','3','4','8','1','3','7','1'};
        mc = new MathContext("precision=1415045507 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(array,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    
    public final void testBigDecimalCharArrayMathContext042() {
        char[] array = {' ','5','0','9','2','6','8','7','0','8','7','2','2','3','.','5','0','0','0','1','5','3','4','8','1','3','7','1'};
        mc = new MathContext("precision=1415045507 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(array,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    /*
     * test methods for java.math.BigDecimal(char[] in, int offset, int len)
     */
    
    public final void testBigDecimalCharArrayIntInt001() {
        char[] array = {'3','3','0','0','.','3','0','0','3','3','4','8','3','1','6','1','3','1','3','2','1','0'};
        int offset = 25;
        int len = 28;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt002() {
        char[] array = {'3','5','6','8','0','6','.','3','8','4','2','7','8','0','6','2','0','6','5','1','8','3','3','8','7','0','2','5','7','0','0','1','2','2','8','3','2','7','3','3','0','4','1','8','6','6','5','0','1','7','7','6'};
        int offset = 9;
        int len = 20;
        bigDec = new BigDecimal(array,offset,len);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayIntInt003() {
        char[] array = {'4','1','0','.','5','0','0','7','0','7','1','7','5','1','2','1','2','1','6','0','5','3','0','8','3','0','8','2','3','1','6','6','0','3','8','5','2','6','2','1','6','3','8','5','4','3','3'};
        int offset = 48;
        int len = 47;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt004() {
        char[] array = {'5','3','4','.','1','4','3','1','0','3','6','7','7','2','1','7','7','0','3','2','6','6','7','2','6','5','6','8','2','5','3','6','8','0','0','5','6','1','6','7','1','2','2','3','6','5','7'};
        int offset = 5;
        int len = 62;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt005() {
        char[] array = {'-','4','.','7','0','6','8','2','0','1','6','8','7','0','0','6','6','7','7','1','8','5','1','8','8','0','3','5','2','4','7','2'};
        int offset = 35;
        int len = 36;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt006() {
        char[] array = {'6','0','.','0','4','5','6','4','1','3','4','8','6','2','5','4','0','5','2','3','5','7','2','5','7','5','7','4','0','6','4','0','5','0','3'};
        int offset = 51;
        int len = 43;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt007() {
        char[] array = {'4','4','1','.','6','1','6','1','8','5','6','4','4','1','8','2','4','5','4'};
        int offset = 7;
        int len = 18;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt008() {
        char[] array = {'-','1','3','4','0','.','3','7','0','6','4','4','3','8','2','8','6','3','3','8','3','8','4','1','2','2','4','4','1','6','6','4','8','8','8','5','8','7','2','2','8'};
        int offset = 37;
        int len = 11;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt009() {
        char[] array = {'-','8','2','0','.','2','0','5','6','4','0','6','1','8','5','5','2','1','4','7','8','2','5','7','6','8','5','8','2','0','1','1','6','5','7','6','5','8','6','2','5','0','3','8','4','6','5','1','0'};
        int offset = 23;
        int len = 69;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt010() {
        char[] array = {'3','8','8','7','6','6','5','6','0','5','1','7','7','7','6','3','4','1','7','8','.','5','3','3','4','6','0','5','5','6','0','2','8','3','2','6','4','7','5','8','8','6','7','0','5','1','7','4','1','6','6','5','8','4','6','1','3','0','5','7'};
        int offset = 12;
        int len = 23;
        bigDec = new BigDecimal(array,offset,len);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayIntInt011() {
        char[] array = {'-','8','3','.','4','8','2','1','7','7','3','1','3','5','6','0','8','4','2','6','1','2','8','5','8','5','4','4','0','8','2','0','1','4','1','7','6','3','3','0','2','5'};
        int offset = 25;
        int len = 26;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt012() {
        char[] array = {'-','0','7','.','7','3','1','0','2','7','0','8','2','4','4','4','6','8','6','0','1','5','8','7','1','7','5','6','4','4','3','1','4','8','7','6','3','0','4','2','1','5','3','6','8','7','2','0'};
        int offset = 64;
        int len = 30;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt013() {
        char[] array = {'-','6','0','5','1','.','0','4','5','4','3','7','4','6','3','6','7','1','7','4','2','7','3','1','2','6','3','7','4','5','5','3','0','6','8','0','2','2','6','1','2','5'};
        int offset = 27;
        int len = 32;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt014() {
        char[] array = {'5','2','7','1','8','1','.','5','7','7','0','6','8','7','8','6','3','3','6','8','3','8','8','1','3','1','5','0','6','2','7','7','2','2','5','4','7','0','6','7','8','6','8','6','8','2','4','4','8','0','4','5','1','5','4','1','2','6','2','5'};
        int offset = 4;
        int len = 70;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt015() {
        char[] array = {'-','0','4','8','1','8','4','1','1','0','8','6','6','5','6','.','7','7','7','8','6','1','7','6','4','8','4','8','0','3','6','8','7','7','7','7','2','5','3','2','6','1','1','1','1','1'};
        int offset = 11;
        int len = 42;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt016() {
        char[] array = {'3','7','.','5','1','0','2','7','3','1','0','5','8','0','7','5','8','0','0','4','8','5','0','5','2','5','8','3','7'};
        int offset = 1;
        int len = 26;
        bigDec = new BigDecimal(array,offset,len);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayIntInt017() {
        char[] array = {'-','4','3','4','0','.','8','1','4','5','1','7','4','8','4','2','6','1','6','8','0','8','5','8','2','8','2','5','5','8','8','6','5','6','7','4','0','0','4','5','2','3','7','3','7','5','2'};
        int offset = 50;
        int len = 37;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt018() {
        char[] array = {'-','2','0','3','.','3','6','5','2','1','2','7','4','3','8','8','3','8','8','1','0','3','4','0','1','0','5','2','1','8','4','1','7','1','1','7','2','6','0','6','6','6','8'};
        int offset = 11;
        int len = 44;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt019() {
        char[] array = {'-','4','3','8','.','4','0','2','5','8','7','1','1','3','4','8','0','4','5','2','4','0','2','2','5','6','0','2','4','7','5','2','8','8','8','6','0','4','3','6','3','3','7','3','7','3','1','7','1','7','5','3','4','2','2','5','6','4'};
        int offset = 7;
        int len = 43;
        bigDec = new BigDecimal(array,offset,len);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayIntInt020() {
        char[] array = {'3','3','6','3','.','6','1','3','2','1','6','8','2','7','3','4','3','7','8','1','5','0','5','1','1','2','7','7','4','2','1','0','6','6','5','0','1','1','2','3','0','7','8','1','2','2','2','1','0','2','4','1','0'};
        int offset = 73;
        int len = 38;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt021() {
        char[] array = {'-','7','3','4','0','6','8','.','3','3','1','0','0','5','7','1','4','3','2','7','3','4','1','8','1','4','8','7','6','8','8','1','4','0','3','6','8'};
        int offset = 0;
        int len = 39;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt022() {
        char[] array = {'-','2','.','1','2','4','8','7','6','1','6','8','7','2','3','2','3','1','1','4','3','6','8','6'};
        int offset = 26;
        int len = 19;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt023() {
        char[] array = {'7','3','.','3','2','5','8','3','7','0','4','4','4','8','6','6','6','0','1','1','7','7','0','8','5','0'};
        int offset = 26;
        int len = 25;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt024() {
        char[] array = {'-','8','6','.','4','7','6','3','4','1','3','5','8','7','8','7','8','5','5','8','8','1','3','8','3','0','7','1','6','6','6','4','0','5','2','1','5','3'};
        int offset = 36;
        int len = 36;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt025() {
        char[] array = {'2','4','1','0','7','6','2','7','0','6','2','7','3','1','0','4','5','7','4','3','2','.','6','0','2','3','6','8','0','2','8','8','1','7','4','5','6','1','4','4','4','2'};
        int offset = 11;
        int len = 59;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt026() {
        char[] array = {'-','1','1','6','7','3','5','7','0','5','3','.','6','2','8','3','2','2','1','1','4','8','3','1','1','8','3','0','2','1','7','7','1','0','7','2','0','3','6','2','2','8','8','5','1','2'};
        int offset = 49;
        int len = 55;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt027() {
        char[] array = {'-','0','0','6','8','8','7','5','8','0','8','0','.','0','0','7','2','2','6','0','5','1','3','4','5'};
        int offset = 6;
        int len = 16;
        bigDec = new BigDecimal(array,offset,len);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayIntInt028() {
        char[] array = {'-','5','4','7','1','6','0','.','2','0','3','7','3','4','4','4','3','8','0','5','5','0','3','2','0','3','5','3','3','1','5','6','6','5','5','8','7','3','6','5','7','5','3'};
        int offset = 51;
        int len = 13;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt029() {
        char[] array = {'3','5','3','.','0','0','1','1','5','0','7','5','6','6','6','3','1','6','3','6','3','5','8','2','3','2','4','0'};
        int offset = 39;
        int len = 40;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt030() {
        char[] array = {'-','6','1','1','7','2','4','.','5','3','7','7','0','6','7','4','0','2','8','0','1','2','1','5','0','7','7','1','8','0','1','7','8','5','1','5','6','5','3','8','7','4','5','4','2','5','2','4','8','8','3','7','4','0','5'};
        int offset = 32;
        int len = 19;
        bigDec = new BigDecimal(array,offset,len);
        assertNotNull(msgNotNull,bigDec);
    }
    
    
    public final void testBigDecimalCharArrayIntInt031() {
        char[] array = {'-','4','3','8','.','4','0','2','5','8','7','1','1','3','4','8','0','4','.','2','4','0','2','2','5','6','0','2','4','7','5','2','8','8','8','6','0','4','3','6','3','3','7','3','7','3','1','7','1','7','5','3','4','2','2','5','6','4'};
        int offset = 3;
        int len = 43;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntInt032() {
        char[] array = {' ','4','3','8','9','4',' ','2','5','8','7','1','1','3','4','8','0','4','.','2','4','0','2','2','5','6','0','2','4','7','5','2','8','8','8','6','0','4','3','6','3','3','7','3','7','3','1','7','1','7','5','3','4','2','2','5','6','4'};
        int offset = 6;
        int len = 43;
        try {
            bigDec = new BigDecimal(array,offset,len);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    /*
     * test methods for java.math.BigDecimal(char[] in, int offset, int len, MathContext mc)
     */
    
    public final void testBigDecimalCharArrayIntIntMathContext001() {
        char[] array = {'-','4','7','1','6','.','0','8','7','4','7','8','2','4','0','7','2','4','7','4','6','5','0','7','8','5','3','4','2','0','4','2','1'};
        int offset = 35;
        int len = 30;
        MathContext mc = new MathContext("precision=5 roundingMode=HALF_UP");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext002() {
        char[] array = {'-','5','4','.','3','3','8','3','4','8','0','0','4','0','0','6','6','5','2','4','5','8','0','8','6'};
        int offset = 12;
        int len = 26;
        MathContext mc = new MathContext("precision=36 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext003() {
        char[] array = {'-','3','0','7','8','.','5','7','6','2','5','7','4','4','1','6','5','3','7','0','0','0','8','0','1','2','3','0','5','8','7','3','7','8','7','8','2'};
        int offset = 20;
        int len = 22;
        MathContext mc = new MathContext("precision=44 roundingMode=FLOOR");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext004() {
        char[] array = {'1','2','5','4','.','5','6','0','0','4','4','7','8','6','1','2','4','1','0','0','6','5','5','8','3','8','6','0','2','2','0','4','7','7','2','1','5','2','3','1','4','1','5','6','5','1','6','4','1','6','4','5','7','0','7','1','5','0','8','6','5','5','8'};
        int offset = 13;
        int len = 6;
        MathContext mc = new MathContext("precision=35 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(array,offset,len,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext005() {
        char[] array = {'-','4','3','7','.','5','6','7','5','5','6','5','1','7','4','8','5','0','5','8','4','7','3','3','0','8','6','7','2','6','0','2','5','8','5','3','8','7','8','1','5','3','8'};
        int offset = 49;
        int len = 11;
        MathContext mc = new MathContext("precision=41 roundingMode=FLOOR");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext006() {
        char[] array = {'-','6','2','3','0','6','6','0','6','8','3','1','4','2','0','6','7','8','4','1','3','1','6','7','8','2','2','0','.','4','7','0','4','3','3','4','2','3','3','1','2','2','0','3','3','0','3','2','5','1','4','3','2','3','6','5','4'};
        int offset = 44;
        int len = 68;
        MathContext mc = new MathContext("precision=16 roundingMode=HALF_DOWN");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext007() {
        char[] array = {'-','3','2','4','.','8','1','3','5','5','5','5','5','3','2','2','5','0','3'};
        int offset = 3;
        int len = 17;
        MathContext mc = new MathContext("precision=16 roundingMode=HALF_EVEN");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext008() {
        char[] array = {'0','6','.','7','8','4','1','3','7','3','0','1','3','4','0','5','1','2','8','1','3','2','6','6','4','5','3','7','1','7','6','7','7','1','6','4','5'};
        int offset = 6;
        int len = 10;
        MathContext mc = new MathContext("precision=63 roundingMode=FLOOR");
        bigDec = new BigDecimal(array,offset,len,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext009() {
        char[] array = {'5','0','6','5','.','0','4','1','3','3','0','4','8','6','3','0','0','6','5','2','1','8','7','6','4','8','6','4','1','7','3','2','6','1','4','6','3','6','8','0','8','7','8','5','2','8','3','5','1','3'};
        int offset = 30;
        int len = 55;
        MathContext mc = new MathContext("precision=35 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext010() {
        char[] array = {'-','0','.','6','6','7','7','2','0','1','2','1','6','4','6','5','2','6','5','3'};
        int offset = 10;
        int len = 21;
        MathContext mc = new MathContext("precision=22 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext011() {
        char[] array = {'-','7','5','1','.','8','5','6','3','0','4','3','3','8','5','1','2','5','2','7','0','2','8','3','6','4','0','5','1','2','0','2','1','2','5','3','2','3','4','3','8','5','1','3','2','6','5','2','0','8','0','3','4','7','1','0','7','4'};
        int offset = 65;
        int len = 9;
        MathContext mc = new MathContext("precision=58 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext012() {
        char[] array = {'-','0','.','5','5','8','4','7','2','6','3','0','0','0','3','1','7','3','6','5','3','5','8','3','3','6','7','2','3','2','3','3','4','3','1'};
        int offset = 22;
        int len = 23;
        MathContext mc = new MathContext("precision=12 roundingMode=FLOOR");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext013() {
        char[] array = {'0','3','8','0','3','3','2','4','8','2','6','7','4','7','7','2','6','.','7','8','3','7','2','8','4','5','6','8','5','8','8','1','8','5','5','6','2','2','8','4','6','7','6','8','0','7','5','6','5','0','4','8','8'};
        int offset = 10;
        int len = 53;
        MathContext mc = new MathContext("precision=78 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext014() {
        char[] array = {'3','5','.','8','2','4','6','7','3','8','1','5','7','8','7','2','0','5','8','4','8','8','5','1','0','3','5','2','6','6','3','5'};
        int offset = 19;
        int len = 26;
        MathContext mc = new MathContext("precision=57 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext015() {
        char[] array = {'-','5','3','.','1','7','5','5','1','2','0','1','3','7','6','0','8','2','3','7','4','3','3','0','4','7','5','8','5','6','8','8','8','7','8','2','5','5','7','5','7','8','2','2','4','7'};
        int offset = 26;
        int len = 21;
        MathContext mc = new MathContext("precision=84 roundingMode=CEILING");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext016() {
        char[] array = {'-','3','2','0','0','4','.','6','2','3','7','5','8','8','3','2','0','4','8','7','7','1','1','6','8','1','3','0','2','4','1','4','4','1','4','0','7','8','3','4','1','8','5','3','0','5','7','7','6','6','5','5','5','0','4','4','7'};
        int offset = 48;
        int len = 26;
        MathContext mc = new MathContext("precision=86 roundingMode=FLOOR");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext017() {
        char[] array = {'-','0','6','4','5','8','8','8','8','5','2','.','4','2','7','8','3','1','1','0','8','8','4'};
        int offset = 19;
        int len = 1;
        MathContext mc = new MathContext("precision=29 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(array,offset,len,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext018() {
        char[] array = {'6','5','0','.','2','6','2','2','0','3','5','2','6','5','8','0','6','2','7','1'};
        int offset = 10;
        int len = 14;
        MathContext mc = new MathContext("precision=32 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext019() {
        char[] array = {'8','0','8','8','0','3','7','4','8','6','0','8','8','6','7','4','8','6','7','.','8','1','7','8','7','6','3','2','3','2','5','7','5','7','7','6','7','7','6'};
        int offset = 33;
        int len = 1;
        MathContext mc = new MathContext("precision=59 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(array,offset,len,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext020() {
        char[] array = {'-','0','3','2','2','5','6','.','2','3','6','6','7','3','3','0','7','6','2','6','2','8','1','8'};
        int offset = 3;
        int len = 2;
        MathContext mc = new MathContext("precision=45 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(array,offset,len,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext021() {
        char[] array = {'3','8','0','3','2','0','7','0','.','1','7','2','6','1','6','6','6'};
        int offset = 13;
        int len = 16;
        MathContext mc = new MathContext("precision=6 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext022() {
        char[] array = {'-','3','6','.','6','0','1','5','1','5','7','8','4','2','6','8','7','8','2','2','7','2','1','2','4','5','7','4','3','0','3','2','1','1','1','8','2','8','7','0','3','6','5','4','5','6','5','4','2','5','3','3'};
        int offset = 60;
        int len = 31;
        MathContext mc = new MathContext("precision=3 roundingMode=FLOOR");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext023() {
        char[] array = {'-','5','2','.','0','7','7','0','2','7','5','5','6','3','7','5','7','6','2','3','6','8','8','1','3','6','1'};
        int offset = 26;
        int len = 30;
        MathContext mc = new MathContext("precision=9 roundingMode=CEILING");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext024() {
        char[] array = {'2','3','3','7','3','.','6','0','3','2','1','3','1','0','5','2','5','3','0','2','0','0','0','7','2','5','3','0','8','2','3','8','8','2','0','8','6','3','4','0','5','1','2','3','5'};
        int offset = 19;
        int len = 44;
        MathContext mc = new MathContext("precision=58 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext025() {
        char[] array = {'0','0','1','.','0','0','3','6','8','7','4','1','3','1','6','0','2','5','5','6','6','0','8','1','0','5','5'};
        int offset = 14;
        int len = 12;
        MathContext mc = new MathContext("precision=39 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(array,offset,len,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext026() {
        char[] array = {'-','2','5','1','.','0','0','3','1','7','0','1','5','7','5','2','6','0','5','1','5','4','8','7','1','1','0','7','8'};
        int offset = 1;
        int len = 3;
        MathContext mc = new MathContext("precision=44 roundingMode=FLOOR");
        bigDec = new BigDecimal(array,offset,len,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext027() {
        char[] array = {'-','8','6','3','.','5','3','3','3','8','2','1','4','0','6','4','8','0','5','7','7','7','5','4','6','7','6','8','1','0','3','2','7','0','3','4','6','3','8','4','7','5','0','4','1','4','4','4','5','5','1','0','4','7','5','0','6','8','1','1','0','2'};
        int offset = 61;
        int len = 24;
        MathContext mc = new MathContext("precision=47 roundingMode=HALF_UP");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext028() {
        char[] array = {'-','1','4','0','.','6','1','1','8','5','4','4','4','4','7','1','7','1','2','5','4','3','1','3','8','0','4','4','1','0','6','2','3','7','6','6','4','4','0','0','1','7','1','0','0','6','6','4','6','0','3','4','6','8','4'};
        int offset = 63;
        int len = 36;
        MathContext mc = new MathContext("precision=16 roundingMode=FLOOR");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext029() {
        char[] array = {'-','5','1','8','7','5','3','5','1','3','7','8','2','.','7','0','7','8','5','2','3','5','2','3','0','2','8','6','4','4','6','4','0','2','4','7','5','4','5','3','8','4','8','2','7','8','1','7','0','5','2','2','1','1','2'};
        int offset = 57;
        int len = 20;
        MathContext mc = new MathContext("precision=53 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext030() {
        char[] array = {'5','3','.','6','8','1','4','2','1','1','6','2','4','4','2','7','0','0','6','4','0','1','6','4','4','1','4','3','8','7','7','3'};
        int offset = 1;
        int len = 12;
        MathContext mc = new MathContext("precision=57 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(array,offset,len,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext031() {
        char[] array = {'-','4','8','.','2','5','3','0','4','6','8','8','6','1','4','5','4','3','3','1','5','5','3','7','0','2','0','1','8','4','1','4','2','8','5','8','5','7','6','3','4','3','2','5'};
        int offset = 1;
        int len = 33;
        MathContext mc = new MathContext("precision=43 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(array,offset,len,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext032() {
        char[] array = {'1','0','1','4','4','.','6','7','0','3','5','4','6','3','4','0','2','8','5','4','2','8','1','4','5','6'};
        int offset = 21;
        int len = 17;
        MathContext mc = new MathContext("precision=19 roundingMode=HALF_DOWN");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext033() {
        char[] array = {'2','4','6','5','6','2','8','6','7','7','2','5','2','8','4','8','7','6','6','6','8','.','2','3','0','6','3','2','4','4','6','5','7','3','5','3','0','5','6','5','1','1'};
        int offset = 20;
        int len = 25;
        MathContext mc = new MathContext("precision=20 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext034() {
        char[] array = {'-','4','5','.','1','3','6','1','0','5','0','6','5','4','1','6','8','7','5','6','4','2','6','4','3','2','5','4','4','6','6','7','8','8','0','5','2','4','0','2','7','6','0','5','8'};
        int offset = 18;
        int len = 42;
        MathContext mc = new MathContext("precision=55 roundingMode=UP");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext035() {
        char[] array = {'1','6','7','.','6','0','6','5','4','8','1','2','5','4','4','5','4','5','5','3','3','7','4','3','7','5','4','4','5','4','8','5','3','2'};
        int offset = 15;
        int len = 39;
        MathContext mc = new MathContext("precision=6 roundingMode=CEILING");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext036() {
        char[] array = {'8','2','7','.','1','3','0','0','6','5','6','4','2','0','7','3','0','6','0','0','2','7','1','4','2','0','4','3','0','5','6','5','8','7','2','1','6','0','2'};
        int offset = 22;
        int len = 15;
        MathContext mc = new MathContext("precision=23 roundingMode=HALF_DOWN");
        bigDec = new BigDecimal(array,offset,len,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext037() {
        char[] array = {'7','0','6','2','5','7','7','5','2','.','6','5','4','3','4','2','5','2','6','2','7','2','0','2','6','7','0','3','8','0','2','2','2','0','0','1'};
        int offset = 6;
        int len = 44;
        MathContext mc = new MathContext("precision=8 roundingMode=UNNECESSARY");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext038() {
        char[] array = {'0','6','.','7','3','8','3','5','0','1','5','2','5','3','4','2','2','6','8','5','6','7','6','6','7','7'};
        int offset = 7;
        int len = 8;
        MathContext mc = new MathContext("precision=22 roundingMode=HALF_UP");
        bigDec = new BigDecimal(array,offset,len,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext039() {
        char[] array = {'2','7','1','3','5','1','0','6','6','8','.','8','0','0','4','2','4','1','2','5','1','7','1','6','5','7','8','2','1','3','4','2','8','4','3','7','2','5','5','1','5','5','7','2','8','4','8','5','3','3','1','3','4','4','4','1','5','1','6','8'};
        int offset = 26;
        int len = 12;
        MathContext mc = new MathContext("precision=88 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(array,offset,len,mc);
        assertNotNull(msgNotNull,bigDec);
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext040() {
        char[] array = {'2','6','8','2','.','7','1','2','8','7','1','7','7','3','6','2','3','1','6','1','1','0','0','4','5','6','0','4','8','7','6','3','4','1','3','5','4','3','7','5','2','4','0','2','6','3','2','5','7','5','1'};
        int offset = 54;
        int len = 24;
        MathContext mc = new MathContext("precision=21 roundingMode=HALF_EVEN");
        try {
            bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    
    public final void testBigDecimalCharArrayIntIntMathContext041() {
        char[] array = {'0','6','.','7','3','8','3','-','.','1','5','2','5','3','4','2','2','6','8','5','6','7','6','6','7','7'};
        int offset = 7;
        int len = 8;
        MathContext mc = new MathContext("precision=22 roundingMode=HALF_UP");
        bigDec = new BigDecimal(array,offset,len,mc);
        assertNotNull(msgNotNull,bigDec);
    }

    public final void testBigDecimalCharArrayIntIntMathContext042() {
        char[] array = {'0','6','.','7','3','8','3','6','.','1','5','2','5','3','4','2','2','6','8','5','6','7','6','6','7','7'};
        int offset = 4;
        int len = 8;
        MathContext mc = new MathContext("precision=22 roundingMode=HALF_UP");
        bigDec = new BigDecimal(array,offset,len,mc);
        assertNotNull(msgNotNull,bigDec);
    }    
       
    /** This method test that for a BigDecimal constructed with a char array:'2','6','8','2','.','7','1','2','8','7','1','7','7','3','6','2','3','1','6','1','1','0','0','4','5','6','0','4','8','7','6','3','4','1','3','5','4','3','7','5','2','4','0','2','6','3','2','5','7','5','1'.
     *  offset = 4, len = 10 and a Mathcontext (precision=7 roundingMode=UNNECESSARY)</b>  throw an <i>ArithmeticException</i>
     */
    
    public final void testBigDecimalCharArrayIntIntMathContext043() {
    	   char[] array = {'2','6','8','2','.','7','1','2','8','7','1','7','7','3','6','2','3','1','6','1','1','0','0','4','5','6','0','4','8','7','6','3','4','1','3','5','4','3','7','5','2','4','0','2','6','3','2','5','7','5','1'};
        int offset = 4;
        int len = 10;
        try {
        	   MathContext mc = new MathContext("precision=5 roundingMode=UNNECESSARY");
        bigDec = new BigDecimal(array,offset,len,mc);
            fail(msgRaise+"ArithmeticException");
        } catch (ArithmeticException e) {
            }
    }
}
