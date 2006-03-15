/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
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
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package java.util.logging;

/**
 * <code>Handler</code> objects can report errors to the
 * <code>ErrorManager</code> instance attached to them when they encounter any
 * exceptions or errors.
 * <p>
 * Callers of a logger are unlikely to be interested in the exceptions occured
 * during logging. Use an <code>ErrorManager</code> object to report these
 * exceptions.
 * </p>
 * 
 */
public class ErrorManager {

    /*
     * -------------------------------------------------------------------
     * Constants
     * -------------------------------------------------------------------
     */

    /**
     * The error code indicating a failure that does not fit in any of the
     * specific types of failures that follow.
     */
    public static final int GENERIC_FAILURE = 0;

    /**
     * The error code indicating a failure when writting to an output stream.
     */
    public static final int WRITE_FAILURE = 1;

    /**
     * The error code indicating a failure when flushing an output stream.
     */
    public static final int FLUSH_FAILURE = 2;

    /**
     * The error code indicating a failure when closing an output stream.
     */
    public static final int CLOSE_FAILURE = 3;

    /**
     * The error code indicating a failure when opening an output stream.
     */
    public static final int OPEN_FAILURE = 4;

    /**
     * The error code indicating a failure when formatting the error messages.
     */
    public static final int FORMAT_FAILURE = 5;

    /*
     * -------------------------------------------------------------------
     * Instance variables
     * -------------------------------------------------------------------
     */

    // Used to synchronize calls to the error method.
    private Object lock;

    // Indicating whether the current call is the first call.
    private boolean firstCall;

    /*
     * -------------------------------------------------------------------
     * Constructors
     * -------------------------------------------------------------------
     */

    /**
     * Constructs an instance of <code>ErrorManager</code>.
     */
    public ErrorManager() {
        lock = new Object();
        firstCall = true;
    }

    /*
     * -------------------------------------------------------------------
     * Methods
     * -------------------------------------------------------------------
     */

    /**
     * Reports an error.
     * <p>
     * This method can be called by a <code>Handler</code> object when it
     * encounters an exception or error. The first call to this method will do
     * the exact error-reporting as desired. Subsequent calls are ignored.
     * 
     * @param msg
     *            the error message which may be <code>null</code>
     * @param ex
     *            the exception which may be <code>null</code>
     * @param errorCode
     *            the error code indicating the type of the failure
     */
    public void error(String msg, Exception ex, int errorCode) {
        if (firstCall) {
            // Synchronize concurrent "first" calls
            synchronized (lock) {
                if (firstCall) {
                    outputError(msg, ex, errorCode);
                }
            }
        }
    }

    //if it is the first time to call error, output it
    private void outputError(String msg, Exception ex, int errorCode) {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getName());
        sb.append(": the error code is "); //$NON-NLS-1$
        sb.append(errorCode);
        sb.append("."); //$NON-NLS-1$
        sb.append(LogManager.getSystemLineSeparator());
        if (null != msg) {
            sb.append("The error message is: "); //$NON-NLS-1$
            sb.append(msg);
            sb.append(LogManager.getSystemLineSeparator());
        }
        if (null != ex) {
            sb.append(ex.toString());
        }
        System.err.println(sb);
        firstCall = false;
    }

}

