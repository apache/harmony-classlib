package ar.org.fitc.test.rmi.tunneling.integration;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ProtocolTestByte extends UnicastRemoteObject implements ProtocolTest {

    private byte myData;
    
    public ProtocolTestByte() throws RemoteException {
        super();
    }

    public void setByte(byte data) throws RemoteException {
        myData = data;
        System.out.println(data);
    }

}
