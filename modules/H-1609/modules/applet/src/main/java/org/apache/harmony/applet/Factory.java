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

import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Applet context factory
 */
final class Factory {
    
    private final Callback callback;
    private final Map codeBases = Collections.synchronizedMap(new HashMap());
    private final Map allProxies = Collections.synchronizedMap(new HashMap());
    private final Map documents = Collections.synchronizedMap(new HashMap());
    
    Factory(Callback callback) {
        this.callback = callback;
    }
    
    CodeBase getCodeBase(URL url) {
        synchronized(codeBases) {
            CodeBase cb = (CodeBase)codeBases.get(url);
            if (cb == null) {
                cb = new CodeBase(url, this);
                codeBases.put(url, cb);
            }
            return cb;
        }
    }
    
    void remove(CodeBase codeBase) {
        codeBases.remove(codeBase.codeBase);
    }

    void remove(Document doc) {
        documents.remove(new Integer(doc.id));
    }

    void dispose(int id) {
        Proxy p = (Proxy)allProxies.get(new Integer(id));
        if (p == null) {
            return;
        }
        p.docSlice.remove(p);
        p.dispose();
        
    }
    
    Document getDocument(URL docBase, int docId) {
        synchronized(documents) {
            Document doc;
            Integer objDocId = new Integer(docId);
            doc = (Document)documents.get(objDocId);
            if (doc == null) {
                doc = new Document(this, docBase, docId);
                documents.put(objDocId, doc);
            }
            return doc;
        }
    }
    
    void createAndRun(Parameters params) {
        
        CodeBase codeBase = getCodeBase(params.codeBase);
        Document doc = getDocument(params.documentBase, params.documentId);
        DocumentSlice ds = codeBase.getDocumentSlice(doc);
        doc.add(ds);
        
        Proxy p = new Proxy(ds, params);
        p.create();
    }
    
    void start(int id) {
        Proxy p = (Proxy)allProxies.get(new Integer(id));
        if (p != null) {
            p.start();
        }
    }
    
    void stop(int id) {
        Proxy p = (Proxy)allProxies.get(new Integer(id));
        if (p != null) {
            p.stop();
        }
    }
    
    void init(int id) {
        Proxy p = (Proxy)allProxies.get(new Integer(id));
        if (p != null) {
            p.init();
        }
    }
    
    void destroy(int id) {
        Proxy p = (Proxy)allProxies.get(new Integer(id));
        if (p != null) {
            p.destroy();
        }
    }
    
    void appletResize(Proxy p, int width, int height) {
        callback.appletResize(p.params.id, width, height);
    }
    
    void showStatus(DocumentSlice ds, String status) {
        callback.showStatus(ds.document.id, status);
    }
    
    void showDocument(DocumentSlice ds, URL url, String target) {
        callback.showDocument(ds.document.id, url, target);
    }

    void add(Proxy p) {
        allProxies.put(new Integer(p.params.id), p);
    }
    
    void remove(Proxy p) {
        allProxies.remove(new Integer(p.params.id));
    }
    
    void dump() {
        for (Iterator it = allProxies.values().iterator(); it.hasNext(); ) {
            Proxy p = (Proxy)it.next();
            System.err.println("app " + p.params.id + " " + 
                    " cb " + p.docSlice.codeBase.hashCode() + " " +
                    " doc " + p.params.documentId + " " +
                    p.params.codeBase + p.params.className + " " + 
                    (p.isActive() ? "active" : "stopped"));
        }
        for (Iterator it = codeBases.values().iterator(); it.hasNext(); ) {
            CodeBase cb = (CodeBase)it.next();
            System.err.println("cb " + cb.hashCode() + " " + cb.threadGroup);
        }
    }
}

