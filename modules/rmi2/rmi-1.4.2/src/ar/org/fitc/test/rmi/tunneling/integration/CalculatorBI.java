package ar.org.fitc.test.rmi.tunneling.integration;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface CalculatorBI extends Remote {

    public static final String SERVICENAME = "RemoteCalculator";
    
    public void setVectorA(Vector data) throws RemoteException;

    public void setMatrixA(BigInteger[][] data) throws RemoteException;
    
    public void setMatrixB(BigInteger[][] data) throws RemoteException;
    
    public BigInteger[][] sumMatrixAB() throws RemoteException;
    
    public BigInteger[][] multiplyMatrixAB() throws RemoteException;
    
    public BigDecimal[] getDevProm() throws RemoteException;


    
}