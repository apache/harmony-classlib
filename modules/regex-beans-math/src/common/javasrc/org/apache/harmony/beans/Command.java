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
 * @version $Revision: 1.1.2.1 $
 */
package org.apache.harmony.beans;

import java.beans.BeanInfo;
import java.beans.IndexedPropertyDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.Statement;
import java.beans.XMLDecoder;
import java.beans.Expression;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.xml.sax.Attributes;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.1.2.1 $
 */

public class Command {
    
    private static int INITIALIZED = 0;
    private static int CHILDREN_FILTERED = 1;
    private static int COMMAND_EXECUTED = 2;
    private static int CHILDREN_PROCESSED = 3;
    
    private String tagName; // tag name
    private HashMap attrs; // set of attrs
    private String data = null; // string data
    private Vector commands = new Vector(); // inner commands
    private Vector arguments = new Vector(); // arguments
    private Vector operations = new Vector(); // operations
    private Vector auxArguments = new Vector();  // additonal arguments placed before others
    private Argument result = null; // result argument
    private Object target = null; // target to execute a command on
    private String methodName = null; // method name
    private Command ctx = null; // context for command
    private int status; // commands
    private int tabCount = 0;
    private XMLDecoder decoder;
    
    public Command(String tagName, HashMap attrs) {
        this.tagName = tagName;
        this.attrs = attrs;
        this.status = initializeStatus(tagName);
    }
    
    public Command(XMLDecoder decoder, String tagName, HashMap attrs) {
        this.decoder = decoder;
        this.tagName = tagName;
        this.attrs = attrs;
        this.status = initializeStatus(tagName);
    }

    // set data for command
    public void setData(String data) {
        this.data = data;
    }
    
    // set tab count to display log messages
    public void setTabCount(int tabCount) {
        this.tabCount = tabCount;
    }
    
    // set context - upper level command
    public void setContext(Command ctx) {
        this.ctx = ctx;
    }
    
    // add child command
    public void addChild(Command cmd) {
        if(cmd != null) {
            cmd.setContext(this);
            commands.add(cmd);
        }
    }
    
    // remove child command
    public void removeChild(Command cmd) {
         if((cmd != null) && commands.remove(cmd))
            cmd.setContext(null);
    }
    
    // command status
    public int getStatus() {
        return status;
    }
    
    // check if one of arguments or operations is unresolved
    private boolean isResolved() {
        if(getStatus() < Command.CHILDREN_PROCESSED) {
            return false;
        } else {
            for(int i = 0; i < arguments.size(); ++i) {
                Command arg = (Command) arguments.elementAt(i);
                if(!arg.isResolved()) return false;
            }
            for(int j = 0; j < operations.size(); ++j) {
                Command opr = (Command) operations.elementAt(j);
                if(!opr.isResolved()) return false;
            }
            return true;
        }
    }
    
    // execute command and return execution flags
    public int exec(HashMap references) throws Exception {
        //System.out.println("in exec() status = " + translateStatus(status) + "...");
        if(status < Command.CHILDREN_PROCESSED) {
            if(status < Command.COMMAND_EXECUTED) {
                if(status < Command.CHILDREN_FILTERED) {
                    status = doBeforeRun(references);
                    //System.out.println("after doBeforeRun() status = " + translateStatus(status));
                }
                if(status == Command.CHILDREN_FILTERED) {
                    status = doRun(references);
                    //System.out.println("after doRun() status = " + translateStatus(status));
                }
            }
            if(status == Command.COMMAND_EXECUTED) {
                status = doAfterRun(references);
                //System.out.println("after doAfterRun() status = " + translateStatus(status));
            }
        }
        //System.out.println("...out of exec() status = " + translateStatus(status));
        return status;
    }
    
