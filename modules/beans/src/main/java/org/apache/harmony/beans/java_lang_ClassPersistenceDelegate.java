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

package org.apache.harmony.beans;

import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.lang.reflect.Field;

public class java_lang_ClassPersistenceDelegate extends PersistenceDelegate {

    @Override
    protected Expression instantiate(Object oldInstance, Encoder out) {
        Class value = (Class) oldInstance;
        Field fld = null;
        final String TYPE = "TYPE"; //$NON-NLS-1$
        Expression result;

        try {
            if (value.equals(Integer.TYPE)) {
                fld = Integer.class.getField(TYPE);
            } else if (value.equals(Short.TYPE)) {
                fld = Short.class.getField(TYPE);
            } else if (value.equals(Long.TYPE)) {
                fld = Long.class.getField(TYPE);
            } else if (value.equals(Float.TYPE)) {
                fld = Float.class.getField(TYPE);
            } else if (value.equals(Double.TYPE)) {
                fld = Double.class.getField(TYPE);
            } else if (value.equals(Byte.TYPE)) {
                fld = Byte.class.getField(TYPE);
            } else if (value.equals(Character.TYPE)) {
                fld = Character.class.getField(TYPE);
            } else if (value.equals(Boolean.TYPE)) {
                fld = Boolean.class.getField(TYPE);
            }
        } catch (NoSuchFieldException e) {
            // impossible situation for valid java.lang classes
            // implementation with version >= 1.1
        }
        if (fld != null) {
            // we have primitive type
            result = new Expression(oldInstance, fld, "get", //$NON-NLS-1$
                    new Object[] { null });
        } else {
            result = new Expression(oldInstance, Class.class, "forName", //$NON-NLS-1$
                    new Object[] { new String(value.getName()) });
        }
        return result;
    }

    @Override
    protected void initialize(Class type, Object oldInstance,
            Object newInstance, Encoder out) {
    }

    @Override
    protected boolean mutatesTo(Object oldInstance, Object newInstance) {
        if (oldInstance instanceof Class && newInstance instanceof Class) {
            Class c1 = (Class) oldInstance;
            Class c2 = (Class) newInstance;

            if (c1.getName().equals(c2.getName())) {
                return true;
            }
            return false;
        }
        return super.mutatesTo(oldInstance, newInstance);
    }
}
