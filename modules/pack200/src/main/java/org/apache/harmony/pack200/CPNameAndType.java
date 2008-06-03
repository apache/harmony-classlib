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

public class CPNameAndType implements Comparable {

    private final String name;
    private final String signature;

    public CPNameAndType(String name, String signature) {
        this.name = name;
        this.signature = signature;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof CPNameAndType)) {
            return false;
        }
        return ((CPNameAndType) obj).name.equals(name)
                && ((CPNameAndType) obj).signature.equals(signature);
    }

    public int hashCode() {
        return name.hashCode() + signature.hashCode();
    }

    public String toString() {
        return name + ":" + signature;
    }

    public int compareTo(Object obj) {
        if (obj instanceof CPNameAndType) {
            CPNameAndType nat = (CPNameAndType) obj;
            int compareName = name.compareTo(nat.name);
            if (compareName == 0) {
                return signature.compareTo(nat.signature);
            } else {
                return compareName;
            }
        }
        return 0;
    }

}