package ar.org.fitc.test.rmi.tunneling.integration;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ProtocolTestServer {

    public static void main(String[] args) {
        Registry localRegistry = null;
        System.setProperty("sun.rmi.transport.logLevel", "VERBOSE");
        System.setProperty("sun.rmi.transport.tcp.logLevel", "VERBOSE");
        System.setProperty("sun.rmi.transport.tcp.readTimeout", "1000");
        
        try {
            localRegistry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        
        try {
            ProtocolTestByte rc = new ProtocolTestByte();
            localRegistry.rebind(ProtocolTest.SERVICENAME,rc);
            
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
