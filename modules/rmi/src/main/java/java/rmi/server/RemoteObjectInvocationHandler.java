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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.rmi.Remote;
import java.rmi.UnexpectedException;
import org.apache.harmony.rmi.internal.utils.MethodHashGenerator;

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public class RemoteObjectInvocationHandler extends RemoteObject implements
        InvocationHandler {

    private static final long serialVersionUID = 2L;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public RemoteObjectInvocationHandler(RemoteRef ref) {
        super(ref);
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {

        Method hashCode = Object.class.getMethod("hashCode", (Class[]) null);
        Method equals = Object.class.getMethod("equals", Object.class);
        Method toString = Object.class.getMethod("toString", (Class[]) null);

        if (method.equals(hashCode)) {
            return ref.remoteHashCode();
        }
        if (method.equals(equals)) {
            if ((args[0] != null)
                    && (Proxy.isProxyClass(args[0].getClass()))
                    && (this.equals(Proxy.getInvocationHandler(args[0])))) {
                return true;
            } else {
                return false;
            }
        }
        if (method.equals(toString)) {
            return "[" + "Proxy " + ref.remoteToString() + "]";
        }
        if (!(proxy instanceof Remote)) {
            throw new IllegalArgumentException(
                    "Proxy must be an instance of java.rmi.Remote");
        }

        long methodHash = MethodHashGenerator.getMethodHash(method);
        try {
            return ref.invoke((Remote) proxy, method, args, methodHash);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            Class[] exceptions = method.getExceptionTypes();
            for (Class<Exception> exception : exceptions) {
                if (exception.isAssignableFrom(e.getClass())) {
                    throw e;
                }
            }
            throw new UnexpectedException("undeclared checked exception", e);
        }
    }
}
