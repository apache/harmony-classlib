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
package java.rmi.dgc;

import java.io.Serializable;

/**
 * @ar.org.fitc.spec_ref
 * @author Gustavo Petri
 */

public final class Lease implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5713411624328831948L;

    /**
     * The duation of the <code>Lease</code>.
     */
    private long duration;

    /**
     * The <code>VMID</code> of the <code>Lease</code>.
     */
    private VMID id;

    /**
     * @ar.org.fitc.spec_ref
     */
    public Lease(VMID id, long duration) {
        // Constructor
        this.duration = duration;
        this.id = id;
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public VMID getVMID() {
        return id;
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public long getValue() {
        return duration;
    }
}
