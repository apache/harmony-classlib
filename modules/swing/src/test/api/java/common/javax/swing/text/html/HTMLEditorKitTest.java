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
 * @author Aleksey V. Yantsen
 * @version $Revision$
 */
package javax.swing.text.html;

import javax.swing.BasicSwingTestCase;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import junit.framework.TestCase;

/**
 * Tests functionality of HTMLEditorKit class.
 *
 */
public class HTMLEditorKitTest extends TestCase {

	class MyHTMLEditorKit extends HTMLEditorKit {
		public void createInputAttributes(Element element,
	            MutableAttributeSet set) {
			super.createInputAttributes(element, set);
		}
	}

    /**
     * Check that method throws NPE if element == null
     */
	public void testcreateInputAttributes01() {
		MyHTMLEditorKit kit = new MyHTMLEditorKit();
		Element element = null;
		MutableAttributeSet set = new SimpleAttributeSet();
		
		try {
			kit.createInputAttributes(element, set);
			fail("NullPointerException not thrown!");
		} catch (NullPointerException e) {
			// expected exception
		}
	}

    /**
     * Check that method throws NPE if set == null
     */
	public void testcreateInputAttributes02() {
		MyHTMLEditorKit kit = new MyHTMLEditorKit();
		HTMLDocument doc = new HTMLDocument();
		Element element = doc.getDefaultRootElement();
		MutableAttributeSet set = null;
		
		try {
			kit.createInputAttributes(element, set);
			fail("NullPointerException not thrown!");
		} catch (NullPointerException e) {
			// expected exception
		}
	}
}