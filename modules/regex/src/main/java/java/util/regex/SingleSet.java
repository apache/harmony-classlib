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
 * @version $Revision: 1.8.2.2 $
 */
package java.util.regex;

/**
 * Group node over subexpression w/o alternations.
 * @author Nikolay A. Kuznetsov
 * @version $Revision: 1.8.2.2 $
 */
class SingleSet extends JointSet {
    
    private AbstractSet kid;

    public SingleSet(AbstractSet child, FSet fSet) {
        this.kid = child;
        this.fSet = fSet;
        this.groupIndex = fSet.getGroupIndex();
    }

    public int matches(int stringIndex, CharSequence testString,
            MatchResultImpl matchResult) {
        int start = matchResult.getStart(groupIndex);
        matchResult.setStart(groupIndex, stringIndex);
        int shift = kid.matches(stringIndex, testString, matchResult);
        if (shift >= 0) {
            return shift;
        }
        matchResult.setStart(groupIndex, start);
        return -1;
    }

    public int find(int stringIndex, CharSequence testString,
            MatchResultImpl matchResult) {
        int res = kid.find(stringIndex, testString, matchResult);
        if (res >= 0)
            matchResult.setStart(groupIndex, res);
        return res;
    }

    public int findBack(int stringIndex, int lastIndex,
            CharSequence testString, MatchResultImpl matchResult) {
        int res = kid.findBack(stringIndex, lastIndex, testString, matchResult);
        if (res >= 0)
            matchResult.setStart(groupIndex, res);
        return res;
    }

    public boolean first(AbstractSet set) {
        return kid.first(set);
    }
}
