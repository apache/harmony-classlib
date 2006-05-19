/* 
*  Copyright 2005 The Apache Software Foundation or its licensors, as applicable. 
* 
*  Licensed under the Apache License, Version 2.0 (the "License"); 
*  you may not use this file except in compliance with the License. 
*  You may obtain a copy of the License at 
* 
*    http://www.apache.org/licenses/LICENSE-2.0 
* 
*  Unless required by applicable law or agreed to in writing, software 
*  distributed under the License is distributed on an "AS IS" BASIS, 
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
*  See the License for the specific language governing permissions and 
*  limitations under the License. 
*/
package java.rmi.server;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public class LogStream extends PrintStream {

    public static final int SILENT = 0;

    public static final int BRIEF = 10;

    public static final int VERBOSE = 20;

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public LogStream(OutputStream out) {
        super(out);
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public synchronized void setOutputStream(OutputStream out) {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public String toString() {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public void write(int b) {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public void write(byte[] b, int off, int len) {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public synchronized OutputStream getOutputStream() {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static synchronized PrintStream getDefaultStream() {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static int parseLevel(String s) {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static synchronized void setDefaultStream(PrintStream newDefault) {
        throw new UnsupportedOperationException();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static LogStream log(String name) {
        throw new UnsupportedOperationException();
    }
}
