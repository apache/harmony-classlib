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
 * @author Alexey A. Ivanov
 * @version $Revision$
 */
package javax.swing.text.html;

final class Specificity implements Comparable {
    final int tags;
    final int ids;
    final int classes;

    Specificity(final Selector selector) {
        int tmpTags    = 0;
        int tmpIDs     = 0;
        int tmpClasses = 0;

        for (int i = 0; i < selector.simpleSelectors.length; i++) {
            if (selector.simpleSelectors[i].tag != null) {
                ++tmpTags;
            }
            if (selector.simpleSelectors[i].id != null) {
                ++tmpIDs;
            }
            if (selector.simpleSelectors[i].clazz != null) {
                ++tmpClasses;
            }
        }

        tags    = tmpTags;
        ids     = tmpIDs;
        classes = tmpClasses;
    }

    public int compareTo(final Object object) {
        Specificity another = (Specificity)object;
        int result = ids - another.ids;
        if (result != 0) {
            return result;
        }
        result = classes - another.classes;
        if (result != 0) {
            return result;
        }
        return tags - another.tags;
    }

    public String toString() {
        return "" + ids + classes + tags;
    }
}
