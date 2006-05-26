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
 * @version $Revision: 1.5.4.2 $
 */
package java.rmi.server;


/**
 * @com.intel.drl.spec_ref
 *
 * @author  Mikhail A. Markov
 * @version $Revision: 1.5.4.2 $
 */
public abstract class RemoteStub extends RemoteObject {

    private static final long serialVersionUID = -1585587260594494182L;

    /**
     * @com.intel.drl.spec_ref
     */
    protected RemoteStub(RemoteRef ref) {
        super(ref);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected RemoteStub() {
        super();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected static void setRef(RemoteStub stub, RemoteRef ref) {
        stub.ref = ref;
    }
}
