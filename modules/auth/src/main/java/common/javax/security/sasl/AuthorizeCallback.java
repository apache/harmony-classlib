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
* @author Vera Y. Petrashkova
* @version $Revision$
*/

package javax.security.sasl;

import java.io.Serializable;
import javax.security.auth.callback.Callback;

/**
 * @com.intel.drl.spec_ref
 * 
 */

public class AuthorizeCallback implements Callback, Serializable {
    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = -2353344186490470805L;

    /**
     * @com.intel.drl.spec_ref
     * 
     * Serialized field for storing authenticationID.
     */
    private final String authenticationID;

    /**
     * @com.intel.drl.spec_ref
     * 
     * Serialized field for storing authorizationID.
     */
    private final String authorizationID;

    /**
     * @com.intel.drl.spec_ref
     * 
     * Serialized field for storing authorizedID.
     */
    private String authorizedID;

    /**
     * @com.intel.drl.spec_ref
     * 
     * Store authorized Serialized field.
     */
    private boolean authorized;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public AuthorizeCallback(String authnID, String authzID) {
        authenticationID = authnID;
        authorizationID = authzID;
        authorizedID = authzID;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public String getAuthenticationID() {
        return authenticationID;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public String getAuthorizationID() {
        return authorizationID;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public String getAuthorizedID() {
        return (authorized ? authorizedID : null);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public boolean isAuthorized() {
        return authorized;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public void setAuthorized(boolean ok) {
        authorized = ok;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public void setAuthorizedID(String id) {
        if (id != null) {
            authorizedID = id;
        }
    }
}