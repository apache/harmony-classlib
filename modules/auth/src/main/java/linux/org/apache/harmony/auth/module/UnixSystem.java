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
package org.apache.harmony.auth.module;

/** 
 * A helper class which queries an information about the current user.
 */
public class UnixSystem {
    
    // Shows whether the jassnix library was loaded already
    private static boolean loadLibDone;
  
    /** 
     * User's main group's ID 
     */
    protected long gid;

    /** 
     * User's main group's name 
     */
    protected String groupname;

    /** 
     * List of group IDs the user belongs to 
     */
    protected long[] groups;

    /** 
     * List of group names the user belongs to 
     */
    protected String[] groupsNames;

    /** 
     * User ID 
     */
    protected long uid;

    /** 
     * User name 
     */
    protected String username;

    /** 
     * Sole constructor. 
     * @throws UnsatisfiedLinkError if jaasnix library could not be loaded
     */
    public UnixSystem() {
        if (!loadLibDone) {
            System.loadLibrary("hyauth");
            loadLibDone = true;
        }
        load();
    }

    /** 
     * returns user's main group's id 
     */
    public long getGid() {
        return gid;
    }

    /** 
     * returns user's main group's name
     */
    public String getGroupName() {
        return groupname;
    }
    
    /** 
     * returns list of ids of groups the user belongs to 
     */
    public long[] getGroups() {
        if (groups == null) {
            return new long[0];
        }
        long[] tmp = new long[groups.length];
        System.arraycopy(groups, 0, tmp, 0, tmp.length);
        return tmp;
    }

    /** 
     * returns list of names of groups the user belongs to 
     */
    public String[] getGroupNames() {
        if (groupsNames == null) {
            return new String[0];
        }
        String[] tmp = new String[groupsNames.length];
        System.arraycopy(groupsNames, 0, tmp, 0, tmp.length);
        return tmp;
    }

    /** 
     * returns user's id 
     */
    public long getUid() {
        return uid;
    }

    /** 
     * returns user name 
     */
    public String getUsername() {
        return username;
    }

    /**
     * The function which actually does all the work with loading info
     */
    public native void load();

    /**
     * Returns string representation of this object
     */
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("UnixSystem: \n");
        buf.append("uid:gid=").append(uid).append(":").append(gid);
        buf.append("=");
        buf.append(username).append(":").append(groupname);
        buf.append("\n");

        buf.append("total groups: ");
        buf.append(groups == null ? 0 : groups.length);

        if (groups != null) {
            buf.append("\n");
            for (int i = 0; i < groups.length; i++) {
                buf.append(i).append(") ").append(groupsNames[i]).append("\n");
            }
        }
        return buf.toString();
    }
}