    // execute commands in backtrack order
    public boolean backtrack(HashMap references) throws Exception {
        for(int i = 0; i < arguments.size(); ++i) {
            Command arg = (Command) arguments.elementAt(i);
            arg.backtrack(references);
        }
        for(int i = 0; i < operations.size(); ++i) {
            Command opr = (Command) operations.elementAt(i);
            opr.backtrack(references);
        }
        if(status == Command.CHILDREN_FILTERED) {
            status = doRun(references);
        }
        if(status == Command.COMMAND_EXECUTED) {
            status = doAfterRun(references);
            return (getStatus() == Command.CHILDREN_PROCESSED);
        } else {
            return false;
        }
    }
    
    // put command in one of two collections - arguments ot operations
    private int doBeforeRun(HashMap references) throws Exception {
        if(status == Command.INITIALIZED) {
            for(int i = 0; i < commands.size(); ++i) {
                Command cmd = (Command) commands.elementAt(i);
                Vector dest = operations;
                if(cmd.isExecutable()) {
                    dest = arguments;
                }
                dest.add(cmd);
            }
            return Command.CHILDREN_FILTERED;
        } else {
            return status;
        }
    }
    
    // run command
    private int doRun(HashMap references) throws Exception {
        if(status == Command.CHILDREN_FILTERED) {
            if(isRoot()) {
                 result = new Argument(decoder);
                 //System.out.println("doRun(): result is decoder...");
                 return Command.COMMAND_EXECUTED;
            }
            
            if(isNull()) {
                result = new Argument(null);
                //System.out.println("doRun(): result is null...");
                return Command.COMMAND_EXECUTED;
            }
            
            if(ctx != null && ctx.isArray() && (ctx.getResultValue() == null)
                    && !isExecutable()) {
                //System.out.println("doRun(): context is array...");
                return status;
            }
                
            Object target = getTarget(references);
            if(target == null) {
                //System.out.println("doRun(): target is null...");
                return status;
            } else {
                if(target instanceof Class) {
                    //System.out.println("doRun(): target = " + ((Class)target).getName());
                } else {
                    //System.out.println("doRun(): target = " + target.getClass().getName());
                }
                if(isReference()) {
                    result = getReferencedArgument(references);
                    //System.out.println("doRun(): reference - result is " + result.getType());
                } else {
                    String methodName = getMethodName(references);
                    //System.out.println("doRun(): methodName = " + methodName);
                    Argument[] arguments = getArguments();
                    if(arguments == null) return status;
                    //System.out.println("doRun(): args number is " + arguments.length);
                    for(int i = 0; i < arguments.length; ++i) {
                        if(arguments[i] != null) {
                            //System.out.println("doRun(): arg [" + i + "] = " + arguments[i].getType());
                        } else {
                            //System.out.println("doRun(): arg [" + i + "] = null");
                        }
                    }
                    
                    Expression expr = new Expression(target, methodName,
                            getArgumentsValues());
                    result = new Argument(expr.getValue());
                    
                    if(isPrimitiveClassName(getTagName()))
                        result.setType(getPrimitiveClass(tagName));
                    
                    //System.out.println("doRun(): method call - result is " + result.getType());
                }
                return Command.COMMAND_EXECUTED;
            }
        } else return status;
    }
    
