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
 * @author Evgeniya G. Maenkova
 * @version $Revision$
 */
package javax.swing.undo;

import javax.swing.BasicSwingTestCase;
import javax.swing.UIManager;

import junit.framework.TestCase;

public class AbstractUndoableEditTest extends TestCase {
    protected AbstractUndoableEdit obj;

    public static void main(final String[] args) {
        junit.textui.TestRunner.run(AbstractUndoableEditTest.class);
    }

    protected void setUp() throws Exception {
        obj = new AbstractUndoableEdit();
    }

    public void testToString() {
        String s = obj.toString();
        assertNotNull(s);
        assertNotSame("", s);
    }

    protected boolean isAlive(final Object o) {
        return o.toString().indexOf("alive: true") != -1;
    }

    protected boolean hasBeenDone(final Object o) {
        return o.toString().indexOf("hasBeenDone: true") != -1;
    }

    public void testAbstractUndoableEdit() {
        if (BasicSwingTestCase.isHarmony()) {
            assertTrue(isAlive(obj));
        }
    }

    public void testDie() {
        obj.die();

        if (BasicSwingTestCase.isHarmony()) {
            assertEquals(false, isAlive(obj));
        }

        boolean wasException = false;
        try {
            obj.redo();
        } catch (CannotRedoException e) {
            wasException = true;
        }
        assertTrue("CannotRedoException was expected", wasException);

        wasException = false;
        try {
            obj.undo();
        } catch (CannotUndoException e) {
            wasException = true;
        }
        assertTrue("CannotUndoException was expected", wasException);
    }

    public void testRedo() {
        obj.undo();
        obj.redo();

        if (BasicSwingTestCase.isHarmony()) {
            assertEquals(true, hasBeenDone(obj));
        }

        if (!obj.canRedo()) {
            boolean wasException = false;
            try {
                obj.redo();
            } catch (CannotRedoException e) {
                wasException = true;
            }
            assertTrue("CannotRedoException was expected", wasException);
        }
    }

    public void testUndo() {
        obj.undo();

        if (BasicSwingTestCase.isHarmony()) {
            assertEquals(false, hasBeenDone(obj));
        }

        if (!obj.canUndo()) {
            boolean wasException = false;
            try {
                obj.undo();
            } catch (CannotUndoException e) {
                wasException = true;
            }
            assertTrue("CannotUndoException was expected", wasException);
        }
    }

    public void testCanRedo() {
        if (BasicSwingTestCase.isHarmony()) {
            assertEquals(isAlive(obj) && !hasBeenDone(obj), obj.canRedo());
        }
        obj.die();
        assertEquals(false, obj.canRedo());
    }

    public void testCanUndo() {
        if (BasicSwingTestCase.isHarmony()) {
            assertEquals(isAlive(obj) && hasBeenDone(obj), obj.canUndo());
        }
        obj.die();
        assertEquals(false, obj.canUndo());
    }

    public void testIsSignificant() {
        assertEquals(true, obj.isSignificant());
    }

    public void testGetPresentationName() {
        assertEquals("", obj.getPresentationName());
    }

    public void testGetRedoPresentationName() {
        assertEquals(UIManager.getString("AbstractUndoableEdit.redoText"),
                     obj.getRedoPresentationName());
    }

    public void testGetUndoPresentationName() {
        assertEquals(UIManager.getString("AbstractUndoableEdit.undoText"),
                     obj.getUndoPresentationName());
    }

    public void testAddEdit() {
        assertEquals(false, obj.addEdit(null));
    }

    public void testReplaceEdit() {
        assertEquals(false, obj.replaceEdit(null));
    }

}
