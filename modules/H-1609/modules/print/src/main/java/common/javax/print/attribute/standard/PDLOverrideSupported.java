/*
 *  Copyright 2005 - 2006 The Apache Software Foundation or its licensors, as applicable.
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
 * @author Elena V. Sayapina 
 * @version $Revision: 1.5 $ 
 */ 

package javax.print.attribute.standard;

import javax.print.attribute.EnumSyntax;
import javax.print.attribute.PrintServiceAttribute;


public class PDLOverrideSupported extends EnumSyntax 
    implements PrintServiceAttribute {


    public static final PDLOverrideSupported
        NOT_ATTEMPTED = new PDLOverrideSupported(0);

    public static final PDLOverrideSupported
        ATTEMPTED = new PDLOverrideSupported(1);


    private static final PDLOverrideSupported[] enumValueTable = {

            NOT_ATTEMPTED,
            ATTEMPTED
    };

    private static final String[] stringTable = { "not-attempted",
                                                  "attempted" };


    protected PDLOverrideSupported(int value) {
        super(value);
    }


    public final Class getCategory() {
    /* 1.5 support requires the following changes
       Class<? extends Attribute> getCategory() { */
        return PDLOverrideSupported.class;
    }

    protected EnumSyntax[]  getEnumValueTable() {
        return (EnumSyntax[]) enumValueTable.clone();
    }

    public final String getName() {
        return "pdl-override-supported";
    }

    protected String[] getStringTable() {
        return (String[]) stringTable.clone();
    }


}
