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
 * @author Boris Kuznetsov
 * @version $Revision$
 */
package org.apache.harmony.xnet.provider.jsse;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;

/**
 * 
 * SSLSessionContext implementation
 * @see javax.net.ssl.SSLSessionContext
 */
public class SSLSessionContextImpl implements SSLSessionContext {

    private int cacheSize = 0;

    private long timeout = 0;

    private final Hashtable sessions = new Hashtable();

    /**
     * 
     * @see javax.net.ssl.SSLSessionContext.getIds()
     */
    public Enumeration getIds() {
        return new Enumeration() {
            Enumeration keys = sessions.keys();
            public boolean hasMoreElements() {
                return keys.hasMoreElements();
            }
            public Object nextElement() {
                return ((IdKey)keys.nextElement()).id;
            }
        };
    }

    /**
     *
     * @see javax.net.ssl.SSLSessionContext.getSession(byte[] sessionId)
     */
    public SSLSession getSession(byte[] sessionId) {      
        return (SSLSession) sessions.get(new IdKey(sessionId));
    }

    /**
     * @see javax.net.ssl.SSLSessionContext.getSessionCacheSize()
     */
    public int getSessionCacheSize() {
        return cacheSize;
    }

    /**
     * @see javax.net.ssl.SSLSessionContext.getSessionTimeout()
     */
    public int getSessionTimeout() {
        return (int)(timeout/1000);
    }

    /**
     * @see javax.net.ssl.SSLSessionContext.setSessionCacheSize(int size)
     */
    public void setSessionCacheSize(int size) throws IllegalArgumentException {
        if (size < 0) {
            throw new IllegalArgumentException("size < 0");
        }
        cacheSize = size;
        if (size > 0 && sessions.size() < size) {
            // remove size-sessions.size() oldest sessions
            removeOldest(size - sessions.size());
        }

    }

    /**
     * @see javax.net.ssl.SSLSessionContext.setSessionTimeout(int seconds)
     */
    public void setSessionTimeout(int seconds) throws IllegalArgumentException {
        if (seconds < 0) {
            throw new IllegalArgumentException("seconds < 0");
        }
        timeout = seconds * 1000;

        // Check timeouts and remome expired sessions
        SSLSessionImpl ses;
        for (Enumeration en = sessions.keys(); en.hasMoreElements();) {
            ses = (SSLSessionImpl)(sessions.get(en.nextElement()));
            if (!ses.isValid()) {
                sessions.remove(ses.getId());
            }
        }
    }

    /**
     * Adds session to the session cach
     * @param ses
     */
    void putSession(SSLSessionImpl ses) {
        if (cacheSize > 0 && sessions.size() == cacheSize) {
            // remove 1 oldest session
            removeOldest(1);
        }
        ses.context = this;
        sessions.put(new IdKey(ses.getId()), ses);
    }

    // removes invalidated/oldest sessions from the session cache
    private void removeOldest(int num) {
        //TODO
        // ses.context = null;
    }
    
    private class IdKey {
        private byte[] id;
        
        private IdKey(byte[] id) {
            this.id = id;
        }
        
        public boolean equals(Object o) {
            if (!(o instanceof IdKey)) {
                return false;
            }
            return Arrays.equals(id, ((IdKey)o).id);
        }
        
        public int hashCode() {
            // TODO uncomment for 1.5
            // return Arrays.hashCode(id);
            int hash = 0;
            for (int i = 0; i < id.length; i++) {
                hash += id[i];
            }
            return hash;
        }
    }

}
