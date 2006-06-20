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
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.1.2.1 $
 */
package org.apache.harmony.beans;

import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.1.2.1 $
 */

public class java_lang_StringPersistenceDelegate extends PersistenceDelegate {

    protected Expression instantiate(Object oldInstance, Encoder out) {
        String value = (String) oldInstance;

        return new Expression(oldInstance, String.class, "new",
                              new Object[] {new String(value)});
    }

    protected void initialize(Class type, Object oldInstance,
                              Object newInstance, Encoder out) {}

    protected boolean mutatesTo(Object oldInstance, Object newInstance) {
        if (oldInstance instanceof String && newInstance instanceof String) {
            return newInstance.equals(oldInstance);
        }
        return super.mutatesTo(oldInstance, newInstance);
    }
}
