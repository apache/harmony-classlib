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

/*
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/licenses/publicdomain
 */

package java.util.concurrent.atomic;
import java.lang.reflect.Field;

import org.apache.harmony.concurrent.AtomicSupport;

/**
 * A <tt>boolean</tt> value that may be updated atomically. See the
 * {@link java.util.concurrent.atomic} package specification for
 * description of the properties of atomic variables. An
 * <tt>AtomicBoolean</tt> is used in applications such as atomically
 * updated flags, and cannot be used as a replacement for a
 * {@link java.lang.Boolean}.
 *
 * @since 1.5
 * @author Doug Lea
 */
public class AtomicBoolean implements java.io.Serializable {
    private static final long serialVersionUID = 4654671469794556979L;

    private static final AtomicSupport SUPPORT = AtomicSupport.getInstance();
    
    private static final Field VALUE_FIELD;
    static {
        try {
            VALUE_FIELD = AtomicBoolean.class.getDeclaredField("value");
        } catch (Exception ex) {
            throw new AssertionError(ex);
        }
    }

    private volatile boolean value;

    /**
     * Creates a new <tt>AtomicBoolean</tt> with the given initial value.
     *
     * @param initialValue the initial value
     */
    public AtomicBoolean(boolean initialValue) {
        value = initialValue;
    }

    /**
     * Creates a new <tt>AtomicBoolean</tt> with initial value <tt>false</tt>.
     */
    public AtomicBoolean() {
    }

    /**
     * Returns the current value.
     *
     * @return the current value
     */
    public final boolean get() {
        return value;
    }

    /**
     * Atomically sets the value to the given update value if the
     * current value is equal to the expected value.  Any given
     * invocation of this operation may fail (return
     * <tt>false</tt>) spuriously, but repeated invocation when
     * the current value holds the expected value and no other thread
     * is also attempting to set the value will eventually succeed.
     *
     * @param expect the expected value
     * @param update the new value
     * @return true if successful
     */
    public final boolean compareAndSet(boolean expect, boolean update) {
        return SUPPORT.compareAndSet(this, VALUE_FIELD, expect, update);
    }

    /**
     * Atomically set the value to the given updated value
     * if the current value <tt>==</tt> the expected value.
     * May fail spuriously.
     * @param expect the expected value
     * @param update the new value
     * @return true if successful.
     */
    public boolean weakCompareAndSet(boolean expect, boolean update) {
        return SUPPORT.compareAndSet(this, VALUE_FIELD, expect, update);
    }

    /**
     * Unconditionally sets to the given value.
     *
     * @param newValue the new value
     */
    public final void set(boolean newValue) {
        value = newValue;
    }

    /**
     * Sets to the given value and returns the previous value.
     *
     * @param newValue the new value
     * @return the previous value
     */
    public final boolean getAndSet(boolean newValue) {
        for (;;) {
            boolean current = get();
            if (compareAndSet(current, newValue))
                return current;
        }
    }

    /**
     * Returns the String representation of the current value.
     * @return the String representation of the current value.
     */
    public String toString() {
        return Boolean.toString(get());
    }

}
