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
package ar.org.fitc.test.rmi.server.rmiclassloader;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class EchoServer implements IEcho {

    public String echo(String msg) throws RemoteException {

        return this.getClass() + " - Have said: " + msg;
    }

    public static void main(String args[]) throws Exception {

        LocateRegistry.createRegistry(1099);
        IEcho echoServer = new EchoServer();
        IEcho stubProxy = (IEcho) UnicastRemoteObject.exportObject(echoServer,
                0);
        Naming.rebind("//127.0.0.1:1099/Echo2", stubProxy);

    }
}
