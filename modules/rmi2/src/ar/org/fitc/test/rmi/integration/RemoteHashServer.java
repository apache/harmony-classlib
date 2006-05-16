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
package ar.org.fitc.test.rmi.integration;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Hashtable;
import java.util.logging.Logger;

public class RemoteHashServer {

    private static Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) {

        try {
            System.setProperty("sun.rmi.transport.logLevel", "VERBOSE");
            System.setProperty("sun.rmi.transport.tcp.logLevel", "VERBOSE");
            System.setProperty("sun.rmi.transport.tcp.readTimeout", "1000");
            
            logger.info("RemoteMapServer creating a local RMI registry on the default port.");
            Registry localRegistry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT );

            logger
                    .info("RemoteMapServer creating local object and remote adapter.");
            Hashtable hash = new Hashtable();
            RemoteMapAdapter adapter = new RemoteMapAdapter(hash);

            logger.info("RemoteMapServer publishing service \""
                    + RemoteMap.SERVICENAME + "\" in local registry.");
            localRegistry.rebind(RemoteMap.SERVICENAME, adapter);

            logger.info("Published RemoteMap as service \""
                    + RemoteMap.SERVICENAME + "\". Ready.");
        } catch (RemoteException e) {
            logger
                    .fine("RemoteMapServer problem with remote object, exception:\n   "
                            + e);
        }

    }

}
