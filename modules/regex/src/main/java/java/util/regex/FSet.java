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
 * The node which marks end of the particular group.
 * @author Nikolay A. Kuznetsov
 * @version $Revision: 1.8.2.2 $
 */
class FSet extends AbstractSet {
    
    static PossessiveFSet posFSet = new PossessiveFSet();

    private int groupIndex;

    public FSet(int groupIndex) {
        this.groupIndex = groupIndex;
    }

    public int matches(int stringIndex, CharSequence testString,
            MatchResultImpl matchResult) {
        int end = matchResult.getEnd(groupIndex);
        matchResult.setEnd(groupIndex, stringIndex);
        int shift = next.matches(stringIndex, testString, matchResult);
        /*
         * if(shift >=0 && matchResult.getEnd(groupIndex) == -1) {
         * matchResult.setEnd(groupIndex, stringIndex); }
         */
        if (shift < 0)
            matchResult.setEnd(groupIndex, end);
        return shift;
    }

    public int getGroupIndex() {
        return groupIndex;
    }

    protected String getName() {
        return "fSet";
    }

    public boolean hasConsumed(MatchResultImpl mr) {
        return false;
    }

    /**
     * Marks the end of the particular group and not take into account possible
     * kickbacks(required for atomic groups, for instance)
     * 
     */
    static class PossessiveFSet extends AbstractSet {

        public int matches(int stringIndex, CharSequence testString,
                MatchResultImpl matchResult) {
            return stringIndex;
        }

        protected String getName() {
            return "posFSet";
        }

        public boolean hasConsumed(MatchResultImpl mr) {
            return false;
        }
    }
}