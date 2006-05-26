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
 * @author  Victor A. Martynov
 * @version $Revision: 1.1.2.4 $
 */
package org.apache.harmony.rmi.activation;

import java.lang.reflect.Constructor;

import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

import org.apache.harmony.rmi.common.RMILog;


/**
 * Factory class to create RmidMonitors.
 *
 * @author  Victor A. Martynov
 * @version $Revision: 1.1.2.4 $
 */
class RmidMonitorFactory {

    /**
     * Standard logger for RMI Activation.
     *
     * @see org.apache.harmony.rmi.common.RMILog#getActivationLog()
     */
    private static RMILog rLog = RMILog.getActivationLog();

    /**
     * Factory method intended to obtain RmidMonitor implementation.
     *
     * @param className
     *         Fully qualified class name of the monitor.
     *
     * @return instance of the monitor class, <code>null</code> - if class
     *         was not found.
     * @see Rmid
     * @see org.apache.harmony.rmi.common.RMIProperties#ACTIVATION_MONITOR_CLASS_NAME_PROP
     * @see org.apache.harmony.rmi.common.RMIConstants#DEFAULT_ACTIVATION_MONITOR_CLASS_NAME
     * @see org.apache.harmony.rmi.activation.RmidMonitorAdapter
     */
    static RmidMonitor getRmidMonitor(String className) {

        try {
            final Class cl = Class.forName(className);

            rLog.log(Rmid.commonDebugLevel, "RMID Monitor class = " + cl);

            return (RmidMonitor) AccessController
                    .doPrivileged(new PrivilegedExceptionAction() {

                        public Object run() throws Exception {
                            Constructor constructor = cl
                                    .getConstructor(new Class[0]);

                            return constructor.newInstance(new Object[0]);
                        }
                    });
        } catch (Exception e) {
            return null;
        }
    }
}
