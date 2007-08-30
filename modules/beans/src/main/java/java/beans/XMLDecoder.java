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
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package java.beans;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * <code>XMLDecoder</code> reads objects from xml created by
 * <code>XMLEncoder</code>.
 * <p>The API is similar to <code>ObjectInputStream</code>.</p>
 *
 */
public class XMLDecoder {

    private ClassLoader defaultClassLoader = null;
    
    private static class DefaultExceptionListener implements ExceptionListener {

        public void exceptionThrown(Exception e) {
            e.printStackTrace();
            System.err.println("Continue..."); //$NON-NLS-1$
        }
    }

    private class SAXHandler extends DefaultHandler {

        boolean inJavaElem = false;

        HashMap<String, Object> idObjMap = new HashMap<String, Object>();

        @Override
        public void characters(char[] ch, int start, int length)
                throws SAXException {
            if (!inJavaElem) {
                return;
            }
            if (readObjs.size() > 0) {
                Elem elem = readObjs.peek();
                if (elem.isBasicType) {
                    String str = new String(ch, start, length);
                    elem.methodName = elem.methodName == null ? str
                            : elem.methodName + str;
                }
            }
        }

        @SuppressWarnings("nls")
        @Override
        public void startElement(String uri, String localName, String qName,
                Attributes attributes) throws SAXException {
            if (!inJavaElem) {
                if ("java".equals(qName)) {
                    inJavaElem = true;
                } else {
                    listener.exceptionThrown(new Exception(
                            "unknown root element: " + qName));
                }
                return;
            }

            if ("object".equals(qName)) {
                startObjectElem(attributes);
            } else if ("array".equals(qName)) {
                startArrayElem(attributes);
            } else if ("void".equals(qName)) {
                startVoidElem(attributes);
            } else if ("boolean".equals(qName) || "byte".equals(qName)
                    || "char".equals(qName) || "class".equals(qName)
                    || "double".equals(qName) || "float".equals(qName)
                    || "int".equals(qName) || "long".equals(qName)
                    || "short".equals(qName) || "string".equals(qName)
                    || "null".equals(qName)) {
                startBasicElem(qName, attributes);
            }
        }

        @SuppressWarnings("nls")
        private void startObjectElem(Attributes attributes) {
            Elem elem = new Elem();
            elem.isExpression = true;
            elem.id = attributes.getValue("id");
            elem.idref = attributes.getValue("idref");
            if (elem.idref == null) {
                obtainTarget(elem, attributes);
                obtainMethod(elem, attributes);
            }
			
            readObjs.push(elem);
        }

        private void obtainTarget(Elem elem, Attributes attributes) {
            String className = attributes.getValue("class"); //$NON-NLS-1$
            if (className != null) {
                try {
                    elem.target = classForName(className);
                } catch (ClassNotFoundException e) {
                    listener.exceptionThrown(e);
                }
            } else {
                Elem parent = latestUnclosedElem();
				if(parent == null) {
					elem.target = owner;
					return;
				}
                elem.target = execute(parent);
            }
        }

        @SuppressWarnings("nls")
        private void obtainMethod(Elem elem, Attributes attributes) {
            elem.methodName = attributes.getValue("method");
            if (elem.methodName != null) {
                return;
            }

            elem.methodName = attributes.getValue("property");
            if (elem.methodName != null) {
                elem.fromProperty = true;
                return;
            }

            elem.methodName = attributes.getValue("index");
            if (elem.methodName != null) {
                elem.fromIndex = true;
                return;
            }

            elem.methodName = attributes.getValue("field");
            if (elem.methodName != null) {
				elem.fromField = true;
                return;
            }

            elem.methodName = attributes.getValue("owner");
            if (elem.methodName != null) {
				elem.fromOwner = true;
                return;
            }

            elem.methodName = "new"; // default method name
        }

        @SuppressWarnings("nls")
        private Class<?> classForName(String className)
                throws ClassNotFoundException {
            if ("boolean".equals(className)) {
                return Boolean.TYPE;
            } else if ("byte".equals(className)) {
                return Byte.TYPE;
            } else if ("char".equals(className)) {
                return Character.TYPE;
            } else if ("double".equals(className)) {
                return Double.TYPE;
            } else if ("float".equals(className)) {
                return Float.TYPE;
            } else if ("int".equals(className)) {
                return Integer.TYPE;
            } else if ("long".equals(className)) {
                return Long.TYPE;
            } else if ("short".equals(className)) {
                return Short.TYPE;
            } else {
                return Class.forName(className, true,
                        defaultClassLoader == null ? Thread.currentThread()
                                .getContextClassLoader() : defaultClassLoader);
            }
        }

