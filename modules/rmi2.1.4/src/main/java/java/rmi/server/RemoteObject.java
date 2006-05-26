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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Proxy;
import java.rmi.NoSuchObjectException;
import java.rmi.Remote;

import org.apache.harmony.rmi.internal.runtime.RemoteReferenceManager;
import org.apache.harmony.rmi.internal.server.ReferenceTypes;
import org.apache.harmony.rmi.internal.server.UnicastRemoteRef2Impl;
import org.apache.harmony.rmi.internal.server.UnicastRemoteRefImpl;
import org.apache.harmony.rmi.internal.server.UnicastServerRef2Impl;
import org.apache.harmony.rmi.internal.server.UnicastServerRefImpl;

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public abstract class RemoteObject implements Remote, Serializable {

    private static final long serialVersionUID = -3215090123894869218L;

    protected transient RemoteRef ref;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    protected RemoteObject() {
        super();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    protected RemoteObject(RemoteRef newRef) {
    	if (newRef == null) {
    		throw new NullPointerException(); 
    	}
        ref = newRef;
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    @Override
	public int hashCode() {
        if (ref != null) {
            return ref.remoteHashCode();
        } else {
            return super.hashCode();
        }
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    @Override
	public boolean equals(Object obj) {
        if (obj != null) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof RemoteObject) {
                if (ref == null) {
                    return false;
                }
                return ref.remoteEquals(((RemoteObject) obj).getRef());
            } else {
                return obj.equals(this);
            }
        }
        return false;
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    @Override
	public String toString() {
        if (ref != null) {
            return "[" + this.getClass().getName() + " " + ref.remoteToString()
                    + "]";
        } else {
            return super.toString();
        }
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public RemoteRef getRef() {
        return ref;
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static Remote toStub(Remote obj) throws NoSuchObjectException {
        if (obj == null) {
            throw new NoSuchObjectException(
                    "Invalid (null) Remote server object.");
        }
        if (obj instanceof RemoteStub) {
            return obj;
        }
        if (Proxy.isProxyClass(obj.getClass())) {
            if (Proxy.getInvocationHandler(obj).getClass().equals(
                    RemoteObjectInvocationHandler.class)) {
                return obj;
            }
        }
        RemoteReferenceManager rrm = RemoteReferenceManager
                .getRemoteReferenceManager();
        return rrm.getStub(obj);
    }

    /**
     * 
     * 
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        if (ref != null) {
            String externalRefTypeName = ref.getRefClass(out);
            if (externalRefTypeName != null && externalRefTypeName.length() > 0) {
                out.writeUTF(externalRefTypeName);
                ref.writeExternal(out);
            } else {
                out.writeUTF("");
                out.writeObject(ref);
            }
        }
    }

    /**
     * 
     * 
     */
    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        in.defaultReadObject();
        String externalRefTypeName = in.readUTF();
        if (externalRefTypeName.length() == 0) {
            ref = (RemoteRef) in.readObject();
        } else {
            if (externalRefTypeName.equals(ReferenceTypes.UNICAST_REF
                    .toString())) {
                ref = new UnicastRemoteRefImpl();
            } else if (externalRefTypeName.equals(ReferenceTypes.UNICAST_REF2
                    .toString())) {
                ref = new UnicastRemoteRef2Impl();
            } else if (externalRefTypeName
                    .equals(ReferenceTypes.UNICAST_SERVER_REF.toString())) {
                ref = new UnicastServerRefImpl();
            } else if (externalRefTypeName
                    .equals(ReferenceTypes.UNICAST_SERVER_REF2.toString())) {
                ref = new UnicastServerRef2Impl();
            } else {
                throw new ClassNotFoundException(
                        "Invalid external ref type name");
            }
            ref.readExternal(in);
        }
    }
}
