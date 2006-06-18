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
import java.util.Comparator;

/**
 * Flavors comparator. Used for sorting text flavors.
 */
public class FlavorsComparator implements Comparator {

    private final DataFlavor[] arr = new DataFlavor[2];

    public int compare(Object o1, Object o2) {
        DataFlavor flav1 = (DataFlavor) o1;
        DataFlavor flav2 = (DataFlavor) o2;

        if (!flav1.isFlavorTextType() && !flav2.isFlavorTextType()) {
            return 0;
        } else if (!flav1.isFlavorTextType() && flav2.isFlavorTextType()) {
            return -1;
        } else if (flav1.isFlavorTextType() && !flav2.isFlavorTextType()) {
            return 1;
        } else {
            arr[0] = flav1;
            arr[1] = flav2;

            return (DataFlavor.selectBestTextFlavor(arr) == flav1) ? -1 : 1;
        }
    }

}
