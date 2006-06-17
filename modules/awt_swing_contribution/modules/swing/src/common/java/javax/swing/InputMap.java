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

public class InputMap implements Serializable {

    private InputMap parent;
    private HashMap table;

    public void put(final KeyStroke keyStroke, final Object key) {
        if (keyStroke == null) {
            return;
        }
        if (key != null) {
            if (table == null) {
                table = new HashMap();
            }
            table.put(keyStroke, key);
        } else {
            remove(keyStroke);
        }
    }

    public Object get(final KeyStroke keyStroke) {
        Object key = null;
        if (table != null) {
            key = table.get(keyStroke);
        }
        if (key == null && getParent() != null) {
            key = getParent().get(keyStroke);
        }

        return key;
    }

    public void remove(final KeyStroke keyStroke) {
        if (table != null) {
            table.remove(keyStroke);
        }
    }

    public KeyStroke[] keys() {
        if (table == null) {
            return new KeyStroke[0];
        }
        return (KeyStroke[])table.keySet().toArray(new KeyStroke[table.size()]);
    }

    public KeyStroke[] allKeys() {
        KeyStroke[] keys = keys();
        if (parent == null) {
            return keys;
        }
        KeyStroke[] parentKeys = parent.allKeys();
        if (keys.length == 0) {
            return parentKeys;
        }
        if (parentKeys.length == 0) {
            return keys;
        }
        HashSet keySet = new HashSet(Arrays.asList(keys));
        keySet.addAll(Arrays.asList(parentKeys));
        return (KeyStroke[])keySet.toArray(new KeyStroke[keySet.size()]);
    }

    public void setParent(final InputMap parent) {
        this.parent = parent;
    }

    public InputMap getParent() {
        return parent;
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