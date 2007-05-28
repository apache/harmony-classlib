package javax.swing.plaf.multi;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.ComponentUI;

/**
 * Auxiliary ui for multi package testing. Console mesages has been removed to
 * tidy unit tests console output
 */
public class SyserrButtonUI extends ButtonUI {

    public static ComponentUI createUI(JComponent a) {
        // System.err.println("create");
        return new SyserrButtonUI();
    }

    @Override
    public void paint(Graphics a, JComponent b) {
        // System.err.println("paint");
    }
}
