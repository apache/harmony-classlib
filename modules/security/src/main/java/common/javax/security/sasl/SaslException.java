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

package javax.security.sasl;

import java.io.IOException;

/**
 * @com.intel.drl.spec_ref
 * 
 */
public class SaslException extends IOException {

    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = 4579784287983423626L;

    /**
     * @com.intel.drl.spec_ref
     * 
     * Serialized field for storing initial cause
     */
    private Throwable _exception;

    /**
     * @com.intel.drl.spec_ref
     */
    public SaslException() {
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public SaslException(String detail) {
        super(detail);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public SaslException(String detail, Throwable ex) {
        super(detail);
        if (ex != null) {
            super.initCause(ex);
            _exception = ex;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Throwable getCause() {
        return _exception;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Throwable initCause(Throwable cause) {
        super.initCause(cause);
        _exception = cause;
        return this;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String toString() {
        if (_exception == null) {
            return super.toString();
        } else {
            StringBuffer sb = new StringBuffer(super.toString());
            sb.append(", caused by: ");
            sb.append(_exception.toString());
            return sb.toString();
        }
    }
}