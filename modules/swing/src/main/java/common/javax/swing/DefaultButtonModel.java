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
 * @author Alexander T. Simbirtsev
 * @version $Revision$
 */
package javax.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.Serializable;
import java.util.EventListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

public class DefaultButtonModel implements ButtonModel, Serializable {

    public static final int ARMED = 1;
    public static final int SELECTED = 2;
    public static final int PRESSED = 4;
    public static final int ENABLED = 8;
    public static final int ROLLOVER = 16;

    protected int stateMask = ENABLED;
    protected String actionCommand;
    protected ButtonGroup group;
    protected int mnemonic;
    protected transient ChangeEvent changeEvent;
    protected EventListenerList listenerList = new EventListenerList();


    public EventListener[] getListeners(final Class listenersClass) {
        return listenerList.getListeners(listenersClass);
    }

    public void addChangeListener(final ChangeListener listener) {
        listenerList.add(ChangeListener.class, listener);
    }

    public void removeChangeListener(final ChangeListener listener) {
        listenerList.remove(ChangeListener.class, listener);
    }

    public ChangeListener[] getChangeListeners() {
        return (ChangeListener[])listenerList.getListeners(ChangeListener.class);
    }

    public void addItemListener(final ItemListener listener) {
        listenerList.add(ItemListener.class, listener);
    }

    public void removeItemListener(final ItemListener listener) {
        listenerList.remove(ItemListener.class, listener);
    }

    public ItemListener[] getItemListeners() {
        return (ItemListener[])listenerList.getListeners(ItemListener.class);
    }

    public void addActionListener(final ActionListener listener) {
        listenerList.add(ActionListener.class, listener);
    }

    public void removeActionListener(final ActionListener listener) {
        listenerList.remove(ActionListener.class, listener);
    }

    public ActionListener[] getActionListeners() {
        return (ActionListener[])listenerList.getListeners(ActionListener.class);
    }

    public void setGroup(final ButtonGroup group) {
        this.group = group;
    }

    public ButtonGroup getGroup() {
        return group;
    }

    public void setActionCommand(final String command) {
        actionCommand = command;
    }

    public String getActionCommand() {
        return actionCommand;
    }

    public Object[] getSelectedObjects() {
        return null;
    }

    public void setSelected(final boolean selected) {
        if (isSelected() != selected) {
            toggleState(SELECTED);
            int state = selected ? ItemEvent.SELECTED : ItemEvent.DESELECTED;
            ItemEvent event = new ItemEvent(this, ItemEvent.ITEM_STATE_CHANGED,
                                            this, state);
            fireItemStateChanged(event);
        }
    }

    public boolean isSelected() {
        return isStateSet(SELECTED);
    }

    public void setRollover(final boolean rollover) {
        if (isEnabled() && isRollover() != rollover) {
            toggleState(ROLLOVER);
        }
    }

    public boolean isRollover() {
        return isStateSet(ROLLOVER);
    }

    public void setPressed(final boolean pressed) {
        if (isEnabled() && isPressed() != pressed) {
            toggleState(PRESSED);
            if (!pressed && isArmed()) {
                fireActionPerformed(new ActionEvent(this,
                        ActionEvent.ACTION_PERFORMED, actionCommand,
                        System.currentTimeMillis(), 0));
            }
        }
    }

    public boolean isPressed() {
        return isStateSet(PRESSED);
    }

    public void setEnabled(final boolean enabled) {
        if (isEnabled() != enabled) {
            stateMask = isSelected() ? SELECTED : 0;
            if (enabled) {
                stateMask |= ENABLED;
            }
            fireStateChanged();
        }
    }

    public boolean isEnabled() {
        return isStateSet(ENABLED);
    }

    public void setArmed(final boolean armed) {
        if (isEnabled() && isArmed() != armed) {
            toggleState(ARMED);
        }
    }

    public boolean isArmed() {
        return isStateSet(ARMED);
    }

    public void setMnemonic(final int mnemonic) {
        if (this.mnemonic != mnemonic) {
            this.mnemonic = mnemonic;
            fireStateChanged();
        }
    }

    public int getMnemonic() {
        return mnemonic;
    }

    protected void fireStateChanged() {
        ChangeListener[] listeners = getChangeListeners();
        if (listeners.length == 0) {
            return;
        }
        if (changeEvent == null) {
            changeEvent = new ChangeEvent(this);
        }
        for (int i = 0; i < listeners.length; i++) {
            listeners[i].stateChanged(changeEvent);
        }
    }

    protected void fireItemStateChanged(final ItemEvent event) {
        ItemListener[] listeners = getItemListeners();
        for (int i = 0; i < listeners.length; i++) {
            listeners[i].itemStateChanged(event);
        }
    }

    protected void fireActionPerformed(final ActionEvent event) {
        ActionListener[] listeners = getActionListeners();
        for (int i = 0; i < listeners.length; i++) {
            listeners[i].actionPerformed(event);
        }
    }

    private void toggleState(final int stateFlag) {
        stateMask ^= stateFlag;
        fireStateChanged();
    }

    private boolean isStateSet(final int stateFlag) {
        return (stateMask & stateFlag) != 0;
    }
}