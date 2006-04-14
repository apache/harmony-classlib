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

package javax.net.ssl.serialization;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSessionBindingEvent;
import javax.net.ssl.SSLSocket;

import org.apache.harmony.security.test.SerializationTest;

/**
 * Test for SSLSessionBindingEvent seialization
 * 
 */

public class SSLSessionBindingEventTest extends SerializationTest {

    protected Object[] getData() {
        try {
            SSLContext cont = SSLContext.getInstance("TLS");
            cont.init(null, null, null);
            SSLSocket soc = (SSLSocket )cont.getSocketFactory().createSocket();
            return new Object[] { new SSLSessionBindingEvent(soc.getSession(), "someName")};
        } catch (Exception e) {
            fail("Can not create data: "+ e);
            return null;
        }
    }

    protected void assertDeserialized(Object oref, Object otest) {
        SSLSessionBindingEvent ref = (SSLSessionBindingEvent) oref;
        SSLSessionBindingEvent test = (SSLSessionBindingEvent) otest;
        assertEquals(ref.getName(), test.getName());
    }
}