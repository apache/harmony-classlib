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

package org.apache.harmony.kernel.vm;

import java.lang.reflect.Field;

/**
 * <p>
 * This class must be implemented by the VM to support Object manipulation.
 * </p>
 */
public class Objects {

    /**
     * <p>
     * Retrieves the offset value of the {@link Field} for use by other methods
     * in this class.
     * </p>
     * 
     * @param field The {@link Field} to retrieve the offset for.
     * @return The offset value.
     */
    public static long objectFieldOffset(Field field) {
        return 0L;
    }

    /**
     * <p>
     * Compares and swaps the value of an int-typed field on an Object instance.
     * </p>
     * 
     * @param object The instance containing the field.
     * @param fieldOffset The offset value of the field.
     * @param expected The expected value of the field.
     * @param update The new value to write to the field.
     * @return <code>true</code> if the field was updated, <code>false</code>
     *         otherwise.
     */
    public static boolean compareAndSwapInt(Object object, long fieldOffset, int expected,
            int update) {
        return false;
    }

    /**
     * <p>
     * Compares and swaps the value of a long-typed field on an Object instance.
     * </p>
     * 
     * @param object The instance containing the field.
     * @param fieldOffset The offset value of the field.
     * @param expected The expected value of the field.
     * @param update The new value to write to the field.
     * @return <code>true</code> if the field was updated, <code>false</code>
     *         otherwise.
     */
    public static boolean compareAndSwapLong(Object object, long fieldOffset, long expected,
            long update) {
        return false;
    }

    /**
     * <p>
     * Compares and swaps the value of an Object-typed field on an Object
     * instance.
     * </p>
     * 
     * @param object The instance containing the field.
     * @param fieldOffset The offset value of the field.
     * @param expected The expected value of the field.
     * @param update The new value to write to the field.
     * @return <code>true</code> if the field was updated, <code>false</code>
     *         otherwise.
     */
    public static boolean compareAndSwapObject(Object object, long fieldOffset,
            Object expected, Object update) {
        return false;
    }

    /**
     * <p>
     * Retrieves the base offset for the array Class given. The Class passed
     * MUST me any array type, such that the method {@link Class#isArray()}
     * returns <code>true</code>. For example, <code>int[].class</code>.
     * </p>
     * 
     * @param clazz The array Class object.
     * @return The base offset value.
     */
    public static int arrayBaseOffset(Class<?> clazz) {
        return 0;
    }

    /**
     * <p>
     * Retrieves the array index scale for the array Class given. The index
     * scale is the value used to determine the offset of a particular element
     * in the array given the array's base offset and an index. The following
     * code snippet illustrates the usage.
     * </p>
     * 
     * <pre>
     * int base = Objects.arrayBaseOffset(int[].class);
     * 
     * int scale = Objects.arrayIndexScale(int[].class);
     * 
     * int elementIdx = 1;
     * 
     * int[] array = { 0, 1, 2 };
     * 
     * long offsetForIdx = base + (elementIdx * scale);
     * </pre>
     * 
     * <p>
     * The Class passed MUST me any array type, such that the method
     * {@link Class#isArray()} returns <code>true</code>. For example,
     * <code>int[].class</code>.
     * </p>
     * 
     * @param clazz The array Class object.
     * @return The index scale value.
     */
    public static int arrayIndexScale(Class<?> clazz) {
        return 0;
    }

    /**
     * <p>
     * Writes an int value to an Object's field as though it were declared
     * <code>volatile</code>.
     * </p>
     * 
     * @param object The instance containing the field to write to.
     * @param fieldOffset The offset of the field to write to.
     * @param newValue The value to write.
     */
    public static void putIntVolatile(Object object, long fieldOffset, int newValue) {
        return;
    }

    /**
     * <p>
     * Reads an int value from an Object's field as though it were declared
     * <code>volatile</code>.
     * </p>
     * 
     * @param object The instance containing the field to read from.
     * @param fieldOffset The offset of the field to read from.
     * @return The value that was read.
     */
    public static int getIntVolatile(Object object, long fieldOffset) {
        return 0;
    }

    /**
     * <p>
     * Writes a long value to an Object's field as though it were declared
     * <code>volatile</code>.
     * </p>
     * 
     * @param object The instance containing the field to write to.
     * @param fieldOffset The offset of the field to write to.
     * @param newValue The value to write.
     */
    public static void putLongVolatile(Object object, long fieldOffset, long newValue) {
        return;
    }

    /**
     * <p>
     * Reads a long value from an Object's field as though it were declared
     * <code>volatile</code>.
     * </p>
     * 
     * @param object The instance containing the field to read from.
     * @param fieldOffset The offset of the field to read from.
     * @return The value that was read.
     */
    public static long getLongVolatile(Object object, long fieldOffset) {
        return 0;
    }

    /**
     * <p>
     * Writes an Object reference value to an Object's field as though it were
     * declared <code>volatile</code>.
     * </p>
     * 
     * @param object The instance containing the field to write to.
     * @param fieldOffset The offset of the field to write to.
     * @param newValue The value to write.
     */
    public static void putObjectVolatile(Object object, long fieldOffset, Object newValue) {
        return;
    }

    /**
     * <p>
     * Writes an int value to an Object's field as though it were declared
     * <code>volatile</code>.
     * </p>
     * 
     * @param object The instance containing the field to write to.
     * @param fieldOffset The offset of the field to write to.
     * @param newValue The value to write.
     */
    public static Object getObjectVolatile(Object object, long fieldOffset) {
        return null;
    }

    /**
     * <p>
     * Writes a long value to an Object's field.
     * </p>
     * 
     * @param object The instance containing the field to write to.
     * @param fieldOffset The offset of the field to write to.
     * @param newValue The value to write.
     */
    public static void putLong(Object object, long fieldOffset, long newValue) {
        return;
    }

    /**
     * <p>
     * Reads a long value from an Object's field.
     * </p>
     * 
     * @param object The instance containing the field to read from.
     * @param fieldOffset The offset of the field to read from.
     * @return The value that was read.
     */
    public static long getLong(Object object, long fieldOffset) {
        return 0L;
    }

    private Objects() {
    }
}
