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
package java.rmi;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.URI;

/**
 * @ar.org.fitc.spec_ref
 * 
 * This class contains static methods which have the same names as 
 * methods were defined in the Registry Interface. It handles a 
 * String in an URL format (without the scheme component). The URL 
 * is parsed and the host/port information is forwarded to 
 * LocateRegistry. Naming uses the stub returned by LocateRegistry 
 * to invoke the correct method on the registry. 
 *
 * @author Marcelo Arcidiacono
 */
public class Naming  {
	
	/**
	 * @ar.org.fitc.spec_ref
	 *
	 */
    public static String[] list(String name) throws RemoteException, 
    		MalformedURLException {
    	
    	URI uri = nameParseURI(name);
    	Registry reg = getRegistry(uri);
    	String[] result = new String[reg.list().length];
    	for(int i = 0; i < reg.list().length; i++) {
    		result[i] = "//" + uri.getHost() + ":" + uri.getPort() 
    				  + "/" + reg.list()[i];
    	}
    	return result;
    }

    /**
	 * @ar.org.fitc.spec_ref
	 *
	 */
    public static Remote lookup(String name) throws NotBoundException, 
    		MalformedURLException, RemoteException {
    	Remote rem;
    	
    	URI uri = nameParseURI(name);
    	Registry reg = getRegistry(uri);
    	try {
    		rem = reg.lookup(uri.getPath().substring(1));
    	} catch(NotBoundException nbe){
    		throw new NotBoundException("The key " + name + " is not currently bound.");
    	}
    	return rem;
    }

    /**
	 * @ar.org.fitc.spec_ref
	 *
	 */
    public static void bind(String name, Remote obj) throws AlreadyBoundException, 
    		MalformedURLException, RemoteException {
        
    	URI uri = nameParseURI(name);
    	Registry reg = getRegistry(uri);
    	try {
    		reg.bind(uri.getPath().substring(1),obj);
    	} catch(AlreadyBoundException abe){
    		throw new AlreadyBoundException ("The key " + name + " already exists."); 
    	}
    }
    
    /**
	 * @ar.org.fitc.spec_ref
	 *
	 */
    public static void rebind(String name, Remote obj) throws RemoteException, 
    		MalformedURLException {
    	
    	URI uri = nameParseURI(name);
    	Registry reg = getRegistry(uri);
    	reg.rebind(uri.getPath().substring(1),obj);
    }
    
    /**
	 * @ar.org.fitc.spec_ref
	 *
	 */
    public static void unbind(String name) throws RemoteException, 
    		NotBoundException, MalformedURLException {
    	
    	URI uri = nameParseURI(name);
    	Registry reg = getRegistry(uri);
    	try {
    		reg.unbind(uri.getPath().substring(1));
    	} catch(NotBoundException nbe){
			throw new NotBoundException("The key " + name + " is not courrently bound.");
    	}
    }
    

    /**
     * Returns a reference to the remote object Registry on the specified 
     * host and port.
     * 
     * @param uri that contains the specified host and port
     * @return a reference to the remote object Registry
     * @throws RemoteException if the reference could not be created
     */
    private static Registry getRegistry(URI uri) throws RemoteException {
    	
    	return LocateRegistry.getRegistry(uri.getHost(), uri.getPort());    	
    }	
    	
    
  
    /**
     * Parses a string in an URL format without the scheme component 
     * and set it to an URI format. If the host is not defined, the 
     * default host is the localhost. If the port is not defined the 
     * default port is 1099.
     * 
     * @param name a string in URL format (without the scheme component)
     * @return an URI without the scheme component with the host and port in
     * explicit way  
     * @throws MalformedURLException if the name should be in invalid URL form
     */
    private static URI nameParseURI (String name) throws MalformedURLException {
    	URI uri;
    	URL url;
    	String path;
    	    	
    	try {
    		uri = new URI(name.trim());
    	} catch (URISyntaxException e) {
    		throw new MalformedURLException("invalid URL string: " + name);
    	}
    	if(uri.getScheme()!=null) {
    		throw new MalformedURLException("invalid URL scheme: " + name);
    	} else {
    		try {				 
    			url = new URL ("http:" + name.trim());
    			if (url.getFile().equals(url.getPath())) {
					path = (uri.getPath().startsWith("/")) 
					     ?  uri.getPath() 
    		             : "/" + uri.getPath();
				} else {
					throw new MalformedURLException("invalid character in URL name");
				}    
    	   		String host = (uri.getHost() != null) ? uri.getHost() : "localhost";
				int port = (url.getPort() != -1) ? url.getPort() : Registry.REGISTRY_PORT;
				uri = new URI(null, null, host, port, path, null, null);			
    		} catch (URISyntaxException e) {
    			e.getStackTrace(); 	// This exception should never happen.
    		}
    	}	
		return uri;
    }
}