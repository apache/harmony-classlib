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

package javax.sql.rowset.serial;

import java.io.Serializable;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.Map;

import org.apache.harmony.luni.util.NotImplementedException;

public class SerialStruct implements Struct, Serializable, Cloneable {
    // required by serialized form
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -8322445504027483372L;

    // required by serialized form
    private String SQLTypeName;

    // required by serialized form
    private Object[] attribs;

    public SerialStruct(SQLData in, Map<String, Class<?>> map)
            throws SerialException, NotImplementedException {
        throw new NotImplementedException();
    }

    public SerialStruct(Struct in, Map<String, Class<?>> map)
            throws SerialException, NotImplementedException {
        throw new NotImplementedException();
    }

    public Object[] getAttributes() throws SerialException,
            NotImplementedException {
        throw new NotImplementedException();

    }

    public Object[] getAttributes(Map<String, Class<?>> map)
            throws SerialException, NotImplementedException {
        throw new NotImplementedException();
    }

    public String getSQLTypeName() throws SerialException, NotImplementedException {
        throw new NotImplementedException();
    }

}
