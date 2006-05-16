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
package java.rmi;

import java.io.IOException;
import java.io.Serializable;

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public final class MarshalledObject implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8988374069173025854L;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public MarshalledObject(Object obj) throws IOException {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public Object get() throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException();
    }
}
