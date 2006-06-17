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
package java.awt;

final class ShutdownThread extends Thread {

    public void run() {
        synchronized (this) {
            notifyAll();

            while (true) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }

                if (timeToDie) {
                    break;
                } else {
                    throw new RuntimeException("Attempt to shutdown AWT in wrong way.");
                }
            }

            timeToDie = false;
            notifyAll();

            return;
        }
    }

    void startAndInit() {
        synchronized (this) {
            setName("AWT-Shutdown");
            setDaemon(false);
            start();
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Attempt to shutdown AWT in wrong way.");
            }
        }
    }

    void shutdown() {
        synchronized (this) {
            timeToDie = true;
            notifyAll();
            try {
                wait();
            } catch (InterruptedException e) {
            }
            if (timeToDie) {
                throw new RuntimeException("Attempt to shutdown AWT in wrong way.");
            }
        }
    }

    private boolean timeToDie = false;

}
