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
 * @author Sergey Burlak
 * @version $Revision$
 */

package javax.swing;

import java.util.EventListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

public abstract class AbstractSpinnerModel implements SpinnerModel {
    protected EventListenerList listenerList = new EventListenerList();
    private ChangeEvent changeEvent;

    public void addChangeListener(final ChangeListener l) {
        listenerList.add(ChangeListener.class, l);
    }

    public void removeChangeListener(final ChangeListener l) {
        listenerList.remove(ChangeListener.class, l);
    }

    public ChangeListener[] getChangeListeners() {
        return (ChangeListener[])listenerList.getListeners(ChangeListener.class);
    }

    protected void fireStateChanged() {
        ChangeListener[] list = getChangeListeners();
        if (list.length == 0) {
            return;
        }
        if (changeEvent == null) {
            changeEvent = new ChangeEvent(this);
        }
        for (int i = 0; i < list.length; i++) {
            list[i].stateChanged(changeEvent);
        }
    }

    public EventListener[] getListeners(final Class listenerType) {
        return listenerList.getListeners(listenerType);
    }
}
