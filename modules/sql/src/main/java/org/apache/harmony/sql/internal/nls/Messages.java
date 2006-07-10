/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.sql.internal.nls;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.harmony.luni.util.MsgHelp;

public class Messages {

    // ResourceBundle holding the system messages.
    static private ResourceBundle bundle = null;

    static {
        // Attempt to load the messages.
        try {
            bundle = MsgHelp.setLocale(
                    Locale.getDefault(),
                    "org.apache.harmony.sql.internal.nls.messages"); //$NON-NLS-1$
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a message which has no arguments.
     * 
     * @param msg
     *            String the key to look up.
     * @return String the message for that key in the system message bundle.
     */
    static public String getString(String msg) {
        if (bundle == null)
            return msg;
        try {
            return bundle.getString(msg);
        } catch (MissingResourceException e) {
            return "Missing message: " + msg; //$NON-NLS-1$
        }
    }

    /**
     * Retrieves a message which takes 1 argument.
     * 
     * @param msg
     *            String the key to look up.
     * @param arg
     *            Object the object to insert in the formatted output.
     * @return String the message for that key in the system message bundle.
     */
    static public String getString(String msg, Object arg) {
        return getString(msg, new Object[] { arg });
    }

    /**
     * Retrieves a message which takes 1 integer argument.
     * 
     * @param msg
     *            String the key to look up.
     * @param arg
     *            int the integer to insert in the formatted output.
     * @return String the message for that key in the system message bundle.
     */
    static public String getString(String msg, int arg) {
        return getString(msg, new Object[] { Integer.toString(arg) });
    }

    /**
     * Retrieves a message which takes 1 character argument.
     * 
     * @param msg
     *            String the key to look up.
     * @param arg
     *            char the character to insert in the formatted output.
     * @return String the message for that key in the system message bundle.
     */
    static public String getString(String msg, char arg) {
        return getString(msg, new Object[] { String.valueOf(arg) });
    }

    /**
     * Retrieves a message which takes 2 arguments.
     * 
     * @param msg
     *            String the key to look up.
     * @param arg1
     *            Object an object to insert in the formatted output.
     * @param arg2
     *            Object another object to insert in the formatted output.
     * @return String the message for that key in the system message bundle.
     */
    static public String getString(String msg, Object arg1, Object arg2) {
        return getString(msg, new Object[] { arg1, arg2 });
    }

    /**
     * Retrieves a message which takes several arguments.
     * 
     * @param msg
     *            String the key to look up.
     * @param args
     *            Object[] the objects to insert in the formatted output.
     * @return String the message for that key in the system message bundle.
     */
    static public String getString(String msg, Object[] args) {
        String format = msg;

        if (bundle != null) {
            try {
                format = bundle.getString(msg);
            } catch (MissingResourceException e) {
            }
        }

        return MsgHelp.format(format, args);
    }
}
