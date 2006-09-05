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

import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Vector;

public class CompoundEdit extends AbstractUndoableEdit {

    protected Vector<UndoableEdit> edits = new Vector<UndoableEdit>();

    /**
     * This is flag that indicates edits are still being added to it.
     * The method end() sets inProgress to false.
     */
    boolean inProgress = true;

    public boolean addEdit(final UndoableEdit anEdit) {
       if (!inProgress) {
           return false;
       }

       UndoableEdit last = lastEdit();

       if (last != null && (last.addEdit(anEdit) || anEdit.replaceEdit(last))) {
            return true;
       }

       return edits.add(anEdit);
    }

    protected UndoableEdit lastEdit() {
        UndoableEdit last = null;
        try {
            last = edits.lastElement();
        } catch (final NoSuchElementException e) {
        }

        return last;
    }

    /*
     * The format of the string is based on 1.5 release behavior
     * which can be revealed using the following code:
     *
     *  CompoundEdit obj = new CompoundEdit();
     *  obj.addEdit(new CompoundEdit());
     *  System.out.println(obj.toString());
     */
    public String toString() {
        String str = super.toString();
        str += " inProgress: " + inProgress;
        str += " edits: [";
        for (ListIterator li = edits.listIterator(); li.hasNext();) {
            str += li.next().toString() + ((li.hasNext()) ? "," : "");
        }
        str += "]";
        return str;
    }

    public String getUndoPresentationName() {
        UndoableEdit last = lastEdit();

        if (last != null) {
            String undoName = last.getUndoPresentationName();
            if (undoName.length() != 0) {
                return undoName;
            }
        }

        return super.getUndoPresentationName();
    }

    public String getRedoPresentationName() {
        UndoableEdit last = lastEdit();

        if (last != null) {
            String redoName = last.getRedoPresentationName();
            if (redoName.length() != 0) {
                return redoName;
            }
        }

        return super.getRedoPresentationName();
    }

    public String getPresentationName() {
        UndoableEdit last = lastEdit();

        if (last != null) {
            String name = last.getPresentationName();
            if (name.length() != 0) {
                return name;
            }
        }

        return super.getPresentationName();
    }

    public boolean isSignificant() {
        for (ListIterator li = edits.listIterator(); li.hasNext();) {
            if (((UndoableEdit)li.next()).isSignificant()) {
                return true;
            }
        }
        return false;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public boolean canUndo() {
        return !isInProgress() && super.canUndo();
    }

    public boolean canRedo() {
        return !isInProgress() && super.canRedo();
    }

    public void undo() {
        super.undo();
        for (ListIterator li = edits.listIterator(edits.size());
            li.hasPrevious();) {
            ((UndoableEdit)li.previous()).undo();
        }
    }

    public void redo() {
        super.redo();
        for (ListIterator li = edits.listIterator(); li.hasNext();) {
            ((UndoableEdit)li.next()).redo();
        }
    }

    public void end() {
        inProgress = false;
    }

    public void die() {
        super.die();
        for (ListIterator li = edits.listIterator(edits.size());
           li.hasPrevious();) {
            ((UndoableEdit)li.previous()).die();
        }
    }

}
