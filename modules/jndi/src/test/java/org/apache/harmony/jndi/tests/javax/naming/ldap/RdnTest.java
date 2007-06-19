/* 
 *  Licensed to the Apache Software Foundation (ASF) under one or more 
 *  contributor license agreements.  See the NOTICE file distributed with 
 *  this work for additional information regarding copyright ownership. 
 *  The ASF licenses this file to You under the Apache License, Version 2.0 
 *  (the "License"); you may not use this file except in compliance with 
 *  the License.  You may obtain a copy of the License at 
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
 * @author Leonardo Soler
 * @author Gabriel Miretti
 * @version 1.0
 */
package org.apache.harmony.jndi.tests.javax.naming.ldap;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.naming.InvalidNameException;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.ldap.BasicControl;
import javax.naming.ldap.Rdn;

import junit.framework.TestCase;

import org.apache.harmony.testframework.serialization.SerializationTest;

/**
 * <p>
 * Test cases for all methods of the class Rdn.
 * </p>
 * <p>
 * The next two tables contains a list of the methods to be tested, with the
 * return of each method.
 * </p>
 * <table class="t" cellspacing="0"> <tbody>
 * <th>Constructors:</th>
 * <tr>
 * <td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11"
 * readonly="readonly" value="" id="f00"></td>
 * <td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21"
 * readonly="readonly" value="Rdn(Attributes attrSet)" id="f10"></td>
 * 
 * </tr>
 * <tr>
 * <td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11"
 * readonly="readonly" value="" id="f00"></td>
 * <td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21"
 * readonly="readonly" value="Rdn(Rdn rdn)" id="f10"></td>
 * 
 * </tr>
 * <tr>
 * <td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11"
 * readonly="readonly" value="" id="f00"></td>
 * <td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21"
 * readonly="readonly" value="Rdn(String rdnString)" id="f10"></td>
 * 
 * </tr>
 * <tr>
 * <td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11"
 * readonly="readonly" value="" id="f00"></td>
 * <td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21"
 * readonly="readonly" value="Rdn(String type, Object value)" id="f10"></td>
 * 
 * </tr>
 * </tbody> <table> <tbody>
 * <th>Method Summary:</th>
 * <tr>
 * <TD>Return</TD>
 * <TD>Method</TD>
 * </tr>
 * <tr>
 * <td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11"
 * readonly="readonly" value="int" id="f00"></TD>
 * <td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21"
 * readonly="readonly" value="compareTo(Object obj)" id="f10"></td>
 * 
 * </tr>
 * <tr>
 * <td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11"
 * readonly="readonly" value="boolean" id="f00"></td>
 * <td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21"
 * readonly="readonly" value="equals(Object obj)" id="f10"></td>
 * 
 * </tr>
 * <tr>
 * <td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11"
 * readonly="readonly" value="static String" id="f00"></td>
 * <td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21"
 * readonly="readonly" value="escapeValue(Object val)" id="f10"></td>
 * 
 * </tr>
 * <tr>
 * <td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11"
 * readonly="readonly" value="String " id="f00"></td>
 * <td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21"
 * readonly="readonly" value="getType()" id="f10"></td>
 * 
 * </tr>
 * <tr>
 * <td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11"
 * readonly="readonly" value="Object " id="f00"></td>
 * <td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21"
 * readonly="readonly" value="getValue()" id="f10"></td>
 * 
 * </tr>
 * <tr>
 * <td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11"
 * readonly="readonly" value="int" id="f00"></td>
 * <td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21"
 * readonly="readonly" value="hashCode()" id="f10"></td>
 * 
 * </tr>
 * <tr>
 * <td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11"
 * readonly="readonly" value="int" id="f00"></td>
 * <td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21"
 * readonly="readonly" value="size()" id="f10"></td>
 * 
 * </tr>
 * <tr>
 * <td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11"
 * readonly="readonly" value="Attributes" id="f00"></td>
 * <td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21"
 * readonly="readonly" value="toAttributes()" id="f10"></td>
 * 
 * </tr>
 * <tr>
 * <td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11"
 * readonly="readonly" value="String" id="f00"></td>
 * <td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21"
 * readonly="readonly" value="toString()" id="f10"></td>
 * 
 * </tr>
 * <tr>
 * <td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11"
 * readonly="readonly" value="static Object" id="f00"></td>
 * <td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21"
 * readonly="readonly" value="unescapeValue(String val)" id="f10"></td>
 * 
 * </tr>
 * 
 * </tbody> </table>
 * 
 */
