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
import java.util.Vector;


public abstract class SetOfIntegerSyntax implements Cloneable, Serializable {


    private int[][] canonicalArray;


    protected  SetOfIntegerSyntax(int value) {

        if (value < 0) {
            throw new IllegalArgumentException ("Value " + value +
                    " is less than 0");
        }
        canonicalArray = new int[][] {{value, value} };

    }

    protected  SetOfIntegerSyntax(int lowerBound, int upperBound) {

        if (lowerBound <= upperBound) {
            if (lowerBound < 0) {
                throw new IllegalArgumentException ("Lower bound is less than 0");
            } else {
                canonicalArray = new int[][] {{lowerBound, upperBound} };
            }
        } else {
            canonicalArray = new int[0][];
        }

    }

    protected  SetOfIntegerSyntax(int[][] values) {
        if (values == null) {
            canonicalArray = new int[0][];
        } else {
            canonicalArray = rearrange(values);
        }
    }

    protected  SetOfIntegerSyntax(String values) {
        if (values == null) {
            canonicalArray = new int[0][];
        } else {
            canonicalArray = parse(values);
        }
    }

    /*
     * Parses given string containing set of integer and
     * returns this set in cannonical array form.
     * One after another integer intervals are extracted from the string
     * according comma-separated integer groups and than these intervals
     * are organized in cannonical array form.
     */
    private static int[][] parse(String values) {

        int flag;
        long num1long; /*first number in a range*/
        long num2long; /*second number in a range*/
        char symbol;

        /*vector for storing integer intervals in cannonical form*/
        Vector vector = new Vector();

        /*obtain an array of intervals from the string*/
        String[] str = values.split(",");
        int n = str.length;

        /*take next interval*/
        for (int i = 0; i < n; i++) {
            flag = 0;
            num1long = 0;
            num2long = 0;

            /*take next literal in interval*/
            for (int j = 0; j < str[i].length(); j++) {
                symbol = str[i].charAt(j);
                switch(flag) {

                    /*before the first number*/
                    case 0:
                        if(Character.isWhitespace(symbol)) {
                            continue;
                        } else if(Character.isDigit(symbol) ) {
                            num1long = Character.digit(symbol,10);
                            num2long = num1long;
                            flag = 1;
                        } else {
                            throw new IllegalArgumentException();
                        }
                        break;

                    /*before the separator*/
                    case 1:
                        if(Character.isWhitespace(symbol)) {
                            continue;
                        } else if(Character.isDigit(symbol)) {
                            num1long = num1long*10 + Character.digit(symbol,10);
                            if (num1long > Integer.MAX_VALUE) {
                                throw new IllegalArgumentException(num1long +
                                            " is out of int range");
                            }
                            num2long = num1long;
                        } else if( (symbol == ':') || (symbol == '-')) {
                            flag = 2;
                        } else {
                            throw new IllegalArgumentException();
                        }
                        break;

                    /*before the second number*/
                    case 2:
                        if(Character.isWhitespace(symbol)) {
                            continue;
                        } else if(Character.isDigit(symbol)) {
                            num2long = Character.digit(symbol,10);
                            flag = 3;
                        } else {
                            throw new IllegalArgumentException();
                        }
                        break;

                    /*afrer the first digit of second number*/
                    case 3:
                        if(Character.isWhitespace(symbol)) {
                            continue;
                        } else if(Character.isDigit(symbol)) {
                            num2long = num2long*10 + Character.digit(symbol,10);
                            if (num2long > Integer.MAX_VALUE) {
                                throw new IllegalArgumentException(num2long +
                                            " is out of int range");
                            }
                        } else {
                            throw new IllegalArgumentException();
                        }
                        break;
                }
            }

            /*Add the int interval to the vector if the interval in the
              string form had legal syntax*/

            if (((flag == 1) || (flag == 3)) && (num1long <= num2long)) {
                addRange(vector, (int) num1long, (int) num2long);
            }
        }
        return (int[][]) vector.toArray(new int[vector.size()][]);
    }

