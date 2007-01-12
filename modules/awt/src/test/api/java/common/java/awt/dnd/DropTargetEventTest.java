package java.awt.dnd;

import junit.framework.TestCase;

public class DropTargetEventTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(DropTargetEventTest.class);
    }

    /**
     * Test method for
     * {@link java.awt.dnd.DropTargetEvent#DropTargetEvent(java.awt.dnd.DropTargetContext)}.
     */
    public void testDropTargetEventDropTargetContext() {
        // Regression test for HARMONY-2430
        try {
            new DropTargetEvent(null);
            fail("NPE was not thrown");
        } catch (NullPointerException ex) {
            // passed
        }

        final DropTarget dt = new DropTarget();
        final DropTargetEvent e = new DropTargetEvent(dt.getDropTargetContext());

        assertSame(dt, e.getSource());
    }
}
