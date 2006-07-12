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
 * A <tt>long</tt> value that may be updated atomically.  See the
 * {@link java.util.concurrent.atomic} package specification for
 * description of the properties of atomic variables. An
 * <tt>AtomicLong</tt> is used in applications such as atomically
 * incremented sequence numbers, and cannot be used as a replacement
 * for a {@link java.lang.Long}. However, this class does extend
 * <tt>Number</tt> to allow uniform access by tools and utilities that
 * deal with numerically-based classes.
 *
 * @since 1.5
 * @author Doug Lea
 */
public class AtomicLong extends Number implements java.io.Serializable { 
    private static final long serialVersionUID = 1927816293512124184L;
    
    private static final AtomicSupport SUPPORT = AtomicSupport.getInstance();

    private static final Field VALUE_FIELD;
    static {
        try {
            VALUE_FIELD = AtomicLong.class.getDeclaredField("value");
        } catch (Exception ex) {
            throw new AssertionError(ex);
        }
    }

    private volatile long value;

    /**
     * Create a new AtomicLong with the given initial value.
     *
     * @param initialValue the initial value
     */
    public AtomicLong(long initialValue) {
        value = initialValue;
    }

    /**
     * Create a new AtomicLong with initial value <tt>0</tt>.
     */
    public AtomicLong() {
    }
  
    /**
     * Get the current value.
     *
     * @return the current value
     */
    public final long get() {
        return value;
    }
 
    /**
     * Set to the given value.
     *
     * @param newValue the new value
     */
    public final void set(long newValue) {
        value = newValue;
    }
  
    /**
     * Set to the give value and return the old value.
     *
     * @param newValue the new value
     * @return the previous value
     */
    public final long getAndSet(long newValue) {
        while (true) {
            long current = get();
            if (compareAndSet(current, newValue))
                return current;
        }
    }
  
    /**
     * Atomically set the value to the given updated value
     * if the current value <tt>==</tt> the expected value.
     * @param expect the expected value
     * @param update the new value
     * @return true if successful. False return indicates that
     * the actual value was not equal to the expected value.
     */
    public final boolean compareAndSet(long expect, long update) {
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
    public final boolean weakCompareAndSet(long expect, long update) {
        return SUPPORT.compareAndSet(this, VALUE_FIELD, expect, update);
    }
  
    /**
     * Atomically increment by one the current value.
     * @return the previous value
     */
    public final long getAndIncrement() {
        while (true) {
            long current = get();
            long next = current + 1;
            if (compareAndSet(current, next))
                return current;
        }
    }
  
  
    /**
     * Atomically decrement by one the current value.
     * @return the previous value
     */
    public final long getAndDecrement() {
        while (true) {
            long current = get();
            long next = current - 1;
            if (compareAndSet(current, next))
                return current;
        }
    }
  
  
    /**
     * Atomically add the given value to current value.
     * @param delta the value to add
     * @return the previous value
     */
    public final long getAndAdd(long delta) {
        while (true) {
            long current = get();
            long next = current + delta;
            if (compareAndSet(current, next))
                return current;
        }
    }
  
    /**
     * Atomically increment by one the current value.
     * @return the updated value
     */
    public final long incrementAndGet() {
        for (;;) {
            long current = get();
            long next = current + 1;
            if (compareAndSet(current, next))
                return next;
        }
    }
    
    /**
     * Atomically decrement by one the current value.
     * @return the updated value
     */
    public final long decrementAndGet() {
        for (;;) {
            long current = get();
            long next = current - 1;
            if (compareAndSet(current, next))
                return next;
        }
    }
  
  
    /**
     * Atomically add the given value to current value.
     * @param delta the value to add
     * @return the updated value
     */
    public final long addAndGet(long delta) {
        for (;;) {
            long current = get();
            long next = current + delta;
            if (compareAndSet(current, next))
                return next;
        }
    }
  
    /**
     * Returns the String representation of the current value.
     * @return the String representation of the current value.
     */
    public String toString() {
        return Long.toString(get());
    }


    public int intValue() {
	return (int)get();
    }

    public long longValue() {
	return (long)get();
    }

    public float floatValue() {
	return (float)get();
    }

    public double doubleValue() {
	return (double)get();
    }
  
}
