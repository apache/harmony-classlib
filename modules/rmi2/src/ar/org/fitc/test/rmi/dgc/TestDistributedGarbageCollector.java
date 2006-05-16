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
package ar.org.fitc.test.rmi.dgc;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import junit.framework.TestCase;
import ar.org.fitc.test.rmi.server.rmiclassloader.EchoTest2;
import ar.org.fitc.test.rmi.server.rmiclassloader.IEcho;

public class TestDistributedGarbageCollector extends TestCase {

    private Registry reg;

    private File f;

    public static void main(String[] args) throws Exception {
        junit.textui.TestRunner.run(TestDistributedGarbageCollector.class);
    }

    public TestDistributedGarbageCollector(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        reg = LocateRegistry.createRegistry(1099);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        UnicastRemoteObject.unexportObject(reg, true);
        f.delete();
    }

    @SuppressWarnings( { "unused", "unchecked" })
    private void runGc() {
        WeakReference wr = new WeakReference(new Integer(2));
        while (wr.get() != null) {
            System.gc();
        }
    }

    public void testDGC() throws NotBoundException, IOException,
            InterruptedException {
        System.setProperty("java.rmi.server.leaseValue", "500");
        System.setProperty("sun.rmi.dgc.checkInterval", "500");
        System.setProperty("sun.rmi.dgc.server.gcInterval", "500");
        System.setProperty("java.rmi.server.logCalls", "true");
        System.setProperty("sun.rmi.dgc.logLevel", "VERBOSE");
        System.setProperty("sun.rmi.dgc.logCalls", "true");
        IEcho echoServer = new EchoTest2();
        Naming.rebind("//127.0.0.1:1099/Echo2", echoServer);
        lookRef();
        try {
            Thread.sleep(5 * 6000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        Naming.unbind("//127.0.0.1:1099/Echo2");
        try {
            Thread.sleep(15000 * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runGc();
        f = new File("done");
        assertTrue("unreference method was not called", f.exists());
    }

    private void lookRef() throws NotBoundException, IOException,
            InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(
                "/usr/java/jdk1.5.0_05/bin/java",
                "-cp",
                "/home/odemo/workspace/RMIJunitTesting/:/home/odemo/workspace/TestingUtilities/",
                "ar.org.fitc.test.rmi.dgc.EchoClient2");
        pb.redirectErrorStream();
        Process p = pb.start();
        p.destroy();
    }
}
