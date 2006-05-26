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
 * @author  Victor A. Martynov
 * @version $Revision: 1.1.2.5 $
 */
package org.apache.harmony.rmi.activation;

import java.rmi.activation.ActivationGroup;
import java.rmi.activation.ActivationSystem;

import org.apache.harmony.rmi.JavaInvoker;
import org.apache.harmony.rmi.common.SubProcess;

import junit.framework.TestCase;


/**
 * StartupShutdownTest
 *
 * @author  Victor A. Martynov
 * @version $Revision: 1.1.2.5 $
 */
public class StartupShutdownTest extends TestCase {

    SubProcess rmid;
    SubProcess rmiregistry;

    public void testStartup() throws Exception {
        try {
            rmid = JavaInvoker.invokeSimilar((String[]) null,
                    "org.apache.harmony.rmi.activation.Rmid",
                    (String[]) null, true, true);
            rmid.pipeError();
            rmid.pipeInput();
            rmid.closeOutput();
            Thread.sleep(5000);
            ActivationSystem as = ActivationGroup.getSystem();

            rmid.destroy();
        } catch (Throwable t) {
            System.out.println("Unexpected exception: " + t);
            t.printStackTrace();
            fail("Unexpected exception: " + t);
        }
    }
}
