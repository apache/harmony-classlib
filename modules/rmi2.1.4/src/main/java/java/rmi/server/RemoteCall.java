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
package java.rmi.server;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public interface RemoteCall {

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    ObjectOutput getResultStream(boolean success) throws IOException,
            StreamCorruptedException;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    void releaseInputStream() throws IOException;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    void releaseOutputStream() throws IOException;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    void executeCall() throws Exception;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    ObjectInput getInputStream() throws IOException;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    ObjectOutput getOutputStream() throws IOException;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    void done() throws IOException;
}
