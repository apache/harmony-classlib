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

import java.io.Serializable;
import javax.swing.UIManager;

public class AbstractUndoableEdit implements UndoableEdit, Serializable {

    private static final long serialVersionUID = 580150227676302096L;

    protected static final String UndoName = "Undo";

    protected static final String RedoName = "Redo";

    private boolean alive;

    private boolean hasBeenDone;

    public AbstractUndoableEdit() {
        alive = true;
        hasBeenDone = true;
    }

    public boolean replaceEdit(final UndoableEdit anEdit) {
        return false;
    }

    public boolean addEdit(final UndoableEdit anEdit) {
        return false;
    }

    /*
     * The format of the string is based on 1.5 release behavior
     * which can be revealed using the following code:
     *
     *  Object obj = new AbstractUndoableEdit() {};
     *  System.out.println(obj.toString());
     */
    public String toString() {
        return super.toString() + " alive: " + alive
           +  " hasBeenDone: " + hasBeenDone;
    }

    public String getUndoPresentationName() {
        String presentationName = getPresentationName();
        String name = getUndoName();
        if (presentationName.length() == 0) {
            return name;
        }
        return name + " " + presentationName;
    }

    public String getRedoPresentationName() {
        String presentationName = getPresentationName();
        String name = getRedoName();
        if (presentationName.length() == 0) {
            return name;
        }
        return name + " " + presentationName;
    }

    public String getPresentationName() {
        return "";
    }

    public boolean isSignificant() {
        return true;
    }

    public boolean canUndo() {
        return alive && hasBeenDone;
    }

    public boolean canRedo() {
        return alive && !hasBeenDone;
    }

    public void undo() {
        if (!canUndo()) {
            throw new CannotUndoException();
        }

        hasBeenDone = false;
    }

    public void redo() {
        if (!canRedo()) {
            throw new CannotRedoException();
        }

        hasBeenDone = true;
    }

    public void die() {
        alive = false;
    }

    private String undoName;
    private String redoName;
    private String getUndoName() {
        if (undoName == null) {
            undoName = UIManager.getString("AbstractUndoableEdit.undoText");
        }
        return undoName;
    }

    private String getRedoName() {
        if (redoName == null) {
            redoName = UIManager.getString("AbstractUndoableEdit.redoText");
        }
        return redoName;
    }

}

