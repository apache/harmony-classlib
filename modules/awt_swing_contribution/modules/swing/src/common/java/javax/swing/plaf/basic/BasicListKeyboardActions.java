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

import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;

import org.apache.harmony.x.swing.Utilities;


final class BasicListKeyboardActions {
    private static AbstractAction selectPreviousRowAction = new AbstractAction() {
        public void actionPerformed(final ActionEvent e) {
            JList list = (JList)e.getSource();

            int previousIndex = getPreviousRow(list);
            if (previousIndex == -1) {
                return;
            }

            list.setSelectedIndex(previousIndex);
            list.ensureIndexIsVisible(previousIndex);
        }
    };

    private static AbstractAction selectPreviousRowExtendSelectionAction = new AbstractAction() {
        public void actionPerformed(final ActionEvent e) {
            JList list = (JList)e.getSource();

            int previousIndex = getPreviousRow(list);
            if (previousIndex == -1) {
                return;
            }

            list.setValueIsAdjusting(true);
            list.removeSelectionInterval(list.getAnchorSelectionIndex(), list.getLeadSelectionIndex());
            list.addSelectionInterval(list.getAnchorSelectionIndex(), previousIndex);
            list.setValueIsAdjusting(false);
            list.ensureIndexIsVisible(previousIndex);
        }
    };

    private static AbstractAction selectNextRowAction = new AbstractAction() {
        public void actionPerformed(final ActionEvent e) {
            JList list = (JList)e.getSource();
            int nextIndex = getNextRow(list);
            if (nextIndex == -1) {
                return;
            }

            list.setSelectedIndex(nextIndex);
            list.ensureIndexIsVisible(nextIndex);
        }
    };

    private static AbstractAction selectNextRowExtendSelectionAction = new AbstractAction() {
        public void actionPerformed(final ActionEvent e) {
            JList list = (JList)e.getSource();
            int nextIndex = getNextRow(list);
            if (nextIndex == -1) {
                return;
            }

            list.setValueIsAdjusting(true);
            list.removeSelectionInterval(list.getAnchorSelectionIndex(), list.getLeadSelectionIndex());
            list.addSelectionInterval(list.getAnchorSelectionIndex(), nextIndex);
            list.setValueIsAdjusting(false);
            list.ensureIndexIsVisible(nextIndex);
        }
    };

    private static AbstractAction selectPreviousColumnAction = new AbstractAction() {
        public void actionPerformed(final ActionEvent e) {
            JList list = (JList)e.getSource();
            int previousIndex = getPreviousColumn(list);
            if (previousIndex == -1) {
                return;
            }

            list.setSelectedIndex(previousIndex);
            list.ensureIndexIsVisible(previousIndex);
        }
    };

    private static AbstractAction selectPreviousColumnExtendSelectionAction = new AbstractAction() {
        public void actionPerformed(final ActionEvent e) {
            JList list = (JList)e.getSource();

            int previousIndex = getPreviousColumn(list);
            if (previousIndex == -1) {
                return;
            }

            list.setValueIsAdjusting(true);
            list.removeSelectionInterval(list.getAnchorSelectionIndex(), list.getLeadSelectionIndex());
            list.addSelectionInterval(list.getAnchorSelectionIndex(), previousIndex);
            list.setValueIsAdjusting(false);
            list.ensureIndexIsVisible(previousIndex);
        }
    };

    private static AbstractAction selectNextColumnAction = new AbstractAction() {
        public void actionPerformed(final ActionEvent e) {
            JList list = (JList)e.getSource();

            int nextIndex = getNextColumn(list);
            if (nextIndex == -1) {
                return;
            }

            list.setSelectedIndex(nextIndex);
            list.ensureIndexIsVisible(nextIndex);
        }
    };

    private static AbstractAction selectNextColumnExtendSelectionAction = new AbstractAction() {
        public void actionPerformed(final ActionEvent e) {
            JList list = (JList)e.getSource();

            int nextIndex = getNextColumn(list);
            if (nextIndex == -1) {
                return;
            }

            list.setValueIsAdjusting(true);
            list.removeSelectionInterval(list.getAnchorSelectionIndex(), list.getLeadSelectionIndex());
            list.addSelectionInterval(list.getAnchorSelectionIndex(), nextIndex);
            list.setValueIsAdjusting(false);
            list.ensureIndexIsVisible(nextIndex);
        }
    };

