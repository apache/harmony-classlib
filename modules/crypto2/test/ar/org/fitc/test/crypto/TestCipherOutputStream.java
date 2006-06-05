package ar.org.fitc.test.crypto;

import junit.framework.*;

import java.io.IOException;
import java.io.OutputStream;
import javax.crypto.CipherOutputStream;
import ar.org.fitc.test.util.Messages;
import ar.org.fitc.test.util.crypto.Util;



class MyOutputStream extends OutputStream {

	public int[] buffer;
	public int index = 0;
	public boolean closed = false;
	public boolean flushed = false;
	
	public final static int bufferSize = 10;
	public final static String closeException = "MyOutputStream closed exception"; 
	public final static String flushException = "MyOutputStream flushed exception";
	public final static String writeException = "MyOutputStream writed exception";
	
	MyOutputStream() {
		buffer = new int[bufferSize];
		
	}
	
	public void write(int arg0) throws IOException {
		buffer[index++] = arg0;
		if (index == bufferSize) {
			throw new IOException(writeException);
		}
	}
	
	public void flush() throws IOException {
		if (flushed) {
			throw new IOException(flushException);
		}
		flushed = true;
	}
	
	public void close() throws IOException {
		if (closed) {
			throw new IOException(closeException);
		}
		closed = true;
	}
	
	
}

/**
 * JUnit test case for CipherOutputStreamTest
 */

public class TestCipherOutputStream extends TestCase implements Messages{
	
	private CipherOutputStream cipherOutputStream = null;
	private MyOutputStream outputStream = null;
	
