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
 * @version $Revision: 1.12.4.2 $
 */
package java.rmi;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 * @com.intel.drl.spec_ref
 *
 * This class could not be instantiated.
 *
 * @author  Mikhail A. Markov
 * @version $Revision: 1.12.4.2 $
 */
public final class Naming {

    // This class could not be instantiated.
    private Naming() {
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static String[] list(String name)
            throws RemoteException, MalformedURLException {
        if (name == null) {
            throw new NullPointerException("URL could not be null.");
        }
        RegistryURL url = getRegistryURL(name, true);
        Registry reg = LocateRegistry.getRegistry(url.host, url.port);
        String[] names = reg.list();
        String regName = "//" + ((url.host == null) ? "" : url.host) + ":"
                + url.port + "/";

        for (int i = 0; i < names.length; ++i) {
            names[i] = regName + names[i];
        }
        return names;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static void rebind(String name, Remote obj)
            throws RemoteException, MalformedURLException {
        if (name == null) {
            throw new NullPointerException("URL could not be null.");
        }
        RegistryURL url = getRegistryURL(name, false);
        Registry reg = LocateRegistry.getRegistry(url.host, url.port);
        reg.rebind(url.name, obj);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static void unbind(String name)
            throws RemoteException, NotBoundException, MalformedURLException {
        if (name == null) {
            throw new NullPointerException("URL could not be null.");
        }
        RegistryURL url = getRegistryURL(name, false);
        Registry reg = LocateRegistry.getRegistry(url.host, url.port);
        reg.unbind(url.name);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static void bind(String name, Remote obj)
            throws AlreadyBoundException, MalformedURLException,
            RemoteException {
        if (name == null) {
            throw new NullPointerException("URL could not be null.");
        }
        RegistryURL url = getRegistryURL(name, false);
        Registry reg = LocateRegistry.getRegistry(url.host, url.port);
        reg.bind(url.name, obj);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static Remote lookup(String name)
            throws NotBoundException, MalformedURLException, RemoteException {
        if (name == null) {
            throw new NullPointerException("URL could not be null.");
        }
        RegistryURL url = getRegistryURL(name, false);
        Registry reg = LocateRegistry.getRegistry(url.host, url.port);
        return reg.lookup(url.name);
    }

    /*
     * Parse the given name and returnes URL containing parsed parameters.
     */
    private static RegistryURL getRegistryURL(String strUrl,
                                              boolean ignoreEmptyNames)
            throws MalformedURLException {
        URI uri;

        try {
            uri = new URI(strUrl);
        } catch (URISyntaxException use) {
            throw new MalformedURLException("Invalid URL \"" + strUrl + "\":"
                    + use);
        }
        String prot = uri.getScheme();

        if ((prot != null) && !prot.toLowerCase().equals("rmi")) {
            throw new MalformedURLException(
                    "Non-rmi protocol in URL \"" + strUrl + "\": " + prot);
        }

        if (uri.getUserInfo() != null) {
            throw new MalformedURLException("Invalid character ('@') in URL \""
                    + strUrl + "\" host part.");
        } else if (uri.getQuery() != null) {
            throw new MalformedURLException("Invalid character ('?') in URL \""
                    + strUrl + "\" name part.");
        } else if (uri.getFragment() != null) {
            throw new MalformedURLException("Invalid character ('#') in URL \""
                    + strUrl + "\" name part.");
        }
        int port = uri.getPort();
        String auth = uri.getAuthority();

        if (auth != null && auth.startsWith(":") && auth.length() != 1) {
            // to handle URLs like "rmi://:1099/xxx"
            try {
                port = Integer.parseInt(auth.substring(1));
            } catch (NumberFormatException nfe) {
                throw new MalformedURLException("Invalid port number in URL \""
                        + strUrl + "\": " + auth.substring(1));
            }
        }

        if (port == -1) {
            port = Registry.REGISTRY_PORT;
        }
        String path = uri.getPath();

        if (!ignoreEmptyNames) {
            if (path == null || path.length() == 0) {
                throw new MalformedURLException(
                        "Name could not be empty (URL: \"" + strUrl + "\").");
            }
        }

        if (path != null && path.startsWith("/")) {
            path = path.substring(1);
        }
        String host = uri.getHost();

        if (host == null) {
            host = "localhost";
        }
        return new RegistryURL(host, port, path);
    }


    /*
     * Auxiliary class holding information about host, port and name.
     */
    private static class RegistryURL {

        // Host name.
        String host;

        // Port number.
        int port;

        // bind name
        String name;

        /*
         * Constructs RegistryURL from the given host, port and bind name.
         */
        RegistryURL(String host, int port, String name) {
            this.host = host;
            this.port = port;
            this.name = name;
        }
    }
}
