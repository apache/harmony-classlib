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

import java.awt.datatransfer.*;
import java.io.*;

/**
 * Clipboard translator for serializable Java objects.
 */
public final class SerializableTranslator implements ClipboardTranslator {

    public byte[] java2Native(Transferable contents, TranslationPoint point) throws Exception {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        new ObjectOutputStream(stream).writeObject(contents.getTransferData(point.flavor));

        return stream.toByteArray();
    }

    public Object native2Java(byte[] contents, TranslationPoint point) throws Exception {
        return new ObjectInputStream(new ByteArrayInputStream(contents)).readObject();
    }

    public void assigned2Point(TranslationPoint point) throws UnsupportedTranslationPoint {
        if (!point.flavor.isFlavorSerializedObjectType()) {
            throw new UnsupportedTranslationPoint(point.toString());
        }
    }

}
