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
package ar.org.fitc.test.rmi.object2test;

import java.io.IOException;
import java.rmi.MarshalledObject;
import java.rmi.RemoteException;
import java.rmi.activation.Activatable;
import java.rmi.activation.ActivationException;
import java.rmi.activation.ActivationID;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;

public class EchoActivatable_Imp extends Activatable implements Echo {

    public EchoActivatable_Imp(String arg0, MarshalledObject arg1,
            boolean arg2, int arg3) throws ActivationException, RemoteException {
        super(arg0, arg1, arg2, arg3);

    }

    public EchoActivatable_Imp(String arg0, MarshalledObject arg1,
            boolean arg2, int arg3, RMIClientSocketFactory arg4,
            RMIServerSocketFactory arg5) throws ActivationException,
            RemoteException {
        super(arg0, arg1, arg2, arg3, arg4, arg5);

    }

    public EchoActivatable_Imp(ActivationID arg0, int arg1)
            throws RemoteException {
        super(arg0, arg1);

    }

    public EchoActivatable_Imp(ActivationID arg0, int arg1,
            RMIClientSocketFactory arg2, RMIServerSocketFactory arg3)
            throws RemoteException {
        super(arg0, arg1, arg2, arg3);

    }

    public EchoActivatable_Imp() throws RemoteException, IOException {
        super(new ActivationID(new MyActivator()), 0);

    }

    public EchoActivatable_Imp(MarshalledObject mo) throws RemoteException {
        super(new ActivationID(new MyActivator(mo)), 0);

    }

    public int msgCount = 0;

    public String echo(String msg) throws RemoteException {

        msgCount += 1;
        return EchoUnicast_Imp.class + " - Ha dicho: " + msg;
    }
}
