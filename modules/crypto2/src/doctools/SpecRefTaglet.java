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

public class SpecRefTaglet implements Taglet {
    
    private static final String NAME = "ar.org.fitc.spec_ref";
    private static final String HEADER = "Spec reference:";
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
       SpecRefTaglet tag = new SpecRefTaglet();
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
        Doc holder = tag.holder(); //Return the containing Doc of this Tag element.
        String holderString = holder.toString(); //javax.crypto.spec.PSource.PSpecified
        String holderName = holder.name(); //PSource.PSpecified
        String url = BASE_URL;

        if (holder.isField()) {
            url += holderString.substring(0,holderString.lastIndexOf(".")).replace(".", "/") 
            + ".html#" + holderString.substring(holderString.lastIndexOf(".") + 1);
            //=> entonces queda: ...javax/crypto/Cipher.html#DECRYPT_MODE
        } else if (holder.isClass()) {
            //BUG FIXED: EL LINK DEL API REFERENCE A NIVEL DE LA CLASE (PARA CLASES ANIDADAS)
            if (holder.name().contains(".")) { //si es clase anidada...
                url += holderString.substring(0, holderString.lastIndexOf(holderName)).
                        replace('.', '/') + holderName + ".html";
                //=> entonces queda: ...javax/crypto/spec/PSource.PSpecified
            } else {
                url += holderString.replace('.','/') + ".html";    
            }
        } else if (holder.isMethod() || holder.isConstructor()){
            //BUG FIXED: LINK DEL API REFERENCE EN UN CONTRUCTOR DADO (PARA TODAS LAS CLASES)
            //hs: javax.crypto.spec.PSource(java.lang.String)   
            //hn: PSource
            int i = holderString.indexOf(holderName+"(");
            url += holderString.substring(0,i-1).replace('.','/') 
            + (holder.isConstructor() ? "/" + holderName : "") +".html#" + holderString.substring(i);
        } 
        
        String ret = String.format("<DT><B>%s</B><DD>"
                + "<table cellpadding=2 cellspacing=0><tr><td>"
                + "See corresponding <a href=\"%s\">J2SE API specification reference</a>"
                + "</td></tr></table></DD>\n"
                , HEADER, url);
        
        if (tag.text()!= null && tag.text().length() > 0)
            ret += String.format("<DT><B>%s</B><DD>"
               + "<table cellpadding=2 cellspacing=0><tr><td>%s</td></tr></table></DD>\n",
               "Clarification:", tag.text());       
        return ret;
    }
    
    /**
     * Given an array of <code>Tag</code>s representing this custom
     * tag, return its string representation.
     * @param tags  the array of <code>Tag</code>s representing of this custom tag.
     */
    public String toString(Tag[] tags) {
        if (tags.length == 0) {
            return null;
        }
        return toString(tags[0]);
    }
}


