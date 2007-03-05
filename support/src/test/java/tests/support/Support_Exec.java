/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tests.support;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import junit.framework.TestCase;

public class Support_Exec extends TestCase {

	public static String execJava(String[] args, String[] classpath,
			boolean displayOutput) throws IOException, InterruptedException {
		// this function returns the output of the process as a string
		Object[] execArgs = execJava2(args, classpath, displayOutput);
		Process proc = (Process) execArgs[0];

		StringBuffer output = new StringBuffer();
		InputStream in = proc.getInputStream();
		int result;
		byte[] bytes = new byte[1024];
		while ((result = in.read(bytes)) != -1) {
			output.append(new String(bytes, 0, result));
			if (displayOutput) {
                System.out.write(bytes, 0, result);
            }
		}
		in.close();
		proc.waitFor();
		checkStderr(execArgs);
		proc.destroy();
		return output.toString();
	}
	
	public static String execJava(String[] args, String[] classpath, String[] envp,
			boolean displayOutput) throws IOException, InterruptedException {
		// this function returns the output of the process as a string
		Object[] execArgs = execJavaNoSystemClasspath(args, classpath, envp, displayOutput);
		Process proc = (Process) execArgs[0];

		StringBuffer output = new StringBuffer();
		InputStream in = proc.getInputStream();
		int result;
		byte[] bytes = new byte[1024];
		while ((result = in.read(bytes)) != -1) {
			output.append(new String(bytes, 0, result));
			if (displayOutput)
				System.out.write(bytes, 0, result);
		}
		in.close();
		proc.waitFor();
		checkStderr(execArgs);
		proc.destroy();
		return output.toString();
	}


	public static void checkStderr(Object[] execArgs) {
		StringBuffer errBuf = (StringBuffer) execArgs[1];
		synchronized (errBuf) {
			if (errBuf.length() > 0) {
				fail(errBuf.toString());
			}
		}
	}
	
	private static Object[] execJavaNoSystemClasspath(String[] args, String[] classpath, String[] envp,
			final boolean displayOutput) throws IOException, InterruptedException {
		// this function returns the resulting process from the exec
		int baseArgs = 0;
		String[] execArgs = null;
        baseArgs = (classpath == null) ? 1 : 3;
        execArgs = new String[baseArgs + args.length];
        String executable = System.getProperty("java.home");
        if (!executable.endsWith(File.separator))
            executable += File.separator;
        executable += "bin" + File.separator;
        execArgs[0] = executable + "java";
        
		String classPathString = "";
		if (classpath != null) {
            for (int i = 0; i < classpath.length; i++) {
            	if( i != 0) {
            		classPathString += File.pathSeparator;
            	}
                classPathString +=  classpath[i];
            }
            execArgs[1] = "-cp";
            execArgs[2] = classPathString;
		}

        for (int i = 0; i < args.length; i++)
            execArgs[baseArgs + i] = args[i];

		final Process proc = Runtime.getRuntime().exec(execArgs, envp);
		final StringBuffer errBuf = new StringBuffer();
		Thread errThread = new Thread(new Runnable() {
			public void run() {
				synchronized (errBuf) {
					synchronized (proc) {
						proc.notifyAll();
					}
					InputStream err = proc.getErrorStream();
					int result;
					byte[] bytes = new byte[1024];
					try {
						while ((result = err.read(bytes)) != -1) {
							if(displayOutput) {
							   System.err.write(bytes, 0, result);
							}
							errBuf.append(new String(bytes));
						}
						err.close();
					} catch (IOException e) {
						e.printStackTrace();
						ByteArrayOutputStream out = new ByteArrayOutputStream();
						PrintStream printer = new PrintStream(out);
						e.printStackTrace(printer);
						printer.close();
						errBuf.append(new String(out.toByteArray()));
					}
				}
			}
		});
		synchronized (proc) {
			errThread.start();
			// wait for errThread to start
			proc.wait();
		}
		return new Object[] { proc, errBuf };
	}


	public static Object[] execJava2(String[] args, String[] classpath,
			boolean displayOutput) throws IOException, InterruptedException {
		// this function returns the resulting process from the exec
		int baseArgs = 0;
		String[] execArgs = null;
//		String vendor = System.getProperty("java.vendor");
		String classPathString = "";
		if (classpath != null)
            for (String element : classpath) {
                classPathString += File.pathSeparator + element;
            }

        baseArgs = 3;
        execArgs = new String[baseArgs + args.length];
        String executable = System.getProperty("java.home");
        if (!executable.endsWith(File.separator)) {
            executable += File.separator;
        }
        executable += "bin" + File.separator;
        execArgs[0] = executable + "java";
        execArgs[1] = "-cp";
        execArgs[2] = System.getProperty("java.class.path") + classPathString;

        for (int i = 0; i < args.length; i++) {
            execArgs[baseArgs + i] = args[i];
        }
        StringBuffer command = new StringBuffer(execArgs[0]);
        for (int i = 1; i < execArgs.length; i++) {
            command.append(" " + execArgs[i]);
        }
        System.out.println();
        System.out.println("Exec: " + command.toString());

		final Process proc = Runtime.getRuntime().exec(execArgs);
		final StringBuffer errBuf = new StringBuffer();
		Thread errThread = new Thread(new Runnable() {
			public void run() {
				synchronized (errBuf) {
					synchronized (proc) {
						proc.notifyAll();
					}
					InputStream err = proc.getErrorStream();
					int result;
					byte[] bytes = new byte[1024];
					try {
						while ((result = err.read(bytes)) != -1) {
							System.err.write(bytes, 0, result);
							errBuf.append(new String(bytes));
						}
						err.close();
					} catch (IOException e) {
						e.printStackTrace();
						ByteArrayOutputStream out = new ByteArrayOutputStream();
						PrintStream printer = new PrintStream(out);
						e.printStackTrace(printer);
						printer.close();
						errBuf.append(new String(out.toByteArray()));
					}
				}
			}
		});
		synchronized (proc) {
			errThread.start();
			// wait for errThread to start
			proc.wait();
		}
		return new Object[] { proc, errBuf };
	}

}
