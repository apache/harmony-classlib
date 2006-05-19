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

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public final class UID implements Serializable {

    private static final long serialVersionUID = 1086053664494604050L;

    private int unique;

    private long time;

    private short count;

    static private long lastTime;

    static private short counter;

    static private int PID; // Unique identifier inside the host

    /*
     * REVIEW: Impossible to get a unique identifier inside a host from Java.
     * This solution seems to be the best until now.
     */
    static {
        PID = new Object().hashCode();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public UID() {
        unique = PID;
        synchronized (UID.class) {
            time = System.currentTimeMillis();
            if (lastTime == time) {
                count = ++counter;
            } else {
                count = 0;
                counter = 0;
            }
            lastTime = time;
        }
    }

    // Contructor for a well-known UID
    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public UID(short num) {
        unique = 0;
        time = 0;
        count = num;
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    @Override
	public int hashCode() {
        return (int) (unique + time + count);
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    @Override
	public boolean equals(Object obj) {
        if (obj != null && obj instanceof UID) {
            return (unique == ((UID) obj).unique 
                    && time == ((UID) obj).time 
                    && count == ((UID) obj).count);
        }
        return false;
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    @Override
	public String toString() {
        return "[UID:" + unique + ", " + time + ", " + count + "]";
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public void write(DataOutput out) throws IOException {
        out.writeInt(unique);
        out.writeLong(time);
        out.writeShort(count);
        return;
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static UID read(DataInput in) throws IOException {
        UID tmp = new UID();
        tmp.unique = in.readInt();
        tmp.time = in.readLong();
        tmp.count = in.readShort();
        return tmp;
    }
}
