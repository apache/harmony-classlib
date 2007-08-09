package java.awt.datatransfer;

import junit.framework.TestCase;

public class UnsupportedFlavorExceptionTest extends TestCase {

    /**
     * Test method for
     * {@link java.awt.datatransfer.UnsupportedFlavorException#UnsupportedFlavorException(java.awt.datatransfer.DataFlavor)}.
     */
    public void testUnsupportedFlavorException() throws NullPointerException {
        // Regression test for HARMONY-2065
        new UnsupportedFlavorException(null);
    }
}