    private static AbstractAction selectFirstRowAction = new AbstractAction() {
        public void actionPerformed(final ActionEvent e) {
            JList list = (JList)e.getSource();
            if (list.getModel().getSize() == 0) {
                return;
            }

            list.setSelectedIndex(0);
            list.ensureIndexIsVisible(0);
        }
    };

    private static AbstractAction selectFirstRowExtendSelectionAction = new AbstractAction() {
        public void actionPerformed(final ActionEvent e) {
            JList list = (JList)e.getSource();
            if (list.getModel().getSize() == 0) {
                return;
            }

            int beginIndex = list.getAnchorSelectionIndex();
            if (beginIndex != -1) {
                list.setValueIsAdjusting(true);
                list.removeSelectionInterval(list.getAnchorSelectionIndex(), list.getLeadSelectionIndex());
                list.addSelectionInterval(beginIndex, 0);
                list.setValueIsAdjusting(false);
            }

            list.ensureIndexIsVisible(0);
        }
    };

    private static AbstractAction selectLastRowAction = new AbstractAction() {
        public void actionPerformed(final ActionEvent e) {
            JList list = (JList)e.getSource();
            if (list.getModel().getSize() == 0) {
                return;
            }

            int lastIndex = list.getModel().getSize() - 1;
            list.setSelectedIndex(lastIndex);
            list.ensureIndexIsVisible(lastIndex);
        }
    };

    private static AbstractAction selectLastRowExtendSelectionAction = new AbstractAction() {
        public void actionPerformed(final ActionEvent e) {
            JList list = (JList)e.getSource();
            if (list.getModel().getSize() == 0) {
                return;
            }

            int lastIndex = list.getModel().getSize() - 1;
            int beginIndex = list.getAnchorSelectionIndex();
            if (beginIndex != -1) {
                list.setValueIsAdjusting(true);
                list.removeSelectionInterval(list.getAnchorSelectionIndex(), list.getLeadSelectionIndex());
                list.addSelectionInterval(beginIndex, lastIndex);
                list.setValueIsAdjusting(false);
            }

            list.ensureIndexIsVisible(lastIndex);
        }
    };

    private static AbstractAction scrollUpAction = new AbstractAction() {
        public void actionPerformed(final ActionEvent e) {
            JList list = (JList)e.getSource();

            int upIndex = getScrollUpIndex(list);
            if (upIndex == -1) {
                return;
            }

            list.setSelectedIndex(upIndex);
            list.ensureIndexIsVisible(upIndex);
        }
    };

    private static AbstractAction scrollUpExtendSelectionAction = new AbstractAction() {
        public void actionPerformed(final ActionEvent e) {
            JList list = (JList)e.getSource();

            int upIndex = getScrollUpIndex(list);
            if (upIndex == -1) {
                return;
            }

            int beginIndex = list.getAnchorSelectionIndex();
            if (beginIndex != -1) {
                list.setValueIsAdjusting(true);
                list.removeSelectionInterval(list.getAnchorSelectionIndex(), list.getLeadSelectionIndex());
                list.addSelectionInterval(beginIndex, upIndex);
                list.setValueIsAdjusting(false);
            }

            list.ensureIndexIsVisible(upIndex);
        }
    };

    private static AbstractAction scrollDownAction = new AbstractAction() {
        public void actionPerformed(final ActionEvent e) {
            JList list = (JList)e.getSource();

            int downIndex = getScrollDownIndex(list);
            if (downIndex == -1) {
                return;
            }

            list.setSelectedIndex(downIndex);
            list.ensureIndexIsVisible(downIndex);
        }
    };

    private static AbstractAction scrollDownExtendSelectionAction = new AbstractAction() {
        public void actionPerformed(final ActionEvent e) {
            JList list = (JList)e.getSource();

            int downIndex = getScrollDownIndex(list);
            if (downIndex == -1) {
                return;
            }

            int beginIndex = list.getAnchorSelectionIndex();
            if (beginIndex != -1) {
                list.setValueIsAdjusting(true);
                list.removeSelectionInterval(list.getAnchorSelectionIndex(), list.getLeadSelectionIndex());
                list.addSelectionInterval(beginIndex, downIndex);
                list.setValueIsAdjusting(false);
            }

            list.ensureIndexIsVisible(downIndex);
        }
    };

