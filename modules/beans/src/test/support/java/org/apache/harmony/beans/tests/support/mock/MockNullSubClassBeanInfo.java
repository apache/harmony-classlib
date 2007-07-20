package org.apache.harmony.beans.tests.support.mock;

import java.beans.EventSetDescriptor;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class MockNullSubClassBeanInfo extends SimpleBeanInfo{
    public PropertyDescriptor[] getPropertyDescriptors(){
        return null;
    }
    
    public MethodDescriptor[] getMethodDescriptors(){
        return null;
    }
    
    public EventSetDescriptor[] getEventSetDescriptors(){
        return null;
    }
    
    public int getDefaultEventIndex(){
        return Integer.MIN_VALUE;
    }
    
    public int  getDefaultPropertyIndex(){
        return Integer.MIN_VALUE;
    }
}
