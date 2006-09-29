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

package javax.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleAction;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleEditableText;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleText;
import javax.accessibility.AccessibleValue;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.SpinnerUI;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.DocumentFilter;
import javax.swing.text.NumberFormatter;

import org.apache.harmony.x.swing.StringConstants;
import org.apache.harmony.x.swing.Utilities;


public class JSpinner extends JComponent implements Accessible {
    protected class AccessibleJSpinner extends JComponent.AccessibleJComponent implements AccessibleValue, AccessibleAction, AccessibleText, AccessibleEditableText, ChangeListener {
        protected AccessibleJSpinner() {
        }
        public void stateChanged(final ChangeEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public AccessibleRole getAccessibleRole() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public int getAccessibleChildrenCount() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public Accessible getAccessibleChild(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public AccessibleAction getAccessibleAction() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public AccessibleText getAccessibleText() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public AccessibleValue getAccessibleValue() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public Number getCurrentAccessibleValue() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public boolean setCurrentAccessibleValue(final Number n) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public Number getMinimumAccessibleValue() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public Number getMaximumAccessibleValue() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public int getAccessibleActionCount() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public String getAccessibleActionDescription(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public boolean doAccessibleAction(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public int getIndexAtPoint(final Point p) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public Rectangle getCharacterBounds(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public int getCharCount() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public int getCaretPosition() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public String getAtIndex(final int part, final int index) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public String getAfterIndex(final int part, final int index) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public String getBeforeIndex(final int part, final int index) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public AttributeSet getCharacterAttribute(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public int getSelectionStart() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public int getSelectionEnd() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public String getSelectedText() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public void setTextContents(final String s) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public void insertTextAtIndex(final int index,final String s) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public String getTextRange(final int startIndex, final int endIndex) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public void delete(final int startIndex, final int endIndex) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public void cut(final int startIndex, final int endIndex) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public void paste(final int startIndex) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public void replaceText(final int startIndex, final int endIndex, final String s) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public void selectText(final int startIndex, final int endIndex) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public void setAttributes(final int startIndex, final int endIndex,final AttributeSet as) {
            throw new UnsupportedOperationException("Not implemented");
        }
    }

    public static class DefaultEditor extends JPanel implements ChangeListener, PropertyChangeListener, LayoutManager {
        private JFormattedTextField text;
        private JSpinner spinner;

        public DefaultEditor(final JSpinner spinner) {
            this.spinner = spinner;
            setLayout(this);

            spinner.addChangeListener(this);

            text = new JFormattedTextField();
            text.setEditable(false);
            text.setValue(spinner.getModel().getValue());
            text.addPropertyChangeListener(this);
            text.getActionMap().put("increment", disabledAction);
            text.getActionMap().put("decrement", disabledAction);
            add(text);
        }

        public JSpinner getSpinner() {
            return spinner;
        }

        public JFormattedTextField getTextField() {
            return text;
        }

        public void stateChanged(final ChangeEvent e) {
            if (this != spinner.getEditor()) {
                return;
            }
            text.setValue(spinner.getValue());
        }

        public void propertyChange(final PropertyChangeEvent e) {
            if (e.getSource() != text || this != spinner.getEditor()) {
                return;
            }
            if (StringConstants.VALUE_PROPERTY_NAME.equals(e.getPropertyName())) {
              try {
                    spinner.setValue(e.getNewValue());
                } catch (IllegalArgumentException ex) {
                    text.setValue(e.getOldValue());
                }
            }
        }

        public void addLayoutComponent(final String name, final Component child) {
        }

        public void removeLayoutComponent(final Component child) {
        }

        public Dimension preferredLayoutSize(final Container parent) {
            return Utilities.addInsets(text.getPreferredSize(), parent.getInsets());
        }

        public Dimension minimumLayoutSize(final Container parent) {
            return Utilities.addInsets(text.getMinimumSize(), parent.getInsets());
        }

        public void layoutContainer(final Container parent) {
            text.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        }

        public void commitEdit() throws ParseException {
            text.commitEdit();
        }

        public void dismiss(final JSpinner spinner) {
            spinner.removeChangeListener(this);
        }
    }

    private static class SpinnerDateFormatter extends DateFormatter {
        private SpinnerDateModel model;

        public SpinnerDateFormatter(final SimpleDateFormat format, final SpinnerDateModel model) {
            super(format);
            this.model = model;
        }

        public void setMaximum(final Comparable max) {
            super.setMaximum(max);
            model.setEnd(max);
        }

        public void setMinimum(final Comparable min) {
            super.setMinimum(min);
            model.setStart(min);
        }

        public Comparable getMaximum() {
            Comparable max = model.getEnd();
            super.setMaximum(max);
            return max;
        }

        public Comparable getMinimum() {
            Comparable min = model.getStart();
            super.setMinimum(min);
            return min;
        }
    }

    public static class DateEditor extends DefaultEditor {
        private SimpleDateFormat format;

        public DateEditor(final JSpinner spinner) {
            super(spinner);
            if (!(spinner.getModel() instanceof SpinnerDateModel)) {
                throw new IllegalArgumentException("model not a SpinnerDateModel");
            }
            format = new SimpleDateFormat();
            initTextField();
        }

        public DateEditor(final JSpinner spinner, final String dateFormatPattern) {
            super(spinner);
            if (!(spinner.getModel() instanceof SpinnerDateModel)) {
                throw new IllegalArgumentException("model not a SpinnerDateModel");
            }
            format = new SimpleDateFormat(dateFormatPattern);
            initTextField();
        }

        public SimpleDateFormat getFormat() {
            return format;
        }

        public SpinnerDateModel getModel() {
            return (SpinnerDateModel)this.getSpinner().getModel();
        }

        private void initTextField() {
            SpinnerDateFormatter formatter = new SpinnerDateFormatter(format, getModel());
            JFormattedTextField textField = getTextField();
            textField.setFormatterFactory(new DefaultFormatterFactory(formatter));
            textField.setEditable(true);
        }
    }

    private static class SpinnerListFormatter extends AbstractFormatter {
        private class ListFilter extends DocumentFilter {
            private JFormattedTextField textField;

            public ListFilter(final JFormattedTextField textField) {
                this.textField = textField;
            }

            public void insertString(final FilterBypass fb, final int offset,
                                     final String text, final AttributeSet attrs) throws BadLocationException {
                super.insertString(fb, offset, text, attrs);
            }


            public void replace(final FilterBypass fb, final int offset, final int length,
                                final String text, final AttributeSet attrs) throws BadLocationException {

                String str = textField.getText().substring(0, offset) + text;
                String replace = findElementText(str);

                if (!"".equals(replace)) {
                    fb.replace(0, textField.getText().length(), replace, attrs);
                    textField.setCaretPosition(offset + text.length());
                    textField.moveCaretPosition(textField.getText().length());
                } else {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            private String findElementText(final String text) {
                Object findElement = findElement(text);
                if (findElement == null) {
                    return "";
                }
                String result = findElement.toString();
                return (result.indexOf(text) == 0) ? result : "";
            }
        }

        private SpinnerListModel model;
        private ListFilter filter;

        public SpinnerListFormatter(final SpinnerListModel model, final JFormattedTextField textField) {
            this.model = model;
            filter = new ListFilter(textField);
        }

        public Object stringToValue(final String text) throws ParseException {
//            Object result = findElement(text);
            return ("".equals(text)) ? null : findElement(text);
        }

        public String valueToString(final Object value) throws ParseException {
            return (value == null ? "" : value.toString());
        }

        protected DocumentFilter getDocumentFilter() {
            return filter;
        }

        private Object findElement(final String text) {
            List modelList = model.getList();
            for (int i = 0; i < modelList.size(); i++) {
                Object obj = modelList.get(i);
                if (obj != null && obj.toString().indexOf(text) == 0) {
                    return obj;
                }
            }
            return modelList.get(0);
        }
    }

    public static class ListEditor extends DefaultEditor {

        public ListEditor(final JSpinner spinner) {
            super(spinner);
            if (!(spinner.getModel() instanceof SpinnerListModel)) {
                throw new IllegalArgumentException("model not a SpinnerListModel");
            }
            SpinnerListFormatter formatter = new SpinnerListFormatter(this.getModel(), this.getTextField());
            JFormattedTextField textField = this.getTextField();
            textField.setFormatterFactory(new DefaultFormatterFactory(formatter));
            textField.setEditable(true);
        }

        public SpinnerListModel getModel() {
            return (SpinnerListModel)this.getSpinner().getModel();
        }
    }

    private static class SpinnerNumberFormatter extends NumberFormatter {
        private SpinnerNumberModel model;

        public SpinnerNumberFormatter(final DecimalFormat format, final SpinnerNumberModel model) {
            super(format);
            this.model = model;
            setValueClass(model.getValue().getClass());
        }

        public void setMaximum(final Comparable max) {
            super.setMaximum(max);
            model.setMaximum(max);
        }

        public void setMinimum(final Comparable min) {
            super.setMinimum(min);
            model.setMinimum(min);
        }

        public Comparable getMaximum() {
            Comparable max = model.getMaximum();
            super.setMaximum(max);
            return max;
        }

        public Comparable getMinimum() {
            Comparable min = model.getMinimum();
            super.setMinimum(min);
            return min;
        }
    }

    public static class NumberEditor extends DefaultEditor {
        private DecimalFormat format;

        public NumberEditor(final JSpinner spinner) {
            super(spinner);
            if (!(spinner.getModel() instanceof SpinnerNumberModel)) {
                throw new IllegalArgumentException("model not a SpinnerNumberModel");
            }
            format = new DecimalFormat();
            initTextField();
        }

        public NumberEditor(final JSpinner spinner, final String decimalFormatPattern) {
            super(spinner);
            if (!(spinner.getModel() instanceof SpinnerNumberModel)) {
                throw new IllegalArgumentException("model not a SpinnerNumberModel");
            }
            format = new DecimalFormat(decimalFormatPattern);
            initTextField();
        }

        public DecimalFormat getFormat() {
            return format;
        }

        public SpinnerNumberModel getModel() {
            return (SpinnerNumberModel)this.getSpinner().getModel();
        }

        private void initTextField() {
            final SpinnerNumberFormatter numberFormatter = new SpinnerNumberFormatter(format, this.getModel());
            JFormattedTextField textField = this.getTextField();
            textField.setFormatterFactory(new DefaultFormatterFactory(numberFormatter));
            textField.setHorizontalAlignment(SwingConstants.RIGHT);
            textField.setEditable(true);
        }
    }

    private class ModelChangeListener implements ChangeListener, Serializable {
        public void stateChanged(final ChangeEvent e) {
            fireStateChanged();
        }
    }

    private static Action disabledAction = new AbstractAction() {
        public void actionPerformed(final ActionEvent e) {
        }

        public boolean isEnabled() {
            return false;
        }
    };

    private static final String UI_CLASS_ID = "SpinnerUI";
    private SpinnerModel model;
    private boolean editorSet;
    private JComponent editor;
    private ChangeListener changeListener = new ModelChangeListener();
    private ChangeEvent changeEvent;

    public JSpinner(final SpinnerModel model) {
        this.model = model;
        model.addChangeListener(changeListener);
        editor = createEditor(model);
        updateUI();
    }

    public JSpinner() {
        this(new SpinnerNumberModel());
    }

    public SpinnerUI getUI() {
        return (SpinnerUI)ui;
    }

    public void setUI(final SpinnerUI ui) {
        super.setUI(ui);
    }

    public String getUIClassID() {
        return UI_CLASS_ID;
    }

    public void updateUI() {
        setUI((SpinnerUI)UIManager.getUI(this));
    }

    protected JComponent createEditor(final SpinnerModel model) {
        if (model instanceof SpinnerNumberModel) {
            return new NumberEditor(this);
        }
        if (model instanceof SpinnerDateModel) {
            return new DateEditor(this);
        }
        if (model instanceof SpinnerListModel) {
            return new ListEditor(this);
        }
        return new DefaultEditor(this);
    }

    public void setModel(final SpinnerModel model) {
        if (model == null) {
            throw new IllegalArgumentException("null model");
        }
        SpinnerModel oldModel = this.model;
        oldModel.removeChangeListener(changeListener);
        this.model = model;
        model.addChangeListener(changeListener);
        firePropertyChange(StringConstants.MODEL_PROPERTY_CHANGED, oldModel, model);
        if (!editorSet) {
            setEditor(createEditor(model));
            editorSet = false;
        }
    }

    public SpinnerModel getModel() {
        return model;
    }

    public Object getValue() {
        return model.getValue();
    }

    public void setValue(final Object value) {
        model.setValue(value);
    }

    public Object getNextValue() {
        return model.getNextValue();
    }

    public Object getPreviousValue() {
        return model.getPreviousValue();
    }

    public void addChangeListener(final ChangeListener listener) {
        listenerList.add(ChangeListener.class, listener);
    }

    public void removeChangeListener(final ChangeListener listener) {
        listenerList.remove(ChangeListener.class, listener);
    }

    public ChangeListener[] getChangeListeners() {
        return (ChangeListener[])getListeners(ChangeListener.class);
    }

    protected void fireStateChanged() {
        if(changeEvent == null) {
            changeEvent = new ChangeEvent(this);
        }
        ChangeListener[] listeners = getChangeListeners();
        for (int i = 0; i < listeners.length; i++) {
            listeners[i].stateChanged(changeEvent);
        }
    }

    public void setEditor(final JComponent editor) {
        if (editor == null) {
            throw new IllegalArgumentException("null editor");
        }

        JComponent oldEditor = this.editor;
        if (oldEditor == editor) {
            return;
        }

        if (oldEditor instanceof DefaultEditor) {
            DefaultEditor def = (DefaultEditor)oldEditor;
            def.dismiss(this);
        }
        this.editor = editor;
        editorSet = true;
        firePropertyChange(StringConstants.EDITOR_PROPERTY_CHANGED, oldEditor, editor);
    }

    public JComponent getEditor() {
        return editor;
    }

    public void commitEdit() throws ParseException {
        if (editor instanceof DefaultEditor) {
            ((DefaultEditor)editor).commitEdit();
        }
    }

    public AccessibleContext getAccessibleContext() {
        if (accessibleContext == null) {
            accessibleContext = new AccessibleJSpinner();
        }
        return accessibleContext;
    }
}
