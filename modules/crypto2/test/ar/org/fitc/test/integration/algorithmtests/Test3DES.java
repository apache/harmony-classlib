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

package ar.org.fitc.test.integration.algorithmtests;

final class Test3DES extends CipherTest {
    private static final String NAME = "DESede";

    private static final String[][] TEST_VALUES = {
            { "010101010101010101010101010101010101010101010101",
                    "95F8A5E5DD31D900", "8000000000000000" },
            { "010101010101010101010101010101010101010101010101",
                    "9D64555A9A10B852", "0000001000000000" },
            { "3849674C2602319E3849674C2602319E3849674C2602319E",
                    "51454B582DDF440A", "7178876E01F19B2A" },
            { "04B915BA43FEB5B604B915BA43FEB5B604B915BA43FEB5B6",
                    "42FD443059577FA2", "AF37FB421F8C4095" },
            { "0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF",
                    "736F6D6564617461", "3D124FE2198BA318" },
            { "0123456789ABCDEF55555555555555550123456789ABCDEF",
                    "736F6D6564617461", "FBABA1FF9D05E9B1" },
            { "0123456789ABCDEF5555555555555555FEDCBA9876543210",
                    "736F6D6564617461", "18d748e563620572" },
            { "0352020767208217860287665908219864056ABDFEA93457",
                    "7371756967676C65", "c07d2a0fa566fa30" },
            { "010101010101010180010101010101010101010101010102",
                    "0000000000000000", "e6e6dd5b7e722974" },
            { "10461034899880209107D0158919010119079210981A0101",
                    "0000000000000000", "e1ef62c332fe825b" },
            { "000000000000000000000000000000000000000000", "95F8A5E5DD31D900",
                    "8000000000000000" },
            { "000000000000000000000000000000000000000000", "9D64555A9A10B852",
                    "0000001000000000" },
            { "000000000000008000000000000000000000000001", "0000000000000000",
                    "e6e6dd5b7e722974" }, };

    protected Test3DES() {
        super(NAME, provider);
    }

    protected void doIt() throws Exception {
        testExistence(NAME, provider);
        testValuesECB(NAME, provider, TEST_VALUES);
        testKeyGenExistence(NAME, provider);
        testKeyGenWorks(NAME, provider);
    }
}
