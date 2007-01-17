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
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tests.support;

import java.net.ServerSocket;
import java.util.Calendar;
import java.util.TimeZone;

public class Support_PortManager {

    private static int lastAssignedPort = somewhatRandomPort();
    private static boolean failedOnce = false;

    public static synchronized int getNextPort() {
        if (!failedOnce) {
            try {
                ServerSocket ss = new ServerSocket(0);
                int port = ss.getLocalPort();

                ss.close();
                return port;
            } catch (Exception ex) {
                failedOnce = true;
            }
        }
        return getNextPort_unsafe();
    }

    public static synchronized int getNextPort_unsafe() {
        if (++lastAssignedPort > 65534) {
            lastAssignedPort = 6000;
        }
        return lastAssignedPort;
    }

    /*
      * Returns a different port number every 6 seconds or so. The port number
      * should be about += 100 at each 6 second interval
      */
    private static int somewhatRandomPort() {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        int minutes = c.get(Calendar.MINUTE);
        int seconds = c.get(Calendar.SECOND);

        return 6000 + (1000 * minutes) + ((seconds / 6) * 100);
    }

}
