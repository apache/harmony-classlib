package java.lang.management;

import java.security.BasicPermission;
import java.security.SecurityPermission;

/**
 * <p>
 * A {@link SecurityPermission} for use with the management system.
 * </p>
 * 
 * @since 1.5
 */
public class ManagementPermission extends BasicPermission {
    private static final long serialVersionUID = 1897496590799378737L;

    /**
     * <p>
     * Constructs and instance with the given name.
     * </p>
     * 
     * @param name The permission name, which must be <code>"monitor"</code>
     *        or <code>"control"</code>.
     * @throws IllegalArgumentException if <code>name</code> is invalid.
     */
    public ManagementPermission(String name) {
        super(name);
        if (!"control".equals(name) && !"monitor".equals(name)) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * <p>
     * Constructs and instance with the given name.
     * </p>
     * 
     * @param name The permission name, which must be <code>"monitor"</code>
     *        or <code>"control"</code>.
     * @param actions This is not used, so it must be <code>null</code> or
     *        empty.
     * @throws IllegalArgumentException if <code>name</code> is invalid.
     */
    public ManagementPermission(String name, String actions) {
        super(name, actions);
        if (!"control".equals(name) && !"monitor".equals(name)) {
            throw new IllegalArgumentException();
        }
        if (actions != null && actions.length() != 0) {
            throw new IllegalArgumentException();
        }
    }
}
