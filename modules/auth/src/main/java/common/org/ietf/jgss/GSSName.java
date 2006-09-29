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
* @author Alexander V. Esin
* @version $Revision$
*/
package org.ietf.jgss;

/**
 * @com.intel.drl.spec_ref
 */
public interface GSSName {
    /**
     * @com.intel.drl.spec_ref
     */
    public static final Oid NT_ANONYMOUS = new Oid(new int[] { 1, 3, 6, 1, 5,
            6, 3 });

    /**
     * @com.intel.drl.spec_ref
     */
    public static final Oid NT_EXPORT_NAME = new Oid(new int[] { 1, 3, 6, 1, 5,
            6, 4 });

    /**
     * @com.intel.drl.spec_ref
     */
    public static final Oid NT_HOSTBASED_SERVICE = new Oid(new int[] { 1, 3, 6,
            1, 5, 6, 2 });

    /**
     * @com.intel.drl.spec_ref
     */
    public static final Oid NT_MACHINE_UID_NAME = new Oid(new int[] { 1, 2,
            840, 113554, 1, 2, 1, 2 });

    /**
     * @com.intel.drl.spec_ref
     */
    public static final Oid NT_STRING_UID_NAME = new Oid(new int[] { 1, 2, 840,
            113554, 1, 2, 1, 3 });

    /**
     * @com.intel.drl.spec_ref
     */
    public static final Oid NT_USER_NAME = new Oid(new int[] { 1, 2, 840,
            113554, 1, 2, 1, 1 });

    /**
     * @com.intel.drl.spec_ref
     */
    public GSSName canonicalize(Oid mech) throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean equals(GSSName another) throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean equals(Object another);

    /**
     * @com.intel.drl.spec_ref
     */
    public byte[] export() throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public Oid getStringNameType() throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public int hashCode();

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isAnonymous();

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isMN();

    /**
     * @com.intel.drl.spec_ref
     */
    public String toString();
}