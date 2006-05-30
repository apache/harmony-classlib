/*
 * Copyright 2005-2006 The Apache Software Foundation or its licensors, as applicable
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Alexei Y. Zakharov
 * @version $Revision: 1.1.2.4 $
 */

package org.apache.harmony.jndi.provider.dns;

import javax.naming.NamingException;

/**
 * The exception that can be thrown from the DNS Resovler classes. 
 * 
 * @author Alexei Zakharov
 * @version $Revision: 1.1.2.4 $
 */
public class DomainProtocolException extends NamingException {
    
    private static final long serialVersionUID = -6631370496197297208L;
    
    public DomainProtocolException(String message) {
        super(message);
    }

    public DomainProtocolException(Exception cause) {
        super();
        setRootCause(cause);
    }
    
    public DomainProtocolException(String mes, Exception cause) {
        super(mes);
        setRootCause(cause);
    }
}