    // run child commands
    private int doAfterRun(HashMap references) throws Exception {
        if(status == Command.COMMAND_EXECUTED) {
            //System.out.println("doAfterRun(): command " + getResultType() + " processed...");            
            Vector toBeRemoved = new Vector();
            try {
                Statement[] statements = null;
                
                for(int i = 0; i < operations.size(); ++i) {
                    Command cmd = (Command) operations.elementAt(i);
                    
                    if(cmd.isArrayElement()) {
                        
                        if(cmd.isResolved()) {
                            if(statements == null) {
                                statements = new Statement[operations.size()];
                            }
                            statements[i] = new Statement(getResultValue(),
                                    "set", new Object[] {
                                        new Integer(i), cmd.getResultValue()} );
                            if( (i + 1) == operations.size() ) {
                                for(int j = 0; j < operations.size(); ++j) {
                                    statements[j].execute();
                                }
                                toBeRemoved.addAll(operations);
                            }
                        } else {
                            break;
                        }
                        
                    } else {
                        // since the call is Array.set()
                        if(!isArray()) {
                            cmd.setTarget(getResultValue());
                        }
                        cmd.exec(references);
                        
                        if(cmd.isResolved()) {
                            //System.out.println("doAfterRun(): cmd = " + cmd.methodName + " is resolved");
                            toBeRemoved.add(cmd);
                        } else {
                            //System.out.println("doAfterRun(): cmd = " + cmd.methodName + " is unresolved");
                            break;
                        }
                        
                    }
                    
                }
            } catch (Exception e) {
                throw new Exception(e);
            } finally {
                operations.removeAll(toBeRemoved);
            }
            
            if(operations.size() == 0) {
                //System.out.println("doAfterRun(): command " + getResultType() + " completely processed.");
            } else {
                //System.out.println("doAfterRun(): command " + getResultType() + " contains incomplete " + 
                    //operations.size() + " commands.");
            }
            return (operations.size() == 0) ? Command.CHILDREN_PROCESSED
                    : status;
        } else return status;
    }
    
    // Result accessors
    
    // Return result - Argument class
    public Argument getResult() {
        return result;
    }
    
    // Returns result value
    public Object getResultValue() {
        return (result != null) ? result.getValue() : null;
    }
    
    // Returns result type
    public Class getResultType() throws ClassNotFoundException {
        return (result != null) ? result.getType() : null;
    }
    
    // accessors to XML tags and attrs
    public boolean hasAttr(String name) {
        return attrs.get(name) != null;
    }

    public String getAttr(String name) {
        return (String) attrs.get(name);
    }

    public boolean isTag(String name) {
        return tagName.equals(name);
    }

    public boolean hasAttr(String name, String value) {
        return value.equals(attrs.get(name));
    }

    public String getTagName() {
        return tagName;
    }

    // Checks if the object is primitive - int, float, etc...
    private boolean isPrimitive() {
        return isPrimitiveClassName(tagName);
    }
    
    // Checks if the object is constructor
    private boolean isConstructor() {
        return isPrimitive() || !isStaticMethod() && !isMethod()
                && !isProperty() && !isField() && !isArray() && !isReference();
    }
    
    // Checks if the command is static method
    private boolean isStaticMethod() {
        return isTag("object") && hasAttr("method") || isTag("class");
    }
    
    // Checks if the command is public method
    private boolean isMethod() {
        return isTag("void") && (hasAttr("method") || hasAttr("index"));
    }
    
    // Check if the command relates to property - getter ot setter depends on
    // number of args
    private boolean isProperty() {
        return isTag("void") && hasAttr("property");
    }
    
    // Check if the command is field accessor
    private boolean isField() {
        return isTag("object") && hasAttr("field");
    }
    
    // Check if the command is array
    private boolean isArray() {
        return isTag("array");
    }
    
    // Check if the command is array element
    private boolean isArrayElement() {
        return (ctx != null) && (ctx.isArray()) && isExecutable();
    }
    
    private boolean isReference() {
        return hasAttr("idref");
    }
    
    // Check if the command is root object
    private boolean isRoot() {
        return isTag("java");
    }
    
    // Check if the command is null
    private boolean isNull() {
        return isTag("null");
    }
    
    // Checks if the command could generate object
    public boolean isExecutable() {
        boolean result = isTag("object") || isTag("void")
                && hasAttr("class") && hasAttr("method")
                || isTag("array") || isPrimitive()
                || isTag("class") || isTag("null");
        return result;
    }
    
    private Argument getReferencedArgument(HashMap references) {
        return ((Command) references.get(getAttr("idref"))).getResult();
    }
    
