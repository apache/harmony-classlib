/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
* @author Vladimir N. Molotkov
* @version $Revision$
*/

package java.security;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @com.intel.drl.spec_ref
 * 
 */
public class DigestOutputStream extends FilterOutputStream {

    /**
     * @com.intel.drl.spec_ref
     */
    protected MessageDigest digest;

    // Indicates wether digest functionality is on or off
    private boolean isOn = true;

    /**
     * @com.intel.drl.spec_ref
     */
    public DigestOutputStream(OutputStream stream, MessageDigest digest) {
        super(stream);
        this.digest = digest;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public MessageDigest getMessageDigest() {
        return digest;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setMessageDigest(MessageDigest digest) {
        this.digest = digest;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void write(int b) throws IOException {
        // update digest only if digest functionality is on
        if (isOn) {
            digest.update((byte)b);
        }
        // write the byte
        out.write(b);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void write(byte[] b, int off, int len) throws IOException {
        // update digest only if digest functionality is on
        if (isOn) {
            digest.update(b, off, len);
        }
        // write len bytes
        out.write(b, off, len);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void on(boolean on) {
        isOn = on;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String toString() {
        return super.toString() + ", " + digest.toString() +
            (isOn ? ", is on" : ", is off");
    }
}
