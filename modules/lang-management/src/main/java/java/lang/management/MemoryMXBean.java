package java.lang.management;

/**
 * <p>
 * The management interface for the memory system.
 * </p>
 * <p>
 * <code>ObjectName</code>: java.lang:type=Memory
 * </p>
 * 
 * @since 1.5
 */
public interface MemoryMXBean {
    /**
     * <p>
     * Invokes the JVM's garbage collector; equivalent to {@link System#gc()}.
     * </p>
     */
    void gc();

    /**
     * <p>
     * The current memory usage for the heap; memory used for object allocation.
     * </p>
     * 
     * @return A {@link MemoryUsage} instance representing heap memory.
     */
    MemoryUsage getHeapMemoryUsage();

    /**
     * <p>
     * The current memory usage outside of the heap.
     * </p>
     * 
     * @return A {@link MemoryUsage} instance representing non-heap memory.
     */
    MemoryUsage getNonHeapMemoryUsage();

    /**
     * <p>
     * The approximate number of objects that are about to be finalized.
     * </p>
     * 
     * @return The number of objects about to be finalized.
     */
    int getObjectPendingFinalizationCount();

    /**
     * <p>
     * Indicates whether or not the verbose output is enabled for the memory
     * system.
     * </p>
     * 
     * @return <code>true</code> is verbose output is enabled, otherwise
     *         <code>false</code>.
     */
    boolean isVerbose();

    /**
     * <p>
     * Enables or disables the verbose output of the memory system.
     * </p>
     * 
     * @param value <code>true</code> to enable, <code>false</code> to
     *        disable.
     * @throws SecurityException if caller doesn't have
     *         <code>ManagementPermission("control")</code>.
     */
    void setVerbose(boolean value);
}
