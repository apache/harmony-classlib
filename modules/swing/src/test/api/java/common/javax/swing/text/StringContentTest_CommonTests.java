/*
 *  Copyright 2005 - 2006 The Apache Software Foundation or its licensors, as applicable.
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
 * @author Roman I. Chernyatchik
 * @version $Revision$
 */
package javax.swing.text;

import java.util.Vector;

import javax.swing.BasicSwingTestCase;
import javax.swing.undo.UndoableEdit;


public class StringContentTest_CommonTests extends GapContentTest {

    protected void setUp() throws Exception {
        super.setUp();
        obj = content = new StringContent(30);
        obj.insertString(0, "This is a test string.");
    }

    public void testGetCharsImpliedCharPartial() throws BadLocationException {
        obj = content = new StringContent();

        assertEquals(1, content.length());

        text.setPartialReturn(false);
        content.getChars(0, 1, text);
        assertEquals("\n", text.toString());

        text.setPartialReturn(true);
        content.getChars(0, 1, text);
        assertEquals("\n", text.toString());
    }

    public void testGetPositionsInRangeVector() throws BadLocationException {
        Vector v = new Vector();
        v.add(new Object());
        v.add(new Object());
        content.createPosition(0);
        content.createPosition(1);
        content.createPosition(2);

        ((StringContent)content).getPositionsInRange(v, 0, 3);
        if (BasicSwingTestCase.isHarmony()) {
            // Position at offset 0 WILL NOT be included
            assertEquals(4, v.size());
        } else {
            // Position at offset 0 WILL be included
            assertEquals(5, v.size());
        }
    }

    /**
     * Tests that the position at offset of offset + len is included in
     * the returned vector.
     */
    public void testGetPositionsInRangeEnd() throws BadLocationException {
        content.createPosition(10);
        Vector v = ((StringContent)content).getPositionsInRange(null, 0, 10);
        assertEquals(1, v.size());
    }

    public void testGetPositionsInRange() throws BadLocationException {
        Vector pos = new Vector();

        for (int i = 0; i < content.length(); i += 2) {
            Position p = content.createPosition(i);
            if (i >= 3 && i <= 3 + 9) {
                pos.add(p);
            }
        }
        Vector v = ((StringContent)content).getPositionsInRange(null, 3, 9);

        assertEquals(pos.size(), v.size());

        int[] offsets = new int[v.size()];
        for (int i = 0; i < pos.size(); i++) {
            offsets[i] = ((Position)pos.get(i)).getOffset();
        }

        UndoableEdit ue = content.remove(0, 9);
        ue.undo();

        for (int i = 0; i < pos.size(); i++) {
            assertEquals(offsets[i], ((Position)pos.get(i)).getOffset());
        }
    }

    public void testGetCharsNegativeLength() {
        // Is Already tested in StringContentTest
    }

    public void testGetCharsAfterGapNoImplied() throws BadLocationException {
        // N/A
    }

    public void testGetCharsAfterGap() throws BadLocationException {
        // N/A
    }

    public void testGetCharsBeforeGap() throws BadLocationException {
        // N/A
    }

    public void testGetCharsFullLength() throws BadLocationException {
        // N/A
    }

    public void testGetCharsFullActualLength() throws BadLocationException {
        // N/A
    }

    public void testGetCharsImpliedChar() throws BadLocationException {
        // N/A
    }
    public void testGetCharsPartial() throws BadLocationException {
        // N/A
    }

    public void testGetCharsWithGap() throws BadLocationException {
        // N/A
    }

    public void testGetStringNegativeLength() {
      // Is Already tested in StringContentTest
    }
}
