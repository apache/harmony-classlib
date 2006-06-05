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
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */

package ar.org.fitc.test.util.xmlcompare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

public class Html2XmlInputStream extends InputStream {
	
	public static void main(String[] argv) throws Exception {
		System.out.println("Start");
		InputStream io = new Html2XmlInputStream(new File("profile.class.Crypto.ITC.html"));
		int b = 0;
		do {
			System.out.print((char) b);
			b = io.read();
		} while (b != -1);
		
//		new File("performanRunTest-JCE-SUN.20060110.xml");
	}
	
	private BufferedReader externalBuffer;
	private final static String 
		HEAD = "<?xml version=\"1.0\"?>\n<PerformaceTest>", 
		TAIL = "</PerformaceTest>";
	private ByteBuffer 
	buffer = ByteBuffer.wrap(HEAD.getBytes());
	boolean end = false;
	
	public Html2XmlInputStream(InputStream io) throws IOException {
		super();
		externalBuffer = new BufferedReader(new InputStreamReader(io));
		externalBuffer.readLine();
		externalBuffer.readLine();
		// TODO Auto-generated constructor stub
	}
	
	public Html2XmlInputStream(File f) throws IOException {
		this((InputStream) new FileInputStream(f));
		// TODO Auto-generated constructor stub
	}
	
	private static String html2xml(String line) {
		if (line == null || line.startsWith("</table>")) {
			return null;
		}
		line = line.replaceAll("tr", "test");
		line = line.replaceAll("&nbsp;", " ");
		line = line.replaceFirst("td", "name");
		line = line.replaceFirst("td", "name");
		line = line.replaceFirst("td", "calls");
		line = line.replaceFirst("td", "calls");
		line = line.replaceFirst("td", "callspor");
		line = line.replaceFirst("td", "callspor");
		line = line.replaceFirst("td", "time");
		line = line.replaceFirst("td", "time");
		line = line.replaceFirst("td", "timepor");
		line = line.replaceFirst("td", "timepor");
		line = line.replaceFirst("td", "timeupcalls");
		line = line.replaceFirst("td", "timeupcalls");
		line = line.replaceFirst("td", "totaltime");
		line = line.replaceFirst("td", "totaltime");
		line = line.replaceFirst("td", "insttime");
		line = line.replaceFirst("td", "insttime");
		return line + "\n";
	}

	private boolean recharch() throws IOException {
		if (end) return false;
		String line = externalBuffer.readLine();
		line = html2xml(line);
		if (line == null) {
			line = TAIL;
			end = true;
		}
		buffer = ByteBuffer.wrap(line.getBytes());
		return true;
	}
	
	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		if (buffer.hasRemaining() || recharch()) {
			return buffer.get();
		} else {
			return -1;
		}
		
		
	}

}
