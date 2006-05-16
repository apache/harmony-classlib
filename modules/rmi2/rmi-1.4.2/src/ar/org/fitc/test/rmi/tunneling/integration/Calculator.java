package ar.org.fitc.test.rmi.tunneling.integration;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface Calculator extends Remote {

    public static final String SERVICENAME = "RemoteCalculator";
    
    public void setVectorA(Vector data) throws RemoteException;

    public void setMatrixA(int[][] data) throws RemoteException;
    
    public void setMatrixB(int[][] data) throws RemoteException;
    
    public int[][] sumMatrixAB() throws RemoteException;
    
    public int[][] multiplyMatrixAB() throws RemoteException;
    
    public double[] getDevProm() throws RemoteException;
    
}
