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

package org.apache.harmony.beans;

import java.beans.DefaultPersistenceDelegate;
import java.beans.PersistenceDelegate;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public final class DefaultPersistenceDelegatesFactory {

    private static Map<String, PersistenceDelegate> persistenceDelegates = new HashMap<String, PersistenceDelegate>();

    private static PersistenceDelegate createPersistenceDelegate(Class<?> type) {
        if (type == null) {
            return new NullPersistenceDelegate();
        }

        PersistenceDelegate pd = null;
        try {
            String className = createDefaultNameForPersistenceDelegateClass(type);

            pd = (PersistenceDelegate) Class.forName(className, true,
                    type.getClassLoader()).newInstance();
        } catch (Exception e) {
            Class<?> ancestor = type.getSuperclass();

            while (pd == null && ancestor != null) {
                try {
                    String className = createDefaultNameForPersistenceDelegateClass(ancestor);

                    pd = (PersistenceDelegate) Class.forName(className, true,
                            type.getClassLoader()).newInstance();
                } catch (Exception e2) {
                    ancestor = ancestor.getSuperclass();
                }
            }

            if (pd == null) {
                pd = new DefaultPersistenceDelegate();
            }
        }
        return pd;
    }

    public static PersistenceDelegate getPersistenceDelegate(Class<?> type) {
        String className = (type == null) ? null : type.getName();
        PersistenceDelegate result = persistenceDelegates.get(className);

        if (result == null) {
            if (type != null && type.isArray()) {
                result = org.apache.harmony.beans.ArrayPersistenceDelegate
                        .getInstance();
            } else {
                result = createPersistenceDelegate(type);
                persistenceDelegates.put(className, result);
            }
        }
        return result;
    }

    private static String createDefaultNameForPersistenceDelegateClass(
            Class<?> type) {
        String typeName = type.getName();
        StringTokenizer st = new StringTokenizer(typeName, "."); //$NON-NLS-1$
        String className = ""; //$NON-NLS-1$
        while (st.hasMoreElements()) {
            String s = (String) st.nextElement();
            className += "".equals(className) ? s : "_" + s; //$NON-NLS-1$ //$NON-NLS-2$
        }
        return "org.apache.harmony.beans." + className + "PersistenceDelegate"; //$NON-NLS-1$ //$NON-NLS-2$
    }
}
