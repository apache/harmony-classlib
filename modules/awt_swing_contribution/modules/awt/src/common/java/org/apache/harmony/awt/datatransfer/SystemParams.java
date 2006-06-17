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
 * System parameters of DataFlavor.
 * User can see them in object with flavor map properties.
 */
public class SystemParams {

    /**
     * End of line string;
     */
    public final String eol;

    /**
     * Number of zeros that terminates sequence of bytes in native clipboard;
     */
    public final int terminators;

    public SystemParams(String eol, int terminators) {
        this.eol = eol;
        this.terminators = terminators;
    }

    public SystemParams(SystemParams params) {
        this.eol = params.eol;
        this.terminators = params.terminators;
    }

}
