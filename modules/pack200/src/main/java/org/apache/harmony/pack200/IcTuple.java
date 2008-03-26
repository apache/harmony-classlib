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

    public IcTuple(String C, int F, String C2, String N) {
        this.C = C;
        this.F = F;
        this.C2 = C2;
        this.N = N;
        if(null == N) {
            predictSimple = true;
        }
        if(null == C2) {
            predictOuter = true;
        }
        initializeClassStrings();
    }

    public IcTuple(String C, int F) {
        this(C, F, null, null);
    }

    public static final int NESTED_CLASS_FLAG = 0x00010000;
    protected String C; // this class
    protected int F; // flags
    protected String C2; // outer class
    protected String N; // name

    private boolean predictSimple = false;
    private boolean predictOuter = false;
    private String cachedOuterClassString = null;
    private String cachedSimpleClassName = null;
    private boolean initialized = false;
    private boolean anonymous = false;
    private boolean member = true;
    private boolean outerIsAnonymous = false;

    /**
     * Answer true if the receiver is predicted;
     * answer false if the receiver is specified
     * explicitly in the outer and name fields.
     * @return
     */
    public boolean predicted() {
        return predictOuter || predictSimple;
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
        return cachedOuterClassString;
    }

    /**
     * Answer the inner class name for the receiver.
     * @return String name of inner class
     */
    public String simpleClassName() {
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
        return member;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public boolean outerIsAnonymous() {
        String [] result = innerBreakAtDollar(cachedOuterClassString);
        if(result.length == 0) {
            throw new Error("Should have an outer before checking if it's anonymous");
        }

        for(int index=0; index < result.length; index++) {
            if(isAllDigits(result[index])) {
                return true;
            }
        }
        return false;
    }

    public boolean shouldAddToRelevantForClassName(String className) {
        // If the outerClassString of the tuple doesn't match the
        // class name of the class we're looking through, don't
        // consider it relevant.
        if(!outerClassString().equals(className)) {
            return false;
        }
        // If it's not anon and the outer is not anon, it's relevant
        if(!isAnonymous() && !outerIsAnonymous()) {
            return true;
        }

        // Otherwise it's not relevant.
        return false;
    }

    private void initializeClassStrings() {
        if(initialized) {
            return;
        }
        initialized = true;

        if(!predictSimple) {
            cachedSimpleClassName = N;
        }
        if(!predictOuter) {
            cachedOuterClassString = C2;
        }
        // Class names must be calculated from
        // this class name.
        String nameComponents[] = innerBreakAtDollar(C);
        if(nameComponents.length == 0) {
            // Unable to predict outer class
            // throw new Error("Unable to predict outer class name: " + C);
        }
        if(nameComponents.length == 1) {
            // Unable to predict simple class name
            // throw new Error("Unable to predict inner class name: " + C);
        }
        if(nameComponents.length < 2) {
            // If we get here, we hope cachedSimpleClassName
            // and cachedOuterClassString were caught by the
            // predictSimple / predictOuter code above.
            return;
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
        // TODO: these two blocks are the same as blocks
        // above. Can we eliminate some by reworking the logic?
        if(!predictSimple) {
            cachedSimpleClassName = N;
        }
        if(!predictOuter) {
            cachedOuterClassString = C2;
        }
        if(isAllDigits(cachedSimpleClassName)) {
            anonymous = true;
            member = false;
            if((F & 65536) == 65536) {
                // Predicted class - marking as member
                member = true;
            }
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

    public boolean nullSafeEquals(String stringOne, String stringTwo) {
        if(null==stringOne) {
            return null==stringTwo;
        }
        return stringOne.equals(stringTwo);
    }

    public boolean equals(Object object) {
        if(object.getClass() != this.getClass()) {
            return false;
        }
        IcTuple compareTuple = (IcTuple)object;

        if(!nullSafeEquals(this.C, compareTuple.C)) {
            return false;
        }

        if(!nullSafeEquals(this.C2, compareTuple.C2)) {
            return false;
        }

        if(!nullSafeEquals(this.N, compareTuple.N)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return 17 + C.hashCode() + C2.hashCode() + N.hashCode();
    }

    public String getC() {
        return C;
    }

    public int getF() {
        return F;
    }

    public String getC2() {
        return C2;
    }

    public String getN() {
        return N;
    }

    public String realOuterClassString() {
        int firstDollarPosition = cachedOuterClassString.indexOf('$');
        if(firstDollarPosition <= 0) {
            return cachedOuterClassString;
        }
        return cachedOuterClassString.substring(0, firstDollarPosition);
    }
}
