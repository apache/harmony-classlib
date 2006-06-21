/**
 * 
 */
package java.lang;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;

/**
 * Stub implementation
 */
// GCH -- below lines do not compile inside Eclipse (OK with RI javac & jsr14
// target)
//@Target( { ElementType.TYPE, ElementType.FIELD, ElementType.METHOD,
//        ElementType.PARAMETER, ElementType.CONSTRUCTOR,
//        ElementType.LOCAL_VARIABLE })
//@Retention(RetentionPolicy.SOURCE)
public @interface SuppressWarnings {
    public String[] value();
}
