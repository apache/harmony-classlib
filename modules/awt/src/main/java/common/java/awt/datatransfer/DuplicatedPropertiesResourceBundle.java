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
 * @author Michael Danilov
 * @version $Revision$
 */
package java.awt.datatransfer;

import java.util.*;
import java.io.*;

final class DuplicatedPropertiesResourceBundle extends ResourceBundle {

    private Properties properties;

    public DuplicatedPropertiesResourceBundle(InputStream stream) throws IOException {
        properties = new Properties() {
            private static final long serialVersionUID = -4869518800983843099L;

            public Object put(Object key, Object value) {
                Object oldValue = get(key);

                if (oldValue == null) {
                    return super.put(key, value);
                } else {
                    List list;

                    if (oldValue instanceof String) {
                        list = new LinkedList();
                        list.add(oldValue);
                    } else {
                        list = (List) oldValue;
                    }
                    list.add(value);

                    return super.put(key, list);
                }
            }
        };
        properties.load(stream);
    }

    public Object handleGetObject(String key) {
        return properties.get(key);
    }

    public Enumeration getKeys() {
        Enumeration result = properties.propertyNames();

        if (parent == null) {
            return result;
        }

        ArrayList keys = Collections.list(result);
        Enumeration e = parent.getKeys();

        while (e.hasMoreElements()) {
            Object key = e.nextElement();

            if (!keys.contains(key)) {
                keys.add(key);
            }
        }

        return Collections.enumeration(keys);
    }
}
