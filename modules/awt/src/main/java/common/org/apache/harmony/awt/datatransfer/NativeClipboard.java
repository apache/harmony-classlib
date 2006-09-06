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
 * @author Mikhail Danilov
 * @version $Revision$
 */
package org.apache.harmony.awt.datatransfer;

import java.awt.datatransfer.Clipboard;


/**
 * Base class for platrorm-specific clipboards.
 */
public abstract class NativeClipboard extends Clipboard {

    protected static final int OPS_TIMEOUT = 10000;     // ms

    /**
     * Creates native clipboard object.
     * @param name - clipboard name.
     */
    public NativeClipboard(String name) {
        super(name);
    }

    /**
     * Called when AWT is shutting down.
     * Necessary for Windows like native platforms where native clipboard
     * viewers' list supported on application level.
     */
    public void onShutdown() {
    }

    /**
     * Called when AWT is restarted after automatic shutdown.
     * Necessary for Windows like native platforms where native clipboard
     * viewers' list supported on application level.
     */
    public void onRestart() {
    }

}
