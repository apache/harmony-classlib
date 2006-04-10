/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
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
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.net;

import java.io.IOException;
import java.util.Map;

/**
 * This class is ready for managing a stateful cookie with HTTP protocol
 * 
 */
public abstract class CookieHandler {

	private static CookieHandler systemWideCookieHandler;

	private final static NetPermission getCookieHandlerPermission = new NetPermission(
			"getCookieHandler"); //$NON-NLS-1$

	private final static NetPermission setCookieHandlerPermission = new NetPermission(
			"setCookieHandler"); //$NON-NLS-1$

	/**
	 * returns a system-wide cookie handler, or null if not set
	 * 
	 * @return a cookie handler
	 */
	public static CookieHandler getDefault() {
		SecurityManager sm = System.getSecurityManager();
		if (null != sm) {
			sm.checkPermission(getCookieHandlerPermission);
		}
		return systemWideCookieHandler;
	}

	/**
	 * sets a system-wide cookie handler
	 * 
	 * @param cHandler
	 *            the cookie handler to set
	 */
	public static void setDefault(CookieHandler cHandler) {
		SecurityManager sm = System.getSecurityManager();
		if (null != sm) {
			sm.checkPermission(setCookieHandlerPermission);
		}
		systemWideCookieHandler = cHandler;
	}

	/**
	 * searchs and gets all cookies in the cache by the specified uri in the
	 * request header.
	 * 
	 * @param uri
	 *            the specified uri to search for
	 * @param requestHeaders
	 *            a list of request headers
	 * @return a map that record all such cookies, the map is unchangeable
	 * @throws IOException
	 *             if some error of I/O operation occurs
	 */
	public abstract Map get(URI uri, Map requestHeaders) throws IOException;

	/**
	 * sets cookies according to uri and responseHeaders
	 * 
	 * @param uri
	 *            the specified uri
	 * @param responseHeaders
	 *            a list of request headers
	 * @throws IOException
	 *             if some error of I/O operation occurs
	 */
	public abstract void put(URI uri, Map responseHeaders) throws IOException;
}