    // get a target through tag and attrs analysis
    private Object getTarget(HashMap references) throws Exception {
        if(target == null){
            if(isReference()) {
                Command cmd = (Command) references.get(getAttr("idref"));
                target = (cmd != null) ? cmd.getResultValue() : null;
            } else if(isExecutable()) {
                String className = null;
                
                if(isPrimitive()) {
                    className = getPrimitiveClassName(tagName);
                } else if(isTag("class")) {
                    className = getPrimitiveClassName(tagName);
                } else if(isConstructor() || isStaticMethod() || isField()) {
                    className = getAttr("class");
                } else if(isArray()) {
                    className = getAttr("class");
                    Class componentType = isPrimitiveClassName(className)
                            ? getPrimitiveClass(className)
                            : Class.forName(className, true,
                                Thread.currentThread().getContextClassLoader());
                    className = Array.newInstance(
                            componentType, 0).getClass().getName();
                }
                
                if(className != null) {
                    target = Class.forName(className, true,
                        Thread.currentThread().getContextClassLoader());
                    
                    if(isField()) {
                        String fieldName = getAttr("field");
                        target = ((Class) target).getField(fieldName);
                    }
                } else {
                    throw new Exception("target is not generated: classname "
                            + className + " is not found");
                }
            } else if(ctx.isArray()) {
                //target = ctx.getTarget(references);
                target = Class.forName("java.lang.reflect.Array");
            }
        }
        return target;
    }
    
    // set target to execute command on
    private void setTarget(Object target) {
        this.target = target;
    }
    
    // Return a method name of command
    private String getMethodName(HashMap references)
        throws NoSuchMethodException, IntrospectionException, Exception
    {
        if(methodName == null) {
            String methodValue = null;
            if(isTag("class")) {
                addArgument(new Argument(String.class, data), 0);
                methodValue = "forName";
            } else if(isPrimitive()) {
                if(isTag("char")) {
                    if(data.length() != 1) {
                        throw new IntrospectionException("Cannot convert" + data
                                + " to char");
                    } else {
                        addArgument(new Argument(char.class, new Character(
                                data.charAt(0))), 0);
                    }
                } else {
                    addArgument(new Argument(String.class, data), 0);
                }
                methodValue = "new";
            } else if(isConstructor() || hasAttr("method", "new")) {
                methodValue = "new";
            } else if(isArray()) {
                methodValue = "new";
                int length = hasAttr("length") ? Integer.parseInt(
                        getAttr("length")) : getArgumentsNumber();
                copyArgumentsToCommands();
                addArgument(new Argument(int.class, new Integer(length)), 0);
            } else if(isTag("class")) {
                methodValue = "forName";
            } else if(hasAttr("property")) {
                String propertyValue = getAttr("property");
                if(hasAttr("index")) {
                    addArgument(new Argument(int.class, new Integer(
                            getAttr("index"))), 0);
                }
                
                BeanInfo beanInfo = Introspector.getBeanInfo(getTarget(
                        references).getClass());
                PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
                
                boolean methodFound = false;
                Method method = null;
                for(int i = 0; i < pds.length; ++i) {
                    PropertyDescriptor pd = pds[i];
                    if(propertyValue.equals(pd.getName())) {
                        int argsNum = getArgumentsNumber();
                        if(hasAttr("index")) {
                            IndexedPropertyDescriptor ipd =
                                    (IndexedPropertyDescriptor) pd;
                            if(argsNum == 1) {
                                method = ipd.getIndexedReadMethod();
                            } else if(argsNum == 0) {
                                method = ipd.getReadMethod();
                            }
                        } else {
                            method = pd.getReadMethod();
                        }
                        
                        if(method != null)
                            methodFound = matchMethodParams(method, references);
                        
                        if(methodFound == false) {
                            if(hasAttr("index")) {
                                IndexedPropertyDescriptor ipd =
                                        (IndexedPropertyDescriptor) pd;
                                if (argsNum == 2) {
                                    method = ipd.getIndexedWriteMethod();
                                } else if(argsNum == 1) {
                                    method = ipd.getWriteMethod();
                                }
                            } else {
                                method = pd.getWriteMethod();
                            }
                        }
                        
                        if(method != null)
                            methodFound = matchMethodParams(method, references);
                    }
                }
                
                if(method == null) {
                    throw new NoSuchMethodException("for property "
                            + propertyValue + " no getter(setter) is found");
                } else {
                    methodValue = method.getName();
                }
            } else if(hasAttr("method")) {
                if(hasAttr("index"))
                    addArgument(new Argument(int.class, Integer.valueOf(
                            getAttr("index"))), 0);
                methodValue = getAttr("method");
            } else if(hasAttr("index")) {
                addArgument(new Argument(int.class, Integer.valueOf(
                        getAttr("index"))), 0);
                methodValue = getArgumentsNumber() > 1 ? "set" : "get";
                if(ctx.isArray()) {
                    addArgument(ctx.getResult(), 0);
                }
            } else if(hasAttr("field")) {
                addArgument(
                    new Argument(
                        Class.forName(
                            getAttr("class"), true,
                            Thread.currentThread().getContextClassLoader()
                        )
                    ),
                    0
                );
                
                methodValue = "get";
            } else {
                throw new Exception(
                        "method name is not generated: error in "
                        + "getMethodName()");
            }
            methodName = methodValue;
        }
        return methodName;
    }
    
