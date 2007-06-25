/* Licensed to the Apache Software Foundation (ASF) under one or more
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

package org.apache.harmony.luni.tests.java.io;

import java.io.File;
import java.io.IOException;

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
     * @tests java.io.File#mkdir() 
     * @throws IOException
     */
    public void test_mkdir() throws IOException {
        // Test for method boolean java.io.File.mkdir() in Windows Platform

        String base = System.getProperty("user.dir");
        // Old test left behind "garbage files" so this time it creates a
        // directory
        // that is guaranteed not to already exist (and deletes it afterward.)
        int dirNumber = 1;
        boolean dirExists = true;
        File dir = new File(base, String.valueOf(dirNumber));
        while (dirExists) {
            if (dir.exists()) {
                dirNumber++;
                dir = new File(base, String.valueOf(dirNumber));
            } else {
                dirExists = false;
            }
        }
        assertTrue("mkdir failed", dir.mkdir() && dir.exists());
        dir.deleteOnExit();        
        String newbase = new String(dir + File.separator);
        
        dir = new File(newbase, ".abcd");
        assertTrue("mkdir " + dir.getCanonicalPath() + " failed",
                dir.mkdir() && dir.exists() && !(new File(newbase,"abcd")).exists());
        dir.deleteOnExit();        

        String []ss1 = {
                ".abcd" + File.separator + "." + File.separator + "dir1",
                ".abcd" + File.separator + ".." + File.separator + "dir2",
                ".abcd" + File.separator + "." + File.separator + "." + File.separator + "dir3",
                "12" + File.separator + "34" + File.separator + ".." + File.separator + ".." + File.separator + "dir4",
                "12" + File.separator + ".." + File.separator + "34" + File.separator + ".." + File.separator + "dir5",
                ".abcd." + File.separator + ".." + File.separator + "dir6.",
                ".abcd.." + File.separator + "dir7",
                ".abcd.." + File.separator + ".." + File.separator + "dir8"
        };
        String []ss2 = {
                ".abcd" + File.separator + "dir1",
                "dir2",
                ".abcd" + File.separator + "dir3",
                "dir4",
                "dir5",
                "dir6",
                ".abcd" + File.separator + "dir7",
                "dir8"                
        };
        for (int i=0; i<ss1.length; i++)
        {
            dir = new File(newbase, ss1[i]);
            assertTrue("mkdir " + dir.getCanonicalPath() + " failed",
                    dir.mkdir() && dir.exists());
            dir = new File(newbase, ss2[i]);
            assertTrue("mkdir " + dir.getCanonicalPath() + " failed",
                    dir.exists());
            dir.deleteOnExit();
        }
    }

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
