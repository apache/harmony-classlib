package org.apache.harmony.luni.tests.java.io;

import java.io.File;

import junit.framework.TestCase;

/**
 * Please note that this case can only be passed on Windows due to some file
 * system dependent reason.
 * 
 */

public class WinFileTest extends TestCase {
	private File testFile;

	private File testDir;	

	/**
	 * @tests java.io.File#canExecute()
	 * 
	 * @since 1.6
	 */
	public void test_canExecute() {
		// files and directories are always executable on Windows.
		assertTrue(testFile.canExecute());
		assertFalse(testFile.setExecutable(false, false));
		assertTrue(testFile.canExecute());
		assertTrue(testFile.setExecutable(true));
		assertTrue(testFile.canExecute());

		assertFalse(testDir.setExecutable(false, false));
		assertTrue(testDir.canExecute());
	}
	
	/**
	 * @tests java.io.File#setExecutable(boolean, boolean)
	 * 
	 * @since 1.6
	 */
	public void test_setExecutableZZ() {
		// setExecutable(false, true/false) fails on Windows.
		assertTrue(testFile.canExecute());
		assertFalse(testFile.setExecutable(false, false));
		assertTrue(testFile.canExecute());
		assertFalse(testFile.setExecutable(false, true));
		// setExecutable(true, true/false)
		assertTrue(testFile.setExecutable(true, false));
		assertTrue(testFile.canExecute());
		assertTrue(testFile.setExecutable(true, true));

		// tests directory
		assertTrue(testDir.canExecute());
		assertFalse(testDir.setExecutable(false, true));
		assertTrue(testDir.canExecute());
		assertFalse(testDir.setExecutable(false, false));

		assertTrue(testDir.setExecutable(true, true));
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
		assertFalse(testFile.setExecutable(false));
		// tests directory
		assertTrue(testDir.setExecutable(true));
		assertFalse(testDir.setExecutable(false));
		assertTrue(testDir.canExecute());
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		testFile = File.createTempFile("testfile", null);
		testDir = new File(System.getProperty("java.io.tmpdir"));
	}

	@Override
	protected void tearDown() throws Exception {
		testFile.deleteOnExit();
		testDir.deleteOnExit();
		testFile = null;
		testDir = null;
		super.tearDown();
	}

}
