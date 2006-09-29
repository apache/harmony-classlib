/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.apache.harmony.tools.jarsigner;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

/**
 * Class to interact with user - ask for confirmations, and necessary parameters
 * which haven't been set in the command line.
 */
public class UserInteractor {
    // used to get additional data prompted
    private static InputStreamReader in = new InputStreamReader(System.in);

    // buffer for the data read
    private static char[] readData = new char[256];

    // number of symbols read
    private static int charsRead;

    // length of the "\r\n" which is added to the end of the line,
    // when ENTER is pressed.
    private static int newLineLength = 2;

    // an instance of Logger, used to manage message output 
    private final static Logger logger = Logger.getLogger("JarSignerLogger");
    
    // log handler 
    private static StreamHandler logHandler = new StreamHandler(System.out,
            new JSLogFormatter());

    static {
        // Do not send massages to another handlers.
        logger.setUseParentHandlers(false);
        // log to System.out by default.
        logger.addHandler(logHandler);
    }

    // Prints prompt and waits the user to enter the needed data,
    // tha data is returned.
    static char[] getDataFromUser(String prompt) throws IOException {
        print(prompt);
        charsRead = in.read(readData);
        char[] password = new char[charsRead - newLineLength];
        System.arraycopy(readData, 0, password, 0, charsRead - newLineLength);
        return password;
    }

    static void setOutputStream(OutputStream out) {
        logger.removeHandler(logHandler);
        // reuse the Formatter from old handler 
        logHandler = new StreamHandler(out, logHandler.getFormatter());
        logger.addHandler(logHandler);
    }

    // prints the given message, if the output is not turned off with
    // setNoOutput()
    static void print(String msg) {
        logger.info(msg);
        logHandler.flush();
    }

    // prints the given message and a new line symbol, if the output is not
    // turned off with setNoOutput()
    static void println(String msg) {
        logger.info(msg + "\n");
        logHandler.flush();
    }

    // prints the given message, if verbose output is set with
    // setVerboseOutput()
    static void printIfVerbose(String msg) {
        logger.fine(msg);
        logHandler.flush();
    }

    // prints the given message and a new line symbol, if verbose output is set
    // with setVerboseOutput()
    static void printlnIfVerbose(String msg) {
        logger.fine(msg + "\n");
        logHandler.flush();
    }

    // turns off the output
    static void setNoOutput() {
        logger.setLevel(Level.OFF);
        logger.setLevel(Level.OFF);
    }

    // sets the verbose output   
    static void setVerboseOutput() {
        logger.setLevel(Level.ALL);
        logHandler.setLevel(Level.ALL);        
    }

    // sets the normal output
    static void setNormalOutput() {
        logger.setLevel(Level.INFO);
        logHandler.setLevel(Level.INFO);
    }
}

