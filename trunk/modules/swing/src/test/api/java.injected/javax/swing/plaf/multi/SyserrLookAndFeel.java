package javax.swing.plaf.multi;

import javax.swing.LookAndFeel;
import javax.swing.UIDefaults;

public class SyserrLookAndFeel extends LookAndFeel {

	UIDefaults uiDefaults;

	@Override
	public String getName() {
		return "SyserrLaf"; //$NON-NLS-1$
	}

	@Override
	public String getID() {
		return "SyserrLaf"; //$NON-NLS-1$
	}

	@Override
	public String getDescription() {
		return "Look and feel for testing Muttiplexing laf"; //$NON-NLS-1$
	}

	@Override
	public boolean isNativeLookAndFeel() {
		return false;
	}

	@Override
	public boolean isSupportedLookAndFeel() {
		return true;
	}

	@Override
	public UIDefaults getDefaults() {

		return new UIDefaults(new Object[] { "ButtonUI", //$NON-NLS-1$
				"javax.swing.plaf.multi.SyserrButtonUI", }) {//$NON-NLS-1$
			@Override
			protected void getUIError(String s) {
				// Remove unneded mesage
			}
		};
	}
}
