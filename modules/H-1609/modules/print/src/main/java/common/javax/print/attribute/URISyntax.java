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
 * @version $Revision: 1.4 $ 
 */ 

package javax.print.attribute;

import java.io.Serializable;
import java.net.URI;


public abstract class URISyntax implements Cloneable, Serializable {


    private URI uri;


    protected URISyntax (URI uriValue) {

        if (uriValue == null) {
            throw new NullPointerException("Null uri value");
        } else {
            uri = uriValue;
        }
    }


    public boolean equals(Object object) {

        if ((object instanceof URISyntax) &&
                uri.equals (((URISyntax) object).uri)) {
            return true;
        } else {
            return false;
        }
    }

    public URI getURI()  {
        return uri;
    }

    public int hashCode() {
        return uri.hashCode();
     }

    public String toString() {
        return uri.toString();
    }

}
