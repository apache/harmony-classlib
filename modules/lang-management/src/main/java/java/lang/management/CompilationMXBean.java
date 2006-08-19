package java.lang.management;

/**
 * <p>
 * The management interface for the compilation system.
 * </p>
 * <p>
 * <code>ObjectName</code>: java.lang:type=Compilation
 * </p>
 * 
 * @since 1.5
 */
public interface CompilationMXBean {
    /**
     * <p>
     * The name of the compilation system.
     * </p>
     * 
     * @return The compiler's name.
     */
    String getName();

    /**
     * <p>
     * The approximate, cumulative time (in milliseconds) spent performing
     * compilation.
     * </p>
     * 
     * @return The total time spent compiling.
     * @throws UnsupportedOperationException if this is not supported.
     */
    long getTotalCompilationTime();

    /**
     * <p>
     * Indicates whether or not the JVM supports compilation time monitoring.
     * </p>
     * 
     * @return <code>true</code> if supported, otherwise <code>false</code>.
     */
    boolean isCompilationTimeMonitoringSupported();
}
