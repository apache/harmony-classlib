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
package java.rmi.activation;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public class ActivationID implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4608673054848209235L;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public ActivationID(Activator arg0) {
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
    public Remote activate(boolean force) throws ActivationException,
            UnknownObjectException, RemoteException {
        throw new UnsupportedOperationException();
    }
}
