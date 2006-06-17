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
 * @author Michael Danilov
 * @version $Revision$
 */
package java.awt.dnd;

import java.awt.event.KeyEvent;

public class DragSourceDragEvent extends DragSourceEvent {

    private static final long serialVersionUID = 481346297933902471L;
    private int userAction;
    private int targetAction;
    private int modifiers;
    private int modifiersEx;

    public DragSourceDragEvent(DragSourceContext dsc, int dropAction, int action, int modifiers) {
        super(dsc);

        initFields(dropAction, action, modifiers);
    }

    public DragSourceDragEvent(DragSourceContext dsc, int dropAction, int action,
            int modifiers, int x, int y)
    {
        super(dsc, x, y);

        initFields(dropAction, action, modifiers);
    }

    private void initFields(int dropAction, int action, int modifiers) {
        KeyEvent converter = new KeyEvent(null, 0, 0l, modifiers, 0, (char) 0);

        userAction = dropAction;
        targetAction = action;
        modifiers = converter.getModifiers();
        modifiersEx = converter.getModifiersEx();
    }

    public int getUserAction() {
        return userAction;
    }

    public int getTargetActions() {
        return targetAction;
    }

    public int getGestureModifiersEx() {
        return modifiersEx;
    }

    public int getGestureModifiers() {
        return modifiers;
    }

    public int getDropAction() {
        return (userAction & targetAction & getDragSourceContext().getSourceActions());
    }

}
