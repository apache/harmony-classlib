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

package java.beans;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Vector;

import org.apache.harmony.beans.ObjectNode;
import org.apache.harmony.beans.internal.nls.Messages;

public class XMLEncoder extends Encoder {

    private OutputStream out;

    private Object owner;

    private final Vector<ObjectNode> printed = new Vector<ObjectNode>();

    public XMLEncoder(OutputStream out) {
        this.out = out;
        this.owner = null;
    }

    @Override
    public void writeObject(Object object) {
        super.writeObject(object);
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

    public Object getOwner() {
        return owner;
    }

    @Override
    public void writeStatement(Statement oldStm) {
        try {
            super.writeStatement(oldStm);
        } catch (NullPointerException ignore) {
            // ignore exception like RI does
            ignore.printStackTrace();
        }
    }

    public void flush() {
        writeAll();
    }

    public void close() {
        try {
            flush();
            out.close();
        } catch (Exception e) {
            getExceptionListener().exceptionThrown(e);
        }
    }

    private void writeAll() {
        Tag mainTag = new Tag("java"); //$NON-NLS-1$
        Enumeration<Object> e;

        printed.clear();
        NameMaker.clear();

        mainTag.addAttr("version", System.getProperty("java.version")); //$NON-NLS-1$ //$NON-NLS-2$
        mainTag.addAttr("class", "java.beans.XMLDecoder"); //$NON-NLS-1$ //$NON-NLS-2$

        printBytes(0, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); //$NON-NLS-1$
        printBytes(0, mainTag.toStringOnOpen());

        e = roots.elements();
        while (e.hasMoreElements()) {
            Object object = e.nextElement();

            if (object != null) {
                ObjectNode node = nodes.get(object);

                printObjectTag(0, object, node);
            } else {
                printNullTag(0);
            }
        }

        printBytes(0, mainTag.toStringOnClose());
    }

    // FIXME processing of default constructor: investigate
    private void printObjectTag(int tabCount, Object object, ObjectNode node) {
        Class<?> nodeType = null;

        try {
            nodeType = node.getObjectType();
        } catch (Exception e) {
            Exception e2 = new Exception(Messages.getString(
                    "beans.3B", node.getInitializer())); //$NON-NLS-1$

            e2.initCause(e);
            getExceptionListener().exceptionThrown(e2);
            return;
        }

        if (isPrimitive(nodeType) || isString(nodeType) || isClass(nodeType)) {
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
                tag = new Tag("array"); //$NON-NLS-1$
            } else {
                tag = new Tag("object"); //$NON-NLS-1$
            }

            // check if the object presents references
            if (isReferenced) {
                if (printed.contains(node)) {
                    String nodeId = node.getId();

                    if (nodeId != null) {
                        tag.addAttr("idref", node.getId()); //$NON-NLS-1$
                    }

                    objectPrinted = true;
                } else { // if(printed.contains(node) == false
                    try {
                        Class<?> type = node.getObjectType();

                        if (type != null) {
                            // check if it is necessary to assign
                            // and display *id* attribute to the object
                            String objectName = NameMaker.getInstanceName(type);

                            node.setId(objectName);
                            tag.addAttr("id", objectName); //$NON-NLS-1$
                        }
                    } catch (Exception e) {
                        getExceptionListener().exceptionThrown(e);
                    }
                }
            }

            if (!objectPrinted) {
                try {
                    if (isArray(nodeType)) {
                        tag.addAttr("class", ((Class) arguments[0]).getName()); //$NON-NLS-1$
                        tag.addAttr("length", ((Integer) arguments[1]) //$NON-NLS-1$
                                .toString());
                    } else {
                        tag.addAttr("class", node.getObjectType().getName()); //$NON-NLS-1$
                    }
                } catch (Exception e) {
                    getExceptionListener().exceptionThrown(e);
                }
            }

            // preprocessing is done, print it!
            if (objectPrinted) {
                // if object has already been printed then only print the
                // reference
                printBytes(tabCount, tag.toStringShortForm());
            } else if (isArray(nodeType) && !node.statements().hasNext()) {
                // if we have an empty array
                printBytes(tabCount, tag.toStringShortForm());
            } else if (arguments.length == 0 && !node.statements().hasNext()
                    && !node.expressions().hasNext()) {
                // if given tag has no children print the short form of the tag
                printBytes(tabCount, tag.toStringShortForm());
            } else {
                // the tag has not been printed and contains children,
                // let's print them

                printBytes(tabCount, tag.toStringOnOpen());

                printed.add(node);

                if (isArray(nodeType)) { // if array
                    Iterator<Statement> it = node.statements();

                    while (it.hasNext()) {
                        Statement s = it.next();

                        printVoidTag(++tabCount, s);
                        --tabCount;
                    }
                } else { // if object
                    Iterator<Expression> i1;
                    Iterator<Statement> i2;

                    for (Object element : arguments) {
                        if (element != null) {
                            ObjectNode succNode = nodes.get(element);

                            printObjectTag(++tabCount, element, succNode);
                        } else {
                            printNullTag(++tabCount);
                        }

                        --tabCount;
                    }

                    i1 = node.expressions();
                    while (i1.hasNext()) {
                        Expression e = i1.next();

                        printVoidTag(++tabCount, e);
                        --tabCount;
                    }

                    i2 = node.statements();
                    while (i2.hasNext()) {
                        Statement s = i2.next();

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
            Enumeration<ObjectNode> enumeration;
            Tag tag;
            String objectName;
            String methodName;
            ObjectNode node;
            Object[] args;

            exprValue = expr.getValue();

            // find out, if this object is already printed
            enumeration = printed.elements();
            while (enumeration.hasMoreElements()) {
                ObjectNode node2 = enumeration.nextElement();

                if (node2.getObjectValue() == exprValue) {
                    return;
                }
            }

            node = nodes.get(exprValue);

            // find out, if this object has no references to be printed
            // System.out.println("---- node.getReferencesNumber() = " +
            // node.getReferencesNumber());
            // System.out.println("---- node.getReferencedExpressionsNumber() =
            // " + node.getReferencedExpressionsNumber());

            if (node.getReferencesNumber() == 0) {
                return;
            }

            tag = new Tag("void"); //$NON-NLS-1$
            objectName = NameMaker.getInstanceName(exprValue.getClass());

            node.setId(objectName);
            tag.addAttr("id", objectName); //$NON-NLS-1$

            methodName = expr.getMethodName();
            args = expr.getArguments();

            if (methodName.startsWith("get") //$NON-NLS-1$
                    && (args.length == 0 || args.length == 1
                            && args[0].getClass() == Integer.class)
                    || methodName.startsWith("set") //$NON-NLS-1$
                    && (args.length == 1 || args.length == 2
                            && args[0].getClass() == Integer.class)) {
                String propertyName = methodName.substring(3);

                if (propertyName.length() > 0) {
                    tag.addAttr("property", Introspector //$NON-NLS-1$
                            .decapitalize(propertyName));
                }

                if (methodName.startsWith("get") && args.length == 1 //$NON-NLS-1$
                        || methodName.startsWith("set") && args.length == 2) { //$NON-NLS-1$
                    tag.addAttr("index", args[0].toString()); //$NON-NLS-1$
                }
            } else {
                tag.addAttr("method", expr.getMethodName()); //$NON-NLS-1$
            }

            printBytes(tabCount, tag.toStringOnOpen());

            for (int i = tag.hasAttr("index") ? 1 : 0; i < args.length; ++i) { //$NON-NLS-1$
                if (args[i] != null) {
                    ObjectNode node2 = nodes.get(args[i]);

                    printObjectTag(++tabCount, args[i], node2);
                } else {
                    printNullTag(++tabCount);
                }

                --tabCount;
            }

            printBytes(tabCount, tag.toStringOnClose());

            printed.add(node);
        } catch (Exception e) {
            // TODO - signal problem with expr.getValue()
        }

    }

    private void printVoidTag(int tabCount, Statement stat) {
        Tag tag = new Tag("void"); //$NON-NLS-1$

        String methodName = stat.getMethodName();
        Object[] args = stat.getArguments();

        if (methodName.startsWith("get") //$NON-NLS-1$
                && (args.length == 0 || args.length == 1
                        && args[0].getClass() == Integer.class)
                || methodName.startsWith("set") //$NON-NLS-1$
                && (args.length == 1 || args.length == 2
                        && args[0].getClass() == Integer.class)) {
            String propertyName = methodName.substring(3);

            if (propertyName.length() > 0) {
                tag.addAttr("property", Introspector //$NON-NLS-1$
                        .decapitalize(propertyName));
            }

            if (methodName.startsWith("get") && args.length == 1 //$NON-NLS-1$
                    || methodName.startsWith("set") && args.length == 2) { //$NON-NLS-1$
                tag.addAttr("index", args[0].toString()); //$NON-NLS-1$
            }
        } else {
            tag.addAttr("method", stat.getMethodName()); //$NON-NLS-1$
        }

        printBytes(tabCount, tag.toStringOnOpen());

        for (int i = tag.hasAttr("index") ? 1 : 0; i < args.length; ++i) { //$NON-NLS-1$
            if (args[i] != null) {
                ObjectNode node = nodes.get(args[i]);

                printObjectTag(++tabCount, args[i], node);
            } else {
                printNullTag(++tabCount);
            }

            --tabCount;
        }

        printBytes(tabCount, tag.toStringOnClose());
    }

    private void printNullTag(int tabCount) {
        printBytes(tabCount, "<null/>"); //$NON-NLS-1$
    }

    private void printBytes(int tabCount, String s) {
        try {
            String result = ""; //$NON-NLS-1$

            for (int i = 0; i < tabCount; ++i) {
                result += '\t';
            }
            result = result + s + "\n"; //$NON-NLS-1$
            out.write(result.getBytes("UTF-8")); //$NON-NLS-1$
        } catch (IOException ioe) {
            ExceptionListener listener = getExceptionListener();

            if (listener != null) {
                listener.exceptionThrown(ioe);
            }
        }
    }

    /**
     * Escapes '&', '<', '>', '\'', '"' chars.
     * 
     * @param input
     *            input string to be escaped
     * @return string with escaped characters
     */
    static String escapeChars(String input) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            switch (c) {
                case '&':
                    sb.append("&amp;"); //$NON-NLS-1$
                    break;
                case '<':
                    sb.append("&lt;"); //$NON-NLS-1$
                    break;
                case '>':
                    sb.append("&gt;"); //$NON-NLS-1$
                    break;
                case '\'':
                    sb.append("&apos;"); //$NON-NLS-1$
                    break;
                case '"':
                    sb.append("&quot;"); //$NON-NLS-1$
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

        LinkedHashMap<String, String> attrs;

        String characters;

        public Tag(String name) {
            this.name = name;
            this.attrs = new LinkedHashMap<String, String>();
            this.characters = null;
        }

        public Tag(String name, String characters) {
            this.name = name;
            this.attrs = new LinkedHashMap<String, String>();
            this.characters = characters;
        }

        public boolean hasAttr(String attrName) {
            return attrs.get(attrName) != null;
        }

        public void addAttr(String attrName, String attrValue) {
            attrs.put(attrName, attrValue);
        }

        public String toStringOnOpenUnfinished() {
            String result = "<" + name; //$NON-NLS-1$
            Iterator<String> i = attrs.keySet().iterator();

            while (i.hasNext()) {
                String attrName = i.next();
                String attrValue = attrs.get(attrName);

                result += " " + attrName + "=\"" + attrValue + "\""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            }
            return result;
        }

        public String toStringOnOpen() {
            return toStringOnOpenUnfinished() + ">"; //$NON-NLS-1$
        }

        public String toStringShortForm() {
            return toStringOnOpenUnfinished() + "/>"; //$NON-NLS-1$
        }

        public String toStringOnClose() {
            return "</" + name + ">"; //$NON-NLS-1$ //$NON-NLS-2$
        }

        public String toStringOnCharacters() {
            return XMLEncoder.escapeChars(characters);
        }

        @Override
        public String toString() {
            return toStringOnOpen() + toStringOnCharacters()
                    + toStringOnClose();
        }
    }

    static class NameMaker {

        private static final HashMap<String, Integer> numOfExemplars = new HashMap<String, Integer>();

        public static void clear() {
            numOfExemplars.clear();
        }

        private static String getCompName(Class<?> clz) {
            if (clz.isArray()) {
                return getCompName(clz.getComponentType()) + "Array"; //$NON-NLS-1$
            }
            return clz.getName().substring(clz.getName().lastIndexOf(".") + 1); //$NON-NLS-1$
        }

        public static String getInstanceName(Class<?> type) {
            String result = null;

            String fullName;
            String shortName;
            Integer iNum;

            if (type.isArray()) {
                fullName = getCompName(type);
                shortName = fullName;
            } else {
                fullName = type.getName();
                shortName = fullName.substring(fullName.lastIndexOf(".") + 1); //$NON-NLS-1$
            }
            iNum = numOfExemplars.get(shortName);
            if (iNum == null) {
                numOfExemplars.put(shortName, new Integer(0));
                result = shortName + "0"; //$NON-NLS-1$
            } else {
                int newValue = iNum.intValue() + 1;

                result = shortName + Integer.toString(newValue);
                numOfExemplars.put(shortName, new Integer(newValue));
            }
            return result;
        }
    }
}
