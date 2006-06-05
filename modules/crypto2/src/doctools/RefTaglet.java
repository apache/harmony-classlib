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

public class RefTaglet implements Taglet {
    
    private static final String NAME = "ar.org.fitc.ref";
    private static final String HEADER = "References:";
    private static UrlTaglet urlTaglet = new UrlTaglet();
    
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
        return false;
    }

    /**
     * Will return true since <code>@ar.org.fitc.spec_ref</code>
     * can be used in constructor documentation.
     * @return true since <code>@ar.org.fitc.spec_ref</code>
     * can be used in constructor documentation and false
     * otherwise.
     */
    public boolean inConstructor() {
        return true;
    }
    
    /**
     * Will return true since <code>@ar.org.fitc.spec_ref</code>
     * can be used in method documentation.
     * @return true since <code>@ar.org.fitc.spec_ref</code>
     * can be used in method documentation and false
     * otherwise.
     */
    public boolean inMethod() {
        return true;
    }
    
    /**
     * Will return true since <code>@ar.org.fitc.spec_ref</code>
     * can be used in method documentation.
     * @return true since <code>@ar.org.fitc.spec_ref</code>
     * can be used in overview documentation and false
     * otherwise.
     */
    public boolean inOverview() {
        return true;
    }

    /**
     * Will return true since <code>@ar.org.fitc.spec_ref</code>
     * can be used in package documentation.
     * @return true since <code>@ar.org.fitc.spec_ref</code>
     * can be used in package documentation and false
     * otherwise.
     */
    public boolean inPackage() {
        return true;
    }

    /**
     * Will return true since <code>@ar.org.fitc.spec_ref</code>
     * can be used in type documentation (classes or interfaces).
     * @return true since <code>@ar.org.fitc.spec_ref</code>
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
        return false;
    }
    
    /**
     * Register this Taglet.
     * @param tagletMap  the map to register this tag to.
     */
    @SuppressWarnings("unchecked")
    public static void register(Map tagletMap) {
       RefTaglet tag = new RefTaglet();
       Taglet t = (Taglet) tagletMap.get(tag.getName());
       if (t != null) {
           tagletMap.remove(tag.getName());
       }
       tagletMap.put(tag.getName(), tag);
    }

    private String format(Tag tag){
    	String ret = "";
    	for (Tag inlineTag:tag.inlineTags()){
    		if (inlineTag.name().equals("@"+urlTaglet.getName())){
    				ret += urlTaglet.toString(inlineTag);
    		} else
    			ret += inlineTag.text();
    	}
    	return String.format("<li>%s</li>\n",ret);
    }
    
    /**
     * Given the <code>Tag</code> representation of this custom
     * tag, return its string representation.
     * @param tag   the <code>Tag</code> representation of this custom tag.
     */
    public String toString(Tag tag) {
        String ret = "";
        if (tag.text()!= null && tag.text().length() > 0){
        	ret = String.format("<DT><B>%s</B><DD><ul>%s</ul>", HEADER,format(tag));
        }
        return ret;
    }
    
    
    /**
     * Given an array of <code>Tag</code>s representing this custom
     * tag, return its string representation.
     * @param tags  the array of <code>Tag</code>s representing of this custom tag.
     */
    public String toString(Tag[] tags) {
        String ret = null;
        for (Tag tag: tags)
        	if (tag.text()!= null && tag.text().length() > 0){
        		if (ret == null)
        			ret = String.format("<DT><B>%s</B><DD><ul>", HEADER);
        		ret += format(tag);
        	}
        return ret == null ? "" : ret + "</ul>";
    }
}


