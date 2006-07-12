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

/**
 * <p>
 * The SPI that VM implementations must implement to provide support for the
 * <code>java.util.concurrent.locks</code> classes and others. Implementations
 * of this class are registered as <a
 * href="http://java.sun.com/j2se/1.5.0/docs/guide/jar/jar.html#Service%20Provider">Service
 * Providers<a> as defined by the <a
 * href="http://java.sun.com/j2se/1.5.0/docs/guide/jar/jar.html">JAR File
 * Specification</a>. The configuration file for this class has a ClassLoader
 * resource URI of
 * <code>META-INF/services/org.apache.harmony.concurrent.LocksSupport</code>.
 * </p>
 * 
 * @see java.util.concurrent.locks
 */
public abstract class LocksSupport {

    /*
     * The implementation is currently just a simple singleton, but can change
     * in the future.
     */
    private static LocksSupport INSTANCE = ServiceLoader.load(LocksSupport.class);

    /**
     * <p>
     * Gets an instance of this service provider. See the class documentation
     * for details on configuring a provider implementation.
     * </p>
     * 
     * @return An instance of LocksSupport.
     * @throws IllegalStateException if an implementation could not be loaded
     *         for any reason.
     */
    public static LocksSupport getInstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException(
                    "An implementation of LocksSupport could not be loaded.");
        }
        return INSTANCE;
    }

    /**
     * <p>
     * All implementations MUST provide a public, nullary constructor.
     * </p>
     */
    protected LocksSupport() {
        super();
    }

    /**
     * <p>
     * Parks the current thread for maximum amount of time or until interruption
     * event. See the
     * {@link java.util.concurrent.locks.LockSupport#parkNanos(long)} method for
     * details.
     * </p>
     * 
     * @param nanos The maximum number of nanoseconds to park the current
     *        thread.
     * @see Thread#currentThread()
     * @see java.util.concurrent.locks.LockSupport#parkNanos(long)
     */
    public abstract void parkNanos(long nanos);

    /**
     * <p>
     * Parks the current thread until a specified time or until an interruption
     * event. See the
     * {@link java.util.concurrent.locks.LockSupport#parkUntil(long)} method for
     * details.
     * </p>
     * 
     * @param stopTime The time when the current thread should be unparked. Time
     *        is defined using the {@link System#currentTimeMillis()}
     *        definition.
     * @see Thread#currentThread()
     * @see System#currentTimeMillis()
     * @see java.util.concurrent.locks.LockSupport#parkUntil(long)
     */
    public abstract void parkUntil(long stopTime);

    /**
     * <p>
     * Unparks the Thread passed if it's parked or if not parked guarantees that
     * the next call to the Thread will not be blocked. See the
     * {@link java.util.concurrent.locks.LockSupport#unpark(Thread)} method for
     * details.
     * </p>
     * 
     * @param thread The Thread to unpark; if <code>null</code> or not
     *        started, then ignore.
     * 
     * @see java.util.concurrent.locks.LockSupport#unpark(Thread)
     */
    public abstract void unpark(Thread thread);
}
