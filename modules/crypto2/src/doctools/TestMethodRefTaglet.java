package doctools;

import com.sun.tools.doclets.Taglet;
import com.sun.javadoc.*;
import java.util.Map;

/**
 * A sample Taglet representing @todo. This tag can be used in any kind of
 * {@link com.sun.javadoc.Doc}.  It is not an inline tag. The text is displayed
 * in yellow to remind the developer to perform a task.  For
 * example, "@todo Fix this!" would be shown as:
 * <DL>
 * <DT>
 * <B>To Do:</B>
 * <DD><table cellpadding=2 cellspacing=0><tr><td bgcolor="yellow">Fix this!
 * </td></tr></table></DD>
 * </DL>
 *
 * @author Jamie Ho
 * @since 1.4
 */

public class TestMethodRefTaglet implements Taglet {

    private static final String NAME = "ar.org.fitc.testmethod_ref";
    private static final String HEADER = "Tested method:";
    private static final String BASE_URL = "http://java.sun.com/j2se/1.5.0/docs/api/";

    /**
     * Return the name of this custom tag.
     */
    public String getName() {
        return NAME;
    }

    /**
     * Will return false since <code>@ar.org.fitc.spec_ref</code>
     * can not be used in field documentation.
     * @return false since <code>@ar.org.fitc.spec_ref</code>
     * can not be used in field documentation.
     */
    public boolean inField() {
        return true;
    }

    /**
     * Will return false since <code>@ar.org.fitc.spec_ref</code>
     * can be used in constructor documentation.
     * @return false since <code>@ar.org.fitc.spec_ref</code>
     * can be used in constructor documentation and false
     * otherwise.
     */
    public boolean inConstructor() {
        return true;
    }

    /**
     * Will return false since <code>@ar.org.fitc.spec_ref</code>
     * can be used in method documentation.
     * @return false since <code>@ar.org.fitc.spec_ref</code>
     * can be used in method documentation and false
     * otherwise.
     */
    public boolean inMethod() {
        return true;
    }

    /**
     * Will return false since <code>@ar.org.fitc.spec_ref</code>
     * can be used in method documentation.
     * @return false since <code>@ar.org.fitc.spec_ref</code>
     * can be used in overview documentation and false
     * otherwise.
     */
    public boolean inOverview() {
        return false;
    }

    /**
     * Will return false since <code>@ar.org.fitc.spec_ref</code>
     * can be used in package documentation.
     * @return false since <code>@ar.org.fitc.spec_ref</code>
     * can be used in package documentation and false
     * otherwise.
     */
    public boolean inPackage() {
        return false;
    }

    /**
     * Will return false since <code>@ar.org.fitc.spec_ref</code>
     * can be used in type documentation (classes or interfaces).
     * @return false since <code>@ar.org.fitc.spec_ref</code>
     * can be used in type documentation and false
     * otherwise.
     */
    public boolean inType() {
        return true;
    }

    /**
     * Will return false since <code>@ar.org.fitc.spec_ref</code>
     * is not an inline tag.
     * @return false since <code>@ar.org.fitc.spec_ref</code>
     * is not an inline tag.
     */

    public boolean isInlineTag() {
        return true;
    }

    /**
     * Register this Taglet.
     * @param tagletMap  the map to register this tag to.
     */
    @SuppressWarnings("unchecked")
    public static void register(Map tagletMap) {
        TestMethodRefTaglet tag = new TestMethodRefTaglet();
        Taglet t = (Taglet) tagletMap.get(tag.getName());
        if (t != null) {
            tagletMap.remove(tag.getName());
        }
        tagletMap.put(tag.getName(), tag);
    }

    /**
     * Given the <code>Tag</code> representation of this custom
     * tag, return its string representation.
     * @param tag   the <code>Tag</code> representation of this custom tag.
     */
    public String toString(Tag tag) {
        String className = tag.text();
        className = className.substring(0,className.lastIndexOf('.'));
//        System.err.println(className);
        String url = BASE_URL;
        String methodName = tag.text();
        methodName = methodName.substring(methodName.lastIndexOf('.')+1);
//        System.err.println(methodName);

        url += className.substring(0).replace('.','/')+".html#"
        +methodName;

        String ret = String.format("<DT><B>%s</B><DD>"
                + "<table cellpadding=2 cellspacing=0><tr><td>"
                + "%s (<a href=\"%s\">see J2SE API</a>)"
                + "</td></tr></table></DD>\n"
                , HEADER, className.substring(0).replace('/','.')+"."+methodName, url);

        ret += String.format("<DT><B>%s</B><DD>"
                + "<table cellpadding=2 cellspacing=0><tr><td>%s</td></tr></table></DD>\n",
                "Test explanation:","");

        return ret;
    }

    /**
     * Given an array of <code>Tag</code>s representing this custom
     * tag, return its string representation.
     * @param tags  the array of <code>Tag</code>s representing of this custom tag.
     */
    public String toString(Tag[] tags) {
//        if (tags.length == 0) {
            return null;
//        }
//        return toString(tags[0]);
    }
}


