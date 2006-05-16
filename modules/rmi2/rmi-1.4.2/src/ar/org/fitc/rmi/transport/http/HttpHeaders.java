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

import java.util.HashSet;
import java.util.Set;

/*
 * NOTE: 
 * This class has been modified in order to support 
 * Java VM 1.4.2 using the javac's target jsr14 
 */

/**
 * This enum is used for http tunneling  
 * 
 * @author Diego Raúl Mercado
 */
public class HttpHeaders {
    private static Set<HttpHeaders> set;
    
    static {
        set = new HashSet<HttpHeaders>();
    }
    
    /**
     * the Content-Length key 
     */
    public final static HttpHeaders CONTENT_LENGTH_HEADER = 
        new HttpHeaders("Content-Length");
    /**
     * the Content-Type key 
     */
    public final static HttpHeaders CONTENT_TYPE_HEADER = 
        new HttpHeaders("Content-Type");

    /**
     * the Connection key 
     */
    public final static HttpHeaders CONNECTION_HEADER = 
        new HttpHeaders("Connection");
    
    /**
     * the Cache-Control key 
     */
    public final static HttpHeaders CACHE_CONTROL_HEADER = 
        new HttpHeaders("Cache-Control");

    /**
     * the Pragma key 
     */
    public final static HttpHeaders PRAGMA_HEADER = 
        new HttpHeaders("Pragma");

    /**
     * the Host key 
     */
    public final static HttpHeaders HOST_HEADER = 
        new HttpHeaders("Host");

    /**
     * the User-Agent key 
     */
    public final static HttpHeaders USER_AGENT_HEADER = 
        new HttpHeaders("User-Agent");

    /**
     * the Accept key 
     */
    public final static HttpHeaders ACCEPT_HEADER = 
        new HttpHeaders("Accept");
    
    /** Indicates de value of this enum */
    private String value;

    /** 
     * Constructor
     * 
     * @param value the value of this enum 
     */
    private HttpHeaders(String value) {
        this.value = value;
        set.add(this);
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
        for (HttpHeaders hh : set) {
            if (hh.toString() == value) {
                return hh;
            }
        }
        return null;
    }
}
 