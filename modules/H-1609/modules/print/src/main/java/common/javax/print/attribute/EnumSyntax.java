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
/** 
 * @author Elena V. Sayapina 
 * @version $Revision: 1.4 $ 
 */ 

package javax.print.attribute;

import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;

public abstract class EnumSyntax implements Cloneable, Serializable {


    private int value;

    protected EnumSyntax(int intValue) {
        value = intValue;
    }

    public Object clone() {
        return this;
    }

    protected  EnumSyntax[] getEnumValueTable() {
        return null;
    }

    protected  int getOffset() {
        return 0;
    }

    protected  String[] getStringTable() {
        return null;
    }

    public int getValue() {
        return value;
     }

    public int hashCode() {
        return value;
    }

    protected Object readResolve() throws ObjectStreamException {

        int offset = getOffset();
        int i = value - offset;
        EnumSyntax[] enumTable = getEnumValueTable();

        if (enumTable == null) {
            throw new InvalidObjectException("Null enumeration value table");
        }

        if ( (i < 0) || (i > enumTable.length-1) ) {
            throw new InvalidObjectException("Value = " + value +
                    " is not in valid range (" + offset + ","
                        +(offset + enumTable.length-1) + ")" );
        }

        EnumSyntax outcome = enumTable[i];

        if (outcome == null) {
            throw new InvalidObjectException("Null enumeration value");
        }

        return outcome;

    }

    public String toString() {

        int i = value - getOffset();
        String[] stringTable = getStringTable();
        if ( (stringTable == null) || (i < 0) || (i > stringTable.length-1) ) {
            //No string value corresponding to enumeration value
            return Integer.toString(value);
        } else {
            return stringTable[i];
        }
    }


}
