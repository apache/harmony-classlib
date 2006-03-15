/* Copyright 2005 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.util.prefs;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.apache.harmony.luni.util.BASE64Decoder;
import org.apache.harmony.luni.util.BASE64Encoder;

/**
 * This class is partly implementation of <code>Preferences</code>, which can be 
 * used to simplify <code>Preferences</code> provider's implementation. 
 * <p>
 * This class define nine abstract SPI methods, which must be implemented by 
 * preference provider. And provider can also override other methods of this 
 * class. Some SPI methods will throw <code>BackingStoreException</code>, 
 * including <code>childrenNamesSpi()</code>, <code>flushSpi()</code>, 
 * <code>keysSpi()</code>, <code>removeNodeSpi()</code>, 
 * <code>syncSpi()</code>; <code>getSpi(String, String)</code> never throws any 
 * exceptions; the last SPI methods, <code>putSpi(String)</code>, 
 * <code>removeSpi(String)</code> and <code>childSpi(String)</code> won't throw 
 * <code>BackingStoreException</code>, but in some implementations, they may 
 * throw <code>SecurityException</code> due to lacking the permission to access 
 * backing end storage.</p>
 * 
 * @since 1.4
 * @see Preferences
 */
public abstract class AbstractPreferences extends Preferences {
    /*
     * -----------------------------------------------------------
     * Constants
     * -----------------------------------------------------------
     */
    /**
     * The object used to lock this node. 
     */
    protected final Object lock;

    //the unhandled events colletion
    static final LinkedList events = new LinkedList();
    //the event dispatcher thread
    private final static EventDispatcher dispatcher = new EventDispatcher("Preference Event Dispatcher");

    /*
     * -----------------------------------------------------------
     * variables
     * -----------------------------------------------------------
     */
    /**
     * This field is true if this node is created while it doesn't exist in the 
     * backing store. This field's default value is false, and it is checked when 
     * the node creation is completed, and if it is true, the node change event 
     * will be fired for this node's parent.
     */
    protected boolean newNode;

    //cached child nodes
    private HashMap cachedNode;

    //true if this node is in user preference hierarchy
    boolean userNode;

    //the colletions of listeners
    LinkedList nodeChangeListeners, preferenceChangeListeners;

    //this node's name
    private String nodeName;

    //handler to this node's parent
    private AbstractPreferences parentPref;

    //true if this node has been removed
    private boolean isRemoved;

    //handler to this node's root node
    private AbstractPreferences root;

