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
import java.math.BigInteger;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Logger;

public class RemoteCalculatorClientBI {

    private static Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) {


        System.setProperty("sun.rmi.transport.logLevel", "VERBOSE");
        System.setProperty("sun.rmi.transport.tcp.logLevel", "VERBOSE");

        try {
            RMISocketFactory.setSocketFactory(new CipherRMISocketFactory());
        } catch (IOException e1) {
            e1.printStackTrace();
            System.exit(1);
        }

        String addr = "localhost"; //"10.100.2.229";

        CalculatorBI remoteCalc = null;
        Registry remoteRegistry = null;

        try {
            logger
                    .info("RemoteCalculatorClient locating RMI registry on remote host \""
                            + addr + "\".");
            remoteRegistry = LocateRegistry.getRegistry(addr);

            logger.info("RemoteCalculatorClient looking up service \""
                    + RemoteCalculatorBI.SERVICENAME + "\".");
            remoteCalc = (CalculatorBI) remoteRegistry
                    .lookup(RemoteCalculatorBI.SERVICENAME);

            BigInteger[][] a = new BigInteger[2][2];
            a[0][0] = new BigInteger("9");
            a[0][1] = new BigInteger("2");
            a[1][0] = new BigInteger("6");
            a[1][1] = new BigInteger("4");

            BigInteger[][] b = new BigInteger[2][2];
            b[0][0] = new BigInteger("1");
            b[0][1] = new BigInteger("4");
            b[1][0] = new BigInteger("1");
            b[1][1] = new BigInteger("5");

            remoteCalc.setMatrixA(a);
            remoteCalc.setMatrixB(b);

            System.out.println("Matrix A");
            System.out.println(Arrays.toString(a[0]));
            System.out.println(Arrays.toString(a[1]));
            System.out.println();

            System.out.println("Matrix B");
            System.out.println(Arrays.toString(b[0]));
            System.out.println(Arrays.toString(b[1]));
            System.out.println();

            System.out.println("Result Matrix (multiply)");
            System.out.println(Arrays
                    .toString(remoteCalc.multiplyMatrixAB()[0]));
            System.out.println(Arrays
                    .toString(remoteCalc.multiplyMatrixAB()[1]));
            System.out.println();

            System.out.println("Result Matrix (add)");
            System.out.println(Arrays.toString(remoteCalc.sumMatrixAB()[0]));
            System.out.println(Arrays.toString(remoteCalc.sumMatrixAB()[1]));
            System.out.println();

            System.out.println("Vector");
            Vector v = new Vector(4);
            v.add(new Double(50.21));
            v.add(new Double(2000.125));
            v.add(new Double(196.15));
            v.add(new Double(36.95));

            remoteCalc.setVectorA(v);

            System.out.println(v.toString());
            System.out.println();

            System.out.println("Standard Deviation: "
                    + remoteCalc.getDevProm()[0]);
            System.out.println("Average: " + remoteCalc.getDevProm()[1]);

        } catch (Exception e) {
            logger
                    .info("RemoteCalculatorClient problem with RemoteCalculator, exception:\n "
                            + e);
            e.printStackTrace();

        }
    }
}
