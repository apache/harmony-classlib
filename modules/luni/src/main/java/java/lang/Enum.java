/* Copyright 1998, 2004 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.lang;

import java.io.Serializable;

/**
 * Stub for Enum 
 */
public class Enum <E extends Enum<E>> implements Serializable, Comparable<E> {

    private String name;


    private int ordinal;


    protected Enum(String name, int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
    }


    public final String name() {
        return name;
    }


    public final int ordinal() {
        return ordinal;
    }


    public String toString() {
        return "Enum:" + name;
    }


    public final boolean equals(Object other) {
        throw new RuntimeException("not yet implemented");
    }


    public final int hashCode() {
        return ordinal + (name == null ? 0 : name.hashCode());
    }


    protected final Object clone() throws CloneNotSupportedException {
        return new Enum<E> (name, ordinal);
    }


    public final int compareTo(E o) {
        return ordinal - o.ordinal;
    }


    public final Class<E> getDeclaringClass() {
        throw new RuntimeException("not yet implemented");
    }


    public static <T extends Enum<T>> T valueOf(Class<T> enumType, String name) {
        throw new RuntimeException("not yet implemented");
    }
}
