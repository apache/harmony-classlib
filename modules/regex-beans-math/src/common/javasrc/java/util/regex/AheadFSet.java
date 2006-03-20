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
 * @version $Revision: 1.3.2.2 $
 */
package java.util.regex;

/**
 * LookAhead FSet, always returns true;
 * 
 * @author Nikolay A. Kuznetsov
 * @version $Revision: 1.3.2.2 $
 */
class AheadFSet extends FSet {
  
    public AheadFSet() {
        super(-1);
    }

    public int matches(int stringIndex, CharSequence testString,
            MatchResultImpl matchResult) {
        return stringIndex;
    }

    protected String getName() {
        return "AheadFSet";
    }
}
