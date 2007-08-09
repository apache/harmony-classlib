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
 * @author Nikolay A. Kuznetsov
 * @version $Revision: 1.7.2.2 $
 */
package java.util.regex;

import java.util.Arrays;

import org.apache.harmony.regex.internal.nls.Messages;

/**
 * @com.intel.drl.spec_ref
 * 
 * @author Nikolay A. Kuznetsov
 * @version $Revision: 1.7.2.2 $
 */
public class PatternSyntaxException extends IllegalArgumentException {
    
    private static final long serialVersionUID = -3864639126226059218L;
    
    private String desc;
    
    private String pattern;
   
    private int index = -1;

    /**
     * @com.intel.drl.spec_ref
     */
    public PatternSyntaxException(String desc, String pattern, int index) {
        this.desc = desc;
        this.pattern = pattern;
        this.index = index;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getMessage() {
        String filler = ""; //$NON-NLS-1$
        if (index >= 1) {
            char[] temp = new char[index];
            Arrays.fill(temp, ' ');
            filler = new String(temp);
        }
        return desc
                + ((pattern != null && pattern.length() != 0) ? Messages.getString("regex.07", //$NON-NLS-1$
                        new Object[]{index, pattern, filler}) : ""); //$NON-NLS-1$
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getDescription() {
        return desc;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int getIndex() {
        return index;
    }
}
