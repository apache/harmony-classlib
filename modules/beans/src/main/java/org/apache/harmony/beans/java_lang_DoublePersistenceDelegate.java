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

public class java_lang_DoublePersistenceDelegate extends PersistenceDelegate {
    
    protected Expression instantiate(Object oldInstance, Encoder out) {
        Double value = (Double) oldInstance;
        return new Expression(oldInstance, Double.class, "new", //$NON-NLS-1$
                new Object[] { new Double(value.doubleValue()) });
    }
    
    protected void initialize(
            Class type, Object oldInstance, Object newInstance, Encoder out) {
    }
}
