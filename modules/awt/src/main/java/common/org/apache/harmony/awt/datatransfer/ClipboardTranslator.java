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

import java.awt.datatransfer.Transferable;

/**
 * Interface for classes that translate clipboard data from Java format to native one and back.
 */
public interface ClipboardTranslator {

    /**
     * Translates clipboard data from Java format to native one.
     */
    public abstract byte[] java2Native(Transferable contents, TranslationPoint point)
            throws Exception;

    /**
     * Translates clipboard data from native format to Java one.
     */
    public abstract Object native2Java(byte[] contents, TranslationPoint point)
            throws Exception;

    /**
     * Called by translation manager when this translator assigned to translation point.
     *
     * @throws UnsupportedTranslationPoint may be generated if translator doesn't support
     * given point
     */
    public abstract void assigned2Point(TranslationPoint point)
            throws UnsupportedTranslationPoint;

}
