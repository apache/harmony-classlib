/*
 *  Copyright 2005 - 2006 The Apache Software Software Foundation or its licensors, as applicable.
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
 * @author Alexander T. Simbirtsev
 * @version $Revision$
 */
package javax.swing;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class ActionMap implements Serializable {

    private ActionMap parent;
    private HashMap table;

    public void put(final Object key, final Action action) {
        if (action != null) {
            if (table == null) {
                table = new HashMap();
            }
            table.put(key, action);
        } else {
            remove(key);
        }
    }

    public Action get(final Object key) {
        Action action = null;
        if (table != null) {
            action = (Action)table.get(key);
        }
        if (action == null && getParent() != null) {
            action = getParent().get(key);
        }

        return action;
    }

    public void setParent(final ActionMap parent) {
        this.parent = parent;
    }

    public ActionMap getParent() {
        return parent;
    }

    public void remove(final Object key) {
        if (table != null) {
            table.remove(key);
        }
    }

    public Object[] keys() {
        if (table == null) {
            return new Object[0];
        }
        return table.keySet().toArray(new Object[table.size()]);
    }

    public Object[] allKeys() {
        Object[] keys = keys();
        if (parent == null) {
            return keys;
        }
        Object[] parentKeys = parent.allKeys();
        if (keys.length == 0) {
            return parentKeys;
        }
        if (parentKeys.length == 0) {
            return keys;
        }
        HashSet keySet = new HashSet(Arrays.asList(keys));
        keySet.addAll(Arrays.asList(parentKeys));
        return keySet.toArray(new Object[keySet.size()]);
    }

    public void clear() {
        if (table != null) {
            table.clear();
        }
    }

    public int size() {
        return (table != null) ? table.size() : 0;
    }

}

