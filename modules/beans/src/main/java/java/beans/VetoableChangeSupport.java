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

public class VetoableChangeSupport implements Serializable {

    private static final long serialVersionUID = -5090210921595982017l;

    private transient Object sourceBean;

    private transient List<VetoableChangeListener> allVetoableChangeListeners = new ArrayList<VetoableChangeListener>();

    private transient Map<String, List<VetoableChangeListener>> selectedVetoableChangeListeners = new HashMap<String, List<VetoableChangeListener>>();

    // fields for serialization compatibility
    private Hashtable<String, List<VetoableChangeListener>> children;

    private int vetoableChangeSupportSerializedDataVersion = 1;

    public VetoableChangeSupport(Object sourceBean) {
        if (sourceBean == null) {
            throw new NullPointerException();
        }
        this.sourceBean = sourceBean;
    }

    public void fireVetoableChange(String propertyName, Object oldValue,
            Object newValue) throws PropertyVetoException {
        PropertyChangeEvent event = createPropertyChangeEvent(propertyName,
                oldValue, newValue);
        doFirePropertyChange(event);
    }

    public synchronized void removeVetoableChangeListener(String propertyName,
            VetoableChangeListener listener) {
        if ((propertyName != null) && (listener != null)) {
            List<VetoableChangeListener> listeners = selectedVetoableChangeListeners
                    .get(propertyName);

            if (listeners != null) {
                listeners.remove(listener);
            }
        }
    }

    public synchronized void addVetoableChangeListener(String propertyName,
            VetoableChangeListener listener) {
        if (propertyName != null && listener != null) {
            List<VetoableChangeListener> listeners = selectedVetoableChangeListeners
                    .get(propertyName);

            if (listeners == null) {
                listeners = new ArrayList<VetoableChangeListener>();
                selectedVetoableChangeListeners.put(propertyName, listeners);
            }

            listeners.add(listener);
        }
    }

    public synchronized VetoableChangeListener[] getVetoableChangeListeners(
            String propertyName) {
        List<VetoableChangeListener> listeners = null;

        if (propertyName != null) {
            listeners = selectedVetoableChangeListeners.get(propertyName);
        }

        return (listeners == null) ? new VetoableChangeListener[] {}
                : getAsVetoableChangeListenerArray(listeners);

    }

    public void fireVetoableChange(String propertyName, boolean oldValue,
            boolean newValue) throws PropertyVetoException {
        PropertyChangeEvent event = createPropertyChangeEvent(propertyName,
                oldValue, newValue);
        doFirePropertyChange(event);
    }

    public void fireVetoableChange(String propertyName, int oldValue,
            int newValue) throws PropertyVetoException {
        PropertyChangeEvent event = createPropertyChangeEvent(propertyName,
                oldValue, newValue);
        doFirePropertyChange(event);
    }

    public synchronized boolean hasListeners(String propertyName) {
        boolean result = allVetoableChangeListeners.size() > 0;
        if (!result && propertyName != null) {
            List<VetoableChangeListener> listeners = selectedVetoableChangeListeners
                    .get(propertyName);
            if (listeners != null) {
                result = listeners.size() > 0;
            }
        }
        return result;
    }

