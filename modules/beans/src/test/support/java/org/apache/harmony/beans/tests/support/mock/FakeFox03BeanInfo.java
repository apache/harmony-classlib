package org.apache.harmony.beans.tests.support.mock;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.beans.BeanDescriptor;
import java.beans.EventSetDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class FakeFox03BeanInfo extends SimpleBeanInfo {

    PropertyDescriptor propdescr[];

    EventSetDescriptor eventdescr[];

    public FakeFox03BeanInfo() {
        super();
        try {
            propdescr = new PropertyDescriptor[] { new PropertyDescriptor(
                    "Other", FakeFox03.class) };
            eventdescr = new EventSetDescriptor[] { new EventSetDescriptor(
                    SomeOtherObject.class, "SomeOther",
                    SomeOtherListener.class, "aMethod") };
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptor descriptor = new BeanDescriptor(FakeFox03.class);
        descriptor.setName("SomeOtherBean Descriptor");
        return descriptor;
    }

    public static final Image someOtherImage = new BufferedImage(1, 1,
            BufferedImage.TYPE_3BYTE_BGR);

    public Image getIcon(int iconKind) {
        return someOtherImage;

    }

    public int getDefaultPropertyIndex() {
        System.out
                .println("SomeOtherBeanBeanInfo.getDefaultPropertyIndex() called...");
        return propdescr.length - 1;
    }

    public PropertyDescriptor[] getPropertyDescriptors() {
        return propdescr;
    }

    public int getDefaultEventIndex() {
        return eventdescr.length - 1;
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        return eventdescr;
    }

    private interface SomeOtherListener {
        public void aMethod(SomeOtherEvent s);
    }

    private class SomeOtherObject {
        public void addFakeFox03BeanInfo$SomeOtherListener(
                SomeOtherListener l) {
        }

        public void removeFakeFox03BeanInfo$SomeOtherListener(
                SomeOtherListener l) {
        }
    }

    private class SomeOtherEvent {
    }
}
