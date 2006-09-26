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
 * @version $Revision: 1.12.2.2 $
 */
package java.util.regex;

/**
 * Node accepting any character except line terminators;
 * 
 * @author Nikolay A. Kuznetsov
 * @version $Revision: 1.12.2.2 $
 */
final class DotSet extends LeafSet {
    
    AbstractLineTerminator lt;

    public DotSet(AbstractLineTerminator lt) {
        super();
        this.lt = lt;
    }

    public int accepts(int strIndex, CharSequence testString) {
        char ch = testString.charAt(strIndex);
        return lt.isLineTerminator(ch) ? -1 : 1;

        /*
         * return (strIndex<testString.length() && testString.charAt(strIndex) !=
         * '\n') ? 1 : -1;
         */
    }

    protected String getName() {
        return "."; //$NON-NLS-1$
    }

    public int getType() {
        return AbstractSet.TYPE_DOTSET;
    }
}
