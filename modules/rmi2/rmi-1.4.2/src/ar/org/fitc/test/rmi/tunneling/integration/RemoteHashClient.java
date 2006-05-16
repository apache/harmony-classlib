package ar.org.fitc.test.rmi.tunneling.integration;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;
import java.util.logging.Logger;

import ar.org.fitc.test.rmi.tunneling.testrunner.Util;

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
                logger.info("RemoteMapClient found at " + addr
                        + ", value=" + value);
            else
                logger.info("RemoteMapClient could not find key " + addr
                        + ".");

        } catch (RemoteException e) {
            logger.info("RemoteMapClient problem with " + addr
                    + ", exception:\n   " + e);
            e.printStackTrace();
        }
        return value;
    }

    /*
     * arg[0] ---> mode
     * arg[1] ---> proxy ip number
     * arg[2] ---> proxy port number
     * arg[3] ---> client ip number
     * arg[4] ---> rmi package supplier
     * 
     */
    
    public static void main(String[] args) {
        
        if (args.length != 5) {
            System.err.println("5 Parameters needed...");
            System.exit(1);
        }

        System.setProperty("sun.rmi.transport.logLevel", "VERBOSE");
        System.setProperty("sun.rmi.transport.tcp.logLevel", "VERBOSE");
        System.setProperty("sun.rmi.transport.tcp.readTimeout", "1000");

        System.setProperty("http.proxyHost",args[1]);
        System.setProperty("http.proxyPort",args[2]);        
        
        if (args[4].equalsIgnoreCase("sun")) {
            try {
                RMISocketFactory.setSocketFactory(Util.getSunSocketFactory(args[0]));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (args[4].equalsIgnoreCase("itc")) {
            Util.setITCSocketFactoryMode(args[0]);
        }
        
        String addr = args[3];
        if (addr == null) {
            addr = "10.100.2.246"; 
        }

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
