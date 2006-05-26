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
package org.apache.harmony.rmi.internal.transport;

import java.rmi.RemoteException;

/**
 * @author Gustavo Petri
 */
public class ProtocolException extends RemoteException {

    /**
     * Serial number 1L
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor of <code>ProtocolException</code>
     */
    public ProtocolException() {
        super();
    }

    /**
     * Constructor of <code>ProtocolException</code>, receives a <code>
     * String</code>
     * 
     * @param message
     *            message of the <code>ProtocolException</code>
     */
    public ProtocolException(String message) {
        super(message);
    }

    /**
     * Constructor of <code>ProtocolException</code>, receives a <code>
     * String</code>
     * and <code>Throwable</code>
     * 
     * @param message
     *            message of the <code>ProtocolException</code>
     * @param cause
     *            that it has generated this exception
     */
    public ProtocolException(String message, Throwable cause) {
        super(message, cause);
    }
}
