package ar.org.fitc.test.rmi.tunneling.integration;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Logger;

import ar.org.fitc.test.rmi.tunneling.testrunner.Util;

public class RemoteCalculatorClient {
    
    protected static Logger logger = Logger.getAnonymousLogger();
    
    /*
     * arg[0] ---> mode
     * arg[1] ---> proxy ip number
     * arg[2] ---> proxy port number
     * arg[3] ---> client ip number
     * arg[4] ---> rmi package supplier
     * 
     */
    
    @SuppressWarnings({"unchecked"})
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
        
        Calculator remoteCalc = null;
        Registry remoteRegistry = null;
        
        try {
            logger.info("RemoteCalculatorClient locating RMI registry on remote host \""+ addr + "\".");
            remoteRegistry = LocateRegistry.getRegistry(addr);
            
            logger.info("RemoteCalculatorClient looking up service \""+ RemoteCalculator.SERVICENAME + "\".");
            remoteCalc = (Calculator) remoteRegistry.lookup(RemoteCalculator.SERVICENAME);
            
            int[][] a = new int[][] {{9,2},{6,4}};
            int[][] b = new int[][] {{1,4},{1,5}}; 
            
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
            System.out.println(Arrays.toString(remoteCalc.multiplyMatrixAB()[0]));
            System.out.println(Arrays.toString(remoteCalc.multiplyMatrixAB()[1]));
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
            
            System.out.println("Standard Deviation: "+remoteCalc.getDevProm()[0]);
            System.out.println("Average: "+remoteCalc.getDevProm()[1]);
            
        } catch (Exception e) {
            logger.info("RemoteCalculatorClient problem with RemoteCalculator, exception:\n "+ e);
        }
    }
}
