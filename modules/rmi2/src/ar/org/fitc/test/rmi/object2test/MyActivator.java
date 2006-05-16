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
import java.io.Serializable;
import java.rmi.MarshalledObject;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.activation.ActivationException;
import java.rmi.activation.ActivationID;
import java.rmi.activation.Activator;
import java.rmi.activation.UnknownObjectException;

public class MyActivator implements Activator, Remote, Serializable {

    private static final long serialVersionUID = 1L;

    public MyActivator(MarshalledObject mo) {
        super();
        this.mo = mo;
    }

    public MyActivator() throws IOException {
        super();
        mo = new MarshalledObject(this);
    }

    final static public int NullR = 0;

    final static public int ActivationE = 1;

    final static public int UnknownObjectE = 2;

    final static public int RemoteE = 3;

    final static public int MySelfInMarshalledObjectR = 4;

    public int i = 0;

    public Boolean b = null;

    public MarshalledObject mo;

    public MarshalledObject activate(ActivationID arg0, boolean arg1)
            throws ActivationException, UnknownObjectException, RemoteException {
        b = new Boolean(arg1);
        switch (i) {
        case (NullR):
            return null;
        case (ActivationE):
            throw new ActivationException();
        case (UnknownObjectE):
            throw new UnknownObjectException(null);
        case (RemoteE):
            throw new RemoteException();
        default:
            return mo;
        }
    }

    public boolean equals(Object o) {
        if (o instanceof MyActivator) {
            MyActivator oac = (MyActivator) o;
            if (oac.b == null && b == null) {
                return oac.i == i;
            }
            if (oac.b.equals(b)) {
                return oac.i == i;
            }
        }
        return false;
    }
}
