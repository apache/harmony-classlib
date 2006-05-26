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
 * @author  Mikhail A. Markov
 * @version $Revision: 1.4.4.3 $
 */
package java.rmi.server;

import java.io.InvalidObjectException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.rmi.Remote;
import java.rmi.UnexpectedException;

import org.apache.harmony.rmi.common.RMIHash;


/**
 * @com.intel.drl.spec_ref
 *
 * @author  Mikhail A. Markov
 * @version $Revision: 1.4.4.3 $
 */
public class RemoteObjectInvocationHandler extends RemoteObject
        implements InvocationHandler {

    private static final long serialVersionUID = 2L;

    /**
     * @com.intel.drl.spec_ref
     */
    public RemoteObjectInvocationHandler(RemoteRef ref) {
        super(ref);

        if (ref == null) {
            throw new NullPointerException(
                    "RemoteRef parameter could not be null.");
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Object invoke(Object proxy, Method m, Object[] args)
            throws Throwable {
        Class mClass = m.getDeclaringClass();

        if (m.getDeclaringClass() == Object.class) {
            return invokeObjectMethod(proxy, m, args);
        } else if (!(proxy instanceof Remote)) {
            throw new IllegalArgumentException(
                    "Proxy does not implement Remote interface.");
        } else {
            return invokeRemoteMethod(proxy, m, args);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    private void readObjectNoData() throws InvalidObjectException {
        throw new InvalidObjectException("No data in stream for class "
                + this.getClass().getName());
    }

    /*
     * Invokes methods from Object class.
     */
    private Object invokeObjectMethod(Object proxy, Method m, Object[] args) {
        String mName = m.getName();

        if (mName.equals("hashCode")) {
            // return result of hashCode method call from RemoteObject class
            return new Integer(hashCode());
        } else if (mName.equals("equals")) {
            Object obj = args[0];
            return new Boolean((proxy == obj) // the same object?
                    || (obj != null && Proxy.isProxyClass(obj.getClass())
                            && equals(Proxy.getInvocationHandler(obj))));
        } else if (mName.equals("toString")) {
            Class[] interf = proxy.getClass().getInterfaces();

            if (interf.length == 0) {
                return "Proxy[" + toString() + "]";
            }
            String str = "Proxy[interf:[";

            for (int i = 0; i < interf.length - 1; ++i) {
                str += interf[i].getName() + ", ";
            }
            return str + interf[interf.length - 1].getName() + "], "
                    + toString() + "]";
        } else {
            throw new IllegalArgumentException(
                    "Illegal method from Object class: " + m);
        }
    }

    /*
     * Invokes Remote methods.
     */
    private Object invokeRemoteMethod(Object proxy, Method m, Object[] args)
            throws Throwable {
        try {
            return ref.invoke((Remote) proxy, m, args,
                    RMIHash.getMethodHash(m));
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception ex) {
            Method m1 = proxy.getClass().getMethod(m.getName(),
                    m.getParameterTypes());
            Class[] declaredEx = m1.getExceptionTypes();

            for (int i = 0; i < declaredEx.length; ++i) {
                if (declaredEx[i].isAssignableFrom(ex.getClass())) {
                    throw ex;
                }
            }
            throw new UnexpectedException("Unexpected exception", ex);
        }
    }
}
