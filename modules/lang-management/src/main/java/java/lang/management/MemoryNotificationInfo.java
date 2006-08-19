package java.lang.management;

import javax.management.openmbean.CompositeData;

/**
 * <p>
 * Memory notification information.
 * </p>
 * 
 * @since 1.5
 */
public class MemoryNotificationInfo {
    public static final String MEMORY_COLLECTION_THRESHOLD_EXCEEDED = "java.management.memory.collection.threshold.exceeded";

    public static final String MEMORY_THRESHOLD_EXCEEDED = "java.management.memory.threshold.exceeded";

    /**
     * <p>
     * Constructs a MemoryNotificationInfo object from the CompositeData passed.
     * </p>
     * 
     * @param cd The CompositeDate object to retrieve data from.
     * @return A MemoryNotificationInfo instance.
     * @throws IllegalArgumentException if <code>cd</code> does not contain
     *         MemoryUsage data.
     */
    public static MemoryNotificationInfo from(CompositeData cd) {
        String poolName = (String) cd.get("poolName");
        MemoryUsage usage = MemoryUsage.from((CompositeData) cd.get("usage"));
        long count = ((Long) cd.get("count")).longValue();
        return new MemoryNotificationInfo(poolName, usage, count);
    }

    private final String poolName;

    private final MemoryUsage usage;

    private final long count;

    /**
     * <p>
     * Constructs a MemoryNotificationInfo instance.
     * </p>
     * 
     * @param poolName The memory pool name.
     * @param usage The memory usage snapshot.
     * @param count The threshold crossing count.
     */
    public MemoryNotificationInfo(String poolName, MemoryUsage usage, long count) {
        super();
        this.poolName = poolName;
        this.usage = usage;
        this.count = count;
    }

    public String getPoolName() {
        return poolName;
    }

    public MemoryUsage getUsage() {
        return usage;
    }

    public long getCount() {
        return count;
    }
}
