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
 * @version $Revision: 1.3.4.2 $
 */
package java.rmi.server;

import java.rmi.Remote;


/**
 * @com.intel.drl.spec_ref
 *
 * @author  Mikhail A. Markov
 * @version $Revision: 1.3.4.2 $
 * @deprecated Skeletons are not used by RMI framework since Java v1.2
 */
@Deprecated
public interface Skeleton {

    /**
     * @com.intel.drl.spec_ref
     */
    @Deprecated
    public Operation[] getOperations();

    /**
     * @com.intel.drl.spec_ref
     */
    @Deprecated
    public void dispatch(Remote impl, RemoteCall call, int opnum, long hash)
            throws Exception;
}
