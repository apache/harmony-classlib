package ar.org.fitc.test.rmi.tunneling.integration;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Hashtable;
import java.util.logging.Logger;

public class RemoteHashServer {

    private static Logger logger = 
        Logger.getAnonymousLogger();
    
    public static void main(String[] args) {
    
        try {
            System.setProperty("sun.rmi.transport.logLevel", "VERBOSE");
            System.setProperty("sun.rmi.transport.tcp.logLevel", "VERBOSE");
            System.setProperty("sun.rmi.transport.tcp.readTimeout", "1000");
            
            logger.info("RemoteMapServer creating a local RMI registry on the default port.");
            Registry localRegistry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT );

            logger.info("RemoteMapServer creating local object and remote adapter.");
            Hashtable hash = new Hashtable();
            RemoteMapAdapter adapter = new RemoteMapAdapter( hash );

            logger.info("RemoteMapServer publishing service \"" + RemoteMap.SERVICENAME + "\" in local registry.");
            localRegistry.rebind( RemoteMap.SERVICENAME, adapter );

            logger.info("Published RemoteMap as service \"" + RemoteMap.SERVICENAME + "\". Ready.");
         } catch (RemoteException e) {
            logger.fine("RemoteMapServer problem with remote object, exception:\n   " + e);
         }

    }
    
    
}
