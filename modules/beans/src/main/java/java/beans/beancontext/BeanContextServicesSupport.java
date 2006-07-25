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
 * @version $Revision: 1.12.4.3 $
 */
package java.beans.beancontext;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.TooManyListenersException;

/**
 * @author Sergei A. Krivenko
 * @version $Revision: 1.12.4.3 $
 */

public class BeanContextServicesSupport extends BeanContextSupport implements
        BeanContextServices {

    private static final long serialVersionUID = -8494482757288719206L;

    /**
     * Nested class
     */
    protected class BCSSChild extends BeanContextSupport.BCSChild {

        private static final long serialVersionUID = -3263851306889194873L;

        /**
         * 
         */
        BCSSChild(Object child, Object peer) {
            super(child, peer);
        }
    }

    /**
     * Nested class
     */
    protected class BCSSProxyServiceProvider implements
            BeanContextServiceProvider, BeanContextServiceRevokedListener {

        /**
         * The peer of this object
         */
        private BeanContextChild child;

        /**
         * Initialize the peer
         *
         * @param child - The peer to initialize with
         */
        BCSSProxyServiceProvider(BeanContextChild child) {
            this.child = child;
        }

        /**
         *
         */
        public Iterator getCurrentServiceSelectors(BeanContextServices bcs,
                Class serviceClass) {

            return bcs.getCurrentServiceSelectors(serviceClass);
        }

        /**
         *
         */
        public Object getService(BeanContextServices bcs, Object requestor,
                Class serviceClass, Object serviceSelector) {

            try {
                BeanContextServiceRevokedListener l = this;
                return bcs.getService(this.child, requestor, serviceClass,
                        serviceSelector, l);
            } catch (TooManyListenersException e) {
                return null;
            }
        }

        /**
         *
         */
        public void releaseService(BeanContextServices bcs, Object requestor,
                Object service) {

            bcs.releaseService(this.child, requestor, service);
        }

        /**
         *
         */
        public void serviceRevoked(BeanContextServiceRevokedEvent bcsre) {

            if (this.child instanceof BeanContextServices) {
                BeanContextServices ser = (BeanContextServices) this.child;
                ser.serviceRevoked(bcsre);
            }
        }
    }

    /**
     * Nested class
     */
    protected static class BCSSServiceProvider implements Serializable {

        private static final long serialVersionUID = 861278251667444782L;

        /**
         * @serial
         */
        protected BeanContextServiceProvider serviceProvider;

        /**
         * Service class
         */
        private transient Class sc;

        /**
         * Constructor for setting BeanContextServiceProvider in this class
         *
         * @param sc - service class
         * @param serviceProvider - service provider
         */
        BCSSServiceProvider(Class sc, BeanContextServiceProvider serviceProvider) {
            this.sc = sc;
            this.serviceProvider = serviceProvider;
        }

        /**
         *
         */
        protected BeanContextServiceProvider getServiceProvider() {
            return this.serviceProvider;
        }

        Class getServiceClass() {
            return this.sc;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected transient ArrayList<BeanContextServicesListener> bcsListeners;

    /**
     * @com.intel.drl.spec_ref
     */
    protected transient BCSSProxyServiceProvider proxy;

    /**
     * @com.intel.drl.spec_ref
     */
    protected transient int serializable;

    /**
     * @com.intel.drl.spec_ref
     */
    protected transient HashMap<Class, BCSSServiceProvider> services;

    /**
     * @com.intel.drl.spec_ref
     */
    public BeanContextServicesSupport() {
        super();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BeanContextServicesSupport(BeanContextServices peer) {
        super(peer);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BeanContextServicesSupport(BeanContextServices peer, Locale lcle) {
        super(peer, lcle);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BeanContextServicesSupport(BeanContextServices peer, Locale lcle,
            boolean dtime) {

        super(peer, lcle, dtime);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BeanContextServicesSupport(BeanContextServices peer, Locale lcle,
            boolean dtime, boolean visible) {

        super(peer, lcle, dtime, visible);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void addBeanContextServicesListener(BeanContextServicesListener bcsl) {

        if (bcsl == null) {
            throw new NullPointerException("The listener is null");
        }

        synchronized (this.bcsListeners) {
            this.bcsListeners.add(bcsl);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean addService(Class serviceClass,
            BeanContextServiceProvider bcsp) {

        return addService(serviceClass, bcsp, true);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected boolean addService(Class serviceClass,
            BeanContextServiceProvider bcsp, boolean fireEvent) {

        if (bcsp == null) {
            throw new NullPointerException("The provider is null");
        }

        synchronized (BeanContext.globalHierarchyLock) {
            synchronized (this.services) {
                if (!this.services.containsKey(serviceClass)) {
                    this.services.put(serviceClass, createBCSSServiceProvider(
                            serviceClass, bcsp));

                    if (getChildSerializable(bcsp) != null) {
                        this.serializable++;
                    }
                } else {
                    fireEvent = false;
                }
            }

            BeanContextServiceAvailableEvent ev = getEvent(serviceClass);

            if (fireEvent) {
                fireServiceAdded(ev);
            }

            for (Iterator it = iterator(); it.hasNext();) {
                Object child = it.next();

                if (child instanceof BeanContextServices) {
                    ((BeanContextServices) child).serviceAvailable(ev);
                }
            }

            return fireEvent;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected synchronized void bcsPreDeserializationHook(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {

        synchronized (this.services) {
            for (int i = 0; i < this.serializable; i++) {
                BCSSServiceProvider provider = (BCSSServiceProvider) ois
                        .readObject();
                this.services.put(provider.getClass(), provider);
            }
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected synchronized void bcsPreSerializationHook(ObjectOutputStream oos)
            throws IOException {

        synchronized (this.services) {
            for (Iterator it = this.services.keySet().iterator(); it.hasNext();) {
                Object key = it.next();

                if (BeanContextSupport.getChildSerializable(key) != null) {
                    oos.writeObject(this.services.get(key));
                }
            }
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected void childJustRemovedHook(Object child,
            BeanContextSupport.BCSChild bcsc) {

        return;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected BeanContextSupport.BCSChild createBCSChild(Object targetChild,
            Object peer) {

        return new BCSSChild(targetChild, peer);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected BCSSServiceProvider createBCSSServiceProvider(Class sc,
            BeanContextServiceProvider bcsp) {

        return new BCSSServiceProvider(sc, bcsp);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected final void fireServiceAdded(
            BeanContextServiceAvailableEvent bcssae) {

        for (Iterator<BeanContextServicesListener> it = this.bcsListeners
                .iterator(); it.hasNext();) {
            BeanContextServicesListener l = it.next();

            l.serviceAvailable(bcssae);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected final void fireServiceAdded(Class serviceClass) {
        fireServiceAdded(getEvent(serviceClass));
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected final void fireServiceRevoked(BeanContextServiceRevokedEvent bcsre) {

        for (Iterator<BeanContextServicesListener> it = this.bcsListeners
                .iterator(); it.hasNext();) {
            BeanContextServicesListener l = it.next();

            l.serviceRevoked(bcsre);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected final void fireServiceRevoked(Class serviceClass,
            boolean revokeNow) {

        fireServiceRevoked(new BeanContextServiceRevokedEvent(
                getBeanContextServicesPeer(), serviceClass, revokeNow));
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BeanContextServices getBeanContextServicesPeer() {
        return (BeanContextServices) getBeanContextPeer();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected static final BeanContextServicesListener getChildBeanContextServicesListener(
            Object child) {

        if (child instanceof BeanContextServicesListener) {
            return (BeanContextServicesListener) child;
        } else {
            return null;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Iterator getCurrentServiceClasses() {

        synchronized (BeanContext.globalHierarchyLock) {
            synchronized (this.services) {
                return this.services.keySet().iterator();
            }
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Iterator getCurrentServiceSelectors(Class serviceClass) {

        synchronized (BeanContext.globalHierarchyLock) {
            synchronized (this.services) {
                BCSSServiceProvider bcsp = this.services.get(serviceClass);

                return bcsp.getServiceProvider().getCurrentServiceSelectors(
                        getBeanContextServicesPeer(), serviceClass);
            }
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    private BeanContextServiceAvailableEvent getEvent(Class serviceClass) {
        return new BeanContextServiceAvailableEvent(
                getBeanContextServicesPeer(), serviceClass);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Object getService(BeanContextChild child, Object requestor,
            Class serviceClass, Object serviceSelector,
            BeanContextServiceRevokedListener bcsrl)
            throws TooManyListenersException {

        if (child == null) {
            throw new NullPointerException("The child is null");
        }

        if (requestor == null) {
            throw new NullPointerException("The requestor is null");
        }

        if (serviceClass == null) {
            throw new NullPointerException("The service class is null");
        }

        if (serviceSelector == null) {
            throw new NullPointerException("The service selector is null");
        }

        if (bcsrl == null) {
            throw new NullPointerException("The listener is null");
        }

        synchronized (BeanContext.globalHierarchyLock) {
            Object service = null;

            synchronized (this.services) {
                BCSSServiceProvider bcsp = this.services.get(serviceClass);

                if (bcsp != null) {
                    service = bcsp.getServiceProvider().getService(
                            getBeanContextServicesPeer(), requestor,
                            serviceClass, serviceSelector);
                }

                if ((service == null) && this.proxy != null) {
                    service = this.proxy.getService(
                            getBeanContextServicesPeer(), requestor,
                            serviceClass, serviceSelector);
                }
            }

            return service;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public synchronized boolean hasService(Class serviceClass) {
        if (serviceClass == null) {
            throw new NullPointerException("The service class is null");
        }

        synchronized (BeanContext.globalHierarchyLock) {
            synchronized (this.services) {
                return this.services.containsKey(serviceClass);
            }
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void initialize() {
        super.initialize();

        this.services = new HashMap<Class, BCSSServiceProvider>();
        this.bcsListeners = new ArrayList<BeanContextServicesListener>();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected synchronized void initializeBeanContextResources() {
        super.initializeBeanContextResources();
        this.proxy = new BCSSProxyServiceProvider(getBeanContextServicesPeer());
    }

    /**
     * @com.intel.drl.spec_ref
     */
    private void readObject(ObjectInputStream ois) throws IOException,
            ClassNotFoundException {

        synchronized (BeanContext.globalHierarchyLock) {
            ois.defaultReadObject();
            initialize();
            bcsPreDeserializationHook(ois);
            this.serializable = ois.readInt();

            synchronized (this.services) {
                for (int i = 0; i < this.serializable; i++) {
                    BCSSServiceProvider prov = (BCSSServiceProvider) ois
                            .readObject();

                    this.services.put(prov.getServiceClass(), prov);
                }
            }

            deserialize(ois, this.bcsListeners);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected synchronized void releaseBeanContextResources() {
        super.releaseBeanContextResources();
        this.proxy = null;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void releaseService(BeanContextChild child, Object requestor,
            Object service) {

        if (child == null) {
            throw new NullPointerException("The child is null");
        }

        if (requestor == null) {
            throw new NullPointerException("The requestor is null");
        }

        if (service == null) {
            throw new NullPointerException("The service is null");
        }

        synchronized (BeanContext.globalHierarchyLock) {
            if (this.proxy != null) {
                this.proxy.releaseService(getBeanContextServicesPeer(),
                        requestor, service);
            }
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void removeBeanContextServicesListener(
            BeanContextServicesListener bcsl) {

        if (bcsl == null) {
            throw new NullPointerException("The listener is null");
        }

        synchronized (this.bcsListeners) {
            this.bcsListeners.remove(bcsl);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void revokeService(Class serviceClass,
            BeanContextServiceProvider bcsp, boolean revokeCurrentServicesNow) {

        if (serviceClass == null) {
            throw new NullPointerException("The service class is null");
        }

        if (bcsp == null) {
            throw new NullPointerException("The provider is null");
        }

        synchronized (BeanContext.globalHierarchyLock) {
            synchronized (this.services) {
                Object removed = this.services.remove(serviceClass);

                if (removed != null) {
                    if (getChildSerializable(bcsp) != null) {
                        this.serializable--;
                    }
                }
            }

            BeanContextServiceRevokedEvent ev = new BeanContextServiceRevokedEvent(
                    getBeanContextServicesPeer(), serviceClass,
                    revokeCurrentServicesNow);

            fireServiceRevoked(ev);

            for (Iterator it = iterator(); it.hasNext();) {
                Object child = it.next();

                if (child instanceof BeanContextServices) {
                    ((BeanContextServices) child).serviceRevoked(ev);
                }
            }
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void serviceAvailable(BeanContextServiceAvailableEvent bcssae) {
        if (bcssae == null) {
            throw new NullPointerException("The event is null");
        }

        for (Iterator<BeanContextServicesListener> it = this.bcsListeners
                .iterator(); it.hasNext();) {
            BeanContextServicesListener l = it.next();

            l.serviceAvailable(bcssae);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void serviceRevoked(BeanContextServiceRevokedEvent bcssre) {
        if (bcssre == null) {
            throw new NullPointerException("The event is null");
        }

        for (Iterator<BeanContextServicesListener> it = this.bcsListeners
                .iterator(); it.hasNext();) {
            BeanContextServicesListener l = it.next();

            l.serviceRevoked(bcssre);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {

        synchronized (BeanContext.globalHierarchyLock) {
            oos.defaultWriteObject();
            bcsPreSerializationHook(oos);
            oos.writeInt(this.serializable);

            synchronized (this.services) {
                for (Iterator it = this.getCurrentServiceClasses(); it
                        .hasNext();) {
                    BCSSServiceProvider prov = (BCSSServiceProvider) it.next();

                    if (prov.getServiceProvider() instanceof Serializable) {
                        oos.writeObject(prov);
                    }
                }
            }

            serialize(oos, this.bcsListeners);
        }
    }
}
