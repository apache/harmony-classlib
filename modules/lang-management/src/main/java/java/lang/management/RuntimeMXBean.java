package java.lang.management;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * The management interface for the runtime system.
 * </p>
 * <p>
 * <code>ObjectName</code>: java.lang:type=Runtime
 * </p>
 * 
 * @since 1.5
 */
public interface RuntimeMXBean {
    /**
     * <p>
     * The boot class path used by the JVM's bootstrap class loader.
     * </p>
     * 
     * @return The boot class path.
     * @throws UnsupportedOperationException if this is not supported.
     * @throws SecurityException if caller doesn't have
     *         <code>ManagementPermission("monitor")</code>.
     * @see #isBootClassPathSupported()
     */
    String getBootClassPath();

    /**
     * <p>
     * The class path of the JVM; equivalent to
     * <code>System.getProperty("java.class.path")</code>.
     * </p>
     * 
     * @return The class path.
     * @throws SecurityException if the
     *         {@link SecurityManager#checkPropertiesAccess(String)} doesn't
     *         allow access.
     */
    String getClassPath();

    /**
     * <p>
     * The input arguments passed to the JVM upon invocation.
     * </p>
     * 
     * @return A List of arguments passed to the JVM.
     * 
     * @throws SecurityException if caller doesn't have
     *         <code>ManagementPermission("monitor")</code>.
     */
    List<String> getInputArguments();

    /**
     * <p>
     * The library path of the JVM; equivalent to
     * <code>System.getProperty("java.library.path")</code>.
     * </p>
     * 
     * @return The library path.
     * @throws SecurityException if the
     *         {@link SecurityManager#checkPropertiesAccess(String)} doesn't
     *         allow access.
     */
    String getLibraryPath();

    /**
     * <p>
     * The version of the management specification implemented by the JVM.
     * </p>
     * 
     * @return The management specification version.
     */
    String getManagementSpecVersion();

    /**
     * <p>
     * The name of the JVM.
     * </p>
     * 
     * @return The name of the JVM.
     */
    String getName();

    /**
     * <p>
     * The JVM specification name; equivalent to
     * <code>System.getProperty("java.vm.specification.name")</code>.
     * </p>
     * 
     * @return The JVM specification name.
     * @throws SecurityException if the
     *         {@link SecurityManager#checkPropertiesAccess(String)} doesn't
     *         allow access.
     */
    String getSpecName();

    /**
     * <p>
     * The JVM specification vendor; equivalent to
     * <code>System.getProperty("java.vm.specification.vendor")</code>.
     * </p>
     * 
     * @return The JVM specification vendor.
     * @throws SecurityException if the
     *         {@link SecurityManager#checkPropertiesAccess(String)} doesn't
     *         allow access.
     */
    String getSpecVendor();

    /**
     * <p>
     * The JVM specification version; equivalent to
     * <code>System.getProperty("java.vm.specification.version")</code>.
     * </p>
     * 
     * @return The JVM specification version.
     * @throws SecurityException if the
     *         {@link SecurityManager#checkPropertiesAccess(String)} doesn't
     *         allow access.
     */
    String getSpecVersion();

    /**
     * <p>
     * The approximate start time (in milliseconds) of the JVM. The time is
     * specified by {@link System#currentTimeMillis()}.
     * </p>
     * 
     * @return The start time of the JVM.
     */
    long getStartTime();

    /**
     * <p>
     * The current JVM system properties, as defined by
     * {@link System#getProperties()}. Any properties whose name or value is
     * not of type <code>String</code> are excluded.
     * </p>
     * 
     * @return The library path.
     * @throws SecurityException if the
     *         {@link SecurityManager#checkPropertiesAccess(String)} doesn't
     *         allow access.
     */
    Map<String, String> getSystemProperties();

    /**
     * <p>
     * The approximate time elapsed (in milliseconds) since the JVM started.
     * </p>
     * 
     * @return The JVM's up time.
     */
    long getUpTime();

    /**
     * <p>
     * The JVM name; equivalent to
     * <code>System.getProperty("java.vm.name")</code>.
     * </p>
     * 
     * @return The JVM name.
     * @throws SecurityException if the
     *         {@link SecurityManager#checkPropertiesAccess(String)} doesn't
     *         allow access.
     */
    String getVmName();

    /**
     * <p>
     * The JVM vendor; equivalent to
     * <code>System.getProperty("java.vm.vendor")</code>.
     * </p>
     * 
     * @return The JVM vendor.
     * @throws SecurityException if the
     *         {@link SecurityManager#checkPropertiesAccess(String)} doesn't
     *         allow access.
     */
    String getVmVendor();

    /**
     * <p>
     * The JVM version; equivalent to
     * <code>System.getProperty("java.vm.version")</code>.
     * </p>
     * 
     * @return The JVM version.
     * @throws SecurityException if the
     *         {@link SecurityManager#checkPropertiesAccess(String)} doesn't
     *         allow access.
     */
    String getVmVersion();

    /**
     * <p>
     * Indicates whether or not the boot class path is supported by this JVM.
     * </p>
     * 
     * @return <code>true</code> if supported, <code>false</code> otherwise.
     */
    boolean isBootClassPathSupported();
}
