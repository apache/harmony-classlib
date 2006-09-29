/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
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
 * @author  Victor A. Martynov
 * @version $Revision: 1.7.2.3 $
 */
package java.rmi.activation;

import java.rmi.RemoteException;


/**
 * @com.intel.drl.spec_ref
 *
 * @author  Victor A. Martynov
 * @version $Revision: 1.7.2.3 $
 */
public class ActivateFailedException extends RemoteException {

    private static final long serialVersionUID = 4863550261346652506L;

    /**
     * @com.intel.drl.spec_ref
     */
    public ActivateFailedException(String message, Exception nested_exception) {
        super(message, nested_exception);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public ActivateFailedException(String message) {
        super(message);
    }
}
