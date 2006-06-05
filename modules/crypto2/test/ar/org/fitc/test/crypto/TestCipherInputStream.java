package ar.org.fitc.test.crypto;

import junit.framework.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;

import ar.org.fitc.test.util.crypto.Util;

class IOExcepctionInputStream extends InputStream {
	
	public int i=0;
	public boolean b = false;
	
	IOExcepctionInputStream() {}
	
	IOExcepctionInputStream(boolean b) {
		this.b = b;
	}

	public int available() throws IOException {
		throw new IOException();
	}
	
	public int read() throws IOException {
		if (i > 10) {
			throw new IOException();	
		}
		return i++;
	}
	
	public boolean markSupported() {
		return b;
	}
	
	
}
/**
 * JUnit test case for CipherInputStreamTest
 */

public class TestCipherInputStream extends TestCase {
	
	private InputStream inputStream = null;
	private CipherInputStream cipherInputStream = null;
	
	final private static int bufLen = 10;
	//declare reusable objects to be used across multiple tests
	public TestCipherInputStream(String name) {
		super(name);
	}
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestCipherInputStream.class);
	}
	public static Test suite() {
		return new TestSuite(TestCipherInputStream.class);
	}
	protected void setUp() throws Exception {
		//define reusable objects to be used across multiple tests
		super.setUp();
		
		byte[] buf = new byte[bufLen];
		for (int i=0; i < bufLen; i++) {
			buf[i] = (byte) i;
		}
		inputStream = new ByteArrayInputStream(buf);
		cipherInputStream = new CipherInputStream(inputStream, Util.getInstanceCipherInited());
	}
	protected void tearDown() {
		//clean up after testing (if necessary)
	}
	
	/*
	 * Se pasan los dos parametros -- InputStream y Cipher --, 
	 * ambos con valor null. Debe arrojar una excepcion NullPointerException 
	 */
	
	public void testCipherInputStreamConstructor001() {
		//insert code testing basic functionality
		InputStream is = null;
		Cipher c = null;
		
		CipherInputStream cis;
		
		//two null point
		try {
			cis = new CipherInputStream(is, c);
			cis.close();
			fail("if the specified input stream or cipher is null, " 
					+"a NullPointerException may be thrown later when they are used"
					+", it did? No");
		} catch (NullPointerException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	
	/*
	 * Se pasan los dos parametros -- InputStream y Cipher --, 
	 * el InputStream con valor null y Cipher instanciado. 
	 * Debe arrojar una excepcion NullPointerException
	 */
	public void testCipherInputStreamConstructor002() {
		//insert code testing basic functionality
		InputStream is = null;
		
		CipherInputStream cis;
		
		//is null point
		try {
			cis = new CipherInputStream(is, Util.getInstanceCipherInited());
			cis.close();
			fail("if the specified input stream or cipher is null, " 
					+"a NullPointerException may be thrown later when they are used"
					+", it did? No");
		} catch (NullPointerException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	/*
	 * Se pasan los dos parametros -- InputStream y Cipher --, 
	 * el InputStream instanciado y Cipher con valor null. 
	 * Debe arrojar una excepcion NullPointerException 
	 */
	public void testCipherInputStreamConstructor003() {
		//insert code testing basic functionality
		Cipher c = null;
		
		CipherInputStream cis;
		
		//c null point
		try {
			cis = new CipherInputStream(inputStream, c);
			cis.close();
			fail("if the specified input stream or cipher is null, " 
					+"a NullPointerException may be thrown later when they are used"
					+", it did? No");
		} catch (NullPointerException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	
	public void testCipherInputStreamConstructor004() {
		assertTrue(cipherInputStream instanceof CipherInputStream);
	}
	/*
	 * Se controla que inicialmente no hay byte para leer sin bloqueo.
	 */
	public void testAvailable001() {
		try {
			assertEquals(cipherInputStream.available(), 0);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	
	/*
	 * Se realisa una lectura, verificando que luego si hay byte para leer sin bloqueo.
	 * Queda pendiente logara que arroje excepcion. 
	 */
	public void testAvailable002() {	
		try {
			cipherInputStream.read();
			assertTrue(cipherInputStream.available() > 0);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	
	/*
	 * Se realisa una lectura para garantizar un available mayor a cero, 
	 * luego se cierra para finalmente verificar la imposivilitud de lectura. 
	 */
	public void testClose001() {
		
		try {
			cipherInputStream.read();
			assertTrue(cipherInputStream.available() > 0);
			cipherInputStream.close();
			assertEquals(cipherInputStream.available(), 0);
			cipherInputStream.read();
			assertEquals(cipherInputStream.available(), 0);
			
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}	
		
	}	
	
	/* 
	 * Se instancia con un inputStream que emite interrupción. 
	 * Se verifica que la misma no sea capturada.
	 */
	public void testClose002() {
		try {
			cipherInputStream = new CipherInputStream(new IOExcepctionInputStream(), Util.getInstanceCipherInited());
			cipherInputStream.close();
		} catch (IOException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}	
		
	}	
	
	/*
	 * Se intancia con un inputStream que no permite ''make'' y ''rest''. 
	 */
	public void testMarkSupported001() {
		try {
			cipherInputStream = new CipherInputStream(new IOExcepctionInputStream(false), Util.getInstanceCipherInited());
			assertEquals(cipherInputStream.markSupported(), false);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	
	/*
	 * Se intancia con un inputStream que permite ''make'' y ''rest''
	 *  Verifica que lo mismo no lo permite.
	 */
	public void testMarkSupported002() {
		try {
			cipherInputStream = new CipherInputStream(new IOExcepctionInputStream(true), Util.getInstanceCipherInited());
			assertEquals(cipherInputStream.markSupported(), false);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	
	/*
	 * Prueva de arroje la excepción. en la lectura. 
	 * El ''inputStream'' solo permite 10 caracteres y luego interrumpe.
	 * Aun así, los caracteres leidos estan encriptados, 
	 * dando mayor cantidad de caracteres para leer en 'cipherInputStream'.   
	 * Por ello, se lee hasta que arroje la excepción.
	 */
	
	public void testRead001() {
		try{
			cipherInputStream = new CipherInputStream(new IOExcepctionInputStream(), Util.getInstanceCipherInited());
			while(true) {
				cipherInputStream.read();
			}
		} catch (IOException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		} 
		
	}
	
	
	/*
	 * Lee hasta que no pueda mas, controla que los valores esten entre 0 y 255, 
	 * siendo -1 el valor con que termina al no tener mas byte para leer. 
	 */
	public void testRead002() {
		
		try {
			for(int j=0; j < bufLen; j++, j++) {
				int i=cipherInputStream.read();
				assertTrue(i >= 0);
				assertTrue(i <= 255);
			}
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	
	/*
	 * Lee hasta que no pueda mas, controla que los valores esten entre 0 y 255, 
	 * siendo -1 el valor con que termina al no tener mas byte para leer. 
	 */
	public void testRead002bis() {
		
		try {
			int i=0;
			do {
				assertTrue(i >= 0);
				assertTrue(i <= 255);
				i = cipherInputStream.read();
			} while (i != -1);
			assertEquals(i, -1);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	
	/*
	 * Con un ''inputStream'' acotado, que al terminar la cota arroja excepción.
	 * Le paso un buffer grande, esperando que la excepción sea arrojada.
	 * 
	 */
	
	public void testRead003() {
		try{
			cipherInputStream = new CipherInputStream(new IOExcepctionInputStream(), Util.getInstanceCipherInited());
			cipherInputStream.read(new byte[10]);
			cipherInputStream.read(new byte[10]);
			assertTrue(false);
		} catch (IOException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	
	/*
	 * Buffer vacio. Retorna cero.
	 */
	
	public void testRead004() {
		try{
			assertEquals(cipherInputStream.read(new byte[0]), 0);
			assertEquals(cipherInputStream.read(new byte[0]), 0);
			assertEquals(cipherInputStream.read(new byte[0]), 0);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	
	/*
	 * Leo de a 5 byte, guardando siempre el valor del ultimo leido.
	 * Controlo que el buffer no sea actualizado en los lugares no indicados y
	 * que read retorne -1 (sabiendo que significa no mas datos para leer)
	 */
	public void testRead005() {
		try{
			byte[] b = new byte[5];
			int i = 5;
			byte last;
			do {
				assertEquals(i, 5);
				i = cipherInputStream.read(b);
				last = b[4];
			} while (i == 5);
			assertEquals(b[4], last);
			assertEquals(cipherInputStream.read(b), -1);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	/*
	 * valor negativo de largo, retorna 0 caracteres leidos
	 */
	
	public void testRead006() {
		try{
			assertEquals(cipherInputStream.read(null, 0, -5),0);
			assertEquals(cipherInputStream.read(null, 0, -1),0);
			assertEquals(cipherInputStream.read(null, 0, 0),0);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	
	/*
	 * Leo un rango, aseguro que a los lados, nada fue cambiado.
	 */
	public void testRead007() {
		try{
			byte[] b = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14};
			assertEquals(cipherInputStream.read(b, 2, 3),3);
			assertEquals(b[1],1);
			assertEquals(b[5],5);
			
			int len  = cipherInputStream.available();
			assertEquals(cipherInputStream.read(b, 3, len+5),len);
			assertEquals(b[len+4],len+4);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	
	/*
	 * Leo un rango con maximo mayor a la cantidad, aseguro que a lea todos los caracteres 
	 * restantes. 
	 */
	public void testRead008() {
		try{
			byte[] b = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14};
			cipherInputStream.read();	
			int len  = cipherInputStream.available();
			assertEquals(cipherInputStream.read(b, 0, len+5),len);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	
	/*
	 * Paso un buffer null, control avance en el stream. 
	 */
	public void testRead009() {
		try{
			byte[] b = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14};
			cipherInputStream.read();	
			int len  = cipherInputStream.available();
			assertEquals(cipherInputStream.read(null, -12, len-2),len-2);
			assertEquals(cipherInputStream.read(b, 0, 6),2);
			assertEquals(cipherInputStream.read(b, 0, 2),-1);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	
	/*
	 * Con un ''inputStream'' acotado, que al terminar la cota arroja excepción.
	 */
	
	public void testRead010() {
		try{
			cipherInputStream = new CipherInputStream(new IOExcepctionInputStream(), Util.getInstanceCipherInited());
			cipherInputStream.read(null, 0, 200);
			cipherInputStream.read(null, 0, 200);
			assertTrue(false);
		} catch (IOException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	
	
	/*
	 *  Skip valores negativos, no deve hacer nada. 
	 */
	
	public void testSkip001() {
		
		try{	
			cipherInputStream.read();
			int i = cipherInputStream.available();
			assertEquals(cipherInputStream.skip(-1), 0);
			assertEquals(cipherInputStream.skip(0), 0);
			assertEquals(cipherInputStream.skip(-203), 0);
			assertEquals(cipherInputStream.available(), i);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	
	/*
	 *  Skip valores mas que 'available', salta solo 'available'. 
	 */
	
	public void testSkip002() {
		
		try{	
			cipherInputStream.read();
			int i = cipherInputStream.available();
			assertEquals(cipherInputStream.skip(i+5), i);
			assertEquals(cipherInputStream.available(), 0);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	
	
	/*
	 *  Skip 3, 'available' se disminulle en 3. 
	 */
	
	public void testSkip003() {
		
		try{	
			cipherInputStream.read();
			int i = cipherInputStream.available();
			assertEquals(cipherInputStream.skip(3), 3);
			assertEquals(cipherInputStream.available(), i-3);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
	
	public void testSkip004() {
		
		try{	
			cipherInputStream.read();
			@SuppressWarnings("unused") int i = cipherInputStream.available();
			assertEquals(cipherInputStream.skip(0), 0);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}
}