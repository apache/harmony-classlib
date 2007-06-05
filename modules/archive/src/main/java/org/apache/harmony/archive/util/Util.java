/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.archive.util;

public class Util {

    public static boolean ASCIIIgnoreCaseRegionMatches(String s1, int start1,
            String s2, int start2, int length) {

        if (s1 != null && s2 != null) {
            if (start1 < 0 || length > s1.length() - start1) {
                return false;
            }
            if (start2 < 0 || length > s2.length() - start2) {
                return false;
            }

            s1 = s1.substring(start1, start1 + length);
            s2 = s2.substring(start2, start2 + length);

            return toASCIILowerCase(s1).equals(toASCIILowerCase(s2));
        }
        throw new NullPointerException();
    }

    public static String toASCIILowerCase(String s) {
        int len = s.length();
        StringBuilder buffer = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if ('A' <= c && c <= 'Z') {
                buffer.append((char) (c + ('a' - 'A')));
            } else {
                buffer.append(c);
            }
        }
        return buffer.toString();
    }

    public static String toASCIIUpperCase(String s) {
        int len = s.length();
        StringBuilder buffer = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if ('a' <= c && c <= 'z') {
                buffer.append((char) (c - ('a' - 'A')));
            } else {
                buffer.append(c);
            }
        }
        return buffer.toString();
    }
    
    public static final boolean equalsIgnoreCase(String s1, String s2){
        if (s1 == s2) {
            return true;
        }
        
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }

        char c1, c2;
        
        for (int i=0; i< s1.length(); i++) {
            if ((c1 = s1.charAt(i)) != (c2 = s2.charAt(i))
                    && toASCIIUpperCase(c1) != toASCIIUpperCase(c2)) {
                return false;
            }
        }
        return true;
    }

    public static final char toASCIILowerCase(char c) {
        if ('A' <= c && c <= 'Z') {
            return (char) (c + ('a' - 'A'));
        }
        return c;
    }

    public static final char toASCIIUpperCase(char c) {
        if ('a' <= c && c <= 'z') {
            return (char) (c - ('a' - 'A'));
        }
        return c;
    }
}
