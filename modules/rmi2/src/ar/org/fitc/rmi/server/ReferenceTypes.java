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
package ar.org.fitc.rmi.server;

/**
 * This enum holds the string representation for all the remote reference types
 * 
 * UnicastRef UnicastRef2 UnicastServerRef UnicastServerRef2
 * 
 * @author Gustavo Petri
 * 
 */
public enum ReferenceTypes {

    UNICAST_REF("UnicastRef"), UNICAST_REF2("UnicastRef2"), UNICAST_SERVER_REF(
            "UnicastServerRef"), UNICAST_SERVER_REF2("UnicastServerRef2");

    private String type;

    private ReferenceTypes(String type) {
        this.type = type;
    }

    @Override
    public final String toString() {
        return type;
    }
}
