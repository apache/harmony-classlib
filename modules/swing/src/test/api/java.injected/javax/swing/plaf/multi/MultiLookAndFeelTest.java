package javax.swing.plaf.multi;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;

import junit.framework.TestCase;

public class MultiLookAndFeelTest extends TestCase {

	private MultiLookAndFeel mlaf = new MultiLookAndFeel();

	/*
	 * Test method for
	 * 'javax.swing.plaf.multi.MultiLookAndFeel.isNativeLookAndFeel()'
	 */
	public void testIsNativeLookAndFeel() {
		assertFalse(mlaf.isNativeLookAndFeel());
	}

	/*
	 * Test method for
	 * 'javax.swing.plaf.multi.MultiLookAndFeel.isSupportedLookAndFeel()'
	 */
	public void testIsSupportedLookAndFeel() {
		assertTrue(mlaf.isSupportedLookAndFeel());
	}

	/*
	 * Test method for 'javax.swing.plaf.multi.MultiLookAndFeel.getName()'
	 */
	public void testGetName() {
		assertEquals(mlaf.getName(), "Multiplexing Look and Feel"); //$NON-NLS-1$
	}

	/*
	 * Test method for 'javax.swing.plaf.multi.MultiLookAndFeel.getID()'
	 */
	public void testGetID() {
		assertEquals(mlaf.getID(), "Multiplex"); //$NON-NLS-1$
	}

	/*
	 * Test method for
	 * 'javax.swing.plaf.multi.MultiLookAndFeel.createUIs(ComponentUI, Vector,
	 * JComponent)'
	 */
	public void testCreateUIs() {

		JButton button = new JButton();
		JLabel label = new JLabel();
		ComponentUI buttonUI = UIManager.getUI(button);
		ComponentUI labelUI = UIManager.getUI(label);
		MultiButtonUI multiButtonUI = new MultiButtonUI();
		MultiLabelUI multiLabelUI = new MultiLabelUI();
		LookAndFeel auxLaf = new SyserrLookAndFeel();

		// without auxiliary look and feels createUIs returns UI fron default
		// look and feel
		assertEquals(buttonUI, MultiLookAndFeel.createUIs(multiButtonUI,
				multiButtonUI.uis, button));

		UIManager.addAuxiliaryLookAndFeel(auxLaf);

		// SyserrLookAndFeel contains UI for button so createUIs should return
		// MultiButtonUI
		assertEquals(multiButtonUI, MultiLookAndFeel.createUIs(multiButtonUI,
				multiButtonUI.uis, button));
		// But SyserrLookAndFeel doesn't contain UI for JLabel so createUIs
		// should return UI from default laf
		assertEquals(labelUI, MultiLookAndFeel.createUIs(multiLabelUI,
				multiLabelUI.uis, label));

		UIManager.removeAuxiliaryLookAndFeel(auxLaf);
	}

	/*
	 * Test method for 'javax.swing.plaf.multi.MultiLookAndFeel.getDefaults()'
	 * Defaults contains references to Multi classes only
	 */
	public void testGetDefaults() {
		assertEquals(mlaf.getDefaults().get("ButtonUI"), //$NON-NLS-1$
				"javax.swing.plaf.multi.MultiButtonUI"); //$NON-NLS-1$
		assertNull(mlaf.getDefaults().get("Button.background")); //$NON-NLS-1$
	}

	/*
	 * Test method for
	 * 'javax.swing.plaf.multi.MultiLookAndFeel.uisToArray(Vector)'
	 */
	@SuppressWarnings("unchecked")
	public void testUisToArray() {

		assertEquals(0, MultiLookAndFeel.uisToArray(null).length);
		assertNull(MultiLookAndFeel.uisToArray(new Vector()));

		Vector v = new Vector();
		ComponentUI content = new SyserrButtonUI();
		v.add(content);
		assertSame(content, MultiLookAndFeel.uisToArray(v)[0]);

		v.add(new Object());
		try {
			MultiLookAndFeel.uisToArray(v);
			fail();
		} catch (Exception e) {
			// Correct behavior. The exception isn't described in spec
		}
	}
}
