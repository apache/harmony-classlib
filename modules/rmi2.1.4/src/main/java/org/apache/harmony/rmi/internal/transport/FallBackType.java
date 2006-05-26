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

/*
 * NOTE: 
 * This class has been modified in order to support 
 * Java VM 1.4.2 using the javac's target jsr14 
 */

/**
 * This class if for RMIDefaultSocketFactory purposes
 * 
 * @author Diego Raúl Mercado
 */
class FallBackType {
    
    /**
     * Direct Connection with the server
     */
    final static FallBackType DIRECT = new FallBackType(); 
    
    /**
     * Connection traversing a Proxy 
     */
    final static FallBackType PROXY = new FallBackType(); 
    
    /**
     * Connection traversing a Proxy and redirecting through a cgi script
     */
    final static FallBackType CGI = new FallBackType();
}
