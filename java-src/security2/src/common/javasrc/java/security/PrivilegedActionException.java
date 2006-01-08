/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
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
* @author Alexander V. Astapchuk
* @version $Revision$
*/

package java.security;

/**
 * @com.intel.drl.spec_ref
 */
public class PrivilegedActionException extends Exception {

    /**
     * @com.intel.drl.spec_ref 
     */
    private static final long serialVersionUID = 4724086851538908602l;

    /**
     * @com.intel.drl.spec_ref 
     */
    private Exception exception;

    /**
     * @com.intel.drl.spec_ref 
     */
    public PrivilegedActionException(Exception ex) {
        super(ex);
        this.exception = ex;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public Exception getException() {
        return exception; // return ( getCause() instanceof Exception ) ?
        // getCause() : null;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public Throwable getCause() {
        return exception;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public String toString() {
        String s = getClass().getName();
        return exception == null ? s : s + ": " + exception;
    }

}