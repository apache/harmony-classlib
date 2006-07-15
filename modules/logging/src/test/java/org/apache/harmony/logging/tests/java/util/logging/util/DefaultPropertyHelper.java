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

package org.apache.harmony.logging.tests.java.util.logging.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 
 */
public class DefaultPropertyHelper {

	static final String libPath = new StringBuffer().append(
			System.getProperty("java.home")).append(File.separator).append(
			"lib").toString();

	static final String propertyPath = libPath + File.separator
			+ "logging.properties";

	static final Properties defaultProps = new Properties();

	static {
		defaultProps.put(".level", "INFO");
		defaultProps.put("java.util.logging.FileHandler.limit", "50000");
		defaultProps.put("java.util.logging.ConsoleHandler.formatter",
				"java.util.logging.SimpleFormatter");
		defaultProps.put("handlers", "java.util.logging.ConsoleHandler");
		defaultProps.put("java.util.logging.FileHandler.count", "1");
		defaultProps.put("com.xyz.foo.level", "SEVERE");
		defaultProps.put("java.util.logging.FileHandler.formatter",
				"java.util.logging.XMLFormatter");
		defaultProps.put("java.util.logging.ConsoleHandler.level", "INFO");
		defaultProps.put("java.util.logging.FileHandler.pattern",
				"%h/java%u.log");
	}

	private DefaultPropertyHelper() {
	}

	public static File init() throws FileNotFoundException, IOException {
		return init(defaultProps);
	}

	public synchronized static File init(Properties p)
			throws FileNotFoundException, IOException {

		File file = new File(libPath);
		if (!file.exists()) {
			file.mkdir();
		}
		file = new File(propertyPath);
		File bakFile = file;
		if (bakFile.exists()) {
			bakFile = new File(bakFile.getPath() + ".bak");
		}
		file.renameTo(bakFile);

		OutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(propertyPath));
			p.store(out, "");
		} finally {
			try {
				out.close();
			} catch (Exception e) {
			}
		}

		return bakFile;
	}

	public synchronized static void reset(File bak) {
		if (bak.exists()) {
			File file = new File(propertyPath);
			file.delete();
			bak.renameTo(file);
		}
	}

}
