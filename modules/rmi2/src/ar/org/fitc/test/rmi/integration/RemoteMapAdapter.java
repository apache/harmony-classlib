/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/**
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */
package ar.org.fitc.test.rmi.integration;

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