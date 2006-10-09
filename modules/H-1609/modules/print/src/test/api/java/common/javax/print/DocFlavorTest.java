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
 * @author Irina A. Arkhipets 
 * @version $Revision: 1.3 $ 
 */ 

/*
 * DocFlavorTest.java
 * 
 * JUnit tests for javax.print.DocFlavor class.
 * 
 */

package javax.print;

import junit.framework.TestCase;

public class DocFlavorTest extends TestCase {

static public void testStatic() {
    System.out.println("----------------------------------------");
    System.out.println("Static variables testing...");
    try {
        String encoding = System.getProperty("file.encoding");
        if ((encoding != null) && (encoding.length() > 0)) {
            assertEquals(DocFlavor.hostEncoding, encoding);
        } else {
            System.out.println(
                    "WARNING: Can not get system file.encoding property!");
        }
    } catch (Exception e) {
        System.out.println(
                "WARNING: Can not get system file.encoding property!");
        e.printStackTrace();
    }
}

public void testHashCode() {
    DocFlavor f1 = new DocFlavor("text/plain; charset=us-ascii", "[B");
    DocFlavor f2 = 
            new DocFlavor("text/plain; charset=us-ascii (comments)", "[B");
    assertEquals(f1.hashCode(), f1.hashCode());
    assertEquals(f1.hashCode(), f2.hashCode());
}

public void testMyDocFlavor() {
    tryNullPointer(null, "[B");
    tryNullPointer("text/plain", null);
}

public void testEqualsObject() {
    DocFlavor f1 = new DocFlavor("text/plain; charset=us-ascii", "[B");
    DocFlavor f2 = 
            new DocFlavor("text/plain; charset=us-ascii (comments)", "[B");
    DocFlavor f3 = new DocFlavor("image/gif", "[B");
    DocFlavor f4 = new DocFlavor("text/plain", "[B");
    DocFlavor f5 = new DocFlavor("text/plain; charset=us-ascii", "[C");
    assertTrue(f1.equals(f1));
    assertTrue(f1.equals(f2));
    assertFalse(f1.equals(f3));
    assertFalse(f1.equals(f4));
    assertFalse(f1.equals(f5));
}

public void testGetMediaSubtype() {
    DocFlavor f = new DocFlavor("text/plain; charset=us-ascii", "[B");
    assertTrue(f.getMediaSubtype().equals(new String("plain")));
}

public void testGetMediaType() {
    DocFlavor f = new DocFlavor("text/plain; charset=us-ascii", "[B");
    assertTrue(f.getMediaType().equals(new String("text")));
}

public void testGetMimeType() {
    DocFlavor f = 
            new DocFlavor("TEXT/plain; BBB=par1; aaa=par2 (comments)", "[B");
    assertTrue((f.getMimeType()).equals(
            new String("text/plain; aaa=\"par2\"; bbb=\"par1\"")));
}

public void testGetParameter() {
    DocFlavor f =
            new DocFlavor("TEXT/plain; BBB=par1; aaa=par2 (comments)", "[B");
    assertEquals(f.getParameter("bbb"), new String("par1"));
    assertEquals(f.getParameter("absent"), null);
}

public void testGetRepresentationClassName() {
    DocFlavor f = 
            new DocFlavor("TEXT/plain; BBB=par1; aaa=par2 (comments)", "[B");
    assertEquals(f.getRepresentationClassName(), new String("[B"));
}

public void testToString() {
    DocFlavor f = 
            new DocFlavor("TEXT/plain; BBB=par1; aaa=par2 (comments)", "[B");
    assertEquals(f.toString(), new String(
            "text/plain; aaa=\"par2\"; bbb=\"par1\"; class=\"[B\""));
}

void tryNullPointer(String s1, String s2) {
    try {
        new DocFlavor(s1, s2);
        fail();
    } catch (NullPointerException e) {
        /* NullPointerException is expected here, so the test passes */
    }
}
}