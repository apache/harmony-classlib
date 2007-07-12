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

import java.lang.reflect.Method;

import org.apache.harmony.beans.internal.nls.Messages;

public class DefaultPersistenceDelegate extends PersistenceDelegate {

    private String[] constructorPropertyNames;

    public DefaultPersistenceDelegate(String[] constructorPropertyNames) {
        String[] arr = null;

        // convert first letters of property names to lower case
        if (constructorPropertyNames != null) {
            arr = new String[constructorPropertyNames.length];
            for (int i = 0; i < constructorPropertyNames.length; i++) {
                if (constructorPropertyNames[i] != null
                        && constructorPropertyNames[i].length() > 0) {
                    arr[i] = constructorPropertyNames[i].substring(0, 1)
                            .toLowerCase()
                            + constructorPropertyNames[i].substring(1);
                } else {
                    arr[i] = constructorPropertyNames[i];
                }
            }
        }

        this.constructorPropertyNames = arr;
    }

    public DefaultPersistenceDelegate() {
        this.constructorPropertyNames = null;
    }

    @Override
    protected void initialize(Class<?> type, Object oldInstance,
            Object newInstance, Encoder out) {

        // added for compatibility with RI
        if (out == null) {
            throw new NullPointerException(Messages.getString("beans.4C")); //$NON-NLS-1$
        }

        // added for compatibility with RI
        if (newInstance == null) {
            out.getExceptionListener().exceptionThrown(
                    new NullPointerException(Messages.getString("beans.4A"))); //$NON-NLS-1$
            return;
        }

        // added for compatibility with RI
        if (oldInstance == null) {
            throw new NullPointerException(Messages.getString("beans.4C")); //$NON-NLS-1$
        }

        BeanInfo info = null;
        try {
            info = Introspector.getBeanInfo(type);
        } catch (IntrospectionException e) {
            out.getExceptionListener().exceptionThrown(e);
            return;
        }
        PropertyDescriptor[] pds = info.getPropertyDescriptors();

        for (PropertyDescriptor pd : pds) {
            if (!isTransient(pd)) {
                Method getter = pd.getReadMethod();

                if (getter != null) {
                    Method setter = pd.getWriteMethod();

                    if (setter != null) {
                        Expression getterExp = new Expression(oldInstance, pd
                                .getReadMethod().getName(), null);
                        try {
                            // Calculate the old value of the property
                            Object oldValue = getterExp.getValue();

                            // Write the getter expression to the encoder
                            out.writeExpression(getterExp);

                            // Get the target value that exists in the new
                            // environment
                            Object targetVal = out.get(oldValue);

                            Object newValue = new Expression(newInstance, pd
                                    .getReadMethod().getName(), null)
                                    .getValue();

                            /*
                             * Make the target value and current property value
                             * equivalent in the new environment
                             */
                            if (null == targetVal) {
                                if (null != newValue) {
                                    // Set to null
                                    Statement setterStm = new Statement(
                                            oldInstance, pd.getWriteMethod()
                                                    .getName(),
                                            new Object[] { null });
                                    out.writeStatement(setterStm);
                                }
                            } else {
                                PersistenceDelegate delegate = out
                                        .getPersistenceDelegate(targetVal
                                                .getClass());
                                if (!delegate.mutatesTo(targetVal, newValue)) {
                                    Statement setterStm = new Statement(
                                            oldInstance, pd.getWriteMethod()
                                                    .getName(),
                                            new Object[] { oldValue });
                                    out.writeStatement(setterStm);
                                }
                            }
                        } catch (Exception ex) {
                            out.getExceptionListener().exceptionThrown(ex);
                        }
                    } else {
                        // commented since the process should be
                        // continued even if no setter is found
                        // throw new Exception("no setter for " +
                        // pd.getName() + " property.");
                        continue;
                    }
                }
            }
        }
    }

    @Override
    protected Expression instantiate(Object oldInstance, Encoder out) {
        Object[] args = null;

        if (constructorPropertyNames == null
                || constructorPropertyNames.length == 0) {
            args = new Object[] {};
        } else {
            args = new Object[constructorPropertyNames.length];

            try {
                PropertyDescriptor[] pds = Introspector.getBeanInfo(
                        oldInstance.getClass()).getPropertyDescriptors();

                for (int i = 0; i < constructorPropertyNames.length; ++i) {

                    boolean found = false;

                    for (PropertyDescriptor element : pds) {

                        if (constructorPropertyNames[i].equals(element
                                .getName())) {
                            Method getter = element.getReadMethod();

                            if (getter != null) {
                                args[i] = getter.invoke(oldInstance,
                                        (Object[]) null);
                                found = true;
                                break;
                            }

                            throw new NoSuchMethodException(Messages.getString(
                                    "beans.00", element.getName())); //$NON-NLS-1$

                        }
                    }

                    if (found == false) {
                        throw new NoSuchMethodException(Messages.getString(
                                "beans.01", constructorPropertyNames[i])); //$NON-NLS-1$
                    }

                }
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                out.getExceptionListener().exceptionThrown(e);
            }

        }

        return new Expression(oldInstance, oldInstance.getClass(), "new", args); //$NON-NLS-1$
    }

    @Override
    protected boolean mutatesTo(Object oldInstance, Object newInstance) {
        if (oldInstance != null && constructorPropertyNames != null
                && constructorPropertyNames.length > 0) {

            // Get explicitly declared equals method.
            Method equalsMethod = null;
            try {
                equalsMethod = oldInstance.getClass().getDeclaredMethod(
                        "equals", new Class[] { Object.class }); //$NON-NLS-1$

            } catch (NoSuchMethodException e) {
                // does not declare explicitly an equals method.
            }

            if (equalsMethod != null) {
                Object result;
                try {
                    result = equalsMethod.invoke(oldInstance,
                            new Object[] { newInstance });
                } catch (Exception e) {
                    // should not happen here.
                    throw new Error(e);
                }
                return ((Boolean) result).booleanValue();
            }
        }
        return super.mutatesTo(oldInstance, newInstance);
    }

    private static boolean isTransient(PropertyDescriptor pd) {
        Boolean isTransient = (Boolean) pd.getValue("transient"); //$NON-NLS-1$
        return (isTransient != null) && isTransient.equals(Boolean.TRUE);
    }
}