public class RdnTest extends TestCase {

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(Attributes)'
     * </p>
     * <p>
     * Here in this case we are testing to construct an Rdn from the given
     * attribute set. Here we are testing if we send a null attribute set.
     * </p>
     * <p>
     * The expected result is a null pointer exception.
     * </p>
     */
    public void testRdnAttributes001() throws Exception {
        try {
            new Rdn((Attributes) null);
            fail("NullPointerException  expected");
        } catch (NullPointerException e) {}
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(Attributes)'
     * </p>
     * <p>
     * Here in this case we are testing to construct an Rdn from the given
     * attribute set. Here we are testing if we send an empty attribute set.
     * </p>
     * <p>
     * The expected result is an invalid name exception.
     * </p>
     */
    public void testRdnAttributes002() {
        BasicAttributes set = new BasicAttributes();
        try {
            new Rdn(set);
            fail("InvalidNameException  expected");
        } catch (InvalidNameException e) {}
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(Attributes)'
     * </p>
     * <p>
     * Here in this case we are testing to construct an Rdn from the given
     * attribute set. Here we are testing if we send a not empty attribute set,
     * but here we are testing if the values are used literally (not parsed) and
     * assumed to be unescaped.
     * </p>
     * <p>
     * The expected result is an instance of Rdn.
     * </p>
     */
    public void testRdnAttributes003() throws Exception {
        String x = "anything";
        BasicAttributes set = new BasicAttributes("t", x);
        new Rdn(set);
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(Attributes)'
     * </p>
     * <p>
     * This is the test method for the constructor of the class Rdn, in this
     * case we are testing to construct an Rdn from the given attribute set.
     * Here we are testing if we send a not empty attribute set, but here we are
     * testing if the values are used literally (not parsed) and assumed to be
     * unescaped.
     * </p>
     * <p>
     * The expected result is an instance of Rdn.
     * </p>
     */
    public void testRdnAttributes004() throws Exception {
        String x = "as \\, asd";
        BasicAttributes set = new BasicAttributes("t", x);
        new Rdn(set);
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(Attributes)'
     * </p>
     * <p>
     * This is the test method for the constructor of the class Rdn, in this
     * case we are testing to construct an Rdn from the given attribute set.
     * Here we are testing if we send a not empty attribute set but here we are
     * testing if the values are used literally (not parsed) and assumed to be
     * unescaped.
     * </p>
     * <p>
     * The expected result is an instance of Rdn.
     * </p>
     */
    public void testRdnAttributes005() throws Exception {
        String x = "asd,asd";
        BasicAttributes set = new BasicAttributes("t", x);
        new Rdn(set);
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(Attributes)'
     * </p>
     * <p>
     * This is the test method for the constructor of the class Rdn, in this
     * case we are testing to construct an Rdn from the given attribute set.
     * Here we are testing if we send a not empty attribute set but here we are
     * testing if the values are used literally (not parsed) and assumed to be
     * unescaped.
     * </p>
     * <p>
     * The expected result is an instance of Rdn.
     * </p>
     */
    public void testRdnAttributes006() throws Exception {
        BasicAttributes set = new BasicAttributes("t", null);
        new Rdn(set);
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(Attributes)'
     * </p>
     * <p>
     * This is the test method for the constructor of the class Rdn, in this
     * case we are testing to construct an Rdn from the given attribute set.
     * Here we are testing if we send a not empty attribute set.
     * </p>
     * <p>
     * The expected result is an exception.
     * </p>
     */
    public void testRdnAttributes007() throws Exception {
        BasicAttributes x = new BasicAttributes();
        x.put(new BasicAttribute("t"));
        try {
            new Rdn(x);
            fail("NoSuchElementException expected");
        } catch (NoSuchElementException e) {}
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(String)'
     * </p>
     * <p>
     * This is the test method for the constructor of the class Rdn, in this
     * case we are testing to construct an Rdn from the given String. Here we
     * are testing if we send a null String.
     * </p>
     * <p>
     * The expected result is a null pointer exception.
     * </p>
     */
    public void testRdnString001() throws Exception {
        try {
            new Rdn((String) null);
            fail("NPE expected");
        } catch (NullPointerException e) {}
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(String)'
     * </p>
     * <p>
     * This is the test method for the constructor of the class Rdn, in this
     * case we are testing to construct an Rdn from the given String. Here we
     * are testing if we send an empty String.
     * </p>
     * <p>
     * The expected result is an instance of the class.
     * </p>
     */
    public void testRdnString002() throws Exception {
        new Rdn("");
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(String)'
     * </p>
     * <p>
     * This is the test method for the constructor of the class Rdn, in this
     * case we are testing to construct an Rdn from the given String. Here we
     * are testing if we send a non empty String but with no valid format.
     * </p>
     * <p>
     * The expected result is invalid name exception.
     * </p>
     */
    public void testRdnString003() throws Exception {
        try {
            new Rdn("wrong");
            fail("InvalidNameException expected");
        } catch (InvalidNameException e) {}

        try {
            new Rdn("=wrong");
            fail("InvalidNameException expected");
        } catch (InvalidNameException e) {}

        new Rdn("fine=");
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(String)'
     * </p>
     * <p>
     * This is the test method for the constructor of the class Rdn, in this
     * case we are testing to construct an Rdn from the given String. Here we
     * are testing if we send a non empty String with a valid format, in this
     * case we use the especial character "+", and we are given here a type with
     * no value.
     * </p>
     * <p>
     * The expected result is an instance not null of Rdn.
     * </p>
     */
    public void testRdnString006() throws Exception {
        new Rdn("type=test+value=");
        new Rdn("type=+value=");
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(String)'
     * </p>
     * <p>
     * This is the test method for the constructor of the class Rdn, in this
     * case we are testing to construct an Rdn from the given String. Here we
     * are testing if we send a non empty String but with valid format, in this
     * case we use the especial character "=", and we are given here a type with
     * no value, and type is quoted..
     * </p>
     * <p>
     * The expected result is an exception like Invalid name exception.
     * </p>
     */
    public void testRdnString008() {
        try {
            new Rdn("\\4C=");

            // the type must not be quoted
            fail("InvalidNameException expected");
        } catch (InvalidNameException e) {}
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(String)'
     * </p>
     * <p>
     * This is the test method for the constructor of the class Rdn, in this
     * case we are testing to construct an Rdn from the given String. Here we
     * are testing if we send a non empty String but with valid format, in this
     * case we use the especial character "=".
     * </p>
     * <p>
     * The expected result is an instance of rdn.
     * </p>
     */
    public void testRdnString009() throws Exception {
        new Rdn("t==t");
        new Rdn("t=t=t");
        new Rdn("t=t=t=");

        try {
            new Rdn("t=+=t");
            fail("InvalidNameException expected");
        } catch (InvalidNameException e) {}

        new Rdn("t=+t=t=");
        new Rdn("t=+t=t=s<asd");
        new Rdn("t=>asd+t=t+t=t=s<asd");
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(String)'
     * </p>
     * <p>
     * This is the test method for the constructor of the class Rdn, in this
     * case we are testing to construct an Rdn from the given String. Here we
     * are testing if we send a non empty String but with valid format.
     * </p>
     * <p>
     * The expected result is an instance of rdn.
     * </p>
     */
    public void testRdnString016() throws Exception {
        new Rdn("t=\\#0FA3TA");
        new Rdn("t.2.f.4=\\<0FA3TA\\>");
        new Rdn("t.2.f.4=\\<0FA3TA\\>+h    =\\#0FA3T A+f=#080808");
        new Rdn(
                "t.2.f.4=\\<0FA3TA\\>+h    =\\#0FA3T A+f=#080808+p=p=p+f=\\4c\\4c#34=#080808+f=f=f=f=\\4c>>\\4c=f=\\4c\\,4c<<<<\\#=+f=\\,");

        new Rdn(
                "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                        + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                        + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                        + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                        + "a=<z>=at+a=<z>=at+a=<z>=at+a=<z>=at+a=>z<=at+a=>z<=at+a=>z<=at"
                        + "+v================================+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a="
                        + "+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a========a");

        try {
            new Rdn(
                    "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                            + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                            + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                            + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                            + "a=<z>=at+a=<z>=at+a=<z>=at+a=<z>=at+a=>z<=at+a=>z<=at+a=>z<=at"
                            + "+v================================+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a="
                            + "+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a========a++");
            fail("InvalidNameException expected");
        } catch (InvalidNameException e) {}

        new Rdn(
                "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                        + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                        + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                        + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                        + "a=<z>=at+a=<z>=at+a=<z>=at+a=<z>=at+a=>z<=at+a=>z<=at+a=>z<=at"
                        + "+v================================+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a="
                        + "+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a========a+");

        new Rdn(
                "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                        + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                        + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                        + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                        + "a=<z>=at+a=<z>=at+a=<z>=at+a=<z>=at+a=>z<=at+a=>z<=at+a=>z<=at"
                        + "+v================================+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a="
                        + "+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a========a+s=s<>ss");

        try {
            new Rdn(
                    "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                            + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                            + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                            + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                            + "a=<z>=at+a=<z>=at+a=<z>=at+a=<z>=at+a=>z<=at+a=>z<=at+a=>z<=at"
                            + "+v================================+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a="
                            + "+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a========a+s=<>;s=s");
            fail("InvalidNameException expected");
        } catch (InvalidNameException e) {}

        try {
            new Rdn(
                    "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                            + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                            + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                            + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                            + "a=<z>=at+a=<z>=at+a=<z>=at+a=<z>=at+a=>z<=at+a=>z<=at+a=>z<=at"
                            + "+v================================+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a="
                            + "+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a========a+s=<>,s=s");
            fail("InvalidNameException expected");
        } catch (InvalidNameException e) {}

    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(String)'
     * </p>
     * <p>
     * This is the test method for the constructor of the class Rdn, in this
     * case we are testing to construct an Rdn from the given String. Here we
     * are testing if we send a non empty String but with invalid format.
     * </p>
     * <p>
     * The expected result is an invalid name exception.
     * </p>
     */
    public void testRdnString026() {
        try {
            new Rdn("=t=t");
            fail("InvalidNameException expected");
        } catch (InvalidNameException e) {}
    }

    public void testRdnString027() {
        try {
            Rdn rdn1 = new Rdn("t", "test");
            Rdn rdn2 = new Rdn("t = test + t = test");
            assertFalse(rdn1.equals(rdn2));
            assertEquals(rdn1.toAttributes(), rdn2.toAttributes());            
        } catch (InvalidNameException e) {}
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(Rdn)'
     * </p>
     * <p>
     * This is the test method for the constructor of the class Rdn, in this
     * case we are testing to construct an Rdn from the given Rdn. Here we are
     * testing if we send a null Rdn.
     * </p>
     * <p>
     * The expected result is a null pointer exception.
     * </p>
     */
    public void testRdnRdn001() {
        try {
            new Rdn((Rdn) null);
            fail("The parameter is null");
        } catch (NullPointerException e) {}
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(Rdn)'
     * </p>
     * <p>
     * This is the test method for the constructor of the class Rdn, in this
     * case we are testing to construct an Rdn from the given Rdn. Here we are
     * testing if we send a non null Rdn.
     * </p>
     * <p>
     * The expected result is another Rdn with a copy of the given one.
     * </p>
     */
    public void testRdnRdn002() throws Exception {
        Rdn x;

        x = new Rdn("t=test");
        assertEquals(0, x.compareTo(new Rdn(x)));

        x = new Rdn("");
        assertEquals(0, x.compareTo(new Rdn(x)));

        x = new Rdn("t=test+y=this");
        assertEquals(0, x.compareTo(new Rdn(x)));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'
     * </p>
     * <p>
     * This is the test method for the constructor of the class Rdn, in this
     * case we are testing to construct an Rdn from the given String and Object.
     * Here we are testing if we send a null String and null object as the
     * parameters.
     * </p>
     * <p>
     * The expected result is a null pointer exception.
     * </p>
     */
    public void testRdnStringObject001() throws Exception {
        try {
            new Rdn("type", (Object) null);
            fail("NPE expected");
        } catch (NullPointerException e) {}

        try {
            new Rdn((String) null, "type");
            fail("NPE expected");
        } catch (NullPointerException e) {}

        try {
            new Rdn("", "value");
            fail("InvalidNameException expected");
        } catch (InvalidNameException e) {}

        try {
            new Rdn("type", "");
            fail("InvalidNameException expected");
        } catch (InvalidNameException e) {}
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'
     * </p>
     * <p>
     * This is the test method for the constructor of the class Rdn, in this
     * case we are testing to construct an Rdn from the given String and Object.
     * Here we are testing if we send a non null String with a non null object
     * but the type here has a special character like "+" this must be permited.
     * </p>
     * <p>
     * The expected result is an instance not null of Rdn.
     * </p>
     */
    public void testRdnStringObject007() throws Exception {
        new Rdn("y=asd+t=test", "a=asd");
        new Rdn("y=asd", "a=asd+t=test");
        new Rdn("t", new ArrayList());
        new Rdn("t=t=t", new Object());
        new Rdn("t=t=t", "test");
        new Rdn("t", new BasicControl("test"));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'
     * </p>
     * <p>
     * This is the test method for the constructor of the class Rdn, in this
     * case we are testing to construct an Rdn from the given String and Object.
     * Here we are testing if we send a non empty String and this one not ok
     * with diferents objects.
     * </p>
     * <p>
     * The expected result is an instance of the class with the diferents
     * arguments because the arguments are not parsed.
     * </p>
     */
    public void testRdnStringObject018() throws Exception {
        new Rdn(new String("t===T"), new ArrayList());
        new Rdn(new String("t=+=T"), new Object());
        new Rdn(new String("t=,33,=T"), new BasicControl("test"));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'
     * </p>
     * <p>
     * This is the test method for the constructor of the class Rdn, in this
     * case we are testing to construct an Rdn from the given String and Object.
     * Here we are testing if we send a non empty String and this one not ok
     * with diferents objects.
     * </p>
     * <p>
     * The expected result is an instance of the class with the diferents
     * arguments because the arguments are not parsed.
     * </p>
     */
    public void testRdnStringObject019() throws Exception {
        new Rdn(new String("t===T"), new char[] { 'a', 'v' });
        new Rdn(new String("t=+=T"), new int[] { 1, 2, 3 });
        new Rdn(new String("t=,33,=T"), new BasicControl("test"));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'
     * </p>
     * <p>
     * This is the test method for the constructor of the class Rdn, in this
     * case we are testing to construct an Rdn from the given String and Object.
     * Here we are testing if we send a non empty String and a non empty object.
     * </p>
     * <p>
     * The expected result is an instance of the class because the arguments are
     * not parsed.
     * </p>
     */
    public void testRdnStringObject020() throws Exception {
        String x = "t=t=t\\<0FA3TA\\>+h    =\\#0FA3T A+f=#080808+p=p=p+f=\\4c\\4c#34=#080808+f=f=f=f=\\4c>>\\4c=f=\\4c\\,4c<<<<\\#=+f=\\,";
        Object o = new String(
                "\\<0FA3TA\\>+h    =\\#0FA3T A+f=#080808+p=p=p+f=\\4c\\4c#34=#080808+f=f=f=f=\\4c>>\\4c=f=\\4c\\,4c<<<<\\#=+f=\\,");
        new Rdn(x, o);
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.hashCode()'
     * </p>
     * <p>
     * Here we are testing if this method returns the hash code of this RDN, in
     * this case we are testing if the hashcode returned by this method is the
     * correct one, the only hash that we know something is of the Rdn empty,
     * this hash has to be zero.
     * </p>
     * <p>
     * The expected result is the hashcode of the rdn.
     * </p>
     */
    public void testHashCode001() throws Exception {
        assertEquals(0, new Rdn("").hashCode());

        int x = new Rdn("t= test\\, that+s= this").hashCode();
        int y = new Rdn("T=TEST\\, THAT+S=THIS").hashCode();
        assertNotSame(0, x & y);
        assertEquals(x, y);
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.hashCode()'
     * </p>
     * <p>
     * Here we are testing if this method returns the hash code of this RDN, in
     * this case we are testing if the hashcode returned by this method is the
     * correct one, the only hash that we know something is of the Rdn empty,
     * this hash has to be zero.
     * </p>
     * <p>
     * The expected result is the hashcode of the rdn.
     * </p>
     */
    public void testHashCode002() throws Exception {
        int x = new Rdn("t= #20").hashCode();
        int y = new Rdn("t= #20").hashCode();
        assertTrue(x == y);
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.equals(Object)'
     * </p>
     * <p>
     * Here we are testing if this method compares the specified Object with
     * this Rdn for equality. In this case we are sending a null object.
     * </p>
     * <p>
     * The expected result is false.
     * </p>
     */
    public void testEquals001() throws Exception {
        Rdn rdn = new Rdn("t=tEst");

        assertFalse(rdn.equals(null));
        assertFalse(rdn.equals(new Object()));
        assertFalse(rdn.equals(new Rdn("t=test2")));

        assertEquals(rdn, new Rdn("t=test"));
        assertEquals(rdn, new Rdn("T=TeSt"));
        assertEquals(new Rdn("T=TEST+v=test"), new Rdn("t=test+V=test"));
        assertEquals(new Rdn(""), new Rdn(""));
        assertEquals(new Rdn("t=test+t=that+t=here"), new Rdn(
                "t=that+t=test+t=here"));

        assertTrue(new Rdn("t=test+t=asd+t=that").equals(new Rdn(
                "t=that+t=test+t=asd+")));
        assertFalse(new Rdn("t=test+t=asd+t=that").equals(new Rdn(
                "t=that+t=test+t=asd=")));
        assertFalse(new Rdn("t=test+t=asd+t=that").equals(new Rdn(
                "t=that+t=test+t=asd=+")));
        assertFalse(new Rdn("t=test+t=asd+t=that").equals(new Rdn(
                "t=that+t=test+t=asd+t=")));

        assertTrue(new Rdn("t", new byte[] { 00, 01, 02 }).equals(new Rdn("t",
                new byte[] { 00, 01, 02 })));
        assertFalse(new Rdn("t", new byte[] { 00, 01 }).equals(new Rdn("t",
                new byte[] { 00, 01, 02 })));
        assertFalse(new Rdn("t", new byte[] { 00, 01, 02 }).equals(new Rdn("t",
                new byte[] { 00, 01 })));

    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.equals(Object)'
     * </p>
     * <p>
     * Here we are testing if this method compares the specified Object with
     * this Rdn for equality. In this case we are sending equals rdns.
     * </p>
     * <p>
     * The expected result is exception here.
     * </p>
     */
    public void testEquals017() throws Exception {
        try {
            new Rdn("t", new char[] { 'a', 'v' }).equals(new Rdn("t",
                    new char[] { 'a', 'v' }));
            fail("Should raise an exception.");
        } catch (ClassCastException e) {}

        try {
            new Rdn("t", new int[] { 00 })
                    .equals(new Rdn("t", new int[] { 00 }));
            fail("Should raise an exception.");
        } catch (ClassCastException e) {}

        try {
            new Rdn("t", new BasicControl("t")).equals(new Rdn("t",
                    new BasicControl("t")));
            fail("Should raise an exception.");
        } catch (ClassCastException e) {}
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.toString()'
     * </p>
     * <p>
     * Here we are testing if this method give us the correct string of a Rdn
     * that we create, notice here that Rdn created by us is empty so the string
     * returned must be also empty.
     * </p>
     * <p>
     * The expected result is an empty string.
     * </p>
     */
    public void testToString001() throws Exception {
        Rdn rdn = new Rdn("");
        assertEquals("", rdn.toString());
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.toString()'
     * </p>
     * <p>
     * Here we are testing if this method give us the correct string of a Rdn
     * that we create, notice here that Rdn created by us is not empty so the
     * string returned must be also not empty.
     * </p>
     * <p>
     * The expected result is an non empty string an eqaul to the one used by to
     * create the rdn.
     * </p>
     */
    public void testToString002() throws Exception {
        String t = "t=test";
        Rdn rdn = new Rdn(t);
        assertEquals(t, rdn.toString());
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.toString()'
     * </p>
     * <p>
     * Here we are testing if this method give us the correct string of a Rdn
     * that we create, notice here that Rdn created by us is not empty so the
     * string returned must be also not empty. Here is the paricularity that the
     * rdn contains multi-values so the string must be a concatenation of the
     * strings.
     * </p>
     * <p>
     * The expected result is an non empty string an eqaul to the one used by to
     * create the rdn.
     * </p>
     */
    public void testToString003() throws Exception {
        String t = "t=test+v=value";
        Rdn rdn = new Rdn(t);
        assertEquals(t, rdn.toString());
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.toString()'
     * </p>
     * <p>
     * Here we are testing if this method give us the correct string of a Rdn
     * that we create, notice here that Rdn created by us is not empty so the
     * string returned must be also not empty. Here is the paricularity that the
     * rdn contains multi-values and quoted values so the string must be a
     * concatenation of strings and values quoted must in ascii.
     * </p>
     * <p>
     * The expected result is an non empty string an eqaul to the one used by to
     * create the rdn.
     * </p>
     */
    public void testToString004() throws Exception {
        String t = "t=test+v=LL";
        Rdn rdn = new Rdn("t=test+v=\\4C\\4C");
        assertEquals(t, rdn.toString());
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.toString()'
     * </p>
     * <p>
     * Here we are testing if this method give us the correct string of a Rdn
     * that we create, notice here that Rdn created by us is not empty so the
     * string returned must be also not empty. Here is the paricularity that the
     * rdn contains quoted values so the string must be must in ascii.
     * </p>
     * <p>
     * The expected result is an non empty string an eqaul to the one used by to
     * create the rdn.
     * </p>
     */
    public void testToString005() throws Exception {
        String t = "v=LL";
        Rdn rdn = new Rdn("v=\\4C\\4C");
        assertEquals(t, rdn.toString());
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.toString()'
     * </p>
     * <p>
     * Here we are testing if this method give us the correct string of a Rdn
     * that we create, notice here that Rdn created by us is not empty so the
     * string returned must be also not empty. Here is the paricularity that the
     * rdn contains quoted values so the string must be must in ascii also
     * contains two special characters but like character so they must be in the
     * string.
     * </p>
     * <p>
     * The expected result is an non empty string an eqaul to the one used by to
     * create the rdn.
     * </p>
     */
    public void testToString006() throws Exception {
        String t = "v=L\\,L";
        Rdn rdn = new Rdn("v=\\4C\\,\\4C");
        assertEquals(t, rdn.toString());
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.toString()'
     * </p>
     * <p>
     * Here we are testing if this method give us the correct string of a Rdn
     * that we create, notice here that Rdn created by us is not empty so the
     * string returned must be also not empty. Here is the paricularity that the
     * rdn is created with an object that contains an array of primitives.
     * </p>
     * <p>
     * The expected result is the string with the object parsed.
     * </p>
     */
    public void testToString007() throws Exception {
        String t = "v=#080100";
        Rdn rdn = new Rdn("v", new byte[] { 8, 01, 0 });
        assertEquals(t, rdn.toString());
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.toString()'
     * </p>
     * <p>
     * Here we are testing if this method give us the correct string of a Rdn
     * that we create, notice here that Rdn created by us is not empty so the
     * string returned must be also not empty. Here is the paricularity that the
     * rdn is created with an object that contains an array of primitives.
     * </p>
     * <p>
     * The expected result is an exception.
     * </p>
     */
    public void testToString008() throws Exception {
        int[] t = new int[] { 1, 2, 3, 4, 5 };
        Rdn rdn = new Rdn("t", t);
        try {
            rdn.toString();
            fail("Should raise an exception.");
        } catch (ClassCastException e) {}
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.toString()'
     * </p>
     * <p>
     * Here we are testing if this method give us the correct string of a Rdn
     * that we create, notice here that Rdn created by us is not empty so the
     * string returned must be also not empty. Here is the paricularity that the
     * rdn contains multi-values and quoted values so the string must be a
     * concatenation of strings and values quoted must in ascii.
     * </p>
     * <p>
     * The expected result is an non empty string an eqaul to the one used by to
     * create the rdn.
     * </p>
     */
    public void testToString009() throws Exception {
        String t = "t=test+t=test+v=LL";
        Rdn rdn = new Rdn("t=test+v=\\4C\\4C+t=test");
        assertEquals(t, rdn.toString());
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.toString()'
     * </p>
     * <p>
     * Here we are testing if this method give us the correct string of a Rdn
     * that we create, notice here that Rdn created by us is not empty so the
     * string returned must be also not empty. Here is the paricularity that the
     * rdn contains multi-values and quoted values so the string must be a
     * concatenation of strings and values quoted must in ascii.
     * </p>
     * <p>
     * The expected result is an non empty string an eqaul to the one used by to
     * create the rdn.
     * </p>
     */
    public void testToString010() throws Exception {
        String t = "a=\\<a+a=a\\=a+t=test+t=test+v=asdasd+v=LL";
        Rdn rdn = new Rdn("t=test+v=\\4C\\4C+t=test+a=a=a+v=asdasd+a=<a");
        assertEquals(t, rdn.toString());
    }

    public void testToString011() throws Exception {
        String t = "a=";
        Rdn rdn = new Rdn("a=");
        assertEquals(t, rdn.toString());
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.getValue()'
     * </p>
     * <p>
     * Here we are testing if this method retrieves one of this Rdn's value. In
     * this case should raise an exception because the rdn is empty.
     * </p>
     * <p>
     * The expected result is an exception.
     * </p>
     */
    public void testGetValue001() throws Exception {
        Rdn rdn = new Rdn("");
        try {
            rdn.getValue();
            fail("Should raise an exception.");
        } catch (IndexOutOfBoundsException e) {}
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.getValue()'
     * </p>
     * <p>
     * Here we are testing if this method retrieves one of this Rdn's value. In
     * this case if returns an object not null when a not empty name is created.
     * </p>
     */
    public void testGetValue002() throws Exception {
        assertEquals("test", new Rdn("t=test").getValue());

        //multivalue object
        assertEquals("test", new Rdn("t=this+d=test").getValue());

        //value is quoted so the result must be in ascii
        assertEquals("LL", new Rdn("t=\\4C\\4C").getValue());

        //multivalue object is created and value is quoted so the result must be in ascii
        assertEquals("test", new Rdn("t=\\4C\\4C+d=test+f=asd").getValue());

        //multivalue object is created and value is quoted so the result must be in ascii
        assertEquals("", new Rdn("t=\\4C\\4C+d=+f=asd").getValue());
    }


    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.getValue()'
     * </p>
     * <p>
     * Here we are testing if this method retrieves one of this Rdn's value. In
     * this case if returns an object not null when a not empty name is created.
     * </p>
     */
    public void testGetValue014() throws Exception {
        //object is created with a primitive
        int b = 8;
        assertEquals(b, new Rdn("t", b).getValue());

        Object data = new Object();
        assertSame(data, new Rdn("t", data).getValue());

        BasicAttributes x = new BasicAttributes("t", null);
        assertNull(new Rdn(x).getValue());
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.getValue()'
     * </p>
     * <p>
     * Here we are testing if this method retrieves one of this Rdn's value. In
     * this case should raise an exception because the rdn is empty.
     * </p>
     * <p>
     * The expected result is an exception.
     * </p>
     */
    public void testGetType001() throws Exception {
        Rdn x = new Rdn("");
        try {
            x.getType();
            fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException e) {}
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.getType()'
     * </p>
     * <p>
     * Here we are testing if the method retrieves one of this Rdn's type. In
     * this case the Rdn is created not empty so the result must be not null and
     * not empty.
     * </p>
     * <p>
     * The expected result is a not null and not empty String.
     * </p>
     */
    public void testGetType002() throws Exception {
        Rdn x = new Rdn("t=test");
        assertEquals("t", x.getType());
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.getType()'
     * </p>
     * <p>
     * Here we are testing if the method retrieves one of this Rdn's type. In
     * this case the Rdn is created not empty so the result must be not null and
     * not empty, also here we include a multivalue name.
     * </p>
     * <p>
     * The expected result is a not null and not empty String.
     * </p>
     */
    public void testGetType003() throws Exception {
        Rdn x = new Rdn("t=test+t2=this");
        assertEquals("t", x.getType());
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.getType()'
     * </p>
     * <p>
     * Here we are testing if the method retrieves one of this Rdn's type. In
     * this case the Rdn is created not empty so the result must be not null and
     * not empty, also here we include a multivalue name.
     * </p>
     * <p>
     * The expected result is a not null and not empty String.
     * </p>
     */
    public void testGetType004() throws Exception {
        Rdn x = new Rdn("t2=test+t=this");
        assertEquals("t", x.getType());
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.getType()'
     * </p>
     * <p>
     * Here we are testing if the method retrieves one of this Rdn's type. In
     * this case the Rdn is created not empty so the result must be not null and
     * not empty, also here we include a multivalue name.
     * </p>
     * <p>
     * The expected result is a not null and not empty String.
     * </p>
     */
    public void testGetType005() throws Exception {
        Rdn x = new Rdn("t=test+d=this+a=asd");
        assertEquals("a", x.getType());
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'
     * </p>
     * <p>
     * Here we are testing if this method compares this Rdn with the specified
     * Object for order. In this case we are comparing two empty Rdn.
     * </p>
     * <p>
     * The expected result is a zero.
     * </p>
     */
    public void testCompareTo001() throws Exception {
        Rdn rdn1 = new Rdn("");
        Rdn rdn2 = new Rdn("");
        assertEquals(0, rdn1.compareTo(rdn2));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'
     * </p>
     * <p>
     * Here we are testing if this method compares this Rdn with the specified
     * Object for order. In this case we are comparing two Rdn one of them is
     * empty in this case the second one.
     * </p>
     * <p>
     * The expected result is a positive number.
     * </p>
     */
    public void testCompareTo002() throws Exception {
        Rdn rdn1 = new Rdn("t=test");
        Rdn rdn2 = new Rdn("");
        assertFalse(rdn1.compareTo(rdn2) <= 0);
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'
     * </p>
     * <p>
     * Here we are testing if this method compares this Rdn with the specified
     * Object for order. In this case we are comparing two Rdn one of them is
     * empty in this case the first one.
     * </p>
     * <p>
     * The expected result is a negative number.
     * </p>
     */
    public void testCompareTo003() throws Exception {
        Rdn rdn1 = new Rdn("");
        Rdn rdn2 = new Rdn("t=test");
        assertFalse(rdn1.compareTo(rdn2) >= 0);
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'
     * </p>
     * <p>
     * Here we are testing if this method compares this Rdn with the specified
     * Object for order. In this case we are comparing two Rdn one of them is
     * null in this case the second one.
     * </p>
     * <p>
     * The expected result is a ClassCastException.
     * </p>
     */
    public void testCompareTo004() throws Exception {
        try {
            new Rdn("").compareTo(null);
            fail("ClassCastException expected");
        } catch (ClassCastException e) {}
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'
     * </p>
     * <p>
     * Here we are testing if this method compares this Rdn with the specified
     * Object for order. In this case we are comparing two Rdn one of them has
     * two values, the other one has two values but one is empty.
     * </p>
     * <p>
     * The expected result is a positive number.
     * </p>
     */
    public void testCompareTo005() throws Exception {
        Rdn rdn1 = new Rdn("t=test+m=test");
        Rdn rdn2 = new Rdn("t=test+m=");
        assertFalse(rdn1.compareTo(rdn2) <= 0);
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'
     * </p>
     * <p>
     * Here we are testing if this method compares this Rdn with the specified
     * Object for order. In this case we are comparing two Rdn one of them has
     * two values, the other one has two values but one is empty.
     * </p>
     * <p>
     * The expected result is a negative number.
     * </p>
     */
    public void testCompareTo006() throws Exception {
        Rdn rdn1 = new Rdn("t=test+m=");
        Rdn rdn2 = new Rdn("t=test+m=test");
        assertFalse(rdn1.compareTo(rdn2) >= 0);
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'
     * </p>
     * <p>
     * Here we are testing if this method compares this Rdn with the specified
     * Object for order. In this case we are comparing with an object that is
     * not an instance of Rdn.
     * </p>
     * <p>
     * The expected result is a ClassCastException.
     * </p>
     */
    public void testCompareTo007() throws Exception {
        try {
            new Rdn("").compareTo(new Object());
            fail("ClassCastException expected");
        } catch (ClassCastException e) {}
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'
     * </p>
     * <p>
     * Here we are testing if this method compares this Rdn with the specified
     * Object for order. In this case we are comparing with an Rdn that is Upper
     * case.
     * </p>
     * <p>
     * The expected result is a 0.
     * </p>
     */
    public void testCompareTo008() throws Exception {
        Rdn rdn1 = new Rdn("t=test");
        Rdn rdn2 = new Rdn("T=TEST");
        assertEquals(0, rdn1.compareTo(rdn2));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'
     * </p>
     * <p>
     * Here we are testing if this method compares this Rdn with the specified
     * Object for order.
     * </p>
     * <p>
     * The expected result is a 0.
     * </p>
     */
    public void testCompareTo009() throws Exception {
        Rdn rdn1 = new Rdn("t=test+a=anything");
        Rdn rdn2 = new Rdn("A=ANYTHING+T=TEST");
        assertEquals(0, rdn1.compareTo(rdn2));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'
     * </p>
     * <p>
     * Here we are testing if this method compares this Rdn with the specified
     * Object for order.
     * </p>
     * <p>
     * The expected result is a 0.
     * </p>
     */
    public void testCompareTo010() throws Exception {
        Rdn rdn1 = new Rdn("t=test+a=   anything+A=\\#080808<a+c=c=#0808");
        Rdn rdn2 = new Rdn("A=\\#080808<a+c=c=#0808+t=test+a=   anything");
        assertEquals(0, rdn1.compareTo(rdn2));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'
     * </p>
     * <p>
     * Here we are testing if this method compares this Rdn with the specified
     * Object for order.
     * </p>
     * <p>
     * The expected result is a 0.
     * </p>
     */
    public void testCompareTo011() throws Exception {
        String x1 = "t=test+a=   anything+A=\\#080808<a+c=c=#0808+"
                + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                + "a=<z>=at+a=<z>=at+a=<z>=at+a=<z>=at+a=>z<=at+a=>z<=at+a=>z<=at"
                + "+v================================+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a="
                + "+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a========a+s=s<>ss";
        String x2 = "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                + "asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+"
                + "a=<z>=at+a=<z>=at+a=<z>=at+a=<z>=at+a=>z<=at+a=>z<=at+a=>z<=at"
                + "+v================================+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a="
                + "+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a========a+s=s<>ss+"
                + "A=\\#080808<a+c=c=#0808+t=test+a=   anything";
        Rdn rdn1 = new Rdn(x1);
        Rdn rdn2 = new Rdn(x2);
        assertEquals(0, rdn1.compareTo(rdn2));
    }

    /**
     * <p>
     * Method to test 'javax.naming.ldap.Rdn.toAttributes()', we use this method
     * to compare each attribute of an enumeration.
     * </p>
     * 
     * @param ne The naming enumeration that returns the rdn.
     * @param att The set of attributes with we create the rdn.
     * @return true if the two enumerations are equals.
     */
    public static void assertAttributesEqual(NamingEnumeration<Attribute> ne, Attributes att) {
        assertNotNull(ne);
        assertNotNull(att);

        NamingEnumeration<? extends Attribute> comp = att.getAll();

        while (ne.hasMoreElements() && comp.hasMoreElements()) {
            assertEquals(ne.nextElement(), comp.nextElement());
        }

        //length differ?
        assertFalse(ne.hasMoreElements());
        assertFalse(comp.hasMoreElements());
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.toAttributes()'
     * </p>
     * <p>
     * Here we are testing if this method retrieves the Attributes view of the
     * type/value mappings contained in given Rdn wich is sended in the next
     * form: "t=\\4C\\4c", notice that here the values are quoted.
     * </p>
     * <p>
     * The expected result is the map of the Rdn."
     * </p>
     */
    public void testToAttributes001() throws Exception {
        BasicAttributes t = new BasicAttributes("t", "\\4C\\4C");
        Rdn rdn = new Rdn(t);
        BasicAttributes ba = (BasicAttributes) rdn.toAttributes();

        NamingEnumeration ne = ba.getAll();
        assertAttributesEqual(ne, t);
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.toAttributes()'
     * </p>
     * <p>
     * Here we are testing if this method retrieves the Attributes view of the
     * type/value mappings contained in given Rdn wich is sended in the next
     * form: "", notice that here the name is empty.
     * </p>
     * <p>
     * The expected result is the map of the Rdn not null but empty.
     * </p>
     */
    public void testToAttributes002() throws Exception {
        Rdn rdn = new Rdn("");
        BasicAttributes ba = (BasicAttributes) rdn.toAttributes();

        assertEquals(0, ba.size());
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.toAttributes()'
     * </p>
     * <p>
     * Here we are testing if this method retrieves the Attributes view of the
     * type/value mappings contained in given Rdn wich is sended in the next
     * form: "t=hola", notice that here the values are not quoted.
     * </p>
     * <p>
     * The expected result is the map of the Rdn."
     * </p>
     */
    public void testToAttributes003() throws Exception {
        BasicAttributes t = new BasicAttributes("t", "hola");
        Rdn rdn = new Rdn(t);
        BasicAttributes ba = (BasicAttributes) rdn.toAttributes();

        NamingEnumeration ne = ba.getAll();
        assertAttributesEqual(ne, t);
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.toAttributes()'
     * </p>
     * <p>
     * Here we are testing if this method retrieves the Attributes view of the
     * type/value mappings contained in given Rdn wich is sended in the next
     * form: "t=", notice that here the values are not quoted.
     * </p>
     * <p>
     * The expected result is the map of the Rdn."
     * </p>
     */
    public void testToAttributes004() throws Exception {
        BasicAttributes t = new BasicAttributes("t", "");
        Rdn rdn = new Rdn(t);
        BasicAttributes ba = (BasicAttributes) rdn.toAttributes();

        NamingEnumeration ne = ba.getAll();
        assertAttributesEqual(ne, t);
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.toAttributes()'
     * </p>
     * <p>
     * Here we are testing if this method retrieves the Attributes view of the
     * type/value mappings contained in given Rdn wich is sended in the next
     * form: "t=test+a=test2+b=test3", notice that here the values are not
     * quoted.
     * </p>
     * <p>
     * The expected result is the map of the Rdn."
     * </p>
     */
    public void testToAttributes005() throws Exception {
        BasicAttributes t = new BasicAttributes("t", "test+a=test2+b=test3");
        Rdn rdn = new Rdn(t);
        BasicAttributes ba = (BasicAttributes) rdn.toAttributes();

        NamingEnumeration ne = ba.getAll();
        assertAttributesEqual(ne, t);
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.toAttributes()'
     * </p>
     * <p>
     * Here we are testing if this method retrieves the Attributes view of the
     * type/value mappings contained in given Rdn wich is sended in the
     * multivalue form.
     * </p>
     * <p>
     * The expected result is the map of the Rdn.
     * </p>
     */
    public void testToAttributes006() throws Exception {
        BasicAttributes t = new BasicAttributes("t", "test+a=test2");
        Rdn rdn = new Rdn(t);
        BasicAttributes ba = (BasicAttributes) rdn.toAttributes();

        NamingEnumeration ne = ba.getAll();
        assertAttributesEqual(ne, t);
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.toAttributes()'
     * </p>
     * <p>
     * Here we are testing if this method retrieves the Attributes view of the
     * type/value mappings contained in given Rdn wich is sended in the
     * multivalue form.
     * </p>
     * <p>
     * The expected result is the map of the Rdn.
     * </p>
     */
    public void testToAttributes007() throws Exception {
        BasicAttributes t = new BasicAttributes("a", "test+a=test2");
        Rdn rdn = new Rdn(t);
        BasicAttributes ba = (BasicAttributes) rdn.toAttributes();

        NamingEnumeration ne = ba.getAll();
        assertAttributesEqual(ne, t);
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.toAttributes()'
     * </p>
     * <p>
     * Here we are testing if this method retrieves the Attributes view of the
     * type/value mappings contained in given Rdn wich is sended in the
     * multivalue form.
     * </p>
     * <p>
     * The expected result is the map of the Rdn.
     * </p>
     */
    public void testToAttributes008() throws Exception {
        BasicAttributes t = new BasicAttributes("a", ">asd+t=t+t=t=s<asd");
        Rdn rdn = new Rdn(t);
        BasicAttributes ba = (BasicAttributes) rdn.toAttributes();

        NamingEnumeration ne = ba.getAll();
        assertAttributesEqual(ne, t);
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.toAttributes()'
     * </p>
     * <p>
     * Here we are testing if this method retrieves the Attributes view of the
     * type/value mappings contained in given Rdn wich is sended in the
     * multivalue form.
     * </p>
     * <p>
     * The expected result is the map of the Rdn.
     * </p>
     */
    public void testToAttributes009() throws Exception {
        BasicAttributes t = new BasicAttributes("a", "+t=t=");
        Rdn rdn = new Rdn(t);
        BasicAttributes ba = (BasicAttributes) rdn.toAttributes();
        assertNotNull(ba);
        NamingEnumeration ne = ba.getAll();
        assertAttributesEqual(ne, t);
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.toAttributes()'
     * </p>
     * <p>
     * Here we are testing if this method retrieves the Attributes view of the
     * type/value mappings contained in given Rdn wich is sended in the
     * multivalue form.
     * </p>
     * <p>
     * The expected result is the map of the Rdn.
     * </p>
     */
    public void testToAttributes010() throws Exception {
        BasicAttributes t = new BasicAttributes("a", "t=t");
        Rdn rdn = new Rdn(t);
        BasicAttributes ba = (BasicAttributes) rdn.toAttributes();
        assertNotNull(ba);
        NamingEnumeration ne = ba.getAll();
        assertAttributesEqual(ne, t);
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.size()'
     * </p>
     * <p>
     * Here we are testing if the return of this method is the correct size of
     * the given Rdn wich has one names.
     * </p>
     * <p>
     * The expected result is an integer with the value of one.
     * </p>
     */
    public void testSize001() throws Exception {
        Rdn rdn = new Rdn("t1", "test");
        assertEquals(1, rdn.size());
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.size()'
     * </p>
     * <p>
     * Here we are testing if the return of this method is the correct size of
     * the given empty Rdn.
     * </p>
     * <p>
     * The expected result is an integer with the value of zero.
     * </p>
     */
    public void testSize002() throws Exception {
        Rdn rdn = new Rdn("");
        assertEquals(0, rdn.size());
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.size()'
     * </p>
     * <p>
     * Here we are testing if the return of this method is the correct size of
     * the given Rdn wich has three names.
     * </p>
     * <p>
     * The expected result is an integer with the value of three.
     * </p>
     */
    public void testSize003() throws Exception {
        Rdn rdn = new Rdn("t=test+d=asd+s=anything");
        assertEquals(3, rdn.size());
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.size()'
     * </p>
     * <p>
     * Here we are testing if the return of this method is the correct size of
     * the given Rdn wich has three names.
     * </p>
     * <p>
     * The expected result is an integer with the value of three.
     * </p>
     */
    public void testSize004() throws Exception {
        Rdn rdn = new Rdn("t = #20 ");
        assertEquals(1, rdn.size());
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.escapeValue(Object)'
     * </p>
     * <p>
     * Here we are testing if this method given the value of an attribute,
     * returns a string escaped. In this case the method must be static so there
     * is no need to create a previously instance of Rdn, the value could be
     * like this "this, that" here the special character is ",".
     * </p>
     * <p>
     * The expected result is like this "this\, that".
     * </p>
     */
    public void testEscapeValue001() throws Exception {
        assertEquals("this\\, that", Rdn.escapeValue("this, that"));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.escapeValue(Object)'
     * </p>
     * <p>
     * Here we are testing if this method given the value of an attribute,
     * returns a string escaped. In this case the method must be static so there
     * is no need to create a previously instance of Rdn, the value could be
     * like this "this "that"", here the special character is """.
     * </p>
     * <p>
     * The expected result is like this "this \"that\"".
     * </p>
     */
    public void testEscapeValue002() {
        assertEquals("this \\\"that\\\"", Rdn.escapeValue("this \"that\""));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.escapeValue(Object)'
     * </p>
     * <p>
     * Here we are testing if this method given the value of an attribute,
     * returns a string escaped. In this case the method must be static so there
     * is no need to create a previously instance of Rdn, the value could be
     * like this "this+that", here the special character is "+".
     * </p>
     * <p>
     * The expected result is like this "this+that".
     * </p>
     */
    public void testEscapeValue003() {
        assertEquals("this\\+that", Rdn.escapeValue("this+that"));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.escapeValue(Object)'
     * </p>
     * <p>
     * Here we are testing if this method given the value of an attribute,
     * returns a string escaped. In this case the method must be static so there
     * is no need to create a previously instance of Rdn, the value could be
     * like this "this;that", here the special character is ";".
     * </p>
     * <p>
     * The expected result is like this "this\;that".
     * </p>
     */
    public void testEscapeValue004() {
        assertEquals("this\\;that", Rdn.escapeValue("this;that"));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.escapeValue(Object)'
     * </p>
     * <p>
     * Here we are testing if this method given the value of an attribute,
     * returns a string escaped. In this case the method must be static so there
     * is no need to create a previously instance of Rdn, the value could be
     * like this "this&lt;that", here the special character is "&lt;".
     * </p>
     * <p>
     * The expected result is like this "this&lt;that".
     * </p>
     */
    public void testEscapeValue005() {
        assertEquals("this\\<that", Rdn.escapeValue("this<that"));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.escapeValue(Object)'
     * </p>
     * <p>
     * Here we are testing if this method given the value of an attribute,
     * returns a string escaped. In this case the method must be static so there
     * is no need to create a previously instance of Rdn, the value could be
     * like this "this&gt;that", here the special character is "&gt;".
     * </p>
     * <p>
     * The expected result is like this "this&gt;that".
     * </p>
     */
    public void testEscapeValue006() {
        assertEquals("this\\>that", Rdn.escapeValue("this>that"));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.escapeValue(Object)'
     * </p>
     * <p>
     * Here we are testing if this method given the value of an attribute,
     * returns a string escaped. In this case the method must be static so there
     * is no need to create a previously instance of Rdn, the value in this case
     * is null.
     * </p>
     * <p>
     * The expected result is a null pointer exception.
     * </p>
     */
    public void testEscapeValue007() {
        try {
            Rdn.escapeValue(null);
            fail("The argument is wrong.");
        } catch (NullPointerException e) {}
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.escapeValue(Object)'
     * </p>
     * <p>
     * Here we are testing if this method given the value of an attribute,
     * returns a string escaped. In this case the method must be static so there
     * is no need to create a previously instance of Rdn, the value in this case
     * is not a string but a diferent object.
     * </p>
     * <p>
     * The expected result is a class cast exception.
     * </p>
     */
    public void testEscapeValue008() {
        try {
            Object x = new Object();
            Rdn.escapeValue(x);
            fail("The argument is wrong.");
        } catch (ClassCastException e) {}
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.escapeValue(Object)'
     * </p>
     * <p>
     * Here we are testing if this method given the value of an attribute,
     * returns a string escaped. In this case the method must be static so there
     * is no need to create a previously instance of Rdn, the value in this case
     * is a byte array.
     * </p>
     * <p>
     * The expected result is the escape value for that array.
     * </p>
     */
    public void testEscapeValue009() {
        byte[] x = { (byte) 4, (byte) 8f };
        assertEquals("#0408", Rdn.escapeValue(x));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.unescapeValue(String)'
     * </p>
     * <p>
     * Here we are testing if a given attribute value string formated, returns
     * the unformated value. In this case we are testing the special character
     * "," in the form "this\,that".
     * </p>
     * <p>
     * The expected result is "this,that".
     * </p>
     */
    public void testUnescapeValue001() {
        assertEquals("this,that", Rdn.unescapeValue("this\\,that"));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.unescapeValue(String)'
     * </p>
     * <p>
     * Here we are testing if a given attribute value string formated, returns
     * the unformated value. In this case we are testing the special character
     * "+" in the form "this\+that".
     * </p>
     * <p>
     * The expected result is "this+that".
     * </p>
     */
    public void testUnescapeValue002() {
        assertEquals("this+that", Rdn.unescapeValue("this\\+that"));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.unescapeValue(String)'
     * </p>
     * <p>
     * Here we are testing if a given attribute value string formated, returns
     * the unformated value. In this case we are testing the special character
     * ";" in the form "this\;that".
     * </p>
     * <p>
     * The expected result is "this;that".
     * </p>
     */
    public void testUnescapeValue003() {
        assertEquals("this;that", Rdn.unescapeValue("this\\;that"));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.unescapeValue(String)'
     * </p>
     * <p>
     * Here we are testing if a given attribute value string formated, returns
     * the unformated value. In this case we are testing the special character
     * "&lt;" in the form "this\&lt;that".
     * </p>
     * <p>
     * The expected result is "this&lt;that".
     * </p>
     */
    public void testUnescapeValue004() {
        assertEquals("this<that", Rdn.unescapeValue("this\\<that"));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.unescapeValue(String)'
     * </p>
     * <p>
     * Here we are testing if a given attribute value string formated, returns
     * the unformated value. In this case we are testing the special character
     * "&gt;" in the form "this\&gt;that".
     * </p>
     * <p>
     * The expected result is "this&gt;that".
     * </p>
     */
    public void testUnescapeValue005() {
        assertEquals("this>that", Rdn.unescapeValue("this\\>that"));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.unescapeValue(String)'
     * </p>
     * <p>
     * Here we are testing if a given attribute value string formated, returns
     * the unformated value. In this case we are testing the special character
     * "\" in the form "this\that".
     * </p>
     * <p>
     * The expected result is an illegal argument ecpetion.
     * </p>
     */
    public void testUnescapeValue006() {
        try {
            Rdn.unescapeValue("this\\that");
            fail("The arguments are wrong.");
        } catch (IllegalArgumentException e) {}
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.unescapeValue(String)'
     * </p>
     * <p>
     * Here we are testing if a given attribute value string formated, returns
     * the unformated value. In this case we are testing the special character
     * "\" in the form "this\4C".
     * </p>
     * <p>
     * The expected result is "thisL".
     * </p>
     */
    public void testUnescapeValue007() {
        assertEquals("thisL", Rdn.unescapeValue("this\\4C"));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.unescapeValue(String)'
     * </p>
     * <p>
     * Here we are testing if a given attribute value string formated, returns
     * the unformated value. In this case we are testing if send a null.
     * </p>
     * <p>
     * The expected result is a null pointer exception.
     * </p>
     */
    public void testUnescapeValue008() {
        try {
            Rdn.unescapeValue(null);
            fail("The argument is worng.");
        } catch (NullPointerException e) {

        }
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.unescapeValue(String)'
     * </p>
     * <p>
     * Here we are testing if a given attribute value string formated, returns
     * the unformated value. In this case we are testing the special character
     * "#" in the form "#04".
     * </p>
     * <p>
     * The expected result is an object with the implementation of the byte that
     * represents.
     * </p>
     */
    public void testUnescapeValue009() {
        assertEquals("#04", Rdn.escapeValue(Rdn.unescapeValue("#04")));
    }

    /**
     * <p>
     * Test method for 'javax.naming.ldap.Rdn.unescapeValue(String)'
     * </p>
     * <p>
     * Here we are testing if a given attribute value string formated, returns
     * the unformated value. In this case we are testing the special character
     * "#" in the form "#GOFJMOII".
     * </p>
     * <p>
     * The expected result is an exception.
     * </p>
     */
    public void testUnescapeValue010() {
        try {
            Rdn.unescapeValue("#GOFJMOII");
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {}
    }

    public void testSerializationCompatibility() throws Exception{
        Rdn object = new Rdn("t=\\20\\ te\\ s\\20t\\20\\20 + t2 = test1\\20\\ ");
        SerializationTest.verifyGolden(this, object);
    }
}
