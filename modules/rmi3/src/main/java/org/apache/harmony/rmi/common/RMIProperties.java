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
 * @author  Mikhail A. Markov, Vasily Zakharov
 * @version $Revision: 1.1.2.7 $
 */
package org.apache.harmony.rmi.common;


/**
 * Interface containing names of all supported RMI properties.
 *
 * @author  Mikhail A. Markov, Vasily Zakharov
 * @version $Revision: 1.1.2.7 $
 */
public interface RMIProperties {

    /*
     * -------------------------------------------------------------------------
     * java.rmi.* supported properties
     * -------------------------------------------------------------------------
     */
    String ACTIVATIONPORT_PROP = "java.rmi.activation.port";
    String DGCLEASEVALUE_PROP = "java.rmi.dgc.leaseValue";
    String CODEBASE_PROP = "java.rmi.server.codebase";
    String HOSTNAME_PROP = "java.rmi.server.hostname";
    String LOGSERVER_PROP = "java.rmi.server.logCalls";
    String RANDOMIDS_PROP = "java.rmi.server.randomIDs";
    String USECODEBASEONLY_PROP = "java.rmi.server.usecodebaseOnly";
    String USELOCALHOSTNAME_PROP = "java.rmi.server.useLocalHostname";
    String DISABLEHTTP_PROP = "java.rmi.server.disableHttp";
    String IGNORESTUBCLASSES_PROP = "java.rmi.server.ignoreStubClasses";

    /*
     * -------------------------------------------------------------------------
     * harmony.rmi.* supported properties
     * -------------------------------------------------------------------------
     */

    // Server properties.
    String DGCACKTIMEOUT_PROP = "harmony.rmi.dgc.ackTimeout";
    String DGCCHECKINTERVAL_PROP = "harmony.rmi.dgc.checkInterval";
    String DGCLOGLEVEL_PROP = "harmony.rmi.dgc.logLevel";
    String LOADERLOGLEVEL_PROP = "harmony.rmi.loader.logLevel";
    String EXCEPTIONTRACE_PROP = "harmony.rmi.server.exceptionTrace";
    String SUPPRESSSTACKTRACES_PROP = "harmony.rmi.server.suppressStackTraces";
    String TRANSPORTLOGLEVEL_PROP = "harmony.rmi.transport.logLevel";
    String LOCALHOSTNAMETIMEOUT_PROP = "harmony.rmi.transport.tcp.localHostNameTimeOut";
    String TRANSPORTTCPLOGLEVEL_PROP = "harmony.rmi.transport.tcp.logLevel";
    String READTIMEOUT_PROP = "harmony.rmi.transport.tcp.readTimeout";

    // Client properties.
    String LOGCLIENT_PROP = "harmony.rmi.client.logCalls";
    String DGCCLEANINTERVAL_PROP = "harmony.rmi.dgc.cleanInterval";
    String SERVERLOGLEVEL_PROP = "harmony.rmi.server.logLevel";
    String CLIENTLOGLEVEL_PROP = "harmony.rmi.client.logLevel";
    String CONNECTIONTIMEOUT_PROP = "harmony.rmi.transport.connectionTimeout";
    String CONNECTTIMEOUT_PROP = "harmony.rmi.transport.proxy.connectTimeout";
    String EAGERHTTPFALLBACK_PROP = "harmony.rmi.transport.proxy.eagerHttpFallback";
    String TRANSPORTPROXYLOGLEVEL_PROP = "harmony.rmi.transport.proxy.logLevel";
    String HANDSHAKETIMEOUT_PROP = "harmony.rmi.transport.tcp.handshakeTimeout";

    // Activation properties.
    String ACTIVATIONLOGLEVEL_PROP = "harmony.rmi.activation.logLevel";
    String ACTIVATION_EXECTIMEOUT_PROP = "harmony.rmi.activation.execTimeout";
    String MAXSTARTGROUP_PROP = "harmony.rmi.activation.groupThrottle";
    String ACTIVATION_SNAPSHOTINTERVAL_PROP = "harmony.rmi.activation.snapshotInterval";
    String ACTIVATION_LOG_DEBUG_PROP = "harmony.rmi.log.debug";
    String ACTIVATION_DEBUGEXEC_PROP = "harmony.rmi.server.activation.debugExec";

    /*
     * -------------------------------------------------------------------------
     * Additional proxy properties
     * -------------------------------------------------------------------------
     */

    /**
     * Name of the system property containing HTTP proxy host name.
     */
    String PROXY_HOST_PROP = "http.proxyHost";

    /**
     * Name of the system property containing HTTP proxy port number.
     */
    String PROXY_PORT_PROP = "http.proxyPort";

    /**
     * Name of the property allowing to disable direct socket connections.
     */
    String DISABLE_DIRECT_SOCKET_PROP =
            "org.apache.harmony.rmi.transport.disableDirectSocket";

    /**
     * Name of the property allowing to enable direct HTTP connections.
     */
    String ENABLE_DIRECT_HTTP_PROP =
            "org.apache.harmony.rmi.transport.proxy.enableDirectHTTP";

    /**
     * Name of the property allowing to disable plain HTTP connections
     * (and force CGI instead).
     */
    String DISABLE_PLAIN_HTTP_PROP =
            "org.apache.harmony.rmi.transport.proxy.disablePlainHTTP";

    /*
     * -------------------------------------------------------------------------
     * Additional Activation properties
     * -------------------------------------------------------------------------
     */

    /**
     * @see org.apache.harmony.rmi.common.RMIConstants#DEFAULT_ACTIVATION_MONITOR_CLASS_NAME
     */
    String ACTIVATION_MONITOR_CLASS_NAME_PROP =
            "org.apache.harmony.rmi.activation.monitor";

    /*
     * -------------------------------------------------------------------------
     * RMI Compiler properties
     * -------------------------------------------------------------------------
     */

    /**
     * Property specifying the compiler class to use.
     */
    String JAVA_COMPILER_CLASS_PROPERTY =
            "org.apache.harmony.rmi.compiler.class";

    /**
     * Property specifying the compiler class method to use.
     */
    String JAVA_COMPILER_METHOD_PROPERTY =
            "org.apache.harmony.rmi.compiler.method";

    /**
     * Property specifying the compiler executable to use.
     */
    String JAVA_COMPILER_EXECUTABLE_PROPERTY =
            "org.apache.harmony.rmi.compiler.executable";
}
