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
 * @version $Revision: 1.6.6.3 $
 */
package java.beans;

import java.applet.Applet;
import java.beans.beancontext.BeanContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Array;

import org.apache.harmony.beans.internal.nls.Messages;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.6.6.3 $
 */

public class Beans {

    private static boolean designTime = false;

    private static boolean guiAvailable = false;

    /**
     */
    public Beans() {}

    /**
     * @com.intel.drl.spec_ref
     */
    public static Object instantiate(ClassLoader cls, String beanName,
            BeanContext beanContext, AppletInitializer initializer)
            throws IOException, ClassNotFoundException {
        Object result = instantiate(cls, beanName, beanContext);

        if (result instanceof Applet) {
            initializer.initialize((Applet) result, beanContext);
        }

        return result;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static Object instantiate(ClassLoader cls, String beanName,
            BeanContext beanContext) throws IOException, ClassNotFoundException {
        Object result = instantiate(cls, beanName);

        if (beanContext != null) {
            beanContext.add(result);
        }

        return result;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static Object instantiate(ClassLoader cls, String beanName)
            throws IOException, ClassNotFoundException {
        Object result = null;

        String beanResourceName = getBeanResourceName(beanName);

        InputStream is = (cls == null) ? ClassLoader
                .getSystemResourceAsStream(beanResourceName) : cls
                .getResourceAsStream(beanResourceName);

        if (is != null) {
            try {
                ObjectInputStream ois = (cls == null) ? new ObjectInputStream(
                        is) : new CustomizedObjectInputStream(is, cls);

                try {
                    result = ois.readObject();
                } catch (ClassNotFoundException cnfe) {
                    // skip exception
                }
            } catch (IOException ioe) {
                // skip exception
            }
        }

        if (result == null) {
            try {
                Class<?> c = Class.forName(beanName, true,
                        cls == null ? ClassLoader.getSystemClassLoader() : cls);

                try {
                    result = c.newInstance();

                    if (result instanceof Applet) {
                        Applet applet = (Applet) result;

                        applet.init();
                    }
                } catch (IllegalAccessException iae) {
                    throw new ClassNotFoundException(iae.getClass() + ": " //$NON-NLS-1$
                            + iae.getMessage());
                }
            } catch (InstantiationException ie) {
                throw new ClassNotFoundException(ie.getClass() + ": " //$NON-NLS-1$
                        + ie.getMessage());
            }
        }

        return result;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static Object getInstanceOf(Object bean, Class<?> targetType) {
        return bean;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static boolean isInstanceOf(Object bean, Class<?> targetType) {
        if (bean == null) {
            throw new NullPointerException(Messages.getString("beans.1D")); //$NON-NLS-1$
        }

        return targetType == null ? false : targetType.isInstance(bean);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static void setGuiAvailable(boolean isGuiAvailable)
            throws SecurityException {
        checkPropertiesAccess();
        guiAvailable = isGuiAvailable;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static void setDesignTime(boolean isDesignTime)
            throws SecurityException {
        checkPropertiesAccess();
        designTime = isDesignTime;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static boolean isGuiAvailable() {
        return guiAvailable;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static boolean isDesignTime() {
        return designTime;
    }

    private static void checkPropertiesAccess() throws SecurityException {
        SecurityManager sm = System.getSecurityManager();

        if (sm != null) {
            sm.checkPropertiesAccess();
        }
    }

    private static String getBeanResourceName(String beanName) {
        return beanName.replace('.', '/') + ".ser"; //$NON-NLS-1$
    }

}

/**
 * Customized object input stream that allows
 * to read objects by specified class loader
 */
class CustomizedObjectInputStream extends ObjectInputStream {

    private ClassLoader cls;

    public CustomizedObjectInputStream(InputStream in, ClassLoader cls)
            throws IOException {
        super(in);
        this.cls = cls;
    }

    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException,
            ClassNotFoundException {
        String className = desc.getName();

        if (className.startsWith("[")) { //$NON-NLS-1$
            int idx = className.lastIndexOf("["); //$NON-NLS-1$
            String prefix = className.substring(0, idx + 1);
            int[] dimensions = new int[prefix.length()];
            String postfix;
            Class<?> componentType = null;

            for (int i = 0; i < dimensions.length; ++i) {
                dimensions[i] = 0;
            }

            postfix = className.substring(idx + 1);
            if (postfix.equals("Z")) { //$NON-NLS-1$
                componentType = boolean.class;
            } else if (postfix.equals("B")) { //$NON-NLS-1$
                componentType = byte.class;
            } else if (postfix.equals("C")) { //$NON-NLS-1$
                componentType = char.class;
            } else if (postfix.equals("D")) { //$NON-NLS-1$
                componentType = double.class;
            } else if (postfix.equals("F")) { //$NON-NLS-1$
                componentType = float.class;
            } else if (postfix.equals("I")) { //$NON-NLS-1$
                componentType = int.class;
            } else if (postfix.equals("L")) { //$NON-NLS-1$
                componentType = long.class;
            } else if (postfix.equals("S")) { //$NON-NLS-1$
                componentType = short.class;
            } else if (postfix.equals("V")) { //$NON-NLS-1$
                componentType = null;
            } else if (postfix.startsWith("L")) { //$NON-NLS-1$
                componentType = cls.loadClass(postfix.substring(1, postfix
                        .length() - 1));
            } else {
                throw new IllegalArgumentException(Messages.getString("beans.1E", className)); //$NON-NLS-1$
            }

            return Array.newInstance(componentType, dimensions).getClass();
        } else {
            return Class.forName(className, true, cls);
        }
    }
}
