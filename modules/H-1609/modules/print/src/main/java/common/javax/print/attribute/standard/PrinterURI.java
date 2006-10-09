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

import java.net.URI;

import javax.print.attribute.PrintServiceAttribute;
import javax.print.attribute.URISyntax;


public final class PrinterURI extends URISyntax 
    implements PrintServiceAttribute {


    public PrinterURI(URI uri) {
        super(uri);
    }


    public boolean equals(Object object) {
        if ( !(object instanceof PrinterURI) ) {
            return false;
        }
        return super.equals(object);
    }

    public final Class getCategory() {
    /* 1.5 support requires the following changes
       Class<? extends Attribute> getCategory() { */
        return PrinterURI.class;
    }

    public final String getName() {
        return "printer-uri";
    }


}
