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
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

import org.apache.harmony.beans.Handler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XMLDecoder {

    private InputStream is = null;

    private Object owner = null;

    private ExceptionListener exceptionListener = null;

    private final Vector<Object> objects = new Vector<Object>();

    private Iterator<Object> iterator = null;
    
    private ClassLoader classLoader = null;

    public XMLDecoder(InputStream is, Object owner,
            ExceptionListener exceptionListener, ClassLoader cl) {
        this.is = is;
        this.owner = owner;
        this.exceptionListener = exceptionListener;
        this.classLoader = cl;
    }

    public XMLDecoder(InputStream is, Object owner,
            ExceptionListener exceptionListener) {
        this.is = is;
        this.owner = owner;
        this.exceptionListener = exceptionListener;
    }

    public XMLDecoder(InputStream is, Object owner) {
        this.is = is;
        this.owner = owner;
    }

    public XMLDecoder(InputStream is) {
        this.is = is;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

    public Object readObject() {
        try {
            if (iterator == null) {
                initialize();
            }
            return iterator.next();
        } catch (NoSuchElementException nsee) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public Object getOwner() {
        return owner;
    }

    public void setExceptionListener(ExceptionListener exceptionListener) {
        this.exceptionListener = exceptionListener;
    }

    public ExceptionListener getExceptionListener() {
        return exceptionListener;
    }

    public void close() {
        try {
            is.close();
        } catch (IOException ioe) {
            handleException(ioe);
        }
    }

    private void handleException(Exception e) {
        if (exceptionListener != null) {
            exceptionListener.exceptionThrown(e);
        }
    }

    private void initialize() {
        ClassLoader oldCL = null;
        
        try {
            String saxParserClassName = System
                    .getProperty("org.xml.sax.driver"); //$NON-NLS-1$

            if (saxParserClassName == null) {
                saxParserClassName = "org.apache.xerces.parsers.SAXParser"; //$NON-NLS-1$
            }
            XMLReader xmlReader = XMLReaderFactory
                    .createXMLReader(saxParserClassName);
            xmlReader.setContentHandler(new Handler(this, objects));
            if (classLoader != null) {
                oldCL = Thread.currentThread().getContextClassLoader();
                Thread.currentThread().setContextClassLoader(classLoader);
            }
            xmlReader.parse(new InputSource(is));
            if (classLoader != null) {
                Thread.currentThread().setContextClassLoader(oldCL);
            }
        } catch (SAXException saxe) {
            saxe.printStackTrace();
            handleException(saxe);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            handleException(ioe);
        } finally {
            iterator = objects.iterator();
        }
    }
}
