/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
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
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */

package ar.org.fitc.test.integration;

import junit.framework.TestCase;
import ar.org.fitc.test.integration.crypto.BlockAndNeverContinue;
import ar.org.fitc.test.integration.crypto.agreement.StreamDHKeyAgreement;
import ar.org.fitc.test.integration.crypto.chat.ClientError;
import ar.org.fitc.test.integration.crypto.chat.EncryptedChatClientV2;
import ar.org.fitc.test.integration.crypto.chat.EncryptedChatServerV2;
import ar.org.fitc.test.integration.crypto.chat.ServerError;



public class IntegrationTestRunner extends TestCase implements Thread.UncaughtExceptionHandler{

    public static void main(String[] args) {
        junit.textui.TestRunner.run(IntegrationTestRunner.class);
    }

    public IntegrationTestRunner(String name) {
        super(name);
    }

    public void testChat() throws Throwable {
        threadThrow = null;
        Thread t = new Thread() {
            public void run() {
                try {
                    EncryptedChatServerV2.main(null);
                } catch (ServerError e) {
                    fail("Integration test failed at server with:" + e);
                } catch (Throwable t) {
                    fail("fail with: "+ t);
                }
            }
        };
        t.setUncaughtExceptionHandler(this);
        t.start();
        t.join(1000);

        try {
            EncryptedChatClientV2.main(null);
        } catch (ClientError e) {
            fail("Integration test failed at client with:" + e);
        } catch (Throwable e) {
            fail("fail with: "+ e);
        }

        t.join();
        if (threadThrow != null)
            throw threadThrow;
    }
    
    private Throwable threadThrow = null;
    public void uncaughtException(Thread arg0, Throwable arg1) {
        threadThrow = arg1;
    }

    public void testDHKeyAgreement() throws Exception {
        StreamDHKeyAgreement.main();
    }
    
    public void testBlockAndNeverContinue() throws Throwable {
        threadThrow = null;
        Thread t = new Thread() {
            public void run() {
                try {
                    BlockAndNeverContinue.main();
                } catch (Exception e) {
                    this.getUncaughtExceptionHandler().uncaughtException(this, e);
                }
                
            }
        };
        t.setUncaughtExceptionHandler(this);
        t.start();
        t.join(10000);
        if (threadThrow != null)
            throw threadThrow;
        if (t.isAlive()) { 
            t.interrupt();
        } else {
            fail("thread must be alive and blocked");
        }
    }
}
