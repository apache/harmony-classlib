/* 
*  Copyright 2005 The Apache Software Foundation or its licensors, as applicable. 
* 
*  Licensed under the Apache License, Version 2.0 (the "License"); 
*  you may not use this file except in compliance with the License. 
*  You may obtain a copy of the License at 
* 
*    http://www.apache.org/licenses/LICENSE-2.0 
* 
*  Unless required by applicable law or agreed to in writing, software 
*  distributed under the License is distributed on an "AS IS" BASIS, 
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
*  See the License for the specific language governing permissions and 
*  limitations under the License. 
*/
package org.apache.harmony.rmi.internal.utils;

/*
 * NOTE: 
 * This class has been modified in order to support 
 * Java VM 1.4.2 using the javac's target jsr14 
 */

public final class PropertiesReader {

    /** To prevent instantiation */
    private PropertiesReader() {}
    
    public final static int readInt(String name, int defaultValue) {
        int ret;
        try {
            ret = Integer.parseInt(System.getProperty(name));
            if (ret < 0) {
                // TODO LOG HERE
                ret = defaultValue;
            }
        } catch (NumberFormatException e) {
            // TODO LOG HERE
            ret = defaultValue;
        }
        return ret;
    }

    public final static long readLong(String name, long defaultValue) {
        long ret;
        try {
            ret = new Long(System.getProperty(name));
            if (ret < 0) {
                // TODO LOG HERE
                ret = defaultValue;
            }
        } catch (NumberFormatException e) {
            // TODO LOG HERE
            ret = defaultValue;
        }
        return ret;    
    }
    
    public final static boolean readBoolean(String name, boolean defaultValue) {
        boolean ret;
        try {
            ret = Boolean.valueOf(System.getProperty(name));
        } catch (Exception e) {   // TODO: exception name
            // TODO LOG HERE
            ret = defaultValue;
        }
        return ret;
    }
    
    public final static String readString (String name) {
        String ret = System.getProperty(name);
        if (ret == null) {
            // TODO LOG HERE
        }
        return ret; 
    }
}
