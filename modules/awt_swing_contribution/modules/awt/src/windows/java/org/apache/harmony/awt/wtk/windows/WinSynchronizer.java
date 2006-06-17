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
 * @author Michael Danilov
 * @version $Revision$
 */
package org.apache.harmony.awt.wtk.windows;

import org.apache.harmony.awt.wtk.NativeEventQueue;
import org.apache.harmony.awt.wtk.Synchronizer;
import org.apache.harmony.awt.wtk.WTK;

public final class WinSynchronizer extends Synchronizer implements NativeServer {

    private NativeEventQueue nativeEventQueue = null;
    private Callback callback = null;

    /**
     * Acquire the lock for this synchronizer. Nested lock is supported.
     * If the mutex is already locked by another thread, the current thread will be put
     * into wait queue until the lock becomes available.
     * All user threads are served in FIFO order. Dispatch thread has higher priority.
     * Supposed to be used in Toolkit.lockAWT() only.
     *
     * Dispatch thread as native server thread may serve user thread's callbacks in
     * this method.
     */
    public void lock() {
        if (!isDispatchThread()) {
            super.lock();
        } else {
            synchronized (this) {
                if (acquestCounter > 0) {
                    if (owner == dispatchThread) {
                        acquestCounter++;
                    } else {
                        waitQueue.addFirst(dispatchThread);
                        do {
                            if (callback != null) {
                                invokeCallback();
                            }
                            try {
                                wait();
                            } catch (InterruptedException e) {
                            }
                        } while (callback != null);
                        if (owner != dispatchThread) {
                            waitQueue.remove(dispatchThread);
                            throw new RuntimeException(
                                    "Waiting for resource access thread interrupted not from unlock method.");
                        }
                    }
                } else {
                    acquestCounter = 1;
                    owner = dispatchThread;
                }
            }
        }
    }

    /**
     * Sets reference to WTK.
     * Called on dispatch thread startup.
     * Registers itself as a native server.
     *
     * @param wtk - reference to WTK instance
     */
    public void setEnvironment(WTK wtk, Thread dispatchThread) {
        synchronized (this) {
            super.setEnvironment(wtk, dispatchThread);
            nativeEventQueue = wtk.getNativeEventQueue();
        }
    }

    /**
     * Causes callback to have its body method called in the native server thread.
     * The call blocks thread until this has happened.
     *
     * @param callback - the Callback whose body method should be executed synchronously
     *                   on the native server thread
     */
    public void invokeAndWait(Callback callback) {
        Thread curThread = Thread.currentThread();

        if (curThread != owner) {
            throw new RuntimeException("Not resource owner can't register callback.");
        }

        if (curThread != dispatchThread) {
            synchronized (this) {
                this.callback = callback;
                if (waitQueue.contains(dispatchThread)) {
                    dispatchThread.interrupt();
                } else {
                    nativeEventQueue.awake();
                }
                try {
                    wait();
                } catch (InterruptedException e) {
                    if (this.callback != null) {
                        throw new RuntimeException(
                                "Waiting for callback operation thread interrupted not by native server.");
                    }
                }
            }
        } else {
            callback.body();
        }
    }

    /**
     * Returns true if the calling thread is the current AWT EventQueue's dispatch thread.
     *
     * @return true if running on the current AWT EventQueue's dispatch thread
     */
    protected boolean isDispatchThread() {
        return (Thread.currentThread() == dispatchThread);
    }

    private void invokeCallback() {
        Thread preemptedThread = owner;
        int preemptedCounter = acquestCounter;

        acquestCounter = 1;
        owner = dispatchThread;
        callback.body();
        callback = null;
        owner = preemptedThread;
        acquestCounter = preemptedCounter;
        owner.interrupt();
    }

}
