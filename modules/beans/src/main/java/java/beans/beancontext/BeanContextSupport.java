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

package java.beans.beancontext;

import java.beans.Beans;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.Visibility;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import org.apache.harmony.beans.internal.nls.Messages;
@SuppressWarnings("unchecked")
public class BeanContextSupport extends BeanContextChildSupport implements
        BeanContext, Serializable, PropertyChangeListener,
        VetoableChangeListener {

    /**
     * Nested class
     */
    protected class BCSChild implements Serializable {

        private static final long serialVersionUID = -5815286101609939109L;

        /**
         * @serial
         */
        private Object child;

        /**
         * @serial
         */
        private Object proxyPeer;

        /**
         * Construct an object not initially locked for editing with child and
         * its peer
         * 
         * @param child -
         *            a child
         * @param proxyPeer -
         *            a peer for this child
         */
        BCSChild(Object child, Object proxyPeer) {
            this.child = child;
            this.proxyPeer = proxyPeer;
        }

        /**
         * Return a child
         * 
         * @return a child this object was created with
         */
        Object getChild() {
            return this.child;
        }
    }

    /**
     * Nested class
     */
    protected static final class BCSIterator implements Iterator {

        /**
         * 
         */
        private Iterator it;

        /**
         * Construct an iterator
         * 
         * @param set -
         *            a set to create iterator from
         */
        BCSIterator(HashMap map) {
            this.it = map.values().iterator();
        }

        public boolean hasNext() {
            return it.hasNext();
        }

        public Object next() {
            return it.next();
        }

        public void remove() {
            it.remove();
        }
    }

    private static final long serialVersionUID = -4879613978649577204L;

    protected transient ArrayList<BeanContextMembershipListener> bcmListeners;

    protected transient HashMap<Object, BCSChild> children;

    /**
     * @serial
     */
    protected boolean designTime;

    /**
     * @serial
     */
    protected Locale locale;

    /**
     * @serial
     */
    protected boolean okToUseGui;

    /**
     * @serial
     */
    private int serializable;

    /**
     * A flag to show if this BeanContext is in a process of serializing
     */
    private transient boolean serializing;

    /**
     * PropertyChangeListener of this BeanContext
     */
    private transient PropertyChangeListener pcl;

    /**
     * VetoableChangeListener of this BeanContext
     */
    private transient VetoableChangeListener vcl;

    /**
     * A flag to prevent the infinite roll backs while adding and removing
     * children
     */
    private boolean rollingBack = false;

    public BeanContextSupport() {
        this(null, null, false, true);
    }

    public BeanContextSupport(BeanContext peer) {
        this(peer, null, false, true);
    }

    public BeanContextSupport(BeanContext peer, Locale lcle) {
        this(peer, lcle, false, true);
    }

    public BeanContextSupport(BeanContext peer, Locale lcle, boolean dtime) {
        this(peer, lcle, dtime, true);
    }

    public BeanContextSupport(BeanContext peer, Locale lcle, boolean dtime,
            boolean visible) {

        super(peer);

        // If locale is null, use default
        this.locale = (lcle == null ? Locale.getDefault() : lcle);

        this.designTime = dtime;
        this.okToUseGui = visible;

        // Initialize some values necessary for this class to operate
        initialize();
    }

    public boolean add(Object targetChild) {

        // We must synchronize on BeanContext.globalHierarchyLock
        synchronized (BeanContext.globalHierarchyLock) {

            // Validate some values and states to check if we can proceed
            if (!addChecks(targetChild)) {
                return false;
            }

            try {

                // If child is an instance of BeanContextChild or
                // BeanContextProxy,
                // invoke setBeanContext() method on this child
                // and register BeanContext with its child
                // on PropertyChangeListener and VetoableChangeListener.
                BeanContextChild ch = getChildBeanContextChild(targetChild);

                if (ch != null) {
                    ch.setBeanContext(getBeanContextPeer());
                    ch.addPropertyChangeListener(BEAN_CONTEXT, this.pcl);
                    ch.addVetoableChangeListener(BEAN_CONTEXT, this.vcl);
                }
            } catch (PropertyVetoException e) {

                // Throw IllegalStateException, if PropertyVetoException occurs
                throw new IllegalStateException(Messages.getString(
                        "beans.30", targetChild, e.getMessage())); //$NON-NLS-1$
            }

            // If child implements Visibility,
            // set an appropriate type of ability to render GUI
            Visibility vis = getChildVisibility(targetChild);

            if (vis != null) {
                if (this.okToUseGui) {
                    vis.okToUseGui();
                } else {
                    vis.dontUseGui();
                }
            }

            // Check if this child implements Serializable and increase
            // the number of serializable children of the BeanContext
            if (getChildSerializable(targetChild) != null) {
                this.serializable++;
            }

            // Finally add child to the collection
            addChild(targetChild);
        }

        return true;
    }

    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    public void addBeanContextMembershipListener(
            BeanContextMembershipListener bcml) {

        // BeanContextMembershipListener can not be null
        if (bcml == null) {
            throw new NullPointerException(Messages.getString("beans.29")); //$NON-NLS-1$
        }

        synchronized (this.bcmListeners) {
            this.bcmListeners.add(bcml);
        }
    }

    /**
     * Check if we can add this child to BeanContext
     * 
     * @param targetChild -
     *            a child to check
     * 
     * @return true if we may continue
     */
    private boolean addChecks(Object targetChild) {

        // Child can not be null
        if (targetChild == null) {
            throw new IllegalArgumentException(Messages.getString("beans.2A")); //$NON-NLS-1$
        }

        // Each child should appear only once in a given BeanContext
        if (containsKey(targetChild)) {
            return false;
        }

        return validatePendingAdd(targetChild);
    }

    /**
     * Add child to the collection
     * 
     * @param targetChild -
     *            the child to be added to the BeanContext
     * @param fire -
     *            true if BeanContextMembershipEvent should be fired
     */
    private void addChild(Object targetChild) {

        // Add a new child using targetChild as a key and
        // its BCSChild instance as an entry
        synchronized (this.children) {
            BCSChild ch = createBCSChild(targetChild, getBeanContextPeer());
            this.children.put(targetChild, ch);
            childJustAddedHook(targetChild, ch);
        }

        // Fire membership event
        fireChildrenAdded(getBCME(new Object[] { targetChild }));
    }

    public boolean avoidingGui() {

        // Avoiding GUI means that
        // GUI is needed but not allowed to use at this time
        return (needsGui() && !this.okToUseGui);
    }

    protected Iterator bcsChildren() {

        // Return Iterator containing children
        // that are instances of BCSChild class
        synchronized (this.children) {
            return new BCSIterator(this.children);
        }
    }

    protected void bcsPreDeserializationHook(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {

        // Leave it for subclasses to implement
    }

    protected void bcsPreSerializationHook(ObjectOutputStream oos)
            throws IOException {

        // Leave it for subclasses to implement
    }

    protected void childDeserializedHook(Object child,
            BeanContextSupport.BCSChild bcsc) {

        synchronized (this.children) {
            this.children.put(child, bcsc);
        }
    }

    protected void childJustAddedHook(Object child,
            BeanContextSupport.BCSChild bcsc) {

        // Leave it for subclasses to implement
    }

    protected void childJustRemovedHook(Object child,
            BeanContextSupport.BCSChild bcsc) {

        // Leave it for subclasses to implement
    }

    protected static final boolean classEquals(Class first, Class second) {
        // Either classes themselves or their names should be equal
        return (first.equals(second) ? true : (first.getName().equals(
                second.getName()) ? true : false));
    }

    public void clear() {
        throw new UnsupportedOperationException();
        /*
         * synchronized(BeanContext.globalHierarchyLock) { Collection col = new
         * ArrayList(); // Remove all children from BeanContext that are in the
         * collection // one by one. This operation is successful if all the //
         * removals succeeded for (Iterator i = iterator(); i.hasNext(); ) { try {
         * Object next = i.next();
         * 
         * if (remove(next)) { col.add(next); } } catch(Exception e) { // Roll
         * back changes this.rollingBack = true; addAll(col); return; } } }
         */
    }

    public boolean contains(Object o) {
        // See if a given object can be found among
        // the children's collection values
        synchronized (BeanContext.globalHierarchyLock) {
            synchronized (this.children) {
                return this.children.containsKey(o);
            }
        }
    }

    public boolean containsAll(Collection c) {

        // Iterate through the collection provided and find matches
        // in the current BeanContext. If not found return false.
        for (Iterator i = c.iterator(); i.hasNext();) {
            Object next = i.next();

            if (!contains(next)) {
                return false;
            }
        }

        // If we are here, all the elements of the collection are presented
        // in this BeanContext
        return true;
    }

    public boolean containsKey(Object o) {
        // See if a given object can be found among
        // the children's collection keys
        synchronized (BeanContext.globalHierarchyLock) {
            synchronized (this.children) {
                return this.children.containsKey(o);
            }
        }
    }

    protected final Object[] copyChildren() {
        // Make a copy of all children
        synchronized (this.children) {
            return this.children.entrySet().toArray();
        }
    }

    protected BCSChild createBCSChild(Object targetChild, Object peer) {
        // Create a child object with a reference to its peer
        return new BCSChild(targetChild, peer);
    }

    protected final void deserialize(ObjectInputStream ois, Collection coll)
            throws IOException, ClassNotFoundException {
        // Read objects from output stream until "EOS" (the end of stream)
        // mark is found. Place all the objects into collection provided
        while (true) {
            Object l = ois.readObject();

            if (l != null && l.equals("EOS")) { //$NON-NLS-1$
                coll.add(l);
            } else {
                break;
            }
        }
    }

    public synchronized void dontUseGui() {
        // Command this BeanContext and its children not to use GUI
        this.okToUseGui = false;

        for (Iterator it = iterator(); it.hasNext();) {
            Object next = it.next();
            Visibility vis = getChildVisibility(next);

            if (vis != null) {
                vis.dontUseGui();
            }
        }
    }

    protected final void fireChildrenAdded(BeanContextMembershipEvent bcme) {
        for (BeanContextMembershipListener cur : bcmListeners) {
            cur.childrenAdded(bcme);
        }
    }

    protected final void fireChildrenRemoved(BeanContextMembershipEvent bcme) {
        for (BeanContextMembershipListener cur : bcmListeners) {
            cur.childrenRemoved(bcme);
        }
    }

    /**
     * Get BeanContextMembershipEvent class instance
     * 
     * @param changes -
     *            an array of changes that has been made
     * 
     * @return BeanContextMembershipEvent object
     */
    private BeanContextMembershipEvent getBCME(Object[] changes) {
        return new BeanContextMembershipEvent(getBeanContextPeer(), changes);
    }

    public BeanContext getBeanContextPeer() {
        return (BeanContext) getBeanContextChildPeer();
    }

    protected static final BeanContextChild getChildBeanContextChild(
            Object child) {

        // There's nothing to do here if the child is null
        if (child == null) {
            return null;
        }

        if (child instanceof BeanContextChild
                && child instanceof BeanContextProxy) {
            // beans.49=Child cannot implement both BeanContextChild and
            // BeanContextProxy
            throw new IllegalArgumentException(Messages.getString("beans.49")); //$NON-NLS-1$
        }

        // See if the child implements BeanContextChild or BeanContextProxy.
        // Cast it to appropriate class or simply return null
        if (child instanceof BeanContextChild) {
            return (BeanContextChild) child;
        } else if (child instanceof BeanContextProxy) {
            return ((BeanContextProxy) child).getBeanContextProxy();
        } else {
            return null;
        }
    }

    protected static final BeanContextMembershipListener getChildBeanContextMembershipListener(
            Object child) {
        // See if child implements BeanContextMembershipListener.
        // Cast it to BeanContextMembershipListener if it does
        // or return null otherwise
        if ((child != null) && child instanceof BeanContextMembershipListener) {
            return (BeanContextMembershipListener) child;
        }
        return null;
    }

    protected static final PropertyChangeListener getChildPropertyChangeListener(
            Object child) {
        // See if child implements PropertyChangeListener.
        // Cast it to PropertyChangeListener if it does
        // or return null otherwise
        if ((child != null) && child instanceof PropertyChangeListener) {
            return (PropertyChangeListener) child;
        }
        return null;
    }

    protected static final Serializable getChildSerializable(Object child) {
        // See if child implements Serializable.
        // Cast it to Serializable if it does
        // or return null otherwise
        if ((child != null) && child instanceof Serializable) {
            return (Serializable) child;
        }
        return null;
    }

    protected static final VetoableChangeListener getChildVetoableChangeListener(
            Object child) {
        // See if child implements VetoableChangeListener.
        // Cast it to VetoableChangeListener if it does
        // or return null otherwise
        if ((child != null) && child instanceof VetoableChangeListener) {
            return (VetoableChangeListener) child;
        }
        return null;
    }

    protected static final Visibility getChildVisibility(Object child) {
        // See if child implements Visibility.
        // Cast it to Visibility if it does
        // or return null otherwise
        if ((child != null) && child instanceof Visibility) {
            return (Visibility) child;
        }
        return null;
    }

    public synchronized Locale getLocale() {
        return this.locale;
    }

    /**
     * Construct PropertyChangeEvent object and return
     * 
     * @param prop -
     *            property name
     * @param o -
     *            the old value
     * @param n -
     *            the new value
     * 
     * @return PropertyChangeEvent object
     */
    // private PropertyChangeEvent getPCE(String prop, Object o, Object n) {
    // return new PropertyChangeEvent(getBeanContextPeer(), prop, o, n);
    // }
    public URL getResource(String name, BeanContextChild bcc) {

        // The resource name should not be null
        if (name == null) {
            throw new NullPointerException(Messages.getString("beans.2B")); //$NON-NLS-1$
        }

        // The child should not be null
        if (bcc == null) {
            throw new NullPointerException(Messages.getString("beans.2C")); //$NON-NLS-1$
        }

        if (!containsKey(bcc)) {
            throw new IllegalArgumentException(Messages.getString("beans.46")); //$NON-NLS-1$
        }

        // Load resource using the same ClassLoader as BeanContextChild
        // specified
        // If NullPointerException occurs try to load it as system resource
        try {
            return bcc.getClass().getClassLoader().getResource(name);
        } catch (NullPointerException e) {
            try {
                return ClassLoader.getSystemResource(name);
            } catch (Exception ex) {

                // We tried our best but still failed
                throw new IllegalArgumentException(Messages
                        .getString("beans.2D")); //$NON-NLS-1$
            }
        }
    }

    public InputStream getResourceAsStream(String name, BeanContextChild bcc) {

        // The resource name should not be null
        if (name == null) {
            throw new NullPointerException(Messages.getString("beans.2B")); //$NON-NLS-1$
        }

        // The child should not be null
        if (bcc == null) {
            throw new NullPointerException(Messages.getString("beans.2C")); //$NON-NLS-1$
        }

        if (!containsKey(bcc)) {
            throw new IllegalArgumentException(Messages.getString("beans.46")); //$NON-NLS-1$
        }

        // Load resource using the same ClassLoader as BeanContextChild
        // specified
        // If NullPointerException occurs try to load it as system resource
        try {
            return bcc.getClass().getClassLoader().getResourceAsStream(name);
        } catch (NullPointerException e) {
            try {
                return ClassLoader.getSystemResourceAsStream(name);
            } catch (Exception ex) {

                // No success at all
                throw new IllegalArgumentException(Messages
                        .getString("beans.2D")); //$NON-NLS-1$
            }
        }
    }

    protected synchronized void initialize() {

        // Initialize some fields
        this.children = new HashMap<Object, BCSChild>();
        this.bcmListeners = new ArrayList<BeanContextMembershipListener>();

        this.serializable = 0;
        this.serializing = false;

        // Initialize PropertyChangeListener
        this.pcl = new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent pce) {
                BeanContextSupport.this.propertyChange(pce);
            }
        };

        // Initialize VetoableChangeListener
        this.vcl = new VetoableChangeListener() {

            public void vetoableChange(PropertyChangeEvent pce)
                    throws PropertyVetoException {

                BeanContextSupport.this.vetoableChange(pce);
            }
        };
    }

    public Object instantiateChild(String beanName) throws IOException,
            ClassNotFoundException {

        // Use Beans convenience method to instantiate the bean
        return Beans.instantiate(getBeanContextPeer().getClass()
                .getClassLoader(), beanName, getBeanContextPeer());
    }

    public synchronized boolean isDesignTime() {
        return this.designTime;
    }

    public boolean isEmpty() {
        // See if there are any children in this BeanContext
        synchronized (BeanContext.globalHierarchyLock) {
            synchronized (this.children) {
                return this.children.isEmpty();
            }
        }
    }

    public boolean isSerializing() {
        return this.serializing;
    }

    public Iterator iterator() {
        synchronized (BeanContext.globalHierarchyLock) {
            synchronized (this.children) {
                return this.children.keySet().iterator();
            }
        }
    }

    public synchronized boolean needsGui() {
        // BeanContext needs GUI if at least one its children needs it.
        // We may check it by trying to cast each child to Visibility
        // and see it needs GUI.
        // A child definitely needs GUI if it implements Component
        for (Iterator it = iterator(); it.hasNext();) {
            Object next = it.next();
            Visibility vis = getChildVisibility(next);

            if (vis != null) {
                if (vis.needsGui()) {
                    return true;
                }
            }

            if (next instanceof java.awt.Component) {
                return true;
            }
        }

        return false;
    }

    public synchronized void okToUseGui() {
        // Notify this BeanContext and its children that it's OK now to use GUI
        this.okToUseGui = true;

        for (Iterator it = iterator(); it.hasNext();) {
            Object next = it.next();
            Visibility vis = getChildVisibility(next);

            if (vis != null) {
                vis.okToUseGui();
            }
        }
    }

    public void propertyChange(PropertyChangeEvent pce) {
        Object source;

        if (pce == null || pce.getPropertyName() == null ||
                !pce.getPropertyName().equals(BEAN_CONTEXT)) {
            return;
        }

        source = pce.getSource();

        if (source instanceof BCSChild) {
            BCSChild ch = (BCSChild) source;
            Object newValue = pce.getNewValue();

            if (!newValue.equals(this.getBeanContextPeer())) {
                remove(ch.getChild(), false);
            }
        }
    }

    public final void readChildren(ObjectInputStream ois) throws IOException,
            ClassNotFoundException {
        // Deserialize children
        for (int i = 0; i < this.serializable; i++) {
            BCSChild bChild = (BCSChild) ois.readObject();
            childDeserializedHook(bChild.getChild(), bChild);
        }
    }

    private void readObject(ObjectInputStream ois) throws IOException,
            ClassNotFoundException {

        synchronized (BeanContext.globalHierarchyLock) {
            ois.defaultReadObject();
            initialize();
            bcsPreDeserializationHook(ois);

            // Deserialize children
            if (!getBeanContextPeer().equals(this)) {
                readChildren(ois);
            }

            // Deserialize listeners
            synchronized (this.bcmListeners) {
                deserialize(ois, this.bcmListeners);
            }
        }
    }

    public boolean remove(Object targetChild) {
        return remove(targetChild, true);
    }

    protected boolean remove(Object targetChild, boolean callChildSetBC) {

        synchronized (BeanContext.globalHierarchyLock) {

            // Make necessary checks
            if (!validatePendingRemove(targetChild)) {
                return false;
            }

            Object removed = null;

            if (containsKey(targetChild)) {
                // Remove the reference to the BeanContext from the child
                // if ordered to do so
                if (callChildSetBC) {
                    removeFromContext(targetChild);
                }

                synchronized (this.children) {
                    // Just before removing save a reference to it for later use
                    // in childJustRemovedHook() method
                    BCSChild ch = this.children.get(targetChild);
                    removed = this.children.remove(targetChild);
                    childJustRemovedHook(targetChild, ch);
                }

                // Fire the event
                fireChildrenRemoved(getBCME(new Object[] { targetChild }));

                // Check if this child implements Serializable and decrease
                // the number of serializable children of BeanContext
                if (getChildSerializable(targetChild) != null) {
                    this.serializable--;
                }
            }

            return (removed != null);
        }
    }

    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException();
        /*
         * synchronized(BeanContext.globalHierarchyLock) { Collection col = new
         * ArrayList(); // Remove all children from BeanContext that are in the
         * collection // one by one. This operation is successful if all the //
         * removals succeeded for (Iterator i = c.iterator(); i.hasNext(); ) {
         * try { Object next = i.next();
         * 
         * if (remove(next)) { col.add(next); } } catch(Exception e) { // Roll
         * back changes but first check if it's already rolling // back to avoid
         * infinitive action if (!this.rollingBack) { this.rollingBack = true;
         * addAll(col); } else { this.rollingBack = false; }
         * 
         * return false; } } }
         * 
         * return true;
         */
    }

    public void removeBeanContextMembershipListener(
            BeanContextMembershipListener bcml) {

        // BeanContextMembershipListener can not be null
        if (bcml == null) {
            throw new NullPointerException(Messages.getString("beans.29")); //$NON-NLS-1$
        }

        synchronized (this.bcmListeners) {
            this.bcmListeners.remove(bcml);
        }
    }

    /**
     * Separate BeanContext and its child that had just been removed by removing
     * all references to each other
     * 
     * @param targetChild -
     *            a BeanContext child that was removed
     */
    private void removeFromContext(Object targetChild) {
        try {
            // If child is an instance of BeanContextChild or BeanContextProxy,
            // invoke setBeanContext() method on this child
            // with null parameter
            BeanContextChild ch = getChildBeanContextChild(targetChild);

            if (ch != null) {
                ch.setBeanContext(null);
                ch.removePropertyChangeListener(BEAN_CONTEXT, this.pcl);
                ch.removeVetoableChangeListener(BEAN_CONTEXT, this.vcl);
            }
        } catch (PropertyVetoException e) {
            // Required by spec
            throw new IllegalStateException(Messages.getString(
                    "beans.2E", targetChild, e.getMessage())); //$NON-NLS-1$
        }
    }

    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();

        /*
         * synchronized(BeanContext.globalHierarchyLock) {
         * synchronized(this.children) {
         * 
         * Collection col = new ArrayList(); // Remove all children from
         * BeanContext that are not in the // collection // This operation is
         * successful if all the removals succeeded for (Iterator i =
         * iterator(); i.hasNext(); ) { Object nextKey = i.next(); Object
         * nextValue = this.children.get(nextKey);
         * 
         * if (!c.contains(nextKey) && !c.contains(nextValue)) { try { if
         * (remove(nextKey)) { col.add(nextKey); } } catch(Exception e) { //
         * Roll back changes this.rollingBack = true; addAll(col); return false; } } } } }
         * 
         * return true;
         */
    }

    protected final void serialize(ObjectOutputStream oos, Collection coll)
            throws IOException {

        // Write the collection into ObjectOutputStream
        for (Iterator it = coll.iterator(); it.hasNext();) {
            Object l = it.next();

            if (getChildSerializable(l) != null) {
                oos.writeObject(l);
            }
        }

        // Mark the end of stream
        oos.writeObject("EOS"); //$NON-NLS-1$
    }

    public synchronized void setDesignTime(boolean dTime) {

        boolean old = this.designTime;
        this.designTime = dTime;

        // Notify BeanContext about this change
        firePropertyChange("designTime", new Boolean(old), new Boolean(dTime)); //$NON-NLS-1$
    }

    public synchronized void setLocale(Locale newLocale)
            throws PropertyVetoException {

        // Use default locale if a new value is null
        newLocale = (newLocale == null ? Locale.getDefault() : newLocale);

        // Notify BeanContext about this change
        Locale old = (Locale) this.locale.clone();
        this.locale = newLocale;
        firePropertyChange("locale", old, newLocale); //$NON-NLS-1$
    }

    public int size() {
        // Return the number of children of this BeanContext
        synchronized (BeanContext.globalHierarchyLock) {
            synchronized (this.children) {
                return this.children.size();
            }
        }
    }

    public Object[] toArray() {
        // Convert the collection of children to array
        synchronized (BeanContext.globalHierarchyLock) {
            synchronized (this.children) {
                return this.children.keySet().toArray();
            }
        }
    }

    public Object[] toArray(Object[] arry) {
        // Convert the collection of children to array
        synchronized (BeanContext.globalHierarchyLock) {
            synchronized (this.children) {
                return this.children.keySet().toArray(arry);
            }
        }
    }

    protected boolean validatePendingAdd(Object targetChild) {
        return true;
    }

    protected boolean validatePendingRemove(Object targetChild) {
        if (targetChild == null) {
            throw new IllegalArgumentException(Messages.getString("beans.2F")); //$NON-NLS-1$
        }

        return true;
    }

    public void vetoableChange(PropertyChangeEvent pce)
            throws PropertyVetoException {

        if (pce == null) {
            throw new NullPointerException(Messages.getString("beans.1C")); //$NON-NLS-1$
        }
    }

    public final void writeChildren(ObjectOutputStream oos) throws IOException {
        // Write serializable children to ObjectOutputStream
        synchronized (this.children) {
            for (Iterator it = iterator(); it.hasNext();) {
                Object next = it.next();

                if (getChildSerializable(next) != null) {
                    oos.writeObject(this.children.get(next));
                }
            }
        }
    }

    private void writeObject(ObjectOutputStream oos) throws IOException,
            ClassNotFoundException {

        synchronized (BeanContext.globalHierarchyLock) {
            this.serializing = true;
            oos.defaultWriteObject();

            bcsPreSerializationHook(oos);

            if (!this.getBeanContextPeer().equals(this)) {
                writeChildren(oos);
            }

            synchronized (this.bcmListeners) {
                serialize(oos, this.bcmListeners);
            }

            this.serializing = false;
        }
    }
}
