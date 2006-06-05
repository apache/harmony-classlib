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

package ar.org.fitc.test.math.biginteger;

import java.math.BigInteger;
import java.util.Random;
import junit.framework.TestCase;
import ar.org.fitc.test.util.Messages;
/**
 * Test cases for all bigInteger Constructors:
 * BigInteger(byte[] val)  
 * BigInteger(int signum, byte[] magnitude) 
 * BigInteger(int bitLength, int certainty, Random rnd) 
 * BigInteger(int numBits, Random rnd) 
 * BigInteger(String val) 
 * BigInteger(String val, int radix) 
 * 
 */
public class TestBigIntegerConstructors extends TestCase implements Messages {
	byte[] val = null;

	byte[] magnitude = null;

	BigInteger bi = null;

	int signum = 0;

	String valString = null;

	int radix;

	BigInteger bi2 = null;

	String valString2 = null;

	int radix2;

	public TestBigIntegerConstructors(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'java.math.BigInteger.BigInteger(byte[])'
	 */
	public void testBigIntegerByteArray001() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerByteArray002() {
		try {
			byte[] val = new byte[500];

			for (int i = 1; i < 500; i++) {
				val[i] = (byte) 0xc7;
			}

			bi = new BigInteger(val);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerByteArray003() {
		try {
			val = new byte[0];
			new BigInteger(val);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerByteArray004() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			assertEquals(bi, new BigInteger("0"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerByteArray005() {
		try {
			byte[] val = new byte[500];

			for (int i = 1; i < 500; i++) {
				val[i] = (byte) 0xc7;
			}

			bi = new BigInteger(val);

			assertEquals(
					bi,
					new BigInteger(
							"40184224049135673547897375774062757548042250774748970748999369883288852434812202292660396331361095045880012416355126344740363307615715007277335806189490266906715536373054394949593522639577616300203442463724351307954620166888691033130336731735594350188052951549817118638784049368842555018750549905172121992366328831978516169160865673130011210066326709315123137087242705778741100842935373079244166528101567118067624399692222684323042183010766053076231568051000424330952743490663339779440984089928980563854687654728322293054823506602756395170788041085154355530105239324423856388011346292971338604017628038174487178153709230118365021486647704546166527292166414444467634604478827739572876999333808896426445356488704236106275620101048836382681306499942451637471742716371091895222002384358357994876184074629337404946007896244682668988166767265354490615872000456414601557769706088283586648112352696771189646018390620763325252676413599721102819987430774178232097325260335948902834817857207983797851356500238427016332278601183450379205284468057542476265890298309826809006354978863140050482976709559340957427196510494331252902454137080154099463447524411212401331893714252962767677129780262390489916948691263408071"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerByteArray006() {
		try {
			byte[] val = { (byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00 };
			bi = new BigInteger(val);
			assertEquals(bi, new BigInteger("1435982080"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerByteArray007() {
		try {
			byte[] val = { (byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xef,
					(byte) 0x55, (byte) 0x97, (byte) 0xa9, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x5d,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0xc9, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x0a,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0x7f, (byte) 0x55,
					(byte) 0x97, (byte) 0x5a, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0xa7, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x30 };
			bi = new BigInteger(val);
			assertEquals(
					bi,
					new BigInteger(
							"24725427961391786871632876428049388551763708955622571163383481990007274544321843145937133396555313841188389404846295228476907261811076033333017824354171410201631052700122989990429637710850884052373170003323220138257684105430782009228292825100056219729366623065719124675661438984673783075682530113041747764931101134980770351689325750667950221471761814590986320556341025072"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerByteArray008() {
		try {
			byte[] val = { (byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xef, (byte) 0x55, (byte) 0x97, (byte) 0xa9,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x5d, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0xc9, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x0a, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0x7f,
					(byte) 0x55, (byte) 0x97, (byte) 0x5a, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0xa7, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x30 };
			bi = new BigInteger(val);

			assertEquals(
					bi,
					new BigInteger(
							"-49227378190632870393378297500976509144821212828220676896505569772657063154737887919326325915539198705422522534921443769803297963021519406901238978766538826033709474966237953805444293992131416912922333801257752881081311742867020222988620338898344402246411804071631542480468836168274784509375661411787526747290772317275011497874139404025204537280709003644296065603686934224"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerByteArray009() {
		try {
			byte[] val = { (byte) 0x56, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xef,
					(byte) 0x55, (byte) 0x97, (byte) 0xa9, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x5d,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0xc9, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x0a,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0x7f, (byte) 0x55,
					(byte) 0x97, (byte) 0x5a, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0xa7, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x30 };
			bi = new BigInteger(val);
			assertEquals(
					bi,
					new BigInteger(
							"25014306110423133189074326326209645964640993806340708851117423598455182113458795220410818784493183030823588279611012958938939313470578359271432889991361684561925351636319712427132270256565658665518855565059864564114477057963195299197890142146924972158959507546724400719240072871989988417811663673685612118494467828153644499539183036428470357248138653724717892377278634288"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerByteArray010() {
		try {
			byte[] val = { 17, -20, -56, 105, 64, 117, -52, -123, 119, 31,
					-120, 4, -91, -123, 66, 82, -83, -126 };
			bi = new BigInteger(val);
			assertEquals(bi, new BigInteger(
					"1561481891531000005641001115450345610456450"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerByteArray011() {
		try {
			byte[] val = { -94, 96, 39, 81, 124, 9, 74, -102, -25, 68, 8, 127,
					-71, -74, 77, 81, -81, 123, -102, -84, 44, 14, 63, -11,
					-75, 84 };
			bi = new BigInteger(val);
			assertEquals(
					bi,
					new BigInteger(
							"-150448610311864301189460189400002623041896410056489748904168108"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerByteArray012() {
		try {

			byte[] val = { 2, 122, 22, -63, 123, -87, -117, 98, 98, -94, 7,
					-27, 66, 125, -116, 69, 63, 4, 44, -109, -111, -16, 46,
					-14, -92, -47, 91 };
			bi = new BigInteger(val);
			assertEquals(
					bi,
					new BigInteger(
							"1018941560489102123187480189704051894054864066108789260456087899"));

		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerByteArray013() {
		try {
			byte[] val = { 4, -1, -1, -1, -25 };
			bi = new BigInteger(val);
			assertEquals(bi, new BigInteger("21474836455"));

		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerByteArray014() {
		try {

			byte[] val = { -128, 0, 0, 2 };
			bi = new BigInteger(val);
			assertEquals(bi, new BigInteger("-2147483646"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerByteArray015() {
		try {

			byte[] val = { 0, -45, -62, 27, -50, -54, -24, 57, -11, 70, -126 };
			bi = new BigInteger(val);
			assertEquals(bi, new BigInteger("999999999997777773151874"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerByteArray016() {
		try {

			byte[] val = { -1, 44, 61, -28, 49, 53, 23, -58, 10, -71, 126 };
			bi = new BigInteger(val);
			assertEquals(bi, new BigInteger("-999999999997777773151874"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerByteArray017() {
		try {

			byte[] val = { 37, -13, -117, 6, 30, 6, 99, 85, -80, 0, -39, 116,
					9, 53, -72, 109, -50, 9, -86, 100, 63, 92, 47, 122, 93,
					-36, 18, -113, 40, -7, -17, 15, -75, 73, -4, -38, 21, 48,
					-28, 63, -76, -99, -82, -61, -93, -27, -113, 45, 72, 88,
					-110, 105, 1, -124, 44, -97, 93, 54, 76, 1, -101, -64, -95,
					-108, -119, -85, 123, 15, 70, -62, -63, -104, -99, 66, 63,
					32, 41, -47, 33, 34, 106, 48, 69, 124, 102, 22, -11, 27,
					-56, 27, 53, 113, 37, 99, 23, -105, 99, -122, 27, 30, 86,
					-58, -52, -95, 88, 21, 30, 39, 29, 61, -29, -24, 68, -44,
					46, -109, 83, -65, 28, -35, 75, 45, 0, -8, 111, 47, -123,
					20, -112, 76, -112, 65, 62, 57, -26, 69, 78, -93, -46, -90,
					96, -85, -59, 119, -120, 67, 12, -109, 38, -125, 75, -41,
					-41, -95, -38, -126, 36, -27, 17, -22, -18, 103, 71, -19,
					-8, -40, 89, -113, 110, -77, 59, 30, 94, 62, 47, -35, 98,
					88, -77, -61, -43, -91, -73, -121, 122, 84, -34, -44, 8,
					53, -37, -46, -90, 108, 60, 90, 14, -99, -53, -94, 69, -75,
					68, -24, 97, -125, 121, -106, 112, 19, -18, 67, 28, -90,
					-23, -57, 21, -115, 110, -19, -24, 65, 3, -25, -23, 55,
					-39, -15, 107, -48, -5, 32, -94, 45, -85, 117, 98, -49,
					-68, 15, -127, -121, 45, 70, -23, 94, 60, 52, 24, -127, 4,
					-24, -83, 90, -91, 62, -36, -23, -128, 93, -53, 38, -85,
					58, 68, -7, -110, 66, -115, -11, -24, 13, -99, 118, -119,
					57, -97, -11, 36, 54, 71, 52, -25, 69, -77, -99, 9, 70,
					-63, -89, -76, 49, -112, 2 };
			bi = new BigInteger(val);
			assertEquals(
					bi,
					new BigInteger(
							"156148189153100000564100111545034561045645015614818915310000056410011154503456104564501561481891531000005641001115450345610456450156148189153100000564100111545034561045645015614818915310000056410011154503456104564501561481891531000005641001115450345610456450156148189153100000564100111545034561045645015614818915310000056410011154503456104564501561481891531000005641001115450345610456450156148189153100000564100111545034561045645015614818915310000056410011154503456104564501561481891531000005641001115450345610456450156148189153100000564100111545034561045645015614818915310000056410011154503456104564501561481891531000005641001115450345610415044861031186430118946018940000262304189641005648974890416810856450"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerByteArray018() {
		try {
			byte[] val = { -26, -5, 77, -16, 4, -88, 113, -78, 47, -113, 118,
					26, 10, 7, 3, 18, -73, -91, 85, -88, -81, 112, 12, -32, 13,
					75, -16, 37, -119, 97, -31, -81, -23, 87, 113, 77, 124, 39,
					28, -36, 31, -102, 92, 58, 49, -24, 28, -63, 8, 72, 0, 88,
					18, 113, 68, -68, 57, 59, 28, 48, 68, -94, -126, 60, -89,
					-47, 3, -32, 11, 71, -86, -50, 126, 92, -118, -81, -99, 77,
					-64, -125, -108, -114, -72, 66, -53, 94, 96, 25, -77, -25,
					93, 75, 69, 37, -111, 68, -58, 118, 21, 4, -113, -76, 63,
					-64, 124, -95, -44, 71, -8, 86, 47, -78, 30, 23, -95, -6,
					62, -57, 85, 95, -85, -124, 27, 15, 100, 73, -88, -32, -16,
					73, -52, -85, 52, 3, -35, 75, -118, -80, -83, 8, -101, -52,
					-120, -39, 39, 118, -5, -109, 41, 10, 14, -5, 58, -64, 107,
					81, 12, 34, 73, -49, 52, 82, -71, -99, -70, -25, -31, -53,
					38, -94, -51, -50, 6, -80, -116, 7, 14, 103, -89, -11, -72,
					-126, -110, 75, 97, -22, -99, 49, -16, -76, 125, -78, 102,
					2, -19, 67, 11, -103, 22, 70, 66, 19, 120, 7, -93, -15, 51,
					65, -110, 122, 108, 59, 40, 121, -9, 93, -17, 70, 49, -41,
					104, -76, 81, -102, -112, 33, 108, -26, 114, -30, 97, 77,
					96, 117, 7, -126, 7, -84, -105, 42, -47, -85, -9, 52, -30,
					-42, 114, -14, 110, -55, -58, 38, -3, -7, -1, -116, -35,
					-9, -7, 3, 125, -12, -40, -126, 13, -1, 44, 101, 59, 88,
					-67, 56, -3, 0, -49, 84, 16, -28, -89, -111, 22, -84, -36,
					59, -126, 32, -108, -20, 125, -42, -49, 45, 121, 53, 61,
					70, 51, -2, 97, -46, -119, 12, -64, -93, -108, 80, -6, -84,
					44, 14, 63, -11, -75, 84 };
			bi = new BigInteger(val);
			assertEquals(
					bi,
					new BigInteger(
							"-150441504486103118643011894601894000026230418964100564897489041681081504486103118643011894601894000026230418964100564897489041681081504486103118643011894601894000026230418964100564897489041681081504486103118643011894601894000026230418964100564897489041681081504486103118643011894601894000026230418964100564897489041681081504486103118643011894601894000026230418964100564897489041681081504486103118643011894601894000026230418964100564897489041681081504486103118643011894601894000026230418964100564897489041681081504486103118643011894601894000026230418964100564897489041681081504486103118643011894601894000026230418964100564897489041681081504486103118643011894601894000026230418964100564897489041681088610311864301189460189400002623041896410056489748904168108"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.BigInteger.BigInteger(int, byte[])'
	 */
	public void testBigIntegerIntByteArray001() {
		try {
			magnitude = new byte[500];

			for (int i = 1; i < 500; i++) {
				magnitude[i] = (byte) 0xc7;
			}

			signum = 1;
			bi = new BigInteger(signum, magnitude);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray002() {
		try {
			magnitude = new byte[500];

			for (int i = 1; i < 500; i++) {
				magnitude[i] = 0;
			}

			signum = 0;
			bi = new BigInteger(signum, magnitude);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray003() {
		try {
			magnitude = new byte[500];

			for (int i = 1; i < 500; i++) {
				magnitude[i] = (byte) 0xc7;
			}

			signum = -1;
			bi = new BigInteger(signum, magnitude);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray004() {
		try {
			magnitude = new byte[0];
			signum = 1;
			bi = new BigInteger(signum, magnitude);

			if (bi.byteValue() == 0) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray005() {
		try {
			magnitude = new byte[0];
			signum = 0;
			bi = new BigInteger(signum, magnitude);

			if (bi.byteValue() == 0) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray006() {
		try {
			magnitude = new byte[0];
			signum = -1;
			bi = new BigInteger(signum, magnitude);

			if (bi.byteValue() == 0) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray007() {
		try {
			magnitude = new byte[3];
			magnitude[0] = (byte) 0x01;
			magnitude[1] = (byte) 0x00;

			byte[] magnitude2 = new byte[1];
			magnitude2[0] = (byte) 0x01;
			signum = 1;
			bi = new BigInteger(signum, magnitude);

			BigInteger bi2 = new BigInteger(signum, magnitude2);

			if (bi.compareTo(bi2) == 1) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray008() {
		try {
			magnitude = new byte[3];
			magnitude[0] = (byte) 0x01;
			magnitude[1] = (byte) 0x00;

			byte[] magnitude2 = new byte[1];
			magnitude2[0] = (byte) 0x01;
			signum = -1;
			bi = new BigInteger(signum, magnitude);

			BigInteger bi2 = new BigInteger(signum, magnitude2);

			if (bi.compareTo(bi2) == -1) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray009() {
		try {
			magnitude = new byte[500];

			for (int i = 1; i < 500; i++) {
				magnitude[i] = (byte) 0xc7;
			}

			signum = 2;
			new BigInteger(signum, magnitude);

			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray010() {
		try {
			magnitude = new byte[500];

			for (int i = 1; i < 500; i++) {
				magnitude[i] = 0;
			}

			signum = -2;
			new BigInteger(signum, magnitude);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray011() {
		try {
			magnitude = new byte[500];
			magnitude[55] = (byte) 0xc7;
			signum = 0;
			new BigInteger(signum, magnitude);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray012() {
		try {
			byte[] magnitude = new byte[500];

			for (int i = 1; i < 500; i++) {
				magnitude[i] = (byte) 0xc7;
			}

			int signum = 1;
			bi = new BigInteger(signum, magnitude);

			assertEquals(
					bi,
					new BigInteger(
							"40184224049135673547897375774062757548042250774748970748999369883288852434812202292660396331361095045880012416355126344740363307615715007277335806189490266906715536373054394949593522639577616300203442463724351307954620166888691033130336731735594350188052951549817118638784049368842555018750549905172121992366328831978516169160865673130011210066326709315123137087242705778741100842935373079244166528101567118067624399692222684323042183010766053076231568051000424330952743490663339779440984089928980563854687654728322293054823506602756395170788041085154355530105239324423856388011346292971338604017628038174487178153709230118365021486647704546166527292166414444467634604478827739572876999333808896426445356488704236106275620101048836382681306499942451637471742716371091895222002384358357994876184074629337404946007896244682668988166767265354490615872000456414601557769706088283586648112352696771189646018390620763325252676413599721102819987430774178232097325260335948902834817857207983797851356500238427016332278601183450379205284468057542476265890298309826809006354978863140050482976709559340957427196510494331252902454137080154099463447524411212401331893714252962767677129780262390489916948691263408071"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray013() {
		try {
			byte[] magnitude = { (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00 };
			int signum = 1;
			bi = new BigInteger(signum, magnitude);

			assertEquals(bi, new BigInteger("1435982080"));

		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray014() {
		try {
			byte[] magnitude = { (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xef, (byte) 0x55, (byte) 0x97, (byte) 0xa9,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x5d, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0xc9, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x0a, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0x7f,
					(byte) 0x55, (byte) 0x97, (byte) 0x5a, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0xa7, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x30 };
			int signum = 1;
			bi = new BigInteger(signum, magnitude);
			assertEquals(
					bi,
					new BigInteger(
							"24725427961391786871632876428049388551763708955622571163383481990007274544321843145937133396555313841188389404846295228476907261811076033333017824354171410201631052700122989990429637710850884052373170003323220138257684105430782009228292825100056219729366623065719124675661438984673783075682530113041747764931101134980770351689325750667950221471761814590986320556341025072"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray015() {
		try {
			byte[] magnitude = { (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xef, (byte) 0x55, (byte) 0x97,
					(byte) 0xa9, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x5d, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0xc9, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x0a, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0x7f, (byte) 0x55, (byte) 0x97, (byte) 0x5a,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0xa7,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x30 };

			int signum = 1;
			bi = new BigInteger(signum, magnitude);

			assertEquals(
					bi,
					new BigInteger(
							"18882690996727679389449482228329653301180918763835650826435091681469413387804553264788119257980656013226970934045619739789929239594122913293068502620135281650213465607622163657938282221971337630202726640171471340069701625421370351224541149644692214823552865543090139249488881602986558517265521368944506748381508831460205141990372940197422413703351820464587994791363470645552"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray016() {
		try {
			byte[] magnitude = { (byte) 0x56, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xef, (byte) 0x55, (byte) 0x97, (byte) 0xa9,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x5d, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0xc9, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x0a, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0x7f,
					(byte) 0x55, (byte) 0x97, (byte) 0x5a, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0xa7, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x30 };
			int signum = 1;
			bi = new BigInteger(signum, magnitude);
			assertEquals(
					bi,
					new BigInteger(
							"25014306110423133189074326326209645964640993806340708851117423598455182113458795220410818784493183030823588279611012958938939313470578359271432889991361684561925351636319712427132270256565658665518855565059864564114477057963195299197890142146924972158959507546724400719240072871989988417811663673685612118494467828153644499539183036428470357248138653724717892377278634288"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray017() {
		try {
			byte[] magnitude = { 17, -20, -56, 105, 64, 117, -52, -123, 119,
					31, -120, 4, -91, -123, 66, 82, -83, -126 };
			int signum = 1;
			bi = new BigInteger(signum, magnitude);
			assertEquals(bi, new BigInteger(
					"1561481891531000005641001115450345610456450"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray018() {
		try {
			byte[] magnitude = { -94, 96, 39, 81, 124, 9, 74, -102, -25, 68, 8,
					127, -71, -74, 77, 81, -81, 123, -102, -84, 44, 14, 63,
					-11, -75, 84 };
			int signum = 1;
			bi = new BigInteger(signum, magnitude);

			assertEquals(
					bi,
					new BigInteger(
							"260927529018437209349282106239335003203787556351905216932984148"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray019() {
		try {

			byte[] magnitude = { 2, 122, 22, -63, 123, -87, -117, 98, 98, -94,
					7, -27, 66, 125, -116, 69, 63, 4, 44, -109, -111, -16, 46,
					-14, -92, -47, 91 };
			int signum = 1;
			bi = new BigInteger(signum, magnitude);
			assertEquals(
					bi,
					new BigInteger(
							"1018941560489102123187480189704051894054864066108789260456087899"));

		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray020() {
		try {
			byte[] magnitude = { 4, -1, -1, -1, -25 };
			int signum = 1;
			bi = new BigInteger(signum, magnitude);
			assertEquals(bi, new BigInteger("21474836455"));

		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray021() {
		try {

			byte[] magnitude = { -128, 0, 0, 2 };
			int signum = 1;
			bi = new BigInteger(signum, magnitude);

			assertEquals(bi, new BigInteger("2147483650"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray022() {
		try {

			byte[] magnitude = { 0, -45, -62, 27, -50, -54, -24, 57, -11, 70,
					-126 };
			int signum = 1;
			bi = new BigInteger(signum, magnitude);
			assertEquals(bi, new BigInteger("999999999997777773151874"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray023() {
		try {

			byte[] magnitude = { -1, 44, 61, -28, 49, 53, 23, -58, 10, -71, 126 };
			int signum = 1;
			bi = new BigInteger(signum, magnitude);
			assertEquals(bi, new BigInteger("308485009821347290951629182"));

		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray024() {
		try {

			byte[] magnitude = { 37, -13, -117, 6, 30, 6, 99, 85, -80, 0, -39,
					116, 9, 53, -72, 109, -50, 9, -86, 100, 63, 92, 47, 122,
					93, -36, 18, -113, 40, -7, -17, 15, -75, 73, -4, -38, 21,
					48, -28, 63, -76, -99, -82, -61, -93, -27, -113, 45, 72,
					88, -110, 105, 1, -124, 44, -97, 93, 54, 76, 1, -101, -64,
					-95, -108, -119, -85, 123, 15, 70, -62, -63, -104, -99, 66,
					63, 32, 41, -47, 33, 34, 106, 48, 69, 124, 102, 22, -11,
					27, -56, 27, 53, 113, 37, 99, 23, -105, 99, -122, 27, 30,
					86, -58, -52, -95, 88, 21, 30, 39, 29, 61, -29, -24, 68,
					-44, 46, -109, 83, -65, 28, -35, 75, 45, 0, -8, 111, 47,
					-123, 20, -112, 76, -112, 65, 62, 57, -26, 69, 78, -93,
					-46, -90, 96, -85, -59, 119, -120, 67, 12, -109, 38, -125,
					75, -41, -41, -95, -38, -126, 36, -27, 17, -22, -18, 103,
					71, -19, -8, -40, 89, -113, 110, -77, 59, 30, 94, 62, 47,
					-35, 98, 88, -77, -61, -43, -91, -73, -121, 122, 84, -34,
					-44, 8, 53, -37, -46, -90, 108, 60, 90, 14, -99, -53, -94,
					69, -75, 68, -24, 97, -125, 121, -106, 112, 19, -18, 67,
					28, -90, -23, -57, 21, -115, 110, -19, -24, 65, 3, -25,
					-23, 55, -39, -15, 107, -48, -5, 32, -94, 45, -85, 117, 98,
					-49, -68, 15, -127, -121, 45, 70, -23, 94, 60, 52, 24,
					-127, 4, -24, -83, 90, -91, 62, -36, -23, -128, 93, -53,
					38, -85, 58, 68, -7, -110, 66, -115, -11, -24, 13, -99,
					118, -119, 57, -97, -11, 36, 54, 71, 52, -25, 69, -77, -99,
					9, 70, -63, -89, -76, 49, -112, 2 };
			int signum = 1;
			bi = new BigInteger(signum, magnitude);
			assertEquals(
					bi,
					new BigInteger(
							"156148189153100000564100111545034561045645015614818915310000056410011154503456104564501561481891531000005641001115450345610456450156148189153100000564100111545034561045645015614818915310000056410011154503456104564501561481891531000005641001115450345610456450156148189153100000564100111545034561045645015614818915310000056410011154503456104564501561481891531000005641001115450345610456450156148189153100000564100111545034561045645015614818915310000056410011154503456104564501561481891531000005641001115450345610456450156148189153100000564100111545034561045645015614818915310000056410011154503456104564501561481891531000005641001115450345610415044861031186430118946018940000262304189641005648974890416810856450"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray025() {
		try {
			byte[] magnitude = { -26, -5, 77, -16, 4, -88, 113, -78, 47, -113,
					118, 26, 10, 7, 3, 18, -73, -91, 85, -88, -81, 112, 12,
					-32, 13, 75, -16, 37, -119, 97, -31, -81, -23, 87, 113, 77,
					124, 39, 28, -36, 31, -102, 92, 58, 49, -24, 28, -63, 8,
					72, 0, 88, 18, 113, 68, -68, 57, 59, 28, 48, 68, -94, -126,
					60, -89, -47, 3, -32, 11, 71, -86, -50, 126, 92, -118, -81,
					-99, 77, -64, -125, -108, -114, -72, 66, -53, 94, 96, 25,
					-77, -25, 93, 75, 69, 37, -111, 68, -58, 118, 21, 4, -113,
					-76, 63, -64, 124, -95, -44, 71, -8, 86, 47, -78, 30, 23,
					-95, -6, 62, -57, 85, 95, -85, -124, 27, 15, 100, 73, -88,
					-32, -16, 73, -52, -85, 52, 3, -35, 75, -118, -80, -83, 8,
					-101, -52, -120, -39, 39, 118, -5, -109, 41, 10, 14, -5,
					58, -64, 107, 81, 12, 34, 73, -49, 52, 82, -71, -99, -70,
					-25, -31, -53, 38, -94, -51, -50, 6, -80, -116, 7, 14, 103,
					-89, -11, -72, -126, -110, 75, 97, -22, -99, 49, -16, -76,
					125, -78, 102, 2, -19, 67, 11, -103, 22, 70, 66, 19, 120,
					7, -93, -15, 51, 65, -110, 122, 108, 59, 40, 121, -9, 93,
					-17, 70, 49, -41, 104, -76, 81, -102, -112, 33, 108, -26,
					114, -30, 97, 77, 96, 117, 7, -126, 7, -84, -105, 42, -47,
					-85, -9, 52, -30, -42, 114, -14, 110, -55, -58, 38, -3, -7,
					-1, -116, -35, -9, -7, 3, 125, -12, -40, -126, 13, -1, 44,
					101, 59, 88, -67, 56, -3, 0, -49, 84, 16, -28, -89, -111,
					22, -84, -36, 59, -126, 32, -108, -20, 125, -42, -49, 45,
					121, 53, 61, 70, 51, -2, 97, -46, -119, 12, -64, -93, -108,
					80, -6, -84, 44, 14, 63, -11, -75, 84 };
			int signum = 1;
			bi = new BigInteger(signum, magnitude);

			assertEquals(
					bi,
					new BigInteger(
							"1388950079584762611644040573509001123912018497041822976326032606066939462931088135519349350080996626364614847977958167688737555761894721882007093702716229246665386775192885994452418312407267594904799428336444949407563442963456677585137742511217610928465625936095727091312332801336868818384615398000474634896571018715303921966290495076036180215110321561664229536906784047292826467835236766518700727060201611572548318834644308137128418243171977034805564922303845824248273940879766455186690694407527477515902563761752356757437384778568009637777286027614031227236731271193738327895018154176902453641388297250580065276931255023703213257452281989104812074936720138353569691098071437344030251392427272995964326756797836861043546296913005712384942883519680126235988"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray026() {
		try {
			byte[] magnitude = new byte[500];

			for (int i = 1; i < 500; i++) {
				magnitude[i] = (byte) 0xc7;
			}

			int signum = -1;
			bi = new BigInteger(signum, magnitude);

			assertEquals(
					bi,
					new BigInteger(
							"-40184224049135673547897375774062757548042250774748970748999369883288852434812202292660396331361095045880012416355126344740363307615715007277335806189490266906715536373054394949593522639577616300203442463724351307954620166888691033130336731735594350188052951549817118638784049368842555018750549905172121992366328831978516169160865673130011210066326709315123137087242705778741100842935373079244166528101567118067624399692222684323042183010766053076231568051000424330952743490663339779440984089928980563854687654728322293054823506602756395170788041085154355530105239324423856388011346292971338604017628038174487178153709230118365021486647704546166527292166414444467634604478827739572876999333808896426445356488704236106275620101048836382681306499942451637471742716371091895222002384358357994876184074629337404946007896244682668988166767265354490615872000456414601557769706088283586648112352696771189646018390620763325252676413599721102819987430774178232097325260335948902834817857207983797851356500238427016332278601183450379205284468057542476265890298309826809006354978863140050482976709559340957427196510494331252902454137080154099463447524411212401331893714252962767677129780262390489916948691263408071"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray027() {
		try {
			byte[] magnitude = { (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00 };
			int signum = -1;
			bi = new BigInteger(signum, magnitude);

			assertEquals(bi, new BigInteger("-1435982080"));

		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray028() {
		try {
			byte[] magnitude = { (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xef, (byte) 0x55, (byte) 0x97, (byte) 0xa9,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x5d, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0xc9, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x0a, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0x7f,
					(byte) 0x55, (byte) 0x97, (byte) 0x5a, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0xa7, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x30 };
			int signum = -1;
			bi = new BigInteger(signum, magnitude);
			assertEquals(
					bi,
					new BigInteger(
							"-24725427961391786871632876428049388551763708955622571163383481990007274544321843145937133396555313841188389404846295228476907261811076033333017824354171410201631052700122989990429637710850884052373170003323220138257684105430782009228292825100056219729366623065719124675661438984673783075682530113041747764931101134980770351689325750667950221471761814590986320556341025072"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray029() {
		try {
			byte[] magnitude = { (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xef, (byte) 0x55, (byte) 0x97,
					(byte) 0xa9, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x5d, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0xc9, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x0a, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0x7f, (byte) 0x55, (byte) 0x97, (byte) 0x5a,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0xa7,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x30 };
			int signum = -1;
			bi = new BigInteger(signum, magnitude);

			assertEquals(
					bi,
					new BigInteger(
							"-18882690996727679389449482228329653301180918763835650826435091681469413387804553264788119257980656013226970934045619739789929239594122913293068502620135281650213465607622163657938282221971337630202726640171471340069701625421370351224541149644692214823552865543090139249488881602986558517265521368944506748381508831460205141990372940197422413703351820464587994791363470645552"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray030() {
		try {
			byte[] magnitude = { (byte) 0x56, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xef, (byte) 0x55, (byte) 0x97, (byte) 0xa9,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x5d, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0xc9, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x0a, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0xff,
					(byte) 0x55, (byte) 0x97, (byte) 0x59, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0x97, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x00, (byte) 0x7f,
					(byte) 0x55, (byte) 0x97, (byte) 0x5a, (byte) 0x00,
					(byte) 0xff, (byte) 0x55, (byte) 0xa7, (byte) 0x59,
					(byte) 0x00, (byte) 0xff, (byte) 0x55, (byte) 0x97,
					(byte) 0x59, (byte) 0x00, (byte) 0xff, (byte) 0x55,
					(byte) 0x97, (byte) 0x59, (byte) 0x30 };
			int signum = -1;
			bi = new BigInteger(signum, magnitude);
			assertEquals(
					bi,
					new BigInteger(
							"-25014306110423133189074326326209645964640993806340708851117423598455182113458795220410818784493183030823588279611012958938939313470578359271432889991361684561925351636319712427132270256565658665518855565059864564114477057963195299197890142146924972158959507546724400719240072871989988417811663673685612118494467828153644499539183036428470357248138653724717892377278634288"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray031() {
		try {
			byte[] magnitude = { 17, -20, -56, 105, 64, 117, -52, -123, 119,
					31, -120, 4, -91, -123, 66, 82, -83, -126 };
			int signum = -1;
			bi = new BigInteger(signum, magnitude);
			assertEquals(bi, new BigInteger(
					"-1561481891531000005641001115450345610456450"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray032() {
		try {
			byte[] magnitude = { -94, 96, 39, 81, 124, 9, 74, -102, -25, 68, 8,
					127, -71, -74, 77, 81, -81, 123, -102, -84, 44, 14, 63,
					-11, -75, 84 };
			int signum = -1;
			bi = new BigInteger(signum, magnitude);
			assertEquals(
					bi,
					new BigInteger(
							"-260927529018437209349282106239335003203787556351905216932984148"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray033() {
		try {

			byte[] magnitude = { 2, 122, 22, -63, 123, -87, -117, 98, 98, -94,
					7, -27, 66, 125, -116, 69, 63, 4, 44, -109, -111, -16, 46,
					-14, -92, -47, 91 };
			int signum = -1;
			bi = new BigInteger(signum, magnitude);
			assertEquals(
					bi,
					new BigInteger(
							"-1018941560489102123187480189704051894054864066108789260456087899"));

		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray034() {
		try {
			byte[] magnitude = { 4, -1, -1, -1, -25 };
			int signum = -1;
			bi = new BigInteger(signum, magnitude);
			assertEquals(bi, new BigInteger("-21474836455"));

		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray035() {
		try {

			byte[] magnitude = { -128, 0, 0, 2 };
			int signum = -1;
			bi = new BigInteger(signum, magnitude);
			assertEquals(bi, new BigInteger("-2147483650"));

		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray036() {
		try {

			byte[] magnitude = { 0, -45, -62, 27, -50, -54, -24, 57, -11, 70,
					-126 };
			int signum = -1;
			bi = new BigInteger(signum, magnitude);
			assertEquals(bi, new BigInteger("-999999999997777773151874"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray037() {
		try {

			byte[] magnitude = { -1, 44, 61, -28, 49, 53, 23, -58, 10, -71, 126 };
			int signum = -1;
			bi = new BigInteger(signum, magnitude);

			assertEquals(bi, new BigInteger("-308485009821347290951629182"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray038() {
		try {

			byte[] magnitude = { 37, -13, -117, 6, 30, 6, 99, 85, -80, 0, -39,
					116, 9, 53, -72, 109, -50, 9, -86, 100, 63, 92, 47, 122,
					93, -36, 18, -113, 40, -7, -17, 15, -75, 73, -4, -38, 21,
					48, -28, 63, -76, -99, -82, -61, -93, -27, -113, 45, 72,
					88, -110, 105, 1, -124, 44, -97, 93, 54, 76, 1, -101, -64,
					-95, -108, -119, -85, 123, 15, 70, -62, -63, -104, -99, 66,
					63, 32, 41, -47, 33, 34, 106, 48, 69, 124, 102, 22, -11,
					27, -56, 27, 53, 113, 37, 99, 23, -105, 99, -122, 27, 30,
					86, -58, -52, -95, 88, 21, 30, 39, 29, 61, -29, -24, 68,
					-44, 46, -109, 83, -65, 28, -35, 75, 45, 0, -8, 111, 47,
					-123, 20, -112, 76, -112, 65, 62, 57, -26, 69, 78, -93,
					-46, -90, 96, -85, -59, 119, -120, 67, 12, -109, 38, -125,
					75, -41, -41, -95, -38, -126, 36, -27, 17, -22, -18, 103,
					71, -19, -8, -40, 89, -113, 110, -77, 59, 30, 94, 62, 47,
					-35, 98, 88, -77, -61, -43, -91, -73, -121, 122, 84, -34,
					-44, 8, 53, -37, -46, -90, 108, 60, 90, 14, -99, -53, -94,
					69, -75, 68, -24, 97, -125, 121, -106, 112, 19, -18, 67,
					28, -90, -23, -57, 21, -115, 110, -19, -24, 65, 3, -25,
					-23, 55, -39, -15, 107, -48, -5, 32, -94, 45, -85, 117, 98,
					-49, -68, 15, -127, -121, 45, 70, -23, 94, 60, 52, 24,
					-127, 4, -24, -83, 90, -91, 62, -36, -23, -128, 93, -53,
					38, -85, 58, 68, -7, -110, 66, -115, -11, -24, 13, -99,
					118, -119, 57, -97, -11, 36, 54, 71, 52, -25, 69, -77, -99,
					9, 70, -63, -89, -76, 49, -112, 2 };
			int signum = -1;
			bi = new BigInteger(signum, magnitude);
			assertEquals(
					bi,
					new BigInteger(
							"-156148189153100000564100111545034561045645015614818915310000056410011154503456104564501561481891531000005641001115450345610456450156148189153100000564100111545034561045645015614818915310000056410011154503456104564501561481891531000005641001115450345610456450156148189153100000564100111545034561045645015614818915310000056410011154503456104564501561481891531000005641001115450345610456450156148189153100000564100111545034561045645015614818915310000056410011154503456104564501561481891531000005641001115450345610456450156148189153100000564100111545034561045645015614818915310000056410011154503456104564501561481891531000005641001115450345610415044861031186430118946018940000262304189641005648974890416810856450"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntByteArray039() {
		try {
			byte[] magnitude = { -26, -5, 77, -16, 4, -88, 113, -78, 47, -113,
					118, 26, 10, 7, 3, 18, -73, -91, 85, -88, -81, 112, 12,
					-32, 13, 75, -16, 37, -119, 97, -31, -81, -23, 87, 113, 77,
					124, 39, 28, -36, 31, -102, 92, 58, 49, -24, 28, -63, 8,
					72, 0, 88, 18, 113, 68, -68, 57, 59, 28, 48, 68, -94, -126,
					60, -89, -47, 3, -32, 11, 71, -86, -50, 126, 92, -118, -81,
					-99, 77, -64, -125, -108, -114, -72, 66, -53, 94, 96, 25,
					-77, -25, 93, 75, 69, 37, -111, 68, -58, 118, 21, 4, -113,
					-76, 63, -64, 124, -95, -44, 71, -8, 86, 47, -78, 30, 23,
					-95, -6, 62, -57, 85, 95, -85, -124, 27, 15, 100, 73, -88,
					-32, -16, 73, -52, -85, 52, 3, -35, 75, -118, -80, -83, 8,
					-101, -52, -120, -39, 39, 118, -5, -109, 41, 10, 14, -5,
					58, -64, 107, 81, 12, 34, 73, -49, 52, 82, -71, -99, -70,
					-25, -31, -53, 38, -94, -51, -50, 6, -80, -116, 7, 14, 103,
					-89, -11, -72, -126, -110, 75, 97, -22, -99, 49, -16, -76,
					125, -78, 102, 2, -19, 67, 11, -103, 22, 70, 66, 19, 120,
					7, -93, -15, 51, 65, -110, 122, 108, 59, 40, 121, -9, 93,
					-17, 70, 49, -41, 104, -76, 81, -102, -112, 33, 108, -26,
					114, -30, 97, 77, 96, 117, 7, -126, 7, -84, -105, 42, -47,
					-85, -9, 52, -30, -42, 114, -14, 110, -55, -58, 38, -3, -7,
					-1, -116, -35, -9, -7, 3, 125, -12, -40, -126, 13, -1, 44,
					101, 59, 88, -67, 56, -3, 0, -49, 84, 16, -28, -89, -111,
					22, -84, -36, 59, -126, 32, -108, -20, 125, -42, -49, 45,
					121, 53, 61, 70, 51, -2, 97, -46, -119, 12, -64, -93, -108,
					80, -6, -84, 44, 14, 63, -11, -75, 84 };
			int signum = -1;
			bi = new BigInteger(signum, magnitude);

			assertEquals(
					bi,
					new BigInteger(
							"-1388950079584762611644040573509001123912018497041822976326032606066939462931088135519349350080996626364614847977958167688737555761894721882007093702716229246665386775192885994452418312407267594904799428336444949407563442963456677585137742511217610928465625936095727091312332801336868818384615398000474634896571018715303921966290495076036180215110321561664229536906784047292826467835236766518700727060201611572548318834644308137128418243171977034805564922303845824248273940879766455186690694407527477515902563761752356757437384778568009637777286027614031227236731271193738327895018154176902453641388297250580065276931255023703213257452281989104812074936720138353569691098071437344030251392427272995964326756797836861043546296913005712384942883519680126235988"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.BigInteger.BigInteger(String)'
	 */
	public void testBigIntegerString001() {
		try {
			valString = "2084534089640156400533116641056046";
			bi = new BigInteger(valString);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerString002() {
		try {
			valString = "0";
			bi = new BigInteger(valString);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerString003() {
		try {
			valString = "-2084534089640156400533116641056046";
			bi = new BigInteger(valString);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerString004() {
		try {
			valString = "-20845340896401aa400533116641056046";
			bi = new BigInteger(valString);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerString005() {
		try {
			valString = "208453408964014 00533116641056046";
			bi = new BigInteger(valString);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerString006() {
		try {
			valString = "";
			bi = new BigInteger(valString);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerString007() {
		try {
			valString = "5,8";
			bi = new BigInteger(valString);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.BigInteger.BigInteger(String, int)'
	 */
	public void testBigIntegerStringInt001() {
		try {
			radix = 2;
			valString = "1111111111000000000001111111111110000111111110111000000000110000000000011111111111100000000000001111111111110000000000011111111111111100000000000000111111111111111000000000001111111111111111111111111000000000"
					+ "111111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111111111111111";
			bi = new BigInteger(valString, radix);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt002() {
		try {
			radix = 2;
			valString = "0";
			bi = new BigInteger(valString, radix);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt003() {
		try {
			radix = 2;
			valString = "-010101010101011111111111111111110000000000000111111111000000001111111111100000000000001111100000000111111110000000000000000001111111111111100000000";
			bi = new BigInteger(valString, radix);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt004() {
		try {
			radix = 2;
			valString = "-000000011111000020000000111";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt005() {
		try {
			radix = 2;
			valString = "000001111111 11111110000000";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt006() {
		try {
			radix = 2;
			valString = "";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt007() {
		try {
			radix = 2;
			valString = "0,01";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt008() {
		try {
			radix = 16;
			valString = "151874e184186b411167841874411c21384874513f52d41854157484a746";
			bi = new BigInteger(valString, radix);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt009() {
		try {
			radix = 16;
			valString = "0";
			bi = new BigInteger(valString, radix);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt010() {
		try {
			radix = 16;
			valString = "-151874e184186b411167841874411c21384874513f52d41854157484a746";
			bi = new BigInteger(valString, radix);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt011() {
		try {
			radix = 16;
			valString = "-151874e184186bg411167841874411c21384874513f52d41854157484a746";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt012() {
		try {
			radix = 16;
			valString = "151874e184186b411167841874411c21384874513f5 2d41854157484a746";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt013() {
		try {
			radix = 16;
			valString = "";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt014() {
		try {
			radix = 16;
			valString = "151874e184186b41116784187,4411c21384874513f52d41854157484a746";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt015() {
		try {
			radix = 36;
			valString = "151874e184186b41vewraaaajy11678liiiirroiup418744grweqaagncmpiut11zc21384874513f52d41854157484a746";
			bi = new BigInteger(valString, radix);

			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt016() {
		try {
			radix = 36;
			valString = "0";
			bi = new BigInteger(valString, radix);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt017() {
		try {
			radix = 36;
			valString = "-151874e184186b41vewraaaajy11678liiiirroiup418744grweqaagncmpiut11zc21384874513f52d41854157484a746";
			bi = new BigInteger(valString, radix);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt018() {
		try {
			radix = 36;
			valString = "-151874e184186b41vewraañaajy11678liiiirroiup418744grweqaagncmpiut11zc21384874513f52d41854157484a746";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt019() {
		try {
			radix = 36;
			valString = "151874e184186b41vewraaaajy11678liiiirroiup418744grweqaagncmp iut11zc21384874513f52d41854157484a746";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt020() {
		try {
			radix = 36;
			valString = "";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt021() {
		try {
			radix = 36;
			valString = "151874e184186b41vewraaaajy11678liiiirroiup418744grweqaagncmpiut11zc213848,74513f52d41854157484a746";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt022() {
		try {
			radix = 1;
			valString = "1111111111000000000001111111111110000111111110111000000000110000000000011111111111100000000000001111111111110000000000011111111111111100000000000000111111111111111000000000001111111111111111111111111000000000"
					+ "111111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111111111111111";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt023() {
		try {
			radix = 1;
			valString = "0";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt024() {
		try {
			radix = 1;
			valString = "-010101010101011111111111111111110000000000000111111111000000001111111111100000000000001111100000000111111110000000000000000001111111111111100000000";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt025() {
		try {
			radix = 1;
			valString = "-000000011111000020000000111";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt026() {
		try {
			radix = 1;
			valString = "000001111111 11111110000000";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt027() {
		try {
			radix = 1;
			valString = "";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt028() {
		try {
			radix = 1;
			valString = "0,01";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt029() {
		try {
			radix = 0;
			valString = "1111111111000000000001111111111110000111111110111000000000110000000000011111111111100000000000001111111111110000000000011111111111111100000000000000111111111111111000000000001111111111111111111111111000000000"
					+ "111111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111111111111111";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt030() {
		try {
			radix = 0;
			valString = "0";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt031() {
		try {
			radix = 0;
			valString = "-010101010101011111111111111111110000000000000111111111000000001111111111100000000000001111100000000111111110000000000000000001111111111111100000000";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt032() {
		try {
			radix = 0;
			valString = "-000000011111000020000000111";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt033() {
		try {
			radix = 0;
			valString = "000001111111 11111110000000";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt034() {
		try {
			radix = 0;
			valString = "";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt035() {
		try {
			radix = 0;
			valString = "0,01";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt036() {
		try {
			radix = 37;
			valString = "1111111111000000000001111111111110000111111110111000000000110000000000011111111111100000000000001111111111110000000000011111111111111100000000000000111111111111111000000000001111111111111111111111111000000000"
					+ "111111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111111111111111";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt037() {
		try {
			radix = 37;
			valString = "0";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt038() {
		try {
			radix = 37;
			valString = "-010101010101011111111111111111110000000000000111111111000000001111111111100000000000001111100000000111111110000000000000000001111111111111100000000";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt039() {
		try {
			radix = 37;
			valString = "-000000011111000020000000111";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt040() {
		try {
			radix = 37;
			valString = "000001111111 11111110000000";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt041() {
		try {
			radix = 37;
			valString = "";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerStringInt042() {
		try {
			radix = 37;
			valString = "0,01";
			bi = new BigInteger(valString, radix);
			fail("Should raise NumberFormatException");
		} catch (NumberFormatException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.BigInteger.BigInteger(int, Random)'
	 */
	public void testBigIntegerIntRandom001() {
		try {
			int numBits = 1;
			Random rnd = new Random();

			bi = new BigInteger(numBits, rnd);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}



	public void testBigIntegerIntRandom002() {
		try {
			int numBits = 200000000;
			Random rnd = new Random();

			bi = new BigInteger(numBits, rnd);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom003() {
		try {
			int numBits = 0;
			Random rnd = new Random();

			bi = new BigInteger(numBits, rnd);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom004() {
		try {
			int numBits = -1;
			Random rnd = new Random();

			bi = new BigInteger(numBits, rnd);
			fail("Should raise IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		} catch (Throwable e) {
			fail(msgRaise + "IllegalArgumentException");
		}
	}

	public void testBigIntegerIntRandom005() {
		try {
			int numBits = Integer.MIN_VALUE;
			Random rnd = new Random();

			bi = new BigInteger(numBits, rnd);
			fail("Should raise IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		} catch (Throwable e) {
			fail(msgRaise + "IllegalArgumentException");
		}
	}

	public void testBigIntegerIntRandom006() {
		try {
			int numBits = 1;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(numBits, rnd);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}


	public void testBigIntegerIntRandom007() {
		try {
			int numBits = Integer.MAX_VALUE;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(numBits, rnd);
			assertNotNull(msgNotNull, bi);
			fail("Should raise OutOfMemoryError");
		} catch (OutOfMemoryError e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom008() {
		try {
			int numBits = 0;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(numBits, rnd);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom009() {
		try {
			int numBits = -1;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(numBits, rnd);
			fail("Should raise IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		} catch (Throwable e) {
			fail(msgRaise + "IllegalArgumentException");
		}
	}

	public void testBigIntegerIntRandom010() {
		try {
			int numBits = Integer.MIN_VALUE;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(numBits, rnd);
			fail("Should raise IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		} catch (Throwable e) {
			fail(msgRaise + "IllegalArgumentException");
		}
	}

	public void testBigIntegerIntRandom011() {
		try {
			int numBits = 1;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(numBits, rnd);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom012() {
		try {
			int numBits = 10000000;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(numBits, rnd);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom013() {
		try {
			int numBits = 0;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(numBits, rnd);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom014() {
		try {
			int numBits = -1;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(numBits, rnd);
			fail("Should raise IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		} catch (Throwable e) {
			fail(msgRaise + "IllegalArgumentException");
		}
	}

	public void testBigIntegerIntRandom015() {
		try {
			int numBits = Integer.MIN_VALUE;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(numBits, rnd);
			fail("Should raise IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		} catch (Throwable e) {
			fail(msgRaise + "IllegalArgumentException");
		}
	}

	public void testBigIntegerIntRandom016() {
		try {
			int numBits = 1;

			Random rnd = null;
			bi = new BigInteger(numBits, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntRandom017() {
		try {
			int numBits = 10000000;

			Random rnd = null;
			bi = new BigInteger(numBits, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntRandom018() {
		try {
			int numBits = 0;

			Random rnd = null;
			bi = new BigInteger(numBits, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntRandom019() {
		try {
			int numBits = -1;

			Random rnd = null;
			bi = new BigInteger(numBits, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntRandom020() {
		try {
			int numBits = Integer.MIN_VALUE;

			Random rnd = null;
			bi = new BigInteger(numBits, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntRandom021() {
		try {
			int numBits = 1;
			Random rnd = new Random();

			bi = new BigInteger(numBits, rnd);

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.compareTo(bi2), 1);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom022() {
		try {
			int numBits = 24700000;
			Random rnd = new Random();

			bi = new BigInteger(numBits, rnd);

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.compareTo(bi2), 1);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom023() {
		try {
			int numBits = 0;
			Random rnd = new Random();

			bi = new BigInteger(numBits, rnd);

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.compareTo(bi2), 1);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom024() {
		try {
			int numBits = 1;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(numBits, rnd);

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.compareTo(bi2), 1);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom025() {
		try {
			int numBits = 10000000;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(numBits, rnd);

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.compareTo(bi2), 1);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom026() {
		try {
			int numBits = 0;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(numBits, rnd);

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.compareTo(bi2), 1);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom027() {
		try {
			int numBits = 1;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(numBits, rnd);

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.compareTo(bi2), 1);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom028() {
		try {
			int numBits = 10000000;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(numBits, rnd);

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.compareTo(bi2), 1);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom029() {
		try {
			int numBits = 0;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(numBits, rnd);

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.compareTo(bi2), 1);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}



	public void testBigIntegerIntRandom031() {
		try {
			int numBits = 1;
			Random rnd = new Random();
			bi = new BigInteger(numBits, rnd);

			assertTrue(bi.bitLength() <= numBits);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom032() {
		try {
			int numBits = 100000;
			Random rnd = new Random();
			bi = new BigInteger(numBits, rnd);

			assertTrue(bi.bitLength() <= numBits);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom033() {
		try {
			int numBits = 0;
			Random rnd = new Random();
			bi = new BigInteger(numBits, rnd);

			assertTrue(bi.bitLength() <= numBits);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom034() {
		try {
			int numBits = 1;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(numBits, rnd);

			assertTrue(bi.bitLength() <= numBits);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom035() {
		try {
			int numBits = 100000;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(numBits, rnd);

			assertTrue(bi.bitLength() <= numBits);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom036() {
		try {
			int numBits = 0;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(numBits, rnd);
            BigInteger bi2 = new BigInteger("-1");

			assertEquals(bi.compareTo(bi2), 1);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom037() {
		try {
			int numBits = 1;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(numBits, rnd);

			assertTrue(bi.bitLength() <= numBits);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom038() {
		try {
			int numBits = 1000000;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(numBits, rnd);

			assertTrue(bi.bitLength() <= numBits);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntRandom039() {
		try {
			int numBits = 0;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(numBits, rnd);

			assertTrue(bi.bitLength() <= numBits);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}



	/*
	 * Test method for 'java.math.BigInteger.BigInteger(int, int, Random)'
	 */
	public void testBigIntegerIntIntRandom001() {
		try {
			int bitLength = 2;
			int certainty = 0;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);
			assertNotNull(msgNotNull, bi);

		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom002() {
		try {
			int bitLength = 300;
			int certainty = 0;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);

			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom003() {
		try {
			int bitLength = 1;
			int certainty = 0;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);

			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom004() {
		try {
			int bitLength = 0;
			int certainty = 0;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);

			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom005() {
		try {
			int bitLength = -1;
			int certainty = 0;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom006() {
		try {
			int bitLength = Integer.MIN_VALUE;
			int certainty = 0;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom007() {
		try {
			int bitLength = 2;
			int certainty = 0;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);

			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom008() {
		try {
			int bitLength = 300;
			int certainty = 0;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);

			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom009() {
		try {
			int bitLength = 1;
			int certainty = 0;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);

			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom010() {
		try {
			int bitLength = 0;
			int certainty = 0;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);

			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom011() {
		try {
			int bitLength = -1;
			int certainty = 0;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);

			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom012() {
		try {
			int bitLength = Integer.MIN_VALUE;
			int certainty = 0;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom013() {
		try {
			int bitLength = 2;
			int certainty = 0;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom014() {
		try {
			int bitLength = 300;
			int certainty = 0;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom015() {
		try {
			int bitLength = 1;
			int certainty = 0;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom016() {
		try {
			int bitLength = 0;
			int certainty = 0;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom017() {
		try {
			int bitLength = -1;
			int certainty = 0;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom018() {
		try {
			int bitLength = Integer.MIN_VALUE;
			int certainty = 0;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom019() {
		try {
			int bitLength = 2;
			int certainty = 0;
			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom020() {
		try {
			int bitLength = 300;
			int certainty = 0;
			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom021() {
		try {
			int bitLength = 1;
			int certainty = 0;
			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom022() {
		try {
			int bitLength = 0;
			int certainty = 0;
			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom023() {
		try {
			int bitLength = -1;
			int certainty = 10;

			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom024() {
		try {
			int bitLength = Integer.MIN_VALUE;
			int certainty = 10;
			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom025() {
		try {
			int bitLength = 2;
			int certainty = 10;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);

			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom026() {
		try {
			int bitLength = 300;
			int certainty = 10;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);

			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom027() {
		try {
			int bitLength = 1;
			int certainty = 10;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom028() {
		try {
			int bitLength = 0;
			int certainty = 10;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom029() {
		try {
			int bitLength = -1;
			int certainty = 10;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom030() {
		try {
			int bitLength = Integer.MIN_VALUE;
			int certainty = 10;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom031() {
		try {
			int bitLength = 2;
			int certainty = 10;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);

			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom032() {
		try {
			int bitLength = 300;
			int certainty = 10;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);

			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom033() {
		try {
			int bitLength = 1;
			int certainty = 10;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom034() {
		try {
			int bitLength = 0;
			int certainty = 10;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom035() {
		try {
			int bitLength = -1;
			int certainty = 10;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom036() {
		try {
			int bitLength = Integer.MIN_VALUE;
			int certainty = 10;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom037() {
		try {
			int bitLength = 2;
			int certainty = 10;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);

			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom038() {
		try {
			int bitLength = 300;
			int certainty = 10;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);

			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom039() {
		try {
			int bitLength = 1;
			int certainty = 10;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom040() {
		try {
			int bitLength = 0;
			int certainty = 10;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom041() {
		try {
			int bitLength = -1;
			int certainty = 10;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);

			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom042() {
		try {
			int bitLength = Integer.MIN_VALUE;
			int certainty = 10;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom043() {
		try {
			int bitLength = 2;
			int certainty = 10;
			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom044() {
		try {
			int bitLength = 300;
			int certainty = 10;
			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom045() {
		try {
			int bitLength = 1;
			int certainty = 10;
			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom046() {
		try {
			int bitLength = 0;
			int certainty = 10;
			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom047() {
		try {
			int bitLength = -1;
			int certainty = 10;

			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom048() {
		try {
			int bitLength = Integer.MIN_VALUE;
			int certainty = 10;
			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom101() {
		try {
			int bitLength = 2;
			int certainty = -1;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);

			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom102() {
		try {
			int bitLength = 300;
			int certainty = -1;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);

			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom103() {
		try {
			int bitLength = 1;
			int certainty = -1;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom104() {
		try {
			int bitLength = 0;
			int certainty = -1;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);

			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom105() {
		try {
			int bitLength = -1;
			int certainty = -1;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom106() {
		try {
			int bitLength = Integer.MIN_VALUE;
			int certainty = -1;
			Random rnd = new Random();
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom107() {
		try {
			int bitLength = 2;
			int certainty = -1;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom108() {
		try {
			int bitLength = 300;
			int certainty = -1;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom109() {
		try {
			int bitLength = 1;
			int certainty = -1;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom110() {
		try {
			int bitLength = 0;
			int certainty = -1;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom111() {
		try {
			int bitLength = -1;
			int certainty = -1;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom112() {
		try {
			int bitLength = Integer.MIN_VALUE;
			int certainty = -1;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom113() {
		try {
			int bitLength = 2;
			int certainty = -1;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);

			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom114() {
		try {
			int bitLength = 300;
			int certainty = -1;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);

			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom115() {
		try {
			int bitLength = 1;
			int certainty = -1;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom116() {
		try {
			int bitLength = 0;
			int certainty = -1;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom117() {
		try {
			int bitLength = -1;
			int certainty = -1;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom118() {
		try {
			int bitLength = Integer.MIN_VALUE;
			int certainty = -1;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom119() {
		try {
			int bitLength = 2;
			int certainty = -1;
			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom120() {
		try {
			int bitLength = 300;
			int certainty = -1;
			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom121() {
		try {
			int bitLength = 1;
			int certainty = -1;
			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom122() {
		try {
			int bitLength = 0;
			int certainty = -1;
			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom123() {
		try {
			int bitLength = -1;
			int certainty = -10;
			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom124() {
		try {
			int bitLength = Integer.MIN_VALUE;
			int certainty = -10;
			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom125() {
		try {
			int bitLength = 2;
			int certainty = -10;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);

			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom126() {
		try {
			int bitLength = 300;
			int certainty = -10;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);

			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom127() {
		try {
			int bitLength = 1;
			int certainty = -10;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom128() {
		try {
			int bitLength = 0;
			int certainty = -10;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom129() {
		try {
			int bitLength = -1;
			int certainty = -10;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom130() {
		try {
			int bitLength = Integer.MIN_VALUE;
			int certainty = -10;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom131() {
		try {
			int bitLength = 2;
			int certainty = -10;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);

			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom132() {
		try {
			int bitLength = 300;
			int certainty = -10;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);

			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom133() {
		try {
			int bitLength = 1;
			int certainty = -10;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom134() {
		try {
			int bitLength = 0;
			int certainty = -10;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom135() {
		try {
			int bitLength = -1;
			int certainty = -10;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom136() {
		try {
			int bitLength = Integer.MIN_VALUE;
			int certainty = -10;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom137() {
		try {
			int bitLength = 2;
			int certainty = -10;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);

			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom138() {
		try {
			int bitLength = 300;
			int certainty = -10;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);

			assertNotNull(msgNotNull, bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom139() {
		try {
			int bitLength = 1;
			int certainty = -10;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);

			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom140() {
		try {
			int bitLength = 0;
			int certainty = -10;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom141() {
		try {
			int bitLength = -1;
			int certainty = -10;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public void testBigIntegerIntIntRandom142() {
		try {
			int bitLength = Integer.MIN_VALUE;
			int certainty = -10;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise ArithmeticException");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom143() {
		try {
			int bitLength = 2;
			int certainty = -10;
			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom144() {
		try {
			int bitLength = 300;
			int certainty = -10;
			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom145() {
		try {
			int bitLength = 1;
			int certainty = -10;
			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom146() {
		try {
			int bitLength = 0;
			int certainty = -10;
			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom147() {
		try {
			int bitLength = -1;
			int certainty = -10;

			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom148() {
		try {
			int bitLength = Integer.MIN_VALUE;
			int certainty = -10;
			Random rnd = null;
			bi = new BigInteger(bitLength, certainty, rnd);
			fail("Should raise an Exception");
		} catch (Throwable e) {
			assertTrue(true);
		}
	}

	public void testBigIntegerIntIntRandom149() {
		try {
			int bitLength = 2;
			int certainty = 100;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);
			assertTrue(bi.isProbablePrime(100));
			assertEquals(bi.bitLength(), 2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom150() {
		try {
			int bitLength = 300;
			int certainty = 100;
			Random rnd = new Random();

			bi = new BigInteger(bitLength, certainty, rnd);

			assertTrue(bi.isProbablePrime(100));
			assertEquals(bi.bitLength(), 300);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom151() {
		try {
			int bitLength = 2;
			int certainty = 100;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			assertTrue(bi.isProbablePrime(100));
			assertEquals(bi.bitLength(), 2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom152() {
		try {
			int bitLength = 300;
			int certainty = 100;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			assertTrue(bi.isProbablePrime(100));
			assertEquals(bi.bitLength(), 300);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom153() {
		try {
			int bitLength = 2;
			int certainty = 100;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);
			assertTrue(bi.isProbablePrime(100));
			assertEquals(bi.bitLength(), 2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigIntegerIntIntRandom154() {
		try {
			int bitLength = 300;
			int certainty = 100;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);

			bi = new BigInteger(bitLength, certainty, rnd);
			assertTrue(bi.isProbablePrime(100));
			assertEquals(bi.bitLength(), 300);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

}
