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