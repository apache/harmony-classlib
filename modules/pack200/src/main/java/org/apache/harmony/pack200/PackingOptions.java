/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.harmony.pack200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.Attribute;

/**
 * Utility class to manage the various options available for pack200
 */
public class PackingOptions {

    public static final String STRIP = "strip";
    public static final String ERROR = "error";
    public static final String PASS = "pass";
    public static final String KEEP = "keep";

    // All options are initially set to their defaults
    private boolean repack = false;
    private boolean gzip = true;
    private boolean stripDebug = false;
    private boolean keepFileOrder = true;
    private long segmentLimit = 1000000;
    private int effort = 5;
    private String deflateHint = KEEP;
    private String modificationTime = KEEP;
    private List passFiles;
    private String unknownAttributeAction = PASS;
    private Map classAttributeActions;
    private Map fieldAttributeActions;
    private Map methodAttributeActions;
    private Map codeAttributeActions;
    private boolean verbose = false;
    private boolean quiet = true;
    private String logFile;

    private Attribute[] unknownAttributeTypes;

    public boolean isRepack() {
        return repack;
    }

    public void setRepack(boolean repack) {
        this.repack = repack;
    }

    public boolean isGzip() {
        return gzip;
    }

    public void setGzip(boolean gzip) {
        this.gzip = gzip;
    }

    public boolean isStripDebug() {
        return stripDebug;
    }

    /**
     * Set strip debug attributes. If true, all debug attributes (i.e.
     * LineNumberTable, SourceFile, LocalVariableTable and
     * LocalVariableTypeTable attributes) are stripped when reading the input
     * class files and not included in the output archive.
     *
     * @param stripDebug
     */
    public void setStripDebug(boolean stripDebug) {
        this.stripDebug = stripDebug;
    }

    public boolean isKeepFileOrder() {
        return keepFileOrder;
    }

    public void setKeepFileOrder(boolean keepFileOrder) {
        this.keepFileOrder = keepFileOrder;
    }

    public long getSegmentLimit() {
        return segmentLimit;
    }

    /**
     * Set the segment limit (equivalent to -S command line option)
     * @param segmentLimit - the limit in bytes
     */
    public void setSegmentLimit(long segmentLimit) {
        this.segmentLimit = segmentLimit;
    }

    public int getEffort() {
        return effort;
    }

    /**
     * Set the compression effort level (0-9, equivalent to -E command line option)
     * @param effort
     */
    public void setEffort(int effort) {
        this.effort = effort;
    }

    public String getDeflateHint() {
        return deflateHint;
    }

    public void setDeflateHint(String deflateHint) {
        this.deflateHint = deflateHint;
    }

    public String getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(String modificationTime) {
        this.modificationTime = modificationTime;
    }

    public boolean isPassFile(String passFileName) {
        if (passFiles != null) {
            for (Iterator iterator = passFiles.iterator(); iterator.hasNext();) {
                String pass = (String) iterator.next();
                if (passFileName.equals(pass)) {
                    return true;
                } else if (!pass.endsWith(".class")) { // a whole directory is
                    // passed
                    if (!pass.endsWith("/")) {
                        // Make sure we don't get any false positives (e.g.
                        // exclude "org/apache/harmony/pack" should not match
                        // files under "org/apache/harmony/pack200/")
                        pass = pass + "/";
                    }
                    return passFileName.startsWith(pass);
                }
            }
        }
        return false;
    }

    /**
     * Tell the compressor to pass the file with the given name, or if the name
     * is a directory name all files under that directory will be passed.
     *
     * @param passFileName
     *            the file name
     */
    public void addPassFile(String passFileName) {
        if(passFiles == null) {
            passFiles = new ArrayList();
        }
        passFileName.replaceAll(System.getProperty("file.separator"), "/");
        passFiles.add(passFileName);
    }

    public void removePassFile(String passFileName) {
        passFiles.remove(passFileName);
    }

    public String getUnknownAttributeAction() {
        return unknownAttributeAction;
    }

    /**
     * Tell the compressor what to do if an unknown attribute is encountered
     * @param unknownAttributeAction - the action to perform
     */
    public void setUnknownAttributeAction(String unknownAttributeAction) {
        this.unknownAttributeAction = unknownAttributeAction;
        if (!unknownAttributeAction.equals(PASS)
                && !unknownAttributeAction.equals(ERROR)
                && !unknownAttributeAction.equals(STRIP)) {
            throw new RuntimeException("Incorrect option for -U, "
                    + unknownAttributeAction);
        }
    }

    public Map getClassAttributeActions() {
        return classAttributeActions;
    }

    public void addClassAttributeAction(String attributeName, String action) {
        if(classAttributeActions == null) {
            classAttributeActions = new HashMap();
        }
        classAttributeActions.put(attributeName, action);
    }

    public Map getFieldAttributeActions() {
        return fieldAttributeActions;
    }

    public void addFieldAttributeAction(String attributeName, String action) {
        if(fieldAttributeActions == null) {
            fieldAttributeActions = new HashMap();
        }
        fieldAttributeActions.put(attributeName, action);
    }

    public Map getMethodAttributeActions() {
        return methodAttributeActions;
    }

    public void addMethodAttributeAction(String attributeName, String action) {
        if(methodAttributeActions == null) {
            methodAttributeActions = new HashMap();
        }
        methodAttributeActions.put(attributeName, action);
    }

