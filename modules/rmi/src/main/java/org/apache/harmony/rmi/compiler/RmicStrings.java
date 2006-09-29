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
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author  Vasily Zakharov
 * @version $Revision: 1.1.2.1 $
 */
package org.apache.harmony.rmi.compiler;


/**
 * This interface contains command line options and other textual constants
 * for RMI Compiler.
 *
 * @author  Vasily Zakharov
 * @version $Revision: 1.1.2.1 $
 */
interface RmicStrings extends RmicConstants {

    /**
     * Options prefix character.
     */
    String optionPrefix = "-";

    /**
     * <code>-v1.1</code> option.
     */
    String optionV11 = optionPrefix + "v1.1";

    /**
     * <code>-v1.2</code> option.
     */
    String optionV12 = optionPrefix + "v1.2";

    /**
     * <code>-vcompat</code> option.
     */
    String optionVCompat = optionPrefix + "vcompat";

    /**
     * <code>-idl</code> option.
     */
    String optionIDL = optionPrefix + "idl";

    /**
     * <code>-iiop</code> option.
     */
    String optionIIOP = optionPrefix + "iiop";

    /**
     * <code>-source</code> option for Java Compiler.
     */
    String optionSource = optionPrefix + "source";

    /**
     * <code>-target</code> option.
     */
    String optionTarget = optionPrefix + "target";

    /**
     * <code>-keep</code> option.
     */
    String optionKeep = optionPrefix + "keep";

    /**
     * <code>-keepgenerated</code> option.
     */
    String optionKeepGenerated = optionPrefix + "keepgenerated";

    /**
     * <code>-always</code> option.
     */
    String optionAlways = optionPrefix + "always";

    /**
     * <code>-alwaysgenerate</code> option.
     */
    String optionAlwaysGenerate = optionPrefix + "alwaysgenerate";

    /**
     * <code>-factory</code> option.
     */
    String optionFactory = optionPrefix + "factory";

    /**
     * <code>-noValueMethods</code> option.
     */
    String optionNoValueMethods = optionPrefix + "noValueMethods";

    /**
     * <code>-nolocalstubs</code> option.
     */
    String optionNoLocalStubs = optionPrefix + "nolocalstubs";

    /**
     * <code>-poa</code> option.
     */
    String optionPOA = optionPrefix + "poa";

    /**
     * <code>-g</code> option.
     */
    String optionDebug = optionPrefix + 'g';

    /**
     * <code>-g:</code> option.
     */
    String optionDebugDetails = optionDebug + ':';

    /**
     * <code>-nowarn</code> option.
     */
    String optionNoWarnings = optionPrefix + "nowarn";

    /**
     * <code>-nowrite</code> option.
     */
    String optionNoWrite = optionPrefix + "nowrite";

    /**
     * <code>-verbose</code> option.
     */
    String optionVerbose = optionPrefix + "verbose";

    /**
     * <code>-depend</code> option.
     */
    String optionDepend = optionPrefix + "depend";

    /**
     * <code>-idlModule</code> option.
     */
    String optionIdlModule = optionPrefix + "idlModule";

    /**
     * <code>-idlFile</code> option.
     */
    String optionIdlFile = optionPrefix + "idlFile";

    /**
     * <code>-d</code> option.
     */
    String optionDestinationDir = optionPrefix + 'd';

    /**
     * <code>-classpath</code> option.
     */
    String optionClassPath = optionPrefix + "classpath";

    /**
     * <code>-cp</code> option.
     */
    String optionCP = optionPrefix + "cp";

    /**
     * <code>-bootclasspath</code> option.
     */
    String optionBootClassPath = optionPrefix + "bootclasspath";

    /**
     * <code>-extdirs</code> option.
     */
    String optionExtDirs = optionPrefix + "extdirs";

    /**
     * <code>-J</code> option.
     */
    String optionJava = optionPrefix + 'J';

    /**
     * <code>-X</code> option.
     */
    String optionX = optionPrefix + 'X';

    /**
     * RMI Compiler usage text.
     */
    String usageText = "Usage: rmic <options> <class names>" + EOLN
            + EOLN + "Options:" + EOLN + "  " + optionV11 + "              "
            + "Create stubs/skeletons for 1.1 stub protocol version only"
            + EOLN + "  " + optionV12 + "              "
            + "(default) Create stubs for 1.2 stub protocol version only"
            + EOLN + "  " + optionVCompat + "           "
            + "Create stubs/skeletons compatible with both v1.1 and v1.2"
            + EOLN + EOLN + "  " + optionTarget + " <version>  "
            + "Generate class files for the specified VM version"
            + EOLN + EOLN + "  " + optionKeep + "              "
            + "Do not delete generated source files" + EOLN + "  "
            + optionKeepGenerated + "     (the same as \"" + optionKeep + "\")"
            + EOLN + EOLN + "  " + optionDebug
            + "                 Generate debug information" + EOLN + "  "
            + optionNoWarnings + "            Do not notify about warnings"
            + EOLN + "  " + optionNoWrite + "           "
            + "Check run: do not write compiled classes" + EOLN + "  "
            + optionVerbose + "           Print detailed compilation log"
            + EOLN + EOLN + "  " + optionDestinationDir
            + " <directory>         Target directory for generated files"
            + EOLN + "  " + optionClassPath
            + " <path>      Input class files location"
            + EOLN + "  " + optionCP + " <path>             (the same as \""
            + optionClassPath + "\")" + EOLN + "  " + optionBootClassPath
            + " <path>  Override location of bootstrap class files"
            + EOLN + "  " + optionExtDirs
            + " <dirs>        Override location of installed extensions"
            + EOLN + EOLN + "  " + optionJava
            + "<JVM option>         Pass option to JVM"
            + EOLN + "  " + optionX
            + "<extended option>    Pass -X option to JVM";

    /**
     * Version error text.
     */
    String errorVersionText = "You should specify at most one of \""
            + optionV11 + "\", \"" + optionV12 + "\" (default), \""
            + optionVCompat + "\", \"" + optionIDL + "\", \"" + optionIIOP;

    /**
     * No option parameter error text.
     */
    String errorNeedParameterText = "Option %s requires a parameter";

    /**
     * No JVM option parameter error text.
     */
    String errorNeedJVMParameterText = "Option %s must be immediately "
                                 + "(without a space) followed by a JVM option";

    /**
     * Need two parameters error text.
     */
    String errorNeedTwoParametersText = "Option %s requires two parameters";

    /**
     * Unknown option error text.
     */
    String errorUnknownOptionText = "Unknown option: %s";

    /**
     * No classes to compile error text.
     */
    String errorNoClassesText = "No classes to compile specified";

    /**
     * Unusable IDL/IIOP option error text.
     */
    String errorUnusableExceptIDL_IIOP = "Option %s must only be used with "
                                         + optionIDL + " or " + optionIIOP;

    /**
     * Unusable IDL option error text.
     */
    String errorUnusableExceptIDL = "Option %s must only be used with "
                                    + optionIDL;

    /**
     * Unusable IIOP option error text.
     */
    String errorUnusableExceptIIOP = "Option %s must only be used with "
                                     + optionIIOP;

    /**
     * Warning about classpath.
     */
    String warningClassPathText = "%s is specified. For proper operation "
            + "the same %s should be specified in VM arguments. "
            + "This is a limitation of current RMIC implementation.";
}
