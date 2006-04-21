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
 * @version $Revision: 1.8.2.3 $
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
 * @version $Revision: 1.8.2.3 $
 */

public class VetoableChangeSupport implements Serializable {
    
    private static final long serialVersionUID = -5090210921595982017l;
    
    private transient Object sourceBean;
    private transient ArrayList allVetoableChangeListeners = new ArrayList();
    private transient HashMap selectedVetoableChangeListeners = new HashMap();
    
    // fields for serialization compatibility
    private Hashtable children;
    private Object source;
    private int vetoableChangeSupportSerializedDataVersion = 1; 

    /**
     * @com.intel.drl.spec_ref
     */
    public VetoableChangeSupport(Object sourceBean) {
        if (sourceBean == null) {
            throw new NullPointerException();
        }
        this.sourceBean = sourceBean;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void fireVetoableChange(
            String propertyName, Object oldValue, Object newValue) throws PropertyVetoException {
        PropertyChangeEvent event = createPropertyChangeEvent(propertyName,
                oldValue, newValue);
        doFirePropertyChange(event);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public synchronized void removeVetoableChangeListener(
            String propertyName, VetoableChangeListener listener) {
        if ( (propertyName != null) && (listener != null) ) {
            ArrayList listeners =
                (ArrayList) selectedVetoableChangeListeners.get(propertyName);
            
            if(listeners != null) {
                listeners.remove(listener);    
            }
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public synchronized void addVetoableChangeListener(
            String propertyName, VetoableChangeListener listener) {
        if(propertyName == null) {
            throw new NullPointerException("propertyName is null");
        } else if(listener != null) {
            ArrayList listeners =
                (ArrayList) selectedVetoableChangeListeners.get(propertyName);
            
            if (listeners == null) {
                listeners = new ArrayList();
                selectedVetoableChangeListeners.put(propertyName, listeners);
            }
            
            listeners.add(listener);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public synchronized VetoableChangeListener[] getVetoableChangeListeners(
            String propertyName) {
        ArrayList listeners = null;
        
        if (propertyName != null) {
            listeners = (ArrayList) selectedVetoableChangeListeners.get(
                    propertyName);
        }
        
        return (listeners == null) ? new VetoableChangeListener[] {} :
            getAsVetoableChangeListenerArray(listeners);
        
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void fireVetoableChange(
            String propertyName,
            boolean oldValue,
            boolean newValue) throws PropertyVetoException {
        PropertyChangeEvent event = createPropertyChangeEvent(
                propertyName, oldValue, newValue);
        doFirePropertyChange(event);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void fireVetoableChange(
            String propertyName,
            int oldValue,
            int newValue) throws PropertyVetoException {
        PropertyChangeEvent event = createPropertyChangeEvent(
                propertyName, oldValue, newValue);
        doFirePropertyChange(event);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public synchronized boolean hasListeners(String propertyName) {
        boolean result = allVetoableChangeListeners.size() > 0;
        if (!result && propertyName != null) {
            ArrayList listeners =
                (ArrayList) selectedVetoableChangeListeners.get(propertyName);
            if (listeners != null) {
                result = listeners.size() > 0;
            }
        }
        return result;    
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public synchronized void removeVetoableChangeListener(
            VetoableChangeListener listener) {
        if (listener != null) {
            Iterator iterator = allVetoableChangeListeners.iterator();
            while (iterator.hasNext()) {
                VetoableChangeListener pcl =
                        (VetoableChangeListener) iterator.next();
                if (pcl == listener) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public synchronized void addVetoableChangeListener(
            VetoableChangeListener listener) {
        if (listener != null) {
            allVetoableChangeListeners.add(listener);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public synchronized VetoableChangeListener[] getVetoableChangeListeners() {
        ArrayList result = new ArrayList(allVetoableChangeListeners);
        
        Iterator keysIterator =
                selectedVetoableChangeListeners.keySet().iterator();
        while (keysIterator.hasNext()) {
            String propertyName = (String) keysIterator.next();
            
            ArrayList selectedListeners =
                (ArrayList) selectedVetoableChangeListeners.get(propertyName);
            if(selectedListeners != null) {

                Iterator i = selectedListeners.iterator();
                while(i.hasNext()) {
                    VetoableChangeListener listener =
                            (VetoableChangeListener) i.next();
                    result.add(new VetoableChangeListenerProxy(
                            propertyName, listener));
                }
                
            }
            
        }
        
        return getAsVetoableChangeListenerArray(result);
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        ArrayList allSerializedVetoableChangeListeners = new ArrayList();
        Iterator i = allVetoableChangeListeners.iterator();
        while(i.hasNext()) {
            VetoableChangeListener vcl = (VetoableChangeListener) i.next();
            if(vcl instanceof Serializable) {
                allSerializedVetoableChangeListeners.add(vcl);
            }
        }
        
        HashMap selectedSerializedVetoableChangeListeners = new HashMap();
        Iterator keyIterator =
                selectedVetoableChangeListeners.keySet().iterator();
        while(keyIterator.hasNext()) {
            String propertyName = (String) keyIterator.next();
            ArrayList keyValues =
                (ArrayList) selectedVetoableChangeListeners.get(propertyName);
            if(keyValues != null) {
                ArrayList serializedVetoableChangeListeners = new ArrayList();
                
                Iterator j = keyValues.iterator();
                while(j.hasNext()) {
                    PropertyChangeListener pcl =
                            (PropertyChangeListener) j.next();
                    if(pcl instanceof Serializable) {
                        serializedVetoableChangeListeners.add(pcl);
                    }
                }
                
                if(!serializedVetoableChangeListeners.isEmpty()) {
                    selectedSerializedVetoableChangeListeners.put(
                        propertyName, serializedVetoableChangeListeners);
                }
                
            }
        }
        
        children = new Hashtable(selectedSerializedVetoableChangeListeners);
        children.put("", allSerializedVetoableChangeListeners);
        oos.writeObject(children);
        
        Object source = null;
        if(sourceBean instanceof Serializable) {
            source = sourceBean;
        }
        oos.writeObject(source);
        
        oos.writeInt(vetoableChangeSupportSerializedDataVersion);
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    private void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        allVetoableChangeListeners = (ArrayList) ois.readObject();
        selectedVetoableChangeListeners = (HashMap) ois.readObject();
        
        children = (Hashtable) ois.readObject();
        
        selectedVetoableChangeListeners = new HashMap(children);
        allVetoableChangeListeners =
                (ArrayList) selectedVetoableChangeListeners.remove("");
        if(allVetoableChangeListeners == null) {
            allVetoableChangeListeners = new ArrayList();
        }
        
        sourceBean = ois.readObject();
        vetoableChangeSupportSerializedDataVersion = ois.readInt();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void fireVetoableChange(PropertyChangeEvent event)
            throws PropertyVetoException {
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
    
    private void doFirePropertyChange(PropertyChangeEvent event)
            throws PropertyVetoException {
        String propertyName = event.getPropertyName();
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        if (newValue != null && oldValue != null && newValue.equals(oldValue)) {
            return;
        }

        Iterator iterator = allVetoableChangeListeners.iterator();
        while (iterator.hasNext()) {
            VetoableChangeListener listener =
                    (VetoableChangeListener) iterator.next();
            listener.vetoableChange(event);
        }
        
        ArrayList listeners = (ArrayList) selectedVetoableChangeListeners.get(
                propertyName);
        if (listeners != null) {
            iterator = listeners.iterator();
            while (iterator.hasNext()) {
                VetoableChangeListener listener =
                        (VetoableChangeListener) iterator.next();
                listener.vetoableChange(event);
            }
        }
    }
    
    private static VetoableChangeListener[] getAsVetoableChangeListenerArray(
            ArrayList listeners) {
        Object[] objects = listeners.toArray();
        VetoableChangeListener[] arrayResult =
                new VetoableChangeListener[objects.length];
        
        for(int i = 0; i < objects.length; ++i) {
            arrayResult[i] = (VetoableChangeListener) objects[i];
        }
        
        return arrayResult;
    }
}
