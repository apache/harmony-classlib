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
* @author Maxim V. Makarov
* @version $Revision$
*/

package org.apache.harmony.auth.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

import javax.security.auth.login.AppConfigurationEntry;

import org.apache.harmony.security.fortress.PolicyUtils;



/**
 * jaas configuration parser
 */

public class DefaultConfigurationParser {


    // logging flag for debug
    private static final boolean debug = false;

    /** 
     * Reads a login configuration file from a location to a stream,
     * defines applications name and entries
     * 
     * @param location an URL of a configration file to be loaded
     * @param system properties, used for property expansion
     * @param newConfig 
     * @return Map of applications name and entries
     * @throws Exception error while reading location or file syntax error 
     */
    public static Map configParser(URL location, 
                                   Properties system, 
                                   Map newConfig) 
            throws IOException, PrivilegedActionException, 
                   DefaultConfigurationParser.InvalidFormatException {

        
        Reader source = new BufferedReader(new InputStreamReader((InputStream)AccessController
                    .doPrivileged(new PolicyUtils.URLLoader(location))));
        try {
            newConfig =  scanStream(source, newConfig, system);
        } finally {
            source.close();  
        }
        
        return newConfig; 
    }
    
    /** 
     * Performs the main parsing loop. Starts with creating and configuring a
     * StreamTokenizer instance and then collects result to the passed map
     */
    private static Map scanStream(Reader source, Map newConfig, Properties system) throws IOException,
                                    InvalidFormatException{
        
        LinkedList entriesList;
        
        StreamTokenizer st = new StreamTokenizer(source);
             
        st.slashSlashComments(true);
        st.slashStarComments(true);
        st.eolIsSignificant(false);
        st.quoteChar('"');
        st.wordChars('$', '$');
        st.wordChars('@', '@');
        st.wordChars('_', '_');
        st.wordChars('-', '-');

        st.nextToken();
        while (st.ttype != StreamTokenizer.TT_EOF) {
            entriesList = new LinkedList();

            String appName = null;
            String loginModuleName = null;
            AppConfigurationEntry.LoginModuleControlFlag flag;
            HashMap options;

            appName = parseApplicationName(st);
            hasToken(st, '{');
            while (st.ttype != '}') {
                loginModuleName = parseModuleClass(st);
                flag = parseControlFlag(st);
                options = parseModuleOptions(st, system);
                AppConfigurationEntry entry = new AppConfigurationEntry(
                    loginModuleName, flag, options);
                entriesList.add(entry);

                if (debug) {
                   System.out.println("loginModuleName: " + loginModuleName);
                   System.out.println("flag: " + flag.toString());
                   System.out.println("options: " + options.toString());
                }
            }
            hasToken(st, '}');
            hasToken(st, ';');
        
            if (newConfig.containsKey(appName)) {
                throw new InvalidFormatException("\n\tApplication name" + " ["
                    + appName + "] " + "already exists");
            } 
            newConfig.put(appName, entriesList);
        }
        return newConfig;
    }

    /**
     * Defines a application name token
     * 
     * @throws IOException if stream reading failed
     * @throws InvalidFormatException if unexpected or unknown token encountered
     */
    private static String parseApplicationName(StreamTokenizer st)
        throws IOException, InvalidFormatException {

        if (st.ttype != StreamTokenizer.TT_WORD) {
            throw new InvalidFormatException("\n\tInvalid application name "
                                         + st.toString());
        }

        String appName = st.sval;

        if (debug) {
            System.out.println("\nAppname: " + appName);
        }
        st.nextToken();

        return appName;
    }

    /**
     * Defines a login module name token
     */
    private static String parseModuleClass(StreamTokenizer st)
        throws IOException, InvalidFormatException {

        if (st.ttype != StreamTokenizer.TT_WORD) {
            throw new InvalidFormatException("\n\tInvalid LoginModule name "
                                         + st.toString());
        }
        return st.sval;
    }

    /**
     * Defines a control flag token
     */
    private static AppConfigurationEntry.LoginModuleControlFlag parseControlFlag(StreamTokenizer st)
        throws IOException, InvalidFormatException {

        st.nextToken();

        if ("required".equalsIgnoreCase(st.sval)) {
            return AppConfigurationEntry.LoginModuleControlFlag.REQUIRED;
        } else if ("requisite".equalsIgnoreCase(st.sval)) {
            return AppConfigurationEntry.LoginModuleControlFlag.REQUISITE;
        } else if ("optional".equalsIgnoreCase(st.sval)) {
            return AppConfigurationEntry.LoginModuleControlFlag.OPTIONAL;
        } else if ("sufficient".equalsIgnoreCase(st.sval)) {
            return AppConfigurationEntry.LoginModuleControlFlag.SUFFICIENT;
        } else {
            throw new InvalidFormatException("\n\tInvalid control flag: " + st.sval);
        }
    }

    /**
     * Defines map of options
     */
    private static HashMap parseModuleOptions(StreamTokenizer st, Properties system)
        throws IOException, InvalidFormatException {

        HashMap options = new HashMap();

        st.nextToken();
        
        while (st.ttype != ';') {
            String key = null;
            String val = null;

            if (st.ttype == StreamTokenizer.TT_WORD) {
                key = st.sval;
                st.nextToken();
                if (st.ttype == '=') {
                    st.nextToken();
                    if (PolicyUtils.canExpandProperties()) {
                        try {
                            val = PolicyUtils.expand(st.sval, system);
                        } catch (Exception e) {
                            //TODO: warning log
                        } 
                    }
                } else {
                    throw new InvalidFormatException(
                        "\n\tInvalid options format " + st.toString());
                }
            } else {
                throw new InvalidFormatException("\n\tInvalid options format "
                    + st.toString());
            }

            if (key != null && val != null) {
                options.put(key, val);
            }
            st.nextToken();
        }
        hasToken(st, ';');

        return options;
    }

    /**
     * checks current token
     */
    private static void hasToken(StreamTokenizer st, char ttype)
        throws IOException, InvalidFormatException {
        if (st.ttype == ttype) {
            st.nextToken();
        } else {
            throw new InvalidFormatException("\n\tInvalid token: "
                + st.toString());
        }
    }

    /**
     * Specific exception class to signal configuration file syntax error.
     * 
     */
    public static class InvalidFormatException extends Exception {
        
        /**
         *  Constructor with detailed message parameter.
         * 
         * @param message - the detail message.
         */
        public InvalidFormatException(String message) {
            super(message);
        }
    }

}
