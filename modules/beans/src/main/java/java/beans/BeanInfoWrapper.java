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

import java.awt.Image;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class BeanInfoWrapper implements BeanInfo {
    
    private BeanInfo info;
    private BeanInfoImpl impl;
    private BeanInfoWrapper parentBeanInfoWrapper;
    
    public BeanInfoWrapper(BeanInfo info, BeanInfoImpl impl) {
        this.info = info;
        this.impl = impl;
    }
    
    public PropertyDescriptor[] getPropertyDescriptors() {
        PropertyDescriptor[] result = null;
        PropertyDescriptor[] infoResult = null;
        
        if (info != null) {
            BeanInfo[] infos = info.getAdditionalBeanInfo();

            infoResult = info.getPropertyDescriptors();

            if (infos != null) {
                for (int i = 0; i < infos.length; ++i) {
                    BeanInfo additionalInfo = infos[i];

                    if (infoResult == null) {
                        infoResult = additionalInfo.getPropertyDescriptors();
                    } else {
                        infoResult = concatArraysToOneArray(infoResult,
                                additionalInfo.getPropertyDescriptors());
                    }
                }
            }
        }

        if (info == null || infoResult == null) {
            PropertyDescriptor[] implResult = impl.getPropertyDescriptors();

            // merge with parent info
            if (parentBeanInfoWrapper != null) {
                PropertyDescriptor[] parentResult =
                        parentBeanInfoWrapper.getPropertyDescriptors();
                Map<String, FeatureDescriptor> hm =
                        concatArraysUniqueByName(implResult, parentResult);

                Collection<FeatureDescriptor> values = hm.values();
                Iterator<FeatureDescriptor> iterator;
                int idx = 0;

                result = new PropertyDescriptor[values.size()];
                iterator = values.iterator();
                while (iterator.hasNext()) {
                    result[idx++] = (PropertyDescriptor) iterator.next();
                }

                Arrays.sort(result, new Comparator<PropertyDescriptor>() {
                    public int compare(PropertyDescriptor pd1,
                            PropertyDescriptor pd2) {
                        return pd1.getName().compareTo(pd2.getName());
                    }
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
                for (int i = 0; i < infos.length; ++i) {
                    BeanInfo additionalInfo = infos[i];

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
                MethodDescriptor[] parentResult =
                        parentBeanInfoWrapper.getMethodDescriptors();

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

        if (info != null) {
            BeanInfo[] infos = info.getAdditionalBeanInfo();

            infoResult = info.getEventSetDescriptors();

            if (infos != null) {
                for (int i = 0; i < infos.length; ++i) {
                    BeanInfo additionalInfo = infos[i];

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
                EventSetDescriptor[] parentResult =
                        parentBeanInfoWrapper.getEventSetDescriptors();
                Map<String, FeatureDescriptor> hm = concatArraysUniqueByName(
                        implResult, parentResult);
                
                Collection<FeatureDescriptor> values = hm.values();
                Iterator<FeatureDescriptor> iterator;
                int idx = 0;
                
                result = new EventSetDescriptor[values.size()];
                iterator = values.iterator();
                while (iterator.hasNext()) {
                    result[idx++] = (EventSetDescriptor) iterator.next();
                }
                
                Arrays.sort(result, new Comparator<EventSetDescriptor>() {
                    public int compare(EventSetDescriptor esd1,
                                       EventSetDescriptor esd2) {
                        return esd1.getName().compareTo(esd2.getName());
                    }
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
        }

        if (info == null || result == -1) {
            result = impl.getDefaultPropertyIndex();
        }

        return result;
    }

    public int getDefaultEventIndex() {
        int result = -1;

        if (info != null) {
            result = info.getDefaultEventIndex();
        }
        
        if (info == null || result == -1) {
            result = impl.getDefaultEventIndex();
        }

        return result;
    }
    
    void merge(BeanInfoWrapper parentBeanInfoWrapper) {
        this.parentBeanInfoWrapper = parentBeanInfoWrapper;
    }
    
    private static Map<String, FeatureDescriptor>
            concatArraysUniqueByName(FeatureDescriptor[] childs,
                                     FeatureDescriptor[] parents)
    {
        Map<String, FeatureDescriptor> result =
                new HashMap<String, FeatureDescriptor>();

        if (childs != null) {
            for (int i = 0; i < childs.length; ++i) {
                result.put(childs[i].getName(), childs[i]);
            }
        }

        if (parents != null) {
            for (int j = 0; j < parents.length; ++j) {
                if (result.get(parents[j].getName()) == null) {
                    result.put(parents[j].getName(), parents[j]);
                }
            }
        }

        return result;
    }
    
    private static PropertyDescriptor[] concatArraysToOneArray(
            PropertyDescriptor[] childs, PropertyDescriptor[] parents) {
        if (childs != null || parents != null) {
            Map<String, FeatureDescriptor> hm = concatArraysUniqueByName(
                    childs, parents);
            PropertyDescriptor[] result = new PropertyDescriptor[hm.size()];

            Iterator<String> iterator = hm.keySet().iterator();
            int idx = 0;

            while (iterator.hasNext()) {
                String name = (String) iterator.next();

                result[idx++] = (PropertyDescriptor) hm.get(name);
            }

            return result;
        } else {
            return null;
        }
    }
    
    private static MethodDescriptor[] concatArraysToOneArray(
            MethodDescriptor[] childs, MethodDescriptor[] parents) {
        MethodDescriptor[] result = null;
        
        if (childs != null || parents != null) {
            int resultLength = 0;

            if (childs != null) {
                resultLength = childs.length; 
            }

            if (parents != null) {
                resultLength += parents.length;
            }

            result = new MethodDescriptor[resultLength];

            if (childs != null) {
                for (int i = 0; i < childs.length; ++i) {
                    result[i] = childs[i];
                }
            }
            
            if (parents != null) {
                int shift = (childs == null) ? 0 : childs.length;

                for (int i = 0; i < parents.length; ++i) {
                    result[shift + i] = parents[i];
                }
            }
            
        }

        return result;
    }
    
    private static EventSetDescriptor[] concatArraysToOneArray(
            EventSetDescriptor[] childs, EventSetDescriptor[] parents) {
        if (childs != null || parents != null) {
            Map<String, FeatureDescriptor> hm =
                    concatArraysUniqueByName(childs, parents);
            EventSetDescriptor[] result = new EventSetDescriptor[hm.size()];

            Iterator<String> iterator = hm.keySet().iterator();
            int idx = 0;

            while (iterator.hasNext()) {
                String name = (String) iterator.next();

                result[idx++] = (EventSetDescriptor) hm.get(name);
            }

            return result;
        } else {
            return null;
        }
    }
}
