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

/**
 * @author Dennis Ushakov
 * @version $Revision$
 */

package javax.accessibility;

public class AccessibleRole extends AccessibleBundle {
    public static final AccessibleRole ALERT = new AccessibleRole("alert"); //$NON-NLS-1$
    public static final AccessibleRole COLUMN_HEADER = new AccessibleRole("columnHeader"); //$NON-NLS-1$
    public static final AccessibleRole CANVAS = new AccessibleRole("canvas"); //$NON-NLS-1$
    public static final AccessibleRole COMBO_BOX = new AccessibleRole("combobox"); //$NON-NLS-1$
    public static final AccessibleRole DESKTOP_ICON = new AccessibleRole("desktopIcon"); //$NON-NLS-1$
    public static final AccessibleRole INTERNAL_FRAME = new AccessibleRole("internalFrame"); //$NON-NLS-1$
    public static final AccessibleRole DESKTOP_PANE = new AccessibleRole("desktopPane"); //$NON-NLS-1$
    public static final AccessibleRole OPTION_PANE = new AccessibleRole("optionPane"); //$NON-NLS-1$
    public static final AccessibleRole WINDOW = new AccessibleRole("window"); //$NON-NLS-1$
    public static final AccessibleRole FRAME = new AccessibleRole("frame"); //$NON-NLS-1$
    public static final AccessibleRole DIALOG = new AccessibleRole("dialog"); //$NON-NLS-1$
    public static final AccessibleRole COLOR_CHOOSER = new AccessibleRole("colorChooser"); //$NON-NLS-1$
    public static final AccessibleRole DIRECTORY_PANE = new AccessibleRole("directoryPane"); //$NON-NLS-1$
    public static final AccessibleRole FILE_CHOOSER = new AccessibleRole("fileChooser"); //$NON-NLS-1$
    public static final AccessibleRole FILLER = new AccessibleRole("filler"); //$NON-NLS-1$
    public static final AccessibleRole HYPERLINK = new AccessibleRole("hyperlink"); //$NON-NLS-1$
    public static final AccessibleRole ICON = new AccessibleRole("icon"); //$NON-NLS-1$
    public static final AccessibleRole LABEL = new AccessibleRole("label"); //$NON-NLS-1$
    public static final AccessibleRole ROOT_PANE = new AccessibleRole("rootPane"); //$NON-NLS-1$
    public static final AccessibleRole GLASS_PANE = new AccessibleRole("glassPane"); //$NON-NLS-1$
    public static final AccessibleRole LAYERED_PANE = new AccessibleRole("layeredPane"); //$NON-NLS-1$
    public static final AccessibleRole LIST = new AccessibleRole("list"); //$NON-NLS-1$
    public static final AccessibleRole LIST_ITEM = new AccessibleRole("listItem"); //$NON-NLS-1$
    public static final AccessibleRole MENU_BAR = new AccessibleRole("menuBar"); //$NON-NLS-1$
    public static final AccessibleRole POPUP_MENU = new AccessibleRole("popupMenu"); //$NON-NLS-1$
    public static final AccessibleRole MENU = new AccessibleRole("menu"); //$NON-NLS-1$
    public static final AccessibleRole MENU_ITEM = new AccessibleRole("menuItem"); //$NON-NLS-1$
    public static final AccessibleRole SEPARATOR = new AccessibleRole("separator"); //$NON-NLS-1$
    public static final AccessibleRole PAGE_TAB_LIST = new AccessibleRole("pageTabList"); //$NON-NLS-1$
    public static final AccessibleRole PAGE_TAB = new AccessibleRole("pageTab"); //$NON-NLS-1$
    public static final AccessibleRole PANEL = new AccessibleRole("panel"); //$NON-NLS-1$
    public static final AccessibleRole PROGRESS_BAR = new AccessibleRole("progressBar"); //$NON-NLS-1$
    public static final AccessibleRole PASSWORD_TEXT = new AccessibleRole("passwordText"); //$NON-NLS-1$
    public static final AccessibleRole PUSH_BUTTON = new AccessibleRole("pushButton"); //$NON-NLS-1$
    public static final AccessibleRole TOGGLE_BUTTON = new AccessibleRole("toggleButton"); //$NON-NLS-1$
    public static final AccessibleRole CHECK_BOX = new AccessibleRole("checkBox"); //$NON-NLS-1$
    public static final AccessibleRole RADIO_BUTTON = new AccessibleRole("radioButton"); //$NON-NLS-1$
    public static final AccessibleRole ROW_HEADER = new AccessibleRole("rowHeader"); //$NON-NLS-1$
    public static final AccessibleRole SCROLL_PANE = new AccessibleRole("scrollPane"); //$NON-NLS-1$
    public static final AccessibleRole SCROLL_BAR = new AccessibleRole("scrollBar"); //$NON-NLS-1$
    public static final AccessibleRole VIEWPORT = new AccessibleRole("viewport"); //$NON-NLS-1$
    public static final AccessibleRole SLIDER = new AccessibleRole("slider"); //$NON-NLS-1$
    public static final AccessibleRole SPLIT_PANE = new AccessibleRole("splitPane"); //$NON-NLS-1$
    public static final AccessibleRole TABLE = new AccessibleRole("table"); //$NON-NLS-1$
    public static final AccessibleRole TEXT = new AccessibleRole("text"); //$NON-NLS-1$
    public static final AccessibleRole TREE = new AccessibleRole("tree"); //$NON-NLS-1$
    public static final AccessibleRole TOOL_BAR = new AccessibleRole("toolBar"); //$NON-NLS-1$
    public static final AccessibleRole TOOL_TIP = new AccessibleRole("toolTip"); //$NON-NLS-1$
    public static final AccessibleRole AWT_COMPONENT = new AccessibleRole("awtComponent"); //$NON-NLS-1$
    public static final AccessibleRole SWING_COMPONENT = new AccessibleRole("swingComponent"); //$NON-NLS-1$
    public static final AccessibleRole UNKNOWN = new AccessibleRole("unknown"); //$NON-NLS-1$
    public static final AccessibleRole STATUS_BAR = new AccessibleRole("statusBar"); //$NON-NLS-1$
    public static final AccessibleRole DATE_EDITOR = new AccessibleRole("dateEditor"); //$NON-NLS-1$
    public static final AccessibleRole SPIN_BOX = new AccessibleRole("spinBox"); //$NON-NLS-1$
    public static final AccessibleRole FONT_CHOOSER = new AccessibleRole("fontChooser"); //$NON-NLS-1$
    public static final AccessibleRole GROUP_BOX = new AccessibleRole("groupBox"); //$NON-NLS-1$
    public static final AccessibleRole HEADER = new AccessibleRole("header"); //$NON-NLS-1$
    public static final AccessibleRole FOOTER = new AccessibleRole("footer"); //$NON-NLS-1$
    public static final AccessibleRole PARAGRAPH = new AccessibleRole("paragraph"); //$NON-NLS-1$
    public static final AccessibleRole RULER = new AccessibleRole("ruler"); //$NON-NLS-1$
    public static final AccessibleRole EDITBAR = new AccessibleRole("editBar"); //$NON-NLS-1$
    public static final AccessibleRole PROGRESS_MONITOR = new AccessibleRole("progressMonitor"); //$NON-NLS-1$

    protected AccessibleRole(final String key) {
        this.key = key;
    }
}

