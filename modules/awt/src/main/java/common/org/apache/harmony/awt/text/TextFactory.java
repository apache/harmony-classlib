/*
 *  Copyright 2005 - 2006 The Apache Software Foundation or its licensors, as applicable.
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
 * @author Evgeniya G. Maenkova
 * @version $Revision$
 */

package org.apache.harmony.awt.text;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;

import javax.swing.text.Element;
import javax.swing.text.View;

public abstract class TextFactory {
    private static final String FACTORY_IMPL_CLS_NAME =
        "javax.swing.text.TextFactoryImpl";

    private static final TextFactory viewFactory = createTextFactory();

    public static TextFactory getTextFactory() {
        return viewFactory;
    }

    private static TextFactory createTextFactory() {
        PrivilegedAction createAction = new PrivilegedAction() {
            public Object run() {
                try {
                    Class factoryImplClass = Class
                        .forName(FACTORY_IMPL_CLS_NAME);
                    Constructor defConstr =
                        factoryImplClass.getDeclaredConstructor(new Class[0]);
                    defConstr.setAccessible(true);
                    return defConstr.newInstance(new Object[0]);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();

                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }  catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        TextFactory factory =
            (TextFactory)AccessController.doPrivileged(createAction);

        return factory;
    }

    public abstract RootViewContext createRootView(final Element element);

    public abstract View createPlainView(final Element e);

    public abstract View createWrappedPlainView(final Element e);

    public abstract View createFieldView(final Element e);

    public abstract View createPasswordView(Element e);

    public abstract TextCaret createCaret();
}
