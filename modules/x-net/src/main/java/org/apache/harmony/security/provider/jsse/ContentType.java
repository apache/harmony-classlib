/*
 *  Copyright 2006 The Apache Software Foundation or its licensors, as applicable.
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
 * @author Alexander Y. Kleymenov
 * @version $Revision$
 */

package org.apache.harmony.security.provider.jsse;

/**
 * This class incapsulates the constants determining the
 * types of SSL/TLS record's content data.
 * Constant values are taken according to the TLS v1 specification
 * (http://www.ietf.org/rfc/rfc2246.txt).
 */
public class ContentType {

    /**
     * Identifies change cipher spec message
     */
    protected static final byte CHANGE_CIPHER_SPEC = 20;

    /**
     * Identifies alert message
     */
    protected static final byte ALERT = 21;

    /**
     * Identifies handshake message
     */
    protected static final byte HANDSHAKE = 22;

    /**
     * Identifies application data message
     */
    protected static final byte APPLICATION_DATA = 23;

}

