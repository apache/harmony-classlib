/*
 * Copyright 2005-2006 The Apache Software Foundation or its licensors, as applicable
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author  Victor A. Martynov
 * @version $Revision: 1.15.2.3 $
 */
package java.rmi.activation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.rmi.MarshalledObject;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RMIClassLoader;
import java.rmi.server.RemoteObject;
import java.rmi.server.RemoteRef;
import java.rmi.server.UID;

import org.apache.harmony.rmi.common.RMILog;


/**
 * @com.intel.drl.spec_ref
 *
 * @author  Victor A. Martynov
 * @version $Revision: 1.15.2.3 $
 */
public class ActivationID implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4608673054848209235L;

    /**
     * A unique identifier for the object.
     */
    private transient UID uid = new UID();

    /**
     * A remote reference to the object's activator.
     */
    private transient Activator activator;

    private static RMILog rlog = RMILog.getActivationLog();

    /**
     * @com.intel.drl.spec_ref
     */
    public ActivationID(Activator activator) {
        this.activator = activator;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Remote activate(boolean force) throws ActivationException,
            UnknownObjectException, RemoteException {
        rlog.log(RMILog.VERBOSE, "ActivationID.activate: activator = "
                + activator);

        try {
            MarshalledObject stub = (MarshalledObject) activator.activate(this,
                    force);
            rlog.log(RMILog.VERBOSE, "ActivationID.activate:stub=" + stub);
            Remote deserialized_stub = (Remote) stub.get();
            rlog.log(RMILog.VERBOSE,
                    "ActivationID.activate: deserialized_stub = "
                            + deserialized_stub);

            rlog.log(RMILog.VERBOSE,
                    "<<<<<<<<< ActivationID.activate COMPLETED.");

            return deserialized_stub;
        } catch (IOException ioe) {
            throw new RemoteException(
                    "An IOException occured while deserializing the object" +
                    " from its internal representation.");
        } catch (ClassNotFoundException cnfe) {
            throw new RemoteException(
                    "A ClassNotFoundException occured while deserializing the " +
                    "object from its internal representation.");
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean equals(Object obj) {
        if (obj instanceof ActivationID) {
            ActivationID castedObj = (ActivationID) obj;
            boolean p0, p1;
            p0 = uid.equals(castedObj.uid);
            p1 = activator.equals(castedObj.activator);
            return p0 && p1;
        }
        return false;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int hashCode() {
        return activator.hashCode() ^ uid.hashCode();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        rlog.log(RMILog.VERBOSE, "ActivationID.readObject:");
        try {
            uid = (UID) in.readObject();
            rlog.log(RMILog.VERBOSE, "UID=" + uid);

            String refType = in.readUTF();
            rlog.log(RMILog.VERBOSE, "refType=" + refType);

            Class cl = Class.forName("org.apache.harmony.rmi.remoteref."
                    + refType);
            RemoteRef ref = (RemoteRef) cl.newInstance();
            rlog.log(RMILog.VERBOSE, "ref = " + ref);
            ref.readExternal(in);
            rlog.log(RMILog.VERBOSE, "readExternal finished successfully.");

            Class activator_class = RMIClassLoader.loadClass((String) null,
                    "org.apache.harmony.rmi.activation.Rmid_Stub");
            Class constructor_parameter_classes[] = { RemoteRef.class };
            Constructor constructor = activator_class
                    .getConstructor(constructor_parameter_classes);
            Object[] constructor_parameters = { ref };
            activator = (Activator) constructor
                    .newInstance(constructor_parameters);
            rlog.log(RMILog.VERBOSE,
                    "ActivationID.readObject COMPLETED.");
        } catch (Throwable t) {
            throw new IOException("Unable to deserialize ActivationID: " + t);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    private void writeObject(ObjectOutputStream out) throws IOException,
            ClassNotFoundException {
        rlog.log(RMILog.VERBOSE, "ActivationID.writeObject:");
        try {
            out.writeObject(uid);
            rlog.log(RMILog.VERBOSE, "activator = " + activator);

            RemoteRef ref = ((RemoteObject) activator).getRef();
            rlog.log(RMILog.VERBOSE, "ref = " + ref);

            String refType = ref.getRefClass(out);
            rlog.log(RMILog.VERBOSE, "refType = " + refType);
            out.writeUTF(refType);
            ref.writeExternal(out);
            rlog.log(RMILog.VERBOSE,
                    "ActivationID.writeObject COMPLETED.");

        } catch (Throwable t) {
            throw new IOException("Unable to serialize ActivationID: "
                    + t.getMessage());
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String toString() {
        return "ActivationID: " + "[" + uid + "; " + activator + "]";
    }
}
