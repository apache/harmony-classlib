package ar.org.fitc.test.rmi.tunneling.integration;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ProtocolTest extends Remote {

    public static final String SERVICENAME = "ProtocolTest";

    public void setByte(byte data) throws RemoteException;
    
}