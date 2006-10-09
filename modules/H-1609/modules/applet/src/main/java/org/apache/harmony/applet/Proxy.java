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

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.Window;
import java.net.URL;

import org.apache.harmony.awt.ComponentInternals;


/**
 * Applet's state and parameters, implementation <b>AppletStub</b> interface
 */
class Proxy implements AppletStub {

    final DocumentSlice docSlice;
    final Parameters params;
    
    private final AppletThread mainThread;
    private Applet applet;
    private Container parent;
    private boolean active;
    
    Proxy(DocumentSlice ds, Parameters params) {
        this.docSlice = ds;
        this.params = params;
        
        mainThread = new AppletThread(this);
        ds.add(this);
    }
    
    Applet getApplet() {
        return applet;
    }
    
    void create() {
        if (Thread.currentThread() == mainThread) {
            createImpl();
        } else {
            if (!mainThread.isAlive()) {
                mainThread.start();
            }
            mainThread.postCommand(new Command("create") {
                public void run() {
                    createImpl();
                }});
        }        
    }
    
    private void createImpl() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        if (toolkit == null) {
            throw new InternalError("Toolkit is null");
        }
        
        if ((params.container != null) && (params.container instanceof Container)) { 

            parent = (Container)params.container;
        } else {
            
            ComponentInternals ci = ComponentInternals.getComponentInternals();
            parent = ci.attachNativeWindow(params.parentWindowId);
        }

        applet = createApplet();
        applet.setStub(this);

        parent.add(applet);
        parent.validate();

        initImpl();
        startImpl();

        parent.setVisible(true);
    }
        
    void start() {
        if (Thread.currentThread() == mainThread) {
            startImpl();
        } else {
            mainThread.postCommand(new Command("start") {
                public void run() {
                    startImpl();
                }});
        }
    }
    
    private void startImpl() {
        if (applet != null) {
            applet.start();
            active = true;
            docSlice.showStatus("Applet started");
        }
    }
    
    void stop () {
        if (Thread.currentThread() == mainThread) {
            stopImpl();
        } else {
            mainThread.postCommand(new Command("stop") {
                public void run() {
                    stopImpl();
                }});
        }
    }
    
    private void stopImpl() {
        if (applet != null) {
            active = false;
            applet.stop();
            docSlice.showStatus("Applet stopped");
        }
    }
    
    void init() {
        if (Thread.currentThread() == mainThread) {
            initImpl();
        } else {
            mainThread.postCommand(new Command("init") {
                public void run() {
                    initImpl();
                }});
        }
    }
    
    private void initImpl() {
        if (applet != null) {
            applet.init();
            docSlice.showStatus("Applet initialized");
        }
    }
    
    void destroy() {
        if (Thread.currentThread() == mainThread) {
            destroyImpl();
        } else {
            mainThread.postCommand(new Command("destroy") {
                public void run() {
                    destroyImpl();
                }});
        }
    }
    
    private void destroyImpl() {
        if (applet != null) {
            applet.destroy();
            docSlice.showStatus("Applet destroyed");
        }
    }
    
    void dispose() {
        if (Thread.currentThread() == mainThread) {
            disposeImpl();
        } else {
            mainThread.postCommand(new Command("dispose") {
                public void run() {
                    disposeImpl();
                }});
        }
    }
    
    private void disposeImpl() {
        if (applet != null) {
            parent.setVisible(false);
            parent.remove(applet);
            if (parent instanceof Window) {
                ((Window)parent).dispose();
            }
            applet.stop();
            applet.destroy();
            applet = null;
            parent = null;
        }

        mainThread.exit();
    }
    
    private Applet createApplet() {
        Class appletClass;
        try {
            appletClass = docSlice.codeBase.classLoader.loadClass(params.className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            return (Applet)appletClass.newInstance();

        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    /* (non-Javadoc)
     * @see java.applet.AppletStub#getCodeBase()
     */
    public URL getCodeBase() {
        return docSlice.codeBase.codeBase;
    }

    /* (non-Javadoc)
     * @see java.applet.AppletStub#appletResize(int, int)
     */
    public void appletResize(int width, int height) {
        docSlice.codeBase.factory.appletResize(this, width, height);
    }

    /* (non-Javadoc)
     * @see java.applet.AppletStub#getAppletContext()
     */
    public AppletContext getAppletContext() {
        return docSlice;
    }

    /* (non-Javadoc)
     * @see java.applet.AppletStub#getDocumentBase()
     */
    public URL getDocumentBase() {
        return docSlice.document.docBase;
    }

    /* (non-Javadoc)
     * @see java.applet.AppletStub#getParameter(java.lang.String)
     */
    public String getParameter(String name) {
        return params.getParameter(name);
    }

    /* (non-Javadoc)
     * @see java.applet.AppletStub#isActive()
     */
    public boolean isActive() {
        return active;
    }

}
