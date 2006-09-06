/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
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
package java.awt.dnd;

/**
 * A wrapper for array of drag source listeners
 */
class DragSourceMulticaster implements DragSourceListener {

    final DragSourceListener[] listeners;
    
    DragSourceMulticaster(DragSourceListener[] listeners) {
        this.listeners = listeners;
    }

    public void dragExit(DragSourceEvent dsde) {
        for (int i=0; i<listeners.length; i++) {
            listeners[i].dragExit(dsde);
        }
    }

    public void dragDropEnd(DragSourceDropEvent dsde) {
        for (int i=0; i<listeners.length; i++) {
            listeners[i].dragDropEnd(dsde);
        }
    }

    public void dropActionChanged(DragSourceDragEvent dsde) {
        for (int i=0; i<listeners.length; i++) {
            listeners[i].dropActionChanged(dsde);
        }
    }

    public void dragOver(DragSourceDragEvent dsde) {
        for (int i=0; i<listeners.length; i++) {
            listeners[i].dragOver(dsde);
        }
    }

    public void dragEnter(DragSourceDragEvent dsde) {
        for (int i=0; i<listeners.length; i++) {
            listeners[i].dragEnter(dsde);
        }
    }

}