    private static AbstractAction selectAllAction = new AbstractAction() {
        public void actionPerformed(final ActionEvent e) {
            JList list = (JList)e.getSource();
            if (list.getModel().getSize() == 0) {
                return;
            }

            list.setSelectionInterval(0, list.getModel().getSize() - 1);
        }
    };

    private static AbstractAction clearSelectionAction = new AbstractAction() {
        public void actionPerformed(final ActionEvent e) {
            JList list = (JList)e.getSource();
            if (list.getModel().getSize() == 0) {
                return;
            }

            list.clearSelection();
        }
    };


    public static void installKeyboardActions(final JList list) {
        Utilities.installKeyboardActions(list, JComponent.WHEN_FOCUSED, "List.focusInputMap", "List.focusInputMap.RightToLeft");

        list.getActionMap().put("selectPreviousRow", selectPreviousRowAction);
        list.getActionMap().put("selectNextRow", selectNextRowAction);
        list.getActionMap().put("selectPreviousRowExtendSelection", selectPreviousRowExtendSelectionAction);
        list.getActionMap().put("selectNextRowExtendSelection", selectNextRowExtendSelectionAction);

        list.getActionMap().put("selectPreviousColumn", selectPreviousColumnAction);
        list.getActionMap().put("selectNextColumn", selectNextColumnAction);
        list.getActionMap().put("selectPreviousColumnExtendSelection", selectPreviousColumnExtendSelectionAction);
        list.getActionMap().put("selectNextColumnExtendSelection", selectNextColumnExtendSelectionAction);

        list.getActionMap().put("selectFirstRow", selectFirstRowAction);
        list.getActionMap().put("selectFirstRowExtendSelection", selectFirstRowExtendSelectionAction);
        list.getActionMap().put("selectLastRow", selectLastRowAction);
        list.getActionMap().put("selectLastRowExtendSelection", selectLastRowExtendSelectionAction);

        list.getActionMap().put("scrollUp", scrollUpAction);
        list.getActionMap().put("scrollUpExtendSelection", scrollUpExtendSelectionAction);
        list.getActionMap().put("scrollDown", scrollDownAction);
        list.getActionMap().put("scrollDownExtendSelection", scrollDownExtendSelectionAction);

        list.getActionMap().put("selectAll", selectAllAction);
        list.getActionMap().put("clearSelection", clearSelectionAction);

        list.getActionMap().put("copy", TransferHandler.getCopyAction());
        list.getActionMap().put("paste", TransferHandler.getPasteAction());
        list.getActionMap().put("cut", TransferHandler.getCutAction());
    }

    public static void uninstallKeyboardActions(final JList list) {
        Utilities.uninstallKeyboardActions(list, JComponent.WHEN_FOCUSED);
    }


    private static int getNextRow(final JList list) {
        if (list.getModel().getSize() == 0) {
            return -1;
        }

        int currectSelection = list.getLeadSelectionIndex();
        if (currectSelection == -1 || currectSelection >= list.getModel().getSize()) {
            list.setSelectedIndex(0);
            return -1;
        }

        BasicListUI ui = (BasicListUI)list.getUI();
        ui.maybeUpdateLayoutState();
        BasicListUI.LayoutStrategy strategy = ui.layouter.getLayoutStrategy();

        int selectedRow = strategy.getRow(currectSelection);
        int selectedColumn = strategy.getColumn(currectSelection);
        if (selectedRow < strategy.getRowCount() - 1 && strategy.getIndex(selectedRow + 1, selectedColumn) < list.getModel().getSize()) {
            return strategy.getIndex(selectedRow + 1, selectedColumn);
        } else if (list.getLayoutOrientation() == JList.VERTICAL_WRAP && selectedColumn < strategy.getColumnCount() - 1 && strategy.getIndex(0, selectedColumn + 1) < list.getModel().getSize()) {
            return strategy.getIndex(0, selectedColumn + 1);
        }

        return -1;
    }