    public Map getCodeAttributeActions() {
        return codeAttributeActions;
    }

    public void addCodeAttributeAction(String attributeName, String action) {
        if(codeAttributeActions == null) {
            codeAttributeActions = new HashMap();
        }
        codeAttributeActions.put(attributeName, action);
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public boolean isQuiet() {
        return quiet;
    }

    public void setQuiet(boolean quiet) {
        this.quiet = quiet;
    }

    public String getLogFile() {
        return logFile;
    }

    public void setLogFile(String logFile) {
        this.logFile = logFile;
    }

    public Attribute[] getUnknownAttributePrototypes() {
        if(unknownAttributeTypes == null) {
            List prototypes = new ArrayList();
            if(classAttributeActions != null) {
                for (Iterator iterator = classAttributeActions.keySet().iterator(); iterator
                        .hasNext();) {
                    String name = (String) iterator.next();
                    String action = (String) classAttributeActions.get(name);
                    if(!(action.equals(ERROR)
                            || action.equals(STRIP)
                            || action.equals(PASS))) {
                        NewAttribute prototype = new NewAttribute(name, action, AttributeDefinitionBands.CONTEXT_CLASS);
                        prototypes.add(prototype);
                    }
                }
            }
            if(methodAttributeActions != null) {
                for (Iterator iterator = methodAttributeActions.keySet().iterator(); iterator
                        .hasNext();) {
                    String name = (String) iterator.next();
                    String action = (String) methodAttributeActions.get(name);
                    if(!(action.equals(ERROR)
                            || action.equals(STRIP)
                            || action.equals(PASS))) {
                        boolean prototypeExists = false;
                        for (Iterator iterator2 = prototypes.iterator(); iterator2
                                .hasNext();) {
                            NewAttribute newAttr = (NewAttribute) iterator2.next();
                            if(newAttr.type.equals(name)) {
                                newAttr.addContext(AttributeDefinitionBands.CONTEXT_METHOD);
                                prototypeExists = true;
                                break;
                            }
                        }
                        if(!prototypeExists) {
                            NewAttribute prototype = new NewAttribute(name, action, AttributeDefinitionBands.CONTEXT_METHOD);
                            prototypes.add(prototype);
                        }
                    }
                }
            }
            if(fieldAttributeActions != null) {
                for (Iterator iterator = fieldAttributeActions.keySet().iterator(); iterator
                        .hasNext();) {
                    String name = (String) iterator.next();
                    String action = (String) fieldAttributeActions.get(name);
                    if(!(action.equals(ERROR)
                            || action.equals(STRIP)
                            || action.equals(PASS))) {
                        boolean prototypeExists = false;
                        for (Iterator iterator2 = prototypes.iterator(); iterator2
                                .hasNext();) {
                            NewAttribute newAttr = (NewAttribute) iterator2.next();
                            if(newAttr.type.equals(name)) {
                                newAttr.addContext(AttributeDefinitionBands.CONTEXT_FIELD);
                                prototypeExists = true;
                                break;
                            }
                        }
                        if(!prototypeExists) {
                            NewAttribute prototype = new NewAttribute(name, action, AttributeDefinitionBands.CONTEXT_FIELD);
                            prototypes.add(prototype);
                        }
                    }
                }
            }
            if(codeAttributeActions != null) {
                for (Iterator iterator = codeAttributeActions.keySet().iterator(); iterator
                        .hasNext();) {
                    String name = (String) iterator.next();
                    String action = (String) codeAttributeActions.get(name);
                    if(!(action.equals(ERROR)
                            || action.equals(STRIP)
                            || action.equals(PASS))) {
                        boolean prototypeExists = false;
                        for (Iterator iterator2 = prototypes.iterator(); iterator2
                                .hasNext();) {
                            NewAttribute newAttr = (NewAttribute) iterator2.next();
                            if(newAttr.type.equals(name)) {
                                newAttr.addContext(AttributeDefinitionBands.CONTEXT_CODE);
                                prototypeExists = true;
                                break;
                            }
                        }
                        if(!prototypeExists) {
                            NewAttribute prototype = new NewAttribute(name, action, AttributeDefinitionBands.CONTEXT_CODE);
                            prototypes.add(prototype);
                        }
                    }
                }
            }
            unknownAttributeTypes = new Attribute[prototypes.size()];
            for (int i = 0; i < unknownAttributeTypes.length; i++) {
                unknownAttributeTypes[i] = (Attribute) prototypes.get(i);
            }
        }
        return unknownAttributeTypes;
    }

    public String getUnknownClassAttributeAction(String type) {
        String action = (String) classAttributeActions.get(type);
        if(action == null) {
            action = unknownAttributeAction;
        }
        return action;
    }

    public String getUnknownMethodAttributeAction(String type) {
        String action = (String) methodAttributeActions.get(type);
        if(action == null) {
            action = unknownAttributeAction;
        }
        return action;
    }

    public String getUnknownFieldAttributeAction(String type) {
        String action = (String) fieldAttributeActions.get(type);
        if(action == null) {
            action = unknownAttributeAction;
        }
        return action;
    }

    public String getUnknownCodeAttributeAction(String type) {
        String action = (String) codeAttributeActions.get(type);
        if(action == null) {
            action = unknownAttributeAction;
        }
        return action;
    }

}
