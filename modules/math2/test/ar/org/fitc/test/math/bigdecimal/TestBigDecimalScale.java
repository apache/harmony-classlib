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


import ar.org.fitc.test.util.Messages;
import junit.framework.TestCase;
/**
 * Test cases for
 * scale() 
 * unscaledValue() 
 * scaleByPowerOfTen(int) 
 *
 */
public class TestBigDecimalScale extends TestCase implements Messages{
	private boolean log = false;
	private BigDecimal bigDec = null;
		
	public TestBigDecimalScale(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/*
	 * Test method for 'java.math.BigDecimal.scale()'
	 */
	public final void testScale001() {
		String number = "-3.04450005628952219875276851592181739985177404354991623940287953949840771053166735078899150474180032104811583948703137298867750663E-150059897";
		if (log) { System.out.println("testScale001"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 150060025");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,150060025,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale002() {
		String number = "-3.04450005628952219875276851592181739985177404354991623940287953949840771053166735078899150474180032104811583948703137298867750663E-150059897";
		if (log) { System.out.println("testScale002"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 150060025");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,150060025,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale003() {
		String number = "3.04450005628952219875276851592181899137152629834566390628179786190978721842915459692242793587084144348634289172900982147023247898E+150444902";
		if (log) { System.out.println("testScale003"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -150444774");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,-150444774,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale004() {
		String number = "1.130814306621822530965314020199532177087801787604254603206783828956551435340333587435911130332668690675014454666611652824365931034E-150444645";
		if (log) { System.out.println("testScale004"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 150444774");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,150444774,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale005() {
		String number = "1.130814306621822530965314020199532177087801787604254603206783828956551435340333587435911130332668690675014454666611652824365931034E-150444645";
		if (log) { System.out.println("testScale005"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 150444774");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,150444774,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale006() {
		String number = "-1.130814306621822530965314020199532177087801787604254646799099777122648535978822061294888204504898211857344445991819749921073560186E-152368389";
		if (log) { System.out.println("testScale006"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 152368518");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,152368518,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale007() {
		String number = "-5.306128669533167260683396556320881754027377618758425445816447197411510581212334525660813765407137702398144748820254678637409368698E+152368647";
		if (log) { System.out.println("testScale007"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -152368518");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,-152368518,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale008() {
		String number = "5.306128669533167260683396556320881754027377618758425445816447197411510581212334525660813765407137702398144748820254678637409368698E-152368389";
		if (log) { System.out.println("testScale008"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 152368518");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,152368518,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale009() {
		String number = "-5.306128669533167260683396556320881754649065021982955034748821774881202526332606981616334639013047484086597181262536701468847476109E+152753396";
		if (log) { System.out.println("testScale009"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -152753267");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,-152753267,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale010() {
		String number = "-6.132492970526037571773433724928232191130002002007688425082943072418221245499501378017825745265626360968347619538163194162907549069E+152753396";
		if (log) { System.out.println("testScale010"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -152753267");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,-152753267,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale011() {
		String number = "-6.132492970526037571773433724928232191130002002007688425082943072418221245499501378017825745265626360968347619538163194162907549069E-152753138";
		if (log) { System.out.println("testScale011"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 152753267");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,152753267,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale012() {
		String number = "-6.132492970526037571773433724928232191130002002007688425082941502609248249998718353641982560687288156402385321960044765039750763347E-151598892";
		if (log) { System.out.println("testScale012"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 151599021");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,151599021,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale013() {
		String number = "3.609907209600433464235425526021583488395674937352043541006271453976683428201834144506947069908134666385623066820337199400860472147E-151598892";
		if (log) { System.out.println("testScale013"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 151599021");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,151599021,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale014() {
		String number = "3.609907209600433464235425526021583488395674937352043541006271453976683428201834144506947069908134666385623066820337199400860472147E+151599150";
		if (log) { System.out.println("testScale014"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -151599021");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,-151599021,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	
	public final void testScale015() {
		String number = "-3.609907209600433464235425526021583488395674937352043541006271453976683428201834144506948818986189453101163579425691916919409960807E-151983640";
		if (log) { System.out.println("testScale015"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 151983769");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,151983769,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale016() {
		String number = "-4.479764368540296949593359387713531316924753235509162466549951322404799916925167673303801785548649043827941878102346163111911188327E+151983898";
		if (log) { System.out.println("testScale016"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -151983769");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,-151983769,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale017() {
		String number = "-4.479764368540296949593359387713531316924753235509162466549951322404799916925167673303801785548650978213046038136168556826229917382E-153907385";
		if (log) { System.out.println("testScale017"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 153907514");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,153907514,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale018() {
		String number = "8.611585873504648505043545230750283502437875151755477362882430697438353238361001935088861684841092336678956231691888740739402090182E-153907385";
		if (log) { System.out.println("testScale018"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 153907514");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,153907514,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale019() {
		String number = "-8.611585873504648505043545230750283502437875151755477362882430697438353238361001935088861684841092336678956231691888740739402090182E+153907643";
		if (log) { System.out.println("testScale019"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -153907514");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,-153907514,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale020() {
		String number = "8.611585873504648505043545230750283502437875151755477362882431211686120254128499822384396521168478989898840432622651674417677588953E-154292134";
		if (log) { System.out.println("testScale020"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 154292263");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,154292263,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale021() {
		String number = "-9.437950174497518816133582399357633939540499535004740342148926572445063902648168787445873664699580995249159102409797256264900270553E-154292134";
		if (log) { System.out.println("testScale021"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 154292263");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,154292263,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale022() {
		String number = "-9.437950174497518816133582399357633939540499535004740342148926572445063902648168787445873664699580995249159102409797256264900270553E+154292392";
		if (log) { System.out.println("testScale022"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -154292263");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,-154292263,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale023() {
		String number = "-9.437950174497518816133582399357633939540499535004740342148926572445063902648168787444597537350808607590801105542995354731193344160E+153138145";
		if (log) { System.out.println("testScale023"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -153138016");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,-153138016,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale024() {
		String number = "-6.958857271518907882863470893535582628232626385256951404349438947424931909786668230374837725124115019538550490256071709688405729440E+153138145";
		if (log) { System.out.println("testScale024"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -153138016");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,-153138016,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale025() {
		String number = "6.958857271518907882863470893535582628232626385256951404349438947424932029519306822221171981191878762605567933086883011151401300915E+153522894";
		if (log) { System.out.println("testScale025"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -153522765");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,-153522765,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale026() {
		String number = "-7.785221572511778193953508062142933065335250768506207292599207254079847536879710001671578972966601565783074772073463097482796234358E-167758345";
		if (log) { System.out.println("testScale026"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 167758474");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,167758474,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale027() {
		String number = "5.132157237745194563611809783982492188321561959127001660707711223725887283467667819901442822279034826909680986563852885895199225462E-167758345";
		if (log) { System.out.println("testScale027"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 167758474");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,167758474,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale028() {
		String number = "5.132157237745194563611809783982532445394874256749966329234455736931623677969371529992471706522043429215571581695160377486531841871E+166989106";
		if (log) { System.out.println("testScale028"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -166988977");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,-166988977,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale029() {
		String number = "-4.305792936752324252521772615375141751218937575877738681441873585860956801577796705829021341475462289791255307222499478562339472066E+169682348";
		if (log) { System.out.println("testScale029"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -169682219");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,-169682219,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testScale030() {
		String number = "-9.263978742709546119061995627019244373834683875373316557040190598759440604903502081686502721571478119760695340153395463522690127317E+169682348";
		if (log) { System.out.println("testScale030"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -169682219");
			System.out.println("-> actual result: "+ bigDec.scale()); 
		}
		assertEquals(msgNotSame,-169682219,bigDec.scale());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	
	/*
	 * Test method for 'java.math.BigDecimal.unscaledValue()'
	 */
	public final void testUnscaledValue001() {
		String number = "-7.43975525515368057184230203287193208732539383828178431851950218190450476870867225E-2137348247";
		if (log) { System.out.println("testUnscaledValue001"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -743975525515368057184230203287193208732539383828178431851950218190450476870867225");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"-743975525515368057184230203287193208732539383828178431851950218190450476870867225",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue002() {
		String number = "7.43975525515368057184230203287193208732539383828178431851950218190450476870867424E-2136194000";
		if (log) { System.out.println("testUnscaledValue002"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 743975525515368057184230203287193208732539383828178431851950218190450476870867424");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"743975525515368057184230203287193208732539383828178431851950218190450476870867424",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue003() {
		String number = "6.666020708617697792370702621453251150243552879100478749393473954986436272762970336E+2136194161";
		if (log) { System.out.println("testUnscaledValue003"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 6666020708617697792370702621453251150243552879100478749393473954986436272762970336");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"6666020708617697792370702621453251150243552879100478749393473954986436272762970336",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue004() {
		String number = "-6.666020708617697792370702621453251150243552879100478749393473954986436272762970336E-2136193999";
		if (log) { System.out.println("testUnscaledValue004"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -6666020708617697792370702621453251150243552879100478749393473954986436272762970336");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"-6666020708617697792370702621453251150243552879100478749393473954986436272762970336",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue005() {
		String number = "-6.666020708617697792370702621453251150243552879100478749393473954986436272762970336E-2136193999";
		if (log) { System.out.println("testUnscaledValue005"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -6666020708617697792370702621453251150243552879100478749393473954986436272762970336");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"-6666020708617697792370702621453251150243552879100478749393473954986436272762970336",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue006() {
		String number = "6.666020708617697792370702621453251150243552879100478749393473954986436272762970336E+2136194161";
		if (log) { System.out.println("testUnscaledValue006"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 6666020708617697792370702621453251150243552879100478749393473954986436272762970336");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"6666020708617697792370702621453251150243552879100478749393473954986436272762970336",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue007() {
		String number = "6.666020708617697792370702621453251150243552879100478749393564031812211480426968051E-2136578748";
		if (log) { System.out.println("testUnscaledValue007"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 6666020708617697792370702621453251150243552879100478749393564031812211480426968051");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"6666020708617697792370702621453251150243552879100478749393564031812211480426968051",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue008() {
		String number = "-7.231442108009377515830717575951517988880282810809894357600956120811178635184829427E+2136578910";
		if (log) { System.out.println("testUnscaledValue008"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -7231442108009377515830717575951517988880282810809894357600956120811178635184829427");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"-7231442108009377515830717575951517988880282810809894357600956120811178635184829427",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue009() {
		String number = "7.231442108009377515830717575951517988880282810809894357600956120811178635184829427E-2136578748";
		if (log) { System.out.println("testUnscaledValue009"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 7231442108009377515830717575951517988880282810809894357600956120811178635184829427");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"7231442108009377515830717575951517988880282810809894357600956120811178635184829427",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue010() {
		String number = "7.231442108009377515830717575951517988880282810809894357600956120811178635184829427E-2136578748";
		if (log) { System.out.println("testUnscaledValue010"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 7231442108009377515830717575951517988880282810809894357600956120811178635184829427");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"7231442108009377515830717575951517988880282810809894357600956120811178635184829427",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue011() {
		String number = "-7.231442108009377515830717575951517988880282810809894357600956120811178635184829427E+2136578910";
		if (log) { System.out.println("testUnscaledValue011"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -7231442108009377515830717575951517988880282810809894357600956120811178635184829427");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"-7231442108009377515830717575951517988880282810809894357600956120811178635184829427",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue012() {
		String number = "7.231442108009377515830717575951517988880282729409014427494107998199998033859884726E-2144152677";
		if (log) { System.out.println("testUnscaledValue012"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 7231442108009377515830717575951517988880282729409014427494107998199998033859884726");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"7231442108009377515830717575951517988880282729409014427494107998199998033859884726",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue013() {
		String number = "5.416141825751879456301195879930766559572886714269138983882197588426479471619913398E+2144152839";
		if (log) { System.out.println("testUnscaledValue013"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 5416141825751879456301195879930766559572886714269138983882197588426479471619913398");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"5416141825751879456301195879930766559572886714269138983882197588426479471619913398",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue014() {
		String number = "-5.416141825751879456301195879930766559572886714269138983882197588426479471619913398E+2144152839";
		if (log) { System.out.println("testUnscaledValue014"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -5416141825751879456301195879930766559572886714269138983882197588426479471619913398");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"-5416141825751879456301195879930766559572886714269138983882197588426479471619913398",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue015() {
		String number = "5.416141825751879456301195879930766559572886714269138983882197588426479471619913398E+2144152839";
		if (log) { System.out.println("testUnscaledValue015"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 5416141825751879456301195879930766559572886714269138983882197588426479471619913398");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"5416141825751879456301195879930766559572886714269138983882197588426479471619913398",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue016() {
		String number = "5.416141825751879456301195879930766559572886714269138983882197588426479471619913398E+2144152839";
		if (log) { System.out.println("testUnscaledValue016"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 5416141825751879456301195879930766559572886714269138983882197588426479471619913398");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"5416141825751879456301195879930766559572886714269138983882197588426479471619913398",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue017() {
		String number = "-5.416141825751879456301195879937903180964173941795807756802750498840390266551650761E+2143768090";
		if (log) { System.out.println("testUnscaledValue017"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -5416141825751879456301195879937903180964173941795807756802750498840390266551650761");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"-5416141825751879456301195879937903180964173941795807756802750498840390266551650761",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue018() {
		String number = "5.981563225143559179761210834429033398209616645978554592089679754251221834041772489E-2143767928";
		if (log) { System.out.println("testUnscaledValue018"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 5981563225143559179761210834429033398209616645978554592089679754251221834041772489");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"5981563225143559179761210834429033398209616645978554592089679754251221834041772489",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue019() {
		String number = "-5.981563225143559179761210834429033398209616645978554592089679754251221834041772489E+2143768090";
		if (log) { System.out.println("testUnscaledValue019"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -5981563225143559179761210834429033398209616645978554592089679754251221834041772489");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"-5981563225143559179761210834429033398209616645978554592089679754251221834041772489",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue020() {
		String number = "-5.981563225143559179761210834429033398209616645978554592089679754251221834041772489E+2143768090";
		if (log) { System.out.println("testUnscaledValue020"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -5981563225143559179761210834429033398209616645978554592089679754251221834041772489");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"-5981563225143559179761210834429033398209616645978554592089679754251221834041772489",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue021() {
		String number = "5.981563225143559179761210834429033398209616645978554592089679754251221834041772489E-2144922174";
		if (log) { System.out.println("testUnscaledValue021"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 5981563225143559179761210834429033398209616645978554592089679754251221834041772489");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"5981563225143559179761210834429033398209616645978554592089679754251221834041772489",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue022() {
		String number = "4.255540005947905287093796762802745153950125275497180630193155248049376727701360527E+2144922336";
		if (log) { System.out.println("testUnscaledValue022"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 4255540005947905287093796762802745153950125275497180630193155248049376727701360527");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"4255540005947905287093796762802745153950125275497180630193155248049376727701360527",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue023() {
		String number = "-4.255540005947905287093796762802745153950125275497180630193155248049376727701360527E+2144922336";
		if (log) { System.out.println("testUnscaledValue023"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -4255540005947905287093796762802745153950125275497180630193155248049376727701360527");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"-4255540005947905287093796762802745153950125275497180630193155248049376727701360527",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue024() {
		String number = "-4.255540005947905287093796762802745153950125275497180630193155248049376727701360527E+2144922336";
		if (log) { System.out.println("testUnscaledValue024"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -4255540005947905287093796762802745153950125275497180630193155248049376727701360527");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"-4255540005947905287093796762802745153950125275497180630193155248049376727701360527",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue025() {
		String number = "-4.255540005947905287093796762802745153950125275497180630193155248049376727701360527E+2144922336";
		if (log) { System.out.println("testUnscaledValue025"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -4255540005947905287093796762802745153950125275497180630193155248049376727701360527");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"-4255540005947905287093796762802745153950125275497180630193155248049376727701360527",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue026() {
		String number = "-4.255540005947905287093796762802745153950125275497180630193155248049398804170122147E-2144537426";
		if (log) { System.out.println("testUnscaledValue026"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: -4255540005947905287093796762802745153950125275497180630193155248049398804170122147");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"-4255540005947905287093796762802745153950125275497180630193155248049398804170122147",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue027() {
		String number = "4.850720426360199732841180925432499720936156782559723375674715422601737109198054307E+2144537588";
		if (log) { System.out.println("testUnscaledValue027"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 4850720426360199732841180925432499720936156782559723375674715422601737109198054307");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"4850720426360199732841180925432499720936156782559723375674715422601737109198054307",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue028() {
		String number = "4.850720426360199732841180925432499720936156782559723375674715422601737109198054307E+2144537588";
		if (log) { System.out.println("testUnscaledValue028"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 4850720426360199732841180925432499720936156782559723375674715422601737109198054307");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"4850720426360199732841180925432499720936156782559723375674715422601737109198054307",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue029() {
		String number = "4.850720426360199732841180925432499720936156782559723375674715422601737109198054307E-2144537426";
		if (log) { System.out.println("testUnscaledValue029"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 4850720426360199732841180925432499720936156782559723375674715422601737109198054307");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"4850720426360199732841180925432499720936156782559723375674715422601737109198054307",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	public final void testUnscaledValue030() {
		String number = "4.850720426360199732841180925432499720936156782559723375674715422601737109198054307E-2144537426";
		if (log) { System.out.println("testUnscaledValue030"); }
		bigDec = new BigDecimal(number);
		if (log) { 
			System.out.println("-> expected result: 4850720426360199732841180925432499720936156782559723375674715422601737109198054307");
			System.out.println("-> actual result: "+ bigDec.unscaledValue().toString()); 
		}
		assertEquals(msgNotSame,"4850720426360199732841180925432499720936156782559723375674715422601737109198054307",bigDec.unscaledValue().toString());
		if (log) { System.out.println("---Test PASSED---"); }
	}
	
	/*
	 * Test method for 'java.math.BigDecimal.scaleByPowerOfTen(int)'
	 * All these tests have problems
	 */    
	/** This method test that for a BigDecimal whose value is 
	 * 0 the returning value of 
	 * <b>scaleByPowerOfTen(0)</b> should be 0
	 */
	public void testScaleByPowerOfTen001() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,"0",bigDec.scaleByPowerOfTen(0).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 0 the returning value of 
	 * <b>scaleByPowerOfTen(2147483647)</b> should be 0E+2147483647
	 */
	public void testScaleByPowerOfTen002() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,"0E+2147483647",bigDec.scaleByPowerOfTen(2147483647).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 0 the returning value of 
	 * <b>scaleByPowerOfTen(-2147483648)</b> should be 0E-2147483647
	 */
	public void testScaleByPowerOfTen003() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,"0E-2147483647",bigDec.scaleByPowerOfTen(-2147483648).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 0 the returning value of 
	 * <b>scaleByPowerOfTen(1789610230)</b> should be 0E+1789610230
	 */
	public void testScaleByPowerOfTen004() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,"0E+1789610230",bigDec.scaleByPowerOfTen(1789610230).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 0 the returning value of 
	 * <b>scaleByPowerOfTen(-644494099)</b> should be 0E-644494099
	 */
	public void testScaleByPowerOfTen005() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,"0E-644494099",bigDec.scaleByPowerOfTen(-644494099).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 0 the returning value of 
	 * <b>scaleByPowerOfTen(1)</b> should be 0E+1
	 */
	public void testScaleByPowerOfTen006() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,"0E+1",bigDec.scaleByPowerOfTen(1).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 0 the returning value of 
	 * <b>scaleByPowerOfTen(10)</b> should be 0E+10
	 */
	public void testScaleByPowerOfTen007() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,"0E+10",bigDec.scaleByPowerOfTen(10).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 0 the returning value of 
	 * <b>scaleByPowerOfTen(-100)</b> should be 0E-100
	 */
	public void testScaleByPowerOfTen008() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,"0E-100",bigDec.scaleByPowerOfTen(-100).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444 the returning value of 
	 * <b>scaleByPowerOfTen(0)</b> should be 4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444
	 */
	public void testScaleByPowerOfTen009() {
		bigDec= new BigDecimal("4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");  
		assertEquals(msgNotSame,"4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444",bigDec.scaleByPowerOfTen(0).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444 the returning value of 
	 * <b>scaleByPowerOfTen(2147483647)</b> should be 4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444E+2147484724
	 */
	public void testScaleByPowerOfTen010() {
		bigDec= new BigDecimal("4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");  
		assertEquals(msgNotSame,"4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444E+2147484724",bigDec.scaleByPowerOfTen(2147483647).toString());
	}
	
	/** This method test that for a BigDecimal whose value is 
	 * 4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444 the returning value of 
	 * <b>scaleByPowerOfTen(1789610230)</b> should be 4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444E+1789611307
	 */
	public void testScaleByPowerOfTen011() {
		bigDec= new BigDecimal("4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");  
		assertEquals(msgNotSame,"4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444E+1789611307",bigDec.scaleByPowerOfTen(1789610230).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444 the returning value of 
	 * <b>scaleByPowerOfTen(-644494099)</b> should be 4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444E-644493022
	 */
	public void testScaleByPowerOfTen012() {
		bigDec= new BigDecimal("4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");  
		assertEquals(msgNotSame,"4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444E-644493022",bigDec.scaleByPowerOfTen(-644494099).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444 the returning value of 
	 * <b>scaleByPowerOfTen(1)</b> should be 4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444E+1078
	 */
	public void testScaleByPowerOfTen013() {
		bigDec= new BigDecimal("4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");  
		assertEquals(msgNotSame,"4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444E+1078",bigDec.scaleByPowerOfTen(1).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444 the returning value of 
	 * <b>scaleByPowerOfTen(10)</b> should be 4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444E+1087
	 */
	public void testScaleByPowerOfTen014() {
		bigDec= new BigDecimal("4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");  
		assertEquals(msgNotSame,"4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444E+1087",bigDec.scaleByPowerOfTen(10).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444 the returning value of 
	 * <b>scaleByPowerOfTen(-100)</b> should be 492273781906328703933782975009765091448212128282206768965055697726570631547378879193263259155391987054225225349214437698032979630215194069012389787665388260337094749662379538054442939921314169129223338012577528810813117428670202229886203388983444022464118040716315424804688361682747845093756614117875267472907723172750114978741394040252045372807090036442960656036869342240000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011564864444444922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444
	 */
	public void testScaleByPowerOfTen015() {
		bigDec= new BigDecimal("4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");  
		assertEquals(msgNotSame,"492273781906328703933782975009765091448212128282206768965055697726570631547378879193263259155391987054225225349214437698032979630215194069012389787665388260337094749662379538054442939921314169129223338012577528810813117428670202229886203388983444022464118040716315424804688361682747845093756614117875267472907723172750114978741394040252045372807090036442960656036869342240000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011564864444444922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444",bigDec.scaleByPowerOfTen(-100).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * -4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444 the returning value of 
	 * <b>scaleByPowerOfTen(0)</b> should be -4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444
	 */
	public void testScaleByPowerOfTen016() {
		bigDec= new BigDecimal("-4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");  
		assertEquals(msgNotSame,"-4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444",bigDec.scaleByPowerOfTen(0).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * -4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444 the returning value of 
	 * <b>scaleByPowerOfTen(2147483647)</b> should be -4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444E+2147484724
	 */
	public void testScaleByPowerOfTen017() {
		bigDec= new BigDecimal("-4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");  
		assertEquals(msgNotSame,"-4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444E+2147484724",bigDec.scaleByPowerOfTen(2147483647).toString());
	}
	
	/** This method test that for a BigDecimal whose value is 
	 * -4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444 the returning value of 
	 * <b>scaleByPowerOfTen(1789610230)</b> should be -4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444E+1789611307
	 */
	public void testScaleByPowerOfTen018() {
		bigDec= new BigDecimal("-4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");  
		assertEquals(msgNotSame,"-4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444E+1789611307",bigDec.scaleByPowerOfTen(1789610230).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * -4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444 the returning value of 
	 * <b>scaleByPowerOfTen(-644494099)</b> should be -4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444E-644493022
	 */
	public void testScaleByPowerOfTen019() {
		bigDec= new BigDecimal("-4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");  
		assertEquals(msgNotSame,"-4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444E-644493022",bigDec.scaleByPowerOfTen(-644494099).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * -4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444 the returning value of 
	 * <b>scaleByPowerOfTen(1)</b> should be -4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444E+1078
	 */
	public void testScaleByPowerOfTen020() {
		bigDec= new BigDecimal("-4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");  
		assertEquals(msgNotSame,"-4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444E+1078",bigDec.scaleByPowerOfTen(1).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * -4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444 the returning value of 
	 * <b>scaleByPowerOfTen(10)</b> should be -4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444E+1087
	 */
	public void testScaleByPowerOfTen021() {
		bigDec= new BigDecimal("-4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");  
		assertEquals(msgNotSame,"-4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444E+1087",bigDec.scaleByPowerOfTen(10).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * -4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444 the returning value of 
	 * <b>scaleByPowerOfTen(-100)</b> should be -492273781906328703933782975009765091448212128282206768965055697726570631547378879193263259155391987054225225349214437698032979630215194069012389787665388260337094749662379538054442939921314169129223338012577528810813117428670202229886203388983444022464118040716315424804688361682747845093756614117875267472907723172750114978741394040252045372807090036442960656036869342240000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011564864444444922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444
	 */
	public void testScaleByPowerOfTen022() {
		bigDec= new BigDecimal("-4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");  
		assertEquals(msgNotSame,"-492273781906328703933782975009765091448212128282206768965055697726570631547378879193263259155391987054225225349214437698032979630215194069012389787665388260337094749662379538054442939921314169129223338012577528810813117428670202229886203388983444022464118040716315424804688361682747845093756614117875267472907723172750114978741394040252045372807090036442960656036869342240000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011564864444444922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444",bigDec.scaleByPowerOfTen(-100).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000 the returning value of 
	 * <b>scaleByPowerOfTen(0)</b> should be 969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000
	 */
	public void testScaleByPowerOfTen023() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,"969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",bigDec.scaleByPowerOfTen(0).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000 the returning value of 
	 * <b>scaleByPowerOfTen(2147483647)</b> should be 9.69358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+2147484153
	 */
	public void testScaleByPowerOfTen024() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,"9.69358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+2147484153",bigDec.scaleByPowerOfTen(2147483647).toString());
	}
	
	/** This method test that for a BigDecimal whose value is 
	 * 969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000 the returning value of 
	 * <b>scaleByPowerOfTen(1789610230)</b> should be 9.69358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1789610736
	 */
	public void testScaleByPowerOfTen025() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,"9.69358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1789610736",bigDec.scaleByPowerOfTen(1789610230).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000 the returning value of 
	 * <b>scaleByPowerOfTen(-644494099)</b> should be 9.69358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E-644493593
	 */
	public void testScaleByPowerOfTen026() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,"9.69358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E-644493593",bigDec.scaleByPowerOfTen(-644494099).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000 the returning value of 
	 * <b>scaleByPowerOfTen(1)</b> should be 9.69358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+507
	 */
	public void testScaleByPowerOfTen027() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,"9.69358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+507",bigDec.scaleByPowerOfTen(1).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000 the returning value of 
	 * <b>scaleByPowerOfTen(10)</b> should be 9.69358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+516
	 */
	public void testScaleByPowerOfTen028() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,"9.69358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+516",bigDec.scaleByPowerOfTen(10).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000 the returning value of 
	 * <b>scaleByPowerOfTen(-100)</b> should be 96935831603022741820000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000
	 */
	public void testScaleByPowerOfTen029() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,"96935831603022741820000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",bigDec.scaleByPowerOfTen(-100).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 9.693583160302274182E-804 the returning value of 
	 * <b>scaleByPowerOfTen(0)</b> should be 9.693583160302274182E-804
	 */
	public void testScaleByPowerOfTen030() {
		bigDec= new BigDecimal("9.693583160302274182E-804");  
		assertEquals(msgNotSame,"9.693583160302274182E-804",bigDec.scaleByPowerOfTen(0).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 9.693583160302274182E-804 the returning value of 
	 * <b>scaleByPowerOfTen(2147483647)</b> should be 9.693583160302274182E+2147482843
	 */
	public void testScaleByPowerOfTen031() {
		bigDec= new BigDecimal("9.693583160302274182E-804");  
		assertEquals(msgNotSame,"9.693583160302274182E+2147482843",bigDec.scaleByPowerOfTen(2147483647).toString());
	}
	
	/** This method test that for a BigDecimal whose value is 
	 * 9.693583160302274182E-804 the returning value of 
	 * <b>scaleByPowerOfTen(1789610230)</b> should be 9.693583160302274182E+1789609426
	 */
	public void testScaleByPowerOfTen032() {
		bigDec= new BigDecimal("9.693583160302274182E-804");  
		assertEquals(msgNotSame,"9.693583160302274182E+1789609426",bigDec.scaleByPowerOfTen(1789610230).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 9.693583160302274182E-804 the returning value of 
	 * <b>scaleByPowerOfTen(-644494099)</b> should be 9.693583160302274182E-644494903
	 */
	public void testScaleByPowerOfTen033() {
		bigDec= new BigDecimal("9.693583160302274182E-804");  
		assertEquals(msgNotSame,"9.693583160302274182E-644494903",bigDec.scaleByPowerOfTen(-644494099).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 9.693583160302274182E-804 the returning value of 
	 * <b>scaleByPowerOfTen(1)</b> should be 9.693583160302274182E-803
	 */
	public void testScaleByPowerOfTen034() {
		bigDec= new BigDecimal("9.693583160302274182E-804");  
		assertEquals(msgNotSame,"9.693583160302274182E-803",bigDec.scaleByPowerOfTen(1).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 9.693583160302274182E-804 the returning value of 
	 * <b>scaleByPowerOfTen(10)</b> should be 9.693583160302274182E-794
	 */
	public void testScaleByPowerOfTen035() {
		bigDec= new BigDecimal("9.693583160302274182E-804");  
		assertEquals(msgNotSame,"9.693583160302274182E-794",bigDec.scaleByPowerOfTen(10).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 9.693583160302274182E-804 the returning value of 
	 * <b>scaleByPowerOfTen(-100)</b> should be 9.693583160302274182E-904
	 */
	public void testScaleByPowerOfTen036() {
		bigDec= new BigDecimal("9.693583160302274182E-804");  
		assertEquals(msgNotSame,"9.693583160302274182E-904",bigDec.scaleByPowerOfTen(-100).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * -9.693583160302274182E+506 the returning value of 
	 * <b>scaleByPowerOfTen(0)</b> should be -9.693583160302274182E+506
	 */
	public void testScaleByPowerOfTen037() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,"-9.693583160302274182E+506",bigDec.scaleByPowerOfTen(0).toString());
	}
	
	/** This method test that for a BigDecimal whose value is 
	 * -9.693583160302274182E+506 the returning value of 
	 * <b>scaleByPowerOfTen(-2147483648)</b> should be -9.693583160302274182E-2147483142
	 */
	public void testScaleByPowerOfTen038() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,"-9.693583160302274182E-2147483142",bigDec.scaleByPowerOfTen(-2147483648).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * -9.693583160302274182E+506 the returning value of 
	 * <b>scaleByPowerOfTen(1789610230)</b> should be -9.693583160302274182E+1789610736
	 */
	public void testScaleByPowerOfTen039() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,"-9.693583160302274182E+1789610736",bigDec.scaleByPowerOfTen(1789610230).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * -9.693583160302274182E+506 the returning value of 
	 * <b>scaleByPowerOfTen(-644494099)</b> should be -9.693583160302274182E-644493593
	 */
	public void testScaleByPowerOfTen040() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,"-9.693583160302274182E-644493593",bigDec.scaleByPowerOfTen(-644494099).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * -9.693583160302274182E+506 the returning value of 
	 * <b>scaleByPowerOfTen(1)</b> should be -9.693583160302274182E+507
	 */
	public void testScaleByPowerOfTen041() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,"-9.693583160302274182E+507",bigDec.scaleByPowerOfTen(1).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * -9.693583160302274182E+506 the returning value of 
	 * <b>scaleByPowerOfTen(10)</b> should be -9.693583160302274182E+516
	 */
	public void testScaleByPowerOfTen042() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,"-9.693583160302274182E+516",bigDec.scaleByPowerOfTen(10).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * -9.693583160302274182E+506 the returning value of 
	 * <b>scaleByPowerOfTen(-100)</b> should be -9.693583160302274182E+406
	 */
	public void testScaleByPowerOfTen043() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,"-9.693583160302274182E+406",bigDec.scaleByPowerOfTen(-100).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * -9.693583160302274182E-751 the returning value of 
	 * <b>scaleByPowerOfTen(0)</b> should be -9.693583160302274182E-751
	 */
	public void testScaleByPowerOfTen044() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,"-9.693583160302274182E-751",bigDec.scaleByPowerOfTen(0).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * -9.693583160302274182E-751 the returning value of 
	 * <b>scaleByPowerOfTen(2147483647)</b> should be -9.693583160302274182E+2147482896
	 */
	public void testScaleByPowerOfTen045() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,"-9.693583160302274182E+2147482896",bigDec.scaleByPowerOfTen(2147483647).toString());
	}
	
	/** This method test that for a BigDecimal whose value is 
	 * -9.693583160302274182E-751 the returning value of 
	 * <b>scaleByPowerOfTen(1789610230)</b> should be -9.693583160302274182E+1789609479
	 */
	public void testScaleByPowerOfTen046() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,"-9.693583160302274182E+1789609479",bigDec.scaleByPowerOfTen(1789610230).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * -9.693583160302274182E-751 the returning value of 
	 * <b>scaleByPowerOfTen(-644494099)</b> should be -9.693583160302274182E-644494850
	 */
	public void testScaleByPowerOfTen047() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,"-9.693583160302274182E-644494850",bigDec.scaleByPowerOfTen(-644494099).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * -9.693583160302274182E-751 the returning value of 
	 * <b>scaleByPowerOfTen(1)</b> should be -9.693583160302274182E-750
	 */
	public void testScaleByPowerOfTen048() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,"-9.693583160302274182E-750",bigDec.scaleByPowerOfTen(1).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * -9.693583160302274182E-751 the returning value of 
	 * <b>scaleByPowerOfTen(10)</b> should be -9.693583160302274182E-741
	 */
	public void testScaleByPowerOfTen049() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,"-9.693583160302274182E-741",bigDec.scaleByPowerOfTen(10).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * -9.693583160302274182E-751 the returning value of 
	 * <b>scaleByPowerOfTen(-100)</b> should be -9.693583160302274182E-851
	 */
	public void testScaleByPowerOfTen050() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,"-9.693583160302274182E-851",bigDec.scaleByPowerOfTen(-100).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 1.3872923614502094470E-185 the returning value of 
	 * <b>scaleByPowerOfTen(0)</b> should be 1.3872923614502094470E-185
	 */
	public void testScaleByPowerOfTen051() {
		bigDec= new BigDecimal("1.3872923614502094470E-185");  
		assertEquals(msgNotSame,"1.3872923614502094470E-185",bigDec.scaleByPowerOfTen(0).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 1.3872923614502094470E-185 the returning value of 
	 * <b>scaleByPowerOfTen(2147483647)</b> should be 1.3872923614502094470E+2147483462
	 */
	public void testScaleByPowerOfTen052() {
		bigDec= new BigDecimal("1.3872923614502094470E-185");  
		assertEquals(msgNotSame,"1.3872923614502094470E+2147483462",bigDec.scaleByPowerOfTen(2147483647).toString());
	}
	
	/** This method test that for a BigDecimal whose value is 
	 * 1.3872923614502094470E-185 the returning value of 
	 * <b>scaleByPowerOfTen(1789610230)</b> should be 1.3872923614502094470E+1789610045
	 */
	public void testScaleByPowerOfTen053() {
		bigDec= new BigDecimal("1.3872923614502094470E-185");  
		assertEquals(msgNotSame,"1.3872923614502094470E+1789610045",bigDec.scaleByPowerOfTen(1789610230).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 1.3872923614502094470E-185 the returning value of 
	 * <b>scaleByPowerOfTen(-644494099)</b> should be 1.3872923614502094470E-644494284
	 */
	public void testScaleByPowerOfTen054() {
		bigDec= new BigDecimal("1.3872923614502094470E-185");  
		assertEquals(msgNotSame,"1.3872923614502094470E-644494284",bigDec.scaleByPowerOfTen(-644494099).toString());
	}
	/** This method test that for a BigDecimal whose value is 
	 * 4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444 the returning value of 
	 * <b>scaleByPowerOfTen(-2147483648)</b> should raise ArithmeticException
	 */
	public void testScaleByPowerOfTen055() {
		try{
			bigDec= new BigDecimal("4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");  
			assertEquals(msgNotSame,"4.922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444",bigDec.scaleByPowerOfTen(-2147483648).toString());
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}  
	/** This method test that for a BigDecimal whose value is 
	 * -4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444 the returning value of 
	 * <b>scaleByPowerOfTen(-2147483648)</b> should raise ArithmeticException
	 */
	public void testScaleByPowerOfTen056() {
		try{
			bigDec= new BigDecimal("-4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444");  
			assertEquals(msgNotSame,"-4922737819063287039337829750097650914482121282822067689650556977265706315473788791932632591553919870542252253492144376980329796302151940690123897876653882603370947496623795380544429399213141691292233380125775288108131174286702022298862033889834440224641180407163154248046883616827478450937566141178752674729077231727501149787413940402520453728070900364429606560368693422400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000115648644444449227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001156486444444E-2147483648",bigDec.scaleByPowerOfTen(-2147483648).toString());
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}    /** This method test that for a BigDecimal whose value is 
	* 969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000 the returning value of 
	* <b>scaleByPowerOfTen(-2147483648)</b> should raise ArithmeticException  
	*/
	public void testScaleByPowerOfTen057() {
		try{
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			assertEquals(msgNotSame,"9.693583160302274182E+506",bigDec.scaleByPowerOfTen(-2147483648).toString());
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	/** This method test that for a BigDecimal whose value is 
	 * 9.693583160302274182E-804 the returning value of 
	 * <b>scaleByPowerOfTen(-2147483648)</b> should raise ArithmeticException  
	 */
	public void testScaleByPowerOfTen058() {
		try{
			bigDec= new BigDecimal("9.693583160302274182E-804");  
			assertEquals(msgNotSame,"9.693583160302274182E-804",bigDec.scaleByPowerOfTen(-2147483648).toString());
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	/** This method test that for a BigDecimal whose value is 
	 * -9.693583160302274182E+506 the returning value of 
	 * <b>scaleByPowerOfTen(2147483647)</b> should raise ArithmeticException 
	 */
	public void testScaleByPowerOfTen059() {
		try{
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			assertEquals(msgNotSame,"-9.693583160302274182E+506",bigDec.scaleByPowerOfTen(2147483647).toString());
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	/** This method test that for a BigDecimal whose value is 
	 * -9.693583160302274182E-751 the returning value of 
	 * <b>scaleByPowerOfTen(-2147483648)</b> should raise ArithmeticException 
	 */
	public void testScaleByPowerOfTen060() {
		try{
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			assertEquals(msgNotSame,"-9.693583160302274182E-751",bigDec.scaleByPowerOfTen(-2147483648).toString());
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	/** This method test that for a BigDecimal whose value is 
	 * 1.3872923614502094470E-185 the returning value of 
	 * <b>scaleByPowerOfTen(-2147483648)</b> should raise ArithmeticException 
	 */
	public void testScaleByPowerOfTen061() {
		try{
			bigDec= new BigDecimal("1.3872923614502094470E-185");  
			assertEquals(msgNotSame,"1.387292361450209447E-185",bigDec.scaleByPowerOfTen(-2147483648).toString());
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
}
