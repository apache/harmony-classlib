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
package ar.org.fitc.rmi.transport.http;

/**
 * This enum is used for http tunneling  
 * 
 * @author Diego Raúl Mercado
 */
public enum HttpHeaders {
    /**
     * the Content-Length key 
     */
    CONTENT_LENGTH_HEADER("Content-Length"),
    /**
     * the Content-Type key 
     */
    CONTENT_TYPE_HEADER("Content-Type"),
    /**
     * the Connection key 
     */
    CONNECTION_HEADER("Connection"),
    /**
     * the Cache-Control key 
     */
    CACHE_CONTROL_HEADER("Cache-Control"),
    /**
     * the Pragma key 
     */
    PRAGMA_HEADER("Pragma"),
    /**
     * the Host key 
     */
    HOST_HEADER("Host"),
    /**
     * the User-Agent key 
     */
    USER_AGENT_HEADER("User-Agent"),
    /**
     * the Accept key 
     */
    ACCEPT_HEADER("Accept");
    
    /** Indicates de value of this enum */
    private String value;

    /** 
     * Constructor
     * 
     * @param value the value of this enum 
     */
    private HttpHeaders(String value) {
        this.value = value;
    }
    
    /** 
     * @return the value of this enum 
     */
    @Override
    public final String toString() {
        return value;
    }

    /**
     * Given a String value, returns the current type  
     * 
     * @param value the value at constructor time 
     * @return the current type or null if is not available 
     */
    public final static HttpHeaders getEnum(String value) {
        for (HttpHeaders httpConstant : values()) {
            if (httpConstant.toString().equalsIgnoreCase(value)) {
                return httpConstant;
            }
        }
        return null;
    }
}
