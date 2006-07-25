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
 * @version $Revision: 1.14.6.4 $
 */
package java.beans;

import java.io.InputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import org.apache.harmony.beans.Handler;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.14.6.4 $
 */

public class XMLDecoder {

    private InputStream is = null;

    private Object owner = null;

    private ExceptionListener exceptionListener = null;

    private Vector<Object> objects = new Vector<Object>();

    private Iterator<Object> iterator = null;

    /**
     * @com.intel.drl.spec_ref
     */
    public XMLDecoder(InputStream is, Object owner,
            ExceptionListener exceptionListener) {
        this.is = is;
        this.owner = owner;
        this.exceptionListener = exceptionListener;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public XMLDecoder(InputStream is, Object owner) {
        this.is = is;
        this.owner = owner;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public XMLDecoder(InputStream is) {
        this.is = is;
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

    /**
     * @com.intel.drl.spec_ref
     */
    public Object getOwner() {
        return owner;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setExceptionListener(ExceptionListener exceptionListener) {
        this.exceptionListener = exceptionListener;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public ExceptionListener getExceptionListener() {
        return exceptionListener;
    }

    /**
     * @com.intel.drl.spec_ref
     */
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
        try {
            String saxParserClassName = System
                    .getProperty("org.xml.sax.driver");

            if (saxParserClassName == null) {
                saxParserClassName = "org.apache.xerces.parsers.SAXParser";
            }
            XMLReader xmlReader = XMLReaderFactory
                    .createXMLReader(saxParserClassName);
            xmlReader.setContentHandler(new Handler(this, objects));
            xmlReader.parse(new InputSource(is));
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
