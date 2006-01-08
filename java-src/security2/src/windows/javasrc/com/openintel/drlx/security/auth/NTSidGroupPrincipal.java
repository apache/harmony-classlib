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
 * @author Alexander V. Astapchuk
 * @version $Revision$
 */
package com.openintel.drlx.security.auth;

/** 
 * A principal which holds information about user's group basing on group's sid.
 */
public class NTSidGroupPrincipal extends NTSid {
    
    /**
     * A constructor which takes group SID as its only argumant. 
     * @param sid group SID
     */
    public NTSidGroupPrincipal(String name) {
        super(name);
    }

    /**
     * A constructor which takes an extended set of information - group SID, 
     * its name and its domain name 
     * @param sid group SID
     * @param objName group name
     * @param objDomain name of group's domain
     */
    public NTSidGroupPrincipal(String sid, String objName, String objDomain) {
        super(sid, objName, objDomain);
    }
}