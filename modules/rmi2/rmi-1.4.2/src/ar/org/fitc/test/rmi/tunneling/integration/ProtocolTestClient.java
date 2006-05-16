package ar.org.fitc.test.rmi.tunneling.integration;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ProtocolTestClient {

    public static void main(String[] args) {
        
        String addr = "127.0.0.1";
        
        ProtocolTest pt = null;
        Registry remoteRegistry = null;
        
        
        byte b = 0x43;
        
        try {
            remoteRegistry = LocateRegistry.getRegistry(addr);
            pt = (ProtocolTest) remoteRegistry.lookup(ProtocolTest.SERVICENAME);
            
            pt.setByte(b);
            pt.setByte((byte) 0x12);
            
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        
    }

}
