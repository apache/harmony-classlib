/*
 *  Copyright 2005 - 2006 The Apache Software Software Foundation or its licensors, as applicable.
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
 * @author Michael Danilov
 * @version $Revision$
 */
package org.apache.harmony.awt.datatransfer;

/**
 * Native clipboard entry. Consist of data and its format.
 */
public final class ClipboardEntry {

    /**
     * Native format of clipboard data.
     */
    public long format;

    /**
     * Native clipboard data.
     */
    public byte[] data;

    public ClipboardEntry(long format, byte[] data) {
        this.format = (data == null) ? 0 : format;
        this.data = data;
    }

}
