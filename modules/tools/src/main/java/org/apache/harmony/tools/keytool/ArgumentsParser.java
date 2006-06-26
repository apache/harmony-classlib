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

import java.io.IOException;

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

    final static String sIssuerAlias = "-issuer";

    final static String sIssuerPass = "-issuerpass";

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
     * 
     * @throws KeytoolException
     * @throws IOException
     */
    static KeytoolParameters parseArgs(String[] args) throws KeytoolException,
            IOException {
        if (args == null || args.length == 0) {
            return null;
        }
        KeytoolParameters param = new KeytoolParameters();

        // look for known options and get their values.
        try {
            for (int i = 0; i < args.length; i++) {

                // commands
                if (args[i].compareToIgnoreCase(sGenkey) == 0) {
                    param.setCommand(Command.GENKEY);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sSelfcert) == 0) {
                    param.setCommand(Command.SELFCERT);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sImport) == 0) {
                    param.setCommand(Command.IMPORT);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sExport) == 0) {
                    param.setCommand(Command.EXPORT);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sStorepasswd) == 0) {
                    param.setCommand(Command.STOREPASSWD);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sKeypasswd) == 0) {
                    param.setCommand(Command.KEYPASSWD);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sCertreq) == 0) {
                    param.setCommand(Command.CERTREQ);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sCheck) == 0) {
                    param.setCommand(Command.CHECK);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sAdd) == 0) {
                    param.setCommand(Command.ADD);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sVerify) == 0) {
                    param.setCommand(Command.VERIFY);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sPrintcert) == 0) {
                    param.setCommand(Command.PRINTCERT);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sKeyclone) == 0) {
                    param.setCommand(Command.KEYCLONE);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sDelete) == 0) {
                    param.setCommand(Command.DELETE);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sList) == 0) {
                    param.setCommand(Command.LIST);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sHelp) == 0) {
                    param.setCommand(Command.HELP);
                    continue;
                }

                // additional options
                if (args[i].compareToIgnoreCase(sKeystore) == 0) {
                    param.setStorePath(args[++i]);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sStoretype) == 0) {
                    param.setStoreType(args[++i]);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sProvider) == 0) {
                    param.setProvider(args[++i]);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sAlias) == 0) {
                    param.setAlias(args[++i]);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sKeyalg) == 0) {
                    param.setKeyAlg(args[++i]);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sSigalg) == 0) {
                    param.setSigAlg(args[++i]);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sDname) == 0) {
                    param.setDName(args[++i]);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sFile) == 0) {
                    param.setFileName(args[++i]);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sIssuerAlias) == 0) {
                    param.setIssuerAlias(args[++i]);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sCertstore) == 0) {
                    param.setStorePath(args[++i]);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sStorepass) == 0) {
                    param.setStorePass(args[++i].toCharArray());
                    continue;
                }
                if (args[i].compareToIgnoreCase(sKeypass) == 0) {
                    param.setKeyPass(args[++i].toCharArray());
                    continue;
                }
                if (args[i].compareToIgnoreCase(sIssuerPass) == 0) {
                    param.setIssuerPass(args[++i].toCharArray());
                    continue;
                }
                if (args[i].compareToIgnoreCase(sCRLstore) == 0) {
                    param.setCrlStore(args[++i]);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sDestAlias) == 0) {
                    param.setDestAlias(args[++i]);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sNew) == 0) {
                    param.setNewPasswd(args[++i].toCharArray());
                    continue;
                }
                if (args[i].compareToIgnoreCase(sKeysize) == 0) {

                    param.setKeySize((new Integer(args[++i])).intValue());
                    if (param.getKeySize() <= 0) {
                        throw new KeytoolException("Key size"
                                + " must be more than zero.");
                    }
                    continue;
                }
                if (args[i].compareToIgnoreCase(sValidity) == 0) {
                    param.setValidity((new Integer(args[++i])).intValue());
                    if (param.getValidity() <= 0) {
                        throw new KeytoolException("Validity"
                                + " must be more than zero.");
                    }
                    continue;
                }
                if (args[i].compareToIgnoreCase(sX509Version) == 0) {
                    param.setX509version((new Integer(args[++i])).intValue());
                    if (param.getX509version() < 1
                            || param.getX509version() > 3) {
                        throw new KeytoolException(
                                "Certificate version must be " + "1, 2 or 3");
                    }
                    continue;
                }
                if (args[i].compareToIgnoreCase(sCertSerial) == 0) {
                    param.setCertSerialNr((new Integer(args[++i])).intValue());
                    if (param.getCertSerialNr() <= 0) {
                        throw new KeytoolException("Certificate serial number"
                                + " must be more than zero.");
                    }
                    continue;
                }

                // flags
                if (args[i].compareToIgnoreCase(sNoprompt) == 0) {
                    param.setNoPrompt(true);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sTrustcacerts) == 0) {
                    param.setTrustCACerts(true);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sRfc) == 0) {
                    param.setRfc(true);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sV) == 0) {
                    param.setVerbose(true);
                    continue;
                }
                if (args[i].compareToIgnoreCase(sSecretkey) == 0) {
                    param.setSecretKey(true);
                    continue;
                }

                System.out.println("Illegal option: " + args[i]);
                return null;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // ignore the last option if its value is not provided
        }

        // set flag to use certstore, not keystore.
        Command cmd = param.getCommand();

        // check whether -v and -rfc options are used separately with -list.
        if (cmd == Command.LIST && param.isRfc() && param.isVerbose()) {
            throw new KeytoolException("There must not be both -v and -rfc "
                    + "options specified");
        }

        // skip the store password setting if -printcert or -help commands were
        // given.
        if (cmd == Command.PRINTCERT || cmd == Command.HELP) {
            return param;
        }

        // TODO: if the store password has not been entered, prompt for it
        return param;
    }

    /**
     * Checks if the needed values are set and, if not, prompts for them.
     */
    static void getAdditionalParameters(KeytoolParameters param) {
        // TODO
    }
}
