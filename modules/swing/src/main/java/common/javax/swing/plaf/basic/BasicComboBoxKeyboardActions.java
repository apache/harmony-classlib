/*
 *  Copyright 2005 - 2006 The Apache Software Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * @author Anton Avtamonov
 * @version $Revision$
 */

package javax.swing.plaf.basic;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;

import org.apache.harmony.x.swing.Utilities;


class BasicComboBoxKeyboardActions {
    private abstract static class PassThroughAction extends AbstractAction {
        protected static void passThroughEvent(final ActionEvent event, final String correspondingCommand) {
            JComboBox comboBox = (JComboBox)event.getSource();
            JList popupList = ((BasicComboBoxUI)comboBox.getUI()).popup.getList();
            Action action = popupList.getActionMap().get(correspondingCommand);
            if (action != null) {
                action.actionPerformed(new ActionEvent(popupList, ActionEvent.ACTION_PERFORMED, correspondingCommand, event.getWhen(), event.getModifiers()));
                comboBox.setSelectedIndex(popupList.getSelectedIndex());
            }
        }
    }

    private static AbstractAction selectNextAction = new PassThroughAction() {
        public void actionPerformed(final ActionEvent e) {
            JComboBox comboBox = (JComboBox)e.getSource();
            if (!comboBox.isPopupVisible()) {
                comboBox.showPopup();
            } else {
                passThroughEvent(e, "selectNextRow");
            }
        }
    };

    private static AbstractAction togglePopupAction = new AbstractAction() {
        public void actionPerformed(final ActionEvent e) {
            JComboBox comboBox = (JComboBox)e.getSource();
            comboBox.setPopupVisible(!comboBox.isPopupVisible());
        }
    };

    private static AbstractAction pageUpPassThroughAction = new PassThroughAction() {
        public void actionPerformed(final ActionEvent e) {
            passThroughEvent(e, "scrollUp");
        }
    };

    private static AbstractAction pageDownPassThroughAction = new PassThroughAction() {
        public void actionPerformed(final ActionEvent e) {
            passThroughEvent(e, "scrollDown");
        }
    };

    private static AbstractAction homePassThroughAction = new PassThroughAction() {
        public void actionPerformed(final ActionEvent e) {
            passThroughEvent(e, "selectFirstRow");
        }
    };

    private static AbstractAction endPassThroughAction = new PassThroughAction() {
        public void actionPerformed(final ActionEvent e) {
            passThroughEvent(e, "selectLastRow");
        }
    };


    private static class HidePopupAction extends AbstractAction {
        private final JComboBox comboBox;

        public HidePopupAction(final JComboBox comboBox) {
            this.comboBox = comboBox;
        }

        public void actionPerformed(final ActionEvent e) {
            comboBox.hidePopup();
        }

        public boolean isEnabled() {
            return comboBox.isPopupVisible();
        }
    }

    public static void installKeyboardActions(final JComboBox comboBox) {
        Utilities.installKeyboardActions(comboBox, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, "ComboBox.ancestorInputMap", null);

        comboBox.getActionMap().put("hidePopup", new HidePopupAction(comboBox));
        comboBox.getActionMap().put("enterPressed", new HidePopupAction(comboBox));
        comboBox.getActionMap().put("selectNext", selectNextAction);
        comboBox.getActionMap().put("selectPrevious", new PassThroughAction() {
            public void actionPerformed(final ActionEvent e) {
                passThroughEvent(e, "selectPreviousRow");
            }

            public boolean isEnabled() {
                return comboBox.isPopupVisible();
            }
        });
        comboBox.getActionMap().put("togglePopup", togglePopupAction);
        comboBox.getActionMap().put("spacePopup", new AbstractAction() {
            public void actionPerformed(final ActionEvent e) {
                comboBox.setPopupVisible(!comboBox.isPopupVisible());
            }

            public boolean isEnabled() {
                return !comboBox.isEditable();
            }
        });
        comboBox.getActionMap().put("pageUpPassThrough", pageUpPassThroughAction);
        comboBox.getActionMap().put("pageDownPassThrough", pageDownPassThroughAction);
        comboBox.getActionMap().put("homePassThrough", homePassThroughAction);
        comboBox.getActionMap().put("endPassThrough", endPassThroughAction);
    }

    public static void uninstallKeyboardActions(final JComboBox comboBox) {
        Utilities.uninstallKeyboardActions(comboBox, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
}
