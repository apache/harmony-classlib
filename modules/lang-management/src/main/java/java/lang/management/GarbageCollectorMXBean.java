package java.lang.management;

/**
 * <p>
 * The management interface for the garbage collector system.
 * </p>
 * <p>
 * <code>ObjectName</code> pattern: java.lang:type=GarbageCollector,name=<i>collector_name</i>
 * </p>
 * 
 * @since 1.5
 */
public interface GarbageCollectorMXBean extends MemoryManagerMXBean {
    /**
     * <p>
     * The number of collections that have been executed by this collector. A
     * value of <code>-1</code> means that collection counts are undefined for
     * this collector.
     * </p>
     * 
     * @return The number of collections executed.
     */
    long getCollectionCount();

    /**
     * <p>
     * The approximate, cumulative time (in milliseconds) spent executing
     * collections for this collector.
     * </p>
     * 
     * @return The time spent collecting garbage.
     */
    long getCollectionTime();
}
