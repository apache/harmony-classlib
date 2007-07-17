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

package java.beans;

import java.applet.Applet;
import java.beans.beancontext.BeanContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import org.apache.harmony.beans.internal.nls.Messages;

public class Beans {

    private static boolean designTime = false;

    private static boolean guiAvailable = true;

    public Beans() {
    }

    public static Object instantiate(ClassLoader cls, String beanName,
            BeanContext beanContext, AppletInitializer initializer)
            throws IOException, ClassNotFoundException {
        Object result = instantiate(cls, beanName, beanContext);

        if (result instanceof Applet) {
            initializer.initialize((Applet) result, beanContext);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public static Object instantiate(ClassLoader cls, String beanName,
            BeanContext beanContext) throws IOException, ClassNotFoundException {
        Object result = instantiate(cls, beanName);

        if (beanContext != null) {
            beanContext.add(result);
        }

        return result;
    }

    public static Object instantiate(ClassLoader cls, String beanName)
            throws IOException, ClassNotFoundException {
        Object result = null;

        String beanResourceName = getBeanResourceName(beanName);

        InputStream is = (cls == null) ? ClassLoader
                .getSystemResourceAsStream(beanResourceName) : cls
                .getResourceAsStream(beanResourceName);

        if (is != null) {            
            ObjectInputStream ois = (cls == null) ? new ObjectInputStream(is) : new CustomizedObjectInputStream(is, cls);
            result = ois.readObject();
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

    public static Object getInstanceOf(Object bean, Class<?> targetType) {
        return bean;
    }

    public static boolean isInstanceOf(Object bean, Class<?> targetType) {
        if (bean == null) {
            throw new NullPointerException(Messages.getString("beans.1D")); //$NON-NLS-1$
        }

        return targetType == null ? false : targetType.isInstance(bean);
    }

    public static synchronized void setGuiAvailable(boolean isGuiAvailable)
            throws SecurityException {
        checkPropertiesAccess();
        guiAvailable = isGuiAvailable;
    }

    public static void setDesignTime(boolean isDesignTime)
            throws SecurityException {
        checkPropertiesAccess();
        synchronized(Beans.class){
            designTime = isDesignTime;
        }
    }

    public static synchronized boolean isGuiAvailable() {
        return guiAvailable;
    }

    public static synchronized boolean isDesignTime() {
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