    // return a list of arguments as of Argument type
    private Argument[] getArguments() {
        Argument[] args = new Argument[auxArguments.size() + arguments.size()];
        for(int i = 0; i < auxArguments.size(); ++i)
            args[i] = (Argument) auxArguments.elementAt(i);
        for(int j = 0; j < arguments.size(); ++j) {
            Command cmd = (Command) arguments.elementAt(j);
            if(cmd.getStatus() >= Command.COMMAND_EXECUTED) {
                args[auxArguments.size() + j] = cmd.getResult();
            } else {
                //System.out.println("arg: " + cmd.getResultValue());
                args = null; break;
            }
        }
        return args;
    }
    
    // return argument values
    private Object[] getArgumentsValues() {
        Argument[] args = getArguments();
        Object[] result = new Object[args.length];
        for(int i = 0; i < args.length; ++i)
            result[i] = args[i].getValue();
        return result;
    }
    
    // copy arguments to treat as commands
    private void copyArgumentsToCommands() {
        Iterator i = arguments.iterator();
        while(i.hasNext()) {
            Command cmd = (Command) i.next();
            cmd.status = Command.CHILDREN_FILTERED;
            operations.add(cmd);
        }
        arguments.clear();
    }
    
    // return number of arguments
    private int getArgumentsNumber() {
        return auxArguments.size() + arguments.size();
    }
    
    // return number of commands
    private int getOperationsNumber() {
        return operations.size();
    }
    
    // add argument to the beginning of arguments
    private void addArgument(Argument argument, int idx) {
        auxArguments.insertElementAt(argument, idx);
    }
    
    // Check if the name of class is primitive
    public static boolean isPrimitiveClassName(String className) {
        return className.equalsIgnoreCase("boolean")
                || className.equalsIgnoreCase("byte")
                || className.equalsIgnoreCase("char")
                || className.equalsIgnoreCase("short")
                || className.equalsIgnoreCase("int")
                || className.equalsIgnoreCase("long")
                || className.equalsIgnoreCase("float")
                || className.equalsIgnoreCase("double")
                || className.equalsIgnoreCase("string");
    }
    
    // Transforms a primitive class name
    private String getPrimitiveClassName(String data) {
        String shortClassName = null;
        if(data.equals("int")) {
            shortClassName = "Integer";
        } else if (data.equals("char")) {
            shortClassName = "Character";
        } else {
            shortClassName = data.substring(0,1).toUpperCase()
                    + data.substring(1, data.length());
        }
        return "java.lang." + shortClassName;
    }
    
    public static Class getPrimitiveClass(String className) {
        Class result = null;
        if(className.equals("boolean")) {
            result = boolean.class;
        } else if(className.equals("byte")) {
            result = byte.class;
        } else if(className.equals("char")) {
            result = char.class;
        } else if(className.equals("short")) {
            result = short.class;
        } else if(className.equals("int")) {
            result = int.class;
        } else if(className.equals("long")) {
            result = long.class;
        } else if(className.equals("float")) {
            result = float.class;
        } else if(className.equals("double")) {
            result = double.class;
        } else if(className.equals("string")) {
            result = String.class;
        }
        return result;
    }
    
