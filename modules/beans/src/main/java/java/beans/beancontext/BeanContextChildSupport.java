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
 * @author Sergei A. Krivenko
 * @version $Revision: 1.7.4.3 $
 */
package java.beans.beancontext;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Sergei A. Krivenko
 * @version $Revision: 1.7.4.3 $
 */

public class BeanContextChildSupport 
        implements BeanContextChild, BeanContextServicesListener, Serializable {

    private static final long serialVersionUID = 6328947014421475877L;
    static final String BEAN_CONTEXT = "beanContext";
    
    /**
     * 
     */
    protected transient BeanContext beanContext;

    /**
     * @serial
     */
    public BeanContextChild beanContextChildPeer;

    /**
     * @serial
     */
    protected PropertyChangeSupport pcSupport;
    
    /**
     * 
     */
    protected transient boolean rejectedSetBCOnce;

    /**
     * @serial
     */
    protected VetoableChangeSupport vcSupport;
    
    /**
     * 
     */
    public BeanContextChildSupport() {
        
        // This class implements the JavaBean component itself
        this(null);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BeanContextChildSupport(BeanContextChild bcc) {

        // If 'bcc' parameter is not null the JavaBean component itself 
        // implements BeanContextChild
        this.beanContextChildPeer = (bcc == null ? this : bcc);
        
        // Initialize necessary fileds for later use
        pcSupport = new PropertyChangeSupport(this.beanContextChildPeer);
        vcSupport = new VetoableChangeSupport(this.beanContextChildPeer);
        this.rejectedSetBCOnce = false;
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public void addPropertyChangeListener(String name, 
            PropertyChangeListener pcl) {
                
        // Do nothing if name or listener is null
        if ((name == null) || (pcl == null)) {
            return;
        }
        
        this.pcSupport.addPropertyChangeListener(name, pcl);
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public void addVetoableChangeListener(String name, 
            VetoableChangeListener vcl) {
                
        // Do nothing if name or listener is null
        if ((name == null) || (vcl == null)) {
            return;
        }
        
        this.vcSupport.addVetoableChangeListener(name, vcl);
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public void firePropertyChange(String name, Object oldValue, 
            Object newValue) {
                
        this.pcSupport.firePropertyChange(name, oldValue, newValue);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void fireVetoableChange(String name, Object oldValue, 
            Object newValue) throws PropertyVetoException {
                
        this.vcSupport.fireVetoableChange(name, oldValue, newValue);
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public synchronized BeanContext getBeanContext() {
        return this.beanContext;
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public BeanContextChild getBeanContextChildPeer() {
        return this.beanContextChildPeer;
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    protected void initializeBeanContextResources() {}
    
    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isDelegated() {
        return (!this.beanContextChildPeer.equals(this));
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    private void readObject(ObjectInputStream ois) 
            throws IOException, ClassNotFoundException {
        
        ois.defaultReadObject();
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    protected void releaseBeanContextResources() {}
    
    /**
     * @com.intel.drl.spec_ref
     */
    public void removePropertyChangeListener(String name, 
            PropertyChangeListener pcl) {
                
        this.pcSupport.removePropertyChangeListener(name, pcl);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void removeVetoableChangeListener(String name, 
            VetoableChangeListener vcl) {
                
        this.vcSupport.removeVetoableChangeListener(name, vcl);
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public void serviceAvailable(BeanContextServiceAvailableEvent bcsae) {
        if (isDelegated()) {
            ((BeanContextServicesListener) beanContextChildPeer)
                    .serviceAvailable(bcsae);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void serviceRevoked(BeanContextServiceRevokedEvent bcsre) {
        if (isDelegated()) {
            ((BeanContextServicesListener) beanContextChildPeer)
                    .serviceRevoked(bcsre);
        }
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public synchronized void setBeanContext(BeanContext bc) 
            throws PropertyVetoException {
                
        // Do nothing if the old and new values are uqual
        if ((this.beanContext == null) && (bc == null)) {
            return;
        }
        
        if ((this.beanContext != null) && this.beanContext.equals(bc)) {
            return;
        }
        
        releaseBeanContextResources();
        
        // Children are not allowed to repeatedly veto this operation.
        // So, we set rejectedSetBCOnce flag to true if veto occurs
        // and never veto the change again
        if (!this.rejectedSetBCOnce) {
        
            // Validate the new BeanContext value and throw
            // PropertyVetoException if it was not successful
            if (!validatePendingSetBeanContext(bc)) {
                this.rejectedSetBCOnce = true;                
                fireVetoableChange(BEAN_CONTEXT, this.beanContext, bc);
                
                throw new PropertyVetoException(
                    "The new BeanContext can not be set", 
                    new PropertyChangeEvent(this.beanContextChildPeer, 
                                            BEAN_CONTEXT, 
                                            this.beanContext, 
                                            bc));
            }
            else {
                this.rejectedSetBCOnce = false;
            }
            
            // We have to notify all listeners about "beanContext"
            // property change
            firePropertyChange(BEAN_CONTEXT, this.beanContext, bc); 
            this.beanContext = bc;
            initializeBeanContextResources();
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean validatePendingSetBeanContext(BeanContext newValue) {
        return true;
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }
}
