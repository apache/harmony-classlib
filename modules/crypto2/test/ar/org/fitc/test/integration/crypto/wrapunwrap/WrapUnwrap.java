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

package ar.org.fitc.test.integration.crypto.wrapunwrap;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.Key;

/**
 * A remote interface to test wrap and unwrap, we know is not secure.
 * The idea is use those service in any object and all kinds information needed to do the job are self contained.  
 */

public interface WrapUnwrap extends Remote {

    /**
     * Wrap job. All information to do the unwrap is obteined here.
     * @param key the key to be wrap
     * @return the wrap inforamtion. 
     * @throws RemoteException if the cipher fail the exception es replace to this.
     * @see javax.crypto.Cipher#wrap(java.security.Key)
     */
    public byte[] wrap(Key key) throws RemoteException;
    
    /**
     * Unwrap job. Use information obteined in wrap job.  
     * @param k the byte array return at wrap call  
     * @return the key 
     * @throws RemoteException if the cipher fail the exception es replace to this.
     * @see javax.crypto.Cipher#unwrap(byte[], java.lang.String, int)
     */
    public Key unwrap(byte[] k) throws RemoteException;
}
