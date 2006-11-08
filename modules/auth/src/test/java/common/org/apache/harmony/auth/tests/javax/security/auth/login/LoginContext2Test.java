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

package org.apache.harmony.auth.tests.javax.security.auth.login;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag;
import javax.security.auth.spi.LoginModule;

import junit.framework.TestCase;

public class LoginContext2Test extends TestCase {

    /**
     * @tests javax.security.auth.login.LoginContext.login()
     */
    public void test_login_resourcesLeakage() throws Exception {

        // This is a compatibility test.
        // The test verifies that LoginContext allows to invoke login() method
        // multiple times without invoking logout() before. In testing scenario
        // each login() invocation adds new credentials to the passed subject.
        Configuration.setConfiguration(new Configuration() {

            @Override
            public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
                return new AppConfigurationEntry[] { new AppConfigurationEntry(
                        MyModule.class.getName(),
                        LoginModuleControlFlag.REQUIRED,
                        new HashMap<String, Object>()) };
            }

            @Override
            public void refresh() {
            }
        });

        LoginContext context = new LoginContext("moduleName", new Subject());

        context.login();
        context.login();

        Subject subject = context.getSubject();

        assertEquals(2, subject.getPrivateCredentials().size());
        assertEquals(2, subject.getPublicCredentials().size());
    }

    public static class MyModule implements LoginModule {

        Subject sub;

        public boolean abort() throws LoginException {
            return false;
        }

        public boolean commit() throws LoginException {
            sub.getPrivateCredentials().add(new Object());
            return true;
        }

        public void initialize(Subject arg0, CallbackHandler arg1,
                Map<String, ?> arg2, Map<String, ?> arg3) {
            sub = arg0;
        }

        public boolean login() throws LoginException {
            sub.getPublicCredentials().add(new Object());
            return true;
        }

        public boolean logout() throws LoginException {
            return false;
        }
    }
}
