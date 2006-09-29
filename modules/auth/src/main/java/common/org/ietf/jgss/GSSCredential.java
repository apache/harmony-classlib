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
public interface GSSCredential extends Cloneable {
    /**
     * @com.intel.drl.spec_ref
     */
    public static final int ACCEPT_ONLY = 2;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int DEFAULT_LIFETIME = 0;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int INDEFINITE_LIFETIME = 2147483647;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int INITIATE_AND_ACCEPT = 0;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int INITIATE_ONLY = 1;

    /**
     * @com.intel.drl.spec_ref
     */
    public void add(GSSName name, int initLifetime, int acceptLifetime,
            Oid mech, int usage) throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public void dispose() throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean equals(Object another);

    /**
     * @com.intel.drl.spec_ref
     */
    public Oid[] getMechs() throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public GSSName getName() throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public GSSName getName(Oid mech) throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public int getRemainingAcceptLifetime(Oid mech) throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public int getRemainingInitLifetime(Oid mech) throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public int getRemainingLifetime() throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public int getUsage() throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public int getUsage(Oid mech) throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public int hashCode();
}