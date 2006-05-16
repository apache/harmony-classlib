/* 
*  Copyright 2005 The Apache Software Foundation or its licensors, as applicable. 
* 
*  Licensed under the Apache License, Version 2.0 (the "License"); 
*  you may not use this file except in compliance with the License. 
*  You may obtain a copy of the License at 
* 
*    http://www.apache.org/licenses/LICENSE-2.0 
* 
*  Unless required by applicable law or agreed to in writing, software 
*  distributed under the License is distributed on an "AS IS" BASIS, 
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
*  See the License for the specific language governing permissions and 
*  limitations under the License. 
*/
package java.rmi.server;

import java.io.Externalizable;
import java.io.ObjectOutput;
import java.lang.reflect.Method;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public interface RemoteRef extends Externalizable {

    static final long serialVersionUID = 3632638527362204081L;

    static final String packagePrefix = "ar.org.fitc.rmi.server";

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    String getRefClass(ObjectOutput out);

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    boolean remoteEquals(RemoteRef obj);

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    int remoteHashCode();

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    String remoteToString();

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    RemoteCall newCall(RemoteObject obj, Operation[] op, int opnum, long hash)
            throws RemoteException;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    Object invoke(Remote obj, Method method, Object[] params, long opnum)
            throws Exception;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    void invoke(RemoteCall call) throws Exception;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    void done(RemoteCall call) throws RemoteException;

}
