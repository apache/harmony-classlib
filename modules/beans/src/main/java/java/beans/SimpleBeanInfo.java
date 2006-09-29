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
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class SimpleBeanInfo implements BeanInfo {

    public SimpleBeanInfo() {
    }

    public Image loadImage(String resourceName) {
        byte[] result = null;
        InputStream is = FileInputStream.class
                .getResourceAsStream(resourceName);

        if (is != null) {
            ArrayList<Byte> byteArrayList = new ArrayList<Byte>();

            byte b;
            try {
                while ((b = (byte) is.read()) != -1) {
                    byteArrayList.add(new Byte(b));
                }

                result = new byte[byteArrayList.size()];

                Iterator<Byte> i = byteArrayList.iterator();
                int idx = 0;
                while (i.hasNext()) {
                    result[idx++] = i.next().byteValue();
                }

            } catch (IOException ioe) {
                byteArrayList.clear();
                System.out.println(ioe.getClass() + ": " + ioe.getMessage()); //$NON-NLS-1$
            } finally {
                try {
                    is.close();
                } catch (IOException ioe) {
                    System.out.println(ioe.getClass() + ": " //$NON-NLS-1$
                            + ioe.getMessage());
                }
            }

            return Toolkit.getDefaultToolkit().createImage(result);
        }
        return null;
    }

    public PropertyDescriptor[] getPropertyDescriptors() {
        return null;
    }

    public MethodDescriptor[] getMethodDescriptors() {
        return null;
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        return null;
    }

    public BeanInfo[] getAdditionalBeanInfo() {
        return null;
    }

    public BeanDescriptor getBeanDescriptor() {
        return null;
    }

    public Image getIcon(int iconKind) {
        return null;
    }

    public int getDefaultPropertyIndex() {
        return -1;
    }

    public int getDefaultEventIndex() {
        return -1;
    }
}