    public synchronized void removeVetoableChangeListener(
            VetoableChangeListener listener) {
        if (listener != null) {
            Iterator<VetoableChangeListener> iterator = allVetoableChangeListeners
                    .iterator();
            while (iterator.hasNext()) {
                VetoableChangeListener pcl = iterator.next();
                if (pcl == listener) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    public synchronized void addVetoableChangeListener(
            VetoableChangeListener listener) {
        if (listener != null) {
            allVetoableChangeListeners.add(listener);
        }
    }

    public synchronized VetoableChangeListener[] getVetoableChangeListeners() {
        List<VetoableChangeListener> result = new ArrayList<VetoableChangeListener>(
                allVetoableChangeListeners);

        Iterator<String> keysIterator = selectedVetoableChangeListeners
                .keySet().iterator();
        while (keysIterator.hasNext()) {
            String propertyName = keysIterator.next();

            List<VetoableChangeListener> selectedListeners = selectedVetoableChangeListeners
                    .get(propertyName);
            if (selectedListeners != null) {
                for (VetoableChangeListener listener : selectedListeners) {
                    result.add(new VetoableChangeListenerProxy(propertyName,
                            listener));
                }

            }

        }

        return getAsVetoableChangeListenerArray(result);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        List<VetoableChangeListener> allSerializedVetoableChangeListeners = new ArrayList<VetoableChangeListener>();
        for (VetoableChangeListener vcl : allVetoableChangeListeners) {
            if (vcl instanceof Serializable) {
                allSerializedVetoableChangeListeners.add(vcl);
            }
        }

        Map<String, List<VetoableChangeListener>> selectedSerializedVetoableChangeListeners = new HashMap<String, List<VetoableChangeListener>>();
        for (String propertyName : selectedVetoableChangeListeners.keySet()) {
            List<VetoableChangeListener> keyValues = selectedVetoableChangeListeners
                    .get(propertyName);
            if (keyValues != null) {
                List<VetoableChangeListener> serializedVetoableChangeListeners = new ArrayList<VetoableChangeListener>();
                for (VetoableChangeListener pcl : keyValues) {
                    if (pcl instanceof Serializable) {
                        serializedVetoableChangeListeners.add(pcl);
                    }
                }

                if (!serializedVetoableChangeListeners.isEmpty()) {
                    selectedSerializedVetoableChangeListeners.put(propertyName,
                            serializedVetoableChangeListeners);
                }

            }
        }

        children = new Hashtable<String, List<VetoableChangeListener>>(
                selectedSerializedVetoableChangeListeners);
        children.put("", allSerializedVetoableChangeListeners); //$NON-NLS-1$
        oos.writeObject(children);

        Object source = null;
        if (sourceBean instanceof Serializable) {
            source = sourceBean;
        }
        oos.writeObject(source);

        oos.writeInt(vetoableChangeSupportSerializedDataVersion);
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream ois) throws IOException,
            ClassNotFoundException {

        children = (Hashtable<String, List<VetoableChangeListener>>) ois
                .readObject();

        selectedVetoableChangeListeners = new HashMap<String, List<VetoableChangeListener>>(
                children);
        allVetoableChangeListeners = selectedVetoableChangeListeners.remove(""); //$NON-NLS-1$
        if (allVetoableChangeListeners == null) {
            allVetoableChangeListeners = new ArrayList<VetoableChangeListener>();
        }

        sourceBean = ois.readObject();
        vetoableChangeSupportSerializedDataVersion = ois.readInt();
    }

    public void fireVetoableChange(PropertyChangeEvent event)
            throws PropertyVetoException {
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

    private void doFirePropertyChange(PropertyChangeEvent event)
            throws PropertyVetoException {
        String propName = event.getPropertyName();
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && oldValue != null && newValue.equals(oldValue)) {
            return;
        }

        /* Take note of who we are going to notify (and potentially un-notify) */

        VetoableChangeListener[] listensToAll; // Listeners to all property
        // change events
        VetoableChangeListener[] listensToOne = null; // Listens to a given
        // property change
        synchronized (this) {
            listensToAll = allVetoableChangeListeners
                    .toArray(new VetoableChangeListener[allVetoableChangeListeners
                            .size()]);

            List<VetoableChangeListener> listeners = selectedVetoableChangeListeners
                    .get(event.getPropertyName());
            if (listeners != null) {
                listensToOne = listeners
                        .toArray(new VetoableChangeListener[listeners.size()]);
            }
        }

        try {
            for (VetoableChangeListener listener : listensToAll) {
                listener.vetoableChange(event);
            }
            if (listensToOne != null) {
                for (VetoableChangeListener listener : listensToOne) {
                    listener.vetoableChange(event);
                }
            }
        } catch (PropertyVetoException pve) {
            // Tell them we have changed it back
            PropertyChangeEvent revertEvent = createPropertyChangeEvent(
                    propName, newValue, oldValue);
            for (VetoableChangeListener listener : listensToAll) {
                try {
                    listener.vetoableChange(revertEvent);
                } catch (PropertyVetoException ignored) {
                }
            }
            if (listensToOne != null) {
                for (VetoableChangeListener listener : listensToOne) {
                    try {
                        listener.vetoableChange(revertEvent);
                    } catch (PropertyVetoException ignored) {
                    }
                }
            }
            throw pve;
        }
    }

    private static VetoableChangeListener[] getAsVetoableChangeListenerArray(
            List<VetoableChangeListener> listeners) {
        Object[] objects = listeners.toArray();
        VetoableChangeListener[] arrayResult = new VetoableChangeListener[objects.length];

        for (int i = 0; i < objects.length; ++i) {
            arrayResult[i] = (VetoableChangeListener) objects[i];
        }

        return arrayResult;
    }
}
