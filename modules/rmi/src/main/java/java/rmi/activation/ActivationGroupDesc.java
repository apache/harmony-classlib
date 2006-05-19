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
package java.rmi.activation;

import java.io.Serializable;
import java.rmi.MarshalledObject;
import java.util.Properties;

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public final class ActivationGroupDesc implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4936225423168276595L;
    
    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static class CommandEnvironment implements Serializable {
        
        /**
         * 
         */
        private static final long serialVersionUID = 6165754737887770191L;
        
        /**
         * @ar.org.fitc.spec_ref
         * 
         */
        public CommandEnvironment(String cmdpath, String[] argv) {
            throw new UnsupportedOperationException();
        }
        
        /**
         * @ar.org.fitc.spec_ref
         * 
         */
        public String[] getCommandOptions() {
            throw new UnsupportedOperationException();
        }
        
        /**
         * @ar.org.fitc.spec_ref
         * 
         */
        public String getCommandPath() {
            throw new UnsupportedOperationException();
        }
        
        /**
         * @ar.org.fitc.spec_ref
         * 
         */
        public int hashCode() {
            throw new UnsupportedOperationException();
        }
        
        /**
         * @ar.org.fitc.spec_ref
         * 
         */
        public boolean equals(Object obj) {
            throw new UnsupportedOperationException();
        }
        
    }
    
    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public ActivationGroupDesc(Properties overrides, CommandEnvironment cmd) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public ActivationGroupDesc(String className, String location,
            MarshalledObject data, Properties overrides, CommandEnvironment cmd) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public CommandEnvironment getCommandEnvironment() {
        throw new UnsupportedOperationException();
    }
    
    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public Properties getPropertyOverrides() {
        throw new UnsupportedOperationException();
    }
    
    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public int hashCode() {
        throw new UnsupportedOperationException();
    }
    
    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public String getLocation() {
        throw new UnsupportedOperationException();
    }
    
    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public String getClassName() {
        throw new UnsupportedOperationException();
    }
    
    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public MarshalledObject getData() {
        throw new UnsupportedOperationException();
    }
}
