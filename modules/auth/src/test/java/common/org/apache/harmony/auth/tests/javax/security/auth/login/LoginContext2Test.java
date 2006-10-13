/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.harmony.auth.tests.javax.security.auth.login;

import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
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
    private static final String VALID_NAME = "name1";
    private static final String INVALID_NAME = "name2";

    public void testLogin() throws Exception{
        MyPrincipal pri = new MyPrincipal();
        HashSet<Principal> set = new HashSet<Principal>();
        set.add(pri);
        Subject sub = new Subject(false, set, new HashSet(), new HashSet());
        Configuration.setConfiguration(new MyConfig());
        LoginContext context = new LoginContext("moduleName", sub);
        context.login();
        assertNotNull(context.getSubject());
        pri.name = INVALID_NAME;
        assertNotNull(context.getSubject());
        try{
            context.login();
            fail("Should throw LoginException");
        }catch(LoginException e){
        }
        assertNotNull(context.getSubject());
    }    
    static class MyConfig extends Configuration{
        AppConfigurationEntry[] entries = new AppConfigurationEntry[]{new AppConfigurationEntry(MyModule.class.getName(), LoginModuleControlFlag.REQUIRED, new HashMap())};
        public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
            return entries;
        }
        public void refresh() {
        }
    }
    public static class MyModule implements LoginModule{
        Subject sub;
        public void MyModule(){
        }
        public boolean abort() throws LoginException {
            return false;
        }
        public boolean commit() throws LoginException {
            return true;
        }
        public void initialize(Subject arg0, CallbackHandler arg1, Map<String, ?> arg2, Map<String, ?> arg3) {
            sub = arg0;
        }
        public boolean login() throws LoginException {
            Principal[] pris = sub.getPrincipals().toArray(new Principal[0]);
            return VALID_NAME.equals(pris[0].getName());
        }
        public boolean logout() throws LoginException {
            return false;
        }
    }
    public static class MyPrincipal implements Principal{
        public String name = VALID_NAME;
        public String getName() {
            return name;
        }
        public String toString(){
            return name; 
        }
    };
}
