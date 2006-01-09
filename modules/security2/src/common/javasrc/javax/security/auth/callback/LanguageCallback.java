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
* @author Maxim V. Makarov
* @version $Revision$
*/

package javax.security.auth.callback;

import java.io.Serializable;
import java.util.Locale;

/**
 * @com.intel.drl.spec_ref
 *
 */
public class LanguageCallback implements Callback, Serializable {
    
    /**
     * @com.intel.drl.spec_ref
     */
    private final static long serialVersionUID = 2019050433478903213L; 

    /**
     * @com.intel.drl.spec_ref
     */
    private Locale locale;

    /**
     * @com.intel.drl.spec_ref
     */
    public LanguageCallback() {
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

}