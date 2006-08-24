/*
 *  Copyright 2006 The Apache Software Foundation or its licensors, as applicable.
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

package org.apache.harmony.crypto.utils;

import junit.framework.TestCase;

public class AlgNameMapperTest extends TestCase {

    /**
     * @tests org.apache.harmony.crypto.utils.AlgNameMapper#getStandardName(String)
     */
    public void testGetStandardName() {
        //Regression for HARMONY-962
        // check a hardcoded mapping
        String standardName = AlgNameMapper
                .getStandardName("PBEWITHSHAAND40BITRC2-CBC");
        assertEquals("pbeWithSHAAnd40BitRC2-CBC", standardName);
    }

    /**
     * @tests org.apache.harmony.crypto.utils.AlgNameMapper#isOID(String)
     */
    public void testIsOID() {
        //Regression for HARMONY-962
        String notOID = "not.an.oid";
        String badOID = "999.88.77";
        // SHA1withDSA OID
        String normalOID = "1.2.840.10040.4.3";

        assertTrue(AlgNameMapper.isOID(normalOID));
        assertFalse(AlgNameMapper.isOID(badOID));
        assertFalse(AlgNameMapper.isOID(notOID));
    }

    /**
     * @tests org.apache.harmony.crypto.utils.AlgNameMapper.selectEntries(Provider)
     */
    public void testSelectEntries() {
        // Regression for HARMONY-1185
        String algStandardName = "SHA1withRSA";
        String hardcodedOID = "1.2.840.113549.1.1.5";
        String alternativeName = "SHA1WithRSAEncryption";
        String anotherAlgStandardName = "SHA1withDSA";
        String alternativeOID = "1.3.14.3.2.13";
        assertEquals(hardcodedOID, AlgNameMapper.map2OID(algStandardName));
        assertEquals(algStandardName, AlgNameMapper.map2AlgName(hardcodedOID));

        // Mappings taken from a provider that do not override any of hardcoded
        // mappings should not be rejected.
        assertEquals(hardcodedOID, AlgNameMapper.map2OID(alternativeName));
        assertEquals(anotherAlgStandardName, AlgNameMapper
                .map2AlgName(alternativeOID));
    }
}
