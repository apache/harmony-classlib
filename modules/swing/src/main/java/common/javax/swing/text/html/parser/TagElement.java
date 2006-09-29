/*
 *  Copyright 2005 - 2006 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/**
 * @author Evgeniya G. Maenkova
 * @version $Revision$
 */
package javax.swing.text.html.parser;

import javax.swing.text.html.HTML;

public class TagElement {
    private HTML.Tag tag;
    private Element element;
    private boolean isFictional;

    public TagElement(final Element element,
                      final boolean fictional) {
        this.element = element;
        isFictional = fictional;
        tag = HTML.getTag(element.name.toLowerCase());
        if (tag == null) {
            tag = new HTML.UnknownTag(element.name.toLowerCase());
        }
    }


    public TagElement(final Element element) {
        this(element, false);
    }


    public boolean fictional() {
        return isFictional;
    }


    public HTML.Tag getHTMLTag() {
        return tag;
    }


    public Element getElement() {
        return element;
    }


    public boolean isPreformatted() {
        return tag.isPreformatted();
    }


    public boolean breaksFlow() {
        return tag.breaksFlow();
    }
}

