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

public class RemoteHashClient {

    private static Logger logger = Logger.getAnonymousLogger();

    private static boolean checkPopulatedHashMap(String[] data, String[] key,
            String addr, RemoteMap remoteMap) {
        for (int i = 0; i < key.length; i++) {
            if (!getCall(key[i], addr, remoteMap).equals(data[i])) {
                return false;
            }
        }
        return true;
    }

    private static String getCall(String pattern, String addr,
            RemoteMap remoteMap) {
        String value = null;
        try {
            value = (String) remoteMap.get(pattern);

            if (value != null)
                logger.info("RemoteMapClient found at " + addr + ", value="
                        + value);
            else
                logger.info("RemoteMapClient could not find key " + addr + ".");

        } catch (RemoteException e) {
            logger.info("RemoteMapClient problem with " + addr
                    + ", exception:\n   " + e);
            e.printStackTrace();
        }
        return value;
    }

    public static void main(String[] args) {

        String addr = "10.100.2.229";

        RemoteMap remoteMap = null;
        Registry remoteRegistry = null;

        String[] numbers = { "one", "two", "three", "four", "five", "six",
                "seven" };

        String[] data = { "pride", "envy", "gluttony", "lust", "anger",
                "greed", "sloth" };

        try {
            System.out
                    .println("RemoteMapClient locating RMI registry on remote host \""
                            + addr + "\".");
            remoteRegistry = LocateRegistry.getRegistry(addr);
            logger.info("RemoteMapClient looking up service \""
                    + RemoteMap.SERVICENAME + "\".");
            remoteMap = (RemoteMap) remoteRegistry
                    .lookup(RemoteMap.SERVICENAME);
        } catch (Exception e) {
            System.out
                    .println("RemoteMapClient problem with RemoteMap, exception:\n   "
                            + e);
        }

        if (populateHashMap(data, numbers, addr, remoteMap)) {
            System.out
                    .println("RemoteMapClient HashMap successfully populated...");
        }

        // getCall("one",addr,remoteMap);
        if (data.length == sizeCall(remoteMap)) {
            logger.info("RemoteMapClient HashMap size correct...");
        }

        if (checkPopulatedHashMap(data, numbers, addr, remoteMap)) {
            logger.info("RemoteMapClient HashMap check passed...");
        }
    }

    private static boolean populateHashMap(String[] data, String[] key,
            String addr, RemoteMap remoteMap) {
        boolean retval = false;
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
            retval = putCall(key[i], data[i], addr, remoteMap);
        }
        return retval;
    }

    private static boolean putCall(String pattern1, String pattern2,
            String addr, RemoteMap remoteMap) {
        try {
            logger.info("RemoteMapClient inserting on " + addr + " key="
                    + pattern1 + ", value=" + pattern2);
            remoteMap.put(pattern1, pattern2);

        } catch (RemoteException e) {
            logger.info("RemoteMapClient problem with " + addr
                    + ", exception:\n   " + e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static int sizeCall(RemoteMap remoteMap) {
        int retval = -1;
        try {
            retval = remoteMap.size();
            logger.info("RemoteMapClient HashMap size: " + retval);

        } catch (RemoteException e) {
            e.printStackTrace();
            return retval;
        }
        return retval;
    }

}
