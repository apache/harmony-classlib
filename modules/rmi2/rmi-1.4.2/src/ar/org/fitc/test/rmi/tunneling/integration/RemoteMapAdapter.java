package ar.org.fitc.test.rmi.tunneling.integration;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;


public class RemoteMapAdapter extends UnicastRemoteObject implements RemoteMap {

    private static final long serialVersionUID = 1L;

    private int getCalls = 0;

    protected Hashtable hashMap;

    private int putCalls = 0;

    public RemoteMapAdapter(Hashtable table) throws RemoteException {
        this.hashMap = table;
    }

    public synchronized void clear() throws RemoteException {
        hashMap.clear();
    }

    public boolean containsKey(Object key) throws RemoteException {
        return hashMap.containsKey(key);
    }

    public boolean containsValue(Object value) throws RemoteException {
        return hashMap.contains(value);
    }

    public synchronized Object get(Object key) throws RemoteException {
        getCalls++;
        printStatus();
        return hashMap.get(key);
    }

    public boolean isEmpty() throws RemoteException {
        return hashMap.isEmpty();
    }

    private void printStatus() {
        System.out.println("Internal HashMap Status... Timestamp: "
                + System.currentTimeMillis());
        System.out.println("get() called " + getCalls + " time(s)..");
        System.out.println("put() called " + putCalls + " time(s)");
    }

    public synchronized Object put(Object key, Object value)
            throws RemoteException {
        putCalls++;
        printStatus();
        return hashMap.put(key, value);
    }

    public synchronized Object remove(Object key) throws RemoteException {
        return hashMap.remove(key);
    }

    public int size() throws RemoteException {
        return hashMap.size();
    }
}