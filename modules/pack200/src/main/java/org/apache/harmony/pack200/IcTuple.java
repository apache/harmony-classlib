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
package org.apache.harmony.pack200;

import java.util.ArrayList;

public class IcTuple {

    public static final int NESTED_CLASS_FLAG = 0x00010000;
    public String C; // this class
    public int F; // flags
    public String C2; // outer class
    public String N; // name

    private String cachedOuterClassString = null;
    private String cachedSimpleClassName = null;
    private boolean initialized = false;
    private boolean anonymous = false;
    private boolean member = true;

    /**
     * Answer true if the receiver is predicted;
     * answer false if the receiver is specified
     * explicitly in the outer and name fields.
     * @return
     */
    public boolean predicted() {
        return ((F & NESTED_CLASS_FLAG) == 0);
    }

    /**
     * Break the receiver into components at $ boundaries.
     *
     * @return
     */
    public String[] innerBreakAtDollar(String className) {
        ArrayList resultList = new ArrayList();
        int start = 0;
        int index = 0;
        while(index < className.length()) {
            if(className.charAt(index) <= '$') {
                resultList.add(className.substring(start, index));
                start = index + 1;
            }
            index++;
            if(index >= className.length()) {
                // Add the last element
                resultList.add(className.substring(start, className.length()));
            }
        }
        String[] result = new String[resultList.size()];
        for(int i=0; i < resultList.size(); i++) {
            result[i] = (String)resultList.get(i);
        }
        return result;
    }

    /**
     * Answer the outer class name for the receiver.
     * This may either be specified or inferred from
     * inner class name.
     * @return String name of outer class
     */
    public String outerClassString() {
        if(!initialized) {
            initializeClassStrings();
        }
        return cachedOuterClassString;
    }

    /**
     * Answer the inner class name for the receiver.
     * @return String name of inner class
     */
    public String simpleClassName() {
        if(!initialized) {
            initializeClassStrings();
        }
        return cachedSimpleClassName;
    }

    /**
     * Answer the full name of the inner class represented
     * by this tuple (including its outer component)
     * @return String full name of inner class
     */
    public String thisClassString() {
        if(predicted()) {
            return C;
        } else {
            // TODO: this may not be right. What if I
            // get a class like Foo#Bar$Baz$Bug?
            return C2 + "$" + N;
        }
    }

    public boolean isMember() {
        initializeClassStrings();
        return member;
    }

    public boolean isAnonymous() {
        initializeClassStrings();
        return anonymous;
    }

    private void initializeClassStrings() {
        if(initialized) {
            return;
        }
        initialized = true;
        if(!predicted()) {
            cachedOuterClassString = C2;
            cachedSimpleClassName = N;
            return;
        }
        // Class names must be calculated from
        // this class name.
        String nameComponents[] = innerBreakAtDollar(C);
        if(nameComponents.length == 0) {
            throw new Error("Unable to predict outer class name: " + C);
        }
        if(nameComponents.length == 1) {
            throw new Error("Unable to predict inner class name: " + C);
        }
        // If we get to this point, nameComponents.length must be >=2
        int lastPosition = nameComponents.length - 1;
        cachedSimpleClassName = nameComponents[lastPosition];
        cachedOuterClassString = "";
        for(int index=0; index < lastPosition; index++) {
            cachedOuterClassString += nameComponents[index];
            if(isAllDigits(nameComponents[index])) {
                member = false;
            }
            if(index + 1 != lastPosition) {
                // TODO: might need more logic to handle
                // classes with separators of non-$ characters
                // (ie Foo#Bar)
                cachedOuterClassString += '$';
            }
        }
        if(isAllDigits(cachedSimpleClassName)) {
            anonymous = true;
            member = false;
        }
    }

    private boolean isAllDigits(String nameString) {
        // Answer true if the receiver is all digits; otherwise answer false.
        if(null == nameString) {
            return false;
        }
        for(int index = 0; index < nameString.length(); index++) {
            if(!Character.isDigit(nameString.charAt(index))) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append(this.getClass().getName());
        result.append('(');
        result.append(simpleClassName());
        result.append(" in ");
        result.append(outerClassString());
        result.append(')');
        return result.toString();
    }
}
