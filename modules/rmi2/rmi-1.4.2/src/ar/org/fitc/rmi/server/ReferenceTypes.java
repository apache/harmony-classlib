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

/*
 * NOTE: 
 * This class has been modified in order to support 
 * Java VM 1.4.2 using the javac's target jsr14 
 */

/**
 * This enum holds the string representation for all the remote reference types
 * 
 * UnicastRef UnicastRef2 UnicastServerRef UnicastServerRef2
 * 
 * @author Gustavo Petri
 * 
 */
public class ReferenceTypes {

    public static final ReferenceTypes UNICAST_REF = 
        new ReferenceTypes("UnicastRef"); 
    
    public static final ReferenceTypes UNICAST_REF2 = 
        new ReferenceTypes("UnicastRef2");
    
    public static final ReferenceTypes UNICAST_SERVER_REF = 
        new ReferenceTypes("UnicastServerRef"); 
    
    public static final ReferenceTypes UNICAST_SERVER_REF2 = 
        new ReferenceTypes("UnicastServerRef2");

    private String type;

    private ReferenceTypes(String type) {
        this.type = type;
    }

    @Override
    public final String toString() {
        return type;
    }
}
