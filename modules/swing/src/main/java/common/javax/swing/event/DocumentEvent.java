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
 * @author Anton Avtamonov
 * @version $Revision$
 */
package javax.swing.event;

import javax.swing.text.Document;
import javax.swing.text.Element;

public interface DocumentEvent {
    public static interface ElementChange {
        Element getElement();
        int getIndex();
        Element[] getChildrenRemoved();
        Element[] getChildrenAdded();
    }

    public static final class EventType {
        public static final EventType INSERT = new EventType("INSERT");
        public static final EventType REMOVE = new EventType("REMOVE");
        public static final EventType CHANGE = new EventType("CHANGE");

        private final String id;

        public String toString() {
            return id;
        }

        private EventType(final String id) {
            this.id = id;
        }
    }

    int getOffset();
    int getLength();
    Document getDocument();
    EventType getType();
    ElementChange getChange(Element elem);
}
