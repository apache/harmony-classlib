package ar.org.fitc.test.rmi.tunneling.integration;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Logger;

public class RemoteCalculatorServerBI {

    private static Logger logger = 
        Logger.getAnonymousLogger();
    
    public static void main(String[] args) {
    	
        System.setProperty("sun.rmi.transport.logLevel", "VERBOSE");
        System.setProperty("sun.rmi.transport.tcp.logLevel", "VERBOSE");
      
        Registry localRegistry = null;
        try {
            localRegistry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        
        try {
            RemoteCalculatorBI rc = new RemoteCalculatorBI();
            localRegistry.rebind(RemoteCalculatorBI.SERVICENAME,rc);
            logger.info("RemoteCalculator Server Ready.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
