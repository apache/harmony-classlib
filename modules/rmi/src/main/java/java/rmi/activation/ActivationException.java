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

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public class ActivationException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -4320118837291406071L;

    public Throwable detail;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public ActivationException() {
        super();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public ActivationException(String s) {
        super(s);
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public ActivationException(String s, Throwable cause) {
        super(s + "; nested exception is:\n\t"  + cause.toString());
        detail = cause;
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    @Override
    public Throwable getCause() {
        return detail.getCause();
    }
}