    /*
     * -----------------------------------------------------------
     * Class init
     * -----------------------------------------------------------
     */
    static {
        dispatcher.setDaemon(true);
        dispatcher.start();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                Preferences uroot = Preferences.userRoot();
                Preferences sroot = Preferences.systemRoot();
                try {
                    uroot.flush();
                } catch (BackingStoreException e) {//ignore
                }
                try {
                    sroot.flush();
                } catch (BackingStoreException e) {//ignore
                }
            }
        });
    }

    /*
     * -----------------------------------------------------------
     * Constructor
     * -----------------------------------------------------------
     */
    /**
     * Construct a new <code>AbstractPreferences</code> instance using given 
     * parent node and node name. 
     *
     * @param parent
     * 				the parent node of this node, can be null, which means this 
     * 				node is root
     * @param name
     * 				the name of this node, can be empty(""), which means this 
     * 				node is root
     * @throws IllegalArgumentException
     * 				if name contains slash, or be empty if parent is not null
     */
    protected AbstractPreferences(AbstractPreferences parent, String name) {
        if ((null == parent ^ name.length() == 0) || name.indexOf("/") >= 0) { //$NON-NLS-1$
            throw new IllegalArgumentException();
        }
        root = null == parent ? this : parent.root;
        nodeChangeListeners = new LinkedList();
        preferenceChangeListeners = new LinkedList();
        isRemoved = false;
        cachedNode = new HashMap();
        nodeName = name;
        parentPref = parent;
        lock = new Object();
        userNode = root.userNode;
    }

    /*
     * -----------------------------------------------------------
     * Methods
     * -----------------------------------------------------------
     */
    /**
     * Return arrays of all cached children node.
     * 
     * @return arrays of all cached children node.
     */
    protected final AbstractPreferences[] cachedChildren() {
        return (AbstractPreferences[]) cachedNode.values().toArray(
                new AbstractPreferences[0]);
    }

    /**
     * Return the child node with given name, or null if it doesn't exist. The 
     * given name must be valid and this node cannot be removed. Invocation of 
     * this method implies that the node with given name is not cached(or, has 
     * been removed.)
     * 
     * @param name	the given child name to be got 
     * @return		the child node with given name, or null if it doesn't exist 
     * @throws BackingStoreException
     * 				if backing store is unavailable or causes operation failure
     */
    protected AbstractPreferences getChild(String name)
            throws BackingStoreException {
        synchronized (lock) {
            checkState();
            AbstractPreferences result = null;
            String[] childrenNames = childrenNames();
            for (int i = 0; i < childrenNames.length; i++) {
                if (childrenNames[i].equals(name)) {
                    result = childSpi(name);
                    break;
                }
            }
            return result;
        }

    }

    /**
     * Return true if and only if this node has been removed by invoking 
     * {@link #removeNode() removeNode}. 
     *
     * @return true if and only if this node has been removed by invoking 
     * 			{@link #removeNode() removeNode}
     */
    protected boolean isRemoved() {
        synchronized (lock) {
            return isRemoved;
        }
    }

    /**
     * Flush changes of this node to the backing store. This method should only 
     * flush this node, and should not include the descendent nodes. The 
     * implementation which want to flush all nodes at once should override 
     * {@link #flush() flush()} method. 
     *
     * @throws BackingStoreException
     * 				if backing store is unavailable or causes operation failure
     */
    protected abstract void flushSpi() throws BackingStoreException;
    
    /**
     * Return names of this node's all children , or empty array if this node has 
     * no child. Cached children name is not required to be returned.
     *
     * @return names of this node's all children
     * @throws BackingStoreException
     * 				if backing store is unavailable or causes operation failure
     */
    protected abstract String[] childrenNamesSpi() throws BackingStoreException;

    /**
     * Return the child preference node with the given name, and create new one if 
     * it does not exist. Invoker of this method should assure that the given name 
     * are valid as well as this node is not removed. Invocation of this method 
     * implies that the node with given name is not cached(or, has been removed.)
     * If the named node has just been removed, implementation of this method must 
     * create a new one instead of reactivated the removed one. 
     * <p>
     * The new creation is not required to be presisted immediately until the flush 
     * method is invoked.</p>
     * 
     * @param name
     * @return AbstractPreferences
     */
    protected abstract AbstractPreferences childSpi(String name);


    /**
     * Put the given key-value pair into this node. Invoker of this method should
     * assure that both the given values are valid as well as this node 
     * is not removed.
     *
     * @param name	the given preference key
     * @param value the given preference value
     */
    protected abstract void putSpi(String name, String value);

    /**
     * Get the preference value mapped to the given key. Invoker of this method 
     * should assure that given key are valid as well as this node is not removed. 
     * This method should not throw exceptions, but if it does, the invoker should 
     * catch it and deal with it as null return value.
     *
     * @param key	the given key to be searched for
     * @return the preference value mapped to the given key
     */
    protected abstract String getSpi(String key);


    /**
     * Return all keys of this node's preferences, or empty array if no preference 
     * found on this node. Invoker of this method should assure that this node is 
     * not removed.
     *
     * @return all keys of this node's preferences
     * @throws BackingStoreException
     * 				if backing store is unavailable or causes operation failure
     */
    protected abstract String[] keysSpi() throws BackingStoreException;

    /**
     * Remove this node from the preference hierarchy tree. The invoker of this 
     * method should assure that this node has no child node, which means the 
     * {@link Preferences#removeNode() Preferences.removeNode()} should invoke 
     * this method multi-times in bottom-up pattern. The removal is not required 
     * to be persisted at once until the it is flushed.  
     *
     * @throws BackingStoreException
     * 				if backing store is unavailable or causes operation failure
     */
    protected abstract void removeNodeSpi() throws BackingStoreException;

    /**
     * Remove the preference with the given key. Invoker of this method 
     * should assure that given key are valid as well as this node is not removed. 
     * 
     * @param key	the given key to removed
     */
    protected abstract void removeSpi(String key);

    /**
     * Synchronize this node with the backing store. This method should only 
     * synchronize this node, and should not include the descendent nodes. The 
     * implementation which want to synchronize all nodes at once should override 
     * {@link #sync() sync()} method. 
     * 
     * @throws BackingStoreException
     * 				if backing store is unavailable or causes operation failure
     */
    protected abstract void syncSpi() throws BackingStoreException;

    /*
     * -----------------------------------------------------------
     * Methods inherited from Preferences
     * -----------------------------------------------------------
     */
    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#absolutePath()
     */
    public String absolutePath() {
        if (parentPref == null) {
            return "/"; //$NON-NLS-1$
        } else if (parentPref == root) {
            return "/" + nodeName; //$NON-NLS-1$
        }
        return parentPref.absolutePath() + "/" + nodeName; //$NON-NLS-1$
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#childrenNames()
     */
    public String[] childrenNames() throws BackingStoreException {
        synchronized (lock) {
            checkState();
            TreeSet result = new TreeSet(cachedNode.keySet());
            String[] names = childrenNamesSpi();
            for (int i = 0; i < names.length; i++) {
                result.add(names[i]);
            }
            return (String[]) result.toArray(new String[0]);
        }
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#clear()
     */
    public void clear() throws BackingStoreException {
        synchronized (lock) {
            String[] keyList = keys();
            for (int i = 0; i < keyList.length; i++)
                remove(keyList[i]);
        }
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#exportNode(java.io.OutputStream)
     */
    public void exportNode(OutputStream ostream) throws IOException,
            BackingStoreException {
        checkState();
        XMLParser.exportPrefs(this, ostream, false);

    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#exportSubtree(java.io.OutputStream)
     */
    public void exportSubtree(OutputStream ostream) throws IOException,
            BackingStoreException {
        checkState();
        XMLParser.exportPrefs(this, ostream, true);
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#flush()
     */
    public void flush() throws BackingStoreException {
        synchronized (lock) {
            flushSpi();
        }
        AbstractPreferences[] cc = cachedChildren();
        int i;
        for (i = 0; i < cc.length; i++)
            cc[i].flush();
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#get(java.lang.String, java.lang.String)
     */
    public String get(String key, String deflt) {
        if (key == null)
            throw new NullPointerException();
        String result;
        synchronized (lock) {
            checkState();
            try {
                result = getSpi(key);
            } catch (Exception e) {
                result = null;
            }
        }
        return (result == null ? deflt : result);
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#getBoolean(java.lang.String, boolean)
     */
    public boolean getBoolean(String key, boolean deflt) {
        String result = get(key, null);
        if (result == null)
            return deflt;
        else if (result.equalsIgnoreCase("true")) { //$NON-NLS-1$
            return true;
        } else if (result.equalsIgnoreCase("false")) { //$NON-NLS-1$
            return false;
        } else
            return deflt;
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#getByteArray(java.lang.String, byte[])
     */
    public byte[] getByteArray(String key, byte[] deflt) {
        String svalue = get(key, null);
        if (svalue == null)
            return deflt;
        if ("".equals(svalue)) { //$NON-NLS-1$
            return new byte[0];
        }
        byte[] dres;
        try {
            byte[] bavalue = svalue.getBytes("ascii"); //$NON-NLS-1$
            if (bavalue.length % 4 != 0) {
                return deflt;
            }
            dres = BASE64Decoder.decode(bavalue);
        } catch (Exception e) {
            dres = deflt;
        }
        return dres;
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#getDouble(java.lang.String, double)
     */
    public double getDouble(String key, double deflt) {
        String result = get(key, null);
        if (result == null)
            return deflt;
        double dres;
        try {
            dres = Double.parseDouble(result);
        } catch (NumberFormatException e) {
            dres = deflt;
        }
        return dres;
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#getFloat(java.lang.String, float)
     */
    public float getFloat(String key, float deflt) {
        String result = get(key, null);
        if (result == null)
            return deflt;
        float fres;
        try {
            fres = Float.parseFloat(result);
        } catch (NumberFormatException e) {
            fres = deflt;
        }
        return fres;
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#getInt(java.lang.String, int)
     */
    public int getInt(String key, int deflt) {
        String result = get(key, null);
        if (result == null)
            return deflt;
        int ires;
        try {
            ires = Integer.parseInt(result);
        } catch (NumberFormatException e) {
            ires = deflt;
        }
        return ires;
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#getLong(java.lang.String, long)
     */
    public long getLong(String key, long deflt) {
        String result = get(key, null);
        if (result == null)
            return deflt;
        long lres;
        try {
            lres = Long.parseLong(result);
        } catch (NumberFormatException e) {
            lres = deflt;
        }
        return lres;
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#isUserNode()
     */
    public boolean isUserNode() {
        return root == Preferences.userRoot();
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#keys()
     */
    public String[] keys() throws BackingStoreException {
        synchronized (lock) {
            checkState();
            return keysSpi();
        }
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#name()
     */
    public String name() {
        return nodeName;
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#node(java.lang.String)
     */
    public Preferences node(String name) {
        AbstractPreferences startNode = null;
        synchronized (lock) {
            checkState();
            validateName(name);
            if ("".equals(name)) { //$NON-NLS-1$
                return this;
            } else if ("/".equals(name)) { //$NON-NLS-1$
                return root;
            }
            if (name.startsWith("/")) { //$NON-NLS-1$
                startNode = root;
                name = name.substring(1);
            } else {
                startNode = this;
            }
        }
        Preferences result = null;
        try {
            result = startNode.nodeImpl(name, true);
        } catch (BackingStoreException e) {
            //should not happen
        }
        return result;
    }

    private void validateName(String name) {
        if (name.endsWith("/") && name.length() > 1) { //$NON-NLS-1$
            throw new IllegalArgumentException("Name cannot end with '/'!"); //$NON-NLS-1$
        }
        if (name.indexOf("//") >= 0) { //$NON-NLS-1$
            throw new IllegalArgumentException(
                    "Name cannot contains consecutive '/'!"); //$NON-NLS-1$
        }
    }

    private AbstractPreferences nodeImpl(String path, boolean createNew)
            throws BackingStoreException {
        StringTokenizer st = new StringTokenizer(path, "/"); //$NON-NLS-1$
        AbstractPreferences currentNode = this;
        AbstractPreferences temp = null;
        while (st.hasMoreTokens() && null != currentNode) {
            String name = st.nextToken();
            synchronized (currentNode.lock) {
                temp = (AbstractPreferences) currentNode.cachedNode.get(name);
                if (temp == null) {
                    temp = getNodeFromBackend(createNew, currentNode, name);
                }
            }

            currentNode = temp;
        }
        return currentNode;
    }

    private AbstractPreferences getNodeFromBackend(boolean createNew,
            AbstractPreferences currentNode, String name)
            throws BackingStoreException {
        AbstractPreferences temp;
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Name length is too long: " //$NON-NLS-1$
                    + name);
        }
        if (createNew) {
            temp = currentNode.childSpi(name);
            currentNode.cachedNode.put(name, temp);
            if (temp.newNode && currentNode.nodeChangeListeners.size() > 0)
                currentNode.notifyChildAdded(temp);
        } else {
            temp = currentNode.getChild(name);
        }
        return temp;
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#nodeExists(java.lang.String)
     */
    public boolean nodeExists(String name) throws BackingStoreException {
        AbstractPreferences startNode = null;
        synchronized (lock) {
            if (isRemoved()) {
                if ("".equals(name)) { //$NON-NLS-1$
                    return false;
                }
                throw new IllegalStateException("This node has been removed!"); //$NON-NLS-1$
            }
            validateName(name);
            if ("".equals(name) || "/".equals(name)) { //$NON-NLS-1$ //$NON-NLS-2$
                return true;
            }
            if (name.startsWith("/")) { //$NON-NLS-1$
                startNode = root;
                name = name.substring(1);
            } else {
                startNode = this;
            }
        }
        Preferences result = startNode.nodeImpl(name, false);
        return null == result ? false : true;
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#parent()
     */
    public Preferences parent() {
        checkState();
        return parentPref;
    }

    private void checkState() {
        if (isRemoved()) {
            throw new IllegalStateException("This node has been removed!"); //$NON-NLS-1$
        }
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#put(java.lang.String, java.lang.String)
     */
    public void put(String key, String value) {
        if (null == key || null == value) {
            throw new NullPointerException();
        }
        if (key.length() > MAX_KEY_LENGTH || value.length() > MAX_VALUE_LENGTH) {
            throw new IllegalArgumentException();
        }
        synchronized (lock) {
            checkState();
            putSpi(key, value);
        }
        notifyPreferenceChange(key, value);
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#putBoolean(java.lang.String, boolean)
     */
    public void putBoolean(String key, boolean value) {
        String sval = String.valueOf(value);
        put(key, sval);
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#putByteArray(java.lang.String, byte[])
     */
    public void putByteArray(String key, byte[] value) {
        byte[] result = BASE64Encoder.encode(value);
        try {
            put(key, new String(result, "ascii")); //$NON-NLS-1$
        } catch (UnsupportedEncodingException e) {
            //should not happen
        }
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#putDouble(java.lang.String, double)
     */
    public void putDouble(String key, double value) {
        String sval = Double.toString(value);
        put(key, sval);
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#putFloat(java.lang.String, float)
     */
    public void putFloat(String key, float value) {
        String sval = Float.toString(value);
        put(key, sval);
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#putInt(java.lang.String, int)
     */
    public void putInt(String key, int value) {
        String sval = Integer.toString(value);
        put(key, sval);
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#putLong(java.lang.String, long)
     */
    public void putLong(String key, long value) {
        String sval = Long.toString(value);
        put(key, sval);
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#remove(java.lang.String)
     */
    public void remove(String key) {
        synchronized (lock) {
            checkState();
            removeSpi(key);
        }
        notifyPreferenceChange(key, null);
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#removeNode()
     */
    public void removeNode() throws BackingStoreException {
        if (root == this) {
            throw new UnsupportedOperationException("Cannot remove root node!"); //$NON-NLS-1$
        }
        synchronized (parentPref.lock) {
            removeNodeImpl();
        }
    }

    private void removeNodeImpl() throws BackingStoreException {
        synchronized (lock) {
            checkState();
            String[] childrenNames = childrenNamesSpi();
            for (int i = 0; i < childrenNames.length; i++) {
                if (null == cachedNode.get(childrenNames[i])) {
                    AbstractPreferences child = childSpi(childrenNames[i]);
                    cachedNode.put(childrenNames[i], child);
                }
            }
            AbstractPreferences[] children = (AbstractPreferences[]) cachedNode
                    .values().toArray(new AbstractPreferences[0]);
            for (int i = 0; i < children.length; i++) {
                children[i].removeNodeImpl();
            }
            removeNodeSpi();
            isRemoved = true;
            parentPref.cachedNode.remove(nodeName);
        }
        if (parentPref.nodeChangeListeners.size() > 0) {
            parentPref.notifyChildRemoved(this);
        }
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#addNodeChangeListener(java.util.prefs.NodeChangeListener)
     */
    public void addNodeChangeListener(NodeChangeListener ncl) {
        if (null == ncl) {
            throw new NullPointerException();
        }
        checkState();
        synchronized (nodeChangeListeners) {
            nodeChangeListeners.add(ncl);
        }
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#addPreferenceChangeListener(java.util.prefs.PreferenceChangeListener)
     */
    public void addPreferenceChangeListener(PreferenceChangeListener pcl) {
        if (null == pcl) {
            throw new NullPointerException();
        }
        checkState();
        synchronized (preferenceChangeListeners) {
            preferenceChangeListeners.add(pcl);
        }
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#removeNodeChangeListener(java.util.prefs.NodeChangeListener)
     */
    public void removeNodeChangeListener(NodeChangeListener ncl) {
        checkState();
        synchronized (nodeChangeListeners) {
            int pos;
            if ((pos = nodeChangeListeners.indexOf(ncl)) == -1) {
                throw new IllegalArgumentException();
            }
            nodeChangeListeners.remove(pos);
        }
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#removePreferenceChangeListener(java.util.prefs.PreferenceChangeListener)
     */
    public void removePreferenceChangeListener(PreferenceChangeListener pcl) {
        checkState();
        synchronized (preferenceChangeListeners) {
            int pos;
            if ((pos = preferenceChangeListeners.indexOf(pcl)) == -1) {
                throw new IllegalArgumentException();
            }
            preferenceChangeListeners.remove(pos);
        }
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#sync()
     */
    public void sync() throws BackingStoreException {
        synchronized (lock) {
            checkState();
            syncSpi();
        }
        AbstractPreferences[] cc = cachedChildren();
        int i;
        for (i = 0; i < cc.length; i++)
            cc[i].sync();
    }

    /*
     *  (non-Javadoc)
     * @see java.util.prefs.Preferences#toString()
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(isUserNode() ? "User" : "System"); //$NON-NLS-1$ //$NON-NLS-2$
        sb.append(" Preference Node: "); //$NON-NLS-1$
        sb.append(absolutePath());
        return sb.toString();
    }

    private void notifyChildAdded(Preferences child) {
        NodeChangeEvent nce = new NodeAddEvent(this, child);
        synchronized (events) {
            events.add(nce);
            events.notifyAll();
        }
    }

    private void notifyChildRemoved(Preferences child) {
        NodeChangeEvent nce = new NodeRemoveEvent(this, child);
        synchronized (events) {
            events.add(nce);
            events.notifyAll();
        }
    }

    private void notifyPreferenceChange(String key, String newValue) {
        PreferenceChangeEvent pce = new PreferenceChangeEvent(this, key,
                newValue);
        synchronized (events) {
            events.add(pce);
            events.notifyAll();
        }
    }

    private static class EventDispatcher extends Thread {
        public EventDispatcher(){
            super();
        }
        
        public EventDispatcher(String name){
            super(name);
        }
        
        public void run() {
            while (true) {
                EventObject event = null;
                try {
                    event = getEventObject();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    continue;
                }
                AbstractPreferences pref = (AbstractPreferences) event
                        .getSource();
                if (event instanceof NodeAddEvent) {
                    dispatchNodeAdd((NodeChangeEvent) event,
                            pref.nodeChangeListeners);
                } else if (event instanceof NodeRemoveEvent) {
                    dispatchNodeRemove((NodeChangeEvent) event,
                            pref.nodeChangeListeners);
                } else if (event instanceof PreferenceChangeEvent) {
                    dispatchPrefChange((PreferenceChangeEvent) event,
                            pref.preferenceChangeListeners);
                }
            }
        }

        private EventObject getEventObject() throws InterruptedException {
            synchronized (events) {
                if (events.isEmpty()) {
                    events.wait();
                }
                EventObject event = (EventObject) events.get(0);
                events.remove(0);
                return event;
            }
        }

        private void dispatchPrefChange(PreferenceChangeEvent event,
                LinkedList preferenceChangeListeners) {
            synchronized (preferenceChangeListeners) {
                Iterator i = preferenceChangeListeners.iterator();
                while (i.hasNext()) {
                    PreferenceChangeListener pcl = (PreferenceChangeListener) i
                            .next();
                    pcl.preferenceChange(event);
                }
            }
        }

        private void dispatchNodeRemove(NodeChangeEvent event,
                LinkedList nodeChangeListeners) {
            synchronized (nodeChangeListeners) {
                Iterator i = nodeChangeListeners.iterator();
                while (i.hasNext()) {
                    NodeChangeListener ncl = (NodeChangeListener) i.next();
                    ncl.childRemoved(event);
                }
            }
        }

        private void dispatchNodeAdd(NodeChangeEvent event,
                LinkedList nodeChangeListeners) {
            synchronized (nodeChangeListeners) {
                Iterator i = nodeChangeListeners.iterator();
                while (i.hasNext()) {
                    NodeChangeListener ncl = (NodeChangeListener) i.next();
                    ncl.childAdded(event);
                }
            }
        }
    }

    private static class NodeAddEvent extends NodeChangeEvent {
        /**
         * @param p
         * @param c
         */
        public NodeAddEvent(Preferences p, Preferences c) {
            super(p, c);
        }
    }

    private static class NodeRemoveEvent extends NodeChangeEvent {
        /**
         * @param p
         * @param c
         */
        public NodeRemoveEvent(Preferences p, Preferences c) {
            super(p, c);
        }
    }
}



