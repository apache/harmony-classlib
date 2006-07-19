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
/**
 * @author Alexey A. Ivanov
 * @version $Revision$
 */
package javax.swing.text;

import javax.swing.BasicSwingTestCase;

public class DefaultStyledDocumentRTest extends BasicSwingTestCase {
    private Document doc;
    private Element root;
    private Element paragraph;
    private Element child;
    private int insertOffset = 5;
    private MutableAttributeSet attrs;

    /**
     * Insert one space with attributes containing element name
     * <code>"icon"</code>.
     */
    public void testIconElement() throws Exception {
        attrs.addAttribute(AbstractDocument.ElementNameAttribute, "icon");
        doc.insertString(insertOffset, " ", attrs);

        assertEquals(3, paragraph.getElementCount());
        child = paragraph.getElement(0);
        assertEquals(AbstractDocument.ContentElementName, child.getName());

        child = paragraph.getElement(1);
        assertEquals("icon", child.getName());
        assertEquals(insertOffset, child.getStartOffset());
        assertEquals(insertOffset + 1, child.getEndOffset());

        child = paragraph.getElement(2);
        assertEquals(AbstractDocument.ContentElementName, child.getName());
    }

    /**
     * Insert two spaces with attributes containing element name
     * <code>"icon"</code>.
     */
    public void testIconElementTwoSpaces() throws Exception {
        attrs.addAttribute(AbstractDocument.ElementNameAttribute, "icon");
        doc.insertString(insertOffset, "  ", attrs);

        assertEquals(3, paragraph.getElementCount());
        child = paragraph.getElement(0);
        assertEquals(AbstractDocument.ContentElementName, child.getName());

        child = paragraph.getElement(1);
        assertEquals("icon", child.getName());
        assertEquals(insertOffset, child.getStartOffset());
        assertEquals(insertOffset + 2, child.getEndOffset());

        child = paragraph.getElement(2);
        assertEquals(AbstractDocument.ContentElementName, child.getName());
    }

    /**
     * Insert not spaces with attributes containing element name
     * <code>"icon"</code>.
     */
    public void testIconElementNotSpaces() throws Exception {
        attrs.addAttribute(AbstractDocument.ElementNameAttribute, "icon");
        doc.insertString(insertOffset, "ab", attrs);

        assertEquals(3, paragraph.getElementCount());
        child = paragraph.getElement(0);
        assertEquals(AbstractDocument.ContentElementName, child.getName());

        child = paragraph.getElement(1);
        assertEquals("icon", child.getName());
        assertEquals(insertOffset, child.getStartOffset());
        assertEquals(insertOffset + 2, child.getEndOffset());
        assertEquals("ab", doc.getText(insertOffset, 2));

        child = paragraph.getElement(2);
        assertEquals(AbstractDocument.ContentElementName, child.getName());
    }

    /**
     * Insert not spaces with attributes containing element name
     * <code>"component"</code>.
     */
    public void testComponentElement() throws Exception {
        attrs.addAttribute(AbstractDocument.ElementNameAttribute, "component");
        doc.insertString(insertOffset, "ab", attrs);

        assertEquals(3, paragraph.getElementCount());
        child = paragraph.getElement(0);
        assertEquals(AbstractDocument.ContentElementName, child.getName());

        child = paragraph.getElement(1);
        assertEquals("component", child.getName());
        assertEquals(insertOffset, child.getStartOffset());
        assertEquals(insertOffset + 2, child.getEndOffset());
        assertEquals("ab", doc.getText(insertOffset, 2));

        child = paragraph.getElement(2);
        assertEquals(AbstractDocument.ContentElementName, child.getName());
    }

    protected void setUp() throws Exception {
        super.setUp();
        doc = new DefStyledDoc_Helpers.DefStyledDocWithLogging();
        doc.insertString(0, "test  text", null);
        root = doc.getDefaultRootElement();
        paragraph = root.getElement(0);
        attrs = new SimpleAttributeSet();
    }

}
