/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
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

/**
* @author Vera Y. Petrashkova
* @version $Revision$
*/

package java.security.cert;

import java.util.Iterator;
import java.util.Set;

/**
 * @com.intel.drl.spec_ref
 * 
 */
public interface PolicyNode {

    /**
     * @com.intel.drl.spec_ref
     * 
     * FIXME: for 1.5 update Iterator <? extends PolicyNode> getChildren();
     */
    public Iterator getChildren();

    /**
     * @com.intel.drl.spec_ref
     */
    public int getDepth();

    /**
     * @com.intel.drl.spec_ref
     * 
     * FIXME: for 1.5 update Set <String>getExpectedPolicies();
     */
    public Set getExpectedPolicies();

    /**
     * @com.intel.drl.spec_ref
     */
    public PolicyNode getParent();

    /**
     * @com.intel.drl.spec_ref
     * 
     * FIXME: for 1.5 update Set <? extends PolicyQualifierInfo>
     * getPolicyQualifiers();
     */
    public Set getPolicyQualifiers();

    /**
     * @com.intel.drl.spec_ref
     */
    public String getValidPolicy();

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isCritical();
}