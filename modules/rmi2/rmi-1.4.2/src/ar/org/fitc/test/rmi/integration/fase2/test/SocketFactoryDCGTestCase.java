package ar.org.fitc.test.rmi.integration.fase2.test;

import java.lang.ref.WeakReference;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

import ar.org.fitc.test.rmi.integration.fase2.LogRemoteImpl;
import ar.org.fitc.test.rmi.integration.fase2.MoveRemoteObject;
import ar.org.fitc.test.rmi.integration.fase2.interfaces.LogRemote;
import ar.org.fitc.test.rmi.integration.fase2.socketfactory.CipherRMISocketFactory;


/**
 * DGC Test case with Cipher Socket Factory
 * 
 * @author Osvaldo Demo
 * 
 * @version 1.0
 */

public class SocketFactoryDCGTestCase extends DGCTestCase {
    
    public RMIClientSocketFactory crsk;
    
    public RMIServerSocketFactory srsk; 

    @SuppressWarnings("unchecked")
	@Override
    protected void setUp() throws Exception {
        
        crsk = new CipherRMISocketFactory();
        srsk = new CipherRMISocketFactory();
        remote = new LogRemoteImpl();
        stub = (LogRemote) UnicastRemoteObject.exportObject(remote, 0,crsk,srsk);
        // Container and remote execution
        serv = new MoveRemoteObject(remote);
        wref = new WeakReference(remote);
        super.setUp();
    }
    
}
