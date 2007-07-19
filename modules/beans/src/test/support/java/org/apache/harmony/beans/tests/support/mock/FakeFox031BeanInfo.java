package org.apache.harmony.beans.tests.support.mock;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class FakeFox031BeanInfo extends SimpleBeanInfo{
    public FakeFox031BeanInfo() {
        super();
    }

    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptor descriptor = new BeanDescriptor(FakeFox031.class);
        descriptor.setName("DummyBean Descriptor");
        return descriptor;
    }

    public BeanInfo[] getAdditionalBeanInfo() {

        return new BeanInfo[] { new FakeFox03BeanInfo() };

    }

    public PropertyDescriptor[] getPropertyDescriptors() {
        return new PropertyDescriptor[] {};
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        return new EventSetDescriptor[] {};
    }

    PropertyDescriptor propdescr[];

    EventSetDescriptor eventdescr[];
}
