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
package org.apache.harmony.pack200;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class PackingUtils {

    private static PackingLogger packingLogger;

    static {
        packingLogger = new PackingLogger("org.harmony.apache.pack200", null);
        LogManager.getLogManager().addLogger(packingLogger);
    }

    private static class PackingLogger extends Logger {

        private boolean verbose = false;

        protected PackingLogger(String name, String resourceBundleName) {
            super(name, resourceBundleName);
        }

        public void log(LogRecord logRecord) {
            if (verbose) {
                super.log(logRecord);
            }
        }

        public void setVerbose(boolean isVerbose) {
            verbose = isVerbose;
        }
    }

    public static void config(PackingOptions options) throws IOException {
        String logFileName = options.getLogFile();
        if (logFileName != null) {
            FileHandler fileHandler = new FileHandler(logFileName, false);
            fileHandler.setFormatter(new SimpleFormatter());
            packingLogger.addHandler(fileHandler);
            packingLogger.setUseParentHandlers(false);
        }

        packingLogger.setVerbose(options.isVerbose());
    }

    public static void log(String message) {
        packingLogger.log(Level.INFO, message);
    }

}