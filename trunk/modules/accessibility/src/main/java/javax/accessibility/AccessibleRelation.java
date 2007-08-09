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


public class AccessibleRelation extends AccessibleBundle {
    public static final String LABEL_FOR = "labelFor"; //$NON-NLS-1$
    public static final String LABELED_BY = "labeledBy"; //$NON-NLS-1$
    public static final String MEMBER_OF = "memberOf"; //$NON-NLS-1$
    public static final String CONTROLLER_FOR = "controllerFor"; //$NON-NLS-1$
    public static final String CONTROLLED_BY = "controlledBy"; //$NON-NLS-1$
    public static final String FLOWS_TO = "flowsTo"; //$NON-NLS-1$
    public static final String FLOWS_FROM = "flowsFrom"; //$NON-NLS-1$
    public static final String SUBWINDOW_OF = "subwindowOf"; //$NON-NLS-1$
    public static final String PARENT_WINDOW_OF = "parentWindowOf"; //$NON-NLS-1$
    public static final String EMBEDS = "embeds"; //$NON-NLS-1$
    public static final String EMBEDDED_BY = "embeddedBy"; //$NON-NLS-1$
    public static final String CHILD_NODE_OF = "childNodeOf"; //$NON-NLS-1$
    public static final String LABEL_FOR_PROPERTY = "labelForProperty"; //$NON-NLS-1$
    public static final String LABELED_BY_PROPERTY = "labeledByProperty"; //$NON-NLS-1$
    public static final String MEMBER_OF_PROPERTY = "memberOfProperty"; //$NON-NLS-1$
    public static final String CONTROLLER_FOR_PROPERTY = "controllerForProperty"; //$NON-NLS-1$
    public static final String CONTROLLED_BY_PROPERTY = "controlledByProperty"; //$NON-NLS-1$
    public static final String FLOWS_TO_PROPERTY = "flowsToProperty"; //$NON-NLS-1$
    public static final String FLOWS_FROM_PROPERTY = "flowsFromProperty"; //$NON-NLS-1$
    public static final String SUBWINDOW_OF_PROPERTY = "subwindowOfProperty"; //$NON-NLS-1$
    public static final String PARENT_WINDOW_OF_PROPERTY = "parentWindowOfProperty"; //$NON-NLS-1$
    public static final String EMBEDS_PROPERTY = "embedsProperty"; //$NON-NLS-1$
    public static final String EMBEDDED_BY_PROPERTY = "embeddedByProperty"; //$NON-NLS-1$
    public static final String CHILD_NODE_OF_PROPERTY = "childNodeOfProperty"; //$NON-NLS-1$

    private Object[] targets;

    public AccessibleRelation(final String key) {
        this.key = key;
        targets = new Object[0];
    }

    public AccessibleRelation(final String key, final Object target) {
        this.key = key;
        setTarget(target);
    }

    public AccessibleRelation(final String key, final Object[] target) {
        this.key = key;
        setTarget(target);
    }

    public String getKey() {
        return key;
    }

    public Object[] getTarget() {
        return targets.clone();
    }

    public void setTarget(final Object target) {
        targets = new Object[]{target};
    }

    public void setTarget(final Object[] target) {
        targets = target != null ? (Object[])target.clone() : new Object[0];
    }
}