        @SuppressWarnings("nls")
        private void startArrayElem(Attributes attributes) {
            Elem elem = new Elem();
            elem.isExpression = true;
            elem.id = attributes.getValue("id");
            try {
                // find componet class
                Class<?> compClass = classForName(attributes.getValue("class"));
                // find length
                int length = Integer.parseInt(attributes.getValue("length"));
                // execute, new array instance
                elem.result = Array.newInstance(compClass, length);
                elem.isExecuted = true;
            } catch (Exception e) {
                listener.exceptionThrown(e);
            }
            readObjs.push(elem);
        }

        @SuppressWarnings("nls")
        private void startVoidElem(Attributes attributes) {
            Elem elem = new Elem();
            elem.id = attributes.getValue("id");
            obtainTarget(elem, attributes);
            obtainMethod(elem, attributes);
            readObjs.push(elem);
        }

        @SuppressWarnings("nls")
        private void startBasicElem(String tagName, Attributes attributes) {
            Elem elem = new Elem();
            elem.isBasicType = true;
            elem.isExpression = true;
            elem.id = attributes.getValue("id");
            elem.idref = attributes.getValue("idref");
            elem.target = tagName;
            readObjs.push(elem);
        }

        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            if (!inJavaElem) {
                return;
            }
            if ("java".equals(qName)) { //$NON-NLS-1$
                inJavaElem = false;
                return;
            }
            // find the elem to close
            Elem toClose = latestUnclosedElem();
            // make sure it is executed
            execute(toClose);
            // set to closed
            toClose.isClosed = true;
            // pop it and its children
            while (readObjs.pop() != toClose) {
                //
            }
            // push back expression
            if (toClose.isExpression) {
                readObjs.push(toClose);
            }
        }

        private Elem latestUnclosedElem() {
            for (int i = readObjs.size() - 1; i >= 0; i--) {
                Elem elem = readObjs.get(i);
                if (!elem.isClosed) {
                    return elem;
                }
            }
            return null;
        }

        private Object execute(Elem elem) {
            if (elem.isExecuted) {
                return elem.result;
            }

            // execute to obtain result
            try {
                if (elem.idref != null) {
                    elem.result = idObjMap.get(elem.idref);
                } else if (elem.isBasicType) {
                    elem.result = executeBasic(elem);
                } else {
                    elem.result = executeCommon(elem);
                }
            } catch (Exception e) {
                listener.exceptionThrown(e);
            }

            // track id
            if (elem.id != null) {
                idObjMap.put(elem.id, elem.result);
            }

            elem.isExecuted = true;
            return elem.result;
        }

        @SuppressWarnings("nls")
        private Object executeCommon(Elem elem) throws Exception {
            // pop args
            ArrayList<Object> args = new ArrayList<Object>(5);
            while (readObjs.peek() != elem) {
                Elem argElem = readObjs.pop();
                args.add(0, argElem.result);
            }
            // decide method name
            String method = elem.methodName;
            if (elem.fromProperty) {
                method = (args.size() == 0 ? "get" : "set")
                        + capitalize(method);
            }
            if (elem.fromIndex) {
                Integer index = Integer.valueOf(method);
                args.add(0, index);
                method = args.size() == 1 ? "get" : "set";
            }
			if(elem.fromField) {
				Field f = ((Class)elem.target).getField(method);
		        return (new Expression(f, "get", new Object[] {null})).getValue();
			}
			if(elem.fromOwner) {
				return owner;
			}
			
			if(elem.target == owner) {
				if("getOwner".equals(method)) {
					return owner;
				}
                Class[] c = new Class[args.size()];
                for(int i = 0; i < args.size(); i++) {
                	c[i] = args.get(i).getClass();
                }
                Method m = owner.getClass().getMethod(method, c);
                return m.invoke(owner, args.toArray());
			}
			
            // execute
            Expression exp = new Expression(elem.target, method, args.toArray());
            return exp.getValue();
        }

        private String capitalize(String str) {
            StringBuffer buf = new StringBuffer(str);
            buf.setCharAt(0, Character.toUpperCase(buf.charAt(0)));
            return buf.toString();
        }

