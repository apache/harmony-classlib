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
* @author Maxim V. Makarov
* @version $Revision$
*/

package javax.security.auth.callback;


import java.util.Locale;

import org.apache.harmony.security.test.PerformanceTest;

/**
 * Tests LanguageCallback class
 */

public class LanguageCallbackTest extends PerformanceTest {

    LanguageCallback lc;
    
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(LanguageCallbackTest.class);
    }

    /**
     * test of Ctor, method set/getLocale() 
     */
    public final void testLanguageCallback_01() {
        lc = new LanguageCallback();
        Locale locale = Locale.US;
        lc.setLocale(locale);
        assertEquals("US", lc.getLocale().getCountry());
    }

    /**
     * locale is null 
     */
    public final void testLanguageCallback_02() {
        lc = new LanguageCallback();
        assertNull(lc.getLocale());
        try {
        lc.setLocale(null);
        } catch (NullPointerException e ){}
    }

}
