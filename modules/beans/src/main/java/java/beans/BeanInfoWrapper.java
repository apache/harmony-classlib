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

import java.awt.Image;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class BeanInfoWrapper implements BeanInfo {

    // external BeanInfo class if it was specified 
    private BeanInfo info;

    // information gathered by means of reflection API
    private BeanInfoImpl impl;

    private BeanInfoWrapper parentBeanInfoWrapper;
    
    private int defaultEventIndex = -1;
    
    private EventSetDescriptor[] events = null;

    public BeanInfoWrapper(BeanInfo info, BeanInfoImpl impl) {
        this.info = info;
        this.impl = impl;
    }

    public PropertyDescriptor[] getPropertyDescriptors() {
        PropertyDescriptor[] result = null;
        PropertyDescriptor[] infoResult = null;

        // if external BeanInfo class was specified
        if (info != null) {
            BeanInfo[] infos = info.getAdditionalBeanInfo();

            infoResult = info.getPropertyDescriptors();

            if (infos != null) {
                for (BeanInfo additionalInfo : infos) {
                    if (infoResult == null) {
                        infoResult = additionalInfo.getPropertyDescriptors();
                    } else {
                        infoResult = concatArraysToOneArray(infoResult,
                                additionalInfo.getPropertyDescriptors());
                    }
                }
            }
        }

        // if no external BeanInfo class was specified or we fail to 
        // to obtain the result on the previous step
        if (info == null || infoResult == null) {
            PropertyDescriptor[] implResult = impl.getPropertyDescriptors();

            // merge with parent info
            if (parentBeanInfoWrapper != null) {
                PropertyDescriptor[] parentResult = parentBeanInfoWrapper
                        .getPropertyDescriptors();

                result = concatArraysToOneArray(implResult, parentResult);
                Arrays.sort(result, new Comparator<PropertyDescriptor>() {
                    public int compare(PropertyDescriptor pd1,
                            PropertyDescriptor pd2) {
                        return pd1.getName().compareTo(pd2.getName());
                    }

                    @Override
                    public boolean equals(Object o) {
                        return false;
                    }
                });
            } else {
                result = implResult;
            }
        } else {
            result = infoResult;
        }

        return result;
    }

    public MethodDescriptor[] getMethodDescriptors() {
        MethodDescriptor[] result = null;
        MethodDescriptor[] infoResult = null;

        if (info != null) {
            BeanInfo[] infos = info.getAdditionalBeanInfo();

            infoResult = info.getMethodDescriptors();

            if (infos != null) {
                for (BeanInfo additionalInfo : infos) {
                    if (infoResult == null) {
                        infoResult = additionalInfo.getMethodDescriptors();
                    } else {
                        infoResult = concatArraysToOneArray(infoResult,
                                additionalInfo.getMethodDescriptors());
                    }
                }
            }
        }

        if (info == null || infoResult == null) {
            MethodDescriptor[] implResult = impl.getMethodDescriptors();

            // merge with parent info
            if (parentBeanInfoWrapper != null) {
                MethodDescriptor[] parentResult = parentBeanInfoWrapper
                        .getMethodDescriptors();

                result = concatArraysToOneArray(implResult, parentResult);
            } else {
                result = implResult;
            }
        } else {
            result = infoResult;
        }

        if (result != null) {
            Arrays.sort(result, new Comparator<MethodDescriptor>() {
                public int compare(MethodDescriptor md1, MethodDescriptor md2) {
                    return md1.getName().compareTo(md2.getName());
                }

                @Override
                public boolean equals(Object o) {
                    return false;
                }
            });
        }

        return result;
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        EventSetDescriptor[] result = null;
        EventSetDescriptor[] infoResult = null;
        String defaultEventName = null;

        if (events != null) {
        	return events;
        }
        
        if (info != null) {
            BeanInfo[] infos = info.getAdditionalBeanInfo();
            int defIndex = info.getDefaultEventIndex();

            infoResult = info.getEventSetDescriptors();

            if (defIndex >= 0 && defIndex < infoResult.length) {
                defaultEventName = infoResult[defIndex].getName();
            }

            if (infos != null) {
                for (BeanInfo additionalInfo : infos) {
                    if (infoResult == null) {
                        infoResult = additionalInfo.getEventSetDescriptors();
                    } else {
                        infoResult = concatArraysToOneArray(infoResult,
                                additionalInfo.getEventSetDescriptors());
                    }
                }
            }
        }

        if (info == null || infoResult == null) {
            EventSetDescriptor[] implResult = impl.getEventSetDescriptors();
            
            // merge with parent info
            if (parentBeanInfoWrapper != null) {
                EventSetDescriptor[] parentResult = parentBeanInfoWrapper
                        .getEventSetDescriptors();
                Map<String, FeatureDescriptor> hm;

                if (defaultEventName == null) {
                	int parentDefaultIdx = parentBeanInfoWrapper.getDefaultEventIndex();
                	
                	if (parentDefaultIdx >= 0 &&
                			parentDefaultIdx < parentResult.length) {
                		defaultEventName = parentResult[parentDefaultIdx].getName();
                	}
                }

                hm = concatArraysUniqueByName(implResult, parentResult);
                result = hm.values().toArray(new EventSetDescriptor[hm.size()]);
                
                Arrays.sort(result, new Comparator<EventSetDescriptor>() {
                    public int compare(EventSetDescriptor esd1,
                            EventSetDescriptor esd2) {
                        return esd1.getName().compareTo(esd2.getName());
                    }

                    @Override
                    public boolean equals(Object o) {
                        return false;
                    }
                });
            } else {
                result = implResult;
            }
        } else {
            result = infoResult;
        }

        if (defaultEventName != null) {
            for (int i = 0; i < result.length; i++) {
                if (result[i].getName().equals(defaultEventName)) {
                    defaultEventIndex = i;
                }
            }
        }
        
        return result;
    }

    public BeanInfo[] getAdditionalBeanInfo() {
        BeanInfo[] result = null;

        if (info != null) {
            result = info.getAdditionalBeanInfo();
        }

        return result;
    }

    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptor result = null;

        if (info != null) {
            result = info.getBeanDescriptor();
        }

        if (info == null || result == null) {
            result = impl.getBeanDescriptor();
        }

        return result;
    }

    public Image getIcon(int iconKind) {
        Image result = null;

        if (info != null) {
            result = info.getIcon(iconKind);
        }

        return result;
    }

    public int getDefaultPropertyIndex() {
        int result = -1;

        if (info != null) {
            result = info.getDefaultPropertyIndex();
        } else if (parentBeanInfoWrapper != null) {
            result = parentBeanInfoWrapper.getDefaultPropertyIndex();
        }

        return result;
    }

    public int getDefaultEventIndex() {

    	if (events == null) {
            events = getEventSetDescriptors();
    	} 
    	
    	return defaultEventIndex;
    }

    void setParentToMerge(BeanInfoWrapper parentBeanInfoWrapper) {
        this.parentBeanInfoWrapper = parentBeanInfoWrapper;
    }

    private static Map<String, FeatureDescriptor> concatArraysUniqueByName(
            FeatureDescriptor[] childs, FeatureDescriptor[] parents) {
        Map<String, FeatureDescriptor> result = new HashMap<String, FeatureDescriptor>();

        if (childs != null) {
            for (FeatureDescriptor element : childs) {
                result.put(element.getName(), element);
            }
        }

        if (parents != null) {
            for (FeatureDescriptor element : parents) {
                if (result.get(element.getName()) == null) {
                    result.put(element.getName(), element);
                }
            }
        }

        return result;
    }

    private static PropertyDescriptor[] concatArraysToOneArray(
            PropertyDescriptor[] childs, PropertyDescriptor[] parents) {
        if (childs != null || parents != null) {
            Map<String, PropertyDescriptor> resultHM =
                    new HashMap<String, PropertyDescriptor>();

            if (childs != null) {
                for (PropertyDescriptor element : childs) {
                    resultHM.put(element.getName(), element);
                }
            }

            if (parents != null) {
                for (PropertyDescriptor parentPD : parents) {
                    PropertyDescriptor childPD =
                            resultHM.get(parentPD.getName());
                    
                    if (childPD == null) {
                        resultHM.put(parentPD.getName(), parentPD);
                    } else {
                        boolean childIsIndexed =
                                childPD instanceof IndexedPropertyDescriptor; 
                        boolean parentIsIndexed =
                                parentPD instanceof IndexedPropertyDescriptor;
                        boolean shouldBeMerged;
                        Class<?> childPropType = childPD.getPropertyType();
                        Class<?> childIdxPropType = childIsIndexed ?
                               ((IndexedPropertyDescriptor) childPD)
                                   .getIndexedPropertyType() : null;
                        Class<?> parentPropType = parentPD.getPropertyType();
                        Class<?> parentIdxPropType = parentIsIndexed ?
                               ((IndexedPropertyDescriptor) parentPD)
                                   .getIndexedPropertyType() : null;
                        
                        // additional consistency checks
                        // for child
                        assert (!childIsIndexed ? childPropType != null : true);
                        assert (childIsIndexed ?
                                childIdxPropType != null : true);
                        assert (childIsIndexed ? childPropType == null ||
                                childPropType.isArray() : true);
                        assert (childIsIndexed && childPropType != null ?
                                childPropType.getComponentType() ==
                                        childIdxPropType : true);
                        // for parent
                        assert (!parentIsIndexed ?
                                parentPropType != null : true);
                        assert (parentIsIndexed ?
                                parentIdxPropType != null : true);
                        assert (parentIsIndexed ? parentPropType == null ||
                                parentPropType.isArray() : true);
                        assert (parentIsIndexed && parentPropType != null ?
                                parentPropType.getComponentType() ==
                                        parentIdxPropType : true);

                        // let's check types
                        shouldBeMerged = childPropType != null &&
                                childPropType == parentPropType;
                        if (childIsIndexed) {
                            shouldBeMerged |=
                                childPropType == null
                                && parentPropType != null
                                && parentPropType.isArray()
                                && childIdxPropType ==
                                    parentPropType.getComponentType();                                
                        } 
                        if (parentIsIndexed) {
                            shouldBeMerged |=
                                parentPropType == null
                                && childPropType != null
                                && childPropType.isArray()
                                && parentIdxPropType ==
                                    childPropType.getComponentType();                                
                            
                        }
                        if (childIsIndexed && parentIsIndexed) {
                            shouldBeMerged |=
                                childIdxPropType == parentIdxPropType;
                        }
                        
                        // merge if descriptors are compatible
                        if (shouldBeMerged) {
                            try {
                                resultHM.put(parentPD.getName(),
                                        mergePDs(childPD, parentPD));
                            } catch (IntrospectionException e) {
                                // should be logged
                                //e.printStackTrace();
                            }
                        }
                        // we should not do anything if parent is not
                        // compatible with the child
                    }
                }
            }

            return resultHM.values().toArray(
                    new PropertyDescriptor[resultHM.size()]);
        }

        return null;
    }

    @SuppressWarnings("null")
    private static MethodDescriptor[] concatArraysToOneArray(
            MethodDescriptor[] childs, MethodDescriptor[] parents) {

        if (childs != null || parents != null) {
            HashMap<String, MethodDescriptor> result =
                    new HashMap<String, MethodDescriptor>(
                            childs.length + parents.length);

            if (childs != null) {
                for (MethodDescriptor md : childs) {
                    result.put(getMethodId(md.getMethod()), md);
                }
            }

            if (parents != null) {
                for (MethodDescriptor md : parents) {
                    String id = getMethodId(md.getMethod());
                    
                    if (!result.containsKey(id)) {
                        result.put(id, md);
                    }
                }
            }
            
            return result.values().toArray(new MethodDescriptor[result.size()]);
        }

        return null;
    }
    
    private static String getMethodId(Method m) {
        StringBuilder sb = new StringBuilder();
        
        sb.append(m.getName());
        sb.append(' ');
        
        for (Class<?> pType : m.getParameterTypes()) {
            sb.append(pType.getName());
            sb.append(' ');
        }
        return sb.toString();
    }

    private static EventSetDescriptor[] concatArraysToOneArray(
            EventSetDescriptor[] childs, EventSetDescriptor[] parents) {
        if (childs != null || parents != null) {
            Map<String, FeatureDescriptor> hm = concatArraysUniqueByName(
                    childs, parents);
            EventSetDescriptor[] result = new EventSetDescriptor[hm.size()];

            Iterator<String> iterator = hm.keySet().iterator();
            int idx = 0;

            while (iterator.hasNext()) {
                String name = iterator.next();

                result[idx++] = (EventSetDescriptor) hm.get(name);
            }

            return result;
        }

        return null;
    }
    
    /**
     * Merges two property descriptors. 
     * @param childPD child property descriptor
     * @param parentPD parent property descriptor
     * @return merged descriptor 
     * XXX 
     */
    private static PropertyDescriptor mergePDs(PropertyDescriptor childPD,
            PropertyDescriptor parentPD) throws IntrospectionException {

        assert childPD != null;
        assert childPD instanceof IndexedPropertyDescriptor ?
                (childPD.getPropertyType() != null ||
                        ((IndexedPropertyDescriptor) childPD)
                                .getIndexedPropertyType() != null)
                : childPD.getPropertyType() != null;
        assert parentPD != null;
        assert parentPD instanceof IndexedPropertyDescriptor ?
                (parentPD.getPropertyType() != null ||
                        ((IndexedPropertyDescriptor) parentPD)
                                .getIndexedPropertyType() != null)
                : parentPD.getPropertyType() != null;
        
        PropertyDescriptor result;

        if (childPD instanceof IndexedPropertyDescriptor) {
            result = new IndexedPropertyDescriptor(childPD.getName(),
                    childPD.getReadMethod(), childPD.getWriteMethod(),
                    ((IndexedPropertyDescriptor) childPD).getIndexedReadMethod(),
                    ((IndexedPropertyDescriptor) childPD).getIndexedWriteMethod());
        } else if (parentPD instanceof IndexedPropertyDescriptor) {
            result = new IndexedPropertyDescriptor(childPD.getName(),
                    childPD.getReadMethod(), childPD.getWriteMethod(),
                    ((IndexedPropertyDescriptor) parentPD).getIndexedReadMethod(),
                    ((IndexedPropertyDescriptor) parentPD).getIndexedWriteMethod());
        } else {
            result = new PropertyDescriptor(childPD.getName(),
                    childPD.getReadMethod(), childPD.getWriteMethod());
        }
        copy(result, childPD);

        if (result.getReadMethod() == null &&
                parentPD.getReadMethod() != null) {
            result.setReadMethod(parentPD.getReadMethod());
        }
        if (result.getWriteMethod() == null &&
                parentPD.getWriteMethod() != null) {
            result.setWriteMethod(parentPD.getWriteMethod());
        }
        
        if (parentPD instanceof IndexedPropertyDescriptor) {
            IndexedPropertyDescriptor result2 =
                    (IndexedPropertyDescriptor) result;
            IndexedPropertyDescriptor parentPD2 =
                    (IndexedPropertyDescriptor) parentPD;
            
            if (result2.getIndexedReadMethod() == null &&
                    parentPD2.getIndexedReadMethod() != null) {
                result2.setIndexedReadMethod(parentPD2.getIndexedReadMethod());
            }
            if (result2.getIndexedWriteMethod() == null &&
                    parentPD2.getIndexedWriteMethod() != null) {
                result2.setIndexedWriteMethod(parentPD2.getIndexedWriteMethod());
            }
        }

        return result;
    }
    
    private static void copy(PropertyDescriptor target, 
            PropertyDescriptor source) {
        target.setBound(source.isBound());
        target.setConstrained(source.isConstrained());
        target.setDisplayName(source.getDisplayName());
        target.setExpert(source.isExpert());
        target.setHidden(source.isHidden());
        target.setPreferred(source.isPreferred());
        target.setPropertyEditorClass(source.getPropertyEditorClass());
        target.setShortDescription(source.getShortDescription());
        // XXX Attributes?
    }
}
