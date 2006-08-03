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
 * @author Evgeniya G. Maenkova
 * @version $Revision$
 */
package javax.swing.plaf.basic;

import java.beans.PropertyChangeEvent;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.Caret;
import javax.swing.text.Element;
import javax.swing.text.Highlighter;
import javax.swing.text.View;

public class TextAreaUI extends BasicTextAreaUI {
    boolean propertyChangeFlag = false;
    String eventName = "";
    boolean flagModelChanged = false;
    boolean flagCreate = false;
    static String callOrder = "";

    public static ComponentUI createUI(final JComponent arg0) {
        callOrder += "createUI::";
        return new TextAreaUI();
    }

    public View create(final Element elem) {
        callOrder += "create::";
        flagCreate = true;
        return super.create(elem);
    }


    protected Highlighter createHighlighter() {
        callOrder += "createHighlighter::";
        return super.createHighlighter();
    }

    protected Caret createCaret() {
        callOrder += "createCaret::";
        return super.createCaret();
    }
    private String content() {
        Object a = getComponent().getInputMap().getParent();
        return  (" " +  " \n    " + a + ((a == null)? (Object)"" :
            getComponent().getInputMap().getParent().getParent()));
    }

    protected void installDefaults() {
        callOrder += "installDefaults::";
        super.installDefaults();

    }

    protected void installKeyboardActions() {
        callOrder += "installKeyboardActions::";
        super.installKeyboardActions();
    }

    protected void installListeners() {
        callOrder += "installListeners::";
        super.installListeners();
    }

    public void installUI(final JComponent c) {
        callOrder += "installUI::";
        super.installUI(c);
    }

    protected void uninstallDefaults() {
        callOrder += "uninstallDefaults::";
        super.uninstallDefaults();
    }

    protected void uninstallKeyboardActions() {
        callOrder += "uninstallKeyboardActions::";
        super.uninstallKeyboardActions();
    }

    protected void uninstallListeners() {
        callOrder += "uninstallListeners::";
        super.uninstallListeners();
    }

    public void uninstallUI(final JComponent c) {
        callOrder += "uninstallUI::";
        super.uninstallUI(c);
    }

    protected void propertyChange(final PropertyChangeEvent e) {
        eventName = e.getPropertyName();
        propertyChangeFlag = true;
        super.propertyChange(e);
    }

    protected void modelChanged() {
        callOrder += "modelChanged::";
        flagModelChanged = true;
        super.modelChanged();
    }

}