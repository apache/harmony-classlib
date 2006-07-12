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
import java.lang.reflect.Modifier;

import org.apache.harmony.concurrent.AtomicSupport;

/**
 * A reflection-based utility that enables atomic updates to
 * designated <tt>volatile long</tt> fields of designated classes.
 * This class is designed for use in atomic data structures in which
 * several fields of the same node are independently subject to atomic
 * updates.
 *
 * <p> Note that the guarantees of the <tt>compareAndSet</tt> method
 * in this class are weaker than in other atomic classes. Because this
 * class cannot ensure that all uses of the field are appropriate for
 * purposes of atomic access, it can guarantee atomicity and volatile
 * semantics only with respect to other invocations of
 * <tt>compareAndSet</tt> and <tt>set</tt>.
 *
 * @since 1.5
 * @author Doug Lea
 * @param <T> The type of the object holding the updatable field
 */
public abstract class  AtomicLongFieldUpdater<T>  {
    
    private static final AtomicSupport SUPPORT = AtomicSupport.getInstance();
    
    /**
     * Creates an updater for objects with the given field.  The Class
     * argument is needed to check that reflective types and generic
     * types match.
     * @param tclass the class of the objects holding the field
     * @param fieldName the name of the field to be updated.
     * @return the updater
     * @throws IllegalArgumentException if the field is not a
     * volatile long type.
     * @throws RuntimeException with a nested reflection-based
     * exception if the class does not hold field or is the wrong type.
     */
    public static <U> AtomicLongFieldUpdater<U> newUpdater(Class<U> tclass, String fieldName) {
        return new CASUpdater<U>(tclass, fieldName);
    }

    /**
     * Protected do-nothing constructor for use by subclasses.
     */
    protected AtomicLongFieldUpdater() {
    }

    /**
     * Atomically set the value of the field of the given object managed
     * by this Updater to the given updated value if the current value
     * <tt>==</tt> the expected value. This method is guaranteed to be
     * atomic with respect to other calls to <tt>compareAndSet</tt> and
     * <tt>set</tt>, but not necessarily with respect to other
     * changes in the field.
     * @param obj An object whose field to conditionally set
     * @param expect the expected value
     * @param update the new value
     * @return true if successful.
     * @throws ClassCastException if <tt>obj</tt> is not an instance
     * of the class possessing the field established in the constructor.
     */

    public abstract boolean compareAndSet(T obj, long expect, long update);

    /**
     * Atomically set the value of the field of the given object managed
     * by this Updater to the given updated value if the current value
     * <tt>==</tt> the expected value. This method is guaranteed to be
     * atomic with respect to other calls to <tt>compareAndSet</tt> and
     * <tt>set</tt>, but not necessarily with respect to other
     * changes in the field, and may fail spuriously.
     * @param obj An object whose field to conditionally set
     * @param expect the expected value
     * @param update the new value
     * @return true if successful.
     * @throws ClassCastException if <tt>obj</tt> is not an instance
     * of the class possessing the field established in the constructor.
     */

    public abstract boolean weakCompareAndSet(T obj, long expect, long update);

    /**
     * Set the field of the given object managed by this updater. This
     * operation is guaranteed to act as a volatile store with respect
     * to subsequent invocations of <tt>compareAndSet</tt>.
     * @param obj An object whose field to set
     * @param newValue the new value
     */
    public abstract void set(T obj, long newValue);

    /**
     * Get the current value held in the field by the given object.
     * @param obj An object whose field to get
     * @return the current value
     */
    public abstract long get(T obj);

    /**
     * Set to the given value and return the old value.
     *
     * @param obj An object whose field to get and set
     * @param newValue the new value
     * @return the previous value
     */
    public long getAndSet(T obj, long newValue) {
        for (;;) {
            long current = get(obj);
            if (compareAndSet(obj, current, newValue))
                return current;
        }
    }

    /**
     * Atomically increment by one the current value.
     * @param obj An object whose field to get and set
     * @return the previous value;
     */
    public long getAndIncrement(T obj) {
        for (;;) {
            long current = get(obj);
            long next = current + 1;
            if (compareAndSet(obj, current, next))
                return current;
        }
    }


    /**
     * Atomically decrement by one the current value.
     * @param obj An object whose field to get and set
     * @return the previous value;
     */
    public long getAndDecrement(T obj) {
        for (;;) {
            long current = get(obj);
            long next = current - 1;
            if (compareAndSet(obj, current, next))
                return current;
        }
    }


    /**
     * Atomically add the given value to current value.
     * @param obj An object whose field to get and set
     * @param delta the value to add
     * @return the previous value;
     */
    public long getAndAdd(T obj, long delta) {
        for (;;) {
            long current = get(obj);
            long next = current + delta;
            if (compareAndSet(obj, current, next))
                return current;
        }
    }

    /**
     * Atomically increment by one the current value.
     * @param obj An object whose field to get and set
     * @return the updated value;
     */
    public long incrementAndGet(T obj) {
        for (;;) {
            long current = get(obj);
            long next = current + 1;
            if (compareAndSet(obj, current, next))
                return next;
        }
    }


    /**
     * Atomically decrement by one the current value.
     * @param obj An object whose field to get and set
     * @return the updated value;
     */
    public long decrementAndGet(T obj) {
        for (;;) {
            long current = get(obj);
            long next = current - 1;
            if (compareAndSet(obj, current, next))
                return next;
        }
    }


    /**
     * Atomically add the given value to current value.
     * @param obj An object whose field to get and set
     * @param delta the value to add
     * @return the updated value;
     */
    public long addAndGet(T obj, long delta) {
        for (;;) {
            long current = get(obj);
            long next = current + delta;
            if (compareAndSet(obj, current, next))
                return next;
        }
    }

    private static class CASUpdater<T> extends AtomicLongFieldUpdater<T> {
        private final Field field;
        private final Class<T> tclass;

        CASUpdater(Class<T> tclass, String fieldName) {
            Field field = null;
            try {
                field = tclass.getDeclaredField(fieldName);
            } catch(Exception ex) {
                throw new RuntimeException(ex);
            }
            
            Class fieldt = field.getType();
            if (fieldt != long.class)
                throw new IllegalArgumentException("Must be long type");
            
            if (!Modifier.isVolatile(field.getModifiers()))
                throw new IllegalArgumentException("Must be volatile type");
            
            this.tclass = tclass;
            this.field = field;
        }

        public boolean compareAndSet(T obj, long expect, long update) {
            if (!tclass.isInstance(obj))
                throw new ClassCastException();
            return SUPPORT.compareAndSet(obj, field, expect, update);
        }

        public boolean weakCompareAndSet(T obj, long expect, long update) {
            if (!tclass.isInstance(obj))
                throw new ClassCastException();
            return SUPPORT.compareAndSet(obj, field, expect, update);
        }

        public void set(T obj, long newValue) {
            if (!tclass.isInstance(obj))
                throw new ClassCastException();
            
            SUPPORT.volatileSetLong(obj, field, newValue);
        }

        public long get(T obj) {
            if (!tclass.isInstance(obj))
                throw new ClassCastException();
            
            return SUPPORT.volatileGetLong(obj, field);
        }
    }
}