    private static int getPreviousRow(final JList list) {
        if (list.getModel().getSize() == 0) {
            return -1;
        }

        int currectSelection = list.getLeadSelectionIndex();
        if (currectSelection == -1 || currectSelection >= list.getModel().getSize()) {
            list.setSelectedIndex(list.getModel().getSize() - 1);
            return -1;
        }

        BasicListUI ui = (BasicListUI)list.getUI();
        ui.maybeUpdateLayoutState();
        BasicListUI.LayoutStrategy strategy = ui.layouter.getLayoutStrategy();

        int selectedRow = strategy.getRow(currectSelection);
        int selectedColumn = strategy.getColumn(currectSelection);
        if (selectedRow > 0) {
            return strategy.getIndex(selectedRow - 1, selectedColumn);
        } else if (list.getLayoutOrientation() == JList.VERTICAL_WRAP && selectedColumn > 0) {
            return strategy.getIndex(strategy.getRowCount() - 1, selectedColumn - 1);
        }

        return -1;
    }

    private static int getNextColumn(final JList list) {
        if (list.getModel().getSize() == 0) {
            return -1;
        }

        int currectSelection = list.getLeadSelectionIndex();
        if (currectSelection == -1 || currectSelection >= list.getModel().getSize()) {
            list.setSelectedIndex(0);
            return -1;
        }

        BasicListUI ui = (BasicListUI)list.getUI();
        ui.maybeUpdateLayoutState();
        BasicListUI.LayoutStrategy strategy = ui.layouter.getLayoutStrategy();

        int selectedRow = strategy.getRow(currectSelection);
        int selectedColumn = strategy.getColumn(currectSelection);
        if (selectedColumn < strategy.getColumnCount() - 1) {
            if (strategy.getIndex(selectedRow, selectedColumn + 1) < list.getModel().getSize()) {
                return strategy.getIndex(selectedRow, selectedColumn + 1);
            } else {
                return list.getModel().getSize() - 1;
            }
        } else if (list.getLayoutOrientation() == JList.VERTICAL_WRAP && selectedRow < strategy.getRowCount() - 1 && strategy.getIndex(selectedRow + 1, selectedColumn) < list.getModel().getSize()) {
            return strategy.getIndex(selectedRow + 1, selectedColumn);
        }

        return -1;
    }

    private static int getPreviousColumn(final JList list) {
        if (list.getModel().getSize() == 0) {
            return -1;
        }

        int currectSelection = list.getLeadSelectionIndex();
        if (currectSelection == -1 || currectSelection >= list.getModel().getSize()) {
            list.setSelectedIndex(list.getModel().getSize() - 1);
            return -1;
        }

        BasicListUI ui = (BasicListUI)list.getUI();
        ui.maybeUpdateLayoutState();
        BasicListUI.LayoutStrategy strategy = ui.layouter.getLayoutStrategy();

        int selectedRow = strategy.getRow(currectSelection);
        int selectedColumn = strategy.getColumn(currectSelection);
        if (selectedColumn > 0) {
            return strategy.getIndex(selectedRow, selectedColumn - 1);
        } else if (list.getLayoutOrientation() == JList.VERTICAL_WRAP && selectedRow > 0) {
            return strategy.getIndex(selectedRow - 1, selectedColumn);
        }

        return -1;
    }

    private static int getScrollDownIndex(final JList list) {
        if (list.getModel().getSize() == 0) {
            return -1;
        }

        int currentSelection = list.getLeadSelectionIndex();
        int lastVisible = list.getLastVisibleIndex();
        if (lastVisible != currentSelection || lastVisible == list.getModel().getSize() - 1) {
            return lastVisible;
        } else {
            Rectangle visibleRect = list.getVisibleRect();
            int i = lastVisible + 1;
            while (i < list.getModel().getSize() - 1 && list.getCellBounds(lastVisible + 1, i).height < visibleRect.height) {
                i++;
            }

            return i;
        }
    }

    private static int getScrollUpIndex(final JList list) {
        if (list.getModel().getSize() == 0) {
            return -1;
        }

        int currentSelection = list.getLeadSelectionIndex();

        int firstVisible = list.getFirstVisibleIndex();
        if (firstVisible != currentSelection || firstVisible == 0) {
            return firstVisible;
        } else {
            Rectangle visibleRect = list.getVisibleRect();
            int i = firstVisible - 1;
            while (i > 0 && list.getCellBounds(firstVisible - 1, i).height < visibleRect.height) {
                i--;
            }

            return i;
        }
    }
}
