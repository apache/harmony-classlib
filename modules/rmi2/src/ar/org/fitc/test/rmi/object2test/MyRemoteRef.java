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
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Method;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.Operation;
import java.rmi.server.RemoteCall;
import java.rmi.server.RemoteObject;
import java.rmi.server.RemoteRef;

@SuppressWarnings("serial")
public class MyRemoteRef implements RemoteRef {

    public boolean bRemoteEquals = false;

    public int iHashCode = 0;

    public String sToString = null;

    public Object invoke(Remote arg0, Method arg1, Object[] arg2, long arg3)
            throws Exception {
        return null;
    }

    @SuppressWarnings("deprecation")
    public RemoteCall newCall(RemoteObject arg0, Operation[] arg1, int arg2,
            long arg3) throws RemoteException {
        return null;
    }

    @SuppressWarnings("deprecation")
    public void invoke(RemoteCall arg0) throws Exception {
    }

    @SuppressWarnings("deprecation")
    public void done(RemoteCall arg0) throws RemoteException {
    }

    public String getRefClass(ObjectOutput arg0) {
        return null;
    }

    public int remoteHashCode() {
        return iHashCode;
    }

    public boolean remoteEquals(RemoteRef arg0) {
        bRemoteEquals = !bRemoteEquals;
        return bRemoteEquals;
    }

    public String remoteToString() {
        return sToString;
    }

    public void writeExternal(ObjectOutput arg0) throws IOException {
    }

    public void readExternal(ObjectInput arg0) throws IOException,
            ClassNotFoundException {
    }

}