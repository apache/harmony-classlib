package java.lang.management;

/**
 * <p>
 * The management interface for a memory manager system.
 * </p>
 * <p>
 * <code>ObjectName</code> pattern: java.lang:type=MemoryManager,name=<i>manager_name</i>
 * </p>
 * 
 * @since 1.5
 */
public interface MemoryManagerMXBean {
    /**
     * <p>
     * The names of the memory pools being managed by this manager.
     * </p>
     * 
     * @return A <code>String[]</code> of pool names.
     */
    String[] getMemoryPoolNames();

    /**
     * <p>
     * The name of the memory manager.
     * </p>
     * 
     * @return The manager's name.
     */
    String getName();

    /**
     * <p>
     * Indicates whether or not the manager is currently valid. A memory manager
     * may be removed by a JVM and become invalid.
     * </p>
     * 
     * @return <code>true</code> if the manager is valid, <code>false</code>
     *         otherwise.
     */
    boolean isValid();
}
