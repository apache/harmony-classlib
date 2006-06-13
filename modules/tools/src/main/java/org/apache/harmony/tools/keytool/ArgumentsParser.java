/*
 * Copyright 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.apache.harmony.tools.keytool;

/**
 * The class to interact with the user - parse the program arguments, ask for
 * confirmations, and necessary parameters which haven't been set in the command
 * line.
 */

class ArgumentsParser {
    // / options names to compare to ///
    // commands
    final static String sGenkey = "-genkey";

    final static String sSelfcert = "-selfcert";

    final static String sImport = "-import";

    final static String sExport = "-export";

    final static String sStorepasswd = "-storepasswd";

    final static String sKeypasswd = "-keypasswd";

    final static String sCertreq = "-certreq";

    final static String sSign = "-sign";

    final static String sCheck = "-checkcrl";

    final static String sAdd = "-addtocrl";

    final static String sVerify = "-verify";

    final static String sPrintcert = "-printcert";

    final static String sKeyclone = "-keyclone";

    final static String sDelete = "-delete";

    final static String sList = "-list";

    final static String sHelp = "-help";

    // addititanal options
    final static String sKeystore = "-keystore";

    final static String sStoretype = "-storetype";

    final static String sProvider = "-provider";

    final static String sStorepass = "-storepass";

    final static String sAlias = "-alias";

    final static String sKeyalg = "-keyalg";

    final static String sKeysize = "-keysize";

    final static String sSigalg = "-sigalg";

    final static String sDname = "-dname";

    final static String sKeypass = "-keypass";

    final static String sValidity = "-validity";

    final static String sV = "-v";

    final static String sJ = "-J";

    final static String sFile = "-file";

    final static String sNoprompt = "-noprompt";

    final static String sTrustcacerts = "-trustcacerts";

    final static String sRfc = "-rfc";

    final static String sNew = "-new";

    final static String sCertalias = "-certalias";

    final static String sCertstore = "-certstore";

    final static String sSecretkey = "-secretkey";

    final static String sX509Version = "-x509version";

    final static String sCertSerial = "-certserial";

    final static String sDestAlias = "-dest";

    final static String sCRLstore = "-crlstore";

    /**
     * The method finds known options in args which is usually taken from
     * command line and sets the corresponding fields of the returned
     * KeytoolParameters object to given values.
     */
    static KeytoolParameters parseArgs(String[] args) {
        // TODO: look for known options and get their values
        if (args == null || args.length == 0) {
            return null;
        }
        KeytoolParameters param = new KeytoolParameters();
        return param;
    }

    /**
     * Checks if the needed values are set and, if not, prompts for them.
     */
    static void getAdditionalParameters(KeytoolParameters param) {
        // TODO
        throw new RuntimeException("The method is not implemented yet.");
    }

}
