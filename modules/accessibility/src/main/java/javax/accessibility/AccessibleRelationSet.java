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
 * @author Dennis Ushakov
 * @version $Revision$
 */

package javax.accessibility;

import java.util.Iterator;
import java.util.Vector;

public class AccessibleRelationSet {
    protected Vector<AccessibleRelation> relations;

    public AccessibleRelationSet() {
        initStorage(0);
    }

    public AccessibleRelationSet(final AccessibleRelation[] relations) {
        initStorage(relations.length);
        addAll(relations);
    }

    public boolean add(final AccessibleRelation relation) {
        initStorage(1);
        if (contains(relation.key)) {
            return false;
        }
        relations.add(relation);
        return true;
    }

    public void addAll(final AccessibleRelation[] relations) {
        initStorage(relations.length);
        for(int i = 0; i < relations.length; i++) {
            add(relations[i]);
        }
    }
    
    private void initStorage(final int capacity) {
        if(relations == null) {
            relations = new Vector<AccessibleRelation>(capacity);
        }
    }

    public boolean remove(final AccessibleRelation relation) {
        return relations != null && relations.remove(relation);
    }

    public void clear() {
        if (relations != null) {
            relations.clear();
        }
    }

    public int size() {
        return relations == null ? 0 : relations.size();
    }

    public boolean contains(final String key) {
        return get(key) != null;
    }

    public AccessibleRelation get(final String key) {
        if (relations == null) {
            return null;
        }
        for (Iterator<AccessibleRelation> i = relations.iterator(); i.hasNext();) {
            AccessibleRelation rel = i.next();
            if(key.equals(rel.key)) {
                return rel;
            }
        }
        return null;
    }

    public AccessibleRelation[] toArray() {
        return relations == null ? new AccessibleRelation[0] :
            (AccessibleRelation[])relations.toArray(new AccessibleRelation[relations.size()]);
    }

    public String toString() {
        if (relations == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (Iterator<AccessibleRelation> it = relations.iterator(); it.hasNext(); ) {
            result.append(it.next());
            if (it.hasNext()) {
                result.append(",");
            }
        }
        return result.toString();
    }
}