        @SuppressWarnings("nls")
        private Object executeBasic(Elem elem) throws Exception {
            String tag = (String) elem.target;
            String value = elem.methodName;

            if ("null".equals(tag)) {
                return null;
            } else if ("string".equals(tag)) {
                return value == null ? "" : value;
            } else if ("class".equals(tag)) {
                return classForName(value);
            } else if ("boolean".equals(tag)) {
                return Boolean.valueOf(value);
            } else if ("byte".equals(tag)) {
                return Byte.valueOf(value);
            } else if ("char".equals(tag)) {
                return new Character(value.charAt(0));
            } else if ("double".equals(tag)) {
                return Double.valueOf(value);
            } else if ("float".equals(tag)) {
                return Float.valueOf(value);
            } else if ("int".equals(tag)) {
                return Integer.valueOf(value);
            } else if ("long".equals(tag)) {
                return Long.valueOf(value);
            } else if ("short".equals(tag)) {
                return Short.valueOf(value);
            } else {
                throw new Exception("Unknown tag of basic type: " + tag);
            }
        }

        @Override
        public void error(SAXParseException e) throws SAXException {
            listener.exceptionThrown(e);
        }

        @Override
        public void fatalError(SAXParseException e) throws SAXException {
            listener.exceptionThrown(e);
        }

        @Override
        public void warning(SAXParseException e) throws SAXException {
            listener.exceptionThrown(e);
        }
    }

    private static class Elem {
		String id;

        String idref;

        boolean isExecuted;

        boolean isExpression;

        boolean isBasicType;

        boolean isClosed;

        Object target;

        String methodName;

        boolean fromProperty;

        boolean fromIndex;
		
		boolean fromField;

        boolean fromOwner;

        Object result;
    }

    private InputStream inputStream;

    private ExceptionListener listener;

    private Object owner;

    private Stack<Elem> readObjs = new Stack<Elem>();

    private int readObjIndex = 0;

    /**
     * Create a decoder to read from specified input stream.
     *
     * @param inputStream   an input stream of xml
     */
    public XMLDecoder(InputStream inputStream) {
        this(inputStream, null, null);
    }

    /**
     * Create a decoder to read from specified input stream.
     *
     * @param inputStream   an input stream of xml
     * @param owner         the owner of this decoder
     */
    public XMLDecoder(InputStream inputStream, Object owner) {
        this(inputStream, owner, null);
    }

    /**
     * Create a decoder to read from specified input stream.
     *
     * @param inputStream   an input stream of xml
     * @param owner         the owner of this decoder
     * @param listener      listen to the exceptions thrown by the decoder
     */
    public XMLDecoder(InputStream inputStream, Object owner,
            ExceptionListener listener) {
        if (inputStream == null) {
            throw new IllegalArgumentException("Input stream cannot be null"); //$NON-NLS-1$
        }
        this.inputStream = inputStream;
        this.owner = owner;
        this.listener = (listener == null)? new DefaultExceptionListener(): listener;

        try {
            SAXParserFactory.newInstance().newSAXParser().parse(inputStream,
                    new SAXHandler());
        } catch (Exception e) {
            this.listener.exceptionThrown(e);
        }
    }
    
    public XMLDecoder(InputStream inputStream, Object owner,
            ExceptionListener listener, ClassLoader cl) {
        this(inputStream, owner, listener);
        defaultClassLoader = cl;
    }

    /**
     * Close the input stream of xml data.
     */
    public void close() {
        try {
            inputStream.close();
        } catch (Exception e) {
            listener.exceptionThrown(e);
        }
    }

    /**
     * Returns the exception listener.
     * @return the exception listener
     */
    public ExceptionListener getExceptionListener() {
        return listener;
    }

    /**
     * Returns the owner of this decoder.
     * @return the owner of this decoder
     */
    public Object getOwner() {
        return owner;
    }

    /**
     * Reads the next object.
     *
     * @return the next object
     * @exception ArrayIndexOutOfBoundsException if no more objects to read
     */
    @SuppressWarnings("nls")
    public Object readObject() {
        if (readObjIndex >= readObjs.size()) {
            throw new ArrayIndexOutOfBoundsException("no more objects to read");
        }
        Elem elem = readObjs.get(readObjIndex);
        if (!elem.isClosed) {
            // bad element, error occured while parsing
            throw new ArrayIndexOutOfBoundsException("no more objects to read");
        }
        readObjIndex++;
        return elem.result;
    }

    /**
     * Sets the exception listener.
     *
     * @param listener  an exception listener
     */
    public void setExceptionListener(ExceptionListener listener) {
        if (listener != null) {
            this.listener = listener;
        }
    }

    /**
     * Sets the owner of this decoder.
     *
     * @param owner     the owner of this decoder
     */
    public void setOwner(Object owner) {
        this.owner = owner;
    }
}


