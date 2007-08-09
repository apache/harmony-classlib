/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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
        initStorage();
    }

    public AccessibleRelationSet(final AccessibleRelation[] relations) {
        initStorage(relations.length);
        addAll(relations);
    }

    public boolean add(final AccessibleRelation relation) {
        initStorage();
        if (contains(relation.key)) {
            return false;
        }
        relations.add(relation);
        return true;
    }

    public void addAll(final AccessibleRelation[] relations) {
        initStorage(relations.length);
        for (AccessibleRelation element : relations) {
            add(element);
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
        for (AccessibleRelation rel : relations) {
            if(key.equals(rel.key)) {
                return rel;
            }
        }
        return null;
    }

    public AccessibleRelation[] toArray() {
        return relations == null ? new AccessibleRelation[0] :
            relations.toArray(new AccessibleRelation[relations.size()]);
    }

    @Override
    public String toString() {
        if (relations == null) {
            return ""; //$NON-NLS-1$
        }
        StringBuffer result = new StringBuffer();
        for (Iterator<AccessibleRelation> it = relations.iterator(); it.hasNext(); ) {
            result.append(it.next());
            if (it.hasNext()) {
                result.append(","); //$NON-NLS-1$
            }
        }
        return result.toString();
    }


    private void initStorage(final int capacity) {
        if(relations == null) {
            relations = new Vector<AccessibleRelation>(capacity);
        }
    }

    private void initStorage() {
        if(relations == null) {
            relations = new Vector<AccessibleRelation>();
        }
    }
}
