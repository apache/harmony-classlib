/*
 *  Copyright 2005 - 2006 The Apache Software Software Foundation or its licensors, as applicable.
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
 * @author Pavel Dolgov
 * @version $Revision$
 */
package java.awt;

import java.util.EventListener;

import org.apache.harmony.awt.ListenerList;


final class AWTListenerList extends ListenerList {
    private static final long serialVersionUID = -2622077171532840953L;

    private final Component owner;

    AWTListenerList(Component owner) {
        this.owner = owner;
    }

    public void addUserListener(EventListener listener) {
        super.addUserListener(listener);

        if (owner != null) {
            owner.deprecatedEventHandler = false;
        }
    }

}
