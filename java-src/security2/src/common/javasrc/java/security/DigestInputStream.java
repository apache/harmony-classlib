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

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @com.intel.drl.spec_ref
 * 
 */
public class DigestInputStream extends FilterInputStream {

    /**
     * @com.intel.drl.spec_ref
     */
    protected MessageDigest digest;

    // Indicates wether digest functionality is on or off
    private boolean isOn = true;

    /**
     * @com.intel.drl.spec_ref
     */
    public DigestInputStream(InputStream stream, MessageDigest digest) {
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
    public int read() throws IOException {
        // read the next byte
        int byteRead = in.read();
        // update digest only if
        // - digest functionality is on
        // - eos has not been reached
        if (isOn && (byteRead != -1)) {
            digest.update((byte)byteRead);
        }
        // return byte read
        return byteRead;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int read(byte[] b, int off, int len) throws IOException {
        // read next up to len bytes
        int bytesRead = in.read(b, off, len);
        // update digest only if
        // - digest functionality is on
        // - eos has not been reached
        if (isOn && (bytesRead != -1)) {
            digest.update(b, off, bytesRead);
        }
        // return number of bytes read
        return bytesRead;
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
