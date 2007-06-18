package org.apache.harmony.luni.tests.java.io.unix;

import java.io.File;

import junit.framework.TestCase;

/**
 * Please note that this case can only be passed on Linux due to some file
 * system dependent reason.
 * 
 */
public class UnixFileTest extends TestCase {
	private boolean root = false;
	
	private File testFile;

	private File testDir;

	/**
	 * @tests java.io.File#canExecute()
	 * 
	 * @since 1.6
	 */
	public void test_canExecute() {		
		assertFalse(testFile.canExecute());
		assertTrue(testFile.setExecutable(true, false));
		assertTrue(testFile.canExecute());
		assertTrue(testFile.setExecutable(true, true));
		assertTrue(testFile.canExecute());

		assertTrue(testFile.setExecutable(false, false));
		assertFalse(testFile.canExecute());
		assertTrue(testFile.setExecutable(false, true));
		assertFalse(testFile.canExecute());

		assertTrue(testFile.setExecutable(true, false));
		assertTrue(testFile.canExecute());

		// tests directory
		assertTrue(testDir.canExecute());
		assertTrue(testDir.setExecutable(false, true));
		if (root) {
			assertTrue(testDir.canExecute());
		} else {
			assertFalse(testDir.canExecute());			
		}		
		assertTrue(testDir.setExecutable(true, false));
		assertTrue(testDir.canExecute());
	}

	/**
	 * @tests java.io.File#setExecutable(boolean, boolean)
	 * 
	 * @since 1.6
	 */
	public void test_setExecutableZZ() {		
		// setExecutable(true, true/false)
		assertFalse(testFile.canExecute());
		assertTrue(testFile.setExecutable(true, false));
		assertTrue(testFile.canExecute());
		assertTrue(testFile.setExecutable(true, true));
		assertTrue(testFile.canExecute());

		// setExecutable(false, true/false)
		assertTrue(testFile.setExecutable(false, true));
		if (root) {
			assertTrue(testFile.canExecute());			
		} else {
			assertFalse(testFile.canExecute());			
		}		
		assertTrue(testFile.setExecutable(false, false));
		assertFalse(testFile.canExecute());

		// tests directory
		assertTrue(testDir.canExecute());
		assertTrue(testDir.setExecutable(false, true));
		if (root) {
			assertTrue(testDir.canExecute());
		} else {
			assertFalse(testDir.canExecute());
		}
		assertTrue(testDir.setExecutable(false, false));
		if (root) {
			assertTrue(testDir.canExecute());
		} else {
			assertFalse(testDir.canExecute());
		}

		assertTrue(testDir.setExecutable(true, true));
		assertTrue(testDir.canExecute());
		assertTrue(testDir.setExecutable(true, false));
		assertTrue(testDir.canExecute());
	}

	/**
	 * @tests java.io.File#setExecutable(boolean)
	 * 
	 * @since 1.6
	 */
	public void test_setExecutableZ() {
		// So far this method only deals with the situation that the user is the
		// owner of the file
		assertTrue(testFile.setExecutable(true));
		assertTrue(testFile.canExecute());
		assertTrue(testFile.setExecutable(false));
		assertFalse(testFile.canExecute());
		assertTrue(testFile.setExecutable(true));

		// tests directory
		assertTrue(testDir.canExecute());
		assertTrue(testDir.setExecutable(false));
		if (root) {
			assertTrue(testDir.canExecute());
		} else {
			assertFalse(testDir.canExecute());			
		}		
		assertTrue(testDir.setExecutable(true));
		assertTrue(testDir.canExecute());
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		testFile = File.createTempFile("testfile", null);
		testDir = new File(System.getProperty("java.io.tmpdir") + "/temp");
		if (!testDir.exists()) {
			testDir.mkdir();
		}
		root = System.getProperty("user.name").equals("root");
	}

	@Override
	protected void tearDown() throws Exception {
		testFile.delete();
		testDir.delete();
		testFile = null;
		testDir = null;
		root = false;
		super.tearDown();
	}

}
