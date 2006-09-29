/*
 *  Copyright 2005 - 2006 The Apache Software Foundation or its licensors, as applicable.
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
 * @author Evgueni V. Brevnov
 * @version $Revision$
 */

package org.apache.harmony.awt;

import java.awt.AWTPermission;


public interface AWTPermissionCollection {

    AWTPermission ACCESS_CLIPBOARD_PERMISSION = new AWTPermission(
        "accessClipboard");

    AWTPermission ACCESS_EVENT_QUEUE_PERMISSION = new AWTPermission(
        "accessEventQueue");

    AWTPermission CREATE_ROBOT_PERMISSION = new AWTPermission("createRobot");

    AWTPermission FULL_SCREEN_EXCLUSIVE_PERMISSION = new AWTPermission(
        "fullScreenExclusive");

    AWTPermission LISTEN_TO_ALL_AWTEVENTS_PERMISSION = new AWTPermission(
        "listenToAllAWTEvents");

    AWTPermission READ_DISPLAY_PIXELS_PERMISSION = new AWTPermission(
        "readDisplayPixels");

    AWTPermission REPLACE_KEYBOARD_FOCUS_MANAGER_PERMISSION = new AWTPermission(
        "replaceKeyboardFocusManager");

    AWTPermission SET_APPLET_STUB_PERMISSION = new AWTPermission(
        "setAppletStub");

    AWTPermission SET_WINDOW_ALWAYS_ON_TOP_PERMISSION = new AWTPermission(
        "setWindowAlwaysOnTop");

    AWTPermission SHOW_WINDOW_WITHOUT_WARNING_BANNER_PERMISSION = new AWTPermission(
        "showWindowWithoutWarningBanner");

    AWTPermission WATCH_MAOUSE_POINTER_PERMISSION = new AWTPermission(
        "watchMousePointer");
}

