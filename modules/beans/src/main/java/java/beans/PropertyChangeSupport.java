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

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.13.2.4 $
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

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.13.2.4 $
 */

public class PropertyChangeSupport implements Serializable {
    
    private static final long serialVersionUID = 6401253773779951803l; 
    
    private transient Object sourceBean;
    private transient ArrayList allPropertiesChangeListeners = new ArrayList();
    private transient HashMap selectedPropertiesChangeListeners = new HashMap();

    // fields for serialization compatibility
    private Hashtable children;
    private Object source;
    private int propertyChangeSupportSerializedDataVersion = 1; 

    /**
     * @com.intel.drl.spec_ref
     */
    public PropertyChangeSupport(Object sourceBean) {
        if (sourceBean == null) {
            throw new NullPointerException();
        }
        this.sourceBean = sourceBean;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void firePropertyChange(
            String propertyName, Object oldValue, Object newValue) {
        PropertyChangeEvent event = createPropertyChangeEvent(propertyName,
                oldValue, newValue);
        doFirePropertyChange(event);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public synchronized void removePropertyChangeListener(
            String propertyName, PropertyChangeListener listener) {
        if ( (propertyName != null) && (listener != null) ) {
            ArrayList listeners =
                (ArrayList) selectedPropertiesChangeListeners.get(propertyName);
            
            if(listeners != null) {
                listeners.remove(listener);    
            }
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public synchronized void addPropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        if (listener != null && propertyName != null) {
            ArrayList listeners = (ArrayList) selectedPropertiesChangeListeners
                    .get(propertyName);

            if (listeners == null) {
                listeners = new ArrayList();
                selectedPropertiesChangeListeners.put(propertyName, listeners);
            }

            listeners.add(listener);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public synchronized PropertyChangeListener[] getPropertyChangeListeners(
            String propertyName) {
        ArrayList listeners = null;
        
        if(propertyName != null) {
            listeners = (ArrayList) selectedPropertiesChangeListeners.get(
                    propertyName);
        }
        
        return (listeners == null) ? new PropertyChangeListener[] {} :
            getAsPropertyChangeListenerArray(listeners);
        
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void firePropertyChange(
            String propertyName, boolean oldValue, boolean newValue) {
        PropertyChangeEvent event = createPropertyChangeEvent(propertyName,
                oldValue, newValue);
        doFirePropertyChange(event);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void firePropertyChange(
            String propertyName, int oldValue, int newValue) {
        PropertyChangeEvent event = createPropertyChangeEvent(propertyName,
                oldValue, newValue);
        doFirePropertyChange(event);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public synchronized boolean hasListeners(String propertyName) {
        boolean result = allPropertiesChangeListeners.size() > 0;
        if (!result && propertyName != null) {
            ArrayList listeners =
                (ArrayList) selectedPropertiesChangeListeners.get(propertyName);
            if (listeners != null) {
                result = listeners.size() > 0;
            }
        }
        return result;    
    }

    /**
     * @com.intel.drl.spec_ref
     */
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

    /**
     * @com.intel.drl.spec_ref
     */
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

    /**
     * @com.intel.drl.spec_ref
     */
    public synchronized PropertyChangeListener[] getPropertyChangeListeners() {
        ArrayList result = new ArrayList(allPropertiesChangeListeners);
        
        Iterator keysIterator =
                selectedPropertiesChangeListeners.keySet().iterator();
        while (keysIterator.hasNext()) {
            String propertyName = (String) keysIterator.next();
            
            ArrayList selectedListeners =
                (ArrayList) selectedPropertiesChangeListeners.get(propertyName);
            if(selectedListeners != null) {

                Iterator i = selectedListeners.iterator();
                while(i.hasNext()) {
                    PropertyChangeListener listener =
                        (PropertyChangeListener) i.next();
                    result.add(new PropertyChangeListenerProxy(propertyName,
                            listener));
                }
                
            }
            
        }
        
        return getAsPropertyChangeListenerArray(result);
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        ArrayList allSerializedPropertiesChangeListeners = new ArrayList();
        Iterator i = allPropertiesChangeListeners.iterator();
        while(i.hasNext()) {
            PropertyChangeListener pcl = (PropertyChangeListener) i.next();
            if(pcl instanceof Serializable) {
                allSerializedPropertiesChangeListeners.add(pcl);
            }
        }
        
        HashMap selectedSerializedPropertiesChangeListeners = new HashMap();
        Iterator keyIterator =
            selectedPropertiesChangeListeners.keySet().iterator();
        while(keyIterator.hasNext()) {
            String propertyName = (String) keyIterator.next();
            ArrayList keyValues =
                (ArrayList) selectedPropertiesChangeListeners.get(propertyName);
            if(keyValues != null) {
                ArrayList serializedPropertiesChangeListeners = new ArrayList();
                
                Iterator j = keyValues.iterator();
                while(j.hasNext()) {
                    PropertyChangeListener pcl =
                        (PropertyChangeListener) j.next();
                    if(pcl instanceof Serializable) {
                        serializedPropertiesChangeListeners.add(pcl);
                    }
                }
                
                if(!serializedPropertiesChangeListeners.isEmpty()) {
                    selectedSerializedPropertiesChangeListeners.put(
                        propertyName, serializedPropertiesChangeListeners);
                }
                
            }
        }
        
        children = new Hashtable(selectedSerializedPropertiesChangeListeners);
        children.put("", allSerializedPropertiesChangeListeners);
        oos.writeObject(children);
        
        Object source = null;
        if(sourceBean instanceof Serializable) {
            source = sourceBean;
        }
        oos.writeObject(source);
        
        oos.writeInt(propertyChangeSupportSerializedDataVersion);
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    private void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        children = (Hashtable) ois.readObject();
        
        selectedPropertiesChangeListeners = new HashMap(children);
        allPropertiesChangeListeners =
            (ArrayList) selectedPropertiesChangeListeners.remove("");
        if(allPropertiesChangeListeners == null) {
            allPropertiesChangeListeners = new ArrayList();
        }
        
        sourceBean = ois.readObject();
        propertyChangeSupportSerializedDataVersion = ois.readInt();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void firePropertyChange(PropertyChangeEvent event) {
        doFirePropertyChange(event);
    }
    
    private PropertyChangeEvent createPropertyChangeEvent(
            String propertyName, Object oldValue, Object newValue) {
        return new PropertyChangeEvent(sourceBean, propertyName, oldValue,
                newValue);
    }
    
    private PropertyChangeEvent createPropertyChangeEvent(
            String propertyName, boolean oldValue, boolean newValue) {
        return new PropertyChangeEvent(sourceBean, propertyName,
                new Boolean(oldValue), new Boolean(newValue));
    }
    
    private PropertyChangeEvent createPropertyChangeEvent(
            String propertyName, int oldValue, int newValue) {
        return new PropertyChangeEvent(sourceBean, propertyName,
                new Integer(oldValue), new Integer(newValue));
    }
    
    private void doFirePropertyChange(PropertyChangeEvent event) {
        String propertyName = event.getPropertyName();
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        if (newValue != null && oldValue != null && newValue.equals(oldValue)) {
            return;
        }

        Iterator iterator = allPropertiesChangeListeners.iterator();
        while (iterator.hasNext()) {
            PropertyChangeListener listener =
                (PropertyChangeListener) iterator.next();
            listener.propertyChange(event);
        }
        ArrayList listeners = (ArrayList) selectedPropertiesChangeListeners.get(
                propertyName);
        if (listeners != null) {
            iterator = listeners.iterator();
            while (iterator.hasNext()) {
                PropertyChangeListener listener =
                    (PropertyChangeListener) iterator.next();
                listener.propertyChange(event);
            }
        }
    }

    private static PropertyChangeListener[] getAsPropertyChangeListenerArray(
            ArrayList listeners) {
        Object[] objects = listeners.toArray();
        PropertyChangeListener[] arrayResult =
                new PropertyChangeListener[objects.length];
        
        for(int i = 0; i < objects.length; ++i) {
            arrayResult[i] = (PropertyChangeListener) objects[i];
        }
        
        return arrayResult;
    }
}
