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
/** 
 * @author Igor A. Pyankov 
 * @version $Revision: 1.2 $ 
 */ 

package javax.print;

import javax.print.attribute.standard.Destination;
import javax.print.attribute.standard.DocumentName;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.RequestingUserName;

import junit.framework.TestCase;

public class GetDefaultAttributeValueTest extends TestCase {

    public void testGetDefaultAttributeValue() {
        System.out.println("============= START testGetDefaultAttributeValue ================");

        PrintService[] services;
        Object probe;
        Class claz;
        services = PrintServiceLookup.lookupPrintServices(null, null);
        TestUtil.checkServices(services);
        try {
            for (int i = 0, ii = services.length; i < ii; i++) {
                System.out.println("------------------" + services[i].getName()
                        + "-------------------");
                probe = services[i].getDefaultAttributeValue(claz = Media.class);
                System.out.println(claz + ": " + probe);
                probe = services[i].getDefaultAttributeValue(claz = Destination.class);
                System.out.println(claz + ": " + probe);
                probe = services[i].getDefaultAttributeValue(claz = JobName.class);
                System.out.println(claz + ": " + probe);
                probe = services[i].getDefaultAttributeValue(claz = RequestingUserName.class);
                System.out.println(claz + ": " + probe);
                probe = services[i].getDefaultAttributeValue(claz = DocumentName.class);
                System.out.println(claz + ": " + probe);
                try {
                    probe = services[i].getDefaultAttributeValue(null);
                    fail("NullPointerException must be thrown - the category is null");
                } catch (Exception e) {
                    // OK
                }
                try {
                    probe = services[i].getDefaultAttributeValue(this.getClass());
                    fail("IllegalArgumentException must be thrown - category is not a Class that implements interface Attribute.");
                } catch (IllegalArgumentException e) {
                    // OK
                }
            }
        } catch (RuntimeException e) {
            fail("Exception "+e+" thrown");
        }

        System.out.println("============= END testGetDefaultAttributeValue ================");
    }

}