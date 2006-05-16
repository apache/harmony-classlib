package ar.org.fitc.test.rmi.tunneling.integration;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteMap extends Remote {

    public static final String SERVICENAME = "RemoteHashMap";

    public int size() throws RemoteException;

    public boolean isEmpty() throws RemoteException;

    public boolean containsKey(Object key) throws RemoteException;

    public boolean containsValue(Object value) throws RemoteException;

    public Object get(Object key) throws RemoteException;

    public Object put(Object key, Object value) throws RemoteException;

    public Object remove(Object key) throws RemoteException;

    public void clear() throws RemoteException;
}