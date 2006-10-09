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
 * @author Pavel Dolgov
 * @version $Revision: 1.2 $
 */
package org.apache.harmony.applet;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Collection of applets running in one document and loaded from the same code base,
 * implementation of <b>AppletContext</b> interface
 */
final class DocumentSlice implements AppletContext {

    final CodeBase codeBase;
    final Document document;
    
    private final ArrayList proxies = new ArrayList();
    private final HashMap streams = new HashMap();
    
    
    DocumentSlice(Document doc, CodeBase codeBase) {
        this.document = doc;
        this.codeBase = codeBase;
    }
    
    void add(Proxy p) {
        synchronized (proxies) {
            proxies.add(p);
        }
        codeBase.factory.add(p);
    }
    
    void remove(Proxy p) {
        codeBase.factory.remove(p);

        boolean empty = false;
        synchronized (proxies) {
            proxies.remove(p);
            empty = (proxies.size() == 0);
        }
        if (empty) {
            codeBase.remove(this);
            document.remove(this);
        }
    }
    /*
     * @see java.applet.AppletContext#getApplet(java.lang.String)
     */
    public Applet getApplet(String name) {
        
        synchronized (proxies) {
            for (Iterator it = proxies.iterator(); it.hasNext();) {
                Proxy p = (Proxy)it.next();
                if (p.params.name.equals(name)) {
                    return p.getApplet();
                }
            }
            return null;
        }
    }

    /* (non-Javadoc)
     * @see java.applet.AppletContext#getApplets()
     */
    public Enumeration getApplets() {
        
        synchronized (proxies) {
            ArrayList applets = new ArrayList();
            for (Iterator it = proxies.iterator(); it.hasNext();) {
                Proxy p = (Proxy)it.next();
                Applet a = p.getApplet();
                if (a != null) {
                    applets.add(a);
                }
            }
            return Collections.enumeration(applets);
        }
    }

    /* (non-Javadoc)
     * @see java.applet.AppletContext#getAudioClip(java.net.URL)
     */
    public AudioClip getAudioClip(URL url) {
        return new AudioClipImpl(url);
    }

    /* (non-Javadoc)
     * @see java.applet.AppletContext#getImage(java.net.URL)
     */
    public Image getImage(URL url) {
        return Toolkit.getDefaultToolkit().getImage(url);
    }

    /* (non-Javadoc)
     * @see java.applet.AppletContext#getStream(java.lang.String)
     */
    public InputStream getStream(String key) {

        synchronized (streams) {
            return (InputStream)streams.get(key);
        }
    }

    /* (non-Javadoc)
     * @see java.applet.AppletContext#getStreamKeys()
     */
    public Iterator getStreamKeys() {

        synchronized (streams) {
            ArrayList keys = new ArrayList();
            for(Iterator i = streams.keySet().iterator(); i.hasNext(); ) {
                keys.add(i.next());
            }
            return keys.iterator();
        }
    }

    /* (non-Javadoc)
     * @see java.applet.AppletContext#setStream(java.lang.String, java.io.InputStream)
     */
    public void setStream(String key, InputStream stream) throws IOException {

        synchronized (streams) {
            streams.put(key, stream);
        }
    }

    /* (non-Javadoc)
     * @see java.applet.AppletContext#showDocument(java.net.URL, java.lang.String)
     */
    public void showDocument(URL url, String target) {
        codeBase.factory.showDocument(this, url, target);
    }

    /* (non-Javadoc)
     * @see java.applet.AppletContext#showDocument(java.net.URL)
     */
    public void showDocument(URL url) {
        this.showDocument(url, null);
    }

    /* (non-Javadoc)
     * @see java.applet.AppletContext#showStatus(java.lang.String)
     */
    public void showStatus(String status) {
        codeBase.factory.showStatus(this, status);
    }
}
