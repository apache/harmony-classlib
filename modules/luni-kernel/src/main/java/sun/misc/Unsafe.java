package sun.misc;

import java.lang.reflect.Field;

/**
 * <p>The Unsafe service.</p>
 *
 */
public class Unsafe {

    /**
     * <p>
     * Retrieves an instance of this service.
     * </p>
     * 
     * @return An instance of Unsafe.
     */
    public static Unsafe getUnsafe() {
        return null;
    }

    /**
     * <p>
     * Retrieves the offset value of the {@link Field} for use by other methods
     * in this class.
     * </p>
     * 
     * @param field The {@link Field} to retrieve the offset for.
     * @return The offset value.
     */
    public long objectFieldOffset(Field field) {
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
    public boolean compareAndSwapInt(Object object, long fieldOffset, int expected, int update) {
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
    public boolean compareAndSwapLong(Object object, long fieldOffset, long expected,
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
    public boolean compareAndSwapObject(Object object, long fieldOffset, Object expected,
            Object update) {
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
    public int arrayBaseOffset(Class<?> clazz) {
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
     * int base = unsafe.arrayBaseOffset(int[].class);
     * 
     * int scale = unsafe.arrayIndexScale(int[].class);
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
    public int arrayIndexScale(Class<?> clazz) {
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
    public void putIntVolatile(Object object, long fieldOffset, int newValue) {
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
    public int getIntVolatile(Object object, long fieldOffset) {
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
    public void putLongVolatile(Object object, long fieldOffset, long newValue) {
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
    public long getLongVolatile(Object object, long fieldOffset) {
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
    public void putObjectVolatile(Object object, long fieldOffset, Object newValue) {
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
    public Object getObjectVolatile(Object object, long fieldOffset) {
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
    public void putLong(Object object, long fieldOffset, long newValue) {
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
    public long getLong(Object object, long fieldOffset) {
        return 0L;
    }

    /**
     * <p>
     * Unparks the {@link Thread} that's passed.
     * </p>
     * 
     * @param thread The {@link Thread} to unpark.
     */
    public void unpark(Thread thread) {
        return;
    }

    /**
     * <p>
     * Parks the {@link Thread#currentThread() current thread} either for a set
     * number of nanoseconds or until a future point in time.
     * </p>
     * 
     * @param timestamp If <code>true</code> <code>nanosOrTimestamp</code>
     *        should be consider as a timestamp based on
     *        {@link System#currentTimeMillis()}. If <code>false</code>,
     *        then <code>nanosOrTimestamp</code> should be considered as a
     *        relative number of nanoseconds from when this method was called;
     *        the value <code>0L</code> can be used in conjunction with this
     *        to indicate that time is not a factor when parking the thread.
     * @param nanosOrTimestamp Either a relative number of nanoseconds or a
     *        timestamp in milliseconds as defined by
     *        {@link System#currentTimeMillis()}.
     */
    public void park(boolean timestamp, long nanosOrTimestamp) {

    }
}
