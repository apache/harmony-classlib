package doctools;

import java.util.Map;
import com.sun.javadoc.Tag;
import com.sun.tools.doclets.Taglet;

public class UrlTaglet implements Taglet {
	public static final String NAME = "ar.org.fitc.url";

	public boolean inField() {
		return false;
	}

	public boolean inConstructor() {
		return false;
	}

	public boolean inMethod() {
		return false;
	}

	public boolean inOverview() {
		return false;
	}

	public boolean inPackage() {
		return false;
	}

	public boolean inType() {
		return false;
	}

	public boolean isInlineTag() {
		return true;
	}

	public String getName() {
		return NAME;
	}

	public String toString(Tag tag) {
		String url = tag.text().contains("://") ? tag.text() : "http://" + tag.text();
		String[] components = url.split("\\s+",2);
		if (components.length == 1)
			return String.format("&lt;<a href=%s>%s</a>&gt;",url,url);
		if (components[1].startsWith("\"") && components[1].endsWith("\""))
			return String.format("\"<a href=%s>%s</a>\"",components[0],components[1].substring(1,components[1].length()-1));
		else
			return String.format("<a href=%s>%s</a>",components[0],components[1]);			
	}

	public String toString(Tag[] tags) {
		return null;
	}
    
	@SuppressWarnings("unchecked")
    public static void register(Map tagletMap) {
        UrlTaglet tag = new UrlTaglet();
        Taglet t = (Taglet) tagletMap.get(tag.getName());
        if (t != null) {
            tagletMap.remove(tag.getName());
        }
        tagletMap.put(tag.getName(), tag);     
	}
    


}
