package java.awt;

import junit.framework.TestCase;

public class DisplayModeTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(DisplayModeTest.class);
    }

    /**
     * Test method for {@link java.awt.DisplayMode#equals(java.lang.Object)}.
     */
    public void testEqualsObject() {
        assertFalse(new DisplayMode(1, 2, 3, 10).equals((Object) null));
        assertFalse(new DisplayMode(1, 2, 3, 10).equals(new Object()));
    }

    /**
     * Test method for {@link java.awt.DisplayMode#equals(java.awt.DisplayMode)}.
     */
    public void testEqualsDisplayMode() {
        // Regression test for HARMONY-2444
        assertFalse(new DisplayMode(1, 2, 3, 10).equals((DisplayMode) null));
    }
}
