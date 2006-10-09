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
 * @author Elena V. Sayapina 
 * @version $Revision: 1.5 $ 
 */ 

package javax.print.attribute.standard;

import javax.print.attribute.DocAttribute;
import javax.print.attribute.EnumSyntax;
import javax.print.attribute.PrintJobAttribute;
import javax.print.attribute.PrintRequestAttribute;

/*
 * Table values are obtained from RFC2911: Internet Printing Protocol/1.1: 
 * Model and Semantics, section 4.2.10, http://ietf.org/rfc/rfc2911.txt?number=2911
 */

public final class OrientationRequested extends EnumSyntax 
    implements DocAttribute, PrintJobAttribute, PrintRequestAttribute {


    public static final OrientationRequested
        PORTRAIT = new OrientationRequested(3);

    public static final OrientationRequested 
        LANDSCAPE = new OrientationRequested(4);
   
    public static final OrientationRequested 
        REVERSE_LANDSCAPE = new OrientationRequested(5);

    public static final OrientationRequested 
        REVERSE_PORTRAIT  = new OrientationRequested(6);


    private static final OrientationRequested[] enumValueTable = {

            PORTRAIT,
            LANDSCAPE,
            REVERSE_LANDSCAPE,
            REVERSE_PORTRAIT
    };
    
    private static final String[] stringTable = { "portrait",
                                                  "landscape",
                                                  "reverse-landscape",
                                                  "reverse-portrait" };
    

    protected OrientationRequested(int value) {
        super(value);
    }
    
    
    public final Class getCategory() {
    /* 1.5 support requires the following changes 
       Class<? extends Attribute> getCategory() { */
        return OrientationRequested.class;
    }
    
    protected EnumSyntax[] getEnumValueTable() {
        return enumValueTable;
    }
    
    public final String getName() {
        return "orientation-requested";
    }
    
    protected int getOffset() {
        return 3;
    } 
    
    protected String[] getStringTable() {
        return stringTable;
    }
    

}
