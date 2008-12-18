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

public class AccessibleState extends AccessibleBundle {
    public static final AccessibleState ACTIVE = new AccessibleState("active"); //$NON-NLS-1$
    public static final AccessibleState PRESSED = new AccessibleState("pressed"); //$NON-NLS-1$
    public static final AccessibleState ARMED = new AccessibleState("armed"); //$NON-NLS-1$
    public static final AccessibleState BUSY = new AccessibleState("busy"); //$NON-NLS-1$
    public static final AccessibleState CHECKED = new AccessibleState("checked"); //$NON-NLS-1$
    public static final AccessibleState EDITABLE = new AccessibleState("editable"); //$NON-NLS-1$
    public static final AccessibleState EXPANDABLE = new AccessibleState("expandable"); //$NON-NLS-1$
    public static final AccessibleState COLLAPSED = new AccessibleState("collapsed"); //$NON-NLS-1$
    public static final AccessibleState EXPANDED = new AccessibleState("expanded"); //$NON-NLS-1$
    public static final AccessibleState ENABLED = new AccessibleState("enabled"); //$NON-NLS-1$
    public static final AccessibleState FOCUSABLE = new AccessibleState("focusable"); //$NON-NLS-1$
    public static final AccessibleState FOCUSED = new AccessibleState("focused"); //$NON-NLS-1$
    public static final AccessibleState ICONIFIED = new AccessibleState("iconified"); //$NON-NLS-1$
    public static final AccessibleState MODAL = new AccessibleState("modal"); //$NON-NLS-1$
    public static final AccessibleState OPAQUE = new AccessibleState("opaque"); //$NON-NLS-1$
    public static final AccessibleState RESIZABLE = new AccessibleState("resizable"); //$NON-NLS-1$
    public static final AccessibleState MULTISELECTABLE = new AccessibleState("multiSelectable"); //$NON-NLS-1$
    public static final AccessibleState SELECTABLE = new AccessibleState("selectable"); //$NON-NLS-1$
    public static final AccessibleState SELECTED = new AccessibleState("selected"); //$NON-NLS-1$
    public static final AccessibleState SHOWING = new AccessibleState("showing"); //$NON-NLS-1$
    public static final AccessibleState VISIBLE = new AccessibleState("visible"); //$NON-NLS-1$
    public static final AccessibleState VERTICAL = new AccessibleState("vertical"); //$NON-NLS-1$
    public static final AccessibleState HORIZONTAL = new AccessibleState("horizontal"); //$NON-NLS-1$
    public static final AccessibleState SINGLE_LINE = new AccessibleState("singleLine"); //$NON-NLS-1$
    public static final AccessibleState MULTI_LINE = new AccessibleState("multiLine"); //$NON-NLS-1$
    public static final AccessibleState TRANSIENT = new AccessibleState("transient"); //$NON-NLS-1$
    public static final AccessibleState MANAGES_DESCENDANTS = new AccessibleState("managesDescendants"); //$NON-NLS-1$
    public static final AccessibleState INDETERMINATE = new AccessibleState("indeterminate"); //$NON-NLS-1$
    public static final AccessibleState TRUNCATED = new AccessibleState("truncated"); //$NON-NLS-1$

    protected AccessibleState(final String key) {
        this.key = key;
    }
}

