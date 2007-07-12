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

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Vector;

import org.apache.harmony.beans.DefaultPersistenceDelegatesFactory;
import org.apache.harmony.beans.NullPersistenceDelegate;
import org.apache.harmony.beans.ObjectNode;

public class Encoder {

    private ExceptionListener exceptionListener = defaultExListener;

    private static final ExceptionListener defaultExListener = new DefaultExceptionListener();

    private static class DefaultExceptionListener implements ExceptionListener {

        public void exceptionThrown(Exception exception) {
            System.err.println("Exception during encoding:" + exception); //$NON-NLS-1$
            System.err.println("Continue...");
        }

    }

    private static final HashMap<Class<?>, PersistenceDelegate> persistenceDelegates = new HashMap<Class<?>, PersistenceDelegate>();

    Vector<Object> roots = new Vector<Object>();

    IdentityHashMap<Object, ObjectNode> nodes = new IdentityHashMap<Object, ObjectNode>();

    private IdentityHashMap oldNewMap = new IdentityHashMap();

    public Encoder() {
        super();
    }

    public Object get(Object oldInstance) {
        if (oldInstance == null || oldInstance instanceof String
                || oldInstance == String.class) {
            return oldInstance;
        }

        return oldNewMap.get(oldInstance);
    }

    public Object remove(Object oldInstance) {
        // TODO - notify references on node deletion
        if (oldInstance == null) {
            return null;
        }

        getValue(nodes.remove(oldInstance));
        return oldNewMap.remove(oldInstance);
    }

    public PersistenceDelegate getPersistenceDelegate(Class<?> type) {
        PersistenceDelegate result = persistenceDelegates.get(type);

        if (result == null) {
            result = DefaultPersistenceDelegatesFactory
                    .getPersistenceDelegate(type);
        }

        return result;
    }

    public void setPersistenceDelegate(Class<?> type,
            PersistenceDelegate persistenceDelegate) {
        if (type == null || persistenceDelegate == null) {
            throw new NullPointerException();
        }
        persistenceDelegates.put(type, persistenceDelegate);
    }

    protected void writeObject(Object object) {
        roots.add(object);
        if (object == null) {
            return;
        }
        doWriteObject(object);
    }

    void doWriteObject(Object object) {
        PersistenceDelegate pd = (object != null) ? getPersistenceDelegate(object
                .getClass())
                : new NullPersistenceDelegate();

        if (pd == null) {
            pd = new DefaultPersistenceDelegate();
        }

        pd.writeObject(object, this);
        if (isString(object.getClass())) {
            nodes.put(object, new ObjectNode(pd.instantiate(object, this)));
        }
    }

    private Object forceNew(Object old) {
        if (old == null) {
            return null;
        }
        Object nu = get(old);
        if (nu != null) {
            return nu;
        }
        writeObject(old);
        return get(old);
    }

    private Object[] forceNewArray(Object oldArray[]) {
        if (oldArray == null) {
            return null;
        }
        Object newArray[] = new Object[oldArray.length];
        for (int i = 0; i < oldArray.length; i++) {
            newArray[i] = forceNew(oldArray[i]);
        }
        return newArray;
    }

    public void writeStatement(Statement oldStm) {
        if (oldStm == null) {
            throw new NullPointerException();
        }
        try {
            // FIXME add target processing here
            Object newTarget = forceNew(oldStm.getTarget());
            Object newArgs[] = forceNewArray(oldStm.getArguments());
            Statement statement = new Statement(newTarget, oldStm
                    .getMethodName(), newArgs);
            statement.execute();
        } catch (Exception e) {
            getExceptionListener().exceptionThrown(e);
        }
    }

    private void put(Object old, Object nu) {
        oldNewMap.put(old, nu);
    }

    public void writeExpression(Expression oldExp) {
        if (oldExp == null) {
            throw new NullPointerException();
        }
        try {
            Object oldValue = oldExp.getValue();
            if (oldValue == null || get(oldValue) != null) {
                return;
            }

            // copy to newExp
            Object newTarget = forceNew(oldExp.getTarget());
            Object newArgs[] = forceNewArray(oldExp.getArguments());
            Expression newExp = new Expression(newTarget, oldExp
                    .getMethodName(), newArgs);

            // execute newExp
            Object newValue = null;
            try {
                newValue = newExp.getValue();
            } catch (IndexOutOfBoundsException ex) {
                // Current Container does not have any component, newVal set
                // to null
            }

            // relate oldValue to newValue
            put(oldValue, newValue);

            // force same state
            writeObject(oldValue);
        } catch (Exception e) {
            // TODO - remove written args
            getExceptionListener().exceptionThrown(e);
        }
    }

    public void setExceptionListener(ExceptionListener exceptionListener) {
        if (exceptionListener == null) {
            exceptionListener = defaultExListener;
        }
        this.exceptionListener = exceptionListener;
    }

    public ExceptionListener getExceptionListener() {
        return exceptionListener;
    }

    private Object write(Object oldInstance) throws Exception {
        if (oldInstance == null) {
            return null;
        }

        ObjectNode node = nodes.get(oldInstance);

        if (node == null) {
            doWriteObject(oldInstance);
            node = nodes.get(oldInstance);
        } else {
            node.addReference();
        }

        return node.getObjectValue();
    }

    Object[] write(Object[] oldInstances) throws Exception {
        if (oldInstances != null) {
            Object[] newInstances = new Object[oldInstances.length];

            for (int i = 0; i < oldInstances.length; ++i) {
                newInstances[i] = write(oldInstances[i]);
            }
            return newInstances;
        }
        return null;
    }

    /*
     * @param node node to return the value for @return tentative object value
     * for given node
     */
    private Object getValue(ObjectNode node) {
        if (node != null) {
            try {
                Object result = node.getObjectValue();

                return result;
            } catch (Exception e) {
                getExceptionListener().exceptionThrown(e);
            }
        }
        return null;
    }

    static boolean isNull(Class<?> type) {
        return type == null;
    }

    static boolean isPrimitive(Class<?> type) {
        return type == Boolean.class || type == Byte.class
                || type == Character.class || type == Double.class
                || type == Float.class || type == Integer.class
                || type == Long.class || type == Short.class;
    }

    static boolean isString(Class<?> type) {
        return type == String.class;

    }

    static boolean isClass(Class<?> type) {
        return type == Class.class;
    }

    static boolean isArray(Class<?> type) {
        return type.isArray();
    }

    static String getPrimitiveName(Class<?> type) {
        String result = null;

        if (type == Boolean.class) {
            result = "boolean"; //$NON-NLS-1$
        } else if (type == Byte.class) {
            result = "byte"; //$NON-NLS-1$
        } else if (type == Character.class) {
            result = "char"; //$NON-NLS-1$
        } else if (type == Double.class) {
            result = "double"; //$NON-NLS-1$
        } else if (type == Float.class) {
            result = "float"; //$NON-NLS-1$
        } else if (type == Integer.class) {
            result = "int"; //$NON-NLS-1$
        } else if (type == Long.class) {
            result = "long"; //$NON-NLS-1$
        } else if (type == Short.class) {
            result = "short"; //$NON-NLS-1$
        } else if (type == String.class) {
            result = "string"; //$NON-NLS-1$
        } else if (type == Class.class) {
            result = "class"; //$NON-NLS-1$
        }

        return result;
    }
}
