/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tests.api.java.util;

import java.util.FormattableFlags;
import junit.framework.TestCase;

public class FormattableFlagsTest extends TestCase {

    /**
     * @test java.util.FormattableFlags ConstantFieldValues
     */
    public void test_ConstantFieldValues() {
        assertEquals(1, FormattableFlags.LEFT_JUSTIFY);
        assertEquals(2, FormattableFlags.UPPERCASE);
        assertEquals(4, FormattableFlags.ALTERNATE);
    }
}
