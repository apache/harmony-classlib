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

import java.io.Serializable;

public abstract class IntegerSyntax implements Cloneable, Serializable {


    private int value;


    protected IntegerSyntax(int intValue) {
        value = intValue;
    }

    protected IntegerSyntax(int intValue, int lowerBound, int upperBound) {

        if ((intValue < lowerBound) || (intValue > upperBound)) {
            throw new IllegalArgumentException("Value " + value +
                    " not in valid range (" + lowerBound + "," +
                        upperBound + ")" );
        }
        value = intValue;
    }


    public boolean equals(Object object) {

        if ((object instanceof IntegerSyntax) &&
                value == ((IntegerSyntax) object).value) {
            return true;
        } else {
            return false;
        }
    }

    public int getValue() {
        return value;
    }

    public int hashCode() {
        return value;
    }

    public String toString() {
        return Integer.toString(value);
    }

}
