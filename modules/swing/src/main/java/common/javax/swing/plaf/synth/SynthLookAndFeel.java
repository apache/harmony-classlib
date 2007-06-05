/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package javax.swing.plaf.synth;

import java.awt.Component;
import java.io.InputStream;
import java.io.Serializable;
import java.text.ParseException;

import javax.swing.JComponent;
import javax.swing.UIDefaults;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicLookAndFeel;

import org.apache.harmony.luni.util.NotImplementedException;
import org.apache.harmony.x.swing.internal.nls.Messages;

public class SynthLookAndFeel extends BasicLookAndFeel implements Serializable {

    private final static String packageName = "javax.swing.plaf.synth";

    private static SynthStyleFactory currentFactory;

    private UIDefaults uiDefaults;

    public static SynthStyle getStyle(JComponent c, Region r) {

        return currentFactory.getStyle(c, r);
    }

    public static SynthStyleFactory getStyleFactory() {

        return currentFactory;
    }

    public static void setStyleFactory(SynthStyleFactory proposed) {
        currentFactory = proposed;
    }

    /**
     * Creates the Synth UI object corresponds JComponent given
     */
    public static ComponentUI createUI(JComponent c) {

        try {

            return (ComponentUI) Class.forName(packageName + c.getUIClassID(),
                    true, Thread.currentThread().getContextClassLoader())
                    .getMethod("createUI", JComponent.class).invoke(null, c); //$NON-NLS-1$

        } catch (Exception e) {

            return null;
        }
    }

    /**
     * Renew the synth styles for the JComponent. This method isn't fully
     * correct, but does what needs
     */
    public static void updateStyles(Component c) {

        if (c instanceof JComponent) {
            ((JComponent) c).revalidate();
        }
    }

    /**
     * Calculates the region corresponds JComponent given
     */
    public static Region getRegion(JComponent c) {

        return Region.getRegionFromUIID(c.getUIClassID());
    }

    @Override
    public String getName() {

        return "Synth Look and Feel"; //$NON-NLS-1$
    }

    @Override
    public String getID() {

        return "Synth"; //$NON-NLS-1$
    }

    @Override
    public String getDescription() {

        return Messages.getString("swing.B4"); //$NON-NLS-1$
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

        if (uiDefaults == null) {
            uiDefaults = new UIDefaults();
            initClassDefaults(uiDefaults);
            initComponentDefaults(uiDefaults);
        }

        return uiDefaults;
    }

    @Override
    public void initialize() {
        // Do nothing
    }

    @Override
    public void uninitialize() {
        // Do nothing
    }

    @SuppressWarnings("unused")
    public void load(InputStream input, Class<?> resourceBase)
            throws NotImplementedException, ParseException,
            IllegalArgumentException {
        /*
         * This class will be implemented with XMLSynthParser
         */
    }

    /** The default implementation returns false */
    public boolean shouldUpdateStyleOnAncestorChanged() {

        return false;
    }

    @SuppressWarnings("nls")
    @Override
    protected void initClassDefaults(UIDefaults defaults) {
        Object[] initDefaults = { "InternalFrameUI",
                packageName + "SynthInternalFrameUI", "ViewportUI",
                packageName + "SynthViewportUI", "ScrollBarUI",
                packageName + "SynthScrollBarUI", "ToolTipUI",
                packageName + "SynthToolTipUI", "MenuItemUI",
                packageName + "SynthMenuItemUI", "MenuUI",
                packageName + "SynthMenuUI", "TextAreaUI",
                packageName + "SynthTextAreaUI", "PopupMenuUI",
                packageName + "SynthPopupMenuUI", "ScrollPaneUI",
                packageName + "SynthScrollPaneUI", "SliderUI",
                packageName + "SynthSliderUI", "ComboBoxUI",
                packageName + "SynthComboBoxUI", "RadioButtonUI",
                packageName + "SynthRadioButtonUI", "FormattedTextFieldUI",
                packageName + "SynthFormattedTextFieldUI", "TreeUI",
                packageName + "SynthTreeUI", "MenuBarUI",
                packageName + "SynthMenuBarUI", "RadioButtonMenuItemUI",
                packageName + "SynthRadioButtonMenuItemUI", "ProgressBarUI",
                packageName + "SynthProgressBarUI", "ToolBarUI",
                packageName + "SynthToolBarUI", "ColorChooserUI",
                packageName + "SynthColorChooserUI", "ToolBarSeparatorUI",
                packageName + "SynthToolBarSeparatorUI", "TabbedPaneUI",
                packageName + "SynthTabbedPaneUI", "DesktopPaneUI",
                packageName + "SynthDesktopPaneUI", "TableUI",
                packageName + "SynthTableUI", "PanelUI",
                packageName + "SynthPanelUI", "CheckBoxMenuItemUI",
                packageName + "SynthCheckBoxMenuItemUI", "PasswordFieldUI",
                packageName + "SynthPasswordFieldUI", "CheckBoxUI",
                packageName + "SynthCheckBoxUI", "TableHeaderUI",
                packageName + "SynthTableHeaderUI", "SplitPaneUI",
                packageName + "SynthSplitPaneUI", "EditorPaneUI",
                packageName + "SynthEditorPaneUI", "ListUI",
                packageName + "SynthListUI", "SpinnerUI",
                packageName + "SynthSpinnerUI", "DesktopIconUI",
                packageName + "SynthDesktopIconUI", "TextFieldUI",
                packageName + "SynthTextFieldUI", "TextPaneUI",
                packageName + "SynthTextPaneUI", "LabelUI",
                packageName + "SynthLabelUI", "ButtonUI",
                packageName + "SynthButtonUI", "ToggleButtonUI",
                packageName + "SynthToggleButtonUI", "OptionPaneUI",
                packageName + "SynthOptionPaneUI", "PopupMenuSeparatorUI",
                packageName + "SynthPopupMenuSeparatorUI", "RootPaneUI",
                packageName + "SynthRootPaneUI", "SeparatorUI",
                packageName + "SynthSeparatorUI" };
        defaults.putDefaults(initDefaults);
    }

}
