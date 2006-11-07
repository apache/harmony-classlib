/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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

import java.io.Serializable;
import java.util.BitSet;

public final class Element implements DTDConstants, Serializable {
    public int index;

    public String name;

    public boolean oStart;

    public boolean oEnd;

    public BitSet inclusions;

    public BitSet exclusions;

    public int type = DTDConstants.ANY;

    public ContentModel content;

    public AttributeList atts;

    public Object data;

    Element(final int index,
            final String name,
            final boolean oStart,
            final boolean oEnd,
            final BitSet exclusions,
            final BitSet inclusions,
            final int type,
            final ContentModel content,
            final AttributeList atts,
            final Object data) {
        this.index = index;
        this.name = name;
        this.oStart = oStart;
        this.oEnd = oEnd;
        this.inclusions = inclusions;
        this.exclusions = exclusions;
        this.type = type;
        this.content = content;
        this.atts = atts;
        this.data = data;
    }

    Element() {
    }

    public static int name2type(final String name) {
        if (name.equals("ANY")) {
            return DTDConstants.ANY;
        } else if (name.equals("CDATA")) {
            return DTDConstants.CDATA;
        } else if (name.equals("EMPTY")) {
            return DTDConstants.EMPTY;
        } else if (name.equals("RCDATA")) {
            return DTDConstants.RCDATA;
        } else {
            return 0;
        }
    }


    public AttributeList getAttributeByValue(final String value) {
        AttributeList currentAtts = this.atts;
        if (value == null) {
            return null;
        }
        while (currentAtts != null) {
            if (currentAtts.containsValue(value)) {
                return currentAtts;
            }
            currentAtts = currentAtts.next;
        }
        return null;
    }

    public AttributeList getAttribute(final String name) {
        AttributeList currentAtts = this.atts;
        if (name == null) {
            return null;
        }
        while (currentAtts != null) {
            if (name.equals(currentAtts.getName())) {
                return currentAtts;
            }
            currentAtts = currentAtts.next;
        }
        return null;
    }

    public String toString() {
        return name;
    }

    public boolean isEmpty() {
        return type == DTDConstants.EMPTY;
    }


    public int getIndex() {
        return index;
    }


    public AttributeList getAttributes() {
        return atts;
    }

    public ContentModel getContent() {
        return content;
    }

    public int getType() {
        return type;
    }

    public boolean omitEnd() {
        return oEnd;
    }

    public boolean omitStart() {
        return oStart;
    }

    public String getName() {
        return name;
    }

    final void updateElement(final int index,
                             final String name,
                             final boolean oStart,
                             final boolean oEnd,
                             final BitSet exclusions,
                             final BitSet inclusions,
                             final int type,
                             final ContentModel content,
                             final AttributeList atts,
                             final Object data) {
        this.index = index;
        this.name = name;
        this.oStart = oStart;
        this.oEnd = oEnd;
        this.inclusions = inclusions;
        this.exclusions = exclusions;
        this.type = type;
        this.content = content;
        this.atts = atts;
        this.data = data;
    }

    final void updateElement(final Element element) {
        this.index = element.index;
        this.name = element.name;
        this.oStart = element.oStart;
        this.oEnd = element.oEnd;
        this.inclusions = element.inclusions;
        this.exclusions = element.exclusions;
        this.type = element.type;
        this.content = element.content;
        this.atts = element.atts;
        this.data = element.data;
    }
}

