/*
 *  Copyright 2006 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package java.util.regex;

/**
 * Represents case insensitive 
 * canonical decomposition of
 * Unicode character. Is used when
 * CANON_EQ flag of Pattern class
 * is specified.
 */
public class CIDecomposedCharSet extends DecomposedCharSet{
    
    /*
     * Just only a stub
     */
    public CIDecomposedCharSet(int [] decomp, int decomposedCharLength) {
        super(decomp, decomposedCharLength);        
    }
}
