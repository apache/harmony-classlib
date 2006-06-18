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
 * @author Michael Danilov, Pavel Dolgov
 * @version $Revision$
 */
package java.awt;

import org.apache.harmony.awt.wtk.*;

/**
 * The thread that runs the main event loop, 
 * which handles both AWT events and native events.
 * 
 */
final class EventDispatchThread extends Thread implements NativeEventListener {

    /**
     * The AWT's system event queue
     */
    private EventQueue eventQueue;
    final Dispatcher dispatcher;
    final Toolkit toolkit;
    private NativeEventQueue nativeQueue;

    private Runnable startupAction;
    private volatile boolean shutdownPending = false;

    /**
     * Initialise and run the main event loop
     */
    public void run() {
        synchronized (this) {
            try {
                startupAction.run();
                nativeQueue = toolkit.getNativeEventQueue();
                nativeQueue.setNativeEventListener(this);
            } finally {
                notifyAll();
            }
        }

        try {
            runModalLoop(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        toolkit.stopShutdownThread();
    }

    /**
     * Handle the native event
     * @param event - the native event
     * @return true if the default processing by OS is not needed
     */
    public boolean onEvent(NativeEvent event) {
        return dispatcher.onEvent(event);
    }

    /**
     * Dispatch the AWT events from the system event queue
     */
    public void onEventNestingEnd() {
        boolean reloaded;

        try {
            do {
                while(eventQueue.peekEvent() != null) {
                    if (shutdownPending) {
                        return;
                    }
                    eventQueue.dispatchEvent(eventQueue.getNextEvent());
                }

                reloaded = false;
                if (nativeQueue.isEmpty()) {
                    toolkit.onQueueEmpty();
                    if (shutdownPending) {
                        return;
                    }
                    if (!eventQueue.isEmpty()) {
                        reloaded = true;
                    }
                }
            } while (reloaded);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void onEventBegin() {
        toolkit.lockAWT();
        toolkit.validateShutdownThread();
    }

    public void onEventEnd() {
        toolkit.unlockAWT();
    }

    public void onAwake() {
        toolkit.validateShutdownThread();
    }

    public Synchronizer getSynchronizer() {
        return toolkit.getSynchronizer();
    }

    public WTK getWTK() {
        return toolkit.getWTK();
    }

    void startAndInit(Runnable startupAction) {
        synchronized (this) {
            this.startupAction = startupAction;
            setName("AWT-EventQueue");
            setDaemon(true);
            start();
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Attempt to shutdown AWT in wrong way.");
            }
        }
    }

    void runModalLoop(ModalContext context) {
        nativeQueue.onModalLoopBegin();
        while (!shutdownPending && (context == null || context.isModalLoopRunning()) && nativeQueue.waitEvent()) {
            nativeQueue.dispatchEventToListener();
        }
        nativeQueue.onModalLoopEnd();
    }

    void shutdown() {
        shutdownPending = true;
    }

    EventDispatchThread(Toolkit toolkit, EventQueue eventQueue, Dispatcher dispatcher ) {
        this.toolkit = toolkit;
        this.eventQueue = eventQueue;
        this.dispatcher = dispatcher;
    }

}
