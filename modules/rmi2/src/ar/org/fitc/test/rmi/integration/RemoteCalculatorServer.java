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
import java.util.logging.Logger;

public class RemoteCalculatorServer {

    private static Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) {

        System.setProperty("sun.rmi.transport.logLevel", "VERBOSE");
        System.setProperty("sun.rmi.transport.tcp.logLevel", "VERBOSE");

        Registry localRegistry = null;
        try {

            localRegistry = LocateRegistry
                    .createRegistry(Registry.REGISTRY_PORT);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            RemoteCalculator rc = new RemoteCalculator();
            localRegistry.rebind(RemoteCalculator.SERVICENAME, rc);
            logger.info("RemoteCalculator Server Ready.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        
        try {
            Thread.sleep(3*60*6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
