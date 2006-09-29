/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
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
 * @version $Revision: 1.18.4.3 $
 */
package java.rmi.activation;

import java.rmi.MarshalledObject;
import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.RemoteObject;
import java.rmi.server.RemoteServer;

import org.apache.harmony.rmi.common.RMILog;
import org.apache.harmony.rmi.remoteref.ActivatableServerRef;
import org.apache.harmony.rmi.server.ExportManager;


/**
 * @com.intel.drl.spec_ref
 *
 * @author  Victor A. Martynov
 * @version $Revision: 1.18.4.3 $
 */
public abstract class Activatable extends RemoteServer {

    /**
     * serialVersionUID: -3120617863591563455L
     */
    private static final long serialVersionUID = -3120617863591563455L;

    /**
     * ActivationID
     */
    private ActivationID id;

    /**
     * RMILogger that is used to print all the debug information.
     *
     */
    private static RMILog rlog = RMILog.getActivationLog();

    /**
     * @com.intel.drl.spec_ref
     */
    protected Activatable(String codebase, MarshalledObject data,
            boolean restart, int port) throws ActivationException,
            RemoteException {
        super();
        id = exportObject(this, codebase, data, restart, port);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected Activatable(String codebase, MarshalledObject data,
            boolean restart, int port, RMIClientSocketFactory csf,
            RMIServerSocketFactory ssf) throws ActivationException,
            RemoteException {
        super();
        id = exportObject(this, codebase, data, restart, port, csf, ssf);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected Activatable(ActivationID id, int port) throws RemoteException {
        super();
        rlog.log(RMILog.VERBOSE, "Activatable.<init>[" + id + ", " + port + "]");
        this.id = id;

        rlog.log(RMILog.VERBOSE, "Activatable >>> Ready to export object:");
        exportObject(this, id, port);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected Activatable(ActivationID id, int port,
            RMIClientSocketFactory csf, RMIServerSocketFactory ssf)
            throws RemoteException {
        super();
        this.id = id;
        exportObject(this, id, port, csf, ssf);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected ActivationID getID() {
        return id;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static Remote register(ActivationDesc desc)
            throws UnknownGroupException, ActivationException, RemoteException {

        ActivationSystem as = ActivationGroup.getSystem();
        ActivationID aid = as.registerObject(desc);
        rlog.log(RMILog.VERBOSE, "aid = " + aid);

        return org.apache.harmony.rmi.remoteref.ActivatableRef
                .getStub(desc, aid);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static ActivationID exportObject(Remote obj, String location,
            MarshalledObject data, boolean restart, int port,
            RMIClientSocketFactory csf, RMIServerSocketFactory ssf)
            throws ActivationException, RemoteException {

        ActivationDesc adesc = new ActivationDesc(obj.getClass().getName(), location, data, restart);
        ActivationID aid = ActivationGroup.getSystem().registerObject(adesc);
        Remote stub = exportObject(obj, aid, port, csf, ssf);

        return aid;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static Remote exportObject(Remote robj, ActivationID aid, int port,
            RMIClientSocketFactory csf, RMIServerSocketFactory ssf)
            throws RemoteException {

        ActivatableServerRef asr = new ActivatableServerRef(aid, port, csf, ssf);

        return ExportManager.exportObject(robj, asr, false); //asr.exportObject(robj, null, false, true, true);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static ActivationID exportObject(Remote robj, String location,
            MarshalledObject data, boolean restart, int port)
            throws ActivationException, RemoteException {

        ActivationDesc adesc = new ActivationDesc(robj.getClass().getName(),
                location, data, restart);
        ActivationID aid = ActivationGroup.getSystem().registerObject(adesc);
        Remote stub = exportObject(robj, aid, port);
        ActivationGroup curAG = ActivationGroup.getCurrentAG();
        System.out.println("CurAG = "+curAG);
        curAG.activeObject(aid, robj);

        return aid;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static Remote exportObject(Remote robj, ActivationID aid, int port) throws RemoteException {
        rlog.log(RMILog.VERBOSE, "Activatable >>> exportObject");
        ActivatableServerRef asr = new ActivatableServerRef(aid, port);
        if (robj instanceof Activatable) {
            ((Activatable) robj).ref = asr;
        }
        rlog.log(RMILog.VERBOSE, "Activatable >>> ActivatableServerRef=" + asr);

        ExportManager.exportObject(robj, asr, false, true, true);
        rlog.log(RMILog.VERBOSE, "Activatable >>> asr after export: " + asr);
        Remote rmt = RemoteObject.toStub(robj);
        rlog.log(RMILog.VERBOSE, "Activatable.exportObject: stub = " + rmt);
        return rmt;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static boolean inactive(ActivationID aid)
            throws UnknownObjectException, ActivationException, RemoteException {
        return ActivationGroup.getCurrentAG().inactiveObject(aid);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static void unregister(ActivationID aid)
            throws UnknownObjectException, ActivationException, RemoteException {
        ActivationGroup.getSystem().unregisterObject(aid);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static boolean unexportObject(Remote obj, boolean force)
            throws NoSuchObjectException {
        return ExportManager.unexportObject(obj, force);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String toString() {
        return this.getClass() + ": [ActivationID =" + id + "; Ref =" + ref+ "]";
    }
}
