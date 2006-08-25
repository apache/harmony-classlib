/*
 * Copyright 2005-2006 The Apache Software Foundation or its licensors, as applicable
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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
 * @author  Mikhail A. Markov
 * @version $Revision: 1.3.4.2 $
 */
package java.rmi.server;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Hashtable;


/**
 * @com.intel.drl.spec_ref
 *
 * Note: this class is not used by RMI runtime.
 *
 * @author  Mikhail A. Markov
 * @version $Revision: 1.3.4.2 $
 */
public class LogStream extends PrintStream {

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int SILENT = 0;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int BRIEF = 10;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int VERBOSE = 20;

    // The default print stream.
    private static PrintStream defaultStream = System.err;

    // The list of created LogStreams. Their names are keys in the table.
    private static Hashtable logStreams = new Hashtable();

    // The name of this LogStream
    private String name;

    /*
     * True if write method was never called or '\n' (new-line symbol was
     * the last symbol written to the underlying stream.
     */
    private boolean isFirstByte = true;

    /*
     * Constructs LogStream having the given name and writing to the given
     * OutputStream.
     */
    private LogStream(String name, OutputStream out) {
        super(out);
        this.name = name;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String toString() {
        return "LogStream[" + name + "]";
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void write(byte[] b, int off, int len) {
        for (int i = 0; i < len; ++i) {
            write(b[off + i]);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void write(int b) {
        synchronized (this) {
            if (b == '\n') {
                super.write(b);
                isFirstByte = true;
            } else {
                if (isFirstByte) {
                    isFirstByte = false;
                    print(toString() + ":");
                }
                super.write(b);
            }
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public synchronized void setOutputStream(OutputStream out) {
        this.out = out;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public synchronized OutputStream getOutputStream() {
        return out;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static int parseLevel(String levelStr) {
        if (levelStr == null) {
            return -1;
        }
        levelStr = levelStr.trim().toUpperCase();

        if (levelStr.equals("SILENT")) {
            return SILENT;
        } else if (levelStr.equals("BRIEF")) {
            return BRIEF;
        } else if (levelStr.equals("VERBOSE")) {
            return VERBOSE;
        } else {
            try {
                return Integer.parseInt(levelStr);
            } catch (NumberFormatException nfe) {
                return -1;
            }
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static synchronized void setDefaultStream(PrintStream stream) {
        defaultStream = stream;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static synchronized PrintStream getDefaultStream() {
        return defaultStream;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static LogStream log(String name) {
        synchronized (logStreams) {
            LogStream stream = (LogStream) logStreams.get(name);

            if (stream != null) {
                return stream;
            } else {
                stream = new LogStream(name, defaultStream);
                logStreams.put(name, stream);
                return stream;
            }
        }
    }
}
