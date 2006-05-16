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

/**
 * @ar.org.fitc.spec_ref
 * 
 */
@Deprecated
public class RMISecurityException extends SecurityException {

    /**
     * 
     */
    private static final long serialVersionUID = -8433406075740433514L;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    @Deprecated
    public RMISecurityException(String name) {
        super(name);
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    @Deprecated
    public RMISecurityException(String name, String arg1) {
        // Constructor
        super(name);
        // arg1 ignored
    }
}
