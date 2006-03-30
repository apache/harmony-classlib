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
 * @version $Revision: 1.14.6.4 $
 */
package java.beans;

import java.util.HashMap;
import java.util.Vector;

import org.apache.harmony.beans.NullPersistenceDelegate;
import org.apache.harmony.beans.ObjectNode;
import org.apache.harmony.beans.DefaultPersistenceDelegatesFactory;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.14.6.4 $
 */

public class Encoder {
    
    private ExceptionListener exceptionListener = null;
    private HashMap persistenceDelegates = new HashMap();
    
    Vector roots = new Vector();
    HashMap nodes = new HashMap();
    
    /**
     * @com.intel.drl.spec_ref
     */
    public Encoder() {}
    
    /**
     * @com.intel.drl.spec_ref
     */
    public Object get(Object oldInstance) {
        if(oldInstance == null) {
            return null;
        }
        
        ObjectNode node = (ObjectNode) nodes.get(oldInstance);
        Object result = getValue(node);
        return result;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Object remove(Object oldInstance) {
        //TODO - notify references on node deletion
        if(oldInstance == null) {
            return null;
        }
        
        ObjectNode node = (ObjectNode) nodes.remove(oldInstance);
        return getValue(node);
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public PersistenceDelegate getPersistenceDelegate(Class type) {
        PersistenceDelegate result =
                (PersistenceDelegate) persistenceDelegates.get(type);
        
        if(result == null) {
             result = DefaultPersistenceDelegatesFactory.getPersistenceDelegate(
                     type);
        }
        
        return result;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setPersistenceDelegate(Class type,
            PersistenceDelegate persistenceDelegate) {
        persistenceDelegates.put(type, persistenceDelegate);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected void writeObject(Object object) {
        roots.add(object);
        doWriteObject(object);
    }
    
    void doWriteObject(Object object) {
           PersistenceDelegate pd = (object != null) ?
               getPersistenceDelegate(object.getClass()) :
                   new NullPersistenceDelegate();
               
           if(pd == null) {
               pd = new DefaultPersistenceDelegate();
           }
           
           pd.writeObject(object, this);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void writeStatement(Statement oldStm) {
        ObjectNode node = (ObjectNode) nodes.get(oldStm.getTarget());
        if(node != null) {
            try {
                Object[] oldArgs = oldStm.getArguments();
                Object[] newArgs = write(oldArgs);
                
                Statement statement = new Statement(node.getObjectValue(),
                        oldStm.getMethodName(), oldArgs);
                node.addStatement(statement);
            } catch (Exception e) {
                getExceptionListener().exceptionThrown(e);
            }
        } else {
            System.out.println("no node is found for statement with target = "
                    + oldStm.getTarget());
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void writeExpression(Expression oldExp) {
        try {
            Object oldInstance = oldExp.getValue();
            
            ObjectNode node = null;
            Class type = null;
            
            if(oldInstance != null) {
                type = oldInstance.getClass();
                node = (ObjectNode) nodes.get(oldInstance);
            }
            
            if(node == null) {
                if(isNull(type) || isPrimitive(type) || isString(type)
                        || isClass(type)) {
                    node = new ObjectNode(oldExp);
                } else {
                    write(oldExp.getArguments());
                    node = new ObjectNode(oldExp, nodes);
                }
                
                nodes.put(oldInstance, node);
                
                // if an expression is not a constructor
                if(!(oldExp.getTarget() instanceof Class)) {
                    ObjectNode parent = (ObjectNode) nodes.get(
                            oldExp.getTarget());
                    parent.addExpression(oldExp);
                }
            } else if(oldExp.getMethodName().equals("new")) {
                node.addReference();
            } else {
                node.addReferencedExpression(oldExp);
            }
        } catch (Exception e) {
            // TODO - remove written args
            getExceptionListener().exceptionThrown(e);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setExceptionListener(ExceptionListener exceptionListener) {
        this.exceptionListener = exceptionListener;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public ExceptionListener getExceptionListener() {
        if(exceptionListener == null) {
            exceptionListener = new ExceptionListener() {
                public void exceptionThrown(Exception e) {
                    System.out.println(e.getClass() + ": " + e.getMessage());
                }
            };
        }
        
        return exceptionListener;
    }
    
    private Object write(Object oldInstance) throws Exception {
        if(oldInstance == null) {
            return null;
        }

        ObjectNode node = (ObjectNode) nodes.get(oldInstance);
        if(node == null) {
            Class type = oldInstance.getClass();
            
            if(isPrimitive(type) || isString(type) || isClass(type)) {
                Expression expr = new Expression(type, "new",
                        new Object[] { oldInstance });
                nodes.put(oldInstance, new ObjectNode(expr));
            } else {
                doWriteObject(oldInstance);
            }
            
            node = (ObjectNode) nodes.get(oldInstance);
        } else {
            node.addReference();
        }
        
        return node.getObjectValue();
    }
    
    private Object[] write(Object[] oldInstances) throws Exception {
        if(oldInstances != null) {
            Object[] newInstances = new Object[oldInstances.length];
            
            for(int i = 0; i < oldInstances.length; ++i) {
                newInstances[i] = write(oldInstances[i]);
            }
            
            return newInstances;
        }
        
        return null;
    }

    private Object getValue(ObjectNode node) {
        if(node != null) {
            try {
                Object result = node.getObjectValue();
                return result;
            } catch (Exception e) {
                getExceptionListener().exceptionThrown(e);
            }
        }
        
        return null;
    }
    
    static boolean isNull(Class type) {
        return (type == null);
    }
    
    static boolean isPrimitive(Class type) {
        return (type == Boolean.class) || (type == Byte.class) ||
            (type == Character.class) || (type == Double.class) ||
            (type == Float.class) || (type == Integer.class) ||
            (type == Long.class) || (type == Short.class);
    }
    
    static boolean isString(Class type) {
        return (type == String.class);
        
    }
    
    static boolean isClass(Class type) {
        return (type == Class.class);
    }
    
    
    static boolean isArray(Class type) {
        return type.isArray();
    }
    
    static String getPrimitiveName(Class type) {
        String result = null;
        
        if(type == Boolean.class) {
            result = "boolean";
        } else if(type == Byte.class) {
            result = "byte";
        } else if(type == Character.class) {
            result = "char";
        } else if(type == Double.class) {
            result = "double";
        } else if(type == Float.class) {
            result = "float";
        } else if(type == Integer.class) {
            result = "int";
        } else if(type == Long.class) {
            result = "long";
        } else if(type == Short.class) {
            result = "short";
        } else if(type == String.class) {
            result = "string";
        } else if(type == Class.class) {
            result = "class";
        }
        
        return result;
    }
}
