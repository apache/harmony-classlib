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

package java.beans;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision$
 */
public class Introspector {

    public static final int USE_ALL_BEANINFO = 1;

    public static final int IGNORE_IMMEDIATE_BEANINFO = 2;

    public static final int IGNORE_ALL_BEANINFO = 3;
    
    private Introspector() {}

    /**
     * @com.intel.drl.spec_ref
     */
    public static String decapitalize(String name) {
        if((name != null) && (name.length() > 0)) {
            // first two letters are capital
            if((name.length() > 1) && name.substring(0,2).equals(
                    name.substring(0,2).toUpperCase())) {
                return name;
            }
            String result = name.substring(0,1).toLowerCase();
            if(name.length() > 1) {
                result += name.substring(1);
            }
            return result;
           }
        return name;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static BeanInfo getBeanInfo(Class<?> beanClass, int flags)
        throws IntrospectionException 
    {
        switch (flags) {
            case USE_ALL_BEANINFO:
                return getBeanInfo(beanClass, null, false, false);    
            case IGNORE_IMMEDIATE_BEANINFO:
                return getBeanInfo(beanClass, null, true, false);
            case IGNORE_ALL_BEANINFO:
                return getBeanInfo(beanClass, null, true, true);
            default:
                //TODO: verify that default beahvior complies with RI
                return getBeanInfo(beanClass, null, false, false);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static BeanInfo getBeanInfo(Class<?> beanClass)
            throws IntrospectionException {
        return getBeanInfo(beanClass, null, false, false);    
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static BeanInfo getBeanInfo(Class<?> beanClass, Class<?> stopClass)
       throws IntrospectionException
    {
        return getBeanInfo(beanClass, stopClass, false, false);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static synchronized void setBeanInfoSearchPath(String[] searchPath) {
        if (searchPath != null) {
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                sm.checkPropertiesAccess();
            }
            path = searchPath;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static synchronized String[] getBeanInfoSearchPath() {
        return path;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static void flushFromCaches(Class<?> clz) {
        removeBeanInfoClassFromCache(clz);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static void flushCaches() {
        removeAllBeanInfoClassesFromCache();
    }

    // private methods

    private static BeanInfoWrapper getBeanInfo(Class<?> beanClass, Class<?> stopClass,
        boolean ignoreBeanClassBeanInfo, boolean ignoreSuperClassBeanInfo)
    {
        if (beanClass == null) {
            throw new java.lang.NullPointerException();
        }

        BeanInfoWrapper beanInfoWrapper = findBeanInfoClassInCache(beanClass,
                stopClass, ignoreBeanClassBeanInfo, ignoreSuperClassBeanInfo);

        if (beanInfoWrapper != null) {
            return beanInfoWrapper;
        }

        // find bean info as a separate class
        
        BeanInfo beanInfo = null;
        if (!ignoreBeanClassBeanInfo) {
            try {
                Class<?> beanInfoClass = findBeanInfoClass(beanClass);
                if(beanInfoClass != null) {
                    beanInfo = (BeanInfo) beanInfoClass.newInstance();
                }
            } catch (Exception e) {
            }
        }
        
        //...
        
        // generate bean info automatically
        
        BeanInfoImpl beanInfoImpl = new BeanInfoImpl(beanClass);
        
        //...
        
        BeanInfoWrapper wrapper = new BeanInfoWrapper(beanInfo, beanInfoImpl);
    
        Class<?> parent = beanClass.getSuperclass();
        if ((parent != null) && (parent != stopClass)) {
            BeanInfoWrapper parentBeanInfo = getBeanInfo(parent, stopClass,
                ignoreSuperClassBeanInfo, ignoreSuperClassBeanInfo);
            
            wrapper.merge(parentBeanInfo);
        }
        
        //...
        
        storeBeanInfoClassInCache(wrapper, beanClass, stopClass,
            ignoreBeanClassBeanInfo, ignoreSuperClassBeanInfo);
        
        return wrapper;
    }

    private static Class<?> findBeanInfoClass(Class<?> beanClass) {
        String beanClassName = beanClass.getName();
        int idx = beanClassName.lastIndexOf("."); //$NON-NLS-1$
        String shortBeanInfoClassName = beanClassName.substring(idx + 1,
            beanClassName.length()) + "BeanInfo"; //$NON-NLS-1$
        String fullBeanInfoClassName = beanClassName + "BeanInfo"; //$NON-NLS-1$

        Class<?> beanInfoClass = null;
        try {
            beanInfoClass = Class.forName(
                fullBeanInfoClassName, true, beanClass.getClassLoader());
            
        } catch (ClassNotFoundException cnfe) {
            for (int i = 0; i < path.length; ++i) {
                try {
                    fullBeanInfoClassName = path[i] + "." //$NON-NLS-1$
                            + shortBeanInfoClassName;
                    beanInfoClass = Class.forName(fullBeanInfoClassName, true,
                            beanClass.getClassLoader());
                    break;
                } catch (ClassNotFoundException cnfe2) {
                }
            }
        }
        
        return beanInfoClass;
    }

    private static BeanInfoWrapper findBeanInfoClassInCache(
            Class<?> beanClass,
            Class<?> stopClass,
            boolean ignoreBeanClassBeanInfo,
            boolean ignoreSuperClassBeanInfo) {
        BeanInfoWrapper result = null;
        List<BeanInfoData> beanInfoDatas = beanInfos.get(beanClass.getName());
        if (beanInfoDatas != null) {
            Iterator<BeanInfoData> iterator = beanInfoDatas.iterator();
            while(iterator.hasNext()) {
                BeanInfoData beanInfoData = (BeanInfoData) iterator.next();
                if ((beanInfoData.getStopClass() == stopClass) &&
                    (beanInfoData.getIgnoreBeanClassBeanInfo()
                            ==  ignoreBeanClassBeanInfo)
                    && (beanInfoData.getIgnoreSuperClassBeanInfo()
                            ==  ignoreSuperClassBeanInfo))
                {
                    result = beanInfoData.getBeanInfoWrapper();
                }
                if (result != null) break;
            }
        }
        return result;
    }
    
    private static void storeBeanInfoClassInCache(
            BeanInfoWrapper beanInfoWrapper,
            Class<?> beanClass,
            Class<?> stopClass,
            boolean ignoreBeanClassBeanInfo,
            boolean ignoreSuperClassBeanInfo) {
        List<BeanInfoData> beanInfoDatas = beanInfos.get(beanClass.getName());
        if(beanInfoDatas == null) {
            beanInfoDatas = new ArrayList<BeanInfoData>();
            beanInfos.put(beanClass.getName(), beanInfoDatas);
        }
        beanInfoDatas.add(new BeanInfoData(stopClass, ignoreBeanClassBeanInfo,
            ignoreSuperClassBeanInfo, beanInfoWrapper));
    }

    private static void removeBeanInfoClassFromCache(Class<?> beanClass) {
        beanInfos.remove(beanClass.getName());
    }

    private static void removeAllBeanInfoClassesFromCache() {
        beanInfos.clear();
    }

    // private fields

    private static String[] path = {"org.apache.harmony.beans.infos"}; //$NON-NLS-1$
    private static Map<String, List<BeanInfoData>> beanInfos =
        new HashMap<String, List<BeanInfoData>>();

}
