/* 
*  Copyright 2005 The Apache Software Foundation or its licensors, as applicable. 
* 
*  Licensed under the Apache License, Version 2.0 (the "License"); 
*  you may not use this file except in compliance with the License. 
*  You may obtain a copy of the License at 
* 
*    http://www.apache.org/licenses/LICENSE-2.0 
* 
*  Unless required by applicable law or agreed to in writing, software 
*  distributed under the License is distributed on an "AS IS" BASIS, 
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
*  See the License for the specific language governing permissions and 
*  limitations under the License. 
*/
package java.rmi.server;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.security.SecureRandom;

import org.apache.harmony.rmi.internal.utils.PropertiesReader;

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public final class ObjID implements Serializable {

    /**
     * This class defines unique Object Identifiers. It uses an UID and a
     * counter or a Random generated number to identify between objects within
     * the same UID.
     */
    private static final long serialVersionUID = -6386392263968365220L;

    public static final int REGISTRY_ID = 0;

    public static final int ACTIVATOR_ID = 1;

    public static final int DGC_ID = 2;

    private static long objNumCounter = 0;

    /*
     * REVIEW: It's assumed that we need the same UID for every object in the
     * same JVM. This satisfies the specification, though the same UID could be
     * needed somewhere else, so it may be neccesary to obtain the UID from
     * somewhere else instead of creating a static one in this class.
     */

    private static final UID objUIDConst = new UID();

    private static final SecureRandom random = new SecureRandom();

    private static final boolean IsSecureRandom;

    private long objNum;

    private UID objUID;

    /*
     * The random number generator is set to a default by the SecureRandom()
     * constructor. The seed is automaticaly set in the first call to
     * nextLong().
     */
    static {
        String randomIds = 
            PropertiesReader.readString("java.rmi.server.randomIDs");
        if (randomIds == null) {
            IsSecureRandom = false;
        } else {
            IsSecureRandom = randomIds.equalsIgnoreCase("true");
        }
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public ObjID() {

        if (IsSecureRandom) {
            this.objNum = random.nextLong();
        } else {
            synchronized (ObjID.class) {
                this.objNum = objNumCounter;
                objNumCounter++;
            }
            this.objUID = objUIDConst;
        }

    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public ObjID(int objNum) {
        this.objNum = objNum;
        this.objUID = new UID((short) 0);
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    @Override
	public int hashCode() {

        /*
         * REVIEW: This is an arbitrary election for the hash function, that
         * does not seem to do any harm and it satisfies the specification.
         */
        return (int) this.objNum ^ this.objUID.hashCode();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static ObjID read(ObjectInput in) throws IOException {
        ObjID newObj = new ObjID();

        newObj.objNum = in.readLong();
        newObj.objUID = UID.read(in);

        return newObj;
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    @Override
	public boolean equals(Object obj) {

        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof ObjID))
            return false;
        return (((ObjID) obj).objNum == this.objNum && ((ObjID) obj).objUID
                .equals(this.objUID));
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    @Override
	public String toString() {
        return "[ObjID:" + this.objNum + ", " + this.objUID.toString() + "]";
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public void write(ObjectOutput out) throws IOException {

        out.writeLong(this.objNum);
        this.objUID.write(out);
    }
}
