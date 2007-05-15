/*  Licensed to the Apache Software Foundation (ASF) under one or more
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
 
package javax.swing.text.html;

import javax.swing.text.Element;
import javax.swing.text.html.HTMLDocument;

public class FormViewTest extends junit.framework.TestCase { 
    
    /**
     * javax.swing.text.html.FormView
     */
	public void testgetMaximumSpan_I(){
        HTMLDocument doc = new HTMLDocument();
        Element par=doc.getDefaultRootElement(); 
        FormView obj=new FormView(par); 
        try {
            obj.getMaximumSpan(11);
            fail("IllegalArgumentException should be thrown");
        } catch (IllegalArgumentException e) {
            //expected
        } 
    }    
}
 
