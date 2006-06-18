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

import java.awt.datatransfer.DataFlavor;
import java.util.*;

/**
 * Text clipboard translator. Contains utility methods for
 * manipulating system text parameters.
 */
public abstract class TextTranslator implements ClipboardTranslator {

    private static final LinkedList textFlavors = new LinkedList();

    private String eol;
    private Integer terminators;
    private byte[] eolBinary;
    private byte[] terminatorsBinary;

    static {
        textFlavors.add(DataFlavor.getTextPlainUnicodeFlavor());
        textFlavors.add(DataFlavor.stringFlavor);
        textFlavors.add(DataFlavor.plainTextFlavor);
    }

    public static TextFlavorsIterator getFlavorsIterator() {
        return new TextFlavorsIterator();
    }

    public TextTranslator(String eol, Integer terminators) {
        setEOL(eol);
        setTerminators(terminators);
    }

    public final String getEOL() {
        return eol;
    }

    public final void setEOL(String eol) {
        if ((this.eol != null) && this.eol.equals(eol)) {
            return;
        }

        this.eol = (eol == null) ? "\n" : eol;
        int length = this.eol.length();
        eolBinary = new byte[2 * length];

        for (int i = 0, j = 0; i < length; i++) {
            char c = this.eol.charAt(i);

            this.eolBinary[j++] = (byte) c;
            this.eolBinary[j++] = (byte) (c >> 8);
        }
    }

    public final Integer getTerminators() {
        return terminators;
    }

    public final void setTerminators(Integer terminators) {
        if ((this.terminators != null) && this.terminators.equals(terminators)) {
            return;
        }

        this.terminators = (terminators == null) ? new Integer(0) : terminators;
        terminatorsBinary = new byte[this.terminators.intValue()];

        Arrays.fill(terminatorsBinary, (byte) 0);
    }

    protected final byte[] getEOLBinary() {
        return eolBinary;
    }

    protected final byte[] getTerminatorsBinary() {
        return terminatorsBinary;
    }

    public static class TextFlavorsIterator {

        private Iterator iterator = textFlavors.iterator();

        public DataFlavor getMainFlavor() {
            return ((DataFlavor) iterator.next());
        }

        public DataFlavor getNextFlavor() {
            if (iterator.hasNext()) {
                return ((DataFlavor) iterator.next());
            } else {
                return null;
            }
        }

    }

}
