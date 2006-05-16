/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the License);
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an AS IS BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package ar.org.fitc.test.rmi.integration.fase2.test;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

import ar.org.fitc.test.rmi.integration.fase2.LogRemoteImpl;
import ar.org.fitc.test.rmi.integration.fase2.executor.AbstractServerExecutor;
import ar.org.fitc.test.rmi.integration.fase2.executor.ServerExecutor;
import ar.org.fitc.test.rmi.integration.fase2.socketfactory.CipherRMISocketFactory;
import ar.org.fitc.test.rmi.integration.fase2.socketfactory.GZipRMISocketFactory;


/**
 * Testing class for RMI Socket Factories.
 * 
 * @author Osvaldo Demo
 * 
 * @version 1.0
 */
public class RMISocketFactoryTestCase extends ExecutorTestCase {
    
    /**
     * A <code>LogRemoteImpl</code>.
     */
    public LogRemoteImpl remote;
    
    public Registry reg;

    public ServerExecutor exportObj;
    public ServerExecutor stub;
    
    public RMIClientSocketFactory crsk;
    
    public RMIServerSocketFactory srsk;
    
    /**
     * Default constructor.
     *
     */
    public RMISocketFactoryTestCase() {
        super();
    }
    
    /**
     * Constructs a <code>RMISocketFactoryTestCase</code> with a name. 
     * 
     * @param arg0 a name.
     */
    public RMISocketFactoryTestCase(String arg0) {
        super(arg0);
    }
    
    @SuppressWarnings("unchecked")
    protected void setUp() throws Exception {
        try {
            reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        } catch (RemoteException e) {
        }
        reg = LocateRegistry.getRegistry();
        
        exportObj = new AbstractServerExecutor() {
          
            private static final long serialVersionUID = 3635772699140965835L;

            @Override
            public Object execute(Object... arguments) throws RemoteException {
                System.out.println("\t\tRuning remote object with Custom Sockets");
                return true;
            }
        };
        super.setUp();
    }
    
    protected void tearDown() throws Exception {
        exportObj = null;
    }
    
    public void testCipherSocketFactory() throws RemoteException {
        srsk = new CipherRMISocketFactory();
        crsk = new CipherRMISocketFactory();
        stub = (ServerExecutor) UnicastRemoteObject.exportObject(exportObj, 0, crsk, srsk);
        assertTrue((Boolean)executor.execute(stub, 10));
    }
    
    public void testZIPSocketFactory() throws RemoteException {
        srsk = new GZipRMISocketFactory();
        crsk = new GZipRMISocketFactory();
        stub = (ServerExecutor) UnicastRemoteObject.exportObject(exportObj, 0, crsk, srsk);    
        assertTrue((Boolean)executor.execute(stub, 10));
    }
    
    public void testZIPSocketFactoryError() throws RemoteException {
        srsk = new GZipRMISocketFactory();
        crsk = new GZipRMISocketFactory();
//        crsk = new CipherRMISocketFactory();
        try {
            stub = (ServerExecutor) UnicastRemoteObject.exportObject(exportObj, 0, crsk, srsk);
            fail("No error reported");
        } catch (RemoteException e) {
            
        }
    }
    
}


