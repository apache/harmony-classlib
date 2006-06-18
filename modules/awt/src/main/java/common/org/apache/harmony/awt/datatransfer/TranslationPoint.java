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

/**
 * Point of translation. Pair of native format and Java format.
 */
public final class TranslationPoint {

    public final DataFlavor flavor;
    public final String natife;

    /**
     * Creates new translation point instance.
     */
    public TranslationPoint(DataFlavor flavor, String natife) {
        this.flavor = flavor;
        this.natife = natife;
    }

    public boolean equals(Object o) {
        if ((o == null) || !(o instanceof TranslationPoint)) {
            return false;
        } else {
            TranslationPoint p = (TranslationPoint) o;

            return ((flavor.equals(p.flavor)) && (natife.equals(p.natife)));
        }
    }

    public String toString() {
        return (getClass().getName() + '[' + flavor + ',' + natife + ']');
    }

    public int hashCode() {
        return (flavor.hashCode() + natife.hashCode());
    }

}
