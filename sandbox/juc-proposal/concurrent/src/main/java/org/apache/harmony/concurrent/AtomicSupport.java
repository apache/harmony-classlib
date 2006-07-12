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

package org.apache.harmony.concurrent;

import java.lang.reflect.Field;

/**
 * <p>
 * The SPI that VM implementations must implement to provide support for the
 * <code>java.util.concurrent.atomic</code> classes and others.
 * Implementations of this class are registered as <a
 * href="http://java.sun.com/j2se/1.5.0/docs/guide/jar/jar.html#Service%20Provider">Service
 * Providers<a> as defined by the <a
 * href="http://java.sun.com/j2se/1.5.0/docs/guide/jar/jar.html">JAR File
 * Specification</a>. The configuration file for this class has a ClassLoader
 * resource URI of
 * <code>META-INF/services/org.apache.harmony.concurrent.AtomicSupport</code>.
 * </p>
 * 
 * @see java.util.concurrent.atomic
 */
public abstract class AtomicSupport {

    /*
     * The implementation is currently just a simple singleton, but can change
     * in the future.
     */
    private static AtomicSupport INSTANCE = ServiceLoader.load(AtomicSupport.class);

    /**
     * <p>
     * Gets an instance of this service provider. See the class documentation
     * for details on configuring a provider implementation.
     * </p>
     * 
     * @return An instance of AtomicSupport.
     * @throws IllegalStateException if an implementation could not be loaded
     *         for any reason.
     */
    public static AtomicSupport getInstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException(
                    "An implementation of AtomicSupport could not be loaded.");
        }
        return INSTANCE;
    }

    /**
     * <p>
     * All implementations MUST provide a public, nullary constructor.
     * </p>
     */
    protected AtomicSupport() {
        super();
    }

    /**
     * <p>
     * Atomically compare and set a boolean value on the field of the object
     * instance given if the current value equals the expected value.
     * </p>
     * 
     * @param object A non-null instance of the object with a boolean field.
     * @param field A non-null, {@link Boolean#TYPE} {@link Field} associated
     *        with <code>object</code>.
     * @param expectedValue The expected boolean value of <code>field</code>.
     * @param newValue The new boolean value to set for <code>field</code>.
     * @return <code>true</code> if the current value ==
     *         <code>expectedValue</code> and <code>newValue</code>;
     *         <code>false</code> if not set.
     */
    public abstract boolean compareAndSet(Object object, Field field, boolean expectedValue,
            boolean newValue);

    /**
     * <p>
     * Atomically compare and set an int value on the field of the object
     * instance given if the current value equals the expected value.
     * </p>
     * 
     * @param object A non-null instance of the object with an int field.
     * @param field A non-null, {@link Integer#TYPE} {@link Field} associated
     *        with <code>object</code>.
     * @param expectedValue The expected int value of <code>field</code>.
     * @param newValue The new int value to set for <code>field</code>.
     * @return <code>true</code> if the current value ==
     *         <code>expectedValue</code> and <code>newValue</code>;
     *         <code>false</code> if not set.
     */
    public abstract boolean compareAndSet(Object object, Field field, int expectedValue,
            int newValue);

    /**
     * <p>
     * Atomically compare and set an int value in the int[] given if the current
     * value equals the expected value.
     * </p>
     * 
     * @param array The non-null int[] to compare and set into.
     * @param index The index of the int value to compare and set in
     *        <code>array</code>.
     * @param expectedValue The expected int value of the element at
     *        <code>index</code> in <code>array</code>.
     * @param newValue The new int value to set for <code>field</code>.
     * @return <code>true</code> if the current value ==
     *         <code>expectedValue</code> and <code>newValue</code>;
     *         <code>false</code> if not set.
     */
    public abstract boolean compareAndSet(int[] array, int index, int expectedValue,
            int newValue);

    /**
     * <p>
     * Atomically compare and set a long value on the field of the object
     * instance given if the current value equals the expected value.
     * </p>
     * 
     * @param object A non-null instance of the object with a long field.
     * @param field A non-null, {@link Long#TYPE} {@link Field} associated with
     *        <code>object</code>.
     * @param expectedValue The expected long value of <code>field</code>.
     * @param newValue The new long to set for <code>field</code>.
     * @return <code>true</code> if the current value ==
     *         <code>expectedValue</code> and <code>newValue</code>;
     *         <code>false</code> if not set.
     */
    public abstract boolean compareAndSet(Object object, Field field, long expectedValue,
            long newValue);

    /**
     * <p>
     * Atomically compare and set a long value in the long[] given if the
     * current value equals the expected value.
     * </p>
     * 
     * @param array The non-null long[] to compare and set into.
     * @param index The index of the long value to compare and set in
     *        <code>array</code>.
     * @param expectedValue The expected long value of the element at
     *        <code>index</code> in <code>array</code>.
     * @param newValue The new long value to set for <code>field</code>.
     * @return <code>true</code> if the current value ==
     *         <code>expectedValue</code> and <code>newValue</code>;
     *         <code>false</code> if not set.
     */
    public abstract boolean compareAndSet(long[] array, int index, long expectedValue,
            long newValue);

    /**
     * <p>
     * Atomically compare and set an Object reference value on the field of the
     * object instance given if the current value equals the expected value.
     * This method only compares references to determine equivalence.
     * </p>
     * 
     * @param object A non-null instance of the object with an Object field.
     * @param field A non-null, object reference {@link Field} associated with
     *        <code>object</code>.
     * @param expectedValue The object reference long value of
     *        <code>field</code>.
     * @param newValue The new object reference value to set for
     *        <code>field</code>.
     * @return <code>true</code> if the current value ==
     *         <code>expectedValue</code> and <code>newValue</code>;
     *         <code>false</code> if not set.
     */
    public abstract boolean compareAndSet(Object object, Field field, Object expectedValue,
            Object newValue);

    /**
     * <p>
     * Atomically compare and set an Object reference value in the Object[]
     * given if the current value equals the expected value. This method only
     * compares references to determine equivalence.
     * </p>
     * 
     * @param array The non-null Object[] to compare and set into.
     * @param index The index of the Object reference value to compare and set
     *        in <code>array</code>.
     * @param expectedValue The expected Object reference value of the element
     *        at <code>index</code> in <code>array</code>.
     * @param newValue The new Object reference value to set for
     *        <code>field</code>.
     * @return <code>true</code> if the current value ==
     *         <code>expectedValue</code> and <code>newValue</code>;
     *         <code>false</code> if not set.
     */
    public abstract boolean compareAndSet(Object[] array, int index, Object expectedValue,
            Object newValue);

    /**
     * <p>
     * Performs a read of an int element from an int[] as though that element
     * were a <code>volatile</code> field.
     * </p>
     * 
     * @param array The non-null int[] to read from.
     * @param index The index of the int element to read in <code>array</code>.
     * @return The int element that was read.
     */
    public abstract int volatileGet(int[] array, int index);

    /**
     * <p>
     * Performs a write of an int element to an int[] as though that element
     * were a <code>volatile</code> field.
     * </p>
     * 
     * @param array The non-null int[] to write to.
     * @param index The index of the int element to write in <code>array</code>.
     * @param value The int value to write to <code>array</code>.
     */
    public abstract void volatileSet(int[] array, int index, int value);

    /**
     * <p>
     * Performs a read of a long element from an long[] as though that element
     * were a <code>volatile</code> field.
     * </p>
     * 
     * @param array The non-null long[] to read from.
     * @param index The index of the long element to read in <code>array</code>.
     * @return The long element that was read.
     */
    public abstract long volatileGet(long[] array, int index);

    /**
     * <p>
     * Performs a write of a long element to an long[] as though that element
     * were a <code>volatile</code> field.
     * </p>
     * 
     * @param array The non-null long[] to write to.
     * @param index The index of the long element to write in <code>array</code>.
     * @param value The long value to write to <code>array</code>.
     */
    public abstract void volatileSet(long[] array, int index, long value);

    /**
     * <p>
     * Performs a read of an Object reference element from an Object[] as though
     * that element were a <code>volatile</code> field.
     * </p>
     * 
     * @param array The non-null Object[] to read from.
     * @param index The index of the Object reference element to read in
     *        <code>array</code>.
     * @return The Object reference element that was read.
     */
    public abstract Object volatileGet(Object[] array, int index);

    /**
     * <p>
     * Performs a write of an Object reference element to an Object[] as though
     * that element were a <code>volatile</code> field.
     * </p>
     * 
     * @param array The non-null Object[] to write to.
     * @param index The index of the Object reference element to write in
     *        <code>array</code>.
     * @param value The Object reference value to write to <code>array</code>.
     */
    public abstract void volatileSet(Object[] array, int index, Object value);

    /**
     * <p>
     * Performs a read from an int field on an Object as though that field was a
     * <code>volatile</code> field.
     * </p>
     * 
     * @param object The non-null Object to read from.
     * @param field The non-null, {@link Integer#TYPE} {@link Field} of
     *        <code>object</code> to read.
     * @return The int value that was read.
     */
    public abstract int volatileGetInt(Object object, Field field);

    /**
     * <p>
     * Performs a write to an int field on an Object as though that field was a
     * <code>volatile</code> field.
     * </p>
     * 
     * @param object The non-null Object to write to.
     * @param field The non-null, {@link Integer#TYPE} {@link Field} of
     *        <code>object</code> to read.
     * @param value The int value to write.
     */
    public abstract void volatileSetInt(Object object, Field field, int value);

    /**
     * <p>
     * Performs a read from an long field on an Object as though that field was
     * a <code>volatile</code> field.
     * </p>
     * 
     * @param object The non-null Object to read from.
     * @param field The non-null, {@link Long#TYPE} {@link Field} of
     *        <code>object</code> to read.
     * @return The long value that was read.
     */
    public abstract long volatileGetLong(Object object, Field field);

    /**
     * <p>
     * Performs a write to an long field on an Object as though that field was a
     * <code>volatile</code> field.
     * </p>
     * 
     * @param object The non-null Object to write to.
     * @param field The non-null, {@link Integer#TYPE} {@link Field} of
     *        <code>object</code> to read.
     * @param value The long value to write.
     */
    public abstract void volatileSetLong(Object object, Field field, long value);

    /**
     * <p>
     * Performs a read from an Object reference field on an Object as though
     * that field was a <code>volatile</code> field.
     * </p>
     * 
     * @param object The non-null Object to read from.
     * @param field The non-null {@link Field} of <code>object</code> to read.
     * @return The Object reference value that was read.
     */
    public abstract Object volatileGetObject(Object object, Field field);

    /**
     * <p>
     * Performs a write to an Object reference field on an Object as though that
     * field was a <code>volatile</code> field.
     * </p>
     * 
     * @param object The non-null Object to write to.
     * @param field The non-null {@link Field} of <code>object</code> to read.
     * @param value The Object reference value to write.
     */
    public abstract void volatileSetObject(Object object, Field field, Object value);
}
