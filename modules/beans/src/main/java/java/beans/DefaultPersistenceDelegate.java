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
 * @version $Revision: 1.7.6.3 $
 */
package java.beans;

import java.lang.reflect.Method;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.7.6.3 $
 */

public class DefaultPersistenceDelegate extends PersistenceDelegate {
    
    private String[] constructorPropertyNames;

    /**
     * @com.intel.drl.spec_ref
     */
    public DefaultPersistenceDelegate(String[] constructorPropertyNames) {
        this.constructorPropertyNames = constructorPropertyNames;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public DefaultPersistenceDelegate() {
        this.constructorPropertyNames = null;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected void initialize(Class type, Object oldInstance,
            Object newInstance, Encoder out) {
        try {
            PropertyDescriptor[] pds =
                    Introspector.getBeanInfo(type).getPropertyDescriptors();
            
            for(int i = 0; i < pds.length; ++i) {
                PropertyDescriptor pd = pds[i];
                
                if(!isTransient(pd)) {
                    Method getter = pd.getReadMethod();
                    
                    if(getter != null) {
                           Method setter = pd.getWriteMethod();
                           
                           if(setter != null) {
                               Object oldValue = getter.invoke(oldInstance,
                                       null);
                               Object newValue = getter.invoke(newInstance,
                                       null);
                               
                               if(oldValue != null && !oldValue.equals(newValue)
                                       || oldValue == null && newValue != null)
                               {
                                   String setterName = setter.getName();
                                   Statement s = new Statement(oldInstance,
                                           setterName,
                                           new Object[] { oldValue } );
                                   out.writeStatement(s);
                               }
                           } else {
                               // commented since the process should be continued even if no setter is found
                               // throw new Exception("no setter for " + pd.getName() + " property.");
                               continue;
                           }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("in DefaultPersistenceDelegate initialize() " +
                    e.getClass() + " :" + e.getMessage());
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected Expression instantiate(Object oldInstance, Encoder out) {
        Object[] args = null;
        
        if(constructorPropertyNames == null
                || constructorPropertyNames.length == 0) {
            args = new Object[] {};
        } else {
            args = new Object[constructorPropertyNames.length];
            
            try {
                PropertyDescriptor[] pds = Introspector.getBeanInfo(
                        oldInstance.getClass()).getPropertyDescriptors();
                
                for(int i = 0; i < constructorPropertyNames.length; ++i) {
                    
                    boolean found = false;
                    
                    for(int j = 0; j < pds.length; ++j) {
                        
                        if(constructorPropertyNames[i].equals(pds[j].getName())) {
                            Method getter = pds[j].getReadMethod();
                            
                            if(getter != null) {
                                args[i] = getter.invoke(oldInstance, null);
                                found = true;
                                break;
                            } else {
                                throw new Exception("no getter for " +
                                    pds[j].getName() + " property");
                            }
                            
                        }
                    } // for j
                    
                    if(found == false) {
                        throw new Exception("no property for name " +
                            constructorPropertyNames[i] + " is found");
                    }
                    
                } // for i
            } catch (Exception e) {
                System.out.println("in DefaultPersistenceDelegate instantiate() "
                        + e.getClass() + " :" + e.getMessage());
            }
            
        }
        
        return new Expression(oldInstance, oldInstance.getClass(), "new", args);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected boolean mutatesTo(Object oldInstance, Object newInstance) {
        if(oldInstance != null) {
            try {
                Method equalsMethod = oldInstance.getClass().getMethod(
                    "equals", new Class[] { Object.class });
                if(equalsMethod != null) {
                    Object result = equalsMethod.invoke(oldInstance,
                            new Object[] { newInstance } );
                       return ((Boolean) result).booleanValue();
                }
            } catch(Exception e) {
                System.out.println("in DefaultPersistenceDelegate.mutatesTo() "
                        + e.getClass() + " :" + e.getMessage());
                return false;
            }
        }
        return super.mutatesTo(oldInstance, newInstance);
    }
    
    private static boolean isTransient(PropertyDescriptor pd) {
        Boolean isTransient = (Boolean) pd.getValue("transient");
        return (isTransient != null) && isTransient.equals(Boolean.TRUE);
    }
}
