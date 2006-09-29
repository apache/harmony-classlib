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
 * @author Michael Danilov, Pavel Dolgov
 * @version $Revision$
 */
package org.apache.harmony.awt.wtk;

public final class ShutdownThread extends Thread {
    
    public static final class Watchdog {
    }

    public ShutdownThread() {
        setName("AWT-Shutdown");
        setDaemon(false);
    }
    
    private boolean shouldStop = false;

    public void run() {
        synchronized (this) {
            notifyAll(); // synchronize the startup

            while (true) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }

                if (shouldStop) {
                    notifyAll(); // synchronize the shutdown
                    return;
                }
            }
        }
    }

    public void start() {
        synchronized (this) {
            super.start();
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(
                        "Shutdown thread was interrupted while starting");
            }
        }
    }

    public void shutdown() {
        synchronized (this) {
            shouldStop = true;
            notifyAll();
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(
                        "Shutdown thread was interrupted while stopping");
            }
        }
    }
}
