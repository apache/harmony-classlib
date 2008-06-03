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

public class MethodOrField implements Comparable {

    private final String className;
    private final CPNameAndType nameAndType;

    public MethodOrField(String className, CPNameAndType nameAndType) {
        this.className = className;
        this.nameAndType = nameAndType;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof MethodOrField)) {
            return false;
        }
        return ((MethodOrField) obj).className.equals(className)
                && ((MethodOrField) obj).nameAndType.equals(nameAndType);
    }

    public int hashCode() {
        return className.hashCode() + nameAndType.hashCode();
    }

    public String toString() {
        return className + ": " + nameAndType;
    }

    public int compareTo(Object obj) {
        if (obj instanceof MethodOrField) {
            MethodOrField mof = (MethodOrField) obj;
            int compareName = className.compareTo(mof.className);
            if (compareName == 0) {
                return nameAndType.compareTo(mof.nameAndType);
            } else {
                return compareName;
            }
        }
        return 0;
    }
}