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

package javax.security.sasl;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

class mySaslClientFactory implements SaslClientFactory {
    public mySaslClientFactory() {
        super();
    }

    public String[] getMechanismNames(Map<String, ?> prop) {
        return new String[] { "NAME-1", "NAME-2", "NAME-3", "NAME-4" };
    }

    public SaslClient createSaslClient(String[] mech, String id,
            String protocol, String srvName, Map<String, ?> prop, CallbackHandler hnd)
            throws SaslException {
        if (mech == null) {
            throw new SaslException();
        }
        if ("NAME-1".equals(mech[0])) {
            throw new SaslException("Incorrect mechanisms");
        }
        if (protocol == null) {
            throw new SaslException("Protocol is null");
        }
        TextOutputCallback[] cb = { new TextOutputCallback(
                TextOutputCallback.INFORMATION, "Information") };
        try {
            hnd.handle(cb);
        } catch (UnsupportedCallbackException e) {
            throw new SaslException("Incorrect callback handlere", e);
        } catch (IOException e) {
            throw new SaslException("Incorrect callback handlere", e);
        }
        return new mySaslClient();
    }

    public class mySaslClient implements SaslClient {
        public mySaslClient() {
            super();
        }

        public Object getNegotiatedProperty(String s) {
            return "";
        }

        public String getMechanismName() {
            return "Proba";
        }

        public boolean isComplete() {
            return false;
        }

        public boolean hasInitialResponse() {
            return false;
        }

        public void dispose() throws SaslException {
        }

        public byte[] evaluateChallenge(byte[] challenge) throws SaslException {
            return new byte[0];
        }

        public byte[] unwrap(byte[] incoming, int offset, int len)
                throws SaslException {
            throw new SaslException();
        }

        public byte[] wrap(byte[] outgoing, int offset, int len)
                throws SaslException {
            return new byte[0];
        }
    }
}
