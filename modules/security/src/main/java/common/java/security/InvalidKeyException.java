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
* @author Vera Y. Petrashkova
* @version $Revision$
*/

package java.security;

/**
 * @com.intel.drl.spec_ref
 * 
 */
public class InvalidKeyException extends KeyException {

    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = 5698479920593359816L;

    /**
     * @com.intel.drl.spec_ref
     */
    public InvalidKeyException(String msg) {
        super(msg);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public InvalidKeyException() {
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public InvalidKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public InvalidKeyException(Throwable cause) {
        super(cause);
    }
}