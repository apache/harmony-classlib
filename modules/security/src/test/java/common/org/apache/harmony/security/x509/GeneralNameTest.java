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
* @author Alexander Y. Kleymenov
* @version $Revision$
*/

package org.apache.harmony.security.x509;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.harmony.security.x501.Name;


/**
 * GeneralNameTest
 */
public class GeneralNameTest extends TestCase {

    public void testGeneralName() {
        try {
            GeneralName san0 = 
                new GeneralName(new OtherName("1.2.3.4.5", new byte[] {1, 2, 0, 1}));
            GeneralName san1 = new GeneralName(1, "rfc@822.Name");
            GeneralName san2 = new GeneralName(2, "dNSName");
            GeneralName san3 = new GeneralName(new ORAddress());
            GeneralName san4 = new GeneralName(new Name("O=Organization"));
            GeneralName san5 = 
                new GeneralName(new EDIPartyName("assigner", "party"));
            GeneralName san6 = new GeneralName(6, "http://uniform.Resource.Id");
            GeneralName san7 = new GeneralName(7, "1.1.1.1");
            GeneralName san8 = new GeneralName(8, "1.2.3.4444.55555");

            GeneralNames sans_1 = new GeneralNames();
            sans_1.addName(san0);
            sans_1.addName(san1);
            sans_1.addName(san2);
            sans_1.addName(san3);
            sans_1.addName(san4);
            sans_1.addName(san5);
            sans_1.addName(san6);
            sans_1.addName(san7);
            sans_1.addName(san8);

            byte[] encoding =  GeneralNames.ASN1.encode(sans_1);
            GeneralNames.ASN1.decode(encoding);
        } catch (Exception e) {
            // should not be thrown: 
            // provided string representations are correct
            e.printStackTrace();
        }
    }
    
    public void testGeneralName1() {
        try {
            OtherName on = 
                new OtherName("1.2.3.4.5", new byte[] {1, 2, 0, 1});
            byte[] encoding = OtherName.ASN1.encode(on);
            new GeneralName(0, encoding);
            OtherName.ASN1.decode(encoding);
            GeneralName gn = new GeneralName(on);
            new GeneralName(0, gn.getEncodedName());
            assertEquals(gn, new GeneralName(0, gn.getEncodedName()));
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
        
    
    public static Test suite() {
        return new TestSuite(GeneralNameTest.class);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}

