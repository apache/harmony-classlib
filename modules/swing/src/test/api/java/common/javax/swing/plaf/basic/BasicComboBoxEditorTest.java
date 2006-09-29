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
 * @author Anton Avtamonov
 * @version $Revision$
 */
package javax.swing.plaf.basic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingTestCase;

public class BasicComboBoxEditorTest extends SwingTestCase {
    private BasicComboBoxEditor editor;

    public BasicComboBoxEditorTest(final String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        editor = new BasicComboBoxEditor();
    }

    protected void tearDown() throws Exception {
        editor = null;
    }

    public void testBasicComboBoxEditor() throws Exception {
        assertNotNull(editor.editor);
    }

    public void testAddRemoveActionListener() throws Exception {
        ActionController controller = new ActionController();
        assertEquals(0, editor.editor.getActionListeners().length);
        editor.addActionListener(controller);
        assertEquals(1, editor.editor.getActionListeners().length);

        editor.addActionListener(new ActionController());
        assertEquals(2, editor.editor.getActionListeners().length);

        editor.removeActionListener(controller);
        assertEquals(1, editor.editor.getActionListeners().length);
    }

    public void testGetEditorComponent() throws Exception {
        assertNotNull(editor.getEditorComponent());
        assertEquals(editor.editor, editor.getEditorComponent());
        assertTrue(editor.getEditorComponent() instanceof JTextField);
    }

    public void testGetSetItem() throws Exception {
        assertEquals("", editor.getItem());
        assertEquals("", editor.editor.getText());

        Object item = "any";
        editor.setItem(item);
        assertEquals(item, editor.getItem());
        assertEquals(item, editor.editor.getText());

        item = "another";
        editor.editor.setText(item.toString());
        assertEquals(item, editor.editor.getText());
        assertEquals(item, editor.getItem());
    }

    public void testSelectAll() throws Exception {
        assertNull(editor.editor.getSelectedText());

        editor.setItem("any");
        assertNull(editor.editor.getSelectedText());
        editor.selectAll();
        assertEquals("any", editor.editor.getSelectedText());
    }


    private class ActionController implements ActionListener {
        public void actionPerformed(final ActionEvent e) {
        }
    }
}
