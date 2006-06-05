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

import java.rmi.RemoteException;
import java.security.KeyPair;

/**
 * It is time to split, a cipher may use pair of key or secret key.
 * This interface extends WrapUnwrap to use pair of key. 
 * 
 * @see ar.org.fitc.test.integration.crypto.wrapunwrap.WrapUnwrap
 */

public interface KeyPairWrapUnwrap extends WrapUnwrap {

    /**
     * Set the new keyPair
     * @param kp the new keyPair
     * @throws RemoteException may throw a exception for unusefull key
     */
    
    public void setKeyPair(KeyPair kp) throws RemoteException;
    
    /**
     * get the keyPair uses. 
     * @return key pair uses
     * @throws RemoteException  to respect the rmi interfeace
     */
    public KeyPair getKeyPair() throws RemoteException;
}
