package java.lang.management;

/**
 * <p>
 * The management interface for the class loading system.
 * </p>
 * <p>
 * <code>ObjectName</code>: java.lang:type=ClassLoading
 * </p>
 * 
 * @since 1.5
 */
public interface ClassLoadingMXBean {
    /**
     * <p>
     * The number of classes currently loaded in the JVM.
     * </p>
     * 
     * @return The number of loaded classes.
     */
    int getLoadedClassCount();

    /**
     * <p>
     * The total number of classes the JVM has loaded since startup.
     * </p>
     * 
     * @return The total number of classes loaded.
     */
    long getTotalLoadedClassCount();

    /**
     * <p>
     * The number of classes that have been unloaded in the JVM since startup.
     * </p>
     * 
     * @return The number of classes unloaded.
     */
    long getUnloadedClassCount();

    /**
     * <p>
     * Indicates whether or not the verbose output is enabled for the class
     * loading system.
     * </p>
     * 
     * @return <code>true</code> is verbose output is enabled, otherwise <code>false</code>.
     */
    boolean isVerbose();

    /**
     * <p>
     * Enables or disables the verbose output of the class loading system.
     * </p>
     * 
     * @param value <code>true</code> to enable, <code>false</code> to
     *        disable.
     * @throws SecurityException if caller doesn't have
     *         <code>ManagementPermission("control")</code>.
     */
    void setVerbose(boolean value);
}
