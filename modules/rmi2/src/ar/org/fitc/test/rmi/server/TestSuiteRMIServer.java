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
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */
package ar.org.fitc.test.rmi.server;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteRMIServer {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestSuiteRMIServer.suite());
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for ar.org.fitc.test.rmi.server");

        suite.addTestSuite(TestRMIClassLoader.class);
        suite.addTestSuite(TestRMISocketFactory.class);
        suite.addTestSuite(TestUID.class);
        //      suite.addTestSuite(TestOperation.class);
        suite.addTestSuite(TestUnicastRemoteObject.class);
        suite.addTestSuite(TestObjId.class);
        suite.addTestSuite(TestRemoteObject.class);
        suite.addTestSuite(TestRemoteObjectInvocationHandler.class);
        //      suite.addTestSuite(TestLogStream.class);
        suite.addTestSuite(TestRemoteServer.class);

        return suite;
    }

}
