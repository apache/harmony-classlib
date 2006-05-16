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
package ar.org.fitc.rmi.registry;

import java.lang.reflect.Method;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.UnexpectedException;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteRef;
import java.rmi.server.RemoteStub;

/**
 * Generic stub used to contact a remote registry.
 * 
 * @author Gonzalo Ortega
 * 
 */
final class RegistryImpl_Stub extends RemoteStub implements Registry {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private static final long METHOD_BIND_HASH = 
        7583982177005850366L;

    private static final long METHOD_LIST_HASH = 
        2571371476350237748L;

    private static final long METHOD_LOOK_UP_HASH = 
        -7538657168040752697L;
    
    private static final long METHOD_REBIND_HASH = 
        -8381844669958460146L;
    
    private static final long METHOD_UNBIND_HASH = 
        7305022919901907578L;

    private static Method methodBind;

    private static Method methodList;
    
    private static Method methodLookup;
    
    private static Method methodRebind;

    private static Method methodUnbind;


    static {
        try {
            methodBind = Registry.class.getMethod("bind", new Class[] {
                    String.class, Remote.class });
            methodList = Registry.class.getMethod("list", new Class[] {});
            methodLookup = Registry.class.getMethod("lookup",
                    new Class[] { String.class });
            methodRebind = Registry.class.getMethod("rebind", new Class[] {
                    String.class, Remote.class });
            methodUnbind = Registry.class.getMethod("unbind",
                    new Class[] { String.class });
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError("stub class initialization failed");
        }
    }

    /**
     * Creates a new instance of RegistryImpl_Stub starting from the received
     * <code>RemoteRef</code>
     * 
     * @param ref
     *            The <code>RemoteRef</code> for the remote registry
     */
    public RegistryImpl_Stub(RemoteRef ref) {
        super(ref);
    }

    /**
     * Puts the received key <code>name</code> and the stub <code>obj</code>
     * in this registry, or throws an exception if the received key is already
     * present in the registry.
     * 
     * @param name
     *            The key that will be used to lookup the remote object
     * @param obj
     *            The remote object associated with <code>name</code>
     * @throws RemoteException
     *             if remote communication with the registry failed
     * @throws AlreadyBoundException
     *             if <code>name</code> is already present in the table
     * @throws AccessException
     *             if registry denies the caller access to perform this
     *             operation
     */
    public final void bind(String name, Remote obj) throws RemoteException,
            AlreadyBoundException, AccessException {
        try {
            ref.invoke(this, methodBind, new Object[] { name, obj },
                    METHOD_BIND_HASH);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e) {
            throw e;
        } catch (AlreadyBoundException e) {
            throw e;
        } catch (Exception e) {
            throw new UnexpectedException("undeclared checked exception", e);
        }
    }

    /**
     * Returns all keys present in the registry in a String array.
     * 
     * @return The list of keys bound to the registry
     * @throws RemoteException
     *             if remote communication with the registry failed
     * @throws AccessException
     *             if registry denies the caller access to perform this
     *             operation
     */
    public final String[] list() throws RemoteException, AccessException {
        try {
            Object result = ref.invoke(this, methodList, null,
                    METHOD_LIST_HASH);
            return ((String[]) result);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e) {
            throw e;
        } catch (Exception e) {
            throw new UnexpectedException("undeclared checked exception", e);
        }
    }

    /**
     * Returns the associated stub for the received key, or an exception if no
     * stub is present for that key
     * 
     * @param name
     *            The key used to bind the remote stub in the registry
     * @return The remote object associated with <code>name</code>
     * @throws RemoteException
     *             if remote communication with the registry failed
     * @throws NotBoundException
     *             If the key is not found in the registry
     */
    public final Remote lookup(String name) throws RemoteException,
            NotBoundException, AccessException {
        try {
            Object result = ref.invoke(this, methodLookup,
                    new Object[] { name }, METHOD_LOOK_UP_HASH);
            return ((Remote) result);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e) {
            throw e;
        } catch (NotBoundException e) {
            throw e;
        } catch (Exception e) {
            throw new UnexpectedException("undeclared checked exception", e);
        }
    }

    /**
     * Puts the received key <code>name</code> and the stub <code>obj</code>
     * in this registry.
     * 
     * @param name
     *            The key that will be used to lookup the remote object
     * @param obj
     *            The remote object associated with <code>name</code>
     * @throws RemoteException
     *             if remote communication with the registry failed
     * @throws AccessException
     *             if registry denies the caller access to perform this
     *             operation
     */
    public final void rebind(String name, Remote obj) throws RemoteException,
            AccessException {
        try {
            ref.invoke(this, methodRebind, new Object[] { name, obj },
                    METHOD_REBIND_HASH);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e) {
            throw e;
        } catch (Exception e) {
            throw new UnexpectedException("undeclared checked exception", e);
        }
    }

    /**
     * Removes the stub associated to the key received <code>name</code> from
     * this registry
     * 
     * @param name
     *            the name of the binding to remove
     * @throws RemoteException
     *             if remote communication with the registry failed
     * @throws NotBoundException
     *             if <code>name</code> is not found in the registry
     * @throws AccessException
     *             if registry denies the caller access to perform this
     *             operation
     */
    public final void unbind(String name) throws RemoteException, NotBoundException,
            AccessException {
        try {
            ref.invoke(this, methodUnbind, new Object[] { name },
                    METHOD_UNBIND_HASH);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e) {
            throw e;
        } catch (NotBoundException e) {
            throw e;
        } catch (Exception e) {
            throw new UnexpectedException("undeclared checked exception", e);
        }
    }
}
