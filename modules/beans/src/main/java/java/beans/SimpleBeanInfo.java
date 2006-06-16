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
 * @version $Revision: 1.4.6.3 $
 */
package java.beans;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.4.6.3 $
 */

public class SimpleBeanInfo implements BeanInfo {

    /**
     * @com.intel.drl.spec_ref
     */
    public SimpleBeanInfo() {
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Image loadImage(String resourceName) {
        byte[] result = null;
        InputStream is = FileInputStream.class.getResourceAsStream(
                resourceName);
        
        if(is != null) {
            ArrayList<Byte> byteArrayList = new ArrayList<Byte>();
            
            byte b;
            try {
                while((b = (byte) is.read()) != -1) {
                    byteArrayList.add(new Byte(b));
                }
                
                result = new byte[byteArrayList.size()];
                
                Iterator<Byte> i = byteArrayList.iterator();
                int idx = 0;
                while(i.hasNext()) {
                    result[idx++] = i.next().byteValue();
                }
                
            } catch (IOException ioe) {
                byteArrayList.clear();
                System.out.println(ioe.getClass() + ": " + ioe.getMessage());
            } finally {
                   try {
                       is.close();
                   } catch (IOException ioe) {
                       System.out.println(ioe.getClass() + ": "
                               + ioe.getMessage());
                   }
            }
            
            return Toolkit.getDefaultToolkit().createImage(result);
        } else {
            return null;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public PropertyDescriptor[] getPropertyDescriptors() {
        return null;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public MethodDescriptor[] getMethodDescriptors() {
        return null;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public EventSetDescriptor[] getEventSetDescriptors() {
        return null;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BeanInfo[] getAdditionalBeanInfo() {
        return null;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BeanDescriptor getBeanDescriptor() {
        return null;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Image getIcon(int iconKind) {
        return null;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int getDefaultPropertyIndex() {
        return -1;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int getDefaultEventIndex() {
        return -1;
    }
}
