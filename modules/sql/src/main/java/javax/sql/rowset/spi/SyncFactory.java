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

package javax.sql.rowset.spi;

import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;

class SyncFactory {
    public static String ROWSET_SYNC_PROVIDER = "rowset.provider.classname"; //$NON-NLS-1$

    public static String ROWSET_SYNC_VENDOR = "rowset.provider.vendor"; //$NON-NLS-1$

    public static String ROWSET_SYNC_PROVIDER_VERSION = "rowset.provider.version"; //$NON-NLS-1$

    public static void registerProvider(String providerID) throws SyncFactoryException {
        throw new UnsupportedOperationException();
    }

    public static SyncFactory getSyncFactory() {
        throw new UnsupportedOperationException();
    }

    public static void unregisterProvider(String providerID) throws SyncFactoryException {
        throw new UnsupportedOperationException();
    }

    public static SyncProvider getInstance(String providerID) throws SyncFactoryException {
        throw new UnsupportedOperationException();
    }

    public static Enumeration<SyncProvider> getRegisteredProviders()
            throws SyncFactoryException {
        throw new UnsupportedOperationException();
    }

    public static void setLogger(Logger logger) {
        throw new UnsupportedOperationException();
    }

    public static void setLogger(Logger logger, Level level) {
        throw new UnsupportedOperationException();
    }

    public static Logger getLogger() throws SyncFactoryException {
        throw new UnsupportedOperationException();
    }

    public static void setJNDIContext(Context ctx) throws SyncFactoryException {
        throw new UnsupportedOperationException();
    }
}
