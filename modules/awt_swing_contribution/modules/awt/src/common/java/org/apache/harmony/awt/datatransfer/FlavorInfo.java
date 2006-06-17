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
 * Information about Java format in native clipboard.
 */
public final class FlavorInfo {

    /**
     * Native format.
     */
    public final long format;

    /**
     * Representation string of native format.
     */
    public final String natife;

    /**
     * Whether native data translated to Java data.
     */
    public boolean translated;

    /**
     * Translated Java data.
     */
    public Object data;

    /**
     * Whether native data read from native clipboard.
     */
    public boolean read;

    /**
     * Read data in native format.
     */
    public byte[] rawData;

    public FlavorInfo(String natife, long format) {
        this.format = format;
        this.natife = natife;
        translated = false;
        data = null;
        read = false;
        rawData = null;
    }

}
