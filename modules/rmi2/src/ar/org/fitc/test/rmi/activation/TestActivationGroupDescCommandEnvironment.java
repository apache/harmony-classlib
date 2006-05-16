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
package ar.org.fitc.test.rmi.activation;

import java.rmi.activation.ActivationGroupDesc;

import ar.org.fitc.test.util.Messages;

import junit.framework.TestCase;

public class TestActivationGroupDescCommandEnvironment extends TestCase
        implements Messages {
    int hc = 0;

    public void testActivationGroupDescCommandEnvironment001() {

        assertNotNull(msgNotNull, new ActivationGroupDesc.CommandEnvironment(
                "", new String[0]));

    }

    public void testActivationGroupDescCommandEnvironment002() {

        assertNotNull(msgNotNull, new ActivationGroupDesc.CommandEnvironment(
                "", new String[] { "Hi", "Ha", "Hu" }));

    }

    public void testActivationGroupDescCommandEnvironment003() {

        assertNotNull(msgNotNull, new ActivationGroupDesc.CommandEnvironment(
                "", null));

    }

    public void testActivationGroupDescCommandEnvironment004() {

        assertNotNull(msgNotNull, new ActivationGroupDesc.CommandEnvironment(
                "Hola la", new String[0]));

    }

    public void testActivationGroupDescCommandEnvironment005() {

        assertNotNull(msgNotNull, new ActivationGroupDesc.CommandEnvironment(
                "Hola la", new String[] { "Hi", "Ha", "Hu" }));

    }

    public void testActivationGroupDescCommandEnvironment006() {

        assertNotNull(msgNotNull, new ActivationGroupDesc.CommandEnvironment(
                "Hola la", null));

    }

    public void testActivationGroupDescCommandEnvironment007() {

        assertNotNull(msgNotNull, new ActivationGroupDesc.CommandEnvironment(
                null, new String[0]));

    }

    public void testActivationGroupDescCommandEnvironment008() {

        assertNotNull(msgNotNull, new ActivationGroupDesc.CommandEnvironment(
                null, new String[] { "Hi", "Ha", "Hu" }));

    }

    public void testActivationGroupDescCommandEnvironment009() {

        assertNotNull(msgNotNull, new ActivationGroupDesc.CommandEnvironment(
                null, null));

    }

    public void testGetCommandOptions001() {

        String[] s = new String[0];
        String[] argv = (new ActivationGroupDesc.CommandEnvironment("",
                new String[0])).getCommandOptions();
        for (int i = 0; i < s.length; i++) {
            assertEquals("Point to point the command may be equals", s[i],
                    argv[i]);
        }

    }

    public void testGetCommandOptions002() {

        String[] s = new String[] { "Hi", "Ha", "Hu" };
        String[] argv = (new ActivationGroupDesc.CommandEnvironment("",
                new String[] { "Hi", "Ha", "Hu" })).getCommandOptions();
        for (int i = 0; i < s.length; i++) {
            assertEquals("Point to point the command may be equals", s[i],
                    argv[i]);
        }

    }

    public void testGetCommandOptions003() {

        assertNotNull("Null is give, but not resive",
                (new ActivationGroupDesc.CommandEnvironment("", null))
                        .getCommandOptions());

    }

    public void testGetCommandOptions004() {

        String[] s = new String[0];
        String[] argv = (new ActivationGroupDesc.CommandEnvironment("Hola la",
                new String[0])).getCommandOptions();
        for (int i = 0; i < s.length; i++) {
            assertEquals("Point to point the command may be equals", s[i],
                    argv[i]);
        }

    }

    public void testGetCommandOptions005() {

        String[] s = new String[] { "Hi", "Ha", "Hu" };
        String[] argv = (new ActivationGroupDesc.CommandEnvironment("Hola la",
                new String[] { "Hi", "Ha", "Hu" })).getCommandOptions();
        for (int i = 0; i < s.length; i++) {
            assertEquals("Point to point the command may be equals", s[i],
                    argv[i]);
        }

    }

    public void testGetCommandOptions006() {

        assertNotNull("Null is given, but not recived",
                (new ActivationGroupDesc.CommandEnvironment("Hola la", null))
                        .getCommandOptions());

    }

    public void testGetCommandOptions007() {

        String[] s = new String[0];
        String[] argv = (new ActivationGroupDesc.CommandEnvironment(null,
                new String[0])).getCommandOptions();
        for (int i = 0; i < s.length; i++) {
            assertEquals("Point to point the command may be equals", s[i],
                    argv[i]);
        }

    }

    public void testGetCommandOptions008() {

        String[] s = new String[] { "Hi", "Ha", "Hu" };
        String[] argv = (new ActivationGroupDesc.CommandEnvironment(null,
                new String[] { "Hi", "Ha", "Hu" })).getCommandOptions();
        for (int i = 0; i < s.length; i++) {
            assertEquals("Point to point the command may be equals", s[i],
                    argv[i]);
        }

    }

    public void testGetCommandOptions009() {

        assertNotNull("Null is given, but not recived",
                (new ActivationGroupDesc.CommandEnvironment(null, null))
                        .getCommandOptions());

    }

    public void testGetCommandPath001() {

        String s = "";
        String path = (new ActivationGroupDesc.CommandEnvironment("",
                new String[0])).getCommandPath();
        assertEquals("Path is initial path", s, path);

    }

    public void testGetCommandPath002() {

        String s = "";
        String path = (new ActivationGroupDesc.CommandEnvironment("",
                new String[] { "Hi", "Ha", "Hu" })).getCommandPath();
        assertEquals("Path is initial path", s, path);

    }

    public void testGetCommandPath003() {

        String s = "";
        String path = (new ActivationGroupDesc.CommandEnvironment("", null))
                .getCommandPath();
        assertEquals("Path is initial path", s, path);

    }

    public void testGetCommandPath004() {

        String s = "Hola la";
        String path = (new ActivationGroupDesc.CommandEnvironment("Hola la",
                new String[0])).getCommandPath();
        assertEquals("Path is initial path", s, path);

    }

    public void testGetCommandPath005() {

        String s = "Hola la";
        String path = (new ActivationGroupDesc.CommandEnvironment("Hola la",
                new String[] { "Hi", "Ha", "Hu" })).getCommandPath();
        assertEquals("Path is initial path", s, path);

    }

    public void testGetCommandPath006() {

        String s = "Hola la";
        String path = (new ActivationGroupDesc.CommandEnvironment("Hola la",
                null)).getCommandPath();
        assertEquals("Path is initial path", s, path);

    }

    public void testGetCommandPath007() {

        String s = null;
        String path = (new ActivationGroupDesc.CommandEnvironment(null,
                new String[0])).getCommandPath();
        assertEquals("Path is initial path", s, path);

    }

    public void testGetCommandPath008() {

        String s = null;
        String path = (new ActivationGroupDesc.CommandEnvironment(null,
                new String[] { "Hi", "Ha", "Hu" })).getCommandPath();
        assertEquals("Path is initial path", s, path);

    }

    public void testGetCommandPath009() {

        String s = null;
        String path = (new ActivationGroupDesc.CommandEnvironment(null, null))
                .getCommandPath();
        assertEquals("Path is initial path", s, path);

    }

    /*
     * Test method for 'java.rmi.activation.ActivationGroupID.equals(Object)'
     */
    public void testEquals001() {

        assertEquals("two CommandEnvironmet are equals",
                new ActivationGroupDesc.CommandEnvironment("", new String[0]),
                new ActivationGroupDesc.CommandEnvironment("", new String[0]));

    }

    public void testEquals002() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals003() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                null)));

    }

    public void testEquals004() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", new String[0])));

    }

    public void testEquals005() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals006() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", null)));

    }

    public void testEquals007() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, new String[0])));

    }

    public void testEquals008() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals009() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, null)));

    }

    public void testEquals010() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", new String[] {
                        "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                new String[0])));

    }

    public void testEquals011() {

        assertEquals("two CommandEnvironmet are equals",
                new ActivationGroupDesc.CommandEnvironment("", new String[] {
                        "Hi", "Ha", "Hu" }),
                new ActivationGroupDesc.CommandEnvironment("", new String[] {
                        "Hi", "Ha", "Hu" }));

    }

    public void testEquals012() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", new String[] {
                        "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                null)));

    }

    public void testEquals013() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", new String[] {
                        "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", new String[0])));

    }

    public void testEquals014() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", new String[] {
                        "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals015() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", new String[] {
                        "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", null)));

    }

    public void testEquals016() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", new String[] {
                        "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, new String[0])));

    }

    public void testEquals017() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", new String[] {
                        "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals018() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", new String[] {
                        "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, null)));

    }

    public void testEquals019() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", null)
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                new String[0])));

    }

    public void testEquals020() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", null)
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals021() {

        assertEquals("two CommandEnvironmet are equals",
                new ActivationGroupDesc.CommandEnvironment("", null),
                new ActivationGroupDesc.CommandEnvironment("", null));

    }

    public void testEquals022() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", null)
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", new String[0])));

    }

    public void testEquals023() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", null)
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals024() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", null)
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", null)));

    }

    public void testEquals025() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", null)
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, new String[0])));

    }

    public void testEquals026() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", null)
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals027() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("", null)
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, null)));

    }

    public void testEquals028() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la",
                        new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                new String[0])));

    }

    public void testEquals029() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la",
                        new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals030() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la",
                        new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                null)));

    }

    public void testEquals031() {

        assertEquals("two CommandEnvironmet are equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la",
                        new String[0]),
                new ActivationGroupDesc.CommandEnvironment("Hola la",
                        new String[0]));

    }

    public void testEquals032() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la",
                        new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals033() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la",
                        new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", null)));

    }

    public void testEquals034() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la",
                        new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, new String[0])));

    }

    public void testEquals035() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la",
                        new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals036() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la",
                        new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, null)));

    }

    public void testEquals037() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la",
                        new String[] { "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                new String[0])));

    }

    public void testEquals038() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la",
                        new String[] { "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals039() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la",
                        new String[] { "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                null)));

    }

    public void testEquals040() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la",
                        new String[] { "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", new String[0])));

    }

    public void testEquals041() {

        assertEquals("two CommandEnvironmet are equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la",
                        new String[] { "Hi", "Ha", "Hu" }),
                new ActivationGroupDesc.CommandEnvironment("Hola la",
                        new String[] { "Hi", "Ha", "Hu" }));

    }

    public void testEquals042() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la",
                        new String[] { "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", null)));

    }

    public void testEquals043() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la",
                        new String[] { "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, new String[0])));

    }

    public void testEquals044() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la",
                        new String[] { "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals045() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la",
                        new String[] { "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, null)));

    }

    public void testEquals046() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la", null)
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                new String[0])));

    }

    public void testEquals047() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la", null)
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals048() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la", null)
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                null)));

    }

    public void testEquals049() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la", null)
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", new String[0])));

    }

    public void testEquals050() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la", null)
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals051() {

        assertEquals("two CommandEnvironmet are equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la", null),
                new ActivationGroupDesc.CommandEnvironment("Hola la", null));

    }

    public void testEquals052() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la", null)
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, new String[0])));

    }

    public void testEquals053() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la", null)
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals054() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment("Hola la", null)
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, null)));

    }

    public void testEquals055() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                new String[0])));

    }

    public void testEquals056() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals057() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                null)));

    }

    public void testEquals058() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", new String[0])));

    }

    public void testEquals059() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals060() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", null)));

    }

    public void testEquals061() {

        assertEquals(
                "two CommandEnvironmet are equals",
                new ActivationGroupDesc.CommandEnvironment(null, new String[0]),
                new ActivationGroupDesc.CommandEnvironment(null, new String[0]));

    }

    public void testEquals062() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals063() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, new String[0])
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, null)));

    }

    public void testEquals064() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, new String[] {
                        "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                new String[0])));

    }

    public void testEquals065() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, new String[] {
                        "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals066() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, new String[] {
                        "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                null)));

    }

    public void testEquals067() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, new String[] {
                        "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", new String[0])));

    }

    public void testEquals068() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, new String[] {
                        "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals069() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, new String[] {
                        "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", null)));

    }

    public void testEquals070() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, new String[] {
                        "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, new String[0])));

    }

    public void testEquals071() {

        assertEquals("two CommandEnvironmet are equals",
                new ActivationGroupDesc.CommandEnvironment(null, new String[] {
                        "Hi", "Ha", "Hu" }),
                new ActivationGroupDesc.CommandEnvironment(null, new String[] {
                        "Hi", "Ha", "Hu" }));

    }

    public void testEquals072() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, new String[] {
                        "Hi", "Ha", "Hu" })
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, null)));

    }

    public void testEquals073() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, null)
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                new String[0])));

    }

    public void testEquals074() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, null)
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals075() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, null)
                        .equals(new ActivationGroupDesc.CommandEnvironment("",
                                null)));

    }

    public void testEquals076() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, null)
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", new String[0])));

    }

    public void testEquals077() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, null)
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals078() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, null)
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                "Hola la", null)));

    }

    public void testEquals079() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, null)
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, new String[0])));

    }

    public void testEquals080() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, null)
                        .equals(new ActivationGroupDesc.CommandEnvironment(
                                null, new String[] { "Hi", "Ha", "Hu" })));

    }

    public void testEquals081() {

        assertEquals("two CommandEnvironmet are equals",
                new ActivationGroupDesc.CommandEnvironment(null, null),
                new ActivationGroupDesc.CommandEnvironment(null, null));

    }

    public void testEquals082() {

        assertFalse("two CommandEnvironmet are not equals",
                new ActivationGroupDesc.CommandEnvironment(null, null)
                        .equals(null));

    }

    public void testHashCode001() {

        hc = new ActivationGroupDesc.CommandEnvironment("", new String[0])
                .hashCode();
        for (int i = 1; i < 10; i++) {
            assertEquals("to CommandEnvironmet are equals", hc,
                    new ActivationGroupDesc.CommandEnvironment("",
                            new String[0]).hashCode());
        }

    }

    public void testHashCode002() {

        hc = new ActivationGroupDesc.CommandEnvironment("", new String[] {
                "Hi", "Ha", "Hu" }).hashCode();
        for (int i = 1; i < 10; i++) {
            assertEquals("to CommandEnvironmet are equals", hc,
                    new ActivationGroupDesc.CommandEnvironment("",
                            new String[] { "Hi", "Ha", "Hu" }).hashCode());
        }

    }

    public void testHashCode003() {

        hc = new ActivationGroupDesc.CommandEnvironment("", null).hashCode();
        for (int i = 1; i < 10; i++) {
            assertEquals("to CommandEnvironmet are equals", hc,
                    new ActivationGroupDesc.CommandEnvironment("", null)
                            .hashCode());
        }

    }

    public void testHashCode004() {

        hc = new ActivationGroupDesc.CommandEnvironment("Hola la",
                new String[0]).hashCode();
        for (int i = 1; i < 10; i++) {
            assertEquals("to CommandEnvironmet are equals", hc,
                    new ActivationGroupDesc.CommandEnvironment("Hola la",
                            new String[0]).hashCode());
        }

    }

    public void testHashCode005() {

        hc = new ActivationGroupDesc.CommandEnvironment("Hola la",
                new String[] { "Hi", "Ha", "Hu" }).hashCode();
        for (int i = 1; i < 10; i++) {
            assertEquals("to CommandEnvironmet are equals", hc,
                    new ActivationGroupDesc.CommandEnvironment("Hola la",
                            new String[] { "Hi", "Ha", "Hu" }).hashCode());
        }

    }

    public void testHashCode006() {

        hc = new ActivationGroupDesc.CommandEnvironment("Hola la", null)
                .hashCode();
        for (int i = 1; i < 10; i++) {
            assertEquals("to CommandEnvironmet are equals", hc,
                    new ActivationGroupDesc.CommandEnvironment("Hola la", null)
                            .hashCode());
        }

    }

    public void testHashCode007() {

        hc = new ActivationGroupDesc.CommandEnvironment(null, new String[0])
                .hashCode();
        for (int i = 1; i < 10; i++) {
            assertEquals("to CommandEnvironmet are equals", hc,
                    new ActivationGroupDesc.CommandEnvironment(null,
                            new String[0]).hashCode());
        }

    }

    public void testHashCode008() {

        hc = new ActivationGroupDesc.CommandEnvironment(null, new String[] {
                "Hi", "Ha", "Hu" }).hashCode();
        for (int i = 1; i < 10; i++) {
            assertEquals("to CommandEnvironmet are equals", hc,
                    new ActivationGroupDesc.CommandEnvironment(null,
                            new String[] { "Hi", "Ha", "Hu" }).hashCode());
        }

    }

    public void testHashCode009() {

        hc = new ActivationGroupDesc.CommandEnvironment(null, null).hashCode();
        for (int i = 1; i < 10; i++) {
            assertEquals("to CommandEnvironmet are equals", hc,
                    new ActivationGroupDesc.CommandEnvironment(null, null)
                            .hashCode());
        }

    }
}
