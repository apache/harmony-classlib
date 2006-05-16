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
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */
package ar.org.fitc.test.rmi.server.rmiclassloader;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClassLoader;

public class EchoClient {

    public static void main(String[] args) throws Exception {

        Registry registry = LocateRegistry.getRegistry();
        IEcho stub = (IEcho) registry.lookup("Echo2");
        String response = stub.echo("Real Time in client: "
                + System.currentTimeMillis());
        System.out.println("Answer from server: " + response);

        try {
            RMIClassLoader.getClassLoader("file://home/odemo/classes");
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

}