    /*
     * Work this array out to cannonical array form.
     */
    private static int[][] rearrange(int[][] values) {

        Vector vector = new Vector();
        int num1;
        int num2;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null) {
                throw new NullPointerException("int[][] array has null element");
            }
            if (values[i].length == 1) {
                num1 = values[i][0];
                num2 = values[i][0];
            } else if (values[i].length == 2) {
                num1 = values[i][0];
                num2 = values[i][1];
            } else {
                throw new IllegalArgumentException("Only array of length-1 " +
                   "or length-2 arrays of ints are valid");
            }
            if (num1 < 0) {
                throw new IllegalArgumentException("Valid values are " +
                                                            "not less than 0");
            } else if (num1 <= num2) {
                addRange(vector, num1, num2);
            }
        }
        return (int[][]) vector.toArray(new int[vector.size()][]);
    }

    /*
     * Adds interval [lowerBound, upperBound] to the vector containing
     * intervals in cannoical form and modifies it so that cannonical form
     * is saved.
     */
    private static void addRange(Vector vector,
                                    int lowerBound, int upperBound) {

        int l1; /*lowerBound of the first interval*/
        int u1; /*upperBound of the first interval*/

        int l2; /*lowerBound of the next interval*/
        int u2; /*upperBound of the next interval*/

        /*add interval [lowerBound, upperBound] to the vector*/
        vector.add(new int[] {lowerBound, upperBound});

        /*enumerate all intervals in the vector*/
        for (int i = vector.size()-2; i >= 0; i--) {
            l1 = ((int[]) vector.elementAt(i))[0];
            u1 = ((int[]) vector.elementAt(i))[1];
            l2 = ((int[]) vector.elementAt(i+1))[0];
            u2 = ((int[]) vector.elementAt(i+1))[1];

            /*check if two consecutive intervals overlap*/
            if ( Math.max(l1, l2) - Math.min(u1, u2) <= 1) {

                /*make new interval from two consecutive intervals
                  contained all theirs elements*/
                vector.setElementAt(new int[] { Math.min(l1, l2),
                                                Math.max(u1, u2) }, i);
                vector.removeElementAt(i+1);

            /*if two consecutive intervals doesn't overlap but
              lowerBound of the first interval is bigger than
              upperBound of the next interval
              than interchange theirs position*/
            } else if (l1 > u2) {
                vector.setElementAt(new int[] {l2,u2}, i);
                vector.setElementAt(new int[] {l1,u1}, i+1);
            } else {
                break;
            }
        }
    }


    public boolean contains(int value) {

        for (int i = 0; i < canonicalArray.length; i++) {
            if ( (value >= canonicalArray[i][0]) &&
                    (value <= canonicalArray[i][1]) ) {
                        return true;
            }
        }
        return false;
    }

    public boolean contains(IntegerSyntax attribute) {
        return contains(attribute.getValue());
    }

    public boolean equals(Object object) {

        if (object instanceof SetOfIntegerSyntax) {

            int[][] compare = ((SetOfIntegerSyntax)object).canonicalArray;
            int n = compare.length;
            if (n == canonicalArray.length) {
                for (int i = 0; i < n; i++) {
                    if ( (canonicalArray[i][0] != compare[i][0]) ||
                            (canonicalArray[i][1] != compare[i][1]) ) {
                                return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public int[][] getMembers() {

        int n = canonicalArray.length;
        int[][] members = new int[n][];
        for (int i = 0; i < n; i++) {
            members[i] = new int[] {canonicalArray[i][0], canonicalArray[i][1]};
        }
        return members;
    }

    public int hashCode() {

        int hashCode = 0;
        for (int i = 0; i < canonicalArray.length; i++) {
            hashCode += canonicalArray[i][0] + canonicalArray[i][1];
        }
        return hashCode;
    }

    public int next(int value) {

        for (int i = 0; i < canonicalArray.length; i++) {
            if (value < canonicalArray[i][0]) {
                return canonicalArray[i][0];
            } else if (value < canonicalArray[i][1]) {
                return value+1;
            }
        }
        return -1;
    }

    public String toString() {

        StringBuffer stringSet = new StringBuffer("");
        for (int i = 0; i < canonicalArray.length; i++) {
            if (i > 0) {
                stringSet.append(",");
            }
            stringSet.append (canonicalArray[i][0]);
            if (canonicalArray[i][0] != canonicalArray[i][1]) {
                stringSet.append("-");
                stringSet.append(canonicalArray[i][1]);
            }
        }
        return stringSet.toString();
    }

}