    private boolean matchMethodParams(Method method, HashMap references) {
        Class[] paramTypes = method.getParameterTypes();
        Argument[] args = getArguments();
        if(args == null) return false;
        boolean result = true;
        if(paramTypes.length == args.length) {
            for(int j = 0; j < paramTypes.length; ++j) {
                //System.out.println("paramTypes[j] = " + paramTypes[j]);
                //System.out.println("args[j] = " + args[j].getType());
                
                boolean isAssignable = (args[j].getType() == null)
                        ? !paramTypes[j].isPrimitive()
                        : paramTypes[j].isAssignableFrom(args[j].getType());
                
                //System.out.println("args[j] = " + args[j].getType());
                
                if(!isAssignable) {
                    result = false;
                    break;
                }
            }
        } else result = false;
        return result;
    }
    
    public static HashMap parseAttrs(String tagName, Attributes attrs) {
        HashMap result = new HashMap();
        if(tagName.equals("object")) {
            for(int i = 0; i < objectAttrNames.length; ++i) {
                String name = objectAttrNames[i];
                String value = attrs.getValue(name);
                if(value != null) result.put(name, value);
            }
        } else if(tagName.equals("void")) {
            for(int i = 0; i < voidAttrNames.length; ++i) {
                String name = voidAttrNames[i];
                String value = attrs.getValue(name);
                if(value != null) result.put(name, value);
            }
        } else if(tagName.equals("array")) {
            for(int i = 0; i < arrayAttrNames.length; ++i) {
                String name = arrayAttrNames[i];
                String value = attrs.getValue(name);
                if(value != null) result.put(name, value);
            }
        } else if(tagName.equals("java")) {
            for(int i = 0; i < javaAttrNames.length; ++i) {
                String name = javaAttrNames[i];
                String value = attrs.getValue(name);
                if(value != null) result.put(name, value);
            }
        }
        return result;
    }
    
    // Auxiliary logging with tabs functions
    public static void pr(String msg) { 
        //System.out.print(msg);
    }
    public static void pr(int tabCount, String msg) {
        String result = "";
        for(int i = 0; i < tabCount; ++i) result += '\t';
        result += msg;
        //System.out.print(result);
    }
    public static void prn(String msg) {
        //System.out.println(msg);
    }
    public static void prn(int tabCount, String msg) {
        String result = "";
        for(int i = 0; i < tabCount; ++i) result += '\t';
        result += msg;
        //System.out.println(result);
    }
    
    public static void printAttrs(
            int tabCount, String tagName, Attributes attrs) {
        pr(tabCount, tabCount + ">in <" + tagName);
        for(int i = 0; i < attrs.getLength(); ++i) {
            String attrName = attrs.getQName(i);
            String attrValue = attrs.getValue(i);
            pr(" " + attrName + "=" + attrValue);
        }
        prn(">");
    }
    
    private static int initializeStatus(String tagName) {
        // return tagName.equals("java") ? Command.COMMAND_EXECUTED : Command.INITIALIZED;
        return Command.INITIALIZED;
    }
    
    private static String translateStatus(int status) {
        String result = "unknown";
        if(status == Command.INITIALIZED) {
            result = "intialized";
        } else if(status == Command.CHILDREN_FILTERED) {
            result = "children filtered";
        } else if(status == Command.COMMAND_EXECUTED) {
            result = "executed";
        } else if(status == Command.CHILDREN_PROCESSED) {
            result = "children processed";
        }
        return result;
    }
    
    private static final String[] objectAttrNames =
        { "id", "idref", "class", "field", "method", "property", "index" };

    private static final String[] voidAttrNames =
        { "id", "class", "method", "property", "index" };
    
    private static final String[] arrayAttrNames = { "id", "class", "length" };
    
    private static final String[] javaAttrNames = { "version", "class" };
}
