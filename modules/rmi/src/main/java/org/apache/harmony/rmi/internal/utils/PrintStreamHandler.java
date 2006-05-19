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
package org.apache.harmony.rmi.internal.utils;

import java.io.PrintStream;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

/**
 * This handler publishes log records into a <code>PrintStream</code>.
 * 
 * @author Gonzalo Ortega
 */
public final class PrintStreamHandler extends StreamHandler {

    private PrintStream out;

    /**
     * Creates a new <code>PrintStreamHandler</code>, with
     * <code>System.err</code> as destiny for the log records.
     */
    public PrintStreamHandler() {
        out = System.err;
        super.setOutputStream(out);
    }

    /**
     * Creates a new <code>PrintStreamHandler<code>, with <code>out</code> 
     * as destiny for the log records. 
     *
     * @param out The <code>PrintStream</code> where the messages will be logged to
     */
    public PrintStreamHandler(PrintStream out) {
        if (out != null) {
            super.setOutputStream(out);
        }
        this.out = out;
    }

    /**
     * @param record
     *            The log record to be published
     */
    @Override
    public final void publish(LogRecord record) {
        if ((out != null) && isLoggable(record)) {
            out.println(getFormatter().format(record));
            out.flush();
        }
    }

    /**
     * @param out
     *            The <code>PrintStream</code> where the messages will be
     *            logged, or <code>null</code> if no logging is desired.
     */
    public void setOutputPrintStream(PrintStream out) {
        this.out = out;
    }

    /**
     * 
     */
    @Override
    public final void close() {
        if (out != null) {
            super.close();
            if (out != System.err) {
                out.close();
            }
        }
    }

}
