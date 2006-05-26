/*
 * Copyright 2005-2006 The Apache Software Foundation or its licensors, as applicable
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author  Mikhail A. Markov
 * @version $Revision: 1.7.4.1 $
 */
package java.rmi;

import java.io.IOException;


/**
 * @com.intel.drl.spec_ref
 *
 * @author  Mikhail A. Markov
 * @version $Revision: 1.7.4.1 $
 */
public class RemoteException extends IOException {
    private static final long serialVersionUID = -5148567311918794206L;

    /**
     * @com.intel.drl.spec_ref
     */
    public Throwable detail;

    /**
     * @com.intel.drl.spec_ref
     */
    public RemoteException(String msg, Throwable cause) {
        super(msg);
        detail = cause;
        initCause(null);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public RemoteException(String msg) {
        this(msg, null);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public RemoteException() {
        initCause(null);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Throwable getCause() {
        return detail;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getMessage() {
        if (detail == null) {
            return super.getMessage();
        } else {
            return super.getMessage() + "; nested exception is:\n\t" + detail;
        }
    }
}
