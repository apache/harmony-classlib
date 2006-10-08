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

package javax.accessibility;

import java.util.ListResourceBundle;

@Deprecated
public class AccessibleResourceBundle extends ListResourceBundle {
    // Localized values representing the accessible roles and states of the accessible components.
    private final Object[][] contents = {
        {"alert", "alert"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"column_header", "column header"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"canvas", "canvas"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"combobox", "combobox"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"desktopIcon", "desktop icon"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"internalFrame", "internal frame"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"desktopPane", "desktop pane"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"optionPane", "option pane"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"window", "window"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"frame", "frame"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"dialog", "dialog"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"colorChooser", "color chooser"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"directoryPane", "directory pane"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"fileChooser", "file chooser"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"filler", "filler"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"hyperlink", "hyperlink"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"icon", "icon"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"label", "label"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"rootPane", "root pane"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"glassPane", "glass pane"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"layeredPane", "layered pane"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"list", "list"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"listItem", "list item"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"menuBar", "menu bar"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"popupMenu", "popup menu"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"menu", "menu"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"menuItem", "menu item"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"separator", "separator"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"pageTabList", "page tab list"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"pageTab", "page tab"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"panel", "panel"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"progressBar", "progress"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"passwordText", "password"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"pushButton", "push button"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"toggleButton", "toggle button"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"checkBox", "check box"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"radioButton", "radio button"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"rowHeader", "row header"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"scrollPane", "scroll pane"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"scrollBar", "scroller"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"viewport", "viewport"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"slider", "slider"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"splitPane", "split pane"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"table", "table"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"text", "text"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"tree", "tree"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"toolBar", "tool bar"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"toolTip", "tool tip"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"awtComponent", "AWT component"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"swingComponent", "Swing component"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"unknown", "unknown"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"statusBar", "status bar"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"dateEditor", "date editor"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"spinBox", "spinner"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"fontChooser", "font chooser"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"groupBox", "group"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"header", "header"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"footer", "footer"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"paragraph", "paragraph"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"ruler", "ruler"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"editBar", "edit bar"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"progressMonitor", "progress monitor"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"labelFor", "label for"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"labeledBy", "labeled by"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"memberOf", "member of"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"controllerFor", "controller for"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"controlledBy", "controlled by"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"flowsTo", "flows to"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"flowsFrom", "flows from"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"subwindowOf", "sub-window of"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"parentWindowOf", "parent window of"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"embeds", "embeds"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"embeddedBy", "embedded by"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"childNodeOf", "child node of"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"active", "active"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"pressed", "pressed"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"armed", "armed"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"busy", "busy"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"checked", "checked"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"editable", "editable"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"expandable", "expandable"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"collapsed", "collapsed"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"expanded", "expanded"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"enabled", "enabled"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"focusable", "focusable"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"focused", "focused"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"iconified", "iconified"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"modal", "modal"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"opaque", "opaque"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"resizable", "resizable"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"multiSelectable", "multi-selectable"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"selectable", "selectable"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"selected", "selected"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"showing", "showing"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"visible", "visible"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"vertical", "vertical"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"horizontal", "horizontal"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"singleLine", "singleLine"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"multiLine", "multi line"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"transient", "transient"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"managesDescendants", "manages descendants"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"indeterminate", "indeterminate"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"truncated", "truncated"}, //$NON-NLS-1$ //$NON-NLS-2$
    };

    @Override
    public Object[][] getContents() {
        return contents;
    }
}

