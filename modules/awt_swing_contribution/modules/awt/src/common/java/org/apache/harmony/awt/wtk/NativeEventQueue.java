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
 * @author Mikhail Danilov
 * @version $Revision$
 */
package org.apache.harmony.awt.wtk;

/**
 * Describes the cross-platform native event queue interface
 *
 * <p/> The implementation constructor should remember thread it was
 * created. All other methods would be called obly from this thread,
 * except awake().
 */
public interface NativeEventQueue {
    /**
     * Blocks current thread until native event queue is not empty
     * or awaken from other thread by awake().
     *
     * <p/>Should be called only on tread which
     * will process native events.
     *
     * @return if event loop should be stopped
     */
    boolean waitEvent();

    /**
     * Determines whether or not the native event queue is empty.
     * An queue is empty if it contains no messages waiting.
     *
     * @return true if the queue is empty; false otherwise
     */
    boolean isEmpty();

    /**
     * Dispatches latest native event to listener. Should be called on
     * the same thread as waitEvent.
     *
     * <p/>The return value should be ignored in most cases.
     * @return result of event dipatching
     */
    long dispatchEventToListener();

    /**
     * Sets the application provided native event listener.
     * Only one listener is allowed simultanously.
     *
     * @param e -  the listener
     */
    void setNativeEventListener(NativeEventListener e);

    /**
     * Awakes blocked waitEvent from other thread.
     */
    void awake();

    /**
     * Called at the beginning of each modal loop
     */
    void onModalLoopBegin();

    /**
     * Called at the end of each modal loop
     */
    void onModalLoopEnd();

    /**
     * Gets AWT system window ID.
     *
     * @return AWT system window ID
     */
    long getJavaWindow();

}
