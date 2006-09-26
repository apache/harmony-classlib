/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
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
 * @author Nikolay A. Kuznetsov
 * @version $Revision: 1.4.2.2 $
 */
package java.util.regex;

/**
 * Valid constatnt zero character match.
 * 
 * @author Nikolay A. Kuznetsov
 * @version $Revision: 1.4.2.2 $
 */
class EmptySet extends LeafSet {

    public EmptySet(AbstractSet next) {
        super(next);
        charCount = 0;
    }

    /*
     * @see java.util.regex.LeafSet#accepts(int, java.lang.CharSequence)
     */
    public int accepts(int stringIndex, CharSequence testString) {
        return 0;
    }

    /*
     * @see java.util.regex.AbstractSet#getName()
     */
    protected String getName() {
        return "<Empty set>"; //$NON-NLS-1$
    }

    public boolean hasConsumed(MatchResultImpl mr) {
        return false;
    }

}
