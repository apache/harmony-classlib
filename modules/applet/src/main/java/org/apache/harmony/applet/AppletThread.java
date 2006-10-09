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
 * @author Pavel Dolgov
 * @version $Revision: 1.2 $
 */
package org.apache.harmony.applet;

import java.util.List;
import java.util.Collections;
import java.util.LinkedList;


/**
 * Thread that initializes applet and performs commands from the host application
 */
final class AppletThread extends Thread {
    
    private final Proxy proxy;

    private final Object monitor = new Object();
    private final List commandQueue = Collections.synchronizedList(new LinkedList());
    
    private boolean doExit = false;
    
    AppletThread(Proxy proxy) {
        super(proxy.docSlice.codeBase.threadGroup, "Applet-" + proxy.params.className);
        
        this.proxy = proxy;
        setContextClassLoader(proxy.docSlice.codeBase.classLoader);
    }
    
    public void run() {

        while (true) {
            
            while( !commandQueue.isEmpty()) {
                Command command = (Command)commandQueue.remove(0);
                
                command.run();
            }
            
            synchronized(monitor) {
                if (doExit) {
                    return;
                }
                try {
                    monitor.wait();
                    if (doExit) {
                        return;
                    }
                } catch (InterruptedException e) {
                    // TODO: handle difference between intensional and unexpected interruptions
                }
            }
        }
    }

    void postCommand(Command command) {
        commandQueue.add(command);

        if (Thread.currentThread() != this) {
            synchronized(monitor) {
                monitor.notifyAll();
            }
        }
    }
    
    void exit() {
        if (Thread.currentThread() != this) {
            throw new InternalError("Attempt to stop applet main thread outside of that thread");
        }
        synchronized(monitor) {
            doExit = true;
            monitor.notifyAll();
        }
    }
    
}

abstract class Command {
    final String name;
    
    Command(String name) {
        this.name = name;
    }
    
    abstract void run();
}
