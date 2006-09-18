/*
 *  Copyright 2005 - 2006 The Apache Software Software Foundation or its licensors, as applicable.
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

package java.awt;

import java.awt.font.TextAttribute;
import java.text.AttributedString;

import junit.framework.TestCase;

public class FontTest extends TestCase {

    private final Font f = new Font("dialog", Font.PLAIN, 12);
    
    /**
     * Checks Font.getLineMetrics() methods if FontRenderContext parameter is NULL. 
     *
     */
    public void test_Font_getLineMetrics_WithNullFRC(){
        // // regression test for Harmony-1465
        final String str = "test";
        try{
            f.getLineMetrics(str, null);
            fail("NullPointerException expected but wasn't thrown!");
        }catch (NullPointerException e) {
            // as expected
        }

        try{
            f.getLineMetrics(str, 1, 3, null);
            fail("NullPointerException expected but wasn't thrown!");
        }catch (NullPointerException e) {
            // as expected
        }

        try{
            f.getLineMetrics(str.toCharArray(), 1, 3, null);
            fail("NullPointerException expected but wasn't thrown!");
        }catch (NullPointerException e) {
            // as expected
        }

        try{
            AttributedString as = new AttributedString("test");
            as.addAttribute(TextAttribute.FONT, f, 0, 2 );

            f.getLineMetrics(as.getIterator(), 1, 3, null);
            fail("NullPointerException expected but wasn't thrown!");
        }catch (NullPointerException e) {
            // as expected
        }
        
    }
    
}
