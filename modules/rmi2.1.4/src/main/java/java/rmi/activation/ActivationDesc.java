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
import java.rmi.MarshalledObject;

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public final class ActivationDesc implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7455834104417690957L;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public ActivationDesc(String arg0, String className, MarshalledObject data)
            throws ActivationException {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public ActivationDesc(String arg0, String className, MarshalledObject data,
            boolean arg3) throws ActivationException {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public ActivationDesc(ActivationGroupID groupID, String className,
            String location, MarshalledObject data) {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public ActivationDesc(ActivationGroupID groupID, String className,
            String location, MarshalledObject data, boolean restart) {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public ActivationGroupID getGroupID() {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public boolean getRestartMode() {
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
    public String getLocation() {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public String getClassName() {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public MarshalledObject getData() {
        throw new UnsupportedOperationException();
    }
}
