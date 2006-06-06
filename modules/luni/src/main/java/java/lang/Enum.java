/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
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
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

import org.apache.harmony.luni.util.Msg;

/**
 * TODO Enum doc
 */
public class Enum<E extends Enum<E>> implements Serializable, Comparable<E> {

    private static final long serialVersionUID = 0L;

    private final String name;

    private final int ordinal;

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
        return this == other;
    }

    public final int hashCode() {
        return ordinal + (name == null ? 0 : name.hashCode());
    }

    /**
     * Enums are singletons, they may not be cloned.
     */
    protected final Object clone() throws CloneNotSupportedException {
        // KA004=Enums may not be cloned
        throw new CloneNotSupportedException(Msg.getString("KA004"));
    }

    public final int compareTo(E o) {
        return ordinal - o.ordinal;
    }

    public final Class<E> getDeclaringClass() {
        Class myClass = getClass();
        Class mySuperClass = myClass.getSuperclass();
        if (Enum.class == mySuperClass) {
            return myClass;
        }
        return mySuperClass;
    }

    public static <T extends Enum<T>> T valueOf(Class<T> enumType, String name) {
        if ((enumType == null) || (name == null)) {
            // KA001=Argument must not be null
            throw new NullPointerException(Msg.getString("KA001"));
        }
        T[] values = getValues(enumType);
        if (values == null) {
            // KA005={0} is not an enum type
            throw new IllegalArgumentException(Msg.getString("KA005", enumType));
        }
        for (T enumConst : values) {
            if (enumConst.name.equals(name)) {
                return enumConst;
            }
        }
        // KA006={0} is not a constant in the enum type {1}
        throw new IllegalArgumentException(Msg.getString("KA006", name, enumType));
    }

    /*
     * Helper to invoke the values() static method on T and answer
     * the result.  Returns null if there is a problem.
     */
    static <T extends Enum<T>> T[] getValues(final Class<T> enumType) {
        try {
            Method values = AccessController.doPrivileged(
                new PrivilegedExceptionAction<Method>() {
                    public Method run() throws Exception {
                        Method valsMethod = enumType.getMethod("values", (Class[])null);
                        valsMethod.setAccessible(true);
                        return valsMethod;
                    }
                });
            return (T[]) values.invoke(enumType, null);
        } catch (Exception e) {
            return null;
        }
    }
}