	//declare reusable objects to be used across multiple tests
	public TestCipherOutputStream(String name) {
		super(name);
	}
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestCipherOutputStream.class);
	}
	public static Test suite() {
		return new TestSuite(TestCipherOutputStream.class);
	}
	protected void setUp() throws Exception {
	//define reusable objects to be used across multiple tests
	    outputStream = new MyOutputStream();
	    cipherOutputStream = new CipherOutputStream(outputStream, Util.getInstanceCipherInited());
	}
	protected void tearDown() {
	//clean up after testing (if necessary)
	}
	
	
	public void testCipherOutputStream001() {
		try{
			new CipherOutputStream(outputStream,null).close() ;
			fail(msgNullPointer);
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	
	public void testCipherOutputStream002() {
		try{
			new CipherOutputStream(null,null).close() ;
			fail(msgNullPointer);
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	public void testCipherOutputStream003() {
		try{
			new CipherOutputStream(null,Util.getInstanceCipherInited()).close() ;
			fail(msgNullPointer);
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public void testCipherOutputStream004() {
		assertTrue(cipherOutputStream instanceof CipherOutputStream);
	}
    
    public void testCipherOutputStream005() {
        try{
            cipherOutputStream = new CipherOutputStream(outputStream, Util.getInstanceCipher());
            assertTrue(cipherOutputStream instanceof CipherOutputStream);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
	/*
	 *  cierra el cipherOutputStream y verifica el flush y cerrado del outputStream.
	 */
	
	public void testClose001() {
		
		try {
			cipherOutputStream.close();
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
		assertEquals(outputStream.closed, true);
		assertEquals(outputStream.flushed, true);
	}
	
	/*
     * cierra el cipherOutputStream y verifica el flush y cerrado del outputStream; 
     * esta ultima acción (cerrado del outputStream)  termina excepcionalmente 
     * lo cual hace que cipherOutputStream tambien. 
	 */
	@SuppressWarnings("static-access")
    public void testClose002() {
		outputStream.closed= true;
		try {
			cipherOutputStream.close();
			assertTrue(false);
		} catch (IOException e) {
			assertEquals(e.getMessage(), outputStream.closeException);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
		assertEquals(outputStream.closed, true);
		assertEquals(outputStream.flushed, true);
	}
	
	/*
	 * cierra el cipherOutputStream y verifica el flush y cerrado del outputStream; 
	 * al flushear el outputStream termina excepcionalmente lo cual ¿¿no influye en cipherOutputStream??.
	 */
    @SuppressWarnings("static-access")
    public void testClose003() {
		outputStream.flushed= true;
		try {
			cipherOutputStream.close();
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
		assertEquals(outputStream.closed, true);
		assertEquals(outputStream.flushed, true);
	}
	
	
	/*
	 * Un simple Flush, solo se propaga. No hay datos en él. 
	 */
	
	public void testFlush001() {
		
		try {
			cipherOutputStream.flush();
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
		assertEquals(outputStream.flushed, true);
	}
	
	/*
	 * Un simple Flush, solo se propaga, pero outputStream arroja excepción, la cual se porpaga.
	 */
    @SuppressWarnings("static-access")
	public void testFlush002() {
		outputStream.flushed= true;
		try {
			cipherOutputStream.flush();
			assertTrue(false);
		} catch (IOException e) {
			//no pasa por aca. 
			assertEquals(e.getMessage(), outputStream.flushException);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
		assertEquals(outputStream.flushed, true);
	}
	
	/*
	 * Cargo con unos datos el cipherOutStream.  
	 */
	public void testFlush003() {
		
		try {
			byte[] buf = {0, 1,2,3,4,5,6,7,8,9,10};
			cipherOutputStream.write(buf);
			cipherOutputStream.flush();
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
		assertEquals(outputStream.flushed, true);
		assertTrue(outputStream.index > 0);
	}
	
	/*
	 * Escribo hasta llenar mi buffer y luego arrojo excepción. Ver como se propara la excepción
	 */
    @SuppressWarnings("static-access")
	public void testWriteInt001() {
		try {
			for(int i=0; i < 300; i++) {
				cipherOutputStream.write(i);
			}
			assertTrue(false);
		} catch (IOException e) {
			//no pasa por aca. 
			assertEquals(e.getMessage(), outputStream.writeException);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
		assertEquals(outputStream.index, outputStream.bufferSize);
	}
    public void testWriteInt002() {
        try{
            cipherOutputStream = new CipherOutputStream(outputStream, Util.getInstanceCipher());
            assertTrue(cipherOutputStream instanceof CipherOutputStream);
            byte[] buf = {0, 1,2,3,4,5,6,7,8,9,10};
            cipherOutputStream.write(buf);
            fail("Must raise a RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
	
	/*
	 * Escribo una sola ves hasta llenar mi buffer y luego arrojo excepción. Ver como se propara la excepción
	 */
    @SuppressWarnings("static-access")
	public void testWriteByteArrayIntInt001() {
		try {
			byte[] buf = {0, 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
			
			cipherOutputStream.write(buf,2,18);
			assertTrue(false);
		} catch (IOException e) {
			//no pasa por aca. 
			assertEquals(e.getMessage(), outputStream.writeException);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
		assertEquals(outputStream.index, outputStream.bufferSize);
	}
    
    public void testWriteByteArrayIntInt002() {
        try {
            cipherOutputStream = new CipherOutputStream(outputStream, Util.getInstanceCipher());
            assertTrue(cipherOutputStream instanceof CipherOutputStream);
            byte[] buf = {0, 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};    
            cipherOutputStream.write(buf,2,18);
            fail("Must raise a RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
        
    }

    public final void testWriteByteArrayIntInt003() {
            try { 
                cipherOutputStream.write(new byte[0], 0, 0);
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt004() {
            try { 
                cipherOutputStream.write(new byte[0], 0, 7);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt005() {
            try { 
                cipherOutputStream.write(new byte[0], 0, -3);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt006() {
            try { 
                cipherOutputStream.write(new byte[0], 13, 0);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt007() {
            try { 
                cipherOutputStream.write(new byte[0], 13, 7);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt008() {
            try { 
                cipherOutputStream.write(new byte[0], 13, -3);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt009() {
            try { 
                cipherOutputStream.write(new byte[0], 23, 0);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt010() {
            try { 
                cipherOutputStream.write(new byte[0], 23, 7);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt011() {
            try { 
                cipherOutputStream.write(new byte[0], 23, -3);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt012() {
            try { 
                cipherOutputStream.write(new byte[0], -1, 0);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt013() {
            try { 
                cipherOutputStream.write(new byte[0], -1, 7);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt014() {
            try { 
                cipherOutputStream.write(new byte[0], -1, -3);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt015() {
            try { 
                cipherOutputStream.write(null, 0, 0);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt016() {
            try { 
                cipherOutputStream.write(null, 0, 7);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt017() {
            try { 
                cipherOutputStream.write(null, 0, -3);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt018() {
            try { 
                cipherOutputStream.write(null, 13, 0);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt019() {
            try { 
                cipherOutputStream.write(null, 13, 7);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt020() {
            try { 
                cipherOutputStream.write(null, 13, -3);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt021() {
            try { 
                cipherOutputStream.write(null, 23, 0);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt022() {
            try { 
                cipherOutputStream.write(null, 23, 7);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt023() {
            try { 
                cipherOutputStream.write(null, 23, -3);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt024() {
            try { 
                cipherOutputStream.write(null, -1, 0);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt025() {
            try { 
                cipherOutputStream.write(null, -1, 7);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt026() {
            try { 
                cipherOutputStream.write(null, -1, -3);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt027() {
            try { 
                cipherOutputStream.write(new byte[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}, 0, 0);
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt028() {
            try { 
                cipherOutputStream.write(new byte[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}, 0, 7);
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt029() {
            try { 
                cipherOutputStream.write(new byte[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}, 0, -3);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt030() {
            try { 
                cipherOutputStream.write(new byte[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}, 13, 0);
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt031() {
            try { 
                cipherOutputStream.write(new byte[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}, 13, 7);
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt032() {
            try { 
                cipherOutputStream.write(new byte[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}, 13, -3);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt033() {
            try { 
                cipherOutputStream.write(new byte[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}, 23, 0);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt034() {
            try { 
                cipherOutputStream.write(new byte[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}, 23, 7);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt035() {
            try { 
                cipherOutputStream.write(new byte[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}, 23, -3);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt036() {
            try { 
                cipherOutputStream.write(new byte[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}, -1, 0);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt037() {
            try { 
                cipherOutputStream.write(new byte[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}, -1, 7);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArrayIntInt038() {
            try { 
                cipherOutputStream.write(new byte[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}, -1, -3);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };
	
	/*
	 * Escribo una sola ves hasta llenar mi buffer y luego arrojo excepción. Ver como se propara la excepción
	 */
    @SuppressWarnings("static-access")
	public void testWriteByteArray001() {
		try {
			byte[] buf = {0, 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
			
			cipherOutputStream.write(buf);
			assertTrue(false);
		} catch (IOException e) {
			//no pasa por aca. 
			assertEquals(e.getMessage(), outputStream.writeException);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
		assertEquals(outputStream.index, outputStream.bufferSize);
		
	}
    
    public void testWriteByteArray002() {
        try {
            cipherOutputStream = new CipherOutputStream(outputStream, Util.getInstanceCipher());
            assertTrue(cipherOutputStream instanceof CipherOutputStream);
            byte[] buf = {0, 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};    
            cipherOutputStream.write(buf);
            fail("Must raise a RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
        
    }
    

    public final void testWriteByteArray003() {
            try { 
                cipherOutputStream.write(new byte[0]);
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArray004() {
            try { 
                cipherOutputStream.write(null);
                fail("Must raise a RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };

    public final void testWriteByteArray005() {
            try { 
                cipherOutputStream.write(new byte[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15});
                } catch (Throwable e) {
                    fail("Failed with:" + e);
            }
            
        };


    	
}