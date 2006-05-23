/* Copyright 1998, 2006 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.luni.internal.net.www.protocol.http;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The general structure for request / reponse header. It is essentially
 * constructed by hashtable with key indexed in a vector for position lookup.
 * 
 */

public class Header implements Cloneable {
    /**
     * The default amount of fields for a header
     */
    private static final int incCapacity = 20;

    /*
     * we use the non-synchronized ArrayList and HashMap instead of the
     * synchronized Vector and Hashtable
     */
    private ArrayList props = new ArrayList(incCapacity);

    private HashMap keyTable = new HashMap(incCapacity);

    private String statusLine;

    /**
     * A generic header structure. Used mostly for request / response header.
     * The key/value pair of the header may be inserted for later use. The key
     * is stored in an array for indexed slot access.
     */
    public Header() {
    }

    /**
     * The alternative constructor which sets the input map as its initial
     * keyTable.
     * 
     * @param map
     *            the initial keyTable as a map
     */
    public Header(Map map) {
        for (Iterator entries = map.entrySet().iterator(); entries.hasNext();) {
            Map.Entry next = (Map.Entry) entries.next();
            String key = (String) next.getKey();
            props.add(key);
            List value = (List) next.getValue();
            LinkedList linkedList = new LinkedList();
            for (Iterator iter = value.iterator(); iter.hasNext();) {
                String element = (String) iter.next();
                linkedList.add(element);
                props.add(element);
            }
            keyTable.put(key, linkedList);
        }
    }
    
    public Object clone() {
        try {
            Header clone = (Header) super.clone();

            clone.props = (ArrayList) props.clone();
            HashMap cloneTable = clone.keyTable = new HashMap(incCapacity);
            Iterator entries = keyTable.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry next = (Map.Entry) entries.next();
                cloneTable.put(next.getKey(), ((LinkedList) next.getValue())
                        .clone());
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * Add a field with the specified value.
     * 
     * @param key
     *            java.lang.String
     * @param value
     *            java.lang.String
     */
    public void add(String key, String value) {
        if (key == null) {
            throw new NullPointerException();
        }
        LinkedList list = (LinkedList) keyTable.get(key);
        if (list == null) {
            list = new LinkedList();
            keyTable.put(key.toLowerCase(), list);
        }
        list.add(value);
        props.add(key);
        props.add(value);
    }

    /**
     * Set a field with the specified value. If the field is not found, it is
     * added. If the field is found, the existing value(s) are overwritten.
     * 
     * 
     * @param key
     *            java.lang.String
     * @param value
     *            java.lang.String
     */
    public void set(String key, String value) {
        if (key == null) {
            throw new NullPointerException();
        }
        LinkedList list = (LinkedList) keyTable.get(key);
        if (list == null) {
            add(key, value);
        } else {
            list.clear();
            list.add(value);
            for (int i = 0; i < props.size(); i += 2) {
                String propKey = (String) props.get(i);
                if (propKey != null && key.equals(propKey)) {
                    props.set(i + 1, value);
                }
            }
        }
    }

    /**
     * Provides an unmodifiable map with all String header names mapped to their
     * String values. The map keys are Strings and the values are unmodifiable
     * Lists of Strings.
     * 
     * @return an unmodifiable map of the headers
     * 
     * @since 1.4
     */
    public Map getFieldMap() {
        Map result = new HashMap(keyTable.size());
        Iterator iterator = keyTable.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry next = (Map.Entry) iterator.next();
            result.put(next.getKey(), Collections
                    .unmodifiableList((LinkedList) next.getValue()));
        }
        return Collections.unmodifiableMap(result);
    }

    /**
     * Answers the element at <code>pos</code>, null if no such element
     * exist.
     * 
     * @return java.lang.String the value of the key
     * @param pos
     *            int the position to look for
     */
    public String get(int pos) {
        if (pos >= 0 && pos < props.size() / 2) {
            return (String) props.get(pos * 2 + 1);
        }
        return null;
    }

    /**
     * Answers the key of this header at <code>pos</code>, null if there are
     * fewer keys in the header
     * 
     * 
     * @return java.lang.String the key the desired position
     * @param pos
     *            int the position to look for
     */
    public String getKey(int pos) {
        if (pos >= 0 && pos < props.size() / 2) {
            return (String) props.get(pos * 2);
        }
        return null;
    }

    /**
     * Answers the value corresponding to the specified key, null if no such key
     * exists.
     * 
     * 
     * @return java.lang.String
     * @param key
     *            java.lang.String
     */
    public String get(String key) {
        LinkedList result = (LinkedList) keyTable.get(key.toLowerCase());
        if (result == null) {
            return null;
        }
        return (String) result.getLast();
    }

    /**
     * Answers the number of keys stored in this header
     * 
     * 
     * @return int
     */
    public int length() {
        return props.size() / 2;
    }

    /**
     * Sets the status line in the header request example: GET / HTTP/1.1
     * response example: HTTP/1.1 200 OK
     * 
     * @param statusLine
     */
    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
        // we add the status line to the list of headers so that it is
        // accessible
        // from java.net.HttpURLConnection.getResponseCode()
        // which calls
        // org.apache.harmony.luni.internal.net.www.protocol.http.HttpURLConnection.getHeaderField(0)
        // to get it
        props.add(0, null);
        props.add(1, statusLine);
    }

    /**
     * Gets the status line in the header request example: GET / HTTP/1.1
     * response example: HTTP/1.1 200 OK
     * 
     * @return the status line
     */
    public String getStatusLine() {
        return statusLine;
    }

}
