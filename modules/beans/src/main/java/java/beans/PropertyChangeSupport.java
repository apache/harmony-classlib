/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
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

package java.beans;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PropertyChangeSupport implements Serializable {

    private static final long serialVersionUID = 6401253773779951803l;

    private transient Object sourceBean;

    private transient List<PropertyChangeListener> allPropertiesChangeListeners = new ArrayList<PropertyChangeListener>();

    private transient Map<String, List<PropertyChangeListener>> selectedPropertiesChangeListeners = new HashMap<String, List<PropertyChangeListener>>();

    // fields for serialization compatibility
    private Hashtable<String, List<PropertyChangeListener>> children;

    private Object source;

    private int propertyChangeSupportSerializedDataVersion = 1;

    public PropertyChangeSupport(Object sourceBean) {
        if (sourceBean == null) {
            throw new NullPointerException();
        }
        this.sourceBean = sourceBean;
    }

    public void firePropertyChange(String propertyName, Object oldValue,
            Object newValue) {
        PropertyChangeEvent event = createPropertyChangeEvent(propertyName,
                oldValue, newValue);
        doFirePropertyChange(event);
    }

    public void fireIndexedPropertyChange(String propertyName, int index,
            Object oldValue, Object newValue) {

        // nulls and equals check done in doFire...
        doFirePropertyChange(new IndexedPropertyChangeEvent(source,
                propertyName, oldValue, newValue, index));
    }

    public synchronized void removePropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        if ((propertyName != null) && (listener != null)) {
            List<PropertyChangeListener> listeners = selectedPropertiesChangeListeners
                    .get(propertyName);

            if (listeners != null) {
                listeners.remove(listener);
            }
        }
    }

    public synchronized void addPropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        if ((listener != null) && (propertyName != null)) {
            List<PropertyChangeListener> listeners = selectedPropertiesChangeListeners
                    .get(propertyName);

            if (listeners == null) {
                listeners = new ArrayList<PropertyChangeListener>();
                selectedPropertiesChangeListeners.put(propertyName, listeners);
            }

            listeners.add(listener);
        }
    }

    public synchronized PropertyChangeListener[] getPropertyChangeListeners(
            String propertyName) {
        List<PropertyChangeListener> listeners = null;

        if (propertyName != null) {
            listeners = selectedPropertiesChangeListeners.get(propertyName);
        }

        return (listeners == null) ? new PropertyChangeListener[] {}
                : getAsPropertyChangeListenerArray(listeners);
    }

    public void firePropertyChange(String propertyName, boolean oldValue,
            boolean newValue) {
        PropertyChangeEvent event = createPropertyChangeEvent(propertyName,
                oldValue, newValue);
        doFirePropertyChange(event);
    }

    public void fireIndexedPropertyChange(String propertyName, int index,
            boolean oldValue, boolean newValue) {

        if (oldValue != newValue) {
            fireIndexedPropertyChange(propertyName, index, Boolean
                    .valueOf(oldValue), Boolean.valueOf(newValue));
        }
    }

    public void firePropertyChange(String propertyName, int oldValue,
            int newValue) {
        PropertyChangeEvent event = createPropertyChangeEvent(propertyName,
                oldValue, newValue);
        doFirePropertyChange(event);
    }

    public void fireIndexedPropertyChange(String propertyName, int index,
            int oldValue, int newValue) {

        if (oldValue != newValue) {
            fireIndexedPropertyChange(propertyName, index,
                    new Integer(oldValue), new Integer(newValue));
        }
    }

    public synchronized boolean hasListeners(String propertyName) {
        boolean result = allPropertiesChangeListeners.size() > 0;
        if (!result && (propertyName != null)) {
            List<PropertyChangeListener> listeners = selectedPropertiesChangeListeners
                    .get(propertyName);
            if (listeners != null) {
                result = listeners.size() > 0;
            }
        }
        return result;
    }

    public synchronized void removePropertyChangeListener(
            PropertyChangeListener listener) {
        if (listener != null) {
            if (listener instanceof PropertyChangeListenerProxy) {
                String name = ((PropertyChangeListenerProxy) listener)
                        .getPropertyName();
                PropertyChangeListener lst = (PropertyChangeListener) ((PropertyChangeListenerProxy) listener)
                        .getListener();

                removePropertyChangeListener(name, lst);
            } else {
                allPropertiesChangeListeners.remove(listener);
            }
        }
    }

    public synchronized void addPropertyChangeListener(
            PropertyChangeListener listener) {
        if (listener != null) {
            if (listener instanceof PropertyChangeListenerProxy) {
                String name = ((PropertyChangeListenerProxy) listener)
                        .getPropertyName();
                PropertyChangeListener lst = (PropertyChangeListener) ((PropertyChangeListenerProxy) listener)
                        .getListener();
                addPropertyChangeListener(name, lst);
            } else {
                allPropertiesChangeListeners.add(listener);
            }
        }
    }

    public synchronized PropertyChangeListener[] getPropertyChangeListeners() {
        ArrayList<PropertyChangeListener> result = new ArrayList<PropertyChangeListener>(
                allPropertiesChangeListeners);

        Iterator<String> keysIterator = selectedPropertiesChangeListeners
                .keySet().iterator();
        while (keysIterator.hasNext()) {
            String propertyName = keysIterator.next();

            List<PropertyChangeListener> selectedListeners = selectedPropertiesChangeListeners
                    .get(propertyName);
            if (selectedListeners != null) {

                Iterator<PropertyChangeListener> i = selectedListeners
                        .iterator();
                while (i.hasNext()) {
                    PropertyChangeListener listener = i.next();
                    result.add(new PropertyChangeListenerProxy(propertyName,
                            listener));
                }
            }
        }

        return getAsPropertyChangeListenerArray(result);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        List<PropertyChangeListener> allSerializedPropertiesChangeListeners = new ArrayList<PropertyChangeListener>();
        Iterator<PropertyChangeListener> i = allPropertiesChangeListeners
                .iterator();
        while (i.hasNext()) {
            PropertyChangeListener pcl = i.next();
            if (pcl instanceof Serializable) {
                allSerializedPropertiesChangeListeners.add(pcl);
            }
        }

        Map<String, List<PropertyChangeListener>> selectedSerializedPropertiesChangeListeners = new HashMap<String, List<PropertyChangeListener>>();
        Iterator<String> keyIterator = selectedPropertiesChangeListeners
                .keySet().iterator();
        while (keyIterator.hasNext()) {
            String propertyName = keyIterator.next();
            List<PropertyChangeListener> keyValues = selectedPropertiesChangeListeners
                    .get(propertyName);
            if (keyValues != null) {
                List<PropertyChangeListener> serializedPropertiesChangeListeners = new ArrayList<PropertyChangeListener>();

                Iterator<PropertyChangeListener> j = keyValues.iterator();
                while (j.hasNext()) {
                    PropertyChangeListener pcl = j.next();
                    if (pcl instanceof Serializable) {
                        serializedPropertiesChangeListeners.add(pcl);
                    }
                }

                if (!serializedPropertiesChangeListeners.isEmpty()) {
                    selectedSerializedPropertiesChangeListeners.put(
                            propertyName, serializedPropertiesChangeListeners);
                }
            }
        }

        children = new Hashtable<String, List<PropertyChangeListener>>(
                selectedSerializedPropertiesChangeListeners);
        children.put("", allSerializedPropertiesChangeListeners); //$NON-NLS-1$
        oos.writeObject(children);

        Object source = null;
        if (sourceBean instanceof Serializable) {
            source = sourceBean;
        }
        oos.writeObject(source);

        oos.writeInt(propertyChangeSupportSerializedDataVersion);
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream ois) throws IOException,
            ClassNotFoundException {
        children = (Hashtable<String, List<PropertyChangeListener>>) ois
                .readObject();

        selectedPropertiesChangeListeners = new HashMap<String, List<PropertyChangeListener>>(
                children);
        allPropertiesChangeListeners = selectedPropertiesChangeListeners
                .remove(""); //$NON-NLS-1$
        if (allPropertiesChangeListeners == null) {
            allPropertiesChangeListeners = new ArrayList<PropertyChangeListener>();
        }

        sourceBean = ois.readObject();
        propertyChangeSupportSerializedDataVersion = ois.readInt();
    }

    public void firePropertyChange(PropertyChangeEvent event) {
        doFirePropertyChange(event);
    }

    private PropertyChangeEvent createPropertyChangeEvent(String propertyName,
            Object oldValue, Object newValue) {
        return new PropertyChangeEvent(sourceBean, propertyName, oldValue,
                newValue);
    }

    private PropertyChangeEvent createPropertyChangeEvent(String propertyName,
            boolean oldValue, boolean newValue) {
        return new PropertyChangeEvent(sourceBean, propertyName, new Boolean(
                oldValue), new Boolean(newValue));
    }

    private PropertyChangeEvent createPropertyChangeEvent(String propertyName,
            int oldValue, int newValue) {
        return new PropertyChangeEvent(sourceBean, propertyName, new Integer(
                oldValue), new Integer(newValue));
    }

    private void doFirePropertyChange(PropertyChangeEvent event) {
        String propertyName = event.getPropertyName();
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if ((newValue != null) && (oldValue != null)
                && newValue.equals(oldValue)) {
            return;
        }

        /*
         * Copy the listeners collections so they can be modified while we fire
         * events.
         */

        // Listeners to all property change events
        PropertyChangeListener[] listensToAll;
        // Listens to a given property change
        PropertyChangeListener[] listensToOne = null;
        synchronized (this) {
            listensToAll = allPropertiesChangeListeners
                    .toArray(new PropertyChangeListener[allPropertiesChangeListeners
                            .size()]);

            List<PropertyChangeListener> listeners = selectedPropertiesChangeListeners
                    .get(propertyName);
            if (listeners != null) {
                listensToOne = listeners
                        .toArray(new PropertyChangeListener[listeners.size()]);
            }
        }

        // Fire the listeners
        for (PropertyChangeListener listener : listensToAll) {
            listener.propertyChange(event);
        }
        if (listensToOne != null) {
            for (PropertyChangeListener listener : listensToOne) {
                listener.propertyChange(event);
            }
        }
    }

    private static PropertyChangeListener[] getAsPropertyChangeListenerArray(
            List<PropertyChangeListener> listeners) {
        Object[] objects = listeners.toArray();
        PropertyChangeListener[] arrayResult = new PropertyChangeListener[objects.length];

        for (int i = 0; i < objects.length; ++i) {
            arrayResult[i] = (PropertyChangeListener) objects[i];
        }

        return arrayResult;
    }
}
