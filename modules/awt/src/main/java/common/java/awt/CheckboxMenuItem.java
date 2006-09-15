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
package java.awt;

import java.awt.event.*;
import java.util.*;
import javax.accessibility.*;

public class CheckboxMenuItem extends MenuItem implements ItemSelectable, Accessible {
    private static final long serialVersionUID = 6190621106981774043L;

    private final AWTListenerList itemListeners = new AWTListenerList(null);

    private boolean checked;

    protected class AccessibleAWTCheckboxMenuItem extends AccessibleAWTMenuItem
            implements AccessibleAction, AccessibleValue {
        private static final long serialVersionUID = -1122642964303476L;

        @Override
        public boolean doAccessibleAction(int i) {
            return false; //do nothing
        }

        @Override
        public AccessibleAction getAccessibleAction() {
            return this;
        }

        @Override
        public int getAccessibleActionCount() {
            return 0; // no accessible actions
        }

        @Override
        public String getAccessibleActionDescription(int i) {
            return null;
        }

        @Override
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.CHECK_BOX;
        }

        @Override
        public AccessibleValue getAccessibleValue() {
            return this;
        }

        @Override
        public Number getCurrentAccessibleValue() {
            return null;
        }

        @Override
        public Number getMaximumAccessibleValue() {
            return null;
        }

        @Override
        public Number getMinimumAccessibleValue() {
            return null;
        }

        @Override
        public boolean setCurrentAccessibleValue(Number n) {
            return false;
        }

    }

    public CheckboxMenuItem() throws HeadlessException {
        toolkit.lockAWT();
        try {
        } finally {
            toolkit.unlockAWT();
        }
    }

    public CheckboxMenuItem(String label) throws HeadlessException {
        this(label, false);
        toolkit.lockAWT();
        try {
        } finally {
            toolkit.unlockAWT();
        }
    }

    public CheckboxMenuItem(String label, boolean state) throws HeadlessException {
        super(label);
        toolkit.lockAWT();
        try {
            checked = state;
        } finally {
            toolkit.unlockAWT();
        }
    }

    public boolean getState() {
        toolkit.lockAWT();
        try {
            return checked;
        } finally {
            toolkit.unlockAWT();
        }
    }

    @Override
    public void addNotify() {
        toolkit.lockAWT();
        try {
            super.addNotify();
        } finally {
            toolkit.unlockAWT();
        }
    }

    @Override
    public AccessibleContext getAccessibleContext() {
        toolkit.lockAWT();
        try {
            return super.getAccessibleContext();
        } finally {
            toolkit.unlockAWT();
        }
    }

    @Override
    public String paramString() {
        /* The format of paramString is based on 1.5 release behavior 
         * which can be revealed using the following code:
         *
         * CheckboxMenuItem obj = new CheckboxMenuItem("Label", true);
         * System.out.println(obj.toString());
         */

        toolkit.lockAWT();
        try {
            return super.paramString() + (checked ? ",checked" : "");
        } finally {
            toolkit.unlockAWT();
        }
    }

    public Object[] getSelectedObjects() {
        toolkit.lockAWT();
        try {
            return checked ? new Object[] { getLabel() } : null;
        } finally {
            toolkit.unlockAWT();
        }
    }

    public void setState(boolean b) {
        toolkit.lockAWT();
        try {
            checked = b;
        } finally {
            toolkit.unlockAWT();
        }
    }

    @Override
    public <T extends EventListener> T[] getListeners(Class<T> listenerType) {
        if (ItemListener.class.isAssignableFrom(listenerType)) {
            return (T[]) getItemListeners();
        }
        return super.getListeners(listenerType);
    }

    public void addItemListener(ItemListener l) {
        itemListeners.addUserListener(l);
    }

    public void removeItemListener(ItemListener l) {
        itemListeners.removeUserListener(l);
    }

    public ItemListener[] getItemListeners() {
        return (ItemListener[]) itemListeners.getUserListeners(new ItemListener[0]);
    }

    @Override
    protected void processEvent(AWTEvent e) {
        if (toolkit.eventTypeLookup.getEventMask(e) == AWTEvent.ITEM_EVENT_MASK) {
            processItemEvent((ItemEvent) e);
        } else {
            super.processEvent(e);
        }
    }

    protected void processItemEvent(ItemEvent e) {
        for (Iterator i = itemListeners.getUserIterator(); i.hasNext();) {
            ItemListener listener = (ItemListener) i.next();

            switch (e.getID()) {
            case ItemEvent.ITEM_STATE_CHANGED:
                listener.itemStateChanged(e);
                break;
            }
        }
    }

    @Override
    AccessibleContext createAccessibleContext() {
        return new AccessibleAWTCheckboxMenuItem();
    }

    @Override
    void itemSelected(long when, int modifiers) {
        checked = !checked;

        super.itemSelected(when, modifiers);
    }

    @Override
    AWTEvent createEvent(long when, int modifiers) {
        int state = checked ? ItemEvent.SELECTED : ItemEvent.DESELECTED;
        return new ItemEvent(this, ItemEvent.ITEM_STATE_CHANGED, getLabel(), state);
    }

}
