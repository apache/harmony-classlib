/* Copyright 2005 The Apache Software Foundation or its licensors, as applicable
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


package java.util.prefs;

/*
 * Default implementation of <code>PreferencesFactory</code> for Linux 
 * platform, using file system as back end.
 * 
 * @since 1.4
 */
class FilePreferencesFactoryImpl implements PreferencesFactory {
    
    /*
     * Default constructor
     */
    public FilePreferencesFactoryImpl() {
    	super();
    }

    /* (non-Javadoc)
     * @see java.util.prefs.PreferencesFactory#userRoot()
     */
    public Preferences userRoot() {
        return FilePreferencesImpl.userRoot;
    }

    /* (non-Javadoc)
     * @see java.util.prefs.PreferencesFactory#systemRoot()
     */
    public Preferences systemRoot() {
        return FilePreferencesImpl.systemRoot;    
    }

}



 
