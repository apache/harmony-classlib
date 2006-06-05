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

package ar.org.fitc.test.whitebox;

import java.io.IOException;
import java.io.InputStream;

import javax.crypto.CipherInputStream;

import junit.framework.TestCase;
import ar.org.fitc.test.util.crypto.Util;



class MyInputStream extends InputStream {
	
	public int i=0;
	public boolean b = false;
	public boolean readZero = true;
	public boolean closed = false;
	public final static String closeException = "MyInputStream closed exception";
	
	MyInputStream() {}
	
	MyInputStream(boolean b) {
		this.b = b;
	}

	public int available() throws IOException {
		throw new IOException();
	}
	
	public int read() throws IOException {
		if (i > 10) {
			return -1;	
		}
		return i++;
	}
	
	public int read(byte[] out, int offset, int len) throws IOException {
		if (!readZero) {
			return super.read(out,offset,len);
		}
		i--;
		if (i < -100) {
			readZero = false;
		}
		return 0;
	}
	
	public boolean markSupported() {
		return b;
	}

	public void close() throws IOException {
		if (closed) {
			throw new IOException(closeException);
		}
		closed = true;
	}
	
}
/**
 * JUnit test case for CipherInputStreamTest
 */

public class TestCipherInputStream extends TestCase {
	
	private MyInputStream inputStream = null;
	private CipherInputStream cipherInputStream = null;
	
	@SuppressWarnings("unused")
    final private static int bufLen = 10;
	//declare reusable objects to be used across multiple tests
	public TestCipherInputStream(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception {
		//define reusable objects to be used across multiple tests
		super.setUp();
		
		
		inputStream = new MyInputStream(); 
		cipherInputStream = new CipherInputStream(inputStream, Util.getInstanceCipherInited());
	}
	
	public void testRead001() throws Exception {
			while(cipherInputStream.read() != -1);
			assertFalse(inputStream.closed);
			while(cipherInputStream.read() != -1);
			assertFalse(inputStream.closed);
			cipherInputStream.close();
			assertTrue(inputStream.closed);
	}
	
	
	
	
	/*
	 * Lee hasta que no pueda mas, controla que los valores esten entre 0 y 255, 
	 * siendo -1 el valor con que termina al no tener mas byte para leer. 
	 */
	public void testRead002() throws IOException {		
			cipherInputStream.read();
	}
	
}