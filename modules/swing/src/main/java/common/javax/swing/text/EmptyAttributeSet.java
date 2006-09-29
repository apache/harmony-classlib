/*
 *  Copyright 2005 - 2006 The Apache Software Foundation or its licensors, as applicable.
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
package javax.swing.text;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
 * Implementation of empty attribute set.
 *
 */
final class EmptyAttributeSet implements AttributeSet, Serializable {

    public static final AttributeSet EMPTY = new EmptyAttributeSet();

    private EmptyAttributeSet() {
    }

    public boolean containsAttribute(final Object name, final Object value) {
        return false;
    }

    public boolean containsAttributes(final AttributeSet attr) {
        return attr.getAttributeCount() == 0;
    }

    public AttributeSet copyAttributes() {
        return this;
    }

    public boolean equals(final Object obj) {
        return obj instanceof AttributeSet && isEqual((AttributeSet)obj);
    }

    public Object getAttribute(final Object name) {
        return null;
    }

    public int getAttributeCount() {
        return 0;
    }

    public Enumeration getAttributeNames() {
        return new Enumeration() {
            public boolean hasMoreElements() {
                return false;
            }
            public Object nextElement() {
                throw new NoSuchElementException("Empty attribute set");
            }
        };
    }

    public AttributeSet getResolveParent() {
        return null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isDefined(final Object name) {
        return false;
    }

    public boolean isEqual(final AttributeSet attr) {
        return attr.getAttributeCount() == 0;
    }
}
