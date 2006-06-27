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
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.13.6.4 $
 */
package java.beans;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Vector;

import org.apache.harmony.beans.ObjectNode;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.13.6.4 $
 */

public class XMLEncoder extends Encoder {
    
    private OutputStream out;
    private Object owner;
    private Vector printed = new Vector();

    /**
     * @com.intel.drl.spec_ref
     */
    public XMLEncoder(OutputStream out) {
        this.out = out;
        this.owner = null;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void writeObject(Object object) {
        super.writeObject(object);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setOwner(Object owner) {
        this.owner = owner;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Object getOwner() {
        return owner;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void writeStatement(Statement oldStm) {
        super.writeStatement(oldStm);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void writeExpression(Expression oldExp) {
        super.writeExpression(oldExp);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void flush() {
        writeAll();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void close() {
        try {
            flush();
            out.close();
        } catch (Exception e) {
            getExceptionListener().exceptionThrown(e);
        }
    }
    
    private void writeAll() {
        printed.clear();
        NameMaker.clear();
        
        Tag mainTag = new Tag("java");
        mainTag.addAttr("version", System.getProperty("java.version"));
        mainTag.addAttr("class", "java.beans.XMLDecoder");

        printBytes(0, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        printBytes(0, mainTag.toStringOnOpen());
        
        Enumeration e = roots.elements();
        while(e.hasMoreElements()) {
            Object object = (Object) e.nextElement();
            if(object != null) {
                ObjectNode node = (ObjectNode) nodes.get(object);
                printObjectTag(0, object, node);
            } else {
                printNullTag(0);
            }
        }
        
        printBytes(0, mainTag.toStringOnClose());
    }
    
    private void printObjectTag(int tabCount, Object object, ObjectNode node) {
        Class nodeType = null;

        try {
            nodeType = node.getObjectType();
        } catch (Exception e) {
            getExceptionListener().exceptionThrown(e);
            getExceptionListener().exceptionThrown(new Exception(
                "skipping expression " + node.getInitializer() + "..."));
            return;
        }
        
        if(isPrimitive(nodeType) || isString(nodeType) || isClass(nodeType)) {
            String tagName = getPrimitiveName(nodeType);
            Object arg = node.getObjectArguments()[0];
            Tag tag = new Tag(tagName, arg.toString());

            printBytes(tabCount, tag.toString());
        } else { // if array or complex object
            Tag tag = null;
            Object[] arguments = node.getObjectArguments();
            boolean objectPrinted = false;
            boolean isReferenced = node.getReferencesNumber() > 0;
            
            if (isArray(nodeType)) {
                tag = new Tag("array");
            } else {
                tag = new Tag("object");
            }
            
            // check if the object presents references
            if(isReferenced) {
                if(printed.contains(node)) {
                    String nodeId = node.getId();
                    
                    if(nodeId != null) {
                        tag.addAttr("idref", node.getId());
                    }
                    
                    objectPrinted = true;
                } else { // if(printed.contains(node) == false
                    try {
                        Class type = node.getObjectType();
                        
                        if(type != null) {
                            // check if it is necessary to assign
                            // and display *id* attribute to the object
                            String objectName = NameMaker.getInstanceName(type);

                            node.setId(objectName);
                            tag.addAttr("id", objectName);
                        }
                    } catch (Exception e) {
                        getExceptionListener().exceptionThrown(e);
                    }
                }
            }
    
            if(!objectPrinted) {
                try {
                    if (isArray(nodeType)) {
                        tag.addAttr("class", ((Class) arguments[0]).getName());
                        tag.addAttr("length", ((Integer)
                                arguments[1]).toString());
                    } else {
                        tag.addAttr("class", node.getObjectType().getName());
                    }
                } catch (Exception e) {
                    getExceptionListener().exceptionThrown(e);
                }
            }

            // preprocessing is done, print it!
            if (objectPrinted) {
                // if object has been already printed then only print the
                // reference
                printBytes(tabCount, tag.toStringShortForm());
            }
            else if (isArray(nodeType) && !node.statements().hasNext()) {
                // if we have an empty array 
                printBytes(tabCount, tag.toStringShortForm());
            } 
            else if (arguments.length == 0  &&
                    !node.statements().hasNext() &&
                    !node.expressions().hasNext()) {
                // if given tag has no children print the short form of the tag
                printBytes(tabCount, tag.toStringShortForm());
            } 
            else {
                // the tag has not been printed and contains children,
                // let's print them  

                printBytes(tabCount, tag.toStringOnOpen());
                
                printed.add(node);
    
                if (isArray(nodeType)) { // if array
                    Iterator it = node.statements();

                    while(it.hasNext()) {
                        Statement s = (Statement) it.next();

                        printVoidTag(++tabCount, s);
                        --tabCount;
                    }
                } else { // if object
                    Iterator i1;
                    Iterator i2;
                    
                    for(int i = 0; i < arguments.length; ++i) {
                        if(arguments[i] != null) {
                            ObjectNode succNode = (ObjectNode) nodes.get(
                                    arguments[i]);

                            printObjectTag(++tabCount, arguments[i], succNode);
                        } else {
                            printNullTag(++tabCount);
                        }
                        
                        --tabCount;
                    }
                    
                    i1 = node.expressions();
                    while(i1.hasNext()) {
                        Expression e = (Expression) i1.next();
                        
                        printVoidTag(++tabCount, e);
                        --tabCount;
                    }
                    
                    i2 = node.statements();
                    while(i2.hasNext()) {
                        Statement s = (Statement) i2.next();
                        
                        printVoidTag(++tabCount, s);
                        --tabCount;
                    }
                } // if object
                
                printBytes(tabCount, tag.toStringOnClose());
            }
        } // if node is of non-trivial type
    }

    private void printVoidTag(int tabCount, Expression expr) {
        Object exprValue = null;
        try {
            exprValue = expr.getValue();
            
            // find out, if this object is already printed
            Enumeration enumeration = printed.elements();
            while(enumeration.hasMoreElements()) {
                ObjectNode node = (ObjectNode) enumeration.nextElement();
                if(node.getObjectValue() == exprValue) {
                    return;
                }
            }
            
            ObjectNode node = (ObjectNode) nodes.get(exprValue);
            
            // find out, if this object has no references to be printed
            // System.out.println("---- node.getReferencesNumber() = " + node.getReferencesNumber());
            // System.out.println("---- node.getReferencedExpressionsNumber() = " + node.getReferencedExpressionsNumber());
            
            if(node.getReferencesNumber() == 0) {
                return;
            }
            
            Tag tag = new Tag("void");
            
            String objectName = NameMaker.getInstanceName(exprValue.getClass());
            
            node.setId(objectName);
            tag.addAttr("id", objectName);
            
            String methodName = expr.getMethodName();
            Object[] args = expr.getArguments();
            if(methodName.startsWith("get")
                    && (args.length == 0 || args.length == 1
                            && args[0].getClass() == Integer.class)
                    || methodName.startsWith("set")
                    && (args.length == 1 || args.length == 2
                            && args[0].getClass() == Integer.class))
            {
                String propertyName = methodName.substring(3);
                if(propertyName.length() > 0) {
                    tag.addAttr("property", Introspector.decapitalize(
                            propertyName));
                }
                
                if(methodName.startsWith("get") && args.length == 1 ||
                    methodName.startsWith("set") && args.length == 2)
                {
                    tag.addAttr("index", args[0].toString());
                }
            } else {
                tag.addAttr("method", expr.getMethodName());
            }
            
            printBytes(tabCount, tag.toStringOnOpen());
            
            for(int i = tag.hasAttr("index") ? 1 : 0; i < args.length; ++i) {
                if(args[i] != null) {
                    ObjectNode node2 = (ObjectNode) nodes.get(args[i]);
                    printObjectTag(++tabCount, args[i], node2);
                } else {
                    printNullTag(++tabCount);
                }
                
                --tabCount;
            }

            printBytes(tabCount, tag.toStringOnClose());
            
            printed.add(node);
        } catch (Exception e) {
            //TODO - signal problem with expr.getValue()
        }
        
    }
    
    private void printVoidTag(int tabCount, Statement stat) {
        Tag tag = new Tag("void");
        
        String methodName = stat.getMethodName();
        Object[] args = stat.getArguments();
        if(methodName.startsWith("get")
                && (args.length == 0 || args.length == 1
                        && args[0].getClass() == Integer.class)
                || methodName.startsWith("set")
                && (args.length == 1 || args.length == 2
                        && args[0].getClass() == Integer.class))
        {
            String propertyName = methodName.substring(3);
            if(propertyName.length() > 0) {
                tag.addAttr("property", Introspector.decapitalize(
                        propertyName));
            }
            
            if(methodName.startsWith("get") && args.length == 1 ||
                methodName.startsWith("set") && args.length == 2)
            {
                tag.addAttr("index", args[0].toString());
            }
        } else {
            tag.addAttr("method", stat.getMethodName());
        }
        
        printBytes(tabCount, tag.toStringOnOpen());
        
        for(int i = tag.hasAttr("index") ? 1 : 0; i < args.length; ++i) {
            if(args[i] != null) {
                ObjectNode node = (ObjectNode) nodes.get(args[i]);
                printObjectTag(++tabCount, args[i], node);
            } else {
                printNullTag(++tabCount);
            }
            
            --tabCount;
        }

        printBytes(tabCount, tag.toStringOnClose());
    }
    
    private void printNullTag(int tabCount) {
        printBytes(tabCount, "<null/>");
    }
    
    private void printBytes(int tabCount, String s) {
        try {
            String result = "";

            for(int i = 0; i < tabCount; ++i) {
                result += '\t';
            }
            result = result + s + "\n";
            out.write(result.getBytes("UTF-8"));
        } catch (IOException ioe) {
            ExceptionListener listener = getExceptionListener();

            if(listener != null) {
                listener.exceptionThrown(ioe);
            }
        }
    }
  
    /**
     * Escapes '&', '<', '>', '\'', '"' chars.
     * @param input input string to be escaped
     * @return string with escaped characters 
     */
    static String escapeChars(String input) {
        StringBuffer sb = new StringBuffer();
        
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            switch (c) {
                case '&': 
                    sb.append("&amp;");
                    break;
                case '<': 
                    sb.append("&lt;");
                    break;
                case '>': 
                    sb.append("&gt;");
                    break;
                case '\'': 
                    sb.append("&apos;");
                    break;
                case '"':
                    sb.append("&quot;");
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }
    
    /**
     * This class is used by XMLEncoder to store XML tag information. 
     */
    static class Tag {
        String name;
        LinkedHashMap attrs;
        String characters;
        
        public Tag(String name) {
            this.name = name;
            this.attrs = new LinkedHashMap();
            this.characters = null;
        }
        
        public Tag(String name, String characters) {
            this.name = name;
            this.attrs = new LinkedHashMap();
            this.characters = characters;
        }
        
        public boolean hasAttr(String attrName) {
            return attrs.get(attrName) != null;
        }
        
        public void addAttr(String attrName, String attrValue) {
            attrs.put(attrName, attrValue);
        }
        
        public String toStringOnOpenUnfinished() {
            String result = "<" + name;
            Iterator i = attrs.keySet().iterator();

            while(i.hasNext()) {
                String attrName = (String) i.next();
                String attrValue = (String) attrs.get(attrName);

                result +=" " + attrName + "=\"" + attrValue + "\"";
            }
            return result;
        }

        public String toStringOnOpen() {
            return toStringOnOpenUnfinished() + ">";
        }
        
        public String toStringShortForm() {
            return toStringOnOpenUnfinished() + "/>";
        }
        
        public String toStringOnClose() {
            return "</" + name + ">";
        }
        
        public String toStringOnCharacters() {
            return XMLEncoder.escapeChars(characters);
        }
        
        public String toString() {
            return toStringOnOpen() + toStringOnCharacters()
                    + toStringOnClose();
        }
    }


    static class NameMaker {
        private static HashMap numOfExemplars = new HashMap();
        
        public static void clear() {
            numOfExemplars.clear();
        }
        
        private static String getCompName(Class clz) {
            if (clz.isArray()) {
                return getCompName(clz.getComponentType()) + "Array";
            } else {
                return clz.getName().substring(
                        clz.getName().lastIndexOf(".") + 1);
            }
        }
        
        public static String getInstanceName(Class type) {
            String result = null;

            String fullName;
            String shortName;
            Integer iNum;

            if (type.isArray()) {
                fullName = getCompName(type);
                shortName = fullName;
            } else {
                fullName = type.getName();
                shortName = fullName.substring(fullName.lastIndexOf(".") + 1);
            }
            iNum = (Integer) numOfExemplars.get(shortName);
            if(iNum == null) {
                numOfExemplars.put(shortName, new Integer(0));
                result = shortName + "0"; 
            } else {
                int newValue = iNum.intValue() + 1;

                result = shortName + Integer.toString(newValue);
                numOfExemplars.put(shortName, new Integer(newValue));
            }
            return result;
        }
    }
}


