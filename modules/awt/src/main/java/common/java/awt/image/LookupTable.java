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
 * @author Oleg V. Khaschansky
 * @version $Revision$
 *
 * @date: Oct 14, 2005
 */

package java.awt.image;

public abstract class LookupTable {
    private int offset;
    private int numComponents;

    protected LookupTable(int offset, int numComponents) {
        if (offset < 0) {
            throw new IllegalArgumentException(
                    "Offset should be not less than zero"
            );
        }
        if (numComponents < 1) {
            throw new IllegalArgumentException(
                    "Number of components should be positive"
            );
        }

        this.offset = offset;
        this.numComponents = numComponents;
    }

    public int getOffset() {
        return offset;
    }

    public int getNumComponents() {
        return numComponents;
    }

    public abstract int[] lookupPixel(int[] src, int[] dst);
}
