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

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;
import java.util.logging.Logger;

public class RemoteCalculatorServerBI {

    private static Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) {

        System.setProperty("sun.rmi.transport.logLevel", "VERBOSE");
        System.setProperty("sun.rmi.transport.tcp.logLevel", "VERBOSE");

        try {
            RMISocketFactory.setSocketFactory(new CipherRMISocketFactory());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            System.exit(1);
        }

        Registry localRegistry = null;
        try {

            localRegistry = LocateRegistry
                    .createRegistry(Registry.REGISTRY_PORT);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            RemoteCalculatorBI rc = new RemoteCalculatorBI();
            localRegistry.rebind(RemoteCalculatorBI.SERVICENAME, rc);
            logger.info("RemoteCalculator Server Ready.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
