/* Copyright 1998, 2005 The Apache Software Foundation or its licensors, as applicable
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

package tests.support;

import java.util.Date;

public class Support_PortManager extends java.lang.Object {

	private static int lastAssignedPort = somewhatRandomPort();

	public static synchronized int getNextPort() {
		if (++lastAssignedPort > 65534)
			lastAssignedPort = 6000;
		return lastAssignedPort;
	}

	/*
	 * Returns a different port number every 6 seconds or so. The port number
	 * should be about += 100 at each 6 second interval
	 */
	private static int somewhatRandomPort() {
		Date date = new Date();
		int minutes = date.getMinutes();
		int seconds = date.getSeconds();
		return 6000 + (1000 * minutes) + ((seconds / 6) * 100);
	}

}
