package org.apache.harmony.beans.tests.support.mock;

import java.beans.EventSetDescriptor;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class MockNullSuperClassBeanInfo extends SimpleBeanInfo{
    public PropertyDescriptor[] getPropertyDescriptors(){
        return null;
    }
    
    public MethodDescriptor[] getMethodDescriptors(){
        return null;
    }
    
    public EventSetDescriptor[] getEventSetDescriptors(){
        return null;
    }
}
