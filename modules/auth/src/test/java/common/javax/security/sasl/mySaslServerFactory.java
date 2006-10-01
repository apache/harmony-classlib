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

class mySaslServerFactory implements SaslServerFactory {
    public mySaslServerFactory() {
        super();
    }

    public String[] getMechanismNames(Map<String, ?> prop) {
        return new String[] { "MECH-1", "MECH-2", "MECH-3", "MECH-4" };
    }

    public SaslServer createSaslServer(String mech, String protocol, String srvName,
            Map<String, ?> prop, CallbackHandler hnd) throws SaslException {
        if (mech == null) {
            throw new SaslException();
        }
        if ("MECH-1".equals(mech)) {
            throw new SaslException("Incorrect mechanisms");
        }
        if (protocol == null) {
            throw new SaslException("Protocol is null");
        }
        TextOutputCallback[] cb = { new TextOutputCallback(TextOutputCallback.INFORMATION,
                "Information") };
        try {
            hnd.handle(cb);
        } catch (UnsupportedCallbackException e) {
            throw new SaslException("Incorrect callback handlere", e);
        } catch (IOException e) {
            throw new SaslException("Incorrect callback handlere", e);
        }
        return new mySaslServer();
    }

    public class mySaslServer implements SaslServer {
        public mySaslServer() {
            super();
        }

        public void dispose() throws SaslException {
        }

        public byte[] evaluateResponse(byte[] challenge) throws SaslException {
            return new byte[0];
        }

        public String getMechanismName() {
            return "Server Proba";
        }

        public Object getNegotiatedProperty(String s) {
            return "";
        }

        public String getAuthorizationID() {
            return "";
        }

        public boolean isComplete() {
            return false;
        }

        public byte[] unwrap(byte[] incoming, int offset, int len) throws SaslException {
            throw new SaslException();
        }

        public byte[] wrap(byte[] outgoing, int offset, int len) throws SaslException {
            return new byte[0];
        }
    }
}
