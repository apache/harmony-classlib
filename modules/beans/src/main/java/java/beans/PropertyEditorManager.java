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
 * @version $Revision: 1.6.6.4 $
 */
package java.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.6.6.4 $
 */

public class PropertyEditorManager {
    
    private static String[] path = {"org.apache.harmony.beans.editors"}; //$NON-NLS-1$
    private static Map<Class<?>, Class<?>> registeredEditors = new HashMap<Class<?>, Class<?>>();
    
    /**
     */
    public PropertyEditorManager() {
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static void registerEditor(Class<?> targetType, Class<?> editorClass) {
        if (targetType == null) {
            throw new NullPointerException();
        }

        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPropertiesAccess();
        }
        if (editorClass != null) {
            registeredEditors.put(targetType, editorClass);
        } else {
            registeredEditors.remove(targetType);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static synchronized PropertyEditor findEditor(Class<?> targetType) {
        if (targetType == null) {
            throw new NullPointerException();
        }

        Class<?> editorClass = null;
        PropertyEditor editor = null;

        editorClass = registeredEditors.get(targetType);

        if (editorClass == null) {
            String editorClassName = targetType.getName() + "Editor"; //$NON-NLS-1$
            ClassLoader loader = targetType.getClassLoader();

            if (loader == null) {
                loader = Thread.currentThread().getContextClassLoader();
            }

            try {
                editorClass = Class.forName(editorClassName, true, loader);
            } catch (ClassNotFoundException cnfe) {
                String shortEditorClassName = editorClassName
                        .substring(editorClassName.lastIndexOf(".") + 1); //$NON-NLS-1$

                if (targetType.isPrimitive()) {
                    shortEditorClassName = shortEditorClassName.substring(0, 1)
                            .toUpperCase()
                            + shortEditorClassName.substring(1);
                }

                for (int i = 0; i < path.length; ++i) {
                    editorClassName = path[i] + "." + shortEditorClassName; //$NON-NLS-1$

                    try {
                        editorClass = Class.forName(editorClassName, true,
                                loader);
                        break;
                    } catch (Exception e) {}
                }
            } catch (Exception e) {}
        }

        if (editorClass != null) {
            try {
                editor = (PropertyEditor) editorClass.newInstance();
            } catch (Exception e) {}
        }

        return editor;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static synchronized void setEditorSearchPath(String[] apath) {
        SecurityManager sm = System.getSecurityManager();
        
        if (sm != null) {
            sm.checkPropertiesAccess();
        }
        
        path = apath;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static synchronized String[] getEditorSearchPath() {
        return path;
    }
}
